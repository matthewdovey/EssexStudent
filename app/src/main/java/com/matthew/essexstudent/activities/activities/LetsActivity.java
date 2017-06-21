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
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.matthew.essexstudent.R;

/**
 * Created by matthew on 19/12/15.
 */
public class LetsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public WebView letsWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        letsWebView = (WebView) findViewById(R.id.webViewLets);

        WebSettings webSettings = letsWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String sendTo = "http://www.essexstudent.com/sulets";

        letsWebView.loadUrl(sendTo);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            startActivity(new Intent(LetsActivity.this, AccountActivity.class));
        } else if (id == R.id.nav_schedule) {
            startActivity(new Intent(LetsActivity.this, ScheduleActivity.class));
        } else if (id == R.id.nav_email) {
            startActivity(new Intent(LetsActivity.this, EmailActivity.class));
        } else if (id == R.id.nav_tools) {
            startActivity(new Intent(LetsActivity.this, ToolsActivity.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(LetsActivity.this, LoginActivity.class));
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
