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

public class Customers extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    CardView addCustomerDetails, checkCustomersDetail;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_customers);
        addCustomerDetails = findViewById(R.id.addCustomer);
        checkCustomersDetail = findViewById(R.id.checkCustomers);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view5);
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

        addCustomerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Customers.this, AddCustomers.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Add Customer Details", Toast.LENGTH_LONG).show();
            }
        });
        checkCustomersDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Customers.this, ShowCustomers.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Check Customers Details", Toast.LENGTH_LONG).show();
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
                Intent i = new Intent(Customers.this, Dashboard.class);
                startActivity(i);
                break;
            case R.id.inven:
                Intent i4 = new Intent(Customers.this, Inventory.class);
                startActivity(i4);
                break;
            case R.id.ledg:
                Intent i2 = new Intent(Customers.this, Ledger.class);
                startActivity(i2);
                break;
            case R.id.pay:
                Intent i3 = new Intent(Customers.this, Payments.class);
                startActivity(i3);
                break;
            case R.id.invo:
                Intent i5 = new Intent(Customers.this, Invoices.class);
                startActivity(i5);
                break;
            case R.id.vend:
                Intent i6 = new Intent(Customers.this, Vendors.class);
                startActivity(i6);
                break;
            case R.id.cust:
                break;
            case R.id.logout:
                Intent i7 = new Intent(Customers.this, MainActivity.class);
                startActivity(i7);
                Toast.makeText(this, "Logged Out!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
