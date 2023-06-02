package com.realappraiser.gharvalue.handler;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.realappraiser.gharvalue.activities.ReportActivity;
import com.realappraiser.gharvalue.activities.SplashActivity;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Sivasankar on 11-12-2017.
 */

@SuppressWarnings("ALL")
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final Activity myContext;
    private final String LINE_SEPARATOR = "\n";
    int versionCode;
    String versionName;

    public ExceptionHandler(Activity activity) {
        myContext = activity;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void uncaughtException(Thread t, Throwable exception) {


        PackageInfo pInfo = null;
        try {
            pInfo = myContext.getPackageManager().getPackageInfo(myContext.getPackageName(), 0);
            versionCode = pInfo.versionCode;
            versionName = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        StringBuilder errorReport = new StringBuilder();
        StringBuilder deviceinfo = new StringBuilder();
        //  errorReport.append("************ CAUSE OF ERROR ************\n\n");
        errorReport.append(stackTrace.toString());

        // errorReport.append("\n************ DEVICE INFORMATION ***********\n");
        deviceinfo.append("Brand: ");
        deviceinfo.append(Build.BRAND);
        deviceinfo.append(LINE_SEPARATOR);
        deviceinfo.append("Device: ");
        deviceinfo.append(Build.DEVICE);
        deviceinfo.append(LINE_SEPARATOR);
        deviceinfo.append("Model: ");
        deviceinfo.append(Build.MODEL);
        deviceinfo.append(LINE_SEPARATOR);
        deviceinfo.append("Id: ");
        deviceinfo.append(Build.ID);
        deviceinfo.append(LINE_SEPARATOR);
        deviceinfo.append("Product: ");
        deviceinfo.append(Build.PRODUCT);
        deviceinfo.append(LINE_SEPARATOR);
        //   errorReport.append("\n************ FIRMWARE ************\n");
        deviceinfo.append("SDK: ");
        //noinspection deprecation
        deviceinfo.append(Build.VERSION.SDK);
        deviceinfo.append(LINE_SEPARATOR);
        deviceinfo.append("Release: ");
        deviceinfo.append(Build.VERSION.RELEASE);
        deviceinfo.append(LINE_SEPARATOR);
        deviceinfo.append("Incremental: ");
        deviceinfo.append(Build.VERSION.INCREMENTAL);
        deviceinfo.append(LINE_SEPARATOR);

        deviceinfo.append("versionCode: ");
        deviceinfo.append(versionCode);
        deviceinfo.append(LINE_SEPARATOR);

        deviceinfo.append("versionName: ");
        deviceinfo.append(versionName);
        deviceinfo.append(LINE_SEPARATOR);

        Log.e("errorReport.toString()", "" + errorReport.toString());
        Log.e("deviceinfo.toString()", "" + deviceinfo.toString());

        Intent intent = new Intent(myContext, ReportActivity.class);
        intent.putExtra("error", errorReport.toString());
        intent.putExtra("device_info", deviceinfo.toString());
        intent.putExtra("versionCode", "" + versionCode);
        myContext.startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());
        // kill off the crashed app
        System.exit(10);


    }
}
