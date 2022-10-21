package net.officina_hide.base.initial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.officina_hide.base.control.DBConnectTest;
import net.officina_hide.base.model.FDCheck;
import net.officina_hide.base.model.I_FD_DB;
import net.officina_hide.base.model.PasswordText;
import net.officina_hide.base.model.SingleText;
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
	private FDCheck dbExsistsCheck;
	/** 保存ボタン */
	private Button saveButton;
	
	@Override
	public void start(Stage stage) {
		
		VBox root = new VBox(5);
		root.setPadding(new Insets(10, 10, 10, 10));
		setItem(root);
		Scene scene = new Scene(root, 500, 600);
		stage.setScene(scene);
		stage.setTitle("初期設定");

		//設定情報取得
		getEnvData();
		
		stage.show();
	}

	/**
	 * 設定情報取得[Get setting information]<br>
	 * @author officina-hide.net
	 * @since 2022/10/17 Ver. 1.00
	 */
	private void getEnvData() {
		try {
			File propFile = new File("./Env.prop");
			FileInputStream is = new FileInputStream(propFile);
			Properties prop = new Properties();
			prop.load(is);
			dbServerName.setText(prop.getProperty(DB_SERVER_NAME));
			dbName.setText(prop.getProperty(DB_NAME));
			dbPort.setText(prop.getProperty(DB_PORT));
			dbUserId.setText(prop.getProperty(DB_USER_ID));
			dbPassword.setText(prop.getProperty(DB_USER_PASSWORD));
		} catch (IOException e) {
			//何もしない
		}
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
		dbExsistsCheck = new FDCheck("DB作成済み");
		
		HBox buttonBox = new HBox(5);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		Button testButton = new Button("テスト");
		testButton.setOnAction(event->{
			//接続テスト
			DBConnectTest dct = new DBConnectTest();
			dct.setSetverName(dbServerName.getText());
			dct.setDbName(dbName.getText());
			dct.setDbPort(dbPort.getText());
			dct.setUserId(dbUserId.getText());
			dct.setPassword(dbPassword.getText());
			if(dct.connectTest() == true) {
				System.out.println("接続OK");
				saveButton.setDisable(false);
			} else {
				System.out.println("接続エラー");
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
		
		buttonBox.getChildren().addAll(testButton, saveButton);
		
		root.getChildren().addAll(dbServerName.getNode(), dbName.getNode(), dbPort.getNode(),
				dbUserId.getNode(), dbPassword.getNode(), buttonBox);
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
