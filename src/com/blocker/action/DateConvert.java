package com.blocker.action;
import java.text.SimpleDateFormat;

	public final class DateConvert {
		
		private DateConvert() {
		}
		/**
		 * method used to convert the util date to sql date.
		 * @return the string success
		 * @param date to set
		*/
		public static java.sql.Date convertDate(final java.util.Date date) {
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String st = s.format(date);
			java.sql.Date sqlDate = java.sql.Date.valueOf(st);
			return sqlDate;
		}

	}
