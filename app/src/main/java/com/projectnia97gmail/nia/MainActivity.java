package com.projectnia97gmail.nia;

import android.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.BooleanResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    static EditText txtuser;
   public static TextView txtchat;
    static String utext;
    Button btn_submit;
    NextToMain nexts;
    public static Context mContext;
    static String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //user should not see the app screen if the permission is not granted,
        // we will first call the above method inside onCreate().
        checkPermission();

        //Declarations
        txtchat = (TextView) findViewById(R.id.txtchat);
        txtuser = (EditText) findViewById(R.id.txtuser);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        getSpeechInput();

        nexts = new NextToMain();
        mContext = getBaseContext();
        name=FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        // code to get only first name
        name+=" ";
        name=name.substring(0,name.indexOf(" "));

        // function call in order to greet someone
        greet();
        Weather loc=new Weather(26.9124,75.7873);

        //Button listner
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utext = txtuser.getText().toString();
                utext=utext.trim();
                if (utext.equals(""))
                    Basic.print("No input "+name);
                else {
                    utext=utext.toLowerCase();
                    MainActivity.ret().setGravity(Gravity.RIGHT);
                    txtchat.append(name + " : " + utext + "\n");

                    txtuser.setText("");
                    nexts.output();

                }

            }
        });


    }

    @Override
    protected void onPause() {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(txtuser.getWindowToken(), 0);

        super.onPause();
    }

    // function for call
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", null).show();

    }

    public void getSpeechInput(){
        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                //getting all the matches
                ArrayList<String> matches =  results
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null)
                    txtuser.setText(matches.get(0));

            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
        findViewById(R.id.btnSpeak).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //case 1:when speak button is released
                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        txtuser.setHint("");
                        break;

                    //case 2 :when speak button is pressed
                    case MotionEvent.ACTION_DOWN:
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        txtuser.setText("");
                        txtuser.setHint("Listening...");
                        break;
                }
                return false;
            }

        });
    }


    //function to check permission to record audio
    private void checkPermission() {
        //Here we are checking if the device is running android marshmallow or ahead.
        // If this condition is true again, we are testing the permission granted or not.
        // If the permission is not granted, we are opening the settings from where the user can allow the permission.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }




    // functions for returning references
    public static TextView ret() {
        return txtchat;
    }

    public static String ret1() {
        return utext;
    }

    public static String ret2() {return name;}

    public static Context getContext() {
        return mContext;
    }
    //end of reference retrun function

    // function to speak up during intiiaisation
    public void greet()
    {
        Basic.print("Glad to see you back "+name);
    }




}
