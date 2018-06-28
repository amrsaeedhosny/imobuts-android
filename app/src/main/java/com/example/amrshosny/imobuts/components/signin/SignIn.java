package com.example.amrshosny.imobuts.components.signin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amrshosny.imobuts.components.content.AccountContent;
import com.example.amrshosny.imobuts.components.forgetpassword.ForgetPassword;
import com.example.amrshosny.imobuts.R;
import com.example.amrshosny.imobuts.api.ApiController;
import com.example.amrshosny.imobuts.api.json.Form;
import com.example.amrshosny.imobuts.api.json.JsonResponse;
import com.example.amrshosny.imobuts.components.signup.SignUp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {
    EditText username;
    EditText password;
    Button signIn;
    TextView forgetPassword;
    TextView createAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.login);
        forgetPassword = (TextView) findViewById(R.id.forget_password);
        createAccount = (TextView) findViewById(R.id.create_account);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFormValid()){
                    signIn.setVisibility(View.GONE);
                    findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                    signInApi();
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, ForgetPassword.class);
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
        else {
            username.setError(null);
        }
        return true;
    }

    boolean isPasswordValid(){
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

    void signInApi(){
        ApiController.getApi()
                .signIn(username.getText().toString(), password.getText().toString())
                .enqueue(new Callback<JsonResponse<Form>>() {
                    @Override
                    public void onResponse(Call<JsonResponse<Form>> call, Response<JsonResponse<Form>> response) {
                        if(response.isSuccessful()){
                            if(response.body().getSuccess()) {
                                SharedPreferences sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("token", response.body().getToken());
                                editor.commit();

                                Intent intent = new Intent(SignIn.this, AccountContent.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                username.setError(response.body().getResponse().getUsername());
                                password.setError(response.body().getResponse().getPassword());

                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Some error has occurred", Toast.LENGTH_LONG).show();

                        }
                        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                        signIn.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Call<JsonResponse<Form>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
                        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                        signIn.setVisibility(View.VISIBLE);
                    }
                });
    }
}
