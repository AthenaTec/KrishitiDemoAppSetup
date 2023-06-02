package com.realappraiser.gharvalue.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import android.widget.Spinner;
import android.widget.TextView;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.model.Measurements;
import com.realappraiser.gharvalue.utils.DecimalDigitsInputFilter;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import org.mozilla.javascript.Scriptable;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentLand_ka extends Fragment {

    // TODO General class to call typeface and all...
    public static General general;

    @BindView(R.id.textview_land_measurement_land)
    TextView textview_measurement_doc;
    @BindView(R.id.textview_measurement_doc)
    TextView textview_land_measurement_land;

    public static EditText editText_compound_permissiblearea_land;
    public static EditText editText_land_measurement_land;
    public static Spinner spinner_measurementland;

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
        View view = inflater.inflate(R.layout.activity_fragment_land_ka, container, false);
        ButterKnife.bind(this, view);

        general = new General(getActivity());
        SettingsUtils.init(getActivity());

        initViews(view);
        LandAreaMeasurements();
        spinnerMeasurementonLand();
        DisplayLandValues();

        open_calc_compound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call_calc_function(1);
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

        // TODO -  call the mandatory_validation
        if (Singleton.getInstance().enable_validation_error) {
            set_mandatory_land();
        }

        return view;
    }

    private void initViews(View view) {

        editText_compound_permissiblearea_land = (EditText) view.findViewById(R.id.editText_compound_permissiblearea_land);
        editText_land_measurement_land = (EditText) view.findViewById(R.id.editText_land_measurement_land);
        spinner_measurementland = (Spinner) view.findViewById(R.id.spinner_measurementland);

        textview_land_measurement_land.setTypeface(general.mediumtypeface());
        editText_compound_permissiblearea_land.setTypeface(general.regulartypeface());
        editText_land_measurement_land.setTypeface(general.regulartypeface());
        textview_measurement_doc.setTypeface(general.regulartypeface());

        //  limit the 2 char after the decimal point
        editText_compound_permissiblearea_land.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(10,2)});
        editText_land_measurement_land.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(10,2)});

    }

    private void LandAreaMeasurements() {
        /*editText_compound_permissiblearea_land.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String area = charSequence.toString();
                editText_land_measurement_land.setText(area);

                //TODO Interface
                FragmentValuationLand_ka fragmentValuationLand = new FragmentValuationLand_ka();
                fragmentValuationLand.permission_measurment(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String area = editable.toString();
                editText_land_measurement_land.setText(area);
            }
        });*/


        editText_compound_permissiblearea_land.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String charSequence = editText_compound_permissiblearea_land.getText().toString();
                    if (general.isEmpty(editText_land_measurement_land.getText().toString())) {
                        // If its empty
                        editText_land_measurement_land.setText(charSequence);
                    }

                    editText_land_measurement_land.setError(null);
                    // TODO Interface
                    FragmentValuationLand_ka fragmentValuationLand = new FragmentValuationLand_ka();
                    fragmentValuationLand.permission_measurment(charSequence.toString());
                }
            }
        });

        editText_land_measurement_land.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //TODO Interface
                FragmentValuationLand_ka fragmentValuationLand = new FragmentValuationLand_ka();
                fragmentValuationLand.actual_measurment(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void spinnerMeasurementonLand() {
        ArrayAdapter<Measurements> adapterMeasurements = new ArrayAdapter<>(getActivity(),
                R.layout.row_spinner_item, Singleton.getInstance().measurements_list);
        adapterMeasurements.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_measurementland.setAdapter(adapterMeasurements);
        spinner_measurementland.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftKeyboard(spinner_measurementland);
                return false;
            }
        });

        spinner_measurementland.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_qualityofcon", "::: " + Singleton.getInstance().measurements_list.get(position).getMeasureUnitId());

                Singleton.getInstance().indProperty.setDocumentLandAreaUnit(Singleton.getInstance().measurements_list.get(position).getMeasureUnitId());
                Singleton.getInstance().indProperty.setMeasuredLandAreaUnit(Singleton.getInstance().measurements_list.get(position).getMeasureUnitId());

                FragmentValuationLand_ka fragmentValuationLand = new FragmentValuationLand_ka();
                fragmentValuationLand.get_measurment_land(Singleton.getInstance().measurements_list.get(position).getMeasureUnitId(), Singleton.getInstance().measurements_list.get(position).getName());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    /*******
     * Display LandValues from API
     * *********/
    private void DisplayLandValues() {

        // Todo edittext and spinner set values from API
        setLandPropertyDetails();
    }

    private void set_mandatory_land() {

        String flatsituated = editText_compound_permissiblearea_land.getText().toString();
        String stagespinner = spinner_measurementland.getSelectedItem().toString();
        String percentagecomp = editText_land_measurement_land.getText().toString();

        if (!general.isEmpty(flatsituated)) {
            editText_compound_permissiblearea_land.setError(null);
        } else {
            editText_compound_permissiblearea_land.requestFocus();
            editText_compound_permissiblearea_land.setError(getActivity().getResources().getString(R.string.error_land_comd));
        }

        if (stagespinner.equalsIgnoreCase("Select")) {
            //   ((TextView)  FragmentLand.spinner_measurementland.getSelectedView()).setError(getActivity().getResources().getString(R.string.error_land_mease_unit));
            editText_land_measurement_land.requestFocus();
        }

        if (!general.isEmpty(percentagecomp)) {
            editText_land_measurement_land.setError(null);
        } else {
            editText_land_measurement_land.requestFocus();
            editText_land_measurement_land.setError(getActivity().getResources().getString(R.string.error_land_mease));
        }
    }

    public void setLandPropertyDetails() {

        if (Singleton.getInstance().indProperty != null) {
            if (!general.isEmpty(Singleton.getInstance().indProperty.getDocumentLandArea()))
                editText_compound_permissiblearea_land.setText(Singleton.getInstance().indProperty.getDocumentLandArea());
            if (!general.isEmpty(Singleton.getInstance().indProperty.getMeasuredLandArea()))
                editText_land_measurement_land.setText(Singleton.getInstance().indProperty.getMeasuredLandArea());

            int docpos = Singleton.getInstance().indProperty.getDocumentLandAreaUnit();
            if (docpos != 0)
                Singleton.getInstance().indProperty.setDocumentLandAreaUnit(docpos);
            setMeasurementSpinner(docpos, Singleton.getInstance().measurements_list, spinner_measurementland);

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


    /*******
     * Post Land Values to insert the values
     * *******/
    public void PostLandValues() {
        getLandPropertyDetails();
    }


    /*******
     * Post Land Values in form detail for insertion to API
     * ******/

    public static void getLandPropertyDetails() {

        String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");


        if (Singleton.getInstance().indProperty != null) {

            String permissibleare = editText_compound_permissiblearea_land.getText().toString().trim();
            String measuredarea = editText_land_measurement_land.getText().toString().trim();

            if (!general.isEmpty(caseid))
                Singleton.getInstance().indProperty.setCaseId(Integer.valueOf(caseid));
            Singleton.getInstance().indProperty.setDocumentLandArea(permissibleare);
            Singleton.getInstance().indProperty.setMeasuredLandArea(measuredarea);

            /* *************
             * Get Spinner single select value
             * *********************/
            int pos = spinner_measurementland.getSelectedItemPosition();
            int measureUnitId = Singleton.getInstance().measurements_list.get(pos).getMeasureUnitId();
            if (measureUnitId != 0)
                Singleton.getInstance().indProperty.setDocumentLandAreaUnit(measureUnitId);
        }
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
                            editText_compound_permissiblearea_land.setText(my_val_str);
                            String charSequence = editText_compound_permissiblearea_land.getText().toString();
                            if (general.isEmpty(editText_land_measurement_land.getText().toString())) {
                                // If its empty
                                editText_land_measurement_land.setText(charSequence);
                            }

                            editText_land_measurement_land.setError(null);
                            // TODO Interface
                            FragmentValuationLand_ka fragmentValuationLand = new FragmentValuationLand_ka();
                            fragmentValuationLand.permission_measurment(charSequence.toString());
                        } else {
                            editText_land_measurement_land.setText(my_val_str);
                            //TODO Interface
                            FragmentValuationLand_ka fragmentValuationLand = new FragmentValuationLand_ka();
                            fragmentValuationLand.actual_measurment(my_val_str);
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
                        edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
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
                        edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
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
                        edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
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
                        edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
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
                        edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
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
                        edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
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
                        edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
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
                        edt1.setText("" + BigDecimal.valueOf(mValueCurrent).toPlainString());
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
                            editText_compound_permissiblearea_land.setText(my_val_str);
                            String charSequence = editText_compound_permissiblearea_land.getText().toString();
                            if (general.isEmpty(editText_land_measurement_land.getText().toString())) {
                                // If its empty
                                editText_land_measurement_land.setText(charSequence);
                            }
                            editText_compound_permissiblearea_land.setError(null);
                            editText_land_measurement_land.setError(null);
                            // TODO Interface
                            FragmentValuationLand_ka fragmentValuationLand = new FragmentValuationLand_ka();
                            fragmentValuationLand.permission_measurment(charSequence.toString());
                        } else {
                            editText_land_measurement_land.setText(my_val_str);
                            //TODO Interface
                            FragmentValuationLand_ka fragmentValuationLand = new FragmentValuationLand_ka();
                            fragmentValuationLand.actual_measurment(my_val_str);
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
