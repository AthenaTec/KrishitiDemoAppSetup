package com.realappraiser.gharvalue.fragments;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.realappraiser.gharvalue.AppDatabase;
import com.realappraiser.gharvalue.MyApplication;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.adapter.InternalFloorNewAdapter;
import com.realappraiser.gharvalue.adapter.PropertyActualUsageAdapter;
import com.realappraiser.gharvalue.adapter.PropertyFloorInternalAdapter;
import com.realappraiser.gharvalue.adapter.PropertyGeneralFloorAdapter;
import com.realappraiser.gharvalue.communicator.DataModel;
import com.realappraiser.gharvalue.communicator.DataResponse;
import com.realappraiser.gharvalue.communicator.JsonRequestData;
import com.realappraiser.gharvalue.communicator.RequestParam;
import com.realappraiser.gharvalue.communicator.ResponseParser;
import com.realappraiser.gharvalue.communicator.TaskCompleteListener;
import com.realappraiser.gharvalue.communicator.WebserviceCommunicator;
import com.realappraiser.gharvalue.model.GetPhoto;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.model.InternalFloorModel;
import com.realappraiser.gharvalue.model.LatLongDetails;
import com.realappraiser.gharvalue.model.Measurements;
import com.realappraiser.gharvalue.model.OfflineDataModel;
import com.realappraiser.gharvalue.model.OflineCase;
import com.realappraiser.gharvalue.model.PropertyActualUsage;
import com.realappraiser.gharvalue.utils.Connectivity;
import com.realappraiser.gharvalue.utils.DecimalDigitsInputFilter;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.OthersFormListener;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.INPUT_METHOD_SERVICE;

