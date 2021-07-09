package com.example.e_l;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProductList extends AppCompatActivity {
     DatabaseReference refer;
    ArrayList<String> proname,procate,proqty,prodes,proclr,probarCode;
    FloatingActionButton addProduct;
    private TextView totalnostock, totalnoofsum;
    private int counttotalnoofstock =0;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_product_list);
        refer = FirebaseDatabase.getInstance().getReference();
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        proname = new ArrayList<>();
        procate = new ArrayList<>();
        proqty = new ArrayList<>();
        prodes = new ArrayList<>();
        proclr = new ArrayList<>();
        probarCode = new ArrayList<>();
        addProduct = findViewById(R.id.addProducts);
        totalnostock= findViewById(R.id.totalnostock);
        totalnoofsum = findViewById(R.id.totalsum);
        RecyclerView productLists = (RecyclerView) findViewById(R.id.productlistView);
        productLists.setLayoutManager(new LinearLayoutManager(this));
        refer.keepSynced(true);

       refer.child("User").child("Inventory").child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("Product Qty").exists()) {
                    counttotalnoofstock = (int) snapshot.getChildrenCount();
                    totalnostock.setText(Integer.toString(counttotalnoofstock));
                    Log.d("TAG", "" +totalnostock);
                } else {
                    totalnostock.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        refer.child("User").child("Inventory").child("Purchase Entry").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("Totals");
                    price= price.toString().replace("Rs ","");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum += pValue;
                    totalnoofsum.setText(String.valueOf(sum));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refer.child("User").child("Inventory").child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    try {


                        //Log.d("Hutya",ds.getValue().toString());
                        Log.d("Alpha*",ds.child("Product Name").getValue().toString());
                        String pName = ds.child("Product Name").getValue().toString();
                        proname.add(pName);
                        String pCat = ds.child("Product Category").getValue().toString();
                        procate.add(pCat);
                        String pQtys = ds.child("Product Qty").getValue().toString();
                        proqty.add(pQtys);
                        String pDescr = ds.child("Product Description").getValue().toString();
                        prodes.add(pDescr);
                        String pClr = ds.child("Product Color").getValue().toString();
                        proclr.add(pClr);
                      String pBarCode = ds.child("Product Barcode").getValue().toString();
                       probarCode.add(pBarCode);

                        productLists.setAdapter(new ProductListAdapter(proname,procate,proqty,prodes,proclr,probarCode));
                    }
                    catch (Exception e){
                        Log.d("Taky error pata chaly",e.toString());
                    }
//                    SharedPreferences.Editor prodeditor = prefs.edit();
//                    Set<String> set = new HashSet<String>();
//                    Set<String> set1 = new HashSet<String>();
//                    Set<String> set2 = new HashSet<String>();
//                    Set<String> set3 = new HashSet<String>();
//                    Set<String> set4 = new HashSet<String>();
//                    Set<String> set5 = new HashSet<String>();
//                    set.addAll(proname);
//                    set1.addAll(procate);
//                    set2.addAll(proqty);
//                    set3.addAll(prodes);
//                    set4.addAll(proclr);
//                    set5.addAll(probarCode);
//
//
//                    prodeditor.putStringSet("key", set);
//                    Log.d("akre","abc"+set.toString());
//                    prodeditor.putStringSet("key1", set1);
//                    prodeditor.putStringSet("key2", set2);
//                    prodeditor.putStringSet("key3", set3);
//                    prodeditor.putStringSet("key4", set4);
//                    prodeditor.putStringSet("key5", set5);
//                    prodeditor.commit();
//                    prodeditor.apply();


                }

               // adapter();
               // productLists.setAdapter(new ProductListAdapter(proname,procate,proqty,prodes,proclr,probarCode));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProductList.this, AddProduct.class);
                startActivity(i);
                Toast.makeText(ProductList.this, "Add New Product", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void adapter() {
        Set<String> set = prefs.getStringSet("key",null);
        proname.addAll(set);
//        set1.addAll(procate);
//        set2.addAll(proqty);
//        set3.addAll(prodes);
//        set4.addAll(proclr);
//        set5.addAll(probarCode);
        Set<String> set1 = prefs.getStringSet("key1",null);
        procate.addAll(set1);
        Log.d("akrebakr",proname.toString()+set1.toString());

    }
}
