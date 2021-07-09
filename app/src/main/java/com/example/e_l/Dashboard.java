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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    CardView inven, ledger, payments, invoice, vendor, customer;
    //ImageView menu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dashboard);

        inven = findViewById(R.id.InvenI);
        ledger = findViewById(R.id.LedgerI);
        payments = findViewById(R.id.PaymentI);
        invoice = findViewById(R.id.InvoiceI);
        vendor = findViewById(R.id.VendorI);
        customer = findViewById(R.id.CustI);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
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




        inven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this,Inventory.class);
                startActivity(i);
                Toast.makeText(Dashboard.this, "Inventory", Toast.LENGTH_SHORT).show();
            }
        });
        ledger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this,Ledger.class);
                startActivity(i);
                Toast.makeText(Dashboard.this, "Ledger", Toast.LENGTH_SHORT).show();
            }
        });
        payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Payments.class);
                startActivity(i);
                Toast.makeText(Dashboard.this, "Payments", Toast.LENGTH_SHORT).show();
            }
        });
        invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this,Invoices.class);
                startActivity(i);
                Toast.makeText(Dashboard.this, "Invoices", Toast.LENGTH_SHORT).show();
            }
        });
        vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Vendors.class);
                startActivity(i);
                Toast.makeText(Dashboard.this, "Vendors", Toast.LENGTH_SHORT).show();
            }
        });
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Customers.class);
                startActivity(i);
                Toast.makeText(Dashboard.this, "Customers", Toast.LENGTH_SHORT).show();
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
                break;
            case R.id.inven:
                Intent i = new Intent(Dashboard.this, Inventory.class);
                startActivity(i);
                break;
            case R.id.ledg:
                Intent i2 = new Intent(Dashboard.this, Ledger.class);
                startActivity(i2);
                break;
            case R.id.pay:
                Intent i3 = new Intent(Dashboard.this, Payments.class);
                startActivity(i3);
                break;
            case R.id.invo:
                Intent i4 = new Intent(Dashboard.this, Invoices.class);
                startActivity(i4);
                break;
            case R.id.vend:
                Intent i5 = new Intent(Dashboard.this, Vendors.class);
                startActivity(i5);
                break;
            case R.id.cust:
                Intent i6 = new Intent(Dashboard.this, Customers.class);
                startActivity(i6);
                break;
            case R.id.logout:
                Intent i7 = new Intent(Dashboard.this, MainActivity.class);
                startActivity(i7);
                Toast.makeText(this, "Logged Out!", Toast.LENGTH_SHORT).show();
                finish();
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
