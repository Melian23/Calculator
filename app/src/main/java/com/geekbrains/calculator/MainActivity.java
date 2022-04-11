package com.geekbrains.calculator;

/***
 * TODO:
 * число показывалось без точки если оно целое
 * ланшафтная ориентация калькулятора с сохранением состояния
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {

    private static final String THEME_KEY = "THEME_KEY";
    private static final String THEME_NIGHT = "THEME_NIGHT";
    private static final String THEME_DAY = "THEME_DAY";
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* SharedPreferences sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);

        String theme = sharedPreferences.getString(THEME_KEY, THEME_DAY);  // по умолчанию для первого запуска светлая тема
// выбираем сохраненную тему ранее
        switch (theme) {
            case THEME_DAY:
                setTheme(R.style.Theme_Calculator);
                break;
            default:
                THEME_NIGHT:
                setTheme(R.style.Theme_Calculator_V2);
                break;
        }*/

        setContentView(R.layout.activity_main);

        Button theme_night;
        findViewById(R.id.theme_night);


        display = findViewById(R.id.text);
        display.setShowSoftInputOnFocus(false); // метод отключения всплывающей клавиатуры при нажатии на дисплей (false)

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //если на дисплей еще не вводили никаких чисел
                if (getString(R.string.text).equals(display.getText().toString())) {
                    display.setText(""); // то при нажании на него имеющийся там текст будет меняться на пустой дисплей
                }
            }
        });
/*
        // выбор светлой или темной темы:
        findViewById(R.id.theme_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // сохраняем выбранную тему в SharedPreferences по ключу
                sharedPreferences.edit()
                        .putString(THEME_KEY, THEME_DAY)
                        .apply();
                recreate();
            }
        });

        findViewById(R.id.theme_night).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreferences.edit()
                        .putString(THEME_KEY, THEME_NIGHT)
                        .apply();
                recreate();
            }
        });*/
    }

    // метод обновления текста на дисплее
    private void updateText(String addStr) {
        String oldStr = display.getText().toString(); // переменная в которую сохраняем имеющийся текст на дисплее
        int cursorPos = display.getSelectionStart();  // переменная в которую сохраняем текущее положение курсора
        String leftStr = oldStr.substring(0, cursorPos); // переменная в которую сохраним левую часть строки на дисплее от курсора
        String rightStr = oldStr.substring(cursorPos); // переменная в которую сохраним правую часть строки от курсора до конца
        int textLen = display.getText().length();

        if (textLen <= 9) {
            //если текст на дисплее из ресурса String равно тексту на дисплее, т.е. еще ничего не вводили
            if (getString(R.string.text).equals(display.getText().toString())) {
                display.setText(addStr); // обновляем дисплей и устанавливаем текст новой строки
            } else {
                // если текст уже имеется на дисплее (не с ресуров) то устанавливаем текст в виде сочетания трех строк:
                //левой строки, текущей строи (положение курсора) и правой строки
                display.setText(String.format("%s%s%s", leftStr, addStr, rightStr));
            }
            display.setSelection(cursorPos + 1); //фиксируем положение курсора на один больше, чтоб курсор был в конце числа
        }
    }

    public void zeroBTN(View view) {
        int cursorPos = display.getSelectionStart(); // получили текущее состояние курсора
        int textLen = display.getText().length();  //переменная в которую сохраним длину текста на дисплее

        // если курсор не находится в самом начале и длина введенного текста не равна нулю
        if (cursorPos != 0 && textLen != 0) {
            updateText("0");
        }
    }
    public void oneBTN(View view) {
        updateText("1");
    }

    public void twoBTN(View view) {
        updateText("2");
    }

    public void threeBTN(View view) {
        updateText("3");
    }

    public void fourBTN(View view) {
        updateText("4");
    }

    public void fiveBTN(View view) {
        updateText("5");
    }

    public void sixBTN(View view) {
        updateText("6");
    }

    public void sevenBTN(View view) {
        updateText("7");
    }

    public void eightBTN(View view) {
        updateText("8");
    }

    public void nineBTN(View view) {
        updateText("9");
    }

    public void resetBTN(View view) {
        display.setText("");
    }

    public void dotBTN(View view) {
        updateText(".");
    }

    public void plusMinusBTN(View view) {
        int cursorPos = display.getSelectionStart(); // определяем положение курсора и записываем его в переменную
        int textLen = display.getText().length();

        //если минус уже стоит в начале, то удаляем первый элемент
        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder set = (SpannableStringBuilder) display.getText();
            char c = set.charAt(0); //в переменную с записываем первый символ строки класса SpannableStringBuilder

            if (c == '-') {
                set.delete(0, 1);
                display.setText(set); // обновляем текст на дисплее без минуса;
                display.setSelection(cursorPos - 1); // обновляем положение курсора с учетом одного удаленного символа
                //иначе добавляем с помощью метода минус в начало строки и обновляем текст и курсор
            } else {
                display.setSelection(0);
                updateText("-");
                display.setSelection(cursorPos + 1);
            }
        }
    }

    public void equalBTN(View view) {
        String userExp = display.getText().toString(); // получаем строку с дисплея и сохраняем в переменную userExp
        Expression exp = new Expression(userExp); // создаем переменную exp класса Expression из импортированной библиотеки

        // c помощью метода calculate() класса Expression вычисляем выражение на дисплее и сохраняем в переменную result
        String result = String.valueOf(exp.calculate());

        display.setText(result); // обновляем дисплей - выводим результат
        display.setSelection(result.length()); //обнавляем положение курсора на конец результата
    }

    public void plusBTN(View view) {
        updateText("+");
    }

    public void minusBTN(View view) {
        updateText("-");
    }

    public void multBTN(View view) {
        updateText("*");
    }

    public void divBTN(View view) {
        updateText("/");
    }

    public void clearBTN(View view) {
        int cursorPos = display.getSelectionStart(); // получили текущее состояние курсора
        int textLen = display.getText().length();  //переменная в которую сохраним длину текста на дисплее

        // если курсор не находится в самом начале и длина введенного текста не равна нулю
        if (cursorPos != 0 && textLen != 0) {
            //используем метод SpannableStringBuilder чтобы воспользоваться методом replace
            SpannableStringBuilder set = (SpannableStringBuilder) display.getText();
            set.replace(cursorPos - 1, cursorPos, ""); //заменяем символ стоящий до курсора на пустой
            display.setText(set); //обновляем текст на дисплее на только что полученный после замены
            display.setSelection(cursorPos - 1); //фиксируем место курсора на позиции - 1, т.к. один символ мы удалили
        }
    }

    public void percentBTN(View view) {
        updateText("%");
    }
}

