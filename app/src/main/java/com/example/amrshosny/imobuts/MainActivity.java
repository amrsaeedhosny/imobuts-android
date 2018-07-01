package com.example.amrshosny.imobuts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.amrshosny.imobuts.components.content.AccountContent;
import com.example.amrshosny.imobuts.components.signin.SignIn;

public class MainActivity extends AppCompatActivity {

    public static int TIME_OUT = 5000;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(sharedPreferences.contains("token")){
                    intent = new Intent(MainActivity.this, AccountContent.class);
                }
                else {
                    intent = new Intent(MainActivity.this, SignIn.class);
                }
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);
    }
}
