package com.example.amrshosny.imobuts.components.buyticket;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.amrshosny.imobuts.R;

public class BuyTicket extends AppCompatActivity implements NfcAdapter.CreateNdefMessageCallback {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter == null) {
            Toast.makeText(this, "NFC is not supported", Toast.LENGTH_LONG).show();
        }
        else if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this, "Enable NFC to buy a ticket", Toast.LENGTH_LONG).show();
        }
        else {
            nfcAdapter.setNdefPushMessageCallback(this, this);
        }
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        String balance = sharedPreferences.getString("balance", null);
        String message = token + "-" + balance;
        NdefRecord ndefRecord = null;
        NdefMessage ndefMessage = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            ndefRecord = NdefRecord.createMime("text/plain", message.getBytes());
            ndefMessage = new NdefMessage(ndefRecord);
        }

        return ndefMessage;
    }
}



