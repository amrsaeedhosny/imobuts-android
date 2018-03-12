package com.example.amrshosny.imobuts.components.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amrshosny.imobuts.R;
import com.example.amrshosny.imobuts.api.ApiController;
import com.example.amrshosny.imobuts.api.json.Form;
import com.example.amrshosny.imobuts.api.json.JsonResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;
    EditText retypePassword;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        retypePassword = (EditText) findViewById(R.id.retype_password);
        signUp = (Button) findViewById(R.id.create_account);

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               if(isFormValid()){
                   signUp.setVisibility(View.GONE);
                   findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);

                   String usernameText = String.valueOf(username.getText());
                   String emailText = String.valueOf(email.getText());
                   String passwordText = String.valueOf(password.getText());

                   ApiController.getApi()
                           .signUp(usernameText, emailText, passwordText)
                           .enqueue(new Callback<JsonResponse<Form>>() {
                       @Override
                       public void onResponse(Call<JsonResponse<Form>> call, Response<JsonResponse<Form>> response) {
                           if(response.isSuccessful()){
                               Toast.makeText(getApplicationContext(), "Your account has been created", Toast.LENGTH_LONG).show();
                               if(response.body().getSuccess()) {
                                   finish();
                               }
                               else {
                                   username.setError(response.body().getResponse().getUsername());
                                   email.setError(response.body().getResponse().getEmail());
                                   password.setError(response.body().getResponse().getPassword());
                               }
                           }
                           else {
                               Toast.makeText(getApplicationContext(), "Some error has occurred", Toast.LENGTH_LONG).show();
                           }
                           findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                           signUp.setVisibility(View.VISIBLE);
                       }

                       @Override
                       public void onFailure(Call<JsonResponse<Form>> call, Throwable t) {
                           Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
                           findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                           signUp.setVisibility(View.VISIBLE);
                       }
                   });
               }
            }
        });

    }

    boolean isFormValid(){
        boolean validUsername = isUsernameValid();
        boolean validEmail = isEmailValid();
        boolean validPassword = isPasswordValid();
        boolean validRetypePassword = isRetypePasswordValid();
        return validUsername && validEmail && validPassword && validRetypePassword;
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
            return false;
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
