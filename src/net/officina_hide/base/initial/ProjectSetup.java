package net.officina_hide.base.initial;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.officina_hide.base.model.FD_Check;
import net.officina_hide.base.model.FD_EnvData;

/**
 * プロジェクト初期化画面
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2023/01/24 Ver. 1.00
 */
public class ProjectSetup extends Application {

	//環境情報
	private FD_EnvData env = null;
	//環境プロパティファイル
	final String envFile = "Env.prop";
	//画面項目
	private FD_Check connectDB;
	
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
		
		root.getChildren().addAll(connectDB.getNode());
	}

	public static void main(String[] args) {
		launch(args);
	}

}
