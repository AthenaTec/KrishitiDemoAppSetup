package com.realappraiser.gharvalue.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Data.Builder;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.realappraiser.gharvalue.MyApplication;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.utils.GPSService;
import com.realappraiser.gharvalue.utils.OfflineLocationReceiver;
import com.realappraiser.gharvalue.utils.SettingsUtils;

import java.util.Objects;

public class LocationWorker extends Worker {
    private static final String TAG = "LocationWorker";
    private static final String WORK_RESULT = "work_result";
    private GPSService gpsService;
    private LocationTrackerApi locationTrackerApi;
    private String taskDataString;

    public LocationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    public Result doWork() {
        Data taskData = getInputData();
        String str = TAG;
        Log.e(str, "doWork: ");
        Context context = MyApplication.getAppContext();
        locationTrackerApi = new LocationTrackerApi(context);
//        taskDataString = taskData.getString(HomeActivity.MESSAGE_STATUS);
        gpsService = new GPSService(context);
        if (OfflineLocationReceiver.isLocationEnabled(context)) {
            String fieldStaffId = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_LOGIN_ID, "");
            String sb = "doWork: +Upadeted Latt:" +
                    SettingsUtils.Latitudes +
                    " long:" +
                    SettingsUtils.Longitudes;
            Log.e(str, sb);
            locationTrackerApi.shareLocation("", fieldStaffId, "Interval", SettingsUtils.Latitudes, SettingsUtils.Longitudes);
        }
        return Result.success(new Builder().putString(WORK_RESULT, "Jobs Finished").build());
    }

    private void showNotification(String task, String desc) {
        Log.e(TAG, "showNotification: ");
        NotificationManager manager =
                (NotificationManager) MyApplication.getAppContext()
                        .getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "task_channel";
        String channelName = "task_name";
        if (VERSION.SDK_INT >= 26) {
            Objects.requireNonNull(manager)
                    .createNotificationChannel(new NotificationChannel(channelId,
                            channelName, NotificationManager.IMPORTANCE_LOW));
        }
        Objects.requireNonNull(manager).notify(1,
                new NotificationCompat.Builder(MyApplication.getAppContext(), channelId)
                        .setContentTitle(task)
                        .setContentText(desc)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.launcher_logo).build());
    }
}
