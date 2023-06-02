package com.realappraiser.gharvalue.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.utils.DecimalDigitsInputFilter;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentValuationPenthouse_ka extends Fragment {

    // TODO General class to call typeface and all...
    public static General general;
    public static long DELAY_TIME = 3000; //3 seconds
    public static Handler mHandler;
    public static Runnable mJumpRunnable;
    // TODO -  Linear
    @BindView(R.id.linear_penthouse_terrace_row)
    LinearLayout linear_penthouse_terrace_row;

    // TODO - Textview
    @BindView(R.id.textview_area_measurment)
    TextView textview_area_measurment;
    @BindView(R.id.textview_current_fair)
    TextView textview_current_fair;
    @BindView(R.id.textview_areatype_heading)
    TextView textview_areatype_heading;
    @BindView(R.id.textview_currentarea_heading)
    TextView textview_currentarea_heading;
    @BindView(R.id.textview_marketrate_heading)
    TextView textview_marketrate_heading;
    @BindView(R.id.textview_marketvalue_heading)
    TextView textview_marketvalue_heading;
    @BindView(R.id.textview_terrace)
    TextView textview_terrace;

    @BindView(R.id.textview_balcony)
    TextView textview_balcony;
    @BindView(R.id.textview_otla)
    TextView textview_otla;
    @BindView(R.id.textview_mezzanine)
    TextView textview_mezzanine;

    @BindView(R.id.textview_terrace_total_head)
    TextView textview_terrace_total_head;
    @BindView(R.id.textview_insurance)
    TextView textview_insurance;
    /*  @BindView(R.id.textview_area_sub_heading)
      TextView textview_area_sub_heading;*/
  /*  @BindView(R.id.textview_dlcrate_heading)
    TextView textview_dlcrate_heading;*/
 /*   @BindView(R.id.textview_governmentvalue_head)
    TextView textview_governmentvalue_head;*/
    @BindView(R.id.textview_governmentvalue_subhead)
    TextView textview_governmentvalue_subhead;

    @BindView(R.id.textview_realizable_distress_heading)
    TextView textview_realizable_distress_heading;
    @BindView(R.id.textview_value_subheading)
    TextView textview_value_subheading;
    @BindView(R.id.textview_realizable_value_head)
    TextView textview_realizable_value_head;
    @BindView(R.id.textview_distress_head)
    TextView textview_distress_head;

    @BindView(R.id.textview_aspercompletion)
    TextView textview_aspercompletion;
    @BindView(R.id.textview_proposedvaluation)
    TextView textview_proposedvaluation;

    @BindView(R.id.textview_on_completion)
    TextView textview_on_completion;
    @BindView(R.id.textview_on_completion_value)
    TextView textview_on_completion_value;
    @BindView(R.id.textview_dateof_inspection)
    TextView textview_dateof_inspection;
    @BindView(R.id.textview_dateof_inspection_value)
    TextView textview_dateof_inspection_value;
    @BindView(R.id.textview_realizable)
    TextView textview_realizable;
    @BindView(R.id.textview_distress)
    TextView textview_distress;

    @BindView(R.id.otla_div)
    LinearLayout otla_div;
    @BindView(R.id.mezzanine_div)
    LinearLayout mezzanine_div;


    public static Context mContext;

    // TODO - Edittext

    public static EditText edittext_type_area;
    public static EditText edittext_type_rate;
    public static EditText edittext_terrace_area;
    public static EditText edittext_terrace_rate;
    public static EditText edittext_balcony_area;
    public static EditText edittext_otla_area;
    public static EditText edittext_mezzanine_area;
    public static EditText edittext_balcony_rate;
    public static EditText edittext_otla_rate;
    public static EditText edittext_mezzanine_rate;
    public static EditText edittext_insurance_area;
    public static EditText edittext_insurance_rate;
    public static EditText edittext_governmentvalue_area;
    public static EditText edittext_governmentvalue_rate;
    public static EditText editText_AsPerCompletion;
    public static EditText edittext_proposedvaluation_result;
    public static EditText editText_remarks;
    public static EditText edittext_carpet_area;
    public static EditText edittext_builtup_area;
    public static EditText edittext_Saleable_area;
    public static TextView edittext_measurementunit;

    public static EditText edittext_realizable_value_total;
    public static EditText edittext_distress_total;

    public static EditText edittext_realizable_completion_value;
    public static EditText edittext_distress_completion_value;
    public static EditText edittext_realizable_inspection_value;
    public static EditText edittext_distress_inspection_value;

    // TODO - Textview
    public static TextView textview_type_of_area;
    public static TextView textview_aspercompletion_result;
    public static TextView textview_totalpropertyvalue_result;
    public static TextView textview_type_marketvalue;
    public static TextView textview_terrace_marketvalue;
    public static TextView textview_total_marketval;
    public static TextView textview_insurance_marketvalue;
    public static TextView textview_governmentvalue_marketvalue;
    public static TextView textview_totalpropertyvalue;
    public static TextView spinnerLoadingOverBuildup;
    public static TextView textview_balcony_marketvalue;
    public static TextView textview_otla_marketvalue;
    public static TextView textview_mezzanine_marketvalue;

    public Dialog dialog;

    // TODO - Spinner
    /*public static Spinner spinnerLoadingOverBuildup;*/
    public static Spinner spinnerAreaType;
    public static TextView spinner_realizable;
    public static TextView spinner_distress;
    public static TextView spinner_realizable_completion;
    public static TextView spinner_realizable_inspection;
    public static TextView spinner_distress_completion;
    public static TextView spinner_distress_inspection;

    // TODO - linear layout
    public static LinearLayout realizable_distress_completed_lay;
    public static LinearLayout realizable_distress_underconstrust_lay;
    public static TextView textview_realizable_head;

    @BindView(R.id.textview_areatype)
    TextView textview_areatypetvalue;
    @BindView(R.id.textview_loadingoverbuildup)
    TextView textview_loadingoverbuildup;

    // TODO - String
    public static String property_type = "";
    public static String areaTypeID = "", Measurement_id = "1";

    public static ArrayAdapter<String> adapterAreaType;
    public static FragmentActivity fragmentActivity;
    public static ArrayList<String> loadingbuildupList = new ArrayList<>();
    public static ArrayList<String> realizableList = new ArrayList<>();
    public static ArrayList<String> distressList = new ArrayList<>();
    public static ArrayList<String> realizablecompList = new ArrayList<>();
    public static ArrayList<String> distresscompList = new ArrayList<>();
    public static ArrayList<String> realizableinspectionList = new ArrayList<>();
    public static ArrayList<String> distressinspectionList = new ArrayList<>();

    @BindView(R.id.realizable_div_complete)
    LinearLayout realizable_div_complete;
    @BindView(R.id.realizable_div_uncomplete)
    LinearLayout realizable_div_uncomplete;
    @BindView(R.id.insurance_div)
    LinearLayout insurance_div;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fragment_valuation_penthouse_ka, container, false);
        ButterKnife.bind(this, view);
        general = new General(getActivity());
        fragmentActivity = getActivity();
        SettingsUtils.init(getActivity());


        // Hide the otla and mezzanine div on 16,17,18,19 report type only

        // 31-jan-2019 changes (report type changed as 7 and 8)
        int ReportType = Singleton.getInstance().aCase.getReportType();
        if (ReportType == 7 || ReportType == 8) {
            // Visible
            otla_div.setVisibility(View.VISIBLE);
            mezzanine_div.setVisibility(View.VISIBLE);
        } else {
            otla_div.setVisibility(View.GONE);
            mezzanine_div.setVisibility(View.GONE);
        }
        // Hide the realizable_div_complete and realizable_div_uncomplete and insurance_div for kakode Flat
        realizable_div_complete.setVisibility(View.GONE);
        realizable_div_uncomplete.setVisibility(View.GONE);
        insurance_div.setVisibility(View.GONE);

