package net.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * テーブル項目クラス[Table column class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/11/11 Ver. 1.00
 */
public class FD_Column extends FD_DB implements I_FD_Column {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2022/11/11 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FD_Column(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * テーブル項目情報初期化[Initialize table column information]
	 * @author officina-hide.net
	 * @since 2022/11/11 Ver. 1.00
	 */
	public void initialize() {
		connection(env);
		PreparedStatement pstmt = null;
		try {
			pstmt = getConn().prepareStatement(SQL_DROP_FD_TABLE);
			pstmt.execute();
			DBClose(pstmt, null);
			pstmt = getConn().prepareStatement(SQL_CREATE_FD_COLUMN);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

}
