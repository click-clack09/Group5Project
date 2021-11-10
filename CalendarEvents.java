package application;

public class CalendarEvents {
int month,day,year;

public CalendarEvents(int month, int day, int year) {
	this.month = month;
	this.day = day;
	this.year = year;
}
public int getMonth() {
	return month;
}

public void setMonth(int month) {
	this.month = month;
}

@Override
public String toString() {
	return "CalendarEvents [month=" + month + ", day=" + day + ", year=" + year + "]";
}
public int getDay() {
	return day;
}

public void setDay(int day) {
	this.day = day;
}

public int getYear() {
	return year;
}

public void setYear(int year) {
	this.year = year;
}
}
