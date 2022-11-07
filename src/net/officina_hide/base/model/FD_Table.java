package net.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * テーブル情報クラス[Table information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/11/07 Ver. 1.00
 */
public class FD_Table extends FD_DB implements I_FD_Table {

	/** 環境情報[Environment information] */
	private FD_EnvData env;
	
	public FD_Table(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * テーブル情報初期化[Initialize table information]<br>
	 * <p>テーブル情報テーブルの構築、基盤テーブル情報の登録</p>
	 * <p>Construction of table information table, registration of base table information</p>
	 * @author officina-hide.net
	 * @since 2022/11/07 Ver. 1.00
	 */
	public void initialize() {
		connection(env);
		PreparedStatement pstmt = null;
		try {
			pstmt = getConn().prepareStatement(SQL_DROP_FD_TABLE);
			pstmt.execute();
			DBClose(pstmt, null);
			pstmt = getConn().prepareStatement(SQL_CREATE_FD_TABLE);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

}
