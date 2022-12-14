package net.officina_hide.project.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * プロジェクト情報クラス[Project information class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/12/07 Ver. 1.00
 */
public class FD_Project extends X_FD_Project {

	/**
	 * プロジェクト期間の月数を取得[Get project duration months]<br>
	 * @author officina-hide.net
	 * @since 2022/12/09 Ver. 1.00
	 * @return 月数[number of months]
	 */
	public int getLengthOfMonth() {
		int mcnt = 1;
		Calendar diffCal  = new GregorianCalendar(new Locale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry()));
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
}
