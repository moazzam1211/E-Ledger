package com.example.e_l;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class AddPaymentDetails extends AppCompatActivity {
    private DatabaseReference refer;
    ArrayList<String> date,total,prodPrice,vendName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_add_payment_details);
        refer = FirebaseDatabase.getInstance().getReference();
      date=new ArrayList<>();

        prodPrice=new ArrayList<>();
        vendName=new ArrayList<>();
        total=new ArrayList<>();
        RecyclerView debitPayments = (RecyclerView) findViewById(R.id.debitList);
        debitPayments.setLayoutManager(new LinearLayoutManager(this));

        refer.keepSynced(true);
        refer.child("User").child("Inventory").child("Purchase Entry").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        try {

                            Log.d("Hutya", ds.getValue().toString());
                            String datee = ds.child("Purchase Date").getValue().toString();
                            date.add(datee);
                            String pPrice = ds.child("Purchase Price").getValue().toString();
                            prodPrice.add(pPrice);
                            String totalAmout = ds.child("Totals").getValue().toString();
                            total.add(totalAmout);
                            String venName = ds.child("V Name").getValue().toString();
                            vendName.add(venName);
                            debitPayments.setAdapter(new DebitAdapter(date, prodPrice, total, vendName));

                            Log.d("l", datee.toString());
                        } catch (Exception e) {
                            Log.d("Taky error pata chaly", e.toString());
                        }

                    }
               
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
