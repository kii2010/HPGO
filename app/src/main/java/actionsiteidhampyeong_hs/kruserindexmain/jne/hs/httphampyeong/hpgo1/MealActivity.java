package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MealActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_meal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        TextView textView;

        int dayOfWeek, weekOfMonth, hour;
        TodayAdapter todayAdapter = new TodayAdapter();
        String full_meal;
        private static final String ARG_SECTION_NUMBER = "section_number";

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

            dayOfWeek = todayAdapter.getDayOfWeek();
            weekOfMonth = todayAdapter.getWeekOfMonth();
            hour = todayAdapter.getHour();
            View rootView = inflater.inflate(R.layout.fragment_meal, container, false);
            textView = rootView.findViewById(R.id.section_label);
            (new TodayMeal()).execute(getArguments().getInt(ARG_SECTION_NUMBER));
            return rootView;
        }

        private class TodayMeal extends AsyncTask<Integer, Void, Integer> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Integer... voids) {
                try {
                    //급식정보를 불러올 홈페이지 접속
                    Document document = Jsoup.connect("https://stu.jne.go.kr/sts_sci_md01_001.do?schulCode=Q100000323&schulCrseScCode=4&schulKndScCode=04&schMmealScCode=" + voids[0]).validateTLSCertificates(false).get();
                    //html에서 table 속성 선택
                    Element table = document.select("table tbody").get(0);
                    //table 속성 중 tr 속성 선택(몇 번째 주인지에 따라 달라짐)
                    Element row = table.select("tr").get(1);
                    //tr 속성 중 td 속성 선택(요일에 따라 달라짐)
                    Element text = row.select("td").get(dayOfWeek);

                    //선택된 div 속성을 html 형식으로 불러오기
                    full_meal = text.html();
                    //html에서 줄바꿈을 의미하는 <br>을 줄바꿈으로 바꾸기
                    full_meal = full_meal.replaceAll("<br>", "\n");
                    if (full_meal.equals("")) {
                        full_meal = "급식 정보가 없습니다.";
                    }
                    return voids[0];
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return -1;
            }

            @Override
            protected void onPostExecute(Integer result) {
                if (result == 1) {
                    textView.setText("[조식]\n" + full_meal);
                } else if (result == 2) {
                    textView.setText("[중식]\n" + full_meal);
                } else if (result == 3) {
                    textView.setText("[석식]\n" + full_meal);
                } else if (result == -1) {
                    textView.setText("불러오지 못했습니다.");
                }


            }
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
