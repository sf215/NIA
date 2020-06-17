package com.projectnia97gmail.nia;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Calling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);


        String  utext = Basic.getUsertext();
        utext=utext.trim();
        utext=utext.toLowerCase();
        int index = utext.indexOf("call");
        if(index+3>=utext.length()-1)
            Basic.print("I do not whom to call");
        else {
            String name = utext.substring(index + 5);
            name = name.trim();
            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:"+name));
            if (ActivityCompat.checkSelfPermission(Calling.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(Calling.this,new String[]{android.Manifest.permission.CALL_PHONE},0);
                return;
            }
            startActivity(i);
            finish();



        }
    }
}
