package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

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

        //앱정보 넘어가기 구현
        main_cv_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);

            }
        });

        main_cv_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PeopleActivity.class);
                startActivity(intent);
            }
        });

        main_cv_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent);

            }
        });

        main_cv_time_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimetableActivity.class);
                startActivity(intent);

            }
        });

        main_cv_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MealActivity.class);
                startActivity(intent);

            }
        });

        //텍스트뷰가 스크롤이 가능하게 설정
        main_tv_today_meal.setMovementMethod(ScrollingMovementMethod.getInstance());

        //급식 정보 불러오기
        if ((new TodayAdapter()).getHour() < 14) {
            (new TodayMeal()).execute(2);
        } else {
            (new TodayMeal()).execute(3);
        }

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
                if ((new TodayAdapter()).getHour() < 14) {
                    (new TodayMeal()).execute(2);
                } else {
                    (new TodayMeal()).execute(3);
                }
            }
        });
    }

    //급식 불러오기 함수
    private class TodayMeal extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Integer... voids) {
            try {
                //급식정보를 불러올 홈페이지 접속
                Document document = Jsoup.connect("https://stu.jne.go.kr/sts_sci_md01_001.do?schulCode=Q100000323&schulCrseScCode=4&schulKndScCode=04&schMmealScCode=" + voids[0]).validateTLSCertificates(false).get();
                //html에서 table 속성 선택
                Element table = document.select("table tbody").get(0);
                //table 속성 중 tr 속성 선택(몇 번째 주인지에 따라 달라짐)
                Element row = table.select("tr").get(1);
                //tr 속성 중 td 속성 선택(요일에 따라 달라짐)
                Element text = row.select("td").get(dayOfWeek);

                //선택된 div 속성을 html 형식으로 불러오기
                full_meal = text.html();
                //html에서 줄바꿈을 의미하는 <br>을 줄바꿈으로 바꾸기
                full_meal = full_meal.replaceAll("<br>", "\n");
                return voids[0];
            } catch (IOException e) {
                e.printStackTrace();
            }
            return -1;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result == 2) {
                main_tv_today_meal.setText("중식\n" + full_meal);
            } else if (result == 3) {
                main_tv_today_meal.setText("석식\n" + full_meal);
            } else if (result == -1) {
                main_tv_today_meal.setText("불러오지 못했습니다.");
            }


        }
    }

}
