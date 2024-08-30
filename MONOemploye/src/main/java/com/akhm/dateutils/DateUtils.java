package com.akhm.dateutils;

import java.util.Date;
import java.util.Optional;

public class DateUtils {
	private DateUtils() {

	}

	public static Date convertUtilToSql(java.sql.Date sdate) {
		return Optional.ofNullable(sdate).map(date -> new Date(sdate.getTime())).orElse(null);

	}

	public static java.sql.Date convertSqlToUtil(Date udate) {
		return Optional.ofNullable(udate).map(date ->new  java.sql.Date(udate.getTime())).orElse(null);
	}

}
