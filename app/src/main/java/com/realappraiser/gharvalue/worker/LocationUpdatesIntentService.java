package com.realappraiser.gharvalue.worker;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import com.google.android.gms.location.LocationResult;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import java.util.List;

public class LocationUpdatesIntentService extends IntentService {
    private static final String ACTION_PROCESS_UPDATES = "com.realappraiser.gharvalue.worker" +
            ".locationupdatespendingintent.action.PROCESS_UPDATES";
    private static final String TAG = LocationUpdatesIntentService.class.getSimpleName();

    public LocationUpdatesIntentService() {
        super(TAG);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            if (("com.realappraiser.gharvalue.worker.locationupdatespendingintent" +
                    ".action.PROCESS_UPDATES").equals(intent.getAction())) {
                LocationResult result = LocationResult.extractResult(intent);
                if (result != null) {
                    List<Location> locations = result.getLocations();
                    SettingsUtils.Latitudes = ((Location) result.getLocations().get(0)).getLatitude();
                    SettingsUtils.Longitudes = ((Location) result.getLocations().get(0)).getLongitude();
                    Utils.setLocationUpdatesResult(this, locations);
                    Utils.sendNotification(this, Utils.getLocationResultTitle(this, locations));
                    Log.i(TAG, Utils.getLocationUpdatesResult(this));
                }
            }
        }
    }
}
