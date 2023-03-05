package net.officina_hide.project.tools;

import java.util.Date;

import net.officina_hide.base.model.FD_EnvData;
import net.officina_hide.project.model.FD_Project;

/**
 * プロジェクト環境初期設定[Initial project environment settings]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2023/02/18 Ver. 1.00
 */
public class ProjectInitialize {

	/**
	 * 初期設定ボタンクリック[Initial setting button clicked]
	 * @author officina-hide.net
	 * @since 2023/02/18 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void onClick(FD_EnvData env) {
		//開始メッセージ[Start message]
		System.out.println("プロジェクト初期設定開始 : "+new Date());
		//プロジェクト情報テーブル
		FD_Project project = new FD_Project();
		project.create(env);

	
		System.out.println("プロジェクト初期設定終了 : "+new Date());
}
}
