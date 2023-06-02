package com.realappraiser.gharvalue.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import androidx.appcompat.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.fragments.FragmentBuilding_ka;
import com.realappraiser.gharvalue.fragments.FragmentValuationBuilding_ka;
import com.realappraiser.gharvalue.fragments.OtherDetails_ka;
import com.realappraiser.gharvalue.model.FloorUsage;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.utils.DecimalDigitsInputFilter;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kaptas on 29/12/17.
 */

@SuppressWarnings("ALL")
public class PropertyGeneralFloorAdapter_ka extends RecyclerView.Adapter<PropertyGeneralFloorAdapter_ka.ViewHolder> {

    Context context;
    public ArrayList<IndPropertyFloor> steps;
    @SuppressLint("StaticFieldLeak")
    public static General general;
    PropertyFloorInternalAdapter propertyFloorInternalAdapter;
    ArrayList<FloorUsage> Inter_floors_list = new ArrayList<>();
    public Dialog dialog;

    private static final long DELAY_TIME = 2500; //3 seconds
    private static final long DELAY_TIME_7 = 3500; //3 seconds
    private Handler mHandler;
    private Runnable mJumpRunnable;

    boolean Docarea_cursor = false;


    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText edittext_general_doc_area, edittext_general_actual_area;
        EditText edittext_general_floor_name, edittext_general_comp, edittext_general_progress, edittext_general_age, edittext_general_life;
        TextView spinner_general_floor_usage;
        //Spinner spinner_stage;
        TextView spinner_stage;
        FrameLayout frameUsagelay;
        final IndPropertyFloor stepsModel = new IndPropertyFloor();

