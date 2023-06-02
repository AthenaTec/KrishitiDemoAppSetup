package com.realappraiser.gharvalue.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.realappraiser.gharvalue.BuildConfig;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.communicator.DataResponse;
import com.realappraiser.gharvalue.communicator.JsonRequestData;
import com.realappraiser.gharvalue.communicator.ResponseParser;
import com.realappraiser.gharvalue.communicator.TaskCompleteListener;
import com.realappraiser.gharvalue.communicator.WebserviceCommunicator;
import com.realappraiser.gharvalue.utils.Connectivity;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by kaptas on 14/12/17.
 */

@SuppressWarnings("ALL")
public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIME = 1000;
    private Handler mHandler;
    private Runnable mJumpRunnable;
    private General general;
    private String msg = "", info = "";
    RotateLoading rotateloading;

    // Analytics
    private FirebaseAnalytics mFirebaseAnalytics;

    private TextView tvVersion,tvCopyRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO - > For Bug and error report send to api
        General.report_bug(SplashActivity.this);
        setContentView(R.layout.splash_activity);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Splash");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        //mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        mFirebaseAnalytics.logEvent("Splash", bundle);
        tvVersion = findViewById(R.id.tvVersion);
        tvCopyRight = findViewById(R.id.tvCopyRight);


        int year = Calendar.getInstance().get(Calendar.YEAR);

        tvCopyRight.setText(getResources().getString(R.string.copyright)+" "+year+". "+getResources().getString(R.string.copyright1));

        rotateloading = (RotateLoading) findViewById(R.id.rotateloading);
        if (rotateloading != null) {
            rotateloading.start();
        }

        SettingsUtils.init(this);
        general = new General(SplashActivity.this);

        Translucent();

        mJumpRunnable = new Runnable() {
            public void run() {
                LoggedInAction();
            }
        };
        mHandler = new Handler();
        mHandler.postDelayed(mJumpRunnable, SPLASH_TIME);

        setVersionDetails();
    }

    private void setVersionDetails() {
        PackageInfo pInfo = null;
        try {
            pInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            String version = pInfo.versionName;
            String vers = "Version: "+version+"    Date: "+ BuildConfig.DATE;
                    tvVersion.setText(vers);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void Translucent() {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    public void LoggedInAction() {
        if (!SettingsUtils.getInstance().getValue(SettingsUtils.KEY_LOGGED_IN, false)) {
            // Without Login
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        } else {
            // Already Login
            if (Connectivity.isConnected(this)) {
                // Get Drop down API
                InitiateGetFieldDropDownTask();
            } else {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            }
        }
    }

    /*******
     * GetFieldDropDowns Webservice API call
     * *******/
    private void InitiateGetFieldDropDownTask() {
        String url = general.ApiBaseUrl() + SettingsUtils.GetFieldDropDowns;
        JsonRequestData requestData = new JsonRequestData();
        requestData.setUrl(url);
        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(SplashActivity.this, requestData, SettingsUtils.GET);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                String response = requestData.getResponse();
                SettingsUtils.getInstance().putValue(SettingsUtils.DropDownSave, response);
                parseGetDropDownsDataResponse(response);
            }
        });
        webserviceTask.execute();
    }

    private void parseGetDropDownsDataResponse(String response) {
        DataResponse dataResponse = ResponseParser.parseGetFieldDropDownResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
        }
        if (result != null) {
            if (result.equals("1")) {
                // general.CustomToast(msg);
            } else if (result.equals("2")) {
                general.CustomToast(msg);
            } else if (result.equals("0")) {
                general.CustomToast(msg);
            }
        } else {
            general.CustomToast(getResources().getString(R.string.serverProblem));
        }
        // Get offline count
        InitiateOfflineCaseCount();
    }

    /*******
     * Offline Count limitation Webservice API call
     * *******/
    private void InitiateOfflineCaseCount() {
        String url = general.ApiBaseUrl() + SettingsUtils.OfflineCaseCount;
        JsonRequestData requestData = new JsonRequestData();
        requestData.setUrl(url);
        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(SplashActivity.this, requestData, SettingsUtils.GET);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                String response = requestData.getResponse();
                parseOfflineCaseCountLimit(response);
            }
        });
        webserviceTask.execute();
    }

    private void parseOfflineCaseCountLimit(String response) {
        if (!general.isEmpty(response)) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                if (jsonArray.length() > 2) {
                    // No of cases move to offline
                    String no_offline_case = jsonArray.getJSONObject(1).getString("Value");
                    if (!general.isEmpty(no_offline_case)) {
                        SettingsUtils.getInstance().putValue(SettingsUtils.KEY_OFFLINECASE_COUNT, no_offline_case);
                    } else {
                        SettingsUtils.getInstance().putValue(SettingsUtils.KEY_OFFLINECASE_COUNT, "");
                    }
                    // Offline module enable or not
                    String enable_offline = jsonArray.getJSONObject(2).getString("Value");
                    if (!general.isEmpty(enable_offline)) {
                        if (enable_offline.equalsIgnoreCase("TRUE")) {
                            // enable
                            SettingsUtils.getInstance().putValue(SettingsUtils.KEY_ENABLE_OFFLINE, true);
                        } else {
                            // Hide offline button
                            SettingsUtils.getInstance().putValue(SettingsUtils.KEY_ENABLE_OFFLINE, false);
                        }
                    } else {
                        // Hide offline button
                        SettingsUtils.getInstance().putValue(SettingsUtils.KEY_ENABLE_OFFLINE, false);
                    }

                    // Will get weather the instance is jaipur or kakode
                    String instance_type = jsonArray.getJSONObject(3).getString("Value");
                    if (!general.isEmpty(instance_type)) {
                        if (instance_type.equalsIgnoreCase("1")) {
                            // Jaipur
                            SettingsUtils.getInstance().putValue(SettingsUtils.real_appraiser_jaipur, true);
                        } else {
                            // Kakode
                            SettingsUtils.getInstance().putValue(SettingsUtils.real_appraiser_jaipur, false);
                        }
                    } else {
                        SettingsUtils.getInstance().putValue(SettingsUtils.real_appraiser_jaipur, true);
                    }

                    // check for KEY_INTERNAL_COMPOSTION_NOT_COPY  or not
                    String KEY_INTERNAL_COMPOSTION_NOT_COPY = jsonArray.getJSONObject(4).getString("Value");
                    if (!general.isEmpty(KEY_INTERNAL_COMPOSTION_NOT_COPY)) {
                        if (KEY_INTERNAL_COMPOSTION_NOT_COPY.equalsIgnoreCase("TRUE")) {
                            // enable
                            SettingsUtils.getInstance().putValue(SettingsUtils.KEY_INTERNAL_COMPOSTION_NOT_COPY, true);
                        } else {
                            // Hide
                            SettingsUtils.getInstance().putValue(SettingsUtils.KEY_INTERNAL_COMPOSTION_NOT_COPY, false);
                        }
                    } else {
                        // Hide
                        SettingsUtils.getInstance().putValue(SettingsUtils.KEY_INTERNAL_COMPOSTION_NOT_COPY, false);
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            }
        }, 500);

    }

}
