package pl.asap.raport;

import java.util.HashSet;
import java.util.Set;

public class DayContent {
	
	
	private int dayNr;
	
	private Set<String>	zzSet;
	private int zzTime;
	
	private Set<String>	pzSet;
	private int pzTime;
	
	private Set<String>	wpSet;
	private int wpTime;
	
	private Set<String>	dkSet;
	private int dkTime;
	
	private static Set<Integer> checkDay = new HashSet<>();
	

	private DayContent(int dayNr) {
		this.dayNr = dayNr;
		this.zzSet = new HashSet<>();
		this.zzTime = 0;
		this.pzSet = new HashSet<>();
		this.pzTime = 0;
		this.wpSet = new HashSet<>();
		this.wpTime = 0;
		this.dkSet = new HashSet<>();
		this.dkTime = 0;
	}
	
	
	public void addContent(String sapNr, int time)	{
		String str = sapNr.substring(0, 2);
		if (str.equals("ZZ") || str.equals("Z/")) {
			if (time>0) {
				zzSet.add(sapNr);
				zzTime = zzTime + time;
			}
			else zzSet.add("");
		}
		if (str.equals("PZ")) {
			if (time>0) {
				pzSet.add(sapNr);
				pzTime = pzTime + time;
			}
			else pzSet.add("");
		}
		if (str.equals("WP")) {
			if (time>0) {
				wpSet.add(sapNr);
				wpTime = wpTime + time;
			}
			else wpSet.add("");
		}
		if (str.equals("DK")) {
			if (time>0) {
				dkSet.add(sapNr);
				dkTime = dkTime + time;
			}
			else dkSet.add("");
		}
	}
	
	public Integer getHours(String typeOfSapNr)	{
		int dayHours = 0;
		
		if (typeOfSapNr.equals("ZZ")) {
			dayHours=zzTime;
		}
		if (typeOfSapNr.equals("PZ")) {
			dayHours=pzTime;
		}
		if (typeOfSapNr.equals("WP")) {
			dayHours=wpTime;
		}
		if (typeOfSapNr.equals("DK")) {
			dayHours=dkTime;
		}
		
		return dayHours;
	}
	
	public String getContent(String typeOfSapNr)		{
		
		String resultString = "";
		if (typeOfSapNr.equals("ZZ")) {
			for (String el: zzSet)	{	
					if (!el.equals("")) {
						if (!resultString.equals("")) {
							resultString = resultString + ", " + el;
						}
						else resultString = el;
					} 
			}
		}
		if (typeOfSapNr.equals("PZ")) {
			for (String el: pzSet)	{	
					if (!el.equals("")) {
						if (!resultString.equals("")) {
							resultString = resultString + ", " + el;
						}
						else resultString = el;
					} 
			}
		}
		if (typeOfSapNr.equals("WP")) {
			for (String el: wpSet)	{	
					if (!el.equals("")) {
						if (!resultString.equals("")) {
							resultString = resultString + ", " + el;
						}
						else resultString = el;
					} 
			}
		}
		if (typeOfSapNr.equals("DK")) {
			for (String el: dkSet)	{	
					if (!el.equals("")) {
						if (!resultString.equals("")) {
							resultString = resultString + ", " + el;
						}
						else resultString = el;
					} 
			}
		}
		return resultString;
	}
	
	public void DayContentShow()	{
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("dzień: "+ dayNr);
		System.out.println("--------------------------------------");
		System.out.println("ZZ time: "+ zzTime);
		for (String el: zzSet)	{
			System.out.print(el+", ");
		}
		System.out.println();
		System.out.println("PZ time: "+ pzTime);
		for (String el: pzSet)	{
			System.out.print(el+", ");
		}
		System.out.println();
		System.out.println("WP time: "+ wpTime);
		for (String el:wpSet)	{
			System.out.print(el+", ");
		}
		System.out.println();
		System.out.println("DK time: "+ dkTime);
		for (String el: dkSet)	{
			System.out.print(el+", ");
		}
	}
	
	public static synchronized DayContent getInstance(int dayNr)	{
		
		if (!checkDay.contains(dayNr))	{
			return new DayContent(dayNr);
		}
		else return null;
	}
}
