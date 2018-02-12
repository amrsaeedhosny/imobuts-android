package com.example.amrshosny.imobuts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
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
                validNewAccount = isUsernameValid();
                validNewAccount = isEmailValid();
                validNewAccount = isPasswordValid();
                validNewAccount = isRetypePasswordValid();
                if(validNewAccount){

                }
            }
        });

    }

    boolean isUsernameValid(){
        String usernameText = String.valueOf(username.getText());
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(usernameText);

        if(usernameText.length() == 0) {
            username.setError("Username is required");
            return false;
        }
        else if(!matcher.matches()){
            username.setError("Letters and digits only are allowed");
            return false;
        }
        else if(usernameText.length() < 6){
            username.setError("Username must be greater than 6 characters");
            return false;
        }
        else {
            username.setError(null);
        }
        return true;
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

    boolean isPasswordValid(){
        String passwordText = String.valueOf(password.getText());

        if(passwordText.length() == 0) {
            password.setError("Password is required");
            return false;
        }
        else if(passwordText.length() < 6){
            password.setError("Password must be greater than 6 characters");
            return false;
        }
        else {
            password.setError(null);
        }

        return true;
    }

    boolean isRetypePasswordValid(){
        String retypePasswordText = String.valueOf(retypePassword.getText());
        String passwordText = String.valueOf(password.getText());

        if(retypePassword.length() == 0) {
            retypePassword.setError("Retype password is required");
            return false;
        }
        else if(!retypePasswordText.equals(passwordText)){
            retypePassword.setError("Retype password does not match password");
            return false;
        }
        else {
            retypePassword.setError(null);
        }

        return true;
    }


}
