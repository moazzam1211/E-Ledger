package com.example.e_l;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MakeSelectionForgetPassword extends AppCompatActivity {
Button selectSms, selectEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_make_selection_forget_password);
        selectSms = findViewById(R.id.selectSms);
        selectEmail = findViewById(R.id.selectEmail);
        selectSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MakeSelectionForgetPassword.this, Verification.class);
                startActivity(i);
            }
        });
    }
}
