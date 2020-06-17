package com.projectnia97gmail.nia;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


    public class Open {
        String utext ;


        public int hashCode()
        {
            utext = Basic.getUsertext();

            getName();
            return 0;
        }

        void getName() {
            utext=utext.trim();
            utext=utext.toLowerCase();
            int index = utext.indexOf("open");
            if(index+3>=utext.length()-1)
                Basic.print("I do not what to open");
            else {
                String name = utext.substring(index+5);
                DatabaseReference datarefer;
                datarefer = FirebaseDatabase.getInstance().getReference().child("Open");
                DatabaseReference ref = datarefer.child(name);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Basic.print("data exist");
                            String app = dataSnapshot.getValue().toString();
                            open_app(app);
                        } else {
                            Basic.print("I do not have package name");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        }
        public void open_app( String packageName) {

            Intent intent = MainActivity.getContext().getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent == null) {
                Basic.print("null");
                // Bring user to the market or let them choose an app?
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" + packageName));
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainActivity.getContext().startActivity(intent);
        }

    }


