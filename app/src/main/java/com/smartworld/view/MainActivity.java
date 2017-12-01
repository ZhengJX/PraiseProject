package com.smartworld.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import layout.PraiseView;
import smartworld.com.praiseproject.R;

public class MainActivity extends AppCompatActivity
{
    private PraiseView praiseView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        praiseView = (PraiseView) findViewById(R.id.praiseView);
        praiseView.setLeftNum(86);
        praiseView.setRightNum(118);
        praiseView.invalidate();
    }
}
