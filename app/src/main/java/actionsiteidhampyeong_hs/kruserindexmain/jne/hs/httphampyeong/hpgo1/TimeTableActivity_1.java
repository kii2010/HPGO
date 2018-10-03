package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class TimeTableActivity_1 extends AppCompatActivity {

    public SharedPreferences prefs;
    String grade, class_num, level;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    FragmentManager fm = getSupportFragmentManager();

    FirstSettingDialog firstSettingDialog = new FirstSettingDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        grade = prefs.getString("grade", "null");
        class_num = prefs.getString("class_num", "null");
        level = prefs.getString("level", "null");
        GradeAdapter.setClass_num(class_num);
        GradeAdapter.setGrade(grade);
        GradeAdapter.setLevel(level);
        fm = getSupportFragmentManager();

        toolbar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.container);
        tabLayout = findViewById(R.id.tabs);

        toolbar.setTitle("시간표");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_time_table, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                firstSettingDialog.show(getSupportFragmentManager(), "tag");
                return true;
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ValidFragment")
    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        private RecyclerView mRecyclerView;
        private ArrayList<TimeTableItem> mTimetableList = new ArrayList<>();
        private TimeTableAdapter mTimetableAdaper;
        int class_number, num_level;
        String grade, class_num, level;
        Context context;
        GradeAdapter gradeAdapter = new GradeAdapter();

        ProgressDialog asyncDialog;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_time_table, container, false);
            context = rootView.getContext();

            mRecyclerView = rootView.findViewById(R.id.timetable2_recycler);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            grade = gradeAdapter.getGrade();
            class_num = gradeAdapter.getClass_num();
            level = gradeAdapter.getLevel();

            asyncDialog = new ProgressDialog(context);
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("시간표를 불러오는 중입니다.");
            asyncDialog.show();
            asyncDialog.setCancelable(false);
            if (mTimetableList.size() == 0) {
                (new TimeTable()).execute(getArguments().getInt(ARG_SECTION_NUMBER));
            }
            return rootView;
        }

        private class TimeTable extends AsyncTask<Integer, Void, Void> {

            @Override
            protected Void doInBackground(Integer... WoD) {
                URLs mURLs = new URLs();

                switch (grade) {
                    case "1학년":
                        grade = mURLs.URL1grade;
                        break;
                    case "2학년":
                        grade = mURLs.URL2grade;
                        break;
                    case "3학년":
                        grade = mURLs.URL3grade;
                        break;
                }

                Log.d("grade", grade);

                switch (class_num) {
                    case "1반":
                        class_number = 0;
                        break;
                    case "2반":
                        class_number = 1;
                        break;
                    case "3반":
                        class_number = 2;
                        break;
                    case "4반":
                        class_number = 3;
                        break;
                }

                switch (level) {
                    case "A반":
                        num_level = 4;
                        break;
                    case "B반":
                        num_level = 5;
                        break;
                    case "C반":
                        num_level = 6;
                        break;
                    case "D반":
                        num_level = 7;
                        break;
                    case "E반":
                        num_level = 8;
                        break;
                    case "문과 A반":
                        num_level = 4;
                        break;
                    case "문과 B반":
                        num_level = 5;
                        break;
                    case "문과 C반":
                        num_level = 6;
                        break;
                    case "이과 A반":
                        num_level = 7;
                        break;
                    case "이과 B반":
                        num_level = 8;
                        break;
                    case "이과 C반":
                        num_level = 9;
                        break;
                }

                try {
                    if (grade == mURLs.URL3grade) {
                        Document noti_doc = Jsoup.connect(grade).get();
                        Element table = noti_doc.select("table tbody").get(class_number);
                        Elements rows = table.select("tr");

                        for (int i = 1; i < rows.size(); i++) {
                            table = noti_doc.select("table tbody").get(class_number);
                            rows = table.select("tr");
                            Element row = rows.get(i);
                            Element num = row.select("td").get(0);
                            Element sub = row.select("td").get(WoD[0]);

                            String number = num.text();
                            String subject = sub.text();

                            if (subject != "") {
                                mTimetableList.add(new TimeTableItem(number, subject));
                            }
                        }
                    } else {
                        Document noti_doc = Jsoup.connect(grade).get();
                        Element table = noti_doc.select("table tbody").get(class_number);
                        Elements rows = table.select("tr");

                        for (int i = 1; i < rows.size(); i++) {
                            table = noti_doc.select("table tbody").get(class_number);
                            rows = table.select("tr");
                            Element row = rows.get(i);
                            Element num = row.select("td").get(0);
                            Element sub = row.select("td").get(WoD[0]);

                            String number = num.text();
                            String subject = sub.text();

                            if (subject == "") {
                                table = noti_doc.select("table tbody").get(num_level);
                                rows = table.select("tr");
                                row = rows.get(i);
                                sub = row.select("td").get(WoD[0]);

                                subject = sub.text();
                                if (subject != "") {
                                    mTimetableList.add(new TimeTableItem(number, subject));
                                }
                            } else {
                                mTimetableList.add(new TimeTableItem(number, subject));
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                mTimetableAdaper = new TimeTableAdapter(context, mTimetableList);
                mRecyclerView.setAdapter(mTimetableAdaper);
                asyncDialog.dismiss();
            }
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
