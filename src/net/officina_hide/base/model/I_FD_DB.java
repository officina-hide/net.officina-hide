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
	
	/** 共通項目 : 登録日 */
	public final String COLUMNNAME_Created = "created";
	public final String COMMENT_Created = "登録日";
	/** 共通項目 : 登録者ID */
	public final String COLUMNNAME_CreatedBy = "createdBy";
	public final String COMMENT_CreatedBy = "登録者ID";
	/** 共通項目 : 更新日 */
	public final String COLUMNNAME_Updated = "updated";
	public final String COMMENT_Updated = "更新日";
	/** 共通項目 : 更新者ID */
	public final String COLUMNNAME_UpdatedBy = "updatedBy";
	public final String COMMENT_UpdatedBy = "更新者ID";
	/** 汎用項目 : 名前 */
	public final String COLUMNNAME_FD_Name = "FD_Name";
	public final String COMMENT_FD_Name = "名前";
	
	/** SQL用カラム属性設定[Column attribute setting for SQL] */
	public final String COLUMN_TYPE_ID_KEY = " bigint unsigned NOT NULL PRIMARY KEY ";
	public final String COLUMN_TYPE_ID = " bigint unsigned ";
	/** SQL用コメント[Comments for SQL] */
	public final String COMMENT = " COMMENT ";
	/** SQL用カラム属性 : 文字列 */
	public final String COLUMN_TYPE_VARCHAR = " varchar(n) ";
	/** SQL用カラム属性 : 日時 */
	public final String COLUMN_TYPE_DATETIME = " datetime ";
	/** シングルコーテーション[single quotes] */
	public final String FD_SQ = "'";
	/** 共通項目追加用SQL[SQL for adding common items] */
	public final String SQL_CREATE_COMMON_COLUMN =
			COLUMNNAME_Created + COLUMN_TYPE_DATETIME
				+ COMMENT + FD_SQ + COMMENT_Created + FD_SQ + " "
			+ "," + COLUMNNAME_CreatedBy + COLUMN_TYPE_ID
				+ COMMENT + FD_SQ + COMMENT_CreatedBy + FD_SQ + " "
			+ "," + COLUMNNAME_Updated + COLUMN_TYPE_DATETIME
				+ COMMENT + FD_SQ + COMMENT_Updated + FD_SQ + " "
			+ "," + COLUMNNAME_UpdatedBy + COLUMN_TYPE_ID
				+ COMMENT + FD_SQ + COMMENT_UpdatedBy + FD_SQ + " ";
	public final String SQL_COMMON_COLUMN_LIST =
			COLUMNNAME_Created + "," + COLUMNNAME_CreatedBy + ","
			+ COLUMNNAME_Updated + "," + COLUMNNAME_UpdatedBy;
	public final String SQL_COMMON_COLUMN_INPUT_LIST = "?,?,?,?";
}
