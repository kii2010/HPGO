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
    private OnItemClickListner mListener;

    public interface OnItemClickListner {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListner listener) {
        mListener = listener;
    }


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

        holder.mPeopname.setText(peopname);
        holder.mPeopsubject.setText(peopsubject);
    }

    @Override
    public int getItemCount() {
        return mPeopList.size();
    }

    // == 같다   != 다르다
    public class PeopViewHolder extends RecyclerView.ViewHolder {
        public TextView mPeopname, mPeopsubject;

        public PeopViewHolder(View itemView) {
            super(itemView);
            mPeopname = itemView.findViewById(R.id.item_title1);
            mPeopsubject = itemView.findViewById(R.id.item_title2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) ;
                        {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
