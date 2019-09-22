package pl.asap.raport;

import java.util.HashSet;
import java.util.Set;

public class TimeContent {
	
	//pola
	int dayNo;
	
	Set<String> contentZZ= new HashSet<>();
	int timeZZ = 0;
	
	Set<String> contentPZ= new HashSet<>();
	int timePZ = 0;
	
	Set<String> contentWP= new HashSet<>();
	int timeWP = 0;
	
	Set<String> contentDK= new HashSet<>();
	int timeDK = 0;
	
	static Set<Integer> checkDayInstance = new HashSet<>();
	
	
	
	private TimeContent(int dayNo)	{
		this.dayNo = dayNo;
	}
	
	public static synchronized TimeContent getInstance(int dayNo)	{
		if (checkDayInstance.contains(dayNo))	{
			return null;
		}
		else	{
			return new TimeContent(dayNo);
		}		
	}
	
	public void addZZ(String zz, int time)	{
		contentZZ.add(zz);
		timeZZ = timeZZ+time;
	}
	
	public void addPZ(String pz, int time)	{
		contentPZ.add(pz);
		timePZ = timePZ+time;
	}
	
	public void addWP(String wp, int time)	{
		contentWP.add(wp);
		timeWP = timeWP+time;
	}
	
	public void addDK(String dk, int time)	{
		contentDK.add(dk);
		timeDK = timeDK+time;
	}

	public int getDayNo() {
		return dayNo;
	}

	public Set<String> getContentZZ() {
		return contentZZ;
	}

	public int getTimeZZ() {
		return timeZZ;
	}

	public Set<String> getContentPZ() {
		return contentPZ;
	}

	public int getTimePZ() {
		return timePZ;
	}

	public Set<String> getContentWP() {
		return contentWP;
	}

	public int getTimeWP() {
		return timeWP;
	}

	public Set<String> getContentDK() {
		return contentDK;
	}

	public int getTimeDK() {
		return timeDK;
	}
	

}