//
        initValues(view);
        setPropertyType();

        AreaTypeSpinner(Singleton.getInstance().areaType, "Select");

        PentHouseFlatCalculation();
        RealizeDistressTotalVal();

        DisplayValuation();


        spinner_realizable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(spinner_realizable);
                realizable_distress_popup(1, spinner_realizable);
            }
        });

        spinner_distress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(spinner_distress);
                realizable_distress_popup(2, spinner_distress);
            }
        });

        spinnerLoadingOverBuildup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(spinnerLoadingOverBuildup);
                loadingOverBuildup_popup(spinnerLoadingOverBuildup);
            }
        });

        spinner_realizable_completion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(spinner_realizable_completion);
                realizable_distress_uc_popup(1, spinner_realizable_completion);
            }
        });

        spinner_distress_completion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(spinner_distress_completion);
                realizable_distress_uc_popup(2, spinner_distress_completion);
            }
        });

        spinner_realizable_inspection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(spinner_realizable_inspection);
                realizable_distress_uc_popup(3, spinner_realizable_inspection);
            }
        });

        spinner_distress_inspection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(spinner_distress_inspection);
                realizable_distress_uc_popup(4, spinner_distress_inspection);
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
            } else {
                /* Custom */
                spinner_realizable.setText(getResources().getString(R.string.custom));
                edittext_realizable_value_total.setFocusable(true);
                edittext_realizable_value_total.setFocusableInTouchMode(true);
                edittext_realizable_value_total.setEnabled(true);
                edittext_realizable_value_total.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getRealizationValue()));

            }
        } else {
            /* select */
            spinner_realizable.setText(getResources().getString(R.string.select));
        }

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
            } else {
                /* Custom */
                spinner_distress.setText(getResources().getString(R.string.custom));
                edittext_distress_total.setFocusable(true);
                edittext_distress_total.setFocusableInTouchMode(true);
                edittext_distress_total.setEnabled(true);
                edittext_distress_total.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getDistressValue()));

            }
        } else {
            /* select */
            spinner_distress.setText(getResources().getString(R.string.select));
        }

        loadingbuildupList = new ArrayList<>();
        loadingbuildupList = general.LoadingBuilup_common_array();
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getBAULoadingPercentage())) {
            /* values */
            boolean is_value_from_loop = false;
            for (int x = 0; x < loadingbuildupList.size(); x++) {
                if (loadingbuildupList.get(x).toString().replace("%", "").trim().equalsIgnoreCase(Singleton.getInstance().indPropertyValuation.getBAULoadingPercentage())) {
                    is_value_from_loop = true;
                }
            }
            if (is_value_from_loop) {
                /* From array */
                spinnerLoadingOverBuildup.setText(Singleton.getInstance().indPropertyValuation.getBAULoadingPercentage());
            }
        } else {
            /* select */
            spinnerLoadingOverBuildup.setText(getResources().getString(R.string.select));
        }


        /****for Underconstruction % as the Date of Inspection ******/
        LoadUnderConstructDateofInspection();


        // TODO -  call the mandatory_valiadation
        if (Singleton.getInstance().enable_validation_error) {
            set_pentflathouse_mandatory();
        }

        return view;
    }

    private void LoadUnderConstructDateofInspection() {
        /**********Stage Completion for 100%************/
        realizablecompList = new ArrayList<>();
        realizablecompList = general.Constructionval_common_array();
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getRealizationPercentage())) {
            /* values */
            boolean is_value_from_loop = false;
            for (int x = 0; x < realizablecompList.size(); x++) {
                if (realizablecompList.get(x).toString().replace("%", "").trim().equalsIgnoreCase(Singleton.getInstance().indPropertyValuation.getRealizationPercentage())) {
                    is_value_from_loop = true;
                }
            }
            if (is_value_from_loop) {
                /* From array */
                spinner_realizable_completion.setText(Singleton.getInstance().indPropertyValuation.getRealizationPercentage());
                edittext_realizable_completion_value.setFocusable(false);
            } else {
                /* Custom */
                spinner_realizable_completion.setText(getResources().getString(R.string.custom));
                edittext_realizable_completion_value.setFocusable(true);
                edittext_realizable_completion_value.setFocusableInTouchMode(true);
                edittext_realizable_completion_value.setEnabled(true);
                edittext_realizable_completion_value.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getRealizationValue()));

            }
        } else {
            /* select */
            spinner_realizable_completion.setText(getResources().getString(R.string.select));
        }

        distresscompList = new ArrayList<>();
        distresscompList = general.Constructionval_common_array();
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getDistressPercentage())) {
            /* values */
            boolean is_value_from_loop = false;
            for (int x = 0; x < distresscompList.size(); x++) {
                if (distresscompList.get(x).toString().replace("%", "").trim().equalsIgnoreCase(Singleton.getInstance().indPropertyValuation.getDistressPercentage())) {
                    is_value_from_loop = true;
                }
            }
            if (is_value_from_loop) {
                /* From array */
                spinner_distress_completion.setText(Singleton.getInstance().indPropertyValuation.getDistressPercentage());
                edittext_distress_completion_value.setFocusable(false);
            } else {
                /* Custom */
                spinner_distress_completion.setText(getResources().getString(R.string.custom));
                edittext_distress_completion_value.setFocusable(true);
                edittext_distress_completion_value.setFocusableInTouchMode(true);
                edittext_distress_completion_value.setEnabled(true);
                edittext_distress_completion_value.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getDistressValue()));

            }
        } else {
            /* select */
            spinner_distress_completion.setText(getResources().getString(R.string.select));
        }


        /*********stage Under construction for date of inspection*************/
        realizableinspectionList = new ArrayList<>();
        realizableinspectionList = general.Constructionval_common_array();
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getRealizationPercentageAsOnDate())) {
            /* values */
            boolean is_value_from_loop = false;
            for (int x = 0; x < realizableinspectionList.size(); x++) {
                if (realizableinspectionList.get(x).toString().replace("%", "").trim().equalsIgnoreCase(Singleton.getInstance().indPropertyValuation.getRealizationPercentageAsOnDate())) {
                    is_value_from_loop = true;
                }
            }
            if (is_value_from_loop) {
                /* From array */
                spinner_realizable_inspection.setText(Singleton.getInstance().indPropertyValuation.getRealizationPercentageAsOnDate());
                edittext_realizable_inspection_value.setFocusable(false);
            } else {
                /* Custom */
                spinner_realizable_inspection.setText(getResources().getString(R.string.custom));
                edittext_realizable_inspection_value.setFocusable(true);
                edittext_realizable_inspection_value.setFocusableInTouchMode(true);
                edittext_realizable_inspection_value.setEnabled(true);
                edittext_realizable_inspection_value.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getRealizationValueAsOnDate()));

            }
        } else {
            /* select */
            spinner_realizable_inspection.setText(getResources().getString(R.string.select));
        }

        distressinspectionList = new ArrayList<>();
        distressinspectionList = general.Constructionval_common_array();
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getDistressPercentageAsOnDate())) {
            /* values */
            boolean is_value_from_loop = false;
            for (int x = 0; x < distressinspectionList.size(); x++) {
                if (distressinspectionList.get(x).toString().replace("%", "").trim().equalsIgnoreCase(Singleton.getInstance().indPropertyValuation.getDistressPercentageAsOnDate())) {
                    is_value_from_loop = true;
                }
            }
            if (is_value_from_loop) {
                /* From array */
                spinner_distress_inspection.setText(Singleton.getInstance().indPropertyValuation.getDistressPercentageAsOnDate());
                edittext_distress_inspection_value.setFocusable(false);
            } else {
                /* Custom */
                spinner_distress_inspection.setText(getResources().getString(R.string.custom));
                edittext_distress_inspection_value.setFocusable(true);
                edittext_distress_inspection_value.setFocusableInTouchMode(true);
                edittext_distress_inspection_value.setEnabled(true);
                edittext_distress_inspection_value.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getDistressValueAsOnDate()));
            }
        } else {
            /* select */
            spinner_distress_inspection.setText(getResources().getString(R.string.select));
        }
    }

    private void set_pentflathouse_mandatory() {
       /* if (property_type.equalsIgnoreCase("penthouse")) {
            String terracearea = edittext_terrace_area.getText().toString();
            if (!general.isEmpty(terracearea)) {
                edittext_terrace_area.setError(null);
            } else {
                edittext_terrace_area.requestFocus();
                edittext_terrace_area.setError(getActivity().getResources().getString(R.string.terracearea));
            }

            String terracerate = edittext_terrace_rate.getText().toString();
            if (!general.isEmpty(terracerate)) {
                edittext_terrace_rate.setError(null);
            } else {
                edittext_terrace_rate.requestFocus();
                edittext_terrace_rate.setError(getActivity().getResources().getString(R.string.terracerate));
            }
        } */
    }

    private void set_government_val_area_mandatory() {
        String edittext_governmentvalue_rate_str = edittext_governmentvalue_rate.getText().toString();
        if (!general.isEmpty(edittext_governmentvalue_rate_str)) {
            edittext_governmentvalue_rate.setError(null);
        } else {
            //edittext_governmentvalue_rate.requestFocus();
            edittext_governmentvalue_rate.setError(getActivity().getResources().getString(R.string.error_gov_rate));
        }
        String edittext_governmentvalue_area_str = edittext_governmentvalue_area.getText().toString();
        if (!general.isEmpty(edittext_governmentvalue_area_str)) {
            edittext_governmentvalue_area.setError(null);
        } else {
            //edittext_governmentvalue_area.requestFocus();
            edittext_governmentvalue_area.setError(getActivity().getResources().getString(R.string.error_gov_area));
        }
    }

    private void setPropertyType() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            property_type = bundle.getString("property_type");
        }

        if (property_type.equalsIgnoreCase("flat")) {
            linear_penthouse_terrace_row.setVisibility(View.VISIBLE);
        } else {
            linear_penthouse_terrace_row.setVisibility(View.VISIBLE);
        }
    }

    private void DisplayValuation() {
        DisplayAreaTypeValues();
        DisplayMarketRateValues();
        DisplaySpinnerValues();
    }

    private void DisplayAreaTypeValues() {
        if (Singleton.getInstance().indPropertyValuation != null) {
            /******Set Edittext area type values in Valuation******/
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getCarpetArea())) {
                setEdittextCarpetArea(Singleton.getInstance().indPropertyValuation.getCarpetArea());
                Singleton.getInstance().areaType.add(getResources().getString(R.string.carpet));

            }
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getBuildUpArea())) {
                setEdittextBuildupArea(Singleton.getInstance().indPropertyValuation.getBuildUpArea());
                Singleton.getInstance().areaType.add(getResources().getString(R.string.builtup));

            }
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getSuperBuildUpArea())) {
                setEdittextSaleableArea(Singleton.getInstance().indPropertyValuation.getSuperBuildUpArea());
                Singleton.getInstance().areaType.add(getResources().getString(R.string.Saleable));
            }

            /*****Set spinner Area type as per the id*****/
            String carpetAreaid = Singleton.getInstance().indPropertyValuation.getCarpetAreaTypeId();
            if (!general.isEmpty(carpetAreaid)) {
                String area_type = "";
                int carpetID = Integer.valueOf(carpetAreaid);
                if (carpetID < Singleton.getInstance().areaType.size()) {
                    if (carpetID == 1) {
                        area_type = "Carpet";
                    } else if (carpetID == 2) {
                        area_type = "Built up";
                    } else if (carpetID == 3) {
                        area_type = "Saleable";
                    } else {
                        area_type = "Select";
                    }
                    AreaTypeSpinner(Singleton.getInstance().areaType, area_type);
                }
            }

            /***Set Measurement unit value****/
            Measurement_id = Singleton.getInstance().indPropertyValuation.getCarpetAreaUnit();
            if (!general.isEmpty(Measurement_id)) {
                int measure = Integer.valueOf(Measurement_id);
                if (measure == 1 && measure < Singleton.getInstance().measurements_list_flat.size()) {
                    edittext_measurementunit.setText("" + Singleton.getInstance().measurements_list_flat.get(1).getName());

                } else {
                    edittext_measurementunit.setText("");
                }

            }
        }
    }

    private void DisplayMarketRateValues() {
        if (Singleton.getInstance().indPropertyValuation != null) {
            String areatype_rate = Singleton.getInstance().indPropertyValuation.getConstructionRate();
            String insurance_rate = Singleton.getInstance().indPropertyValuation.getInsuredConstructionRate();
            String terrace_area = Singleton.getInstance().indPropertyValuation.getTerraceArea();
            String terrace_rate = Singleton.getInstance().indPropertyValuation.getTerraceRate();
            //   String areatype_marketval = Singleton.getInstance().indPropertyValuation.getTotalConstructionValue();
            String insurance_value = Singleton.getInstance().indPropertyValuation.getInsuranceValue();
            String terrace_val = Singleton.getInstance().indPropertyValuation.getTerraceValue();
            String total_propertyval = Singleton.getInstance().indPropertyValuation.getTotalPropertyValue();

            String balcony_area = Singleton.getInstance().indPropertyValuation.getBalconyArea();
            String balcony_rate = Singleton.getInstance().indPropertyValuation.getBalconyRate();
            String otla_rate = Singleton.getInstance().indPropertyValuation.getOtlaRate();
            String mezzanine_rate = Singleton.getInstance().indPropertyValuation.getMezzanineRate();
            String balcony_val = Singleton.getInstance().indPropertyValuation.getBalconyValue();
            String otla_val = Singleton.getInstance().indPropertyValuation.getOtlaValue();
            String mezzanine_val = Singleton.getInstance().indPropertyValuation.getMezzanineValue();
            String otla_area = Singleton.getInstance().indPropertyValuation.getOtlaArea();
            String mezzanine_area = Singleton.getInstance().indPropertyValuation.getMezzanineArea();


            int constructionid = 0;
            if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                constructionid = Singleton.getInstance().indPropertyFloors.get(0).getConstructionStageId();
            }

            String realized_total = Singleton.getInstance().indPropertyValuation.getRealizationValue();
            String distress_total = Singleton.getInstance().indPropertyValuation.getDistressValue();
            String realized_inspe_total = Singleton.getInstance().indPropertyValuation.getRealizationValueAsOnDate();
            String distress__inspec_total = Singleton.getInstance().indPropertyValuation.getDistressValueAsOnDate();
            String aspercomp_percent = Singleton.getInstance().indPropertyValuation.getCompletionPercentage();
            String aspercomp_total = Singleton.getInstance().indPropertyValuation.getTotalValueAtCompletion();
            String proposed_remarks = Singleton.getInstance().indPropertyValuation.getProposedValuationComments();


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Reflect the area
                    String GovernmentArea = Singleton.getInstance().indPropertyValuation.getGovernmentArea();
                    if (!general.isEmpty(GovernmentArea)) {
                        edittext_governmentvalue_area.setText(GovernmentArea);
                    } else {
                        edittext_governmentvalue_area.setText("");
                    }
                    // Reflect InsuranceArea
                    String InsuranceArea = Singleton.getInstance().indPropertyValuation.getInsuranceArea();
                    if (!general.isEmpty(InsuranceArea)) {
                        edittext_insurance_area.setText(InsuranceArea);
                    } else {
                        edittext_insurance_area.setText("");
                    }

                    String govern_dlc_rate = Singleton.getInstance().indPropertyValuation.getConstructionDLCRate();
                    if (!general.isEmpty(govern_dlc_rate)) {
                        edittext_governmentvalue_rate.setText(govern_dlc_rate);
                    } else {
                        edittext_governmentvalue_rate.setText("");
                    }
                    String dlc_marketval = Singleton.getInstance().indPropertyValuation.getConstructionDLCValue();
                    if (!general.isEmpty(dlc_marketval)) {
                        textview_governmentvalue_marketvalue.setText("" + general.DecimalFormattedCommaString(dlc_marketval));
                    } else {
                        textview_governmentvalue_marketvalue.setText("");
                    }

                    // TODO -  call the mandatory_valiadation
                    if (Singleton.getInstance().enable_validation_error) {
                        set_government_val_area_mandatory();
                    }

                }
            }, 4000);


            if (!general.isEmpty(areatype_rate)) {
                edittext_type_rate.setText(areatype_rate);
            }
            if (!general.isEmpty(insurance_rate)) {
                edittext_insurance_rate.setText(insurance_rate);
            }

            if (!general.isEmpty(insurance_value)) {
                textview_insurance_marketvalue.setText("" + general.DecimalFormattedCommaString(insurance_value));
                //textview_insurance_marketvalue.setText(insurance_value);
            }


            if (!general.isEmpty(terrace_area)) {
                edittext_terrace_area.setText(terrace_area);
            }
            if (!general.isEmpty(terrace_rate)) {
                edittext_terrace_rate.setText(terrace_rate);
            }
            if (!general.isEmpty(terrace_val)) {
                textview_terrace_marketvalue.setText("" + general.DecimalFormattedCommaString(terrace_val));
                // textview_terrace_marketvalue.setText(terrace_val);
            }
            //BALCONY
            if (!general.isEmpty(balcony_area)) {
                edittext_balcony_area.setText(balcony_area);
            }

            if (!general.isEmpty(otla_area)) {
                edittext_otla_area.setText(otla_area);
            }

            if (!general.isEmpty(mezzanine_area)) {
                edittext_mezzanine_area.setText(mezzanine_area);
            }

            if (!general.isEmpty(balcony_rate)) {
                edittext_balcony_rate.setText(balcony_rate);
            }

            if (!general.isEmpty(otla_rate)) {
                edittext_otla_rate.setText(otla_rate);
            }
            if (!general.isEmpty(mezzanine_rate)) {
                edittext_mezzanine_rate.setText(mezzanine_rate);
            }

            if (!general.isEmpty(balcony_val)) {
                textview_balcony_marketvalue.setText("" + general.DecimalFormattedCommaString(balcony_val));
            }
            if (!general.isEmpty(otla_val)) {
                textview_otla_marketvalue.setText("" + general.DecimalFormattedCommaString(otla_val));
            }
            if (!general.isEmpty(mezzanine_val)) {
                textview_mezzanine_marketvalue.setText("" + general.DecimalFormattedCommaString(mezzanine_val));
            }


            if (!general.isEmpty(total_propertyval)) {
                textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(total_propertyval)));
