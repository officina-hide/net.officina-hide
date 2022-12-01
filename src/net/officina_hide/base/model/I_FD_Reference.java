package net.officina_hide.base.model;

/**
 * 参照情報インターフェースクラス[Reference information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/11/24 Ver. 1.00
 */
public interface I_FD_Reference extends I_FD_DB {
	
	/** テーブル情報[Table information] */
	public final long Table_ID = 104;
	public final String Table_Name = "FD_Reference";
	public final String Table_Comment = "参照情報";

	/** テーブル項目 : 参照情報ID */
	public final String COLUMNNAME_FD_Reference_ID = Table_Name + "_ID";
	public final String NAME_FD_Reference_ID = Table_Comment + "ID";
	/** テーブル項目 : 参照情報名 */
	public final String COLUMNNAME_FD_Reference_Name = "FD_Reference_Name";
	public final String NAME_FD_Reference_Name = "参照名";
	public final Integer SIZE_FD_Reference_Name = 100;
	
	/** 
	 * テーブル項目 : 参照グループID<br>
	 * 参照情報の参照グループIDがゼロの情報を参照グループとする。
	 */
	public final String COLUMNNAME_FD_ReferenceGroup_ID = "FD_ReferenceGroup_ID";
	public final String NAME_FD_ReferenceGroup_ID = "参照グループID";
	
	/** テーブル構築用SQL文[SQL statement for table construction] */
	public final String SQL_CREATE_FD_Reference = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name + " ("
			+ COLUMNNAME_FD_Reference_ID + COLUMN_TYPE_ID_KEY
				+ COMMENT + FD_SQ + NAME_FD_Reference_ID + FD_SQ + " "
			+ "," + COLUMNNAME_FD_ReferenceGroup_ID + COLUMN_TYPE_ID
				+ COMMENT + FD_SQ + NAME_FD_ReferenceGroup_ID + FD_SQ + " "
			+ "," + COLUMNNAME_FD_Reference_Name + COLUMN_TYPE_VARCHAR.replaceAll("n", SIZE_FD_Reference_Name.toString())
				+ COMMENT + FD_SQ + NAME_FD_Reference_Name + FD_SQ + " "
			+ ", " + COLUMNNAME_FD_Name + COLUMN_TYPE_VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Name))
				+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + " "
			+ "," + SQL_CREATE_COMMON_COLUMN
			+ ")"
			+ COMMENT + FD_SQ + Table_Comment + FD_SQ
			+";";
	
	/** テーブル情報登録 */
	public final String ADD_FD_Table = Table_ID + "," + FD_SQ + Table_Name + FD_SQ + ","
			+ FD_SQ + Table_Comment + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	
	/** 採番情報登録 */
	public final String ADD_FD_Numbering = Table_ID + ","+Table_ID+", 100001, 100001,"
			+ SQL_COMMON_COLUMN_INPUT_LIST ;
	
	/** 参照グループ登録 : テーブル項属性 */
	public final String ADD_Reference_Group_ColumnType = "?,"
			+ FD_SQ + REFERENCE_GROUP_COLUMN_TYPE_NAME + FD_SQ + ","
			+ FD_SQ + REFERENCE_GROUP_COLUMN_TYPE_COMMENT + FD_SQ + ","
			+ "0" + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	/** 参照 : テーブル項目属性 : ID */
	public final String ADD_Reference_ColumnType_ID = "?,"
			+ FD_SQ +COLUMN_TYPE_ID_NAME + FD_SQ + ","
			+ FD_SQ + COLUMN_TYPE_ID_COMMENT + FD_SQ + ","
			+ REFERENCE_GROUP_COLUMN_TYPE_ID + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	/** 参照 : テーブル項目属性 : 文字列 */
	public final String ADD_Reference_ColumnType_Verchar = "?,"
			+ FD_SQ + COLUMN_TYPE_VARCHAR_NAME + FD_SQ + ","
			+ FD_SQ + COLUMN_TYPE_VARCHAR_COMMENT + FD_SQ + ","
			+ REFERENCE_GROUP_COLUMN_TYPE_ID + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	/** 参照 : テーブル項目属性 : 日時 */
	public final String ADD_Reference_ColumnType_DateTime = "?,"
			+ FD_SQ + COLUMN_TYPE_DATETIME_NAME + FD_SQ + ","
			+ FD_SQ + COLUMN_TYPE_DATETIME_COMMENT + FD_SQ + ","
			+ REFERENCE_GROUP_COLUMN_TYPE_ID + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	/** 参照 : テーブル項目属性 : 日時 */
	public final String ADD_Reference_ColumnType_UNSIGNED_BIGINT = "?,"
			+ FD_SQ + COLUMN_TYPE_UNSIGNED_BIGINT_NAME + FD_SQ + ","
			+ FD_SQ + COLUMN_TYPE_UNSIGNED_BIGINT_COMMENT + FD_SQ + ","
			+ REFERENCE_GROUP_COLUMN_TYPE_ID + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
}
