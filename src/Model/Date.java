package Model;

import java.time.LocalDate;

public class Date {
	protected int year;
	protected int month;
	protected int day;
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Date(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public Date() {
		this.year = 0;
		this.month = 0;
		this.day = 0;
	}

	public Date(String date){
		String[] tmp = date.split("-");
		this.year = Integer.parseInt(tmp[0]);
		this.month = Integer.parseInt(tmp[1]);
		this.day = Integer.parseInt(tmp[2]);
	}
	
	public static Date parse(String date) {
		Date dateN = new Date();
		String[] tmp = date.split("-");
		dateN.setYear(Integer.parseInt(tmp[0]));
		dateN.setMonth(Integer.parseInt(tmp[1]));
		dateN.setDay(Integer.parseInt(tmp[2]));
		return dateN;
	}
}
