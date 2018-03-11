package com.example.amrshosny.imobuts;

import android.content.Intent;
import android.net.sip.SipAudioCall;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    TextView forgetPassword;
    TextView createAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        forgetPassword = (TextView) findViewById(R.id.forget_password);
        createAccount = (TextView) findViewById(R.id.create_account);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFormValid()){
                    final String usernameText = String.valueOf(username.getText());
                    String passwordText = String.valueOf(password.getText());
                    ApiController.getApi()
                            .signIn(usernameText, passwordText)
                            .enqueue(new Callback<JsonResponse<FormResponse>>() {
                        @Override
                        public void onResponse(Call<JsonResponse<FormResponse>> call, Response<JsonResponse<FormResponse>> response) {
                            if(response.isSuccessful()){
                                if(response.body().getSuccess()) {
                                    Intent intent = new Intent(LoginActivity.this, AccountContentActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    username.setError(response.body().getResponse().getUsername().get(0));
                                    password.setError(response.body().getResponse().getPassword().get(0));
                                }
                            }
                            else {

                            }
                        }

                        @Override
                        public void onFailure(Call<JsonResponse<FormResponse>> call, Throwable t) {

                        }
                    });
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change color to red
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    boolean isFormValid(){
        boolean validUsername = isUsernameValid();
        boolean validPassword = isPasswordValid();
        return validUsername && validPassword;
    }

    boolean isUsernameValid(){
        String usernameText = String.valueOf(username.getText());

        if(usernameText.length() == 0) {
            username.setError("Username is required");
            return false;
        }
        return true;
    }

    boolean isPasswordValid(){
        String passwordText = String.valueOf(password.getText());

        if(passwordText.length() == 0) {
            password.setError("Password is required");
            return false;
        }
        return true;
    }
}
