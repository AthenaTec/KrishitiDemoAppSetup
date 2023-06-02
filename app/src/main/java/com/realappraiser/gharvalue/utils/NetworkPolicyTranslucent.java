package com.realappraiser.gharvalue.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.StrictMode;
import android.view.Window;
import android.view.WindowManager;

import com.realappraiser.gharvalue.R;

/**
 * Created by Suganya on 3/8/2017.
 */

@SuppressWarnings("ALL")
public class NetworkPolicyTranslucent {

    private Activity mContext;

    public NetworkPolicyTranslucent(Activity context){
        mContext = context;
    }

    public void MainThreadTranslucent() {
        /******
         * Network on Main thread exception handler
         ******/
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 15) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        /*****
         * Translucent for the status bar top view
         *****/
        Translucent();
    }

    public void Translucent() {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = mContext.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(mContext.getResources().getColor(R.color.colorPrimary));
        }


        /******
         * Screen Orientation changes as per the device size
         * ******/
        if ((mContext.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
        } else if ((mContext.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            mContext.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if ((mContext.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            mContext.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if ((mContext.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
        }
    }
}
