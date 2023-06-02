package com.realappraiser.gharvalue.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.realappraiser.gharvalue.AppDatabase;
import com.realappraiser.gharvalue.MyApplication;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.adapter.CustomViewPager;
import com.realappraiser.gharvalue.adapter.PhotoOthersTabAdapter;
import com.realappraiser.gharvalue.communicator.DataModel;
import com.realappraiser.gharvalue.communicator.DataResponse;
import com.realappraiser.gharvalue.communicator.JsonRequestData;
import com.realappraiser.gharvalue.communicator.RequestParam;
import com.realappraiser.gharvalue.communicator.ResponseParser;
import com.realappraiser.gharvalue.communicator.TaskCompleteListener;
import com.realappraiser.gharvalue.communicator.WebserviceCommunicator;
import com.realappraiser.gharvalue.fragments.OtherDetails;
import com.realappraiser.gharvalue.fragments.OtherDetails_ka;
import com.realappraiser.gharvalue.fragments.PhotoLatLong_ka;
import com.realappraiser.gharvalue.model.Case;
import com.realappraiser.gharvalue.model.GetPhoto;
import com.realappraiser.gharvalue.model.GetPhoto_measurment;
import com.realappraiser.gharvalue.model.ImageBase64;
import com.realappraiser.gharvalue.model.IndProperty;
import com.realappraiser.gharvalue.model.IndPropertyValuation;
import com.realappraiser.gharvalue.model.LatLongDetails;
import com.realappraiser.gharvalue.model.OfflineDataModel;
import com.realappraiser.gharvalue.model.OflineCase;
import com.realappraiser.gharvalue.model.Property;
import com.realappraiser.gharvalue.utils.Connectivity;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kaptas on 19/12/17.
 */

@SuppressWarnings("ALL")
public class PhotoLatLngTab extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.parentLay)
    LinearLayout parentLay;
    @BindView(R.id.username)
    TextView username;
    General general;
    @SuppressLint("StaticFieldLeak")
    public static CustomViewPager pager;
    public static PhotoOthersTabAdapter adapter;

    String welcome_name = "";

    // TODO for back press
    boolean doubleBackToExitPressedOnce = false;
    boolean is_offline = false;
    int currentPosition = 0;
    AppDatabase appDatabase = null;
    String my_case_id;
    int PropertyId_is = 0;
    public boolean enable_offline_button;

    // Analytics
    private FirebaseAnalytics mFirebaseAnalytics;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO - > For Bug and error report send to api
        General.report_bug(PhotoLatLngTab.this);
        setContentView(R.layout.photolatlng);
        General.statusbarColor(this);
        ButterKnife.bind(this);

        SettingsUtils.init(this);
        general = new General(this);

        pager = (CustomViewPager) findViewById(R.id.pager);
        initToolbar();
        WelcomeName();
        boolean real_appraiser_jaipur = SettingsUtils.getInstance().getValue(SettingsUtils.real_appraiser_jaipur, false);
        // check the case is offline (or) online
        is_offline = SettingsUtils.getInstance().getValue(SettingsUtils.is_offline, false);

        // Room - Declared
        if (MyApplication.getAppContext() != null) {
            appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
        }

        // CaseID
        my_case_id = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        // Property
        if (!general.isEmpty(SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, ""))) {
            PropertyId_is = Integer.parseInt(SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, ""));
        }

        // check the offline module is present or not
        enable_offline_button = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_ENABLE_OFFLINE, false);


