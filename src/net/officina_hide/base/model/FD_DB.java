package net.officina_hide.base.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 	データベースクラス[Database class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/10/22 Ver. 1.00
 */
public class FD_DB implements I_FD_DB {
	
	/** データベース接続情報[Database connection information] */
	private static Connection conn = null;

	/**
	 * テーブル存在チェック[table existence check]<br>
	 * @author officina-hide.net
	 * @since 2022/10/27 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 * @return true - テーブル有り、false - テーブル無し
	 */
	public boolean isExistsTable(FD_EnvData env, String tableName) {
		boolean chk = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection(env);
			pstmt = conn.prepareStatement(SQL_ExistsTable);
			pstmt.setString(1, env.getDbName());
			pstmt.setString(2, tableName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chk;
	}

	/**
	 * データベース接続[Database connection]
	 * @author officina-hide.net
	 * @since 2022/10/27 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void connection(FD_EnvData env) {
		try {
			if(conn == null || conn.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				StringBuffer url = new StringBuffer();
				url.append("jdbc:mysql:")
					.append(env.getDbServerName()).append(":").append(env.getDbPort()).append("/").append(env.getDbName());
				conn = DriverManager.getConnection(url.toString(), env.getDbUserID(), env.getDbPassword());
				System.out.println(new Date() + " : "+"Database Connected.");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 接続情報取得[Get connection information]<br>
	 * @author officina-hide.net
	 * @since 2022/11/07 Ver. 1.00
	 * @return 接続情報[Connection information]
	 */
	public static Connection getConn() {
		return conn;
	}
	
	/**
	 * 
	 * @param pstmt
	 * @param object
	 */
	public void DBClose(PreparedStatement pstmt, ResultSet rs) {
		try {
			if(pstmt != null) {
				pstmt.close();
			}
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * テーブル構築[table build]<br>
	 * 指定されテーブル名を持つテーブルを構築する。
	 * @author officina-hide.net
	 * @since 2022/11/14 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table Name]
	 * @param mode 構築モード
	 * @param sqlStrings 構築用SQL文[SQL statement for construction]
	 */
	public void createTable(FD_EnvData env, String tableName, String mode, String sqlStrings) {
		PreparedStatement pstmt = null;
		try {
			//既登録のテーブルを削除する。[Delete the registered table.]
			connection(env);
			pstmt = getConn().prepareStatement(SQL_DROP_TABLE.replaceAll("@tableName", tableName));
			pstmt.execute();
			pstmt.close();
			pstmt = getConn().prepareStatement(sqlStrings);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	/**
	 * テーブル生成[Table generation]
	 * <p>テーブル情報とテーブル項目情報から実際のテーブルを構築する。<br>
	 * 構築に際しては、既登録のテーブルは削除する。</p>
	 * <p>Build the actual table from table information and table item information.<br>
	 * At the time of construction, the already registered table is deleted.</p>
	 * 
	 * @author officina-hide.net
	 * @since 2023/02/28 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 * @param tableId テーブル情報ID[Table information ID]
	 */
	public void createTable(FD_EnvData env, long tableId) {
		PreparedStatement pstmt = null;
		//テーブル情報取得
		X_FD_Table table = new X_FD_Table(env, tableId);
		//テーブル項目情報取得
		FD_Column column = new FD_Column(env);
		List<X_FD_Column> columns = column.getColumnList(env, table.getFD_Table_ID());
		System.out.println(columns.size());
		//既登録分のテーブルをDropする。
		dropTable(env, table.getFD_Table_Name());
		//テーブル生成
		StringBuffer createSQL = new StringBuffer();
		try {
			createSQL.append("Create Table if not exists ").append(table.getFD_Table_Name()).append(" (");
			for(X_FD_Column xcolumn : columns) {
				createSQL.append(xcolumn.getFD_Column_Name());
				System.out.println(xcolumn.getFD_ColumnType().getFD_Reference_Name());
				switch(xcolumn.getFD_ColumnType().getFD_Reference_Name()) {
				case COLUMN_TYPE_ID_KEY_NAME:
					createSQL.append(COLUMN_TYPE_ID_KEY).append(" ");
					break;
				}
				if(xcolumn.getFD_Name() != null && xcolumn.getFD_Column_Name().length() > 0) {
					createSQL.append(COMMENT).append(" ")
						.append(FD_SQ).append(xcolumn.getFD_Name()).append(FD_SQ).append(" ");
				}
				createSQL.append(",");
			}
			createSQL.append(SQL_CREATE_COMMON_COLUMN);
			createSQL.append(") ");
			createSQL.append(COMMENT).append(" ").append(FD_SQ).append(table.getFD_Name()).append(FD_SQ);
			System.out.println(createSQL.toString());
			connection(env);
			pstmt = getConn().prepareStatement(createSQL.toString());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
		
	}

	/**
	 * テーブル削除[Drop table]
	 * @param env 環境情報[Environment information]
	 * @param tableName テーブル名[Table name]
	 */
	private void dropTable(FD_EnvData env, String tableName) {
		PreparedStatement pstmt = null;
		//既登録のテーブルを削除する。[Delete the registered table]
		try {
			connection(env);
			pstmt = getConn().prepareStatement(SQL_DROP_TABLE.replaceAll("@tableName", tableName));
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose(pstmt, null);
		}
	}

	private final String SQL_ExistsTable =
			"select * from information_schema.tables it "
			+ "where it.TABLE_SCHEMA = ? "
			+ "and it.TABLE_NAME = ? ";
}
