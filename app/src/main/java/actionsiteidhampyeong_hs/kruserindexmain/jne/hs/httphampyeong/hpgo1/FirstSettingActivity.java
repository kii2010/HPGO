package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class FirstSettingActivity extends AppCompatActivity {

    public SharedPreferences prefs;
    Spinner grade, class_num, level;

    private void greadSpinners() {
        ArrayAdapter<CharSequence> fAdapter;
        fAdapter = ArrayAdapter.createFromResource(this, R.array.grade, android.R.layout.simple_spinner_item);
        fAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        grade.setAdapter(fAdapter);
    }

    private void levelSpinners(int intNum) {
        ArrayAdapter<CharSequence> fAdapter;
        fAdapter = ArrayAdapter.createFromResource(this, intNum, android.R.layout.simple_spinner_item);
        fAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level.setAdapter(fAdapter);
    }

    private AdapterView.OnItemSelectedListener spinSelectedlistener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            switch (position) {
                case (0):
                    levelSpinners(R.array.level);
                    break;
                case (1):
                    levelSpinners(R.array.level2);
                    break;
                case (2):
                    levelSpinners(R.array.level3);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_setting);

        Button mButton = findViewById(R.id.finish_btn);
        TextView mTextView = findViewById(R.id.just_start);
        grade = findViewById(R.id.grade_dynamic);
        greadSpinners();
        class_num = findViewById(R.id.class_dynamic);
        level = findViewById(R.id.level_dynamic);
        levelSpinners(R.array.grade);

        grade.setOnItemSelectedListener(spinSelectedlistener);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isFirst", true);
                editor.putString("grade", grade.getSelectedItem().toString());
                editor.putString("class_num", class_num.getSelectedItem().toString());
                editor.putString("level", level.getSelectedItem().toString());
                editor.apply();
                Intent intent = new Intent(FirstSettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isFirst", true);
                editor.apply();
                Intent intent = new Intent(FirstSettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}