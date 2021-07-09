package com.example.e_l;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    ImageView logo,logo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        logo=findViewById(R.id.logo1);
        logo2=findViewById(R.id.logo2);
        logo.setVisibility(View.VISIBLE);
        logo.setAnimation(AnimationUtils.loadAnimation(Splash.this,R.anim.fadeinpop));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                logo2.setVisibility(View.VISIBLE);
                logo2.setAnimation(AnimationUtils.loadAnimation(Splash.this,R.anim.translatefromright));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(Splash.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                },1500);

            }

        },2000);

    }
}
