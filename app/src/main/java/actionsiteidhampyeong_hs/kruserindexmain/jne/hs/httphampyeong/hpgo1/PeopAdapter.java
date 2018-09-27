package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class PeopAdapter extends RecyclerView.Adapter<PeopAdapter.PeopViewHolder> {

    private Context mContext;
    private ArrayList<PeopItem> mPeopList;

    public PeopAdapter(Context context, ArrayList<PeopItem> peopList) {
        mContext = context;
        mPeopList = peopList;
    }

    @NonNull
    @Override
    public PeopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.peop_item, parent, false);
        return new PeopViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopViewHolder holder, int position) {
        PeopItem currentItem = mPeopList.get(position);

        String peopname = currentItem.getPeopname();
        String peopsubject = currentItem.getPeopsubject();
        String peoptast = currentItem.getPeoptast();
        String peopgrade = currentItem.getPeopgrade();

        holder.mPeopname.setText(peopname);
        holder.mPeopsubject.setText(peopsubject);
        holder.mPeoptast.setText(peoptast);
        holder.mPeopgrade.setText(peopgrade);
    }

    @Override
    public int getItemCount() {
        return mPeopList.size();
    }

    public class PeopViewHolder extends RecyclerView.ViewHolder {
        public TextView mPeopname, mPeopsubject, mPeoptast, mPeopgrade;

        public PeopViewHolder(View itemView) {
            super(itemView);
            mPeopname = itemView.findViewById(R.id.item_title1);
            mPeopsubject = itemView.findViewById(R.id.item_title2);

        }
    }
}
