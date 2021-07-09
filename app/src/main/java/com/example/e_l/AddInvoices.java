package com.example.e_l;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddInvoices extends AppCompatActivity {
Spinner productSpinner , qtySpinner, customerSpinner;
Button generateInvoice;
DatabaseReference ref;
ValueEventListener listener;
ValueEventListener listener2;
ValueEventListener listener3;
ArrayList<String> list1;
ArrayList<String> list2;
ArrayList<String> list3;

ArrayAdapter<String> adapter;
ArrayAdapter<String> adapter2;
ArrayAdapter<String> adapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_invoices);
        productSpinner = findViewById(R.id.selectPro);
        qtySpinner = findViewById(R.id.selectQty);
        customerSpinner = findViewById(R.id.selectCus);
        generateInvoice = findViewById(R.id.addIn);
        ref = FirebaseDatabase.getInstance().getReference();
        SpinnerData();
generateInvoice.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
   SpinnerData();
    }
});


    }
    public void SpinnerData(){
        list1= new ArrayList<String>();
        adapter=  new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list1);

        list2= new ArrayList<String>();
        adapter2=  new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list2);

        list3= new ArrayList<String>();
        adapter3=  new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list3);

        listener=ref.child("User").child("Inventory").child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    String pName = ds.child("Product Name").getValue().toString();
                    list1.add(pName);
                    adapter.notifyDataSetChanged();
                    productSpinner.setAdapter(adapter);
                    if (productSpinner.getSelectedItem()==snapshot.child("Product Name").getChildren()){
                        String pQtys = ds.child("Product Qty").getValue().toString();
                        list2.add(pQtys);
                        adapter2.notifyDataSetChanged();
                        qtySpinner.setAdapter(adapter2);
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listener3= ref.child("User").child("Customers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    String cName = ds.child("Name").getValue().toString();
                    list3.add(cName);
                    adapter3.notifyDataSetChanged();
                    customerSpinner.setAdapter(adapter3);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
