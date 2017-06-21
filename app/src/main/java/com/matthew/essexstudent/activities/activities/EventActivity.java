package com.matthew.essexstudent.activities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.matthew.essexstudent.R;
import com.squareup.picasso.Picasso;

public class EventActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static String eventLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        eventLink = WhatsOnActivity.focusLink;

        ImageView eventImage = (ImageView) findViewById(R.id.focusImageId);
        TextView eventTitle = (TextView) findViewById(R.id.focusTitleId);
        TextView eventInfo = (TextView) findViewById(R.id.focusInfoId);
        TextView eventDesc = (TextView) findViewById(R.id.focusDescId);
        Button webEvent = (Button) findViewById(R.id.webButton);

        Picasso.with(eventImage.getContext()).load(WhatsOnActivity.focusImage).into(eventImage);
        eventTitle.setText(WhatsOnActivity.focusTitle);
        eventInfo.setText(WhatsOnActivity.focusInfo);
        eventDesc.setText(WhatsOnActivity.focusDesc);

        webEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventActivity.this, WhatsOnWebActivity.class));
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            startActivity(new Intent(EventActivity.this, AccountActivity.class));
        } else if (id == R.id.nav_schedule) {
            startActivity(new Intent(EventActivity.this, ScheduleActivity.class));
        } else if (id == R.id.nav_email) {
            startActivity(new Intent(EventActivity.this, EmailActivity.class));
        } else if (id == R.id.nav_tools) {
            startActivity(new Intent(EventActivity.this, ToolsActivity.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(EventActivity.this, LoginActivity.class));
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

