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
	public final String NAME_Created = "登録日";
	/** 共通項目 : 登録者ID */
	public final String COLUMNNAME_CreatedBy = "createdBy";
	public final String NAME_CreatedBy = "登録者ID";
	/** 共通項目 : 更新日 */
	public final String COLUMNNAME_Updated = "updated";
	public final String NAME_Updated = "更新日";
	/** 共通項目 : 更新者ID */
	public final String COLUMNNAME_UpdatedBy = "updatedBy";
	public final String NAME_UpdatedBy = "更新者ID";
	/** 汎用項目 : 名前 */
	public final String COLUMNNAME_FD_Name = "FD_Name";
	public final String NAME_FD_Name = "名前";
	public final int SIZE_FD_Name = 200;
	
	/** SQL用コメント[Comments for SQL] */
	public final String COMMENT = " COMMENT ";
	/** シングルコーテーション[single quotes] */
	public final String FD_SQ = "'";

	/** SQL用カラム属性設定[Column attribute setting for SQL] */
	public final String COLUMN_TYPE_ID_KEY = " bigint unsigned NOT NULL PRIMARY KEY ";
	public final String COLUMN_TYPE_ID = " bigint unsigned ";
	public final String COLUMN_TYPE_ID_NAME = "ID";
	public final String COLUMN_TYPE_ID_COMMENT = "情報ID";
	/** SQL用カラム属性 : 文字列 */
	public final String COLUMN_TYPE_VARCHAR = " varchar(n) ";
	public final String COLUMN_TYPE_VARCHAR_NAME = "Varchar";
	public final String COLUMN_TYPE_VARCHAR_COMMENT = "文字列";
	/** SQL用カラム属性 : 日時 */
	public final String COLUMN_TYPE_DATETIME = " datetime ";
	public final String COLUMN_TYPE_DATETIME_NAME = "DateTime";
	public final String COLUMN_TYPE_DATETIME_COMMENT = "日時";
	/** SQL用カラム属性 : 数値 */
	public final String COLUMN_TYPE_UNSIGNED_BIGINT = " bigint unsigned ";
	public final String COLUMN_TYPE_UNSIGNED_BIGINT_NAME = "BigInt Unsigned";
	public final String COLUMN_TYPE_UNSIGNED_BIGINT_COMMENT = "符号無し整数";
	
	/** 共通項目追加用SQL[SQL for adding common items] */
	public final String SQL_CREATE_COMMON_COLUMN =
			COLUMNNAME_Created + COLUMN_TYPE_DATETIME
				+ COMMENT + FD_SQ + NAME_Created + FD_SQ + " "
			+ "," + COLUMNNAME_CreatedBy + COLUMN_TYPE_ID
				+ COMMENT + FD_SQ + NAME_CreatedBy + FD_SQ + " "
			+ "," + COLUMNNAME_Updated + COLUMN_TYPE_DATETIME
				+ COMMENT + FD_SQ + NAME_Updated + FD_SQ + " "
			+ "," + COLUMNNAME_UpdatedBy + COLUMN_TYPE_ID
				+ COMMENT + FD_SQ + NAME_UpdatedBy + FD_SQ + " ";
	public final String SQL_COMMON_COLUMN_LIST =
			COLUMNNAME_Created + "," + COLUMNNAME_CreatedBy + ","
			+ COLUMNNAME_Updated + "," + COLUMNNAME_UpdatedBy;
	public final String SQL_COMMON_COLUMN_INPUT_LIST = "?,?,?,?";
	
	/** テーブル構築モード : オリジナル */
	public final String TABLE_CREATE_MODE_ORIGINAL = "Original";
	/** テーブル構築モード : 汎用 */
	public final String TABLE_CREATE_MODE_GENERAL = "General";
	/** テーブル削除用SQL文[SQL statement for table deletion] */
	public final String SQL_DROP_TABLE = 
			"DROP TABLE IF EXISTS @tableName";
	/** 共通項目カラム情報追加用SQL[SQL for adding common item column information] */
	public final String ADD_COMMON_COLUMN_Created = "?" + "," + "?" + "," 
			+ FD_SQ + COLUMNNAME_Created + FD_SQ + ","
			+ FD_SQ + NAME_Created + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	public final String ADD_COMMON_COLUMN_CreatedBy = "?" + "," + "?" + "," 
			+ FD_SQ + COLUMNNAME_CreatedBy + FD_SQ + ","
			+ FD_SQ + NAME_CreatedBy + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	public final String ADD_COMMON_COLUMN_Updated = "?" + "," + "?" + "," 
			+ FD_SQ + COLUMNNAME_Updated + FD_SQ + ","
			+ FD_SQ + NAME_Updated + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	public final String ADD_COMMON_COLUMN_UpdatedBy = "?" + "," + "?" + "," 
			+ FD_SQ + COLUMNNAME_UpdatedBy + FD_SQ + ","
			+ FD_SQ + NAME_UpdatedBy + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	
	/** 参照グループ */
	public final long REFERENCE_GROUP_COLUMN_TYPE_ID = 1001;
	public final String REFERENCE_GROUP_COLUMN_TYPE_NAME = "columnType";
	public final String REFERENCE_GROUP_COLUMN_TYPE_COMMENT = "項目属性";

}
