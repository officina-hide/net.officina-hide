package net.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * テーブル項目情報I/Oクラス[Table column I/O class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2023/03/04 Ver. 1.00
 */
public class X_FD_Column extends FD_DB implements I_FD_Column {

	/** 環境情報[Environment information] */
	private FD_EnvData env;
	
	/** テーブル項目 */
	/** テーブル項目情報ID*/
	private long FD_Column_ID;
	/** テーブル情報ID */
	private long FD_Table_ID;
	/** テーブル項目名 */
	private String FD_Column_Name;
	/** 名前[Name] */
	private String FD_Name;
	/** 項目種別ID */
	private long FD_ColumnType_ID;
	private X_FD_Reference FD_ColumnType;
	/** 必須判定 */
	private boolean FD_NotNull;
	
	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2023/03/12 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param columnId テーブル項目情報ID[Table column information ID]
	 */
	public X_FD_Column(FD_EnvData env, long columnId) {
		//環境情報保存
		this.env = env;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = getConn().prepareStatement(GET_COLUMN_DATA);
			pstmt.setLong(1, columnId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				setFD_Column_ID(rs.getLong(COLUMNNAME_FD_Column_ID));
				setFD_Table_ID(rs.getLong(COLUMNNAME_FD_Table_ID));
				setFD_Column_Name(rs.getString(COLUMNNAME_FD_Column_Name));
				setFD_Name(rs.getString(COLUMNNAME_FD_Name));
				setFD_ColumnType_ID(rs.getLong(COLUMNNAME_FD_ColumnType_ID));
				if(rs.getString(COLUMNNAME_FD_NotNull).equals("Y")) {
					setFD_NotNull(true);
				} else {
					setFD_NotNull(false);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, rs);
		}
	}

	public long getFD_Column_ID() {
		return FD_Column_ID;
	}
	public void setFD_Column_ID(long fD_Column_ID) {
		FD_Column_ID = fD_Column_ID;
	}
	public long getFD_Table_ID() {
		return FD_Table_ID;
	}
	public void setFD_Table_ID(long fD_Table_ID) {
		FD_Table_ID = fD_Table_ID;
	}
	public String getFD_Column_Name() {
		return FD_Column_Name;
	}
	public void setFD_Column_Name(String fD_Column_Name) {
		FD_Column_Name = fD_Column_Name;
	}
	public boolean isFD_NotNull() {
		return FD_NotNull;
	}
	public void setFD_NotNull(boolean fD_NotNull) {
		FD_NotNull = fD_NotNull;
	}
	public X_FD_Reference getFD_ColumnType() {
		if(FD_ColumnType == null ||
				( getFD_ColumnType_ID() > 0 && FD_ColumnType.getFD_Reference_ID() != getFD_ColumnType_ID())) {
			FD_ColumnType = new X_FD_Reference(env, getFD_ColumnType_ID());
		}
		return FD_ColumnType;
	}
	public long getFD_ColumnType_ID() {
		return FD_ColumnType_ID;
	}
	public void setFD_ColumnType_ID(long fD_ColumnType_ID) {
		FD_ColumnType_ID = fD_ColumnType_ID;
	}

	public String getFD_Name() {
		return FD_Name;
	}

	public void setFD_Name(String fD_Name) {
		FD_Name = fD_Name;
	}
	
}
