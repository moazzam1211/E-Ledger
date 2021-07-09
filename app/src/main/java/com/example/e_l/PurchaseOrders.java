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

public class PurchaseOrders extends AppCompatActivity {
    private DatabaseReference refer;
    ArrayList<String> srNo,dateOfPurchase,prodName,total,prodQty,prodPrice,purchaseType,vendName;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_purchase_orders);
        refer = FirebaseDatabase.getInstance().getReference();
        srNo=new ArrayList<>();
        dateOfPurchase=new ArrayList<>();
        prodName=new ArrayList<>();
        prodQty=new ArrayList<>();
        prodPrice=new ArrayList<>();
        purchaseType=new ArrayList<>();
        vendName=new ArrayList<>();
        total=new ArrayList<>();
        RecyclerView purchaseLists = (RecyclerView) findViewById(R.id.purchaseEntryList);
        purchaseLists.setLayoutManager(new LinearLayoutManager(this));
        refer.keepSynced(true);
        refer.child("User").child("Inventory").child("Purchase Entry").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    try {
                        String srNoo = "";
                        srNo.add(srNoo);
                        Log.d("Hutya",ds.getValue().toString());
                        String datee = ds.child("Purchase Date").getValue().toString();
                        dateOfPurchase.add(datee);
                        String name = ds.child("Product Name").getValue().toString();
                        prodName.add(name);
                        String qtys = ds.child("Product Quantity").getValue().toString();
                        prodQty.add(qtys);
                        String pPrice = ds.child("Purchase Price").getValue().toString();
                        prodPrice.add(pPrice); 
                        String totalAmout = ds.child("Totals").getValue().toString();
                        total.add(totalAmout); 
                        String pType = ds.child("Payment Type").getValue().toString();
                        purchaseType.add(pType); 
                        String venName = ds.child("V Name").getValue().toString();
                        vendName.add(venName);
                        purchaseLists.setAdapter(new PurchaseEntryAdapter(srNo,dateOfPurchase,prodName,
                                prodQty,prodPrice,total,
                                purchaseType,vendName));

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
