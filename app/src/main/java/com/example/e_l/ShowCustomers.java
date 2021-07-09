package com.example.e_l;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowCustomers extends AppCompatActivity {
    private DatabaseReference reference;
    ArrayList<String> namear,comar,addar,emailar,phonear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_show_customers);
        namear=new ArrayList<>();
        comar=new ArrayList<>();
        addar=new ArrayList<>();
        emailar=new ArrayList<>();
        phonear=new ArrayList<>();

        RecyclerView customerList = (RecyclerView) findViewById(R.id.customersList);
        customerList.setLayoutManager(new LinearLayoutManager(this));
        reference = FirebaseDatabase.getInstance().getReference();
        reference.keepSynced(true);
        reference.child("User").child("Customers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    try {
                        Log.d("Hutya",ds.getValue().toString());
                        String namee = ds.child("Name").getValue().toString();
                        namear.add(namee);
                        String comp = ds.child("Company").getValue().toString();
                        comar.add(comp);
                        String addresss = ds.child("Address").getValue().toString();
                        addar.add(addresss);
                        String emaill = ds.child("Email").getValue().toString();
                        emailar.add(emaill);
                        String number = ds.getKey();
                        phonear.add(number);
                        customerList.setAdapter(new VendorsListAdapter(namear,comar,addar,emailar,phonear));

                        Log.d("kutaa",namear.toString());
                    }
                    catch (Exception e){
                        Log.d("Taky error pata chaly",e.toString());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
