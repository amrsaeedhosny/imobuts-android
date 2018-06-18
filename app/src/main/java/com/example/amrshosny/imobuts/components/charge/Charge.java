package com.example.amrshosny.imobuts.components.charge;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.amrshosny.imobuts.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Charge extends AppCompatActivity {
    Button confirm;
    Button charge;
    EditText amount;
    Dialog dialog;
    RadioGroup radioGroup;
    RadioButton stripe;
    SharedPreferences sharedPreferences;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);
        sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);

        amount = (EditText) findViewById(R.id.amount);
        confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAmountValid()) {
                    showRadioButtonDialog();
                }
            }
        });
    }

    boolean isAmountValid(){
        String amountText = String.valueOf(amount.getText());
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(amountText);
        if(amountText.length() == 0) {
            amount.setError("Amount is required");
            return false;
        }
        else if(!matcher.matches()){
            amount.setError("Amount has to be digits");
            return false;
        }
        else {
            amount.setError(null);
        }
        return true;
    }

    private void showRadioButtonDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.charge_dialog);
        radioGroup = (RadioGroup) dialog.findViewById(R.id.radio_group);
        stripe = new RadioButton(this);
        stripe.setText("Stripe (Visa/Mastercard)");
        radioGroup.addView(stripe);
        dialog.show();

        charge = dialog.findViewById(R.id.charge);
        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = radioGroup.getCheckedRadioButtonId();
                if(stripe.getId() == id){
                    stripe();
                }
                dialog.hide();
            }
        });
    }

    void stripe(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://imobuts.herokuapp.com/stripe?token=" + token + "&amount=" + amount.getText()));
        startActivity(browserIntent);
    }
}
