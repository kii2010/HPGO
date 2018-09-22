package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.support.constraint.Placeholder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class mealActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public static class PlaceholderFragment extends Fragment {
        TextView textView;

        int dayOfWeek, weekOfMonth, hour;
        TodayAdapter todayAdapter = new TodayAdapter();
        String today_meal;
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;

            @Override
            public View onCreateView (LayoutInflater inflater, ViewGroup container,
                    Bundle savedInstanceState){

                dayOfWeek = todayAdapter.getDayOfWeek();
                weekOfMonth = todayAdapter.getWeekOfMonth();
                hour = todayAdapter.getHour();
                View rootView = inflater.inflate(R.layout.fragment_meal, container, false);
                textView = rootView.findViewById(R.id.section_label);
                return rootView;
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

