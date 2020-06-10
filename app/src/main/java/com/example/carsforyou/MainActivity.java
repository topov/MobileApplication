package com.example.carsforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    final Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.addNewBTN);
        Button browseButton = (Button) findViewById(R.id.browseBTN);
        Button allButton = (Button) findViewById(R.id.allAdsBTN);

        ImageView img = (ImageView) findViewById(R.id.img);

        img.setImageResource(R.mipmap.cars);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        AddActivity.class));
            }
        });

        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        SearchActivity.class));
            }
        });

        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        AllAdsActivity.class));
            }
        });

    }
}
