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
    private Integer counter = 0;
    private Integer prevLength=0;
    private Integer length=0;
    public static final String TAG = "StartActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        Toast.makeText(this, "onCreate()", Toast.LENGTH_LONG).show();
    }

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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        resetUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        resetUI();
    }

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
            TextView textView = findViewById(R.id.txt_counter);
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            params.width = savedInstanceState.getInt("width");
            params.height = savedInstanceState.getInt("height");
            textView.setLayoutParams(params);
        }
    }

    @SuppressLint("SetTextI18n")
    private void resetUI() {
        TextView textView = findViewById(R.id.txt_counter);
        textView.setText(counter.toString());
    }
}
