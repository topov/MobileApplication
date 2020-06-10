package com.example.carsforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.carsforyou.Database.carBrands;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Spinner spinner = (Spinner) findViewById(R.id.searchSpinner);
        final TextView priceInput = (TextView) findViewById(R.id.priceSearchText);
        final TextView modelInput = (TextView) findViewById(R.id.modelSearchText);
        Button searchBtn = (Button) findViewById(R.id.searchBTN);
        Button backBtn = (Button) findViewById(R.id.backFromSearchBTN);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carBrands);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredCarMake = spinner.getSelectedItem().toString();
                String enteredPrice = priceInput.getText().toString();
                String enteredModel = modelInput.getText().toString();

                Intent intent = new Intent(SearchActivity.this, AllAdsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("carMake", enteredCarMake);
                bundle.putString("price", enteredPrice);
                bundle.putString("model",enteredModel);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this,
                        MainActivity.class));
            }
        });

    }
}
