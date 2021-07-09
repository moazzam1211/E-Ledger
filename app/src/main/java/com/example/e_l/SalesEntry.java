package com.example.e_l;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class SalesEntry extends AppCompatActivity {
    EditText productName,productQty;
    AutoCompleteTextView custName;
    AutoCompleteTextView type;
    EditText salesPrice, salesDate;
    String total;
    ImageView scanCode, addPro, addCus;
    FloatingActionButton add;
    DatePickerDialog.OnDateSetListener listener;
    Bitmap bitmap, scaledBmp;
    Date date;
    final String[] paymentType= new String[]{"Cash","Debit"};
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sales_entry);
        productName = findViewById(R.id.productName);
        custName = findViewById(R.id.customerName);
        productQty = findViewById(R.id.productQty);
        salesDate = findViewById(R.id.salesDate);
        salesPrice = findViewById(R.id.salesPrice);
        type = findViewById(R.id.type);
        add = findViewById(R.id.addS);
        scanCode = findViewById(R.id.scanSearch);
        addPro = findViewById(R.id.addPro);
        addCus = findViewById(R.id.addCust);
        date = new Date();
        ref = FirebaseDatabase.getInstance().getReference();

        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.header_invoice);
       scaledBmp = Bitmap.createScaledBitmap(bitmap,1280,553,false);
        ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);



        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        salesDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SalesEntry.this, (view, year1, month1, dayOfMonth) -> {
                    month1 = month1 +1;
                    String datee = day+"/"+ month1 +"/"+ year1;
                    salesDate.setText(datee);
                },year,month,day);
                datePickerDialog.show();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,paymentType);
        type.setAdapter(adapter);
        addPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SalesEntry.this, AddProduct.class);
                startActivity(i);
            }
        });
        addCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SalesEntry.this, AddCustomers.class);
                startActivity(i);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String proNameValue = productName.getText().toString();

                int proQtyValue = Integer.parseInt(productQty.getText().toString());
                String dateValue = salesDate.getText().toString();
                int priceValue = Integer.parseInt(salesPrice.getText().toString());
                int totals =  priceValue*proQtyValue;
                String typeValue = type.getText().toString();
                String custNamValue = custName.getText().toString();

                if (proNameValue.isEmpty()){
                    productName.setError("Field is Empty!");
                    productName.requestFocus();
                    return;
                }
                if (dateValue.isEmpty()){
                    salesDate.setError("Field is Empty!");
                    salesDate.requestFocus();
                    return;
                }
                if (typeValue.isEmpty()) {
                    type.setError("Field is Empty!");
                    type.requestFocus();
                    return;
                }
                if (custNamValue.isEmpty()){
                    custName.setError("Field is Empty!");
                    custName.requestFocus();
                    return;
                }

                productName.setText("");
                productQty.setText("");
                salesDate.setText("");
                salesPrice.setText("");
                type.setText("");
                custName.setText("");

