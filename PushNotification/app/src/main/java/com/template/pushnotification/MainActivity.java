package com.template.pushnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MainActivity";

    EditText etName, etSubject, etBody;
    Button btnNotify, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //VIEWS
        etName = (EditText) findViewById(R.id.etName);
        etSubject = (EditText) findViewById(R.id.etSubject);
        etBody = (EditText) findViewById(R.id.etBody);
        btnNotify = (Button) findViewById(R.id.btnNotify);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etName.getText().toString();
                String subject = etSubject.getText().toString();
                String body = etBody.getText().toString();

                String message = String.format("onClick: Name %s, Subject: %s, Body: %s", title, subject, body);
                Log.d(TAG, message);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.journaldev.com/"));
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                NotificationManager notificationMan = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setSmallIcon(android.R.drawable.alert_dark_frame);
                builder.setContentIntent(pendingIntent);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                builder.setContentTitle(title);
                builder.setContentText(body);
                builder.setSubText(subject);

                if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
                    String CHANNEL_ID = "channel_id";
                    int importance = notificationMan.IMPORTANCE_HIGH;
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel human readable title", importance);
                    notificationMan.createNotificationChannel(channel);
                    builder.setChannelId(CHANNEL_ID);

                }       //end if()

                // Will display the notification in the notification bar
                notificationMan.notify(1, builder.build());

            }       //end onClick()

        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(1);
            }
        });

    }       //end onCreate()



}       //end class
