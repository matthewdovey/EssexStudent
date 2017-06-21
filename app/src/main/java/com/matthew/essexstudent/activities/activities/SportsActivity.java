package com.matthew.essexstudent.activities.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.matthew.essexstudent.R;
import com.matthew.essexstudent.activities.scrapers.SportsScraper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthew on 15/12/15.
 */
public class SportsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<String> links = new ArrayList<String>();
    List<String> titles = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        new JsoupListView().execute();
    }

    private class JsoupListView extends AsyncTask<ArrayList<String>,Void,ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(ArrayList<String>... params) {
            SportsScraper sports = new SportsScraper();
            sports.connect();
            sports.getLinks();
            sports.getTitles();

            links = sports.returnLinks();
            titles = sports.returnTitles();

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            final LinearLayout lm = (LinearLayout) findViewById(R.id.button_layout);

            for (int k=0;k<links.size();k++) {
                final LayoutInflater btnInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                Button newButton = (Button)btnInflater.inflate(R.layout.sports_button, null);
                newButton.setText(titles.get(k));
                lm.addView(newButton);
            }
        }
    }

//    View.OnClickListener buttonCheck = new View.OnClickListener() {
//        public void onClick(View v) {
//            int longId = v.getId();
//            boolean buttFinder = false;
//            int idNumber = 1;
//            while (!buttFinder) {
//                String id = "button"+idNumber;
//                int resID = getResources().getIdentifier(id, "id", "com.matthew.essexstudent");
//                if (longId == resID) {
//                    buttFinder = true;
//                    idNumber--;
//                }
//                idNumber++;
//            }
//            focusLink = eventLinks.get(idNumber-1);
//            focusImage = eventImages.get(idNumber-1);
//            focusTitle = eventTitles.get(idNumber-1);
//            focusInfo = eventInfos.get(idNumber-1);
//            focusDesc = eventDescs.get(idNumber-1);
//            startActivity(new Intent(WhatsOnActivity.this, EventActivity.class));
//        }
//    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            startActivity(new Intent(SportsActivity.this, AccountActivity.class));
        } else if (id == R.id.nav_schedule) {
            startActivity(new Intent(SportsActivity.this, ScheduleActivity.class));
        } else if (id == R.id.nav_email) {
            startActivity(new Intent(SportsActivity.this, EmailActivity.class));
        } else if (id == R.id.nav_tools) {
            startActivity(new Intent(SportsActivity.this, ToolsActivity.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(SportsActivity.this, LoginActivity.class));
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
