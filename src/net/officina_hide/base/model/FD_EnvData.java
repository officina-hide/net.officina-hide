package net.officina_hide.base.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 環境クラス[Environment class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/10/24 Ver. 1.0
 */
public class FD_EnvData implements I_FD_DB {

	/** データベースサーバー名 */
	private String dbServerName;
	/** データベース名 */
	private String dbName;
	/** データベース接続ポート */
	private String dbPort;
	/** データベースユーザーID */
	private String dbUserID;
	/** データベースパスワード */
	private String dbPassword;
	
	/**
	 * コンストラクタ
	 * @param envFile 環境プロパティファイル
	 */
	public FD_EnvData(String envFile) {
		try {
			File propFile = new File(envFile);
			FileInputStream is = new FileInputStream(propFile);
			Properties prop = new Properties();
			prop.load(is);
			dbServerName = prop.getProperty(DB_SERVER_NAME);
			dbName = prop.getProperty(DB_NAME);
			dbPort = prop.getProperty(DB_PORT);
			dbUserID = prop.getProperty(DB_USER_ID);
			dbPassword = prop.getProperty(DB_USER_PASSWORD);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDbServerName() {
		return dbServerName;
	}

	public void setDbServerName(String dbServerName) {
		this.dbServerName = dbServerName;
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

	public String getDbUserID() {
		return dbUserID;
	}

	public void setDbUserID(String dbUserID) {
		this.dbUserID = dbUserID;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

}
