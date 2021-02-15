package com.application.mycounter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Integer counter = 0; //счетчик студентов
    private Integer prevLength=0; //предыдущаяя длина кол-ва студентов
    private Integer length=0; // длина кол-ва студентов
    private int width = 333; //диаметр окружности - ширина
    private int height = 333;//диаметр окружности - высота
    public static final String TAG = "StartActivity"; // - тег (для отладки)

    /**
     * Начало работы Activity
     * @param savedInstanceState - сохраненное состояние
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        Toast.makeText(this, "onCreate()", Toast.LENGTH_LONG).show();
    }

    /**
     * Действие, происходящее при клике на кнопку "Добавить"
     *
     * @param view - область с кнопкой
     */
    @SuppressLint("SetTextI18n")
    public void onClickBtnAddStudents(View view) {
        counter++;
        length = counter.toString().length();
        TextView counterView = (TextView)findViewById(R.id.txt_counter);
        ViewGroup.LayoutParams params = counterView.getLayoutParams();
        if(prevLength < length){
            params.height=counterView.getHeight() + counter.toString().length()*20;
            params.width=counterView.getWidth() + counter.toString().length()*20;
            counterView.setLayoutParams(params);
            prevLength = length;
        }

        counterView.setText(counter.toString());

    }

    /**
     * Запускается после создания activity
     *
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    /**
     * Восстанавливает состояние после заморозки
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        resetUI();
    }

    /**
     * Сохраняет состояние активити, а также все переменные и поля класса
     *
     * @param outState - внешнее состояние
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter", counter);
        outState.putInt("width",(findViewById(R.id.txt_counter).getWidth()));
        outState.putInt("height",(findViewById(R.id.txt_counter).getHeight()));
        outState.putInt("length",length);
        outState.putInt("prevLength",prevLength);
        Log.d(TAG, "onSaveInstanceState");
    }

    /**
     * Восстанавливает состояние из сохраненного
     *
     * @param savedInstanceState - сохраненное состояние
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null &&
                savedInstanceState.containsKey("counter") &&
                    savedInstanceState.containsKey("width") &&
                        savedInstanceState.containsKey("height")) {
            counter = savedInstanceState.getInt("counter");
            prevLength = savedInstanceState.getInt("prevLength");
            length = savedInstanceState.getInt("length");
            width = savedInstanceState.getInt("width");
            height = savedInstanceState.getInt("height");
        }
    }

    /**
     * Обновляет интерфейс пользователя при обновлении активити
     *
     */
    @SuppressLint("SetTextI18n")
    private void resetUI() {
        TextView textView = findViewById(R.id.txt_counter);
        textView.setText(counter.toString());
        ViewGroup.LayoutParams params = textView.getLayoutParams();
        params.width = width;
        params.height = height;
        textView.setLayoutParams(params);
    }
}
