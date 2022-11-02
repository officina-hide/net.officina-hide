package net.officina_hide.base.initial;

import java.util.Date;

/**
 * データベース初期化[database initialization]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/11/02 Ver. 1.00
 */
public class FD_DBInitial {

	/**
	 * 初期化実行[Initialization execution]<br>
	 * @author officina-hide.net
	 * @since 2022/11/02 Ver. 1.00
	 */
	public void execute() {
		System.out.println("データベース初期化開始 : "+new Date());
		//テーブル情報
		//カラム情報
		//採番情報
		
		System.out.println("データベース初期化終了 : "+new Date());
	}
}
