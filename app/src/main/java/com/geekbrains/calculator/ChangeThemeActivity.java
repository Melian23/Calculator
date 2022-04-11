package com.geekbrains.calculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeThemeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PREF_NAME = "key";
    private static final String PREF_THEME = "key_theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(getAppTheme());

        setContentView(R.layout.activity_change_theme);

        ((RadioButton) findViewById(R.id.radioButtonMaterialDefault)).setOnClickListener(this);
        ((RadioButton) findViewById(R.id.radioButtonMaterialBlue)).setOnClickListener(this);
        ((RadioButton) findViewById(R.id.radioButtonMaterialGreen)).setOnClickListener(this);
        ((RadioButton) findViewById(R.id.radioButtonMaterialRed)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radioButtonMaterialBlue: {
                setAppTheme(R.style.myThemeBlue);
                break;
            }
            case R.id.radioButtonMaterialGreen: {
                setAppTheme(R.style.myThemeGreen);
                break;
            }
            case R.id.radioButtonMaterialRed: {
                setAppTheme(R.style.myThemeRed);
                break;
            }
            case R.id.radioButtonMaterialDefault: {
                setAppTheme(R.style.Theme_Calculator);
                break;
            }
        }
        recreate();
    }

    protected int getAppTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getInt(PREF_THEME, R.style.Theme_Calculator);
    }

    public void setAppTheme(int codeStyle) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_THEME, codeStyle);
        editor.apply();
    }
}

