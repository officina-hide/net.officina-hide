package net.officina_hide.base.initial;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
		HBox dbServerNameBox = new HBox(5);
		dbServerNameBox.setAlignment(Pos.CENTER_LEFT);
		Label lb01 = new Label("データベース・サーバー名");
		TextField tx01 = new TextField("");
		dbServerNameBox.getChildren().addAll(lb01, tx01);
		
		root.getChildren().addAll(dbServerNameBox);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
