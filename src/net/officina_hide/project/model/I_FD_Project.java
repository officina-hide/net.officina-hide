package net.officina_hide.project.model;

import net.officina_hide.base.model.I_FD_DB;

/**
 * プロジェクト情報インターフェースクラス[Project information interface class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2023/02/21 Ver. 1.00
 */
public interface I_FD_Project extends I_FD_DB {
	/** テーブル情報[Table information] */
//	public final long Table_ID = 103;
	public final String Table_Name = "FD_Project";
	public final String Table_Comment = "プロジェクト情報";
	
	/** 項目情報 : プロジェクト情報ID */
	public final String COLUMNNAME_FD_Project_ID = Table_Name + "_ID";
	public final String NAME_FD_Project_ID = Table_Comment + "ID";


	/** テーブル情報登録 */
	public final String ADD_FD_Table = "@TableID@" + "," + FD_SQ + Table_Name + FD_SQ + ","
			+ FD_SQ + Table_Comment + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;

	/** テーブル項目情報 */
	public final String ADD_COLUMN_FD_Project_ID = "?" + "," + "?" + ","
			+ FD_SQ + COLUMNNAME_FD_Project_ID + FD_SQ + ","
			+ FD_SQ + NAME_FD_Project_ID + FD_SQ + ","
			+ "?" + ","
			+ FD_SQ + YESNO_YES + FD_SQ + ","
			+ SQL_COMMON_COLUMN_INPUT_LIST;
}
