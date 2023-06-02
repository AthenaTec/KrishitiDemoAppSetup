package com.realappraiser.gharvalue.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import androidx.core.app.NotificationCompat.Builder;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.activities.HomeActivity;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Utils {
    static final String CHANNEL_ID = "channel_01";
    static final String KEY_LOCATION_UPDATES_REQUESTED = "RP-location-updates-requested";
    static final String KEY_LOCATION_UPDATES_RESULT = "RP-location-update-result";

    static void setRequestingLocationUpdates(Context context, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(KEY_LOCATION_UPDATES_REQUESTED, value)
                .apply();
    }

    static boolean getRequestingLocationUpdates(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(KEY_LOCATION_UPDATES_REQUESTED, false);
    }


    static void sendNotification(Context context, String notificationDetails) {
        Intent notificationIntent = new Intent(context, HomeActivity.class);
        notificationIntent.putExtra("from_notification", true);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(HomeActivity.class);
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent notificationPendingIntent = stackBuilder.getPendingIntent(0, 134217728);
        Builder builder = new Builder(context);
        builder.setSmallIcon(R.mipmap.launcher_logo).setLargeIcon(BitmapFactory.
                decodeResource(context.getResources(), R.mipmap.launcher_logo))
                .setColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setContentTitle("Location Guidance Active").setContentText(notificationDetails)
                .setContentIntent(notificationPendingIntent);
        builder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (VERSION.SDK_INT >= 26) {
            CharSequence name = context.getString(R.string.app_name);
            String str = CHANNEL_ID;
            Objects.requireNonNull(mNotificationManager)
                    .createNotificationChannel(new NotificationChannel(str,
                            name, NotificationManager.IMPORTANCE_DEFAULT));
            builder.setChannelId(str);
        }
        mNotificationManager.notify(0, builder.build());
    }

    static String getLocationResultTitle(Context context, List<Location> locations) {
       /* String numLocationsReported = context.getResources()
                .getQuantityString(R.plurals.num_locations_reported,
                        locations.size(), locations.size());*/
        String numLocationsReported = context.getResources().getString(R.string.loc_updated);
        return numLocationsReported +" "+ DateFormat.getDateTimeInstance().format(new Date());
    }

    private static String getLocationResultText(Context context, List<Location> locations) {
        if (locations.isEmpty()) {
            return context.getString(R.string.unknown_location);
        }
        StringBuilder sb = new StringBuilder();
        for (Location location : locations) {
            sb.append("(");
            sb.append(location.getLatitude());
            sb.append(", ");
            sb.append(location.getLongitude());
            sb.append(")");
            sb.append("\n");
        }
        return sb.toString();
    }

    static void setLocationUpdatesResult(Context context, List<Location> locations) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        String sb = getLocationResultTitle(context, locations) + "\n" + getLocationResultText(context, locations);
        edit.putString(KEY_LOCATION_UPDATES_RESULT, sb).apply();
    }

    static String getLocationUpdatesResult(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_LOCATION_UPDATES_RESULT, "");
    }
}
