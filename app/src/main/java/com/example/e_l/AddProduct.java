package com.example.e_l;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class AddProduct extends AppCompatActivity {
    EditText productName, productCategory, productQty, productDescription, productColor, qtyAlert;
    TextView barcodeNumber;
    CardView scanCode;
    CheckBox alert;
    Button addProduct;

    DatabaseReference reference;
    public static TextView resulttextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_add_product);
        reference = FirebaseDatabase.getInstance().getReference();
        productName = findViewById(R.id.proName);
        productCategory = findViewById(R.id.proCategory);
        productQty = findViewById(R.id.proQty);
        productDescription = findViewById(R.id.description);
        productColor = findViewById(R.id.color);
        barcodeNumber = findViewById(R.id.barcode);
        resulttextview = findViewById(R.id.barcode);
        scanCode = findViewById(R.id.scanCode);
        qtyAlert = findViewById(R.id.qtyAlert);

        alert =  (CheckBox) findViewById(R.id.alert);
        addProduct = findViewById(R.id.addPr);
            if(!alert.isChecked()){
            qtyAlert.setVisibility(View.VISIBLE);
            }
        scanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanBarcode.class));
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameValue = productName.getText().toString();
                String categoryValue = productCategory.getText().toString();
                String qtyValue = productQty.getText().toString();
                String descriptionValue = productDescription.getText().toString();
                String colorValue = productColor.getText().toString();
                String alertValue = alert.getText().toString();
                String barcodeValue = barcodeNumber.getText().toString();

                if (nameValue.isEmpty())
                {
                    productName.setError("Empty Field!");
                    productName.requestFocus();
                    return;
                }
                if (categoryValue.isEmpty())
                {
                    productCategory.setError("Empty Field!");
                    productCategory.requestFocus();
                    return;
                }
                if (qtyValue.isEmpty())
                {
                    productQty.setError("Empty Field!");
                    productQty.requestFocus();
                    return;
                }
                if (descriptionValue.isEmpty())
                {
                    productDescription.setError("Empty Field!");
                    productDescription.requestFocus();
                    return;
                }
                if (colorValue.isEmpty())
                {
                    productColor.setError("Empty Field!");
                    productColor.requestFocus();
                    return;
                }
              /*  if (alertValue.isEmpty())
                {
                    alert.setError("Empty Field!");
                    alert.requestFocus();
                    return;
                }*/
               /* if (barcodeValue.isEmpty())
                {
                    barcodeNumber.setError("Empty Field!");
                    barcodeNumber.requestFocus();
                    return;
                }*/
                if(!TextUtils.isEmpty(nameValue)&&!TextUtils.isEmpty(categoryValue)
                        &&!TextUtils.isEmpty(qtyValue)&&!TextUtils.isEmpty(descriptionValue)&&!TextUtils.isEmpty(colorValue)
                        &&!TextUtils.isEmpty(alertValue))
                {
                    //Product product = new Product(nameValue,categoryValue,qtyValue,barcodeValue);
                    productName.setText("");
                    productCategory.setText("");
                    productQty.setText("");
                    productDescription.setText("");
                    productColor.setText("");
                    barcodeNumber.setText("");
                    Map<String , Object> map = new HashMap<String , Object>();
                    map.put("Product Name" , nameValue);
                    map.put("Product Category" , categoryValue);

                    map.put("Product Qty" , qtyValue);
                    map.put("Product Description" , descriptionValue);

                    map.put("Product Color" , colorValue);
                    map.put("Product Barcode" , barcodeValue);

                    reference.child("User").child("Inventory").child("Products").push().setValue(map);
//
//                    reference.child("User").child("Inventory").child("Products").child(nameValue).child("Product Name").setValue(nameValue);
//                    reference.child("User").child("Inventory").child("Products").child(nameValue).child("Product Category").setValue(categoryValue);
//                    reference.child("User").child("Inventory").child("Products").child(nameValue).child("Product Qty").setValue(qtyValue);
//                    reference.child("User").child("Inventory").child("Products").child(nameValue).child("Product Description").setValue(descriptionValue);
//                    reference.child("User").child("Inventory").child("Products").child(nameValue).child("Product Color").setValue(colorValue);
//                    reference.child("User").child("Inventory").child("Products").child(nameValue).child("Product Barcode").setValue(barcodeValue);

                    //Toast.makeText(AddProduct.this,nameValue+" Added",Toast.LENGTH_SHORT).show();
                    showDailog();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please Fill all the fields",Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
    void showDailog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_layout2,null);
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
