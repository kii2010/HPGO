package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.telephony.SmsManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

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

        //텍스트뷰가 스크롤이 가능하게 설정
        main_tv_today_meal.setMovementMethod(ScrollingMovementMethod.getInstance());

        //급식 정보 불러오기
        (new TodayMeal()).execute();

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
                //급식정보를 불러올 홈페이지 접속
                Document document = Jsoup.connect("http://stu.jne.go.kr/sts_sci_md00_001.do?schulCode=Q100000323&schulCrseScCode=4&schulKndScCode=04").validateTLSCertificates(false).get();
                //html에서 table 속성 선택
                Element table = document.select("table").get(0);
                //table 속성 중 tr 속성 선택(몇 번째 주인지에 따라 달라짐)
                Element row = table.select("tr").get(weekOfMonth);
                //tr 속성 중 td 속성 선택(요일에 따라 달라짐)
                Element col = row.select("td").get(dayOfWeek);
                //td 속성 중 div 속성 선택
                Element text = col.select("div").get(0);

                //선택된 div 속성을 html 형식으로 불러오기
                full_meal = text.html();
                //html에서 줄바꿈을 의미하는 <br>을 줄바꿈으로 바꾸기
                full_meal = full_meal.replaceAll("<br>", "");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("full_meal", full_meal);
            //텍스트뷰에 적용
            main_tv_today_meal.setText(full_meal);
        }
    } //오늘의 급식 불러오기
    package ghj.com.sendsms;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {

        SmsManager mSMSManager;
        EditText editBody;

// sms 수신 , 발신
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mSMSManager = SmsManager.getDefault();
            editBody = (EditText)findViewById(R.id.editBody);

            Button btnSend = (Button)findViewById(R.id.btnSend);
            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendSms();
                }
            });
        }


        public void sendSms(){
            //메시지
            String body = editBody.getText().toString();
            //160자 이하의 메시지 리스트로 만든다
            ArrayList<String> bodyList = mSMSManager.divideMessage(body);

            //송신 인텐트
            PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT"), 0);
            //수신 인텐트
            PendingIntent recvPI = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED"), 0);

            registerReceiver(mSentReceiver, new IntentFilter("SMS_SENT"));
            registerReceiver(mRecvReceiver, new IntentFilter("SMS_DELIVERED"));

            //여러개의 SMS 메시지를 전송
            if(bodyList.size() > 1){
                ArrayList<PendingIntent> sentPIList = new ArrayList<>();
                ArrayList<PendingIntent> recvPIList = new ArrayList<>();
                for(int i=0; i<bodyList.size(); i++){
                    sentPIList.add(sentPI);
                    recvPIList.add(recvPI);
                }
                mSMSManager.sendMultipartTextMessage("010-3048-0231", null, bodyList, sentPIList, recvPIList);
            }
            //1개의 SMS 메시지를 전송
            else{
                mSMSManager.sendTextMessage("010-3048-0231", null, body, sentPI, recvPI);
            }
        }

        BroadcastReceiver mSentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case RESULT_OK:
                        Toast.makeText(MainActivity.this, "SMS Send", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(MainActivity.this, "ERROR_GENERIC_FAILURE", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(MainActivity.this, "ERROR_NO_SERVICE", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(MainActivity.this, "ERROR_NULL_PDU", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(MainActivity.this, "ERROR_RADIO_OFF", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        BroadcastReceiver mRecvReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }

            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case RESULT_OK:
                        Toast.makeText(MainActivity.this, "SMS Delivered", Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_CANCELED:
                        Toast.makeText(MainActivity.this, "SMS Delivered Fail", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

}

}
