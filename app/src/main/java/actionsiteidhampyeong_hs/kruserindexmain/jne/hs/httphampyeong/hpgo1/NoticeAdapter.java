package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NotiViewHolder> {

    private Context mContext;
    private ArrayList<NoticeItem> mNotiList;
    private OnItemClickListner mListener;

    public interface OnItemClickListner {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListner listener) {
        mListener = listener;
    }


    public NoticeAdapter(Context context, ArrayList<NoticeItem> notiList) {
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
        NoticeItem currentItem = mNotiList.get(position);

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
