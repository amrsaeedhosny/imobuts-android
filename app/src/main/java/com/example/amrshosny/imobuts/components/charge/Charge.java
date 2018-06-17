package com.example.amrshosny.imobuts.components.charge;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.amrshosny.imobuts.R;

import java.util.ArrayList;
import java.util.List;

public class Charge extends AppCompatActivity {
    Button charge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);
        charge = (Button) findViewById(R.id.charge);
        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRadioButtonDialog();
            }
        });
    }

    private void showRadioButtonDialog() {

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.charge_dialog);

        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radio_group);

        RadioButton rb=new RadioButton(this);
        rb.setText("Credit Card");
        rg.addView(rb);

        Button b = (Button)findViewById(R.id.hello);

        dialog.show();

    }


}
