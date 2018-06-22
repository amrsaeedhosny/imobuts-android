package com.example.amrshosny.imobuts.components.buyticket;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.amrshosny.imobuts.R;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class BuyTicket extends AppCompatActivity {
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter writeTagFilters[];
    Tag tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if(nfcAdapter != null){
            if(!nfcAdapter.isEnabled()){
                Toast.makeText(this, "NFC is not enabled", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "NFC is enabled", Toast.LENGTH_LONG).show();
                pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
                IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
                tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
                writeTagFilters = new IntentFilter[] { tagDetected };
            }
        }
        else {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            Toast.makeText(this, tag.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private NdefRecord createRecord(String text) throws UnsupportedEncodingException {
        String lang = "en";
        byte[] textBytes = text.getBytes();
        byte[] langBytes = lang.getBytes("US-ASCII");
        int langLength = langBytes.length;
        int textLength = textBytes.length;
        byte[] payload = new byte[1 + langLength + textLength];
        payload[0] = (byte) langLength;
        System.arraycopy(langBytes, 0, payload, 1, langLength);
        System.arraycopy(textBytes, 0, payload, 1 + langLength, textLength);
        NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payload);
        return recordNFC;
    }

    private void write(String text) throws IOException, FormatException {
        NdefRecord[] records = { createRecord(text) };
        NdefMessage message = new NdefMessage(records);
        Ndef ndef = Ndef.get(tag);
        ndef.connect();
        ndef.writeNdefMessage(message);
        ndef.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        writeModeOn();
    }

    private void writeModeOn() {
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, writeTagFilters, null);
            try {
                if (tag == null) {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
                } else {
                    write("hello");
                    Toast.makeText(this, "written", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                Toast.makeText(this, "writing fail", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (FormatException e) {
                Toast.makeText(this, "writing fail", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        writeModeOff();
    }

    private void writeModeOff() {
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }
}


