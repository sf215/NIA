package com.projectnia97gmail.nia;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class Call {

    public int hashCode()
    {
        MainActivity.getContext().startActivity (new Intent(MainActivity.getContext(),Calling.class));
        return 0;
    }



}
