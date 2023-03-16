package net.officina_hide.base.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 参照情報I/Oクラス[Reference information I/O class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2023/03/15 Ver. 1.00
 */
public class X_FD_Reference extends FD_DB implements I_FD_Reference {

	/** 環境情報[Environment information] */
	private FD_EnvData env;
	
	/** 参照情報ID[Reference information ID] */
	private long FD_Reference_ID;
	private long FD_ReferenceGroup_ID;
	private String FD_Reference_Name;
	private String FD_Name;

	/**
	 * コンストラクタ[Constructor]
	 * @author officina-hide.net
	 * @since 2023/03/15 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param referenceId 参照情報ID
	 */
	public X_FD_Reference(FD_EnvData envData, long referenceId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//環境情報取得
		this.env = envData;
		//指定された参照情報取得
		if(referenceId > 0) {
			try {
				connection(env);
				pstmt = getConn().prepareStatement(SQL_GET_RECORD);
				pstmt.setLong(1, referenceId);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					setFD_Reference_ID(rs.getLong(COLUMNNAME_FD_Reference_ID));
					setFD_ReferenceGroup_ID(rs.getLong(COLUMNNAME_FD_ReferenceGroup_ID));
					setFD_Reference_Name(rs.getString(COLUMNNAME_FD_Reference_Name));
					setFD_Name(rs.getString(COLUMNNAME_FD_Name));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBClose(pstmt, rs);
			}
		}
	}
	
	public long getFD_Reference_ID() {
		return FD_Reference_ID;
	}
	public void setFD_Reference_ID(long fD_Reference_ID) {
		FD_Reference_ID = fD_Reference_ID;
	}
	public long getFD_ReferenceGroup_ID() {
		return FD_ReferenceGroup_ID;
	}
	public void setFD_ReferenceGroup_ID(long fD_ReferenceGroup_ID) {
		FD_ReferenceGroup_ID = fD_ReferenceGroup_ID;
	}
	public String getFD_Reference_Name() {
		return FD_Reference_Name;
	}
	public void setFD_Reference_Name(String fD_Reference_Name) {
		FD_Reference_Name = fD_Reference_Name;
	}
	public String getFD_Name() {
		return FD_Name;
	}
	public void setFD_Name(String fD_Name) {
		FD_Name = fD_Name;
	}
}
