package com.example.amrshosny.imobuts.components.forgetpassword;

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

public class ForgetPassword extends AppCompatActivity {

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
            if(isEmailValid()){
                send.setVisibility(View.GONE);
                findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                forgetPasswordApi();
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
            return false;
        }
        else {
            email.setError(null);
        }
        return true;
    }

    void forgetPasswordApi(){
        ApiController.getApi()
                .resetPassword(email.getText().toString())
                .enqueue(new Callback<JsonResponse<Form>>() {
                    @Override
                    public void onResponse(Call<JsonResponse<Form>> call, Response<JsonResponse<Form>> response) {
                        if(response.isSuccessful()){
                            if(response.body().getSuccess()){
                                Toast.makeText(getApplicationContext(), "A message has been sent to your email", Toast.LENGTH_LONG).show();
                            }
                            else {
                                email.setError(response.body().getResponse().getEmail());
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Some error has occurred", Toast.LENGTH_LONG).show();
                        }
                        send.setVisibility(View.VISIBLE);
                        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<JsonResponse<Form>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
                        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                        send.setVisibility(View.VISIBLE);
                    }
                });
    }
}
