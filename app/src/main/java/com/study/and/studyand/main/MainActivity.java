package com.study.and.studyand.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.study.and.studyand.R;
import com.study.and.studyand.domain.HelloWorldActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        // pre do something.

        Intent intent = new Intent(getApplicationContext(), HelloWorldActivity.class);
        startActivity(intent);
    }
}
