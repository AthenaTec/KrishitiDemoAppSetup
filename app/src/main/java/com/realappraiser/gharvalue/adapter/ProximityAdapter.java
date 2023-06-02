package com.realappraiser.gharvalue.adapter;

import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.communicator.DataResponse;
import com.realappraiser.gharvalue.communicator.JsonRequestData;
import com.realappraiser.gharvalue.communicator.RequestParam;
import com.realappraiser.gharvalue.communicator.ResponseParser;
import com.realappraiser.gharvalue.communicator.TaskCompleteListener;
import com.realappraiser.gharvalue.communicator.WebserviceCommunicator;
import com.realappraiser.gharvalue.model.Proximity;
import com.realappraiser.gharvalue.model.ProximitySpinner;
import com.realappraiser.gharvalue.utils.Connectivity;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;

/**
 * Created by kaptas on 28/12/17.
 */

@SuppressWarnings("ALL")
public class ProximityAdapter extends RecyclerView.Adapter<ProximityAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Proximity> steps;
    private General general;
    private String msg = "", info = "";

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView plus, minus;
        EditText edittext_name_head_1, edittext_km_1;
        Spinner spinner_proximities_1;
        final Proximity stepsModel = new Proximity();


        public ViewHolder(View itemView) {
            super(itemView);
            plus = (ImageView) itemView.findViewById(R.id.add_proximity_1);
            minus = (ImageView) itemView.findViewById(R.id.minus_proximity_1);
            edittext_name_head_1 = (EditText) itemView.findViewById(R.id.edittext_name_head_1);
            edittext_km_1 = (EditText) itemView.findViewById(R.id.edittext_km_1);
            spinner_proximities_1 = (Spinner) itemView.findViewById(R.id.spinner_proximities_1);
            edittext_name_head_1.setTypeface(General.regularTypeface());
            edittext_km_1.setTypeface(General.regularTypeface());


            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int position = getAdapterPosition();
                        String Id = String.valueOf(steps.get(position).getId());
                        String proximity_Id = String.valueOf(steps.get(position).getProximityId());
                        if (steps.get(position).getId() != 0 && steps.get(position).getProximityId() != 0) {
                            RemoveProximityWebservice(Id, proximity_Id, position);
                        }

                        steps.remove(position);
                        Singleton.getInstance().proximities.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, steps.size());
                        notifyDataSetChanged();


                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            });

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        int position = getAdapterPosition();
                        boolean checked = checkValidation();


                        if (checked) {
                            if (position < 4) {
                                /*******Add the Proximity Row******/
                                final Proximity stepsModel = new Proximity();
                                steps.add(position + 1, stepsModel);

                                if (position < Singleton.getInstance().proximities.size()) {

                                    Singleton.getInstance().proximities.add(position + 1, stepsModel);
                                } else {
                                    Singleton.getInstance().proximities.add(stepsModel);
                                }
                                notifyItemInserted(position + 1);
                                general.CustomToast(context.getResources().getString(R.string.proximity_added));

                                if (position < steps.size()) {
                                    if (position < steps.size() - 1) {
                                        if (position < 2) {
                                            minus.setVisibility(View.INVISIBLE);
                                            plus.setVisibility(View.INVISIBLE);
                                        } else {
                                            minus.setVisibility(View.VISIBLE);
                                            plus.setVisibility(View.INVISIBLE);

                                        }
                                    } else {
                                        Log.d("pos", "< than stepssize");
                                    }
                                }
                                /*****Add the Proximity Row Ended*********/
                            } else {
                                general.CustomToast(context.getResources().getString(R.string.proximity_reached));
                            }
                        } else {
                            general.CustomToast(context.getResources().getString(R.string.enter_name_dis));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            edittext_name_head_1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    try {

                        final Proximity stepsModel = steps.get(getAdapterPosition());
                        stepsModel.setProximityName(s.toString());
                        steps.set(getAdapterPosition(), stepsModel);
                        int position = getAdapterPosition();
                   /* Singleton.getInstance().proximities.set(getAdapterPosition(), stepsModel);*/
                        if (position < Singleton.getInstance().proximities.size()) {
                            Singleton.getInstance().proximities.set(position, stepsModel);
                        } else {
                            Singleton.getInstance().proximities.add(stepsModel);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    //  steps.set(getAdapterPosition(), s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            edittext_km_1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    try {
                        final Proximity stepsModel = steps.get(getAdapterPosition());
                        stepsModel.setProximityDistance(s.toString());
                        steps.set(getAdapterPosition(), stepsModel);
                        //  steps.set(getAdapterPosition(), s.toString());
                        int position = getAdapterPosition();
                   /* Singleton.getInstance().proximities.set(getAdapterPosition(), stepsModel);*/
                        if (position < Singleton.getInstance().proximities.size()) {
                            Singleton.getInstance().proximities.set(position, stepsModel);
                        } else {
                            Singleton.getInstance().proximities.add(stepsModel);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            ArrayAdapter<ProximitySpinner> adapterProximity = new ArrayAdapter<>(context,
                    R.layout.row_spinner_item, Singleton.getInstance().proximities_list);
            adapterProximity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_proximities_1.setAdapter(adapterProximity);

            spinner_proximities_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    try {
                        int select_id = (int) spinner_proximities_1.getSelectedItemId();
                        int proximity_id = Singleton.getInstance().proximities_list.get(i).getProximityId();

                        if (getAdapterPosition() > 1) {
                            if ((proximity_id == 5)) {
//                                general.CustomToast("Already Railway is chosen");
                                spinner_proximities_1.setSelection(0);
                            } else if ((proximity_id == 1)) {
//                                general.CustomToast("Already Bus Stand is chosen");
                                spinner_proximities_1.setSelection(0);
                            } else {

                                if (proximity_id == 4) {
                                    final Proximity stepsModel = steps.get(getAdapterPosition());
                                    stepsModel.setProximityId(Singleton.getInstance().proximities_list.get(i).getProximityId());
                                    String name = SettingsUtils.getInstance().getValue(SettingsUtils.Case_BankBranch, "");
                                    stepsModel.setProximityName(name);

                                    steps.set(getAdapterPosition(), stepsModel);
                                    int position = getAdapterPosition();
                                    /* Singleton.getInstance().proximities.set(getAdapterPosition(), stepsModel);*/
                                    if (position < Singleton.getInstance().proximities.size()) {
                                        Singleton.getInstance().proximities.set(position, stepsModel);
                                    } else {
                                        Singleton.getInstance().proximities.add(stepsModel);
                                    }
                                    notifyItemChanged(position);
                                    notifyDataSetChanged();

                                } else {
                                    final Proximity stepsModel = steps.get(getAdapterPosition());
                                    stepsModel.setProximityId(Singleton.getInstance().proximities_list.get(i).getProximityId());
                                    steps.set(getAdapterPosition(), stepsModel);
                                    int position = getAdapterPosition();
                                    /* Singleton.getInstance().proximities.set(getAdapterPosition(), stepsModel);*/
                                    if (position < Singleton.getInstance().proximities.size()) {
                                        Singleton.getInstance().proximities.set(position, stepsModel);
                                    } else {
                                        Singleton.getInstance().proximities.add(stepsModel);
                                    }
                                }

                            }
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    /*else {
                        final Proximity stepsModel = steps.get(getAdapterPosition());
                        stepsModel.setProximityId(Singleton.getInstance().proximities_list.get(i).getProximityId());
                        steps.set(getAdapterPosition(), stepsModel);
                        Singleton.getInstance().proximities.set(getAdapterPosition(), stepsModel);
                    }*/
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        }
    }

    public ProximityAdapter(ArrayList<Proximity> steps, Context context) {
        this.steps = steps;
        this.context = context;
        general = new General((Activity) context);
        SettingsUtils.init(context);

    }


    @Override
    public int getItemCount() {
        return steps.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.proximity_row_item, viewGroup, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            int x = holder.getLayoutPosition();

            if (position == 0) {
                holder.minus.setVisibility(View.INVISIBLE);
                holder.plus.setVisibility(View.INVISIBLE);
            }

            if (steps.size() <= 2) {
                if (position == 1) {
                    holder.minus.setVisibility(View.INVISIBLE);
                    holder.plus.setVisibility(View.VISIBLE);
                }
            } else {

                int minus1 = steps.size() - 1;
                int minus2 = steps.size() - 2;
                if (position <= minus2) {
                    if (position < 2) {
                        holder.minus.setVisibility(View.INVISIBLE);
                        holder.plus.setVisibility(View.INVISIBLE);
                    } else {
                        holder.minus.setVisibility(View.VISIBLE);
                        holder.plus.setVisibility(View.INVISIBLE);
                    }
                } else if (position == minus1) {
                    holder.minus.setVisibility(View.VISIBLE);
                    holder.plus.setVisibility(View.VISIBLE);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        //  if (steps.get(x).getProximityName().length() > 0 || steps.get(x).getProximityId().length() > 0) {
        // if(steps.get(x).length() > 0) {

        try {
            if (Singleton.getInstance().proximities.size() > 0) {
                String valdis = steps.get(position).getProximityDistance();

                if (steps.get(position).getProximityId() != 0) {
                    holder.spinner_proximities_1.setSelection(steps.get(position).getProximityId());
                } else {

                    //Select Spinner default
                    ProximitySpinnerIdSelection(position, holder.spinner_proximities_1);
                    //holder.spinner_proximities_1.setSelection(0);
                }

                if (steps.get(position).getProximityName() != null)
                    holder.edittext_name_head_1.setText(steps.get(position).getProximityName());
                else
                    holder.edittext_name_head_1.setText("");
                if (steps.get(position).getProximityDistance() != null)
                    holder.edittext_km_1.setText(steps.get(position).getProximityDistance());
                else
                    holder.edittext_km_1.setText("");

                if (position < 2) {

                    if (position == 0)
                        if (steps.get(position).getProximityId() != 5)
                            holder.spinner_proximities_1.setSelection(5);
                    if (position == 1)
                        if (steps.get(position).getProximityId() != 1)
                            holder.spinner_proximities_1.setSelection(1);

                    holder.spinner_proximities_1.setEnabled(false);
                } else {
                    holder.spinner_proximities_1.setEnabled(true);
                }

            } else {
                holder.edittext_name_head_1.setText(null);
                holder.edittext_name_head_1.setHint("Name");
                holder.edittext_name_head_1.requestFocus();

                holder.edittext_km_1.setText(null);
                holder.edittext_km_1.setHint("Distance");
                holder.edittext_km_1.requestFocus();

                int id = steps.get(position).getProximityId();
                if (steps.get(position).getProximityId() != 0)
                    holder.spinner_proximities_1.setSelection(steps.get(position).getProximityId());
                else
                    holder.spinner_proximities_1.setSelection(0);
                if (position < 2) {
                    holder.spinner_proximities_1.setEnabled(false);
                } else {
                    holder.spinner_proximities_1.setEnabled(true);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public ArrayList<Proximity> getStepList() {
        return steps;
    }

    /********
     * Proximity Remove on minus API call
     * ********/
    private void RemoveProximityWebservice(String id, String proximityid, int position) {
        if (Connectivity.isConnected(context)) {
            InitiateDeleteProximityTask(id, proximityid, position);
        } else {
            // Connectivity.showNoConnectionDialog(context);
        }
    }

    private void InitiateDeleteProximityTask(String id, final String proximityid, final int position) {

        try {
            String case_id = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

            String url = general.ApiBaseUrl() + SettingsUtils.RemoveProximity;
            JsonRequestData requestData = new JsonRequestData();
            requestData.setUrl(url);
            requestData.setId(id);
            requestData.setCaseId(case_id);
            requestData.setProximityId(proximityid);
            requestData.setRequestBody(RequestParam.DeleteProximityonMinus(requestData));

            WebserviceCommunicator webserviceTask = new WebserviceCommunicator(context, requestData, SettingsUtils.DELETE);
            webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                @Override
                public void onTaskComplete(JsonRequestData requestData) {
                    parseDeleteProximityResponse(requestData.getResponse(), position, proximityid);
                }
            });
            webserviceTask.execute();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void parseDeleteProximityResponse(String response, int position, String proximityid) {

        try {
            DataResponse dataResponse = ResponseParser.parseRemoveProximityResponse(response);
            String result = "";
            if (response != null) {
                result = dataResponse.status;
                msg = dataResponse.msg;
                info = dataResponse.info;
            }

            if (result != null) {
                if (result.equals("1")) {

                    if (Singleton.getInstance().proximities != null) {
                        if (Singleton.getInstance().proximities.size() > 0) {
                            for (int i = 0; i < Singleton.getInstance().proximities.size(); i++) {
                                String id = String.valueOf(Singleton.getInstance().proximities.get(i).getProximityId());
                                if (id.equalsIgnoreCase(proximityid)) {
                                    Singleton.getInstance().proximities.remove(i);
                                }
                            }
                        }
                    }

                    int size = Singleton.getInstance().proximities.size();
                    general.CustomToast(info);


                } else if (result.equals("2")) {
                    general.CustomToast(msg);
                } else if (result.equals("0")) {
                    general.CustomToast(msg);
                }
            } else {
                general.CustomToast(context.getResources().getString(R.string.serverProblem));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /******Check validation on plus Button, i.e, for proximity row adding*******/
    private boolean checkValidation() {
        boolean checkValidation = false;
        try {
            if (steps.size() > 0) {
                for (int i = 0; i < steps.size(); i++) {
                    Proximity stepsModel = steps.get(i);
                    int proximityid = stepsModel.getProximityId();
                    if (general.isEmpty(String.valueOf(stepsModel.getProximityId()))) {
                        checkValidation = false;
                        break;
                    } else if (!general.isEmpty(String.valueOf(stepsModel.getProximityId())) && (proximityid == 0)) {
                        checkValidation = false;
                        break;
                    } else {
                        if ((general.isEmpty(stepsModel.getProximityName())) && general.isEmpty(stepsModel.getProximityDistance())) {
                            // ProximityName and ProximityDistance is both are empty
                            checkValidation = false;
                        } else {
                            checkValidation = true;
                        }
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return checkValidation;
    }


    /********Spinner default selection after position 1*******/
    private void ProximitySpinnerIdSelection(int position, Spinner proximity_spinner) {

        try {
            if (position > 1) {

                if (position < steps.size()) {
                    if (position == 2) {
                        proximity_spinner.setSelection(2);
                        proximity_spinner.setEnabled(true);
                        Proximity proximity = steps.get(position);
                        proximity.setProximityId(2);
                        steps.set(position, proximity);

                        if (position < Singleton.getInstance().proximities.size()) {
                            Singleton.getInstance().proximities.set(position, proximity);
                        } else {
                            Singleton.getInstance().proximities.add(proximity);
                        }

                    }
                    if (position == 3) {
                        proximity_spinner.setSelection(3);
                        proximity_spinner.setEnabled(true);
                        Proximity proximity = steps.get(position);
                        proximity.setProximityId(3);
                        steps.set(position, proximity);

                        if (position < Singleton.getInstance().proximities.size()) {
                            Singleton.getInstance().proximities.set(position, proximity);
                        } else {
                            Singleton.getInstance().proximities.add(proximity);
                        }


                    }
                    if (position == 4) {
                        String proximityname = SettingsUtils.getInstance().getValue(SettingsUtils.Case_BankBranch, "");
                        proximity_spinner.setSelection(4);
                        proximity_spinner.setEnabled(true);
                        Proximity proximity = steps.get(position);
                        proximity.setProximityId(4);
                        proximity.setProximityName(proximityname);
                        proximity.setProximityDistance("");
                        steps.set(position, proximity);

                        if (position < Singleton.getInstance().proximities.size()) {
                            Singleton.getInstance().proximities.set(position, proximity);
                        } else {
                            Singleton.getInstance().proximities.add(proximity);
                        }

                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
