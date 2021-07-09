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
import java.util.Arrays;
import java.util.Map;

public class ProfitLoss extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<String> date,openingStocks,totalPurchase,expesnes,totalSales,stockOnhands,profitLosss;
int totalp=0;
int finalv=0;
int finalvalue =0;
int purchasePrice=0;
int salePrice=0;
int pQty=0;
int sQty=0;
int profits=0;
    String tp="";
    String sp="";
    String ts="";
    String ex="";
    String fv="";
    String op="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_profit_loss);
        ref = FirebaseDatabase.getInstance().getReference();
     date=new ArrayList<>();
        openingStocks=new ArrayList<>();
        totalPurchase=new ArrayList<>();
        expesnes=new ArrayList<>();
        totalSales=new ArrayList<>();
        stockOnhands=new ArrayList<>();
        profitLosss=new ArrayList<>();
        ref.keepSynced(true);
        int totals=0;
        RecyclerView profitLossLists = (RecyclerView) findViewById(R.id.profitLossList);
        profitLossLists.setLayoutManager(new LinearLayoutManager(this));
//        ref.child("User").child("Inventory").child("Purchase Entry").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot ds:snapshot.getChildren()){
//                    try {
//                        Log.d("Hutya1",ds.getValue().toString());
//                        String datee = ds.child("Purchase Date").getValue().toString();
//                        date.add(datee);
//                        int resultpQty=0;
//                        for (DataSnapshot dss : snapshot.getChildren()) {
//                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
//                            Object prices = map.get("Product Quantity");
//                            prices = prices.toString().replace("Rs ", "");
//                            int pValues = Integer.parseInt(String.valueOf(prices));
//                            resultpQty += pValues;
//                        }
//                        pQty=resultpQty;
//                        int resultP=0;
//                        for(DataSnapshot dss : snapshot.getChildren()) {
//                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
//                            Object prices = map.get("Purchase Price");
//                            prices = prices.toString().replace("Rs ", "");
//                            int pValues = Integer.parseInt(String.valueOf(prices));
//                            resultP += pValues;
//                        }
//                            purchasePrice=resultP;
//                        String totalPu = ds.child("Totals").getValue().toString();
//                        totalPurchase.add(totalPu.replace("Rs ",""));
//                        int result2=0;
//                        for(DataSnapshot dss : snapshot.getChildren()) {
//                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
//                            Object prices = map.get("Totals");
//                            prices = prices.toString().replace("Rs ", "");
//                            int pValues = Integer.parseInt(String.valueOf(prices));
//                            result2 += pValues;
//
//                        }
//                       totalp =result2;
//                       tp ="Rs "+resultP;
//                        totalPurchase.add(tp);
//                    }
//                    catch (Exception e){
//                        Log.d("Taky error pata chaly",e.toString());
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        ref.child("User").child("Inventory").child("Sales Entry").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot ds:snapshot.getChildren()){
//                    try {
//                        Log.d("Hutya2",ds.getValue().toString());
//                        String datee = ds.child("Sales Date").getValue().toString();
//                        date.add(datee);
//                        int resultsQty=0;
//                            for (DataSnapshot dss : snapshot.getChildren()) {
//                                Map<String, Object> map = (Map<String, Object>) ds.getValue();
//                                Object prices = map.get("Sales Quantity");
//                                prices = prices.toString().replace("Rs ", "");
//                                int pValues = Integer.parseInt(String.valueOf(prices));
//                                resultsQty += pValues;
//                            }
//                            sQty=pQty-resultsQty;
//
//                        String profit = ds.child("Sales Price").getValue().toString();
//                        openingStocks.add(profit.replace("Rs ",""));
//                        int resultS=0;
//                        for(DataSnapshot dss : snapshot.getChildren()){
//                            Map<String,Object> map = (Map<String, Object>) ds.getValue();
//                            Object price = map.get("Sales Price");
//                            price= price.toString().replace("Rs ","");
//                            int pValue = Integer.parseInt(String.valueOf(price));
//                            resultS += pValue;
//                        }
//                            salePrice=resultS-purchasePrice;
//                       sp ="Rs "+salePrice;
////                        String totalSl = ds.child("Total Stock").getValue().toString();
////                        totalSales.add(totalSl.replace("Rs ",""));
//
//                        int result=0;
//                        for(DataSnapshot dss : snapshot.getChildren()){
//                            Map<String,Object> map = (Map<String, Object>) ds.getValue();
//                            Object price = map.get("Total Stock");
//                            price= price.toString().replace("Rs ","");
//                            int pValue = Integer.parseInt(String.valueOf(price));
//                            result += pValue;
//                            //profitLosss = (String.valueOf(result));
//
//                        }finalv = totalp-result;
//                        ts = "Rs "+finalv;
//                        totalSales.add(ts);
//                        profits=sQty*salePrice;
//                        Log.d("aaa222",""+finalv);
//
//                    }
//                    catch (Exception e){
//                        Log.d("Taky error pata chaly",e.toString());
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        ref.child("User").child("Ledger").child("Expenses").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot ds:snapshot.getChildren()){
//                    try {
//
//                        Log.d("Hutya3",ds.getValue().toString());
//                        String datee = ds.child("Expense Date").getValue().toString();
//                        date.add(datee);
////                        String opening = ds.child("Product Name").getValue().toString();
////                        openingStocks.add(opening);
////                        String exAmount = ds.child("Expense Amount").getValue().toString();
////                        expesnes.add(exAmount);
//                        int result3=0;
//                        for(DataSnapshot dss : snapshot.getChildren()) {
//                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
//                            Object prices = map.get("Expense Amount");
//                            prices = prices.toString().replace("Rs ", "");
//                            int pValues = Integer.parseInt(String.valueOf(prices));
//                            result3 += pValues;
//
//                        }
//                        ex= "Rs "+result3;
//                        finalvalue = finalv-result3;
//                        fv="Rs "+finalvalue;
//                        int p = profits-result3;
//                        op = "Rs "+p;
//
//
//                    }
//                    catch (Exception e){
//                        Log.d("Taky error pata chaly",e.toString());
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        Log.d("aa",""+date+""+tp+""+ex+""+ts+""+fv+""+fv+""+op);
//
//        ref.child("User").child("Ledger").child("Profit n Loss").child("Date").setValue(date);
//        ref.child("User").child("Ledger").child("Profit n Loss").child("Opening Stock").setValue(fv);
//        ref.child("User").child("Ledger").child("Profit n Loss").child("Total Purchase").setValue(tp);
//        ref.child("User").child("Ledger").child("Profit n Loss").child("Expense").setValue(ex);
//        ref.child("User").child("Ledger").child("Profit n Loss").child("Total Sales").setValue(ts);
//        ref.child("User").child("Ledger").child("Profit n Loss").child("Stock On Hand").setValue(fv);
//        ref.child("User").child("Ledger").child("Profit n Loss").child("Profit Loss").setValue(op);
//
//
//        ref.child("User").child("Ledger").child("Profit n Loss").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()){
//                    try {
//                        Log.d("Hutya",ds.getValue().toString());
//                        String datee = ds.child("Date").getValue().toString();
//                        date.add(datee);
//                        String openingS = ds.child("Opening Stock").getValue().toString();
//                        openingStocks.add(openingS);
//                        String totalPur = ds.child("Total Purchase").getValue().toString();
//                        totalPurchase.add(totalPur);
//                        String exp = ds.child("Expense").getValue().toString();
//                        expesnes.add(exp);
//                        String totalSl = ds.child("Total Sales").getValue().toString();
//                        totalSales.add(totalSl);
//                        String soh = ds.child("Stock On Hand").getValue().toString();
//                        stockOnhands.add(soh);
//                        String pl = ds.child("Profit Loss").getValue().toString();
//                        profitLosss.add(pl);
//                        profitLossLists.setAdapter(new ProfitLossAdapters(date,openingStocks,totalPurchase,expesnes,
//                                totalSales,stockOnhands,profitLosss));
//                        Log.d("aa",""+date+""+expesnes+""+openingStocks+""+stockOnhands+""+profitLosss);
//
//                    }
//                    catch (Exception e){
//                        Log.d("Taky error pata chaly",e.toString());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



    }
}
