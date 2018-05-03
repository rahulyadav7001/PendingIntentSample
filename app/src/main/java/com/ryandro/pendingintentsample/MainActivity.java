package com.ryandro.pendingintentsample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static android.media.RingtoneManager.getDefaultUri;

public class MainActivity extends AppCompatActivity {
    String CHANNEL_ID = "CHANNEL_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = getString(R.string.channel_name);
                    String description = getString(R.string.channel_description);
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                    channel.setDescription(description);
                    // Register the channel with the system; you can't change the importance
                    // or other notification behaviors after this
                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }*/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = "My Notification";
                    String description = getString(R.string.description);
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel channel;
                    channel = new NotificationChannel(CHANNEL_ID, name, importance);
                    channel.setDescription(description);
                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }


                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("Data_value", "MAster Data");
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                //noinspection deprecation
                Uri alarmSound = getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("Congratulations!!")
                        .setChannelId(CHANNEL_ID)
//                        .setContentInfo("Promotion Things.").setLargeIcon()
                        .setContentText("This is to inform you that have been selected for the free promtion thing which is related to your shopping from Mall")
                        .setSmallIcon(android.R.drawable.stat_notify_missed_call)
//                        .setProgress(100,30,true)
                        .setSound(alarmSound)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());
            }
        });
    }
}
