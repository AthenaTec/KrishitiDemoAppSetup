package com.realappraiser.gharvalue.worker;

import android.content.Context;
import android.util.Log;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.PeriodicWorkRequest.Builder;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;

public class WorkerManager {
    private static final String LOCATION_WORKER = "LOCATION_WORKER";
    private static final String TAG = "WorkerManager";
    private Context context;
    private WorkManager mWorkManager;
    private PeriodicWorkRequest workRequest = new Builder(LocationWorker.class,
            15, TimeUnit.MINUTES).addTag(LOCATION_WORKER).build();

    public WorkerManager(Context context2) {
        this.context = context2;
        this.mWorkManager = WorkManager.getInstance(context2);
    }

    public void startWorker() {
        Log.e(TAG, "startWorker: worker has been Started....");
        this.mWorkManager.enqueueUniquePeriodicWork(LOCATION_WORKER,
                ExistingPeriodicWorkPolicy.REPLACE, this.workRequest);
    }

    public void stopWorker() {
        Log.e(TAG, "stopWorker: worker has been Stoped....");
        this.mWorkManager.cancelAllWorkByTag(LOCATION_WORKER);
    }
}
