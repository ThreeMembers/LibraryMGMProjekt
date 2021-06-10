package Model;

import org.json.simple.JSONObject;

public class Date {
	public static final int number_months_in_year = 12;

	public static final int number_days_in_january = 31;
	public static final int number_days_in_february = 28;
	public static final int number_days_in_february_leap = 29;
	public static final int number_days_in_march = 31;
	public static final int number_days_in_april = 30;
	public static final int number_days_in_may = 31;
	public static final int number_days_in_june = 30;
	public static final int number_days_in_july = 31;
	public static final int number_days_in_august = 31;
	public static final int number_days_in_september = 30;
	public static final int number_days_in_october = 31;
	public static final int number_days_in_november = 30;
	public static final int number_days_in_december = 31;

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

	public Date(String date) {
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

	public boolean isEqual(Date target) {
		if (this.year == target.getYear()) {
			if (this.month == target.getMonth()) {
				if (this.day == target.getDay()) {
					return true;
				}
			}
		}
		return false;
	}

	public Date add(int numberDay) {
		int numberDaysInMonth = getNumberDaysInMonth(this.month, this.year);
		int nextDate = this.day;
		int nextMonth = this.month;
		int nextYear = this.year;
		if (numberDay > numberDaysInMonth && numberDaysInMonth > 0) {

			int temp = numberDaysInMonth;
			while (numberDay > temp) {
				numberDay -= temp;
				if (nextMonth > 12) {
					nextMonth = 1;
					nextYear++;
				} else
					nextMonth++;
				temp = getNumberDaysInMonth(nextMonth, nextYear);
			}
			nextDate = numberDay + this.day;
		} else if (numberDay == numberDaysInMonth) {
			nextDate += numberDaysInMonth - numberDay;
			nextMonth++;
		} else {
			nextDate += numberDay;
            if(nextDate > numberDaysInMonth){
                nextDate -= numberDaysInMonth;
                nextMonth++;
            }
		}
		return new Date(nextDate, nextMonth, nextYear);
	}
	public static Date addStatic(Date first, int numberDay) {
		int numberDaysInMonth = getNumberDaysInMonthStatic(first.getMonth(), first.getYear());
		int nextDate = first.getDay();
		int nextMonth = first.getMonth();
		int nextYear = first.getYear();
		if (numberDay > numberDaysInMonth && numberDaysInMonth > 0) {

			int temp = numberDaysInMonth;
			while (numberDay > temp) {
				numberDay -= temp;
				if (nextMonth > 12) {
					nextMonth = 1;
					nextYear++;
				} else
					nextMonth++;
				temp = getNumberDaysInMonthStatic(nextMonth, nextYear);
			}
			nextDate = numberDay + first.getDay();
		} else if (numberDay == numberDaysInMonth) {
			nextDate += numberDaysInMonth - numberDay;
			nextMonth++;
		} else {
			nextDate += numberDay;
            if(nextDate > numberDaysInMonth){
                nextDate -= numberDaysInMonth;
                nextMonth++;
            }
		}
		return new Date(nextDate, nextMonth, nextYear);
	}

	public int getNumberDaysInMonth(int month, int year) {
		if (month < 1 || month > 12)
			return 0;
		switch (month) {
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if (isLeapYear(year))
				return Date.number_days_in_february_leap;
			else
				return Date.number_days_in_february;
		default:
			return 31;
		}
	}
	
	public static int getNumberDaysInMonthStatic(int month, int year) {
		if (month < 1 || month > 12)
			return 0;
		switch (month) {
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if (isLeapYearStatic(year))
				return Date.number_days_in_february_leap;
			else
				return Date.number_days_in_february;
		default:
			return 31;
		}
	}

	public boolean isLeapYear(int year) {
		// if the year is divided by 4
		if (year % 4 == 0) {
			// if the year is century
			if (year % 100 == 0) {
				// if year is divided by 400
				// then it is a leap year
				if (year % 400 == 0)
					return true;
				else
					return false;
			}
			// if the year is not century
			else
				return true;
		}
		else
			return false;
	}
	public static boolean isLeapYearStatic(int year) {
		// if the year is divided by 4
		if (year % 4 == 0) {
			// if the year is century
			if (year % 100 == 0) {
				// if year is divided by 400
				// then it is a leap year
				if (year % 400 == 0)
					return true;
				else
					return false;
			}
			// if the year is not century
			else
				return true;
		}
		else
			return false;
	}
	
	public int compareTo(Date date2) {
		if(this.year > date2.getYear()) {
			return 1;
		}else if(this.year < date2.getYear()) {
			return -1;
		}else {
			if(this.month > date2.getMonth()) {
				return 1;
			}else if(this.month < date2.getMonth()){
				return -1;
			}else {
				if(this.day > date2.getDay()) {
					return 1;
				}else if(this.day < date2.getDay()) {
					return -1;
				}else {
					return 0;
				}
			}
		}
	}
	
	public static int NumberDaysBetween(Date start, Date end) {
		int number_day = 0;
        if(start.getYear() == end.getYear()){
            number_day = NumberDaysInSameYearFrom(start, end);
        }else{
            for (int i = start.getYear(); i < end.getYear(); i ++){
                Date startOfYear = new Date(i, 1, 1);
                Date endOfYear = new Date(i, 12, 31);
                if(i == start.getYear()){
                    number_day = NumberDaysInSameYearFrom(start, endOfYear);
                }else{
                    number_day += NumberDaysInSameYearFrom(startOfYear, endOfYear);
                }

                if(i == end.getYear() - 1){
                    startOfYear.setYear(end.getYear());
                    number_day += NumberDaysInSameYearFrom(startOfYear, end);
                }
                number_day += 1;
            }
        }
		return number_day;
    }
    public static int NumberDaysInSameYearFrom(Date start, Date end){
        int number_day;
        number_day = getNumberDaysInMonthStatic(start.getMonth(), start.getYear()) - start.getDay();
        number_day += end.getDay();
        for(int i = start.getMonth() + 1; i < end.getMonth(); i ++){
            number_day += getNumberDaysInMonthStatic(i, start.getYear());
        }
        return number_day;
    }

	@Override
	public String toString() {
		return this.year + "-" + this.month + "-" + this.day;
	}
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject element = new JSONObject();
		element.put("year", this.year);
		element.put("month", this.month);
		element.put("day", this.day);
		return element;
	}
	
}
