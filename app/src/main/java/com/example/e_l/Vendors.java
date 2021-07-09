package com.example.e_l;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Vendors extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    CardView addVendorsDetails, checkVendorsDetails;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_vendors);
        addVendorsDetails = findViewById(R.id.addVendor);
        checkVendorsDetails = findViewById(R.id.checkVendors);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view4);
        toolbar = findViewById(R.id.Menu);

//        Menu menu =navigationView.getMenu();
//        menu.findItem(R.id.logout).setVisible(false);
//        menu.findItem(R.id.editprofile).setVisible(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.dashboard);
        addVendorsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Vendors.this, AddVendors.class);
                startActivity(i);
                Toast.makeText(Vendors.this, "Add Vendor Details", Toast.LENGTH_LONG).show();
            }
        });
        checkVendorsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Vendors.this, ShowVendors.class);
                startActivity(i);
                Toast.makeText(Vendors.this, "Check Vendors Details", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.dashboard:
                Intent i = new Intent(Vendors.this, Dashboard.class);
                startActivity(i);
                break;
            case R.id.inven:
                Intent i4 = new Intent(Vendors.this, Inventory.class);
                startActivity(i4);
                break;
            case R.id.ledg:
                Intent i2 = new Intent(Vendors.this, Ledger.class);
                startActivity(i2);
                break;
            case R.id.pay:
                Intent i3 = new Intent(Vendors.this, Payments.class);
                startActivity(i3);
                break;
            case R.id.invo:
                Intent i5 = new Intent(Vendors.this, Invoices.class);
                startActivity(i5);
                break;
            case R.id.vend:
                break;
            case R.id.cust:
                Intent i6 = new Intent(Vendors.this, Customers.class);
                startActivity(i6);
                break;
            case R.id.logout:
                Intent i7 = new Intent(Vendors.this, MainActivity.class);
                startActivity(i7);
                Toast.makeText(this, "Logged Out!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
