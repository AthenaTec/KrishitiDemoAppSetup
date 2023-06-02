package com.realappraiser.gharvalue.fragments;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.utils.SettingsUtils;

/**
 * Created by kaptas on 19/12/17.
 */

public class OtherDetailsFragment extends Fragment {

    public static boolean isVisible;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.otherdetails_fragment, container, false);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                OtherDetails fragment_design = new OtherDetails();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.tabcontent, fragment_design).commitAllowingStateLoss();
            }
        }, 100);


        return view;

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
        } else {
            isVisible = false;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        if (SettingsUtils.getInstance().getValue(SettingsUtils.is_local, false)) {
            isVisible = true;
        }
        super.onStart();
    }

    @Override
    public void onPause() {
        isVisible = false;
        super.onPause();
    }

    @Override
    public void onDestroy() {
        isVisible = false;
        OtherDetails._handler.removeMessages(0);
        super.onDestroy();
    }
}
