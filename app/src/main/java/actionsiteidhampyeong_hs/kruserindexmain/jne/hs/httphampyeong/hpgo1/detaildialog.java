package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class detaildialog extends DialogFragment {
    String mname, msubject, mtask, mgrade;

    TextView text_viewtask, text_viewsubject, text_viewgrade;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View veiw = inflater.inflate(R.layout.dialog_people, null);
        //변수
        text_viewgrade = veiw.findViewById(R.id.grade);
        text_viewsubject = veiw.findViewById(R.id.sujectclass);
        text_viewtask = veiw.findViewById(R.id.task);

        //불러온 변수 지정
        Bundle mArgs = getArguments();
        mname = mArgs.getString(peopleActivity.EXTRA_NAME);
        mtask = mArgs.getString(peopleActivity.EXTRA_PEOPTAST);
        mgrade = mArgs.getString(peopleActivity.EXTRA_GRADE);
        msubject = mArgs.getString(peopleActivity.EXTRA_SUBJECT);

        text_viewtask.setText(mtask);
        text_viewsubject.setText(msubject);
        text_viewgrade.setText(mgrade);
        builder.setView(veiw)
                .setTitle(mname)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        detaildialog.this.dismiss();
                    }

                });
        builder.setCancelable(false);
        return builder.create();
    }

}
