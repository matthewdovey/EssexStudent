package com.matthew.essexstudent.activities.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.matthew.essexstudent.R;
import com.matthew.essexstudent.activities.connections.UniversityLogin;
import com.matthew.essexstudent.activities.databases.StudentDB;
import com.matthew.essexstudent.activities.populaters.DataCentre;
import com.matthew.essexstudent.activities.populaters.StudentAccount;
import com.matthew.essexstudent.activities.populaters.StudentEducation;
import com.matthew.essexstudent.activities.scrapers.WhatsOnScraper;
import com.squareup.picasso.Picasso;

import org.jsoup.nodes.Document;

import java.util.ArrayList;

/**
 * Created by matthew on 15/12/15.
 */
public class LoginActivity extends AppCompatActivity {

    private String username;
    private String password;
    //private StudentDB database = new StudentDB(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText essexEmail = (EditText) findViewById(R.id.essexEmail);
        final EditText essexPassword = (EditText) findViewById(R.id.essexPassword);

        Button proceed = (Button) findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = essexEmail.getText().toString();
                String password = essexPassword.getText().toString();
                setCredentials(email, password);
                //new GatherCredentials().execute();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    private void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private class GatherCredentials extends AsyncTask<String,Void,String> {

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

        @Override
        protected String doInBackground(String... params) {
            UniversityLogin myEssex = new UniversityLogin(username, password);
            StudentAccount account = new StudentAccount();
            StudentEducation education = new StudentEducation();

            myEssex.login();
            myEssex.education();

            Document accountDetails = myEssex.getStudentDetails();
            Document educationDetails = myEssex.getStudentEducation();

            account.getStudentDetails(accountDetails);
            email = account.getEmail();
            mobile = account.getMobile();
            address1 = account.getAddressLine1();
            address2 = account.getAddressLine2();
            city = account.getCity();
            county = account.getCounty();
            postcode = account.getPostCode();

            education.getStudentEducation(educationDetails);
            fullName = education.getFullName();
            degree = education.getDegree();
            regNumb = education.getRegNumber();
            year = education.getCurrentYear();
            tutor = education.getTutor();

            return null;
        }

        @Override
        protected void onPostExecute(String strings) {
            super.onPostExecute(strings);

            //database.insertData(fullName,email,degree,regNumb,
                    //year,tutor,mobile,address1,address2,city,county,postcode);
        }
    }
}
