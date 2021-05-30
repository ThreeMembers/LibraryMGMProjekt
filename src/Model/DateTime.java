package Model;

public class DateTime extends Date{
    private int hour;
    private int minute;
    private int second;

    public DateTime(int year, int month, int day, int hour, int minute, int second) {
        super(year, month, day);
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public DateTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public DateTime() {
        super();
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    public DateTime(String datetime) {
        String[] splits = datetime.split("T");
        String[] date = splits[0].split("-");
        String[] time = splits[1].split(":");

        this.year = Integer.parseInt(date[0]);
        this.month = Integer.parseInt(date[1]);
        this.day = Integer.parseInt(date[2]);

        this.hour = Integer.parseInt(time[0]);
        this.minute = Integer.parseInt(time[1]);
        this.second = Integer.parseInt(time[2]);
    }

    public static DateTime parse(String datetime){
        DateTime dateTime = new DateTime();

        String[] splits = datetime.split("T");
        String[] date = splits[0].split("-");
        String[] time = splits[1].split(":");

        dateTime.setYear(Integer.parseInt(date[0]));
        dateTime.setMonth(Integer.parseInt(date[1]));
        dateTime.setDay(Integer.parseInt(date[2]));

        dateTime.setHour(Integer.parseInt(time[0]));
        dateTime.setMinute(Integer.parseInt(time[1]));
        dateTime.setSecond(Integer.parseInt(time[2]));
        return dateTime;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
