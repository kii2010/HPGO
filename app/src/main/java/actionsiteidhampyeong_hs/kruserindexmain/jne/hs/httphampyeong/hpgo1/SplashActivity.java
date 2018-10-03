package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class SplashActivity extends AppCompatActivity {

    private long mNow = System.currentTimeMillis();
    private Date today = new Date(mNow);

    public SharedPreferences prefs;



    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        SimpleDateFormat WeekOfMonth = new SimpleDateFormat("W");
        SimpleDateFormat Hour = new SimpleDateFormat("H");

        TimeAdapter.setDayOfWeek(cal.get(Calendar.DAY_OF_WEEK) - 1);
        TimeAdapter.setWeekOfMonth(Integer.parseInt(WeekOfMonth.format(today)));
        TimeAdapter.setHour(Integer.parseInt(Hour.format(today)));

        TimeAdapter.setYear(calendar.get(Calendar.YEAR));
        TimeAdapter.setMonth(calendar.get(Calendar.MONTH));
        TimeAdapter.setDay(calendar.get(Calendar.DATE));

        Log.d("ymd", String.valueOf(calendar.get(Calendar.DATE)));


        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean first = prefs.getBoolean("isFirst", false);
        if (!first) {
            Intent intent = new Intent(this, FirstSettingActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}