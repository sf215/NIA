package com.projectnia97gmail.nia;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Mansha Computer on 6/19/2018.
 */

public class What {
    String utext ;
    public int hashCode()
    {
        utext = Basic.getUsertext();

        getName();
        return 0;
    }
    void getName()
    {
        utext=utext.trim();
        utext=utext.toLowerCase();
        final int index = utext.indexOf("what");
        if(index+3>=utext.length()-1)
            Basic.print("I do not what to search for");
        else {
            utext = utext.replace("is", " ");
           final String part = utext.substring(index + 4).trim();
            //part = part.trim();

            //

            FirebaseAuth ref;
            DatabaseReference datarefer;
            ref = FirebaseAuth.getInstance();
            datarefer = FirebaseDatabase.getInstance().getReference().child("Users");
            String uid = ref.getCurrentUser().getUid();
            DatabaseReference currentuser = datarefer.child(uid);
            datarefer = currentuser.child("remember").child(part);
            datarefer.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String value = dataSnapshot.getValue().toString();
                        value = value.replace("my", "your");
                        Basic.print("You told me " + value);
                    } else {
                       // Basic.print("Sorry I did not know about " + dataSnapshot.getKey());
                        // Iske aage hme appi link krna h wiki ki
                        utext=utext.substring(index+4).trim();
                        final String p = utext.replace("is", "").trim();
                        new Wiki(p);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        //
    }
}
