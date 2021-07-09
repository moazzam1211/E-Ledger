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

public class SalesOrders extends AppCompatActivity {
     DatabaseReference refer;
    ArrayList<String> srNo,dateOfPurchase,prodName,total,prodQty,prodPrice,salesType,cusName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sales_orders);
        refer = FirebaseDatabase.getInstance().getReference();
        srNo=new ArrayList<>();
        dateOfPurchase=new ArrayList<>();
        prodName=new ArrayList<>();
        prodQty=new ArrayList<>();
        prodPrice=new ArrayList<>();
        salesType=new ArrayList<>();
        cusName=new ArrayList<>();
        total=new ArrayList<>();
        RecyclerView salesLists = (RecyclerView) findViewById(R.id.salesEntryList);
        salesLists.setLayoutManager(new LinearLayoutManager(this));
        refer.keepSynced(true);
        refer.child("User").child("Inventory").child("Sales Entry").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    try {
                        String srNoo = "";
                        srNo.add(srNoo);
                        Log.d("Hutya",ds.getValue().toString());
                        String datee = ds.child("Sales Date").getValue().toString();
                        dateOfPurchase.add(datee);
                        String name = ds.child("Product Name").getValue().toString();
                        prodName.add(name);
                        String qtys = ds.child("Sales Quantity").getValue().toString();
                        prodQty.add(qtys);
                        String pPrice = ds.child("Sales Price").getValue().toString();
                        prodPrice.add(pPrice);
                        String totalAmout = ds.child("Total Stock").getValue().toString();
                        total.add(totalAmout);
                        String pType = ds.child("Payment Type").getValue().toString();
                        salesType.add(pType);
                        String venName = ds.child("Customer Name").getValue().toString();
                        cusName.add(venName);
                        salesLists.setAdapter(new PurchaseEntryAdapter(srNo,dateOfPurchase,prodName,
                                prodQty,prodPrice,total,
                                salesType,cusName));

                        Log.d("l",datee.toString());
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
