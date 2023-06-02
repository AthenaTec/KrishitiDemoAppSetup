package com.realappraiser.gharvalue.fragments;

import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.realappraiser.gharvalue.R;

/**
 * Created by kaptas on 19/12/17.
 */

public class OtherDetailsFragment_ka extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.otherdetails_fragment, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                OtherDetails_ka fragment_design = new OtherDetails_ka();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.tabcontent, fragment_design).commitAllowingStateLoss();
            }
        }, 50);


        return view;

    }
}
