package com.realappraiser.gharvalue.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.realappraiser.gharvalue.MyApplication;

/**
 * Created by kaptas on 13/2/18.
 */

public class ConnectionReceiver extends BroadcastReceiver {

    public static ConnectionReceiverListener connectionReceiverListener;
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    public ConnectionReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent arg1) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (connectionReceiverListener != null) {
            connectionReceiverListener.onNetworkConnectionChanged(isConnected);
        }

        /*****Toast message for internet connection******/
        String status = getConnectivityStatusString(context);
        if (status != null)
            Toast.makeText(context, status, Toast.LENGTH_LONG).show();

    }

    public static boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }


    public interface ConnectionReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }


    /*******Connectivity status toast********/
    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }


    public static String getConnectivityStatusString(Context context) {
        int conn = getConnectivityStatus(context);
        String status = null;
        if (conn == TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == TYPE_NOT_CONNECTED) {
            status = null;
        }

        /*else if (conn == TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }*/
        return status;
    }

}


