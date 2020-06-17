package com.projectnia97gmail.nia;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Mansha Computer on 5/1/2018.
 */

public class Nia  extends Application{
    public void onCreate()
    {
        super.onCreate();
        Firebase.setAndroidContext(this);


    }

}
