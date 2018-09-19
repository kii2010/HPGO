package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼 검색
        linearLayout = (Button);findViewById(R.id linearLayout);

                //버튼 클릭이벤트 감지자 등록
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//버튼의 이벤트가 감지되었을떄 호출되는 메서드
                //버튼이 클릭되었을떄 하고자 하는 작업을 이곳에서 한다.
                Toast.makeText( getApplicationContext(),"BTN1 !!", Toast.LENGTH_SHORT).show();
            }
        });
    }//oneCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText( getApplicationContext(),"Success !!", Toast.LENGTH_SHORT).show();

        };
    }
}
