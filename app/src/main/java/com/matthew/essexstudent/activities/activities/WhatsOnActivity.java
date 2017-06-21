package com.matthew.essexstudent.activities.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.matthew.essexstudent.activities.databases.EventsDB;
import com.matthew.essexstudent.activities.scrapers.WhatsOnScraper;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import com.matthew.essexstudent.R;

import org.jsoup.nodes.Document;

public class WhatsOnActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static String focusLink;
    static String focusImage;
    static String focusTitle;
    static String focusInfo;
    static String focusDesc;

    private List<String> eventLinks = new ArrayList<String>();
    private List<String> eventTitles = new ArrayList<String>();
    private List<String> eventInfos = new ArrayList<String>();
    private List<String> eventImages = new ArrayList<String>();
    private List<String> eventDescs = new ArrayList<String>();

    private Menu optionsMenu;
    private boolean arraysPopulated;
    EventsDB database = new EventsDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatson);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        database.getAllData();

        if (database.getEventLinks().size()==0 || database.getEventTitles().size()==0
                || database.getEventDescs().size()==0 || database.getEventInfos().size()==0
                || database.getEventImages().size() ==0 ) {
            new JsoupListView().execute();
        } else {
            arraysPopulated = true;

            eventLinks = database.getEventLinks();
            eventTitles = database.getEventTitles();
            eventInfos = database.getEventInfos();
            eventDescs = database.getEventDescs();
            eventImages = database.getEventImages();

            populateMainActivity();
        }

        //Need more buttons!! The events go up to 20 now!

        Button event1 = (Button) findViewById(R.id.button1);
        Button event2 = (Button) findViewById(R.id.button2);
        Button event3 = (Button) findViewById(R.id.button3);
        Button event4 = (Button) findViewById(R.id.button4);
        Button event5 = (Button) findViewById(R.id.button5);
        Button event6 = (Button) findViewById(R.id.button6);
        Button event7 = (Button) findViewById(R.id.button7);
        Button event8 = (Button) findViewById(R.id.button8);
        Button event9 = (Button) findViewById(R.id.button9);
        Button event10 = (Button) findViewById(R.id.button10);
        Button event11 = (Button) findViewById(R.id.button11);
        Button event12 = (Button) findViewById(R.id.button12);
        Button event13 = (Button) findViewById(R.id.button13);
        Button event14 = (Button) findViewById(R.id.button14);
        Button event15 = (Button) findViewById(R.id.button15);

        event1.setOnClickListener(buttonCheck);
        event2.setOnClickListener(buttonCheck);
        event3.setOnClickListener(buttonCheck);
        event4.setOnClickListener(buttonCheck);
        event5.setOnClickListener(buttonCheck);
        event6.setOnClickListener(buttonCheck);
        event7.setOnClickListener(buttonCheck);
        event8.setOnClickListener(buttonCheck);
        event9.setOnClickListener(buttonCheck);
        event10.setOnClickListener(buttonCheck);
        event11.setOnClickListener(buttonCheck);
        event12.setOnClickListener(buttonCheck);
        event13.setOnClickListener(buttonCheck);
        event14.setOnClickListener(buttonCheck);
        event15.setOnClickListener(buttonCheck);
    }

    View.OnClickListener buttonCheck = new View.OnClickListener() {
        public void onClick(View v) {
            int longId = v.getId();
            boolean buttFinder = false;
            int idNumber = 1;
            while (!buttFinder) {
                String id = "button"+idNumber;
                int resID = getResources().getIdentifier(id, "id", "com.matthew.essexstudent");
                if (longId == resID) {
                    buttFinder = true;
                    idNumber--;
                }
                idNumber++;
            }
            focusLink = eventLinks.get(idNumber-1);
            focusImage = eventImages.get(idNumber-1);
            focusTitle = eventTitles.get(idNumber-1);
            focusInfo = eventInfos.get(idNumber-1);
            focusDesc = eventDescs.get(idNumber-1);
            startActivity(new Intent(WhatsOnActivity.this, EventActivity.class));
        }
    };

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refresh_menu, menu);

        optionsMenu = menu;

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_refresh:
                setRefreshActionButtonState(true);
                arraysPopulated = false;
                //Drop database

                new JsoupListView().execute();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void setRefreshActionButtonState(final boolean refreshing) {
        if (optionsMenu != null) {
            final MenuItem refreshItem = optionsMenu.findItem(R.id.action_refresh);
            if (refreshItem != null) {
                if (refreshing) {
                    refreshItem.setActionView(R.layout.refresh_event);
                } else {
                    refreshItem.setActionView(null);
                }
            }
        }
    }

    public void resetUpdating() {
        MenuItem m = optionsMenu.findItem(R.id.action_refresh);
        if(m.getActionView()!=null)
        {
            m.getActionView().clearAnimation();
            m.setActionView(null);
        }
    }

    //Not sure if this actually refreshes the app with the new data.
    public void populateMainActivity() {
        String link;
        String buttName;
        String infoName;
        String descName;
        int j=1;
        for(int i=0;i<15;i++) {
            String imgId = "imageView"+j;
            if (j == 1) {
                ImageView changeImage = (ImageView) findViewById(R.id.imageView);
                link = eventImages.get(i);
                Picasso.with(changeImage.getContext()).load(link).into(changeImage);
            } else {
                int resID = getResources().getIdentifier(imgId, "id", "com.matthew.essexstudent");
                ImageView changeImage = (ImageView) findViewById(resID);
                link = eventImages.get(i);
                try {
                    Picasso.with(changeImage.getContext()).load(link).into(changeImage);
                } catch (Exception e) {
                    System.out.println("ERROR: Unable to use picture");
                }
            }
            j++;
        }
        j=1;
        for(int i=0;i<15;i++) {
            String butId = "button"+j;
            int resID = getResources().getIdentifier(butId, "id", "com.matthew.essexstudent");
            Button changeButton = (Button) findViewById(resID);
            buttName = eventTitles.get(i);
            changeButton.setText(buttName);
            j++;
        }
        j=1;
        for(int i=0;i<15;i++) {
            String infoId = "info"+j;
            int resID = getResources().getIdentifier(infoId, "id", "com.matthew.essexstudent");
            TextView changeInfo = (TextView) findViewById(resID);
            infoName = eventInfos.get(i);
            changeInfo.setText(infoName);
            j++;
        }
        j=1;
        for(int i=0;i<15;i++) {
            String descId = "desc"+j;
            int resID = getResources().getIdentifier(descId, "id", "com.matthew.essexstudent");
            TextView changeDesc = (TextView) findViewById(resID);
            descName = eventDescs.get(i);
            changeDesc.setText(descName);
            j++;
        }
    }

    private class JsoupListView extends AsyncTask<ArrayList<String>,Void,ArrayList<String>> {
        private int x;
        private int itemNumber = x;
        @Override
        protected ArrayList<String> doInBackground(ArrayList<String>... params) {
            WhatsOnScraper whatson = new WhatsOnScraper();
            whatson.connect();
            if (whatson.returnConnected()) {
                whatson.getLinks();
                for(x=0;x<whatson.returnLinks().size();x++) {
                    Document event = whatson.getEvent(x);
                    whatson.getTitles(event);
                    whatson.getInfo(event);
                    whatson.getDesc(event);
                }
                whatson.getImages(itemNumber);
                eventLinks = whatson.returnLinks();
                eventImages = whatson.returnImages();
                eventTitles = whatson.returnTitles();
                eventInfos = whatson.returnInfos();
                eventDescs = whatson.returnDescs();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);

            String link;
            String buttName;
            String infoName;
            String descName;

            int j=1;
            for(int i=0;i<15;i++) {
                String imgId = "imageView"+j;
                if (j == 1) {
                    ImageView changeImage = (ImageView) findViewById(R.id.imageView);
                    link = eventImages.get(i);
                    Picasso.with(changeImage.getContext()).load(link).into(changeImage);
                } else {
                    int resID = getResources().getIdentifier(imgId, "id", "com.matthew.essexstudent");
                    ImageView changeImage = (ImageView) findViewById(resID);
                    link = eventImages.get(i);
                    try {
                        Picasso.with(changeImage.getContext()).load(link).into(changeImage);
                    } catch (Exception e) {
                        System.out.println("ERROR: Unable to use picture");
                    }
                }
                j++;
            }
            j=1;
            for(int i=0;i<15;i++) {
                String butId = "button"+j;
                int resID = getResources().getIdentifier(butId, "id", "com.matthew.essexstudent");
                Button changeButton = (Button) findViewById(resID);
                buttName = eventTitles.get(i);
                changeButton.setText(buttName);
                j++;
            }
            j=1;
            for(int i=0;i<15;i++) {
                String infoId = "info"+j;
                int resID = getResources().getIdentifier(infoId, "id", "com.matthew.essexstudent");
                TextView changeInfo = (TextView) findViewById(resID);
                infoName = eventInfos.get(i);
                changeInfo.setText(infoName);
                j++;
            }
            j=1;
            for(int i=0;i<15;i++) {
                String descId = "desc"+j;
                int resID = getResources().getIdentifier(descId, "id", "com.matthew.essexstudent");
                TextView changeDesc = (TextView) findViewById(resID);
                descName = eventDescs.get(i);
                changeDesc.setText(descName);
                j++;
            }

            resetUpdating();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            startActivity(new Intent(WhatsOnActivity.this, AccountActivity.class));
        } else if (id == R.id.nav_schedule) {
            startActivity(new Intent(WhatsOnActivity.this, ScheduleActivity.class));
        } else if (id == R.id.nav_email) {
            startActivity(new Intent(WhatsOnActivity.this, EmailActivity.class));
        } else if (id == R.id.nav_tools) {
            startActivity(new Intent(WhatsOnActivity.this, ToolsActivity.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(WhatsOnActivity.this, LoginActivity.class));
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onDestroy(){
        super.onDestroy();
        System.out.println("Destroying application...");
        Log.d("inOnDestroy", "Hello");
        if (!(arraysPopulated)) {
            try{

                for(int i = 0; i < eventLinks.size();i++){
                    database.insertData(eventLinks.get(i),eventImages.get(i)
                            ,eventTitles.get(i),eventInfos.get(i),eventDescs.get(i));
                }

            } catch (Exception e) {
                System.out.println("Error: Could not connect to database");
            }
            finish();
        }
    }
}
