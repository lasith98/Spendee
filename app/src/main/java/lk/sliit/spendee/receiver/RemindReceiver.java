package lk.sliit.spendee.receiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.util.Objects;

import lk.sliit.spendee.MainActivity;
import lk.sliit.spendee.R;


/**
 * author: Lasith Hansana
 * date: 5/9/2021
 * time: 12:07 AM
 */
public class RemindReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "REMIND_CHANNEL";

    @Override
    public void onReceive(Context context, Intent intent) {


        int notificationId = intent.getIntExtra("notificationId", 0);
        String message = intent.getStringExtra("message");

        Intent mainIntent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "Spendee Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle("Spendee")
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            notificationManager.notify(notificationId,builder.build());
        }


    }
}
