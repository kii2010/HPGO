package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout school_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼 검색
        school_text = findViewById(R.id.main_top_text);

        //홈페이지 이동 구현
        school_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri go_to_schoolpage = Uri.parse("http://hampyeong.hs.jne.kr/user/hampyeong_hs/index.action");
                Intent go_schoolpage = new Intent(Intent.ACTION_VIEW, go_to_schoolpage);
                startActivity(go_schoolpage);
                Toast.makeText(getApplicationContext(),"홈페이지로 이동중", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
