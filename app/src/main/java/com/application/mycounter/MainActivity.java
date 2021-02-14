package com.application.mycounter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Integer counter = 0;
    private Integer prevLength=0;
    private Integer length=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