@SuppressWarnings("ALL")
public class
FragmentBuilding extends Fragment implements View.OnClickListener {

    // TODO General class to call typeface and all...
    @SuppressLint("StaticFieldLeak")
    public static General general;

    @BindView(R.id.textview_land_measurement)
    TextView textview_land_measurement;
    // TODO CheckBox - Property
    @BindView(R.id.checkbox_is_compounded)
    CheckBox checkbox_is_compounded;
    @BindView(R.id.textview_construction_details)
    TextView textview_construction_details;

    @SuppressLint("StaticFieldLeak")
    public static InternalFloorNewAdapter internalfloorlistAdapter;

    //@BindView(R.id.editText_compound_permissiblearea)
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_compound_permissiblearea;
    // @BindView(R.id.editText_as_per_measurement)
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_as_per_measurement;
    //  @BindView(R.id.editText_floors)
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_floors_measu;
    // @BindView(R.id.editText_approved_floors)
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_approved_floors;
    @BindView(R.id.textview_actualusage)
    TextView textview_actualusage;
    // @BindView(R.id.editText_Floor_details)
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_Floor_details;

    @BindView(R.id.textview_generl_info_measu)
    TextView textview_generl_info_measu;
    @SuppressLint("StaticFieldLeak")
    public static TextView textview_floor_name;
    @SuppressLint("StaticFieldLeak")
    public static TextView textview_stage;
    @SuppressLint("StaticFieldLeak")
    public static TextView textview_comp;
    @BindView(R.id.textview_progress)
    TextView textview_progress;
    @BindView(R.id.textview_doc_area)
    TextView textview_doc_area;
    @BindView(R.id.textview_actual_area)
    TextView textview_actual_area;
    @BindView(R.id.textview_age)
    TextView textview_age;
    @BindView(R.id.textview_life)
    TextView textview_life;
    @BindView(R.id.textview_floor_usage)
    TextView textview_floor_usage;
    @BindView(R.id.textview_average)
    TextView textview_average;
    //@BindView(R.id.textview_comp_total)
    // TextView textview_comp_total;
    @BindView(R.id.textview_total)
    TextView textview_total;
    //@BindView(R.id.textview_doc_total)
    @SuppressLint("StaticFieldLeak")
    public static TextView textview_doc_total;
    @SuppressLint("StaticFieldLeak")
    public static TextView textview_actual_total;
    @SuppressLint("StaticFieldLeak")
    public static TextView textview_comp_total;


    /*@BindView(R.id.edittext_general_floor_name)
    EditText edittext_general_floor_name;
    @BindView(R.id.edittext_general_comp)
    EditText edittext_general_comp;
    @BindView(R.id.edittext_general_progress)
    EditText edittext_general_progress;
    @BindView(R.id.edittext_general_doc_area)
    EditText edittext_general_doc_area;
    @BindView(R.id.edittext_general_actual_area)
    EditText edittext_general_actual_area;
    @BindView(R.id.edittext_general_age)
    EditText edittext_general_age;
    @BindView(R.id.edittext_general_life)
    EditText edittext_general_life;*/

    @BindView(R.id.textview_internal_composition)
    TextView textview_internal_composition;
    @BindView(R.id.textview_floor_name_composition)
    TextView textview_floor_name_composition;
    @BindView(R.id.textview_hall_dinning)
    TextView textview_hall_dinning;
    @BindView(R.id.textview_kitchen)
    TextView textview_kitchen;
    @BindView(R.id.textview_bedroom)
    TextView textview_bedroom;
    @BindView(R.id.textview_bath)
    TextView textview_bath;
    @BindView(R.id.textview_shop_office)
    TextView textview_shop_office;

    @BindView(R.id.textview_error_measure_land)
    TextView textview_error_measure_land;

    boolean is_offline = false;


    /*   @BindView(R.id.textview_internal_floor_name_composition)
       TextView textview_internal_floor_name_composition;*/
    @BindView(R.id.textview_measurement_doc)
    TextView textview_measurement_doc;
    @BindView(R.id.textview_measurement_act)
    TextView textview_measurement_act;
    @BindView(R.id.image_addFloors)
    ImageView image_addFloors;

    // TODO - Spinner - Address
    //@BindView(R.id.spinner_measurement1)
    @SuppressLint("StaticFieldLeak")
    public static Spinner spinner_measurement1;

    //@BindView(R.id.spinner_measurement2)
    @SuppressLint("StaticFieldLeak")
    public static Spinner spinner_measurement2;
    @BindView(R.id.recyclerview_generalinfo)
    RecyclerView recyclerview_generalinfo;
    @BindView(R.id.recyclerview_internal)
    RecyclerView recyclerview_internal;


    /* san Integration */
    @BindView(R.id.textview_actual_usage)
    TextView textview_actual_usage;

    // actual_usage
    ArrayList<PropertyActualUsage> Inter_floors_list = new ArrayList<>();

    private ArrayList<IndPropertyFloor> list;
    private ArrayList<IndPropertyFloor> floornolist;
    private ArrayList<String> floorusage = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    public static PropertyGeneralFloorAdapter listAdapter;
    @SuppressLint("StaticFieldLeak")
    public static PropertyFloorInternalAdapter propertylistAdapter;
    private IndPropertyFloor stepsModel = new IndPropertyFloor();
    private IndPropertyFloor steps2Model = new IndPropertyFloor();
    public static LinearLayoutManager llm, lfm;
    public OthersFormListener listener;
    public static String measurment_floor_id = "0";
    RotateLoading rotateloading;
    private String msg = "", info = "";

   /* public static EditText editText_showfocus_floor;
    public static EditText editText_showfocus_internal_com;*/

    AppDatabase appDatabase = null;
    int caseid_int = 0;
    public boolean enable_offline_button;

    AdapterView.OnItemSelectedListener listener1;


    // calc
    @BindView(R.id.open_calc_compound)
    ImageView open_calc_compound;
    @BindView(R.id.open_calc_measurment)
    ImageView open_calc_measurment;


    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, button10, buttonC, buttonEqual, button_set, button_close;
    TextView edt1;
    float mValueOne, mValueCurrent;
    boolean mAddition, mSubtract, mMultiplication, mDivision, mClear;

    /***** Declaring Variable *****/
    Button btnClear;
    TextView tvProcessor, tvResult;

    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero;
    String processor;
    Boolean isSmallBracketOpen;
    Button btnMultiply, btnMinus, btnPlus, btnDivide, btnclose, btnsetvalue, btnDecimal, btnBack, btnSmallBracket, btnEqual, btnPercentage;
    int developedCounter;
    static String developedBy = "Atif Naseem";
    static String deveopedNote = "developed in IU, Tue Sep 26, 2017";
    double result_value = 0.0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_building, container, false);
        ButterKnife.bind(this, view);

        /*editText_showfocus_floor = (EditText) view.findViewById(R.id.editText_showfocus_floor);
        editText_showfocus_internal_com = (EditText) view.findViewById(R.id.editText_showfocus_internal_com);*/

        // Room - Declared
        if (MyApplication.getAppContext() != null) {
            appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
        }

        // CaseID to interger
        String caseid_str = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        if (!General.isEmpty(caseid_str)) {
            caseid_int = Integer.parseInt(caseid_str);
        }

        // check the offline module is present or not
        enable_offline_button = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_ENABLE_OFFLINE, false);


        textview_floor_name = (TextView) view.findViewById(R.id.textview_floor_name);
        textview_stage = (TextView) view.findViewById(R.id.textview_stage);
        textview_comp = (TextView) view.findViewById(R.id.textview_comp);
        textview_doc_total = (TextView) view.findViewById(R.id.textview_doc_total);
        textview_actual_total = (TextView) view.findViewById(R.id.textview_actual_total);
        textview_comp_total = (TextView) view.findViewById(R.id.textview_comp_total);
        editText_compound_permissiblearea = (EditText) view.findViewById(R.id.editText_compound_permissiblearea);
        editText_as_per_measurement = (EditText) view.findViewById(R.id.editText_as_per_measurement);
        editText_floors_measu = (EditText) view.findViewById(R.id.editText_floors);
        editText_approved_floors = (EditText) view.findViewById(R.id.editText_approved_floors);
        editText_Floor_details = (EditText) view.findViewById(R.id.editText_Floor_details);
        spinner_measurement1 = (Spinner) view.findViewById(R.id.spinner_measurement1);
        spinner_measurement2 = (Spinner) view.findViewById(R.id.spinner_measurement2);
        rotateloading = (RotateLoading) view.findViewById(R.id.rotateloading);


        //  limit the 2 char after the decimal point
        editText_compound_permissiblearea.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});
        editText_as_per_measurement.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});


        spinner_measurement1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftKeyboard(spinner_measurement1);
                return false;
            }
        });


        initViews();
        initFloorViews();
        initInternalCompositionFloorViews();
        DisplayViewsforPropertyDetail();

        // TODO -  call the mandatory_validation
        if (Singleton.getInstance().enable_validation_error) {
            set_mandatory_building();
        }

        open_calc_compound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*call_calc_function(1);*/
                Calculation_Popup_New(1);
            }
        });

        open_calc_measurment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call_calc_function(2);
                Calculation_Popup_New(2);
            }
        });

        return view;
    }

    public void setBuildingData(OthersFormListener listener, View view) {
        this.listener = listener;
        setPostBuildingData(view);
    }


    private void initViews() {
        general = new General(getActivity());
        SettingsUtils.init(getActivity());

        textview_land_measurement.setTypeface(general.mediumtypeface());
        textview_construction_details.setTypeface(general.mediumtypeface());
        checkbox_is_compounded.setTypeface(general.regulartypeface());
        editText_compound_permissiblearea.setTypeface(general.regulartypeface());

        textview_generl_info_measu.setTypeface(general.mediumtypeface());
        textview_internal_composition.setTypeface(general.mediumtypeface());
        editText_as_per_measurement.setTypeface(general.regulartypeface());
        editText_floors_measu.setTypeface(general.regulartypeface());
        editText_approved_floors.setTypeface(general.regulartypeface());
        editText_Floor_details.setTypeface(general.regulartypeface());

        textview_floor_name.setTypeface(general.regulartypeface());
        textview_stage.setTypeface(general.regulartypeface());
        textview_comp.setTypeface(general.regulartypeface());
        textview_progress.setTypeface(general.regulartypeface());
        textview_doc_area.setTypeface(general.regulartypeface());
        textview_actual_area.setTypeface(general.regulartypeface());
        textview_age.setTypeface(general.regulartypeface());
        textview_life.setTypeface(general.regulartypeface());
        textview_floor_usage.setTypeface(general.regulartypeface());
        textview_average.setTypeface(general.regulartypeface());
        textview_comp_total.setTypeface(general.regulartypeface());
        textview_total.setTypeface(general.regulartypeface());
        textview_doc_total.setTypeface(general.regulartypeface());
        textview_actual_total.setTypeface(general.regulartypeface());
        textview_floor_name_composition.setTypeface(general.regulartypeface());
        textview_hall_dinning.setTypeface(general.regulartypeface());
        textview_kitchen.setTypeface(general.regulartypeface());
        textview_bedroom.setTypeface(general.regulartypeface());
        textview_bath.setTypeface(general.regulartypeface());
        textview_shop_office.setTypeface(general.regulartypeface());
        /*textview_internal_floor_name_composition.setTypeface(general.regulartypeface());*/
        textview_actualusage.setTypeface(general.regulartypeface());
        textview_measurement_doc.setTypeface(general.regulartypeface());
        textview_measurement_act.setTypeface(general.regulartypeface());

        spinnerValuesInitiate();
        addonTextWatcher();

        image_addFloors.setOnClickListener(this);
    }

    private void initFloorViews() {

        //AddTextWatcher(editText_floors_measu);

        list = new ArrayList<>();
        if (Singleton.getInstance().indPropertyFloors != null)
            if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                initEnterFloorNumbersViews(String.valueOf(Singleton.getInstance().indPropertyFloors.size()));
            } else {
                if (list == null || list.size() == 0) {
                    list = new ArrayList<>();
                }
                floorsGeneralRecyclerview();
            }
    }

    private void floorsGeneralRecyclerview() {
        listAdapter = new PropertyGeneralFloorAdapter(list, getActivity());
        llm = new LinearLayoutManager(getActivity());

        //Setting the adapter
        recyclerview_generalinfo.setAdapter(listAdapter);
        recyclerview_generalinfo.setLayoutManager(llm);
    }

    private void initInternalCompositionFloorViews() {

        floornolist = new ArrayList<>();
        if (Singleton.getInstance().indPropertyFloors != null)
            if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                initEnterFloorInternalViews(String.valueOf(Singleton.getInstance().indPropertyFloors.size()));
            } else {
                if (floornolist == null || floornolist.size() == 0) {
                    floornolist = new ArrayList<>();
                }
                floorsInternalRecyclerview();
            }
    }

   /* private void floorsInternalRecyclerview() {
        propertylistAdapter = new PropertyFloorInternalAdapter(floornolist, getActivity());
        lfm = new LinearLayoutManager(getActivity());

        //Setting the adapter
        recyclerview_internal.setAdapter(propertylistAdapter);
        recyclerview_internal.setLayoutManager(lfm);
    }*/

    private void floorsInternalRecyclerview() {
        internalfloorlistAdapter = new InternalFloorNewAdapter(floornolist, getActivity());
        lfm = new LinearLayoutManager(getActivity());
        //Setting the adapter
        recyclerview_internal.setAdapter(internalfloorlistAdapter);
        recyclerview_internal.setLayoutManager(lfm);
    }


    private void initEnterFloorNumbersViews(String floors_no) {
        String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

        if (Singleton.getInstance().indPropertyFloors != null)
            if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                list = new ArrayList<>();
                if (floors_no.equalsIgnoreCase("")) {
                    System.err.print("Floors No: Empty");
                } else {

                    /*******
                     * Show the Floors from API
                     * ******/
                    for (int i = 0; i < Singleton.getInstance().indPropertyFloors.size(); i++) {
                        IndPropertyFloor stepsModel = new IndPropertyFloor();
                        int id = Singleton.getInstance().indPropertyFloors.get(i).getConstructionStageId();
                        String name = Singleton.getInstance().indPropertyFloors.get(i).getFloorName();

                        stepsModel.setCaseId(Integer.valueOf(caseid));
                        stepsModel.setConstructionStageId(Singleton.getInstance().indPropertyFloors.get(i).getConstructionStageId());
                        stepsModel.setFloorName(Singleton.getInstance().indPropertyFloors.get(i).getFloorName());
                        stepsModel.setPercentageCompletion(Singleton.getInstance().indPropertyFloors.get(i).getPercentageCompletion());
                        stepsModel.setDocumentFloorArea(Singleton.getInstance().indPropertyFloors.get(i).getDocumentFloorArea());
                        stepsModel.setMeasuredFloorArea(Singleton.getInstance().indPropertyFloors.get(i).getMeasuredFloorArea());
                        stepsModel.setPropertyAge(Singleton.getInstance().indPropertyFloors.get(i).getPropertyAge());
                        stepsModel.setResidualLife(Singleton.getInstance().indPropertyFloors.get(i).getResidualLife());
                        stepsModel.setPresentCondition(Singleton.getInstance().indPropertyFloors.get(i).getPresentCondition());
                        stepsModel.setFloorNo(Singleton.getInstance().indPropertyFloors.get(i).getFloorNo());

                        String dep = Singleton.getInstance().indPropertyFloors.get(i).getPercentageDepreciation();
                        stepsModel.setPercentageDepreciation(Singleton.getInstance().indPropertyFloors.get(i).getPercentageDepreciation());
                        stepsModel.setPropertyFloorUsageId(Singleton.getInstance().indPropertyFloors.get(i).getPropertyFloorUsageId());

                        stepsModel.setFlatHallNo(Singleton.getInstance().indPropertyFloors.get(i).getFlatHallNo());
                        stepsModel.setFlatKitchenNo(Singleton.getInstance().indPropertyFloors.get(i).getFlatKitchenNo());
                        stepsModel.setFlatBedroomNo(Singleton.getInstance().indPropertyFloors.get(i).getFlatBedroomNo());
                        stepsModel.setFlatBathNo(Singleton.getInstance().indPropertyFloors.get(i).getFlatBathNo());
                        stepsModel.setOfficeNo(Singleton.getInstance().indPropertyFloors.get(i).getOfficeNo());

                        stepsModel.setFlatDinningNo(Singleton.getInstance().indPropertyFloors.get(i).getFlatDinningNo());
                        stepsModel.setFloorName(Singleton.getInstance().indPropertyFloors.get(i).getFloorName());
                        stepsModel.setShopNo(Singleton.getInstance().indPropertyFloors.get(i).getShopNo());

                        floorusage = new ArrayList<>();
                        String value = Singleton.getInstance().indPropertyFloors.get(i).getPropertyFloorUsageId();
                        if (!General.isEmptyString(value)) {
                            if (value.contains(",")) {
                                floorusage.clear();
                                List<String> myList = new ArrayList<String>(Arrays.asList(value.split(",")));
                                floorusage.addAll(myList);
                            }
                        } else {
                            floorusage.clear();
                        }
                        stepsModel.setFloorUsage(floorusage);
                        list.add(stepsModel);

                    }

                    /*******
                     * For Extra Floors from the Current Floors
                     * ******/
                    int floors = Integer.valueOf(floors_no);
                    int previousfloors = Singleton.getInstance().indPropertyFloors.size();
                    if (floors > previousfloors) {
                        int future_floor = floors - previousfloors;
                        for (int i = previousfloors; i < floors; i++) {
                            IndPropertyFloor stepsModel = new IndPropertyFloor();
                            int finalI = i + 1;
                            stepsModel.setCaseId(Integer.valueOf(caseid));
                            stepsModel.setFloorName(getResources().getString(R.string.floorname) + finalI);
                            stepsModel.setFloorUsage(floorusage);
                            list.add(stepsModel);


                            Singleton.getInstance().indPropertyFloors.add(stepsModel);
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
                            if (i < Singleton.getInstance().indPropertyFloors.size()) {
                                Singleton.getInstance().indPropertyFloors.remove(i);
                                i = i - 1;
                                previousfloors = previousfloors - 1;
                            }
                        }
                    }
                }

            } else {

                list = new ArrayList<>();
                if (!floors_no.equalsIgnoreCase("")) {
                    /* New Row adding */
                    int floors = Integer.valueOf(floors_no);
                    for (int i = 0; i < floors; i++) {
                        IndPropertyFloor stepsModel = new IndPropertyFloor();
                        int floorname_dynamic = i + 1;
                        stepsModel.setCaseId(Integer.valueOf(caseid));
                        stepsModel.setFloorName(getResources().getString(R.string.floorname) + floorname_dynamic);
                        stepsModel.setConstructionStageId(0);
                        stepsModel.setFloorUsage(floorusage);
                        list.add(stepsModel);

                        Singleton.getInstance().indPropertyFloors.add(stepsModel);
                    }
                }
            }

        listAdapter = new PropertyGeneralFloorAdapter(list, getActivity());
        llm = new LinearLayoutManager(getActivity());
        //Setting the adapter
        recyclerview_generalinfo.setAdapter(listAdapter);
        recyclerview_generalinfo.setLayoutManager(llm);
    }

    private void initEnterFloorInternalViews(String floors_no) {

        String case_id = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

        if (Singleton.getInstance().indPropertyFloors != null)
            if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                floornolist = new ArrayList<>();
                if (floors_no.equalsIgnoreCase("")) {

                } else {

                    /*******
                     * Show the Floors from API
                     * ******/
                    for (int i = 0; i < Singleton.getInstance().indPropertyFloors.size(); i++) {
                        IndPropertyFloor steps2Model = new IndPropertyFloor();
                        int id = Singleton.getInstance().indPropertyFloors.get(i).getConstructionStageId();
                        String name = Singleton.getInstance().indPropertyFloors.get(i).getFloorName();
                        int caseid = Singleton.getInstance().indPropertyFloors.get(i).getCaseId();
                        Log.e("caseid internal floors:", caseid + "");

                        steps2Model.setCaseId(Integer.valueOf(case_id));
                        steps2Model.setFloorName(Singleton.getInstance().indPropertyFloors.get(i).getFloorName());
                        steps2Model.setFlatHallNo(Singleton.getInstance().indPropertyFloors.get(i).getFlatHallNo());
                        steps2Model.setFlatKitchenNo(Singleton.getInstance().indPropertyFloors.get(i).getFlatKitchenNo());
                        steps2Model.setFlatBedroomNo(Singleton.getInstance().indPropertyFloors.get(i).getFlatBedroomNo());
                        steps2Model.setFlatBathNo(Singleton.getInstance().indPropertyFloors.get(i).getFlatBathNo());
                        steps2Model.setShopNo(Singleton.getInstance().indPropertyFloors.get(i).getShopNo());
                        steps2Model.setOfficeNo(Singleton.getInstance().indPropertyFloors.get(i).getOfficeNo());
                        steps2Model.setFlatDinningNo(Singleton.getInstance().indPropertyFloors.get(i).getFlatDinningNo());

                        floornolist.add(steps2Model);

                        Singleton.getInstance().floorFromBackend = true;
                    }

                    /*******
                     * For Extra Floors from the Current Floors
                     * ******/
                    int floors = Integer.valueOf(floors_no);
                    int previousfloors = Singleton.getInstance().indPropertyFloors.size();
                    if (floors > previousfloors) {
                        int future_floor = floors - previousfloors;
                        for (int i = previousfloors; i < floors; i++) {
                            IndPropertyFloor steps2Model = new IndPropertyFloor();
                            steps2Model.setCaseId(Integer.valueOf(case_id));
                            steps2Model.setFloorName(getResources().getString(R.string.floorname) + i + 1);
                            floornolist.add(steps2Model);
                        }
                    } else {
                        /*******
                         * For Floors Delete from the Current Floors as per the floor no
                         * ******/
                        int delete_floors = previousfloors - floors;
                        for (int i = floors; i < previousfloors; i++) {
                            if (i < floornolist.size()) {
                                floornolist.remove(i);
                                i = i - 1;
                                previousfloors = previousfloors - 1;
                            }
                        }
                      /*  int delete_floors = previousfloors - floors;
                        for (int i = floors; i < previousfloors && i >= 0; i--) {
                            floornolist.remove(i);
                        }*/
                    }
                }


            } else {
                floornolist = new ArrayList<>();
                if (!floors_no.equalsIgnoreCase("")) {
                    /* New Row adding */
                    int floors = Integer.valueOf(floors_no);
                    for (int i = 0; i < floors; i++) {
                        IndPropertyFloor steps2Model = new IndPropertyFloor();
                        int floorname_dynamic = i + 1;
                        steps2Model.setCaseId(Integer.valueOf(case_id));
                        steps2Model.setFloorName(getResources().getString(R.string.floorname) + floorname_dynamic);
                        floornolist.add(steps2Model);
                        Singleton.getInstance().floorFromBackend = false;
                    }
                }
            }

        internalfloorlistAdapter = new InternalFloorNewAdapter(floornolist, getActivity());
        lfm = new LinearLayoutManager(getActivity());
        //Setting the adapter
        recyclerview_internal.setAdapter(internalfloorlistAdapter);
        recyclerview_internal.setLayoutManager(lfm);
    }

    private void setFloorNoInternals(ArrayList<InternalFloorModel> internalFloorModels) {
        internalFloorModels.clear();
        InternalFloorModel floorModelw = new InternalFloorModel();
        floorModelw.setId(0); //-1
        floorModelw.setName("Select");
        internalFloorModels.add(floorModelw);
        for (int i = 1; i <= 11; i++) {
            InternalFloorModel floorModel = new InternalFloorModel();
            floorModel.setId(i);
            int setId = i - 1;
            floorModel.setName("" + setId);
            floorModel.setFloorid(setId);
            internalFloorModels.add(floorModel);
        }
    }

    /*private void AddTextWatcher(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Set Approval floor
                if (!general.isEmpty(charSequence.toString())) {
                    editText_approved_floors.setText(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }*/

    public void checkValidationforFloors() {

        image_addFloors.setVisibility(View.INVISIBLE);
        if (rotateloading != null) {
            rotateloading.start();
        }

        if (!general.isEmpty(editText_floors_measu.getText().toString())) {

            if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getNoOfFloors()))) {
                if (Singleton.getInstance().indProperty.getNoOfFloors() == 0) {
                    // TODO - If floor is created from first time (Start Inspection)
                    Singleton.getInstance().is_new_floor_created = true;
                } else {
                    // TODO - If floor is created form second time (Edit Inspection)
                    Singleton.getInstance().is_new_floor_created = false;
                }
            } else {
                Singleton.getInstance().is_new_floor_created = false;
            }


            // check offline or not
            is_offline = SettingsUtils.getInstance().getValue(SettingsUtils.is_offline, false);
            if (is_offline) {
                // offline
                Singleton.getInstance().is_new_floor_created = true;
            }


            /*******
             * Check calculation for the manual floorno textbox and generated floors dynamic list
             * ******/
            final String floorno_textbox = editText_floors_measu.getText().toString().trim();
            int floorsnoList = Singleton.getInstance().indProperty.getNoOfFloors();

            if (!general.isEmpty(floorno_textbox)) {
                int floorno = Integer.valueOf(floorno_textbox);

                if (floorsnoList != 0) {
                    if (floorno == floorsnoList) {
                        //Todo floors manual entry and its generate list equals same
                        Singleton.getInstance().floorsEntryList = true;
                    } else if (floorno < floorsnoList) {
                        //Todo floors manual entry and it has to delete from floorlist
                        Singleton.getInstance().floorsEntryList = false;
                        Singleton.getInstance().floorsDeleteList = true;
                    } else if (floorno > floorsnoList) {
                        //Todo floors manual entry and its generate list has to add from floorlist
                        Singleton.getInstance().floorsEntryList = false;
                        Singleton.getInstance().floorsDeleteList = false;
                    }
                } else {
                    if (floorno == floorsnoList) {
                        //Todo floors manual entry and its generate list equals same
                        Singleton.getInstance().floorsEntryList = true;
                    } else {
                        // Todo floors is empty then make initial as false for start inspection
                        Singleton.getInstance().floorsEntryList = false;
                        Singleton.getInstance().floorsDeleteList = false;
                    }
                }
            }


            /*******
             * User Floor Number equals to Floors List
             * ******/
