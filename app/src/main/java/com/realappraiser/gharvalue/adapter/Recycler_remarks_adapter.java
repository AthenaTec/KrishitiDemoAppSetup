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
import com.realappraiser.gharvalue.model.Remarks;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 04-01-2018.
 */

@SuppressWarnings("ALL")
public class Recycler_remarks_adapter extends RecyclerView.Adapter<Recycler_remarks_adapter.ViewHolder> {

    private final FragmentActivity mContext;
    private General general;
    private ArrayList<Remarks> remarks_list = new ArrayList<>();
    private String selectedId = "";
    private List<String> selectedId_list = new ArrayList<>();

    public Recycler_remarks_adapter(FragmentActivity activity, ArrayList<Remarks> remarks_, String selectedId_) {
        this.mContext = activity;
        this.remarks_list = remarks_;
        this.selectedId = selectedId_;
        Singleton.getInstance().Remarks_Id.clear();
        general = new General(mContext);

        if (!general.isEmpty(selectedId)) {
            this.selectedId_list = new ArrayList<String>(Arrays.asList(selectedId.split(",")));
            Log.e("selectedId", "notempty::  " + selectedId_list);
        }
    }


    @Override
    public Recycler_remarks_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiselect_remarks_checkbox_row, parent, false);
        Recycler_remarks_adapter.ViewHolder viewHolder = new Recycler_remarks_adapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(Recycler_remarks_adapter.ViewHolder holder, final int position) {

        holder.checkbox.setText(remarks_list.get(position).getRemarksName());

        if (selectedId_list.size() > 0) {
            if (selectedId_list.toString().contains("" + remarks_list.get(position).getRemarksId())) {
                for (int x = 0; x < selectedId_list.size(); x++) {
                    int one_by_one_id = Integer.parseInt(selectedId_list.get(x));
                    if (remarks_list.get(position).getRemarksId() == one_by_one_id) {
                        holder.checkbox.setChecked(true);
                        Singleton.getInstance().Remarks_Id.add(remarks_list.get(position).getRemarksId());
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
                    Singleton.getInstance().Remarks_Id.add(remarks_list.get(position).getRemarksId());

                } else {
                    // Delete the ID in the list
                    Singleton.getInstance().Remarks_Id.remove(new Integer(remarks_list.get(position).getRemarksId()));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return remarks_list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkbox;

        public ViewHolder(View itemView) {
            super(itemView);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
            checkbox.setTypeface(general.regulartypeface());
        }
    }

}
