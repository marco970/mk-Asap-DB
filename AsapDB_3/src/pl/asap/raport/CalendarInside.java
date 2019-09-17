package pl.asap.raport;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

public class CalendarInside {
	
	

	  private Calendar kalend;

	  static String[] nazwaDnia = {  "nd ", "pon", "wt ",
	                                 "śr ", "czw", "pt ", "sob" };

	  static String[] nazwaMies = { "styczeń", "luty", "marzec", "kwiecień",
	                           "maj", "czerwiec", "lipiec", "sierpień",
	                           "wrzesień", "październik", "listopad", "grudzień"
	                         };
	  static int[] ldni = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	  int rok;
	  int mies;

	  public CalendarInside(int rok, int mies) {
	     this.rok = rok;
	     this.mies = mies - 1;
	     this.kalend = new GregorianCalendar();
	     kalend.setTimeZone(TimeZone.getTimeZone("GMT+1"));
	  }
	  public int getDayNo(int month)	{
		  if (month == 2 && rokPrzest()) return 29;
		  else {
//			  System.out.println("getDayNo "+ldni[month-1]);
			  return ldni[month];
		  }
	  }
	  public boolean rokPrzest()	{
		  if ((rok - 2016)%4 == 0) return true;
		  else return false;  
	  }
	  
	  public String getDayName(int dayNo) {	//właściwie to Day of Week
		  String CalendarOutput ="";
		  kalend.set(rok, mies, dayNo);
//		  System.out.println("getDayName: rok = "+rok+" mies = "+(mies+1)+" dayNo: "+dayNo+" --> "+(kalend.get(Calendar.DAY_OF_WEEK)-1));
		  CalendarOutput = nazwaDnia[kalend.get(Calendar.DAY_OF_WEEK)-1];
		  
		  return CalendarOutput;
	  }
	  public String getDate(int dayNo)	{
		  String date = "";
		  date = dayNo+"/"+(mies+1)+"/"+rok;
		  return date;
	  }
	  public List<Integer> getWeekendDays()	{
		  List<Integer> weekendDays = new LinkedList<>();
		  System.out.println("weekendDays:");
		  for (int i = 1; i <= getDayNo(mies); i++ )	{
			  System.out.print(getDayName(i)+", ");
			  if(getDayName(i).equals("nd ") || getDayName(i).equals("sob"))	{
				  weekendDays.add(i);
				  System.out.print(i+", ");
			  } 
		  }
		  return weekendDays;
	  }

	  public ArrayList<String> getAllDays()	{
		  ArrayList<String> days = new ArrayList<String>();
		  String a;
//		  System.out.println((mies+1)+" -> "+getDayNo(mies));
		  for (int i = 0; i < getDayNo(mies); i++ )	{
			  a ="";
			  a = "<html><center>"+numString(i+1)+"."+numString(mies+1)+"<br>"+getDayName(i+1)+"</center></html>";
			  days.add(a);
		  }
		return days;
	  }
	  
	  public String numString(int num)	{
			String numStr;
			if (num<10) numStr = "0"+ num;
			else numStr = ""+num;
			return numStr;
		}
}