//            if (Singleton.getInstance().floorsEntryList) {
//                Snackbar.make(editText_floors_measu, getResources().getString(R.string.samefloornumbers), Snackbar.LENGTH_SHORT).show();
//                hideSoftKeyboard(image_addFloors);
//                image_addFloors.setVisibility(View.VISIBLE);
//                if (rotateloading != null) {
//                    rotateloading.stop();
//                }
//            } else {

                /*****
                 * User Floor Number and Floors List calls for Delete Method
                 * *****/
                if (Singleton.getInstance().floorsDeleteList) {
                    Snackbar.make(editText_floors_measu, getResources().getString(R.string.deleting_floors), Snackbar.LENGTH_SHORT).show();
                } else {
                    /******
                     * User Floor Number and Floors List calls for Add Method
                     * *****/
                    Snackbar.make(editText_floors_measu, getResources().getString(R.string.genearating_floors), Snackbar.LENGTH_SHORT).show();
                    // Set as true for Internal composition
                    Singleton.getInstance().is_edit_floor_satge = true;
                    Singleton.getInstance().is_edit_floor_docarea = true;
                    Singleton.getInstance().is_edit_floor_age = true;
                    // Set as true for Internal composition
                    Singleton.getInstance().is_edit_floor_hall = true;
                    Singleton.getInstance().is_edit_floor_kitchen = true;
                    Singleton.getInstance().is_edit_floor_bedroom = true;
                    Singleton.getInstance().is_edit_floor_bath = true;
                    Singleton.getInstance().is_edit_floor_shop = true;
                }

                editText_floors_measu.setError(null);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        if (!Singleton.getInstance().is_new_floor_created) {
                            // Edit case
                            if (Singleton.getInstance().floorsDeleteList) {
                                // Delete case
                                if (Connectivity.isConnected(getActivity())) {
                                    int floorno = Integer.valueOf(floorno_textbox);
                                    Singleton.getInstance().indProperty.setNoOfFloors(floorno);
                                    String floorsno = editText_floors_measu.getText().toString().trim();
                                    initEnterFloorNumbersViews(floorsno);
                                    initEnterFloorInternalViews(floorsno);
                                    FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                                    fragmentValuationBuilding.getValuationDynamicFloorsResult(floorsno);
                                    // Todo Delete Floors as per the floor numbers in edit Inspection
                                    InitiateDeleteFloorsWebservice();
                                } else {
                                    internet_check_box("edit_inspec");
                                   /* Connectivity.showNoConnectionDialog(getActivity());
                                    general.hideloading();*/
                                }
                            } else {
                                // Add case
                                int floorno = Integer.valueOf(floorno_textbox);
                                Singleton.getInstance().indProperty.setNoOfFloors(floorno);
                                String floorsno = editText_floors_measu.getText().toString().trim();
                                initEnterFloorNumbersViews(floorsno);
                                initEnterFloorInternalViews(floorsno);
                                FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                                fragmentValuationBuilding.getValuationDynamicFloorsResult(floorsno);
                                NotifyDataSetChangedfloors();
                            }

                        } else {
                            // new case - // Case refresh
                            int floorno = Integer.valueOf(floorno_textbox);
                            Singleton.getInstance().indProperty.setNoOfFloors(floorno);
                            String floorsno = editText_floors_measu.getText().toString().trim();
                            initEnterFloorNumbersViews(floorsno);
                            initEnterFloorInternalViews(floorsno);
                            FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                            fragmentValuationBuilding.getValuationDynamicFloorsResult(floorsno);
                            NotifyDataSetChangedfloors();
                        }


                    }
                }, 200);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Singleton isnewfloorcreate true
                        //if (Singleton.getInstance().is_new_floor_created) {
                        hideSoftKeyboard(image_addFloors);
                        image_addFloors.setVisibility(View.VISIBLE);
                        if (rotateloading != null) {
                            rotateloading.stop();
                        }
                        //}
                    }
                }, 3000);
