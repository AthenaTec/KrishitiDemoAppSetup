package com.realappraiser.gharvalue.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.realappraiser.gharvalue.AppDatabase;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.model.OfflineDataModel;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kaptas on 22/2/18.
 */

public class OfflineCaseCheckboxAdapter extends RecyclerView.Adapter<OfflineCaseCheckboxAdapter.ViewHolder> {

    private final ArrayList<OfflineDataModel> dataModels;
    private final Activity mContext;
    private General general;

    private ViewHolder holder = null;
    boolean[] mCheckedState;
    private String Checkbox_text = "";
    int count = 0;
    private SparseBooleanArray mChecked = new SparseBooleanArray();
    public String offlinecase_count;
    public AppDatabase appDatabase;

    public OfflineCaseCheckboxAdapter(Activity offline, ArrayList<OfflineDataModel> datamodels, String selectedId) {
        this.mContext = offline;
        this.dataModels = datamodels;

        mCheckedState = new boolean[dataModels.size()];
        SettingsUtils.init(mContext);
        offlinecase_count = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_OFFLINECASE_COUNT, "");
        appDatabase = AppDatabase.getAppDatabase(mContext);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public OfflineCaseCheckboxAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        general = new General(mContext);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offlinecase_checkadapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final OfflineCaseCheckboxAdapter.ViewHolder holder, final int position) {
        ViewHolder rowHolder;
        if (holder instanceof ViewHolder) {
            rowHolder = (ViewHolder) holder;
        }

        String caseId = dataModels.get(position).getCaseId();
        if (!general.isEmpty(caseId)) {
            holder.offline_caseid.setText("Case Id : " + caseId + ",    ");
        } else {
            holder.offline_caseid.setText(R.string.dot);
        }

        String applicantName = dataModels.get(position).getApplicantName();
        if (!general.isEmpty(applicantName)) {
            holder.offline_personname.setText(applicantName);
        } else {
            holder.offline_personname.setText(R.string.dot);
        }
        //  holder.offline_personname.setText("");

        String address = dataModels.get(position).getPropertyAddress();
        if (!general.isEmpty(address)) {
            holder.offlineaddress.setText(address);
        } else {
            holder.offlineaddress.setText(R.string.dot);
        }

        String bank = dataModels.get(position).getBankName();
        if (!general.isEmpty(bank)) {
            holder.offlinebankname.setText(bank);
        } else {
            holder.offlinebankname.setText(R.string.dot);
        }

        if (mCheckedState[position])
            holder.offline_caseid.setChecked(true);
        else
            holder.offline_caseid.setChecked(false);

        holder.linearlaycheck.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseValueOf")
            @Override
            public void onClick(View v) {

                if (!mCheckedState[position]) {

                    int offlineapicount = 0, offlineadaptercount = 0;
                    if (!general.isEmpty(offlinecase_count)) {
                        offlineapicount = Integer.parseInt(offlinecase_count);
                        offlineadaptercount = offlineDataModelAdaptercount();
                    }

                    if (offlineadaptercount <= offlineapicount) {
                        holder.offline_caseid.setChecked(true);
                        mCheckedState[position] = true;
                    } else {
                        holder.offline_caseid.setChecked(false);
                        general.CustomToast(mContext.getResources().getString(R.string.alreadyexceeded));
                    }
                } else {
                    holder.offline_caseid.setChecked(false);
                    mCheckedState[position] = false;
                }
            }
        });

        /******Check previous data has been checked or not******/
        /*for (int i = 0; i < Singleton.getInstance().mCheckPosition.size(); i++) {
            mChecked.put(Singleton.getInstance().mCheckPosition.get(i), true);
        }*/

        holder.offline_caseid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    int offlineapicount = 0, offlineadaptercount = 0;
                    if (!general.isEmpty(offlinecase_count)) {
                        offlineapicount = Integer.parseInt(offlinecase_count);
                        offlineadaptercount = offlineDataModelAdaptercount();
                    }

                    if (offlineadaptercount <= offlineapicount) {

                        // AddSelectValue.add(sampleTests.get(position).toString());
                        if (!Singleton.getInstance().AddSelectValue.contains(dataModels.get(position).getCaseId())) {
                            Singleton.getInstance().AddSelectValue.add(dataModels.get(position).getCaseId());
                            Singleton.getInstance().mCheckPosition.add(position);
                            if (!Singleton.getInstance().mCheckPosition.contains(position)) {
                            }
                        }

                        mChecked.put(position, isChecked);
                        if (isAllValuesChecked()) {
                            holder.offline_caseid.setChecked(isChecked);
                        /*if (MyApplication.getAppContext() != null) {
                            AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
                            OfflineDataModel dataModel = dataModels.get(position);
                            dataModel.setOfflinecase(true);
                            //appDatabase.interfaceOfflineDataModelQuery().updateData(dataModel);

                            long value = appDatabase.interfaceOfflineDataModelQuery().updateOfflineDataModel(dataModels.get(position).getCaseId(), true);
                            Log.e("update", value + "");
                        }*/
                        }

                    } else {
                        holder.offline_caseid.setChecked(false);
                        general.CustomToast(mContext.getResources().getString(R.string.alreadyexceeded));
                    }
                    //OfflineCasesPipeSymbol();
                } else {
                    // Singleton.getInstance().CheckboxSelection.remove(complaints);
                    if (Singleton.getInstance().mCheckPosition.contains(position)) {

                        for (int j = 0; j < Singleton.getInstance().mCheckPosition.size(); j++) {
                            int checkedList = Singleton.getInstance().mCheckPosition.get(j);
                            if (checkedList == position) {
                                Singleton.getInstance().mCheckPosition.remove(j);
                            }
                        }

                        mChecked.delete(position);
                        holder.offline_caseid.setChecked(isChecked);

                        /*if (MyApplication.getAppContext() != null) {
                            AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());

                            OfflineDataModel dataModel = dataModels.get(position);
                            dataModel.setOfflinecase(false);

                            //   appDatabase.interfaceOfflineDataModelQuery().updateData(dataModel);
                            long value = appDatabase.interfaceOfflineDataModelQuery().updateOfflineDataModel(dataModels.get(position).getCaseId(), false);
                            Log.e("update", value + "");
                        }*/
                    }

                    if (Singleton.getInstance().AddSelectValue.contains(dataModels.get(position).getCaseId())) {

                        for (int i = 0; i < Singleton.getInstance().AddSelectValue.size(); i++) {
                            String actual = Singleton.getInstance().AddSelectValue.get(i).toString();
                            String selected = dataModels.get(position).getCaseId();

                            if (selected.equalsIgnoreCase(actual)) {
                                Singleton.getInstance().AddSelectValue.remove(i);
                                //OfflineCasesPipeSymbol();
                            }
                        }

                        /*sampleTestssPipe();*/
                    }
                }

            }
        });

        holder.offline_caseid.setChecked((mChecked.get(position) == true ? true : false));

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox offline_caseid;
        private final TextView offline_personname, offlineaddress, offlinebankname;
        private final LinearLayout linearlaycheck;

        public ViewHolder(View itemView) {
            super(itemView);
            offline_caseid = (CheckBox) itemView.findViewById(R.id.offline_caseid);
            offline_personname = (TextView) itemView.findViewById(R.id.offline_personname);
            offlineaddress = (TextView) itemView.findViewById(R.id.offlineaddress);
            offlinebankname = (TextView) itemView.findViewById(R.id.offlinebankname);
            linearlaycheck = (LinearLayout) itemView.findViewById(R.id.linearlaycheck);
            offline_caseid.setTypeface(general.regulartypeface());
            offline_personname.setTypeface(general.regulartypeface());
            offlineaddress.setTypeface(general.regulartypeface());
            offlinebankname.setTypeface(general.regulartypeface());
            Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
            offline_caseid.setTypeface(boldTypeface);

            offline_caseid.setTypeface(general.regulartypeface());
            offline_personname.setTypeface(general.regulartypeface());
            offline_caseid.setTag(itemView);
            offline_personname.setTag(itemView);
        }
    }


    private void OfflineCasesPipeSymbol() {
        StringBuilder strbul = new StringBuilder();
        Iterator<String> iterator = Singleton.getInstance().AddSelectValue.iterator();
        //Iterator<String> iterator = AddSelectValue.iterator();
        while (iterator.hasNext()) {
            strbul.append(iterator.next());
            if (iterator.hasNext()) {
                strbul.append("|");
            }
        }
        String complaints = strbul.toString();
        Log.d("test OMG------>", complaints);
        SharedPreferences preferences = mContext.getSharedPreferences("SelectOfflineCase", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("checkboxitem", complaints); // Storing string
        editor.apply(); // commit changes
        Singleton.getInstance().CheckboxSelection.add(complaints);

    }

    protected boolean isAllValuesChecked() {
        for (int i = 0; i < count; i++) {
            if (!mChecked.get(i)) {
                return false;
            }
        }
        return true;
    }

    private int offlineDataModelAdaptercount() {
        ArrayList<OfflineDataModel> dataModel_offline = new ArrayList<>();
        if (appDatabase.interfaceOfflineDataModelQuery().getDataModal_offlinecase(true).size() > 0) {
            dataModel_offline = (ArrayList<OfflineDataModel>) appDatabase.interfaceOfflineDataModelQuery().getDataModal_offlinecase(true);
        }
        int datacount = dataModel_offline.size();
        int addvalue = Singleton.getInstance().AddSelectValue.size() + 1;
        int total = datacount + addvalue;
        return total;
    }
}
