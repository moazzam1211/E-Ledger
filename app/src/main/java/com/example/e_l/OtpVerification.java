package com.example.e_l;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

import static com.google.firebase.auth.PhoneAuthProvider.*;

public class OtpVerification extends AppCompatActivity {
    ImageView back;
    CountryCodePicker countryCodePicker;
    EditText phoneNumber ;
    Button buttonGetOTP;
    String codeBySystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp_verification);

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        back = findViewById(R.id.back);
        countryCodePicker = findViewById(R.id.countryCodeHolder);
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference();
        phoneNumber=findViewById(R.id.phoneNo);
        buttonGetOTP  = findViewById(R.id.btnSend);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OtpVerification.this,SignUpInfo.class);
                startActivity(intent);

            }
        });

    }


public void OTPverification(View view){
        if (!validatePhoneNumber()){
            return;
        }
    String _fullName = getIntent().getStringExtra("fullName");
    String _userName = getIntent().getStringExtra("userName");
    String _email = getIntent().getStringExtra("email");
    String _password = getIntent().getStringExtra("password");
    String _getUserPhoneNo = phoneNumber.getText().toString().trim();
    String _PhoneNo = "+"+countryCodePicker.getFullNumber()+_getUserPhoneNo;
    Intent i = new Intent(getApplicationContext(),Verification.class);
    i.putExtra("fullName", _fullName);
    i.putExtra("email", _email);
    i.putExtra("username", _userName);
    i.putExtra("password", _password);
    i.putExtra("phoneNo", _PhoneNo);
    i.putExtra("whatToDO", "createNewUser");
    startActivity(i);
    finish();

}
    private boolean validatePhoneNumber() {
        String val = phoneNumber.getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            phoneNumber.setError("Enter valid phone number");
            return false;
       }
//        else if (!val.matches(checkspaces)) {
//            phoneNumber.setError("No White spaces are allowed!");
//            return false;
//        }
       else {
            phoneNumber.setError(null);
            return true;
        }
    }
}
