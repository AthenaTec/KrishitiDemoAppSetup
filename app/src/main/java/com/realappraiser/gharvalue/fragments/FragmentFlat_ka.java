package com.realappraiser.gharvalue.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Base64;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iceteck.silicompressorr.SiliCompressor;
import com.realappraiser.gharvalue.AppDatabase;
import com.realappraiser.gharvalue.MyApplication;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.activities.MultiPhotoSelectActivity;
import com.realappraiser.gharvalue.adapter.ImageAdapter;
import com.realappraiser.gharvalue.adapter.PropertyActualUsageAdapter;
import com.realappraiser.gharvalue.communicator.DataModel;
import com.realappraiser.gharvalue.communicator.DataResponse;
import com.realappraiser.gharvalue.communicator.JsonRequestData;
import com.realappraiser.gharvalue.communicator.RequestParam;
import com.realappraiser.gharvalue.communicator.ResponseParser;
import com.realappraiser.gharvalue.communicator.TaskCompleteListener;
import com.realappraiser.gharvalue.communicator.WebserviceCommunicator;
import com.realappraiser.gharvalue.model.Construction;
import com.realappraiser.gharvalue.model.GetPhoto;
import com.realappraiser.gharvalue.model.GetPhoto_measurment;
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
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import org.json.JSONArray;
import org.mozilla.javascript.Scriptable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class FragmentFlat_ka extends Fragment implements View.OnTouchListener {

    // TODO General class to call typeface and all...
    public static General general;
    // @BindView(R.id.editText_totalapartmentfloors)
    public static EditText editText_totalapartmentfloors;
    public static EditText editText_totalflatsperfloor;
    public static EditText editText_flatsituatedinfloor;
    //public static EditText editText_floors;
    public static EditText editText_as_per_docs;

    public static EditText edittext_general_comp;
    public static EditText edittext_general_progress;
    public static EditText edittext_general_age;
    public static EditText edittext_general_life;

    public static EditText edittext_general_mezzaninearea;
    public static EditText edittext_general_otlaarea;
    public static EditText edittext_general_terracearea;
    public static EditText edittext_general_balconyarea;
    public static EditText edittext_general_saleablearea;
    public static EditText edittext_general_builduparea;
    public static EditText edittext_general_carpetarea;
    public static TextView textview_comp_total;

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
    @BindView(R.id.saleableloading)
    TextView saleableloading;

    @BindView(R.id.textview_generl_info_measu)
    TextView textview_generl_info_measu;
    @BindView(R.id.textview_stage)
    TextView textview_stage;
    @BindView(R.id.textview_comp)
    TextView textview_comp;
    @BindView(R.id.textview_progress)
    TextView textview_progress;
    //    @BindView(R.id.textview_carpetarea)
//    TextView textview_carpetarea;
//    @BindView(R.id.textview_builduparea)
//    TextView textview_builduparea;
//    @BindView(R.id.textview_saleablearea)
//    TextView textview_saleablearea;
    @BindView(R.id.textview_age)
    TextView textview_age;
    @BindView(R.id.textview_life)
    TextView textview_life;

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
    public static EditText internal_hall_dinning;
    //@BindView(R.id.internal_kitchen)
    public static EditText internal_kitchen;
    //@BindView(R.id.internal_bedroom)
    public static EditText internal_bedroom;
    //@BindView(R.id.internal_bath)
    public static EditText internal_bath;
    //@BindView(R.id.internal_shop_office)
    public static EditText internal_shop_office;
    public static EditText internal_dinning;
    public static EditText internal_wcs;
    public static EditText internal_attachbath;
    public static EditText internal_balcony;
    public static EditText internal_fbs;
    public static EditText internal_dbs;
    public static EditText internal_terrace;
    public static EditText internal_passage;

    @SuppressLint("StaticFieldLeak")
    public static EditText editText_Floor_details;

    // actual_usage
    ArrayList<PropertyActualUsage> Inter_floors_list = new ArrayList<>();
    public static String measurment_floor_id = "0";

    // san - for Image
    @BindView(R.id.upload_image)
    TextView upload_image;
    @BindView(R.id.upload_div)
    RelativeLayout upload_div;
    @BindView(R.id.thumbnail_image)
    ImageView thumbnail_image;
    @BindView(R.id.thumbnail_text)
    EditText thumbnail_text;
    @BindView(R.id.thumbnail_close)
    ImageView thumbnail_close;

    private String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };
    protected static final int REQUEST_CHECK_SETTINGS = 1000;
    private static final int CAMERA_REQUEST = 123;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 4;
    private int PICK_IMAGE_REQUEST = 2;
    public final static int REQUEST_CODE = 1234;
    Uri imageUri_final;
    private int PLACE_PICKER_REQUEST = 1;
    protected static final String TAG = "PhotoLatLong";
    private static final int GALLERY_REQUEST = 2;
    private long current_time_cam_image;
    private String mBase64 = "";
    public Dialog dialog;

    public ArrayList<GetPhoto_measurment> GetImage_list_flat_response = new ArrayList<>();
    int PropertyId_is = 0;
    boolean is_offline = false;
    AppDatabase appDatabase = null;

    @BindView(R.id.textview_error_photo)
    TextView textview_error_photo;
    int caseid_int = 0;
    public boolean enable_offline_button;


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


    // calc click function
    @BindView(R.id.open_calc_plotarea)
    ImageView open_calc_plotarea;
    @BindView(R.id.open_calc_carpetarea)
    ImageView open_calc_carpetarea;
    @BindView(R.id.open_calc_builduparea)
    ImageView open_calc_builduparea;
    @BindView(R.id.open_calc_saleablearea)
    ImageView open_calc_saleablearea;
    @BindView(R.id.open_calc_terracearea)
    ImageView open_calc_terracearea;
    @BindView(R.id.open_calc_balconyarea)
    ImageView open_calc_balconyarea;

    @BindView(R.id.open_calc_otlaarea)
    ImageView open_calc_otlaarea;
    @BindView(R.id.open_calc_mezzaninearea)
    ImageView open_calc_mezzaninearea;

    @BindView(R.id.otla_div)
    RelativeLayout otla_div;
    @BindView(R.id.mezzanine_div)
    RelativeLayout mezzanine_div;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_flat_ka, container, false);
        ButterKnife.bind(this, view);
        general = new General(getActivity());

        // Hide the otla and mezzanine div on 16,17,18,19 report type only
        int ReportType = Singleton.getInstance().aCase.getReportType();
        if (ReportType == 7 || ReportType == 8) {
            // Visible
            otla_div.setVisibility(View.VISIBLE);
            mezzanine_div.setVisibility(View.VISIBLE);
        } else {
            otla_div.setVisibility(View.INVISIBLE);
            mezzanine_div.setVisibility(View.INVISIBLE);
        }


        initViewsStatic(view);
        initViews();
        InitiatePentHouseValues();
        AreaTextWatcher();

        if (MyApplication.getAppContext() != null) {
            appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
        }

        // check the case is offline (or) online
        is_offline = SettingsUtils.getInstance().getValue(SettingsUtils.is_offline, false);

        // Property
        if (!General.isEmpty(SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, ""))) {
            PropertyId_is = Integer.parseInt(SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, ""));
        }

        // check the offline module is present or not
        enable_offline_button = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_ENABLE_OFFLINE, false);

        // CaseID to interger
        String caseid_str = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        if (!General.isEmpty(caseid_str)) {
            caseid_int = Integer.parseInt(caseid_str);
        }

        set_uploadImage_perview();

        // TODO -  call the mandatory_validation
        if (Singleton.getInstance().enable_validation_error) {
            set_pentflathouse_mandatory();
        }


        // san - for Image
        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload_popup();
            }
        });

        upload_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!General.isEmpty(mBase64)) {
                    view_popup();
                }
            }
        });


        saleableloading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(saleableloading);
                saleableloading_popup(saleableloading);
            }
        });

        //calc click function
        open_calc_plotarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*call_calc_function(1);*/
                Calculation_Popup_New(1);
            }
        });

        open_calc_carpetarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*call_calc_function(1);*/
                Calculation_Popup_New(2);
            }
        });

        open_calc_builduparea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*call_calc_function(1);*/
                Calculation_Popup_New(3);
            }
        });

        open_calc_saleablearea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*call_calc_function(1);*/
                Calculation_Popup_New(4);
            }
        });

        open_calc_terracearea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*call_calc_function(1);*/
                Calculation_Popup_New(5);
            }
        });

        open_calc_balconyarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*call_calc_function(1);*/
                Calculation_Popup_New(6);
            }
        });

        open_calc_otlaarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*call_calc_function(1);*/
                Calculation_Popup_New(7);
            }
        });

        open_calc_mezzaninearea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*call_calc_function(1);*/
                Calculation_Popup_New(8);
            }
        });
        return view;
    }

    private void initViewsStatic(View view) {


        internal_hall_dinning = view.findViewById(R.id.internal_hall_dinning);
        internal_kitchen = view.findViewById(R.id.internal_kitchen);
        internal_bedroom = view.findViewById(R.id.internal_bedroom);
        internal_bath = view.findViewById(R.id.internal_bath);
        internal_shop_office = view.findViewById(R.id.internal_shop_office);
        // new ID added
        internal_dinning = view.findViewById(R.id.internal_dinning);
        internal_wcs = view.findViewById(R.id.internal_wcs);
        internal_attachbath = view.findViewById(R.id.internal_attachbath);
        internal_balcony = view.findViewById(R.id.internal_balcony);
        internal_fbs = view.findViewById(R.id.internal_fbs);
        internal_dbs = view.findViewById(R.id.internal_dbs);
        internal_terrace = view.findViewById(R.id.internal_terrace);
        internal_passage = view.findViewById(R.id.internal_passage);

        editText_totalapartmentfloors = view.findViewById(R.id.editText_totalapartmentfloors);
        editText_totalflatsperfloor = view.findViewById(R.id.editText_totalflatsperfloor);
        editText_flatsituatedinfloor = view.findViewById(R.id.editText_flatsituatedinfloor);
        //editText_floors = (EditText) view.findViewById(R.id.editText_floors);
        editText_as_per_docs = view.findViewById(R.id.editText_as_per_docs);
        edittext_general_comp = view.findViewById(R.id.edittext_general_comp);
        edittext_general_progress = view.findViewById(R.id.edittext_general_progress);
        edittext_general_age = view.findViewById(R.id.edittext_general_age);
        edittext_general_life = view.findViewById(R.id.edittext_general_life);
        edittext_general_saleablearea = view.findViewById(R.id.edittext_general_saleablearea);
        edittext_general_terracearea = view.findViewById(R.id.edittext_general_terracearea);
        edittext_general_mezzaninearea = view.findViewById(R.id.edittext_general_mezzaninearea);
        edittext_general_otlaarea = view.findViewById(R.id.edittext_general_otlaarea);
        edittext_general_balconyarea = view.findViewById(R.id.edittext_general_balconyarea);
        edittext_general_builduparea = view.findViewById(R.id.edittext_general_builduparea);
        edittext_general_carpetarea = view.findViewById(R.id.edittext_general_carpetarea);
        textview_comp_total = view.findViewById(R.id.textview_comp_total);
        spinnerMeasurement_doc = view.findViewById(R.id.spinnerMeasurement_doc);
        spinner_measurement_act = view.findViewById(R.id.spinner_measurement_act);
        stageSpinner = view.findViewById(R.id.stageSpinner);
        editText_Floor_details = view.findViewById(R.id.editText_Floor_details);


        //  limit the 2 char after the decimal point
        editText_as_per_docs.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(10, 2)});
        edittext_general_carpetarea.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});
        edittext_general_builduparea.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});
        edittext_general_saleablearea.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});
        edittext_general_terracearea.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});
        edittext_general_mezzaninearea.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});
        edittext_general_otlaarea.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});
        edittext_general_balconyarea.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});

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
        saleableloading.setTypeface(general.regulartypeface());

        textview_generl_info_measu.setTypeface(general.mediumtypeface());
        textview_internal_composition.setTypeface(general.mediumtypeface());
        edittext_general_comp.setTypeface(general.regulartypeface());
        edittext_general_progress.setTypeface(general.regulartypeface());
        edittext_general_age.setTypeface(general.regulartypeface());
        edittext_general_life.setTypeface(general.regulartypeface());
        textview_stage.setTypeface(general.regulartypeface());
        textview_comp.setTypeface(general.regulartypeface());
        textview_progress.setTypeface(general.regulartypeface());
        // textview_carpetarea.setTypeface(general.regulartypeface());
        // textview_builduparea.setTypeface(general.regulartypeface());
        // textview_saleablearea.setTypeface(general.regulartypeface());
        textview_age.setTypeface(general.regulartypeface());
        textview_life.setTypeface(general.regulartypeface());
        textview_average.setTypeface(general.regulartypeface());
        textview_comp_total.setTypeface(general.regulartypeface());
        textview_total.setTypeface(general.regulartypeface());
        textview_doc_total.setTypeface(general.regulartypeface());
        textview_actual_total.setTypeface(general.regulartypeface());
        textview_hall_dinning.setTypeface(general.regulartypeface());
        textview_kitchen.setTypeface(general.regulartypeface());
        textview_bedroom.setTypeface(general.regulartypeface());
        textview_bath.setTypeface(general.regulartypeface());
        textview_shop_office.setTypeface(general.regulartypeface());
        edittext_general_saleablearea.setTypeface(general.regulartypeface());
        edittext_general_terracearea.setTypeface(general.regulartypeface());
        edittext_general_mezzaninearea.setTypeface(general.regulartypeface());
        edittext_general_otlaarea.setTypeface(general.regulartypeface());
        edittext_general_balconyarea.setTypeface(general.regulartypeface());
        edittext_general_builduparea.setTypeface(general.regulartypeface());
        edittext_general_carpetarea.setTypeface(general.regulartypeface());
        editText_Floor_details.setTypeface(general.regulartypeface());
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
                    if (!General.isEmpty(value)) {
                        FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                        fragmentValuationPenthouse.setEdittextAsPerCompletion(value);
                    }
                }
            }
        });

        edittext_general_age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String age = charSequence.toString();
                if (!General.isEmpty(age)) {
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

        //Terrace
        edittext_general_terracearea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final String terrace_area = charSequence.toString();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FragmentValuationPenthouse_ka fragmentValuationPenthouse_ka = new FragmentValuationPenthouse_ka();
                        FragmentValuationPenthouse_ka.edittext_terrace_area.setText(terrace_area);
                    }
                }, 1500);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //otla
        edittext_general_otlaarea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final String terrace_area = charSequence.toString();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FragmentValuationPenthouse_ka fragmentValuationPenthouse_ka = new FragmentValuationPenthouse_ka();
                        FragmentValuationPenthouse_ka.edittext_otla_area.setText(terrace_area);
                    }
                }, 1500);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //mezzanine
        edittext_general_mezzaninearea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final String terrace_area = charSequence.toString();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FragmentValuationPenthouse_ka fragmentValuationPenthouse_ka = new FragmentValuationPenthouse_ka();
                        FragmentValuationPenthouse_ka.edittext_mezzanine_area.setText(terrace_area);
                    }
                }, 1500);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Balcony
        edittext_general_balconyarea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final String balcony_area = charSequence.toString();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FragmentValuationPenthouse_ka fragmentValuationPenthouse_ka = new FragmentValuationPenthouse_ka();
                        FragmentValuationPenthouse_ka.edittext_balcony_area.setText(balcony_area);
                    }
                }, 1500);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (!General.isEmpty(Singleton.getInstance().indProperty.getFloorDetails())) {
            editText_Floor_details.setText(Singleton.getInstance().indProperty.getFloorDetails());
        }


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
                FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                fragmentValuationPenthouse.setEdittextCarpetArea_ka(charSequence.toString());
            }


            @Override
            public void afterTextChanged(Editable editable) {
                String val_is = editable.toString();
                checkSpinnerAreaType(val_is, getResources().getString(R.string.carpet));

            }
        });

        edittext_general_carpetarea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String toString = edittext_general_carpetarea.getText().toString();
                    if (!General.isEmpty(toString)) {
                        // Buildup
                        float carpet_float = general.convertTofloat(toString);
                        float flaot_val = general.convertTofloat("1.2");
                        float builduparea_float = (carpet_float) * (flaot_val);
                        // take two digit after decimal value
                        double builduparea_double = Double.parseDouble(new DecimalFormat("##.##").format(builduparea_float));
                        String builduparea_str = String.valueOf(builduparea_double);

                        edittext_general_builduparea.setText(builduparea_str);
                        // Buildup Valuation
                        FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                        fragmentValuationPenthouse.setEdittextBuildupArea_ka(builduparea_str);
                        checkSpinnerAreaType(builduparea_str, getResources().getString(R.string.builtup));
                        // Government value
                        fragmentValuationPenthouse.setGovernmentvalue_area_ka(builduparea_str);

                        // saleableloading - popup
                        String saleableloading_popup = saleableloading.getText().toString();
                        if (!saleableloading_popup.equalsIgnoreCase("Select")) {
                            int saleableloading_int = Integer.parseInt(saleableloading_popup);
                            int add_value = 100 + saleableloading_int;
                            double result_double = ((double) add_value) / ((double) 100);
                            String result_str = String.valueOf(result_double);
                            float saleableloading_float = Float.parseFloat(result_str);
                            float saleablearea_float = (carpet_float) * (saleableloading_float);
                            double saleablearea_double = Double.parseDouble(new DecimalFormat("##.##").format(saleablearea_float));
                            String saleablearea_str = String.valueOf(saleablearea_double);

                            edittext_general_saleablearea.setText(saleablearea_str);
                            // Saleable Valuation
                            fragmentValuationPenthouse.setEdittextSaleableArea_ka(saleablearea_str);
                            checkSpinnerAreaType(saleablearea_str, getResources().getString(R.string.Saleable));

                        }

                    }
                }
            }
        });

        edittext_general_builduparea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                fragmentValuationPenthouse.setEdittextBuildupArea_ka(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String val_is = editable.toString();
                checkSpinnerAreaType(val_is, getResources().getString(R.string.builtup));
            }
        });

        edittext_general_builduparea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String toString = edittext_general_builduparea.getText().toString();
                    FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                    fragmentValuationPenthouse.setGovernmentvalue_area_ka(toString);
                }
            }
        });

        edittext_general_saleablearea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                fragmentValuationPenthouse.setEdittextSaleableArea_ka(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String val_is = editable.toString();
                checkSpinnerAreaType(val_is, getResources().getString(R.string.Saleable));
            }
        });
    }

    private void checkSpinnerAreaType(String editable, String area) {
        Log.e("my_editable", "is: " + editable);

        if (!General.isEmpty(editable)) {
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

        FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
        fragmentValuationPenthouse.AreaTypeSpinner_ka(Singleton.getInstance().areaType, area);
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
                    FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                    FragmentValuationPenthouse_ka.edittext_measurementunit.setText("");
                } else {
                    FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                    FragmentValuationPenthouse_ka.edittext_measurementunit.setText("" + Singleton.getInstance().measurements_list_flat.get(position).getName());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void getConstructionStageSpinner() {
        ArrayAdapter<Construction> adapterStageConstruct = new ArrayAdapter<>(getActivity(),
                R.layout.row_spinner_item, Singleton.getInstance().constructions_list);

        adapterStageConstruct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        stageSpinner.setAdapter(adapterStageConstruct);
        stageSpinner.setOnTouchListener(this);

        if (Singleton.getInstance().indPropertyFloors.size() > 0) {
            if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getConstructionStageId()))) {
                final int constructionid = Singleton.getInstance().indPropertyFloors.get(0).getConstructionStageId();
                stageSpinner.setSelection(constructionid, false);

                /************/
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (constructionid == 1) {
                            // Completed
                            FragmentValuationPenthouse_ka.textview_realizable_head.setVisibility(View.VISIBLE);
                            FragmentValuationPenthouse_ka.realizable_distress_completed_lay.setVisibility(View.VISIBLE);
                            FragmentValuationPenthouse_ka.realizable_distress_underconstrust_lay.setVisibility(View.GONE);
                            edittext_general_comp.setEnabled(false);
                            edittext_general_progress.setEnabled(false);
                            edittext_general_age.setEnabled(true);
                            edittext_general_life.setEnabled(true);
                        } else if (constructionid == 2) {
                            // UnderConstruction
                            FragmentValuationPenthouse_ka.textview_realizable_head.setVisibility(View.VISIBLE);
                            FragmentValuationPenthouse_ka.realizable_distress_completed_lay.setVisibility(View.GONE);
                            FragmentValuationPenthouse_ka.realizable_distress_underconstrust_lay.setVisibility(View.VISIBLE);
                            edittext_general_comp.setEnabled(true);
                            edittext_general_progress.setEnabled(true);
                            edittext_general_age.setEnabled(false);
                            edittext_general_life.setEnabled(false);

                        }
                    }
                }, 1500);

            } else {
                stageSpinner.setSelection(0, false);

                /************/
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FragmentValuationPenthouse_ka.textview_realizable_head.setVisibility(View.GONE);
                        FragmentValuationPenthouse_ka.realizable_distress_completed_lay.setVisibility(View.GONE);
                        FragmentValuationPenthouse_ka.realizable_distress_underconstrust_lay.setVisibility(View.GONE);

                    }
                }, 1500);
            }
        } else {
            stageSpinner.setSelection(0, false);

            /************/
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentValuationPenthouse_ka.textview_realizable_head.setVisibility(View.GONE);
                    FragmentValuationPenthouse_ka.realizable_distress_completed_lay.setVisibility(View.GONE);
                    FragmentValuationPenthouse_ka.realizable_distress_underconstrust_lay.setVisibility(View.GONE);

                }
            }, 1500);
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
                    edittext_general_age.setEnabled(true);
                    edittext_general_life.setEnabled(true);

                    // as per com 100 in valuation
                    if (!General.isEmpty("" + 100)) {
                        FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                        fragmentValuationPenthouse.setEdittextAsPerCompletion("" + 100);

                        FragmentValuationPenthouse_ka.textview_realizable_head.setVisibility(View.VISIBLE);
                        FragmentValuationPenthouse_ka.realizable_distress_completed_lay.setVisibility(View.VISIBLE);
                        FragmentValuationPenthouse_ka.realizable_distress_underconstrust_lay.setVisibility(View.GONE);

                    }
                } else if (position == 2) {
                    // editable - underconstruction
                    edittext_general_comp.setText("");
                    textview_comp_total.setText("");
                    edittext_general_progress.setText("");
                    edittext_general_age.setText("");
                    edittext_general_life.setText("");

                    edittext_general_comp.setEnabled(true);
                    edittext_general_progress.setEnabled(true);
                    edittext_general_age.setEnabled(false);
                    edittext_general_life.setEnabled(false);

                    FragmentValuationPenthouse_ka.textview_realizable_head.setVisibility(View.VISIBLE);
                    FragmentValuationPenthouse_ka.realizable_distress_completed_lay.setVisibility(View.GONE);
                    FragmentValuationPenthouse_ka.realizable_distress_underconstrust_lay.setVisibility(View.VISIBLE);

                } else {
                    // not editable - select
                    edittext_general_comp.setText("");
                    textview_comp_total.setText("");
                    edittext_general_progress.setText("");
                    edittext_general_age.setText("");
                    edittext_general_life.setText("");
                    edittext_general_comp.setEnabled(true);
                    edittext_general_progress.setEnabled(true);
                    edittext_general_age.setEnabled(true);
                    edittext_general_life.setEnabled(true);


                    FragmentValuationPenthouse_ka.textview_realizable_head.setVisibility(View.GONE);
                    FragmentValuationPenthouse_ka.realizable_distress_completed_lay.setVisibility(View.GONE);
                    FragmentValuationPenthouse_ka.realizable_distress_underconstrust_lay.setVisibility(View.GONE);

                }

                if (FragmentFlat_ka.edittext_general_comp != null) {
                    FragmentFlat_ka.edittext_general_comp.setError(null);
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
            if (!General.isEmpty(getIntFloorId)) {
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

        TextView textview_heading = alertDialog.findViewById(R.id.textview_heading);
        Button btn_cancel = alertDialog.findViewById(R.id.btn_cancel);
        Button btn_save = alertDialog.findViewById(R.id.btn_save);
        RecyclerView recyclerview_dialog = alertDialog.findViewById(R.id.recyclerview_dialog);
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

        if (!General.isEmpty(flatsituated)) {
            editText_flatsituatedinfloor.setError(null);
        } else {
            editText_flatsituatedinfloor.requestFocus();
            editText_flatsituatedinfloor.setError(getActivity().getResources().getString(R.string.err_flatsituated));
        }

        if (stagespinner.equalsIgnoreCase("Select")) {
            ((TextView) stageSpinner.getSelectedView()).setError(getActivity().getResources().getString(R.string.err_construction_stage));
            edittext_general_comp.requestFocus();
        }

        if (!General.isEmpty(percentagecomp)) {
            edittext_general_comp.setError(null);
        } else {
            edittext_general_comp.requestFocus();
            edittext_general_comp.setError(getActivity().getResources().getString(R.string.err_percentage_completion));
        }

        // Hide as per requirement
        // set_flatpenthouse_mandatory();

        if (Singleton.getInstance().GetImage_list_flat.size() == 0) {
            textview_error_photo.setVisibility(View.VISIBLE);
        } else {
            textview_error_photo.setVisibility(View.GONE);
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
            if (!General.isEmpty(Singleton.getInstance().indProperty.getTotalApartmentFloors()))
                editText_totalapartmentfloors.setText(Singleton.getInstance().indProperty.getTotalApartmentFloors());
            if (!General.isEmpty(Singleton.getInstance().indProperty.getTotalFlatsPerFloor()))
                editText_totalflatsperfloor.setText(Singleton.getInstance().indProperty.getTotalFlatsPerFloor());
            if (!General.isEmpty(Singleton.getInstance().indProperty.getFlatSituatedInFloor()))
                editText_flatsituatedinfloor.setText(Singleton.getInstance().indProperty.getFlatSituatedInFloor());
            if (!General.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getNoOfFloors()))) {
                int floorsno = Singleton.getInstance().indProperty.getNoOfFloors();
                /*if (floorsno == 0) {
                    floorsno = 1;
                    editText_floors.setText("" + floorsno);
                } else {
                    editText_floors.setText("" + floorsno);
                }*/
            }

            if (!General.isEmpty(Singleton.getInstance().indProperty.getDocumentLandArea()))
                editText_as_per_docs.setText(Singleton.getInstance().indProperty.getDocumentLandArea());
            if (!General.isEmpty(Singleton.getInstance().indProperty.getAvgPercentageCompletion()))
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
            if (!General.isEmpty(Singleton.getInstance().indPropertyFloors.get(0).getFloorName())) {
                String floorname = Singleton.getInstance().indPropertyFloors.get(0).getFloorName();
            }
            if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getPercentageCompletion())))
                edittext_general_comp.setText("" + Singleton.getInstance().indPropertyFloors.get(0).getPercentageCompletion());
            if (!General.isEmpty(Singleton.getInstance().indPropertyFloors.get(0).getPresentCondition()))
                edittext_general_progress.setText(Singleton.getInstance().indPropertyFloors.get(0).getPresentCondition());
            if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getPropertyAge())))
                edittext_general_age.setText("" + Singleton.getInstance().indPropertyFloors.get(0).getPropertyAge());
            if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getResidualLife())))
                edittext_general_life.setText("" + Singleton.getInstance().indPropertyFloors.get(0).getResidualLife());
        }
        if (Singleton.getInstance().indPropertyValuation != null) {
            if (!General.isEmpty(Singleton.getInstance().indPropertyValuation.getSuperBuildUpArea())) {
                edittext_general_saleablearea.setText(Singleton.getInstance().indPropertyValuation.getSuperBuildUpArea());
            }
            if (!General.isEmpty(Singleton.getInstance().indPropertyValuation.getBuildUpArea())) {
                edittext_general_builduparea.setText(Singleton.getInstance().indPropertyValuation.getBuildUpArea());
            }
            if (!General.isEmpty(Singleton.getInstance().indPropertyValuation.getCarpetArea())) {
                edittext_general_carpetarea.setText(Singleton.getInstance().indPropertyValuation.getCarpetArea());
            }
            if (!General.isEmpty(Singleton.getInstance().indPropertyValuation.getTerraceArea())) {
                String terrace_area = Singleton.getInstance().indPropertyValuation.getTerraceArea();
                edittext_general_terracearea.setText(terrace_area);
            }
            if (!General.isEmpty(Singleton.getInstance().indPropertyValuation.getOtlaArea())) {
                String otla_area = Singleton.getInstance().indPropertyValuation.getOtlaArea();
                edittext_general_otlaarea.setText(otla_area);
            }
            if (!General.isEmpty(Singleton.getInstance().indPropertyValuation.getMezzanineArea())) {
                String mezzanine_area = Singleton.getInstance().indPropertyValuation.getMezzanineArea();
                edittext_general_mezzaninearea.setText(mezzanine_area);
            }
            if (!General.isEmpty(Singleton.getInstance().indPropertyValuation.getBalconyArea())) {
                String balcony_area = Singleton.getInstance().indPropertyValuation.getBalconyArea();
                edittext_general_balconyarea.setText(balcony_area);
            }

            if (!General.isEmpty(Singleton.getInstance().indPropertyValuation.getSaleableLoadingPercentage())) {
                String saleableLoadingPercentage_value = Singleton.getInstance().indPropertyValuation.getSaleableLoadingPercentage();
                saleableloading.setText(saleableLoadingPercentage_value);
            }

            if (!General.isEmpty(Singleton.getInstance().indPropertyValuation.getTerraceValue())) {
                String terraceValue = Singleton.getInstance().indPropertyValuation.getTerraceValue();

            }

            if (!General.isEmpty(Singleton.getInstance().indPropertyValuation.getBalconyValue())) {
                String balconyValue = Singleton.getInstance().indPropertyValuation.getBalconyValue();

            }
        }
    }

    private void setSpinnerInternalFloors() {
        if (Singleton.getInstance().indPropertyFloors != null) {
            if (Singleton.getInstance().indPropertyFloors.size() > 0) {

                if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatHallNo()))) {
                    int hall = Singleton.getInstance().indPropertyFloors.get(0).getFlatHallNo();
                    if (hall != 0)
                        internal_hall_dinning.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatHallNo()));
                }

                if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatKitchenNo()))) {
                    int value = Singleton.getInstance().indPropertyFloors.get(0).getFlatKitchenNo();
                    if (value != 0)
                        internal_kitchen.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatKitchenNo()));
                }

                if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatBedroomNo()))) {
                    int value = Singleton.getInstance().indPropertyFloors.get(0).getFlatBedroomNo();
                    if (value != 0)
                        internal_bedroom.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatBedroomNo()));
                }

                if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatBathNo()))) {
                    int value = Singleton.getInstance().indPropertyFloors.get(0).getFlatBathNo();
                    if (value != 0)
                        internal_bath.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatBathNo()));
                }

                if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getOfficeNo()))) {
                    int value = Singleton.getInstance().indPropertyFloors.get(0).getOfficeNo();
                    if (value != 0)
                        internal_shop_office.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getOfficeNo()));
                }

                if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatDinningNo()))) {
                    int value = Singleton.getInstance().indPropertyFloors.get(0).getFlatDinningNo();
                    if (value != 0)
                        internal_dinning.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatDinningNo()));
                }
                if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatWcNo()))) {
                    int value = Singleton.getInstance().indPropertyFloors.get(0).getFlatWcNo();
                    if (value != 0)
                        internal_wcs.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatWcNo()));
                }
                if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatAttBathWcNo()))) {
                    int value = Singleton.getInstance().indPropertyFloors.get(0).getFlatAttBathWcNo();
                    if (value != 0)
                        internal_attachbath.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatAttBathWcNo()));
                }
                if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatBalconyNo()))) {
                    int value = Singleton.getInstance().indPropertyFloors.get(0).getFlatBalconyNo();
                    if (value != 0)
                        internal_balcony.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatBalconyNo()));
                }
                if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatFbNo()))) {
                    int value = Singleton.getInstance().indPropertyFloors.get(0).getFlatFbNo();
                    if (value != 0)
                        internal_fbs.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatFbNo()));
                }
                if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatDbNo()))) {
                    int value = Singleton.getInstance().indPropertyFloors.get(0).getFlatDbNo();
                    if (value != 0)
                        internal_dbs.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatDbNo()));
                }
                if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatTerraceNo()))) {
                    int value = Singleton.getInstance().indPropertyFloors.get(0).getFlatTerraceNo();
                    if (value != 0)
                        internal_terrace.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatTerraceNo()));
                }
                if (!General.isEmpty(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatPassageNo()))) {
                    int value = Singleton.getInstance().indPropertyFloors.get(0).getFlatPassageNo();
                    if (value != 0)
                        internal_passage.setText(String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getFlatPassageNo()));
                }

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

            Singleton.getInstance().indProperty.setFloorDetails("" + editText_Floor_details.getText().toString());

            String total_appfloors = editText_totalapartmentfloors.getText().toString().trim();
            String total_flatsperfloor = editText_totalflatsperfloor.getText().toString().trim();
            String flatsituated = editText_flatsituatedinfloor.getText().toString().trim();
            String asperdocs = editText_as_per_docs.getText().toString().trim();

            if (!General.isEmpty(caseid))
                Singleton.getInstance().indProperty.setCaseId(Integer.valueOf(caseid));
            Singleton.getInstance().indProperty.setTotalApartmentFloors(total_appfloors);
            Singleton.getInstance().indProperty.setTotalFlatsPerFloor(total_flatsperfloor);
            Singleton.getInstance().indProperty.setFlatSituatedInFloor(flatsituated);
            /*if (!general.isEmpty(floors))*/
            Singleton.getInstance().indProperty.setNoOfFloors(1);
            Singleton.getInstance().indProperty.setDocumentLandArea(asperdocs);

            if (!General.isEmpty(edittext_general_comp.getText().toString())) {
                Singleton.getInstance().indProperty.setAvgPercentageCompletion(edittext_general_comp.getText().toString().trim());
            }


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

        //String generalfloorname = edittext_general_floor_name.getText().toString().trim();
        String generalfloorname = "";
        String flatcomp = edittext_general_comp.getText().toString().trim();
        String flatprogress = edittext_general_progress.getText().toString().trim();
        String flatage = edittext_general_age.getText().toString().trim();
        String flatlife = edittext_general_life.getText().toString().trim();

        if (Singleton.getInstance().indPropertyFloors != null) {

            if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                IndPropertyFloor indPropertyFloor = Singleton.getInstance().indPropertyFloors.get(0);
                if (!General.isEmpty(caseid))
                    indPropertyFloor.setCaseId(Integer.valueOf(caseid));
                indPropertyFloor.setFloorName(generalfloorname);
                if (!General.isEmpty(flatcomp)) {
                    indPropertyFloor.setPercentageCompletion(Integer.valueOf(flatcomp));
                } else {
                    indPropertyFloor.setPercentageCompletion(null);
                }
                indPropertyFloor.setPresentCondition(flatprogress);


                if (!General.isEmpty(flatage)) {
                    indPropertyFloor.setPropertyAge(Integer.valueOf(flatage));
                } else {
                    indPropertyFloor.setPropertyAge(null);
                }
                if (!General.isEmpty(flatlife)) {
                    indPropertyFloor.setResidualLife(Integer.valueOf(flatlife));
                } else {
                    indPropertyFloor.setResidualLife(null);
                }

                indPropertyFloor.setFloorNo(1);

                /* set */
                String charsequence_internal_hall_dinning = internal_hall_dinning.getText().toString();
                if (!General.isEmpty(charsequence_internal_hall_dinning)) {
                    indPropertyFloor.setFlatHallNo(Integer.valueOf(charsequence_internal_hall_dinning));
                } else {
                    indPropertyFloor.setFlatHallNo(0);
                }

                String charsequence_internal_kitchen = internal_kitchen.getText().toString();
                if (!General.isEmpty(charsequence_internal_kitchen)) {
                    indPropertyFloor.setFlatKitchenNo(Integer.valueOf(charsequence_internal_kitchen));
                } else {
                    indPropertyFloor.setFlatKitchenNo(0);
                }

                String charsequence_internal_bedroom = internal_bedroom.getText().toString();
                if (!General.isEmpty(charsequence_internal_bedroom)) {
                    indPropertyFloor.setFlatBedroomNo(Integer.valueOf(charsequence_internal_bedroom));
                } else {
                    indPropertyFloor.setFlatBedroomNo(0);
                }

                String charsequence_internal_bath = internal_bath.getText().toString();
                if (!General.isEmpty(charsequence_internal_bath)) {
                    indPropertyFloor.setFlatBathNo(Integer.valueOf(charsequence_internal_bath));
                } else {
                    indPropertyFloor.setFlatBathNo(0);
                }

                String charsequence_internal_shop_office = internal_shop_office.getText().toString();
                if (!General.isEmpty(charsequence_internal_shop_office)) {
                    indPropertyFloor.setOfficeNo(Integer.valueOf(charsequence_internal_shop_office));
                } else {
                    indPropertyFloor.setOfficeNo(0);
                }

                String charsequence_internal_dinning = internal_dinning.getText().toString();
                if (!General.isEmpty(charsequence_internal_dinning)) {
                    indPropertyFloor.setFlatDinningNo(Integer.valueOf(charsequence_internal_dinning));
                } else {
                    indPropertyFloor.setFlatDinningNo(0);
                }

                String charsequence_internal_wcs = internal_wcs.getText().toString();
                if (!General.isEmpty(charsequence_internal_wcs)) {
                    indPropertyFloor.setFlatWcNo(Integer.valueOf(charsequence_internal_wcs));
                } else {
                    indPropertyFloor.setFlatWcNo(0);
                }

                String charsequence_internal_attachbath = internal_attachbath.getText().toString();
                if (!General.isEmpty(charsequence_internal_attachbath)) {
                    indPropertyFloor.setFlatAttBathWcNo(Integer.valueOf(charsequence_internal_attachbath));
                } else {
                    indPropertyFloor.setFlatAttBathWcNo(0);
                }

                String charsequence_internal_balcony = internal_balcony.getText().toString();
                if (!General.isEmpty(charsequence_internal_balcony)) {
                    indPropertyFloor.setFlatBalconyNo(Integer.valueOf(charsequence_internal_balcony));
                } else {
                    indPropertyFloor.setFlatBalconyNo(0);
                }

                String charsequence_internal_fbs = internal_fbs.getText().toString();
                if (!General.isEmpty(charsequence_internal_fbs)) {
                    indPropertyFloor.setFlatFbNo(Integer.valueOf(charsequence_internal_fbs));
                } else {
                    indPropertyFloor.setFlatFbNo(0);
                }

                String charsequence_internal_dbs = internal_dbs.getText().toString();
                if (!General.isEmpty(charsequence_internal_dbs)) {
                    indPropertyFloor.setFlatDbNo(Integer.valueOf(charsequence_internal_dbs));
                } else {
                    indPropertyFloor.setFlatDbNo(0);
                }

                String charsequence_internal_terrace = internal_terrace.getText().toString();
                if (!General.isEmpty(charsequence_internal_terrace)) {
                    indPropertyFloor.setFlatTerraceNo(Integer.valueOf(charsequence_internal_terrace));
                } else {
                    indPropertyFloor.setFlatTerraceNo(0);
                }

                String charsequence_internal_passage = internal_passage.getText().toString();
                if (!General.isEmpty(charsequence_internal_passage)) {
                    indPropertyFloor.setFlatPassageNo(Integer.valueOf(charsequence_internal_passage));
                } else {
                    indPropertyFloor.setFlatPassageNo(0);
                }


                Singleton.getInstance().indPropertyFloors.set(0, indPropertyFloor);
            } else {

                IndPropertyFloor indPropertyFloor = new IndPropertyFloor();
                if (!General.isEmpty(caseid))
                    indPropertyFloor.setCaseId(Integer.valueOf(caseid));
                indPropertyFloor.setFloorName(generalfloorname);
                if (!General.isEmpty(flatcomp)) {
                    indPropertyFloor.setPercentageCompletion(Integer.valueOf(flatcomp));
                } else {
                    indPropertyFloor.setPercentageCompletion(null);
                }
                indPropertyFloor.setPresentCondition(flatprogress);

                if (!General.isEmpty(flatage)) {
                    indPropertyFloor.setPropertyAge(Integer.valueOf(flatage));
                } else {
                    indPropertyFloor.setPropertyAge(null);
                }

                if (!General.isEmpty(flatlife)) {
                    indPropertyFloor.setResidualLife(Integer.valueOf(flatlife));
                } else {
                    indPropertyFloor.setResidualLife(null);
                }


                indPropertyFloor.setFloorNo(1);


                String charsequence_internal_hall_dinning = internal_hall_dinning.getText().toString();
                if (!General.isEmpty(charsequence_internal_hall_dinning)) {
                    indPropertyFloor.setFlatHallNo(Integer.valueOf(charsequence_internal_hall_dinning));
                } else {
                    indPropertyFloor.setFlatHallNo(0);
                }

                String charsequence_internal_kitchen = internal_kitchen.getText().toString();
                if (!General.isEmpty(charsequence_internal_kitchen)) {
                    indPropertyFloor.setFlatKitchenNo(Integer.valueOf(charsequence_internal_kitchen));
                } else {
                    indPropertyFloor.setFlatKitchenNo(0);
                }

                String charsequence_internal_bedroom = internal_bedroom.getText().toString();
                if (!General.isEmpty(charsequence_internal_bedroom)) {
                    indPropertyFloor.setFlatBedroomNo(Integer.valueOf(charsequence_internal_bedroom));
                } else {
                    indPropertyFloor.setFlatBedroomNo(0);
                }

                String charsequence_internal_bath = internal_bath.getText().toString();
                if (!General.isEmpty(charsequence_internal_bath)) {
                    indPropertyFloor.setFlatBathNo(Integer.valueOf(charsequence_internal_bath));
                } else {
                    indPropertyFloor.setFlatBathNo(0);
                }

                String charsequence_internal_shop_office = internal_shop_office.getText().toString();
                if (!General.isEmpty(charsequence_internal_shop_office)) {
                    indPropertyFloor.setOfficeNo(Integer.valueOf(charsequence_internal_shop_office));
                } else {
                    indPropertyFloor.setOfficeNo(0);
                }

                String charsequence_internal_dinning = internal_dinning.getText().toString();
                if (!General.isEmpty(charsequence_internal_dinning)) {
                    indPropertyFloor.setFlatDinningNo(Integer.valueOf(charsequence_internal_dinning));
                } else {
                    indPropertyFloor.setFlatDinningNo(0);
                }

                String charsequence_internal_wcs = internal_wcs.getText().toString();
                if (!General.isEmpty(charsequence_internal_wcs)) {
                    indPropertyFloor.setFlatWcNo(Integer.valueOf(charsequence_internal_wcs));
                } else {
                    indPropertyFloor.setFlatWcNo(0);
                }

                String charsequence_internal_attachbath = internal_attachbath.getText().toString();
                if (!General.isEmpty(charsequence_internal_attachbath)) {
                    indPropertyFloor.setFlatAttBathWcNo(Integer.valueOf(charsequence_internal_attachbath));
                } else {
                    indPropertyFloor.setFlatAttBathWcNo(0);
                }

                String charsequence_internal_balcony = internal_balcony.getText().toString();
                if (!General.isEmpty(charsequence_internal_balcony)) {
                    indPropertyFloor.setFlatBalconyNo(Integer.valueOf(charsequence_internal_balcony));
                } else {
                    indPropertyFloor.setFlatBalconyNo(0);
                }

                String charsequence_internal_fbs = internal_fbs.getText().toString();
                if (!General.isEmpty(charsequence_internal_fbs)) {
                    indPropertyFloor.setFlatFbNo(Integer.valueOf(charsequence_internal_fbs));
                } else {
                    indPropertyFloor.setFlatFbNo(0);
                }

                String charsequence_internal_dbs = internal_dbs.getText().toString();
                if (!General.isEmpty(charsequence_internal_dbs)) {
                    indPropertyFloor.setFlatDbNo(Integer.valueOf(charsequence_internal_dbs));
                } else {
                    indPropertyFloor.setFlatDbNo(0);
                }

                String charsequence_internal_terrace = internal_terrace.getText().toString();
                if (!General.isEmpty(charsequence_internal_terrace)) {
                    indPropertyFloor.setFlatTerraceNo(Integer.valueOf(charsequence_internal_terrace));
                } else {
                    indPropertyFloor.setFlatTerraceNo(0);
                }

                String charsequence_internal_passage = internal_passage.getText().toString();
                if (!General.isEmpty(charsequence_internal_passage)) {
                    indPropertyFloor.setFlatPassageNo(Integer.valueOf(charsequence_internal_passage));
                } else {
                    indPropertyFloor.setFlatPassageNo(0);
                }


                Singleton.getInstance().indPropertyFloors.add(indPropertyFloor);
            }
        }


        if (Singleton.getInstance().indPropertyValuation != null) {

            String saleablearea = edittext_general_saleablearea.getText().toString().trim();
            String builduparea = edittext_general_builduparea.getText().toString().trim();
            String carpetarea = edittext_general_carpetarea.getText().toString().trim();
            String terracearea = edittext_general_terracearea.getText().toString().trim();
            String otlaarea = edittext_general_otlaarea.getText().toString().trim();
            String mezzaninearea = edittext_general_mezzaninearea.getText().toString().trim();
            String balconyarea = edittext_general_balconyarea.getText().toString().trim();

            if (!General.isEmpty(caseid))
                Singleton.getInstance().indPropertyValuation.setCaseId(Integer.valueOf(caseid));
            Singleton.getInstance().indPropertyValuation.setSuperBuildUpArea(saleablearea);
            Singleton.getInstance().indPropertyValuation.setBuildUpArea(builduparea);
            Singleton.getInstance().indPropertyValuation.setCarpetArea(carpetarea);
            Singleton.getInstance().indPropertyValuation.setTerraceArea(terracearea);
            Singleton.getInstance().indPropertyValuation.setOtlaArea(otlaarea);
            Singleton.getInstance().indPropertyValuation.setMezzanineArea(mezzaninearea);
            Singleton.getInstance().indPropertyValuation.setBalconyArea(balconyarea);
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

    private void saleableloading_popup(final TextView textView) {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.saleableloading_popup);

        TextView popuptitle = dialog.findViewById(R.id.title);
        popuptitle.setTypeface(general.mediumtypeface());

        ImageView close = dialog.findViewById(R.id.close);
        RadioGroup id_radiogroup_stage = dialog.findViewById(R.id.id_radiogroup_stage);
        RadioButton id_radio_select = dialog.findViewById(R.id.id_radio_select);
        RadioButton id_radio_0 = dialog.findViewById(R.id.id_radio_0);
        RadioButton id_radio_10 = dialog.findViewById(R.id.id_radio_10);
        RadioButton id_radio_15 = dialog.findViewById(R.id.id_radio_15);
        RadioButton id_radio_20 = dialog.findViewById(R.id.id_radio_20);
        RadioButton id_radio_25 = dialog.findViewById(R.id.id_radio_25);
        RadioButton id_radio_30 = dialog.findViewById(R.id.id_radio_30);
        RadioButton id_radio_35 = dialog.findViewById(R.id.id_radio_35);
        RadioButton id_radio_40 = dialog.findViewById(R.id.id_radio_40);
        RadioButton id_radio_45 = dialog.findViewById(R.id.id_radio_45);
        RadioButton id_radio_50 = dialog.findViewById(R.id.id_radio_50);
        RadioButton id_radio_55 = dialog.findViewById(R.id.id_radio_55);
        RadioButton id_radio_60 = dialog.findViewById(R.id.id_radio_60);
        RadioButton id_radio_65 = dialog.findViewById(R.id.id_radio_65);
        RadioButton id_radio_70 = dialog.findViewById(R.id.id_radio_70);
        RadioButton id_radio_75 = dialog.findViewById(R.id.id_radio_75);

        id_radio_select.setTypeface(general.mediumtypeface());
        id_radio_0.setTypeface(general.mediumtypeface());
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

        String selected_value = "";
        boolean is_empty = true;
        popuptitle.setText(getActivity().getResources().getString(R.string.scaleable_loading));
        if (!General.isEmpty(Singleton.getInstance().indPropertyValuation.getSaleableLoadingPercentage())) {
            is_empty = false;
            selected_value = Singleton.getInstance().indPropertyValuation.getSaleableLoadingPercentage();
        }


        if (!is_empty) {
            if (selected_value.equalsIgnoreCase("0")) {
                id_radio_0.setChecked(true);
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
            } else {
                id_radio_select.setChecked(true);
            }
        } else {
            id_radio_select.setChecked(true);
        }

        id_radiogroup_stage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton id_radiogenearal = dialog.findViewById(group.getCheckedRadioButtonId());
                //Log.e("id_radiogenearal_",": "+id_radiogenearal.getText().toString());
                String str_radiogenearal = id_radiogenearal.getText().toString();
                if (str_radiogenearal.equalsIgnoreCase("Select")) {
                    textView.setText(str_radiogenearal);
                    Singleton.getInstance().indPropertyValuation.setSaleableLoadingPercentage("");
                } else {
                    String saleableStr = str_radiogenearal.replace("%", "");
                    textView.setText(saleableStr);
                    Singleton.getInstance().indPropertyValuation.setSaleableLoadingPercentage(saleableStr);

                    String carpetarea_str = edittext_general_carpetarea.getText().toString();
                    if (!General.isEmpty(carpetarea_str)) {
                        float carpet_float = general.convertTofloat(carpetarea_str);
                        int saleableloading_int = Integer.parseInt(saleableStr);
                        int add_value = 100 + saleableloading_int;
                        double result_double = ((double) add_value) / ((double) 100);
                        String result_str = String.valueOf(result_double);
                        float saleableloading_float = Float.parseFloat(result_str);
                        float saleablearea_float = (carpet_float) * (saleableloading_float);
                        double saleablearea_double = Double.parseDouble(new DecimalFormat("##.##").format(saleablearea_float));
                        String saleablearea_str = String.valueOf(saleablearea_double);
                        edittext_general_saleablearea.setText(saleablearea_str);
                        // Saleable Valuation
                        FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                        fragmentValuationPenthouse.setEdittextSaleableArea_ka(saleablearea_str);
                        checkSpinnerAreaType(saleablearea_str, getResources().getString(R.string.Saleable));
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

    private void set_flatpenthouse_mandatory() {
        String terracearea = edittext_general_terracearea.getText().toString();
        if (!General.isEmpty(terracearea)) {
            edittext_general_terracearea.setError(null);
        } else {
            edittext_general_terracearea.requestFocus();
            edittext_general_terracearea.setError(getActivity().getResources().getString(R.string.terracearea));
        }


        String balconyarea = edittext_general_balconyarea.getText().toString();
        if (!General.isEmpty(terracearea)) {
            edittext_general_balconyarea.setError(null);
        } else {
            edittext_general_balconyarea.requestFocus();
            edittext_general_balconyarea.setError(getActivity().getResources().getString(R.string.enterbalcony));
        }
    }

    private void set_uploadImage_perview() {
        if (Singleton.getInstance().GetImage_list_flat.size() > 0) {
            mBase64 = Singleton.getInstance().GetImage_list_flat.get(0).getLogo();
            if (!General.isEmpty(mBase64)) {
                byte[] decodedString = Base64.decode(mBase64, Base64.DEFAULT);
                final ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                decodedByte.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                // set Bitmap into image view
                if (decodedByte != null) {
                    /*Glide.with(getActivity()).load(stream.toByteArray()).asBitmap().centerCrop().into(thumbnail_image);*/
                    Glide.with(getActivity()).asBitmap().centerCrop().load(stream.toByteArray()).into(thumbnail_image);
                }
                upload_image.setVisibility(View.GONE);
                upload_div.setVisibility(View.VISIBLE);
            }
        } else {
            // New Image
            upload_image.setVisibility(View.VISIBLE);
            upload_div.setVisibility(View.GONE);
        }
    }

    private void upload_popup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Upload Image");
        String[] animals = {"Camera", "Gallery"};
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        TakePicture();
                        break;
                    case 1:
                        if (checkPermissions()) {
                            Intent gallery_select = new Intent(getActivity(), MultiPhotoSelectActivity.class);
                            gallery_select.putExtra("page_is", "fragment_flat_ka");
                            startActivityForResult(gallery_select, GALLERY_REQUEST);
                        }
                        break;
                    default:
                        TakePicture();
                        break;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void TakePicture() {
        // Method one
        /*Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);*/
        // Method Two
        current_time_cam_image = System.currentTimeMillis();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + current_time_cam_image + ".jpg");
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(takePictureIntent, CAMERA_REQUEST);
    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(getActivity(), p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            String path_new = Environment.getExternalStorageDirectory() + File.separator + current_time_cam_image + ".jpg";
            if (!General.isEmpty(path_new)) {
                String realPath = null;
                realPath = SiliCompressor.with(getActivity()).compress(path_new, new File(SettingsUtils.image_destination_path));
                convertToBase64(realPath);
            }
        } else if (requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK) {
            ImageAdapter imageAdapter = MultiPhotoSelectActivity.imageAdapter;
            General.showloading(getActivity());

            for (int i = 0; i < imageAdapter.getCheckedItems().size(); i++) {
                String realPath = null;
                realPath = SiliCompressor.with(getActivity()).compress(imageAdapter.getCheckedItems().get(i), new File(SettingsUtils.image_destination_path));
                convertToBase64(realPath);
                if (i == imageAdapter.getCheckedItems().size() - 1) {
                    General.hideloading();
                }
            }
            //noinspection StatementWithEmptyBody
            if (Build.VERSION.SDK_INT >= 23 &&
                    ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    private String convertToBase64(String imagePath) {
        Bitmap bmp = null;
        ByteArrayOutputStream bos = null;
        byte[] bt = null;
        String encodedImage = null;
        try {
            bmp = BitmapFactory.decodeFile(imagePath);
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bt = bos.toByteArray();
            encodedImage = Base64.encodeToString(bt, Base64.DEFAULT);
            mBase64 = encodedImage;

            if (!General.isEmpty(mBase64)) {
                byte[] decodedString = Base64.decode(mBase64, Base64.DEFAULT);
                final ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                decodedByte.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                // set Bitmap into image view
                /*Glide.with(getActivity()).load(stream.toByteArray()).asBitmap().centerCrop().into(thumbnail_image);*/
                Glide.with(Objects.requireNonNull(getActivity())).asBitmap().centerCrop().load(stream.toByteArray()).into(thumbnail_image);

                upload_image.setVisibility(View.GONE);
                upload_div.setVisibility(View.VISIBLE);
                textview_error_photo.setVisibility(View.GONE);

                // Add
                GetImage_list_flat_response = new ArrayList<>();
                GetPhoto_measurment getPhoto = new GetPhoto_measurment();
                getPhoto.setId(0);
                getPhoto.setLogo(mBase64);
                getPhoto.setTitle("");
                getPhoto.setPropertyId(PropertyId_is);
                GetImage_list_flat_response.add(getPhoto);
                Singleton.getInstance().GetImage_list_flat = GetImage_list_flat_response;

            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        General.hideloading();
        return encodedImage;
    }

    private void view_popup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        builder.setTitle("Image");
        String[] animals = {"View", "Delete"};
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        DocumentReaderPopup(mBase64, "");
                        break;
                    case 1:
                        if (Singleton.getInstance().GetImage_list_flat.size() > 0) {
                            if (Singleton.getInstance().GetImage_list_flat.get(0).getId() == 0) {
                                // New Image
                                delete_local();
                            } else {
                                // Old Image - Delete it from cloud
                                delete_cloud();
                            }
                        } else {
                            delete_local();
                        }
                        break;
                    default:
                        TakePicture();
                        break;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void delete_local() {
        // Delete it from Local
        mBase64 = "";
        upload_div.setVisibility(View.GONE);
        upload_image.setVisibility(View.VISIBLE);
        // Delete
        GetImage_list_flat_response = new ArrayList<>();
        Singleton.getInstance().GetImage_list_flat = GetImage_list_flat_response;
        // delete it from Local
        appDatabase.interfaceGetPhotoMeasurmentQuery().deleteRow(PropertyId_is);


    }

    private void delete_cloud() {
        if (Connectivity.isConnected(getActivity())) {
            General.showloading(getActivity());
            JsonRequestData data = new JsonRequestData();
            data.setJobID("" + Singleton.getInstance().GetImage_list_flat.get(0).getId());
            data.setPropertyID("" + Singleton.getInstance().GetImage_list_flat.get(0).getPropertyId());
            data.setUrl(general.ApiBaseUrl() + SettingsUtils.DELETEIMAGE);
            data.setRequestBody(RequestParam.deleteimageRequestParams(data));
            WebserviceCommunicator webserviceTask = new WebserviceCommunicator(getActivity(), data, SettingsUtils.DELETE);
            webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                @Override
                public void onTaskComplete(JsonRequestData requestData) {
                    parsedeleteimageResponse(requestData.getResponse());
                }
            });
            webserviceTask.execute();
        } else {
            //Connectivity.showNoConnectionDialog(getActivity());
            internet_check_box("edit_inspec", false);
        }
    }

    private void parsedeleteimageResponse(String response) {
        DataResponse dataResponse = ResponseParser.deleteimage(response);
        if (dataResponse.status.equalsIgnoreCase("1")) {
            Toast.makeText(getContext(), dataResponse.info, Toast.LENGTH_LONG).show();
            delete_local();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                General.hideloading();
            }
        }, 1000);
    }

    private void DocumentReaderPopup(final String documentimage, String title) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.document_reader_ka);

        TextView popuptitle = dialog.findViewById(R.id.title);
        ImageView document_image = dialog.findViewById(R.id.document_read_image);
        Button yesBtn = dialog.findViewById(R.id.yesBtn);
        yesBtn.setTypeface(general.mediumtypeface());
        popuptitle.setTypeface(general.mediumtypeface());
        popuptitle.setText("Image View");

        //decode base64 string to image
        byte[] imageBytes = Base64.decode(documentimage, Base64.DEFAULT);
        final Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        document_image.setImageBitmap(decodedImage);

        PhotoViewAttacher pAttacher;
        pAttacher = new PhotoViewAttacher(document_image);
        pAttacher.update();

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void internet_check_box(final String str, final boolean add_measu_image) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                        if (!General.isEmpty(String.valueOf(Singleton.getInstance().aCase.getCaseId()))) {
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
                            //  Case - Room Delete
                            appDatabase.interfaceCaseQuery().deleteRow(caseid_int);
                            // Case - Room Add
                            Singleton.getInstance().aCase.setCaseId(caseid_int);
                            appDatabase.interfaceCaseQuery().insertAll(Singleton.getInstance().aCase);
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
                            for (int x = 0; x < PhotoLatLong_ka.GetPhoto_list_response.size(); x++) {
                                if (!PhotoLatLong_ka.GetPhoto_list_response.get(x).isDefaultimage()) {
                                    GetPhoto getPhoto = new GetPhoto();
                                    getPhoto.setId(0);
                                    // getPhoto.setId(PhotoLatLong_ka.GetPhoto_list_response.get(x).getId());
                                    getPhoto.setLogo(PhotoLatLong_ka.GetPhoto_list_response.get(x).getLogo());
                                    getPhoto.setTitle(PhotoLatLong_ka.GetPhoto_list_response.get(x).getTitle());
                                    getPhoto.setPropertyId(PhotoLatLong_ka.GetPhoto_list_response.get(x).getPropertyId());
                                    getPhoto.setDefaultimage(false);
                                    getPhoto.setNewimage(false);
                                    // Check the Base64Image should be less than 19,80,000 ...
                                    if (PhotoLatLong_ka.GetPhoto_list_response.get(x).getLogo().length() < 1980000) {
                                        // Room Add
                                        appDatabase.interfaceGetPhotoQuery().insertAll(getPhoto);
                                    }
                                }
                            }

                            // Delete image in local
                            UpdateOfflineStatusEditcase(String.valueOf(Singleton.getInstance().aCase.getCaseId()), "2");
                            delete_local();

                            /*if (add_measu_image) {
                                // move the Measurement Image to offline
                                appDatabase.interfaceGetPhotoMeasurmentQuery().deleteRow(PropertyId_is);
                                if (Singleton.getInstance().GetImage_list_flat.size() > 0) {
                                    GetPhoto_measurment getPhoto = new GetPhoto_measurment();
                                    getPhoto.setId(0);
                                    getPhoto.setLogo(Singleton.getInstance().GetImage_list_flat.get(0).getLogo());
                                    getPhoto.setTitle(Singleton.getInstance().GetImage_list_flat.get(0).getTitle());
                                    getPhoto.setPropertyId(Singleton.getInstance().GetImage_list_flat.get(0).getPropertyId());
                                    // Check the Base64Image should be less than 19,80,000 ...
                                    if (Singleton.getInstance().GetImage_list_flat.get(0).getLogo().length() < 1980000) {
                                        // Room Add
                                        appDatabase.interfaceGetPhotoMeasurmentQuery().insertAll(getPhoto);
                                        // set ID as 0
                                        Singleton.getInstance().GetImage_list_flat.get(0).setId(0);
                                    }
                                }
                                // Generate the Floor again
                                checkValidationforFloors();
                            } else {
                                // Delete image in local
                                UpdateOfflineStatusEditcase(String.valueOf(Singleton.getInstance().aCase.getCaseId()), "2");
                                delete_local();
                            }*/

                        }

                        // hit_photo_api > false
                        Singleton.getInstance().hit_photo_api = false;
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

    // Todo update Start to Edit Inspecction
    private void UpdateOfflineStatusEditcase(String case_id, String updateCaseStatus) {
        if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
            appDatabase.interfaceOfflineDataModelQuery().updateOfflineCaseStatus(updateCaseStatus, case_id);
            // update the case for casemodal
            appDatabase.interfaceCaseQuery().updateCaseStatus(updateCaseStatus, case_id);
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
        btnClear = dialog.findViewById(R.id.btn_delete);
        tvProcessor = dialog.findViewById(R.id.tv_process);
        tvResult = dialog.findViewById(R.id.tv_result);

        tvProcessor.setText("");
        tvResult.setText("");


        btnOne = dialog.findViewById(R.id.btn_one);
        btnTwo = dialog.findViewById(R.id.btn_two);
        btnThree = dialog.findViewById(R.id.btn_three);
        btnFour = dialog.findViewById(R.id.btn_four);
        btnFive = dialog.findViewById(R.id.btn_five);
        btnSix = dialog.findViewById(R.id.btn_six);
        btnSeven = dialog.findViewById(R.id.btn_seven);
        btnEight = dialog.findViewById(R.id.btn_eight);
        btnNine = dialog.findViewById(R.id.btn_nine);
        btnZero = dialog.findViewById(R.id.btn_zero);


        btnMultiply = dialog.findViewById(R.id.btn_multiply);
        btnMinus = dialog.findViewById(R.id.btn_minus);
        btnPlus = dialog.findViewById(R.id.btn_plus);
        btnDivide = dialog.findViewById(R.id.btn_divide);

        btnDecimal = dialog.findViewById(R.id.btn_dot);
        btnBack = dialog.findViewById(R.id.btn_back);

        btnSmallBracket = dialog.findViewById(R.id.btn_small_bracket);
        btnEqual = dialog.findViewById(R.id.btn_equal);
        btnPercentage = dialog.findViewById(R.id.btn_percentage);
        btnclose = dialog.findViewById(R.id.btnclose);
        btnsetvalue = dialog.findViewById(R.id.btnsetvalue);
        TextView calculatortitle = dialog.findViewById(R.id.calculatortitle);

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

                if (!General.isEmpty(tvResult.getText().toString())) {
                    // - values not allowed
                    if (!tvResult.getText().toString().contains("-")) {
                        /*int my_val = general.convertToRoundoff(Float.parseFloat(tvResult.getText().toString()));
                        String my_val_str = String.valueOf(my_val);*/
                        String my_val_str = tvResult.getText().toString();
                        if (type_is == 1) {
                            editText_as_per_docs.setText(my_val_str);
                        } else if (type_is == 2) {
                            // CarpetArea
                            edittext_general_carpetarea.setText(my_val_str);
                            FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                            fragmentValuationPenthouse.setEdittextCarpetArea_ka(my_val_str);
                            checkSpinnerAreaType(my_val_str, getResources().getString(R.string.carpet));
                        } else if (type_is == 3) {
                            // BuildupArea
                            edittext_general_builduparea.setText(my_val_str);
                            FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                            fragmentValuationPenthouse.setEdittextBuildupArea_ka(my_val_str);
                            checkSpinnerAreaType(my_val_str, getResources().getString(R.string.builtup));
                            // set the Government value
                            fragmentValuationPenthouse.setGovernmentvalue_area_ka(my_val_str);
                        } else if (type_is == 4) {
                            // SaleableArea
                            edittext_general_saleablearea.setText(my_val_str);
                            FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                            fragmentValuationPenthouse.setEdittextSaleableArea_ka(my_val_str);
                            checkSpinnerAreaType(my_val_str, getResources().getString(R.string.Saleable));
                        } else if (type_is == 5) {
                            // TerraceArea
                            edittext_general_terracearea.setText(my_val_str);
                            FragmentValuationPenthouse_ka fragmentValuationPenthouse_ka = new FragmentValuationPenthouse_ka();
                            FragmentValuationPenthouse_ka.edittext_terrace_area.setText(my_val_str);
                        } else if (type_is == 6) {
                            // BalconyArea
                            edittext_general_balconyarea.setText(my_val_str);
                            FragmentValuationPenthouse_ka fragmentValuationPenthouse_ka = new FragmentValuationPenthouse_ka();
                            FragmentValuationPenthouse_ka.edittext_balcony_area.setText(my_val_str);
                        } else if (type_is == 7) {
                            // OtlaArea
                            edittext_general_otlaarea.setText(my_val_str);
                            FragmentValuationPenthouse_ka fragmentValuationPenthouse_ka = new FragmentValuationPenthouse_ka();
                            FragmentValuationPenthouse_ka.edittext_otla_area.setText(my_val_str);
                        } else if (type_is == 8) {
                            // mezzanine
                            edittext_general_mezzaninearea.setText(my_val_str);
                            FragmentValuationPenthouse_ka fragmentValuationPenthouse_ka = new FragmentValuationPenthouse_ka();
                            FragmentValuationPenthouse_ka.edittext_mezzanine_area.setText(my_val_str);
                        }
                    }
                    dialog.dismiss();
                } else if (General.isEmpty(tvResult.getText().toString())) {
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
