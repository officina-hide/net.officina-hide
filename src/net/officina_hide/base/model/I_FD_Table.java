package net.officina_hide.base.model;

/**
 * テーブル情報インターフェースクラス[Table information interface class]<br>
 * @author officina-hide.net
 * @version 新規作成[New create]
 * @since 2022/11/07 Ver. 1.00
 */
public interface I_FD_Table extends I_FD_DB {
	/** テーブル情報[Table information] */
	public final String Table_ID = "101";
	public final String Table_Name = "FD_Table";
	public final String Table_Comment = "テーブル情報";
	
	/** テーブル情報ID */
	public final String COLUMNNAME_FD_Table_ID = Table_Name + "_ID";
	public final String COMMENT_FD_Table_ID = Table_Comment + "ID";
	

	/** テーブル情報構築用SQL[SQL for building table information] */
	public final String SQL_DROP_FD_TABLE = 
			"DROP TABLE IF EXISTS " + Table_Name + " ;";
	public final String SQL_CREATE_FD_TABLE = 
			"CREATE TABLE IF NOT EXISTS " + Table_Name + " ("
			+ COLUMNNAME_FD_Table_ID + COLUMN_TYPE_ID_KEY 
				+ COMMENT + FD_SQ + COMMENT_FD_Table_ID + FD_SQ
			+ ")"
			+ COMMENT + FD_SQ + Table_Comment + FD_SQ
			+";";
}