//            }

        } else {
            editText_floors_measu.setError(getResources().getString(R.string.err_no_floor));
            image_addFloors.setVisibility(View.VISIBLE);
            if (rotateloading != null) {
                rotateloading.stop();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void NotifyDataSetChangedfloors() {

        if (Singleton.getInstance().indPropertyFloors != null) {
            if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                int average_total = general.getCompletedSumValue(Singleton.getInstance().indPropertyFloors);
                if (FragmentBuilding.textview_comp_total != null) {
                    FragmentBuilding.textview_comp_total.setText("" + average_total);
                    // set Aspercom
                    FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                    fragmentValuationBuilding.aspercompletion_val("" + average_total);
                }

                // Total Doc Area
                int total_documentarea = general.getDocSumValue(Singleton.getInstance().indPropertyFloors);
                float total_documentarea_float = general.getDocSumValue_float(Singleton.getInstance().indPropertyFloors);
                //FragmentValuationBuilding fragmentValuationBuilding_is = new FragmentValuationBuilding();
                if (FragmentBuilding.textview_doc_total != null) {
                    //FragmentBuilding.textview_doc_total.setText("" + total_documentarea);
                    FragmentBuilding.textview_doc_total.setText("" + total_documentarea_float);
                    FragmentValuationBuilding.constrction_measurment(String.valueOf(total_documentarea));
                } else {
                    FragmentValuationBuilding.constrction_measurment("");
                }

                // Total Doc Area
                int total_actualarea = general.getMeasureSumValue(Singleton.getInstance().indPropertyFloors);
                float total_actualarea_float = general.getMeasureSumValue_float(Singleton.getInstance().indPropertyFloors);
                //FragmentValuationBuilding fragmentValuationBuilding_is = new FragmentValuationBuilding();
                if (FragmentBuilding.textview_actual_total != null) {
                    //FragmentBuilding.textview_actual_total.setText("" + total_actualarea);
                    FragmentBuilding.textview_actual_total.setText("" + total_actualarea_float);
                    FragmentValuationBuilding.constrction_actual(String.valueOf(total_actualarea));
                } else {
                    FragmentValuationBuilding.constrction_actual("");
                }
            }
        }
    }

    private void hideSoftKeyboard(View addkeys) {
        if ((addkeys != null) && (getActivity() != null)) {
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
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

    public boolean checkValidationonSave() {
        boolean checkvalidation = true;

        // compound_permissiblearea
        String comp_permissiblearea = editText_compound_permissiblearea.getText().toString();
        if (!general.isEmpty(comp_permissiblearea)) {
            editText_compound_permissiblearea.setError(null);
        } else {
            editText_compound_permissiblearea.requestFocus();
            editText_compound_permissiblearea.setError(getResources().getString(R.string.err_comp_permis));
            checkvalidation = false;
        }
        // as_per_measurement
        String aspermeasurement = editText_as_per_measurement.getText().toString();
        if (!general.isEmpty(aspermeasurement)) {
            editText_as_per_measurement.setError(null);
        } else {
            editText_as_per_measurement.requestFocus();
            editText_as_per_measurement.setError(getResources().getString(R.string.err_aspermeasu));
            checkvalidation = false;
        }

        // Nooffloors
        String floor_no = editText_floors_measu.getText().toString();
        if (!general.isEmpty(floor_no)) {
            editText_floors_measu.setError(null);

            int measurementland = spinner_measurement1.getSelectedItemPosition();
            int measurementconstruction = spinner_measurement2.getSelectedItemPosition();
            if (measurementland == 0) {
                // measurementland
                editText_floors_measu.requestFocus();
                ((TextView) spinner_measurement1.getSelectedView()).setError(getResources().getString(R.string.err_measure_land));
                general.CustomToast(getResources().getString(R.string.err_measure_land));
                checkvalidation = false;
            } else if (measurementconstruction == 0) {
                // measurementconstruction
                editText_floors_measu.requestFocus();
                ((TextView) spinner_measurement2.getSelectedView()).setError(getResources().getString(R.string.err_measure_construct));
                general.CustomToast(getResources().getString(R.string.err_measure_construct));
                checkvalidation = false;
            } else {
                // Check all values
                ArrayList<IndPropertyFloor> floor_list = new ArrayList<>();
                floor_list = FragmentBuilding.listAdapter.getStepList();
                if (floor_list.size() == 0) {
                    // Kindly Generate your Floors
                    editText_floors_measu.requestFocus();
                    general.CustomToast(getResources().getString(R.string.err_msg_genera_floors));
                    checkvalidation = false;
                } else {
                    boolean is_floor_name_filled = true;
                    // Check with Floor name
                    for (int j = 0; j < floor_list.size(); j++) {
                        String floorName = floor_list.get(j).getFloorName();
                        if (general.isEmpty(floorName)) {
                            // If floor name is empty
                            editText_floors_measu.requestFocus();
                            textview_floor_name.setError(getResources().getString(R.string.err_msg_enter_floor_name));
                            general.CustomToast(getResources().getString(R.string.err_msg_enter_floor_name));
                            checkvalidation = false;
                            is_floor_name_filled = false;
                        }
                    }

                    if (is_floor_name_filled) {
                        // If Floor name is all filled
                        boolean is_constructionStageId_filled = true;
                        // Check with Floor ConstructionStageId
                        for (int j = 0; j < floor_list.size(); j++) {
                            int constructionStageId = floor_list.get(j).getConstructionStageId();
                            if (constructionStageId == 0) {
                                // If constructionStageId is empty
                                editText_floors_measu.requestFocus();
                                textview_floor_name.setError(null);
                                textview_stage.setError(getResources().getString(R.string.err_msg_stagetype));
                                general.CustomToast(getResources().getString(R.string.err_msg_stagetype));
                                checkvalidation = false;
                                is_constructionStageId_filled = false;
                            }
                        }
                        // If ConstructionStageId is all filled
                        boolean is_PercentageCompletion_filled = true;
                        // If ConstructionStageId is all filled
                        if (is_constructionStageId_filled) {
                            // Check with Floor PercentageCompletion
                            for (int j = 0; j < floor_list.size(); j++) {
                                String percentageCompletion = String.valueOf(floor_list.get(j).getPercentageCompletion());
                                if (general.isEmpty(percentageCompletion)) {
                                    // If PercentageCompletion is empty
                                    editText_floors_measu.requestFocus();
                                    textview_floor_name.setError(null);
                                    textview_stage.setError(null);
                                    textview_comp.setError(getResources().getString(R.string.err_msg_percentage_completion));
                                    general.CustomToast(getResources().getString(R.string.err_msg_percentage_completion));
                                    checkvalidation = false;
                                    is_PercentageCompletion_filled = false;
                                }
                            }
                            if (is_PercentageCompletion_filled) {
                                if (!floor_no.equalsIgnoreCase(String.valueOf(floor_list.size()))) {
                                    editText_floors_measu.requestFocus();
                                    textview_floor_name.setError(null);
                                    textview_stage.setError(null);
                                    textview_comp.setError(null);
                                    editText_floors_measu.requestFocus();
                                    general.CustomToast(getResources().getString(R.string.err_genea_floors_check));
                                    checkvalidation = false;
                                }
                            }
                            // If true clear the error
                            if (checkvalidation) {
                                textview_floor_name.setError(null);
                                textview_stage.setError(null);
                                textview_comp.setError(null);
                            }
                        }
                    }

                }
            }
        } else {
            editText_floors_measu.requestFocus();
            editText_floors_measu.setError(getResources().getString(R.string.err_no_floor));
            checkvalidation = false;
        }

        return checkvalidation;
    }

    public boolean checkValidationonSave_nonmandatory() {
        boolean checkvalidation = true;

        // Nooffloors
        String floor_no = editText_floors_measu.getText().toString();
        if (!general.isEmpty(floor_no)) {
            editText_floors_measu.setError(null);
            // Check all values
            ArrayList<IndPropertyFloor> floor_list = new ArrayList<>();
            floor_list = FragmentBuilding.listAdapter.getStepList();
            if (floor_list.size() == 0) {
                // Kindly Generate your Floors
                editText_floors_measu.requestFocus();
                general.CustomToast(getResources().getString(R.string.err_msg_genera_floors));
                checkvalidation = false;
            } else {
                if (!floor_no.equalsIgnoreCase(String.valueOf(floor_list.size()))) {
                    editText_floors_measu.requestFocus();
                    textview_floor_name.setError(null);
                    textview_stage.setError(null);
                    textview_comp.setError(null);
                    editText_floors_measu.requestFocus();
                    general.CustomToast(getResources().getString(R.string.err_genea_floors_check));
                    checkvalidation = false;
                }
            }
        } else {
            editText_floors_measu.requestFocus();
            editText_floors_measu.setError(getResources().getString(R.string.err_no_floor));
            checkvalidation = false;
        }

        return checkvalidation;
    }

    private void set_mandatory_building() {

        // compound_permissiblearea
        String comp_permissiblearea = editText_compound_permissiblearea.getText().toString();
        if (!general.isEmpty(comp_permissiblearea)) {
            editText_compound_permissiblearea.setError(null);
        } else {
            editText_compound_permissiblearea.requestFocus();
            editText_compound_permissiblearea.setError(getResources().getString(R.string.err_comp_permis));
        }
        // as_per_measurement
        String aspermeasurement = editText_as_per_measurement.getText().toString();
        if (!general.isEmpty(aspermeasurement)) {
            editText_as_per_measurement.setError(null);
        } else {
            editText_as_per_measurement.requestFocus();
            editText_as_per_measurement.setError(getResources().getString(R.string.err_aspermeasu));
        }

        // Nooffloors
        String floor_no = editText_floors_measu.getText().toString();
        if (!general.isEmpty(floor_no)) {
            editText_floors_measu.setError(null);

            int measurementland = spinner_measurement1.getSelectedItemPosition();
            //int measurementconstruction = spinner_measurement2.getSelectedItemPosition();
            if (measurementland == 0) {
                // measurementland
                editText_floors_measu.requestFocus();

                textview_error_measure_land.setVisibility(View.VISIBLE);
                //((TextView) spinner_measurement1.getSelectedView()).setError(getResources().getString(R.string.err_measure_land));
                //general.CustomToast(getResources().getString(R.string.err_measure_land));
            }

            // Check all values
            ArrayList<IndPropertyFloor> floor_list = new ArrayList<>();
            floor_list = FragmentBuilding.listAdapter.getStepList();
            if (floor_list.size() == 0) {
                // Kindly Generate your Floors
                editText_floors_measu.requestFocus();
                //general.CustomToast(getResources().getString(R.string.err_msg_genera_floors));
            } else {
                // Check with Floor name
                for (int j = 0; j < floor_list.size(); j++) {
                    String floorName = floor_list.get(j).getFloorName();
                    if (general.isEmpty(floorName)) {
                        // If floor name is empty
                        editText_floors_measu.requestFocus();
                        textview_floor_name.setError(getResources().getString(R.string.err_msg_enter_floor_name));
                        //general.CustomToast(getResources().getString(R.string.err_msg_enter_floor_name));
                    }

                    int constructionStageId = floor_list.get(j).getConstructionStageId();
                    if (constructionStageId == 0) {
                        // If constructionStageId is empty
                        editText_floors_measu.requestFocus();
                        textview_stage.setError(getResources().getString(R.string.err_msg_stagetype));
                    }

                    String percentageCompletion = String.valueOf(floor_list.get(j).getPercentageCompletion());
                    if (general.isEmpty(percentageCompletion)) {
                        // If PercentageCompletion is empty
                        editText_floors_measu.requestFocus();
                        textview_comp.setError(getResources().getString(R.string.err_msg_percentage_completion));
                    }
                }

            }
        } else {
            editText_floors_measu.requestFocus();
            editText_floors_measu.setError(getResources().getString(R.string.err_no_floor));
        }
    }

    private void spinnerValuesInitiate() {

        ArrayAdapter<Measurements> adapterMeasurements = new ArrayAdapter<>(getActivity(),
                R.layout.row_spinner_item, Singleton.getInstance().measurements_list);
        adapterMeasurements.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_measurement1.setAdapter(adapterMeasurements);

        listener1 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                int measurementland = spinner_measurement1.getSelectedItemPosition();
                if (measurementland != 0) {
                    textview_error_measure_land.setVisibility(View.GONE);
                }


                Singleton.getInstance().indProperty.setDocumentLandAreaUnit(Singleton.getInstance().measurements_list.get(position).getMeasureUnitId());
                Singleton.getInstance().indProperty.setMeasuredLandAreaUnit(Singleton.getInstance().measurements_list.get(position).getMeasureUnitId());

                FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                fragmentValuationBuilding.get_measurment_land(Singleton.getInstance().measurements_list.get(position).getMeasureUnitId(), Singleton.getInstance().measurements_list.get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        spinner_measurement1.setOnItemSelectedListener(listener1);



        /* san Integration */
        Singleton.getInstance().PropertyActualUsage_id.clear();
        Singleton.getInstance().PropertyActualUsage_name.clear();
        textview_actual_usage.setTypeface(general.regulartypeface());
        function_actual_usage();
        textview_actual_usage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog_dynamic("actual_usage");
                hideSoftKeyboard(textview_actual_usage);
            }
        });
        /* san Integration */

    }

    private void addonTextWatcher() {
        /*editText_compound_permissiblearea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editText_as_per_measurement.setText(charSequence.toString());
                editText_as_per_measurement.setError(null);
                //TODO Interface
                FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                fragmentValuationBuilding.permission_measurment(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });*/

        editText_compound_permissiblearea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String charSequence = editText_compound_permissiblearea.getText().toString();
                    if (general.isEmpty(editText_as_per_measurement.getText().toString())) {
                        // If its empty
                        editText_as_per_measurement.setText(charSequence);
                        //TODO Interface
                        FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                        fragmentValuationBuilding.actual_measurment(charSequence);
                    }

                    editText_as_per_measurement.setError(null);
                    //TODO Interface
                    FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                    fragmentValuationBuilding.permission_measurment(charSequence);
                }
            }
        });

        editText_as_per_measurement.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //TODO Interface
                FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                fragmentValuationBuilding.actual_measurment(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        editText_floors_measu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String charSequence = editText_floors_measu.getText().toString();
                    if (general.isEmpty(editText_approved_floors.getText().toString())) {
                        // If its empty - Set Approval floor
                        editText_approved_floors.setText(charSequence);
                    }
                }
            }
        });

        editText_floors_measu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String strEnteredVal = charSequence.toString();
                if (!general.isEmpty(strEnteredVal)) {
                    int num = Integer.parseInt(strEnteredVal);
                    if (num > 12) {
                        general.CustomToast(getResources().getString(R.string.only_12floor));
                        editText_floors_measu.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void DisplayViewsforPropertyDetail() {

        if (Singleton.getInstance().indProperty != null) {

            //  Todo set the values for editext

            if (Singleton.getInstance().indProperty.getNoOfFloors() != 0) {
                editText_floors_measu.setText("" + Singleton.getInstance().indProperty.getNoOfFloors());
            } else {
                Log.e("Floor No.s text", "has null value");
            }

            if (Singleton.getInstance().indProperty.getApprovedNoOfFloors() != 0)
                editText_approved_floors.setText("" + Singleton.getInstance().indProperty.getApprovedNoOfFloors());
            if (!general.isEmpty(Singleton.getInstance().indProperty.getDocumentLandArea()))
                editText_compound_permissiblearea.setText(Singleton.getInstance().indProperty.getDocumentLandArea());
            if (!general.isEmpty(Singleton.getInstance().indProperty.getMeasuredLandArea()))
                editText_as_per_measurement.setText(Singleton.getInstance().indProperty.getMeasuredLandArea());
            if (!general.isEmpty(Singleton.getInstance().indProperty.getFloorDetails()))
                editText_Floor_details.setText(Singleton.getInstance().indProperty.getFloorDetails());



            int areaUnit = Singleton.getInstance().indProperty.getMeasuredLandAreaUnit();
            if (areaUnit != 0)
                spinner_measurement1.setSelection(areaUnit);


            spinner_measurement1.post(new Runnable() {
                @Override
                public void run() {
                    spinner_measurement1.setSelection(areaUnit);
                }
            });
            /********
             *  TODO Address Spinner values display
             * ********/

            // Method 1 - Dropdown values
            /*if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                if (!general.isEmpty(Singleton.getInstance().indPropertyFloors.get(0).getDocumentFloorAreaUnit())) {
                    int docareaUnit = Integer.parseInt(Singleton.getInstance().indPropertyFloors.get(0).getDocumentFloorAreaUnit());
                    if (docareaUnit != 0) {
                        measurment_floor_id = Singleton.getInstance().indPropertyFloors.get(0).getDocumentFloorAreaUnit();
                        spinner_measurement2.setSelection(docareaUnit);
                    }
                }
            }*/

            // Method 2 - constant sq.ft fixed
          /*  spinner_measurement2.setEnabled(false);
            spinner_measurement2.setClickable(false);
            spinner_measurement2.setSelection(1);*/
            if (Singleton.getInstance().measurements_list.size() > 0) {
                measurment_floor_id = String.valueOf(Singleton.getInstance().measurements_list.get(1).getMeasureUnitId());
                FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                fragmentValuationBuilding.get_constrction_land(Singleton.getInstance().measurements_list.get(1).getMeasureUnitId());
            }


            // TODO CheckBox - iscompounded
            if (Singleton.getInstance().indProperty.getIsCompounded() != null)
                checkbox_is_compounded.setChecked(Singleton.getInstance().indProperty.getIsCompounded());

            /*
            // Todo average result

            getDocSumValue(Singleton.getInstance().indProperty);*/
        }

    }

    public void setPostBuildingData(View view) {

        if (view != null)
            if (Singleton.getInstance().indProperty != null) {
                String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
                Singleton.getInstance().indProperty.setCaseId(Integer.valueOf(caseid));
                Singleton.getInstance().indProperty.setFloorDetails("" + editText_Floor_details.getText().toString());
                Singleton.getInstance().indProperty.setMeasuredLandArea("" + editText_as_per_measurement.getText().toString());
                Singleton.getInstance().indProperty.setDocumentLandArea("" + editText_compound_permissiblearea.getText().toString());
                if (!general.isEmpty(editText_approved_floors.getText().toString())) {
                    Singleton.getInstance().indProperty.setApprovedNoOfFloors(Integer.valueOf(editText_approved_floors.getText().toString()));
                }
                if (!general.isEmpty(editText_floors_measu.getText().toString())) {
                    Log.e("floors_measu_data", "if: " + Integer.valueOf(editText_floors_measu.getText().toString()));
                    Singleton.getInstance().indProperty.setNoOfFloors(Integer.valueOf(editText_floors_measu.getText().toString()));
                } else {
                    Log.e("floors_measu_data", "else: empty");
                }
                Singleton.getInstance().indProperty.setAvgPercentageCompletion(textview_comp_total.getText().toString());
                Singleton.getInstance().indProperty.setDocumentFloorAreaTotal(textview_doc_total.getText().toString());
                Singleton.getInstance().indProperty.setMeasuredFloorAreaTotal(textview_actual_total.getText().toString());

                /*  *************
                 * CheckBox values view
                 * *********************/
                boolean check_compound = checkbox_is_compounded.isChecked();
                Singleton.getInstance().indProperty.setIsCompounded(check_compound);

                /* *************
                 * Get Spinner single select value
                 * *********************/
                int pos = spinner_measurement1.getSelectedItemPosition();
                if (pos >= 0) {
                    int measureUnitId = Singleton.getInstance().measurements_list.get(pos).getMeasureUnitId();
                    if (measureUnitId != 0) {
                        Singleton.getInstance().indProperty.setDocumentLandAreaUnit(measureUnitId);
                        Singleton.getInstance().indProperty.setMeasuredLandAreaUnit(measureUnitId);
                    }
                }
            }

        listener.getBuildingData(Singleton.getInstance().indProperty);
    }

    private void function_actual_usage() {
        // clear the array and set the floor list in array
        Inter_floors_list = new ArrayList<>();
        Inter_floors_list = Singleton.getInstance().propertyActualUsages_list;
        // check Floor Dropdown is empty
        if (Inter_floors_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().indProperty.getPropertyActualUsageId();
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> Inter_floors_list_selected_id_commo = new ArrayList<>();
                Inter_floors_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (Inter_floors_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < Inter_floors_list.size(); x++) {
                        if (Inter_floors_list_selected_id_commo.toString().contains("" + Inter_floors_list.get(x).getPropertyActualUsageId())) {
                            for (int y = 0; y < Inter_floors_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(Inter_floors_list_selected_id_commo.get(y));
                                if (Inter_floors_list.get(x).getPropertyActualUsageId() == one_by_one_id) {
                                    Singleton.getInstance().PropertyActualUsage_id.add(Inter_floors_list.get(x).getPropertyActualUsageId());
                                    Singleton.getInstance().PropertyActualUsage_name.add(Inter_floors_list.get(x).getName());
                                }
                            }
                            textview_actual_usage.setText(general.remove_array_brac_and_space(Singleton.getInstance().PropertyActualUsage_name.toString()));
                        }
                    }
                }
            } else {
                textview_actual_usage.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void showdialog_dynamic(final String type_) {
        final String type_of_dialog = type_;

        @SuppressLint("InflateParams") View view = LayoutInflater.from(getContext()).inflate(R.layout.multiselect_checkbox, null);
        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(getContext());
        alertdialogBuilder.setView(view);
        final AlertDialog alertDialog = alertdialogBuilder.show();
        alertDialog.setCancelable(false);

        TextView textview_heading = (TextView) alertDialog.findViewById(R.id.textview_heading);
        Button btn_cancel = (Button) alertDialog.findViewById(R.id.btn_cancel);
        Button btn_save = (Button) alertDialog.findViewById(R.id.btn_save);
        RecyclerView recyclerview_dialog = (RecyclerView) alertDialog.findViewById(R.id.recyclerview_dialog);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerview_dialog.setLayoutManager(linearLayoutManager);

        if (type_of_dialog.equalsIgnoreCase("actual_usage")) {
            // Type -> actual_usage
            textview_heading.setText(getResources().getString(R.string.actualusage));
            String selectedId = Singleton.getInstance().indProperty.getPropertyActualUsageId();
            PropertyActualUsageAdapter propertyActualUsageAdapter = new PropertyActualUsageAdapter(getActivity(), Inter_floors_list, selectedId);
            recyclerview_dialog.setAdapter(propertyActualUsageAdapter);
        }


        textview_heading.setTypeface(general.mediumtypeface());

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type_of_dialog.equalsIgnoreCase("actual_usage")) {
                    // Type -> Floor
                    if (Singleton.getInstance().PropertyActualUsage_id.size() > 0) {
                        String PropertyActualUsage_id = general.remove_array_brac_and_space(Singleton.getInstance().PropertyActualUsage_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().indProperty.setPropertyActualUsageId(PropertyActualUsage_id);
                        // setText to the floor text
                        String PropertyActualUsage_name = general.remove_array_brac_and_space(Singleton.getInstance().PropertyActualUsage_name.toString());
                        textview_actual_usage.setText(PropertyActualUsage_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().indProperty.setPropertyActualUsageId("");
                        textview_actual_usage.setText(getResources().getString(R.string.select));
                    }
                    Log.e("PropertyActualUsage_id", "::: " + Singleton.getInstance().PropertyActualUsage_id);
                    Log.e("PropertyActuaUs_name", ":: " + Singleton.getInstance().PropertyActualUsage_name);
                }

                alertDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_addFloors:
                checkValidationforFloors();
                break;
        }
    }


    /*******
     * Todo Delete Floors API Call in Edit Inspection
     * Suganya Integration
     * ******/
    private void InitiateDeleteFloorsWebservice() {
        if (Connectivity.isConnected(getActivity())) {
            general.showloading(getActivity());
            InitiateDeleteFloorsTask();
        } else {
            general.hideloading();
            Connectivity.showNoConnectionDialog(getActivity());
        }
    }

    private void InitiateDeleteFloorsTask() {

        /*JSONArray IndPropertyFloorsObj = null, IndPropertyFloorsValuationObj = null;
        JSONObject mainObj = new JSONObject();
        try {
            Gson gson = new Gson();

            String IndPropertyfloorJsonObj = gson.toJson(Singleton.getInstance().indPropertyFloors);
            IndPropertyFloorsObj = new JSONArray(IndPropertyfloorJsonObj);

            String IndPropertyfloorValuationJsonObj = gson.toJson(Singleton.getInstance().indPropertyFloorsValuations);
            IndPropertyFloorsValuationObj = new JSONArray(IndPropertyfloorValuationJsonObj);

            *//* mainObj.put("DYNAMICFLOOR", IndPropertyFloorsObj);
             mainObj.put("INDFLOORVALUATION", IndPropertyFloorsValuationObj);
             mainObj.put("NOOFFLOOR", editText_floors_measu.getText().toString().trim());*//*

        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        LinkedTreeMap<String,Object> deleteMap = new LinkedTreeMap<>();
        deleteMap.put("DYNAMICFLOOR", Singleton.getInstance().indPropertyFloors);
        deleteMap.put("INDFLOORVALUATION", Singleton.getInstance().indPropertyFloorsValuations);
        deleteMap.put("NOOFFLOOR", editText_floors_measu.getText().toString().trim());


        String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        String url = general.ApiBaseUrl() + SettingsUtils.DeleteFloors;

        JsonRequestData requestUrlData = new JsonRequestData();
        requestUrlData.setInitQueryUrl(url);
        requestUrlData.setCaseId(caseid);

        JsonRequestData requestData = new JsonRequestData();
        requestData.setUrl(RequestParam.DeleteFloorRequestParams(requestUrlData));
        requestData.setMainJson(new Gson().toJson(deleteMap));
        requestData.setRequestBody(RequestParam.SaveCaseInspectionRequestParams(requestData));

        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(getActivity(), requestData, SettingsUtils.PUT);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                String response = requestData.getResponse();
                parseDeleteFloorsDataResponse(response);
            }
        });
        webserviceTask.execute();
    }

    private void parseDeleteFloorsDataResponse(String response) {

        DataResponse dataResponse = ResponseParser.parseSaveCaseInspectionResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
        } else {
            general.hideloading();
            general.CustomToast(getResources().getString(R.string.serverProblem));
        }

        if (result != null) {
            if (result.equals("1")) {
                // general.CustomToast(msg);

                general.hideloading();

                hideSoftKeyboard(image_addFloors);
                image_addFloors.setVisibility(View.VISIBLE);
                if (rotateloading != null) {
                    rotateloading.stop();
                }

                Singleton.getInstance().floorsEntryList = false;
                Singleton.getInstance().floorsDeleteList = false;

                NotifyDataSetChangedfloors();
            } else if (result.equals("2")) {
                general.CustomToast(msg);
            } else if (result.equals("0")) {
                general.CustomToast(msg);
            }
        } else {
            general.hideloading();
            general.CustomToast(getResources().getString(R.string.serverProblem));
        }
    }


    public void internet_check_box(final String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.AlertDialogCustom));
        builder.setTitle("Network Information");
        builder.setMessage("Please check your Internet connection");


        if (enable_offline_button) {
            // Show offline Button
            builder.setNeutralButton("Save offline", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (str.equalsIgnoreCase("start_inspec")) {
                        // TODO - Start_inspec - Add the offline case to the modal
                    } else {
                        // TODO - Edit_inspec - Add the offline case to the modal
                        // set as offline
                        SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, true);
                        is_offline = SettingsUtils.getInstance().getValue(SettingsUtils.is_offline, false);

                        int check_offline_data = appDatabase.interfaceOfflineDataModelQuery().get_case_list("" + Singleton.getInstance().aCase.getCaseId()).size();
                        if (check_offline_data == 0) {
                            ArrayList<DataModel> dataModel_list = (ArrayList<DataModel>) appDatabase.interfaceDataModelQuery().getCase_caseID("" + Singleton.getInstance().aCase.getCaseId());
                            // Get the online modal data
                            DataModel dataModel = dataModel_list.get(0);
                            // Init the offlinecase modal
                            OfflineDataModel offlineDataModel = new OfflineDataModel();
                            offlineDataModel.setCaseId(dataModel.getCaseId());
                            offlineDataModel.setApplicantName(dataModel.getApplicantName());
                            offlineDataModel.setApplicantContactNo(dataModel.getApplicantContactNo());
                            offlineDataModel.setPropertyAddress(dataModel.getPropertyAddress());
                            offlineDataModel.setContactPersonName(dataModel.getContactPersonName());
                            offlineDataModel.setContactPersonNumber(dataModel.getContactPersonNumber());
                            offlineDataModel.setBankName(dataModel.getBankName());
                            offlineDataModel.setBankBranchName(dataModel.getBankBranchName());
                            offlineDataModel.setBankId(dataModel.getBankId());
                            offlineDataModel.setAgencyBranchId(dataModel.getAgencyBranchId());
                            offlineDataModel.setPropertyType(dataModel.getPropertyType());
                            offlineDataModel.setTypeID(dataModel.getTypeID());
                            offlineDataModel.setAssignedAt(dataModel.getAssignedAt());
                            offlineDataModel.setReportChecker(dataModel.getReportChecker());
                            offlineDataModel.setStatus(dataModel.getStatus());
                            offlineDataModel.setReportDispatcher(dataModel.getReportDispatcher());
                            offlineDataModel.setFieldStaff(dataModel.getFieldStaff());
                            offlineDataModel.setAssignedTo(dataModel.getAssignedTo());
                            offlineDataModel.setReportMaker(dataModel.getReportMaker());
                            offlineDataModel.setReportFinalizer(dataModel.getReportFinalizer());
                            offlineDataModel.setPropertyId(dataModel.getPropertyId());
                            offlineDataModel.setReportFile(dataModel.getReportFile());
                            offlineDataModel.setReportId(dataModel.getReportId());
                            offlineDataModel.setReportTemplateId(dataModel.getReportTemplateId());
                            offlineDataModel.setSignature1(dataModel.getSignature1());
                            offlineDataModel.setPropertyCategoryId(dataModel.getPropertyCategoryId());
                            offlineDataModel.setSignature2(dataModel.getSignature2());
                            offlineDataModel.setEmployeeName(dataModel.getEmployeeName());
                            offlineDataModel.setRole(dataModel.getRole());
                            offlineDataModel.setStatusId(dataModel.getStatusId());
                            offlineDataModel.setShowoffline(true);
                            offlineDataModel.setOfflinecase(false);
                            offlineDataModel.setIs_property_changed(false);
                            offlineDataModel.setSync_edit(true);
                            // Insert into offline database
                            appDatabase.interfaceOfflineDataModelQuery().insertAll(offlineDataModel);
                        }

                        // updateOfflineDataModel > offlinecase as true
                        appDatabase.interfaceOfflineDataModelQuery().updateOfflineDataModel("" + Singleton.getInstance().aCase.getCaseId(), true);
                        // update_sync_edit - true
                        appDatabase.interfaceOfflineDataModelQuery().update_sync_edit_DataModel("" + Singleton.getInstance().aCase.getCaseId(), true);

                        // Add the offline case to the modal
                        if (!general.isEmpty(String.valueOf(Singleton.getInstance().aCase.getCaseId()))) {
                            // offline set the caseID and propertyID
                            OflineCase oflineCase = new OflineCase();
                            oflineCase.setCaseId(Singleton.getInstance().aCase.getCaseId());
                            oflineCase.setPropertyId(Singleton.getInstance().aCase.getPropertyId());
                            // Room Delete - OfflineCase
                            appDatabase.interfaceOfflineCaseQuery().deleteRow(oflineCase.getCaseId());
                            // Room Add - OfflineCase
                            appDatabase.interfaceOfflineCaseQuery().insertAll(oflineCase);

                            int PropertyId_is = Singleton.getInstance().aCase.getPropertyId();
                            // Room - delete lat long
                            appDatabase.interfaceLatLongQuery().deleteRow(caseid_int);
                            LatLongDetails latLongDetails = new LatLongDetails();
                            latLongDetails.setCaseId(caseid_int);
                            latLongDetails.setLatLongDetails(Singleton.getInstance().latlng_details);
                            // Room - add lat long
                            appDatabase.interfaceLatLongQuery().insertAll(latLongDetails);
                            // Photo delete and add
                            // Room Delete
                            appDatabase.interfaceGetPhotoQuery().deleteRow(PropertyId_is);
                            GetPhoto dummy_cameraImage = new GetPhoto();
                            dummy_cameraImage.setDefaultimage(true);
                            dummy_cameraImage.setPropertyId(PropertyId_is);
                            // Room Add
                            appDatabase.interfaceGetPhotoQuery().insertAll(dummy_cameraImage);

                            GetPhoto dummy_galleryImage = new GetPhoto();
                            dummy_galleryImage.setDefaultimage(true);
                            dummy_galleryImage.setPropertyId(PropertyId_is);
                            // Room Add
                            appDatabase.interfaceGetPhotoQuery().insertAll(dummy_galleryImage);

                            JSONArray jsonArray = new JSONArray();
                            for (int x = 0; x < PhotoLatLong.GetPhoto_list_response.size(); x++) {
                                if (!PhotoLatLong.GetPhoto_list_response.get(x).isDefaultimage()) {
                                    GetPhoto getPhoto = new GetPhoto();
                                    getPhoto.setId(0);
                                    // getPhoto.setId(PhotoLatLong.GetPhoto_list_response.get(x).getId());
                                    getPhoto.setLogo(PhotoLatLong.GetPhoto_list_response.get(x).getLogo());
                                    getPhoto.setTitle(PhotoLatLong.GetPhoto_list_response.get(x).getTitle());
                                    getPhoto.setPropertyId(PhotoLatLong.GetPhoto_list_response.get(x).getPropertyId());
                                    getPhoto.setDefaultimage(false);
                                    getPhoto.setNewimage(false);
                                    // Check the Base64Image should be less than 19,80,000 ...
                                    if (PhotoLatLong.GetPhoto_list_response.get(x).getLogo().length() < 1980000) {
                                        // Room Add
                                        appDatabase.interfaceGetPhotoQuery().insertAll(getPhoto);
                                    }
                                }
                            }
                        }

                        // Generate the Floor again
                        checkValidationforFloors();

                        // hit_photo_api > false
                        Singleton.getInstance().hit_photo_api = true;
                        general.CustomToast(getActivity().getResources().getString(R.string.case_moved_offline));
                    }
                }
            });
        }


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Settings.ACTION_SETTINGS);
                getActivity().startActivity(i);
                dialog.dismiss();
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    // calc
    void call_calc_function(final int type_is) {


        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.popup_calc);
        dialog.setTitle("Calculator");


        button0 = (Button) dialog.findViewById(R.id.button0);
        button1 = (Button) dialog.findViewById(R.id.button1);
        button2 = (Button) dialog.findViewById(R.id.button2);
        button3 = (Button) dialog.findViewById(R.id.button3);
        button4 = (Button) dialog.findViewById(R.id.button4);
        button5 = (Button) dialog.findViewById(R.id.button5);
        button6 = (Button) dialog.findViewById(R.id.button6);
        button7 = (Button) dialog.findViewById(R.id.button7);
        button8 = (Button) dialog.findViewById(R.id.button8);
        button9 = (Button) dialog.findViewById(R.id.button9);
        button10 = (Button) dialog.findViewById(R.id.button10);
        buttonAdd = (Button) dialog.findViewById(R.id.buttonadd);
        buttonSub = (Button) dialog.findViewById(R.id.buttonsub);
        buttonMul = (Button) dialog.findViewById(R.id.buttonmul);
        buttonDivision = (Button) dialog.findViewById(R.id.buttondiv);
        buttonC = (Button) dialog.findViewById(R.id.buttonC);
        buttonEqual = (Button) dialog.findViewById(R.id.buttoneql);
        button_set = (Button) dialog.findViewById(R.id.button_set);
        button_close = (Button) dialog.findViewById(R.id.button_close);
        edt1 = (TextView) dialog.findViewById(R.id.edt1);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_mytext("1");
                //edt1.setText(edt1.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_mytext("2");
                //edt1.setText(edt1.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_mytext("3");
                //edt1.setText(edt1.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_mytext("4");
                //edt1.setText(edt1.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_mytext("5");
                //edt1.setText(edt1.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_mytext("6");
                //edt1.setText(edt1.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_mytext("7");
                //edt1.setText(edt1.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_mytext("8");
                //edt1.setText(edt1.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_mytext("9");
                //edt1.setText(edt1.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_mytext("0");
                //edt1.setText(edt1.getText() + "0");
            }
        });

        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        button_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!general.isEmpty(edt1.getText().toString())) {
                    // - values not allowed
                    if (!edt1.getText().toString().contains("-")) {
                        int my_val = general.convertToRoundoff(Float.parseFloat(edt1.getText().toString()));
                        String my_val_str = String.valueOf(my_val);
                        if (type_is == 1) {
                            editText_compound_permissiblearea.setText(my_val_str);
                            String charSequence = editText_compound_permissiblearea.getText().toString();
                            if (general.isEmpty(editText_as_per_measurement.getText().toString())) {
                                // If its empty
                                editText_as_per_measurement.setText(charSequence);
                                //TODO Interface
                                FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                                fragmentValuationBuilding.actual_measurment(charSequence);
                            }
                            editText_compound_permissiblearea.setError(null);
                            editText_as_per_measurement.setError(null);
                            //TODO Interface
                            FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                            fragmentValuationBuilding.permission_measurment(charSequence);
                        } else {
                            editText_as_per_measurement.setText(my_val_str);
                            //TODO Interface
                            FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                            fragmentValuationBuilding.actual_measurment(my_val_str);
                        }
                    }
                }
                dialog.dismiss();
            }
        });


        mValueCurrent = 0;

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mClear) {
                    if (!general.isEmpty(edt1.getText().toString())) {
                        mValueOne = Float.parseFloat(edt1.getText() + "");
                        mValueCurrent = mValueCurrent + mValueOne;
                        result_value = Double.parseDouble(new DecimalFormat("##.##").format(mValueCurrent));
                        Log.e("double", result_value + "");
                        Log.e("bigdecimal", BigDecimal.valueOf(mValueCurrent).toPlainString() + "");

                        edt1.setText("" + result_value);
                        // edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
                        mClear = true;
                    }
                }
                mAddition = true;
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mClear) {
                    if (!general.isEmpty(edt1.getText().toString())) {
                        mValueOne = Float.parseFloat(edt1.getText() + "");
                        if (mValueCurrent == 0) {
                            mValueCurrent = mValueOne;
                        } else {
                            mValueCurrent = mValueCurrent - mValueOne;
                        }
                        result_value = Double.parseDouble(new DecimalFormat("##.##").format(mValueCurrent));
                        Log.e("double", result_value + "");
                        Log.e("bigdecimal", BigDecimal.valueOf(mValueCurrent).toPlainString() + "");

                        edt1.setText("" + result_value);
                        // edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
                        mClear = true;
                    }
                }
                mSubtract = true;
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mClear) {
                    if (!general.isEmpty(edt1.getText().toString())) {
                        mValueOne = Float.parseFloat(edt1.getText() + "");
                        if (mValueCurrent == 0) {
                            mValueCurrent = mValueOne;
                        } else {
                            mValueCurrent = mValueCurrent * mValueOne;
                        }

                        result_value = Double.parseDouble(new DecimalFormat("##.##").format(mValueCurrent));
                        Log.e("double", result_value + "");

                        edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
                        Log.e("bigdecimal", BigDecimal.valueOf(mValueCurrent).toPlainString() + "");
                        mClear = true;
                    }
                }
                mMultiplication = true;
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mClear) {
                    if (!general.isEmpty(edt1.getText().toString())) {
                        mValueOne = Float.parseFloat(edt1.getText() + "");
                        if (mValueCurrent == 0) {
                            mValueCurrent = mValueOne;
                        } else {
                            mValueCurrent = mValueCurrent / mValueOne;
                        }

                        result_value = Double.parseDouble(new DecimalFormat("##.##").format(mValueCurrent));
                        Log.e("double", result_value + "");
                        Log.e("bigdecimal", BigDecimal.valueOf(mValueCurrent).toPlainString() + "");

                        edt1.setText("" + result_value);
                        // edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());

                        mClear = true;
                    }
                }
                mDivision = true;
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!general.isEmpty(edt1.getText().toString())) {
                    //mValueTwo = Float.parseFloat(edt1.getText() + "");


                    if (mAddition == true) {
                        mValueOne = Float.parseFloat(edt1.getText() + "");
                        mValueCurrent = mValueCurrent + mValueOne;
                        result_value = Double.parseDouble(new DecimalFormat("##.##").format(mValueCurrent));
                        edt1.setText("" + result_value);
                        //edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
                        mClear = true;
                        mAddition = false;
                    }

                    if (mSubtract == true) {
                        mValueOne = Float.parseFloat(edt1.getText() + "");
                        if (mValueCurrent == 0) {
                            mValueCurrent = mValueOne;
                        } else {
                            mValueCurrent = mValueCurrent - mValueOne;
                        }
                        result_value = Double.parseDouble(new DecimalFormat("##.##").format(mValueCurrent));
                        edt1.setText("" + result_value);
                        // edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
                        mClear = true;
                        mSubtract = false;
                    }

                    if (mMultiplication == true) {
                        mValueOne = Float.parseFloat(edt1.getText() + "");
                        if (mValueCurrent == 0) {
                            mValueCurrent = mValueOne;
                        } else {
                            mValueCurrent = mValueCurrent * mValueOne;
                        }
                        result_value = Double.parseDouble(new DecimalFormat("##.##").format(mValueCurrent));
                        edt1.setText("" + result_value);
                        // edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
                        mClear = true;
                        mMultiplication = false;
                    }

                    if (mDivision == true) {
                        mValueOne = Float.parseFloat(edt1.getText() + "");
                        if (mValueCurrent == 0) {
                            mValueCurrent = mValueOne;
                        } else {
                            mValueCurrent = mValueCurrent / mValueOne;
                        }

                        result_value = Double.parseDouble(new DecimalFormat("##.##").format(mValueCurrent));
                        edt1.setText("" + result_value);
                        //edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
                        mClear = true;
                        mDivision = false;
                    }
                }

            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText("");
                mValueCurrent = 0;
                mClear = false;
                mAddition = false;
                mSubtract = false;
                mMultiplication = false;
                mDivision = false;
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_mytext(".");
                //edt1.setText(edt1.getText() + ".");
            }
        });

        dialog.show();
    }

    private void set_mytext(String my_num) {
        if (mClear) {
            mClear = false;
            edt1.setText(my_num);
        } else {
            edt1.setText(edt1.getText() + my_num);
        }
    }


    public void Calculation_Popup_New(final int type_is) {

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.calculator_popup_new);
        // dialog.setTitle("Calculator");

        isSmallBracketOpen = false;
        developedCounter = 0;


        /***** Assigning Variable *****/
        btnClear = (Button) dialog.findViewById(R.id.btn_delete);
        tvProcessor = (TextView) dialog.findViewById(R.id.tv_process);
        tvResult = (TextView) dialog.findViewById(R.id.tv_result);

        tvProcessor.setText("");
        tvResult.setText("");


        btnOne = (Button) dialog.findViewById(R.id.btn_one);
        btnTwo = (Button) dialog.findViewById(R.id.btn_two);
        btnThree = (Button) dialog.findViewById(R.id.btn_three);
        btnFour = (Button) dialog.findViewById(R.id.btn_four);
        btnFive = (Button) dialog.findViewById(R.id.btn_five);
        btnSix = (Button) dialog.findViewById(R.id.btn_six);
        btnSeven = (Button) dialog.findViewById(R.id.btn_seven);
        btnEight = (Button) dialog.findViewById(R.id.btn_eight);
        btnNine = (Button) dialog.findViewById(R.id.btn_nine);
        btnZero = (Button) dialog.findViewById(R.id.btn_zero);


        btnMultiply = (Button) dialog.findViewById(R.id.btn_multiply);
        btnMinus = (Button) dialog.findViewById(R.id.btn_minus);
        btnPlus = (Button) dialog.findViewById(R.id.btn_plus);
        btnDivide = (Button) dialog.findViewById(R.id.btn_divide);

        btnDecimal = (Button) dialog.findViewById(R.id.btn_dot);
        btnBack = (Button) dialog.findViewById(R.id.btn_back);

        btnSmallBracket = (Button) dialog.findViewById(R.id.btn_small_bracket);
        btnEqual = (Button) dialog.findViewById(R.id.btn_equal);
        btnPercentage = (Button) dialog.findViewById(R.id.btn_percentage);
        btnclose = (Button) dialog.findViewById(R.id.btnclose);
        btnsetvalue = (Button) dialog.findViewById(R.id.btnsetvalue);
        TextView calculatortitle = (TextView) dialog.findViewById(R.id.calculatortitle);

        calculatortitle.setTypeface(general.regulartypeface());
        btnClear.setTypeface(general.regulartypeface());
        tvProcessor.setTypeface(general.regulartypeface());
        tvResult.setTypeface(general.regulartypeface());
        btnOne.setTypeface(general.RobotoLightTypeface());
        btnTwo.setTypeface(general.RobotoLightTypeface());
        btnThree.setTypeface(general.RobotoLightTypeface());
        btnFour.setTypeface(general.RobotoLightTypeface());
        btnFive.setTypeface(general.RobotoLightTypeface());
        btnSix.setTypeface(general.RobotoLightTypeface());
        btnSeven.setTypeface(general.RobotoLightTypeface());
        btnEight.setTypeface(general.RobotoLightTypeface());
        btnNine.setTypeface(general.RobotoLightTypeface());
        btnZero.setTypeface(general.RobotoLightTypeface());
        btnMultiply.setTypeface(general.regulartypeface());
        btnMinus.setTypeface(general.regulartypeface());
        btnPlus.setTypeface(general.regulartypeface());
        btnDivide.setTypeface(general.RobotoLightTypeface());
        btnDecimal.setTypeface(general.regulartypeface());
        btnBack.setTypeface(general.regulartypeface());
        btnSmallBracket.setTypeface(general.regulartypeface());
        btnEqual.setTypeface(general.regulartypeface());
        btnPercentage.setTypeface(general.regulartypeface());
        btnclose.setTypeface(general.regulartypeface());
        btnsetvalue.setTypeface(general.regulartypeface());

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                developedCounter++;
                if (developedCounter >= 5) {
                    developedCounter = 0;
                    tvProcessor.setText(developedBy);
                    tvResult.setText(deveopedNote);
                } else {
                    tvProcessor.setText("");
                    tvResult.setText("");
                }
            }
        });


        /******************************************************
         * Number Buttons on-Click
         ******************************************************/
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "1");
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "2");
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "3");
            }
        });
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "4");
            }
        });
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "5");
            }
        });
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "6");
            }
        });
        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "7");
            }
        });
        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "8");
            }
        });
        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "9");
            }
        });
        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "0");
            }
        });


        /******************************************************
         * Functional Buttons on-Click
         ******************************************************/
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "*");
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "-");
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "+");
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "/");
            }
        });
        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + ".");
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                if (processor.length() > 0) {
                    processor = processor.substring(0, processor.length() - 1);
                    tvProcessor.setText(processor);
                } else {
                    tvResult.setText("");
                }
            }
        });
        btnSmallBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                if (isSmallBracketOpen) {
                    processor = tvProcessor.getText().toString();
                    tvProcessor.setText(processor + ")");
                    isSmallBracketOpen = false;
                } else {
                    processor = tvProcessor.getText().toString();
                    tvProcessor.setText(processor + "(");
                    isSmallBracketOpen = true;
                }
            }
        });

        btnPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();
                tvProcessor.setText(processor + "%");
            }
        });


        /******************************************************
         * Equal Buttons on-Click
         ******************************************************/
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearScreen();
                processor = tvProcessor.getText().toString();

                //processor = processor.replaceAll("X", "*");
                processor = processor.replaceAll("%", "/100");

                Context rhino = Context.enter();
                rhino.setOptimizationLevel(-1);
                String result = "";

                try {
                    Scriptable scope = rhino.initStandardObjects();
                    result = rhino.evaluateString(scope, processor, "JavaScript", 1, null).toString();
                } catch (Exception e) {
                    result = "Error";
                }

                /*************/
                if (result.contains("javascript") || result.contains("Error")) {
                    tvResult.setText("");
                } else if (result.contains(".")) {
                    Float resfloat = Float.valueOf(result);
                    result_value = Double.parseDouble(new DecimalFormat("##.##").format(resfloat));
                    Log.e("double", result_value + "");
                    /***********/

                    tvResult.setText("" + result_value);
                } else {
                    tvResult.setText("" + result);
                }
            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnsetvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!general.isEmpty(tvResult.getText().toString())) {
                    // - values not allowed
                    if (!tvResult.getText().toString().contains("-")) {
                        /*int my_val = general.convertToRoundoff(Float.parseFloat(tvResult.getText().toString()));
                        String my_val_str = String.valueOf(my_val);*/
                        String my_val_str = tvResult.getText().toString();
                        if (type_is == 1) {
                            editText_compound_permissiblearea.setText(my_val_str);
                            String charSequence = editText_compound_permissiblearea.getText().toString();
                            if (general.isEmpty(editText_as_per_measurement.getText().toString())) {
                                // If its empty
                                editText_as_per_measurement.setText(charSequence);
                                //TODO Interface
                                FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                                fragmentValuationBuilding.actual_measurment(charSequence);
                            }
                            editText_compound_permissiblearea.setError(null);
                            editText_as_per_measurement.setError(null);
                            //TODO Interface
                            FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                            fragmentValuationBuilding.permission_measurment(charSequence);
                        } else {
                            editText_as_per_measurement.setText(my_val_str);
                            //TODO Interface
                            FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                            fragmentValuationBuilding.actual_measurment(my_val_str);
                        }
                    }
                    dialog.dismiss();
                } else if (general.isEmpty(tvResult.getText().toString())) {
                    general.CustomToast("Press equals first and then set the value");
                }

            }
        });

        dialog.show();
    }


    public void clearScreen() {
        processor = tvProcessor.getText().toString();
        if (processor.contains(developedBy)) {
            tvProcessor.setText("");
            tvResult.setText("");
        }
        developedCounter = 0;
    }

}
