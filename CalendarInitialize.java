package application;

import java.util.ArrayList;
import java.time.*;
public class CalendarInitialize extends CalendarEvents {
public CalendarInitialize(int month, int day, int year) {
		super(month, day, year);
		// TODO Auto-generated constructor stub
	}
//construct calender objects to be stored in a calendar.

//Create array Calendar, store in event objects

	public void populateCalendar(ArrayList<CalendarEvents> list) {
		ArrayList<String> calendar = new ArrayList<>();
		int i=0;
		//iterate through the whole month(30 days)
		while(i<list.size()) {
			 LocalDate currentdate = LocalDate.now();
			 Month currentMonth = currentdate.getMonth();
			 int currentDay = currentdate.getDayOfMonth();
			 int currentYear = currentdate.getYear();
			//if currItem in list is in the currMonth add to our array
			CalendarEvents tempEvent = list.get(i);
			String monthVal = String.valueOf(currentMonth);
			//we need to set month numbers to month names for this to work
				String monthString;
		        switch (tempEvent.getMonth()) {
		            case 1:  monthString = "JANUARY";
		                     break;
		            case 2:  monthString = "FEBUARY";
		                     break;
		            case 3:  monthString = "MARCH";
		                     break;
		            case 4:  monthString = "APRIL";
		                     break;
		            case 5:  monthString = "MAY";
		                     break;
		            case 6:  monthString = "JUNE";
		                     break;
		            case 7:  monthString = "JULY";
		                     break;
		            case 8:  monthString = "AUGUST";
		                     break;
		            case 9:  monthString = "SEPTEMBER";
		                     break;
		            case 10: monthString = "OCTOBER";
		                     break;
		            case 11: monthString = "NOVEMBER";
		                     break;
		            case 12: monthString = "DECEMBER";
		                     break;
		            default: monthString = "Invalid month";
		                     break;
		        }
		        System.out.println(monthString);
			System.out.println("monthVal is, "+monthVal);
			//if this event's month is = to current month add to our displayed Calendar, otherwise do not add it
			if(monthString.equals(monthVal)==true) {
				System.out.println("this month is in our current month!" + monthVal);
				calendar.add(tempEvent.toString());
			}
			
			i++;
		}
		System.out.println(calendar);
	}

	@Override
	public String toString() {
		return "calender holds: ";
	}

}
