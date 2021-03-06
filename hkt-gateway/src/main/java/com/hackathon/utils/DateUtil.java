package com.hackathon.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
	public static final Locale TH_LOCALE = new Locale("th", "TH");
	public static final Locale EN_LOCALE = new Locale("en", "US");

	public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public static final String DEFAULT_DATE_IMEX_PATTERN = "yyyyMMdd";
	public static final String DEFAULT_TIME_IMEX_PATTERN = "HHmmss";
	public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
	public static final String SIMPLE_DATETIME_PATTERN = "dd/MM/yyyy HH:mm:ss";
	public static final String SIMPLE_DATE_PATTERN_REPORT = " dd / MM / yyyy";
	public static final String SIMPLE_DATE_PATTERN = "dd/MM/yyyy";
	public static final String FULL_DATE_PATTERN = "dd MMMM yyyy";
	public static final long MILLISECS_PER_DAY = 86400000;
	public static final String DEFAULT_DATETIME_REPORT_PATTERN = "dd/MM/yyyy HH:mm";
	public static final String ORACLE_BAM_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss:SSSSZ";
	public static final String DEFAULT_DATETIME_NO_SEC_PATTERN = "yyyyMMddHHmm";
	public static final String DATE_DATETIME = "yyyy-MM-dd_HH-mm-ss";
	public static final String ALL_DATETIME_PATTERN = "yyyyMMddHHmmssSSS";

	public static Date getCurrentDate() {
		return new GregorianCalendar(EN_LOCALE).getTime();
	}

	public static Date getCurrentDateTh() throws Exception {
		return new GregorianCalendar(TH_LOCALE).getTime();
	}

	public static Date getCurrentDateByPattern() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_PATTERN);
		String curDate = sdf.format(new Date());
		return sdf.parse(curDate);
	}

	public static Date convertDateByPattern(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_PATTERN);
		String curDate = sdf.format(date);
		return sdf.parse(curDate);
	}

	public static Locale getDefaultLocale() {
		return EN_LOCALE;
	}

	/*
	 * String Thai Date Format with pattern dd/MM/yyyy
	 */
	public static String toStringThaiDateSimpleFormat(Date date) throws Exception {
		SimpleDateFormat thFormat = new SimpleDateFormat(SIMPLE_DATE_PATTERN, TH_LOCALE);
		return thFormat.format(date);
	}

	/*
	 * String Thai Date Format with pattern dd/MM/yyyy
	 */
	public static String toStringThaiDateSimpleFormatReport(Date date) throws Exception {
		SimpleDateFormat thFormat = new SimpleDateFormat(SIMPLE_DATE_PATTERN_REPORT, TH_LOCALE);
		return thFormat.format(date);
	}

	/*
	 * String Thai Date Format with pattern dd/MM/yyyy hh:mm:ss
	 */
	public static String toStringThaiDateTimeSimpleFormat(Date date) throws Exception {
		SimpleDateFormat thFormat = new SimpleDateFormat(SIMPLE_DATETIME_PATTERN, TH_LOCALE);
		return thFormat.format(date);
	}

	/*
	 * String Thai Date Format with pattern dd/MM/yyyy hh:mm:ss
	 */
	public static String toStringThaiDateFullFormat(Date date) throws Exception {
		if (date != null) {
			SimpleDateFormat thFormat = new SimpleDateFormat(FULL_DATE_PATTERN, TH_LOCALE);
			return thFormat.format(date);
		}
		return "";
	}

	/*
	 * String Eng Date Format with pattern dd/MM/yyyy
	 */
	public static String toStringEngDateSimpleFormat(Date date) throws Exception {
		SimpleDateFormat engFormat = new SimpleDateFormat(SIMPLE_DATE_PATTERN, EN_LOCALE);
		if (date != null) {
			return engFormat.format(date);
		} else {
			return null;
		}
	}

	/*
	 * String Eng Date Format with pattern dd/MM/yyyy hh:mm:ss
	 */
	public static String toStringEngDateTimeSimpleFormat(Date date) {
		SimpleDateFormat engFormat = new SimpleDateFormat(SIMPLE_DATETIME_PATTERN, EN_LOCALE);
		return engFormat.format(date);
	}

	/*
	 * String Eng Date Format with pattern yyMM
	 */
	public static String toStringEngDateBySimpleFormat(Date date, String formatDate) {
		SimpleDateFormat engFormat = new SimpleDateFormat(formatDate, EN_LOCALE);
		return engFormat.format(date);
	}

	/*
	 * Thai Date Format with custom pattern and Locale
	 */
	public static String toStringCustomDateFormat(Date date, String pattern, Locale locale) {
		SimpleDateFormat strFormat = new SimpleDateFormat(pattern, locale);
		return strFormat.format(date);
	}

	/*
	 * convert Date String to Date Object . Apply for patterns, dd/MM/yyyy,
	 * dd/MM/yyyy HH:mm:ss, yyyy-MM-dd HH:mm:ss
	 */
	public static Date convertStringToDate(String stringDate) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat();
		format.setCalendar(new GregorianCalendar(EN_LOCALE));
		Date rtnDate = null;
		try {
			format.applyPattern(SIMPLE_DATE_PATTERN);
			rtnDate = format.parse(stringDate);
		} catch (ParseException pe1) {
			try {
				format.applyPattern(SIMPLE_DATETIME_PATTERN);
				rtnDate = format.parse(stringDate);
			} catch (ParseException pe2) {
				format.applyPattern(DEFAULT_DATETIME_PATTERN);
				rtnDate = format.parse(stringDate);
			}
		}
		return rtnDate;
	}

	/*
	 * convert Date String to Date Object . Apply for patterns, dd/MM/yyyy,
	 * dd/MM/yyyy HH:mm:ss, yyyy-MM-dd HH:mm:ss
	 */
	public static Date convertStringToDateByFormat(String stringDate, String pattern) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(pattern, EN_LOCALE);
		Date rtnDate = format.parse(stringDate);
		return rtnDate;
	}

	public static Date convertStringToDateByFormatTh(String stringDate, String pattern) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(pattern, TH_LOCALE);
		Date rtnDate = format.parse(stringDate);
		return rtnDate;
	}

	/*
	 * convert Date String to Date Object . Apply for patterns, dd/MM/yyyy,
	 * dd/MM/yyyy HH:mm:ss, yyyy-MM-dd HH:mm:ss
	 */
	public static Date convertCalendarToDate(Calendar calendar) throws Exception {
		Date rtnDate = null;
		if (calendar != null) {
			long timeInMillis = calendar.getTimeInMillis();
			if (timeInMillis > 0) {
				rtnDate = new Date(timeInMillis);
			}
		}
		return rtnDate;
	}

	public static Timestamp convertCalendarToTimestamp(Calendar calendar) throws Exception {
		Timestamp timestamp = null;
		if (calendar != null) {
			long timeInMillis = calendar.getTimeInMillis();
			if (timeInMillis > 0) {
				timestamp = new Timestamp(timeInMillis);
			}
		}
		return timestamp;
	}

	/*
	 * calculate Diff Date return number of differ days
	 */
	public static long diffDayCalculate(Date startDate, Date endDate) throws Exception {
		long endL = endDate.getTime();
		long startL = startDate.getTime();
		return ((endL - startL) / MILLISECS_PER_DAY);
	}

	public static long diffSecCalculate(Date startDate, Date endDate) throws Exception {
		long endL = endDate.getTime();
		long startL = startDate.getTime();
		return ((endL - startL) / 1000);
	}

	/*
	 * calculate Diff Date return number of differ days
	 */
	public static long diffDayCalculateIgnoreTime(Date startDate, Date endDate) throws Exception {
		/*
		 * Date startDateNoTime = setDateIgnoreTime(startDate); Date
		 * endDateNoTime = setDateIgnoreTime(endDate); long endL =
		 * endDateNoTime.getTime(); long startL = startDateNoTime.getTime();
		 * return ((endL - startL) / MILLISECS_PER_DAY);
		 */
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_PATTERN);
		String startDtString = sdf.format(startDate);
		Date startDateNoTime = sdf.parse(startDtString);
		String endDtString = sdf.format(endDate);
		Date endDateNoTime = sdf.parse(endDtString);

		long endL = endDateNoTime.getTime();
		long startL = startDateNoTime.getTime();
		return ((endL - startL) / MILLISECS_PER_DAY);
	}

	/*
	 * private static Date setDateIgnoreTime(Date date) throws Exception{
	 * Calendar calendar = new GregorianCalendar(); calendar.setTime(date);
	 * calendar.set(Calendar.HOUR, 0); calendar.set(Calendar.MINUTE, 0);
	 * calendar.set(Calendar.SECOND, 0); calendar.set(Calendar.MILLISECOND, 0);
	 * return calendar.getTime(); }
	 */

	/*
	 * calculate Diff Month return number of differ months
	 */
	public static long diffMonthCalculate(Date startDate, Date endDate) throws Exception {
		long endL = endDate.getTime();
		long startL = startDate.getTime();
		return ((endL - startL) / (MILLISECS_PER_DAY * 30));
	}

	/*
	 * calculate Diff Month return number of differ months
	 */
	public static long diffMonthCalculateIgnoreTime(Date startDate, Date endDate) throws Exception {
		/*
		 * Date startDateNoTime = setDateIgnoreTime(startDate); Date
		 * endDateNoTime = setDateIgnoreTime(endDate); long endL =
		 * endDateNoTime.getTime(); long startL = startDateNoTime.getTime();
		 * return ((endL - startL) / MILLISECS_PER_DAY);
		 */
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_PATTERN);
		String startDtString = sdf.format(startDate);
		Date startDateNoTime = sdf.parse(startDtString);
		String endDtString = sdf.format(endDate);
		Date endDateNoTime = sdf.parse(endDtString);

		long endL = endDateNoTime.getTime();
		long startL = startDateNoTime.getTime();
		return ((endL - startL) / (MILLISECS_PER_DAY * 30));
	}

	public static long diffYearCalculate(Date startDate, Date endDate) throws Exception {
		long endL = endDate.getTime();
		long startL = startDate.getTime();
		return ((endL - startL) / (MILLISECS_PER_DAY * 365));
	}

	/*
	 * calculate Diff Year return number of differ years
	 */
	public static long diffYearCalculateIgnoreTime(Date startDate, Date endDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_PATTERN);
		String startDtString = sdf.format(startDate);
		Date startDateNoTime = sdf.parse(startDtString);
		String endDtString = sdf.format(endDate);
		Date endDateNoTime = sdf.parse(endDtString);

		long endL = endDateNoTime.getTime();
		long startL = startDateNoTime.getTime();
		return ((endL - startL) / (MILLISECS_PER_DAY * 365));
	}

	/*
	 * shift min up int mins = +,-
	 */
	// Add by Chayatorn 12/05/2010
	public static Date shiftMinute(Date inputDate, int mins) throws Exception {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(inputDate);
		calendar.add(Calendar.MINUTE, mins);
		return calendar.getTime();
	}

	/*
	 * shift date up
	 */
	public static Date shiftDateUp(Date inputDate, int days) throws Exception {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(inputDate);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	/*
	 * shift date down
	 */
	public static Date shiftDateDown(Date inputDate, int days) throws Exception {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(inputDate);
		int dayDown = days * -1;
		calendar.add(Calendar.DAY_OF_MONTH, dayDown);
		return calendar.getTime();
	}

	/*
	 * shift month up
	 */
	public static Date shiftMonthUp(Date inputDate, int months) throws Exception {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(inputDate);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/*
	 * shift month down
	 */
	public static Date shiftMonthDown(Date inputDate, int months) throws Exception {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(inputDate);
		int monthDown = months * -1;
		calendar.add(Calendar.MONTH, monthDown);
		return calendar.getTime();
	}

	public static String getCurrentMonthYear() {
		SimpleDateFormat engFormat = new SimpleDateFormat("MMyyyy", TH_LOCALE);
		return engFormat.format(new Date());
	}

	public static String getCurrentDayMonthYear() {
		SimpleDateFormat engFormat = new SimpleDateFormat("ddMMyy", EN_LOCALE);
		return engFormat.format(new Date());
	}

	public static String getCurrentHoMinSec() {
		SimpleDateFormat engFormat = new SimpleDateFormat("HHmmss", EN_LOCALE);
		return engFormat.format(new Date());
	}

	/*
	 * String Eng Date Format with pattern yyMM
	 */
	public static String toStringThaiDateBySimpleFormat(Date date, String formatDate) {
		SimpleDateFormat engFormat = new SimpleDateFormat(formatDate, TH_LOCALE);
		return engFormat.format(date);
	}

	public static boolean compareDateTime(String timeFrom, String timeTo) throws ParseException {
		boolean checked = false;

		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		Date from = sdf.parse(timeFrom);
		Date to = sdf.parse(timeTo);

		if (from.after(to)) {
			checked = true;
		}
		return checked;
	}

	/*
	 * get current date timestamp dd/MM/yyyy HH:mm:ss, yyyy-MM-dd HH:mm:ss
	 */
	public static java.sql.Timestamp getCurrentDateTimeStamp() {
		Date curDate = null;
		java.sql.Timestamp currentTimestamp = null;
		try {
			curDate = getCurrentDate();
			currentTimestamp = new java.sql.Timestamp(curDate.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentTimestamp;
	}

	public static Date addDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + day);
		return cal.getTime();
	}

	public static java.sql.Date getCurrentSqlDate() {
		return new java.sql.Date(new java.util.Date().getTime());
	}

	public static java.sql.Date getSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static Date getDateWithoutTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getTomorrowDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}
}
