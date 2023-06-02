package com.realappraiser.gharvalue.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.adapter.PropertyActualUsageAdapter;
import com.realappraiser.gharvalue.model.Construction;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.model.InternalFloorModel;
import com.realappraiser.gharvalue.model.Measurements;
import com.realappraiser.gharvalue.model.PropertyActualUsage;
import com.realappraiser.gharvalue.utils.DecimalDigitsInputFilter;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentFlat extends Fragment implements View.OnTouchListener {

    // TODO General class to call typeface and all...
    public static General general;
    // @BindView(R.id.editText_totalapartmentfloors)
    public static EditText editText_totalapartmentfloors;
    public static EditText editText_totalflatsperfloor;
    public static EditText editText_flatsituatedinfloor;
    //public static EditText editText_floors;
    public static EditText editText_as_per_docs;

    public static EditText edittext_general_floor_name;
    public static EditText edittext_general_comp;
    public static EditText edittext_general_progress;
    public static EditText edittext_general_age;
    public static EditText edittext_general_life;

    public static EditText edittext_general_saleablearea;
    public static EditText edittext_general_builduparea;
    public static EditText edittext_general_carpetarea;
    public static TextView textview_comp_total;
    public static TextView textview_internal_floor_name_composition;

    public static Spinner spinnerMeasurement_doc;
    public static Spinner spinner_measurement_act;
    public static Spinner stageSpinner;

    @BindView(R.id.textview_actualusage_flat)
    TextView textview_actualusage_flat;
    @BindView(R.id.textview_land_measurement)
    TextView textview_land_measurement;
    @BindView(R.id.textview_measurement_doc)
    TextView textview_measurement_doc;
    @BindView(R.id.textview_measurement_act)
    TextView textview_measurement_act;

    @BindView(R.id.textview_generl_info_measu)
    TextView textview_generl_info_measu;
    @BindView(R.id.textview_floor_name)
    TextView textview_floor_name;
    @BindView(R.id.textview_stage)
    TextView textview_stage;
    @BindView(R.id.textview_comp)
    TextView textview_comp;
    @BindView(R.id.textview_progress)
    TextView textview_progress;
    @BindView(R.id.textview_carpetarea)
    TextView textview_carpetarea;
    @BindView(R.id.textview_builduparea)
    TextView textview_builduparea;
    @BindView(R.id.textview_saleablearea)
    TextView textview_saleablearea;
    @BindView(R.id.textview_age)
    TextView textview_age;
    @BindView(R.id.textview_life)
    TextView textview_life;

    @BindView(R.id.textviewlabel_carpetloading_per)
    TextView textviewlabel_carpetloading_per;
    @BindView(R.id.textviewlabel_carpet_area_per)
    TextView textviewlabel_carpet_area_per;
    @BindView(R.id.textviewlabel_carpetloading_type)
    TextView textviewlabel_carpetloading_type;

    @BindView(R.id.textview_average)
    TextView textview_average;
    /*  @BindView(R.id.textview_comp_total)
      TextView textview_comp_total;*/
    @BindView(R.id.textview_total)
    TextView textview_total;
    @BindView(R.id.textview_doc_total)
    TextView textview_doc_total;
    @BindView(R.id.textview_actual_total)
    TextView textview_actual_total;

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

    // Todo spinner init
    @BindView(R.id.textview_actual_usage_flat)
    TextView textview_actual_usage_flat;


    /*@BindView(R.id.internal_hall_dinning)
    Spinner internal_hall_dinning;
    @BindView(R.id.internal_kitchen)
    Spinner internal_kitchen;
    @BindView(R.id.internal_bedroom)
    Spinner internal_bedroom;
    @BindView(R.id.internal_bath)
    Spinner internal_bath;
    @BindView(R.id.internal_shop_office)
    Spinner internal_shop_office;*/

    //@BindView(R.id.internal_hall_dinning)
    static EditText internal_hall_dinning;
    //@BindView(R.id.internal_kitchen)
    static EditText internal_kitchen;
    //@BindView(R.id.internal_bedroom)
    static EditText internal_bedroom;
    //@BindView(R.id.internal_bath)
    static EditText internal_bath;
    //@BindView(R.id.internal_shop_office)
    static EditText internal_shop_office;


    // actual_usage
    ArrayList<PropertyActualUsage> Inter_floors_list = new ArrayList<>();
    public static String measurment_floor_id = "0";

    public static TextView textview_carpetloading_per;
    public static TextView textview_loading_factor;
    public static Spinner spinner_carpetloading_type;
    public Dialog dialog;
    String val_carpetloading_per = "";
    String val_textview_loading_factor_per = "";
    String val_carpetloading_type = "";

    // calc click function
    @BindView(R.id.open_calc_builduparea)
    ImageView open_calc_builduparea;
    @BindView(R.id.open_calc_saleablearea)
    ImageView open_calc_saleablearea;
    @BindView(R.id.open_calc_carpetarea)
    ImageView open_calc_carpetarea;


    // calc
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
        View view = inflater.inflate(R.layout.activity_fragment_flat, container, false);
        ButterKnife.bind(this, view);
        general = new General(getActivity());

        initViewsStatic(view);
        initViews();
        InitiatePentHouseValues();
        AreaTextWatcher();


        // TODO -  call the mandatory_validation
        if (Singleton.getInstance().enable_validation_error) {
            set_pentflathouse_mandatory();
        }

        open_calc_builduparea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculation_Popup_New(1);
            }
        });

        open_calc_saleablearea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculation_Popup_New(2);
            }
        });

        open_calc_carpetarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculation_Popup_New(3);
            }
        });

        return view;
    }

    private void initViewsStatic(View view) {


        internal_hall_dinning = (EditText) view.findViewById(R.id.internal_hall_dinning);
        internal_kitchen = (EditText) view.findViewById(R.id.internal_kitchen);
        internal_bedroom = (EditText) view.findViewById(R.id.internal_bedroom);
        internal_bath = (EditText) view.findViewById(R.id.internal_bath);
        internal_shop_office = (EditText) view.findViewById(R.id.internal_shop_office);

        editText_totalapartmentfloors = (EditText) view.findViewById(R.id.editText_totalapartmentfloors);
        editText_totalflatsperfloor = (EditText) view.findViewById(R.id.editText_totalflatsperfloor);
        editText_flatsituatedinfloor = (EditText) view.findViewById(R.id.editText_flatsituatedinfloor);
        //editText_floors = (EditText) view.findViewById(R.id.editText_floors);
        editText_as_per_docs = (EditText) view.findViewById(R.id.editText_as_per_docs);
        edittext_general_floor_name = (EditText) view.findViewById(R.id.edittext_general_floor_name);
        edittext_general_comp = (EditText) view.findViewById(R.id.edittext_general_comp);
        edittext_general_progress = (EditText) view.findViewById(R.id.edittext_general_progress);
        edittext_general_age = (EditText) view.findViewById(R.id.edittext_general_age);
        edittext_general_life = (EditText) view.findViewById(R.id.edittext_general_life);
        edittext_general_saleablearea = (EditText) view.findViewById(R.id.edittext_general_saleablearea);
        edittext_general_builduparea = (EditText) view.findViewById(R.id.edittext_general_builduparea);
        edittext_general_carpetarea = (EditText) view.findViewById(R.id.edittext_general_carpetarea);
        textview_comp_total = (TextView) view.findViewById(R.id.textview_comp_total);
        textview_internal_floor_name_composition = (TextView) view.findViewById(R.id.textview_internal_floor_name_composition);
        spinnerMeasurement_doc = (Spinner) view.findViewById(R.id.spinnerMeasurement_doc);
        spinner_measurement_act = (Spinner) view.findViewById(R.id.spinner_measurement_act);
        stageSpinner = (Spinner) view.findViewById(R.id.stageSpinner);
        spinner_carpetloading_type = (Spinner) view.findViewById(R.id.spinner_carpetloading_type);
        textview_carpetloading_per = (TextView) view.findViewById(R.id.textview_carpetloading_per);
        textview_loading_factor = (TextView) view.findViewById(R.id.textview_loading_factor);

        //  limit the 2 char after the decimal point
        editText_as_per_docs.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(10, 2)});
        edittext_general_builduparea.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});
        edittext_general_saleablearea.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});
        edittext_general_carpetarea.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});


    }

    private void initViews() {


        textview_land_measurement.setTypeface(general.mediumtypeface());
        editText_totalapartmentfloors.setTypeface(general.regulartypeface());
        editText_totalflatsperfloor.setTypeface(general.regulartypeface());
        editText_flatsituatedinfloor.setTypeface(general.regulartypeface());
        textview_actualusage_flat.setTypeface(general.regulartypeface());
//        editText_floors.setTypeface(general.regulartypeface());
        editText_as_per_docs.setTypeface(general.regulartypeface());
        textview_measurement_doc.setTypeface(general.regulartypeface());
        textview_measurement_act.setTypeface(general.regulartypeface());

        textview_generl_info_measu.setTypeface(general.mediumtypeface());
        textview_internal_composition.setTypeface(general.mediumtypeface());
        edittext_general_floor_name.setTypeface(general.regulartypeface());
        edittext_general_comp.setTypeface(general.regulartypeface());
        edittext_general_progress.setTypeface(general.regulartypeface());
        edittext_general_age.setTypeface(general.regulartypeface());
        edittext_general_life.setTypeface(general.regulartypeface());
        textview_floor_name.setTypeface(general.regulartypeface());
        textview_stage.setTypeface(general.regulartypeface());
        textview_comp.setTypeface(general.regulartypeface());
        textview_progress.setTypeface(general.regulartypeface());
        textview_carpetarea.setTypeface(general.regulartypeface());
        textview_builduparea.setTypeface(general.regulartypeface());
        textviewlabel_carpetloading_per.setTypeface(general.regulartypeface());
        textviewlabel_carpet_area_per.setTypeface(general.regulartypeface());
        textviewlabel_carpetloading_type.setTypeface(general.regulartypeface());
        textview_saleablearea.setTypeface(general.regulartypeface());
        textview_age.setTypeface(general.regulartypeface());
        textview_life.setTypeface(general.regulartypeface());
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
        textview_internal_floor_name_composition.setTypeface(general.regulartypeface());
        edittext_general_saleablearea.setTypeface(general.regulartypeface());
        edittext_general_builduparea.setTypeface(general.regulartypeface());
        edittext_general_carpetarea.setTypeface(general.regulartypeface());
        textview_carpetloading_per.setTypeface(general.regulartypeface());
        textview_loading_factor.setTypeface(general.regulartypeface());

    }

    public void InitiatePentHouseValues() {

        /*editText_floors.setText("1");
        editText_floors.setFocusable(false);*/

        // TODO - changes
        edittext_general_comp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    String value = edittext_general_comp.getText().toString();
                    textview_comp_total.setText(value);
                    if (!general.isEmpty(value)) {
                        FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
                        fragmentValuationPenthouse.setEdittextAsPerCompletion(value);
                    }
                }
            }
        });

        /*edittext_general_comp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String value = charSequence.toString();
                textview_comp_total.setText(value);

                if (!general.isEmpty(value)) {
                    FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
                    fragmentValuationPenthouse.setEdittextAsPerCompletion(value);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/

        edittext_general_age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String age = charSequence.toString();
                if (!general.isEmpty(age)) {
                    if (Integer.valueOf(age) < 46) {
                        int life = general.getAgeLifeValue(Integer.valueOf(age));
                        edittext_general_life.setText("" + life);
                    } else {
                        edittext_general_life.setText("");
                    }
                } else {
                    edittext_general_life.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        edittext_general_floor_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String floorname = charSequence.toString();
                textview_internal_floor_name_composition.setText(floorname);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        SpinnerInitiateValuesSet();
        DisplayFlatorPentHouseValues();
    }

    private void AreaTextWatcher() {
        Singleton.getInstance().areaType.clear();
        Singleton.getInstance().areaType.add("Select");
        edittext_general_carpetarea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
                fragmentValuationPenthouse.setEdittextCarpetArea(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkSpinnerAreaType(editable.toString(), getResources().getString(R.string.carpet));

            }
        });


        edittext_general_builduparea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
                fragmentValuationPenthouse.setEdittextBuildupArea(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkSpinnerAreaType(editable.toString(), getResources().getString(R.string.builtup));
            }
        });

        edittext_general_builduparea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    fun_carpet_value();
                }
            }
        });

        edittext_general_saleablearea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
                fragmentValuationPenthouse.setEdittextSaleableArea(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkSpinnerAreaType(editable.toString(), getResources().getString(R.string.Saleable));
            }
        });

        edittext_general_saleablearea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    fun_carpet_value();
                }
            }
        });
    }

    private void checkSpinnerAreaType(String editable, String area) {

        if (!general.isEmpty(editable.toString())) {
            if (!Singleton.getInstance().areaType.contains(area)) {
                Singleton.getInstance().areaType.add(area);
            }
        } else {
            if (Singleton.getInstance().areaType.contains(area)) {
                for (int i = 0; i < Singleton.getInstance().areaType.size(); i++) {
                    if (Singleton.getInstance().areaType.get(i).equalsIgnoreCase(area)) {
                        Singleton.getInstance().areaType.remove(i);
                    }
                }
            }
        }

        FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
        fragmentValuationPenthouse.AreaTypeSpinner(Singleton.getInstance().areaType, area);
    }

    public void SpinnerInitiateValuesSet() {

        // Todo spinner set for penthouse land measurement
        getMeasurementonGeneralFloor();
        // Todo Multiselect
        getActualUsage();

        // Todo spinner set for penthouse general info measurement
        getConstructionStageSpinner();

        // Todo spinner set for internal composition floor values
        //setInternalCompositionFloorSpinner();
    }

    public void getMeasurementonGeneralFloor() {
        ArrayAdapter<Measurements> adapterMeasurements = new ArrayAdapter<>(getActivity(),
                R.layout.row_spinner_item, Singleton.getInstance().measurements_list);
        adapterMeasurements.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMeasurement_doc.setAdapter(adapterMeasurements);
        spinnerMeasurement_doc.setOnTouchListener(this);

        spinnerMeasurement_doc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_measurement", "::: " + Singleton.getInstance().measurements_list.get(position).getMeasureUnitId());

                Singleton.getInstance().indProperty.setDocumentLandAreaUnit(Singleton.getInstance().measurements_list.get(position).getMeasureUnitId());
                Singleton.getInstance().indProperty.setMeasuredLandAreaUnit(Singleton.getInstance().measurements_list.get(position).getMeasureUnitId());

               /* FragmentValuationBuilding fragmentValuationBuilding = new FragmentValuationBuilding();
                fragmentValuationBuilding.get_measurment_land(Singleton.getInstance().measurements_list.get(position).getMeasureUnitId(), Singleton.getInstance().measurements_list.get(position).getName());*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<Measurements> adapterMeasurements2 = new ArrayAdapter<Measurements>(getActivity(),
                R.layout.row_spinner_item, Singleton.getInstance().measurements_list_flat);
        adapterMeasurements2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_measurement_act.setAdapter(adapterMeasurements2);
        spinner_measurement_act.setSelection(1);
        spinner_measurement_act.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                measurment_floor_id = String.valueOf(Singleton.getInstance().measurements_list_flat.get(position).getMeasureUnitId());

                if (position == 0) {
                    FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
                    fragmentValuationPenthouse.edittext_measurementunit.setText("");
                } else {
                    FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
                    fragmentValuationPenthouse.edittext_measurementunit.setText("" + Singleton.getInstance().measurements_list_flat.get(position).getName());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        final ArrayList<String> carpetloading_type = new ArrayList<>();
        carpetloading_type.add("Select");
        carpetloading_type.add("Buildup Area");
        carpetloading_type.add("Saleable Area");

        ArrayAdapter<String> adapterMeasurements3 = new ArrayAdapter<String>(getActivity(), R.layout.row_spinner_item, carpetloading_type);
        adapterMeasurements3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_carpetloading_type.setAdapter(adapterMeasurements3);

        String SelectedCarpetAreaTypeId = Singleton.getInstance().indPropertyValuation.getSelectedCarpetAreaTypeId();
        if (!general.isEmpty(SelectedCarpetAreaTypeId)) {
            int pos = Integer.parseInt(SelectedCarpetAreaTypeId);
            spinner_carpetloading_type.setSelection(pos, false);
            val_carpetloading_type = "" + SelectedCarpetAreaTypeId;
        } else {
            val_carpetloading_type = "" + 0;
            spinner_carpetloading_type.setSelection(0, false);
        }

        spinner_carpetloading_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                val_carpetloading_type = "" + position;
                Singleton.getInstance().indPropertyValuation.setSelectedCarpetAreaTypeId("" + position);
                fun_carpet_value();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        String CarpetAreaPercentage_str = Singleton.getInstance().indPropertyValuation.getCarpetAreaPercentage();
        if (!general.isEmpty(CarpetAreaPercentage_str)) {
            val_carpetloading_per = CarpetAreaPercentage_str;
            textview_carpetloading_per.setText(CarpetAreaPercentage_str);
        } else {
            val_carpetloading_per = "";
            textview_carpetloading_per.setText("Select");
        }

        textview_carpetloading_per.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_carpetloading_per);
                carpetloading_per(textview_carpetloading_per, "carpet_per");
            }
        });


        String SaleableLoadingPercentage_str = Singleton.getInstance().indPropertyValuation.getSaleableLoadingPercentage();
        if (!general.isEmpty(SaleableLoadingPercentage_str)) {
            val_textview_loading_factor_per = SaleableLoadingPercentage_str;
            textview_loading_factor.setText(SaleableLoadingPercentage_str);
        } else {
            val_textview_loading_factor_per = "";
            textview_loading_factor.setText("Select");
        }

        textview_loading_factor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_loading_factor);
                carpetloading_per(textview_loading_factor, "loading_factor");
            }
        });


    }

    /*public void getConstructionStageSpinner() {
        ArrayAdapter<Construction> adapterStageConstruct = new ArrayAdapter<>(getActivity(),
                R.layout.row_spinner_item, Singleton.getInstance().constructions_list);
        adapterStageConstruct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stageSpinner.setAdapter(adapterStageConstruct);

        stageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                int stageSpinnerSelectedItemId = (int) stageSpinner.getSelectedItemId();
                int selectposition = stageSpinner.getSelectedItemPosition();

                if (position > 0) {
                    if (Singleton.getInstance().indPropertyFloors != null)
                        if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                            IndPropertyFloor indPropertyFloor = Singleton.getInstance().indPropertyFloors.get(0);

                            int constructid = 0;
                            if (Singleton.getInstance().constructions_list.size() > 0) {
                                for (int i = 0; i < Singleton.getInstance().constructions_list.size(); i++) {
                                    if (selectposition == i) {
                                        constructid = Singleton.getInstance().constructions_list.get(i).getConstructionId();
                                    }
                                }
                            }

                            indPropertyFloor.setConstructionStageId(constructid);
                            Singleton.getInstance().indPropertyFloors.set(0, indPropertyFloor);

                        } else {
                            IndPropertyFloor indPropertyFloor = new IndPropertyFloor();
                            int constructid = 0;
                            if (Singleton.getInstance().constructions_list.size() > 0) {
                                for (int i = 0; i < Singleton.getInstance().constructions_list.size(); i++) {
                                    if (selectposition == i) {
                                        constructid = Singleton.getInstance().constructions_list.get(i).getConstructionId();
                                    }
                                }
                            }

                            indPropertyFloor.setConstructionStageId(constructid);
                            Singleton.getInstance().indPropertyFloors.add(indPropertyFloor);
                        }
                }
                if (position == 1) {
                    edittext_general_comp.setText("" + 100);
                    textview_comp_total.setText("" + 100);
                    edittext_general_progress.setText("");

                    edittext_general_comp.setEnabled(false);
                    edittext_general_progress.setEnabled(false);
                } else {
                    if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                        int value = Singleton.getInstance().indPropertyFloors.get(0).getPercentageCompletion();
                        if (value == 0)
                            edittext_general_comp.setText("");
                    }
                    edittext_general_comp.setEnabled(true);
                    edittext_general_progress.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }*/

    public void getConstructionStageSpinner() {
        ArrayAdapter<Construction> adapterStageConstruct = new ArrayAdapter<>(getActivity(),
                R.layout.row_spinner_item, Singleton.getInstance().constructions_list);

        adapterStageConstruct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        stageSpinner.setAdapter(adapterStageConstruct);
        stageSpinner.setOnTouchListener(this);

        if (Singleton.getInstance().indPropertyFloors.size() > 0) {
            if (!general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getConstructionStageId()))) {
                int constructionid = Singleton.getInstance().indPropertyFloors.get(0).getConstructionStageId();
                stageSpinner.setSelection(constructionid, false);
            } else {
                stageSpinner.setSelection(0, false);
            }
        } else {
            stageSpinner.setSelection(0, false);
        }

        stageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                int stageSpinnerSelectedItemId = (int) stageSpinner.getSelectedItemId();
                int selectposition = stageSpinner.getSelectedItemPosition();

                if (position > 0) {
                    if (Singleton.getInstance().indPropertyFloors != null) {
                        if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                            IndPropertyFloor indPropertyFloor = Singleton.getInstance().indPropertyFloors.get(0);
                            int constructid = 0;
                            if (Singleton.getInstance().constructions_list.size() > 0) {
                                for (int i = 0; i < Singleton.getInstance().constructions_list.size(); i++) {
                                    if (selectposition == i) {
                                        constructid = Singleton.getInstance().constructions_list.get(i).getConstructionId();
                                    }
                                }
                            }
                            indPropertyFloor.setConstructionStageId(constructid);
                            Singleton.getInstance().indPropertyFloors.set(0, indPropertyFloor);
                        } else {
                            IndPropertyFloor indPropertyFloor = new IndPropertyFloor();
                            int constructid = 0;
                            if (Singleton.getInstance().constructions_list.size() > 0) {
                                for (int i = 0; i < Singleton.getInstance().constructions_list.size(); i++) {
                                    if (selectposition == i) {
                                        constructid = Singleton.getInstance().constructions_list.get(i).getConstructionId();
                                    }
                                }
                            }
                            indPropertyFloor.setConstructionStageId(constructid);
                            Singleton.getInstance().indPropertyFloors.add(indPropertyFloor);
                        }
                    }
                }

                if (position == 1) {
                    // not editable - complete
                    edittext_general_comp.setText("" + 100);
                    textview_comp_total.setText("" + 100);
                    edittext_general_progress.setText("");
                    edittext_general_comp.setEnabled(false);
                    edittext_general_progress.setEnabled(false);
                    // as per com 100 in valuation
                    if (!general.isEmpty("" + 100)) {
                        FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
                        fragmentValuationPenthouse.setEdittextAsPerCompletion("" + 100);
                    }
                } else if (position == 2) {
                    // editable - underconstruction
                    edittext_general_comp.setText("");
                    textview_comp_total.setText("");
                    edittext_general_progress.setText("");
                    edittext_general_comp.setEnabled(true);
                    edittext_general_progress.setEnabled(true);
                    edittext_general_age.setText("");
                    edittext_general_life.setText("");
                } else {
                    // not editable - select
                    edittext_general_comp.setText("");
                    textview_comp_total.setText("");
                    edittext_general_progress.setText("");
                    edittext_general_comp.setEnabled(false);
                    edittext_general_progress.setEnabled(false);
                }

                if (FragmentFlat.edittext_general_comp != null) {
                    FragmentFlat.edittext_general_comp.setError(null);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }


    public void getActualUsage() {
        /* sugan Integration */
        Singleton.getInstance().PropertyActualUsage_id.clear();
        Singleton.getInstance().PropertyActualUsage_name.clear();
        textview_actual_usage_flat.setTypeface(general.regulartypeface());
        function_actual_usage();
        textview_actual_usage_flat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_actual_usage_flat);
                showdialog_dynamic("actual_usage");
            }
        });
        /* sugan Integration */

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
                            textview_actual_usage_flat.setText(general.remove_array_brac_and_space(Singleton.getInstance().PropertyActualUsage_name.toString()));
                        }
                    }
                }
            } else {
                textview_actual_usage_flat.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void showdialog_dynamic(final String type_) {
        final String type_of_dialog = type_;

        View view = LayoutInflater.from(getContext()).inflate(R.layout.multiselect_checkbox, null);
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
                        textview_actual_usage_flat.setText(PropertyActualUsage_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().indProperty.setPropertyActualUsageId("");
                        textview_actual_usage_flat.setText(getResources().getString(R.string.select));
                    }
                    Log.e("PropertyActualUsage_id", "::: " + Singleton.getInstance().PropertyActualUsage_id);
                    Log.e("PropertyActuaUs_name", ":: " + Singleton.getInstance().PropertyActualUsage_name);
                }

                alertDialog.dismiss();
            }
        });
    }


    private void set_pentflathouse_mandatory() {

        String flatsituated = editText_flatsituatedinfloor.getText().toString();
        String stagespinner = stageSpinner.getSelectedItem().toString();
        String percentagecomp = edittext_general_comp.getText().toString();

        if (!general.isEmpty(flatsituated)) {
            editText_flatsituatedinfloor.setError(null);
        } else {
            editText_flatsituatedinfloor.requestFocus();
            editText_flatsituatedinfloor.setError(getActivity().getResources().getString(R.string.err_flatsituated));
        }

        if (stagespinner.equalsIgnoreCase("Select")) {
            ((TextView) stageSpinner.getSelectedView()).setError(getActivity().getResources().getString(R.string.err_construction_stage));
            edittext_general_comp.requestFocus();
        }

        if (!general.isEmpty(percentagecomp)) {
            edittext_general_comp.setError(null);
        } else {
            edittext_general_comp.requestFocus();
            edittext_general_comp.setError(getActivity().getResources().getString(R.string.err_percentage_completion));
        }
    }


    private void setFloorNoInternals(ArrayList<InternalFloorModel> internalFloorModels) {
        internalFloorModels.clear();
        InternalFloorModel floorModelw = new InternalFloorModel();
        floorModelw.setId(0); //-1
        floorModelw.setName("Select");
        internalFloorModels.add(floorModelw);
        for (int i = 1; i <= 10; i++) {
            InternalFloorModel floorModel = new InternalFloorModel();
            floorModel.setId(i);
            int setId = i - 1;
            floorModel.setName("" + setId);
            floorModel.setFloorid(setId);
            internalFloorModels.add(floorModel);
        }
    }

    /*******
     * Display PentHouseValues from API
     * *********/
    private void DisplayFlatorPentHouseValues() {

        // Todo edittext set values from API
        setFlatPentHousePropertyDetails();
        setFlatPentHouseGeneralInternalFloors();

        // Todo Spinner internal floors selection from api
        setSpinnerInternalFloors();
    }


    public void setFlatPentHousePropertyDetails() {

        if (Singleton.getInstance().indProperty != null) {
            if (!general.isEmpty(Singleton.getInstance().indProperty.getTotalApartmentFloors()))
                editText_totalapartmentfloors.setText(Singleton.getInstance().indProperty.getTotalApartmentFloors());
            if (!general.isEmpty(Singleton.getInstance().indProperty.getTotalFlatsPerFloor()))
                editText_totalflatsperfloor.setText(Singleton.getInstance().indProperty.getTotalFlatsPerFloor());
            if (!general.isEmpty(Singleton.getInstance().indProperty.getFlatSituatedInFloor()))
                editText_flatsituatedinfloor.setText(Singleton.getInstance().indProperty.getFlatSituatedInFloor());
            if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getNoOfFloors()))) {
                int floorsno = Singleton.getInstance().indProperty.getNoOfFloors();
                /*if (floorsno == 0) {
                    floorsno = 1;
                    editText_floors.setText("" + floorsno);
                } else {
                    editText_floors.setText("" + floorsno);
                }*/
            }

            if (!general.isEmpty(Singleton.getInstance().indProperty.getDocumentLandArea()))
                editText_as_per_docs.setText(Singleton.getInstance().indProperty.getDocumentLandArea());
            if (!general.isEmpty(Singleton.getInstance().indProperty.getAvgPercentageCompletion()))
                textview_comp_total.setText(Singleton.getInstance().indProperty.getAvgPercentageCompletion());


            int docpos = Singleton.getInstance().indProperty.getDocumentLandAreaUnit();
            int actpos = Singleton.getInstance().indProperty.getMeasuredLandAreaUnit();
            if (docpos != 0) {
                Singleton.getInstance().indProperty.setDocumentLandAreaUnit(docpos);
                setMeasurementSpinner(docpos, Singleton.getInstance().measurements_list, spinnerMeasurement_doc);
            }

            if (actpos != 0) {
                Singleton.getInstance().indProperty.setMeasuredLandAreaUnit(actpos);
                setMeasurementSpinner(actpos, Singleton.getInstance().measurements_list_flat, spinner_measurement_act);
            }
        }
    }

    public void setFlatPentHouseGeneralInternalFloors() {
        if (Singleton.getInstance().indPropertyFloors.size() > 0) {
            if (!general.isEmpty(Singleton.getInstance().indPropertyFloors.get(0).getFloorName())) {
                String floorname = Singleton.getInstance().indPropertyFloors.get(0).getFloorName();
                edittext_general_floor_name.setText(floorname);
                textview_internal_floor_name_composition.setText(floorname);
            }
            if (!general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getPercentageCompletion())))
                edittext_general_comp.setText("" + Singleton.getInstance().indPropertyFloors.get(0).getPercentageCompletion());
            if (!general.isEmpty(Singleton.getInstance().indPropertyFloors.get(0).getPresentCondition()))
                edittext_general_progress.setText(Singleton.getInstance().indPropertyFloors.get(0).getPresentCondition());
            if (!general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getPropertyAge())))
                edittext_general_age.setText("" + Singleton.getInstance().indPropertyFloors.get(0).getPropertyAge());
            if (!general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getResidualLife())))
                edittext_general_life.setText("" + Singleton.getInstance().indPropertyFloors.get(0).getResidualLife());

        }


        if (Singleton.getInstance().indPropertyValuation != null) {
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getSuperBuildUpArea())) {
                edittext_general_saleablearea.setText(Singleton.getInstance().indPropertyValuation.getSuperBuildUpArea());

            }
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getBuildUpArea())) {
                edittext_general_builduparea.setText(Singleton.getInstance().indPropertyValuation.getBuildUpArea());
            }
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getCarpetArea())) {
                edittext_general_carpetarea.setText(Singleton.getInstance().indPropertyValuation.getCarpetArea());

            }
        }
    }

    private void setSpinnerInternalFloors() {
        if (Singleton.getInstance().indPropertyFloors != null) {
            if (Singleton.getInstance().indPropertyFloors.size() > 0) {

                if (!general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatHallNo()))) {
                    internal_hall_dinning.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatHallNo()));
                }

                if (!general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatKitchenNo()))) {
                    internal_kitchen.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatKitchenNo()));
                }

                if (!general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatBedroomNo()))) {
                    internal_bedroom.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatBedroomNo()));
                }

                if (!general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatBathNo()))) {
                    internal_bath.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatBathNo()));
                }

                if (!general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getOfficeNo()))) {
                    internal_shop_office.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getOfficeNo()));
                }

                /*int halldinning = Singleton.getInstance().indPropertyFloors.get(0).getFlatHallNo();
                int kitchen = Singleton.getInstance().indPropertyFloors.get(0).getFlatKitchenNo();
                int bedroom = Singleton.getInstance().indPropertyFloors.get(0).getFlatBedroomNo();
                int bath = Singleton.getInstance().indPropertyFloors.get(0).getFlatBathNo();
                int shopno = Singleton.getInstance().indPropertyFloors.get(0).getShopNo();

                setFloorNumbersSpinner(halldinning, Singleton.getInstance().internalFloorHalldining, internal_hall_dinning);
                setFloorNumbersSpinner(kitchen, Singleton.getInstance().internalFloorKitchen, internal_kitchen);
                setFloorNumbersSpinner(bedroom, Singleton.getInstance().internalFloorBedroom, internal_bedroom);
                setFloorNumbersSpinner(bath, Singleton.getInstance().internalFloorBath, internal_bath);
                setFloorNumbersSpinner(shopno, Singleton.getInstance().internalFloorshopOffice, internal_shop_office);*/


               /* int constructionid = Singleton.getInstance().indPropertyFloors.get(0).getConstructionStageId();
                setConstructionstageSpinner(constructionid, Singleton.getInstance().constructions_list, stageSpinner);*/
            }

        }
    }

    private void setMeasurementSpinner(int id, ArrayList<Measurements> measurements, Spinner internalspinner) {
        if (measurements.size() > 0) {
            for (int i = 0; i < measurements.size(); i++) {
                if (measurements.get(i).getName().equalsIgnoreCase("Select")) {
                } else {
                    int floorno = measurements.get(i).getMeasureUnitId();
                    if (id == floorno) {
                        internalspinner.setSelection(i);
                    }
                }
            }
        }
    }

    public static void getFlatPentHousePropertyDetails() {

        String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        if (Singleton.getInstance().indProperty != null) {

            String total_appfloors = editText_totalapartmentfloors.getText().toString().trim();
            String total_flatsperfloor = editText_totalflatsperfloor.getText().toString().trim();
            String flatsituated = editText_flatsituatedinfloor.getText().toString().trim();
            String asperdocs = editText_as_per_docs.getText().toString().trim();

            if (!general.isEmpty(caseid))
                Singleton.getInstance().indProperty.setCaseId(Integer.valueOf(caseid));
            Singleton.getInstance().indProperty.setTotalApartmentFloors(total_appfloors);
            Singleton.getInstance().indProperty.setTotalFlatsPerFloor(total_flatsperfloor);
            Singleton.getInstance().indProperty.setFlatSituatedInFloor(flatsituated);
            /*if (!general.isEmpty(floors))*/
            Singleton.getInstance().indProperty.setNoOfFloors(1);
            Singleton.getInstance().indProperty.setDocumentLandArea(asperdocs);
            //Singleton.getInstance().indProperty.setAvgPercentageCompletion(textview_comp_total.getText().toString().trim());
            if (!general.isEmpty(edittext_general_comp.getText().toString()))
                Singleton.getInstance().indProperty.setAvgPercentageCompletion(edittext_general_comp.getText().toString().trim());


            /* *************
             * Get Spinner single select value
             * *********************/
            int pos = spinnerMeasurement_doc.getSelectedItemPosition();
            if (pos >= 0) {
                int measureUnitId = Singleton.getInstance().measurements_list.get(pos).getMeasureUnitId();
                if (measureUnitId != 0) {
                    Singleton.getInstance().indProperty.setDocumentLandAreaUnit(measureUnitId);
                } else {
                    Singleton.getInstance().indProperty.setDocumentLandAreaUnit(0);
                }
            }

            int posact = spinner_measurement_act.getSelectedItemPosition();
            int UnitId = Singleton.getInstance().measurements_list_flat.get(posact).getMeasureUnitId();
            if (UnitId != 0) {
                Singleton.getInstance().indProperty.setMeasuredLandAreaUnit(UnitId);
                Singleton.getInstance().indPropertyValuation.setCarpetAreaUnit("" + UnitId);
                Singleton.getInstance().indPropertyValuation.setConstructionDLCRateUnit("" + UnitId);
            } else {
                Singleton.getInstance().indProperty.setMeasuredLandAreaUnit(0);
                Singleton.getInstance().indPropertyValuation.setCarpetAreaUnit("");
                Singleton.getInstance().indPropertyValuation.setConstructionDLCRateUnit("");
            }

        }
    }

    public static void getFlatPentHouseGeneralInternalFloors() {

        String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

        String generalfloorname = edittext_general_floor_name.getText().toString().trim();
        String flatcomp = edittext_general_comp.getText().toString().trim();
        String flatprogress = edittext_general_progress.getText().toString().trim();
        String flatage = edittext_general_age.getText().toString().trim();
        String flatlife = edittext_general_life.getText().toString().trim();

        if (Singleton.getInstance().indPropertyFloors != null) {

            if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                IndPropertyFloor indPropertyFloor = Singleton.getInstance().indPropertyFloors.get(0);
                if (!general.isEmpty(caseid))
                    indPropertyFloor.setCaseId(Integer.valueOf(caseid));
                indPropertyFloor.setFloorName(generalfloorname);
                if (!general.isEmpty(flatcomp)) {
                    indPropertyFloor.setPercentageCompletion(Integer.valueOf(flatcomp));
                } else {
                    indPropertyFloor.setPercentageCompletion(null);
                }

                indPropertyFloor.setPresentCondition(flatprogress);


                if (!general.isEmpty(flatage)) {
                    indPropertyFloor.setPropertyAge(Integer.valueOf(flatage));
                } else {
                    indPropertyFloor.setPropertyAge(null);
                }
                if (!general.isEmpty(flatlife)) {
                    indPropertyFloor.setResidualLife(Integer.valueOf(flatlife));
                } else {
                    indPropertyFloor.setResidualLife(null);
                }

                indPropertyFloor.setFloorNo(1);

                /* set */
                String charsequence_internal_hall_dinning = internal_hall_dinning.getText().toString();
                if (!general.isEmpty(charsequence_internal_hall_dinning)) {
                    indPropertyFloor.setFlatHallNo(Integer.valueOf(charsequence_internal_hall_dinning));
                } else {
                    indPropertyFloor.setFlatHallNo(0);
                }

                String charsequence_internal_kitchen = internal_kitchen.getText().toString();
                if (!general.isEmpty(charsequence_internal_kitchen)) {
                    indPropertyFloor.setFlatKitchenNo(Integer.valueOf(charsequence_internal_kitchen));
                } else {
                    indPropertyFloor.setFlatKitchenNo(0);
                }

                String charsequence_internal_bedroom = internal_bedroom.getText().toString();
                if (!general.isEmpty(charsequence_internal_bedroom)) {
                    indPropertyFloor.setFlatBedroomNo(Integer.valueOf(charsequence_internal_bedroom));
                } else {
                    indPropertyFloor.setFlatBedroomNo(0);
                }

                String charsequence_internal_bath = internal_bath.getText().toString();
                if (!general.isEmpty(charsequence_internal_bath)) {
                    indPropertyFloor.setFlatBathNo(Integer.valueOf(charsequence_internal_bath));
                } else {
                    indPropertyFloor.setFlatBathNo(0);
                }

                String charsequence_internal_shop_office = internal_shop_office.getText().toString();
                if (!general.isEmpty(charsequence_internal_shop_office)) {
                    indPropertyFloor.setOfficeNo(Integer.valueOf(charsequence_internal_shop_office));
                } else {
                    indPropertyFloor.setOfficeNo(0);
                }

                Singleton.getInstance().indPropertyFloors.set(0, indPropertyFloor);
            } else {

                IndPropertyFloor indPropertyFloor = new IndPropertyFloor();
                if (!general.isEmpty(caseid))
                    indPropertyFloor.setCaseId(Integer.valueOf(caseid));
                indPropertyFloor.setFloorName(generalfloorname);
                if (!general.isEmpty(flatcomp)) {
                    indPropertyFloor.setPercentageCompletion(Integer.valueOf(flatcomp));
                } else {
                    indPropertyFloor.setPercentageCompletion(null);
                }
                indPropertyFloor.setPresentCondition(flatprogress);

                if (!general.isEmpty(flatage)) {
                    indPropertyFloor.setPropertyAge(Integer.valueOf(flatage));
                } else {
                    indPropertyFloor.setPropertyAge(null);
                }

                if (!general.isEmpty(flatlife)) {
                    indPropertyFloor.setResidualLife(Integer.valueOf(flatlife));
                } else {
                    indPropertyFloor.setResidualLife(null);
                }


                indPropertyFloor.setFloorNo(1);


                String charsequence_internal_hall_dinning = internal_hall_dinning.getText().toString();
                if (!general.isEmpty(charsequence_internal_hall_dinning)) {
                    indPropertyFloor.setFlatHallNo(Integer.valueOf(charsequence_internal_hall_dinning));
                } else {
                    indPropertyFloor.setFlatHallNo(0);
                }

                String charsequence_internal_kitchen = internal_kitchen.getText().toString();
                if (!general.isEmpty(charsequence_internal_kitchen)) {
                    indPropertyFloor.setFlatKitchenNo(Integer.valueOf(charsequence_internal_kitchen));
                } else {
                    indPropertyFloor.setFlatKitchenNo(0);
                }

                String charsequence_internal_bedroom = internal_bedroom.getText().toString();
                if (!general.isEmpty(charsequence_internal_bedroom)) {
                    indPropertyFloor.setFlatBedroomNo(Integer.valueOf(charsequence_internal_bedroom));
                } else {
                    indPropertyFloor.setFlatBedroomNo(0);
                }

                String charsequence_internal_bath = internal_bath.getText().toString();
                if (!general.isEmpty(charsequence_internal_bath)) {
                    indPropertyFloor.setFlatBathNo(Integer.valueOf(charsequence_internal_bath));
                } else {
                    indPropertyFloor.setFlatBathNo(0);
                }

                String charsequence_internal_shop_office = internal_shop_office.getText().toString();
                if (!general.isEmpty(charsequence_internal_shop_office)) {
                    indPropertyFloor.setOfficeNo(Integer.valueOf(charsequence_internal_shop_office));
                } else {
                    indPropertyFloor.setOfficeNo(0);
                }


                Singleton.getInstance().indPropertyFloors.add(indPropertyFloor);
            }
        }


        if (Singleton.getInstance().indPropertyValuation != null) {

            String saleablearea = edittext_general_saleablearea.getText().toString().trim();
            String builduparea = edittext_general_builduparea.getText().toString().trim();
            String carpetarea = edittext_general_carpetarea.getText().toString().trim();

            if (!general.isEmpty(caseid))
                Singleton.getInstance().indPropertyValuation.setCaseId(Integer.valueOf(caseid));
            Singleton.getInstance().indPropertyValuation.setSuperBuildUpArea(saleablearea);
            Singleton.getInstance().indPropertyValuation.setBuildUpArea(builduparea);
            Singleton.getInstance().indPropertyValuation.setCarpetArea(carpetarea);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v == spinnerMeasurement_doc || v == stageSpinner) {
            hideSoftKeyboard(spinnerMeasurement_doc);
        }
        return false;
    }

    public void PostPentHouseValues() {
        getFlatPentHousePropertyDetails();
        getFlatPentHouseGeneralInternalFloors();
    }


    private void carpetloading_per(final TextView textView, final String type_is) {
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

        RadioButton id_radio_80 = (RadioButton) dialog.findViewById(R.id.id_radio_80);
        RadioButton id_radio_85 = (RadioButton) dialog.findViewById(R.id.id_radio_85);
        RadioButton id_radio_90 = (RadioButton) dialog.findViewById(R.id.id_radio_90);
        RadioButton id_radio_95 = (RadioButton) dialog.findViewById(R.id.id_radio_95);
        RadioButton id_radio_100 = (RadioButton) dialog.findViewById(R.id.id_radio_100);


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

        id_radio_80.setTypeface(general.mediumtypeface());
        id_radio_85.setTypeface(general.mediumtypeface());
        id_radio_90.setTypeface(general.mediumtypeface());
        id_radio_95.setTypeface(general.mediumtypeface());
        id_radio_100.setTypeface(general.mediumtypeface());

        id_radio_95.setVisibility(View.GONE);
        id_radio_100.setVisibility(View.GONE);

        boolean is_empty = true;
        String selected_value = "";


        if (type_is.equalsIgnoreCase("carpet_per")) {
            id_radio_80.setVisibility(View.VISIBLE);
            id_radio_85.setVisibility(View.VISIBLE);
            id_radio_90.setVisibility(View.VISIBLE);
            // Carpet Percentage
            popuptitle.setText(getActivity().getResources().getString(R.string.carpet_area_per));
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getCarpetAreaPercentage())) {
                is_empty = false;
                selected_value = Singleton.getInstance().indPropertyValuation.getCarpetAreaPercentage();
            }
        } else {
            id_radio_80.setVisibility(View.GONE);
            id_radio_85.setVisibility(View.GONE);
            id_radio_90.setVisibility(View.GONE);
            // Loading Factor
            popuptitle.setText(getActivity().getResources().getString(R.string.loading_factor));
            if (!general.isEmpty(Singleton.getInstance().indPropertyValuation.getSaleableLoadingPercentage())) {
                is_empty = false;
                selected_value = Singleton.getInstance().indPropertyValuation.getSaleableLoadingPercentage();
            }
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
                if (type_is.equalsIgnoreCase("carpet_per")) {
                    // Carpet Percentage
                    if (str_radiogenearal.equalsIgnoreCase("Select")) {
                        textView.setText(str_radiogenearal);
                        Singleton.getInstance().indPropertyValuation.setCarpetAreaPercentage("");
                        // set and call the carpet value
                        val_carpetloading_per = "";
                        fun_carpet_value();
                    } else {
                        String loadingStr = str_radiogenearal.replace("%", "");
                        textView.setText(loadingStr);
                        Singleton.getInstance().indPropertyValuation.setCarpetAreaPercentage(loadingStr);
                        // set and call the carpet value
                        val_carpetloading_per = loadingStr;
                        fun_carpet_value();
                    }
                } else {
                    // Loading Factor
                    if (str_radiogenearal.equalsIgnoreCase("Select")) {
                        textView.setText(str_radiogenearal);
                        Singleton.getInstance().indPropertyValuation.setSaleableLoadingPercentage("");
                        // set and call the carpet value
                        val_textview_loading_factor_per = "";
                    } else {
                        String loadingStr = str_radiogenearal.replace("%", "");
                        textView.setText(loadingStr);
                        Singleton.getInstance().indPropertyValuation.setSaleableLoadingPercentage(loadingStr);
                        // set and call the carpet value
                        val_textview_loading_factor_per = loadingStr;
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

    private void fun_carpet_value() {
        String val_buildup = edittext_general_builduparea.getText().toString();
        String val_saleable = edittext_general_saleablearea.getText().toString();
        if ((!general.isEmpty(val_carpetloading_per)) && (!general.isEmpty(val_carpetloading_type))) {
            if (val_carpetloading_type.equalsIgnoreCase("1")) {
                // buildup
                float sumtotal = 0;
                sumtotal = (general.convertTofloat(val_carpetloading_per) / 100) * (general.convertTofloat(val_buildup));
                /*int sum_total = 0;
                sum_total = general.convertToRoundoff(sumtotal);*/

                // split the decimal point after dot 2 number
                String decimalFormat = new DecimalFormat("##.##").format(sumtotal);

                edittext_general_carpetarea.setText("" + decimalFormat);
                // send the value
                FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
                fragmentValuationPenthouse.setEdittextCarpetArea("" + decimalFormat);
                checkSpinnerAreaType(edittext_general_carpetarea.getText().toString(), getResources().getString(R.string.carpet));
            } else if (val_carpetloading_type.equalsIgnoreCase("2")) {
                // saleable
                float sumtotal = 0;
                sumtotal = (general.convertTofloat(val_carpetloading_per) / 100) * (general.convertTofloat(val_saleable));
                /*int sum_total = 0;
                sum_total = general.convertToRoundoff(sumtotal);*/

                // split the decimal point after dot 2 number
                String decimalFormat = new DecimalFormat("##.##").format(sumtotal);

                edittext_general_carpetarea.setText("" + decimalFormat);
                // send the value
                FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
                fragmentValuationPenthouse.setEdittextCarpetArea("" + decimalFormat);
                checkSpinnerAreaType(edittext_general_carpetarea.getText().toString(), getResources().getString(R.string.carpet));
            }
        }
    }



    // TODO - calc function
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

                org.mozilla.javascript.Context rhino = org.mozilla.javascript.Context.enter();
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
                            // BuildupArea
                            edittext_general_builduparea.setText(my_val_str);
                            FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
                            fragmentValuationPenthouse.setEdittextBuildupArea(my_val_str);
                            checkSpinnerAreaType(my_val_str, getResources().getString(R.string.builtup));
                            fun_carpet_value();
                        } else if (type_is == 2) {
                            // SaleableArea
                            edittext_general_saleablearea.setText(my_val_str);
                            FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
                            fragmentValuationPenthouse.setEdittextSaleableArea(my_val_str);
                            checkSpinnerAreaType(my_val_str, getResources().getString(R.string.Saleable));
                            fun_carpet_value();
                        } else if (type_is == 3) {
                            // CarpetArea
                            edittext_general_carpetarea.setText(my_val_str);
                            FragmentValuationPenthouse fragmentValuationPenthouse = new FragmentValuationPenthouse();
                            fragmentValuationPenthouse.setEdittextCarpetArea(my_val_str);
                            checkSpinnerAreaType(my_val_str, getResources().getString(R.string.carpet));
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
