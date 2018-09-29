package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class detaildialog extends DialogFragment {
    String mname, msubject, mtask, mgrade;

    TextView
            text_viewtask, text_viewsubject, text_viewgrade;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View veiw = inflater.inflate(R.layout.dialog_people, null);
          //변수
        text_viewgrade = veiw.findViewById(R.id.grade);
        text_viewsubject = veiw.findViewById(R.id.sujectclass);
        text_viewtask = veiw.findViewById(R.id.task);


    }

}
