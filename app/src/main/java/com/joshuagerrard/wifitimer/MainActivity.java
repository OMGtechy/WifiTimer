package com.joshuagerrard.wifitimer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_SetupTimer(final View clickedView) {
        Log.d(TAG, "onClick_SetupTimer: called");

        final EditText editText = new EditText(this);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(editText);
        dialogBuilder.setTitle("Enter time until WiFi off (seconds)");

        dialogBuilder.setPositiveButton("Start timer", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int buttonNumber) {
                Log.d(TAG, "onClick_SetupTimer->onClick: called");
                Log.d(TAG, "onClick_SetupTimer->onClick: value " + editText.getText());

                int result = Integer.parseInt(editText.getText().toString()) * 1000 /* to millseconds */;

                new CountDownTimer(result, result) {
                    public void onFinish() {
                        Log.d(TAG, "onClick_SetupTimer->onClick->CountDownTimer_onFinished: called");
                        getApplicationContext().getSystemService(WifiManager.class).setWifiEnabled(false);
                    }

                    public void onTick(long millisecondsUntilFinished) { /* don't care */ }
                }.start();
            }
        });

        dialogBuilder.show();
    }
}
