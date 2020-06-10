package com.example.carsforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActivityDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final TextView adCarMake = (TextView) findViewById(R.id.adType);
        final TextView adCarModel = (TextView) findViewById(R.id.adModel);
        final TextView adCarDesc = (TextView) findViewById(R.id.adDesc);
        final TextView adPrice = (TextView) findViewById(R.id.adPrice);
        final TextView adOwnerName = (TextView) findViewById(R.id.adOwner);
        final TextView adOwnerPhone = (TextView) findViewById(R.id.adPhone);

        final Database db = new Database(this);

        Bundle bundle = getIntent().getExtras();
        int id = -1;
        Advertisement advertisement = null;
        if (bundle != null) {
            id = bundle.getInt("id");
        }

        advertisement = db.getAdvertisement(id);

        adCarMake.setText(advertisement.getCarMake());
        adCarModel.setText(advertisement.getCarModel());
        adCarDesc.setText(advertisement.getCarDescription());
        adPrice.setText(String.valueOf(advertisement.getPrice()) + "USD");
        adOwnerName.setText(advertisement.getOwnerName());
        adOwnerPhone.setText(advertisement.getOwnerNumber());
    }
}
