package net.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 	データベースクラス[Database class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/10/22 Ver. 1.00
 */
public class FD_DB {
	
	/** データベース接続情報[Database connection information] */
	private static Connection conn = null;

	/**
	 * テーブル存在チェック[table existence check]<br>
	 * @author officina-hide.net
	 * @since 2022/10/27 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 * @return true - テーブル有り、false - テーブル無し
	 */
	public boolean isExistsTable(FD_EnvData env, String tableName) {
		boolean chk = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = conn.prepareStatement(SQL_ExistsTable);
			pstmt.setString(1, env.getDbName());
			pstmt.setString(2, tableName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chk;
	}

	/**
	 * データベース接続[Database connection]
	 * @author officina-hide.net
	 * @since 2022/10/27 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void connection(FD_EnvData env) {
		if(conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				StringBuffer url = new StringBuffer();
				url.append("jdbc:mysql:")
					.append(env.getDbServerName()).append(":").append(env.getDbPort()).append("/").append(env.getDbName());
				conn = DriverManager.getConnection(url.toString(), env.getDbUserID(), env.getDbPassword());
				System.out.println(new Date() + " : "+"Database Connected.");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private final String SQL_ExistsTable =
			"select * from information_schema.tables it "
			+ "where it.TABLE_SCHEMA = ? "
			+ "and it.TABLE_NAME = ? ";
}
