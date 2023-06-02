package com.realappraiser.gharvalue.communicator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

@SuppressWarnings("ALL")
@SuppressLint("Registered")
public class ConnectionDetector extends Activity {

    private Context _context;
    private boolean connected = false;

    public ConnectionDetector(Context context) {
        this._context = context;
    }

    /**
     * Detecting network availability thorough out application
     */
    public boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // we are connected to a network
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState() == NetworkInfo.State.CONNECTED
                || connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        return connected;
    }

    /**
     * Alert view process thorough out application
     */
    @SuppressWarnings("deprecation")
    public void showAlertDialog(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        alertDialog.setTitle(title);

        alertDialog.setMessage(message);

        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
            }
        });

        alertDialog.show();

    }

}
