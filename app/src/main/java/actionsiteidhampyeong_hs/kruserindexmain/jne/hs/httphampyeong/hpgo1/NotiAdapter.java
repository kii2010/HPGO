package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.NotiViewHolder> {

    private Context mContext;
    private ArrayList<Notiltem> mNotiList;

    public NotiAdapter(Context context, ArrayList<Notiltem> notiList) {
        mContext = context;
        mNotiList = notiList;
    }
}
