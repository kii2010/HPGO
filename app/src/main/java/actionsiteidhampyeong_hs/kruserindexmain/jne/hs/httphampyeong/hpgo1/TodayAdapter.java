package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TodayAdapter {
    private long mNow = System.currentTimeMillis();
    private Date today = new Date(mNow);

    public int getDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int mDayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return mDayOfWeek;
    }

    public int getWeekOfMonth() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("W");
        int mWeekOfMonth = Integer.parseInt(simpleDateFormat.format(today));
        return mWeekOfMonth;
    }
    public int getHour() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H");
        int mHour = Integer.parseInt(simpleDateFormat.format(today));
        return mHour;
    }
}

//시간 불러오는 함수