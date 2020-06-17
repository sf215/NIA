package com.projectnia97gmail.nia;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Mansha Computer on 6/19/2018.
 */

public class Remember {
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
        int index = utext.indexOf("remember");
        if(index+7>=utext.length()-1)
            Basic.print("I do not what to remember");
        else
        {
            index = index + 9 ;

            int per = index ;
            int space1 =index;
            for(int i=index ; i<utext.length();i++)
            {
                if(utext.charAt(i)==' ')
                {
                    break;
                }
                space1++;
            }
            space1++;
            if(space1>=utext.length()-1)
            {
                Basic.print("Please specify what I have to learn");
            }
            else {
                index = space1;
                for (int i = index; i < utext.length(); i++) {
                    if (utext.charAt(i) == ' ') {
                        break;
                    }
                    space1++;
                }
                if (space1 >= utext.length() - 1)
                    Basic.print("Please specity what I have to learn");
                else {
                    String rem = utext.substring(per, space1);
                    rem = rem.trim();
                    String part2 = utext.substring(per);
                    part2  = part2.trim();
                    FirebaseAuth ref;
                    DatabaseReference datarefer;
                    ref = FirebaseAuth.getInstance();
                    datarefer = FirebaseDatabase.getInstance().getReference().child("Users");
                    String uid = ref.getCurrentUser().getUid();
                    DatabaseReference currentuser = datarefer.child(uid);
                    currentuser.child("remember").child(rem).setValue(part2);
                    Basic.print("Okay! I'll remember" );
                }
            }
        }
    }

}
