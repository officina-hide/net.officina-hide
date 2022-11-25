package net.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 参照情報クラス[Reference information class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/11/24 Ver. 1.00
 */
public class FD_Reference extends FD_DB implements I_FD_Reference {

	/** 環境情報[Environment information] */
	private FD_EnvData env;
	
	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2022/11/24 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public FD_Reference(FD_EnvData env) {
		this.env = env;
	}

	/**
	 * 参照グループ情報の登録<br>
	 * @author officina-hide.net
	 * @since 2022/11/24 Ver. 1.00
	 */
	public void addRefGroupData() {
		addData(ADD_Reference_Group_ColumnType);
	}

	/**
	 * 情報登録[Save data]
	 * @param refrenceId 参照情報ID
	 * @param referenceName 参照名
	 * @param name 名前
	 * @param refGroupId 参照グループ情報ID
	 */
	private void addData(String value) {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO ").append(Table_Name).append("(");
		sql.append(COLUMNNAME_FD_Reference_ID).append(",");
		sql.append(COLUMNNAME_FD_Reference_Name).append(",");
		sql.append(COLUMNNAME_FD_Name).append(",");
		sql.append(COLUMNNAME_FD_ReferenceGroup_ID).append(",");
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

}
