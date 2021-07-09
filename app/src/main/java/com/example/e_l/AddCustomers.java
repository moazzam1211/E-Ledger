package com.example.e_l;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

public class AddCustomers extends AppCompatActivity {
    EditText name, company, address, phoneNo, email;
    FloatingActionButton save;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_add_customers);
        name = findViewById(R.id.customerName);
        company= findViewById(R.id.customerCompany);
        address = findViewById(R.id.customerAddress);
        email = findViewById(R.id.customerEmail);
        phoneNo = findViewById(R.id.customerPhone);

        save = findViewById(R.id.addCustomer);
        reference = FirebaseDatabase.getInstance().getReference();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameValue = name.getText().toString();
                String companyValue = company.getText().toString();
                String addressValue = address.getText().toString();
                String emailValue = email.getText().toString();
                String phoneNovalue = phoneNo.getText().toString();


                name.setText("");
                company.setText("");
                address.setText("");
                email.setText("");
                phoneNo.setText("");


                reference.child("User").child("Customers").child(phoneNovalue).child("Name").setValue(nameValue);
                reference.child("User").child("Customers").child(phoneNovalue).child("Company").setValue(companyValue);
                reference.child("User").child("Customers").child(phoneNovalue).child("Address").setValue(addressValue);
                reference.child("User").child("Customers").child(phoneNovalue).child("Email").setValue(emailValue);
                reference.child("User").child("Customers").child(phoneNovalue).child("Phone No").setValue(phoneNovalue);

                if (nameValue.isEmpty())
                {
                    name.setError("Field cannot be Empty!");
                    name.requestFocus();
                    return;
                }
                if (companyValue.isEmpty())
                {
                    company.setError("Field cannot be Empty!");
                    company.requestFocus();
                    return;
                }
                if (addressValue.isEmpty())
                {
                    address.setError("Field cannot be Empty!");
                    address.requestFocus();
                    return;
                }
                if (emailValue.isEmpty())
                {
                    email.setError("Field cannot be Empty!");
                    email.requestFocus();
                    return;
                }
                if (phoneNovalue.isEmpty())
                {
                    phoneNo.setError("Field cannot be Empty!");
                    phoneNo.requestFocus();
                    return;
                }

                Toast.makeText(AddCustomers.this, nameValue+" is Added!", Toast.LENGTH_SHORT).show();
                showDailog();



            }
        });
    }
    void showDailog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_layout5,null);
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
