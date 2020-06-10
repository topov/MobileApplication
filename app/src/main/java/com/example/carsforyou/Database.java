package com.example.carsforyou;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "carsforyouDb1";

    private static final String TABLE_ADVERTISEMENTS = "ADVERTISEMENTS";

    private static final String KEY_ID = "Id";
    private static final String KEY_NAME = "OwnerName";
    private static final String KEY_NUMBER = "OwnerNumber";
    private static final String KEY_TYPE = "CarMake";
    private static final String KEY_MODEL = "CarModel";
    private static final String KEY_DESCRIPTION = "CarDescription";
    private static final String KEY_PRICE = "Price";

    public static final List<String> carBrands = Arrays.asList(
            "BMW",
            "Peugeot",
            "Mercedes",
            "Audi",
            "Opel",
            "VOLKSWAGEN",
            "Bentley"
    );

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_ADVERTISEMENTS
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_NUMBER + " TEXT," + KEY_TYPE + " TEXT," + KEY_MODEL + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_PRICE + " DECIMAL"
                + ")";
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADVERTISEMENTS);
        onCreate(db);
    }

    public void onDeleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ADVERTISEMENTS,null,null);
    }

    void addAdvertisement(Advertisement ad) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, ad.getOwnerName());
        values.put(KEY_NUMBER, ad.getOwnerNumber());
        values.put(KEY_TYPE, ad.getCarMake());
        values.put(KEY_MODEL, ad.getCarModel());
        values.put(KEY_DESCRIPTION, ad.getCarDescription());
        values.put(KEY_PRICE, ad.getPrice());

        db.insert(TABLE_ADVERTISEMENTS, null, values);
        db.close();
    }

    public Advertisement getAdvertisement(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Advertisement advertisement = null;

        String selectQuery = "SELECT * FROM " + TABLE_ADVERTISEMENTS
                                + " WHERE " + KEY_ID + " = " + id;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        advertisement = new Advertisement(
                cursor.getString(1),
                cursor.getString(2),
                Float.parseFloat(cursor.getString(6)),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5)
        );

        return advertisement;
    }

    public List<Advertisement> getAdvertisements() {
        List<Advertisement> advertisements = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_ADVERTISEMENTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Advertisement ad = new Advertisement();
                ad.setId(Integer.parseInt(cursor.getString(0)));
                ad.setOwnerName(cursor.getString(1));
                ad.setOwnerNumber(cursor.getString(2));
                ad.setCarMake(cursor.getString(3));
                ad.setCarModel(cursor.getString(4));
                ad.setCarDescription(cursor.getString(5));
                ad.setPrice(Float.parseFloat(cursor.getString(6)));

                advertisements.add(ad);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return advertisements;
    }

    public List<Advertisement> getAdvertisementsWithSearchCriteria(String carMake, String carModel, String price) {
        List<Advertisement> advertisements = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_ADVERTISEMENTS;
        if (price.isEmpty() && carModel.isEmpty()) {
            selectQuery = "SELECT * FROM " + TABLE_ADVERTISEMENTS
                            + " WHERE " + KEY_TYPE + " = " + "\"" + carMake + "\"";
        }
        else if(carModel.isEmpty() && !price.isEmpty()) {
            float priceToFloat = Float.parseFloat(price);
            selectQuery = "SELECT * FROM " + TABLE_ADVERTISEMENTS
                    + " WHERE " + KEY_TYPE + " = " + "\"" + carMake + "\""
                    + " AND " + KEY_PRICE + " < " + priceToFloat;
        }
        else if(!carModel.isEmpty() && price.isEmpty()) {
            selectQuery = "SELECT * FROM " + TABLE_ADVERTISEMENTS
                    + " WHERE " + KEY_TYPE + " = " + "\"" + carMake + "\""
                    + " AND " + KEY_MODEL + " = " + "\"" + carModel + "\"";
        }
        else if(!carModel.isEmpty() && !price.isEmpty()) {
            float priceToFloat = Float.parseFloat(price);
            selectQuery = "SELECT * FROM " + TABLE_ADVERTISEMENTS
                    + " WHERE " + KEY_TYPE + " = " + "\"" + carMake + "\""
                    + " AND " + KEY_PRICE + " < " + priceToFloat
                    + " AND " + KEY_MODEL + " = " + "\"" + carModel + "\"";
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Advertisement ad = new Advertisement();
                ad.setId(Integer.parseInt(cursor.getString(0)));
                ad.setOwnerName(cursor.getString(1));
                ad.setOwnerNumber(cursor.getString(2));
                ad.setCarMake(cursor.getString(3));
                ad.setCarModel(cursor.getString(4));
                ad.setCarDescription(cursor.getString(5));
                ad.setPrice(Float.parseFloat(cursor.getString(6)));

                advertisements.add(ad);
            } while (cursor.moveToNext());
        }

        return advertisements;
    }
}
