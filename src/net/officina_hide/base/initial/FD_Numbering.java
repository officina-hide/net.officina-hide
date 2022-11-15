package net.officina_hide.base.initial;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import net.officina_hide.base.model.FD_DB;
import net.officina_hide.base.model.FD_EnvData;
import net.officina_hide.base.model.I_FD_Numbering;

/**
 * 採番情報クラス[Numbering information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/11/14 Ver. 1.00
 */
public class FD_Numbering extends FD_DB implements I_FD_Numbering {
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;

	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/11/14 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FD_Numbering(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 情報登録(SQL Value句指定)[Information registration (SQL Value clause specification)]
	 * @author officina-hide.net
	 * @since 2022/11/15 Ver. 1.00
	 * @param value SQL値部分[SQL value part]<br>
	 * @author officina-hide.net
	 * @since 2022/11/15 Ver. 1.00
	 */
	public void addData(String value) {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ").append(Table_Name).append("(");
		sql.append(COLUMNNAME_FD_Numbering_ID).append(",");
		sql.append(COLUMNNAME_FD_Table_ID).append(",");
		sql.append(COLUMNNAME_FD_InitialNumber).append(",");
		sql.append(COLUMNNAME_FD_CurrentNumber).append(",");
		sql.append(SQL_COMMON_COLUMN_LIST).append(")");
		sql.append(" VALUES ").append("(");
		sql.append(value);
		sql.append(")");
		
		try {
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
			pstmt.setLong(2, 100);
			pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
			pstmt.setLong(4, 100);
			int rs = pstmt.executeUpdate();
			if(rs != 1) {
				System.out.println("Insert Error : "+sql.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

}
