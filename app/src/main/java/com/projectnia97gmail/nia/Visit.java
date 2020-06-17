package com.projectnia97gmail.nia;


import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Visit{

    String utext ;
    String name;
    public int hashCode()
    {
        utext = Basic.getUsertext();
        getName();
        return 0;
    }

    // to get name of domain
    void getName()
    {
       utext=utext.toLowerCase();
        utext=utext.trim();
       final int index = utext.indexOf("visit");
        if(index+4>=utext.length()-1)
            Basic.print("Please Do not know where to visit");
        else {
            name = utext.substring(index+6);
            name = name.trim();
            DatabaseReference datarefer;
            datarefer = FirebaseDatabase.getInstance().getReference().child("Visit");
            DatabaseReference ref = datarefer.child(name);
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        visitTo(dataSnapshot.getValue().toString());
                    } else {
                        utext=utext.substring(index+5).trim();
                        final String p = utext.replace("is", "").trim();
                        new Wiki(p);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }


    void visitTo(String domain) {
        Intent sharingIntent = new Intent(Intent.ACTION_VIEW);
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sharingIntent.setData(Uri.parse(domain));
        Intent chooserIntent = Intent.createChooser(sharingIntent, "Open With");
        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MainActivity.getContext().startActivity(chooserIntent);
        Basic.print("You visited "+name);
    }
}
