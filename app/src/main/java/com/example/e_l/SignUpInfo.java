package com.example.e_l;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpInfo extends AppCompatActivity {
    Button signup;
    EditText fullName, userName, email, password;
    TextView signin;
    private FirebaseAuth mAuth;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up_info);
        signup = findViewById(R.id.signup);
        fullName = findViewById(R.id.fullname);
        userName = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signinbtn);

        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();



    }
    public void nextToOTPScreen(View view){
        if (!validateFullName()|!validateUsername()|!validateEmail()|!validatePassword()){
            return;
        }

        Intent i =  new Intent(getApplicationContext(),OtpVerification.class);
        startActivity(i);
    }
    private boolean validateFullName(){
        String val= fullName.getText().toString().trim();
        if(val.isEmpty()){
            fullName.setError("Field can not be empty!");
            return false;
        }
        else {
            fullName.setError(null);
            return true;
        }
    }
    private boolean validateUsername(){
        String val= userName.getText().toString().trim();
        String checkSpaces ="Aw{1,20}z";
        if(val.isEmpty()){
            userName.setError("Field can not be empty!");
            return false;
        }
        else if (val.length()>20){
            userName.setError("username is too large!");
            return false;
        }
//        else if (!val.matches(checkSpaces)) {
//            userName.setError("No White spaces are allowed!");
//            return false;
//        }
        else {
            userName.setError(null);
            return true;
        }
    }
    private boolean validateEmail(){
        String val= email.getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        if(val.isEmpty()){
            email.setError("Field can not be empty!");
            return false;
        }
        else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        }
       else if (!Patterns.EMAIL_ADDRESS.matcher(val).matches()) {
            email.setError("Not a valid Email Address");
            email.requestFocus();
            return false;
        }
        else {
            email.setError(null);
            return true;
        }

    }
    private boolean validatePassword(){
        String val= password.getText().toString().trim();
//        String checkPassword = "^" +
//                //"(?=.*[0-9])" +         //at least 1 digit
//                //"(?=.*[a-z])" +         //at least 1 lower case letter
//                //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=S+$)" +           //no white spaces
//                ".{4,}" +               //at least 4 characters
//                "$";
        if(val.isEmpty()){
            password.setError("Field can not be empty!");
            return false;
        }
//        else if (!val.matches(checkPassword)) {
//            password.setError("Password should contain 4 characters!");
//            return false;
//        }
        else {
            password.setError(null);
            return true;
        }

    }


}
