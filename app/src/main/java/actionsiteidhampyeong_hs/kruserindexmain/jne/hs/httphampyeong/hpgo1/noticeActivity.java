package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class noticeActivity extends AppCompatActivity {

    Toolbar noti_toolbar;
    RecyclerView noti_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        noti_toolbar = findViewById(R.id.noti_toolbar);
        noti_recycler = findViewById(R.id.noti_recycler);
        setSupportActionBar(noti_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        noti_recycler.setLayoutManager(new LinearLayoutManager(this));

    }
}


