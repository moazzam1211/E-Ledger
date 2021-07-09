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

public class Ledger extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    CardView purchaseOrder, salesOrder, expenses,profitLoss;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_ledger);
        purchaseOrder = findViewById(R.id.purchaseOrder);
        salesOrder = findViewById(R.id.salesOrder);
        expenses = findViewById(R.id.expenses);
        profitLoss = findViewById(R.id.profitLoss);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view2);
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

        purchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Ledger.this,PurchaseOrders.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Purchase Order", Toast.LENGTH_SHORT).show();
            }
        });
        salesOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Ledger.this, SalesOrders.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Sales Order", Toast.LENGTH_SHORT).show();
            }
        });
        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Ledger.this, Expenses.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Expenses", Toast.LENGTH_SHORT).show();
            }
        });
       profitLoss.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(Ledger.this, ProfitLoss.class);
               startActivity(i);
               Toast.makeText(getApplicationContext(), "Profit and Loss", Toast.LENGTH_SHORT).show();
           }
       });
    }
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
                Intent i = new Intent(Ledger.this, Dashboard.class);
                startActivity(i);
                break;
            case R.id.inven:
                Intent i2 = new Intent(Ledger.this, Inventory.class);
                startActivity(i2);
                break;
            case R.id.ledg:
                break;
            case R.id.pay:
                Intent i3 = new Intent(Ledger.this, Payments.class);
                startActivity(i3);
                break;
            case R.id.invo:
                Intent i4 = new Intent(Ledger.this, Invoices.class);
                startActivity(i4);
                break;
            case R.id.vend:
                Intent i5 = new Intent(Ledger.this, Vendors.class);
                startActivity(i5);
                break;
            case R.id.cust:
                Intent i6 = new Intent(Ledger.this, Customers.class);
                startActivity(i6);
                break;
            case R.id.logout:
                Intent i7 = new Intent(Ledger.this, MainActivity.class);
                startActivity(i7);
                Toast.makeText(this, "Logged Out!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
