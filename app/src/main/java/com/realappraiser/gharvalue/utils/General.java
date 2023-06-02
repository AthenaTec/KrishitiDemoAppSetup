package com.realappraiser.gharvalue.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;

import android.util.Base64;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.realappraiser.gharvalue.AppDatabase;
import com.realappraiser.gharvalue.MyApplication;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.activities.LoginActivity;
import com.realappraiser.gharvalue.model.Case;
import com.realappraiser.gharvalue.model.IndProperty;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.model.IndPropertyFloorsValuation;
import com.realappraiser.gharvalue.model.IndPropertyValuation;
import com.realappraiser.gharvalue.model.OfflineDataModel;
import com.realappraiser.gharvalue.model.Property;
import com.realappraiser.gharvalue.worker.GeoUpdate;
import com.realappraiser.gharvalue.worker.LocationTrackerApi;
import com.realappraiser.gharvalue.worker.OreoLocation;
import com.realappraiser.gharvalue.worker.WorkerManager;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by Suganya on Dec17.
 */

@SuppressWarnings("ALL")
public class General implements OnPageChangeListener, OnLoadCompleteListener,
        OnPageErrorListener {

    private Activity mContext;
    private Context mcontext;
    @SuppressLint("StaticFieldLeak")
    private static Activity Context;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private String api_base_url;
    public static boolean isfirsttab_active = false;
    public static boolean issecondtab_active = false;


    private static final String TAG = General.class.getSimpleName();
    public String SAMPLE_FILE = "sample.pdf";
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";

    public PDFView pdfView;
    public Integer pageNumber = 0;
    public String pdfFileName, stringToConvert;

    private String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 3;
    String uriSting;

    public General(Activity context) {
        this.mContext = context;
        this.Context = context;
        SettingsUtils.init(context);
    }

    public static void statusbarColor(Activity context) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public String ApiBaseUrl() {
        api_base_url = SettingsUtils.getInstance().getValue(SettingsUtils.API_BASE_URL, "");
        return api_base_url;
    }

    public Typeface boldtypeface() {
        Typeface bold = Typeface.createFromAsset(mContext.getAssets(), "fonts/Ubuntu.Bold.ttf");
        return bold;
    }

    public static void customToast(String msg, Context context) {
        Toast toast = Toast.makeText(context,
                msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 180);
        toast.show();
    }

    public Typeface mediumtypeface() {
        Typeface medium = Typeface.createFromAsset(mContext.getAssets(), "fonts/Ubuntu.Medium.ttf");
        return medium;
    }

    public Typeface regulartypeface() {
        Typeface regular = Typeface.createFromAsset(mContext.getAssets(), "fonts/Ubuntu.Regular.ttf");
        return regular;
    }

    public static Typeface regularTypeface() {
        Typeface regular = Typeface.createFromAsset(Context.getAssets(), "fonts/Ubuntu.Regular.ttf");
        return regular;
    }

    public Typeface LightTypeface() {
        Typeface regular = Typeface.createFromAsset(Context.getAssets(), "fonts/Ubuntu-L.ttf");
        return regular;
    }

    public Typeface RobotoLightTypeface() {
        Typeface regular = Typeface.createFromAsset(Context.getAssets(), "fonts/Roboto-Thin.ttf");
        return regular;
    }

    public void PhoneCall(String mobile) {

        String telephone = "tel:" + mobile;

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse(telephone));
        mContext.startActivity(callIntent);
    }

    public static Dialog connectionDialog;

    public static void showloading(Context context) {
        if (context != null) {
            connectionDialog = new Dialog(context, R.style.CustomAlertDialog);
            connectionDialog.setContentView(R.layout.dia_loading);
            connectionDialog.setCancelable(false);
            connectionDialog.show();
        }
    }

    public static void hideloading() {
        if (connectionDialog != null) {
            if (connectionDialog.isShowing()) {
                connectionDialog.dismiss();
            }
        }
    }


    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0 || string.equalsIgnoreCase("null");
    }


    public static boolean isEmptyString(String string) {
        return string == null || string.length() == 0 || string.equalsIgnoreCase("null");
    }

    public String NumOnly(String numbers) {
        numbers = numbers.replaceAll("\\p{Alpha}", "");
        return numbers;
    }

    public float convertToint(String str) {
        int rate_int = 0;
        if (!isEmpty(str)) {
            rate_int = Integer.valueOf(str);
        }
        return rate_int;
    }


    public float convertTofloat(String str) {
        float rate_float = 0;
        if (!isEmpty(str)) {
            if (str.contains(",")) {
                str = str.replace(",", "");
            }
            if (str.equalsIgnoreCase(".")) {
                rate_float = Float.valueOf("0.");
            } else {
                rate_float = Float.valueOf(str);
            }
        }
        return rate_float;
    }

    public int convertToRoundoff(float str) {
        int round = 0;
        if (!isEmpty(String.valueOf(str))) {
            round = Math.round(str);
        }
        return round;
    }

    public int convertToInt(float str) {
        int sum_total = 0;
        if (!isEmpty(String.valueOf(str))) {
            sum_total = (int) str;
        }
        return sum_total;
    }


    public int getTotalConstructionValue(ArrayList<IndPropertyFloorsValuation> floor_list) {
        float sumtotal = 0;
        int sum_total = 0;

        for (int i = 0; i < floor_list.size(); i++) {
            if (!isEmpty(floor_list.get(i).getDocumentConstrValue())) {
                String numOnly = NumOnly(floor_list.get(i).getDocumentConstrValue());
                if (!numOnly.equalsIgnoreCase("")) {
                    float rate_float = convertTofloat(numOnly);
                    sumtotal = sumtotal + rate_float;
                }
            }
        }

        sum_total = (int) sumtotal;

        return sum_total;
    }

    public int getTotalConstructionActualValue(ArrayList<IndPropertyFloorsValuation> floor_list) {
        float sumtotal = 0;
        int sum_total = 0;

        for (int i = 0; i < floor_list.size(); i++) {
            if (!isEmpty(floor_list.get(i).getMeasuredConstrValue())) {
                String numOnly = NumOnly(floor_list.get(i).getMeasuredConstrValue());
                if (!numOnly.equalsIgnoreCase("")) {
                    float rate_float = convertTofloat(numOnly);
                    sumtotal = sumtotal + rate_float;
                }
            }
        }

        sum_total = (int) sumtotal;

        return sum_total;
    }

    public int getDocSumValue(ArrayList<IndPropertyFloor> floor_list) {
        float sumtotal = 0;
        int sum_total = 0;


        for (int i = 0; i < floor_list.size(); i++) {
            if (!isEmpty(floor_list.get(i).getDocumentFloorArea())) {
                String numOnly = NumOnly(floor_list.get(i).getDocumentFloorArea());
                if (!numOnly.equalsIgnoreCase("")) {
                    float areaf = Float.valueOf(numOnly);
                    /*int area = Integer.valueOf(numOnly);*/
                    //   int area = Integer.valueOf(floor_list.get(i).getDocumentFloorArea());
                    sumtotal = sumtotal + areaf;
                    sum_total = (int) sumtotal;
                }
            }
        }


        return sum_total;
    }

    public float getDocSumValue_float(ArrayList<IndPropertyFloor> floor_list) {
        float sumtotal = 0;
        //int sum_total = 0;


        for (int i = 0; i < floor_list.size(); i++) {
            if (!isEmpty(floor_list.get(i).getDocumentFloorArea())) {
                String numOnly = NumOnly(floor_list.get(i).getDocumentFloorArea());
                if (!numOnly.equalsIgnoreCase("")) {
                    float areaf = Float.valueOf(numOnly);
                    /*int area = Integer.valueOf(numOnly);*/
                    //   int area = Integer.valueOf(floor_list.get(i).getDocumentFloorArea());
                    sumtotal = sumtotal + areaf;
                    //sum_total = (int) sumtotal;
                }
            }
        }


        //return sum_total;
        return sumtotal;
    }


    public int getMeasureSumValue(ArrayList<IndPropertyFloor> floor_list) {
        float sumtotal = 0;
        int sum_total = 0;

        for (int i = 0; i < floor_list.size(); i++) {
            if (!isEmpty(floor_list.get(i).getMeasuredFloorArea())) {
                String numOnly = NumOnly(floor_list.get(i).getMeasuredFloorArea());
                if (!numOnly.equalsIgnoreCase("")) {
                    float areaf = Float.valueOf(numOnly);
                    /*int area = Integer.valueOf(numOnly);*/
                    sumtotal = sumtotal + areaf;
                    sum_total = (int) sumtotal;
                }
            }
        }
        return sum_total;
    }

    public float getMeasureSumValue_float(ArrayList<IndPropertyFloor> floor_list) {
        float sumtotal = 0;
        //int sum_total = 0;

        for (int i = 0; i < floor_list.size(); i++) {
            if (!isEmpty(floor_list.get(i).getMeasuredFloorArea())) {
                String numOnly = NumOnly(floor_list.get(i).getMeasuredFloorArea());
                if (!numOnly.equalsIgnoreCase("")) {
                    float areaf = Float.valueOf(numOnly);
                    /*int area = Integer.valueOf(numOnly);*/
                    sumtotal = sumtotal + areaf;
                    //sum_total = (int) sumtotal;
                }
            }
        }
        //return sum_total;
        return sumtotal;
    }

    public int getCompletedSumValue(ArrayList<IndPropertyFloor> floor_list) {
        int sumtotal = 0;
        if (floor_list.size() > 0) {
            for (int i = 0; i < floor_list.size(); i++) {
                String numOnly = NumOnly(String.valueOf(floor_list.get(i).getPercentageCompletion()));
                if (!numOnly.equalsIgnoreCase("")) {
                    int area = Integer.valueOf(numOnly);
                    sumtotal = sumtotal + area;
                }

            }
            sumtotal = sumtotal / floor_list.size();
        }
        return sumtotal;
    }

    public int getAgeLifeValue(int age) {
        int sumtotal = 60;
        sumtotal = sumtotal - age;
        return sumtotal;
    }


    public boolean isNetworkAvailable() {
        Activity activity = Context;
        NetworkInfo nwInfo = ((ConnectivityManager) Context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return nwInfo != null && nwInfo.isConnectedOrConnecting();
    }

    public int getcutom_value(int total_int, int realizable_int) {
        int round_off = 0;
        float total_float = 0;
        float realizable_float = 0;
        total_float = Float.valueOf(total_int);
        realizable_float = Float.valueOf(realizable_int);
        float get_total = (total_float * (realizable_float / 100));
        round_off = Math.round(get_total);
        return round_off;
    }


    public String remove_array_brac_and_space(String str) {
        String str_trim = "";
        if (str.length() > 0) {
            str_trim = str
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")  //remove the left bracket
                    .replace(" ", "")  //remove the left bracket
                    .trim();
        }
        return str_trim;
    }

    public void exitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AlertDialogCustom));
        builder.setMessage(mContext.getResources().getString(R.string.exit))
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
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

    public void savechangesDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AlertDialogCustom));
        builder.setMessage(mContext.getResources().getString(R.string.nochanges))
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((Activity) context).finish();
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


    public static void report_bug(Activity activity) {
        /* Bug report and send the bug to api */
        // Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(activity));
    }


    public void exitBackDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AlertDialogCustom));
        builder.setMessage(mContext.getResources().getString(R.string.exit))
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        mContext.finish();
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


    public void LogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AlertDialogCustom));
        builder.setMessage("Are you sure you want to Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Singleton.getInstance().longitude = 0.0;
                        Singleton.getInstance().latitude = 0.0;
                        Singleton.getInstance().aCase = new Case();
                        Singleton.getInstance().property = new Property();
                        Singleton.getInstance().indProperty = new IndProperty();
                        Singleton.getInstance().indPropertyValuation = new IndPropertyValuation();
                        Singleton.getInstance().indPropertyFloors = new ArrayList<>();
                        Singleton.getInstance().proximities = new ArrayList<>();
                        Singleton.getInstance().openCaseList.clear();
                        Singleton.getInstance().closeCaseList.clear();
                        Singleton.getInstance().GetImage_list_flat.clear();
                        SettingsUtils.getInstance().putValue(SettingsUtils.KEY_LOGGED_IN, false);
                        AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
                        ArrayList<OfflineDataModel> oflineData = (ArrayList) appDatabase.interfaceOfflineDataModelQuery().getDataModal_offlinecase(true);
                        if (appDatabase == null) {
                            return;
                        }
                        if (oflineData.size() > 0) {
                            General.customToast("Please sync your offline documents before logout!", Context);
                            return;
                        }

                        // Total - 16 DB


                        // Delete - datamodel
                        appDatabase.interfaceDataModelQuery().deleteRow();
                        // Delete - offlinedatamodel
                        appDatabase.interfaceOfflineDataModelQuery().deleteRow();
                        // Delete - casemodal
                        appDatabase.interfaceCaseQuery().deleteRow();
                        // Delete - propertymodal
                        appDatabase.interfacePropertyQuery().deleteRow();
                        // Delete - indpropertymodal
                        appDatabase.interfaceIndpropertyQuery().deleteRow();
                        // Delete - IndPropertyValuationModal
                        appDatabase.interfaceIndPropertyValuationQuery().deleteRow();
                        // Delete - IndPropertyFloorModal
                        appDatabase.interfaceIndPropertyFloorsQuery().deleteRow();
                        // Delete - IndPropertyFloorsValuationModal
                        appDatabase.interfaceIndPropertyFloorsValuationQuery().deleteRow();
                        // Delete - ProximityModal
                        appDatabase.interfaceProximityQuery().deleteRow();
                        // Delete - GetPhotoModel
                        appDatabase.interfaceGetPhotoQuery().deleteRow();
                        // Delete - OflineCase
                        appDatabase.interfaceOfflineCaseQuery().deleteRow();
                        // Delete - Document_list
                        appDatabase.interfaceDocumentListQuery().deleteRow();
                        // Delete - LatLongDetails
                        appDatabase.interfaceLatLongQuery().deleteRow();
                        // Delete - typeofproperty
                        appDatabase.typeofPropertyQuery().deleteRow();
                        // Delete - casedetail
                        appDatabase.daoAccess().deleteRow();
                        // Delete - propertyupdateroomdb
                        appDatabase.propertyUpdateCategory().deleteRow();
                        // Delete - GetPhotoMeasurmentQuery
                        appDatabase.interfaceGetPhotoMeasurmentQuery().deleteRow();

                        Context.finishAffinity();


                        if (new LocationTrackerApi(Context).shareLocation("",
                                SettingsUtils.getInstance().getValue(SettingsUtils.KEY_LOGIN_ID, ""),
                                "Logout", SettingsUtils.Latitudes, SettingsUtils.Longitudes)) {
                            if (Build.VERSION.SDK_INT < 26) {
                                Context.stopService(new Intent(Context, GeoUpdate.class));
                            } else {
                                new OreoLocation(Context).stopOreoLocationUpdates();
                            }
                            new WorkerManager(Context).stopWorker();

                            Intent intent = new Intent(mContext, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                            return;
                        }

                        if (Build.VERSION.SDK_INT < 26) {
                            Context.stopService(new Intent(Context, GeoUpdate.class));
                        } else {
                            new OreoLocation(Context).stopOreoLocationUpdates();
                        }
                        new WorkerManager(Context).stopWorker();
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
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

    public boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(mContext, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(mContext, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }


    /*public void hideSoftKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }*/

    public void onRequestPermissionsResult(int requestCode, String permissions[], final int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            Log.d("Location::", "Permission Granted, Now you can access data.");                          //   Toast.makeText(ServiceCenter.this, "Permission Granted, Now you can access location data.", Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("Location::", "Permission Denied, You cannot access data.");
                        }
                    }
                });
                break;
        }
    }


    public static String Date(String date) {
        String ActualDate = "";
        String ActualTime = "", actualDateTime = "";
        if (!date.isEmpty())
            try {
                @SuppressLint("SimpleDateFormat") DateFormat inputFormats = new SimpleDateFormat("yyyy-MM-dd");
                @SuppressLint("SimpleDateFormat") DateFormat inputFormats2 = new SimpleDateFormat("hh:mm:ss");
                inputFormats.setTimeZone(TimeZone.getTimeZone("UTC"));
                @SuppressLint("SimpleDateFormat") DateFormat outputFormats = new SimpleDateFormat("dd-MMM-yyyy");
                @SuppressLint("SimpleDateFormat") DateFormat outputFormats2 = new SimpleDateFormat("hh:mm a");
                System.out.println(outputFormats.format(inputFormats.parse(date)));
                ActualDate = outputFormats.format(inputFormats.parse(date));
                //    ActualTime = outputFormats2.format(inputFormats2.parse(time));

            } catch (Exception e) {
                e.printStackTrace();
            }

        return ActualDate;
    }

    public static String AssignedDate(String date) {
        String ActualDate = "";
        if (!date.isEmpty())
            try {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormats = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                //  inputFormats.setTimeZone(TimeZone.getTimeZone("UTC"));
                @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormats = new SimpleDateFormat("dd-MMM-yyyy");
                Date datenew = inputFormats.parse(date);
                String dateee = outputFormats.format(datenew);
                System.out.println(outputFormats.format(inputFormats.parse(date)));
                ActualDate = outputFormats.format(inputFormats.parse(date));

            } catch (Exception e) {
                e.printStackTrace();
            }

        return ActualDate;
    }

    public static String AssignedPendingDate(String date) {
        String ActualDate = "";
        if (!date.isEmpty())
            try {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormats = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm:ss");
                //  inputFormats.setTimeZone(TimeZone.getTimeZone("UTC"));
                @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormats = new SimpleDateFormat("dd-MMM-yyyy");
                Date datenew = inputFormats.parse(date);
                String dateee = outputFormats.format(datenew);
                System.out.println(outputFormats.format(inputFormats.parse(date)));
                ActualDate = outputFormats.format(inputFormats.parse(date));

            } catch (Exception e) {
                e.printStackTrace();
            }

        return ActualDate;
    }

    public static String AssignedClosedDate(String date) {
        String ActualDate = "";
        if (!date.isEmpty())
            try {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormats = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                //  inputFormats.setTimeZone(TimeZone.getTimeZone("UTC"));
                @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormats = new SimpleDateFormat("dd-MMM-yy");
                Date datenew = inputFormats.parse(date);
                String dateee = outputFormats.format(datenew);
                System.out.println(outputFormats.format(inputFormats.parse(date)));
                ActualDate = outputFormats.format(inputFormats.parse(date));

            } catch (Exception e) {
                e.printStackTrace();
            }

        return ActualDate;
    }

    public static String AssignedTime(String date) {
        String ActualTime = "";
        if (!date.isEmpty())
            try {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormats = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss");
                @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormats2 = new SimpleDateFormat("hh:mm a");
                ActualTime = outputFormats2.format(inputFormats.parse(date));

            } catch (Exception e) {
                e.printStackTrace();
            }

        return ActualTime;
    }

    public static String AssignedPendingTime(String date) {
        String ActualTime = "";
        if (!date.isEmpty())
            try {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormats = new SimpleDateFormat("dd-mm-yyyy' 'HH:mm:ss");
                @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormats2 = new SimpleDateFormat("hh:mm a");
                ActualTime = outputFormats2.format(inputFormats.parse(date));

            } catch (Exception e) {
                e.printStackTrace();
            }

        return ActualTime;
    }

    public String ConvertDate(String date) {

        String actual_date = "";
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormats = new SimpleDateFormat("dd/MM/yyyy");
            //  inputFormats.setTimeZone(TimeZone.getTimeZone("UTC"));
            @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormats = new SimpleDateFormat("dd/MMM/yyyy");
            Date datenew = inputFormats.parse(date);
            actual_date = outputFormats.format(datenew);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return actual_date;
    }

    public static String CreatedTime(String time) {
        String ActualDate = "";
        String ActualTime = "", actualDateTime = "";
        if (!time.isEmpty())
            try {
                @SuppressLint("SimpleDateFormat") DateFormat inputFormats = new SimpleDateFormat("yyyy-MM-dd");
                @SuppressLint("SimpleDateFormat") DateFormat inputFormats2 = new SimpleDateFormat("hh:mm:ss");
                inputFormats.setTimeZone(TimeZone.getTimeZone("UTC"));
                @SuppressLint("SimpleDateFormat") DateFormat outputFormats = new SimpleDateFormat("dd-MMM-yyyy");
                @SuppressLint("SimpleDateFormat") DateFormat outputFormats2 = new SimpleDateFormat("hh:mm a");
                ActualTime = outputFormats2.format(inputFormats2.parse(time));

            } catch (Exception e) {
                e.printStackTrace();
            }

        return ActualTime;
    }


    public static int compareDateTime48hrsOld(String indate){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = sdf.parse(indate);

            long diff = new Date().getTime() - date.getTime();
            System.out.println ();

            Log.e(TAG, "compareDateTime48hrsOld: Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) );




            return Math.toIntExact(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
//            int days = Days.daysBetween(date1, date2).getDays();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*public DateTimeModel DateandTimeConversion(String date, String time) {

        DateTimeModel dateTimeModel = new DateTimeModel();
        String ActualDate = "";
        String ActualTime = "", actualDateTime = "";
        if (!date.isEmpty())
            try {
                @SuppressLint("SimpleDateFormat") DateFormat inputFormats = new SimpleDateFormat("yyyy-MM-dd");
                @SuppressLint("SimpleDateFormat") DateFormat inputFormats2 = new SimpleDateFormat("hh:mm:ss");
                inputFormats.setTimeZone(TimeZone.getTimeZone("UTC"));
                @SuppressLint("SimpleDateFormat") DateFormat outputFormats = new SimpleDateFormat("dd-MMM-yyyy");
                DateFormat outputFormats2 = new SimpleDateFormat("hh:mm a");
                System.out.println(outputFormats.format(inputFormats.parse(date)));
                ActualDate = outputFormats.format(inputFormats.parse(date));
                ActualTime = outputFormats2.format(inputFormats2.parse(time));
                dateTimeModel.Date = ActualDate;
                dateTimeModel.Time = ActualTime;

            } catch (Exception e) {
                e.printStackTrace();
            }
        return dateTimeModel;
    }*/

    public void CustomToast(String msg) {
        if (mContext != null) {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean setListViewHeightBasedOnItems(ListView accessoriesList) {
        ListAdapter listAdapter = accessoriesList.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, accessoriesList);
                float px = 500 * (accessoriesList.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = accessoriesList.getDividerHeight() *
                    (numberOfItems - 1);
            // Get padding
            int totalPadding = accessoriesList.getPaddingTop() + accessoriesList.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = accessoriesList.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            accessoriesList.setLayoutParams(params);
            accessoriesList.requestLayout();
            return true;

        } else {
            return false;
        }
    }


    /**********
     * PDF Conversion Functiionality from base64 string to pdf file and view it
     * **********/
    public GetFilePathAndStatus getFileFromBase64AndSaveInSDCard(String base64, String filename, String extension, PDFView pdfview) {
        GetFilePathAndStatus getFilePathAndStatus = new GetFilePathAndStatus();
        pdfView = pdfview;
        try {
            byte[] pdfAsBytes = Base64.decode(base64, 0);
            FileOutputStream os;
            os = new FileOutputStream(getReportPath(filename, extension), false);
            os.write(pdfAsBytes);
            os.flush();
            os.close();

            File file = new File(uriSting);
            // OpenPdfFile(file);
            SAMPLE_FILE = file.getName();
            displayFromFile(file);

            getFilePathAndStatus.filStatus = true;
            getFilePathAndStatus.filePath = getReportPath(filename, extension);
            return getFilePathAndStatus;
        } catch (IOException e) {
            e.printStackTrace();
            getFilePathAndStatus.filStatus = false;
            getFilePathAndStatus.filePath = getReportPath(filename, extension);
            return getFilePathAndStatus;
        }
    }

    private void OpenPdfFile(File file) {
        Uri path = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(path, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);

    }

    private void displayFromFile(File FilePath) {
        pdfFileName = SAMPLE_FILE;

        pdfView.fromFile(FilePath)
                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(mContext))
                .load();
    }

    public String getReportPath(String filename, String extension) {
        File file;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
             file = new File(Environment.getExternalStorageDirectory().getPath(), "RealAppraiser/Document");
        }else{
             file = new File(mcontext.getExternalFilesDir("").getPath(), "RealAppraiser/Document");
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        //    uriSting = (file.getAbsolutePath() + "/" + filename + "." + extension);
        uriSting = (file.getAbsolutePath() + "/" + filename);
        return uriSting;

    }

    public static class GetFilePathAndStatus {
        public boolean filStatus;
        public String filePath;
    }

    /*************
     * PDF viewer library functions
     * ************/
    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        mContext.setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());

        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    public void onPageError(int page, Throwable t) {
        Log.e(TAG, "Cannot load page " + page);
    }

    public ArrayList<String> lobby_array() {
        ArrayList<String> lobby_list = new ArrayList<>();
        lobby_list.add("Select");
        lobby_list.add("0");
        lobby_list.add("1");
        lobby_list.add("2");
        lobby_list.add("3");
        lobby_list.add("4");
        lobby_list.add("5");
        lobby_list.add("6");
        lobby_list.add("7");
        lobby_list.add("8");
        lobby_list.add("9");
        lobby_list.add("10");
        return lobby_list;
    }

    public ArrayList<String> NameofSellerInitials_array() {
        ArrayList<String> initial_list = new ArrayList<>();
        initial_list.add("Select");
        initial_list.add("Mr");
        initial_list.add("Mrs");
        initial_list.add("Ms");
        initial_list.add("M/s");
        initial_list.add("Dr.Mr");
        initial_list.add("Dr.Mrs");
        initial_list.add("Dr.Ms");
        initial_list.add("Prof.Mr");
        initial_list.add("Prof.Mrs");
        initial_list.add("Prof.Ms");
        initial_list.add("Rev.Mr");
        initial_list.add("Rev.Mrs");
        initial_list.add("Rev.Ms");
        return initial_list;
    }

    public ArrayList<String> Constructionval_common_array() {
        ArrayList<String> lobby_list = new ArrayList<>();
        lobby_list.add("Select");
        lobby_list.add("50%");
        lobby_list.add("55%");
        lobby_list.add("60%");
        lobby_list.add("65%");
        lobby_list.add("70%");
        lobby_list.add("75%");
        lobby_list.add("80%");
        lobby_list.add("85%");
        lobby_list.add("90%");
        lobby_list.add("95%");
        lobby_list.add("100%");
        lobby_list.add("Custom");
        return lobby_list;
    }

    public ArrayList<String> Constructionval_common_array_carpet() {
        ArrayList<String> lobby_list = new ArrayList<>();
        lobby_list.add("Select");
        lobby_list.add("50%");
        lobby_list.add("55%");
        lobby_list.add("60%");
        lobby_list.add("65%");
        lobby_list.add("70%");
        lobby_list.add("75%");
        lobby_list.add("80%");
        lobby_list.add("85%");
        lobby_list.add("90%");
        lobby_list.add("95%");
        lobby_list.add("100%");
        return lobby_list;
    }

    public ArrayList<String> carpettype_array() {
        ArrayList<String> lobby_list = new ArrayList<>();
        lobby_list.add("Select");
        lobby_list.add("Permissible Area");
        lobby_list.add("Actual Area");
        lobby_list.add("Selected Area");
        return lobby_list;
    }


    public String ReplaceCommaSaveToString(String str) {
        if (!isEmpty(str)) {
            if (str.contains(",")) {
                str = str.replace(",", "");
            }
            return str;
        }
        return str;
    }

    public String DecimalFormattedCommaString(String value) {
        String formattedString = "";
        //Long longval;
        Double longval;
        try {
            if (!isEmpty(value)) {
                String originalString = value;
                if (originalString.contains(",")) {
                    originalString = originalString.replaceAll(",", "");
                }

                //longval = Long.parseLong(originalString);
                longval = Double.parseDouble(originalString);

                /****Convert Value to Separated with comma****/
                DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                //formatter.applyPattern("##,##,##,###");
                formatter.applyPattern("##,##,##,##,##,##,##,###");
                formattedString = formatter.format(longval);
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return formattedString;
    }

    public static boolean GPS_status() {
        LocationManager manager = (LocationManager) MyApplication.getAppContext().getSystemService(Context.LOCATION_SERVICE);
        boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return statusOfGPS;
    }

    public static double getcurrent_latitude(Context context) {

        double latitude = 0;
        double longitude = 0;
        String address = "";
        GPSService mGPSService = new GPSService(context);
        mGPSService.getLocation();

        if (mGPSService.isLocationAvailable == false) {
//            mGPSService.askUserToOpenGPS();
        } else {
            latitude = mGPSService.getLatitude();
            longitude = mGPSService.getLongitude();
//            Log.e("lon and lat", "is:::" + "Latitude:" + latitude + " | Longitude: " + longitude);
            address = mGPSService.getLocationAddress();
//            Log.e("address", "address" + address);
        }
        // make sure you close the gps after using it. Save user's battery power

        mGPSService.closeGPS();
        return latitude;
    }

    public static double getcurrent_longitude(Context context) {

        double latitude = 0;
        double longitude = 0;
        String address = "";
        GPSService mGPSService = new GPSService(context);
        mGPSService.getLocation();

        if (mGPSService.isLocationAvailable == false) {
//            mGPSService.askUserToOpenGPS();
        } else {
            latitude = mGPSService.getLatitude();
            longitude = mGPSService.getLongitude();
//            Log.e("lon and lat", "is:::" + "Latitude:" + latitude + " | Longitude: " + longitude);
            address = mGPSService.getLocationAddress();
//            Log.e("address", "address" + address);
        }
        // make sure you close the gps after using it. Save user's battery power

        mGPSService.closeGPS();
        return longitude;
    }

    /* checking the intrenet connection */
    public static boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            /*Network connecting...*/
            return true;
        } else {
            /*oops!!! no network*/
            Log.d("TAG", "Internet Connection Not Present");
            return false;
        }
    }

    /* Go to setting page -> weather their is no internet connection */
    public static void No_network_connection(final Context context) {
        AlertDialog.Builder alert_build = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlertDialogCustom));
        alert_build.setTitle("Network Information");
        alert_build.setMessage("Please check your Internet connection");
        alert_build.setCancelable(false);
        alert_build.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /* Go to Settings page Intent */
                Intent i = new Intent(Settings.ACTION_SETTINGS);
                context.startActivity(i);
                dialog.dismiss();

            }
        });
        alert_build.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert_show = alert_build.create();
        alert_show.show();
    }


    public ArrayList<String> LoadingBuilup_common_array() {
        ArrayList<String> buildup_list = new ArrayList<>();
        buildup_list.add("Select");
        buildup_list.add("0%");
        buildup_list.add("5%");
        buildup_list.add("10%");
        buildup_list.add("15%");
        buildup_list.add("20%");
        buildup_list.add("25%");
        buildup_list.add("30%");
        buildup_list.add("35%");
        buildup_list.add("40%");
        buildup_list.add("45%");
        buildup_list.add("50%");
        buildup_list.add("55%");
        buildup_list.add("60%");
        buildup_list.add("65%");
        buildup_list.add("70%");
        buildup_list.add("75%");
        return buildup_list;
    }


    public static String getFormattedLocationInDegree(double latitude, double longitude) {
        try {
            int latSeconds = (int) Math.round(latitude * 3600);
            int latDegrees = latSeconds / 3600;
            latSeconds = Math.abs(latSeconds % 3600);
            int latMinutes = latSeconds / 60;
            latSeconds %= 60;

            int longSeconds = (int) Math.round(longitude * 3600);
            int longDegrees = longSeconds / 3600;
            longSeconds = Math.abs(longSeconds % 3600);
            int longMinutes = longSeconds / 60;
            longSeconds %= 60;
            String latDegree = latDegrees >= 0 ? "N" : "S";
            String lonDegrees = longDegrees >= 0 ? "E" : "W";

            return Math.abs(latDegrees) + "°" + latMinutes + "'" + latSeconds
                    + "\"" + latDegree + ":" + Math.abs(longDegrees) + "°" + longMinutes
                    + "'" + longSeconds + "\"" + lonDegrees;
        } catch (Exception e) {
            return "" + String.format("%8.5f", latitude) + "  "
                    + String.format("%8.5f", longitude);
        }
    }

    public boolean isLocationEnabled(Context context2) {
        Activity activity = Context;
        return ((LocationManager) context2.getSystemService(android.content.Context.LOCATION_SERVICE)).isProviderEnabled("gps");
    }



}
