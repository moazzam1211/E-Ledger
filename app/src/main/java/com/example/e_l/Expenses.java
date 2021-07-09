package com.example.e_l;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Expenses extends AppCompatActivity {
   DatabaseReference refer;
   FloatingActionButton addExp;
    ArrayList<String> srNo, expT,expDp,expDt,expA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_expenses);
        addExp = findViewById(R.id.addExp);
        refer = FirebaseDatabase.getInstance().getReference();
        srNo=new ArrayList<>();
        expT = new ArrayList<>();
        expDp = new ArrayList<>();
        expDp = new ArrayList<>();
        expDt = new ArrayList<>();
        expA = new ArrayList<>();
        RecyclerView expenseLists = (RecyclerView) findViewById(R.id.expenseList);
        expenseLists.setLayoutManager(new LinearLayoutManager(this));
        refer.keepSynced(true);
        refer.child("User").child("Ledger").child("Expenses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    try {
                        String srNoo = "";
                        srNo.add(srNoo);
                       // Log.d("Hutya",ds.getValue().toString());
                        String exptt = ds.child("Expense Title").getValue().toString();
                        expT.add(exptt);
                        String expdp = ds.child("Expense Description").getValue().toString();
                        expDp.add(expdp);
                        String expdt = ds.child("Expense Date").getValue().toString();
                        expDt.add(expdt);
                        String expat = ds.child("Expense Amount").getValue().toString();
                        expA.add(expat);
                        expenseLists.setAdapter(new ExpenseAdapter(srNo,expT,expDp,expDt,expA));

                        Log.d("nmn",ds.getValue().toString());
                    }
                    catch (Exception e){
                        Log.d("Taky error pata chaly",e.toString());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Expenses.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        addExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Expenses.this, AddExpense.class);
                startActivity(i);
            }
        });
    }
}
