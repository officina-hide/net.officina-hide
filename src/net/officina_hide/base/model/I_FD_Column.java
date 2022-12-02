package net.officina_hide.base.model;

/**
 * テーブル項目情報インターフェースクラス[Table column information interface class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/11/11 Ver. 1.00
 */
public interface I_FD_Column extends I_FD_DB {

	/** テーブル情報[Table information] */
	public final long Table_ID = 102;
	public final String Table_Name = "FD_Column";
	public final String Table_Comment = "テーブル項目情報";

	/** テーブル項目情報ID */
	public final String COLUMNNAME_FD_Column_ID = Table_Name + "_ID";
	public final String COMMENT_FD_Column_ID = Table_Comment + "ID";
	/** テーブル情報ID */
	public final String COLUMNNAME_FD_Table_ID = I_FD_Table.COLUMNNAME_FD_Table_ID;
	public final String COMMENT_FD_Table_ID = I_FD_Table.NAME_FD_Table_ID;
	/** 項目名 */
	public final String COLUMNNAME_FD_Column_Name = Table_Name + "_Name";
	public final String COMMENT_FD_Column_Name = "テーブル項目名";
	public final int SIZE_FD_Column_Name = 100;
	/** 項目種別ID(参照ID:Group 項目属性) */
	public final String COLUMNNAME_FD_ColumnType_ID = "FD_ColumnType_ID";
	public final String COMMENT_FD_ColumnType_ID = "項目属性ID";
	
	/** テーブル項目情報構築用SQL[SQL for building table column information] */
	public final String SQL_CREATE_FD_COLUMN = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name + " ("
			+ COLUMNNAME_FD_Column_ID + COLUMN_TYPE_ID_KEY
				+ COMMENT + FD_SQ + COMMENT_FD_Column_ID + FD_SQ + " "
			+ "," + COLUMNNAME_FD_Table_ID + COLUMN_TYPE_ID
				+ COMMENT + FD_SQ + COMMENT_FD_Table_ID + FD_SQ + " "
			+ "," + COLUMNNAME_FD_Column_Name + COLUMN_TYPE_VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Column_Name))
				+ COMMENT + FD_SQ + COMMENT_FD_Column_Name + FD_SQ + " "
			+ "," + COLUMNNAME_FD_Name + COLUMN_TYPE_VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Name)) + " "
				+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + " "
			+ "," + COLUMNNAME_FD_ColumnType_ID + COLUMN_TYPE_ID
				+ COMMENT + FD_SQ + COMMENT_FD_ColumnType_ID + FD_SQ + " "
			+ "," + SQL_CREATE_COMMON_COLUMN
			+ ")"
			+ COMMENT + FD_SQ + Table_Comment + FD_SQ
			+";";
	
	/** 採番情報登録 */
	public final String ADD_FD_Numbering = Table_ID + ","+Table_ID+", 100001, 100001,"
			+ SQL_COMMON_COLUMN_INPUT_LIST ;
	
	/** テーブル情報登録 */
	public final String ADD_FD_Table = Table_ID + "," + FD_SQ + Table_Name + FD_SQ + ","
			+ FD_SQ + Table_Comment + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
}
