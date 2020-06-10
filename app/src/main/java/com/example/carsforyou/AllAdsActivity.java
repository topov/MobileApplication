package com.example.carsforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class AllAdsActivity extends AppCompatActivity {

    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ads);

        final Database db = new Database(this);

        List<Advertisement> ads = null;
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String carMake = bundle.getString("carMake");
            String price = bundle.getString("price");
            String carModel = bundle.getString("model");
            ads = db.getAdvertisementsWithSearchCriteria(carMake, carModel, price);
        } else {
            ads = db.getAdvertisements();
        }

        final ArrayAdapter adapter = new ArrayAdapter<Advertisement>(this,
                R.layout.activity_listview, ads);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Advertisement ad = (Advertisement) adapter.getItem(position);

                Intent intent = new Intent(AllAdsActivity.this, ActivityDetails.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", ad.getId());
                intent.putExtras(bundle);

                startActivity(intent);
                finish();
            }
        });

    }
}