/*
                textview_totalpropertyvalue_result.setText(total_propertyval);*/
            }

            if (constructionid == 1) {

                if (!general.isEmpty(realized_total)) {
                    edittext_realizable_value_total.setText("" + general.DecimalFormattedCommaString(realized_total));
                }
                if (!general.isEmpty(distress_total)) {
                    edittext_distress_total.setText("" + general.DecimalFormattedCommaString(distress_total));
                }
            } else if (constructionid == 2) {
                if (!general.isEmpty(realized_total)) {
                    edittext_realizable_completion_value.setText("" + general.DecimalFormattedCommaString(realized_total));
                }
                if (!general.isEmpty(distress_total)) {
                    edittext_distress_completion_value.setText("" + general.DecimalFormattedCommaString(distress_total));
                }

                if (!general.isEmpty(realized_inspe_total)) {
                    edittext_realizable_inspection_value.setText("" + general.DecimalFormattedCommaString(realized_inspe_total));
                }
                if (!general.isEmpty(distress__inspec_total)) {
                    edittext_distress_inspection_value.setText("" + general.DecimalFormattedCommaString(distress__inspec_total));
                }


            }

            if (!general.isEmpty(aspercomp_percent)) {
                editText_AsPerCompletion.setText(aspercomp_percent);
            }
            if (!general.isEmpty(aspercomp_total)) {
                textview_aspercompletion_result.setText("" + general.DecimalFormattedCommaString(aspercomp_total));
                // textview_aspercompletion_result.setText(aspercomp_total);
            }

            /* ProposedValuation */
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getProposedValuation())) {
                String pro_val = Singleton.getInstance().indPropertyValuation.getProposedValuation();
                edittext_proposedvaluation_result.setText(general.DecimalFormattedCommaString(pro_val));
            } else {
                edittext_proposedvaluation_result.setText("");
            }

            edittext_proposedvaluation_result.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        edittext_proposedvaluation_result.setText(general.DecimalFormattedCommaString(edittext_proposedvaluation_result.getText().toString()));
                    }
                }
            });


            if (!general.isEmpty(proposed_remarks)) {
                editText_remarks.setText(proposed_remarks);
            }
        }
    }

    private void DisplaySpinnerValues() {
        if (Singleton.getInstance().indPropertyValuation != null) {
            /******Set Spinner type values in Valuation******/

            String loadingBuilup = Singleton.getInstance().indPropertyValuation.getBAULoadingPercentage();
            String realizable_val = Singleton.getInstance().indPropertyValuation.getRealizationPercentage();
            String distress_val = Singleton.getInstance().indPropertyValuation.getDistressPercentage();

            /*  *//*****Set Loading Over Build up spinner******//*
            if (!general.isEmpty(loadingBuilup)) {
                loadingBuilup = general.NumOnly(loadingBuilup);
                for (int i = 0; i < loadingbuildupList.size(); i++) {
                    String loadingBuildupStr = loadingbuildupList.get(i).toString().replace("%", "");
                    if (loadingBuildupStr.equalsIgnoreCase(loadingBuilup)) {
                        spinnerLoadingOverBuildup.setSelection(i);
                    }
                }
            }*/

            /*****Set Realizable spinner******/
            /*if (!general.isEmpty(realizable_val)) {
                    realizable_val = general.NumOnly(realizable_val);
                    for (int i = 0; i < realizableList.size(); i++) {
                        String realizableStr="";
                        if(realizableList.get(i).toString().contains("%")) {
                            realizableStr = realizableList.get(i).toString().replace("%", "");
                        }
                        if (realizableStr.equalsIgnoreCase(realizable_val)) {
                            spinner_realizable.setSelection(i);
                        }
                        else if(realizable_val.equalsIgnoreCase("101")) {
                            spinner_realizable.setSelection(12);
                        }

                }
            }*/

            /*****Set Distress spinner******/
            /*if (!general.isEmpty(distress_val)) {
                distress_val = general.NumOnly(distress_val);
                for (int i = 0; i < distressList.size(); i++) {
                    String distressStr = "";
                    if (distressList.get(i).toString().contains("%")) {
                        distressStr = distressList.get(i).toString().replace("%", "");
                    }
                    if (distressStr.equalsIgnoreCase(distress_val)) {
                        spinner_distress.setSelection(i);
                    } else if (distress_val.equalsIgnoreCase("101")) {
                        spinner_distress.setSelection(12);
                    }

                }
            }*/
        }
    }

    private void initValues(View view) {
        mContext = this.getContext();

        edittext_type_area = (EditText) view.findViewById(R.id.edittext_type_area);
        edittext_type_rate = (EditText) view.findViewById(R.id.edittext_type_rate);
        edittext_balcony_area = (EditText) view.findViewById(R.id.edittext_balcony_area);
        edittext_otla_area = (EditText) view.findViewById(R.id.edittext_otla_area);
        edittext_mezzanine_area = (EditText) view.findViewById(R.id.edittext_mezzanine_area);
        edittext_balcony_rate = (EditText) view.findViewById(R.id.edittext_balcony_rate);
        edittext_otla_rate = (EditText) view.findViewById(R.id.edittext_otla_rate);
        edittext_mezzanine_rate = (EditText) view.findViewById(R.id.edittext_mezzanine_rate);
        edittext_terrace_area = (EditText) view.findViewById(R.id.edittext_terrace_area);
        edittext_terrace_rate = (EditText) view.findViewById(R.id.edittext_terrace_rate);
        edittext_insurance_area = (EditText) view.findViewById(R.id.edittext_insurance_area);
        edittext_insurance_rate = (EditText) view.findViewById(R.id.edittext_insurance_rate);
        edittext_governmentvalue_area = (EditText) view.findViewById(R.id.edittext_governmentvalue_area);
        edittext_governmentvalue_rate = (EditText) view.findViewById(R.id.edittext_governmentvalue_rate);
        editText_AsPerCompletion = (EditText) view.findViewById(R.id.editText_AsPerCompletion);
        edittext_proposedvaluation_result = (EditText) view.findViewById(R.id.edittext_proposedvaluation_result);
        editText_remarks = (EditText) view.findViewById(R.id.editText_remarks);
        edittext_carpet_area = (EditText) view.findViewById(R.id.edittext_carpet_area);
        edittext_builtup_area = (EditText) view.findViewById(R.id.edittext_builtup_area);
        edittext_Saleable_area = (EditText) view.findViewById(R.id.edittext_Saleable_area);
        edittext_measurementunit = (EditText) view.findViewById(R.id.edittext_measurementunit);
        edittext_realizable_value_total = (EditText) view.findViewById(R.id.edittext_realizable_value_total);
        edittext_distress_total = (EditText) view.findViewById(R.id.edittext_distress_total);

        edittext_realizable_completion_value = (EditText) view.findViewById(R.id.edittext_realizable_completion_value);
        edittext_distress_completion_value = (EditText) view.findViewById(R.id.edittext_distress_completion_value);
        edittext_realizable_inspection_value = (EditText) view.findViewById(R.id.edittext_realizable_inspection_value);
        edittext_distress_inspection_value = (EditText) view.findViewById(R.id.edittext_distress_inspection_value);

        textview_type_of_area = (TextView) view.findViewById(R.id.textview_type_of_area);
        textview_aspercompletion_result = (TextView) view.findViewById(R.id.textview_aspercompletion_result);
        textview_totalpropertyvalue_result = (TextView) view.findViewById(R.id.textview_totalpropertyvalue_result);
        textview_type_marketvalue = (TextView) view.findViewById(R.id.textview_type_marketvalue);
        textview_terrace_marketvalue = (TextView) view.findViewById(R.id.textview_terrace_marketvalue);
        textview_total_marketval = (TextView) view.findViewById(R.id.textview_total_marketval);
        textview_insurance_marketvalue = (TextView) view.findViewById(R.id.textview_insurance_marketvalue);
        textview_governmentvalue_marketvalue = (TextView) view.findViewById(R.id.textview_governmentvalue_marketvalue);
        textview_totalpropertyvalue = (TextView) view.findViewById(R.id.textview_totalpropertyvalue);

        // spinnerLoadingOverBuildup = (Spinner) view.findViewById(R.id.spinnerLoadingOverBuildup);
        spinnerAreaType = (Spinner) view.findViewById(R.id.spinnerAreaType);
        spinner_realizable = (TextView) view.findViewById(R.id.spinner_realizable);
        spinner_distress = (TextView) view.findViewById(R.id.spinner_distress);
        spinnerLoadingOverBuildup = (TextView) view.findViewById(R.id.spinnerLoadingOverBuildup);
        spinner_realizable_completion = (TextView) view.findViewById(R.id.spinner_realizable_completion);
        spinner_realizable_inspection = (TextView) view.findViewById(R.id.spinner_realizable_inspection);
        spinner_distress_completion = (TextView) view.findViewById(R.id.spinner_distress_completion);
        spinner_distress_inspection = (TextView) view.findViewById(R.id.spinner_distress_inspection);
        textview_balcony_marketvalue = (TextView) view.findViewById(R.id.textview_balcony_marketvalue);
        textview_otla_marketvalue = (TextView) view.findViewById(R.id.textview_otla_marketvalue);
        textview_mezzanine_marketvalue = (TextView) view.findViewById(R.id.textview_mezzanine_marketvalue);

        realizable_distress_completed_lay = (LinearLayout) view.findViewById(R.id.realizable_distress_completed_lay);
        realizable_distress_underconstrust_lay = (LinearLayout) view.findViewById(R.id.realizable_distress_underconstrust_lay);
        realizable_distress_completed_lay.setVisibility(View.VISIBLE);
        realizable_distress_underconstrust_lay.setVisibility(View.GONE);
        textview_realizable_head = (TextView) view.findViewById(R.id.textview_realizable_head);

        edittext_carpet_area.setFocusable(false);
        edittext_builtup_area.setFocusable(false);
        edittext_Saleable_area.setFocusable(false);
        edittext_type_area.setFocusable(false);
        edittext_terrace_area.setFocusable(false);
        edittext_balcony_area.setFocusable(false);
        edittext_otla_area.setFocusable(false);
        edittext_mezzanine_area.setFocusable(false);
        edittext_insurance_area.setFocusable(true);
        edittext_governmentvalue_area.setFocusable(true);
        edittext_realizable_value_total.setFocusable(false);
        edittext_distress_total.setFocusable(false);
        edittext_measurementunit.setFocusable(false);

        textview_area_measurment.setTypeface(general.mediumtypeface());
        textview_current_fair.setTypeface(general.mediumtypeface());
        textview_realizable_head.setTypeface(general.mediumtypeface());
        textview_areatype_heading.setTypeface(general.regulartypeface());
        textview_currentarea_heading.setTypeface(general.regulartypeface());
        textview_marketrate_heading.setTypeface(general.regulartypeface());
        textview_marketvalue_heading.setTypeface(general.regulartypeface());
        textview_type_marketvalue.setTypeface(general.regulartypeface());
        textview_terrace.setTypeface(general.regulartypeface());
        // New fields are added
        textview_balcony.setTypeface(general.regulartypeface());
        textview_otla.setTypeface(general.regulartypeface());
        textview_mezzanine.setTypeface(general.regulartypeface());
        edittext_balcony_area.setTypeface(general.regulartypeface());
        edittext_otla_area.setTypeface(general.regulartypeface());
        edittext_mezzanine_area.setTypeface(general.regulartypeface());
        edittext_balcony_rate.setTypeface(general.regulartypeface());
        edittext_otla_rate.setTypeface(general.regulartypeface());
        edittext_mezzanine_rate.setTypeface(general.regulartypeface());
        textview_balcony_marketvalue.setTypeface(general.regulartypeface());
        textview_otla_marketvalue.setTypeface(general.regulartypeface());
        textview_mezzanine_marketvalue.setTypeface(general.regulartypeface());

        textview_terrace_marketvalue.setTypeface(general.regulartypeface());
        textview_terrace_total_head.setTypeface(general.regulartypeface());
        textview_total_marketval.setTypeface(general.regulartypeface());
        textview_insurance.setTypeface(general.regulartypeface());
        textview_insurance_marketvalue.setTypeface(general.regulartypeface());
        //   textview_area_sub_heading.setTypeface(general.regulartypeface());
        //  textview_dlcrate_heading.setTypeface(general.regulartypeface());
        //  textview_governmentvalue_head.setTypeface(general.regulartypeface());
        textview_governmentvalue_subhead.setTypeface(general.regulartypeface());
        textview_governmentvalue_marketvalue.setTypeface(general.regulartypeface());
        textview_realizable_distress_heading.setTypeface(general.regulartypeface());
        textview_value_subheading.setTypeface(general.regulartypeface());
        textview_realizable_value_head.setTypeface(general.regulartypeface());
        edittext_realizable_value_total.setTypeface(general.regulartypeface());
        textview_distress_head.setTypeface(general.regulartypeface());
        edittext_distress_total.setTypeface(general.regulartypeface());
        textview_totalpropertyvalue.setTypeface(general.regulartypeface());
        textview_totalpropertyvalue_result.setTypeface(general.regulartypeface());
        textview_aspercompletion.setTypeface(general.regulartypeface());
        textview_aspercompletion_result.setTypeface(general.regulartypeface());
        textview_proposedvaluation.setTypeface(general.regulartypeface());
        edittext_type_area.setTypeface(general.regulartypeface());
        edittext_type_rate.setTypeface(general.regulartypeface());
        edittext_terrace_area.setTypeface(general.regulartypeface());
        edittext_terrace_rate.setTypeface(general.regulartypeface());
        edittext_insurance_area.setTypeface(general.regulartypeface());
        edittext_insurance_rate.setTypeface(general.regulartypeface());
        edittext_governmentvalue_area.setTypeface(general.regulartypeface());
        edittext_governmentvalue_rate.setTypeface(general.regulartypeface());
        editText_AsPerCompletion.setTypeface(general.regulartypeface());
        edittext_proposedvaluation_result.setTypeface(general.regulartypeface());
        editText_remarks.setTypeface(general.regulartypeface());
        edittext_carpet_area.setTypeface(general.regulartypeface());
        edittext_builtup_area.setTypeface(general.regulartypeface());
        edittext_Saleable_area.setTypeface(general.regulartypeface());
        edittext_measurementunit.setTypeface(general.regulartypeface());
        textview_type_of_area.setTypeface(general.regulartypeface());
        textview_areatypetvalue.setTypeface(general.regulartypeface());
        textview_loadingoverbuildup.setTypeface(general.regulartypeface());

        textview_on_completion.setTypeface(general.regulartypeface());
        textview_on_completion_value.setTypeface(general.regulartypeface());
        textview_dateof_inspection.setTypeface(general.regulartypeface());
        textview_dateof_inspection_value.setTypeface(general.regulartypeface());
        textview_realizable.setTypeface(general.regulartypeface());
        textview_distress.setTypeface(general.regulartypeface());

        /* *//***Clear Area type spinner***//*
        Singleton.getInstance().areaType.clear();
        Singleton.getInstance().areaType.add("Select");*/


        //  limit the 2 char after the decimal point
        edittext_governmentvalue_area.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(10, 2)});
        edittext_proposedvaluation_result.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(15, 2)});


    }


    /*****
     * Initiate Edittext values
     * ******/
    public void setEdittextCarpetArea(String setArea) {
        if (edittext_carpet_area != null) {
            edittext_carpet_area.setText(setArea);
            edittext_type_area.setText(setArea);
            //edittext_insurance_area.setText(setArea);
            //edittext_governmentvalue_area.setText(setArea);
        }
    }

    public void setEdittextBuildupArea(String setArea) {
        if (edittext_builtup_area != null) {
            edittext_builtup_area.setText(setArea);
            edittext_type_area.setText(setArea);
            //edittext_insurance_area.setText(setArea);
            //edittext_governmentvalue_area.setText(setArea);
        }
    }

    public void setEdittextSaleableArea(String setArea) {
        if (edittext_Saleable_area != null) {
            edittext_Saleable_area.setText(setArea);
            edittext_type_area.setText(setArea);
            //edittext_insurance_area.setText(setArea);
            //edittext_governmentvalue_area.setText(setArea);
        }
    }

    public void setEdittextCarpetArea_ka(String setArea) {
        if (edittext_carpet_area != null) {
            edittext_carpet_area.setText(setArea);
        }
    }

    public void setEdittextBuildupArea_ka(String setArea) {
        if (edittext_builtup_area != null) {
            edittext_builtup_area.setText(setArea);
        }
    }

    public void setEdittextSaleableArea_ka(String setArea) {
        if (edittext_Saleable_area != null) {
            edittext_Saleable_area.setText(setArea);
        }
    }

    public void setGovernmentvalue_area_ka(String setArea) {
        if (edittext_governmentvalue_area != null) {
            edittext_governmentvalue_area.setText(setArea);
            GovernmentMarketCalculation(setArea, edittext_governmentvalue_rate.getText().toString());
        }
    }

    public void setEdittextAsPerCompletion(final String setValue) {

        mJumpRunnable = new Runnable() {
            public void run() {
                editText_AsPerCompletion.setText(setValue);

                setRealizable_distress_inspection();
            }
        };
        mHandler = new Handler();
        mHandler.postDelayed(mJumpRunnable, DELAY_TIME);
    }

    /*******
     * Initiate Spinner values
     * *******/

    public void AreaTypeSpinner_ka(final ArrayList<String> areaType, final String area_type) {
        if (mContext != null) {
            adapterAreaType = new ArrayAdapter<>(mContext, R.layout.row_spinner_item, areaType);
            adapterAreaType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAreaType.setAdapter(adapterAreaType);

            Log.e("areaTypeID_is", "areaTypeID: " + areaTypeID);

            if (areaTypeID.equalsIgnoreCase("0")) {
                // Select
                spinnerAreaType.setSelection(0);
            } else if (areaTypeID.equalsIgnoreCase("1")) {
                // Carpet
                for (int i = 0; i < areaType.size(); i++) {
                    if ("Carpet".equalsIgnoreCase(areaType.get(i))) {
                        spinnerAreaType.setSelection(i);
                    }
                }
                Singleton.getInstance().indPropertyValuation.setSelectedConstructionArea(edittext_carpet_area.getText().toString());
                setEdittextCarpetArea(edittext_carpet_area.getText().toString().trim());
                FlatPentHouseSpinnerCalculation();
            } else if (areaTypeID.equalsIgnoreCase("2")) {
                // Built up
                for (int i = 0; i < areaType.size(); i++) {
                    if ("Built up".equalsIgnoreCase(areaType.get(i))) {
                        spinnerAreaType.setSelection(i);
                    }
                }
                Singleton.getInstance().indPropertyValuation.setSelectedConstructionArea(edittext_builtup_area.getText().toString());
                setEdittextBuildupArea(edittext_builtup_area.getText().toString().trim());
                FlatPentHouseSpinnerCalculation();
            } else if (areaTypeID.equalsIgnoreCase("3")) {
                // Saleable
                for (int i = 0; i < areaType.size(); i++) {
                    if ("Saleable".equalsIgnoreCase(areaType.get(i))) {
                        spinnerAreaType.setSelection(i);
                    }
                }
                Singleton.getInstance().indPropertyValuation.setSelectedConstructionArea(edittext_Saleable_area.getText().toString());
                setEdittextSaleableArea(edittext_Saleable_area.getText().toString().trim());
                FlatPentHouseSpinnerCalculation();
            }
        }
    }


    public void AreaTypeSpinner(final ArrayList<String> areaType, final String area_type) {
        if (mContext != null) {
            adapterAreaType = new ArrayAdapter<>(mContext, R.layout.row_spinner_item, areaType);
            adapterAreaType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAreaType.setAdapter(adapterAreaType);
            spinnerAreaType.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(spinnerAreaType);
                    return false;
                }
            });

            if (area_type.equalsIgnoreCase("Carpet")) {
                for (int i = 0; i < areaType.size(); i++) {
                    if (area_type.equalsIgnoreCase(areaType.get(i))) {
                        spinnerAreaType.setSelection(i);
                        areaTypeID = "1";
                    }
                }

            } else if (area_type.equalsIgnoreCase("Built up")) {
                for (int i = 0; i < areaType.size(); i++) {
                    String area = areaType.get(i);
                    if (area_type.equalsIgnoreCase(areaType.get(i))) {
                        spinnerAreaType.setSelection(i);
                        areaTypeID = "2";
                    }
                }
            /*if (areaType.size() >= 3)
                spinnerAreaType.setSelection(2);*/
            } else if (area_type.equalsIgnoreCase("Saleable")) {
                for (int i = 0; i < areaType.size(); i++) {
                    if (area_type.equalsIgnoreCase(areaType.get(i))) {
                        spinnerAreaType.setSelection(i);
                        areaTypeID = "3";
                    }
                }
            } else {
                spinnerAreaType.setSelection(0);
                areaTypeID = "0";
            }

            spinnerAreaType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Log.e("areaTypeID_is", "spinner::: " + areaType.get(position));

                    if (position == 0) {
                        System.err.println("Position zero on Spinner area type");
                        // areaTypeID = "0";
                    } else {
                        if (areaType.get(position).equalsIgnoreCase("Carpet")) {
                            // For DB
                            Singleton.getInstance().indPropertyValuation.setSelectedConstructionArea(edittext_carpet_area.getText().toString());
                            setEdittextCarpetArea(edittext_carpet_area.getText().toString().trim());
                            textview_type_of_area.setText(areaType.get(position));
                            areaTypeID = "1";

                            String edittext_governmentvalue_area_str = edittext_governmentvalue_area.getText().toString();
                            if (general.isEmpty(edittext_governmentvalue_area_str)) {
                                // Empty
                               /* edittext_governmentvalue_area.setText(edittext_carpet_area.getText().toString().trim());
                                edittext_governmentvalue_area.setError(null);*/
                            }

                            String edittext_insurance_area_str = edittext_insurance_area.getText().toString();
                            if (general.isEmpty(edittext_insurance_area_str)) {
                                // Empty
                                edittext_insurance_area.setText(edittext_carpet_area.getText().toString().trim());
                                edittext_insurance_area.setError(null);
                            }

                            FlatPentHouseSpinnerCalculation();
                        } else if (areaType.get(position).equalsIgnoreCase("Built up")) {
                            // For DB
                            Singleton.getInstance().indPropertyValuation.setSelectedConstructionArea(edittext_builtup_area.getText().toString());
                            setEdittextBuildupArea(edittext_builtup_area.getText().toString().trim());
                            textview_type_of_area.setText(areaType.get(position));
                            areaTypeID = "2";

                            String edittext_governmentvalue_area_str = edittext_governmentvalue_area.getText().toString();
                            if (general.isEmpty(edittext_governmentvalue_area_str)) {
                                // Empty
                                /*edittext_governmentvalue_area.setText(edittext_builtup_area.getText().toString().trim());
                                edittext_governmentvalue_area.setError(null);*/
                            }

                            String edittext_insurance_area_str = edittext_insurance_area.getText().toString();
                            if (general.isEmpty(edittext_insurance_area_str)) {
                                // Empty
                                edittext_insurance_area.setText(edittext_builtup_area.getText().toString().trim());
                                edittext_insurance_area.setError(null);
                            }

                            FlatPentHouseSpinnerCalculation();
                        } else if (areaType.get(position).equalsIgnoreCase("Saleable")) {
                            // For DB
                            Singleton.getInstance().indPropertyValuation.setSelectedConstructionArea(edittext_Saleable_area.getText().toString());
                            setEdittextSaleableArea(edittext_Saleable_area.getText().toString().trim());
                            textview_type_of_area.setText(areaType.get(position));
                            areaTypeID = "3";

                            String edittext_governmentvalue_area_str = edittext_governmentvalue_area.getText().toString();
                            if (general.isEmpty(edittext_governmentvalue_area_str)) {
                                // Empty
                                /*edittext_governmentvalue_area.setText(edittext_Saleable_area.getText().toString().trim());
                                edittext_governmentvalue_area.setError(null);*/
                            }

                            String edittext_insurance_area_str = edittext_insurance_area.getText().toString();
                            if (general.isEmpty(edittext_insurance_area_str)) {
                                // Empty
                                edittext_insurance_area.setText(edittext_Saleable_area.getText().toString().trim());
                                edittext_insurance_area.setError(null);
                            }

                            FlatPentHouseSpinnerCalculation();
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    public void FlatPentHouseSpinnerCalculation() {
        AreaTypeMarketCalculation(edittext_type_area.getText().toString().trim(), edittext_type_rate.getText().toString().trim());
        TerraceMarketCalculation(edittext_terrace_area.getText().toString().trim(), edittext_terrace_rate.getText().toString().trim());
        BalconyMarketCalculation(edittext_balcony_area.getText().toString().trim(), edittext_balcony_rate.getText().toString());

        OtlaMarketCalculation(edittext_otla_area.getText().toString().trim(), edittext_otla_rate.getText().toString());
        MezzanineMarketCalculation(edittext_mezzanine_area.getText().toString().trim(), edittext_mezzanine_rate.getText().toString());

        InsuranceMarketCalculation(edittext_insurance_area.getText().toString().trim(), edittext_insurance_rate.getText().toString().trim());
        GovernmentMarketCalculation(edittext_governmentvalue_area.getText().toString().trim(), edittext_governmentvalue_rate.getText().toString().trim());
        AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
        setRealizableDistressCalculationVal();
        setRealizable_distress_completion();
        setRealizable_distress_inspection();
    }

    public void FlatSpinnerCalculation() {
        FlatAreaTypeMarketCalculation(edittext_type_area.getText().toString().trim(), edittext_type_rate.getText().toString().trim());
        InsuranceMarketCalculation(edittext_insurance_area.getText().toString().trim(), edittext_insurance_rate.getText().toString().trim());
        GovernmentMarketCalculation(edittext_governmentvalue_area.getText().toString().trim(), edittext_governmentvalue_rate.getText().toString().trim());
        AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
        setRealizableDistressCalculationVal();

        setRealizable_distress_completion();
        setRealizable_distress_inspection();
    }

    public void PentHouseFlatCalculation() {

        edittext_type_rate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String toString = edittext_type_rate.getText().toString();

                    float sumtotal_is = 0;
                    int sum_total_is = 0;
                    sumtotal_is = (general.convertTofloat(toString)) / (general.convertTofloat("3"));
                    sum_total_is = general.convertToRoundoff(sumtotal_is);
                    edittext_terrace_rate.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total_is)));
                    edittext_balcony_rate.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total_is)));

                    String edittext_terrace_area_is = edittext_terrace_area.getText().toString().trim();
                    String edittext_terrace_rate_is = edittext_terrace_rate.getText().toString().trim();
                    if (!general.isEmpty(edittext_terrace_area_is) && !general.isEmpty(edittext_terrace_rate_is)) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(edittext_terrace_area_is)) * (general.convertTofloat(edittext_terrace_rate_is));
                        sum_total = general.convertToRoundoff(sumtotal);
                        textview_terrace_marketvalue.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else {
                        textview_terrace_marketvalue.setText("" + 0);
                    }

                    String edittext_balcony_area_is = edittext_balcony_area.getText().toString().trim();
                    String edittext_balcony_rate_is = edittext_balcony_rate.getText().toString().trim();
                    if (!general.isEmpty(edittext_balcony_area_is) && !general.isEmpty(edittext_balcony_rate_is)) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(edittext_balcony_area_is)) * (general.convertTofloat(edittext_balcony_rate_is));
                        sum_total = general.convertToRoundoff(sumtotal);
                        textview_balcony_marketvalue.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else {
                        textview_balcony_marketvalue.setText("" + 0);
                    }


                    AreaTypeMarketCalculation(edittext_type_area.getText().toString(), toString);
                    AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                    setRealizableDistressCalculationVal();

                    setRealizable_distress_completion();
                    setRealizable_distress_inspection();

                }
            }
        });

        //Type area rate
        edittext_type_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String area_rate = charSequence.toString();
                AreaTypeMarketCalculation(edittext_type_area.getText().toString(), area_rate);
                AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                setRealizableDistressCalculationVal();

                setRealizable_distress_completion();
                setRealizable_distress_inspection();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Terrace
        edittext_terrace_area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String terrace_area = charSequence.toString();
                TerraceMarketCalculation(terrace_area, edittext_terrace_rate.getText().toString());
                AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                setRealizableDistressCalculationVal();

                setRealizable_distress_completion();
                setRealizable_distress_inspection();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        //Terrace
        edittext_terrace_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String terrace_rate = charSequence.toString();
                TerraceMarketCalculation(edittext_terrace_area.getText().toString(), terrace_rate);
                AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                setRealizableDistressCalculationVal();

                setRealizable_distress_completion();
                setRealizable_distress_inspection();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //Balcony Area
        edittext_balcony_area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String balcony_area = charSequence.toString();
                BalconyMarketCalculation(balcony_area, edittext_balcony_rate.getText().toString());
                AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                setRealizableDistressCalculationVal();

                setRealizable_distress_completion();
                setRealizable_distress_inspection();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        //Balcony Rate
        edittext_balcony_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String balcony_rate = charSequence.toString();
                BalconyMarketCalculation(edittext_balcony_area.getText().toString(), balcony_rate);
                AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                setRealizableDistressCalculationVal();

                setRealizable_distress_completion();
                setRealizable_distress_inspection();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //Otla Area
        edittext_otla_area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String balcony_area = charSequence.toString();
                OtlaMarketCalculation(balcony_area, edittext_otla_rate.getText().toString());
                AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                setRealizableDistressCalculationVal();

                setRealizable_distress_completion();
                setRealizable_distress_inspection();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        //Otla Rate
        edittext_otla_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String balcony_rate = charSequence.toString();
                OtlaMarketCalculation(edittext_otla_area.getText().toString(), balcony_rate);
                AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                setRealizableDistressCalculationVal();

                setRealizable_distress_completion();
                setRealizable_distress_inspection();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Mezzanine Area
        edittext_mezzanine_area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String balcony_area = charSequence.toString();
                MezzanineMarketCalculation(balcony_area, edittext_mezzanine_rate.getText().toString());
                AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                setRealizableDistressCalculationVal();

                setRealizable_distress_completion();
                setRealizable_distress_inspection();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        //Mezzanine Rate
        edittext_mezzanine_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String balcony_rate = charSequence.toString();
                MezzanineMarketCalculation(edittext_mezzanine_area.getText().toString(), balcony_rate);
                AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                setRealizableDistressCalculationVal();

                setRealizable_distress_completion();
                setRealizable_distress_inspection();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //Insurance rate
        edittext_insurance_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String insurance_rate = charSequence.toString();
                // String area_val, String area_rate
                InsuranceMarketCalculation(edittext_insurance_area.getText().toString(), insurance_rate);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Insurance rate
        edittext_insurance_area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String insurance_rate = charSequence.toString();
                // String area_val, String area_rate
                InsuranceMarketCalculation(insurance_rate, edittext_insurance_rate.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Government rate
        edittext_governmentvalue_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String government_rate = charSequence.toString();
                // String area_val, String area_rate
                GovernmentMarketCalculation(edittext_governmentvalue_area.getText().toString(), government_rate);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Government area
        edittext_governmentvalue_area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String government_rate = charSequence.toString();
                // String area_val, String area_rate
                GovernmentMarketCalculation(government_rate, edittext_governmentvalue_rate.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // as per completion
        editText_AsPerCompletion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String aspercomple_rate = charSequence.toString();
                AsperCompletionCalculation(aspercomple_rate, textview_totalpropertyvalue_result.getText().toString().trim());
                setRealizable_distress_inspection();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void AreaTypeMarketCalculation(String area_val, String area_rate) {
        if ((!general.isEmpty(area_val)) && (!general.isEmpty(area_rate))) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_val)) * (general.convertTofloat(area_rate));
            sum_total = general.convertToRoundoff(sumtotal);
            // textview_type_marketvalue.setText("" + sum_total);
            textview_type_marketvalue.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

        } else {
            textview_type_marketvalue.setText("" + 0);
        }

        /*****Total calculation*****/
        String area_total = textview_type_marketvalue.getText().toString().trim();
        String terrace_total = textview_terrace_marketvalue.getText().toString().trim();
        String balcony_total = textview_balcony_marketvalue.getText().toString().trim();
        String otla_total = textview_otla_marketvalue.getText().toString().trim();
        String mezzanine_total = textview_mezzanine_marketvalue.getText().toString().trim();

        if (!general.isEmpty(area_total) && !(general.isEmpty(terrace_total) && !(general.isEmpty(balcony_total) && !(general.isEmpty(otla_total) && !(general.isEmpty(mezzanine_total)))))) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_total)) + general.convertTofloat(terrace_total) + (general.convertTofloat(balcony_total)) + (general.convertTofloat(otla_total)) + (general.convertTofloat(mezzanine_total));
            sum_total = general.convertToRoundoff(sumtotal);

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

        } else if (!general.isEmpty(area_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));

        } else if (!general.isEmpty(balcony_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(balcony_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(balcony_total)));

        } else if (!general.isEmpty(terrace_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));

        } else if (!general.isEmpty(otla_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(otla_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(otla_total)));

        } else if (!general.isEmpty(mezzanine_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(mezzanine_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(mezzanine_total)));

        }


        /*if (!general.isEmpty(area_total) && !(general.isEmpty(terrace_total))) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_total)) + (general.convertTofloat(terrace_total));
            sum_total = general.convertToRoundoff(sumtotal);

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

        } else if (!general.isEmpty(area_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));

        } else if (!general.isEmpty(terrace_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));

        }*/
    }

    public void FlatAreaTypeMarketCalculation(String area_val, String area_rate) {
        if (!general.isEmpty(area_val) && !general.isEmpty(area_rate)) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_val)) * (general.convertTofloat(area_rate));
            sum_total = general.convertToRoundoff(sumtotal);
            // textview_type_marketvalue.setText("" + sum_total);
            // textview_total_marketval.setText("" + sum_total);
            //  textview_totalpropertyvalue_result.setText("" + sum_total);
            textview_type_marketvalue.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

        } else {
            textview_type_marketvalue.setText("" + 0);
            textview_total_marketval.setText("" + 0);
            textview_totalpropertyvalue_result.setText("" + 0);
        }
    }

    public void TerraceMarketCalculation(String area_val, String area_rate) {
        if (!general.isEmpty(area_val) && !general.isEmpty(area_rate)) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_val)) * (general.convertTofloat(area_rate));
            sum_total = general.convertToRoundoff(sumtotal);
            //  textview_terrace_marketvalue.setText("" + sum_total);
            textview_terrace_marketvalue.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

        } else {
            textview_terrace_marketvalue.setText("" + 0);
        }


        /*****Total calculation*****/

        String area_total = textview_type_marketvalue.getText().toString().trim();
        String terrace_total = textview_terrace_marketvalue.getText().toString().trim();
        String balcony_total = textview_balcony_marketvalue.getText().toString().trim();
        String otla_total = textview_otla_marketvalue.getText().toString().trim();
        String mezzanine_total = textview_mezzanine_marketvalue.getText().toString().trim();

        if (!general.isEmpty(area_total) && !(general.isEmpty(terrace_total) && !(general.isEmpty(balcony_total) && !(general.isEmpty(otla_total) && !(general.isEmpty(mezzanine_total)))))) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_total)) + general.convertTofloat(terrace_total) + (general.convertTofloat(balcony_total)) + (general.convertTofloat(otla_total)) + (general.convertTofloat(mezzanine_total));
            sum_total = general.convertToRoundoff(sumtotal);
          /*  textview_total_marketval.setText("" + sum_total);
            textview_totalpropertyvalue_result.setText("" + sum_total);*/

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

        } else if (!general.isEmpty(area_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));

          /*  textview_total_marketval.setText("" + area_total);
            textview_totalpropertyvalue_result.setText("" + area_total);*/
        } else if (!general.isEmpty(terrace_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));
           /* textview_total_marketval.setText("" + terrace_total);
            textview_totalpropertyvalue_result.setText("" + terrace_total);*/
        } else if (!general.isEmpty(balcony_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(balcony_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(balcony_total)));
           /* textview_total_marketval.setText("" + terrace_total);
            textview_totalpropertyvalue_result.setText("" + terrace_total);*/
        } else if (!general.isEmpty(otla_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(otla_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(otla_total)));

        } else if (!general.isEmpty(mezzanine_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(mezzanine_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(mezzanine_total)));

        }
    }


    public void BalconyMarketCalculation(String area_val, String area_rate) {
        if (!general.isEmpty(area_val) && !general.isEmpty(area_rate)) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_val)) * (general.convertTofloat(area_rate));
            sum_total = general.convertToRoundoff(sumtotal);
            //  textview_terrace_marketvalue.setText("" + sum_total);
            textview_balcony_marketvalue.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

        } else {
            textview_balcony_marketvalue.setText("" + 0);
        }

        /*****Total calculation*****/

        String area_total = textview_type_marketvalue.getText().toString().trim();
        String terrace_total = textview_terrace_marketvalue.getText().toString().trim();
        String balcony_total = textview_balcony_marketvalue.getText().toString().trim();
        String otla_total = textview_otla_marketvalue.getText().toString().trim();
        String mezzanine_total = textview_mezzanine_marketvalue.getText().toString().trim();

        if (!general.isEmpty(area_total) && !(general.isEmpty(terrace_total) && !(general.isEmpty(balcony_total) && !(general.isEmpty(otla_total) && !(general.isEmpty(mezzanine_total)))))) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_total)) + general.convertTofloat(terrace_total) + (general.convertTofloat(balcony_total)) + (general.convertTofloat(otla_total)) + (general.convertTofloat(mezzanine_total));
            sum_total = general.convertToRoundoff(sumtotal);

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

        } else if (!general.isEmpty(area_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));

        } else if (!general.isEmpty(balcony_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(balcony_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(balcony_total)));

        } else if (!general.isEmpty(terrace_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));

        } else if (!general.isEmpty(otla_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(otla_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(otla_total)));

        } else if (!general.isEmpty(mezzanine_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(mezzanine_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(mezzanine_total)));

        }
    }

    public void OtlaMarketCalculation(String area_val, String area_rate) {
        if (!general.isEmpty(area_val) && !general.isEmpty(area_rate)) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_val)) * (general.convertTofloat(area_rate));
            sum_total = general.convertToRoundoff(sumtotal);
            //  textview_terrace_marketvalue.setText("" + sum_total);
            textview_otla_marketvalue.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

        } else {
            textview_otla_marketvalue.setText("" + 0);
        }

        /*****Total calculation*****/

        String area_total = textview_type_marketvalue.getText().toString().trim();
        String terrace_total = textview_terrace_marketvalue.getText().toString().trim();
        String balcony_total = textview_balcony_marketvalue.getText().toString().trim();
        String otla_total = textview_otla_marketvalue.getText().toString().trim();
        String mezzanine_total = textview_mezzanine_marketvalue.getText().toString().trim();

        if (!general.isEmpty(area_total) && !(general.isEmpty(terrace_total) && !(general.isEmpty(balcony_total) && !(general.isEmpty(otla_total) && !(general.isEmpty(mezzanine_total)))))) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_total)) + general.convertTofloat(terrace_total) + (general.convertTofloat(balcony_total)) + (general.convertTofloat(otla_total)) + (general.convertTofloat(mezzanine_total));
            sum_total = general.convertToRoundoff(sumtotal);

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

        } else if (!general.isEmpty(area_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));

        } else if (!general.isEmpty(balcony_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(balcony_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(balcony_total)));

        } else if (!general.isEmpty(terrace_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));

        } else if (!general.isEmpty(otla_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(otla_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(otla_total)));

        } else if (!general.isEmpty(mezzanine_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(mezzanine_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(mezzanine_total)));

        }
    }

    public void MezzanineMarketCalculation(String area_val, String area_rate) {
        if (!general.isEmpty(area_val) && !general.isEmpty(area_rate)) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_val)) * (general.convertTofloat(area_rate));
            sum_total = general.convertToRoundoff(sumtotal);
            //  textview_terrace_marketvalue.setText("" + sum_total);
            textview_mezzanine_marketvalue.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

        } else {
            textview_mezzanine_marketvalue.setText("" + 0);
        }

        /*****Total calculation*****/

        String area_total = textview_type_marketvalue.getText().toString().trim();
        String terrace_total = textview_terrace_marketvalue.getText().toString().trim();
        String balcony_total = textview_balcony_marketvalue.getText().toString().trim();
        String otla_total = textview_otla_marketvalue.getText().toString().trim();
        String mezzanine_total = textview_mezzanine_marketvalue.getText().toString().trim();

        if (!general.isEmpty(area_total) && !(general.isEmpty(terrace_total) && !(general.isEmpty(balcony_total) && !(general.isEmpty(otla_total) && !(general.isEmpty(mezzanine_total)))))) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_total)) + general.convertTofloat(terrace_total) + (general.convertTofloat(balcony_total)) + (general.convertTofloat(otla_total)) + (general.convertTofloat(mezzanine_total));
            sum_total = general.convertToRoundoff(sumtotal);

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

        } else if (!general.isEmpty(area_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));

        } else if (!general.isEmpty(balcony_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(balcony_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(balcony_total)));

        } else if (!general.isEmpty(terrace_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));

        } else if (!general.isEmpty(otla_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(otla_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(otla_total)));

        } else if (!general.isEmpty(mezzanine_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(mezzanine_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(mezzanine_total)));

        }
    }


    public void InsuranceMarketCalculation(String area_val, String area_rate) {
        if (!general.isEmpty(area_val) && !general.isEmpty(area_rate)) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_val)) * (general.convertTofloat(area_rate));
            sum_total = general.convertToRoundoff(sumtotal);
            //    textview_insurance_marketvalue.setText("" + sum_total);
            textview_insurance_marketvalue.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
        } else {
            textview_insurance_marketvalue.setText("" + 0);
        }
    }


    public void GovernmentMarketCalculation(String area_val, String area_rate) {
        if (!general.isEmpty(area_val) && !general.isEmpty(area_rate)) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_val)) * (general.convertTofloat(area_rate));
            sum_total = general.convertToRoundoff(sumtotal);
            //textview_governmentvalue_marketvalue.setText("" + sum_total);
            textview_governmentvalue_marketvalue.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
        } else {
            textview_governmentvalue_marketvalue.setText("" + 0);
        }
    }

    public void AsperCompletionCalculation(String completed_percentage, String total_propertyval) {
        if (!general.isEmpty(completed_percentage) && !general.isEmpty(total_propertyval)) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(completed_percentage) / 100) * (general.convertTofloat(total_propertyval));
            sum_total = general.convertToRoundoff(sumtotal);
            //  textview_aspercompletion_result.setText("" + sum_total);
            textview_aspercompletion_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

            /*RealizableDistressUnderConstructionCalc()*/
        } else {
            textview_aspercompletion_result.setText("" + 0);
        }
    }

    public void setRealizableDistressCalculationVal() {

        String select_realizable = spinner_realizable.getText().toString();
        if ((!select_realizable.equalsIgnoreCase("Select")) && (!select_realizable.equalsIgnoreCase("Custom"))) {
            String realize = select_realizable.replace("%", "").trim();
            String realizable = RealizableDistressCalculation(realize, textview_totalpropertyvalue_result.getText().toString().trim());
            edittext_realizable_value_total.setText("" + general.DecimalFormattedCommaString(realizable));
        }

        String select_distress = spinner_distress.getText().toString();
        if ((!select_distress.equalsIgnoreCase("Select")) && (!select_distress.equalsIgnoreCase("Custom"))) {
            String distressval = select_distress.replace("%", "").trim();
            String distress = RealizableDistressCalculation(distressval, textview_totalpropertyvalue_result.getText().toString().trim());
            edittext_distress_total.setText("" + general.DecimalFormattedCommaString(distress));
        }
    }

    public String RealizableDistressCalculation(String realizable_distress_val, String total_property_val) {
        String total_value = "";
        if (!general.isEmpty(realizable_distress_val) && !general.isEmpty(total_property_val) && !realizable_distress_val.equalsIgnoreCase("Custom")) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(realizable_distress_val) / 100) * (general.convertTofloat(total_property_val));
            sum_total = general.convertToRoundoff(sumtotal);
            total_value = "" + sum_total;
        } else {
            total_value = "" + 0;
        }
        return total_value;
    }

    public void RealizeDistressTotalVal() {
        edittext_realizable_value_total.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String value = charSequence.toString();
                if (!general.isEmpty(value)) {
                    Singleton.getInstance().indPropertyValuation.setRealizationValue(value);
                } else {
                    Singleton.getInstance().indPropertyValuation.setRealizationValue("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edittext_distress_total.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String value = charSequence.toString();
                if (!general.isEmpty(value)) {
                    Singleton.getInstance().indPropertyValuation.setDistressValue(value);
                } else {
                    Singleton.getInstance().indPropertyValuation.setDistressValue("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /*********completion as per the Under construction %********/
        edittext_realizable_completion_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String value = charSequence.toString();
                if (!general.isEmpty(value)) {
                    Singleton.getInstance().indPropertyValuation.setRealizationValue(value);
                } else {
                    Singleton.getInstance().indPropertyValuation.setRealizationValue("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edittext_distress_completion_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String value = charSequence.toString();
                if (!general.isEmpty(value)) {
                    Singleton.getInstance().indPropertyValuation.setDistressValue(value);
                } else {
                    Singleton.getInstance().indPropertyValuation.setDistressValue("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /*********inspection as per the Under construction %********/
        edittext_realizable_inspection_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String value = charSequence.toString();
                if (!general.isEmpty(value)) {
                    Singleton.getInstance().indPropertyValuation.setRealizationValueAsOnDate(value);
                } else {
                    Singleton.getInstance().indPropertyValuation.setRealizationValueAsOnDate("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edittext_distress_inspection_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String value = charSequence.toString();
                if (!general.isEmpty(value)) {
                    Singleton.getInstance().indPropertyValuation.setDistressValueAsOnDate(value);
                } else {
                    Singleton.getInstance().indPropertyValuation.setDistressValueAsOnDate("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    public void setRealizable_distress_completion() {

        String select_realizable = spinner_realizable_completion.getText().toString();
        if ((!select_realizable.equalsIgnoreCase("Select")) && (!select_realizable.equalsIgnoreCase("Custom"))) {
            String realize = select_realizable.replace("%", "").trim();
            String realizable = RealizableDistressCalculation(realize, textview_totalpropertyvalue_result.getText().toString().trim());
            edittext_realizable_completion_value.setText("" + general.DecimalFormattedCommaString(realizable));
        }

        String select_distress = spinner_distress_completion.getText().toString();
        if ((!select_distress.equalsIgnoreCase("Select")) && (!select_distress.equalsIgnoreCase("Custom"))) {
            String distressval = select_distress.replace("%", "").trim();
            String distress = RealizableDistressCalculation(distressval, textview_totalpropertyvalue_result.getText().toString().trim());
            edittext_distress_completion_value.setText("" + general.DecimalFormattedCommaString(distress));
        }
    }

    public void setRealizable_distress_inspection() {

        String realizable = spinner_realizable_inspection.getText().toString();
        if ((!realizable.equalsIgnoreCase("Select")) && (!realizable.equalsIgnoreCase("Custom"))) {
            String realize = realizable.replace("%", "").trim();
            String _realizable = RealizableDistressUnderConstructionCalc(realize, textview_aspercompletion_result.getText().toString().trim());
            edittext_realizable_inspection_value.setText("" + general.DecimalFormattedCommaString(_realizable));
        }

        String distress = spinner_distress_inspection.getText().toString();
        if ((!distress.equalsIgnoreCase("Select")) && (!distress.equalsIgnoreCase("Custom"))) {
            String distressval = distress.replace("%", "").trim();
            String _distress = RealizableDistressUnderConstructionCalc(distressval, textview_aspercompletion_result.getText().toString().trim());
            edittext_distress_inspection_value.setText("" + general.DecimalFormattedCommaString(_distress));
        }
    }

    public String RealizableDistressUnderConstructionCalc(String realizable_distress_val, String asper_completion) {
        //  String value = FragmentFlat_ka.edittext_general_comp.getText().toString();
        String value = editText_AsPerCompletion.getText().toString();
        float percentage = 0;
        if (!general.isEmpty(value)) {
            if (value.equalsIgnoreCase("Select") || value.equalsIgnoreCase("Custom")) {
            } else {
                percentage = general.convertToint(value);
            }
        }

        String total_value = "";

        if (percentage > 0) {
            if (!general.isEmpty(realizable_distress_val) && !general.isEmpty(asper_completion) && !realizable_distress_val.equalsIgnoreCase("Custom")) {
                float sumtotal = 0;
                int sum_total = 0;
                sumtotal = (general.convertTofloat(realizable_distress_val) / 100) * (general.convertTofloat(asper_completion));
                sum_total = general.convertToRoundoff(sumtotal);
                total_value = "" + sum_total;
            } else {
                total_value = "" + 0;
            }
        }
        return total_value;
    }


    public void setIndPropertyValuationforPentHouseFlat() {

        if (Singleton.getInstance().indPropertyValuation != null) {

            String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
            if (!general.isEmpty(caseid))
                Singleton.getInstance().indPropertyValuation.setCaseId(Integer.valueOf(caseid));
            Singleton.getInstance().indPropertyValuation.setCarpetArea(edittext_carpet_area.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setBuildUpArea(edittext_builtup_area.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setSuperBuildUpArea(edittext_Saleable_area.getText().toString().trim());
            // Singleton.getInstance().indPropertyValuation.setCarpetAreaUnit(Measurement_id);
            Singleton.getInstance().indPropertyValuation.setCarpetAreaTypeId(areaTypeID);

            Singleton.getInstance().indPropertyValuation.setTotalValueAtCompletion(general.ReplaceCommaSaveToString(textview_aspercompletion_result.getText().toString().trim()));
            Singleton.getInstance().indPropertyValuation.setCompletionPercentage(editText_AsPerCompletion.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setTotalPropertyValue(general.ReplaceCommaSaveToString(textview_totalpropertyvalue_result.getText().toString().trim()));

            Singleton.getInstance().indPropertyValuation.setTerraceArea(edittext_terrace_area.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setTerraceRate(edittext_terrace_rate.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setTerraceValue(general.ReplaceCommaSaveToString(textview_terrace_marketvalue.getText().toString().trim()));

            Singleton.getInstance().indPropertyValuation.setBalconyArea(edittext_balcony_area.getText().toString().trim());

            Singleton.getInstance().indPropertyValuation.setOtlaArea(edittext_otla_area.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setMezzanineArea(edittext_mezzanine_area.getText().toString().trim());

            Singleton.getInstance().indPropertyValuation.setBalconyRate(edittext_balcony_rate.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setOtlaRate(edittext_otla_rate.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setMezzanineRate(edittext_mezzanine_rate.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setBalconyValue(general.ReplaceCommaSaveToString(textview_balcony_marketvalue.getText().toString().trim()));
            Singleton.getInstance().indPropertyValuation.setOtlaValue(general.ReplaceCommaSaveToString(textview_otla_marketvalue.getText().toString().trim()));
            Singleton.getInstance().indPropertyValuation.setMezzanineValue(general.ReplaceCommaSaveToString(textview_mezzanine_marketvalue.getText().toString().trim()));


            Singleton.getInstance().indPropertyValuation.setConstructionRate(edittext_type_rate.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setInsuredConstructionRate(edittext_insurance_rate.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setInsuranceValue(general.ReplaceCommaSaveToString(textview_insurance_marketvalue.getText().toString().trim()));
            Singleton.getInstance().indPropertyValuation.setConstructionDLCRate(edittext_governmentvalue_rate.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setConstructionDLCValue(general.ReplaceCommaSaveToString(textview_governmentvalue_marketvalue.getText().toString().trim()));
         /*   String val = edittext_realizable_value_total.getText().toString().trim();
            Singleton.getInstance().indPropertyValuation.setRealizationValue(general.ReplaceCommaSaveToString(edittext_realizable_value_total.getText().toString().trim()));
            Singleton.getInstance().indPropertyValuation.setDistressValue(general.ReplaceCommaSaveToString(edittext_distress_total.getText().toString().trim()));*/
            Singleton.getInstance().indPropertyValuation.setProposedValuation(general.ReplaceCommaSaveToString(edittext_proposedvaluation_result.getText().toString()));
            Singleton.getInstance().indPropertyValuation.setProposedValuationComments(editText_remarks.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setGovernmentArea(edittext_governmentvalue_area.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setInsuranceArea(edittext_insurance_area.getText().toString().trim());

            setRealizeDistressvalue();

        }

        if (Singleton.getInstance().indProperty != null) {
            String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
            if (!general.isEmpty(caseid))
                Singleton.getInstance().indProperty.setCaseId(Integer.valueOf(caseid));
            Singleton.getInstance().indProperty.setAvgPercentageCompletion(editText_AsPerCompletion.getText().toString().trim());
        }
    }

    private void setRealizeDistressvalue() {
        final int constructionid = Singleton.getInstance().indPropertyFloors.get(0).getConstructionStageId();

        if (constructionid == 1) {
            String realize = Singleton.getInstance().indPropertyValuation.getRealizationPercentage();
            String distress = Singleton.getInstance().indPropertyValuation.getDistressPercentage();
            if (!general.isEmpty(realize)) {
                if (realize.equalsIgnoreCase("101")) {
                    Singleton.getInstance().indPropertyValuation.setRealizationValue(general.ReplaceCommaSaveToString(edittext_realizable_value_total.getText().toString().trim()));
                } else {
                    String val = edittext_realizable_value_total.getText().toString().trim();
                    Singleton.getInstance().indPropertyValuation.setRealizationValue(general.ReplaceCommaSaveToString(edittext_realizable_value_total.getText().toString().trim()));

                }
            }
            if (!general.isEmpty(distress)) {
                if (distress.equalsIgnoreCase("101")) {
                    Singleton.getInstance().indPropertyValuation.setDistressValue(general.ReplaceCommaSaveToString(edittext_distress_total.getText().toString().trim()));
                } else {
                    Singleton.getInstance().indPropertyValuation.setDistressValue(general.ReplaceCommaSaveToString(edittext_distress_total.getText().toString().trim()));
                }
            }

            Singleton.getInstance().indPropertyValuation.setRealizationValueAsOnDate("");
            Singleton.getInstance().indPropertyValuation.setDistressValueAsOnDate("");
            Singleton.getInstance().indPropertyValuation.setRealizationPercentageAsOnDate("");
            Singleton.getInstance().indPropertyValuation.setDistressPercentageAsOnDate("");

        } else if (constructionid == 2) {

            String realize = Singleton.getInstance().indPropertyValuation.getRealizationPercentage();
            String distress = Singleton.getInstance().indPropertyValuation.getDistressPercentage();
            if (!general.isEmpty(realize)) {
                if (realize.equalsIgnoreCase("101")) {
                    Singleton.getInstance().indPropertyValuation.setRealizationValue(general.ReplaceCommaSaveToString(edittext_realizable_completion_value.getText().toString().trim()));
                } else {
                    Singleton.getInstance().indPropertyValuation.setRealizationValue(general.ReplaceCommaSaveToString(edittext_realizable_completion_value.getText().toString().trim()));
                }
            }

            if (!general.isEmpty(distress)) {
                if (distress.equalsIgnoreCase("101")) {
                    Singleton.getInstance().indPropertyValuation.setDistressValue(general.ReplaceCommaSaveToString(edittext_distress_completion_value.getText().toString().trim()));
                } else {
                    Singleton.getInstance().indPropertyValuation.setDistressValue(general.ReplaceCommaSaveToString(edittext_distress_completion_value.getText().toString().trim()));
                }
            }

            String realizeinsp = Singleton.getInstance().indPropertyValuation.getRealizationPercentageAsOnDate();
            String distressinsp = Singleton.getInstance().indPropertyValuation.getDistressPercentageAsOnDate();
            if (!general.isEmpty(realizeinsp)) {
                if (realizeinsp.equalsIgnoreCase("101")) {
                    Singleton.getInstance().indPropertyValuation.setRealizationValueAsOnDate(general.ReplaceCommaSaveToString(edittext_realizable_inspection_value.getText().toString().trim()));
                }
            }

            if (!general.isEmpty(distressinsp)) {
                if (distressinsp.equalsIgnoreCase("101")) {
                    Singleton.getInstance().indPropertyValuation.setDistressValueAsOnDate(general.ReplaceCommaSaveToString(edittext_distress_inspection_value.getText().toString().trim()));
                }
            }

        } else if (constructionid == 0) {
            Singleton.getInstance().indPropertyValuation.setRealizationPercentage("");
            Singleton.getInstance().indPropertyValuation.setDistressPercentage("");
            Singleton.getInstance().indPropertyValuation.setRealizationValue("");
            Singleton.getInstance().indPropertyValuation.setDistressValue("");

            Singleton.getInstance().indPropertyValuation.setRealizationValueAsOnDate("");
            Singleton.getInstance().indPropertyValuation.setDistressValueAsOnDate("");
            Singleton.getInstance().indPropertyValuation.setRealizationPercentageAsOnDate("");
            Singleton.getInstance().indPropertyValuation.setDistressPercentageAsOnDate("");

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
        String selected_value = "";
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
                String str_radiogenearal = id_radiogenearal.getText().toString();
                if (type_is == 1) {
                    // RealizationPercentage
                    if (str_radiogenearal.equalsIgnoreCase("Select")) {
                        textView.setText(str_radiogenearal);
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentage("");
                        // setRealizationValue
                        edittext_realizable_value_total.setText("");
                        Singleton.getInstance().indPropertyValuation.setRealizationValue("");
                    } else if (str_radiogenearal.equalsIgnoreCase("Custom")) {
                        textView.setText(str_radiogenearal);
                        // For reference set as 0 for custom
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentage("101");
                        edittext_realizable_value_total.setText("");
                        Singleton.getInstance().indPropertyValuation.setRealizationValue("");
                        // setRealizationValue
                        edittext_realizable_value_total.setFocusable(true);
                        edittext_realizable_value_total.setFocusableInTouchMode(true);
                        edittext_realizable_value_total.setEnabled(true);
                        if (general.isEmpty(Singleton.getInstance().indPropertyValuation.getRealizationValue()))
                            edittext_realizable_value_total.setText("");
                    } else {
                        String realizableStr = str_radiogenearal.replace("%", "");
                        textView.setText(realizableStr);
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentage(realizableStr);
                        // setRealizationValue
                        String realizablevalue = RealizableDistressCalculation(realizableStr, textview_totalpropertyvalue_result.getText().toString().trim());
                        edittext_realizable_value_total.setText("" + general.DecimalFormattedCommaString(realizablevalue));
                        Singleton.getInstance().indPropertyValuation.setRealizationValue(realizablevalue);
                        edittext_realizable_value_total.setFocusable(false);
                    }
                } else if (type_is == 2) {
                    // DistressPercentage
                    if (str_radiogenearal.equalsIgnoreCase("Select")) {
                        textView.setText(str_radiogenearal);
                        Singleton.getInstance().indPropertyValuation.setDistressPercentage("");
                        // setDistressValue
                        edittext_distress_total.setText("");
                        Singleton.getInstance().indPropertyValuation.setDistressValue("");
                    } else if (str_radiogenearal.equalsIgnoreCase("Custom")) {
                        textView.setText(str_radiogenearal);
                        // For reference set as 0 for custom
                        Singleton.getInstance().indPropertyValuation.setDistressPercentage("101");
                        edittext_distress_total.setText("");
                        Singleton.getInstance().indPropertyValuation.setDistressValue("");
                        // setDistressValue
                        edittext_distress_total.setFocusable(true);
                        edittext_distress_total.setFocusableInTouchMode(true);
                        edittext_distress_total.setEnabled(true);
                        if (general.isEmpty(Singleton.getInstance().indPropertyValuation.getDistressValue()))
                            edittext_distress_total.setText("");
                    } else {
                        String distressStr = str_radiogenearal.replace("%", "");
                        textView.setText(distressStr);
                        Singleton.getInstance().indPropertyValuation.setDistressPercentage(distressStr);
                        // setDistressValue
                        String distressvalue = RealizableDistressCalculation(distressStr, textview_totalpropertyvalue_result.getText().toString().trim());
                        edittext_distress_total.setText("" + general.DecimalFormattedCommaString(distressvalue));
                        Singleton.getInstance().indPropertyValuation.setDistressValue(distressvalue);
                        edittext_distress_total.setFocusable(false);
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


    private void realizable_distress_uc_popup(final int type_is, final TextView textView) {
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
        String selected_value = "";
        if (type_is == 1) {
            // RealizationPercentage
            popuptitle.setText(getActivity().getResources().getString(R.string.realizable_per_completion));
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getRealizationPercentage())) {
                is_empty = false;
                selected_value = Singleton.getInstance().indPropertyValuation.getRealizationPercentage();
            }
        } else if (type_is == 2) {
            // DistressPercentage
            popuptitle.setText(getActivity().getResources().getString(R.string.distress_per_completion));
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getDistressPercentage())) {
                is_empty = false;
                selected_value = Singleton.getInstance().indPropertyValuation.getDistressPercentage();
            }
        } else if (type_is == 3) {
            // DistressPercentage
            popuptitle.setText(getActivity().getResources().getString(R.string.realizable_per_inspection));
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getRealizationPercentageAsOnDate())) {
                is_empty = false;
                selected_value = Singleton.getInstance().indPropertyValuation.getRealizationPercentageAsOnDate();
            }
        } else if (type_is == 4) {
            // DistressPercentage
            popuptitle.setText(getActivity().getResources().getString(R.string.distress_per_inspection));
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getDistressPercentageAsOnDate())) {
                is_empty = false;
                selected_value = Singleton.getInstance().indPropertyValuation.getDistressPercentageAsOnDate();
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
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentage("");
                        // setRealizationValue
                        edittext_realizable_completion_value.setText("");
                        Singleton.getInstance().indPropertyValuation.setRealizationValue("");
                    } else if (str_radiogenearal.equalsIgnoreCase("Custom")) {
                        textView.setText(str_radiogenearal);
                        // For reference set as 0 for custom
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentage("101");
                        edittext_realizable_completion_value.setText("");
                        Singleton.getInstance().indPropertyValuation.setRealizationValue("");
                        // setRealizationValue
                        edittext_realizable_completion_value.setFocusable(true);
                        edittext_realizable_completion_value.setFocusableInTouchMode(true);
                        edittext_realizable_completion_value.setEnabled(true);
                        if (general.isEmpty(Singleton.getInstance().indPropertyValuation.getRealizationValue()))
                            edittext_realizable_completion_value.setText("");
                    } else {
                        String realizableStr = str_radiogenearal.replace("%", "");
                        textView.setText(realizableStr);
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentage(realizableStr);
                        // setRealizationValue
                        String realizablevalue = RealizableDistressCalculation(realizableStr, textview_totalpropertyvalue_result.getText().toString().trim());
                        edittext_realizable_completion_value.setText("" + general.DecimalFormattedCommaString(realizablevalue));
                        Singleton.getInstance().indPropertyValuation.setRealizationValue(realizablevalue);
                        edittext_realizable_completion_value.setFocusable(false);
                    }
                } else if (type_is == 2) {
                    // DistressPercentage
                    if (str_radiogenearal.equalsIgnoreCase("Select")) {
                        textView.setText(str_radiogenearal);
                        Singleton.getInstance().indPropertyValuation.setDistressPercentage("");
                        // setDistressValue
                        edittext_distress_completion_value.setText("");
                        Singleton.getInstance().indPropertyValuation.setDistressValue("");
                    } else if (str_radiogenearal.equalsIgnoreCase("Custom")) {
                        textView.setText(str_radiogenearal);
                        // For reference set as 0 for custom
                        Singleton.getInstance().indPropertyValuation.setDistressPercentage("101");
                        edittext_distress_completion_value.setText("");
                        Singleton.getInstance().indPropertyValuation.setDistressValue("");
                        // setDistressValue
                        edittext_distress_completion_value.setFocusable(true);
                        edittext_distress_completion_value.setFocusableInTouchMode(true);
                        edittext_distress_completion_value.setEnabled(true);
                        if (general.isEmpty(Singleton.getInstance().indPropertyValuation.getDistressValue()))
                            edittext_distress_completion_value.setText("");
                    } else {
                        String distressStr = str_radiogenearal.replace("%", "");
                        textView.setText(distressStr);
                        Singleton.getInstance().indPropertyValuation.setDistressPercentage(distressStr);
                        // setDistressValue
                        String distressvalue = RealizableDistressCalculation(distressStr, textview_totalpropertyvalue_result.getText().toString().trim());
                        edittext_distress_completion_value.setText("" + general.DecimalFormattedCommaString(distressvalue));
                        Singleton.getInstance().indPropertyValuation.setDistressValue(distressvalue);
                        edittext_distress_completion_value.setFocusable(false);
                    }
                } else if (type_is == 3) {
                    // RealizationPercentage
                    if (str_radiogenearal.equalsIgnoreCase("Select")) {
                        textView.setText(str_radiogenearal);
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentageAsOnDate("");
                        // setRealizationValue
                        edittext_realizable_inspection_value.setText("");
                        Singleton.getInstance().indPropertyValuation.setRealizationValueAsOnDate("");
                    } else if (str_radiogenearal.equalsIgnoreCase("Custom")) {
                        textView.setText(str_radiogenearal);
                        // For reference set as 0 for custom
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentageAsOnDate("101");
                        edittext_realizable_inspection_value.setText("");
                        Singleton.getInstance().indPropertyValuation.setRealizationValueAsOnDate("");
                        // setRealizationValue
                        edittext_realizable_inspection_value.setFocusable(true);
                        edittext_realizable_inspection_value.setFocusableInTouchMode(true);
                        edittext_realizable_inspection_value.setEnabled(true);
                        if (general.isEmpty(Singleton.getInstance().indPropertyValuation.getRealizationValueAsOnDate()))
                            edittext_realizable_inspection_value.setText("");
                    } else {
                        String realizableStr = str_radiogenearal.replace("%", "");
                        textView.setText(realizableStr);
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentageAsOnDate(realizableStr);
                        // setRealizationValue
                        String realizablevalue = RealizableDistressUnderConstructionCalc(realizableStr, textview_aspercompletion_result.getText().toString().trim());
                        edittext_realizable_inspection_value.setText("" + general.DecimalFormattedCommaString(realizablevalue));
                        Singleton.getInstance().indPropertyValuation.setRealizationValueAsOnDate(realizablevalue);
                        edittext_realizable_inspection_value.setFocusable(false);
                    }
                } else if (type_is == 4) {
                    // DistressPercentage
                    if (str_radiogenearal.equalsIgnoreCase("Select")) {
                        textView.setText(str_radiogenearal);
                        Singleton.getInstance().indPropertyValuation.setDistressPercentageAsOnDate("");
                        // setDistressValue
                        edittext_distress_inspection_value.setText("");
                        Singleton.getInstance().indPropertyValuation.setDistressValueAsOnDate("");
                    } else if (str_radiogenearal.equalsIgnoreCase("Custom")) {
                        textView.setText(str_radiogenearal);
                        // For reference set as 0 for custom
                        Singleton.getInstance().indPropertyValuation.setDistressPercentageAsOnDate("101");
                        edittext_distress_inspection_value.setText("");
                        Singleton.getInstance().indPropertyValuation.setDistressValueAsOnDate("");
                        // setDistressValue
                        edittext_distress_inspection_value.setFocusable(true);
                        edittext_distress_inspection_value.setFocusableInTouchMode(true);
                        edittext_distress_inspection_value.setEnabled(true);
                        if (general.isEmpty(Singleton.getInstance().indPropertyValuation.getDistressValueAsOnDate()))
                            edittext_distress_inspection_value.setText("");
                    } else {
                        String distressStr = str_radiogenearal.replace("%", "");
                        textView.setText(distressStr);
                        Singleton.getInstance().indPropertyValuation.setDistressPercentageAsOnDate(distressStr);
                        // setDistressValue
                        String distressvalue = RealizableDistressUnderConstructionCalc(distressStr, textview_aspercompletion_result.getText().toString().trim());
                        edittext_distress_inspection_value.setText("" + general.DecimalFormattedCommaString(distressvalue));
                        Singleton.getInstance().indPropertyValuation.setDistressValueAsOnDate(distressvalue);
                        edittext_distress_inspection_value.setFocusable(false);
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


    private void loadingOverBuildup_popup(final TextView textView) {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.loading_over_builup_popup);

        TextView popuptitle = (TextView) dialog.findViewById(R.id.title);
        popuptitle.setTypeface(general.mediumtypeface());

        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        RadioGroup id_radiogroup_stage = (RadioGroup) dialog.findViewById(R.id.id_radiogroup_stage);
        RadioButton id_radio_select = (RadioButton) dialog.findViewById(R.id.id_radio_select);
        RadioButton id_radio_0 = (RadioButton) dialog.findViewById(R.id.id_radio_0);
        RadioButton id_radio_5 = (RadioButton) dialog.findViewById(R.id.id_radio_5);
        RadioButton id_radio_10 = (RadioButton) dialog.findViewById(R.id.id_radio_10);
        RadioButton id_radio_15 = (RadioButton) dialog.findViewById(R.id.id_radio_15);
        RadioButton id_radio_20 = (RadioButton) dialog.findViewById(R.id.id_radio_20);
        RadioButton id_radio_25 = (RadioButton) dialog.findViewById(R.id.id_radio_25);
        RadioButton id_radio_30 = (RadioButton) dialog.findViewById(R.id.id_radio_30);
        RadioButton id_radio_35 = (RadioButton) dialog.findViewById(R.id.id_radio_35);
        RadioButton id_radio_40 = (RadioButton) dialog.findViewById(R.id.id_radio_40);
        RadioButton id_radio_45 = (RadioButton) dialog.findViewById(R.id.id_radio_45);
        RadioButton id_radio_50 = (RadioButton) dialog.findViewById(R.id.id_radio_50);
        RadioButton id_radio_55 = (RadioButton) dialog.findViewById(R.id.id_radio_55);
        RadioButton id_radio_60 = (RadioButton) dialog.findViewById(R.id.id_radio_60);
        RadioButton id_radio_65 = (RadioButton) dialog.findViewById(R.id.id_radio_65);
        RadioButton id_radio_70 = (RadioButton) dialog.findViewById(R.id.id_radio_70);
        RadioButton id_radio_75 = (RadioButton) dialog.findViewById(R.id.id_radio_75);
        id_radio_select.setTypeface(general.mediumtypeface());
        id_radio_0.setTypeface(general.mediumtypeface());
        id_radio_5.setTypeface(general.mediumtypeface());
        id_radio_10.setTypeface(general.mediumtypeface());
        id_radio_15.setTypeface(general.mediumtypeface());
        id_radio_20.setTypeface(general.mediumtypeface());
        id_radio_25.setTypeface(general.mediumtypeface());
        id_radio_30.setTypeface(general.mediumtypeface());
        id_radio_35.setTypeface(general.mediumtypeface());
        id_radio_40.setTypeface(general.mediumtypeface());
        id_radio_45.setTypeface(general.mediumtypeface());
        id_radio_50.setTypeface(general.mediumtypeface());
        id_radio_55.setTypeface(general.mediumtypeface());
        id_radio_60.setTypeface(general.mediumtypeface());
        id_radio_65.setTypeface(general.mediumtypeface());
        id_radio_70.setTypeface(general.mediumtypeface());
        id_radio_75.setTypeface(general.mediumtypeface());

        boolean is_empty = true;
        String selected_value = "";

        // loading buildup Percentage
        popuptitle.setText(getActivity().getResources().getString(R.string.loading_per));
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getBAULoadingPercentage())) {
            is_empty = false;
            selected_value = Singleton.getInstance().indPropertyValuation.getBAULoadingPercentage();
        }


        if (!is_empty) {
            if (selected_value.equalsIgnoreCase("0")) {
                id_radio_0.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("5")) {
                id_radio_5.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("10")) {
                id_radio_10.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("15")) {
                id_radio_15.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("20")) {
                id_radio_20.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("25")) {
                id_radio_25.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("30")) {
                id_radio_30.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("35")) {
                id_radio_35.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("40")) {
                id_radio_40.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("45")) {
                id_radio_45.setChecked(true);
            } else if (selected_value.equalsIgnoreCase("50")) {
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

                // loading over build upPercentage
                if (str_radiogenearal.equalsIgnoreCase("Select")) {
                    textView.setText(str_radiogenearal);
                    Singleton.getInstance().indPropertyValuation.setBAULoadingPercentage("");
                } else {
                    String loadingStr = str_radiogenearal.replace("%", "");
                    textView.setText(loadingStr);
                    Singleton.getInstance().indPropertyValuation.setBAULoadingPercentage(loadingStr);

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


    private void hideSoftKeyboard(View addkeys) {
        if ((addkeys != null) && (getActivity() != null)) {
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(addkeys.getWindowToken(), 0);
        }
        show_emptyFocus();
    }

    private void show_emptyFocus() {
        // Show focus
        if (OtherDetails.my_focuslayout != null) {
            OtherDetails.my_focuslayout.requestFocus();
        }
    }
}
