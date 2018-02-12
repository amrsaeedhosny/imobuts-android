package com.example.amrshosny.imobuts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText email;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        email = (EditText) findViewById(R.id.email);
        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                boolean validEmail = true;
                validEmail = isEmailValid();
                if(validEmail){

                }
            }
        });
    }

    boolean isEmailValid(){
        String emailText = String.valueOf(email.getText());
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$");
        Matcher matcher = pattern.matcher(emailText);
        if(email.length() == 0) {
            email.setError("Email is required");
            return false;
        }
        else if(!matcher.matches()){
            email.setError("Email format is incorrect");
        }
        else {
            email.setError(null);
        }
        return true;
    }
}
