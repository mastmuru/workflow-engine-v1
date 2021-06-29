/**
\ * @author Kathiravan
 */
package com.fa.workflowengine.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

public class DateUtils {

	static {
		// TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));

	}

	private static String formatMMDDYYYHHMM = "yyyy-MM-dd hh:mm:ss aa";
	private static DecimalFormat decimalFormatHHHMMM = new DecimalFormat("######");

	public static Date sysDate() {
		return new Date();
	}

	public static Date convertStringToDate(String dateValue) {
		if (StringUtils.isNotEmpty(dateValue)) {
			DateFormat dfFormat = new SimpleDateFormat("dd/MM/yyyy");
			try {
				return dfFormat.parse(dateValue);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String dateToStringDDMMYYYY(Date value) {
		String val = "";
		if (value != null) {
			String DATE_FORMAT_NOW = "dd/MM/yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			val = sdf.format(value);
		}
		return val;
	}

	public static String dateToStringFormatYyyyMmDdHhMmSsSlash(Date value) {
		String val = "";
		if (value != null) {
			String DATE_FORMAT_NOW = "dd/MM/yyyy HH:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			val = sdf.format(value);
		}
		return val;
	}

	public static String dateToStringFormatHhMmSs(Date value) {
		String val = "";
		if (value != null) {
			String DATE_FORMAT_NOW = "HH:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			val = sdf.format(value);
		}
		return val;
	}

	public static Date convertStringToDateTimeWithPeriod(String dateValue) {
		if (StringUtils.isNotEmpty(dateValue)) {
			DateFormat dfFormat = new SimpleDateFormat("dd/MM/yyyy H:mm a");
			try {
				return dfFormat.parse(dateValue);
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	public static String convertDateTimeToStringWithPeriod(Date dateValue) {
		if (dateValue != null) {
			try {
				String DATE_FORMAT_NOW = "dd/MM/yyyy H:mm a";
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
				return sdf.format(dateValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Map<String, String> findTwoDaysDiffHourMinitsSecendsMilliseconds(Date fromDate, Date toDate) {
		Map<String, String> map = new HashMap<String, String>();
		if (fromDate != null && toDate != null) {
			try {
				long diff = fromDate.getTime() - toDate.getTime();
				int years = (int) (diff / (24 * 60 * 60 * 1000)) / 365;
				map.put("Age", String.valueOf(years));
				int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
				map.put("Days", String.valueOf(diffDays));
//				System.out.println("difference between days: " + diffDays);
				int diffhours = (int) (diff / (60 * 60 * 1000));
				map.put("hours", decimalFormatHHHMMM.format(diffhours));
//				System.out.println("difference between hours: " + decimalFormatHHHMMM.format(diffhours));
				int diffmin = (int) (diff / (60 * 1000));
				map.put("Minitues", decimalFormatHHHMMM.format(diffmin));
//				System.out.println("difference between minutues: " + decimalFormatHHHMMM.format(diffmin));
				int diffsec = (int) (diff / (1000));
				map.put("Secends", decimalFormatHHHMMM.format(diffsec));
//				System.out.println("difference between seconds: " + decimalFormatHHHMMM.format(diffsec));
				map.put("Milliseconds", decimalFormatHHHMMM.format(diff));
//				System.out.println("difference between milliseconds: " + decimalFormatHHHMMM.format(diff));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

//		 (d/m/yyyy h:mm:ss tt", "dd/MM/yyyy hh:mm:ss", "d/M/yyyy hh:mm tt", "d/m/yyyy h:mm tt", "d/M/yyyy h:mm:ss", 
//		 "d/M/yyyy hh tt", "d/M/yyyy h:mm", "d/M/yyyy h:mm","dd/MM/yyyy hh:mm", "dd/M/yyyy hh:mm)
	public static Boolean validateIsDateFormatInHHMM(String dateValue) {
		boolean isValue = false;
		if (StringUtils.isNotEmpty(dateValue)) {
			DateFormat dfFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			try {
				System.out.println(dfFormat.parse(dateValue));
				isValue = true;
			} catch (ParseException e) {
				isValue = false;
				e.printStackTrace();
			}
		}
		return isValue;
	}

	public static Boolean validateIsDateFormat(String dateValue) {
		boolean isValue = false;
		if (StringUtils.isNotEmpty(dateValue)) {
			DateFormat dfFormat = new SimpleDateFormat("dd/MM/yyyy");
			try {
				System.out.println("validateIsDateFormat " + dfFormat.parse(dateValue));
				isValue = true;
			} catch (ParseException e) {
				isValue = false;
				e.printStackTrace();
			}
		}
		return isValue;
	}

	public static void main(String[] args) {
		// validateIsDateFormat("30/09/2020");
		// System.out.println(isDatebetweenTwoDates(convertStringToDate("18/01/2021"),
		// convertStringToDate("20/01/2021"),
		// convertStringToDate("21/01/2021")));
		System.out.println(validateCaseCreationDateFormat("26/04/2020"));
	}

	public static String uniqueNoYYYMMYDDMilli() {
		SimpleDateFormat dfFormat = new SimpleDateFormat("yyyMMyddkkmmssSS");
		return dfFormat.format(new Date());
	}

	public static String uniqueNoMilli() {
		SimpleDateFormat dfFormat = new SimpleDateFormat("ddMMyyyykkmm");
		return dfFormat.format(new Date());
	}

	public static String uniqueNoYYYY() {
		SimpleDateFormat dfFormat = new SimpleDateFormat("yyyy");
		return dfFormat.format(new Date());
	}

	public static String uniqueNoYYYYMMDD() {
		SimpleDateFormat dfFormat = new SimpleDateFormat("yyyyMMdd");
		return dfFormat.format(new Date());
	}

	public static long dateDifferenceInHour(Date fromDate, Date toDate) {
		LocalDateTime fromDateTime = DateToLocalDateTime(fromDate);
		LocalDateTime toDateTime = DateToLocalDateTime(toDate);
		long diffInDays = ChronoUnit.DAYS.between(fromDateTime, toDateTime);
		long diffInHours = ChronoUnit.HOURS.between(fromDateTime, toDateTime);
		long diffInMin = ChronoUnit.MINUTES.between(fromDateTime, toDateTime);
		long diffInSec = ChronoUnit.SECONDS.between(fromDateTime, toDateTime);
		String result = diffInDays + ":" + diffInHours + ":" + diffInMin + ":" + diffInSec;
		System.out.println(result);
		return diffInHours;
	}

	public static LocalDateTime DateToLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	public static boolean isDatebetweenTwoDates(Date fromDate, Date toDate, Date currentDate) {
		return currentDate.after(fromDate) && currentDate.before(toDate);
	}

	public static List<LocalDate> getDatesBetweenUsingJava8(LocalDate startDate, LocalDate endDate) {
		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i))
				.collect(Collectors.toList());
	}

	public static List<LocalDate> getDatesBetweenUsingJava9(LocalDate startDate, LocalDate endDate) {
		return startDate.datesUntil(endDate).collect(Collectors.toList());
	}

	public static String convertLdtToString(LocalDate localDate) {
		// Get current date time
		String DATE_FORMAT_NOW = "dd/MM/yyyy";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_NOW);
		String formatDateTime = localDate.format(formatter);
		return formatDateTime;
	}

	/*
	 * request format is convertStringToDBDateWithHHMMSSA("02/16/2021 1:36:00 pm")
	 */
	/* written from Kathiravan */
	public static Date convertStringToDBDateWithHHMMSSA(String date) {
		try {
			if (StringUtils.isNotBlank(date) && !date.equalsIgnoreCase("null")) {
//					return new Date(Date.parse(date));
				return new Date(formatMMDDYYYHHMM.format(date));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String uniqueNoYYYYMM() {
		SimpleDateFormat dfFormat = new SimpleDateFormat("yyyyMM");
		return dfFormat.format(new Date());
	}

	public static Date convertStringYYYYMMDDTHHMMSSZ(String dateValue) {
		if (StringUtils.isNotEmpty(dateValue)) {
			DateFormat dfFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
			try {
				return dfFormat.parse(dateValue);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Date convertStringDDMMYYYYYHHMMSS(String dateValue) {
		if (StringUtils.isNotEmpty(dateValue)) {
			DateFormat dfFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			try {
				return dfFormat.parse(dateValue);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Date convertStringYYYYMMDDTHHMMSSSSZ(String dateValue) {
		if (StringUtils.isNotEmpty(dateValue)) {
			DateFormat dfFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			try {
				return dfFormat.parse(dateValue);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Date getDateOnly() {
		Date today = sysDate();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return formatter.parse(formatter.format(today));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean validateCaseCreationDateFormat(String dateValue) {
		boolean isValue = false;

		String[] permissFormats = new String[] { "d/m/yyyy h:mm:ss tt", "d/m/yyyy h:mm tt", "dd/MM/yyyy hh:mm:ss",
				"d/M/yyyy h:mm:ss", "d/M/yyyy hh:mm tt", "d/M/yyyy hh tt", "d/M/yyyy h:mm", "d/M/yyyy h:mm",
				"dd/MM/yyyy hh:mm", "dd/M/yyyy hh:mm", "dd/MM/yyyy", "dd/M/yyyy HH:mm" };
		for (int i = 0; i < permissFormats.length; i++) {
			try {
				SimpleDateFormat sdfObj = new SimpleDateFormat(permissFormats[i]);
				sdfObj.setLenient(false);
				sdfObj.parse(dateValue);
				isValue = true;

				break;
			} catch (Exception e) {
				isValue = false;
			}
		}
		if (!isValue) {
			isValue = isValidDate1(dateValue);
		} else {
			isValue = isValue;
		}
		return isValue;
	}

	public static boolean isValidDate1(String d) {
		String regex = "^([1-9])/([1-9])/[0-9]{4} [0-9]{2} [A-Z][A-Z]$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) d);
		return matcher.matches();
	}

}
