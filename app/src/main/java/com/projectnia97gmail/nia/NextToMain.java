package com.projectnia97gmail.nia;




import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Mansha Computer on 5/1/2018.
 */

public class NextToMain {


    String utext;
    String name;
    String[] words;
    void joke()
    {
        String joke = "Can a kangaroo jump higher than a house?\n" +

                "Of course, a house doesn’t jump at all.";
        Basic.print("let me tell u something: "+joke);
    }
    public void output() {
        utext = Basic.getUsertext();
        utext = utext.trim();
        name = Basic.getUserName();
        if(utext.contains("joke")||utext.contains("bore"))
        {
            joke();
            return;
        }
        words = utext.split("\\s");

        // checking for exact match
        DatabaseReference datarefer;
        datarefer = FirebaseDatabase.getInstance().getReference().child("oneword");
        DatabaseReference ref = datarefer.child(utext.toLowerCase());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    String s = dataSnapshot.getValue().toString();
                    Basic.print(s);
                } else {
                    second();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    void second()
    {
        utext = Basic.getUsertext();
        utext = utext.trim();
        if(utext.contains("your"))
        {
             int index = 0 ;
             for(int i=0;i<words.length;i++)
             {
                 if(words[i].equals("your"))
                 {
                     index =   i ;
                     break;

                 }
             }
             if(index==words.length-1)
                 secondary();
            else
             {
                 String s = "your "+words[index+1];
                 DatabaseReference datarefer;
                 datarefer = FirebaseDatabase.getInstance().getReference().child("twoword");
                 DatabaseReference ref = datarefer.child(s);
                 ref.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                         if (dataSnapshot.exists()) {

                             String answer = dataSnapshot.getValue().toString();
                             Basic.print(answer);

                         } else {
                             secondary();
                         }
                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 });
             }
        }
        else if(utext.contains("search")){
            utext=utext.trim();
            utext=utext.toLowerCase();
            int index = utext.indexOf("search");
            if(index+5>=utext.length()-1)
                Basic.print("I do not know what to search");
            else {
                String search = utext.substring(index + 7);
               new Wiki(search);
            }
        }
        else
        {
            secondary();
        }


    }

    void secondary()
    {
        int i=0;
        while(i<words.length&&words[i]=="EFCqwv")
        {
            i++;
        }
        if(i<words.length)
        {
            String s = words[i];
            words[i]="EFCqwv";
            call(s);
        }
        if(i==words.length)
            // yha se wiki link kr de kya isse to ye kbhi sorry nhi bolegi
            Basic.print("Sorry I did not know that");

    }
    void call(final String s)
    {
        DatabaseReference datarefer;
        datarefer = FirebaseDatabase.getInstance().getReference().child("command");
        DatabaseReference ref = datarefer.child(s);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String s = dataSnapshot.getValue().toString();
                    try {

                        Class.forName("com.projectnia97gmail.nia." + s).newInstance().hashCode();

                    } catch (Exception e) {
                        Basic.print(e.toString());
                    }

                } else
                {
                    secondary();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



}
