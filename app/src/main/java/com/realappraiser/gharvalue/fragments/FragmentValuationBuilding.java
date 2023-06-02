package com.realappraiser.gharvalue.fragments;

import static com.realappraiser.gharvalue.fragments.PhotoLatLong.latvalue;
import static com.realappraiser.gharvalue.fragments.PhotoLatLong.longvalue;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.adapter.ComparePropertyRateAdapter;
import com.realappraiser.gharvalue.adapter.ValuationActualAreaAdapter;
import com.realappraiser.gharvalue.adapter.ValuationPermissibleAreaAdapter;
import com.realappraiser.gharvalue.communicator.JsonRequestData;
import com.realappraiser.gharvalue.communicator.RatePopupApi;
import com.realappraiser.gharvalue.communicator.RatePopupupInterface;
import com.realappraiser.gharvalue.communicator.RequestParam;
import com.realappraiser.gharvalue.communicator.TaskCompleteListener;
import com.realappraiser.gharvalue.communicator.WebserviceCommunicator;
import com.realappraiser.gharvalue.model.GetPropertyCompareDetailsModel;
import com.realappraiser.gharvalue.model.GetPropertyDetailsModel;
import com.realappraiser.gharvalue.model.IndPropertyFloorsValuation;
import com.realappraiser.gharvalue.model.Measurements;
import com.realappraiser.gharvalue.model.RatePopup;
import com.realappraiser.gharvalue.utils.DecimalDigitsInputFilter;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


@SuppressWarnings("ALL")
public class FragmentValuationBuilding extends Fragment implements RatePopupupInterface {

    // TODO General class to call typeface and all...
    @SuppressLint("StaticFieldLeak")
    static private General general;


    // TODO RatioGroup ini
    @BindView(R.id.id_radiogroup_considerforvaluation_land)
    RadioGroup id_radiogroup_considerforvaluation_land;
    @BindView(R.id.id_radio_considerforvaluation_permissiblearea_land)
    RadioButton id_radio_considerforvaluation_permissiblearea_land;
    @BindView(R.id.id_radio_considerforvaluation_actualarea_land)
    RadioButton id_radio_considerforvaluation_actualarea_land;
    @BindView(R.id.id_radiogroup_considerforvaluation_construction)
    RadioGroup id_radiogroup_considerforvaluation_construction;
    @BindView(R.id.id_radio_considerforvaluation_permissiblearea_construction)
    RadioButton id_radio_considerforvaluation_permissiblearea_construction;
    @BindView(R.id.id_radio_considerforvaluation_actualarea_construction)
    RadioButton id_radio_considerforvaluation_actualarea_construction;

    // TODO - Linear
    @BindView(R.id.linear_permissiblearea)
    LinearLayout linear_permissiblearea;
    @BindView(R.id.linear_actualarea)
    LinearLayout linear_actualarea;
    @BindView(R.id.linear_permissiblearea_row)
    LinearLayout linear_permissiblearea_row;
    @BindView(R.id.linear_actual_row)
    LinearLayout linear_actual_row;

    // TODO - Textview
    @BindView(R.id.textview_landvaluation)
    TextView textview_landvaluation;
    @BindView(R.id.textview_unit_land)
    TextView textview_unit_land;
    @BindView(R.id.textview_constructionvaluation)
    TextView textview_constructionvaluation;
    @BindView(R.id.textview_unit_construction)
    TextView textview_unit_construction;
    @BindView(R.id.textview_floor_head)
    TextView textview_floor_head;
    @BindView(R.id.textview_permissiblearea_head)
    TextView textview_permissiblearea_head;
    @BindView(R.id.textview_rate_head)
    TextView textview_rate_head;
    @BindView(R.id.textview_value_head)
    TextView textview_value_head;
    @BindView(R.id.textview_dep_per)
    TextView textview_dep_per;
    @BindView(R.id.textview_depvalue)
    TextView textview_depvalue;
    @BindView(R.id.textview_permissiblearea_floor)
    TextView textview_permissiblearea_floor;
    @BindView(R.id.textview_permissiblearea_unit)
    TextView textview_permissiblearea_unit;
    @BindView(R.id.textview_permissiblearea_value)
    TextView textview_permissiblearea_value;
    @BindView(R.id.textview_permissiblearea_dep_value)
    TextView textview_permissiblearea_dep_value;
    @BindView(R.id.textview_actual_floor)
    TextView textview_actual_floor;
    @BindView(R.id.textview_actual_unit)
    TextView textview_actual_unit;
    @BindView(R.id.textview_actual_value)
    TextView textview_actual_value;
    @BindView(R.id.textview_actual_dep_value)
    TextView textview_actual_dep_value;
    @BindView(R.id.textview_totalconstructionvalue)
    TextView textview_totalconstructionvalue;
    /*@BindView(R.id.textview_totalconstructionvalue_result)
    TextView textview_totalconstructionvalue_result;*/
    @BindView(R.id.textview_totalpropertyvalue)
    TextView textview_totalpropertyvalue;
    /*@BindView(R.id.textview_totalpropertyvalue_result)
    TextView textview_totalpropertyvalue_result;*/
    /*@BindView(R.id.textview_aspercompletion_result)*/
    /*TextView textview_aspercompletion_result;*/
    @BindView(R.id.textview_insurancevaluepe)
    TextView textview_insurancevaluepe;
    /*@BindView(R.id.textview_insurancevaluepe_result)
    TextView textview_insurancevaluepe_result;*/
    @BindView(R.id.textview_proposedvaluation)
    TextView textview_proposedvaluation;
    @BindView(R.id.textview_realizable_per)
    TextView textview_realizable_per;
    @BindView(R.id.textview_distress_per)
    TextView textview_distress_per;
    @BindView(R.id.textview_carpet_per)
    TextView textview_carpet_per;
    @BindView(R.id.textview_select)
    TextView textview_select;

    @SuppressLint("StaticFieldLeak")
    public static EditText editText_permissiblearea;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_actualarea;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_unit_permissiblearea;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_unit_actualarea;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_total_permissiblearea;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_total_actualarea;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_rate_permissiblearea;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_guideline_rate;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_guideline_value;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_rate_actualarea;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_aspercompletion;
    @SuppressLint("StaticFieldLeak")
    public static TextView textview_totalconstructionvalue_result;
    @SuppressLint("StaticFieldLeak")
    public static TextView textview_totalpropertyvalue_result;
    @SuppressLint("StaticFieldLeak")
    public static TextView textview_insurancevaluepe_result;
    @SuppressLint("StaticFieldLeak")
    public static TextView textview_aspercompletion_result;
    @SuppressLint("StaticFieldLeak")
    public static RecyclerView recyclerview_permissiblearea;
    @SuppressLint("StaticFieldLeak")
    public static RecyclerView recyclerview_actualarea;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_construction_rate;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_construction_value;
    @SuppressLint("StaticFieldLeak")
    public static EditText edittext_proposedvaluation_result;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_remarks;

    // TODO - Edittext
    @BindView(R.id.edittext_permissiblearea_rate)
    EditText edittext_permissiblearea_rate;
    @BindView(R.id.edittext_permissiblearea_dep_per)
    EditText edittext_permissiblearea_dep_per;
    @BindView(R.id.edittext_actual_rate)
    EditText edittext_actual_rate;
    @BindView(R.id.edittext_actual_dep_per)
    EditText edittext_actual_dep_per;
    /* @BindView(R.id.editText_aspercompletion)
     EditText editText_aspercompletion;*/
    @BindView(R.id.spinner_ft_meter)
    Spinner spinner_ft_meter;
    @BindView(R.id.spinner_ft_meter_construction)
    Spinner spinner_ft_meter_construction;
    @BindView(R.id.tvRatePopup)
    TextView tvRatePopup;
    @BindView(R.id.tvCompareRate)
    TextView tvCompareRate;


    @SuppressLint("StaticFieldLeak")
    static EditText editText_realizable_custom;
    @SuppressLint("StaticFieldLeak")
    static EditText editText_distress_custom;

    public Dialog dialog;

    //Todo recyclerview for dynamic calculation
   /* @BindView(R.id.recyclerview_permissiblearea)
    RecyclerView recyclerview_permissiblearea;
    @BindView(R.id.recyclerview_actualarea)
    RecyclerView recyclerview_actualarea;*/

    public static int permission_check = 0;
    public static int mea_unit = 0;

    public static ArrayList<IndPropertyFloorsValuation> list;
    public ArrayList<IndPropertyFloorsValuation> getValFloorlist;
    public static ArrayList<IndPropertyFloorsValuation> getValFloorlistSave;
    public ArrayList<IndPropertyFloorsValuation> getValActualFloorlist;
    @SuppressLint("StaticFieldLeak")
    public static ValuationPermissibleAreaAdapter listAdapter;
    @SuppressLint("StaticFieldLeak")
    public static ValuationActualAreaAdapter listActualAdapter;
    public LinearLayoutManager llm;
    public static String caseid;
    @SuppressLint("StaticFieldLeak")
    public static FragmentActivity fragmentActivity;


    static int permission_check_construction = 0;
    static int mea_unit_construction = 0;


    static String total_permisssion_area_floors = "";
    static String total_actual_area_floors = "";
    static int measurment_unit_property_floors = 0;
    static int measurment_unit_property_for_dlc = 0;
    public static String permissiblearea_str_dlc = "";
    public static String actualarea_str_dlc = "";


    /*siva*/
    TextView spinner_realizable;
    TextView spinner_distress;
    TextView spinner_carpet;
    @SuppressLint("StaticFieldLeak")
    public static TextView spinner_carpet_type;
    ArrayList<String> realizableList = new ArrayList<>();
    ArrayList<String> distressList = new ArrayList<>();
    ArrayList<String> carpetList = new ArrayList<>();
    ArrayList<String> carpet_typeList = new ArrayList<>();
    String selected_value = "";

