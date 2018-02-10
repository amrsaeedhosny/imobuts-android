package com.example.amrshosny.imobuts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

import javax.microedition.khronos.egl.EGLDisplay;

public class CreateAccountActivity extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;
    EditText retypePassword;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        retypePassword = (EditText) findViewById(R.id.retype_password);
        button = (Button) findViewById(R.id.create_account);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                boolean validNewAccount = true;
                validNewAccount = validateUsername();
                validNewAccount = validateEmail();
                validNewAccount = validatePassword();
                validNewAccount = validateRetypePassword();
                if(validNewAccount){

                }
            }
        });

    }

    boolean validateUsername(){
        String usernameText = String.valueOf(username.getText());

        if(usernameText.length() == 0) {
            username.setError("Username is required");
            return false;
        }
        else if(!Pattern.matches("[a-zA-Z0-9]", usernameText)){
            username.setError("lol");
            return false;
        }
        else {
            username.setError(null);
        }
        return true;
    }

    boolean validateEmail(){
        String emailText = String.valueOf(email.getText());
        if(email.length() == 0) {
            email.setError("Email is required");
            return false;
        }
        else {
            email.setError(null);
        }
        return true;
    }

    boolean validatePassword(){
        String passwordText = String.valueOf(password.getText());

        if(passwordText.length() == 0) {
            password.setError("Password is required");
            return false;
        }
        else {
            password.setError(null);
        }

        return true;
    }

    boolean validateRetypePassword(){
        String retypePasswordText = String.valueOf(retypePassword.getText());
        String passwordText = String.valueOf(password.getText());

        if(retypePassword.length() == 0){
            retypePassword.setError("Retype password is required");
            return false;
        }
        else {
            retypePassword.setError(null);
        }

        return true;
    }


}
