package com.geekbrains.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] numberId = new int[]{
                R.id.zero,
                R.id.one,
                R.id.two,
                R.id.three,
                R.id.four,
                R.id.five,
                R.id.six,
                R.id.seven,
                R.id.eight,
                R.id.nine
        };

        int[] actionId = new int[]{
                R.id.plus,
                R.id.minus,
                R.id.div,
                R.id.mult,
                R.id.result,
                R.id.root,
                R.id.percent,
                R.id.reset,
                R.id.dot,
                R.id.plus_minus,
        };

        TextView text;
        text = findViewById(R.id.text);

        Button button;

    }


}
