package com.russell.router;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.russell.annotation.Route;

@Route(path = "main/second")
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}