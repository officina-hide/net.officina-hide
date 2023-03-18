package net.officina_hide.base.model;

/**
 * テーブル情報インターフェースクラス[Table information interface class]<br>
 * @author officina-hide.net
 * @version 新規作成[New create]
 * @since 2022/11/07 Ver. 1.00
 */
public interface I_FD_Table extends I_FD_DB {
	/** テーブル情報[Table information] */
	public final long Table_ID = 101;
	public final String Table_Name = "FD_Table";
	public final String Table_Comment = "テーブル情報";
	
	/** テーブル情報ID */
	public final String COLUMNNAME_FD_Table_ID = Table_Name + "_ID";
	public final String NAME_FD_Table_ID = Table_Comment + "ID";
	public final String COMMENT_FD_Table_ID = "テーブル情報を識別するためのID";
	/** テーブル名 */
	public final String COLUMNNAME_FD_Table_Name = Table_Name + "_Name";
	public final String Name_FD_Table_Name = "テーブル名";
	public final int SIZE_FD_Table_Name = 100;

	/** テーブル情報構築用SQL[SQL for building table information] */
	public final String SQL_CREATE_FD_TABLE = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name + " ("
			+ COLUMNNAME_FD_Table_ID + COLUMN_TYPE_ID_KEY 
				+ COMMENT + FD_SQ + NAME_FD_Table_ID + FD_SQ
			+ "," + COLUMNNAME_FD_Table_Name 
				+ COLUMN_TYPE_VARCHAR.replaceAll("n", Integer.toString(SIZE_FD_Table_Name))
				+ " not null " + " "
				+ COMMENT + FD_SQ + Name_FD_Table_Name + FD_SQ + " "
			+ "," + COLUMNNAME_FD_Name + COLUMN_TYPE_VARCHAR.replaceAll("n", "200")
				+ COMMENT + FD_SQ + NAME_FD_Name + FD_SQ + " "
			+ "," + SQL_CREATE_COMMON_COLUMN
			+ ")"
			+ COMMENT + FD_SQ + Table_Comment + FD_SQ
			+";";
	
	/** 採番情報登録 */
	public final String ADD_FD_Numbering = Table_ID + ","+Table_ID+", 1001, 1001,"
			+ SQL_COMMON_COLUMN_INPUT_LIST ;
	/** テーブル情報登録 */
	public final String ADD_FD_Table = Table_ID + "," + FD_SQ + Table_Name + FD_SQ + ","
			+ FD_SQ + Table_Comment + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	/** テーブル項目情報登録 */
	public final String ADD_COLUMN_FD_TABLE_ID = "?" + "," + "?" + ","
			+ FD_SQ + COLUMNNAME_FD_Table_ID + FD_SQ + ","
			+ FD_SQ + NAME_FD_Table_ID + FD_SQ + ","
			+ "?" + "," + SIZE_ZERO + ","
			+ FD_SQ + YESNO_YES + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	public final String ADD_COLUMN_FD_TABLE_Name = "?" + "," + "?" + ","
			+ FD_SQ + COLUMNNAME_FD_Table_Name + FD_SQ + ","
			+ FD_SQ + Name_FD_Table_Name + FD_SQ + ","
			+ "?" + "," + SIZE_FD_Table_Name + ","
			+ FD_SQ + YESNO_YES + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	public final String ADD_COLUMN_FD_Name = "?" + "," + "?" + ","
			+ FD_SQ + COLUMNNAME_FD_Name + FD_SQ + ","
			+ FD_SQ + NAME_FD_Name + FD_SQ + ","
			+ "?" + ","+ SIZE_FD_Name + ","
			+ FD_SQ + YESNO_NO + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;			
}
