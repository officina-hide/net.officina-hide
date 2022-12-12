package net.officina_hide.project.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import net.officina_hide.base.model.FD_DB;

/**
 * プロジェクト情報I/Oクラス[Project information I/O class]
 * @author officina-hide.net
 * @version 1.00 新規作成[New create]
 * @since 2022/12/07 Ver. 1.00
 */
public class X_FD_Project extends FD_DB {
	
	/** プロジェクト開始日 */
	private Calendar startDate;
	/** プロジェクト終了日 */
	private Calendar endDate;
	
	public Calendar getStartDate() {
		if(startDate == null) {
			startDate = new GregorianCalendar(new Locale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry()));
		}
		return startDate;
	}
	/**
	 * 日付文字列からプロジェクト開始日をセットする。<br>
	 * Sets the project start date from a date string.<br>
	 * 書式は"yyyy/mm/dd"とし、"/"が区切り文字となる。<br>
	 * 時間対象としない。<br>
	 * @author officina-hide.net
	 * @since 2022/12/07 Ver. 1.00
	 * @param dateString 日付文字列[Date string]
	 */
	public void setStartDate(String dateString) {
		DateFormat df = DateFormat.getDateInstance();
		try {
			getStartDate().setTime(df.parse(dateString));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public Calendar getEndDate() {
		if(endDate == null) {
			endDate = new GregorianCalendar(new Locale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry()));
		}
		return endDate;
	}
	public void setEndDate(String dateString) {
		DateFormat df = DateFormat.getDateInstance();
		try {
			getEndDate().setTime(df.parse(dateString));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
}
