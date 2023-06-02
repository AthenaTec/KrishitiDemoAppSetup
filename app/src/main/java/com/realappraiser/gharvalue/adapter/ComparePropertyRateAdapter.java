package com.realappraiser.gharvalue.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.model.GetPropertyCompareDetailsModel;

import java.util.List;

public class ComparePropertyRateAdapter extends RecyclerView.Adapter<ComparePropertyRateAdapter.PropertyViewHolder>{

    private List<GetPropertyCompareDetailsModel.Datum> propertyRateList;
    private Context context;
    private static final String TAG = ComparePropertyRateAdapter.class.getSimpleName();

    public ComparePropertyRateAdapter(List<GetPropertyCompareDetailsModel.Datum> propertyRateList, Context context) {
        this.propertyRateList = propertyRateList;
        this.context = context;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_compare_property, parent, false);
        return new PropertyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {

        GetPropertyCompareDetailsModel.Datum item = propertyRateList.get(position);

        Log.e(TAG, "onBindViewHolder: "+new Gson().toJson(item));


        holder.tvPropertyId.setText(""+item.getPropertyId()+"");
        holder.tvCaseId.setText(""+item.getCaseId()+"");
        holder.tvDistance.setText(""+item.getDistance()+"");
        holder.tvPropertyAddress.setText(""+item.getPropertyAddress()+"");
        holder.tvLatLng.setText(""+item.getLatitude()+", "+item.getLongitude());
        holder.tvPropertyType.setText(""+item.getName()+"");
        holder.tvRate.setText(""+item.getRate()+"");
        holder.tvPropertyValue.setText(""+item.getTotalPropertyValue());
    }

    @Override
    public int getItemCount() {
        return propertyRateList.size();
    }

    class PropertyViewHolder extends RecyclerView.ViewHolder{
        TextView tvPropertyId,tvCaseId,tvPropertyAddress,
                tvPropertyType,tvLatLng,tvRate,tvPropertyValue,tvDistance;

        public PropertyViewHolder(@NonNull View view) {
            super(view);
            tvPropertyId = view.findViewById(R.id.tvPropertyId);
            tvCaseId = view.findViewById(R.id.tvCaseId);
            tvPropertyAddress = view.findViewById(R.id.tvPropertyAddress);
            tvPropertyType = view.findViewById(R.id.tvPropertyType);
            tvLatLng = view.findViewById(R.id.tvLatLng);
            tvRate = view.findViewById(R.id.tvRate);
            tvPropertyValue = view.findViewById(R.id.tvPropertyValue);
            tvDistance = view.findViewById(R.id.tvDistance);
        }
    }

}
