package com.example.e_l;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Verification extends AppCompatActivity {
    TextView resendCode, changeNumber;
    PinView pinView;
    String codeBySystem;
    String fullName,userName,email,password,PhoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verification);
        TextView textMobile = findViewById(R.id.txtMobile);
        pinView= findViewById(R.id.pinView);
        resendCode = findViewById(R.id.txtResendOTP);
        changeNumber = findViewById(R.id.changeNum);
        textMobile.setText(getIntent().getStringExtra("phoneNo"));
         TextView change = findViewById(R.id.changeNum);
        ProgressBar progressBar = findViewById(R.id.progressBar);
       Button btnVerification = findViewById(R.id.verifybtn);

        fullName = getIntent().getStringExtra("fullName");
        userName = getIntent().getStringExtra("userName");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        PhoneNo = getIntent().getStringExtra("phoneNo");

        sendOTPCodeToUSer(PhoneNo);

        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTPCodeToUSer(PhoneNo);
                Toast.makeText(Verification.this, "Code Resend", Toast.LENGTH_SHORT).show();
            }
        });
        changeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Verification.this, OtpVerification.class);
                startActivity(in);
                Toast.makeText(Verification.this, "Change your Number", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


   }

    private void sendOTPCodeToUSer(String phoneNo) {
        FirebaseAuth mAuth =  FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeBySystem=s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
        String code = phoneAuthCredential.getSmsCode();
        if (code!=null){
            pinView.setText(code);
            verifyCode(code);

            // signInWithPhoneAuthCredential(phoneAuthCredential);
        }
        }
        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
           Toast.makeText(Verification.this,  e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(codeBySystem,code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            storeNewUserData();

                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(Verification.this, "Verification not completed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void storeNewUserData() {
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference("User");
        NewUserClass newUser = new NewUserClass(fullName,userName,email,password,PhoneNo);
        ref.child(PhoneNo).setValue(newUser);
    }

    public void nextScreenAfterVerify(View view){
        String code = pinView.getText().toString();
        if (!code.isEmpty()){
            verifyCode(code);
        }
        Intent i = new Intent(getApplicationContext(), Dashboard.class);
    }

}
