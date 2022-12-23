package net.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

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

	/**
	 * 採番(テーブルの情報ID用)[Numbering (for table information ID)]<br>
	 * @author officina-hide.net
	 * @since 2022/11/16 Ver. 1.00
	 * @param tableId テーブル情報ID
	 * @return 採番情報
	 */
	public long getNewKey(long tableId) {
		long id = 0;
		long numberingId = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM " + Table_Name + " "
					+ "WHERE " + COLUMNNAME_FD_Table_ID + " = ? ";
			connection(env);
			pstmt = getConn().prepareStatement(sql);
			pstmt.setLong(1, tableId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getLong(COLUMNNAME_FD_CurrentNumber);
				numberingId = rs.getLong(COLUMNNAME_FD_Numbering_ID);
			} else {
				return 0;
			}
			pstmt.close();
			//採番情報更新
			sql = "UPDATE " + Table_Name + " SET "
					+ COLUMNNAME_FD_CurrentNumber + " = ? " + ","
					+ COLUMNNAME_Updated + " = ? " + ","
					+ COLUMNNAME_UpdatedBy + " = ? "
					+ "WHERE " + COLUMNNAME_FD_Numbering_ID + " =  ? ";
			pstmt = getConn().prepareStatement(sql);
			pstmt.setLong(1, id + 1);
			pstmt.setTimestamp(2, new Timestamp(new Date().getTime()));
			pstmt.setLong(3, 100);
			pstmt.setLong(4, numberingId);
			int cnt = pstmt.executeUpdate();
			if(cnt != 1) {
				System.out.println("Error!! : Update FD_Numbering");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		return id;
	}

	/**
	 * テーブル情報項目登録[Table information item registration]<br>
	 * @author officina-hide.net
	 * @since 2022/12/20 Ver. 1.00
	 */
	public void addColumnData() {
		FD_Column column = new FD_Column(env);
		column.addData(Table_ID, ADD_COLUMN_FD_COLUMN_ID, COLUMN_TYPE_ID_KEY_NAME);
		column.addData(Table_ID, ADD_COLUMN_FD_TABLE_ID, COLUMN_TYPE_ID_KEY_NAME);
		column.addData(Table_ID, ADD_COLUMN_FD_InitialNumber, COLUMN_TYPE_UNSIGNED_BIGINT_NAME);
	}

}
