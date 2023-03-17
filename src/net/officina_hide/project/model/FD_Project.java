package net.officina_hide.project.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import net.officina_hide.base.model.FD_Column;
import net.officina_hide.base.model.FD_EnvData;
import net.officina_hide.base.model.FD_Table;

/**
 * プロジェクト情報クラス[Project information class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/12/07 Ver. 1.00
 */
public class FD_Project extends X_FD_Project implements I_FD_Project {

	/**
	 * プロジェクト期間の月数を取得[Get project duration months]<br>
	 * @author officina-hide.net
	 * @since 2022/12/09 Ver. 1.00
	 * @return 月数[number of months]
	 */
	public int getLengthOfMonth() {
		int mcnt = 1;
		Calendar diffCal  = new GregorianCalendar(Locale.JAPAN);
		diffCal.setTimeInMillis(getStartDate().getTimeInMillis());
		while((diffCal.get(Calendar.YEAR) == getEndDate().get(Calendar.YEAR) &&
				diffCal.get(Calendar.MONTH) == getEndDate().get(Calendar.MONTH)) == false) {
			mcnt++;
			diffCal.add(Calendar.MONTH, 1);
		}
		return mcnt;
	}
	
	/**
	 * プロジェクト期間の年数を取得[Get project duration year]<br>
	 * @author officina-hide.net
	 * @since 2022/12/09 Ver. 1.00
	 * @return 年数[number of years]
	 */
	public int getLengthOfYear() {
		int ycnt = getEndDate().get(Calendar.YEAR) -  getStartDate().get(Calendar.YEAR) + 1;
		return ycnt;
	}

	/**
	 * プロジェクトテーブル構築[Build project table]
	 * @author officina-hide.net
	 * @since 2023/02/20 Ver. 1.00
	 * @param env 環境情報[Environment information]
	 */
	public void create(FD_EnvData env) {
		//テーブル情報登録
		FD_Table table = new FD_Table(env);
		FD_Column column = new FD_Column(env);
		long tebleId = table.addData(ADD_FD_Table);
		column.addData(tebleId, ADD_COLUMN_FD_Project_ID, COLUMN_TYPE_ID_KEY_NAME);
		column.addData(tebleId, ADD_COLUMN_FD_Project_Name, COLUMN_TYPE_VARCHAR_NAME);
		createTable(env, tebleId);
	}
}