        public ViewHolder(final View itemView) {
            super(itemView);

            edittext_general_floor_name = (EditText) itemView.findViewById(R.id.edittext_general_floor_name);
            edittext_general_comp = (EditText) itemView.findViewById(R.id.edittext_general_comp);
            edittext_general_progress = (EditText) itemView.findViewById(R.id.edittext_general_progress);
            edittext_general_doc_area = (EditText) itemView.findViewById(R.id.edittext_general_doc_area);
            edittext_general_actual_area = (EditText) itemView.findViewById(R.id.edittext_general_actual_area);
            edittext_general_age = (EditText) itemView.findViewById(R.id.edittext_general_age);
            edittext_general_life = (EditText) itemView.findViewById(R.id.edittext_general_life);
            //spinner_stage = (Spinner) itemView.findViewById(R.id.spinner_stage);
            spinner_stage = (TextView) itemView.findViewById(R.id.spinner_stage);
            spinner_general_floor_usage = (TextView) itemView.findViewById(R.id.spinner_general_floor_usage);
            frameUsagelay = (FrameLayout) itemView.findViewById(R.id.frameUsagelay);
            edittext_general_floor_name.setTypeface(General.regularTypeface());
            edittext_general_comp.setTypeface(General.regularTypeface());
            edittext_general_progress.setTypeface(General.regularTypeface());
            edittext_general_doc_area.setTypeface(General.regularTypeface());
            edittext_general_actual_area.setTypeface(General.regularTypeface());
            edittext_general_age.setTypeface(General.regularTypeface());
            edittext_general_life.setTypeface(General.regularTypeface());

            //  limit the 2 char after the decimal point
            edittext_general_doc_area.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(8,2)});
            edittext_general_actual_area.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(8,2)});

            edittext_general_floor_name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (getAdapterPosition() != -1) {
                        //clear the error
                        if (FragmentBuilding_ka.textview_floor_name != null) {
                            FragmentBuilding_ka.textview_floor_name.setError(null);
                        }
                        String toString = edittext_general_floor_name.getText().toString();
                        IndPropertyFloor stepsModel = steps.get(getAdapterPosition());
                        if (!general.isEmpty(toString)) {
                            stepsModel.setFloorName(toString);
                        } else {
                            stepsModel.setFloorName("");
                        }
                        steps.set(getAdapterPosition(), stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(getAdapterPosition(), stepsModel);
                        // PropertyFloorInternalAdapter
                        if (FragmentBuilding_ka.internalfloorlistAdapter != null) {
                            FragmentBuilding_ka.internalfloorlistAdapter.steps.set(getAdapterPosition(), stepsModel);
                            FragmentBuilding_ka.internalfloorlistAdapter.notifyItemChanged(getAdapterPosition());
                        }
                        // ValuationPermissibleAreaAdapter
                        if (FragmentValuationBuilding_ka.listAdapter != null) {
                            FragmentValuationBuilding_ka.listAdapter.steps.set(getAdapterPosition(), stepsModel);
                            FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(getAdapterPosition());
                        }
                        // ValuationActualAreaAdapter
                        if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                            FragmentValuationBuilding_ka.listActualAdapter.steps.set(getAdapterPosition(), stepsModel);
                            FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(getAdapterPosition());
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });


            edittext_general_comp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        // code to execute when EditText loses focus
                        Log.e("onFocus_coming", "onFocus_coming" + hasFocus);
                        if (getAdapterPosition() != -1) {
                            //clear the error
                            if (FragmentBuilding_ka.textview_comp != null) {
                                FragmentBuilding_ka.textview_comp.setError(null);
                            }
                            String toString = edittext_general_comp.getText().toString();
                            IndPropertyFloor stepsModel = steps.get(getAdapterPosition());
                            if (!general.isEmpty(toString)) {
                                stepsModel.setPercentageCompletion(Integer.valueOf(toString));
                            } else {
                                stepsModel.setPercentageCompletion(null);
                            }
                            steps.set(getAdapterPosition(), stepsModel);
                            Singleton.getInstance().indPropertyFloors.set(getAdapterPosition(), stepsModel);

                            int average_total = general.getCompletedSumValue(steps);
                            // set Average
                            if (FragmentBuilding_ka.textview_comp_total != null) {
                                FragmentBuilding_ka.textview_comp_total.setText("" + average_total);
                            }
                            // set Aspercom
                            FragmentValuationBuilding_ka fragmentValuationBuilding = new FragmentValuationBuilding_ka();
                            fragmentValuationBuilding.aspercompletion_val("" + average_total);


                            // is_new_floor_created - > New rows are created by user
                            /*if (Singleton.getInstance().is_new_floor_created) {
                                if (steps.size() > 0) {
                                    boolean is_not_null = true;
                                    for (int x = 0; x < steps.size(); x++) {
                                        if (general.isEmpty(String.valueOf(steps.get(x).getPercentageCompletion()))) {
                                            // If Empty come in
                                            is_not_null = false;
                                        }
                                    }
                                    if (is_not_null) {
                                        // Old condtion
                                        if (Singleton.getInstance().as_per_com) {
                                            FragmentValuationBuilding_ka fragmentValuationBuilding = new FragmentValuationBuilding_ka();
                                            fragmentValuationBuilding.aspercompletion_val("" + average_total);
                                            Singleton.getInstance().as_per_com = false;
                                        }
                                    }
                                }
                            }*/


                        }
                    }
                }
            });

            edittext_general_progress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        if (getAdapterPosition() != -1) {
                            String toString = edittext_general_progress.getText().toString();
                            IndPropertyFloor stepsModel = steps.get(getAdapterPosition());
                            if (!general.isEmpty(toString)) {
                                stepsModel.setPresentCondition(toString);
                            } else {
                                stepsModel.setPresentCondition("");
                            }
                            steps.set(getAdapterPosition(), stepsModel);
                            Singleton.getInstance().indPropertyFloors.set(getAdapterPosition(), stepsModel);
                        }
                    }
                }
            });

            edittext_general_doc_area.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        // code to execute when EditText loses focus
                        Log.e("onFocus_coming", "onFocus_coming" + hasFocus);
                        if (getAdapterPosition() != -1) {
                            String toString = edittext_general_doc_area.getText().toString();
                            if (Permission_area_Empty_condtion(toString, getAdapterPosition())) {
                                if (!general.isEmpty(toString)) {
                                    // If the value is not empty then it will notifyDataSetChanged
                                    Singleton.getInstance().is_edit_floor_docarea = false;
                                    //hideSoftKeyboard(edittext_general_doc_area);
                                    //Docarea_cursor = true;
                                    doc_area_cursor_moment(edittext_general_doc_area);
                                    notifyDataSetChanged();
                                }
                                /*OtherDetails.ClearFoucs();*/
                                /*edittext_general_age.requestFocus();
                                edittext_general_age.setSelection(edittext_general_age.getText().toString().length());
                                edittext_general_age.requestFocusFromTouch();*/
                            } else {
                                if ((Singleton.getInstance().is_edit_floor_docarea) && (!general.isEmpty(toString))) {
                                    // Edit First Time and not null too
                                    Singleton.getInstance().is_edit_floor_docarea = false;
                                    if (steps.size() > 0) {
                                        // For copying rows for the selected one
                                        Permission_area_module(toString, getAdapterPosition());
                                        // If MeasuredFloorArea is not empty the DocumentFloorArea won't be copy
                                        IndPropertyFloor stepsModel_new = steps.get(getAdapterPosition());
                                        if (general.isEmpty(stepsModel_new.getMeasuredFloorArea())) {
                                            Actual_area_module(toString, getAdapterPosition());
                                        }
                                        // Check all rows
                                        for (int x = 0; x < steps.size(); x++) {
                                            IndPropertyFloor stepsModel = steps.get(x);
                                            if (general.isEmpty(stepsModel.getDocumentFloorArea())) {
                                                Permission_area_module(toString, x);
                                                if (general.isEmpty(stepsModel.getMeasuredFloorArea())) {
                                                    Actual_area_module(toString, x);
                                                }
                                            }
                                        }
                                        //hideSoftKeyboard(edittext_general_doc_area);
                                        //Docarea_cursor = true;
                                        doc_area_cursor_moment(edittext_general_doc_area);
                                        notifyDataSetChanged();
                                    }
                                } else {
                                    // After whole copy
                                    Permission_area_module(toString, getAdapterPosition());
                                    // If MeasuredFloorArea is not empty the DocumentFloorArea won't be copy
                                    IndPropertyFloor stepsModel = steps.get(getAdapterPosition());
                                    if (general.isEmpty(stepsModel.getMeasuredFloorArea())) {
                                        Actual_area_module(toString, getAdapterPosition());
                                        if (!general.isEmpty(toString)) {
                                            edittext_general_actual_area.setText(toString);
                                        } else {
                                            edittext_general_actual_area.setText("");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });

            edittext_general_actual_area.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        // code to execute when EditText loses focus
                        Log.e("onFocus_coming", "onFocus_coming" + hasFocus);
                        if (getAdapterPosition() != -1) {
                            String toString = edittext_general_actual_area.getText().toString();
                            Actual_area_module(toString, getAdapterPosition());
                        }
                    }
                }
            });

            edittext_general_age.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        // code to execute when EditText loses focus
                        Log.e("onFocus_coming", "onFocus_coming" + hasFocus);
                        if (getAdapterPosition() != -1) {
                            String toString = edittext_general_age.getText().toString();
                            if (Age_Empty_condtion(toString, getAdapterPosition())) {
                                if (!general.isEmpty(toString)) {
                                    // If the value is not empty then it will notifyDataSetChanged
                                    Singleton.getInstance().is_edit_floor_age = false;

                                    // For underconstruction it will copy alone the paricluar field
                                    Age_and_life_elsecondition(toString, getAdapterPosition());

                                    hideSoftKeyboard(edittext_general_age);
                                    notifyDataSetChanged();
                                }
                            } else {
                                if ((Singleton.getInstance().is_edit_floor_age) && (!general.isEmpty(toString))) {
                                    // Edit First Time
                                    Singleton.getInstance().is_edit_floor_age = false;
                                    if (steps.size() > 0) {
                                        // For copying rows for the selected one
                                        Age_and_Life(toString, getAdapterPosition(), false);
                                        // Check all rows
                                        for (int x = 0; x < steps.size(); x++) {
                                            IndPropertyFloor stepsModel = steps.get(x);
                                            if (general.isEmpty(String.valueOf(stepsModel.getPropertyAge()))) {
                                                Age_and_Life(toString, x, false);
                                            }
                                        }

                                        // For underconstruction it will copy alone the paricluar field
                                        Age_and_life_elsecondition(toString, getAdapterPosition());

                                        hideSoftKeyboard(edittext_general_age);
                                        notifyDataSetChanged();
                                    }
                                } else {
                                    // After whole copy
                                    IndPropertyFloor stepsModel = steps.get(getAdapterPosition());
                                    if (!general.isEmpty(toString)) {
                                        if (Integer.valueOf(toString) < 46) {
                                            stepsModel.setPropertyAge(Integer.valueOf(toString));
                                            // Added in n ew feature
                                            stepsModel.setPercentageDepreciation(toString);
                                            //if (general.isEmpty(String.valueOf(stepsModel.getResidualLife()))) {
                                            // If ResidualLife is empty it will affect
                                            int life = general.getAgeLifeValue(Integer.valueOf(toString));
                                            stepsModel.setResidualLife(life);
                                            edittext_general_life.setText("" + life);
                                            edittext_general_life.setSelection(edittext_general_life.getText().toString().length());
                                            //}
                                            steps.set(getAdapterPosition(), stepsModel);
                                            Singleton.getInstance().indPropertyFloors.set(getAdapterPosition(), stepsModel);
                                        } else {
                                            stepsModel.setPropertyAge(Integer.valueOf(toString));
                                            // Added in new feature
                                            stepsModel.setPercentageDepreciation(toString);
                                            //if (general.isEmpty(String.valueOf(stepsModel.getResidualLife()))) {
                                            // If ResidualLife is empty it will affect
                                            stepsModel.setResidualLife(null);
                                            edittext_general_life.setText("");
                                            edittext_general_life.setSelection(edittext_general_life.getText().toString().length());
                                            //}
                                            steps.set(getAdapterPosition(), stepsModel);
                                            Singleton.getInstance().indPropertyFloors.set(getAdapterPosition(), stepsModel);
                                        }
                                        // ValuationPermissibleAreaAdapter
                                        if (FragmentValuationBuilding_ka.listAdapter != null) {
                                            FragmentValuationBuilding_ka.listAdapter.steps.set(getAdapterPosition(), stepsModel);
                                            FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(getAdapterPosition());
                                        }
                                        // ValuationActualAreaAdapter
                                        if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                                            FragmentValuationBuilding_ka.listActualAdapter.steps.set(getAdapterPosition(), stepsModel);
                                            FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(getAdapterPosition());
                                        }
                                    } else {
                                        stepsModel.setPropertyAge(null);
                                        // Added in new feature
                                        stepsModel.setPercentageDepreciation(null);
                                        stepsModel.setResidualLife(null);
                                        edittext_general_life.setText("");
                                        steps.set(getAdapterPosition(), stepsModel);
                                        Singleton.getInstance().indPropertyFloors.set(getAdapterPosition(), stepsModel);
                                        // ValuationPermissibleAreaAdapter
                                        if (FragmentValuationBuilding_ka.listAdapter != null) {
                                            FragmentValuationBuilding_ka.listAdapter.steps.set(getAdapterPosition(), stepsModel);
                                            FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(getAdapterPosition());
                                        }
                                        // ValuationActualAreaAdapter
                                        if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                                            FragmentValuationBuilding_ka.listActualAdapter.steps.set(getAdapterPosition(), stepsModel);
                                            FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(getAdapterPosition());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });

            edittext_general_life.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        // code to execute when EditText loses focus
                        Log.e("onFocus_coming", "onFocus_coming" + hasFocus);
                        if (getAdapterPosition() != -1) {
                            String toString = edittext_general_life.getText().toString();
                            IndPropertyFloor stepsModel = steps.get(getAdapterPosition());
                            if (!general.isEmpty(toString)) {
                                stepsModel.setResidualLife(Integer.valueOf(toString));
                            } else {
                                stepsModel.setResidualLife(null);
                            }
                            steps.set(getAdapterPosition(), stepsModel);
                            Singleton.getInstance().indPropertyFloors.set(getAdapterPosition(), stepsModel);
                        }
                    }
                }
            });


            spinner_stage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //clear the error
                    if (FragmentBuilding_ka.textview_stage != null) {
                        FragmentBuilding_ka.textview_stage.setError(null);
                    }
                    hideSoftKeyboard(spinner_stage);
                    if (getAdapterPosition() != -1) {
                        String floortoString = "";
                        if (!general.isEmpty(edittext_general_floor_name.getText().toString())) {
                            floortoString = edittext_general_floor_name.getText().toString();
                        }
                        stage_popup(getAdapterPosition(), spinner_stage, edittext_general_comp, edittext_general_progress, edittext_general_age, edittext_general_life, floortoString);
                    }
                }
            });

            frameUsagelay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideSoftKeyboard(frameUsagelay);
                    if (getAdapterPosition() != -1) {
                        showdialog_dynamic(getAdapterPosition(), spinner_general_floor_usage);
                    }
                }
            });


            /********* suganya Integration - Cursor Visible on Next or Enter button editor listener **********/
            edittext_general_floor_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        spinner_stage.clearFocus();
                        if (spinner_stage.getText().toString().equalsIgnoreCase(context.getResources().getString(R.string.underconstruction))) {
                            edittext_general_floor_name.setNextFocusForwardId(R.id.edittext_general_comp);
                            edittext_general_comp.setSelection(edittext_general_comp.getText().toString().length());
                        } else {
                            edittext_general_floor_name.setNextFocusForwardId(R.id.edittext_general_doc_area);
                            edittext_general_doc_area.setSelection(edittext_general_doc_area.getText().toString().length());
                        }

                    }
                    return false;
                }
            });

            edittext_general_comp.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        edittext_general_progress.setSelection(edittext_general_progress.getText().toString().length());
                    }
                    return false;
                }
            });

            edittext_general_progress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        edittext_general_doc_area.setSelection(edittext_general_doc_area.getText().toString().length());
                    }
                    return false;
                }
            });

            edittext_general_doc_area.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        edittext_general_actual_area.setSelection(edittext_general_actual_area.getText().toString().length());
                    }
                    return false;
                }
            });

            edittext_general_actual_area.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        edittext_general_age.setSelection(edittext_general_age.getText().toString().length());
                    }
                    return false;
                }
            });

            edittext_general_age.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        edittext_general_life.setSelection(edittext_general_life.getText().toString().length());
                    }
                    return false;
                }
            });

            edittext_general_life.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        edittext_general_floor_name.setSelection(edittext_general_floor_name.getText().toString().length());
                       /* spinner_stage.clearFocus();
                        spinner_general_floor_usage.clearFocus();*/
                    }
                    return false;
                }
            });

        }
    }


    private void hideSoftKeyboard(View addkeys) {
        if (addkeys != null) {
            InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(addkeys.getWindowToken(), 0);
        }
        show_emptyFocus();
    }

    public void show_emptyFocus() {
        // Show focus
        if (OtherDetails_ka.my_focuslayout != null) {
            OtherDetails_ka.my_focuslayout.requestFocus();
        }
    }

    private void showdialog_dynamic(final int adapter_postion, final TextView textView) {

        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.multiselect_checkbox, null);
        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(context);
        alertdialogBuilder.setView(view);
        final AlertDialog alertDialog = alertdialogBuilder.show();
        alertDialog.setCancelable(false);

        TextView textview_heading = (TextView) alertDialog.findViewById(R.id.textview_heading);
        Button btn_cancel = (Button) alertDialog.findViewById(R.id.btn_cancel);
        Button btn_save = (Button) alertDialog.findViewById(R.id.btn_save);
        RecyclerView recyclerview_dialog = (RecyclerView) alertDialog.findViewById(R.id.recyclerview_dialog);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerview_dialog.setLayoutManager(linearLayoutManager);
        textview_heading.setText(context.getResources().getString(R.string.actualusage));
        textview_heading.setTypeface(general.mediumtypeface());

        ArrayList<FloorUsage> FloorUsage_list = new ArrayList<>();
        FloorUsage_list = Singleton.getInstance().floor_usage;

        final IndPropertyFloor stepsModel = steps.get(adapter_postion);
        String selectedId = stepsModel.getPropertyFloorUsageId();

        PropertyFloorUsageId propertyActualUsageAdapter = new PropertyFloorUsageId(context, FloorUsage_list, selectedId);
        recyclerview_dialog.setAdapter(propertyActualUsageAdapter);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Type -> Floor
                if (Singleton.getInstance().FloorUsage_id.size() > 0) {
                    String FloorUsage_id = general.remove_array_brac_and_space(Singleton.getInstance().FloorUsage_id.toString());
                    // set the Inter floor ID
                    stepsModel.setPropertyFloorUsageId(FloorUsage_id);
                    // setText to the floor text
                    String FloorUsage_name = general.remove_array_brac_and_space(Singleton.getInstance().FloorUsage_name.toString());
                    stepsModel.setPropertyFloorUsageId_str(FloorUsage_name);
                    steps.set(adapter_postion, stepsModel);
                    Singleton.getInstance().indPropertyFloors.set(adapter_postion, stepsModel);
                    textView.setText(FloorUsage_name);
                } else {
                    // clear all the ID and dummy data and clear the settext
                    stepsModel.setPropertyFloorUsageId("");
                    stepsModel.setPropertyFloorUsageId_str("");
                    steps.set(adapter_postion, stepsModel);
                    Singleton.getInstance().indPropertyFloors.set(adapter_postion, stepsModel);
                    textView.setText(context.getResources().getString(R.string.select));
                }
                Log.e("FloorUsage_id", "::: " + Singleton.getInstance().FloorUsage_id);
                Log.e("FloorUsage_name", ":: " + Singleton.getInstance().FloorUsage_name);
                alertDialog.dismiss();
            }
        });
    }


    public PropertyGeneralFloorAdapter_ka(ArrayList<IndPropertyFloor> steps, Context context) {
        this.steps = steps;
        this.context = context;
        general = new General((Activity) context);
        // propertyFloorInternalAdapter = new PropertyFloorInternalAdapter((Activity)context);
    }


    @Override
    public int getItemCount() {
        return steps.size();
    }

    public void reload_adapter(int index) {
        notifyItemChanged(index);
    }

    @Override
    public PropertyGeneralFloorAdapter_ka.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_floors_ka, viewGroup, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        // Cursor
        if (Docarea_cursor) {
            String propertyAge = String.valueOf(steps.get(position).getPropertyAge());
            if (general.isEmpty(propertyAge)) {
                Docarea_cursor = false;
                holder.edittext_general_age.requestFocus();
            }
        }

        String floorname = steps.get(position).getFloorName();
        if (!general.isEmpty(floorname)) {
            holder.edittext_general_floor_name.setText(floorname);
        } else {
            holder.edittext_general_floor_name.setText("");
        }

        int constructionStageId = steps.get(position).getConstructionStageId();
        if (constructionStageId == 1) {
            // not editable - complete
            holder.edittext_general_comp.setEnabled(false);
            holder.edittext_general_progress.setEnabled(false);
            holder.spinner_stage.setText(context.getResources().getString(R.string.complete));
        } else if (constructionStageId == 2) {
            // editable - underconstruction
            holder.edittext_general_comp.setEnabled(true);
            holder.edittext_general_progress.setEnabled(true);
            holder.spinner_stage.setText(context.getResources().getString(R.string.underconstruction));
        } else {
            // not editable - select
            holder.edittext_general_comp.setEnabled(false);
            holder.edittext_general_progress.setEnabled(false);
            holder.spinner_stage.setText(context.getResources().getString(R.string.select));
        }

        Integer percentageCompletion = steps.get(position).getPercentageCompletion();
        if (percentageCompletion != null) {
            holder.edittext_general_comp.setText("" + percentageCompletion);
        } else {
            holder.edittext_general_comp.setText("");
        }

        String presentCondition = steps.get(position).getPresentCondition();
        if (!general.isEmpty(presentCondition)) {
            holder.edittext_general_progress.setText("" + presentCondition);
        } else {
            holder.edittext_general_progress.setText("");
        }

        String documentFloorArea = steps.get(position).getDocumentFloorArea();
        if (!general.isEmpty(documentFloorArea)) {
            holder.edittext_general_doc_area.setText("" + documentFloorArea);
        } else {
            holder.edittext_general_doc_area.setText("");
        }

        String measuredFloorArea = steps.get(position).getMeasuredFloorArea();

        if (!general.isEmpty(measuredFloorArea)) {
            holder.edittext_general_actual_area.setText("" + measuredFloorArea);
        } else {
            holder.edittext_general_actual_area.setText("");
        }

        String propertyAge = String.valueOf(steps.get(position).getPropertyAge());
        if (!general.isEmpty(propertyAge)) {
            holder.edittext_general_age.setText("" + propertyAge);
        } else {
            holder.edittext_general_age.setText("");
        }

        String ResidualLife = String.valueOf(steps.get(position).getResidualLife());
        if (!general.isEmpty(ResidualLife)) {
            holder.edittext_general_life.setText("" + ResidualLife);
        } else {
            holder.edittext_general_life.setText("");
        }

        //holder.spinner_stage.setSelection(steps.get(position).getConstructionStageId());


        Inter_floors_list = new ArrayList<>();
        Inter_floors_list = Singleton.getInstance().floorUsages_list;
        // check Floor Dropdown is empty
        if (Inter_floors_list.size() > 0) {
            String getIntFloorId = steps.get(position).getPropertyFloorUsageId();
            Log.e("FloorUsage_name_id", "" + getIntFloorId);

            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> Inter_floors_list_selected_id_commo = new ArrayList<>();
                Inter_floors_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (Inter_floors_list_selected_id_commo.size() > 0) {
                    Singleton.getInstance().FloorUsage_id.clear();
                    Singleton.getInstance().FloorUsage_name.clear();
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < Inter_floors_list.size(); x++) {
                        if (Inter_floors_list_selected_id_commo.toString().contains("" + Inter_floors_list.get(x).getPropertyFloorUsageId())) {
                            for (int y = 0; y < Inter_floors_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(Inter_floors_list_selected_id_commo.get(y));
                                if (Inter_floors_list.get(x).getPropertyFloorUsageId() == one_by_one_id) {
                                    Singleton.getInstance().FloorUsage_id.add(Inter_floors_list.get(x).getPropertyFloorUsageId());
                                    Singleton.getInstance().FloorUsage_name.add(Inter_floors_list.get(x).getName());
                                }
                            }
                            String FloorUsage_name = general.remove_array_brac_and_space(Singleton.getInstance().FloorUsage_name.toString());
                            Log.e("FloorUsage_name_1", "" + FloorUsage_name);
                            steps.get(position).setPropertyFloorUsageId_str(FloorUsage_name);
                        }
                    }
                }
            } else {
                steps.get(position).setPropertyFloorUsageId_str("");
            }
        }


        String propertyFloorUsageId_str = steps.get(position).getPropertyFloorUsageId_str();
        Log.e("FloorUsage_name_2", "" + propertyFloorUsageId_str);
        if (!general.isEmpty(propertyFloorUsageId_str)) {
            holder.spinner_general_floor_usage.setText("" + propertyFloorUsageId_str);
        } else {
            holder.spinner_general_floor_usage.setText(context.getResources().getString(R.string.select));
        }


    }


    public ArrayList<IndPropertyFloor> getStepList() {
        return steps;
    }


    public boolean Permission_area_Empty_condtion(String str, int change_position) {
        boolean construction_empty = false;
        int empty_loop = 0;
        Log.e("steps_size_in", "start: " + steps.size());
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (general.isEmpty(stepsModel.getDocumentFloorArea())) {
                    //Is Empty
                    empty_loop = empty_loop + 1;
                }
            }
        }
        if (steps.size() == empty_loop) {
            construction_empty = true;
            // If copy all the text
            //for (int x = change_position; x < steps.size(); x++) {
            // If copy only from the below field
            for (int x = 0; x < steps.size(); x++) {
                Permission_area_module(str, x);
                if (general.isEmpty(steps.get(x).getMeasuredFloorArea())) {
                    // If its empty
                    Actual_area_module(str, x);
                }
            }
        } else {
            construction_empty = false;
        }
        return construction_empty;
    }

    public boolean Permission_area_Empty_condtion_old(String str) {
        boolean is_not_null = true;
        if (!general.isEmpty(str)) {
            if (steps.size() > 0) {
                is_not_null = true;
                for (int x = 0; x < steps.size(); x++) {
                    IndPropertyFloor stepsModel = steps.get(x);
                    if (!general.isEmpty(stepsModel.getDocumentFloorArea())) {
                        // All Text are Not Null
                        is_not_null = false;
                    }
                }
                if (is_not_null) {
                    // Copy all the vaues
                    for (int x = 0; x < steps.size(); x++) {
                        Permission_area_module(str, x);
                        if (general.isEmpty(steps.get(x).getMeasuredFloorArea())) {
                            // If its empty
                            Actual_area_module(str, x);
                        }
                    }
                }
            }
        } else {
            is_not_null = false;
        }
        return is_not_null;
    }


    @SuppressLint("SetTextI18n")
    public void Permission_area_module(String toString, int adapterposition) {
        if (adapterposition != -1) {
            IndPropertyFloor stepsModel = steps.get(adapterposition);
            if (!general.isEmpty(toString.toString())) {
                //  Save Doc Area to modal
                stepsModel.setDocumentFloorArea(toString.toString());
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                // Total Doc Area
                int total_documentarea = general.getDocSumValue(steps);
                //FragmentValuationBuilding fragmentValuationBuilding_is = new FragmentValuationBuilding();
                if (FragmentBuilding_ka.textview_doc_total != null) {
                    FragmentBuilding_ka.textview_doc_total.setText("" + total_documentarea);
                    FragmentValuationBuilding_ka.constrction_measurment(String.valueOf(total_documentarea));
                } else {
                    FragmentValuationBuilding_ka.constrction_measurment("");
                }
                // ValuationPermissibleAreaAdapter
                if (FragmentValuationBuilding_ka.listAdapter != null) {
                    FragmentValuationBuilding_ka.listAdapter.steps.set(adapterposition, stepsModel);
                    FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(adapterposition);
                }
                // ValuationActualAreaAdapter
                if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                    FragmentValuationBuilding_ka.listActualAdapter.steps.set(adapterposition, stepsModel);
                    FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(adapterposition);
                }
            } else {
                String docinit = null;
                stepsModel.setDocumentFloorArea(docinit);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                // Total Doc Area
                int total_documentarea = general.getDocSumValue(steps);
                //FragmentValuationBuilding fragmentValuationBuilding_is = new FragmentValuationBuilding();
                if (FragmentBuilding_ka.textview_doc_total != null) {
                    FragmentBuilding_ka.textview_doc_total.setText("" + total_documentarea);
                    FragmentValuationBuilding_ka.constrction_measurment(String.valueOf(total_documentarea));
                } else {
                    FragmentValuationBuilding_ka.constrction_measurment("");
                }
                // ValuationPermissibleAreaAdapter
                if (FragmentValuationBuilding_ka.listAdapter != null) {
                    FragmentValuationBuilding_ka.listAdapter.steps.set(adapterposition, stepsModel);
                    FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(adapterposition);
                }
                // ValuationActualAreaAdapter
                if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                    FragmentValuationBuilding_ka.listActualAdapter.steps.set(adapterposition, stepsModel);
                    FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(adapterposition);
                }
            }
        }
    }


    @SuppressLint("SetTextI18n")
    public void Actual_area_module(String toString, int adapterposition) {
        if (adapterposition != -1) {
            IndPropertyFloor stepsModel = steps.get(adapterposition);
            if (!general.isEmpty(toString.toString())) {
                //  Save Doc Area to modal
                stepsModel.setMeasuredFloorArea(toString.toString());
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                // Total Doc Area
                int total_actualarea = general.getMeasureSumValue(steps);
                //FragmentValuationBuilding fragmentValuationBuilding_is = new FragmentValuationBuilding();
                if (FragmentBuilding_ka.textview_actual_total != null) {
                    FragmentBuilding_ka.textview_actual_total.setText("" + total_actualarea);
                    FragmentValuationBuilding_ka.constrction_actual(String.valueOf(total_actualarea));
                } else {
                    FragmentValuationBuilding_ka.constrction_actual("");
                }
                // ValuationPermissibleAreaAdapter
                if (FragmentValuationBuilding_ka.listAdapter != null) {
                    FragmentValuationBuilding_ka.listAdapter.steps.set(adapterposition, stepsModel);
                    FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(adapterposition);
                }
                // ValuationActualAreaAdapter
                if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                    FragmentValuationBuilding_ka.listActualAdapter.steps.set(adapterposition, stepsModel);
                    FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(adapterposition);
                }
            } else {
                //  Save Doc Area to modal
                String docinit = null;
                stepsModel.setMeasuredFloorArea(docinit);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                // Total Doc Area
                int total_actualarea = general.getMeasureSumValue(steps);
                //FragmentValuationBuilding fragmentValuationBuilding_is = new FragmentValuationBuilding();
                if (FragmentBuilding_ka.textview_actual_total != null) {
                    FragmentBuilding_ka.textview_actual_total.setText("" + total_actualarea);
                    FragmentValuationBuilding_ka.constrction_actual(String.valueOf(total_actualarea));
                } else {
                    FragmentValuationBuilding_ka.constrction_actual("");
                }
                // ValuationPermissibleAreaAdapter
                if (FragmentValuationBuilding_ka.listAdapter != null) {
                    FragmentValuationBuilding_ka.listAdapter.steps.set(adapterposition, stepsModel);
                    FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(adapterposition);
                }
                // ValuationActualAreaAdapter
                if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                    FragmentValuationBuilding_ka.listActualAdapter.steps.set(adapterposition, stepsModel);
                    FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(adapterposition);
                }
            }
        }
    }


    public boolean Construction_empty() {
        boolean construction_empty = false;
        int empty_loop = 0;
        Log.e("steps_size_in", "start: " + steps.size());
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getConstructionStageId() == 0) {
                    empty_loop = empty_loop + 1;
                }
            }
        }
        if (steps.size() == empty_loop) {
            construction_empty = true;
        } else {
            construction_empty = false;
        }
        return construction_empty;
    }


    public boolean Age_Empty_condtion(String str, int change_position) {
        boolean construction_empty = false;
        int empty_loop = 0;
        Log.e("steps_size_in", "start: " + steps.size());
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (general.isEmpty(String.valueOf(stepsModel.getPropertyAge()))) {
                    //Is Empty
                    empty_loop = empty_loop + 1;
                }
            }
        }
        if (steps.size() == empty_loop) {
            construction_empty = true;

            if (!general.isEmpty(str)) {
                // If the value is not empty then it will notifyDataSetChanged
                boolean check_deper_first_time = false;
                if ((Singleton.getInstance().is_new_floor_created) && (Singleton.getInstance().as_deper_first_time)) {
                    check_deper_first_time = true;
                    Singleton.getInstance().as_deper_first_time = false;
                }

                // If copy all the text
                //for (int x = change_position; x < steps.size(); x++) {
                // If copy only from the below field
                for (int x = 0; x < steps.size(); x++) {
                    Age_and_Life(str, x, check_deper_first_time);
                }
            }

        } else {
            construction_empty = false;
        }
        return construction_empty;
    }

    public void Age_and_Life(String toString, int adapterposition, boolean check_deper_first_time) {
        if (adapterposition != -1) {
            IndPropertyFloor stepsModel = steps.get(adapterposition);
            if ((!general.isEmpty(toString)) && (steps.get(adapterposition).getConstructionStageId() != 2)) {
                // (steps.get(adapterposition).getConstructionStageId() != 2)
                // Age is not empty and construction stage is not under construction
                if (Integer.valueOf(toString) < 46) {
                    stepsModel.setPropertyAge(Integer.valueOf(toString));
                    stepsModel.setPercentageDepreciation(toString);
                    /*if (check_deper_first_time) {
                        stepsModel.setPercentageDepreciation(toString);
                    }*/
                    //if (general.isEmpty(String.valueOf(stepsModel.getResidualLife()))) {
                    // If ResidualLife is empty it will affect
                    int life = general.getAgeLifeValue(Integer.valueOf(toString));
                    stepsModel.setResidualLife(life);
                    //}
                    steps.set(adapterposition, stepsModel);
                    Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                } else {
                    stepsModel.setPropertyAge(Integer.valueOf(toString));
                    stepsModel.setPercentageDepreciation(toString);
                    /*if (check_deper_first_time) {
                        stepsModel.setPercentageDepreciation(toString);
                    }*/
                    //if (general.isEmpty(String.valueOf(stepsModel.getResidualLife()))) {
                    // If ResidualLife is empty it will affect
                    stepsModel.setResidualLife(null);
                    //}
                    steps.set(adapterposition, stepsModel);
                    Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                }
                // ValuationPermissibleAreaAdapter
                if (FragmentValuationBuilding_ka.listAdapter != null) {
                    FragmentValuationBuilding_ka.listAdapter.steps.set(adapterposition, stepsModel);
                    FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(adapterposition);
                }
                // ValuationActualAreaAdapter
                if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                    FragmentValuationBuilding_ka.listActualAdapter.steps.set(adapterposition, stepsModel);
                    FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(adapterposition);
                }
            } else {
                stepsModel.setPropertyAge(null);
                stepsModel.setResidualLife(null);
                stepsModel.setPercentageDepreciation(null);
                /*if (check_deper_first_time) {
                    stepsModel.setPercentageDepreciation("0");
                }*/
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                // ValuationPermissibleAreaAdapter
                if (FragmentValuationBuilding_ka.listAdapter != null) {
                    FragmentValuationBuilding_ka.listAdapter.steps.set(adapterposition, stepsModel);
                    FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(adapterposition);
                }
                // ValuationActualAreaAdapter
                if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                    FragmentValuationBuilding_ka.listActualAdapter.steps.set(adapterposition, stepsModel);
                    FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(adapterposition);
                }
            }
        }
    }


    public void Age_and_life_elsecondition(String toString, int AdapterPosition) {
        // After whole copy
        IndPropertyFloor stepsModel = steps.get(AdapterPosition);
        if (!general.isEmpty(toString)) {
            if (Integer.valueOf(toString) < 46) {
                stepsModel.setPropertyAge(Integer.valueOf(toString));
                // Added in n ew feature
                stepsModel.setPercentageDepreciation(toString);
                //if (general.isEmpty(String.valueOf(stepsModel.getResidualLife()))) {
                // If ResidualLife is empty it will affect
                int life = general.getAgeLifeValue(Integer.valueOf(toString));
                stepsModel.setResidualLife(life);
                //}
                steps.set(AdapterPosition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(AdapterPosition, stepsModel);
            } else {
                stepsModel.setPropertyAge(Integer.valueOf(toString));
                // Added in new feature
                stepsModel.setPercentageDepreciation(toString);
                //if (general.isEmpty(String.valueOf(stepsModel.getResidualLife()))) {
                // If ResidualLife is empty it will affect
                stepsModel.setResidualLife(null);
                //}
                steps.set(AdapterPosition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(AdapterPosition, stepsModel);
            }
            // ValuationPermissibleAreaAdapter
            if (FragmentValuationBuilding_ka.listAdapter != null) {
                FragmentValuationBuilding_ka.listAdapter.steps.set(AdapterPosition, stepsModel);
                FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(AdapterPosition);
            }
            // ValuationActualAreaAdapter
            if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                FragmentValuationBuilding_ka.listActualAdapter.steps.set(AdapterPosition, stepsModel);
                FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(AdapterPosition);
            }
        } else {
            stepsModel.setPropertyAge(null);
            // Added in new feature
            stepsModel.setPercentageDepreciation(null);
            stepsModel.setResidualLife(null);
            steps.set(AdapterPosition, stepsModel);
            Singleton.getInstance().indPropertyFloors.set(AdapterPosition, stepsModel);
            // ValuationPermissibleAreaAdapter
            if (FragmentValuationBuilding_ka.listAdapter != null) {
                FragmentValuationBuilding_ka.listAdapter.steps.set(AdapterPosition, stepsModel);
                FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(AdapterPosition);
            }
            // ValuationActualAreaAdapter
            if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                FragmentValuationBuilding_ka.listActualAdapter.steps.set(AdapterPosition, stepsModel);
                FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(AdapterPosition);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void stage_popup(final int pos, final TextView textView, final TextView edittext_general_comp, final TextView edittext_general_progress, final TextView edittext_general_age, final TextView edittext_general_life, String floortoString) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.construction_type_popup);

        TextView popuptitle = (TextView) dialog.findViewById(R.id.title);
        popuptitle.setTypeface(general.mediumtypeface());
        if (!general.isEmpty(floortoString)) {
            popuptitle.setText(context.getResources().getString(R.string.stage_type) + " - " + floortoString);
        }

        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        RadioGroup id_radiogroup_stage = (RadioGroup) dialog.findViewById(R.id.id_radiogroup_stage);
        RadioButton id_radio_complete = (RadioButton) dialog.findViewById(R.id.id_radio_complete);
        RadioButton id_radio_underconstruction = (RadioButton) dialog.findViewById(R.id.id_radio_underconstruction);
        id_radio_complete.setTypeface(general.mediumtypeface());
        id_radio_underconstruction.setTypeface(general.mediumtypeface());

        if (!general.isEmpty(String.valueOf(steps.get(pos).getConstructionStageId()))) {
            if (steps.get(pos).getConstructionStageId() == 1) {
                id_radio_complete.setChecked(true);
                id_radio_underconstruction.setChecked(false);
            } else if (steps.get(pos).getConstructionStageId() == 2) {
                id_radio_complete.setChecked(false);
                id_radio_underconstruction.setChecked(true);
            }
        }

        id_radiogroup_stage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int spinner_position = 0;
                RadioButton id_radiogenearal = (RadioButton) dialog.findViewById(group.getCheckedRadioButtonId());
                if (id_radiogenearal.getText().toString().equalsIgnoreCase(context.getResources().getString(R.string.complete))) {
                    Log.e("stage_is", "complete");
                    textView.setText(context.getResources().getString(R.string.complete));
                    spinner_position = 1;
                    //clear the error
                    if (FragmentBuilding_ka.textview_comp != null) {
                        FragmentBuilding_ka.textview_comp.setError(null);
                    }
                } else {
                    Log.e("stage_is", "undercomplete");
                    textView.setText(context.getResources().getString(R.string.underconstruction));
                    spinner_position = 2;
                }

                // TODO - if condtion for New floor stage
                if (Construction_empty()) {
                    Singleton.getInstance().is_edit_floor_satge = false;
                    // Start Inspection first time only
                    boolean is_not_null = true;
                    for (int x = 0; x < steps.size(); x++) {
                        final IndPropertyFloor stepsModel = steps.get(x);
                        stepsModel.setConstructionStageId(Singleton.getInstance().constructions_list.get(spinner_position).getConstructionId());
                        steps.set(x, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(pos, stepsModel);
                        if (spinner_position == 1) {
                            stepsModel.setPercentageCompletion(100);
                            stepsModel.setPresentCondition("");
                            steps.set(x, stepsModel);
                            Singleton.getInstance().indPropertyFloors.set(pos, stepsModel);
                        } else {
                            is_not_null = false;
                            stepsModel.setPercentageCompletion(null);
                            stepsModel.setPresentCondition("");
                            stepsModel.setPropertyAge(null);
                            // Added in new feature
                            stepsModel.setPercentageDepreciation(null);
                            stepsModel.setResidualLife(null);
                            steps.set(x, stepsModel);
                            Singleton.getInstance().indPropertyFloors.set(pos, stepsModel);

                            // ValuationPermissibleAreaAdapter PercentageDepreciation
                            if (FragmentValuationBuilding_ka.listAdapter != null) {
                                FragmentValuationBuilding_ka.listAdapter.steps.set(x, stepsModel);
                                FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(x);
                            }
                            // ValuationActualAreaAdapter PercentageDepreciation
                            if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                                FragmentValuationBuilding_ka.listActualAdapter.steps.set(x, stepsModel);
                                FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(x);
                            }
                        }
                    }
                    if (is_not_null) {
                        int average_total = 100;
                        if (FragmentBuilding_ka.textview_comp_total != null) {
                            FragmentBuilding_ka.textview_comp_total.setText("" + average_total);
                        }
                        // Old condtion
                        /*if ((Singleton.getInstance().is_new_floor_created) && (Singleton.getInstance().as_per_com)) {
                            FragmentValuationBuilding_ka fragmentValuationBuilding = new FragmentValuationBuilding_ka();
                            fragmentValuationBuilding.aspercompletion_val("" + average_total);
                            Singleton.getInstance().as_per_com = false;
                        }*/
                        // Added in new feature
                        FragmentValuationBuilding_ka fragmentValuationBuilding = new FragmentValuationBuilding_ka();
                        fragmentValuationBuilding.aspercompletion_val("" + average_total);
                    } else {
                        int average_total = 0;
                        if (FragmentBuilding_ka.textview_comp_total != null) {
                            FragmentBuilding_ka.textview_comp_total.setText("" + average_total);
                        }
                        // Added in new feature
                        FragmentValuationBuilding_ka fragmentValuationBuilding = new FragmentValuationBuilding_ka();
                        fragmentValuationBuilding.aspercompletion_val("" + average_total);
                    }
                    hideSoftKeyboard(textView);
                    notifyDataSetChanged();


                } else {
                    // Edit Inspection - Single concept
                    if (pos != -1) {
                        // TODO - if condtion for edit floor stage for first time
                        if (Singleton.getInstance().is_edit_floor_satge) {
                            Singleton.getInstance().is_edit_floor_satge = false;
                            // Start Inspection first time only
                            for (int x = 0; x < steps.size(); x++) {
                                final IndPropertyFloor stepsModel = steps.get(x);
                                // TODO - only stage is 0 it will be affect
                                if (stepsModel.getConstructionStageId() == 0) {
                                    stepsModel.setConstructionStageId(Singleton.getInstance().constructions_list.get(spinner_position).getConstructionId());
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(pos, stepsModel);
                                    if (spinner_position == 1) {
                                        stepsModel.setPercentageCompletion(100);
                                        stepsModel.setPresentCondition("");
                                        steps.set(x, stepsModel);
                                        Singleton.getInstance().indPropertyFloors.set(pos, stepsModel);
                                    } else {
                                        stepsModel.setPercentageCompletion(null);
                                        stepsModel.setPresentCondition("");
                                        stepsModel.setPropertyAge(null);
                                        // Added in new feature
                                        stepsModel.setPercentageDepreciation(null);
                                        stepsModel.setResidualLife(null);
                                        steps.set(x, stepsModel);
                                        Singleton.getInstance().indPropertyFloors.set(pos, stepsModel);

                                        // ValuationPermissibleAreaAdapter PercentageDepreciation
                                        if (FragmentValuationBuilding_ka.listAdapter != null) {
                                            FragmentValuationBuilding_ka.listAdapter.steps.set(x, stepsModel);
                                            FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(x);
                                        }
                                        // ValuationActualAreaAdapter PercentageDepreciation
                                        if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                                            FragmentValuationBuilding_ka.listActualAdapter.steps.set(x, stepsModel);
                                            FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(x);
                                        }

                                    }
                                }
                            }
                            int average_total = general.getCompletedSumValue(steps);
                            if (FragmentBuilding_ka.textview_comp_total != null) {
                                FragmentBuilding_ka.textview_comp_total.setText("" + average_total);
                            }
                            // set Aspercom
                            FragmentValuationBuilding_ka fragmentValuationBuilding = new FragmentValuationBuilding_ka();
                            fragmentValuationBuilding.aspercompletion_val("" + average_total);
                            hideSoftKeyboard(textView);
                            notifyDataSetChanged();
                        } else {
                            // TODO - else condtion for normal
                            final IndPropertyFloor stepsModel = steps.get(pos);
                            stepsModel.setConstructionStageId(Singleton.getInstance().constructions_list.get(spinner_position).getConstructionId());
                            steps.set(pos, stepsModel);
                            Singleton.getInstance().indPropertyFloors.set(pos, stepsModel);
                            if (spinner_position == 2) {
                                // editable
                                edittext_general_comp.setEnabled(true);
                                edittext_general_progress.setEnabled(true);
                            } else {
                                // not editable
                                edittext_general_comp.setEnabled(false);
                                edittext_general_progress.setEnabled(false);
                            }

                            if (spinner_position == 1) {
                                edittext_general_comp.setText("" + 100);
                                edittext_general_progress.setText("");
                                stepsModel.setPercentageCompletion(100);
                                stepsModel.setPresentCondition("");
                                steps.set(pos, stepsModel);
                                Singleton.getInstance().indPropertyFloors.set(pos, stepsModel);
                            } else {
                                edittext_general_comp.setText("");
                                edittext_general_progress.setText("");
                                stepsModel.setPercentageCompletion(null);
                                stepsModel.setPresentCondition("");
                                stepsModel.setPropertyAge(null);
                                // Added in new feature
                                stepsModel.setPercentageDepreciation(null);
                                stepsModel.setResidualLife(null);
                                steps.set(pos, stepsModel);
                                Singleton.getInstance().indPropertyFloors.set(pos, stepsModel);
                                edittext_general_age.setText("");
                                edittext_general_life.setText("");
                            }
                            int average_total = general.getCompletedSumValue(steps);
                            if (FragmentBuilding_ka.textview_comp_total != null) {
                                FragmentBuilding_ka.textview_comp_total.setText("" + average_total);
                            }
                            // set Aspercom
                            FragmentValuationBuilding_ka fragmentValuationBuilding = new FragmentValuationBuilding_ka();
                            fragmentValuationBuilding.aspercompletion_val("" + average_total);

                            // ValuationPermissibleAreaAdapter PercentageDepreciation
                            if (FragmentValuationBuilding_ka.listAdapter != null) {
                                FragmentValuationBuilding_ka.listAdapter.steps.set(pos, stepsModel);
                                FragmentValuationBuilding_ka.listAdapter.notifyItemChanged(pos);
                            }
                            // ValuationActualAreaAdapter PercentageDepreciation
                            if (FragmentValuationBuilding_ka.listActualAdapter != null) {
                                FragmentValuationBuilding_ka.listActualAdapter.steps.set(pos, stepsModel);
                                FragmentValuationBuilding_ka.listActualAdapter.notifyItemChanged(pos);
                            }

                        }


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


    private void doc_area_cursor_moment(EditText editText) {
        if (doc_area_empty()) {
            Docarea_cursor = true;
        } else {
            hideSoftKeyboard(editText);
        }
    }

    public boolean doc_area_empty() {
        boolean checking = false;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                String propertyAge = String.valueOf(stepsModel.getPropertyAge());
                if (general.isEmpty(propertyAge)) {
                    checking = true;
                }
            }
        }
        return checking;
    }


}
