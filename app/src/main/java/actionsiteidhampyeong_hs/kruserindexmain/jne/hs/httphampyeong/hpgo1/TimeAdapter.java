package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

public class TimeAdapter {

    private static int DayOfWeek, WeekOfMonth, Hour, Month, Day, Year;

    public int getDayOfWeek() {
        return DayOfWeek;
    }

    public int getWeekOfMonth() {
        return WeekOfMonth;
    }

    public int getHour() {
        return Hour;
    }

    public int getYear() {
        return Year;
    }

    public int getMonth() {
        return Month;
    }

    public int getDay() {
        return Day;
    }

    public static void setDayOfWeek(int a) {
        DayOfWeek = a;
    }

    public static void setWeekOfMonth(int a) {
        WeekOfMonth = a;
    }

    public static void setHour(int a) {
        Hour = a;
    }

    public static void setMonth(int a) {
        Month = a;
    }

    public static void setDay(int a) {
        Day = a;
    }

    public static void setYear(int a) {
        Year = a;
    }
}