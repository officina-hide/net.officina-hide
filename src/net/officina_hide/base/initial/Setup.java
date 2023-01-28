package net.officina_hide.base.initial;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.officina_hide.base.control.DBConnectTest;
import net.officina_hide.base.model.FD_Check;
import net.officina_hide.base.model.FD_DB;
import net.officina_hide.base.model.FD_EnvData;
import net.officina_hide.base.model.I_FD_DB;
import net.officina_hide.base.model.PasswordText;
import net.officina_hide.base.model.SingleText;
import net.officina_hide.base.model.StatusBar;
import net.officina_hide.base.model.VC_ViewItem;

/**
 * プロジェクト動作用の環境セット[environment set for project operation]<br>
 * 環境プロパティを読み込んで画面に表示する。<br> 
 * 画面上で以下のチェックを実施する。
 * 　　・DB接続テスト
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]作成
 */
public class Setup extends Application implements I_FD_DB {

	/** 画面項目クラス */
	VC_ViewItem vvi = new VC_ViewItem();
	/** データベースサーバー名 */
	private SingleText dbServerName;
	/** データベース名 */
	private SingleText dbName;
	/** データベースポート */
	private SingleText dbPort;
	/** データベースユーザーID */
	private SingleText dbUserId;
	/** データベースパスワード */
	private PasswordText dbPassword;
	/** DB初期化チェック */
	private FD_Check dbExsistsCheck;
	/** 保存ボタン */
	private Button saveButton;
	/** ステータスバー[Status bar] */
	private StatusBar setupStatus;
	/** データベース初期化ボタン[Database initialization button] */
	private Button dbInitButton;
	
	/** DBクラス */
	private FD_DB DB = new FD_DB();
	/** 環境情報 */
	private FD_EnvData env = null;
	
	@Override
	public void start(Stage stage) {
		
		VBox root = new VBox(5);
		root.setPadding(new Insets(10, 10, 10, 10));
		setItem(root);
		Scene scene = new Scene(root, 500, 300);
		stage.setScene(scene);
		stage.setTitle("初期設定");

		//設定情報取得
		env = getEnvData();
		
		stage.show();
	}

	/**
	 * 設定情報取得[Get setting information]<br>
	 * @author officina-hide.net
	 * @return 
	 * @since 2022/10/17 Ver. 1.00
	 */
	private FD_EnvData getEnvData() {
		//環境情報取得
		env = new FD_EnvData("./env.prop");
		//画面項目セット
		dbServerName.setText(env.getDbServerName());
		dbName.setText(env.getDbName());
		dbPort.setText(env.getDbPort());
		dbUserId.setText(env.getDbUserID());
		dbPassword.setText(env.getDbPassword());
		return env;
	}

	/**
	 * 画面項目設定[Screen item settings]<br>
	 * @param root ルート
	 */
	private void setItem(VBox root) {
		//データベース項目
		dbServerName = new SingleText("データベース・サーバー名");
		dbName = new SingleText("データベース名");
		dbPort = new SingleText("ポート");
		dbUserId = new SingleText("ユーザーID");
		dbPassword = new PasswordText("パスワード");
		dbExsistsCheck = new FD_Check("DB作成済み");
		setupStatus = new StatusBar();
		
		HBox buttonBox = new HBox(5);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		Button testButton = new Button("テスト");
		testButton.setOnAction(event->{
			//接続テスト
			if(checkDBConnection() == true) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("接続OK");
				alert.showAndWait();
				saveButton.setDisable(false);
				dbInitButton.setDisable(false);
				//DB初期設定確認
				setEnv();
				if(DB.isExistsTable(env, "FD_Table") == true) {
					System.out.println("テーブル有り");
					dbExsistsCheck.setCheck(true);
				} else {
					System.out.println("テーブル無し");
					dbExsistsCheck.setCheck(false);
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("接続エラー");
				alert.showAndWait();
				saveButton.setDisable(true);
			}
		});
		saveButton = new Button("保存");
		saveButton.setDisable(true);
		saveButton.setOnAction(event->{
			//パラメータ保存
			try {
				Properties prop = new Properties();
				prop.setProperty(DB_SERVER_NAME, dbServerName.getText());
				prop.setProperty(DB_NAME, dbName.getText());
				prop.setProperty(DB_PORT, dbPort.getText());
				prop.setProperty(DB_USER_ID, dbUserId.getText());
				prop.setProperty(DB_USER_PASSWORD, dbPassword.getText());
				File propFile = new File("./env.prop");
				FileOutputStream os = new FileOutputStream(propFile);
				prop.store(os, "Environment Data");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		dbInitButton = new Button("DB初期化");
		dbInitButton.setDisable(true);
		dbInitButton.setOnAction(event->{
			try {
				String className = "net.officina_hide.base.initial.FD_DBInitial";
				Class<?> clazz = Class.forName(className);
				Object obj = clazz.getDeclaredConstructor(FD_EnvData.class).newInstance(env);
				Method execute = clazz.getMethod("execute");
				execute.invoke(obj);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
					IllegalArgumentException | InvocationTargetException | NoSuchMethodException |
					SecurityException e) {
				e.printStackTrace();
			}
		});
		
		buttonBox.getChildren().addAll(testButton, saveButton, dbInitButton);
		
		root.getChildren().addAll(dbServerName.getNode(), dbName.getNode(), dbPort.getNode(),
				dbUserId.getNode(), dbPassword.getNode(), dbExsistsCheck.getNode(), buttonBox,
				setupStatus.getNode());
		
	}

	/**
	 * 環境情報セット[Environment information set]
	 * @author officina-hide.net
	 * @since 2022/10/26 Ver 1.00
	 */
	private void setEnv() {
		env.setDbServerName(dbServerName.getText());
		env.setDbName(dbName.getText());
		env.setDbPort(dbPort.getText());
		env.setDbUserID(dbUserId.getText());
		env.setDbPassword(dbPassword.getText());
	}

	/**
	 * データベース接続テスト[Database connection test]
	 * @author officina-hide.net
	 * @since 2022/10/22 Ver. 1.00
	 * @return True - 接続OK, false - 接続エラー
	 */
	private boolean checkDBConnection() {
		boolean chk = false;
		DBConnectTest dct = new DBConnectTest();
		dct.setSetverName(dbServerName.getText());
		dct.setDbName(dbName.getText());
		dct.setDbPort(dbPort.getText());
		dct.setUserId(dbUserId.getText());
		dct.setPassword(dbPassword.getText());
		chk = dct.connectTest();
		return chk;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