//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        pager.setOffscreenPageLimit(0);

        adapter = new PhotoOthersTabAdapter(this.getSupportFragmentManager(), real_appraiser_jaipur);
        //Adding adapter to pager
        pager.setAdapter(adapter);

        if (real_appraiser_jaipur) {
            // Jaipur Tab - Enable swipe function
            pager.setPagingEnabled(true);

            // Obtain the FirebaseAnalytics instance.
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "6");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Details");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
            mFirebaseAnalytics.logEvent("Details", bundle);

        } else {
            // Kakode Tab - Disable swipe function
            pager.setPagingEnabled(false);
            if (Singleton.getInstance().aCase.getStatus() == 13 || Singleton.getInstance().enable_validation_error) {
                pager.setCurrentItem(0);
            } else {
                pager.setCurrentItem(1);
            }

            // Obtain the FirebaseAnalytics instance.
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "6");
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Details_kakode");
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
            //mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            mFirebaseAnalytics.logEvent("Details_kakode", bundle);

        }

        //swipe possison
        tabLayout.setupWithViewPager(pager);
        //Adding onTabSelectedListener to swipe views
        //noinspection deprecation
        tabLayout.setOnTabSelectedListener(this);
        username.setText(welcome_name);



    }


    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String casename = SettingsUtils.getInstance().getValue(SettingsUtils.Case_Title, "");
        String bank = SettingsUtils.getInstance().getValue(SettingsUtils.Case_Bank, "");
        String caseID = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        if (!general.isEmpty(casename) && !general.isEmpty(bank)) {
            getSupportActionBar().setTitle("CID:" + caseID + " - " + casename + "(" + bank + ")");
        } else if (!general.isEmpty(casename)) {
            getSupportActionBar().setTitle("CID:" + caseID + " - " + casename);
        } else {
            getSupportActionBar().setTitle(R.string.title);
        }

    }

    private void WelcomeName() {
        String firstname = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_FIRSTNAME, "");
        String lastname = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_LASTNAME, "");
        if ((!general.isEmpty(firstname)) && (!general.isEmpty(lastname))) {
            welcome_name = getResources().getString(R.string.hi) + " " + firstname + " " + lastname;
        } else if ((!general.isEmpty(firstname))) {
            welcome_name = getResources().getString(R.string.hi) + " " + firstname;
        } else if ((!general.isEmpty(lastname))) {
            welcome_name = getResources().getString(R.string.hi) + " " + lastname;
        } else {
            welcome_name = getResources().getString(R.string.title);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        // pager.setCurrentItem(tab.getPosition());

        boolean real_appraiser_jaipur = SettingsUtils.getInstance().getValue(SettingsUtils.real_appraiser_jaipur, false);
        if (real_appraiser_jaipur) {
            // Jaipur
            pager.setCurrentItem(tab.getPosition());
        } else {
            // Kakode
            pager.setCurrentItem(tab.getPosition());
            if (tab.getPosition() == 0) {
                // show_emptyFocus();
            }
            if (tab.getPosition() == 1) {
                // Log.e("test_final","hit_photo_api_popup "+Singleton.getInstance().hit_photo_api);
                if (Singleton.getInstance().hit_photo_api) {
                    saveImage_popup();
                }
            }
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.logout);
        MenuItem item1 = menu.findItem(R.id.refresh);
        MenuItem item2 = menu.findItem(R.id.ic_home);
        MenuItem item3 = menu.findItem(R.id.versionname);
        MenuItem item4 = menu.findItem(R.id.gooffline_case);
        MenuItem item5 = menu.findItem(R.id.create);


        item.setVisible(true);
        item1.setVisible(false);
        item2.setVisible(true);
        item3.setVisible(true);
        item4.setVisible(false);
        item5.setVisible(false);
        return true;
    }


    /*siva-11*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                backbutton();
                return true;
            case R.id.ic_home:
                savechangesDialog();
                return true;
            case R.id.logout:
                general.LogoutDialog();
                return true;

            case R.id.versionname:
                VersionNamePopup();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void VersionNamePopup() {
        final Dialog dialog = new Dialog(PhotoLatLngTab.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.versionname_popup);

        TextView versionname = (TextView) dialog.findViewById(R.id.versionname);
        TextView versionnametext = (TextView) dialog.findViewById(R.id.versionnametext);
        TextView releasedate = (TextView) dialog.findViewById(R.id.releasedate);
        TextView releasedatetext = (TextView) dialog.findViewById(R.id.releasedatetext);
        Button okBtn = (Button) dialog.findViewById(R.id.okBtn);
        versionname.setTypeface(general.mediumtypeface());
        versionnametext.setTypeface(general.mediumtypeface());
        releasedate.setTypeface(general.mediumtypeface());
        releasedatetext.setTypeface(general.mediumtypeface());
        okBtn.setTypeface(general.mediumtypeface());


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialog.dismiss();
            }
        });

        dialog.show();
    }


    @Override
    public void onBackPressed() {
        backbutton();
    }

    public void backbutton() {
        savechangesDialog(PhotoLatLngTab.this);
    }

    public void savechangesDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setMessage(getResources().getString(R.string.nochanges))
                .setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        if(SettingsUtils.getInstance().getValue(SettingsUtils.is_local, false)){
                       SettingsUtils.getInstance().putValue(SettingsUtils.is_local, false);
                        OtherDetails.save_type = "savego";
                        OtherDetails.getInstance().SaveFormDetails();
//                        }else {
//                            Singleton.getInstance().longitude = 0.0;
//                            Singleton.getInstance().latitude = 0.0;
//                            Singleton.getInstance().aCase = new Case();
//                            Singleton.getInstance().property = new Property();
//                            Singleton.getInstance().indProperty = new IndProperty();
//                            Singleton.getInstance().indPropertyValuation = new IndPropertyValuation();
//                            Singleton.getInstance().indPropertyFloors = new ArrayList<>();
//                            Singleton.getInstance().proximities = new ArrayList<>();
//                            Singleton.getInstance().is_new_floor_created = false;
//                            Singleton.getInstance().openCaseList.clear();
//                            Singleton.getInstance().closeCaseList.clear();
//                            /* finish();*/
//                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void savechangesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setMessage(getResources().getString(R.string.nochanges))
                .setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        if(SettingsUtils.getInstance().getValue(SettingsUtils.is_local, false)){
                        SettingsUtils.getInstance().putValue(SettingsUtils.is_local, false);
                        OtherDetails.save_type = "savego";
                        OtherDetails.getInstance().SaveFormDetails();
//                        }else {
//                            Singleton.getInstance().longitude = 0.0;
//                            Singleton.getInstance().latitude = 0.0;
//                            Singleton.getInstance().aCase = new Case();
//                            Singleton.getInstance().property = new Property();
//                            Singleton.getInstance().indProperty = new IndProperty();
//                            Singleton.getInstance().indPropertyValuation = new IndPropertyValuation();
//                            Singleton.getInstance().indPropertyFloors = new ArrayList<>();
//                            Singleton.getInstance().proximities = new ArrayList<>();
//                            Singleton.getInstance().is_new_floor_created = false;
//                            Singleton.getInstance().openCaseList.clear();
//                            Singleton.getInstance().closeCaseList.clear();
//                            /* finish();*/
//                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    protected void onStop() {
        super.onStop();
        Singleton.getInstance().latitude = 0.0;
        Singleton.getInstance().longitude = 0.0;
    }


    // call Photo api

    public void saveImage_popup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(PhotoLatLngTab.this, R.style.AlertDialogCustom));
        builder.setMessage(getResources().getString(R.string.save_popup))
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        general.showloading(PhotoLatLngTab.this);
                        call_photo_function();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void call_photo_function() {
        // check offline or not
        if (!is_offline) {
            // online
            if (Connectivity.isConnected(PhotoLatLngTab.this)) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        JSONArray jsonArray = new JSONArray();
                        for (int x = 0; x < PhotoLatLong_ka.GetPhoto_list_response.size(); x++) {
                            if (!PhotoLatLong_ka.GetPhoto_list_response.get(x).isDefaultimage()) {
                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("ID", PhotoLatLong_ka.GetPhoto_list_response.get(x).getId());
                                    jsonObject.put("PropertyId", PhotoLatLong_ka.GetPhoto_list_response.get(x).getPropertyId());
                                    if (!general.isEmpty(PhotoLatLong_ka.GetPhoto_list_response.get(x).getTitle())) {
                                        jsonObject.put("Title", PhotoLatLong_ka.GetPhoto_list_response.get(x).getTitle());
                                    } else {
                                        jsonObject.put("Title", "");
                                    }
                                    jsonObject.put("Logo", PhotoLatLong_ka.GetPhoto_list_response.get(x).getLogo());
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                                jsonArray.put(jsonObject);
                            }
                        }
                        if (jsonArray.length() > 0) {
                            UploadImage(jsonArray.toString());
                        } else {
                            UploadImage(jsonArray.toString());
                        }
                    }
                }, 500);
            } else {
                String curent_statusID = Singleton.getInstance().curent_statusID;
                if (!general.isEmpty(curent_statusID)) {
                    if (curent_statusID.equalsIgnoreCase("13")) {
                        // Start Inspection - Data will save in LOCALDB
                        // TODO - Incase, If thr is no internet on start Inspection - move the case to offline mode
                        internet_check_box("start_inspec");
                    } else {
                        // Internet Required - Edit case
                        internet_check_box("edit_inspec");
                    }
                }
            }
        } else {
            // offline
            String str_latvalue, str_longvalue;
            str_latvalue = PhotoLatLong_ka.latvalue.getText().toString();
            str_longvalue = PhotoLatLong_ka.longvalue.getText().toString();
            Singleton.getInstance().latlng_details = str_latvalue + ":" + str_longvalue;
            // Room - delete lat long
            appDatabase.interfaceLatLongQuery().deleteRow(Integer.parseInt(my_case_id));
            LatLongDetails latLongDetails = new LatLongDetails();
            latLongDetails.setCaseId(Integer.parseInt(my_case_id));
            latLongDetails.setLatLongDetails(Singleton.getInstance().latlng_details);
            // Room - add lat long
            appDatabase.interfaceLatLongQuery().insertAll(latLongDetails);
            // Photo delete and add
            save_image_offline("next_btn");
        }
    }

    private void UploadImage(String s) {

        String str_latvalue, str_longvalue;
        str_latvalue = PhotoLatLong_ka.latvalue.getText().toString();
        str_longvalue = PhotoLatLong_ka.longvalue.getText().toString();
        Singleton.getInstance().latlng_details = str_latvalue + ":" + str_longvalue;

        JSONObject jsonObject = new JSONObject();
        String PropertyId = SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, "");
        if (general.isEmpty(PropertyId)) {
            PropertyId = "0";
        }
        try {
            jsonObject.put("PropertyId", PropertyId);
            jsonObject.put("LatLongDetails", Singleton.getInstance().latlng_details);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        JSONObject edit_synk_obj = new JSONObject();
        try {
            edit_synk_obj.put("PropertyId", PropertyId);
            edit_synk_obj.put("is_sync", false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonRequestData data = new JsonRequestData();
        // set Photo
        data.setCompanyName(s);
        // set the lat and long
        data.setContactPersonName(jsonObject.toString());
        // set edit_synk
        data.setApplicantName(edit_synk_obj.toString());

        data.setUrl(general.ApiBaseUrl() + SettingsUtils.IMAGE);
        data.setRequestBody(RequestParam.uploadimageRequestParams(data));

        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(PhotoLatLngTab.this, data, SettingsUtils.POST);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                parseuploadimageResponse(requestData.getResponse());
            }
        });
        webserviceTask.execute();
    }

    private void parseuploadimageResponse(String response) {
        // Re - Initial the curent_statusID as 0 for move to case to online
        Singleton.getInstance().curent_statusID = "0";
        // hit_photo_api > false
        Singleton.getInstance().hit_photo_api = false;

        if (Singleton.getInstance().aCase.getStatus() == 13) {
            /* Start Inspection - Update the case ID */
            UpdateStatusCaseIdWebservice();
        } else {
            DataResponse dataResponse = ResponseParser.uploadimage(response);
            if (dataResponse.status.equalsIgnoreCase("1")) {
                Toast.makeText(PhotoLatLngTab.this, dataResponse.info, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(PhotoLatLngTab.this, dataResponse.msg, Toast.LENGTH_LONG).show();
            }
            general.hideloading();
            PhotoLatLngTab.pager.setCurrentItem(1);
            if (Connectivity.isConnected(PhotoLatLngTab.this)) {
                GetImageAPI();
            } else {
                Connectivity.showNoConnectionDialog(PhotoLatLngTab.this);
                general.hideloading();
            }
        }
    }

    // Status Edit inspection Update  API call //
    private void UpdateStatusCaseIdWebservice() {
        String case_id = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        if (Connectivity.isConnected(PhotoLatLngTab.this)) {
            InitiateUpdateStatusCaseidTask(case_id, getResources().getString(R.string.edit_inspection));
        } else {
            Connectivity.showNoConnectionDialog(PhotoLatLngTab.this);
            general.hideloading();
        }
    }

    private void InitiateUpdateStatusCaseidTask(String case_id, String statusId) {

        String url = general.ApiBaseUrl() + SettingsUtils.UpdateCaseStatusById;
        // String url = SettingsUtils.BASE_URL + SettingsUtils.UpdateCaseStatusById;
        String modifiedby = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_LOGIN_ID, "");
        JsonRequestData requestData = new JsonRequestData();
        requestData.setUrl(url);
        requestData.setCaseId(case_id);//case_id
        requestData.setModifiedBy(modifiedby);
        requestData.setStatus(statusId);//statusId
        requestData.setRequestBody(RequestParam.UpdateCaseStatusRequestParams(requestData));

        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(PhotoLatLngTab.this, requestData, SettingsUtils.PUT);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                parseUpdateStatusCaseidResponse(requestData.getResponse());
            }
        });
        webserviceTask.execute();
    }

    private void parseUpdateStatusCaseidResponse(String response) {

        String msg = "", info = "";
        DataResponse dataResponse = ResponseParser.parseUpdateStatusCaseResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
            Singleton.getInstance().updateCaseStatusModel = dataResponse.updateCaseStatusModel;
        }

        if (result != null) {
            if (result.equals("1")) {
                general.hideloading();
                PhotoLatLngTab.pager.setCurrentItem(1);
                if (Connectivity.isConnected(PhotoLatLngTab.this)) {
                    GetImageAPI();
                } else {
                    Connectivity.showNoConnectionDialog(PhotoLatLngTab.this);
                    general.hideloading();
                }
            } else if (result.equals("2")) {
                general.CustomToast(msg);
            } else if (result.equals("0")) {
                general.CustomToast(msg);
            }
        } else {
            general.CustomToast(PhotoLatLngTab.this.getResources().getString(R.string.serverProblem));
        }
        general.hideloading();
    }


    // Get APi
    private void GetImageAPI() {

        Singleton.getInstance().base64image.clear();
        JsonRequestData data = new JsonRequestData();
        String PropertyId = SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, "");
        data.setUrl(general.ApiBaseUrl() + SettingsUtils.GETIMAGE + PropertyId);

        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(PhotoLatLngTab.this, data, SettingsUtils.GET);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                parseViewimageResponse(requestData.getResponse());
            }
        });
        webserviceTask.execute();
    }

    private void parseViewimageResponse(String response) {
        /* san */
        Singleton.getInstance().GetPhoto_list_final.clear();

        // Room Delete
        //appDatabase.interfaceGetPhotoQuery().deleteRow(PropertyId_is);

        int PropertyId_is = 0;
        if (!general.isEmpty(SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, ""))) {
            PropertyId_is = Integer.parseInt(SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, ""));
        }

        ArrayList<GetPhoto> GetPhoto_list_response = new ArrayList<>();
        GetPhoto_list_response = new ArrayList<>();
        GetPhoto dummy_cameraImage = new GetPhoto();
        dummy_cameraImage.setDefaultimage(true);
        dummy_cameraImage.setPropertyId(PropertyId_is);
        Gson gson_dummy_camera = new Gson();
        gson_dummy_camera.toJson(dummy_cameraImage);
        GetPhoto_list_response.add(dummy_cameraImage);

        // Room Add
        //appDatabase.interfaceGetPhotoQuery().insertAll(dummy_cameraImage);

        GetPhoto dummy_galleryImage = new GetPhoto();
        dummy_galleryImage.setDefaultimage(true);
        dummy_galleryImage.setPropertyId(PropertyId_is);
        Gson gson_dummy_gallery = new Gson();
        gson_dummy_gallery.toJson(dummy_galleryImage);
        GetPhoto_list_response.add(dummy_galleryImage);

        // Room Add
        //appDatabase.interfaceGetPhotoQuery().insertAll(dummy_galleryImage);


        DataResponse dataResponse = new DataResponse();
        ArrayList<ImageBase64> list = new ArrayList<>();
        try {
            if (response != null) {
                JSONObject object = new JSONObject(response);
                dataResponse.status = object.getString("status");
                if (dataResponse.status.equalsIgnoreCase("1")) {
                    JSONArray jsonArray = object.getJSONArray("data");
                    if (jsonArray.length() > 0) {
                        for (int p = 0; p < jsonArray.length(); p++) {
                            JSONObject IndPropertyFloors_jobj = jsonArray.getJSONObject(p);
                            Gson IndPropertyFloorsgson = new Gson();
                            GetPhoto getPhoto = null;
                            getPhoto = new GetPhoto();
                            getPhoto = IndPropertyFloorsgson.fromJson(IndPropertyFloors_jobj.toString(), GetPhoto.class);
                            GetPhoto_list_response.add(getPhoto);

                            // Room Add
                            //appDatabase.interfaceGetPhotoQuery().insertAll(getPhoto);

                        }
                    }
                    Singleton.getInstance().GetPhoto_list_final = GetPhoto_list_response;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Log.e("test_final", "get_image : " + GetPhoto_list_response.size());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Singleton.getInstance().hit_photo_api = false;
                PhotoLatLong_ka interface_activity = new PhotoLatLong_ka();
                interface_activity.date_is("kakode");
                general.hideloading();

            }
        }, 1000);

    }

    public void show_emptyFocus() {
        // Show focus
        if (OtherDetails_ka.my_focuslayout != null) {
            OtherDetails_ka.my_focuslayout.requestFocus();
        }
    }

    private void save_image_offline(final String btn_is) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Room Delete
                appDatabase.interfaceGetPhotoQuery().deleteRow(PropertyId_is);
                GetPhoto dummy_cameraImage = new GetPhoto();
                dummy_cameraImage.setDefaultimage(true);
                dummy_cameraImage.setPropertyId(PropertyId_is);
                // Room Add
                appDatabase.interfaceGetPhotoQuery().insertAll(dummy_cameraImage);

                GetPhoto dummy_galleryImage = new GetPhoto();
                dummy_galleryImage.setDefaultimage(true);
                dummy_galleryImage.setPropertyId(PropertyId_is);
                // Room Add
                appDatabase.interfaceGetPhotoQuery().insertAll(dummy_galleryImage);

                JSONArray jsonArray = new JSONArray();
                for (int x = 0; x < PhotoLatLong_ka.GetPhoto_list_response.size(); x++) {
                    if (!PhotoLatLong_ka.GetPhoto_list_response.get(x).isDefaultimage()) {
                        GetPhoto getPhoto = new GetPhoto();
                        getPhoto.setId(0);
                        //getPhoto.setId(GetPhoto_list_response.get(x).getId());
                        getPhoto.setLogo(PhotoLatLong_ka.GetPhoto_list_response.get(x).getLogo());
                        getPhoto.setTitle(PhotoLatLong_ka.GetPhoto_list_response.get(x).getTitle());
                        getPhoto.setPropertyId(PhotoLatLong_ka.GetPhoto_list_response.get(x).getPropertyId());
                        getPhoto.setDefaultimage(false);
                        getPhoto.setNewimage(false);
                        // Check the Base64Image should be less than 19,80,000 ...
                        if (PhotoLatLong_ka.GetPhoto_list_response.get(x).getLogo().length() < 1980000) {
                            // Room Add
                            appDatabase.interfaceGetPhotoQuery().insertAll(getPhoto);
                        }
                    }
                }

                // move the Measurement Image to offline
                appDatabase.interfaceGetPhotoMeasurmentQuery().deleteRow(PropertyId_is);
                if (Singleton.getInstance().GetImage_list_flat.size() > 0) {
                    GetPhoto_measurment getPhoto = new GetPhoto_measurment();
                    getPhoto.setId(0);
                    getPhoto.setLogo(Singleton.getInstance().GetImage_list_flat.get(0).getLogo());
                    getPhoto.setTitle(Singleton.getInstance().GetImage_list_flat.get(0).getTitle());
                    getPhoto.setPropertyId(Singleton.getInstance().GetImage_list_flat.get(0).getPropertyId());
                    // Check the Base64Image should be less than 19,80,000 ...
                    if (Singleton.getInstance().GetImage_list_flat.get(0).getLogo().length() < 1980000) {
                        // Room Add
                        appDatabase.interfaceGetPhotoMeasurmentQuery().insertAll(getPhoto);
                        // set ID as 0
                        Singleton.getInstance().GetImage_list_flat.get(0).setId(0);
                    }
                }

                // update the offline caseID
                UpdateOfflineStatusEditcase(my_case_id, "2");

                // hide loading and show textnext and move to next screen
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (btn_is.equalsIgnoreCase("next_btn")) {
                            // hit_photo_api > false
                            Singleton.getInstance().hit_photo_api = false;
                        }
                        general.hideloading();
                    }
                }, 1000);

            }
        }, 100);
    }

    // Todo update Start to Edit Inspecction
    private void UpdateOfflineStatusEditcase(String case_id, String updateCaseStatus) {
        if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
            appDatabase.interfaceOfflineDataModelQuery().updateOfflineCaseStatus(updateCaseStatus, case_id);
            // update the case for casemodal
            appDatabase.interfaceCaseQuery().updateCaseStatus(updateCaseStatus,case_id);
        }
    }

    public void internet_check_box(final String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        builder.setTitle("Network Information");
        builder.setMessage("Please check your Internet connection");

        if (enable_offline_button) {
            // Show offline Button
            builder.setNeutralButton("Save offline", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (str.equalsIgnoreCase("start_inspec")) {
                        // TODO - Start_inspec - Add the offline case to the modal
                        // set as offline
                        SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, true);
                        is_offline = SettingsUtils.getInstance().getValue(SettingsUtils.is_offline, false);
                        // updateOfflineDataModel > offlinecase as true
                        appDatabase.interfaceOfflineDataModelQuery().updateOfflineDataModel("" + Singleton.getInstance().aCase.getCaseId(), true);
                        // update_sync_edit - true
                        appDatabase.interfaceOfflineDataModelQuery().update_sync_edit_DataModel("" + Singleton.getInstance().aCase.getCaseId(), true);


                        if (!general.isEmpty(String.valueOf(Singleton.getInstance().aCase.getCaseId()))) {
                            // offline set the caseID and propertyID
                            OflineCase oflineCase = new OflineCase();
                            oflineCase.setCaseId(Singleton.getInstance().aCase.getCaseId());
                            oflineCase.setPropertyId(Singleton.getInstance().aCase.getPropertyId());
                            // Room Delete - OfflineCase
                            appDatabase.interfaceOfflineCaseQuery().deleteRow(oflineCase.getCaseId());
                            // Room Add - OfflineCase
                            appDatabase.interfaceOfflineCaseQuery().insertAll(oflineCase);
                            if (appDatabase.interfaceCaseQuery().getCase_caseID(Singleton.getInstance().aCase.getCaseId()).size() == 0) {
                                // save Initial case
                                save_initial_details(Singleton.getInstance().aCase.getCaseId());
                            }
                        }
                        // offline
                        String str_latvalue, str_longvalue;
                        str_latvalue = PhotoLatLong_ka.latvalue.getText().toString();
                        str_longvalue = PhotoLatLong_ka.longvalue.getText().toString();
                        Singleton.getInstance().latlng_details = str_latvalue + ":" + str_longvalue;
                        // Room - delete lat long
                        appDatabase.interfaceLatLongQuery().deleteRow(Integer.parseInt(my_case_id));
                        LatLongDetails latLongDetails = new LatLongDetails();
                        latLongDetails.setCaseId(Integer.parseInt(my_case_id));
                        latLongDetails.setLatLongDetails(Singleton.getInstance().latlng_details);
                        // Room - add lat long
                        appDatabase.interfaceLatLongQuery().insertAll(latLongDetails);
                        // Photo delete and add
                        save_image_offline("next_btn");
                    } else {
                        // TODO - Edit_inspec - Add the offline case to the modal
                        // set as offline
                        SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, true);
                        is_offline = SettingsUtils.getInstance().getValue(SettingsUtils.is_offline, false);

                        int check_offline_data = appDatabase.interfaceOfflineDataModelQuery().get_case_list("" + Singleton.getInstance().aCase.getCaseId()).size();
                        if (check_offline_data == 0) {
                            ArrayList<DataModel> dataModel_list = (ArrayList<DataModel>) appDatabase.interfaceDataModelQuery().getCase_caseID("" + Singleton.getInstance().aCase.getCaseId());
                            // Get the online modal data
                            DataModel dataModel = dataModel_list.get(0);
                            // Init the offlinecase modal
                            OfflineDataModel offlineDataModel = new OfflineDataModel();
                            offlineDataModel.setCaseId(dataModel.getCaseId());
                            offlineDataModel.setApplicantName(dataModel.getApplicantName());
                            offlineDataModel.setApplicantContactNo(dataModel.getApplicantContactNo());
                            offlineDataModel.setPropertyAddress(dataModel.getPropertyAddress());
                            offlineDataModel.setContactPersonName(dataModel.getContactPersonName());
                            offlineDataModel.setContactPersonNumber(dataModel.getContactPersonNumber());
                            offlineDataModel.setBankName(dataModel.getBankName());
                            offlineDataModel.setBankBranchName(dataModel.getBankBranchName());
                            offlineDataModel.setBankId(dataModel.getBankId());
                            offlineDataModel.setAgencyBranchId(dataModel.getAgencyBranchId());
                            offlineDataModel.setPropertyType(dataModel.getPropertyType());
                            offlineDataModel.setTypeID(dataModel.getTypeID());
                            offlineDataModel.setAssignedAt(dataModel.getAssignedAt());
                            offlineDataModel.setReportChecker(dataModel.getReportChecker());
                            offlineDataModel.setStatus(dataModel.getStatus());
                            offlineDataModel.setReportDispatcher(dataModel.getReportDispatcher());
                            offlineDataModel.setFieldStaff(dataModel.getFieldStaff());
                            offlineDataModel.setAssignedTo(dataModel.getAssignedTo());
                            offlineDataModel.setReportMaker(dataModel.getReportMaker());
                            offlineDataModel.setReportFinalizer(dataModel.getReportFinalizer());
                            offlineDataModel.setPropertyId(dataModel.getPropertyId());
                            offlineDataModel.setReportFile(dataModel.getReportFile());
                            offlineDataModel.setReportId(dataModel.getReportId());
                            offlineDataModel.setReportTemplateId(dataModel.getReportTemplateId());
                            offlineDataModel.setSignature1(dataModel.getSignature1());
                            offlineDataModel.setPropertyCategoryId(dataModel.getPropertyCategoryId());
                            offlineDataModel.setSignature2(dataModel.getSignature2());
                            offlineDataModel.setEmployeeName(dataModel.getEmployeeName());
                            offlineDataModel.setRole(dataModel.getRole());
                            offlineDataModel.setStatusId(dataModel.getStatusId());
                            offlineDataModel.setShowoffline(true);
                            offlineDataModel.setOfflinecase(false);
                            offlineDataModel.setIs_property_changed(false);
                            offlineDataModel.setSync_edit(true);
                            // Insert into offline database
                            appDatabase.interfaceOfflineDataModelQuery().insertAll(offlineDataModel);
                        }

                        // updateOfflineDataModel > offlinecase as true
                        appDatabase.interfaceOfflineDataModelQuery().updateOfflineDataModel("" + Singleton.getInstance().aCase.getCaseId(), true);
                        // update_sync_edit - true
                        appDatabase.interfaceOfflineDataModelQuery().update_sync_edit_DataModel("" + Singleton.getInstance().aCase.getCaseId(), true);

                        if (!general.isEmpty(String.valueOf(Singleton.getInstance().aCase.getCaseId()))) {
                            // offline set the caseID and propertyID
                            OflineCase oflineCase = new OflineCase();
                            oflineCase.setCaseId(Singleton.getInstance().aCase.getCaseId());
                            oflineCase.setPropertyId(Singleton.getInstance().aCase.getPropertyId());
                            // Room Delete - OfflineCase
                            appDatabase.interfaceOfflineCaseQuery().deleteRow(oflineCase.getCaseId());
                            // Room Add - OfflineCase
                            appDatabase.interfaceOfflineCaseQuery().insertAll(oflineCase);
                            if (appDatabase.interfaceCaseQuery().getCase_caseID(Singleton.getInstance().aCase.getCaseId()).size() == 0) {
                                // Editcase - save Initial case
                                editcase_save_initial_details(Singleton.getInstance().aCase.getCaseId());
                            }
                        }
                        // offline
                        String str_latvalue, str_longvalue;
                        str_latvalue = PhotoLatLong_ka.latvalue.getText().toString();
                        str_longvalue = PhotoLatLong_ka.longvalue.getText().toString();
                        Singleton.getInstance().latlng_details = str_latvalue + ":" + str_longvalue;
                        // Room - delete lat long
                        appDatabase.interfaceLatLongQuery().deleteRow(Integer.parseInt(my_case_id));
                        LatLongDetails latLongDetails = new LatLongDetails();
                        latLongDetails.setCaseId(Integer.parseInt(my_case_id));
                        latLongDetails.setLatLongDetails(Singleton.getInstance().latlng_details);
                        // Room - add lat long
                        appDatabase.interfaceLatLongQuery().insertAll(latLongDetails);
                        // Photo delete and add
                        save_image_offline("next_btn");
                    }
                    general.CustomToast(PhotoLatLngTab.this.getResources().getString(R.string.case_moved_offline));
                }
            });
        }


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                general.hideloading();
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                general.hideloading();
                Intent i = new Intent(Settings.ACTION_SETTINGS);
                startActivity(i);
                dialog.dismiss();
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void save_initial_details(final int caseid_int) {
        //offline
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //  Case - Room Delete
                appDatabase.interfaceCaseQuery().deleteRow(caseid_int);
                // Case - Room Add
                Singleton.getInstance().aCase.setCaseId(caseid_int);
                appDatabase.interfaceCaseQuery().insertAll(Singleton.getInstance().aCase);

                // Property - Room Delete
                appDatabase.interfacePropertyQuery().deleteRow(caseid_int);
                // Property - Room Add
                Singleton.getInstance().property.setCaseId(caseid_int);
                appDatabase.interfacePropertyQuery().insertAll(Singleton.getInstance().property);

                // IndProperty - Room Delete
                appDatabase.interfaceIndpropertyQuery().deleteRow(caseid_int);
                // IndProperty - Room Add
                Singleton.getInstance().indProperty.setCaseId(caseid_int);
                appDatabase.interfaceIndpropertyQuery().insertAll(Singleton.getInstance().indProperty);

                // IndPropertyValuation - Room Delete
                appDatabase.interfaceIndPropertyValuationQuery().deleteRow(caseid_int);
                // IndPropertyValuation - Room Add
                Singleton.getInstance().indPropertyValuation.setCaseId(caseid_int);
                appDatabase.interfaceIndPropertyValuationQuery().insertAll(Singleton.getInstance().indPropertyValuation);

                // IndPropertyFloor - Room Delete
                appDatabase.interfaceIndPropertyFloorsQuery().deleteRow(caseid_int);
                // IndPropertyFloor - Room Add
                if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                    for (int x = 0; x < Singleton.getInstance().indPropertyFloors.size(); x++) {
                        appDatabase.interfaceIndPropertyFloorsQuery().insertAll(Singleton.getInstance().indPropertyFloors.get(x));
                    }
                }

                // IndPropertyFloorsValuation - Room Delete
                appDatabase.interfaceIndPropertyFloorsValuationQuery().deleteRow(caseid_int);
                // IndPropertyFloorsValuation - Room Add
                if (Singleton.getInstance().indPropertyFloorsValuations.size() > 0) {
                    for (int x = 0; x < Singleton.getInstance().indPropertyFloorsValuations.size(); x++) {
                        appDatabase.interfaceIndPropertyFloorsValuationQuery().insertAll(Singleton.getInstance().indPropertyFloorsValuations.get(x));
                    }
                }

                // IndPropertyFloorsValuation - Room Delete
                appDatabase.interfaceProximityQuery().deleteRow(caseid_int);
                // IndPropertyFloorsValuation - Room Add
                if (Singleton.getInstance().proximities.size() > 0) {
                    for (int x = 0; x < Singleton.getInstance().proximities.size(); x++) {
                        appDatabase.interfaceProximityQuery().insertAll(Singleton.getInstance().proximities.get(x));
                    }
                }

            }
        }, 0);
    }

    private void editcase_save_initial_details(final int caseid_int) {
        //offline
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //  Case - Room Delete
                appDatabase.interfaceCaseQuery().deleteRow(caseid_int);
                // Case - Room Add
                Singleton.getInstance().aCase.setCaseId(caseid_int);
                appDatabase.interfaceCaseQuery().insertAll(Singleton.getInstance().aCase);

                // Property - Room Delete
                appDatabase.interfacePropertyQuery().deleteRow(caseid_int);
                // Property - Room Add
                Singleton.getInstance().property.setCaseId(caseid_int);
                appDatabase.interfacePropertyQuery().insertAll(Singleton.getInstance().property);

                // IndProperty - Room Delete
                appDatabase.interfaceIndpropertyQuery().deleteRow(caseid_int);
                // IndProperty - Room Add
                Singleton.getInstance().indProperty.setCaseId(caseid_int);
                appDatabase.interfaceIndpropertyQuery().insertAll(Singleton.getInstance().indProperty);

                // IndPropertyValuation - Room Delete
                appDatabase.interfaceIndPropertyValuationQuery().deleteRow(caseid_int);
                // IndPropertyValuation - Room Add
                Singleton.getInstance().indPropertyValuation.setCaseId(caseid_int);
                appDatabase.interfaceIndPropertyValuationQuery().insertAll(Singleton.getInstance().indPropertyValuation);

                // IndPropertyFloor - Room Delete
                appDatabase.interfaceIndPropertyFloorsQuery().deleteRow(caseid_int);
                // IndPropertyFloor - Room Add
                if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                    for (int x = 0; x < Singleton.getInstance().indPropertyFloors.size(); x++) {
                        appDatabase.interfaceIndPropertyFloorsQuery().insertAll(Singleton.getInstance().indPropertyFloors.get(x));
                    }
                }

                // IndPropertyFloorsValuation - Room Delete
                appDatabase.interfaceIndPropertyFloorsValuationQuery().deleteRow(caseid_int);
                // IndPropertyFloorsValuation - Room Add
                if (Singleton.getInstance().indPropertyFloorsValuations.size() > 0) {
                    for (int x = 0; x < Singleton.getInstance().indPropertyFloorsValuations.size(); x++) {
                        appDatabase.interfaceIndPropertyFloorsValuationQuery().insertAll(Singleton.getInstance().indPropertyFloorsValuations.get(x));
                    }
                }

                // IndPropertyFloorsValuation - Room Delete
                appDatabase.interfaceProximityQuery().deleteRow(caseid_int);
                // IndPropertyFloorsValuation - Room Add
                if (Singleton.getInstance().proximities.size() > 0) {
                    for (int x = 0; x < Singleton.getInstance().proximities.size(); x++) {
                        /*if (x > 1) {
                                // TODO - set ID as 0 from Thrid row
                            }*/
                        Singleton.getInstance().proximities.get(x).setId(0);
                        appDatabase.interfaceProximityQuery().insertAll(Singleton.getInstance().proximities.get(x));
                    }
                }

            }
        }, 0);
    }


}
