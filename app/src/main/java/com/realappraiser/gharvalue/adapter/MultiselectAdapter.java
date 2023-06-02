package com.realappraiser.gharvalue.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.model.FloorUsage;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;

/**
 * Created by kaptas on 30/12/17.
 */

@SuppressWarnings("ALL")
public class MultiselectAdapter extends RecyclerView.Adapter<MultiselectAdapter.RecyclerViewHolder> {

    private SparseBooleanArray mCheckStates = new SparseBooleanArray();
    private LayoutInflater inflater;
    int count = 0;
    boolean[] mCheckedState;
    ArrayList<String> floorschecked = new ArrayList<>();

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView label;
        private CheckBox checkbox_text;

        RecyclerViewHolder(View view) {
            super(view);
            label = (TextView) view.findViewById(R.id.label);
            checkbox_text = (CheckBox) view.findViewById(R.id.checkbox_text);
        }
    }

    private ArrayList<FloorUsage> floorUsageArrayList;
    private String property_caseid = "", property_type = "";
    private Context context;
    private int selectedPosition = -1;
    private IndPropertyFloor stepsModel;
    private ArrayList<String> floorusage;


    public MultiselectAdapter(Context context, ArrayList<FloorUsage> floorUsageArrayList, ArrayList<String> floorusage) {
        this.floorUsageArrayList = floorUsageArrayList;
        this.context = context;
        count = floorUsageArrayList.size();
        mCheckedState = new boolean[floorUsageArrayList.size()];
        this.floorusage = floorusage;
    }

    @Override
    public MultiselectAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.checkbox_row_list, viewGroup, false);
        return new RecyclerViewHolder(v);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int i) {

        holder.checkbox_text.setText(floorUsageArrayList.get(i).getName());
        holder.checkbox_text.setTag(i);

        String spinnerid = String.valueOf(floorUsageArrayList.get(i).getPropertyFloorUsageId());
        if (floorusage.size() > 0) {
            Singleton.getInstance().floor_usage.get(i).setFloorUsage(floorusage);
            for (int k = 0; k < floorusage.size(); k++) {
                String storedid = floorusage.get(k);
                if (spinnerid.equalsIgnoreCase(storedid)) {
                    mCheckStates.put(i, true);
                }

            }
        }

       /* for (int j = 0; j < Singleton.getInstance().floor_usage.get(i).getFloorUsage().size(); j++) {
            String selectid = Singleton.getInstance().floor_usage.get(i).getFloorUsage().get(j);

            String id = String.valueOf(floorUsageArrayList.get(i).getPropertyFloorUsageId());
            System.err.println("multiselect" + selectid + ",,floor " + id);
            if (id.equalsIgnoreCase(Singleton.getInstance().floor_usage.get(i).getFloorUsage().get(j))) {
                //   holder.filter_check_item.setChecked(mCheckStates.get(position, true));
                mCheckStates.put(i, true);

            }
        }*/

        if (isAllValuesChecked()) {
            holder.checkbox_text.setChecked(true);
        }

        holder.checkbox_text.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    // String id = String.valueOf(CategoryModel.get((Integer) buttonView.getTag()).id);
                   /* String id = String.valueOf(floorUsageArrayList.get(i).getPropertyFloorUsageId());
                    if (!Singleton.getInstance().floor_usage.get(i).getFloorUsage().contains(id)) {
                        floorschecked.add(String.valueOf(floorUsageArrayList.get(i).getPropertyFloorUsageId()));
                        Singleton.getInstance().floor_usage.get(i).setFloorUsage(floorschecked);
                    }*/

                    String spinnerid = String.valueOf(floorUsageArrayList.get(i).getPropertyFloorUsageId());
                    if (!floorusage.contains(spinnerid)) {
                        floorusage.add(spinnerid);
                        Singleton.getInstance().floor_usage.get(i).setFloorUsage(floorusage);
                    }

                    mCheckStates.put(i, isChecked);
                    if (isAllValuesChecked()) {
                        holder.checkbox_text.setChecked(isChecked);
                    }
                } else {


                    String spinnerid = String.valueOf(floorUsageArrayList.get(i).getPropertyFloorUsageId());
                //    if (floorusage.size() > 0) {
                        if (floorusage.contains(spinnerid)) {

                            for (int k = 0; k < floorusage.size(); k++) {
                                String storedid = floorusage.get(k);
                                if (spinnerid.equalsIgnoreCase(storedid)) {
                                    floorusage.remove(k);
                                }
                            }
                            Singleton.getInstance().floor_usage.get(i).setFloorUsage(floorusage);

                            mCheckStates.delete(i);
                            holder.checkbox_text.setChecked(isChecked);
                        }
                   // }

                    /*String id = String.valueOf(floorUsageArrayList.get(i).getPropertyFloorUsageId());
                    if (Singleton.getInstance().floor_usage.get(i).getFloorUsage().contains(id)) {

                        for (int j = 0; j < Singleton.getInstance().floor_usage.get(i).getFloorUsage().size(); j++) {

                            if (id.equalsIgnoreCase(Singleton.getInstance().floor_usage.get(i).getFloorUsage().get(j))) {

                                Singleton.getInstance().floor_usage.get(i).getFloorUsage().remove(j);

                            }
                        }
                        mCheckStates.delete(i);
                        holder.checkbox_text.setChecked(isChecked);
                    }*/


                }
            }
        });

        holder.checkbox_text.setChecked((mCheckStates.get(i) == true ? true : false));

    }

    //On selecting any view set the current position to selectedPositon and notify adapter
    private void itemCheckChanged(View v) {
        selectedPosition = (Integer) v.getTag();
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return (null != floorUsageArrayList ? floorUsageArrayList.size() : 0);
    }

    //Return the selectedPosition item
    public String getSelectedItem() {
        if (selectedPosition != -1) {
            // Toast.makeText(context, "Selected Item : " + propertyTypeList.get(selectedPosition).getName(), Toast.LENGTH_SHORT).show();
            return floorUsageArrayList.get(selectedPosition).getName();
        }
        return "";
    }

    //Delete the selected position from the arrayList
    public void deleteSelectedPosition() {
        if (selectedPosition != -1) {
            floorUsageArrayList.remove(selectedPosition);
            selectedPosition = -1;//after removing selectedPosition set it back to -1
            notifyDataSetChanged();
        }
    }

    protected boolean isAllValuesChecked() {
        for (int i = 0; i < count; i++) {
            if (!mCheckStates.get(i)) {
                return false;
            }
        }
        return true;
    }
}
