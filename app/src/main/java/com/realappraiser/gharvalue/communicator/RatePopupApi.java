package com.realappraiser.gharvalue.communicator;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.gson.Gson;
import com.realappraiser.gharvalue.model.CaseSelection;
import com.realappraiser.gharvalue.model.RatePopup;
import com.realappraiser.gharvalue.utils.Connectivity;
import com.realappraiser.gharvalue.utils.SettingsUtils;

public class RatePopupApi {
    private RatePopupupInterface ratePopupupInterface;
    private Context context;
    private static final String TAG = "RatePopupApi";

    public RatePopupApi(Context context, RatePopupupInterface ratePopupupInterface) {
        this.ratePopupupInterface = ratePopupupInterface;
        this.context = context;
    }

    public void getRatePopup(String caseId, String latitude, String longitude) {

        Log.e(TAG, "getRatePopup: caseId" + caseId + "=latitude=" + latitude + "=longitude=" + longitude);

        if (Connectivity.isConnected(context)) {
            String url = SettingsUtils.getInstance().getValue(SettingsUtils.API_BASE_URL,
                    "") + SettingsUtils.getRatePopup;
            JsonRequestData requestData = new JsonRequestData();
            requestData.setInitQueryUrl(url);
            requestData.setCaseId(caseId);
            requestData.setLatitude(latitude);
            requestData.setLongitude(longitude);
            requestData.setUrl(RequestParam.GetRatePopupDetails(requestData));
            WebserviceCommunicator webserviceTask = new WebserviceCommunicator(context, requestData, SettingsUtils.GET);
            webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                @Override
                public void onTaskComplete(JsonRequestData requestData) {
                    try {
                        String sb = "RatePopup get sucessfully" +
                                requestData.getResponse();
                        Log.e(TAG, sb);
                        ratePopupupInterface.onRatePopupSucess(new Gson().fromJson(requestData.getResponse(), RatePopup.class));
                    } catch (Exception e) {
                        ratePopupupInterface.onRatePopupFailed("Something went wrong!");
                        e.printStackTrace();
                    }
                }
            });
            webserviceTask.execute();
        } else {
            Connectivity.showNoConnectionDialog(context);
        }
    }
}
