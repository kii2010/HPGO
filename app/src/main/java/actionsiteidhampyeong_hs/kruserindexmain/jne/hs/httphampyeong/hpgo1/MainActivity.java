package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {

    LinearLayout school_text;
    CardView main_cv_meal, main_cv_today_meal, main_cv_notice, main_cv_people, main_cv_time_table, main_cv_info;
    TextView main_tv_today_meal;

    int dayOfWeek, weekOfMonth;
    String full_meal = "";
    TodayAdapter todayAdapter = new TodayAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //몇번째 주, 요일 불러오기
        dayOfWeek = todayAdapter.getDayOfWeek();
        weekOfMonth = todayAdapter.getWeekOfMonth();

        //변수 지정
        main_cv_today_meal = findViewById(R.id.main_cv_today_meal);
        main_cv_people = findViewById(R.id.main_cv_people);
        main_cv_time_table = findViewById(R.id.main_cv_time_table);
        main_cv_info = findViewById(R.id.main_cv_info);
        main_cv_notice = findViewById(R.id.main_cv_notice);
        school_text = findViewById(R.id.main_top_text);
        main_cv_meal = findViewById(R.id.main_cv_meal);
        main_tv_today_meal = findViewById(R.id.main_tv_today_meal);

        //급식 정보 불러오기
        //(new TodayMeal()).execute();

        //홈페이지 이동 구현
        school_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri go_to_schoolpage = Uri.parse("http://hampyeong.hs.jne.kr/");
                Intent go_schoolpage = new Intent(Intent.ACTION_VIEW, go_to_schoolpage);
                startActivity(go_schoolpage);
                Toast.makeText(getApplicationContext(), "홈페이지로 이동중", Toast.LENGTH_SHORT).show();
            }
        });

        //오늘의 급식 위젯 클릭시 새로고침
        main_cv_today_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_tv_today_meal.setText(String.valueOf(weekOfMonth));
                (new TodayMeal()).execute();
            }
        });
    }

    //급식 불러오기 함수
    private class TodayMeal extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document document = Jsoup.connect("https://stu.jne.go.kr/sts_sci_md00_001.do?schulCode=Q100000323&schulCrseScCode=4&schulKndScCode=04").get();
                Element table = document.select("table").get(0);
                Element row = table.select("tr").get(weekOfMonth);
                Element col = row.select("td").get(dayOfWeek);
                Element text = col.select("div").get(0);

                full_meal = text.html();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("full_meal", full_meal);
        }
    } //오늘의 급식 불러오기

}
