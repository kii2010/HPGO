package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼 검색
        linearLayout = findViewById(R.id.main_top_text);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gotoschoolpage = Uri.parse("http://hampyeong.hs.jne.kr/user/hampyeong_hs/index.action");
                Intent goschoolpage = new Intent(Intent.ACTION_VIEW, gotoschoolpage);
                startActivity(goschoolpage);
            }
        });
    }
}
