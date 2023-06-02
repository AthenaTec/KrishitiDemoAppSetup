package com.realappraiser.gharvalue.adapter;

import android.annotation.SuppressLint;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.fragments.FragmentValuationBuilding_ka;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.model.IndPropertyFloorsValuation;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;

/**
 * Created by kaptas on 10/1/18.
 */

@SuppressWarnings("ALL")
public class ValuationActualAreaAdapter_ka extends RecyclerView.Adapter<ValuationActualAreaAdapter_ka.ViewHolder> {

    public FragmentActivity mContext;
    public ArrayList<IndPropertyFloor> steps;
    public ArrayList<IndPropertyFloorsValuation> stepsValuation;
    @SuppressLint("StaticFieldLeak")
    public static General general;
    boolean docarea_initial = true;
    boolean actualarea_initial = true;
    boolean age_initial = true;
    public PropertyFloorInternalAdapter propertyFloorInternalAdapter;

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText edittext_actual_rate, edittext_actual_dep_per;
        TextView textview_actual_floor, textview_actual_unit, textview_actual_value, textview_actual_dep_value;

        final IndPropertyFloor stepsModel = new IndPropertyFloor();

        public ViewHolder(final View itemView) {
            super(itemView);

            edittext_actual_rate = (EditText) itemView.findViewById(R.id.edittext_actual_rate);
            edittext_actual_dep_per = (EditText) itemView.findViewById(R.id.edittext_actual_dep_per);
            textview_actual_floor = (TextView) itemView.findViewById(R.id.textview_actual_floor);
            textview_actual_unit = (TextView) itemView.findViewById(R.id.textview_actual_unit);
            textview_actual_value = (TextView) itemView.findViewById(R.id.textview_actual_value);
            textview_actual_dep_value = (TextView) itemView.findViewById(R.id.textview_actual_dep_value);

            edittext_actual_rate.setTypeface(General.regularTypeface());
            edittext_actual_dep_per.setTypeface(General.regularTypeface());
            textview_actual_floor.setTypeface(General.regularTypeface());
            textview_actual_unit.setTypeface(General.regularTypeface());
            textview_actual_value.setTypeface(General.regularTypeface());
            textview_actual_dep_value.setTypeface(General.regularTypeface());

           /* edittext_actual_rate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        // code to execute when EditText loses focus
                        Log.e("onFocus_coming", "onFocus_coming" + hasFocus);
                        // Copy the value to aspercompletion_val
                        String toString = edittext_actual_rate.getText().toString();
                        getMeasuredCalculation(toString, textview_actual_value, textview_actual_dep_value, getAdapterPosition());
                    }
                }
            });*/


            edittext_actual_rate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        edittext_actual_dep_per.setSelection(edittext_actual_dep_per.getText().toString().length());
                    }
                    return false;
                }
            });

            edittext_actual_dep_per.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        edittext_actual_rate.setSelection(edittext_actual_rate.getText().toString().length());
                    }
                    return false;
                }
            });

            edittext_actual_rate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String rate_str = s.toString();
                    getMeasuredCalculation(rate_str, textview_actual_value, textview_actual_dep_value, getAdapterPosition());

                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            edittext_actual_dep_per.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String dep_str = s.toString();

                    DepthValueCalculation(dep_str, textview_actual_dep_value, getAdapterPosition());

                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

        }
    }


    public ValuationActualAreaAdapter_ka(ArrayList<IndPropertyFloorsValuation> stepsValuation, ArrayList<IndPropertyFloor> steps, FragmentActivity context) {
        this.steps = steps;
        this.stepsValuation = stepsValuation;
        this.mContext = context;
        general = new General(mContext);
        // propertyFloorInternalAdapter = new PropertyFloorInternalAdapter((Activity)context);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.valuation_actual_area_ka, viewGroup, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        holder.edittext_actual_rate.setFocusable(true);

        String floorName = steps.get(position).getFloorName();
        if (!general.isEmpty(floorName)) {
            holder.textview_actual_floor.setText(floorName);
        } else {
            holder.textview_actual_floor.setText("");
        }

        String measuredFloorArea = steps.get(position).getMeasuredFloorArea();
        if (!general.isEmpty(measuredFloorArea)) {
            holder.textview_actual_unit.setText(measuredFloorArea);
        } else {
            holder.textview_actual_unit.setText("");
        }

        String measuredConstrRate = stepsValuation.get(position).getMeasuredConstrRate();
        if (!general.isEmpty(measuredConstrRate)) {
            holder.edittext_actual_rate.setText(measuredConstrRate);
        } else {
            holder.edittext_actual_rate.setText("");
        }

        String decimalFormattedCommaString = general.DecimalFormattedCommaString(stepsValuation.get(position).getMeasuredConstrValue());
        if (!general.isEmpty(decimalFormattedCommaString)) {
            holder.textview_actual_value.setText(decimalFormattedCommaString);
        } else {
            holder.textview_actual_value.setText("");
        }

        String percentageDepreciation = steps.get(position).getPercentageDepreciation();
        if (!general.isEmpty(percentageDepreciation)) {
            holder.edittext_actual_dep_per.setText(percentageDepreciation);
        } else {
            holder.edittext_actual_dep_per.setText("");
        }


        /*int x = holder.getLayoutPosition();
        if (Singleton.getInstance().indPropertyFloors.size() > 0) {
            String valdis = steps.get(position).getFloorName();
            int id = steps.get(position).getConstructionStageId();
            holder.textview_actual_floor.setText(steps.get(position).getFloorName());
            holder.textview_actual_unit.setText("" + steps.get(position).getMeasuredFloorArea());
            holder.textview_actual_value.setText("" +general.DecimalFormattedCommaString(stepsValuation.get(position).getMeasuredConstrValue()));
            holder.textview_actual_value.setText("" +general.DecimalFormattedCommaString(stepsValuation.get(position).getFloorDepreciationValue()));
            holder.edittext_actual_rate.setText("" + );
            holder.edittext_actual_dep_per.setText("" + steps.get(position).getPercentageDepreciation());
            holder.edittext_actual_rate.setFocusable(false);
        } else {
            String floorName = steps.get(position).getFloorName();
            if (floorName.equalsIgnoreCase("")) {
                Log.e("Floor Info:", "name is empty");
            } else {
                holder.textview_actual_floor.setText(steps.get(position).getFloorName());
                holder.textview_actual_unit.setText("" + steps.get(position).getMeasuredFloorArea());
                holder.textview_actual_value.setText("" +general.DecimalFormattedCommaString(stepsValuation.get(position).getMeasuredConstrValue()));
                holder.textview_actual_value.setText("" +general.DecimalFormattedCommaString(stepsValuation.get(position).getFloorDepreciationValue()));
                holder.edittext_actual_rate.setText("" + stepsValuation.get(position).getMeasuredConstrRate());
                holder.edittext_actual_dep_per.setText("" + steps.get(position).getPercentageDepreciation());
                holder.edittext_actual_rate.setFocusable(false);
            }
        }*/
    }

    public ArrayList<IndPropertyFloor> getStepList() {
        return steps;
    }

    public ArrayList<IndPropertyFloorsValuation> getFloorvaluationStepList() {
        return stepsValuation;
    }

    /******
     * Get Measured Calc for actual area
     * ********/
    @SuppressLint("SetTextI18n")
    private void getMeasuredCalculation(String rate_str, TextView textview_actual_value, TextView textview_actual_dep_value, int adapterPosition) {
        if (!general.isEmpty(rate_str)) {
            final IndPropertyFloorsValuation stepsModel = stepsValuation.get(adapterPosition);
            stepsModel.setDocumentConstrRate(rate_str);
            stepsModel.setMeasuredConstrRate(rate_str);
            stepsValuation.set(adapterPosition, stepsModel);
            Singleton.getInstance().indPropertyFloorsValuations.set(adapterPosition, stepsModel);

            final IndPropertyFloor stepsmodelfloor = steps.get(adapterPosition);
            String docfloorarea = stepsmodelfloor.getDocumentFloorArea();
            String measuredfloorarea = stepsmodelfloor.getMeasuredFloorArea();

            if (!general.isEmpty(rate_str) && !general.isEmpty(measuredfloorarea)) {
                float sumtotal = 0, acttotal = 0;
                int sum_total = 0, act_total = 0;

                /****Permissible area calc***/
                sumtotal = (general.convertTofloat(rate_str)) * (general.convertTofloat(docfloorarea));
                sum_total = general.convertToRoundoff(sumtotal);

                /****Actual area calc***/
                acttotal = (general.convertTofloat(rate_str)) * (general.convertTofloat(measuredfloorarea));
                act_total = general.convertToRoundoff(acttotal);

                // set the in act_total
                textview_actual_value.setText(general.DecimalFormattedCommaString(String.valueOf(act_total)));
                stepsModel.setDocumentConstrValue("" + sum_total);
                stepsModel.setMeasuredConstrValue("" + act_total);
                stepsValuation.set(adapterPosition, stepsModel);
                Singleton.getInstance().indPropertyFloorsValuations.set(adapterPosition, stepsModel);


                /*****total construction*****/
                int total_construction = general.getTotalConstructionActualValue(stepsValuation);
                if (FragmentValuationBuilding_ka.textview_totalconstructionvalue_result != null)
                    FragmentValuationBuilding_ka.textview_totalconstructionvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(total_construction)));

                //  FragmentValuationBuilding_ka.textview_totalconstructionvalue_result.setText("" + total_construction);

                if (FragmentValuationBuilding_ka.textview_insurancevaluepe_result != null)
                    FragmentValuationBuilding_ka.textview_insurancevaluepe_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(total_construction)));
                //    FragmentValuationBuilding_ka.textview_insurancevaluepe_result.setText("" + total_construction);

                /******total property********/

                String totalland = "0";
                if (FragmentValuationBuilding_ka.permission_check == 1) {
                    totalland = FragmentValuationBuilding_ka.editText_total_permissiblearea.getText().toString();
                } else if (FragmentValuationBuilding_ka.permission_check == 2) {
                    totalland = FragmentValuationBuilding_ka.editText_total_actualarea.getText().toString();
                }

                String edittext_additional_description_val = FragmentValuationBuilding_ka.edittext_additional_description_value.getText().toString();

                if (!general.isEmpty(totalland)) {
                    float property = (general.convertTofloat(totalland)) + general.convertTofloat(String.valueOf(total_construction))+ (general.convertTofloat(edittext_additional_description_val));
                    int total_property = general.convertToRoundoff(property);
                    if (FragmentValuationBuilding_ka.textview_totalpropertyvalue_result != null)
                        FragmentValuationBuilding_ka.textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(total_property)));
                    } else {
                    float property = 0 + general.convertTofloat(String.valueOf(total_construction)) + (general.convertTofloat(edittext_additional_description_val));
                    int total_property = general.convertToRoundoff(property);
                    if (FragmentValuationBuilding_ka.textview_totalpropertyvalue_result != null)
                        FragmentValuationBuilding_ka.textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(total_property)));
                }

                /****As per Completion result value******/
                float percentageCompletetotal = 0;
                int percentageComplete_total = 0;
                String asperCompletepercentage = FragmentValuationBuilding_ka.editText_aspercompletion.getText().toString();
                if (!General.isEmpty(asperCompletepercentage)) {
                    String total_property = FragmentValuationBuilding_ka.textview_totalpropertyvalue_result.getText().toString();
                    if (!general.isEmpty(total_property)) {
                        percentageCompletetotal = (general.convertTofloat(total_property) * ((general.convertTofloat(asperCompletepercentage)) / 100));
                        percentageComplete_total = general.convertToRoundoff(percentageCompletetotal);
                        FragmentValuationBuilding_ka.textview_aspercompletion_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(percentageComplete_total)));

                        //   FragmentValuationBuilding_ka.textview_aspercompletion_result.setText("" + percentageComplete_total);
                    } else {
                        FragmentValuationBuilding_ka.textview_aspercompletion_result.setText("");
                    }
                }


                /******Dep% value calc********/
                String documentconstVal = stepsModel.getDocumentConstrValue();
                String measurementconstVal = stepsModel.getMeasuredConstrValue();
                String depAge = String.valueOf(stepsmodelfloor.getPercentageDepreciation());

                if (!general.isEmpty(depAge) && !general.isEmpty(measurementconstVal)) {
                    float permDeptotal = 0;
                    int dep_total = 0;
                    permDeptotal = (100 - (general.convertTofloat(depAge))) * ((general.convertTofloat(measurementconstVal) / 100));
                    dep_total = general.convertToRoundoff(permDeptotal);
                    stepsModel.setFloorDepreciationValue("" + dep_total);

                    stepsValuation.set(adapterPosition, stepsModel);
                    Singleton.getInstance().indPropertyFloorsValuations.set(adapterPosition, stepsModel);
                    textview_actual_dep_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(dep_total)));

                }

                FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(adapterPosition);

            }else {
                FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(adapterPosition);
            }

        } else {
            String initval = "0";
            textview_actual_value.setText("");
            if (FragmentValuationBuilding_ka.textview_totalconstructionvalue_result != null)
                FragmentValuationBuilding_ka.textview_totalconstructionvalue_result.setText("");
            if (FragmentValuationBuilding_ka.textview_insurancevaluepe_result != null)
                FragmentValuationBuilding_ka.textview_insurancevaluepe_result.setText("");
            if (FragmentValuationBuilding_ka.textview_totalpropertyvalue_result != null)
                FragmentValuationBuilding_ka.textview_totalpropertyvalue_result.setText("");
        }
    }

    /*public void DepthValueCalculation(String dep_str, TextView textview_permissiblearea_dep_value, int adapterPosition) {
        if (!dep_str.equalsIgnoreCase("")) {
            final IndPropertyFloorsValuation stepsModel = stepsValuation.get(adapterPosition);

            String measurementconstVal = stepsModel.getMeasuredConstrValue();
                       *//* final IndPropertyFloor stepsmodelfloor = steps.get(getAdapterPosition());
                        String depAge = String.valueOf(stepsmodelfloor.getPropertyAge());*//*

            if (!general.isEmpty(dep_str) && !general.isEmpty(measurementconstVal)) {
                float permDeptotal = 0;
                int dep_total = 0;
                permDeptotal = (100 - (general.convertTofloat(dep_str))) * ((general.convertTofloat(measurementconstVal) / 100));
                dep_total = general.convertToRoundoff(permDeptotal);

                stepsModel.setFloorDepreciationValue("" + dep_total);
                stepsValuation.set(adapterPosition, stepsModel);
                textview_permissiblearea_dep_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(dep_total)));

            }

        } else {
            String initval = "0";
            textview_permissiblearea_dep_value.setText("");
        }
    }
*/

    public void DepthValueCalculation(String dep_str, TextView textview_permissiblearea_dep_value, int adapterPosition) {
        IndPropertyFloorsValuation stepsModel = stepsValuation.get(adapterPosition);
        if (!general.isEmpty(dep_str)) {
            /*String documentconstVal = stepsModel.getDocumentConstrValue();*/
            String measurementconstVal = stepsModel.getMeasuredConstrValue();
            if ((!general.isEmpty(dep_str)) && (!general.isEmpty(measurementconstVal))) {
                float permDeptotal = 0;
                int sum_total = 0;
                permDeptotal = (100 - (general.convertTofloat(dep_str))) * ((general.convertTofloat(measurementconstVal) / 100));
                sum_total = general.convertToRoundoff(permDeptotal);
                stepsModel.setFloorDepreciationValue("" + sum_total);
                stepsValuation.set(adapterPosition, stepsModel);
                Singleton.getInstance().indPropertyFloorsValuations.set(adapterPosition, stepsModel);
                textview_permissiblearea_dep_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));
            } else {
                textview_permissiblearea_dep_value.setText("");
                stepsModel.setFloorDepreciationValue("");
                stepsValuation.set(adapterPosition, stepsModel);
                Singleton.getInstance().indPropertyFloorsValuations.set(adapterPosition, stepsModel);
            }
            /******set Depreciation %******/
            final IndPropertyFloor stepsmodelfloor = steps.get(adapterPosition);
            stepsmodelfloor.setPercentageDepreciation(dep_str);
            steps.set(adapterPosition, stepsmodelfloor);
        } else {
            textview_permissiblearea_dep_value.setText("");
            stepsModel.setFloorDepreciationValue("");
            stepsValuation.set(adapterPosition, stepsModel);
            Singleton.getInstance().indPropertyFloorsValuations.set(adapterPosition, stepsModel);
            /******set Depreciation %******/
            final IndPropertyFloor stepsmodelfloor = steps.get(adapterPosition);
            stepsmodelfloor.setPercentageDepreciation(null);
            steps.set(adapterPosition, stepsmodelfloor);
        }
    }


}
