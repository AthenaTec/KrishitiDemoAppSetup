package com.realappraiser.gharvalue.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.communicator.DataModel;
import com.realappraiser.gharvalue.utils.General;

import java.util.ArrayList;

/**
 * Created by kaptas on 18/12/17.
 */

@SuppressWarnings("ALL")
public class CloseCaseAdapter extends RecyclerView.Adapter<CloseCaseAdapter.ViewHolder> {

    private final ArrayList<DataModel> dataModels;
    private final Activity mContext;
    private General general;

    public CloseCaseAdapter(Activity closed, ArrayList<DataModel> datamodels) {
        this.mContext = closed;
        this.dataModels = datamodels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        general = new General(mContext);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.close_case_adapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String caseId = dataModels.get(position).getCaseId();
        if (!general.isEmpty(caseId)) {
            holder.close_caseid.setText(caseId);
        } else {
            holder.close_caseid.setText(R.string.dot);
        }

        String applicantName = dataModels.get(position).getApplicantName();
        if (!general.isEmpty(applicantName)) {
            holder.close_personname.setText(applicantName);
        } else {
            holder.close_personname.setText(R.string.dot);
        }

        String contactPersonNumber = dataModels.get(position).getContactPersonNumber();
        if (!general.isEmpty(contactPersonNumber)) {
            holder.close_mobile.setText(contactPersonNumber);
        } else {
            holder.close_mobile.setText(R.string.dot);
        }

        String bankName = dataModels.get(position).getBankName();
        if (!general.isEmpty(bankName)) {
            holder.close_bank.setText(bankName);
        } else {
            holder.close_bank.setText(R.string.dot);
        }

        String assignedAt = dataModels.get(position).getCreatedOn();
        if (!general.isEmpty(assignedAt)) {
            String assigned_date = General.AssignedClosedDate(dataModels.get(position).getCreatedOn());
            holder.assigned_date.setText(assigned_date);
            holder.closed_date.setText(assigned_date);
        } else {
            holder.assigned_date.setText(R.string.dot);
        }



    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView close_caseid;
        private final TextView close_personname;
        private final TextView close_mobile;
        private final TextView close_bank;
        private final TextView assigned_date;
        private final TextView closed_date;

        public ViewHolder(View itemView) {
            super(itemView);
            close_caseid = (TextView) itemView.findViewById(R.id.close_caseid);
            close_personname = (TextView) itemView.findViewById(R.id.close_personname);
            close_mobile = (TextView) itemView.findViewById(R.id.close_mobile);
            close_bank = (TextView) itemView.findViewById(R.id.close_bank);
            assigned_date = (TextView) itemView.findViewById(R.id.assigned_date);
            closed_date = (TextView) itemView.findViewById(R.id.closed_date);
            close_caseid.setTypeface(general.regulartypeface());
            close_personname.setTypeface(general.regulartypeface());
            close_mobile.setTypeface(general.regulartypeface());
            close_bank.setTypeface(general.regulartypeface());
            assigned_date.setTypeface(general.regulartypeface());
            closed_date.setTypeface(general.regulartypeface());
        }
    }
}
