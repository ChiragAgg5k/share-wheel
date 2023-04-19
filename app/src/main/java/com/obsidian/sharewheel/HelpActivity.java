package com.obsidian.sharewheel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
    TextView number_one, number_two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        number_one = findViewById(R.id.number_one);
        number_two = findViewById(R.id.number_two);

        number_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = number_one.getText().toString();
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phonenumber));
                startActivity(dialIntent);
            }
        });

        number_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumbertwo = number_two.getText().toString();
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phonenumbertwo));
                startActivity(dialIntent);
            }
        });
    }


}