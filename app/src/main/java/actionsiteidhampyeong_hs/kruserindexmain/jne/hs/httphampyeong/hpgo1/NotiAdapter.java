package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.NotiViewHolder> {

    private Context mContext;
    private ArrayList<NotiItem> mNotiList;

    public NotiAdapter(Context context, ArrayList<NotiItem> notiList) {
        mContext = context;
        mNotiList = notiList;
    }

    @NonNull
    @Override
    public NotiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.noti_item, parent, false);
        return new NotiViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotiViewHolder holder, int position) {
        NotiItem currentItem = mNotiList.get(position);

        String notiTitle = currentItem.getNoticeTitle();
        String notiDate = currentItem.getNoticeDate();
        String notiWriter = currentItem.getNoticeWriter();

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
