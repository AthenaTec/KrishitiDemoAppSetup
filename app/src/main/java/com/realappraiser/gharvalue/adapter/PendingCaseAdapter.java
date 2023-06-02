package com.realappraiser.gharvalue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.model.PendingCaseModel;
import com.realappraiser.gharvalue.utils.General;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PendingCaseAdapter extends RecyclerView.Adapter<PendingCaseAdapter.PendingCaseViewHolder>{

    private List<PendingCaseModel.Datum> pendingList;
    private Context context;

    public PendingCaseAdapter(List<PendingCaseModel.Datum> pendingList, Context context) {
        this.pendingList = pendingList;
        this.context = context;
    }

    @NonNull
    @Override
    public PendingCaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_case_adapter, parent, false);
        return new PendingCaseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingCaseViewHolder holder, int pos) {
        PendingCaseModel.Datum item = pendingList.get(pos);
        holder.case_person_name.setText(item.getApplicantName());
        holder.case_mobile.setText(item.getApplicantContactNo());
        holder.case_addressloc.setText(item.getLocality());
        holder.case_bank.setText(item.getBankName());

        String assigned_date = General.AssignedPendingDate(item.getAssignedAt());
        String assigned_time = General.AssignedPendingTime(item.getAssignedAt());
        holder.case_assigned_date.setText(assigned_date);
        holder.case_assigned_time.setText(assigned_time.toUpperCase());
    }

    @Override
    public int getItemCount() {
        return pendingList.size();
    }

    class PendingCaseViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.case_person_name)
        TextView case_person_name;
        @BindView(R.id.case_mobile)
        TextView case_mobile;
        @BindView(R.id.case_addressloc)
        TextView case_addressloc;
        @BindView(R.id.case_bank)
        TextView case_bank;
        @BindView(R.id.case_assigned_time)
        TextView case_assigned_time;
        @BindView(R.id.case_assigned_date)
        TextView case_assigned_date;

        public PendingCaseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
