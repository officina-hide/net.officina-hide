package net.officina_hide.base.initial;

import javafx.application.Application;
import javafx.stage.Stage;
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
	final String envFile = "./Env.prop";
	
	@Override
	public void start(Stage stage) throws Exception {
		//環境情報取得
		env = new FD_EnvData(envFile);
		
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
