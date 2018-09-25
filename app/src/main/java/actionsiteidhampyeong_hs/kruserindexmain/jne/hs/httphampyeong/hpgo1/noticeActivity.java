package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    @NoneNull
    @Override
    public NotiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.noti_item, parent, false);
        return new NotiViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotiViewHolder holder, int position) {
        NotiItem currentItem = mNotiList.get(position);

        String notiTitle = currentItem.getNotiTitle();
        String notiDate = currentItem.getNotiDate();
        String notiWriter = currentItem.getWriter();

        holder.mNotiTitle.setText(notiTitle);
        holder.mNotiDate.setText(notiDate);
        holder.mNotiWriter.setText(notiWriter);
    }

    @Override
    public int getItemCount() {
        return mNotiList.size();
    }

    public class NotiViewHolder extends RecyclerView.ViewHolder {
        public TextView mNotiTitle, mNotiDate, mNotiWriter;

        public NotiViewHolder(View itemView) {
            super(itemView);
            mNotiTitle = itemView.findViewById(R.id.item_title);
            mNotiDate = itemView.findViewById(R.id.item_date);
            mNotiWriter = itemView.findViewById(R.id.item_writer);
        }
    }
}


