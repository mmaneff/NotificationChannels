package com.acdesarrollo.notificationchannels;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private NotificationUtils mNotificationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationUtils = new NotificationUtils(this);

        final EditText editTextTitleAndroid = (EditText) findViewById(R.id.et_android_title);
        final EditText editTextAuthorAndroid = (EditText) findViewById(R.id.et_android_author);
        Button buttonAndroid = (Button) findViewById(R.id.btn_send_android);

        buttonAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitleAndroid.getText().toString();
                String author = editTextAuthorAndroid.getText().toString();

                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(author)) {
                    NotificationCompat.Builder nb = mNotificationUtils.
                            getAndroidChannelNotification(title, "By " + author);

                    mNotificationUtils.getManager().notify(101, nb.build());
                }
            }
        });

        final EditText editTextTitleIos = (EditText) findViewById(R.id.et_ios_title);
        final EditText editTextAuthorIos = (EditText) findViewById(R.id.et_ios_author);
        Button buttonIos = (Button) findViewById(R.id.btn_send_ios);
        buttonIos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitleIos.getText().toString();
                String author = editTextAuthorIos.getText().toString();

//                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(author)) {
//                    Notification.Builder nb = mNotificationUtils
//                            .getIosChannelNotification(title, "By " + author);
//
//                    mNotificationUtils.getManager().notify(102, nb.build());
//                }
            }
        });
    }
}
