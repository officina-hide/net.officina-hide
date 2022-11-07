package net.officina_hide.base.model;

/**
 * デーだベースインターフェースクラス[Database interface class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/10/17 Ver. 1.00
 */
public interface I_FD_DB {
	
	/**データベースサーバー名 */
	public final String DB_SERVER_NAME = "dbServerName";
	/** データベース名 */
	public final String DB_NAME = "dbName";
	/** データベース接続ポート */
	public final String DB_PORT = "dbPort";
	/** データベースユーザーID */
	public final String DB_USER_ID = "dbUerID";
	/** データベースユーザーパスワード */
	public final String DB_USER_PASSWORD = "dbUserPassword";
	
	/** SQL用カラム属性設定[Column attribute setting for SQL] */
	public final String COLUMN_TYPE_ID_KEY = " bigint unsigned NOT NULL PRIMARY KEY ";
	/** SQL用コメント[Comments for SQL] */
	public final String COMMENT = " COMMENT ";
	/** シングルコーテーション[single quotes] */
	public final static String FD_SQ = "'";

}
