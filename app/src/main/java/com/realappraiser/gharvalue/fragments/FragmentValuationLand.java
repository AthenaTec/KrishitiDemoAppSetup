package com.realappraiser.gharvalue.fragments;

import static com.realappraiser.gharvalue.fragments.PhotoLatLong.latvalue;
import static com.realappraiser.gharvalue.fragments.PhotoLatLong.longvalue;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.realappraiser.gharvalue.model.Measurements;
import com.realappraiser.gharvalue.model.RatePopup;
import com.realappraiser.gharvalue.utils.DecimalDigitsInputFilter;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentValuationLand extends Fragment implements RatePopupupInterface {
    // TODO General class to call typeface and all...
    public static General general;

    // TODO RatioGroup ini
    @BindView(R.id.id_radiogroup_considerforvaluation_land)
    RadioGroup id_radiogroup_considerforvaluation_land;
    @BindView(R.id.id_radio_considerforvaluation_permissiblearea_land)
    RadioButton id_radio_considerforvaluation_permissiblearea_land;
    @BindView(R.id.id_radio_considerforvaluation_actualarea_land)
    RadioButton id_radio_considerforvaluation_actualarea_land;

    // TODO - Linear
    @BindView(R.id.linear_permissiblearea)
    LinearLayout linear_permissiblearea;
    @BindView(R.id.linear_actualarea)
    LinearLayout linear_actualarea;

    // TODO - Spinner
    @BindView(R.id.spinner_ft_meter)
    Spinner spinner_ft_meter;

    // TODO - Textview
    @BindView(R.id.textview_landvaluation)
    TextView textview_landvaluation;
    @BindView(R.id.textview_unit_land)
    TextView textview_unit_land;
    @BindView(R.id.textview_totalpropertyvalue)
    TextView textview_totalpropertyvalue;

    @BindView(R.id.textview_proposedvaluation)
    TextView textview_proposedvaluation;
    @BindView(R.id.tvRatePopup)
    TextView tvRatePopup;
    @BindView(R.id.tvCompareRate)
    TextView tvCompareRate;

    // TODO - Edittext
    public static EditText editText_guideline_rate;
    public static EditText editText_guideline_value;
    public static EditText editText_permissiblearea;
    public static EditText editText_unit_permissiblearea;
    public static EditText editText_rate_permissiblearea;
    public static EditText editText_total_permissiblearea;
    public static EditText edittext_proposedvaluation_result;
    public static EditText editText_actualarea;
    public static EditText editText_unit_actualarea;
    public static EditText editText_rate_actualarea;
    public static EditText editText_total_actualarea;
    public static EditText editText_remarks;
    public static TextView textview_totalpropertyvalue_result;


    public static int permission_check = 0;
    public static int mea_unit = 0;

    // san - new code
    public static TextView spinner_realizable;
    public static TextView spinner_distress;
    public Dialog dialog;
    public static EditText edittext_realizable_value_total;
    public static EditText edittext_distress_total;

    public static ArrayList<String> realizableList = new ArrayList<>();
    public static ArrayList<String> distressList = new ArrayList<>();
    private RatePopupApi ratePopupApi;
    public static String caseid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fragment_valuation_land, container, false);
        ButterKnife.bind(this, view);

        general = new General(getActivity());
        SettingsUtils.init(getActivity());

        initValues(view);
        DisplayValuesLandBuilding();

        id_radiogroup_considerforvaluation_land.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                hideSoftKeyboard(editText_guideline_rate);


                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                switch (checkedRadioButton.getId()) {
                    case R.id.id_radio_considerforvaluation_permissiblearea_land:
                        linear_permissiblearea.setVisibility(View.VISIBLE);
                        linear_actualarea.setVisibility(View.GONE);

                        Singleton.getInstance().indPropertyValuation.setDocumentedLandValueSel(true);
                        Singleton.getInstance().indPropertyValuation.setMeasuredLandValueSel(false);
                        permission_check = 1;
                        permissionarea_dlc();
                        Total_Property_Value();
                        //Newly added
                        setRealizableDistressCalculationVal();

                        break;
                    case R.id.id_radio_considerforvaluation_actualarea_land:
                        linear_actualarea.setVisibility(View.VISIBLE);
                        linear_permissiblearea.setVisibility(View.GONE);

                        Singleton.getInstance().indPropertyValuation.setDocumentedLandValueSel(false);
                        Singleton.getInstance().indPropertyValuation.setMeasuredLandValueSel(true);
                        permission_check = 2;
                        permissionarea_dlc();
                        Total_Property_Value();
                        //Newly added
                        setRealizableDistressCalculationVal();

                        break;
                }
            }
        });

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


    private void initValues(View view) {

        editText_guideline_rate = (EditText)view.findViewById(R.id.editText_guideline_rate);
        editText_guideline_value = (EditText)view.findViewById(R.id.editText_guideline_value);
        editText_permissiblearea = (EditText)view.findViewById(R.id.editText_permissiblearea);
        editText_unit_permissiblearea = (EditText)view.findViewById(R.id.editText_unit_permissiblearea);
        editText_rate_permissiblearea = (EditText)view.findViewById(R.id.editText_rate_permissiblearea);
        editText_total_permissiblearea = (EditText)view.findViewById(R.id.editText_total_permissiblearea);
        edittext_proposedvaluation_result = (EditText)view.findViewById(R.id.edittext_proposedvaluation_result);
        editText_actualarea = (EditText)view.findViewById(R.id.editText_actualarea);
        editText_unit_actualarea = (EditText)view.findViewById(R.id.editText_unit_actualarea);
        editText_rate_actualarea = (EditText)view.findViewById(R.id.editText_rate_actualarea);
        editText_total_actualarea = (EditText)view.findViewById(R.id.editText_total_actualarea);
        editText_remarks = (EditText)view.findViewById(R.id.editText_remarks);
        textview_totalpropertyvalue_result = (TextView) view.findViewById(R.id.textview_totalpropertyvalue_result);

        textview_landvaluation.setTypeface(general.mediumtypeface());
        textview_unit_land.setTypeface(general.regulartypeface());
        textview_totalpropertyvalue.setTypeface(general.regulartypeface());
        textview_totalpropertyvalue_result.setTypeface(general.regulartypeface());
        textview_proposedvaluation.setTypeface(general.regulartypeface());
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
        edittext_proposedvaluation_result.setTypeface(general.regulartypeface());
        editText_remarks.setTypeface(general.regulartypeface());
        id_radio_considerforvaluation_permissiblearea_land.setTypeface(general.regulartypeface());
        id_radio_considerforvaluation_actualarea_land.setTypeface(general.regulartypeface());

        //  limit the 2 char after the decimal point
        // editText_guideline_rate.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(10,2)});
        edittext_proposedvaluation_result.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(10,2)});


        spinner_realizable = (TextView) view.findViewById(R.id.spinner_realizable);
        spinner_distress = (TextView) view.findViewById(R.id.spinner_distress);
        edittext_realizable_value_total = (EditText) view.findViewById(R.id.edittext_realizable_value_total);
        edittext_distress_total = (EditText) view.findViewById(R.id.edittext_distress_total);

        caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

        ratePopupApi = new RatePopupApi(getActivity(), this);

        ratePopupApi.getRatePopup(caseid, String.valueOf(SettingsUtils.Latitudes), String.valueOf(SettingsUtils.Longitudes));


    }


    /********
     * Save the land values to API for indproperty valuation
     * *********/
    public void save_landval() {
        String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        if(!general.isEmpty(caseid))
            Singleton.getInstance().indPropertyValuation.setCaseId(Integer.valueOf(caseid));
        Singleton.getInstance().indPropertyValuation.setGuidelineRate(editText_guideline_rate.getText().toString().trim());
        Singleton.getInstance().indPropertyValuation.setGuidelineValue(general.ReplaceCommaSaveToString(editText_guideline_value.getText().toString().trim()));
        Singleton.getInstance().indPropertyValuation.setDocumentLandRate(editText_rate_permissiblearea.getText().toString().trim());
        Singleton.getInstance().indPropertyValuation.setDocumentLandValue(general.ReplaceCommaSaveToString(editText_total_permissiblearea.getText().toString().trim()));
        Singleton.getInstance().indPropertyValuation.setMeasuredLandRate(editText_rate_actualarea.getText().toString().trim());
        Singleton.getInstance().indPropertyValuation.setMeasuredLandValue(general.ReplaceCommaSaveToString(editText_total_actualarea.getText().toString().trim()));

        /****Newly Added Line***/
        Singleton.getInstance().indPropertyValuation.setRealizationValue(general.ReplaceCommaSaveToString(edittext_realizable_value_total.getText().toString().trim()));
        Singleton.getInstance().indPropertyValuation.setDistressValue(general.ReplaceCommaSaveToString(edittext_distress_total.getText().toString().trim()));
        /****Newly Added Line ended***/

        Singleton.getInstance().indPropertyValuation.setTotalPropertyValue(general.ReplaceCommaSaveToString(textview_totalpropertyvalue_result.getText().toString().trim()));
        Singleton.getInstance().indPropertyValuation.setProposedValuation(edittext_proposedvaluation_result.getText().toString().trim());
        Singleton.getInstance().indPropertyValuation.setProposedValuationComments(editText_remarks.getText().toString().trim());
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


    /*******
     * Land Valuation
     * **********/
    public void permissionarea_dlc() {
        String permissiblearea_str = editText_permissiblearea.getText().toString();
        String actualarea_str = editText_actualarea.getText().toString();
        int measurment_unit_property = Singleton.getInstance().indProperty.getDocumentLandAreaUnit();
        String guideline_rate_str = editText_guideline_rate.getText().toString().trim();


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
                        if(!general.isEmpty(guideline_rate_str))
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                       // editText_guideline_value.setText("" + sum_total);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("10.7639"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("9"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("43560"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("107639"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("17452.0069808"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
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
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("0.836127"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("4046.85642"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("43560"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("1620.4319957"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
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
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("1.19599"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("4840"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("11959.9"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(permissiblearea_str)) * (general.convertTofloat("1620.4319957"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
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
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("10.7639"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("9"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("43560"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("107639"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("17452.0069808"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
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
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("1.1959900463011"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("4046.85642"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("43560"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("1620.4319957"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
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
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 2) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("1.19599"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 3) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 4) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("4840"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 5) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("11959.9"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else if (measurment_unit_property == 6) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(actualarea_str)) * (general.convertTofloat("1620.4319957"));
                        sumtotal = (sumtotal) * (general.convertTofloat(guideline_rate_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_guideline_value.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    }
                } else {
                    /* all text should be null*/
                    editText_guideline_value.setText("");
                }
            }
        }
        /* AUTUAL AREA end */
    }


    public void Total_Property_Value() {
        /******total property result value********/

        String land_total_str = "0";

        if (permission_check == 1) {
            land_total_str = editText_total_permissiblearea.getText().toString();
        } else if (permission_check == 2) {
            land_total_str = editText_total_actualarea.getText().toString();
        }

        if (!general.isEmpty(land_total_str)) {
            float property = (general.convertTofloat(land_total_str));
            int total_property = general.convertToRoundoff(property);
            //textview_totalpropertyvalue_result.setText("" + total_property);
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(String.valueOf(total_property)));
        }
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
                 //   editText_total_permissiblearea.setText("" + sum_total);
                    editText_total_permissiblearea.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    Total_Property_Value();
                    //Newly added
                    setRealizableDistressCalculationVal();
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
                  //  permission_check = 2;
                    permissionarea_dlc();
                /* multiple the above compond into  */
                    String actualarea_str = editText_actualarea.getText().toString();
                    if (!general.isEmpty(editText_rate_permissiblearea.getText().toString()) && !general.isEmpty(actualarea_str)) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(editText_rate_permissiblearea.getText().toString())) * (general.convertTofloat(actualarea_str));
//                    sum_total = (int) sumtotal;
                        sum_total = general.convertToRoundoff(sumtotal);
                      //  editText_total_actualarea.setText("" + sum_total);
                        editText_total_actualarea.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));

                        if(permission_check==2)
                        Total_Property_Value();
                        //Newly added
                        setRealizableDistressCalculationVal();
                    } else {
                        editText_total_actualarea.setText("");
                    }
                } else {
                    editText_actualarea.setText("");
                    editText_total_actualarea.setText("");
                }

        }
    }

    /******
     * Display values from API
     * *******/
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
        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getGuidelineValue())) {
           // editText_guideline_value.setText(Singleton.getInstance().indPropertyValuation.getGuidelineValue());
            editText_guideline_value.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getGuidelineValue()));

        } else {
            editText_guideline_value.setText("");
        }

        if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getTotalPropertyValue())) {
         //   textview_totalpropertyvalue_result.setText(Singleton.getInstance().indPropertyValuation.getTotalPropertyValue());
            textview_totalpropertyvalue_result.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getTotalPropertyValue()));

        } else {
            textview_totalpropertyvalue_result.setText("0");
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
            //editText_total_permissiblearea.setText(Singleton.getInstance().indPropertyValuation.getDocumentLandValue());
            editText_total_permissiblearea.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getDocumentLandValue()));

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
           // editText_total_actualarea.setText(Singleton.getInstance().indPropertyValuation.getMeasuredLandValue());
            editText_total_actualarea.setText("" + general.DecimalFormattedCommaString(Singleton.getInstance().indPropertyValuation.getMeasuredLandValue()));
        } else {
            editText_total_actualarea.setText("");
        }

        String realized_total = Singleton.getInstance().indPropertyValuation.getRealizationValue();
        String distress_total = Singleton.getInstance().indPropertyValuation.getDistressValue();
        if (!general.isEmpty(realized_total)) {
            edittext_realizable_value_total.setText("" + general.DecimalFormattedCommaString(realized_total));
            // edittext_realizable_value_total.setText(realized_total);
        }
        if (!general.isEmpty(distress_total)) {
            edittext_distress_total.setText("" + general.DecimalFormattedCommaString(distress_total));
            // edittext_distress_total.setText(distress_total);
        }

        editText_rate_permissiblearea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    Log.e("onFocus_coming", "onFocus_coming" + hasFocus);
                    // Copy the value to aspercompletion_val
                    String rate_str = editText_rate_permissiblearea.getText().toString();
                    editText_rate_actualarea.setText(rate_str);

                    String permissiblearea_str = editText_permissiblearea.getText().toString();
                    if (!general.isEmpty(rate_str) && !general.isEmpty(permissiblearea_str)) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(rate_str)) * (general.convertTofloat(permissiblearea_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_total_permissiblearea.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else {
                        editText_total_permissiblearea.setText("");
                    }

                    String actualarea_str = editText_actualarea.getText().toString();
                    if (!general.isEmpty(rate_str) && !general.isEmpty(actualarea_str)) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(rate_str)) * (general.convertTofloat(actualarea_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_total_actualarea.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else {
                        editText_total_actualarea.setText("");
                    }
                    Total_Property_Value();
                    //Newly added
                    setRealizableDistressCalculationVal();

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
                    String rate_str = editText_rate_actualarea.getText().toString();
                    editText_rate_permissiblearea.setText(rate_str);

                    String permissiblearea_str = editText_permissiblearea.getText().toString();
                    if (!general.isEmpty(rate_str) && !general.isEmpty(permissiblearea_str)) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(rate_str)) * (general.convertTofloat(permissiblearea_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_total_permissiblearea.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else {
                        editText_total_permissiblearea.setText("");
                    }

                    String actualarea_str = editText_actualarea.getText().toString();
                    if (!general.isEmpty(rate_str) && !general.isEmpty(actualarea_str)) {
                        float sumtotal = 0;
                        int sum_total = 0;
                        sumtotal = (general.convertTofloat(rate_str)) * (general.convertTofloat(actualarea_str));
                        sum_total = general.convertToRoundoff(sumtotal);
                        editText_total_actualarea.setText("" + general.DecimalFormattedCommaString(String.valueOf(sum_total)));
                    } else {
                        editText_total_actualarea.setText("");
                    }
                    Total_Property_Value();
                    //Newly added
                    setRealizableDistressCalculationVal();
                }
            }
        });



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


        /******
         * Radio button selection for land permissible or actual area
         * *******/
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



        /*****Realizable and Distress Percentage*****/
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
                edittext_realizable_value_total.setFocusable(false);
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
                edittext_distress_total.setFocusable(false);
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