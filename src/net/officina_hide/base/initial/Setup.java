package net.officina_hide.base.initial;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.officina_hide.base.control.DBConnectTest;
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
public class Setup extends Application {

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
	
	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox(5);
		root.setPadding(new Insets(10, 10, 10, 10));
		setItem(root);
		Scene scene = new Scene(root, 500, 600);
		stage.setScene(scene);
		stage.setTitle("初期設定");
		stage.show();
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
		
		HBox buttonBox = new HBox(5);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		Button textButton = new Button("テスト");
		textButton.setOnAction(event->{
			//接続テスト
			DBConnectTest dct = new DBConnectTest();
			dct.setSetverName(dbServerName.getText());
			dct.setDbName(dbName.getText());
			dct.setDbPort(dbPort.getText());
			dct.setUserId(dbUserId.getText());
			dct.setPassword(dbPassword.getText());
			if(dct.connectTest() == true) {
				System.out.println("接続OK");
			}
		});
		buttonBox.getChildren().add(textButton);
		
		root.getChildren().addAll(dbServerName.getNode(), dbName.getNode(), dbPort.getNode(),
				dbUserId.getNode(), dbPassword.getNode(), buttonBox);
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
