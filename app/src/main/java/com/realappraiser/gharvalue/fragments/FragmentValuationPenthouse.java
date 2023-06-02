package com.realappraiser.gharvalue.fragments;

import static com.realappraiser.gharvalue.fragments.PhotoLatLong.latvalue;
import static com.realappraiser.gharvalue.fragments.PhotoLatLong.longvalue;

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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.realappraiser.gharvalue.communicator.JsonRequestData;
import com.realappraiser.gharvalue.communicator.RatePopupApi;
import com.realappraiser.gharvalue.communicator.RatePopupupInterface;
import com.realappraiser.gharvalue.communicator.RequestParam;
import com.realappraiser.gharvalue.communicator.TaskCompleteListener;
import com.realappraiser.gharvalue.communicator.WebserviceCommunicator;
import com.realappraiser.gharvalue.model.GetPropertyCompareDetailsModel;
import com.realappraiser.gharvalue.model.GetPropertyDetailsModel;
import com.realappraiser.gharvalue.model.RatePopup;
import com.realappraiser.gharvalue.utils.DecimalDigitsInputFilter;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentValuationPenthouse extends Fragment implements RatePopupupInterface {

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
    @BindView(R.id.textview_terrace_total_head)
    TextView textview_terrace_total_head;
    @BindView(R.id.textview_insurance)
    TextView textview_insurance;
    @BindView(R.id.textview_area_sub_heading)
    TextView textview_area_sub_heading;
    @BindView(R.id.textview_dlcrate_heading)
    TextView textview_dlcrate_heading;
    @BindView(R.id.textview_governmentvalue_head)
    TextView textview_governmentvalue_head;
    @BindView(R.id.textview_governmentvalue_subhead)
    TextView textview_governmentvalue_subhead;
    @BindView(R.id.textview_realizable_head)
    TextView textview_realizable_head;
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
    @BindView(R.id.tvRatePopup)
    TextView tvRatePopup;
    @BindView(R.id.tvCompareRate)
    TextView tvCompareRate;

    public static Context mContext;
    private static final String TAG = "FragmentValuationPentho";

    // TODO - Edittext

    public static EditText edittext_type_area;
    public static EditText edittext_type_rate;
    public static EditText edittext_terrace_area;
    public static EditText edittext_terrace_rate;
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

    public Dialog dialog;

    // TODO - Spinner
    /*public static Spinner spinnerLoadingOverBuildup;*/
    public static Spinner spinnerAreaType;
    public static TextView spinner_realizable;
    public static TextView spinner_distress;


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

    private RatePopupApi ratePopupApi;
    public static String caseid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fragment_valuation_penthouse, container, false);
        ButterKnife.bind(this, view);
        general = new General(getActivity());
        fragmentActivity = getActivity();
        SettingsUtils.init(getActivity());

        initValues(view);
        setPropertyType();

        AreaTypeSpinner(Singleton.getInstance().areaType, "Select");

        PentHouseFlatCalculation();
        RealizableDistressSpinner();
        LoadingBuildupSpinner();
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


        // TODO -  call the mandatory_valiadation
        if (Singleton.getInstance().enable_validation_error) {
            set_pentflathouse_mandatory();
        }

        caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

        ratePopupApi = new RatePopupApi(getActivity(), this);

        Log.e(TAG, "onCreateView: " + PhotoLatLong.latvalue.getText().toString());
        ratePopupApi.getRatePopup(caseid, String.valueOf(SettingsUtils.Latitudes), String.valueOf(SettingsUtils.Longitudes));


        tvCompareRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetails();
            }
        });


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

    private void comparePopup(GetPropertyDetailsModel fromJson)
    {
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

    private void set_pentflathouse_mandatory() {
        if (property_type.equalsIgnoreCase("penthouse")) {
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
        }
    }

    private void setPropertyType() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            property_type = bundle.getString("property_type");
        }

        if (property_type.equalsIgnoreCase("flat")) {
            linear_penthouse_terrace_row.setVisibility(View.GONE);
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
            String govern_dlc_rate = Singleton.getInstance().indPropertyValuation.getConstructionDLCRate();
            String terrace_area = Singleton.getInstance().indPropertyValuation.getTerraceArea();
            String terrace_rate = Singleton.getInstance().indPropertyValuation.getTerraceRate();
            //   String areatype_marketval = Singleton.getInstance().indPropertyValuation.getTotalConstructionValue();
            String insurance_value = Singleton.getInstance().indPropertyValuation.getInsuranceValue();
            String dlc_marketval = Singleton.getInstance().indPropertyValuation.getConstructionDLCValue();
            String terrace_val = Singleton.getInstance().indPropertyValuation.getTerraceValue();
            String total_propertyval = Singleton.getInstance().indPropertyValuation.getTotalPropertyValue();

            String realized_total = Singleton.getInstance().indPropertyValuation.getRealizationValue();
            String distress_total = Singleton.getInstance().indPropertyValuation.getDistressValue();
            String aspercomp_percent = Singleton.getInstance().indPropertyValuation.getCompletionPercentage();
            String aspercomp_total = Singleton.getInstance().indPropertyValuation.getTotalValueAtCompletion();
            String proposed_val = Singleton.getInstance().indPropertyValuation.getProposedValuation();
            String proposed_remarks = Singleton.getInstance().indPropertyValuation.getProposedValuationComments();

            if (!general.isEmpty(areatype_rate)) {
                edittext_type_rate.setText(areatype_rate);
            }
            if (!general.isEmpty(insurance_rate)) {
                edittext_insurance_rate.setText(insurance_rate);
            }
            if (!general.isEmpty(govern_dlc_rate)) {
                edittext_governmentvalue_rate.setText(govern_dlc_rate);
            }
            if (!general.isEmpty(insurance_value)) {
                textview_insurance_marketvalue.setText("" + general.DecimalFormattedCommaString(insurance_value));
                //textview_insurance_marketvalue.setText(insurance_value);
            }
            if (!general.isEmpty(dlc_marketval)) {
                textview_governmentvalue_marketvalue.setText("" + general.DecimalFormattedCommaString(dlc_marketval));
                //  textview_governmentvalue_marketvalue.setText(dlc_marketval);
            }

            if (property_type.equalsIgnoreCase("penthouse")) {
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
            }

            if (!general.isEmpty(total_propertyval)) {
                textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(total_propertyval)));
