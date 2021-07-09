package com.example.e_l;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class AddExpense extends AppCompatActivity {
    EditText expenseT, expenseD, expenseDt, expenseA;
    DatabaseReference refer;
    public FloatingActionButton addE;
    DatePickerDialog.OnDateSetListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_add_expense);
        expenseT =  findViewById(R.id.expenseTitle);
        expenseD = findViewById(R.id.expenseDescrip);
        expenseDt = findViewById(R.id.expenseDate);
        expenseA = findViewById(R.id.expenseAmount);
        addE =  findViewById(R.id.addEx);
        refer = FirebaseDatabase.getInstance().getReference();
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        expenseDt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddExpense.this, (view, year1, month1, dayOfMonth) -> {
                    month1 = month1 +1;
                    String datee = day+"/"+ month1 +"/"+ year1;
                    expenseDt.setText(datee);
                },year,month,day);
                datePickerDialog.show();
            }
        });
        addE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exTitle = expenseT.getText().toString();
                String exDescrip = expenseD.getText().toString();
                String exDate = expenseDt.getText().toString();
                String exAmount = expenseA.getText().toString();

                if (exTitle.isEmpty()){
                    expenseT.setError("Field cannot be Empty!");
                    expenseT.requestFocus();
                    return;
                }
                if (exDescrip.isEmpty()){
                    expenseD.setError("Field cannot be Empty!");
                    expenseD.requestFocus();
                    return;
                }
                if (exDate.isEmpty()){
                    expenseDt.setError("Field cannot be Empty!");
                    expenseDt.requestFocus();
                    return;
                }
                if (exAmount.isEmpty()){
                    expenseA.setError("Field cannot be Empty!");
                    expenseA.requestFocus();
                    return;
                }

//                Map<String,Object> maps = new HashMap<String, Object>();
//                maps.put("Product Name",exTitle);
//                maps.put("Product Quantity",exDescrip);
//                maps.put("Purchase Date",exDate);
//                maps.put("Purchase Price","Rs "+exAmount);
//
//                refer.child("User").child("Ledger").child("Expenses").push().setValue(maps);
                expenseT.setText("");
                expenseD.setText("");
                expenseDt.setText("");
                expenseA.setText("");
                refer.child("User").child("Ledger").child("Expenses").child(exTitle).child("Expense Title").setValue(exTitle);
                refer.child("User").child("Ledger").child("Expenses").child(exTitle).child("Expense Description").setValue(exDescrip);
                refer.child("User").child("Ledger").child("Expenses").child(exTitle).child("Expense Date").setValue(exDate);
                refer.child("User").child("Ledger").child("Expenses").child(exTitle).child("Expense Amount").setValue("Rs "+exAmount);


                showDailog();
               // Toast.makeText(AddExpense.this, "Expense Added!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    void showDailog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_layout3,null);
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
