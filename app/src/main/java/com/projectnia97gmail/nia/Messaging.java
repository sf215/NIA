package com.projectnia97gmail.nia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

public class Messaging extends AppCompatActivity {
    String phoneNo;
    String message;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        String utext = Basic.getUsertext();
        utext = utext.trim();
        utext = utext.toLowerCase();
        int index = utext.indexOf("message");
        if(index+6>=utext.length()-1)
            Basic.print("hey please specify to whom i need to message");
        else
        {
            if(index+17==utext.length()-1)
                Basic.print("Please speicfy content of message");
            else
            {
                phoneNo = utext.substring(index+8,index+18);
                 message= utext.substring(index+19);
                message();
            }
        }
    }

    void message()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

    }

    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Basic.print("Sms send");
                    finish();
                } else {
                    Basic.print("Sms send failed");
                    return;
                }
            }
        }

    }

}
