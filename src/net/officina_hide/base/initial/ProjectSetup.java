package net.officina_hide.base.initial;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.officina_hide.base.control.FD_Button;
import net.officina_hide.base.control.FD_DispWithButton;
import net.officina_hide.base.model.FD_Check;
import net.officina_hide.base.model.FD_DB;
import net.officina_hide.base.model.FD_EnvData;

/**
 * プロジェクト初期化画面
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2023/01/24 Ver. 1.00
 */
public class ProjectSetup extends Application {

	/** DBクラス */
	private FD_DB DB = new FD_DB();

	//環境情報
	private FD_EnvData env = null;
	//環境プロパティファイル
	final String envFile = "Env.prop";
	//画面項目
	private FD_Check connectDB;
	//プロジェクト情報
	private FD_DispWithButton projectInfo;
	/** 初期化ボタン */
	private FD_Button initialProject;
	
	@Override
	public void start(Stage stage) throws Exception {
		//環境情報取得
		env = new FD_EnvData(envFile);
		//項目設定
		VBox root = new VBox(5);
		root.setPadding(new Insets(10, 10, 10, 10));
		setItem(root);
		Scene scene = new Scene(root, 500, 300);
		stage.setScene(scene);
		stage.setTitle("プロジェクト初期設定");
		
		//データベース接続確認
		if(DB.isExistsTable(env, "FD_Table") == true) {
			connectDB.setCheck(true);
		}
		//プロジェクトテーブル確認
		
		
		stage.show();
	}

	/**
	 * 画面項目設定[Screen item settings]<br>
	 * @author officina-hide.net
	 * @since 2023/01/27 Ver. 1.00
	 * @param root
	 */
	private void setItem(VBox root) {
		connectDB = new FD_Check("データベース接続済");
		projectInfo = new FD_DispWithButton("プロジェクト情報");
		projectInfo.setDispText("test");
		initialProject = new FD_Button(env, "プロジェクト初期化");
		initialProject.setClassName("net.officina_hide.project.tools.ProjectInitialize");
		
		root.getChildren().addAll(connectDB.getNode(), projectInfo.getNode(), initialProject);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