//                Map<String,Object> maps = new HashMap<String, Object>();
//                maps.put("Product Name",proNameValue);
//                maps.put("Product Quantity",proQtyValue);
//                maps.put("Purchase Date",dateValue);
//                maps.put("Purchase Price","Rs "+priceValue);
//                maps.put("Total Stock","Rs "+totals);
//                maps.put("Payment Type",typeValue);
//                maps.put("V Name",custNamValue);
//                ref.child("User").child("Inventory").child("Sales Entry").push().setValue(maps);
//                createPDF();
                ref.child("User").child("Inventory").child("Sales Entry").child(proNameValue).child("Product Name").setValue(proNameValue);
                ref.child("User").child("Inventory").child("Sales Entry").child(proNameValue).child("Sales Quantity").setValue(proQtyValue);
                ref.child("User").child("Inventory").child("Sales Entry").child(proNameValue).child("Sales Date").setValue(dateValue);
                ref.child("User").child("Inventory").child("Sales Entry").child(proNameValue).child("Sales Price").setValue("Rs "+priceValue);
                ref.child("User").child("Inventory").child("Sales Entry").child(proNameValue).child("Total Stock").setValue("Rs "+totals);
                ref.child("User").child("Inventory").child("Sales Entry").child(proNameValue).child("Payment Type").setValue(typeValue);
                ref.child("User").child("Inventory").child("Sales Entry").child(proNameValue).child("Customer Name").setValue(custNamValue);

                if (!typeValue.equalsIgnoreCase("cash")&&!typeValue.equalsIgnoreCase("debit"))
                {
                    type.setError("Type is not Valid!");
                    type.requestFocus();
                    return;
                }
                //Toast.makeText(SalesEntry.this, "Entry Added!", Toast.LENGTH_SHORT).show();
                showDailog();
            }
        });
    }
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
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    private void createPDF() {
//        String initials=
//                custName.getText().toString().charAt(0)+""+
//                        custName.getText().toString().charAt(1)+""+
//                        custName.getText().toString().charAt(2);
//
//
//
//
//        PdfDocument pdfDocument = new PdfDocument();
//        Paint paint = new Paint();
//        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1280,1500,1).create();
//        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
//        Canvas canvas = page.getCanvas();
//        //canvas.drawText("This is my first pdf making app!",40,50,paint);
//        canvas.drawBitmap(scaledBmp,0,0,paint);
//
//        paint.setTextAlign(Paint.Align.LEFT);
//        paint.setTextSize(35f);
//        paint.setColor(Color.BLACK);
//        canvas.drawText("Vendor Name: "+custName.getText(),20,590,paint);
//        paint.setTextAlign(Paint.Align.RIGHT);
//        canvas.drawText("Invoice No.: "+initials+"00000",1230,590,paint);
//        DateFormat dateFormat;
//        dateFormat = new SimpleDateFormat("dd/MM/yy");
//        paint.setTextAlign(Paint.Align.RIGHT);
//        canvas.drawText("Date: "+dateFormat.format(date),1235,640,paint);
//        dateFormat = new SimpleDateFormat("HH:mm:ss");
//        paint.setTextAlign(Paint.Align.RIGHT);
//        canvas.drawText("Time: "+dateFormat.format(date),1225,690,paint);
//
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(3);
//        canvas.drawRect(1230,780,20,860,paint);
//        paint.setTextAlign(Paint.Align.LEFT);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawText("Sr. No.",50,830,paint);
//        canvas.drawText("Product Name",220,830,paint);
//        canvas.drawText("Price",710,830,paint);
//        canvas.drawText("Qty",900,830,paint);
//        canvas.drawText("Total",1055,830,paint);
//        canvas.drawLine(180,790,180,840,paint);
//        canvas.drawLine(680,790,680,840,paint);
//        canvas.drawLine(880,790,880,840,paint);
//        canvas.drawLine(1030,790,1030,840,paint);
//
//        String pp = productName.getText().toString();
//        String pq = productQty.getText().toString();
//        int totals = Integer.parseInt(pp)*Integer.parseInt(pq);
//        ArrayList<String> products = new ArrayList<>();
//        products.add(productName.toString());
//        ArrayList<String> prices = new ArrayList<>();
//        prices.add(salesPrice.toString());
//        ArrayList<String> qtys = new ArrayList<>();
//        qtys.add(productQty.toString());
////                ArrayList<String> total = new ArrayList<>();
////                total.add(String.valueOf(totals));
//        List<Integer> totall = Arrays.asList(totals);
//        int sum=0;
//        for (int j:totall){
//            sum+=totals;
//        }
//        for (int i =1; i<=products.size();i++){
//            canvas.drawText(""+(i),40,950,paint);
//            canvas.drawText(productName.getText().toString(),200,950,paint);
//            canvas.drawText(salesPrice.getText().toString(),700,950,paint);
//            canvas.drawText(productQty.getText().toString(),910,950,paint);
//            paint.setTextAlign(Paint.Align.RIGHT);
//            canvas.drawText(String.valueOf(totals),1170,950,paint);
//            paint.setTextAlign(Paint.Align.LEFT);
//        }
//        paint.setTextAlign(Paint.Align.RIGHT);
//        canvas.drawLine(1800,1000,10,1000,paint);
//        canvas.drawText("Total",820,1050,paint);
//        canvas.drawText(":",900,1050,paint);
//        paint.setTextAlign(Paint.Align.RIGHT);
//        canvas.drawText("Rs "+sum,1150,1050,paint);
//        pdfDocument.finishPage(page);
//        // String myFilePath = Environment.getExternalStorageDirectory().getPath() + "/myPDFFile.pdf";
//        File myFile = new File( getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/E-L_Invoice.pdf");
//
//        try {
//            pdfDocument.writeTo(new FileOutputStream(myFile));
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        pdfDocument.close();
//        Toast.makeText(SalesEntry.this, "Invoice Generated!", Toast.LENGTH_SHORT).show();
//
//    }
}
