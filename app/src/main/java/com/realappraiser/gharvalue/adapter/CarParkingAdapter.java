package com.realappraiser.gharvalue.adapter;

import android.annotation.SuppressLint;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.model.CarParking;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 02-01-2018.
 */

@SuppressWarnings("ALL")
public class CarParkingAdapter extends RecyclerView.Adapter<CarParkingAdapter.ViewHolder> {

    private final FragmentActivity mContext;
    private final General general;
    String selectedId = "";
    ArrayList<CarParking> floors_list = new ArrayList<>();
    List<String> selectedId_list = new ArrayList<>();

    public CarParkingAdapter(FragmentActivity activity, ArrayList<CarParking> floors_, String selected_) {
        this.mContext = activity;
        this.floors_list = floors_;
        this.selectedId = selected_;
        general = new General(mContext);
        /* clear the array in selected id and name first */
        Singleton.getInstance().CarParking_id.clear();
        Singleton.getInstance().CarParking_name.clear();
        if (!general.isEmpty(selectedId)) {
            this.selectedId_list = new ArrayList<String>(Arrays.asList(selectedId.split(",")));
            Log.e("selectedId", "notempty::  " + selectedId_list);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiselect_checkbox_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.checkbox.setText(floors_list.get(position).getName());

        if (selectedId_list.size() > 0) {
            if (selectedId_list.toString().contains("" + floors_list.get(position).getCarParkingId())) {
                for (int x = 0; x < selectedId_list.size(); x++) {
                    int one_by_one_id = Integer.parseInt(selectedId_list.get(x));
                    if (floors_list.get(position).getCarParkingId() == one_by_one_id) {
                        holder.checkbox.setChecked(true);
                        Singleton.getInstance().CarParking_id.add(floors_list.get(position).getCarParkingId());
                        Singleton.getInstance().CarParking_name.add(floors_list.get(position).getName());
                    }
                }
            }
        }

        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseValueOf")
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    // Add the ID in the list
                    Singleton.getInstance().CarParking_id.add(floors_list.get(position).getCarParkingId());
                    Singleton.getInstance().CarParking_name.add(floors_list.get(position).getName());
                } else {
                    // Delete the ID in the list
                    Singleton.getInstance().CarParking_id.remove(new Integer(floors_list.get(position).getCarParkingId()));
                    Singleton.getInstance().CarParking_name.remove(floors_list.get(position).getName());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return floors_list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final CheckBox checkbox;

        public ViewHolder(View itemView) {
            super(itemView);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
            checkbox.setTypeface(general.regulartypeface());
        }
    }

}



