package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout school_text;
    CardView main_cv_meal, main_cv_today_meal, main_cv_notice, main_cv_people, main_cv_time_table, main_cv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객체연결 by TW --------------------------
        main_cv_today_meal = findViewById(R.id.main_cv_today_meal);
        main_cv_people = findViewById(R.id.main_cv_people);
        main_cv_time_table = findViewById(R.id.main_cv_time_table);
        main_cv_info = findViewById(R.id.main_cv_info);
        main_cv_notice = findViewById(R.id.main_cv_notice)
        //버튼 검색
        school_text = findViewById(R.id.main_top_text);
        main_cv_meal = findViewById(R.id.main_cv_meal);
        //홈페이지 이동 구현
        school_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri go_to_schoolpage = Uri.parse("http://hampyeong.hs.jne.kr/user/hampyeong_hs/index.action");
                Intent go_schoolpage = new Intent(Intent.ACTION_VIEW, go_to_schoolpage);
                startActivity(go_schoolpage);
                Toast.makeText(getApplicationContext(), "홈페이지로 이동중", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
