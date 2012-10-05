package com.pensio.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper
{
	public Date currentTime()
	{
		return new Date();
	}
	
	public Date currentUTC()
	{
		return Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
	}
	
	public boolean isToday(Date date)
	{
		Date today = currentTime();
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(today);
		
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
				cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
				cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}

	public boolean thisYear(Date date)
	{
		Date today = currentTime();
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(today);
		
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
				cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR));
	}
	
	public int getYear()
	{
		Date today = currentTime();
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(today);
		return cal1.get(Calendar.YEAR);
	}
	
	public int getYear(Date date)
	{
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		return cal1.get(Calendar.YEAR);
	}
	
	/**
	 * @param date
	 * @return actual month, you know the first is 1
	 */
	public int getMonth(Date date)
	{
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		return cal1.get(Calendar.MONTH) + 1;
	}
	
	public int getHours(Date date)
	{
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getMinutes(Date date)
	{
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now.get(Calendar.MINUTE);
	}
	
	public int getSeconds(Date date)
	{
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now.get(Calendar.SECOND);
	}
	
	public int getSecondsSinceMidnight(Date date)
	{
		return getHours(date) * 3600 + getMinutes(date) * 60 + getSeconds(date);
	}
	
	public static Date newDate(int year, int month, int day, int hourOfDay, int minute, int second)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hourOfDay, minute, second);
		return cal.getTime();
	}
	
	public boolean sameDay(Date date1, Date date2)
	{
		return formatDate("yyyyMMdd", date1).equals(formatDate("yyyyMMdd", date2));
	}
	
	public static String formatDate(String format, Date date)
	{
		if(date == null)
			return null;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public Date add(Date date, DateValue dateValue)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		dateValue.add(calendar);
		return calendar.getTime();
	}
	
	public static abstract class DateValue
	{
		private int value ;

		public DateValue(int value)
		{
			this.value = value;
		}
		
		public int get()
		{
			return value;
		}
		
		public void add(Calendar calendar)
		{
			calendar.add(getField(), value);
		}
		
		protected abstract int getField();
	}
	
	public static class Seconds
		extends DateValue
	{
		public Seconds(int value)
		{
			super(value);
		}

		@Override
		protected int getField()
		{
			return Calendar.SECOND;
		}

	}
	
	public static class Minutes
		extends DateValue
	{
		public Minutes(int value)
		{
			super(value);
		}
		
		@Override
		protected int getField()
		{
			return Calendar.MINUTE;
		}
	}
	
	public static class Hours
		extends DateValue
	{
		public Hours(int value)
		{
			super(value);
		}
		
		@Override
		protected int getField()
		{
			return Calendar.HOUR;
		}
	}
	
	public static class Days
		extends DateValue
	{
		public Days(int value)
		{
			super(value);
		}
		
		@Override
		protected int getField()
		{
			return Calendar.DAY_OF_MONTH;
		}
	}
	
	public static class Months
		extends DateValue
	{
		public Months(int value)
		{
			super(value);
		}
		
		@Override
		protected int getField()
		{
			return Calendar.MONTH;
		}
	}
	
	public static class Years
		extends DateValue
	{
		public Years(int value)
		{
			super(value);
		}
		
		@Override
		protected int getField()
		{
			return Calendar.YEAR;
		}
	}
}