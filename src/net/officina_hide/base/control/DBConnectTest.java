package net.officina_hide.base.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * データベース接続テスト[Database connection test]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/10/11 Ver. 1.00
 */
public class DBConnectTest {

	/** サーバー名[Server Name] */
	private String setverName;
	/** データベース名[Database Name] */
	private String dbName;
	/** データベース接続ポート[Database connection port] */
	private String dbPort;
	/** 接続ユーザーID[Connection user ID] */
	private String userId;
	/** 接続パスワード[Connection password] */
	private String password;
	
	/**
	 * データベース接続テスト[Database connection test]
	 * @author officina-hide.net
	 * @since 2022/10/12 Ver. 1.00
	 * @return 接続結果 true - 接続OK、false - 接続エラー
	 */
	public boolean connectTest() {
		boolean chk = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			StringBuffer url = new StringBuffer();
			url.append("jdbc:mysql:")
				.append(setverName).append(":").append(dbPort).append("/").append(dbName);
			Connection conn = DriverManager.getConnection(url.toString(), userId, password);
			if(conn != null) {
				chk = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return chk;
	}

	public String getSetverName() {
		return setverName;
	}

	public void setSetverName(String setverName) {
		this.setverName = setverName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
