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
import com.realappraiser.gharvalue.fragments.FragmentValuationBuilding;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.model.IndPropertyFloorsValuation;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;

/**
 * Created by kaptas on 10/1/18.
 */

@SuppressWarnings("ALL")
public class ValuationPermissibleAreaAdapter extends RecyclerView.Adapter<ValuationPermissibleAreaAdapter.ViewHolder> {

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
        EditText edittext_permissiblearea_rate, edittext_permissiblearea_dep_per;
        TextView textview_permissiblearea_floor, textview_permissiblearea_unit, textview_permissiblearea_value, textview_permissiblearea_dep_value;

        final IndPropertyFloor stepsModel = new IndPropertyFloor();

        public ViewHolder(final View itemView) {
            super(itemView);

            edittext_permissiblearea_rate = (EditText) itemView.findViewById(R.id.edittext_permissiblearea_rate);
            edittext_permissiblearea_dep_per = (EditText) itemView.findViewById(R.id.edittext_permissiblearea_dep_per);
            textview_permissiblearea_floor = (TextView) itemView.findViewById(R.id.textview_permissiblearea_floor);
            textview_permissiblearea_unit = (TextView) itemView.findViewById(R.id.textview_permissiblearea_unit);
            textview_permissiblearea_value = (TextView) itemView.findViewById(R.id.textview_permissiblearea_value);
            textview_permissiblearea_dep_value = (TextView) itemView.findViewById(R.id.textview_permissiblearea_dep_value);

            edittext_permissiblearea_rate.setTypeface(General.regularTypeface());
            edittext_permissiblearea_dep_per.setTypeface(General.regularTypeface());
            textview_permissiblearea_floor.setTypeface(General.regularTypeface());
            textview_permissiblearea_unit.setTypeface(General.regularTypeface());
            textview_permissiblearea_value.setTypeface(General.regularTypeface());
            textview_permissiblearea_dep_value.setTypeface(General.regularTypeface());

            /*edittext_permissiblearea_rate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        // code to execute when EditText loses focus
                        Log.e("onFocus_coming", "onFocus_coming" + hasFocus);
                        // Copy the value to aspercompletion_val
                        String toString = edittext_permissiblearea_rate.getText().toString();
                        getDocumentCalculation(toString, textview_permissiblearea_value, textview_permissiblearea_dep_value, getAdapterPosition());
                    }
                }
            });*/


            edittext_permissiblearea_rate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        edittext_permissiblearea_dep_per.setSelection(edittext_permissiblearea_dep_per.getText().toString().length());
                    }
                    return false;
                }
            });

            edittext_permissiblearea_dep_per.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        edittext_permissiblearea_rate.setSelection(edittext_permissiblearea_rate.getText().toString().length());
                    }
                    return false;
                }
            });

            edittext_permissiblearea_rate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String rate_str = s.toString();
                    getDocumentCalculation(rate_str, textview_permissiblearea_value, textview_permissiblearea_dep_value, getAdapterPosition());

                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            edittext_permissiblearea_dep_per.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String dep_str = s.toString();

                    DepthValueCalculation(dep_str, textview_permissiblearea_dep_value, getAdapterPosition());

                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

        }
    }

    public ValuationPermissibleAreaAdapter(ArrayList<IndPropertyFloorsValuation> stepsValuation, ArrayList<IndPropertyFloor> steps, FragmentActivity context) {
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.valuation_permissible_area, viewGroup, false);
        return new ViewHolder(v);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        int x = holder.getLayoutPosition();

        String floorName = steps.get(position).getFloorName();
        if (!general.isEmpty(floorName)) {
            holder.textview_permissiblearea_floor.setText(floorName);
        } else {
            holder.textview_permissiblearea_floor.setText("");
        }

        String documentFloorArea = steps.get(position).getDocumentFloorArea();
        if (!general.isEmpty(documentFloorArea)) {
            holder.textview_permissiblearea_unit.setText("" + documentFloorArea);
        } else {
            holder.textview_permissiblearea_unit.setText("");
        }

        if(stepsValuation.size() > 0) {
        }

        String documentConstrRate = stepsValuation.get(position).getDocumentConstrRate();
        if (!general.isEmpty(documentConstrRate)) {
            holder.edittext_permissiblearea_rate.setText("" + documentConstrRate);
        } else {
            holder.edittext_permissiblearea_rate.setText("");
        }

        String formattedCommaString = general.DecimalFormattedCommaString(stepsValuation.get(position).getDocumentConstrValue());
        if (!general.isEmpty(formattedCommaString)) {
            holder.textview_permissiblearea_value.setText("" + formattedCommaString);
        } else {
            holder.textview_permissiblearea_value.setText("");
        }

        String percentageDepreciation = steps.get(position).getPercentageDepreciation();
        if (!general.isEmpty(percentageDepreciation)) {
            holder.edittext_permissiblearea_dep_per.setText("" + percentageDepreciation);
        } else {
            holder.edittext_permissiblearea_dep_per.setText("");
        }

        String formattedCommaString1 = general.DecimalFormattedCommaString(stepsValuation.get(position).getFloorDepreciationValue());
        if (!general.isEmpty(formattedCommaString1)) {
            holder.textview_permissiblearea_dep_value.setText("" + formattedCommaString1);
        } else {
            holder.textview_permissiblearea_dep_value.setText("");
        }

        /*if (Singleton.getInstance().indPropertyFloors.size() > 0) {
            String valdis = steps.get(position).getFloorName();
            int id = steps.get(position).getConstructionStageId();

            String floorName = steps.get(position).getFloorName();
            if (!general.isEmpty(floorName)) {
                holder.textview_permissiblearea_floor.setText(floorName);
            } else {
                holder.textview_permissiblearea_floor.setText("");
            }

            String documentFloorArea = steps.get(position).getDocumentFloorArea();
            if (!general.isEmpty(documentFloorArea)) {
                holder.textview_permissiblearea_unit.setText("" + documentFloorArea);
            } else {
                holder.textview_permissiblearea_unit.setText("");
            }

            String formattedCommaString = general.DecimalFormattedCommaString(stepsValuation.get(position).getDocumentConstrValue());
            if (!general.isEmpty(formattedCommaString)) {
                holder.textview_permissiblearea_value.setText("" + formattedCommaString);
            } else {
                holder.textview_permissiblearea_value.setText("");
            }

            String formattedCommaString1 = general.DecimalFormattedCommaString(stepsValuation.get(position).getFloorDepreciationValue());
            if (!general.isEmpty(formattedCommaString1)) {
                holder.textview_permissiblearea_dep_value.setText("" + formattedCommaString1);
            } else {
                holder.textview_permissiblearea_dep_value.setText("");
            }

            String documentConstrRate = stepsValuation.get(position).getDocumentConstrRate();
            if (!general.isEmpty(documentConstrRate)) {
                holder.edittext_permissiblearea_rate.setText("" + documentConstrRate);
            } else {
                holder.edittext_permissiblearea_rate.setText("");
            }

            String percentageDepreciation = steps.get(position).getPercentageDepreciation();
            if (!general.isEmpty(percentageDepreciation)) {
                holder.edittext_permissiblearea_dep_per.setText("" + percentageDepreciation);
            } else {
                holder.edittext_permissiblearea_dep_per.setText("");
            }

        } else {

            String floorName = steps.get(position).getFloorName();
            if (!general.isEmpty(floorName)) {
                holder.textview_permissiblearea_floor.setText(floorName);
            } else {
                holder.textview_permissiblearea_floor.setText("");
            }

            String documentFloorArea = steps.get(position).getDocumentFloorArea();
            if (!general.isEmpty(documentFloorArea)) {
                holder.textview_permissiblearea_unit.setText("" + documentFloorArea);
            } else {
                holder.textview_permissiblearea_unit.setText("");
            }

            String formattedCommaString = general.DecimalFormattedCommaString(stepsValuation.get(position).getDocumentConstrValue());
            if (!general.isEmpty(formattedCommaString)) {
                holder.textview_permissiblearea_value.setText("" + formattedCommaString);
            } else {
                holder.textview_permissiblearea_value.setText("");
            }

            String formattedCommaString1 = general.DecimalFormattedCommaString(stepsValuation.get(position).getFloorDepreciationValue());
            if (!general.isEmpty(formattedCommaString1)) {
                holder.textview_permissiblearea_dep_value.setText("" + formattedCommaString1);
            } else {
                holder.textview_permissiblearea_dep_value.setText("");
            }

            String documentConstrRate = stepsValuation.get(position).getDocumentConstrRate();
            if (!general.isEmpty(documentConstrRate)) {
                holder.edittext_permissiblearea_rate.setText("" + documentConstrRate);
            } else {
                holder.edittext_permissiblearea_rate.setText("");
            }

            String percentageDepreciation = steps.get(position).getPercentageDepreciation();
            if (!general.isEmpty(percentageDepreciation)) {
                holder.edittext_permissiblearea_dep_per.setText("" + percentageDepreciation);
            } else {
                holder.edittext_permissiblearea_dep_per.setText("");
            }
        }*/
    }

    public ArrayList<IndPropertyFloor> getStepList() {
        return steps;
    }

    public ArrayList<IndPropertyFloorsValuation> getFloorvaluationStepList() {
        return stepsValuation;
    }


    /*******
     * Document Valuation i.e Permissible calculation
     * ********/

    @SuppressLint("SetTextI18n")
    public void getDocumentCalculation(String rateStr, TextView textview_permissiblearea_value, TextView textview_permissiblearea_dep_value, int adapterPosition) {
        if (!general.isEmpty(rateStr)) {
            final IndPropertyFloorsValuation stepsModel = stepsValuation.get(adapterPosition);
            stepsModel.setDocumentConstrRate(rateStr);
            stepsModel.setMeasuredConstrRate(rateStr);
            stepsValuation.set(adapterPosition, stepsModel);
            Singleton.getInstance().indPropertyFloorsValuations.set(adapterPosition, stepsModel);

            final IndPropertyFloor stepsmodelfloor = steps.get(adapterPosition);
            String docfloorarea = stepsmodelfloor.getDocumentFloorArea();
            String measuredfloorarea = stepsmodelfloor.getMeasuredFloorArea();

            if ((!general.isEmpty(rateStr)) && (!general.isEmpty(docfloorarea))) {
                float sumtotal = 0, acttotal = 0;
                int sum_total = 0, act_total = 0;

                /****Permissible area calc***/
                sumtotal = (general.convertTofloat(rateStr)) * (general.convertTofloat(docfloorarea));
                sum_total = general.convertToRoundoff(sumtotal);

                /****Actual area calc***/
                acttotal = (general.convertTofloat(rateStr)) * (general.convertTofloat(measuredfloorarea));
                act_total = general.convertToRoundoff(acttotal);

                // set the in sum_total
                textview_permissiblearea_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                stepsModel.setDocumentConstrValue("" + sum_total);
                stepsModel.setMeasuredConstrValue("" + act_total);
                stepsValuation.set(adapterPosition, stepsModel);
                Singleton.getInstance().indPropertyFloorsValuations.set(adapterPosition, stepsModel);

                /*****total construction*****/
                int total_construction = general.getTotalConstructionValue(stepsValuation);
                if (FragmentValuationBuilding.textview_totalconstructionvalue_result != null)
                    FragmentValuationBuilding.textview_totalconstructionvalue_result.setText(general.DecimalFormattedCommaString(String.valueOf(total_construction)));
                //FragmentValuationBuilding.textview_totalconstructionvalue_result.setText("" + total_construction);
                if (FragmentValuationBuilding.textview_insurancevaluepe_result != null)
                    FragmentValuationBuilding.textview_insurancevaluepe_result.setText(general.DecimalFormattedCommaString(String.valueOf(total_construction)));
                //   FragmentValuationBuilding.textview_insurancevaluepe_result.setText("" + total_construction);

                /******total property********/
                String totalland = "0";
                if (FragmentValuationBuilding.permission_check == 1) {
                    totalland = FragmentValuationBuilding.editText_total_permissiblearea.getText().toString();
                } else if (FragmentValuationBuilding.permission_check == 2) {
                    totalland = FragmentValuationBuilding.editText_total_actualarea.getText().toString();
                }

                //String totalland = Singleton.getInstance().indPropertyValuation.getDocumentLandValue();
                if (!general.isEmpty(totalland)) {
                    float property = (general.convertTofloat(totalland)) + general.convertTofloat(String.valueOf(total_construction));
                    int total_property = general.convertToRoundoff(property);
                    if (FragmentValuationBuilding.textview_totalpropertyvalue_result != null)
                        FragmentValuationBuilding.textview_totalpropertyvalue_result.setText(general.DecimalFormattedCommaString(String.valueOf(total_property)));
                    //FragmentValuationBuilding.textview_totalpropertyvalue_result.setText("" + total_property);
                } else {
                    float property = 0 + general.convertTofloat(String.valueOf(total_construction));
                    int total_property = general.convertToRoundoff(property);
                    if (FragmentValuationBuilding.textview_totalpropertyvalue_result != null)
                        FragmentValuationBuilding.textview_totalpropertyvalue_result.setText(general.DecimalFormattedCommaString(String.valueOf(total_property)));
                    // FragmentValuationBuilding.textview_totalpropertyvalue_result.setText("" + total_property);
                }

                /****As per Completion result value******/
                float percentageCompletetotal = 0;
                int percentageComplete_total = 0;
                String asperCompletepercentage = FragmentValuationBuilding.editText_aspercompletion.getText().toString();
                if (!General.isEmpty(asperCompletepercentage)) {
                    String total_property = FragmentValuationBuilding.textview_totalpropertyvalue_result.getText().toString();
                    if (!general.isEmpty(total_property)) {
                        percentageCompletetotal = (general.convertTofloat(total_property) * ((general.convertTofloat(asperCompletepercentage)) / 100));
                        percentageComplete_total = general.convertToRoundoff(percentageCompletetotal);
                        //  FragmentValuationBuilding.textview_aspercompletion_result.setText("" + percentageComplete_total);
                        FragmentValuationBuilding.textview_aspercompletion_result.setText(general.DecimalFormattedCommaString(String.valueOf(percentageComplete_total)));

                    } else {
                        FragmentValuationBuilding.textview_aspercompletion_result.setText("");
                    }
                }

                /******Dep% value calc********/
                String documentconstVal = stepsModel.getDocumentConstrValue();
                String measurementconstVal = stepsModel.getMeasuredConstrValue();
                String depAge = String.valueOf(stepsmodelfloor.getPercentageDepreciation());
                // String depAge = String.valueOf(stepsmodelfloor.getPropertyAge());

                if (!general.isEmpty(depAge) && !general.isEmpty(documentconstVal)) {
                    float permDeptotal = 0;
                    int dep_total = 0;
                    permDeptotal = (100 - (general.convertTofloat(depAge))) * ((general.convertTofloat(documentconstVal) / 100));
                    dep_total = general.convertToRoundoff(permDeptotal);

                    stepsModel.setFloorDepreciationValue("" + dep_total);
                    stepsValuation.set(adapterPosition, stepsModel);
                    Singleton.getInstance().indPropertyFloorsValuations.set(adapterPosition, stepsModel);
                    textview_permissiblearea_dep_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(dep_total)));

                }

                FragmentValuationBuilding.listActualAdapter.notifyItemChanged(adapterPosition);
            }else {
                FragmentValuationBuilding.listActualAdapter.notifyItemChanged(adapterPosition);
            }


        } else {
            String initval = "0";
            textview_permissiblearea_value.setText("");
            if (FragmentValuationBuilding.textview_totalconstructionvalue_result != null)
                FragmentValuationBuilding.textview_totalconstructionvalue_result.setText("");
            if (FragmentValuationBuilding.textview_insurancevaluepe_result != null)
                FragmentValuationBuilding.textview_insurancevaluepe_result.setText("");
            if (FragmentValuationBuilding.textview_totalpropertyvalue_result != null)
                FragmentValuationBuilding.textview_totalpropertyvalue_result.setText("");


        }
    }

    public void DepthValueCalculation(String dep_str, TextView textview_permissiblearea_dep_value, int adapterPosition) {
        IndPropertyFloorsValuation stepsModel = stepsValuation.get(adapterPosition);
        if (!general.isEmpty(dep_str)) {
            String documentconstVal = stepsModel.getDocumentConstrValue();
            /*String measurementconstVal = stepsModel.getMeasuredConstrValue();*/
            if ((!general.isEmpty(dep_str)) && (!general.isEmpty(documentconstVal))) {
                float permDeptotal = 0;
                int sum_total = 0;
                permDeptotal = (100 - (general.convertTofloat(dep_str))) * ((general.convertTofloat(documentconstVal) / 100));
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
