package com.example.carsforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.carsforyou.Database.carBrands;

public class AddActivity extends AppCompatActivity {

    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final TextView modelInput = (TextView) findViewById(R.id.modelText);
        final TextView desriptionInput = (TextView) findViewById(R.id.describeText);
        final TextView priceInput = (TextView) findViewById(R.id.priceText);
        final TextView nameInput = (TextView) findViewById(R.id.nameText);
        final TextView numberInput = (TextView) findViewById(R.id.numberText);
        Button publishBtn = (Button) findViewById(R.id.publishBTN);
        Button backBtn = (Button) findViewById(R.id.backFromSearchBTN);

        alertDialogBuilder = new AlertDialog.Builder(this);
        final Database db = new Database(this);

        //spinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carBrands);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inputsEmpty = desriptionInput.getText().toString().isEmpty()
                                        || modelInput.getText().toString().isEmpty()
                                        || priceInput.getText().toString().isEmpty()
                                        || nameInput.getText().toString().isEmpty()
                                        || numberInput.getText().toString().isEmpty();

                if (inputsEmpty) {
                    alertDialogBuilder.setMessage("Please fill all fields!");
                    alertDialogBuilder.setPositiveButton("OK", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }

                            });
                }  else {
                    Advertisement advertisement = null;
                    String enteredCarMake = spinner.getSelectedItem().toString();
                    String enteredModel = modelInput.getText().toString();
                    String enteredDescription = desriptionInput.getText().toString();
                    float enteredPrice = Float.parseFloat(priceInput.getText().toString());
                    String enteredName = nameInput.getText().toString();
                    String enteredNumber = numberInput.getText().toString();

                    advertisement = new Advertisement(
                            enteredName,
                            enteredNumber,
                            enteredPrice,
                            enteredCarMake,
                            enteredModel,
                            enteredDescription
                    );

                    db.addAdvertisement(advertisement);

                    alertDialogBuilder.setMessage("The ad is now published");
                    alertDialogBuilder.setPositiveButton("OK", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    modelInput.setText("");
                                    desriptionInput.setText("");
                                    priceInput.setText("");
                                    nameInput.setText("");
                                    numberInput.setText("");
                                    startActivity(new Intent(AddActivity.this,
                                            MainActivity.class));
                                }

                            });
                }

                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddActivity.this,
                        MainActivity.class));
            }
        });
    }


}
