package net.officina_hide.base.initial;

import java.util.Date;

import net.officina_hide.base.model.FD_EnvData;
import net.officina_hide.base.model.FD_Table;
import net.officina_hide.base.model.I_FD_Table;

/**
 * データベース初期化[database initialization]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/11/02 Ver. 1.00
 */
public class FD_DBInitial {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;
	
	public FD_DBInitial() {
	}
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/11/07 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FD_DBInitial(FD_EnvData env) {
		this.env = env;	
	}

	/**
	 * 初期化実行[Initialization execution]<br>
	 * @author officina-hide.net
	 * @since 2022/11/02 Ver. 1.00
	 */
	public void execute() {
		System.out.println("データベース初期化開始 : "+new Date());
		FD_Table table = new FD_Table(env);
		//テーブル情報テーブル構築
		table.initialize();
		//テーブル情報登録
		X_FD_Table xtable = new X_FD_Table(env, 0);
		xtable.setFD_Table_ID(I_FD_Table.Table_ID);
		xtable.setFD_Table_Name(I_FD_Table.Table_Name);
		xtable.setFD_Name(I_FD_Table.Table_Comment);
		xtable.save();
		//カラム情報
		//採番情報
		
		System.out.println("データベース初期化終了 : "+new Date());
	}
}
