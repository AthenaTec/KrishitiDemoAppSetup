package com.realappraiser.gharvalue.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.communicator.DataModel;

import java.util.ArrayList;

/**
 * Created by kaptas on 21/12/17.
 */

@SuppressWarnings("ALL")
public class PropertyTypeAdapter extends RecyclerView.Adapter<PropertyTypeAdapter.RecyclerViewHolder> {

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView label;
        private RadioButton radioButton;

        RecyclerViewHolder(View view) {
            super(view);
            label = (TextView) view.findViewById(R.id.label);
            radioButton = (RadioButton) view.findViewById(R.id.radio_button);
        }

    }

    private ArrayList<DataModel> propertyTypeList;
    private String property_caseid="", property_type="";
    private Context context;
    private int selectedPosition = -1;


    public PropertyTypeAdapter(Context context, String property_type, String property_caseid, ArrayList<DataModel> propertyTypeList) {
        this.propertyTypeList = propertyTypeList;
        this.context = context;
        this.property_caseid = property_caseid;
        this.property_type = property_type;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row_property_type, viewGroup, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int i) {

        holder.label.setText(propertyTypeList.get(i).getName());

        //check the radio button if both position and selectedPosition matches
        holder.radioButton.setChecked(i == selectedPosition);

        //Set the position tag to both radio button and label
        holder.radioButton.setTag(i);
        holder.label.setTag(i);
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCheckChanged(v);
            }
        });

        holder.label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCheckChanged(v);
            }


        });

        if(selectedPosition==-1) {
            String name = propertyTypeList.get(i).getName();
            String propertyId = propertyTypeList.get(i).getTypeOfPropertyId();
            if (property_type.equalsIgnoreCase(name)) {
                selectedPosition = (Integer) holder.radioButton.getTag();
                holder.radioButton.setChecked(i == selectedPosition);
            }
            else if(property_type.equalsIgnoreCase(propertyId)) {
                selectedPosition = (Integer) holder.radioButton.getTag();
                holder.radioButton.setChecked(i == selectedPosition);
            }
        }

    }

    //On selecting any view set the current position to selectedPositon and notify adapter
    private void itemCheckChanged(View v) {
        selectedPosition = (Integer) v.getTag();
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return (null != propertyTypeList ? propertyTypeList.size() : 0);
    }

    //Return the selectedPosition item
    public String getSelectedItem() {
        if (selectedPosition != -1) {
           // Toast.makeText(context, "Selected Item : " + propertyTypeList.get(selectedPosition).getName(), Toast.LENGTH_SHORT).show();
            return propertyTypeList.get(selectedPosition).getTypeOfPropertyId();
        }
        return "";
    }

    //Delete the selected position from the arrayList
    public void deleteSelectedPosition() {
        if (selectedPosition != -1) {
            propertyTypeList.remove(selectedPosition);
            selectedPosition = -1;//after removing selectedPosition set it back to -1
            notifyDataSetChanged();
        }
    }
}
