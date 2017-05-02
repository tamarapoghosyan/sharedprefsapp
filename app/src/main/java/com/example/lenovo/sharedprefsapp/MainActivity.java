package com.example.lenovo.sharedprefsapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String PROGRESS = "progress";
    private static final String MAX_PROGRESS_PROGRESSBAR = "maxProgressProgressBar";
    private static final String MAX_PROGRESS_SEEKBAR = "maxProgressSeekBar";

    private static final String PREFERENCES_NAME = "preferences1";
    Context context;
    SharedPreferences preferences;

    EditText progressMaxEditText, seekbarMaxEditText, progressEditText, seekbarEditText;
    ProgressBar progressBar;
    SeekBar seekBar;
    CheckBox checkBoxEnableToggles;
    ToggleButton toggleButton2, toggleButton4, toggleButton6, toggleButton8;

    RadioButton radioButton2, radioButton4, radioButton8;
    Button radioClearButton;
    RadioGroup radiogroup;

    Switch aSwitch10, aSwitch20, aSwitch30;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = getApplicationContext();
        preferences = context.getSharedPreferences(PREFERENCES_NAME, context.MODE_PRIVATE);
        editor=preferences.edit();


        progressBar = (ProgressBar) findViewById(R.id.act_main_progressbar);
        seekBar = (SeekBar) findViewById(R.id.act_main_seekbar);
        progressMaxEditText = (EditText) findViewById(R.id.act_main_progress_max_edittext);
        progressEditText = (EditText) findViewById(R.id.act_main_progress_edittext);
        seekbarMaxEditText = (EditText) findViewById(R.id.act_main_seekbar_max_edittext);
        seekbarEditText = (EditText) findViewById(R.id.act_main_seekbar_edittext);
        checkBoxEnableToggles = (CheckBox) findViewById(R.id.act_main_checkbox_enable_toggles);
        toggleButton2 = (ToggleButton) findViewById(R.id.act_main_toggleb_2X);
        toggleButton4 = (ToggleButton) findViewById(R.id.act_main_toggleb_4X);
        toggleButton6 = (ToggleButton) findViewById(R.id.act_main_toggleb_6X);
        toggleButton8 = (ToggleButton) findViewById(R.id.act_main_toggleb_8X);
        radioButton2 = (RadioButton) findViewById(R.id.act_main_radiobutton_div_2);
        radioButton4 = (RadioButton) findViewById(R.id.act_main_radiobutton_div_4);
        radioButton8 = (RadioButton) findViewById(R.id.act_main_radiobutton_div_8);
        radioClearButton = (Button) findViewById(R.id.act_main_button_clear_radio);
        aSwitch10 = (Switch) findViewById(R.id.act_main_switch_add_10);
        aSwitch20 = (Switch) findViewById(R.id.act_main_switch_add_20);
        aSwitch30 = (Switch) findViewById(R.id.act_main_switch_add_30);

        progressMaxEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (progressEditText.getText().length() == 0) {
                    progressBar.setMax(1000);
                } else {
                    editor.putString(MAX_PROGRESS_PROGRESSBAR,progressMaxEditText.getText().toString());
                    editor.apply();
                    progressBar.setMax(Integer.parseInt(preferences.getString(MAX_PROGRESS_PROGRESSBAR,null)));
                }
            }
        });
        progressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (progressEditText.getText().length() == 0) {
                    progressBar.setProgress(0);
                } else {
                    editor.putString(PROGRESS,progressEditText.getText().toString());
                    editor.apply();
                    progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                   // seekbarEditText.setText(preferences.getString(PROGRESS,"0"));
                }
            }
        });

        seekbarMaxEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (seekbarMaxEditText.getText().length() == 0) {
                    progressBar.setMax(1000);

                } else {
                    editor.putString(MAX_PROGRESS_SEEKBAR,seekbarMaxEditText.getText().toString());
                    editor.apply();
                    seekBar.setMax(Integer.parseInt(preferences.getString(MAX_PROGRESS_SEEKBAR,null)));
                }
            }
        });

        seekbarEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (seekbarEditText.getText().length() == 0) {

                } else {
                    editor.putString(PROGRESS,seekbarEditText.getText().toString());
                    editor.apply();
                    seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    progressEditText.setText(preferences.getString(PROGRESS,null));
                }
            }
        });


        checkBoxEnableToggles.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxEnableToggles.isChecked()) {
                    toggleButton2.setClickable(true);
                    toggleButton4.setClickable(true);
                    toggleButton6.setClickable(true);
                    toggleButton8.setClickable(true);
                    if (toggleButton2.isChecked()) {
                        Integer prog = seekBar.getProgress() * 2;
                        editor.putString(PROGRESS,prog.toString());
                        editor.apply();
                        seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                        progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                        progressEditText.setText(preferences.getString(PROGRESS,null));
                        seekbarEditText.setText(preferences.getString(PROGRESS,null));
                    }

                    if (toggleButton4.isChecked()) {
                        Integer prog = seekBar.getProgress() * 4;
                        editor.putString(PROGRESS,prog.toString());
                        editor.apply();
                        seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                        progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                        progressEditText.setText(preferences.getString(PROGRESS,null));
                        seekbarEditText.setText(preferences.getString(PROGRESS,null));

                    }
                    if (toggleButton6.isChecked()) {
                        Integer prog = seekBar.getProgress() * 6;
                        editor.putString(PROGRESS,prog.toString());
                        editor.apply();
                        seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                        progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                        progressEditText.setText(preferences.getString(PROGRESS,null));
                        seekbarEditText.setText(preferences.getString(PROGRESS,null));
                    }
                    if (toggleButton8.isChecked()) {
                        Integer prog = seekBar.getProgress() * 8;
                        editor.putString(PROGRESS,prog.toString());
                        editor.apply();
                        seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                        progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                        progressEditText.setText(preferences.getString(PROGRESS,null));
                        seekbarEditText.setText(preferences.getString(PROGRESS,null));
                    }
                    toggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (toggleButton2.isChecked()) {
                                Integer prog = seekBar.getProgress() * 2;
                                editor.putString(PROGRESS,prog.toString());
                                editor.apply();
                                seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                                progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                                progressEditText.setText(preferences.getString(PROGRESS,null));
                                seekbarEditText.setText(preferences.getString(PROGRESS,null));
                            }
                        }
                    });
                    toggleButton4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (toggleButton4.isChecked()) {
                                Integer prog = seekBar.getProgress() * 4;
                                editor.putString(PROGRESS,prog.toString());
                                editor.apply();
                                seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                                progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                                progressEditText.setText(preferences.getString(PROGRESS,null));
                                seekbarEditText.setText(preferences.getString(PROGRESS,null));
                            }
                        }
                    });
                    toggleButton6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (toggleButton6.isChecked()) {
                                Integer prog = seekBar.getProgress() * 6;
                                editor.putString(PROGRESS,prog.toString());
                                editor.apply();
                                seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                                progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                                progressEditText.setText(preferences.getString(PROGRESS,null));
                                seekbarEditText.setText(preferences.getString(PROGRESS,null));
                            }
                        }
                    });
                    toggleButton8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (toggleButton8.isChecked()) {
                                Integer prog = seekBar.getProgress() * 8;
                                editor.putString(PROGRESS,prog.toString());
                                editor.apply();
                                seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                                progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                                progressEditText.setText(preferences.getString(PROGRESS,null));
                                seekbarEditText.setText(preferences.getString(PROGRESS,null));
                            }
                        }
                    });


                } else {
                    toggleButton2.setClickable(false);
                    toggleButton4.setClickable(false);
                    toggleButton6.setClickable(false);
                    toggleButton8.setClickable(false);
                }

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Integer a = seekBar.getProgress();
                editor.putString(PROGRESS,a.toString());
                editor.apply();
                progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                progressEditText.setText(preferences.getString(PROGRESS,null));
                seekbarEditText.setText(preferences.getString(PROGRESS,null));
            }
        });

        radiogroup= (RadioGroup) findViewById(R.id.act_main_radiogroup);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButton2.isChecked()){
                    Integer prog = (Integer.parseInt(preferences.getString(PROGRESS,null)))/2;
                    editor.putString(PROGRESS,prog.toString());
                    editor.apply();
                    seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    progressEditText.setText(preferences.getString(PROGRESS,null));
                    seekbarEditText.setText(preferences.getString(PROGRESS,null));
                }
                else if(radioButton4.isChecked()){
                    Integer prog = (Integer.parseInt(preferences.getString(PROGRESS,null)))/4;
                    editor.putString(PROGRESS,prog.toString());
                    editor.apply();
                    seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    progressEditText.setText(preferences.getString(PROGRESS,null));
                    seekbarEditText.setText(preferences.getString(PROGRESS,null));
                }
                else if(radioButton8.isChecked()){
                    Integer prog = (Integer.parseInt(preferences.getString(PROGRESS,null)))/8;
                    editor.putString(PROGRESS,prog.toString());
                    editor.apply();
                    seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    progressEditText.setText(preferences.getString(PROGRESS,null));
                    seekbarEditText.setText(preferences.getString(PROGRESS,null));
                }

            }
        });

        radioClearButton.setOnClickListener(this);

        if (aSwitch10.isChecked()){
            Integer sum=Integer.parseInt(preferences.getString(PROGRESS,null))+10;
            editor.putString(PROGRESS,sum.toString());
            editor.apply();
            progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
            seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
            progressEditText.setText(preferences.getString(PROGRESS,null));
            seekbarEditText.setText(preferences.getString(PROGRESS,null));
        }


        if (aSwitch20.isChecked()){
            Integer sum=Integer.parseInt(preferences.getString(PROGRESS,null))+20;
            editor.putString(PROGRESS,sum.toString());
            editor.apply();
            progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
            seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
            progressEditText.setText(preferences.getString(PROGRESS,null));
            seekbarEditText.setText(preferences.getString(PROGRESS,null));
        }

        if (aSwitch30.isChecked()){
            Integer sum=Integer.parseInt(preferences.getString(PROGRESS,null))+30;
            editor.putString(PROGRESS,sum.toString());
            editor.apply();
            progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
            seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
            progressEditText.setText(preferences.getString(PROGRESS,null));
            seekbarEditText.setText(preferences.getString(PROGRESS,null));
        }

        aSwitch10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    Integer sum=Integer.parseInt(preferences.getString(PROGRESS,null))+10;
                    editor.putString(PROGRESS,sum.toString());
                    editor.apply();
                    progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    progressEditText.setText(preferences.getString(PROGRESS,null));
                    seekbarEditText.setText(preferences.getString(PROGRESS,null));
                }
            }
        });
        aSwitch20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    Integer sum=Integer.parseInt(preferences.getString(PROGRESS,null))+20;
                    editor.putString(PROGRESS,sum.toString());
                    editor.apply();
                    progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    progressEditText.setText(preferences.getString(PROGRESS,null));
                    seekbarEditText.setText(preferences.getString(PROGRESS,null));
                }
            }
        });
        aSwitch30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    Integer sum=Integer.parseInt(preferences.getString(PROGRESS,null))+30;
                    editor.putString(PROGRESS,sum.toString());
                    editor.apply();
                    progressBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    seekBar.setProgress(Integer.parseInt(preferences.getString(PROGRESS,null)));
                    progressEditText.setText(preferences.getString(PROGRESS,null));
                    seekbarEditText.setText(preferences.getString(PROGRESS,null));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.act_main_button_clear_radio:{
                radioButton2.setChecked(false);
                radioButton4.setChecked(false);
                radioButton8.setChecked(false);
            }
        }
    }
}
