package net.officina_hide.project.tools;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.officina_hide.base.model.FD_EnvData;

/**
 * プロジェクト更新画面[Project update screen]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2023/03/18 Ver. 1.00 
 */
public class FV_Project extends Application {
	//環境情報
	private FD_EnvData env = null;
	//環境プロパティファイル
	final String envFile = "Env.prop";

	@Override
	public void start(Stage stage) throws Exception {
		//環境情報取得
		env = new FD_EnvData(envFile);
		//項目設定
		VBox root = new VBox(5);
		root.setPadding(new Insets(10, 10, 10, 10));
		setItem(root);

		stage.show();
	}

	/**
	 * 項目設定[Setting screen items]
	 * @author officina-hide.net
	 * @since 2023/03/18 Ver. 1.00
	 * @param root ルート
	 */
	private void setItem(VBox root) {
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
