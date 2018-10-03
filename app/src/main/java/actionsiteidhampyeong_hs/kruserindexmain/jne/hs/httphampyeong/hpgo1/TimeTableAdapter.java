package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.TimetableViewHolder> {
    private Context mContext;
    private ArrayList<TimeTableItem> mTimetableList;

    public TimeTableAdapter(Context context, ArrayList<TimeTableItem> TimetableList) {
        mContext = context;
        mTimetableList = TimetableList;
    }

    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.timetable_item, parent, false);
        return new TimetableViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder holder, int position) {
        TimeTableItem currentItem = mTimetableList.get(position);

        String timetableNum = currentItem.getTimetableNum();
        String timetableSub = currentItem.getTimetableSub();

        holder.mNum.setText(timetableNum);
        holder.mSub.setText(timetableSub);
    }

    @Override
    public int getItemCount() {
        return mTimetableList.size();
    }

    public class TimetableViewHolder extends RecyclerView.ViewHolder {
        public TextView mNum, mSub;

        public TimetableViewHolder(View itemView) {
            super(itemView);
            mNum = itemView.findViewById(R.id.timetable_num);
            mSub= itemView.findViewById(R.id.timetable_sub);
        }
    }
}
