package net.officina_hide.base.model;

/**
 * 採番情報インターフェースクラス[Numbering information interface class]<br>
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/11/14 Ver. 1.00
 */
public interface I_FD_Numbering extends I_FD_DB {
	
	/** テーブル情報[Table information] */
	public final long Table_ID = 103;
	public final String Table_Name = "FD_Numbering";
	public final String Table_Comment = "採番情報";
	
	/** 採番情報ID */
	public final String COLUMNNAME_FD_Numbering_ID = Table_Name + "_ID";
	public final String NAME_FD_Numbering_ID = Table_Comment + "ID";
	/** テーブル情報ID */
	public final String COLUMNNAME_FD_Table_ID = I_FD_Table.COLUMNNAME_FD_Table_ID;
	public final String NAME_FD_Table_ID = I_FD_Table.NAME_FD_Table_ID;
	public final String COMMENT_FD_Table_ID = I_FD_Table.COMMENT_FD_Table_ID;
	/** 採番初期値[Initial value for numbering] */
	public final String COLUMNNAME_FD_InitialNumber = "FD_InitialNumber";
	public final String NAME_FD_InitialNumber = "採番初期値";
	public final String COMMENT_FD_InitialNumber = "採番の初期の値";
	/** 採番値[numbering value] */
	public final String COLUMNNAME_FD_CurrentNumber = "FD_CurrentNumber";
	public final String NAME_FD_CurrentNumber = "採番値";
	public final String COMMENT_FD_CurrentNumber = "現在採番されている値";
	
	/** テーブル構築用SQL文[SQL statement for table construction] */
	public final String SQL_CREATE_FD_Numbering = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name + " ("
			+ COLUMNNAME_FD_Numbering_ID + COLUMN_TYPE_ID_KEY
				+ COMMENT + FD_SQ + NAME_FD_Numbering_ID + FD_SQ + " "
			+ "," + COLUMNNAME_FD_Table_ID + COLUMN_TYPE_ID
				+ COMMENT + FD_SQ + NAME_FD_Table_ID + FD_SQ + " "
			+ "," + COLUMNNAME_FD_InitialNumber + COLUMN_TYPE_UNSIGNED_BIGINT
				+ COMMENT + FD_SQ + NAME_FD_InitialNumber + FD_SQ + " "
			+ "," + COLUMNNAME_FD_CurrentNumber  + COLUMN_TYPE_UNSIGNED_BIGINT
				+ COMMENT + FD_SQ + NAME_FD_CurrentNumber + FD_SQ + " "
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

	/** テーブル項目情報登録 */
	public final String ADD_COLUMN_FD_TABLE_ID = "?" + "," + "?" + ","
			+ FD_SQ + COLUMNNAME_FD_Table_ID + FD_SQ + ","
			+ FD_SQ + NAME_FD_Table_ID + FD_SQ + ","
			+ "?" + ","
			+ FD_SQ + YESNO_YES + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	public final String ADD_COLUMN_FD_COLUMN_ID =  "?" + "," + "?" + ","
			+ FD_SQ + COLUMNNAME_FD_Numbering_ID + FD_SQ + ","
			+ FD_SQ + NAME_FD_Numbering_ID + FD_SQ + ","
			+ "?" + ","
			+ FD_SQ + YESNO_YES + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	public final String ADD_COLUMN_FD_InitialNumber = "?" + "," + "?" + ","
			+ FD_SQ + COLUMNNAME_FD_InitialNumber + FD_SQ + ","
			+ FD_SQ + NAME_FD_InitialNumber + FD_SQ + ","
			+ "?" + ","
			+ FD_SQ + YESNO_YES + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
	public final String ADD_COLUMN_FD_CurrentNumber = "?" + "," + "?" + ","
			+ FD_SQ + COLUMNNAME_FD_CurrentNumber + FD_SQ + ","
			+ FD_SQ + NAME_FD_CurrentNumber + FD_SQ + ","
			+ "?" + ","
			+ FD_SQ + YESNO_YES + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
}
