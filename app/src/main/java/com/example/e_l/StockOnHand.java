package com.example.e_l;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class StockOnHand extends AppCompatActivity {
    Button check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_stock_on_hand);
        check = findViewById(R.id.checkStock);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(StockOnHand.this, ShowStocksOnHand.class);
                startActivity(i);
                Toast.makeText(StockOnHand.this, "Stocks on Hand", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
