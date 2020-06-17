package com.projectnia97gmail.nia;


import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

/**
 * Created by Mansha Computer on 5/3/2018.
 */

public class Basic {

   static String s;
    static TextToSpeech speech;
    static int result;
    static public void print(String text)
    {
        text = text.replace("uname",getUserName());
         prints(text);
    }
    static public void prints(final String text)
    {
        speech = new TextToSpeech(MainActivity.getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result = speech.setLanguage(Locale.UK);

                    tts(result,text);

                } else {
                    MainActivity ma = new MainActivity();
                    Toast.makeText(ma.getApplicationContext(), "error", Toast.LENGTH_SHORT).show(); }
            }
        });

        MainActivity.ret().setGravity(Gravity.LEFT);
        MainActivity.ret().append("Nia: "+text+"\n");

    }

    static public void tts(int rs,String text){
        if(rs == TextToSpeech.LANG_NOT_SUPPORTED || rs == TextToSpeech.LANG_MISSING_DATA){
            MainActivity ma = new MainActivity();
            Toast.makeText(ma.getApplicationContext(), "error", Toast.LENGTH_SHORT).show(); }
        else {
            speech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }


    // code to get user text entered
    static public String getUsertext()
    {
        return MainActivity.ret1();
    }

    // code to get user name
    static public String getUserName(){ return MainActivity.ret2();}


     public String Connection(String parent,String child)
    {

        DatabaseReference datarefer;
        datarefer = FirebaseDatabase.getInstance().getReference().child(parent);
        DatabaseReference ref = datarefer.child(child);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Basic.s=dataSnapshot.getValue().toString();
                }
                else {

                   Basic. s = "noidea";
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return Basic.s;
    }

}
