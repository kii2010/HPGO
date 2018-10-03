package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

public class FirstSettingDialog extends DialogFragment {

    public SharedPreferences prefs;
    Spinner grade, class_num;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        prefs = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        @SuppressLint("InflateParams") final View view = inflater.inflate(R.layout.dialog_first_setting, null);
        grade = view.findViewById(R.id.grade_dialog);
        class_num = view.findViewById(R.id.class_dialog);
        builder.setView(view)
                .setTitle("학반을 선택하세요")
                .setPositiveButton("설정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("grade", grade.getSelectedItem().toString());
                        editor.putString("class_num", class_num.getSelectedItem().toString());
                        editor.apply();
                        FirstSettingDialog.this.dismiss();
                        getActivity().recreate();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirstSettingDialog.this.getDialog().cancel();
                    }
                });
        builder.setCancelable(false);
        return builder.create();
    }
}