    private RatePopupApi ratePopupApi;
    public FragmentValuationBuilding() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fragment_valuation_building, container, false);

        ButterKnife.bind(this, view);
        SettingsUtils.init(getActivity());
        general = new General(getActivity());

        fragmentActivity = getActivity();
        caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

        spinner_realizable = (TextView) view.findViewById(R.id.spinner_realizable);
        spinner_distress = (TextView) view.findViewById(R.id.spinner_distress);
        spinner_carpet = (TextView) view.findViewById(R.id.spinner_carpet);
        spinner_carpet_type = (TextView) view.findViewById(R.id.spinner_carpet_type);

        editText_realizable_custom = (EditText) view.findViewById(R.id.editText_realizable_custom);
        editText_distress_custom = (EditText) view.findViewById(R.id.editText_distress_custom);
        editText_permissiblearea = (EditText) view.findViewById(R.id.editText_permissiblearea);
        editText_actualarea = (EditText) view.findViewById(R.id.editText_actualarea);
        editText_unit_permissiblearea = (EditText) view.findViewById(R.id.editText_unit_permissiblearea);
        editText_unit_actualarea = (EditText) view.findViewById(R.id.editText_unit_actualarea);
        editText_total_permissiblearea = (EditText) view.findViewById(R.id.editText_total_permissiblearea);
        editText_total_actualarea = (EditText) view.findViewById(R.id.editText_total_actualarea);
        editText_rate_permissiblearea = (EditText) view.findViewById(R.id.editText_rate_permissiblearea);
        editText_guideline_rate = (EditText) view.findViewById(R.id.editText_guideline_rate);
        editText_guideline_value = (EditText) view.findViewById(R.id.editText_guideline_value);
        editText_rate_actualarea = (EditText) view.findViewById(R.id.editText_rate_actualarea);
        editText_aspercompletion = (EditText) view.findViewById(R.id.editText_aspercompletion);
        textview_totalconstructionvalue_result = (TextView) view.findViewById(R.id.textview_totalconstructionvalue_result);
        textview_totalpropertyvalue_result = (TextView) view.findViewById(R.id.textview_totalpropertyvalue_result);
        textview_insurancevaluepe_result = (TextView) view.findViewById(R.id.textview_insurancevaluepe_result);
        textview_aspercompletion_result = (TextView) view.findViewById(R.id.textview_aspercompletion_result);
        recyclerview_permissiblearea = (RecyclerView) view.findViewById(R.id.recyclerview_permissiblearea);
        recyclerview_actualarea = (RecyclerView) view.findViewById(R.id.recyclerview_actualarea);
        editText_construction_rate = (EditText) view.findViewById(R.id.editText_construction_rate);
        editText_construction_value = (EditText) view.findViewById(R.id.editText_construction_value);
        edittext_proposedvaluation_result = (EditText) view.findViewById(R.id.edittext_proposedvaluation_result);
        editText_remarks = (EditText) view.findViewById(R.id.editText_remarks);


        //  limit the 2 char after the decimal point
        // editText_guideline_rate.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(10,2)});
        // editText_construction_rate.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(10,2)});
        edittext_proposedvaluation_result.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(15, 2)});


        initValues();
        int total_floor_size = Singleton.getInstance().indPropertyFloors.size();

        if (total_floor_size > 0) {
            if (!general.isEmpty(Singleton.getInstance().indPropertyFloors.get(0).getDocumentFloorAreaUnit())) {
                measurment_unit_property_for_dlc = Integer.parseInt(Singleton.getInstance().indPropertyFloors.get(0).getDocumentFloorAreaUnit());
            }
        }

        // TODO - check with getDocumentFloorArea value list

        if (total_floor_size > 0) {
            float rate_float = 0;
            for (int x = 0; x < total_floor_size; x++) {
                if (!general.isEmpty(Singleton.getInstance().indPropertyFloors.get(x).getDocumentFloorArea())) {
                    float rate_check = general.convertTofloat(Singleton.getInstance().indPropertyFloors.get(x).getDocumentFloorArea());
                    rate_float = ((rate_float) + (rate_check));
                }
            }
            permissiblearea_str_dlc = String.valueOf(rate_float);
        }

        // TODO - check with getDocumentFloorArea value list
        if (total_floor_size > 0) {
            float rate_float = 0;
            for (int x = 0; x < total_floor_size; x++) {
                if (!general.isEmpty(Singleton.getInstance().indPropertyFloors.get(x).getMeasuredFloorArea())) {
                    float rate_check = general.convertTofloat(Singleton.getInstance().indPropertyFloors.get(x).getMeasuredFloorArea());
                    rate_float = ((rate_float) + (rate_check));
                }
            }
            actualarea_str_dlc = String.valueOf(rate_float);
        }

        tvCompareRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetails();
            }
        });


        //  radiogroup_considerforvaluation_land - Radio Button
        id_radiogroup_considerforvaluation_land.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                hideSoftKeyboard(editText_guideline_rate);

                RadioButton id_radiogenearal = (RadioButton) view.findViewById(group.getCheckedRadioButtonId());
                if (id_radiogenearal.getText().toString().equalsIgnoreCase(getResources().getString(R.string.documentarea))) {
                    linear_permissiblearea.setVisibility(View.VISIBLE);
                    linear_actualarea.setVisibility(View.GONE);
                    Singleton.getInstance().indPropertyValuation.setDocumentedLandValueSel(true);
                    Singleton.getInstance().indPropertyValuation.setMeasuredLandValueSel(false);
                    permission_check = 1;
                    permissionarea_dlc();
                    Total_Property_Value();
                } else {
                    linear_permissiblearea.setVisibility(View.GONE);
                    linear_actualarea.setVisibility(View.VISIBLE);
                    Singleton.getInstance().indPropertyValuation.setDocumentedLandValueSel(false);
                    Singleton.getInstance().indPropertyValuation.setMeasuredLandValueSel(true);
                    permission_check = 2;
                    permissionarea_dlc();
                    Total_Property_Value();
                }
            }
        });

        //  id_radiogroup_considerforvaluation_construction - Radio Button
        id_radiogroup_considerforvaluation_construction.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                hideSoftKeyboard(editText_guideline_rate);

                RadioButton id_radiogenearal = (RadioButton) view.findViewById(group.getCheckedRadioButtonId());
                if (id_radiogenearal.getText().toString().equalsIgnoreCase(getResources().getString(R.string.considerforvaluation_permissible))) {
                    textview_permissiblearea_head.setText(getResources().getString(R.string.permissiblearea));
                    recyclerview_permissiblearea.setVisibility(View.VISIBLE);
                    recyclerview_actualarea.setVisibility(View.GONE);
                    TotalPermissibleValuationFloors();
                    Singleton.getInstance().indPropertyValuation.setDocumentedConstrValueSel(true);
                    Singleton.getInstance().indPropertyValuation.setMeasuredConstrValueSel(false);
                    permission_check_construction = 1;
                    Log.e("Construction_dlc", "radio1");
                    Construction_dlc();
                    Total_Property_Value();
                } else {
                    textview_permissiblearea_head.setText(getResources().getString(R.string.actualarea));
                    recyclerview_actualarea.setVisibility(View.VISIBLE);
                    recyclerview_permissiblearea.setVisibility(View.GONE);
                    TotalActualValuationFloors();
                    Singleton.getInstance().indPropertyValuation.setDocumentedConstrValueSel(false);
                    Singleton.getInstance().indPropertyValuation.setMeasuredConstrValueSel(true);
                    permission_check_construction = 2;
                    Log.e("Construction_dlc", "radio2");
                    Construction_dlc();
                    Total_Property_Value();
                }
            }
        });


        // Todo Display values from API result and set the values to widgets for Land and Building
        DisplayValuesLandBuilding();

        getStatusfromResult();

        permissibleAreaFloorsTotal();

        displayValuationDetails();


        // id_radio_considerforvaluation_permissiblearea_land and id_radio_considerforvaluation_actualarea_land
        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyValuation.getDocumentedLandValueSel()))) {
            if (Singleton.getInstance().indPropertyValuation.getDocumentedLandValueSel()) {
                permission_check = 1;
                id_radio_considerforvaluation_permissiblearea_land.setChecked(true);
                id_radio_considerforvaluation_actualarea_land.setChecked(false);
                linear_permissiblearea.setVisibility(View.VISIBLE);
                linear_actualarea.setVisibility(View.GONE);
            } else {
                permission_check = 2;
                id_radio_considerforvaluation_permissiblearea_land.setChecked(false);
                id_radio_considerforvaluation_actualarea_land.setChecked(true);
                linear_permissiblearea.setVisibility(View.GONE);
                linear_actualarea.setVisibility(View.VISIBLE);
            }
        }

        // id_radio_considerforvaluation_permissiblearea_construction and id_radio_considerforvaluation_actualarea_construction
        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyValuation.getDocumentedConstrValueSel()))) {
            if (Singleton.getInstance().indPropertyValuation.getDocumentedConstrValueSel()) {
                permission_check_construction = 1;
                id_radio_considerforvaluation_permissiblearea_construction.setChecked(true);
                id_radio_considerforvaluation_actualarea_construction.setChecked(false);
                textview_permissiblearea_head.setText(getResources().getString(R.string.permissiblearea));
                recyclerview_permissiblearea.setVisibility(View.VISIBLE);
                recyclerview_actualarea.setVisibility(View.GONE);
            } else {
                permission_check_construction = 2;
                id_radio_considerforvaluation_permissiblearea_construction.setChecked(false);
                id_radio_considerforvaluation_actualarea_construction.setChecked(true);
                textview_permissiblearea_head.setText(getResources().getString(R.string.actualarea));
                recyclerview_actualarea.setVisibility(View.VISIBLE);
                recyclerview_permissiblearea.setVisibility(View.GONE);
            }
        }


        // spinner - realizableList
        /*ArrayAdapter<String> realizableListAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row_spinner_item, realizableList) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTypeface(general.mediumtypeface());
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);

                ((TextView) v).setTypeface(general.mediumtypeface());
                return v;
            }

        };
        realizableListAdapter.setDropDownViewResource(R.layout.row_spinner_item_popup);
        spinner_realizable.setPrompt(getResources().getString(R.string.realizable_per));
        spinner_realizable.setAdapter(realizableListAdapter);*/


        spinner_realizable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(spinner_realizable);
                realizable_distress_popup(1, spinner_realizable);
            }
        });

        realizableList = new ArrayList<>();
        realizableList = general.Constructionval_common_array();
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getRealizationPercentage())) {
            /* values */
            boolean is_value_from_loop = false;
            for (int x = 0; x < realizableList.size(); x++) {
                if (realizableList.get(x).toString().replace("%", "").trim().equalsIgnoreCase(Singleton.getInstance().indPropertyValuation.getRealizationPercentage())) {
                    is_value_from_loop = true;
                }
            }
            if (is_value_from_loop) {
                /* From array */
                spinner_realizable.setText(Singleton.getInstance().indPropertyValuation.getRealizationPercentage());
                editText_realizable_custom.setText("");
                editText_realizable_custom.setVisibility(View.INVISIBLE);
            } else {
                /* Custom */
                spinner_realizable.setText(getResources().getString(R.string.custom));
                editText_realizable_custom.setVisibility(View.VISIBLE);
                String RealizationPercentage = Singleton.getInstance().indPropertyValuation.getRealizationPercentage();
                if (!general.isEmpty(RealizationPercentage)) {
                    editText_realizable_custom.setText(Singleton.getInstance().indPropertyValuation.getRealizationPercentage());
                }
            }
        } else {
            /* select */
            if (Singleton.getInstance().caseOtherDetailsModel != null && Singleton.getInstance().caseOtherDetailsModel.getData() != null&&Singleton.getInstance().caseOtherDetailsModel.getData().get(0)!=null) {
                if (Singleton.getInstance().caseOtherDetailsModel.getData().get(0).getRealizationPercentage() != null) {
                    spinner_realizable.setText(Singleton.getInstance().caseOtherDetailsModel.getData().get(0).getRealizationPercentage());
                } else {
                    spinner_realizable.setText(getResources().getString(R.string.select));
                }
            } else {
                spinner_realizable.setText(getResources().getString(R.string.select));
            }
            editText_realizable_custom.setText("");
            editText_realizable_custom.setVisibility(View.INVISIBLE);
        }

        editText_realizable_custom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editText_realizable_custom.setError(null);
            }
        });

        /*spinner_realizable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_coming_inside", "spinner_coming_inside");
                if (realizableList.get(position).toString().equalsIgnoreCase("Select")) {
                    editText_realizable_custom.setText("");
                    editText_realizable_custom.setVisibility(View.INVISIBLE);
                    Singleton.getInstance().indPropertyValuation.setRealizationPercentage("");
                } else if (realizableList.get(position).toString().equalsIgnoreCase("Custom")) {
                    editText_realizable_custom.setText("");
                    editText_realizable_custom.setVisibility(View.VISIBLE);
                } else {
                    editText_realizable_custom.setText("");
                    editText_realizable_custom.setVisibility(View.INVISIBLE);
                    String realizableStr = realizableList.get(position).toString().replace("%", "");
                    Singleton.getInstance().indPropertyValuation.setRealizationPercentage(realizableStr);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/


        // spinner - distressList
       /* ArrayAdapter<String> distressListAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row_spinner_item, distressList) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTypeface(general.mediumtypeface());
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTypeface(general.mediumtypeface());
                return v;
            }
        };
        //distressListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distressListAdapter.setDropDownViewResource(R.layout.row_spinner_item_popup);
        spinner_distress.setPrompt(getResources().getString(R.string.distress_per));
        spinner_distress.setAdapter(distressListAdapter);*/

        spinner_distress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(spinner_distress);
                realizable_distress_popup(2, spinner_distress);
            }
        });

        distressList = new ArrayList<>();
        distressList = general.Constructionval_common_array();
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getDistressPercentage())) {
            /* values */
            boolean is_value_from_loop = false;
            for (int x = 0; x < distressList.size(); x++) {
                if (distressList.get(x).toString().replace("%", "").trim().equalsIgnoreCase(Singleton.getInstance().indPropertyValuation.getDistressPercentage())) {
                    is_value_from_loop = true;
                }
            }
            if (is_value_from_loop) {
                /* From array */
                spinner_distress.setText(Singleton.getInstance().indPropertyValuation.getDistressPercentage());
                editText_distress_custom.setText("");
                editText_distress_custom.setVisibility(View.INVISIBLE);
            } else {
                /* Custom */
                spinner_distress.setText(getResources().getString(R.string.custom));
                editText_distress_custom.setVisibility(View.VISIBLE);
                String DistressPercentage = Singleton.getInstance().indPropertyValuation.getDistressPercentage();
                if (!general.isEmpty(DistressPercentage)) {
                    editText_distress_custom.setText(Singleton.getInstance().indPropertyValuation.getDistressPercentage());
                }
            }
        } else {
            /* select */
            if (Singleton.getInstance().caseOtherDetailsModel != null && Singleton.getInstance().caseOtherDetailsModel.getData() != null&&Singleton.getInstance().caseOtherDetailsModel.getData().get(0)!=null) {
                if (Singleton.getInstance().caseOtherDetailsModel.getData().get(0).getDistressPercentage() != null) {
                    spinner_distress.setText(Singleton.getInstance().caseOtherDetailsModel.getData().get(0).getDistressPercentage());
                } else {
                    spinner_distress.setText(getResources().getString(R.string.select));
                }
            } else {
                spinner_distress.setText(getResources().getString(R.string.select));
            }
            editText_distress_custom.setText("");
            editText_distress_custom.setVisibility(View.INVISIBLE);
        }

        editText_distress_custom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editText_distress_custom.setError(null);
            }
        });


       /* spinner_distress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_coming_inside", "spinner_coming_inside");
                if (distressList.get(position).toString().equalsIgnoreCase("Select")) {
                    editText_distress_custom.setText("");
                    editText_distress_custom.setVisibility(View.INVISIBLE);
                    Singleton.getInstance().indPropertyValuation.setDistressPercentage("");
                } else if (distressList.get(position).toString().equalsIgnoreCase("Custom")) {
                    editText_distress_custom.setText("");
                    editText_distress_custom.setVisibility(View.VISIBLE);
                } else {
                    editText_distress_custom.setText("");
                    editText_distress_custom.setVisibility(View.INVISIBLE);
                    String distressStr = distressList.get(position).toString().replace("%", "");
                    Singleton.getInstance().indPropertyValuation.setDistressPercentage(distressStr);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/


        // spinner - carpetList
        /*ArrayAdapter<String> carpetListAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row_spinner_item, carpetList) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTypeface(general.mediumtypeface());
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTypeface(general.mediumtypeface());
                return v;
            }
        };
//        carpetListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carpetListAdapter.setDropDownViewResource(R.layout.row_spinner_item_popup);
        spinner_carpet.setPrompt(getResources().getString(R.string.carpet_per));
        spinner_carpet.setAdapter(carpetListAdapter);*/

        /*spinner_carpet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_coming_inside", "spinner_coming_inside");
                if (carpetList.get(position).toString().equalsIgnoreCase("Select")) {
                    Singleton.getInstance().indPropertyValuation.setCarpetAreaPercentage("");
                } else {
                    String carpetStr = carpetList.get(position).toString().replace("%", "");
                    Singleton.getInstance().indPropertyValuation.setCarpetAreaPercentage(carpetStr);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/


        spinner_carpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(spinner_carpet);
                realizable_distress_popup(3, spinner_carpet);
            }
        });

        carpetList = new ArrayList<>();
        carpetList = general.Constructionval_common_array_carpet();
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getCarpetAreaPercentage())) {
            for (int x = 0; x < carpetList.size(); x++) {
                if (carpetList.get(x).toString().replace("%", "").trim().equalsIgnoreCase(Singleton.getInstance().indPropertyValuation.getCarpetAreaPercentage())) {
                    spinner_carpet.setText(Singleton.getInstance().indPropertyValuation.getCarpetAreaPercentage());
                }
            }
        } else {
            /* select */
            spinner_carpet.setText(getResources().getString(R.string.select));
        }



        /*ArrayAdapter<String> carpet_typeListAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row_spinner_item, carpet_typeList) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTypeface(general.mediumtypeface());
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTypeface(general.mediumtypeface());
                return v;
            }
        };
        carpet_typeListAdapter.setDropDownViewResource(R.layout.row_spinner_item_popup);
        spinner_carpet_type.setPrompt(getResources().getString(R.string.carpet_per_type));
        spinner_carpet_type.setAdapter(carpet_typeListAdapter);*/

        spinner_carpet_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner_carpet_type.setError(null);
                hideSoftKeyboard(spinner_carpet_type);
                carpet_popup(spinner_carpet_type);
            }
        });

        // spinner - carpet_typeList
        carpet_typeList = new ArrayList<>();
        carpet_typeList = general.carpettype_array();
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getCarpetAreaTypeId())) {
            int carpet_id = Integer.parseInt(Singleton.getInstance().indPropertyValuation.getCarpetAreaTypeId());
            spinner_carpet_type.setText(carpet_typeList.get(carpet_id));
        } else {
            /* select */
            spinner_carpet_type.setText(getResources().getString(R.string.select));
        }

        /*spinner_carpet_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_coming_inside", "spinner_coming_inside");
                if (carpet_typeList.get(position).toString().equalsIgnoreCase("Select")) {
                    Singleton.getInstance().indPropertyValuation.setCarpetAreaTypeId("");
                } else {
                    Singleton.getInstance().indPropertyValuation.setCarpetAreaTypeId("" + position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* TODO - Display the final text after after some second  */
                if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getGuidelineValue())) {
                    //  editText_guideline_value.setText(Singleton.getInstance().indPropertyValuation.getGuidelineValue());
                    editText_guideline_value.setText(general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getGuidelineValue()));
                } else {
                    editText_guideline_value.setText("");
                }

                if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getConstructionDLCValue())) {
                    //   editText_construction_value.setText(Singleton.getInstance().indPropertyValuation.getConstructionDLCValue());
                    editText_construction_value.setText(general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getConstructionDLCValue()));
                } else {
                    editText_construction_value.setText("");
                }

            }
        }, 6000);


        return view;
    }

    private void getDetails() {
        general.showloading(getActivity());
        String url = general.ApiBaseUrl() + SettingsUtils.GetPropertyDetails;
        JsonRequestData requestData = new JsonRequestData();
        requestData.setInitQueryUrl(url);
        requestData.setCaseId(SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, ""));
        requestData.setUrl(RequestParam.getPropertyDetails(requestData));
        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(getActivity(), requestData,
                SettingsUtils.GET);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                try {
                    showPopup(requestData.getResponse());
                    general.hideloading();
                } catch (Exception e) {
                    general.hideloading();
                    e.printStackTrace();
                }
            }
        });
        webserviceTask.execute();
    }

    private void showPopup(String response) {

       GetPropertyDetailsModel model = new Gson().fromJson(response, GetPropertyDetailsModel.class);

        if (model.getStatus()==1) {
            Log.e("showPopup", "showPopup: "+new Gson().toJson(response) );
            comparePopup(model);
        } else {
            General.customToast(getResources().getString(R.string.something_wrong), getActivity());
        }
    }

    private void comparePopup(GetPropertyDetailsModel fromJson) {
        final Dialog dialog = new Dialog(getActivity(), R.style.CustomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.popup_compare_property_rate);
        TextView tvLatLng = dialog.findViewById(R.id.tvLatLng);
        TextView tvAddress = dialog.findViewById(R.id.tvAddress);
        TextView tvPropertyType = dialog.findViewById(R.id.tvPropertyType);
        TextView tvNoResult = dialog.findViewById(R.id.tvNoResult);
        Spinner spPropertyType = dialog.findViewById(R.id.spPropertyType);
        Spinner spDistance = dialog.findViewById(R.id.spDistance);
        Button yesBtn = dialog.findViewById(R.id.yesBtn);
        Button noBtn = dialog.findViewById(R.id.noBtn);
        RecyclerView rvResult = dialog.findViewById(R.id.rvResult);

        ArrayList<String> compareList = new ArrayList<>();
        compareList.add("Select");
        compareList.add("All");
        compareList.add("Same as current");

        ArrayList<String> kmList = new ArrayList<>();
        kmList.add("Select");
        kmList.add("0.5");
        kmList.add("1");
        kmList.add("2");
        kmList.add("3");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.row_spinner_item_, compareList);
        arrayAdapter.setDropDownViewResource(R.layout.row_spinner_item_popup);
        spPropertyType.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter_ = new ArrayAdapter<>(getActivity(),
                R.layout.row_spinner_item_, kmList);
        arrayAdapter_.setDropDownViewResource(R.layout.row_spinner_item_popup);
        spDistance.setAdapter(arrayAdapter_);

        tvLatLng.setText(latvalue.getText().toString() + ", " + longvalue.getText().toString());
        if (fromJson.getData().getPropertyAddress() != null)
            tvAddress.setText(fromJson.getData().getPropertyAddress().toString());

        if (fromJson.getData().getPropertyCategoryName()!=null){
            tvPropertyType.setText(fromJson.getData().getPropertyCategoryName());
        }


        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spPropertyType.getSelectedItemPosition() == 0) {
                    General.customToast("Choose property type!", getActivity());
                    return;
                } else if (spDistance.getSelectedItemPosition() == 0) {
                    General.customToast("Choose distance!", getActivity());
                    return;
                } else if (fromJson.getData().getPropertyCategoryId()==null){
                    General.customToast("Property Type Id Required!", getActivity());
                    return;
                }else {

                    String url = general.ApiBaseUrl() + SettingsUtils.GetPropertyCompareDetails;
                    JsonRequestData requestData = new JsonRequestData();
                    requestData.setUrl(url);
                    requestData.setCaseId(SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, ""));
                    requestData.setDistance(spDistance.getSelectedItem().toString());
                    requestData.setCurrentPropertyType(String.valueOf(fromJson.getData().getPropertyCategoryId()));
                    requestData.setLatitude(latvalue.getText().toString());
                    requestData.setLongitude(longvalue.getText().toString());
                    requestData.setPropertyType(String.valueOf(spPropertyType.getSelectedItemPosition()));
                    requestData.setRequestBody(RequestParam.GetPropertyCompareDetails(requestData));
                    WebserviceCommunicator webserviceTask = new WebserviceCommunicator(getActivity(), requestData,
                            SettingsUtils.PUT);
                    webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                        @Override
                        public void onTaskComplete(JsonRequestData requestData) {
                            try {
                                GetPropertyCompareDetailsModel model = new Gson().fromJson(requestData.getResponse(), GetPropertyCompareDetailsModel.class);
                                if (model.getStatus()==1) {

                                    if (model.getData().size() > 0) {

                                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

                                        //Setting the adapter
                                        rvResult.setAdapter(new ComparePropertyRateAdapter(model.getData(), getActivity()));
                                        rvResult.setLayoutManager(llm);

                                        rvResult.setVisibility(View.VISIBLE);
                                        tvNoResult.setVisibility(View.GONE);
                                    } else {
                                        rvResult.setVisibility(View.GONE);
                                        tvNoResult.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    rvResult.setVisibility(View.GONE);
                                    tvNoResult.setVisibility(View.VISIBLE);
                                    General.customToast(getResources().getString(R.string.something_wrong), getActivity());
                                }

                            } catch (Exception e) {
                                General.customToast(getResources().getString(R.string.something_wrong), getActivity());
                                e.printStackTrace();
                            }
                        }
                    });
                    webserviceTask.execute();
                }
            }
        });


        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    /****** Get Status for edit inspection valuation ******/
    public void getStatusfromResult() {
        String status = "";
        if (Singleton.getInstance().aCase != null) {
            status = String.valueOf(Singleton.getInstance().aCase.getStatus());
            if (!general.isEmpty(status)) {
                //  if (status.equalsIgnoreCase("2")) { //Edit inspection
                String floorsno = String.valueOf(Singleton.getInstance().indProperty.getNoOfFloors());
                if (!general.isEmpty(floorsno)) {
                    initValuationPermissibleFloors(floorsno);
                } else {
                    general.CustomToast("Floor No. empty");
                }
            }
        }
    }

    public void getValuationDynamicFloorsResult(String floorsno) {

        String status = "";
        if (!general.isEmpty(floorsno)) {
            Log.e("valuation_floor", floorsno);
            initValuationPermissibleFloors(floorsno);
        } else {
            general.CustomToast("Floor No. empty");
        }
    }

    /****** Get Valuation for permissible floor *******/
    public void initValuationPermissibleFloors(String floors_no) {

        /*FragmentBuilding.listAdapter*/
        //if (Singleton.getInstance().indPropertyFloors != null)
        // if (Singleton.getInstance().indPropertyFloors.size() > 0) {
        list = new ArrayList<>();
        if (general.isEmpty(floors_no)) {
            Log.e("ValuationPermissible", "Floors empty");
        } else {

            if (Singleton.getInstance().indPropertyFloorsValuations.size() > 0) {
                Log.e("ValuationPermissible", "Floors created");
                for (int i = 0; i < Singleton.getInstance().indPropertyFloorsValuations.size(); i++) {
                    IndPropertyFloorsValuation stepsModel = Singleton.getInstance().indPropertyFloorsValuations.get(i);
                    //IndPropertyFloorsValuation stepsModel = new IndPropertyFloorsValuation();
                    int id = Singleton.getInstance().indPropertyFloorsValuations.get(i).getCaseId();
                    int floorno = Singleton.getInstance().indPropertyFloorsValuations.get(i).getFloorNo();

                    stepsModel.setCaseId(Integer.valueOf(caseid));
                    stepsModel.setDocumentConstrRate(Singleton.getInstance().indPropertyFloorsValuations.get(i).getDocumentConstrRate());
                    stepsModel.setDocumentConstrValue(Singleton.getInstance().indPropertyFloorsValuations.get(i).getDocumentConstrValue());
                    stepsModel.setMeasuredConstrRate(Singleton.getInstance().indPropertyFloorsValuations.get(i).getMeasuredConstrRate());
                    stepsModel.setMeasuredConstrValue(Singleton.getInstance().indPropertyFloorsValuations.get(i).getMeasuredConstrValue());
                    stepsModel.setFloorNo(Singleton.getInstance().indPropertyFloorsValuations.get(i).getFloorNo());
                    stepsModel.setFloorDepreciationValue(Singleton.getInstance().indPropertyFloorsValuations.get(i).getFloorDepreciationValue());
                    stepsModel.setFloorCarpetArea(Singleton.getInstance().indPropertyFloorsValuations.get(i).getFloorCarpetArea());
                    list.add(stepsModel);
                }

                int floors = Integer.valueOf(floors_no);
                int previousfloors = Singleton.getInstance().indPropertyFloorsValuations.size();
                if (floors > previousfloors) {
                    int future_floor = floors - previousfloors;
                    for (int i = previousfloors; i < floors; i++) {
                        IndPropertyFloorsValuation stepsModel = new IndPropertyFloorsValuation();
                        stepsModel.setCaseId(Integer.valueOf(caseid));
                        stepsModel.setDocumentConstrValue("");
                        stepsModel.setDocumentConstrRate("");
                        stepsModel.setMeasuredConstrValue("");
                        stepsModel.setMeasuredConstrRate("");
                        stepsModel.setFloorDepreciationValue("");
                        stepsModel.setCaseId(Integer.valueOf(caseid));
                        list.add(stepsModel);

                        Singleton.getInstance().indPropertyFloorsValuations.add(stepsModel);
                    }
                } else {
                    /*******
                     * For Floors Delete from the Current Floors as per the floor no
                     * ******/
                    int delete_floors = previousfloors - floors;
                    for (int i = floors; i < previousfloors; i++) {
                        if (i < list.size()) {
                            list.remove(i);
                        }
                        if (i < Singleton.getInstance().indPropertyFloorsValuations.size()) {
                            Singleton.getInstance().indPropertyFloorsValuations.remove(i);
                            i = i - 1;
                            previousfloors = previousfloors - 1;
                        }
                    }
                }


            } else {

                if (!general.isEmpty(floors_no)) {

                    int floors_valsize = Integer.valueOf(floors_no);
                    list = new ArrayList<>();
                    for (int i = 0; i < floors_valsize; i++) {
                        IndPropertyFloorsValuation stepsModel = new IndPropertyFloorsValuation();
                        stepsModel.setCaseId(Integer.valueOf(caseid));
                        stepsModel.setDocumentConstrValue("");
                        stepsModel.setDocumentConstrRate("");
                        stepsModel.setMeasuredConstrValue("");
                        stepsModel.setMeasuredConstrRate("");
                        stepsModel.setFloorDepreciationValue("");
                        stepsModel.setCaseId(Integer.valueOf(caseid));
                        list.add(stepsModel);
                        Singleton.getInstance().indPropertyFloorsValuations = list;
                        Log.e("ValuationPermissible", "Floors1 created");

                    }
                }
            }
        }


        if (FragmentBuilding.listAdapter != null) {

            listAdapter = new ValuationPermissibleAreaAdapter(list, FragmentBuilding.listAdapter.getStepList(), fragmentActivity);
            recyclerview_permissiblearea.setAdapter(listAdapter);

            listActualAdapter = new ValuationActualAreaAdapter(list, FragmentBuilding.listAdapter.getStepList(), fragmentActivity);
            recyclerview_actualarea.setAdapter(listActualAdapter);


        }
    }

    private void DisplayValuesLandBuilding() {

        String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        if (!general.isEmpty(caseid))
            Singleton.getInstance().indPropertyValuation.setCaseId(Integer.parseInt(caseid));


        /* guideline rate and value and measurment */
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getGuidelineRate())) {
            editText_guideline_rate.setText(Singleton.getInstance().indPropertyValuation.getGuidelineRate());
        } else {
            editText_guideline_rate.setText("");
        }

        // spinner - Measuremnet - GuidelineRateUnit
        ArrayAdapter<Measurements> measurementsArrayAdapter_guild = new ArrayAdapter<Measurements>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().measurements_list_val_sqya) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTypeface(general.mediumtypeface());
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTypeface(general.mediumtypeface());
                return v;
            }
        };
        measurementsArrayAdapter_guild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_ft_meter.setAdapter(measurementsArrayAdapter_guild);
        spinner_ft_meter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_getMeastId", "::: " + Singleton.getInstance().measurements_list_val_sqya.get(position).getMeasureUnitId());
                Singleton.getInstance().indPropertyValuation.setGuidelineRateUnit("" + Singleton.getInstance().measurements_list_val_sqya.get(position).getMeasureUnitId());
                mea_unit = position;
                permissionarea_dlc();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyValuation.getGuidelineRateUnit()))) {
            Log.e("spinner_qualityofcon", "::: " + Singleton.getInstance().indPropertyValuation.getGuidelineRateUnit());
            ArrayList<Measurements> qualityConstructions_ = new ArrayList<>();
            qualityConstructions_ = Singleton.getInstance().measurements_list_val_sqya;
            for (int x = 0; x < qualityConstructions_.size(); x++) {
                int i = Integer.parseInt(Singleton.getInstance().indPropertyValuation.getGuidelineRateUnit());
                if (qualityConstructions_.get(x).getMeasureUnitId() == i) {
                    spinner_ft_meter.setSelection(x);
                    mea_unit = x;
                }
            }
        }

        /* Permission Area */
        if (!general.isEmpty(Singleton.getInstance().indProperty.getDocumentLandArea())) {
            editText_permissiblearea.setText(Singleton.getInstance().indProperty.getDocumentLandArea());
        } else {
            editText_permissiblearea.setText("");
        }

        int docareaUnit = Singleton.getInstance().indProperty.getDocumentLandAreaUnit();
        if (docareaUnit != 0) {
            editText_unit_permissiblearea.setText(Singleton.getInstance().measurements_list.get(docareaUnit).toString());
            editText_unit_actualarea.setText(Singleton.getInstance().measurements_list.get(docareaUnit).toString());
        } else {
            editText_unit_permissiblearea.setText("");
            editText_unit_actualarea.setText("");
        }

        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getDocumentLandRate())) {
            editText_rate_permissiblearea.setText(Singleton.getInstance().indPropertyValuation.getDocumentLandRate());
        } else {
            editText_rate_permissiblearea.setText("");
        }

        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getDocumentLandValue())) {
            // editText_total_permissiblearea.setText(Singleton.getInstance().indPropertyValuation.getDocumentLandValue());
            editText_total_permissiblearea.setText(general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getDocumentLandValue()));
        } else {
            editText_total_permissiblearea.setText("");
        }

        /* Autual Area */
        if (!general.isEmpty(Singleton.getInstance().indProperty.getMeasuredLandArea())) {
            editText_actualarea.setText(Singleton.getInstance().indProperty.getMeasuredLandArea());
        } else {
            editText_actualarea.setText("");
        }

        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getMeasuredLandRate())) {
            editText_rate_actualarea.setText(Singleton.getInstance().indPropertyValuation.getMeasuredLandRate());
        } else {
            editText_rate_actualarea.setText("");
        }

        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getMeasuredLandValue())) {
            //  editText_total_actualarea.setText(Singleton.getInstance().indPropertyValuation.getMeasuredLandValue());
            editText_total_actualarea.setText(general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getMeasuredLandValue()));
        } else {
            editText_total_actualarea.setText("");
        }

        editText_rate_permissiblearea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    Log.e("onFocus_coming", "onFocus_coming" + hasFocus);
                    // Copy the value to aspercompletion_val
                    String toString = editText_rate_permissiblearea.getText().toString();
                    CommonRate_permissiblearea_method(toString);
                }
            }
        });

        editText_rate_actualarea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    Log.e("onFocus_coming", "onFocus_coming" + hasFocus);
                    // Copy the value to aspercompletion_val
                    String toString = editText_rate_actualarea.getText().toString();
                    CommonRate_actualarea_method(toString);
                }
            }
        });


        /*editText_rate_permissiblearea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String toString = charSequence.toString();
                CommonRate_permissiblearea_method(toString);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText_rate_actualarea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String toString = charSequence.toString();
                CommonRate_actualarea_method(toString);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/


        editText_guideline_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                permissionarea_dlc();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        editText_guideline_rate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    if (permission_check == 1) {
                        editText_guideline_rate.setNextFocusForwardId(R.id.editText_rate_permissiblearea);
                        editText_rate_permissiblearea.setSelection(editText_rate_permissiblearea.getText().toString().length());
                    } else if (permission_check == 2) {
                        editText_guideline_rate.setNextFocusForwardId(R.id.editText_rate_actualarea);
                        editText_rate_actualarea.setSelection(editText_rate_actualarea.getText().toString().length());
                    }
                }
                return false;
            }
        });

        editText_rate_permissiblearea.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    editText_rate_permissiblearea.setNextFocusForwardId(R.id.editText_construction_rate);
                    editText_construction_rate.setSelection(editText_construction_rate.getText().toString().length());
                }
                return false;
            }
        });

        editText_rate_actualarea.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    editText_rate_actualarea.setNextFocusForwardId(R.id.editText_construction_rate);
                    editText_construction_rate.setSelection(editText_construction_rate.getText().toString().length());
                }
                return false;
            }
        });





        /* Construction value */
        /* Construction rate and value and measurment */
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getConstructionDLCRate())) {
            editText_construction_rate.setText(Singleton.getInstance().indPropertyValuation.getConstructionDLCRate());
        } else {
            editText_construction_rate.setText("");
        }


        // spinner - Measuremnet - ConstructionDLCRateUnit
        ArrayAdapter<Measurements> measurementsArrayAdapter_guild_cons = new ArrayAdapter<Measurements>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().measurements_list_val_sqya) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTypeface(general.mediumtypeface());
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTypeface(general.mediumtypeface());
                return v;
            }
        };
        measurementsArrayAdapter_guild_cons.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_ft_meter_construction.setAdapter(measurementsArrayAdapter_guild_cons);
        spinner_ft_meter_construction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_getMeastId", "::: " + Singleton.getInstance().measurements_list_val_sqya.get(position).getMeasureUnitId());
                Singleton.getInstance().indPropertyValuation.setConstructionDLCRateUnit("" + Singleton.getInstance().measurements_list_val_sqya.get(position).getMeasureUnitId());
                mea_unit_construction = position;
                Construction_dlc();
                /*if (Construction_dlc_mea){
                    Log.e("Construction_dlc", "spinner");
                    Construction_dlc();
                }else {
                    Construction_dlc_mea = true;
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyValuation.getConstructionDLCRateUnit()))) {
            Log.e("spinner_qualityofcon", "::: " + Singleton.getInstance().indPropertyValuation.getConstructionDLCRateUnit());
            ArrayList<Measurements> qualityConstructions_ = new ArrayList<>();
            qualityConstructions_ = Singleton.getInstance().measurements_list_val_sqya;
            for (int x = 0; x < qualityConstructions_.size(); x++) {
                int i = Integer.parseInt(Singleton.getInstance().indPropertyValuation.getConstructionDLCRateUnit());
                if (qualityConstructions_.get(x).getMeasureUnitId() == i) {
                    spinner_ft_meter_construction.setSelection(x);
                    mea_unit_construction = x;
                }
            }
        }

        editText_construction_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                Log.e("Construction_dlc", "editext");
                Construction_dlc();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /* ProposedValuation */
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getProposedValuation())) {
            edittext_proposedvaluation_result.setText(Singleton.getInstance().indPropertyValuation.getProposedValuation());
        } else {
            edittext_proposedvaluation_result.setText("");
        }
        /* ProposedValuationComments */
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getProposedValuationComments())) {
            editText_remarks.setText(Singleton.getInstance().indPropertyValuation.getProposedValuationComments());
        } else {
            editText_remarks.setText("");
        }

    }

    private void initValues() {

        textview_landvaluation.setTypeface(general.mediumtypeface());
        textview_constructionvaluation.setTypeface(general.mediumtypeface());

        textview_unit_land.setTypeface(general.regulartypeface());
        textview_unit_construction.setTypeface(general.regulartypeface());
        textview_floor_head.setTypeface(general.regulartypeface());
        textview_permissiblearea_head.setTypeface(general.regulartypeface());
        textview_rate_head.setTypeface(general.regulartypeface());
        textview_value_head.setTypeface(general.regulartypeface());
        textview_dep_per.setTypeface(general.regulartypeface());
        textview_depvalue.setTypeface(general.regulartypeface());
        textview_permissiblearea_floor.setTypeface(general.regulartypeface());
        textview_permissiblearea_unit.setTypeface(general.regulartypeface());
        textview_permissiblearea_value.setTypeface(general.regulartypeface());
        textview_permissiblearea_dep_value.setTypeface(general.regulartypeface());
        textview_actual_floor.setTypeface(general.regulartypeface());
        textview_actual_unit.setTypeface(general.regulartypeface());
        textview_actual_value.setTypeface(general.regulartypeface());
        textview_actual_dep_value.setTypeface(general.regulartypeface());
        textview_totalconstructionvalue.setTypeface(general.regulartypeface());
        textview_totalconstructionvalue_result.setTypeface(general.regulartypeface());
        textview_totalpropertyvalue.setTypeface(general.regulartypeface());
        textview_totalpropertyvalue_result.setTypeface(general.regulartypeface());
        textview_aspercompletion_result.setTypeface(general.regulartypeface());
        textview_insurancevaluepe.setTypeface(general.regulartypeface());
        textview_insurancevaluepe_result.setTypeface(general.regulartypeface());
        textview_proposedvaluation.setTypeface(general.regulartypeface());
        textview_realizable_per.setTypeface(general.regulartypeface());
        textview_distress_per.setTypeface(general.regulartypeface());
        textview_carpet_per.setTypeface(general.regulartypeface());
        textview_select.setTypeface(general.regulartypeface());

        editText_guideline_rate.setTypeface(general.regulartypeface());
        editText_guideline_value.setTypeface(general.regulartypeface());
        editText_permissiblearea.setTypeface(general.regulartypeface());
        editText_unit_permissiblearea.setTypeface(general.regulartypeface());
        editText_rate_permissiblearea.setTypeface(general.regulartypeface());
        editText_total_permissiblearea.setTypeface(general.regulartypeface());
        editText_actualarea.setTypeface(general.regulartypeface());
        editText_unit_actualarea.setTypeface(general.regulartypeface());
        editText_rate_actualarea.setTypeface(general.regulartypeface());
        editText_total_actualarea.setTypeface(general.regulartypeface());
        editText_construction_rate.setTypeface(general.regulartypeface());
        editText_construction_value.setTypeface(general.regulartypeface());
        edittext_permissiblearea_rate.setTypeface(general.regulartypeface());
        edittext_permissiblearea_dep_per.setTypeface(general.regulartypeface());
        edittext_actual_rate.setTypeface(general.regulartypeface());
        edittext_actual_dep_per.setTypeface(general.regulartypeface());
        editText_aspercompletion.setTypeface(general.regulartypeface());
        edittext_proposedvaluation_result.setTypeface(general.regulartypeface());
        editText_remarks.setTypeface(general.regulartypeface());
        editText_realizable_custom.setTypeface(general.regulartypeface());
        editText_distress_custom.setTypeface(general.regulartypeface());

        id_radio_considerforvaluation_permissiblearea_land.setTypeface(general.regulartypeface());
        id_radio_considerforvaluation_actualarea_land.setTypeface(general.regulartypeface());
        id_radio_considerforvaluation_permissiblearea_construction.setTypeface(general.regulartypeface());
        id_radio_considerforvaluation_actualarea_construction.setTypeface(general.regulartypeface());

        llm = new LinearLayoutManager(getActivity());
        //Setting the adapter recyclerview_permissiblearea
        recyclerview_permissiblearea.setLayoutManager(llm);

        llm = new LinearLayoutManager(getActivity());
        //Setting the adapter recyclerview_actualarea
        recyclerview_actualarea.setLayoutManager(llm);

        ratePopupApi = new RatePopupApi(getActivity(), this);
        ratePopupApi.getRatePopup(caseid, String.valueOf(SettingsUtils.Latitudes), String.valueOf(SettingsUtils.Longitudes));

    }

    public void displayValuationDetails() {

        if (Singleton.getInstance().indPropertyValuation != null) {
            String asperComPercentage = Singleton.getInstance().indPropertyValuation.getCompletionPercentage();
            String asperCompValue = Singleton.getInstance().indPropertyValuation.getTotalValueAtCompletion();

            if (!general.isEmpty(asperComPercentage)) {
                if (asperComPercentage.contains(","))
                    editText_aspercompletion.setText(general.ReplaceCommaSaveToString(asperComPercentage));
                else
                    editText_aspercompletion.setText(asperComPercentage);
            }

            if (!general.isEmpty(asperCompValue)) {
                if (asperCompValue.contains(","))
                    textview_aspercompletion_result.setText(general.DecimalFormattedCommaString(String.valueOf(asperCompValue)));

                else
                    textview_aspercompletion_result.setText(asperCompValue);
            }
        }


    }

    public void permissibleAreaFloorsTotal() {
        editText_aspercompletion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String percentage = charSequence.toString();
                if (!general.isEmpty(percentage)) {
                    float sumtotal = 0;
                    int sum_total = 0;
                    String total_property = textview_totalpropertyvalue_result.getText().toString();
                    sumtotal = (general.convertTofloat(total_property) * ((general.convertTofloat(percentage)) / 100));
                    sum_total = general.convertToRoundoff(sumtotal);

                    // textview_aspercompletion_result.setText("" + sum_total);
                    textview_aspercompletion_result.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                } else {
                    textview_aspercompletion_result.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void Total_Property_Value() {
        /******total property result value********/

        String land_total_str = "0";
        String construction_total_str = "0";
        construction_total_str = textview_totalconstructionvalue_result.getText().toString();

        if (permission_check == 1) {
            land_total_str = editText_total_permissiblearea.getText().toString();
        } else if (permission_check == 2) {
            land_total_str = editText_total_actualarea.getText().toString();
        }

        if ((!general.isEmpty(land_total_str)) && (!general.isEmpty(construction_total_str))) {
            float property = (general.convertTofloat(land_total_str)) + (general.convertTofloat(construction_total_str));
            int total_property = general.convertToRoundoff(property);
            //textview_totalpropertyvalue_result.setText("" + total_property);
            textview_totalpropertyvalue_result.setText(general.DecimalFormattedCommaString(String.valueOf(total_property)));

        } else if (!general.isEmpty(land_total_str)) {
            float property = (general.convertTofloat(land_total_str));
            int total_property = general.convertToRoundoff(property);
            //textview_totalpropertyvalue_result.setText("" + total_property);
            textview_totalpropertyvalue_result.setText(general.DecimalFormattedCommaString(String.valueOf(total_property)));

        } else if (!general.isEmpty(construction_total_str)) {
            float property = (general.convertTofloat(construction_total_str));
            int total_property = general.convertToRoundoff(property);
            //textview_totalpropertyvalue_result.setText("" + total_property);
            textview_totalpropertyvalue_result.setText(general.DecimalFormattedCommaString(String.valueOf(total_property)));

        } else {
            textview_totalpropertyvalue_result.setText("");
        }


        /****As per Completion result value******/
        float percentageCompletetotal = 0;
        int percentageComplete_total = 0;
        String asperCompletepercentage = editText_aspercompletion.getText().toString();
        if (!General.isEmpty(asperCompletepercentage)) {
            String total_property = textview_totalpropertyvalue_result.getText().toString();
            if (!general.isEmpty(total_property)) {
                percentageCompletetotal = (general.convertTofloat(total_property) * ((general.convertTofloat(asperCompletepercentage)) / 100));
                percentageComplete_total = general.convertToRoundoff(percentageCompletetotal);
                // textview_aspercompletion_result.setText("" + percentageComplete_total);
                textview_aspercompletion_result.setText(general.DecimalFormattedCommaString(String.valueOf(percentageComplete_total)));

            } else {
                textview_aspercompletion_result.setText("");
            }
        }

    }

    public void TotalPermissibleValuationFloors() {

        /****Total Construction result value******/
        getValFloorlist = new ArrayList<>();
        if (listAdapter != null) {
            getValFloorlist = listAdapter.getFloorvaluationStepList();
        }
        int total_construction = 0;
        if (getValFloorlist.size() > 0) {
            total_construction = general.getTotalConstructionValue(getValFloorlist);
            /*textview_totalconstructionvalue_result.setText("" + total_construction);
            textview_insurancevaluepe_result.setText("" + total_construction);*/
            textview_totalconstructionvalue_result.setText(general.DecimalFormattedCommaString(String.valueOf(total_construction)));
            textview_insurancevaluepe_result.setText(general.DecimalFormattedCommaString(String.valueOf(total_construction)));

        }

        Total_Property_Value();
    }

    public void TotalActualValuationFloors() {


        /****Total Construction result value******/
        getValActualFloorlist = new ArrayList<>();
        if (listActualAdapter != null) {
            getValActualFloorlist = listActualAdapter.getFloorvaluationStepList();
        }
        int total_construction = 0;
        if (getValActualFloorlist.size() > 0) {
            total_construction = general.getTotalConstructionActualValue(getValActualFloorlist);
            /*textview_totalconstructionvalue_result.setText("" + total_construction);
            textview_insurancevaluepe_result.setText("" + total_construction);*/
            textview_totalconstructionvalue_result.setText(general.DecimalFormattedCommaString(String.valueOf(total_construction)));
            textview_insurancevaluepe_result.setText(general.DecimalFormattedCommaString(String.valueOf(total_construction)));

        }

        Total_Property_Value();
    }

    public static void saveIndValuationFloorsCalculation() {

        /*****
         * set Floors valuation Calculation dynamic result
         * *****/
        setFloorsValuationCalcListData();

        /*****
         * set the total construction and property values
         * ******/
        String total_construct = textview_totalconstructionvalue_result.getText().toString();
        String total_property = textview_totalpropertyvalue_result.getText().toString();
        String aspercomp_resultval = textview_aspercompletion_result.getText().toString();
        String aspercomp_percentage = editText_aspercompletion.getText().toString();
        Singleton.getInstance().indPropertyValuation.setTotalConstructionValue(general.ReplaceCommaSaveToString(total_construct));
        Singleton.getInstance().indPropertyValuation.setInsuranceValue(general.ReplaceCommaSaveToString(total_construct));
        Singleton.getInstance().indPropertyValuation.setTotalPropertyValue(general.ReplaceCommaSaveToString(total_property));
        Singleton.getInstance().indPropertyValuation.setCompletionPercentage(general.ReplaceCommaSaveToString(aspercomp_percentage));
        Singleton.getInstance().indPropertyValuation.setTotalValueAtCompletion(general.ReplaceCommaSaveToString(aspercomp_resultval));
    }

    public static void setFloorsValuationCalcListData() {

        String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

        JSONArray jsonArray = new JSONArray();
        ArrayList<IndPropertyFloorsValuation> indPropertyFloorsValuationArrayList = new ArrayList<>();
        getValFloorlistSave = listAdapter.getFloorvaluationStepList();

        for (int j = 0; j < getValFloorlistSave.size(); j++) {
           /* general.CustomToast(getValFloorlistSave.get(j).getDocumentConstrValue() + ",," +
                    getValFloorlistSave.get(j).getMeasuredConstrValue() + "  area dep%::" +
                    getValFloorlistSave.get(j).getFloorDepreciationValue());
*/
            if (Singleton.getInstance().indPropertyFloorsValuations.size() > 0) {

                IndPropertyFloorsValuation indPropertyFloorsValuation = new IndPropertyFloorsValuation();

                indPropertyFloorsValuation.setCaseId(Integer.valueOf(caseid)); //Integer.value(caseid);
                indPropertyFloorsValuation.setDocumentConstrRate(getValFloorlistSave.get(j).getDocumentConstrRate());
                indPropertyFloorsValuation.setDocumentConstrValue(general.ReplaceCommaSaveToString(getValFloorlistSave.get(j).getDocumentConstrValue()));
                indPropertyFloorsValuation.setMeasuredConstrRate(getValFloorlistSave.get(j).getMeasuredConstrRate());
                indPropertyFloorsValuation.setMeasuredConstrValue(general.ReplaceCommaSaveToString(getValFloorlistSave.get(j).getMeasuredConstrValue()));
                indPropertyFloorsValuation.setFloorDepreciationValue(general.ReplaceCommaSaveToString(getValFloorlistSave.get(j).getFloorDepreciationValue()));
                indPropertyFloorsValuation.setFloorNo(j + 1);
                indPropertyFloorsValuationArrayList.add(indPropertyFloorsValuation);


            } else {
                // Add New Ind floors valuation
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("CaseId    ", caseid);
                    jsonObject.put("docrate", getValFloorlistSave.get(j).getDocumentConstrRate());
                    jsonObject.put("docval", getValFloorlistSave.get(j).getDocumentConstrValue());
                    jsonArray.put(jsonObject);

                    IndPropertyFloorsValuation indPropertyFloorsValuation = new IndPropertyFloorsValuation();
                    indPropertyFloorsValuation.setCaseId(Integer.valueOf(caseid)); //Integer.value(caseid);
                    indPropertyFloorsValuation.setDocumentConstrRate(getValFloorlistSave.get(j).getDocumentConstrRate());
                    indPropertyFloorsValuation.setDocumentConstrValue(general.ReplaceCommaSaveToString(getValFloorlistSave.get(j).getDocumentConstrValue()));
                    indPropertyFloorsValuation.setMeasuredConstrRate(getValFloorlistSave.get(j).getMeasuredConstrRate());
                    indPropertyFloorsValuation.setMeasuredConstrValue(general.ReplaceCommaSaveToString(getValFloorlistSave.get(j).getMeasuredConstrValue()));
                    indPropertyFloorsValuation.setFloorDepreciationValue(general.ReplaceCommaSaveToString(getValFloorlistSave.get(j).getFloorDepreciationValue()));
                    indPropertyFloorsValuation.setFloorNo(j + 1);
                    indPropertyFloorsValuationArrayList.add(indPropertyFloorsValuation);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            Singleton.getInstance().indPropertyFloorsValuations = indPropertyFloorsValuationArrayList;
        }
    }

    public void permissionarea_dlc() {
        String permissiblearea_str = editText_permissiblearea.getText().toString();
        String actualarea_str = editText_actualarea.getText().toString();
        int measurment_unit_property = Singleton.getInstance().indProperty.getDocumentLandAreaUnit();
        String guideline_rate_str = editText_guideline_rate.getText().toString();


        /* PERMISSION AREA start */
        if (permission_check == 1) {
            /* 1 -> sq.ft */
            if (mea_unit == 1) {
                /* check the null based on permission str and unit */
                if ((!general.isEmpty(permissiblearea_str)) && (mea_unit != 0) && (!general.isEmpty(guideline_rate_str))) {
                    if (measurment_unit_property == 1) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                        //editText_guideline_value.setText("" + sum_total);
                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("10.7639"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("9"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("43560"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("107639"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("17452.0069808"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    }
                } else {
                    /* all text should be null*/
                    editText_guideline_value.setText("");
                }
                /* 2 -> sq.m */
            } else if (mea_unit == 2) {
                /* check the null based on permission str and unit */
                if ((!general.isEmpty(permissiblearea_str)) && (mea_unit != 0) && (!general.isEmpty(guideline_rate_str))) {
                    if (measurment_unit_property == 1) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("0.09290304"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("0.836127"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("4046.85642"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("43560"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("1620.4319957"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    }
                } else {
                    /* all text should be null*/
                    editText_guideline_value.setText("");
                }
                /* 3 -> sq.yard */
            } else if (mea_unit == 3) {
                /* check the null based on permission str and unit */
                if ((!general.isEmpty(permissiblearea_str)) && (mea_unit != 0) && (!general.isEmpty(guideline_rate_str))) {
                    if (measurment_unit_property == 1) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("0.111111"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("1.19599"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("4840"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("11959.9"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("1620.4319957"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    }
                } else {
                    /* all text should be null*/
                    editText_guideline_value.setText("");
                }
            }
            /* PERMISSION AREA over */
            /* AUTUAL AREA start */
        } else if (permission_check == 2) {
            /* 1 -> sq.ft */
            if (mea_unit == 1) {
                /* check the null based on permission str and unit */
                if ((!general.isEmpty(actualarea_str)) && (mea_unit != 0) && (!general.isEmpty(guideline_rate_str))) {
                    if (measurment_unit_property == 1) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("10.7639"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("9"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("43560"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("107639"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("17452.0069808"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    }
                } else {
                    /* all text should be null*/
                    editText_guideline_value.setText("");
                }
                /* 2 -> sq.m */
            } else if (mea_unit == 2) {
                /* check the null based on permission str and unit */
                if ((!general.isEmpty(actualarea_str)) && (mea_unit != 0) && (!general.isEmpty(guideline_rate_str))) {
                    if (measurment_unit_property == 1) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("0.09290304"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("1.1959900463011"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("4046.85642"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("43560"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("1620.4319957"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    }
                } else {
                    /* all text should be null*/
                    editText_guideline_value.setText("");
                }

                /* 3 -> sq.yard */
            } else if (mea_unit == 3) {
                /* check the null based on permission str and unit */
                if ((!general.isEmpty(actualarea_str)) && (mea_unit != 0) && (!general.isEmpty(guideline_rate_str))) {
                    if (measurment_unit_property == 1) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("0.111111"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("1.19599"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("4840"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("11959.9"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("1620.4319957"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    }
                } else {
                    /* all text should be null*/
                    editText_guideline_value.setText("");
                }
            }


        }
        /* AUTUAL AREA end */
    }

    public static void Construction_dlc() {

        int measurment_unit_property = measurment_unit_property_for_dlc;
        String permissiblearea_str = permissiblearea_str_dlc;
        String actualarea_str = actualarea_str_dlc;
        String guideline_rate_str = editText_construction_rate.getText().toString();

        /* PERMISSION AREA start */
        if (permission_check_construction == 1) {
            /* 1 -> sq.ft */
            if (mea_unit_construction == 1) {
                /* check the null based on permission str and unit */
                if ((!general.isEmpty(permissiblearea_str)) && (mea_unit_construction != 0) && (!general.isEmpty(guideline_rate_str))) {
                    if (measurment_unit_property == 1) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("10.7639"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("9"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("43560"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("107639"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("17452.0069808"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    }
                } else {
                    /* all text should be null*/
                    editText_construction_value.setText("");
                }
                /* 2 -> sq.m */
            } else if (mea_unit_construction == 2) {
                /* check the null based on permission str and unit */
                if ((!general.isEmpty(permissiblearea_str)) && (mea_unit_construction != 0) && (!general.isEmpty(guideline_rate_str))) {
                    if (measurment_unit_property == 1) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("0.09290304"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("0.836127"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("4046.85642"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("43560"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("1620.4319957"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    }
                } else {
                    /* all text should be null*/
                    editText_construction_value.setText("");
                }
                /* 3 -> sq.yard */
            } else if (mea_unit_construction == 3) {
                /* check the null based on permission str and unit */
                if ((!general.isEmpty(permissiblearea_str)) && (mea_unit_construction != 0) && (!general.isEmpty(guideline_rate_str))) {
                    if (measurment_unit_property == 1) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("0.111111"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("1.19599"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("4840"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("11959.9"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("1620.4319957"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    }
                } else {
                    /* all text should be null*/
                    editText_construction_value.setText("");
                }
            }
            /* PERMISSION AREA over */
            /* AUTUAL AREA start */
        } else if (permission_check_construction == 2) {
            /* 1 -> sq.ft */
            if (mea_unit_construction == 1) {
                /* check the null based on permission str and unit */
                if ((!general.isEmpty(actualarea_str)) && (mea_unit_construction != 0) && (!general.isEmpty(guideline_rate_str))) {
                    if (measurment_unit_property == 1) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //  editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("10.7639"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //   editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("9"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //   editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("43560"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //  editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("107639"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("17452.0069808"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    }
                } else {
                    /* all text should be null*/
                    editText_construction_value.setText("");
                }
                /* 2 -> sq.m */
            } else if (mea_unit_construction == 2) {
                /* check the null based on permission str and unit */
                if ((!general.isEmpty(actualarea_str)) && (mea_unit_construction != 0) && (!general.isEmpty(guideline_rate_str))) {
                    if (measurment_unit_property == 1) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("0.09290304"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("1.1959900463011"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("4046.85642"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("43560"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("1620.4319957"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    }
                } else {
                    /* all text should be null*/
                    editText_construction_value.setText("");
                }
                /* 3 -> sq.yard */
            } else if (mea_unit_construction == 3) {
                /* check the null based on permission str and unit */
                if ((!general.isEmpty(actualarea_str)) && (mea_unit_construction != 0) && (!general.isEmpty(guideline_rate_str))) {
                    if (measurment_unit_property == 1) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("0.111111"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("1.19599"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("4840"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("11959.9"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("1620.4319957"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        //editText_construction_value.setText("" + sum_total);
                        editText_construction_value.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    }
                } else {
                    /* all text should be null*/
                    editText_construction_value.setText("");
                }
            }
        }
        /* AUTUAL AREA end */
    }

    public void save_landval() {
        Singleton.getInstance().indPropertyValuation.setGuidelineRate(editText_guideline_rate.getText().toString());
        Singleton.getInstance().indPropertyValuation.setGuidelineValue(general.ReplaceCommaSaveToString(editText_guideline_value.getText().toString()));
        Singleton.getInstance().indPropertyValuation.setDocumentLandRate(editText_rate_permissiblearea.getText().toString());
        Singleton.getInstance().indPropertyValuation.setDocumentLandValue(general.ReplaceCommaSaveToString(editText_total_permissiblearea.getText().toString()));
        Singleton.getInstance().indPropertyValuation.setMeasuredLandRate(editText_rate_actualarea.getText().toString());
        Singleton.getInstance().indPropertyValuation.setMeasuredLandValue(general.ReplaceCommaSaveToString(editText_total_actualarea.getText().toString()));
        /* construction rate */
        Singleton.getInstance().indPropertyValuation.setConstructionDLCRate(editText_construction_rate.getText().toString());
        Singleton.getInstance().indPropertyValuation.setConstructionDLCValue(general.ReplaceCommaSaveToString(editText_construction_value.getText().toString()));
        Singleton.getInstance().indPropertyValuation.setProposedValuation(edittext_proposedvaluation_result.getText().toString());
        Singleton.getInstance().indPropertyValuation.setProposedValuationComments(editText_remarks.getText().toString());
    }

    public void permission_measurment(String str) {
        if (editText_permissiblearea != null) {
            if (!general.isEmpty(str)) {
                editText_permissiblearea.setText(str);
                permission_check = 1;
                permissionarea_dlc();
                /* multiple the above compond into  */
                String permissiblearea_str = editText_permissiblearea.getText().toString();
                if (!general.isEmpty(editText_rate_permissiblearea.getText().toString()) && !general.isEmpty(permissiblearea_str)) {
                    float sumtotal = 0;
                    int sum_total = 0;
                    sumtotal = (general.convertTofloat(editText_rate_permissiblearea.getText().toString())) * (general.convertTofloat(permissiblearea_str));
//                    sum_total = (int) sumtotal;
                    sum_total = general.convertToRoundoff(sumtotal);
                    //editText_total_permissiblearea.setText("" + sum_total);
                    editText_total_permissiblearea.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    Total_Property_Value();
                } else {
                    editText_total_permissiblearea.setText("");
                }
            } else {
                editText_permissiblearea.setText("");
                editText_total_permissiblearea.setText("");
            }
        }
    }

    public void actual_measurment(String str) {
        if (editText_actualarea != null) {
            if (!general.isEmpty(str)) {
                editText_actualarea.setText(str);
                permission_check = 2;
                permissionarea_dlc();
                /* multiple the above compond into  */
                String actualarea_str = editText_actualarea.getText().toString();
                if (!general.isEmpty(editText_rate_permissiblearea.getText().toString()) && !general.isEmpty(actualarea_str)) {
                    float sumtotal = 0;
                    int sum_total = 0;
                    sumtotal = (general.convertTofloat(editText_rate_permissiblearea.getText().toString())) * (general.convertTofloat(actualarea_str));
//                    sum_total = (int) sumtotal;
                    sum_total = general.convertToRoundoff(sumtotal);
                    //editText_total_actualarea.setText("" + sum_total);
                    editText_total_actualarea.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                    Total_Property_Value();
                } else {
                    editText_total_actualarea.setText("");
                }
            } else {
                editText_actualarea.setText("");
                editText_total_actualarea.setText("");
            }
        }

    }

    public void get_measurment_land(int id, String name) {
        if (editText_unit_permissiblearea != null) {
            if (id != 0) {
                editText_unit_permissiblearea.setText(name);
                editText_unit_actualarea.setText(name);
                Singleton.getInstance().indProperty.setDocumentLandAreaUnit(id);
                Singleton.getInstance().indProperty.setMeasuredLandAreaUnit(id);
                permissionarea_dlc();
            } else {
                editText_unit_permissiblearea.setText("");
                editText_unit_actualarea.setText("");
            }
        }
    }

    /* construction DLC from floors */
    public static void constrction_measurment(String str) {
        if (!general.isEmpty(str)) {
            permission_check_construction = 1;
            permissiblearea_str_dlc = str;
            Log.e("Construction_dlc", "measurment");
            Construction_dlc();
        }
    }

    public static void constrction_actual(String str) {
        if (!general.isEmpty(str)) {
            permission_check_construction = 2;
            actualarea_str_dlc = str;
            Log.e("Construction_dlc", "actual");
            Construction_dlc();
        }
    }

    public void get_constrction_land(int id) {
        if (id != 0) {
            measurment_unit_property_for_dlc = id;
            Log.e("Construction_dlc", "land");
            // Method 2 - constant sq.ft fixed
            // Construction_dlc();
        }
    }

    @SuppressLint("SetTextI18n")
    public void aspercompletion_val(String as_per_com) {
        if (!general.isEmpty(as_per_com)) {
            if (as_per_com.contains(",")) {
                editText_aspercompletion.setText(general.ReplaceCommaSaveToString(as_per_com));
            } else {
                editText_aspercompletion.setText("" + as_per_com);
            }
        }
    }

    public void CommonRate_permissiblearea_method(String str) {

        String rate_str = str;
        editText_rate_actualarea.setText(rate_str);
        String permissiblearea_str = editText_permissiblearea.getText().toString();

        if (!general.isEmpty(rate_str) && !general.isEmpty(permissiblearea_str)) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(rate_str)) * (general.convertTofloat(permissiblearea_str));
            sum_total = general.convertToRoundoff(sumtotal);
            editText_total_permissiblearea.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));
        } else {
            editText_total_permissiblearea.setText("");
        }

        String actualarea_str = editText_actualarea.getText().toString();
        if (!general.isEmpty(rate_str) && !general.isEmpty(actualarea_str)) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(rate_str)) * (general.convertTofloat(actualarea_str));
            sum_total = general.convertToRoundoff(sumtotal);
            editText_total_actualarea.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));
        } else {
            editText_total_actualarea.setText("");
        }

        Total_Property_Value();
    }

    public void CommonRate_actualarea_method(String str) {

        String rate_str = str;
        editText_rate_permissiblearea.setText(rate_str);
        String permissiblearea_str = editText_actualarea.getText().toString();

        if (!general.isEmpty(rate_str) && !general.isEmpty(permissiblearea_str)) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(rate_str)) * (general.convertTofloat(permissiblearea_str));
            sum_total = general.convertToRoundoff(sumtotal);
            editText_total_actualarea.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));
        } else {
            editText_total_actualarea.setText("");
        }

        String actualarea_str = editText_permissiblearea.getText().toString();
        if (!general.isEmpty(rate_str) && !general.isEmpty(actualarea_str)) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(rate_str)) * (general.convertTofloat(actualarea_str));
            sum_total = general.convertToRoundoff(sumtotal);
            editText_total_permissiblearea.setText(general.DecimalFormattedCommaString(String.valueOf(sum_total)));
        } else {
            editText_total_permissiblearea.setText("");
        }

        Total_Property_Value();
    }

    public static boolean valid_realizationValue() {
        boolean is_custom_valid = false;
        if (editText_realizable_custom.getVisibility() == View.VISIBLE) {
            // realizable_custom - > From Custom value
            String realizable_custom = editText_realizable_custom.getText().toString();
            if (!general.isEmpty(realizable_custom)) {
                String total_value = textview_totalpropertyvalue_result.getText().toString();
                if ((!general.isEmpty(total_value)) && (!general.isEmpty(realizable_custom))) {
                    int total_int = Integer.parseInt(general.ReplaceCommaSaveToString(total_value));
                    int realizable_int = Integer.parseInt(realizable_custom);
                    int final_realizationValue = general.getcutom_value(total_int, realizable_int);
                    Singleton.getInstance().indPropertyValuation.setRealizationValue("" + final_realizationValue);
                }
                Singleton.getInstance().indPropertyValuation.setRealizationPercentage(realizable_custom);
                editText_realizable_custom.setError(null);
                is_custom_valid = true;
            } else {
                editText_realizable_custom.requestFocus();
                editText_realizable_custom.setError("Please enter realization custom value");
                is_custom_valid = false;
            }
        } else {
            // realizable_custom - > From getRealizationPercentage from the spinner
            String realizable_custom = Singleton.getInstance().indPropertyValuation.getRealizationPercentage();
            if (!general.isEmpty(realizable_custom)) {
                String total_value = textview_totalpropertyvalue_result.getText().toString();
                if ((!general.isEmpty(total_value)) && (!general.isEmpty(realizable_custom))) {
                    int total_int = Integer.parseInt(general.ReplaceCommaSaveToString(total_value));
                    int realizable_int = Integer.parseInt(realizable_custom);
                    int final_realizationValue = general.getcutom_value(total_int, realizable_int);
                    Singleton.getInstance().indPropertyValuation.setRealizationValue("" + final_realizationValue);
                }
            } else {
                Singleton.getInstance().indPropertyValuation.setRealizationValue("");
            }
            is_custom_valid = true;
        }
        return is_custom_valid;
    }

    public static boolean valid_distressValue() {
        boolean is_custom_valid = false;
        if (editText_distress_custom.getVisibility() == View.VISIBLE) {
            // distress_custom - > From Custom value
            String distress_custom = editText_distress_custom.getText().toString();
            if (!general.isEmpty(distress_custom)) {
                String total_value = textview_totalpropertyvalue_result.getText().toString();
                if ((!general.isEmpty(total_value)) && (!general.isEmpty(distress_custom))) {
                    int total_int = Integer.parseInt(general.ReplaceCommaSaveToString(total_value));
                    int distress_int = Integer.parseInt(distress_custom);
                    int final_realizationValue = general.getcutom_value(total_int, distress_int);
                    Singleton.getInstance().indPropertyValuation.setDistressValue("" + final_realizationValue);
                }
                Singleton.getInstance().indPropertyValuation.setDistressPercentage(distress_custom);
                editText_distress_custom.setError(null);
                is_custom_valid = true;
            } else {
                editText_distress_custom.requestFocus();
                editText_distress_custom.setError("Please enter distress custom value");
                is_custom_valid = false;
            }
        } else {
            // distress_custom - > From getDistressPercentage from the spinner
            String distress_custom = Singleton.getInstance().indPropertyValuation.getDistressPercentage();
            if (!general.isEmpty(distress_custom)) {
                String total_value = textview_totalpropertyvalue_result.getText().toString();
                if ((!general.isEmpty(total_value)) && (!general.isEmpty(distress_custom))) {
                    int total_int = Integer.parseInt(general.ReplaceCommaSaveToString(total_value));
                    int distress_int = Integer.parseInt(distress_custom);
                    int final_realizationValue = general.getcutom_value(total_int, distress_int);
                    Singleton.getInstance().indPropertyValuation.setDistressValue("" + final_realizationValue);
                }
            } else {
                Singleton.getInstance().indPropertyValuation.setDistressValue("");
            }
            is_custom_valid = true;
        }
        return is_custom_valid;
    }

    public static boolean valid_carpetValue() {
        boolean is_custom_valid = false;
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getCarpetAreaPercentage())) {
            // not null
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getCarpetAreaTypeId())) {
                // not null
                is_custom_valid = true;
            } else {
                editText_remarks.requestFocus();
                spinner_carpet_type.setError("Please select the carpet type");
                Toast.makeText(fragmentActivity, "Please select the carpet type", Toast.LENGTH_SHORT).show();
                is_custom_valid = false;
            }
        } else {
            is_custom_valid = true;
        }
        return is_custom_valid;
    }


    private void hideSoftKeyboard(View addkeys) {
        if ((addkeys != null) && (getActivity() != null)) {
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(addkeys.getWindowToken(), 0);
        }
        show_emptyFocus();
    }

    public void show_emptyFocus() {
        // Show focus
        if (OtherDetails.my_focuslayout != null) {
            OtherDetails.my_focuslayout.requestFocus();
        }
    }


    private void realizable_distress_popup(final int type_is, final TextView textView) {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.distress_popup);

        TextView popuptitle = (TextView) dialog.findViewById(R.id.title);
        popuptitle.setTypeface(general.mediumtypeface());

        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        RadioGroup id_radiogroup_stage = (RadioGroup) dialog.findViewById(R.id.id_radiogroup_stage);
        RadioButton id_radio_select = (RadioButton) dialog.findViewById(R.id.id_radio_select);
        RadioButton id_radio_50 = (RadioButton) dialog.findViewById(R.id.id_radio_50);
        RadioButton id_radio_55 = (RadioButton) dialog.findViewById(R.id.id_radio_55);
        RadioButton id_radio_60 = (RadioButton) dialog.findViewById(R.id.id_radio_60);
        RadioButton id_radio_65 = (RadioButton) dialog.findViewById(R.id.id_radio_65);
        RadioButton id_radio_70 = (RadioButton) dialog.findViewById(R.id.id_radio_70);
        RadioButton id_radio_75 = (RadioButton) dialog.findViewById(R.id.id_radio_75);
        RadioButton id_radio_80 = (RadioButton) dialog.findViewById(R.id.id_radio_80);
        RadioButton id_radio_85 = (RadioButton) dialog.findViewById(R.id.id_radio_85);
        RadioButton id_radio_90 = (RadioButton) dialog.findViewById(R.id.id_radio_90);
        RadioButton id_radio_95 = (RadioButton) dialog.findViewById(R.id.id_radio_95);
        RadioButton id_radio_100 = (RadioButton) dialog.findViewById(R.id.id_radio_100);
        RadioButton id_radio_Custom = (RadioButton) dialog.findViewById(R.id.id_radio_Custom);
        id_radio_select.setTypeface(general.mediumtypeface());
        id_radio_50.setTypeface(general.mediumtypeface());
        id_radio_55.setTypeface(general.mediumtypeface());
        id_radio_60.setTypeface(general.mediumtypeface());
        id_radio_65.setTypeface(general.mediumtypeface());
        id_radio_70.setTypeface(general.mediumtypeface());
        id_radio_75.setTypeface(general.mediumtypeface());
        id_radio_80.setTypeface(general.mediumtypeface());
        id_radio_85.setTypeface(general.mediumtypeface());
        id_radio_90.setTypeface(general.mediumtypeface());
        id_radio_95.setTypeface(general.mediumtypeface());
        id_radio_100.setTypeface(general.mediumtypeface());
        id_radio_Custom.setTypeface(general.mediumtypeface());

        boolean is_empty = true;
        if (type_is == 1) {
            // RealizationPercentage
            popuptitle.setText(getActivity().getResources().getString(R.string.realizable_per));
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getRealizationPercentage())) {
                is_empty = false;
                selected_value = Singleton.getInstance().indPropertyValuation.getRealizationPercentage();
            }
        } else if (type_is == 2) {
            // DistressPercentage
            popuptitle.setText(getActivity().getResources().getString(R.string.distress_per));
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getDistressPercentage())) {
                is_empty = false;
                selected_value = Singleton.getInstance().indPropertyValuation.getDistressPercentage();
            }
        } else if (type_is == 3) {
            id_radio_Custom.setVisibility(View.GONE);
            // DistressPercentage
            popuptitle.setText(getActivity().getResources().getString(R.string.carpet_per));
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getCarpetAreaPercentage())) {
                is_empty = false;
                selected_value = Singleton.getInstance().indPropertyValuation.getCarpetAreaPercentage();
            }
        }

        if (!is_empty) {
            if (selected_value.equalsIgnoreCase("50")) {
                id_radio_50.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("55")) {
                id_radio_55.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("60")) {
                id_radio_60.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("65")) {
                id_radio_65.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("70")) {
                id_radio_70.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("75")) {
                id_radio_75.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("80")) {
                id_radio_80.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("85")) {
                id_radio_85.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("90")) {
                id_radio_90.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("95")) {
                id_radio_95.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("100")) {
                id_radio_100.setChecked(true);
            } else {
                id_radio_Custom.setChecked(true);
            }
        } else {
            id_radio_select.setChecked(true);
        }


        id_radiogroup_stage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton id_radiogenearal = (RadioButton) dialog.findViewById(group.getCheckedRadioButtonId());

                //Log.e("id_radiogenearal_",": "+id_radiogenearal.getText().toString());
                String str_radiogenearal = id_radiogenearal.getText().toString();
                if (type_is == 1) {
                    // RealizationPercentage
                    if (str_radiogenearal.equalsIgnoreCase("Select")) {
                        textView.setText(str_radiogenearal);
                        editText_realizable_custom.setText("");
                        editText_realizable_custom.setVisibility(View.INVISIBLE);
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentage("");
                    } else if (str_radiogenearal.equalsIgnoreCase("Custom")) {
                        textView.setText(str_radiogenearal);
                        editText_realizable_custom.setText("");
                        editText_realizable_custom.setVisibility(View.VISIBLE);
                        // For reference set as 0 for custom
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentage("0");
                    } else {
                        editText_realizable_custom.setText("");
                        editText_realizable_custom.setVisibility(View.INVISIBLE);
                        String realizableStr = str_radiogenearal.replace("%", "");
                        textView.setText(realizableStr);
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentage(realizableStr);
                    }
                } else if (type_is == 2) {
                    // DistressPercentage
                    if (str_radiogenearal.equalsIgnoreCase("Select")) {
                        textView.setText(str_radiogenearal);
                        editText_distress_custom.setText("");
                        editText_distress_custom.setVisibility(View.INVISIBLE);
                        Singleton.getInstance().indPropertyValuation.setDistressPercentage("");
                    } else if (str_radiogenearal.equalsIgnoreCase("Custom")) {
                        textView.setText(str_radiogenearal);
                        editText_distress_custom.setText("");
                        editText_distress_custom.setVisibility(View.VISIBLE);
                        // For reference set as 0 for custom
                        Singleton.getInstance().indPropertyValuation.setDistressPercentage("0");
                    } else {
                        editText_distress_custom.setText("");
                        editText_distress_custom.setVisibility(View.INVISIBLE);
                        String distressStr = str_radiogenearal.replace("%", "");
                        textView.setText(distressStr);
                        Singleton.getInstance().indPropertyValuation.setDistressPercentage(distressStr);
                    }
                } else if (type_is == 3) {
                    if (str_radiogenearal.equalsIgnoreCase("Select")) {
                        textView.setText(str_radiogenearal);
                        Singleton.getInstance().indPropertyValuation.setCarpetAreaPercentage("");
                        // If select set carpet too select
                        spinner_carpet_type.setText(str_radiogenearal);
                        Singleton.getInstance().indPropertyValuation.setCarpetAreaTypeId("");
                    } else {
                        String carpetStr = str_radiogenearal.replace("%", "");
                        textView.setText(carpetStr);
                        Singleton.getInstance().indPropertyValuation.setCarpetAreaPercentage(carpetStr);
                    }
                }

                dialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private void carpet_popup(final TextView textView) {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.carpet_popup);

        TextView popuptitle = (TextView) dialog.findViewById(R.id.title);
        popuptitle.setTypeface(general.mediumtypeface());
        popuptitle.setText(getActivity().getResources().getString(R.string.carpet_per_type));

        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        RadioGroup id_radiogroup_stage = (RadioGroup) dialog.findViewById(R.id.id_radiogroup_stage);

        RadioButton id_radio_select = (RadioButton) dialog.findViewById(R.id.id_radio_select);
        RadioButton id_radio_per = (RadioButton) dialog.findViewById(R.id.id_radio_per);
        RadioButton id_radio_act = (RadioButton) dialog.findViewById(R.id.id_radio_act);
        RadioButton id_radio_sel = (RadioButton) dialog.findViewById(R.id.id_radio_sel);

        id_radio_select.setTypeface(general.mediumtypeface());
        id_radio_per.setTypeface(general.mediumtypeface());
        id_radio_act.setTypeface(general.mediumtypeface());
        id_radio_sel.setTypeface(general.mediumtypeface());


        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getCarpetAreaTypeId())) {
            int carpet_id = Integer.parseInt(Singleton.getInstance().indPropertyValuation.getCarpetAreaTypeId());
            if (carpet_id == 1) {
                id_radio_per.setChecked(true);
            } else if (carpet_id == 2) {
                id_radio_act.setChecked(true);
            } else if (carpet_id == 3) {
                id_radio_sel.setChecked(true);
            }
        } else {
            id_radio_select.setChecked(true);
        }

        id_radiogroup_stage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton id_radiogenearal = (RadioButton) dialog.findViewById(group.getCheckedRadioButtonId());
                //Log.e("id_radiogenearal_",": "+id_radiogenearal.getText().toString());
                String str_radiogenearal = id_radiogenearal.getText().toString();
                if (str_radiogenearal.equalsIgnoreCase("Select")) {
                    Singleton.getInstance().indPropertyValuation.setCarpetAreaTypeId("");
                } else if (str_radiogenearal.equalsIgnoreCase("Permissible Area")) {
                    Singleton.getInstance().indPropertyValuation.setCarpetAreaTypeId("1");
                } else if (str_radiogenearal.equalsIgnoreCase("Actual Area")) {
                    Singleton.getInstance().indPropertyValuation.setCarpetAreaTypeId("2");
                } else if (str_radiogenearal.equalsIgnoreCase("Selected Area")) {
                    Singleton.getInstance().indPropertyValuation.setCarpetAreaTypeId("3");
                }
                textView.setText(str_radiogenearal);

                dialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }


    @Override
    public void onRatePopupSucess(RatePopup ratePopup) {

        if (ratePopup.getData() != null) {
            if (ratePopup.getData().getPropertyCategoryId() == 2 || ratePopup.getData().getPropertyCategoryId() == 3) {
                if (ratePopup.getData().getMinAmount() != 0.0 && ratePopup.getData().getMaxAmount() != 0.0) {

                    tvRatePopup.setText("INR " + ratePopup.getData().getMinAmount() + " to " + ratePopup.getData().getMaxAmount() + " perSqYds");

                } else {

                    tvRatePopup.setText("No land rate suggestion to display!");
                }
            } else {
                if (ratePopup.getData().getMinAmount() != 0.0 && ratePopup.getData().getMaxAmount() != 0.0) {
                    tvRatePopup.setText("INR " + ratePopup.getData().getMinAmount() + " to " + ratePopup.getData().getMaxAmount() + " perSq.ft");
                } else {
                    tvRatePopup.setText("No flat rate suggestion to display!");
                }
            }
        }


    }

    @Override
    public void onRatePopupFailed(String msg) {
        Toast.makeText(getActivity(), "Unable to fetch market rate value!", Toast.LENGTH_SHORT).show();
    }
}
