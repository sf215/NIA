package com.projectnia97gmail.nia;

import android.content.Intent;

public class Message {
    public int hashCode()
    {
        MainActivity.getContext().startActivity (new Intent(MainActivity.getContext(),Messaging.class));
        return 0;
    }
}
