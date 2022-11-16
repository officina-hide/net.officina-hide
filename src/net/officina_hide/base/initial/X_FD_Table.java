package net.officina_hide.base.initial;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import net.officina_hide.base.model.FD_DB;
import net.officina_hide.base.model.FD_EnvData;
import net.officina_hide.base.model.I_FD_Table;

/**
 * テーブル情報I/Oクラス[Table information I/O class}<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/11/10 Ver. 1.00
 */
public class X_FD_Table extends FD_DB implements I_FD_Table {

	/** テーブル情報ID[Table information ID] */
	private long FD_Table_ID;
	/** テーブル物理名[Table physical name] */
	private String FD_Table_Name;
	/** 名前[Name] */
	private String FD_Name;
	
	/** 環境情報[Environment information] */
	private FD_EnvData env;
	
	/**
	 * コンストラクタ[Constructor]<br>
	 * @author officina-hide.net
	 * @since 2022/11/10 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableId テーブル情報ID
	 */
	public X_FD_Table(FD_EnvData env, int tableId) {
		this.env = env;
	}
	
	/**
	 * 情報保存[Save data]
	 * @author officina-hide.net
	 * @since 2022/11/10 Ver. 1.00
	 */
	public void save() {
		PreparedStatement pstmt = null;
		connection(env);
//		try {
//			pstmt = getConn().prepareStatement(SQL_INSERT_FD_TABLE);
//			pstmt.setLong(1, getFD_Table_ID());
//			pstmt.setString(2, getFD_Table_Name());
//			pstmt.setString(3, getFD_Name());
//			Timestamp currentDate = new Timestamp(new Date().getTime());
//			pstmt.setTimestamp(4, currentDate);
//			pstmt.setLong(5, 100);
//			pstmt.setTimestamp(6, currentDate);
//			pstmt.setLong(7, 100);
//			int rs = pstmt.executeUpdate();
//			if(rs != 1) {
//				System.out.println("Data insert error!!");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBClose(pstmt, null);
//		}
	}

	public long getFD_Table_ID() {
		return FD_Table_ID;
	}
	public void setFD_Table_ID(long fD_Table_ID) {
		FD_Table_ID = fD_Table_ID;
	}
	public String getFD_Table_Name() {
		return FD_Table_Name;
	}
	public void setFD_Table_Name(String fD_Table_Name) {
		FD_Table_Name = fD_Table_Name;
	}
	public String getFD_Name() {
		return FD_Name;
	}
	public void setFD_Name(String fD_Name) {
		FD_Name = fD_Name;
	}
}
