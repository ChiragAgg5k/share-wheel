package com.obsidian.sharewheel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ReportActivity extends AppCompatActivity {

    ImageView back_arrow_report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        back_arrow_report = (ImageView) findViewById(R.id.back_arrow_report);



    }
}