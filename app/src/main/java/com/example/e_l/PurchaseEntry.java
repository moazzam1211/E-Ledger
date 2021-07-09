package com.example.e_l;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class PurchaseEntry extends AppCompatActivity {
     EditText productQty;
     AutoCompleteTextView vendorName;
     Spinner selectProduct, selectVendor,selectPayType;
         static String result;
     EditText purchasePrice, purchaseDate;
     ImageView searchBarcodes;
    String total;
    DatePickerDialog.OnDateSetListener listener;
  ImageView scanCode, addPro, addVen;
  FloatingActionButton add;
   DatabaseReference ref;
    ArrayList<String> vendors;
    String proname,procate,proqty,prodes,proclr,probarCode;
    ArrayList<String> paymentType;

    String [] types = new String[]{"Cash", "Debit"};
    ValueEventListener listener1;
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

        setContentView(R.layout.activity_purchase_entry);
        selectProduct = findViewById(R.id.selectPro);
        selectVendor = findViewById(R.id.selectVend);
        selectPayType = findViewById(R.id.selectPayType);
        productQty = findViewById(R.id.productQty);
        purchaseDate = findViewById(R.id.purchaseDate);
        purchasePrice = findViewById(R.id.purchasePrice);
        scanCode = findViewById(R.id.scanSearch);
        addPro = findViewById(R.id.addPro);
        list1= new ArrayList<String>();
        adapter=  new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list1);
        selectProduct.setAdapter(adapter);
        list2= new ArrayList<String>();
        adapter2=  new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list2);
        selectVendor.setAdapter(adapter2);
        list3= new ArrayList<String>();
        adapter3=  new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,types);
        selectPayType.setAdapter(adapter3);
        searchBarcodes = findViewById(R.id.scanSearch);

        add = findViewById(R.id.addP);
        addVen = findViewById(R.id.addVend);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        purchaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PurchaseEntry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month =month+1;
                        String datee = day+"/"+month+"/"+year;
                        purchaseDate.setText(datee);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        ref = FirebaseDatabase.getInstance().getReference();
        listener1=ref.child("User").child("Inventory").child("Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String pName = ds.child("Product Name").getValue().toString();
                    list1.add(pName);
                    adapter.notifyDataSetChanged();
//                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,productNames);
//                                productName.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listener2=ref.child("User").child("Vendors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {

                    String vName = ds.child("Name").getValue().toString();
                    list2.add(vName);
                    adapter2.notifyDataSetChanged();
//                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,productNames);
//                                productName.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        addPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PurchaseEntry.this, AddProduct.class);
                startActivity(i);
            }
        });
        searchBarcodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchBarcode.class));

//                ref.child("User").child("Inventory").child("Products").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot ds : snapshot.getChildren()) {
//                            if (result.equals(ds.child("Product Barcode").getValue().toString())) {
//                                Log.d("Alpha*", ds.child("Product Name").getValue().toString());
//                                String pName = ds.child("Product Name").getValue().toString();
//                                //String pQtys = snapshot.child("Product Qty").getValue().toString();
//                                productName.setText(pName);
//                            } else {
//                                productName.setError("Product is not in ProductList/nclick on plus sign to add product!");
//                                productName.requestFocus();
//                                return;
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
           }
        });
        addVen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PurchaseEntry.this, AddVendors.class);
                startActivity(i);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String proNameValue = selectProduct.getSelectedItem().toString();
                int proQtyValue = Integer.parseInt(productQty.getText().toString());
                String dateValue = purchaseDate.getText().toString();
                int priceValue = Integer.parseInt(purchasePrice.getText().toString());
                int totals =  priceValue*proQtyValue;
                String typeValue = selectPayType.getSelectedItem().toString();
                String vendNamValue = selectVendor.getSelectedItem().toString();



             if (dateValue.isEmpty()){
                 purchaseDate.setError("Field is Empty!");
                 purchaseDate.requestFocus();
                 return;
             }
             if (vendNamValue.isEmpty()){
                 vendorName.setError("Field is Empty!");
                 vendorName.requestFocus();
                 return;
             }



                productQty.setText("");
                purchaseDate.setText("");
                purchasePrice.setText("");
                vendorName.setText("");
//
//                Map<String,Object> maps = new HashMap<String, Object>();
//                maps.put("Product Name",proNameValue);
//                maps.put("Product Quantity",proQtyValue);
//                maps.put("Purchase Date",dateValue);
//                maps.put("Purchase Price","Rs "+priceValue);
//
//                maps.put("Total Stock","Rs "+totals);
//                maps.put("Payment Type",typeValue);
//                maps.put("V Name",vendNamValue);
//                ref.child("User").child("Inventory").child("Purchase Entry").push().setValue(maps);

                ref.child("User").child("Inventory").child("Purchase Entry").child(proNameValue).child("Product Name").setValue(proNameValue);
                ref.child("User").child("Inventory").child("Purchase Entry").child(proNameValue).child("Product Quantity").setValue(proQtyValue);
                ref.child("User").child("Inventory").child("Purchase Entry").child(proNameValue).child("Purchase Date").setValue(dateValue);
                ref.child("User").child("Inventory").child("Purchase Entry").child(proNameValue).child("Purchase Price").setValue("Rs "+priceValue);
                ref.child("User").child("Inventory").child("Purchase Entry").child(proNameValue).child("Totals").setValue("Rs "+totals);
                ref.child("User").child("Inventory").child("Purchase Entry").child(proNameValue).child("Payment Type").setValue(typeValue);
                ref.child("User").child("Inventory").child("Purchase Entry").child(proNameValue).child("V Name").setValue(vendNamValue);

               // Toast.makeText(PurchaseEntry.this, "Entry Added!", Toast.LENGTH_SHORT).show();
                showDailog();



            }
        });



    }

//    public void resultget(String resul2t){
//        result=resul2t;
//        Toast.makeText(this,resul2t+"abc",Toast.LENGTH_LONG).show();
//        ref.child("User").child("Inventory").child("Products").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    if (result.equals(ds.child("Product Barcode").getValue().toString())) {
//                        Log.d("Alpha*", ds.child("Product Name").getValue().toString());
//                        String pName = ds.child("Product Name").getValue().toString();
//                        //String pQtys = snapshot.child("Product Qty").getValue().toString();
//                        productName.setText(pName);
//                    } else {
//                        productName.setError("Product is not in ProductList/nclick on plus sign to add product!");
//                        productName.requestFocus();
//                        return;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
    void showDailog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_layout1,null);
        Button add = view.findViewById(R.id.add);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).setView(view).create();
        alertDialog.show();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: " );
                alertDialog.dismiss();
            }
        });
    }
}

