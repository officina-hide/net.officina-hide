package net.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

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
	 * 情報登録(SQL Value句指定)[Information registration (SQL Value clause specification)]
	 * @author officina-hide.net
	 * @since 2022/11/16 Ver. 1.00
	 * @param value SQL値部分[SQL value part]<br>
	 * @author officina-hide.net
	 * @since 2022/11/16 Ver. 1.00
	 */
	public void addData(String value) {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO ").append(Table_Name).append("(");
		sql.append(COLUMNNAME_FD_Table_ID).append(",");
		sql.append(COLUMNNAME_FD_Table_Name).append(",");
		sql.append(COLUMNNAME_FD_Name).append(",");
		sql.append(SQL_COMMON_COLUMN_LIST);
		sql.append(")");
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

	/**
	 * テーブル情報項目登録[Table information item registration]<br>
	 * @author officina-hide.net
	 * @since 2022/11/16 Ver. 1.00
	 */
	public void addColumnData() {
		FD_Column column = new FD_Column(env);
		column.addData(Table_ID, ADD_COLUMN_FD_TABLE_ID);
		column.addData(Table_ID, ADD_COLUMN_FD_TABLE_Name);
		column.addData(Table_ID, ADD_COMMON_COLUMN_Created);
		column.addData(Table_ID, ADD_COMMON_COLUMN_CreatedBy);
		column.addData(Table_ID, ADD_COMMON_COLUMN_Updated);
		column.addData(Table_ID, ADD_COMMON_COLUMN_UpdatedBy);
	}

}
