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
import com.realappraiser.gharvalue.model.PropertyIdentificationChannel;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;

/**
 * Created by kaptas on 2/1/18.
 */

@SuppressWarnings("ALL")
public class PropertyIdentifiedAdapter extends RecyclerView.Adapter<PropertyIdentifiedAdapter.RecyclerViewHolder> {

    private SparseBooleanArray mCheckStates = new SparseBooleanArray();
    private LayoutInflater inflater;
    private int count = 0;
    private boolean[] mCheckedState;

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView label;
        private CheckBox checkbox_text;

        RecyclerViewHolder(View view) {
            super(view);
            label = (TextView) view.findViewById(R.id.label);
            checkbox_text = (CheckBox) view.findViewById(R.id.checkbox_text);
        }
    }

    private ArrayList<PropertyIdentificationChannel> PropertyIdentificationArrayList;
    private Context context;
    private int selectedPosition = -1;


    public PropertyIdentifiedAdapter(Context context, ArrayList<PropertyIdentificationChannel> PropertyIdentificationArrayList) {
        this.PropertyIdentificationArrayList = PropertyIdentificationArrayList;
        this.context = context;
        count = PropertyIdentificationArrayList.size();
        mCheckedState = new boolean[PropertyIdentificationArrayList.size()];
    }

    @Override
    public PropertyIdentifiedAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.checkbox_row_list, viewGroup, false);
        return new RecyclerViewHolder(v);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int i) {

        holder.checkbox_text.setText(PropertyIdentificationArrayList.get(i).getName());
        holder.checkbox_text.setTag(i);
        /*holder.label.setTag(i);*/

        for (int j = 0; j < Singleton.getInstance().property_identified.size(); j++) {
            String selectid = Singleton.getInstance().property_identified.get(j);

            String id = String.valueOf(PropertyIdentificationArrayList.get(i).getPropertyIdentificationChannelId());
            System.err.println("multiselect" + selectid + ",,floor " + id);
            if (id.equalsIgnoreCase(Singleton.getInstance().property_identified.get(j))) {
                //   holder.filter_check_item.setChecked(mCheckStates.get(position, true));
                mCheckStates.put(i, true);

            }
        }

        if (isAllValuesChecked()) {
            holder.checkbox_text.setChecked(true);
        }

        holder.checkbox_text.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    // String id = String.valueOf(CategoryModel.get((Integer) buttonView.getTag()).id);
                    String id = String.valueOf(PropertyIdentificationArrayList.get(i).getPropertyIdentificationChannelId());
                    if (!Singleton.getInstance().property_identified.contains(id)) {
                        Singleton.getInstance().property_identified.add(String.valueOf(PropertyIdentificationArrayList.get(i).getPropertyIdentificationChannelId()));
                    }

                    mCheckStates.put(i, isChecked);
                    if (isAllValuesChecked()) {
                        holder.checkbox_text.setChecked(isChecked);
                    }
                } else {

                    String id = String.valueOf(PropertyIdentificationArrayList.get(i).getPropertyIdentificationChannelId());
                    if (Singleton.getInstance().property_identified.contains(id)) {

                        for (int i = 0; i < Singleton.getInstance().property_identified.size(); i++) {

                            if (id.equalsIgnoreCase(Singleton.getInstance().property_identified.get(i))) {

                                Singleton.getInstance().property_identified.remove(i);

                            }
                        }
                        mCheckStates.delete(i);
                        holder.checkbox_text.setChecked(isChecked);
                    }


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
        return (null != PropertyIdentificationArrayList ? PropertyIdentificationArrayList.size() : 0);
    }

    //Return the selectedPosition item
    public String getSelectedItem() {
        if (selectedPosition != -1) {
            // Toast.makeText(context, "Selected Item : " + propertyTypeList.get(selectedPosition).getName(), Toast.LENGTH_SHORT).show();
            return PropertyIdentificationArrayList.get(selectedPosition).getName();
        }
        return "";
    }

    //Delete the selected position from the arrayList
    public void deleteSelectedPosition() {
        if (selectedPosition != -1) {
            PropertyIdentificationArrayList.remove(selectedPosition);
            selectedPosition = -1;//after removing selectedPosition set it back to -1
            notifyDataSetChanged();
        }
    }

    private boolean isAllValuesChecked() {
        for (int i = 0; i < count; i++) {
            if (!mCheckStates.get(i)) {
                return false;
            }
        }
        return true;
    }
}
