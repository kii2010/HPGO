package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity implements NoticeAdapter.OnItemClickListner {
    private NoticeAdapter mNotiAdaper;
    private ArrayList<NoticeItem> mNotiList;
    Toolbar noti_toolbar;
    RecyclerView noti_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        noti_toolbar = findViewById(R.id.noti_toolbar);
        noti_recycler = findViewById(R.id.noti_recycler);
        noti_toolbar.setTitle("공지사항");
        setSupportActionBar(noti_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        noti_recycler.setLayoutManager(new LinearLayoutManager(this));

        mNotiList = new ArrayList<>();

        Notiparse notiparse = new Notiparse();
        notiparse.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {
        NoticeItem clickedItem = mNotiList.get(position);
        Uri detail_noti = Uri.parse("http://hampyeong.hs.jne.kr/user/" + clickedItem.getNoticeInfo());
        Intent intent = new Intent(Intent.ACTION_VIEW, detail_noti);
        startActivity(intent);
    }

    private class Notiparse extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(NoticeActivity.this);

        @Override
        //로딩창 구현
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("공지사항를 불러오는 중입니다.");
            asyncDialog.setCancelable(false);
            // show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document noti_doc = Jsoup.connect("http://hampyeong.hs.jne.kr/user/indexSub.action?framePath=unknownboard&siteId=hampyeong_hs&dum=dum&boardId=422382&page=1&command=list&status=").get();
                Element table = noti_doc.select("table tbody").get(0);
                Elements rows = table.select("tr");

                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Element title = row.select("td").get(1);
                    Element writer = row.select("td").get(2);
                    Element date = row.select("td").get(3);
                    // 스타트===
                    Element info = row.select("td").get(1);
                    String Url = info.select("a").attr("href");

                    String notiTitle = title.text();
                    String notiWriter = writer.text();
                    String notiDate = date.text();
                    String notiInfo = Url;

                    mNotiList.add(new NoticeItem(notiTitle, notiDate, notiWriter, notiInfo));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mNotiAdaper = new NoticeAdapter(NoticeActivity.this, mNotiList);
            noti_recycler.setAdapter(mNotiAdaper);
            mNotiAdaper.setOnItemClickListener(NoticeActivity.this);
            asyncDialog.dismiss();
        }
    }
}