/*
                textview_totalpropertyvalue_result.setText(total_propertyval);*/
            }
            if (!general.isEmpty(realized_total)) {
                edittext_realizable_value_total.setText("" + general.DecimalFormattedCommaString(realized_total));
                // edittext_realizable_value_total.setText(realized_total);
            }
            if (!general.isEmpty(distress_total)) {
                edittext_distress_total.setText("" + general.DecimalFormattedCommaString(distress_total));
                // edittext_distress_total.setText(distress_total);
            }
            if (!general.isEmpty(aspercomp_percent)) {
                editText_AsPerCompletion.setText(aspercomp_percent);
            }
            if (!general.isEmpty(aspercomp_total)) {
                textview_aspercompletion_result.setText("" + general.DecimalFormattedCommaString(aspercomp_total));
                // textview_aspercompletion_result.setText(aspercomp_total);
            }
            if (!general.isEmpty(proposed_val)) {
                edittext_proposedvaluation_result.setText(proposed_val);
            }
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

        edittext_carpet_area.setFocusable(false);
        edittext_builtup_area.setFocusable(false);
        edittext_Saleable_area.setFocusable(false);
        edittext_type_area.setFocusable(false);
        edittext_insurance_area.setFocusable(false);
        edittext_governmentvalue_area.setFocusable(false);
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
        textview_terrace_marketvalue.setTypeface(general.regulartypeface());
        textview_terrace_total_head.setTypeface(general.regulartypeface());
        textview_total_marketval.setTypeface(general.regulartypeface());
        textview_insurance.setTypeface(general.regulartypeface());
        textview_insurance_marketvalue.setTypeface(general.regulartypeface());
        textview_area_sub_heading.setTypeface(general.regulartypeface());
        textview_dlcrate_heading.setTypeface(general.regulartypeface());
        textview_governmentvalue_head.setTypeface(general.regulartypeface());
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

       /* *//***Clear Area type spinner***//*
        Singleton.getInstance().areaType.clear();
        Singleton.getInstance().areaType.add("Select");*/


        //  limit the 2 char after the decimal point
        edittext_proposedvaluation_result.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(15,2)});
    }


    /*****
     * Initiate Edittext values
     * ******/
    public void setEdittextCarpetArea(String setArea) {
        if (edittext_carpet_area != null) {
            edittext_carpet_area.setText(setArea);
            edittext_type_area.setText(setArea);
            edittext_insurance_area.setText(setArea);
            edittext_governmentvalue_area.setText(setArea);
        }
    }

    public void setEdittextBuildupArea(String setArea) {
        if (edittext_builtup_area != null) {
            edittext_builtup_area.setText(setArea);
            edittext_type_area.setText(setArea);
            edittext_insurance_area.setText(setArea);
            edittext_governmentvalue_area.setText(setArea);
        }
    }

    public void setEdittextSaleableArea(String setArea) {
        if (edittext_Saleable_area != null) {
            edittext_Saleable_area.setText(setArea);
            edittext_type_area.setText(setArea);
            edittext_insurance_area.setText(setArea);
            edittext_governmentvalue_area.setText(setArea);
        }
    }

    public void setEdittextAsPerCompletion(final String setValue) {

        mJumpRunnable = new Runnable() {
            public void run() {
                editText_AsPerCompletion.setText(setValue);
            }
        };
        mHandler = new Handler();
        mHandler.postDelayed(mJumpRunnable, DELAY_TIME);
    }

    /*******
     * Initiate Spinner values
     * *******/

    public void AreaTypeSpinner(final ArrayList<String> areaType, final String area_type) {
        if (mContext != null) {
            adapterAreaType = new ArrayAdapter<>(mContext,
                    R.layout.row_spinner_item, areaType);
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
                    Log.e("spinner_areatype", "::: " + areaType.get(position));

                    if (position == 0) {
                        System.err.println("Position zero on Spinner area type");
                        areaTypeID = "0";
                    } else {
                        if (areaType.get(position).equalsIgnoreCase("Carpet")) {
                            // For DB
                            Singleton.getInstance().indPropertyValuation.setSelectedConstructionArea(edittext_carpet_area.getText().toString());
                            setEdittextCarpetArea(edittext_carpet_area.getText().toString().trim());
                            textview_type_of_area.setText(areaType.get(position));

                            areaTypeID = "1";
                            if (property_type.equalsIgnoreCase("penthouse")) {
                                PentHouseSpinnerCalculation();
                            } else {
                                FlatSpinnerCalculation();
                            }
                        } else if (areaType.get(position).equalsIgnoreCase("Built up")) {
                            // For DB
                            Singleton.getInstance().indPropertyValuation.setSelectedConstructionArea(edittext_builtup_area.getText().toString());
                            setEdittextBuildupArea(edittext_builtup_area.getText().toString().trim());
                            textview_type_of_area.setText(areaType.get(position));

                            areaTypeID = "2";
                            if (property_type.equalsIgnoreCase("penthouse")) {
                                PentHouseSpinnerCalculation();
                            } else {
                                FlatSpinnerCalculation();
                            }
                        } else if (areaType.get(position).equalsIgnoreCase("Saleable")) {
                            // For DB
                            Singleton.getInstance().indPropertyValuation.setSelectedConstructionArea(edittext_Saleable_area.getText().toString());
                            setEdittextSaleableArea(edittext_Saleable_area.getText().toString().trim());
                            textview_type_of_area.setText(areaType.get(position));

                            areaTypeID = "3";
                            if (property_type.equalsIgnoreCase("penthouse")) {
                                PentHouseSpinnerCalculation();
                            } else {
                                FlatSpinnerCalculation();
                            }
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }


    public void PentHouseSpinnerCalculation() {
        AreaTypeMarketCalculation(edittext_type_area.getText().toString().trim(), edittext_type_rate.getText().toString().trim());
        TerraceMarketCalculation(edittext_terrace_area.getText().toString().trim(), edittext_terrace_rate.getText().toString().trim());
        InsuranceMarketCalculation(edittext_insurance_area.getText().toString().trim(), edittext_insurance_rate.getText().toString().trim());
        GovernmentMarketCalculation(edittext_governmentvalue_area.getText().toString().trim(), edittext_governmentvalue_rate.getText().toString().trim());
        AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
        setRealizableDistressCalculationVal();
    }

    public void FlatSpinnerCalculation() {
        FlatAreaTypeMarketCalculation(edittext_type_area.getText().toString().trim(), edittext_type_rate.getText().toString().trim());
        InsuranceMarketCalculation(edittext_insurance_area.getText().toString().trim(), edittext_insurance_rate.getText().toString().trim());
        GovernmentMarketCalculation(edittext_governmentvalue_area.getText().toString().trim(), edittext_governmentvalue_rate.getText().toString().trim());
        AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
        setRealizableDistressCalculationVal();
    }

    public void PentHouseFlatCalculation() {

        //Type area rate
        edittext_type_rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String area_rate = charSequence.toString();
                if (property_type.equalsIgnoreCase("penthouse")) {
                    AreaTypeMarketCalculation(edittext_type_area.getText().toString(), area_rate);
                    AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                    setRealizableDistressCalculationVal();
                } else {
                    FlatAreaTypeMarketCalculation(edittext_type_area.getText().toString(), area_rate);
                    AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                    setRealizableDistressCalculationVal();
                }

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
                if (property_type.equalsIgnoreCase("penthouse")) {
                    TerraceMarketCalculation(terrace_area, edittext_terrace_rate.getText().toString());
                    AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                    setRealizableDistressCalculationVal();
                }

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
                if (property_type.equalsIgnoreCase("penthouse")) {
                    TerraceMarketCalculation(edittext_terrace_area.getText().toString(), terrace_rate);
                    AsperCompletionCalculation(editText_AsPerCompletion.getText().toString().trim(), textview_totalpropertyvalue_result.getText().toString().trim());
                    setRealizableDistressCalculationVal();
                }

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
                InsuranceMarketCalculation(edittext_insurance_area.getText().toString(), insurance_rate);

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
                GovernmentMarketCalculation(edittext_governmentvalue_area.getText().toString(), government_rate);

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

        if (!general.isEmpty(area_total) && !(general.isEmpty(terrace_total))) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_total)) + (general.convertTofloat(terrace_total));
            sum_total = general.convertToRoundoff(sumtotal);

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

           /* textview_total_marketval.setText("" + sum_total);
            textview_totalpropertyvalue_result.setText("" + sum_total);*/
        } else if (!general.isEmpty(area_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(area_total)));

       /*     textview_total_marketval.setText("" + area_total);
            textview_totalpropertyvalue_result.setText("" + area_total);*/
        } else if (!general.isEmpty(terrace_total)) {

            textview_total_marketval.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(terrace_total)));


           /* textview_total_marketval.setText("" + terrace_total);
            textview_totalpropertyvalue_result.setText("" + terrace_total);*/
        }
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

        if (!general.isEmpty(area_total) && !(general.isEmpty(terrace_total))) {
            float sumtotal = 0;
            int sum_total = 0;
            sumtotal = (general.convertTofloat(area_total)) + (general.convertTofloat(terrace_total));
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


    public void RealizableDistressSpinner() {
/*
        // spinner - realizableList
        realizableList = new ArrayList<>();
        realizableList = general.Constructionval_common_array();
        ArrayAdapter<String> realizableListAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row_spinner_item, realizableList) {
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
        realizableListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_realizable.setAdapter(realizableListAdapter);
        spinner_realizable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Singleton.getInstance().indPropertyValuation.setRealizationPercentage(null);
                } else {
                    if (realizableList.get(position).toString().equalsIgnoreCase("Custom")) {
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentage("101");
                        edittext_realizable_value_total.setFocusable(true);
                        edittext_realizable_value_total.setFocusableInTouchMode(true);
                        edittext_realizable_value_total.setEnabled(true);
                        if(general.isEmpty(Singleton.getInstance().indPropertyValuation.getRealizationValue()))
                        edittext_realizable_value_total.setText("");
                    } else {
                        String realizableStr = realizableList.get(position).toString().replace("%", "");
                        Singleton.getInstance().indPropertyValuation.setRealizationPercentage(realizableStr);

                        String realizablevalue = RealizableDistressCalculation(realizableStr, textview_totalpropertyvalue_result.getText().toString().trim());
                        edittext_realizable_value_total.setText("" + general.DecimalFormattedCommaString(realizablevalue));
                        Singleton.getInstance().indPropertyValuation.setRealizationValue(realizablevalue);
                        edittext_realizable_value_total.setFocusable(false);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getRealizationPercentage()))

        {
             *//* values *//*
            for (int x = 0; x < realizableList.size(); x++) {
                if (realizableList.get(x).toString().replace("%", "").trim().equalsIgnoreCase(Singleton.getInstance().indPropertyValuation.getRealizationPercentage())) {
                    spinner_realizable.setSelection(x);
                }
            }
          //  edittext_realizable_value_total.setText(Singleton.getInstance().indPropertyValuation.getRealizationValue());
            edittext_realizable_value_total.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getRealizationValue()));
        } else if (Singleton.getInstance().indPropertyValuation.getRealizationPercentage() == null || Singleton.getInstance().indPropertyValuation.getRealizationPercentage() == "null")

        {
            *//* select - 0 - position *//*
            spinner_realizable.setSelection(0);
        } else

        {
            *//* Custom *//*
            spinner_realizable.setSelection(12);
            edittext_realizable_value_total.setFocusable(true);
            //edittext_realizable_value_total.setText(Singleton.getInstance().indPropertyValuation.getRealizationValue());
            edittext_realizable_value_total.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getRealizationValue()));
        }


        */


        /*// spinner - distressList
        distressList = new ArrayList<>();
        distressList = general.Constructionval_common_array();
        ArrayAdapter<String> distressListAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row_spinner_item, distressList) {
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
        distressListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_distress.setAdapter(distressListAdapter);
        spinner_distress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Singleton.getInstance().indPropertyValuation.setDistressPercentage(null);
                } else {
                    if (distressList.get(position).toString().equalsIgnoreCase("Custom")) {
                        Singleton.getInstance().indPropertyValuation.setDistressPercentage("101");
                        edittext_distress_total.setFocusable(true);
                        edittext_distress_total.setFocusableInTouchMode(true);
                        edittext_distress_total.setEnabled(true);
                        if (general.isEmpty(Singleton.getInstance().indPropertyValuation.getDistressValue()))
                            edittext_distress_total.setText("");
                    } else {
                        String distressStr = distressList.get(position).toString().replace("%", "");
                        Singleton.getInstance().indPropertyValuation.setDistressPercentage(distressStr);

                        String distressvalue = RealizableDistressCalculation(distressStr, textview_totalpropertyvalue_result.getText().toString().trim());
                        //edittext_distress_total.setText(distressvalue);
                        edittext_distress_total.setText("" + general.DecimalFormattedCommaString(distressvalue));
                        Singleton.getInstance().indPropertyValuation.setDistressValue(distressvalue);

                        edittext_distress_total.setFocusable(false);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getDistressPercentage()))

        {
            *//* values *//*
            for (int x = 0; x < distressList.size(); x++) {
                if (distressList.get(x).toString().replace("%", "").trim().equalsIgnoreCase(Singleton.getInstance().indPropertyValuation.getDistressPercentage())) {
                    spinner_distress.setSelection(x);
                }
            }
            //edittext_distress_total.setText(Singleton.getInstance().indPropertyValuation.getDistressValue());
            edittext_distress_total.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getDistressValue()));
        } else if (Singleton.getInstance().indPropertyValuation.getDistressPercentage() == null || Singleton.getInstance().indPropertyValuation.getDistressPercentage() == "null")

        {
            *//* select - 0 - position *//*
            spinner_distress.setSelection(0);
        } else

        {
            *//* Custom *//*
            spinner_distress.setSelection(12);
            edittext_distress_total.setFocusable(true);
            edittext_distress_total.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getDistressValue()));
            //  edittext_distress_total.setText(Singleton.getInstance().indPropertyValuation.getDistressValue());
        }*/
    }

    public void LoadingBuildupSpinner() {

     /*   // spinner - Loading over Build up
        loadingbuildupList = new ArrayList<>();
        loadingbuildupList = general.LoadingBuilup_common_array();
        ArrayAdapter<String> ListAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row_spinner_item, loadingbuildupList) {
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
        ListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLoadingOverBuildup.setAdapter(ListAdapter);
        spinnerLoadingOverBuildup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Singleton.getInstance().indPropertyValuation.setBAULoadingPercentage(null);
                } else {

                    String loadingBuildupStr = loadingbuildupList.get(position).toString().replace("%", "");
                    Singleton.getInstance().indPropertyValuation.setBAULoadingPercentage(loadingBuildupStr);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getBAULoadingPercentage()))

        {
             *//* values *//*
            for (int x = 0; x < loadingbuildupList.size(); x++) {
                if (loadingbuildupList.get(x).toString().replace("%", "").trim().equalsIgnoreCase(Singleton.getInstance().indPropertyValuation.getBAULoadingPercentage())) {
                    spinnerLoadingOverBuildup.setSelection(x);
                }
            }
        } else if (Singleton.getInstance().indPropertyValuation.getBAULoadingPercentage() == null || Singleton.getInstance().indPropertyValuation.getBAULoadingPercentage() == "null")

        {
            *//* select - 0 - position *//*
            spinnerLoadingOverBuildup.setSelection(0);
        } else

        {
            *//* Custom *//*
            spinnerLoadingOverBuildup.setSelection(12);
        }*/
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

            if (property_type.equalsIgnoreCase("penthouse")) {
                Singleton.getInstance().indPropertyValuation.setTerraceArea(edittext_terrace_area.getText().toString().trim());
                Singleton.getInstance().indPropertyValuation.setTerraceRate(edittext_terrace_rate.getText().toString().trim());
                Singleton.getInstance().indPropertyValuation.setTerraceValue(general.ReplaceCommaSaveToString(textview_terrace_marketvalue.getText().toString().trim()));
            }

            Singleton.getInstance().indPropertyValuation.setConstructionRate(edittext_type_rate.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setInsuredConstructionRate(edittext_insurance_rate.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setInsuranceValue(general.ReplaceCommaSaveToString(textview_insurance_marketvalue.getText().toString().trim()));
            Singleton.getInstance().indPropertyValuation.setConstructionDLCRate(edittext_governmentvalue_rate.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setConstructionDLCValue(general.ReplaceCommaSaveToString(textview_governmentvalue_marketvalue.getText().toString().trim()));
            String val = edittext_realizable_value_total.getText().toString().trim();
            Singleton.getInstance().indPropertyValuation.setRealizationValue(general.ReplaceCommaSaveToString(edittext_realizable_value_total.getText().toString().trim()));
            Singleton.getInstance().indPropertyValuation.setDistressValue(general.ReplaceCommaSaveToString(edittext_distress_total.getText().toString().trim()));
            Singleton.getInstance().indPropertyValuation.setProposedValuation(edittext_proposedvaluation_result.getText().toString().trim());
            Singleton.getInstance().indPropertyValuation.setProposedValuationComments(editText_remarks.getText().toString().trim());
        }

        if (Singleton.getInstance().indProperty != null) {
            String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
            if (!general.isEmpty(caseid))
                Singleton.getInstance().indProperty.setCaseId(Integer.valueOf(caseid));
            Singleton.getInstance().indProperty.setAvgPercentageCompletion(editText_AsPerCompletion.getText().toString().trim());
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

                //Log.e("id_radiogenearal_",": "+id_radiogenearal.getText().toString());
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
