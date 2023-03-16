package net.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	 * @deprecated
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

	/**
	 * 情報登録(SQL Value句指定)[Information registration (SQL Value clause specification)]
	 * @author officina-hide.net
	 * @since 2022/11/16 Ver. 1.00 新規作成[New create]
	 * @since 2022/12/05 Ver. 1.01 項目属性追加
	 * @param tableId テーブル情報ID[Table information ID]
	 * @param value SQL値部分[SQL value part]<br>
	 * @param columnType 項目属性名[Attribute name]
	 */
	public void addData(long tableId, String value, String columnType) {
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();

		//項目属性ID取得
		FD_Reference ref = new FD_Reference(env);
		long columnTypeId = ref.getID(columnType);
		//採番
		FD_Numbering num = new FD_Numbering(env);
		long id = num.getNewKey(I_FD_Column.Table_ID);
		
		sql.append("INSERT INTO ").append(Table_Name).append("(");
		sql.append(COLUMNNAME_FD_Column_ID).append(",");
		sql.append(COLUMNNAME_FD_Table_ID).append(",");
		sql.append(COLUMNNAME_FD_Column_Name).append(",");
		sql.append(COLUMNNAME_FD_Name).append(",");
		sql.append(COLUMNNAME_FD_ColumnType_ID).append(",");
		sql.append(COLUMNNAME_FD_NotNull).append(",");
		sql.append(SQL_COMMON_COLUMN_LIST);
		sql.append(")");
		sql.append(" VALUES ").append("(");
		sql.append(value);
		sql.append(")");
		
		try {
			connection(env);
			pstmt = getConn().prepareStatement(sql.toString());
			pstmt.setLong(1, id);
			pstmt.setLong(2, tableId);
			pstmt.setLong(3, columnTypeId);
			pstmt.setTimestamp(4, new Timestamp(new Date().getTime()));
			pstmt.setLong(5, 100);
			pstmt.setTimestamp(6, new Timestamp(new Date().getTime()));
			pstmt.setLong(7, 100);
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
	 * @since 2022/12/28 Ver. 1.00
	 */
	public void addColumnData() {
		this.addData(Table_ID, ADD_COLUMN_FD_COLUMN_ID, COLUMN_TYPE_ID_KEY_NAME);
		this.addData(Table_ID, ADD_COLUMN_FD_Table_ID, COLUMN_TYPE_ID_KEY_NAME);
		this.addData(Table_ID, ADD_COLUMN_FD_Column_Name, COLUMN_TYPE_VARCHAR_NAME);

		this.addData(Table_ID, ADD_COMMON_COLUMN_Created, COLUMN_TYPE_DATETIME_NAME);
		this.addData(Table_ID, ADD_COMMON_COLUMN_CreatedBy, COLUMN_TYPE_UNSIGNED_BIGINT_NAME);
		this.addData(Table_ID, ADD_COMMON_COLUMN_Updated, COLUMN_TYPE_DATETIME_NAME);
		this.addData(Table_ID, ADD_COMMON_COLUMN_UpdatedBy, COLUMN_TYPE_UNSIGNED_BIGINT_NAME);
	}

	/**
	 * 指定されたテーブル情報IDを持つテーブル項目情報のリストを作成する。<br>
	 * [Create a list of table item information with the specified table information ID.]
	 * @author officina-hide.net
	 * @since 2023/03/09 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableId テーブル情報ID
	 * @return テーブル項目情報リスト
	 */
	public List<X_FD_Column> getColumnList(FD_EnvData env, long tableId) {
		List<X_FD_Column> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(GET_COLUMN_DATALIST);
			pstmt.setLong(1, tableId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new X_FD_Column(env, rs.getLong(COLUMNNAME_FD_Column_ID)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
		return list;
	}

}
