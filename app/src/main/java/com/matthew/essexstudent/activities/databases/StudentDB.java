package com.matthew.essexstudent.activities.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDB {
    DatabaseHandler database;

    private String fullName;
    private String email;
    private String degree;
    private String regNumb;
    private String year;
    private String tutor;
    private String mobile;
    private String address1;
    private String address2;
    private String city;
    private String county;
    private String postcode;

    public StudentDB(Context context){
        database = new DatabaseHandler(context);
    }

    public long insertData(String name, String email, String degree,
                           String regNumb, String year, String tutor,
                           String mobile, String address1, String address2,
                           String city, String county, String postcode){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHandler.NAME, name);
        contentValues.put(DatabaseHandler.EMAIL, email);
        contentValues.put(DatabaseHandler.DEGREE, degree);
        contentValues.put(DatabaseHandler.REGNUMB, regNumb);
        contentValues.put(DatabaseHandler.YEAR, year);
        contentValues.put(DatabaseHandler.TUTOR, tutor);
        contentValues.put(DatabaseHandler.MOBILE, mobile);
        contentValues.put(DatabaseHandler.ADDRESS1, address1);
        contentValues.put(DatabaseHandler.ADDRESS2, address2);
        contentValues.put(DatabaseHandler.CITY, city);
        contentValues.put(DatabaseHandler.COUNTY, county);
        contentValues.put(DatabaseHandler.POSTCODE, postcode);
        long id = db.insert(DatabaseHandler.TABLE_NAME,null,contentValues);
        return id;
    }

    public void populateAllData(){
        SQLiteDatabase db = database.getWritableDatabase();
        String[] columns = {DatabaseHandler.UID, DatabaseHandler.NAME,DatabaseHandler.EMAIL,
                DatabaseHandler.DEGREE,DatabaseHandler.REGNUMB,DatabaseHandler.YEAR,
                DatabaseHandler.TUTOR,DatabaseHandler.MOBILE,DatabaseHandler.ADDRESS1,
                DatabaseHandler.ADDRESS2,DatabaseHandler.CITY,DatabaseHandler.COUNTY,
                DatabaseHandler.POSTCODE};

        Cursor cursor =  db.query(DatabaseHandler.TABLE_NAME, columns, null, null, null, null, null);

        int rowName = cursor.getColumnIndex(DatabaseHandler.NAME);
        int rowEmail = cursor.getColumnIndex(DatabaseHandler.EMAIL);
        int rowDegree = cursor.getColumnIndex(DatabaseHandler.DEGREE);
        int rowReg = cursor.getColumnIndex(DatabaseHandler.REGNUMB);
        int rowYear = cursor.getColumnIndex(DatabaseHandler.YEAR);
        int rowTutor = cursor.getColumnIndex(DatabaseHandler.TUTOR);
        int rowMobile = cursor.getColumnIndex(DatabaseHandler.MOBILE);
        int rowAddress1 = cursor.getColumnIndex(DatabaseHandler.ADDRESS1);
        int rowAddress2 = cursor.getColumnIndex(DatabaseHandler.ADDRESS2);
        int rowCity = cursor.getColumnIndex(DatabaseHandler.CITY);
        int rowCounty = cursor.getColumnIndex(DatabaseHandler.COUNTY);
        int rowPostcode = cursor.getColumnIndex(DatabaseHandler.POSTCODE);

        while(cursor.moveToNext()){
            fullName = cursor.getString(rowName);
            email = cursor.getString(rowEmail);
            degree = cursor.getString(rowDegree);
            regNumb = cursor.getString(rowReg);
            year = cursor.getString(rowYear);
            tutor = cursor.getString(rowTutor);
            mobile = cursor.getString(rowMobile);
            address1 = cursor.getString(rowAddress1);
            address2 = cursor.getString(rowAddress2);
            city = cursor.getString(rowCity);
            county = cursor.getString(rowCounty);
            postcode = cursor.getString(rowPostcode);
        }
    }

    public String getFullName() {
        return fullName;
    }

    public String getDegree() {
        return degree;
    }

    public String getEmail() {
        return email;
    }

    public String getRegNumb() {
        return regNumb;
    }

    public String getYear() {
        return year;
    }

    public String getTutor() {
        return tutor;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getPostcode() {
        return postcode;
    }

    static class DatabaseHandler extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "WebScraperDatabase";
        private static final String TABLE_NAME = "APPDATA";
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String EMAIL = "Email";
        private static final String DEGREE = "Degree";
        private static final String REGNUMB = "RegNumb";
        private static final String YEAR = "Year";
        private static final String TUTOR = "Tutor";
        private static final String MOBILE = "Mobile";
        private static final String ADDRESS1 = "Address1";
        private static final String ADDRESS2 = "Address2";
        private static final String CITY = "City";
        private static final String COUNTY = "County";
        private static final String POSTCODE = "Postcode";
        private Context context;
        private static int DATABASE_VERSION = 1;
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+
                UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255), "+
                EMAIL+" VARCHAR(255), "+DEGREE+" VARCHAR(255), "+REGNUMB+" VARCHAR(255), "+
                YEAR+" VARCHAR(255)), "+TUTOR+" VARCHAR(255), "+MOBILE+" VARCHAR(255), "+
                ADDRESS1+" VARCHAR(255), "+ADDRESS2+" VARCHAR(255), "+CITY+" VARCHAR(255), "+
                COUNTY+" VARCHAR(255), "+POSTCODE+" VARCHAR(255);";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

        public DatabaseHandler(Context context){
            super(context, DATABASE_NAME,null,DATABASE_VERSION);
            this.context = context;
            //Log.d("databaseCreated", "DATABASE HAS BEEN CREATED!");
            //Log.d("Table",CREATE_TABLE);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        /* this method creates the first instance of the database, so you need to fill this with tables
        and data from the arrays in main activity.
         */
            try {
                db.execSQL(CREATE_TABLE);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            //Log.d("databaseFullyCreated", "DATABASE HAS BEEN CREATED THROUGH ON CREATE!");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*this will upgrade the database, so when the refresh button is called the new
        data from the webscraper will be re added. and old ones will need to be removed so that we can
        keep the size down. call on create at the end with the db name in parameters
         */
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
