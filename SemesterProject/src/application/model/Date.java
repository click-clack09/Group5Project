package application.model;

public class Date implements Comparable<Date> {
	
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	
	private boolean valid;
	
	private int display;
	
	/*
	 * CONSTRUCTORS
	 */
	
	public Date() {
		year = -1;
		month = -1;
		day = -1;
		hour = -1;
		minute = -1;
		
		valid = false;
		
		display = -1;
	}
	
	/**
	 * @param year : the user inputed year to set
	 * @param month : the user inputed month to set
	 * @param day : the user inputed day to set
	 */
	public Date(int year, int month, int day) {
		this();
		
		valid = validateDate(year, month, day);
		
		if(valid) {
			this.year = year;
			this.month = month;
			this.day = day;
		}	
	}
	
	/**
	 * @param year : the user inputed year to set
	 * @param month : the user inputed month to set
	 * @param day : the user inputed day to set
	 * @param hour : the user inputed hour to set
	 * @param minute : the user inputed minute to set
	 */
	public Date(int year, int month, int day, int hour, int minute) {
		this(year, month, day);
		
		valid = validateTime(hour, minute);
		
		if(valid) {
			this.hour = hour;
			this.minute = minute;
		}
		//validate date, if valid set
	}
	
	/*
	 * HELPER METHODS
	 */
	
	/**
	 * @param year : year to check validity of
	 * @param month : month to check validity of
	 * @param day : day to check validity of
	 * @return : the validity of the given year/month/day combination
	 */
	public boolean validateDate(int year, int month, int day) {
		if(year < 0) return false;
		if(month < 1 || month > 12) return false;
		if(day < 0) return false;
		
		switch(month) {
			case 9:		//September
			case 4:		//April
			case 6:		//June
			case 11:	//November
				if(day > 30) return false;
				break;
			case 1:		//January
			case 3:		//March
			case 5:		//May
			case 7:		//July
			case 8:		//August
			case 10:	//October
			case 12:	//December
				if(day > 31) return false;
				break;
			case 2:		//February
				if(year % 100 == 0) {
					if(year % 400 == 0) {
						if(day > 29) return false;
					}
					else {
						if(day > 28) return false;
					}
				}
				else if(year % 4 == 0) {
					if(day > 29) return false;
				}
				else {
					if(day > 28) return false;
				}				
		}
		
		return true;
	}
	
	/**
	 * @param hour : hour to check validity of
	 * @param minute : minute to check validity of
	 * @return the validity of the given hour:minute combination
	 */
	public boolean validateTime(int hour, int minute) {
		if(hour < 0 || hour > 24) return false;
		if(minute < 0 || minute > 59) return false;
		
		return true;
	}
	
	/*
	 * GETTERS
	 */
	
	/**
	 * @return the variable valid
	 */
	public boolean isValid() {
		return valid;
	}
	
	/**
	 * @return the variable year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * @return the variable month
	 */
	public int getMonth() {
		return month;
	}
	
	
	/**
	 * @return the variable day
	 */
	public int getDay() {
		return day;
	}
	
	
	/**
	 * @return the variable hour
	 */
	public int getHour() {
		return hour;
	}
	
	/**
	 * @return the variable minute
	 */
	public int getMinute() {
		return minute;
	}
	
	/**
	 * @return the variable display
	 */
	public int getDisplay() {
		return display;
	}
	
	/*
	 * SETTERS
	 */
	
	/**
	 * @param valid : the boolean to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	/**
	 * @param year : the int to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * @param month : the int to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	
	/**
	 * @param day : the int to set
	 */
	public void setDay(int day) {
		this.day = day;
	}
	
	/**
	 * @param hour : the int to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	/**
	 * @param minute : the int to set
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	/**
	 * @param type : the String to set
	 */
	public void setDisplay(String type) {
		switch(type) {
			default:
				display = 0;
				break;
		}
	}
	
	/*
	 * OVERRIDES
	 */
	
	@Override
	public int compareTo(Date date) {
		if(date.isValid()) {
			if(valid) {
				if(year > date.getYear()) return 1;
				if(date.getYear() > year) return -1;
				
				if(month > date.getMonth()) return 1;
				if(date.getMonth() > month) return -1;
		
				if(day > date.getDay()) return 1;
				if(date.getDay() > day) return -1;
				
				if(hour < 0 && date.getHour() > 1) return 1;
				if(date.getHour() < 0 && hour > 1) return -1;
				
				if(hour > date.getHour()) return 1;
				if(date.getHour() > hour) return -1;
				
				if(minute > date.getMinute()) return 1;
				if(date.getMinute() > minute) return -1;
				
				return 0;
			}
			
			return -1;
		}
		
		if(valid) {
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public String toString() {
		if(year < 0) {
			return "invalid date";
		}
		
		String date = "";
		
		//add to switch statements based on acceptable formats
		
		switch(display) {
			case -1: 
			default:
				date += year + " " + month + " " + day;
		}
		
		if(hour >= -1) {
			switch(display) {
				case -1:
				default:
					date += " " + hour + ":" + minute;
			}	
		}
		
		return date;
	}
}
