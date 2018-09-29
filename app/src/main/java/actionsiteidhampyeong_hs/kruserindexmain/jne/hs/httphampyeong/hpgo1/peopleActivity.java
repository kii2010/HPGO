package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class peopleActivity extends AppCompatActivity implements PeopAdapter.OnItemClickListner {
    public static final String EXTRA_NAME = "name", EXTRA_SUBJECT = "subject", EXTRA_PEOPTAST = "task", EXTRA_GRADE = "grade";
    private PeopAdapter mPeopAdaper;
    private ArrayList<PeopItem> mPeopList;
    Toolbar peop_toolbar;
    RecyclerView peop_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        peop_toolbar = findViewById(R.id.peop_toolbar);
        peop_recycler = findViewById(R.id.peop_recycler);
        peop_toolbar.setTitle("임원소개..");
        setSupportActionBar(peop_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        peop_recycler.setLayoutManager(new LinearLayoutManager(this));

        mPeopList = new ArrayList<>();

        Peopparse peopparse = new Peopparse();
        peopparse.execute();
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
        Bundle args = new Bundle();
        PeopItem clickedItem = mPeopList.get(position);

        args.putString(EXTRA_NAME, clickedItem.getPeopname());
        args.putString(EXTRA_GRADE, clickedItem.getPeopgrade());
        args.putString(EXTRA_SUBJECT, clickedItem.getPeopsubject());
        args.putString(EXTRA_PEOPTAST, clickedItem.getPeoptast());
    }

    private class Peopparse extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(peopleActivity.this);

        @Override
        //로딩창 구현
        protected void onPreExecute() {
            super.onPreExecute();
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("임원리스트를 불러오는 중입니다.");
            asyncDialog.setCancelable(false);
            // show dialog
            asyncDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document noti_doc = Jsoup.connect("http://hampyeong.hs.jne.kr/user/indexSub.action?codyMenuSeq=29948&siteId=hampyeong_hs&menuUIType=sub").get();
                Element table = noti_doc.select("table").get(1);
                Elements rows = table.select("tr");

                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Element name = row.select("td").get(0);
                    Element subject = row.select("td").get(1);
                    Element tast = row.select("td").get(2);
                    Element grade = row.select("td").get(3);  ///////////  <<  추가

                    String peopname = name.text();
                    String peopsubject = subject.text();
                    String peoptast = tast.text();
                    String peopgrade = grade.text();      /////////////// <   <   추가

                    mPeopList.add(new PeopItem(peopname, peopsubject, peoptast, peopgrade));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mPeopAdaper = new PeopAdapter(peopleActivity.this, mPeopList);
            peop_recycler.setAdapter(mPeopAdaper);
            asyncDialog.dismiss();
        }
    }
}


