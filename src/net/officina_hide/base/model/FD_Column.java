package net.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

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
	 * 情報登録(SQL Value句指定)[Information registration (SQL Value clause specification)]
	 * @author officina-hide.net
	 * @since 2022/11/16 Ver. 1.00
	 * @param tableId テーブル情報ID[Table information ID]
	 * @param value SQL値部分[SQL value part]<br>
	 * @since 2022/11/16 Ver. 1.00
	 */
	public void addData(long tableId, String value) {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();

		sql.append("INSERT INTO ").append(Table_Name).append("(");
		sql.append(COLUMNNAME_FD_Column_ID).append(",");
		sql.append(COLUMNNAME_FD_Table_ID).append(",");
		sql.append(COLUMNNAME_FD_Column_Name).append(",");
		sql.append(COLUMNNAME_FD_Name).append(",");
		sql.append(SQL_COMMON_COLUMN_LIST);
		sql.append(")");
		sql.append(" VALUES ").append("(");
		sql.append(value);
		sql.append(")");
		
		try {
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			//採番
			FD_Numbering num = new FD_Numbering(env);
			long id = num.getNewKey(I_FD_Column.Table_ID);
			pstmt.setLong(1, id);
			pstmt.setLong(2, tableId);
			pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
			pstmt.setLong(4, 100);
			pstmt.setTimestamp(5, new Timestamp(new Date().getTime()));
			pstmt.setLong(6, 100);
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
