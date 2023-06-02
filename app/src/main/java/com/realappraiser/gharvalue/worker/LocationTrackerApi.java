package com.realappraiser.gharvalue.worker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.realappraiser.gharvalue.communicator.JsonRequestData;
import com.realappraiser.gharvalue.communicator.RequestParam;
import com.realappraiser.gharvalue.communicator.TaskCompleteListener;
import com.realappraiser.gharvalue.communicator.WebserviceCommunicator;
import com.realappraiser.gharvalue.utils.Connectivity;
import com.realappraiser.gharvalue.utils.SettingsUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LocationTrackerApi {
    private static final String TAG = "LocationTrackerApi";
    private Context context;
    private boolean success = false;


    public LocationTrackerApi(Context context) {
        this.context = context;
    }


    public boolean shareLocation(String caseId, String fieldStaffId, String interval, double latitudes, double longitudes) {

        String address = SettingsUtils.convertLatLngToAddress(this.context, latitudes, longitudes);
        @SuppressLint("SimpleDateFormat") String time =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(Calendar.getInstance().getTime());

        String sb2 = "shareLocation:1 caseId=>" +
                caseId +
                "\nfieldStaffId=>" +
                fieldStaffId +
                "\nType=>" +
                interval +
                "\nLatt=>" +
                latitudes +
                "\nLong=>" +
                longitudes +
                "\nTime=>" +
                time +
                "\nAddress=>" +
                address;
        Log.e(TAG, sb2);

        if (Connectivity.isConnected(context)) {
            JsonRequestData requestData = new JsonRequestData();
            String url = SettingsUtils.getInstance().getValue(SettingsUtils.API_BASE_URL,
                    "") + SettingsUtils.LocationTracker;
            requestData.setUrl(url);
            requestData.setCaseId(caseId);
            requestData.setEmpId(fieldStaffId);
            requestData.setLocationType(interval);
            requestData.setLatitude(String.valueOf(latitudes));
            requestData.setLongitude(String.valueOf(longitudes));
            requestData.setTrackerTime(time);
            requestData.setAddress(address);
            requestData.setRequestBody(RequestParam.LocationTracker(requestData));
            WebserviceCommunicator webserviceTask = new WebserviceCommunicator(context,
                    requestData, SettingsUtils.POST);
            webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                public void onTaskComplete(JsonRequestData requestData) {
                    String sb = "Location updated sucessfully" +
                            requestData.getResponse();
                    Log.e(TAG, sb);
                    success = true;
                }
            });
            webserviceTask.execute();
        } else {
           /* Toast.makeText(context, "Please check your Internet Connection!",
                    Toast.LENGTH_SHORT).show();*/
            success = false;
        }
        return success;
    }
}
