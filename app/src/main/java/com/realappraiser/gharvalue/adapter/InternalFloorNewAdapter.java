package com.realappraiser.gharvalue.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.fragments.OtherDetails;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;

/**
 * Created by kaptas on 22/1/18.
 * Internal Floor composition New Adapter for Textbox initiate values
 */

@SuppressWarnings("ALL")
public class InternalFloorNewAdapter extends RecyclerView.Adapter<InternalFloorNewAdapter.ViewHolder> {

    @SuppressLint("StaticFieldLeak")
    private static General general;
    private Context context;
    private Activity mActivity;
    public ArrayList<IndPropertyFloor> steps;

    private static final long DELAY_TIME = 1300; //3 seconds
    private static final long DELAY_TIME_7 = 3500; //3 seconds
    private static final long DELAY_TIME2 = 500; //3 seconds
    private Handler mHandler;
    private Runnable mJumpRunnable;


    boolean Internalhalldinning = false;
    boolean Internalkitchen = false;
    boolean InternalBedroom = false;
    boolean InternalBath = false;
    //boolean InternalShopOffice = false;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_internal_floor_name_composition;
        public EditText internal_hall_dinning, internal_kitchen, internal_bedroom, internal_bath, internal_shop_office;

        public ViewHolder(View itemView) {
            super(itemView);

            textview_internal_floor_name_composition = (TextView) itemView.findViewById(R.id.textview_internal_floor_name_composition);
            internal_hall_dinning = (EditText) itemView.findViewById(R.id.internal_hall_dinning);
            internal_kitchen = (EditText) itemView.findViewById(R.id.internal_kitchen);
            internal_bedroom = (EditText) itemView.findViewById(R.id.internal_bedroom);
            internal_bath = (EditText) itemView.findViewById(R.id.internal_bath);
            internal_shop_office = (EditText) itemView.findViewById(R.id.internal_shop_office);
            textview_internal_floor_name_composition.setTypeface(General.regularTypeface());
            textview_internal_floor_name_composition.setTag(getAdapterPosition());


            internal_hall_dinning.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        String toString = internal_hall_dinning.getText().toString();
                        setInternalhalldinning(toString, getAdapterPosition(), internal_hall_dinning);
                    }
                }
            });

            internal_kitchen.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        String toString = internal_kitchen.getText().toString();
                        setInternalkitchen(toString, getAdapterPosition(), internal_kitchen);
                    }
                }
            });

            internal_bedroom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        String toString = internal_bedroom.getText().toString();
                        setInternalBedroom(toString, getAdapterPosition(), internal_bedroom);
                    }
                }
            });

            internal_bath.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        String toString = internal_bath.getText().toString();
                        setInternalBath(toString, getAdapterPosition(), internal_bath);
                    }
                }
            });

            internal_shop_office.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        String toString = internal_shop_office.getText().toString();
                        setInternalShopOffice(toString, getAdapterPosition(), internal_shop_office);
                    }
                }
            });

            /********* suganya Integration
             * Cursor Visible on Next or Enter button editor listener
             * **********/
            internal_hall_dinning.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        internal_kitchen.setSelection(internal_kitchen.getText().toString().length());
                        /*if(internalHallCheck("hall")) {
                            mJumpRunnable = new Runnable() {
                                public void run() {

                                    internal_kitchen.setTextIsSelectable(true);
                                    internal_kitchen.setSelection(0);
                                    internal_kitchen.requestFocus();
                                    internal_kitchen.requestFocusFromTouch();
                                }
                            };
                            mHandler = new Handler();
                            if (steps.size() > 5) {
                                mHandler.postDelayed(mJumpRunnable, DELAY_TIME_7);
                            } else {
                                mHandler.postDelayed(mJumpRunnable, DELAY_TIME);
                            }
                        }*/
                    }
                    return false;
                }
            });

            internal_kitchen.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        internal_bedroom.setSelection(internal_bedroom.getText().toString().length());
                    }
                    return false;
                }
            });

            internal_bedroom.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        internal_bath.setSelection(internal_bath.getText().toString().length());
                    }
                    return false;
                }
            });

            internal_bath.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        internal_shop_office.setSelection(internal_shop_office.getText().toString().length());
                    }
                    return false;
                }
            });

            internal_shop_office.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                        Log.i("Next key", "Enter pressed");
                        internal_hall_dinning.setSelection(internal_hall_dinning.getText().toString().length());
                    }
                    return false;
                }
            });


        }
    }

    public InternalFloorNewAdapter(ArrayList<IndPropertyFloor> steps, Context context) {
        this.steps = steps;
        this.context = context;
        this.mActivity = (Activity) context;
        general = new General((Activity) context);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    @Override
    public InternalFloorNewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.internalfloors_adapternew, viewGroup, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(InternalFloorNewAdapter.ViewHolder holder, final int position) {


        int x = holder.getLayoutPosition();
        int id = 0;
        if (Singleton.getInstance().indPropertyFloors.size() > 0) {

            String valname = steps.get(position).getFloorName();
            id = steps.get(position).getFlatHallNo();

            holder.textview_internal_floor_name_composition.setText(steps.get(position).getFloorName());

            if (position < Singleton.getInstance().indPropertyFloors.size()) {

                if (id != 0) {
                    holder.internal_hall_dinning.setText("" + id);
                } else {
                    holder.internal_hall_dinning.setText("");
                }

                if (steps.get(position).getFlatKitchenNo() != 0) {
                    holder.internal_kitchen.setText("" + steps.get(position).getFlatKitchenNo());
                } else {
                    holder.internal_kitchen.setText("");
                }

                if (steps.get(position).getFlatBathNo() != 0) {
                    holder.internal_bath.setText("" + steps.get(position).getFlatBathNo());
                } else {
                    holder.internal_bath.setText("");
                }

                if (steps.get(position).getFlatBedroomNo() != 0) {
                    holder.internal_bedroom.setText("" + steps.get(position).getFlatBedroomNo());
                } else {
                    holder.internal_bedroom.setText("");
                }

                if (steps.get(position).getOfficeNo() != 0) {
                    holder.internal_shop_office.setText("" + steps.get(position).getOfficeNo());
                } else {
                    holder.internal_shop_office.setText("");
                }


                // Cursor
                if (Internalhalldinning) {
                    if (steps.get(position).getFlatKitchenNo() == 0) {
                        Internalhalldinning = false;
                        holder.internal_kitchen.requestFocus();
                    }
                }

                if (Internalkitchen) {
                    if (steps.get(position).getFlatBedroomNo() == 0) {
                        Internalkitchen = false;
                        holder.internal_bedroom.requestFocus();
                    }
                }

                if (InternalBedroom) {
                    if (steps.get(position).getFlatBathNo() == 0) {
                        InternalBedroom = false;
                        holder.internal_bath.requestFocus();
                    }
                }

                if (InternalBath) {
                    if (steps.get(position).getOfficeNo() == 0) {
                        InternalBath = false;
                        holder.internal_shop_office.requestFocus();
                    }
                }
                // Cursor


            }

        } else {

            String floorName = steps.get(position).getFloorName();
            //holder.textview_internal_floor_name_composition.setTag(position);
            holder.textview_internal_floor_name_composition.setText(floorName);


            id = steps.get(position).getFlatHallNo();
            if (id != 0) {
                holder.internal_hall_dinning.setText("" + id);
            } else {
                holder.internal_hall_dinning.setText("");
            }

            if (steps.get(position).getFlatKitchenNo() != 0) {
                holder.internal_kitchen.setText("" + steps.get(position).getFlatKitchenNo());
            } else {
                holder.internal_kitchen.setText("");
            }

            if (steps.get(position).getFlatBathNo() != 0) {
                holder.internal_bath.setText("" + steps.get(position).getFlatBathNo());
            } else {
                holder.internal_bath.setText("");
            }

            if (steps.get(position).getFlatBedroomNo() != 0) {
                holder.internal_bedroom.setText("" + steps.get(position).getFlatBedroomNo());
            } else {
                holder.internal_bedroom.setText("");
            }

            if (steps.get(position).getOfficeNo() != 0) {
                holder.internal_shop_office.setText("" + steps.get(position).getOfficeNo());
            } else {
                holder.internal_shop_office.setText("");
            }

        }
    }


    public ArrayList<IndPropertyFloor> getStepList() {
        return steps;
    }

    private void hideSoftKeyboard(View addkeys) {
        if (addkeys != null) {
            InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
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


    private void setInternalhalldinning(String charsequence, final int adapterposition, EditText editText) {
        if (adapterposition != -1) {
            if (!general.isEmpty(charsequence)) {
                if (internalHallCheck()) {
                    for (int i = adapterposition; i < steps.size(); i++) {
                        final IndPropertyFloor stepsModel = steps.get(i);
                        stepsModel.setFlatHallNo(Integer.valueOf(charsequence));
                        steps.set(i, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(i, stepsModel);
                    }
                    Singleton.getInstance().is_edit_floor_hall = false;
                    //hideSoftKeyboard(editText);
                    //Internalhalldinning = true;
                    halldinning_cursor_moment(editText);
                    notifyDataSetChanged();
                } else {
                    if ((Singleton.getInstance().is_edit_floor_hall) && (!general.isEmpty(charsequence))) {
                        // Edit First Time and not null too
                        Singleton.getInstance().is_edit_floor_hall = false;
                        if (steps.size() > 0) {
                            // For copying rows for the selected one
                            final IndPropertyFloor stepsModel_new = steps.get(adapterposition);
                            stepsModel_new.setFlatHallNo(Integer.valueOf(charsequence));
                            steps.set(adapterposition, stepsModel_new);
                            Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel_new);
                            // Check all rows
                            for (int x = adapterposition; x < steps.size(); x++) {
                                IndPropertyFloor stepsModel = steps.get(x);
                                if (stepsModel.getFlatHallNo() == 0) {
                                    stepsModel.setFlatHallNo(Integer.valueOf(charsequence));
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(x, stepsModel);
                                }
                            }
                            //hideSoftKeyboard(editText);
                            //Internalhalldinning = true;
                            halldinning_cursor_moment(editText);
                            notifyDataSetChanged();
                        }
                    } else {
                        final IndPropertyFloor stepsModel = steps.get(adapterposition);
                        stepsModel.setFlatHallNo(Integer.valueOf(charsequence));
                        steps.set(adapterposition, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                    }
                }
            } else {
                final IndPropertyFloor stepsModel = steps.get(adapterposition);
                stepsModel.setFlatHallNo(0);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
            }
        }
    }

    private void setInternalkitchen(String charsequence, final int adapterposition, EditText editText) {
        if (adapterposition != -1) {
            if (!general.isEmpty(charsequence)) {
                if (internalKitchenCheck()) {
                    for (int i = adapterposition; i < steps.size(); i++) {
                        final IndPropertyFloor stepsModel = steps.get(i);
                        stepsModel.setFlatKitchenNo(Integer.valueOf(charsequence));
                        steps.set(i, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(i, stepsModel);
                    }
                    Singleton.getInstance().is_edit_floor_kitchen = false;
                    //hideSoftKeyboard(editText);
                    //Internalkitchen = true;
                    internalkitchen_cursor_moment(editText);
                    notifyDataSetChanged();
                } else {
                    if ((Singleton.getInstance().is_edit_floor_kitchen) && (!general.isEmpty(charsequence))) {
                        // Edit First Time and not null too
                        Singleton.getInstance().is_edit_floor_kitchen = false;
                        if (steps.size() > 0) {
                            // For copying rows for the selected one
                            final IndPropertyFloor stepsModel_new = steps.get(adapterposition);
                            stepsModel_new.setFlatKitchenNo(Integer.valueOf(charsequence));
                            steps.set(adapterposition, stepsModel_new);
                            Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel_new);
                            // Check all rows
                            for (int x = adapterposition; x < steps.size(); x++) {
                                IndPropertyFloor stepsModel = steps.get(x);
                                if (stepsModel.getFlatKitchenNo() == 0) {
                                    stepsModel.setFlatKitchenNo(Integer.valueOf(charsequence));
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(x, stepsModel);
                                }
                            }
                            //hideSoftKeyboard(editText);
                            //Internalkitchen = true;
                            internalkitchen_cursor_moment(editText);
                            notifyDataSetChanged();
                        }
                    } else {
                        final IndPropertyFloor stepsModel = steps.get(adapterposition);
                        stepsModel.setFlatKitchenNo(Integer.valueOf(charsequence));
                        steps.set(adapterposition, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                    }
                }
            } else {
                final IndPropertyFloor stepsModel = steps.get(adapterposition);
                stepsModel.setFlatKitchenNo(0);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
            }
        }
    }

    private void setInternalBedroom(String charsequence, final int adapterposition, EditText editText) {
        if (adapterposition != -1) {
            if (!general.isEmpty(charsequence)) {
                if (internalBedroomCheck()) {
                    for (int i = adapterposition; i < steps.size(); i++) {
                        final IndPropertyFloor stepsModel = steps.get(i);
                        stepsModel.setFlatBedroomNo(Integer.valueOf(charsequence));
                        steps.set(i, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(i, stepsModel);
                    }
                    Singleton.getInstance().is_edit_floor_bedroom = false;
                    //hideSoftKeyboard(editText);
                    //InternalBedroom = true;
                    internalbedroom_cursor_moment(editText);
                    notifyDataSetChanged();
                } else {
                    if ((Singleton.getInstance().is_edit_floor_bedroom) && (!general.isEmpty(charsequence))) {
                        // Edit First Time and not null too
                        Singleton.getInstance().is_edit_floor_bedroom = false;
                        if (steps.size() > 0) {
                            // For copying rows for the selected one
                            final IndPropertyFloor stepsModel_new = steps.get(adapterposition);
                            stepsModel_new.setFlatBedroomNo(Integer.valueOf(charsequence));
                            steps.set(adapterposition, stepsModel_new);
                            Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel_new);
                            // Check all rows
                            for (int x = adapterposition; x < steps.size(); x++) {
                                IndPropertyFloor stepsModel = steps.get(x);
                                if (stepsModel.getFlatBedroomNo() == 0) {
                                    stepsModel.setFlatBedroomNo(Integer.valueOf(charsequence));
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(x, stepsModel);
                                }
                            }
                            //hideSoftKeyboard(editText);
                            //InternalBedroom = true;
                            internalbedroom_cursor_moment(editText);
                            notifyDataSetChanged();
                        }
                    } else {
                        final IndPropertyFloor stepsModel = steps.get(adapterposition);
                        stepsModel.setFlatBedroomNo(Integer.valueOf(charsequence));
                        steps.set(adapterposition, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                    }
                }
            } else {
                final IndPropertyFloor stepsModel = steps.get(adapterposition);
                stepsModel.setFlatBedroomNo(0);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
            }
        }
    }

    private void setInternalBath(String charsequence, final int adapterposition, EditText editText) {
        if (adapterposition != -1) {
            if (!general.isEmpty(charsequence)) {
                if (internalBathCheck()) {
                    for (int i = adapterposition; i < steps.size(); i++) {
                        final IndPropertyFloor stepsModel = steps.get(i);
                        stepsModel.setFlatBathNo(Integer.valueOf(charsequence));
                        steps.set(i, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(i, stepsModel);
                    }
                    Singleton.getInstance().is_edit_floor_bath = false;
                    //hideSoftKeyboard(editText);
                    //InternalBath = true;
                    internalBath_cursor_moment(editText);
                    notifyDataSetChanged();
                } else {
                    if ((Singleton.getInstance().is_edit_floor_bath) && (!general.isEmpty(charsequence))) {
                        // Edit First Time and not null too
                        Singleton.getInstance().is_edit_floor_bath = false;
                        if (steps.size() > 0) {
                            // For copying rows for the selected one
                            final IndPropertyFloor stepsModel_new = steps.get(adapterposition);
                            stepsModel_new.setFlatBathNo(Integer.valueOf(charsequence));
                            steps.set(adapterposition, stepsModel_new);
                            Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel_new);
                            // Check all rows
                            for (int x = adapterposition; x < steps.size(); x++) {
                                IndPropertyFloor stepsModel = steps.get(x);
                                if (stepsModel.getFlatBathNo() == 0) {
                                    stepsModel.setFlatBathNo(Integer.valueOf(charsequence));
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(x, stepsModel);
                                }
                            }
                            //hideSoftKeyboard(editText);
                            //InternalBath = true;
                            internalBath_cursor_moment(editText);
                            notifyDataSetChanged();
                        }
                    } else {
                        final IndPropertyFloor stepsModel = steps.get(adapterposition);
                        stepsModel.setFlatBathNo(Integer.valueOf(charsequence));
                        steps.set(adapterposition, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                    }
                }
            } else {
                final IndPropertyFloor stepsModel = steps.get(adapterposition);
                stepsModel.setFlatBathNo(0);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
            }
        }
    }

    private void setInternalShopOffice(String charsequence, final int adapterposition, EditText editText) {
        if (adapterposition != -1) {
            if (!general.isEmpty(charsequence)) {
                if (internalOfficeCheck()) {
                    for (int i = adapterposition; i < steps.size(); i++) {
                        final IndPropertyFloor stepsModel = steps.get(i);
                        stepsModel.setOfficeNo(Integer.valueOf(charsequence));
                        steps.set(i, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(i, stepsModel);
                    }
                    Singleton.getInstance().is_edit_floor_shop = false;
                    hideSoftKeyboard(editText);
                    //InternalShopOffice = true;
                    notifyDataSetChanged();
                } else {
                    if ((Singleton.getInstance().is_edit_floor_shop) && (!general.isEmpty(charsequence))) {
                        // Edit First Time and not null too
                        Singleton.getInstance().is_edit_floor_shop = false;
                        if (steps.size() > 0) {
                            // For copying rows for the selected one
                            final IndPropertyFloor stepsModel_new = steps.get(adapterposition);
                            stepsModel_new.setOfficeNo(Integer.valueOf(charsequence));
                            steps.set(adapterposition, stepsModel_new);
                            Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel_new);
                            // Check all rows
                            for (int x = adapterposition; x < steps.size(); x++) {
                                IndPropertyFloor stepsModel = steps.get(x);
                                if (stepsModel.getOfficeNo() == 0) {
                                    stepsModel.setOfficeNo(Integer.valueOf(charsequence));
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(x, stepsModel);
                                }
                            }
                            hideSoftKeyboard(editText);
                            //InternalShopOffice = true;
                            notifyDataSetChanged();
                        }
                    } else {
                        final IndPropertyFloor stepsModel = steps.get(adapterposition);
                        stepsModel.setOfficeNo(Integer.valueOf(charsequence));
                        steps.set(adapterposition, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                    }
                }
            } else {
                final IndPropertyFloor stepsModel = steps.get(adapterposition);
                stepsModel.setOfficeNo(0);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
            }
        }
    }


    private boolean internalHallCheck() {
        boolean construction_empty = false;
        int empty_loop = 0;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatHallNo() == 0) {
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

    private boolean internalKitchenCheck() {
        boolean construction_empty = false;
        int empty_loop = 0;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatKitchenNo() == 0) {
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

    private boolean internalBedroomCheck() {
        boolean construction_empty = false;
        int empty_loop = 0;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatBedroomNo() == 0) {
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

    private boolean internalBathCheck() {
        boolean construction_empty = false;
        int empty_loop = 0;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatBathNo() == 0) {
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

    private boolean internalOfficeCheck() {
        boolean construction_empty = false;
        int empty_loop = 0;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getOfficeNo() == 0) {
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


    public boolean internalHallCheck_old(String str) {
        boolean is_not_null = true;
        ArrayList<Boolean> nullcheckHappened = new ArrayList<>();
        if (!general.isEmpty(str)) {
            if (steps.size() > 0) {
                is_not_null = true;
                for (int x = 0; x < steps.size(); x++) {
                    IndPropertyFloor stepsModel = steps.get(x);
                    if (!general.isEmpty(String.valueOf(stepsModel.getFlatHallNo()))) {
                        // All Text are Not Null
                        if (stepsModel.getFlatHallNo() != 0) {
                            is_not_null = false;
                        } else { // text are NUll
                            is_not_null = true;
                            nullcheckHappened.add(is_not_null);
                        }
                    }
                }
            }
        } else {
            is_not_null = false;
        }

        if (nullcheckHappened.size() != 0) {
            if (nullcheckHappened.size() < steps.size()) {
                is_not_null = true;
            }
        } else {
            is_not_null = false;
        }

        return is_not_null;
    }

    public boolean internalKitchenCheck_old(String str) {
        boolean is_not_null = true;
        ArrayList<Boolean> nullcheckHappened = new ArrayList<>();
        if (!general.isEmpty(str)) {
            if (steps.size() > 0) {
                is_not_null = true;
                for (int x = 0; x < steps.size(); x++) {
                    IndPropertyFloor stepsModel = steps.get(x);
                    if (!general.isEmpty(String.valueOf(stepsModel.getFlatKitchenNo()))) {
                        // All Text are Not Null
                        if (stepsModel.getFlatKitchenNo() != 0) {
                            is_not_null = false;
                        } else { // text are NUll
                            is_not_null = true;
                            nullcheckHappened.add(is_not_null);
                        }
                    }
                }
            }
        } else {
            is_not_null = false;
        }

        if (nullcheckHappened.size() != 0) {
            if (nullcheckHappened.size() < steps.size()) {
                is_not_null = true;
            }
        } else {
            is_not_null = false;
        }

        return is_not_null;
    }

    public boolean internalBedroomCheck_old(String str) {
        boolean is_not_null = true;
        ArrayList<Boolean> nullcheckHappened = new ArrayList<>();
        if (!general.isEmpty(str)) {
            if (steps.size() > 0) {
                is_not_null = true;
                for (int x = 0; x < steps.size(); x++) {
                    IndPropertyFloor stepsModel = steps.get(x);
                    if (!general.isEmpty(String.valueOf(stepsModel.getFlatBedroomNo()))) {
                        // All Text are Not Null
                        if (stepsModel.getFlatBedroomNo() != 0) {
                            is_not_null = false;
                        } else { // text are NUll
                            is_not_null = true;
                            nullcheckHappened.add(is_not_null);
                        }
                    }
                }
            }
        } else {
            is_not_null = false;
        }

        if (nullcheckHappened.size() != 0) {
            if (nullcheckHappened.size() < steps.size()) {
                is_not_null = true;
            }
        } else {
            is_not_null = false;
        }

        return is_not_null;
    }

    public boolean internalBathCheck_old(String str) {
        boolean is_not_null = true;
        ArrayList<Boolean> nullcheckHappened = new ArrayList<>();
        if (!general.isEmpty(str)) {
            if (steps.size() > 0) {
                is_not_null = true;
                for (int x = 0; x < steps.size(); x++) {
                    IndPropertyFloor stepsModel = steps.get(x);
                    if (!general.isEmpty(String.valueOf(stepsModel.getFlatBathNo()))) {
                        // All Text are Not Null
                        if (stepsModel.getFlatBathNo() != 0) {
                            is_not_null = false;
                        } else { // text are NUll
                            is_not_null = true;
                            nullcheckHappened.add(is_not_null);
                        }
                    }
                }
            }
        } else {
            is_not_null = false;
        }

        if (nullcheckHappened.size() != 0) {
            if (nullcheckHappened.size() < steps.size()) {
                is_not_null = true;
            }
        } else {
            is_not_null = false;
        }

        return is_not_null;
    }

    public boolean internalOfficeCheck_old(String str) {
        boolean is_not_null = true;
        ArrayList<Boolean> nullcheckHappened = new ArrayList<>();
        if (!general.isEmpty(str)) {
            if (steps.size() > 0) {
                is_not_null = true;
                for (int x = 0; x < steps.size(); x++) {
                    IndPropertyFloor stepsModel = steps.get(x);
                    if (!general.isEmpty(String.valueOf(stepsModel.getOfficeNo()))) {
                        // All Text are Not Null
                        if (stepsModel.getOfficeNo() != 0) {
                            is_not_null = false;
                        } else { // text are NUll
                            is_not_null = true;
                            nullcheckHappened.add(is_not_null);
                        }
                    }
                }
            }
        } else {
            is_not_null = false;
        }

       /* if (nullcheckHappened.size() == steps.size()) {
            is_not_null = true;
        }*/

        if (nullcheckHappened.size() != 0) {
            if (nullcheckHappened.size() < steps.size()) {
                is_not_null = true;
            }
        } else {
            is_not_null = false;
        }

        return is_not_null;
    }


    // Cursor Moment -
    private void halldinning_cursor_moment(EditText editText) {
        if (FlatKitchenNo_empty()) {
            Internalhalldinning = true;
        } else if (FlatBedroomNo_empty()) {
            Internalkitchen = true;
        } else if (FlatBathNo_empty()) {
            InternalBedroom = true;
        } else if (OfficeNo_empty()) {
            InternalBath = true;
        } else {
            hideSoftKeyboard(editText);
        }
    }

    private void internalkitchen_cursor_moment(EditText editText) {
        if (FlatBedroomNo_empty()) {
            Internalkitchen = true;
        } else if (FlatBathNo_empty()) {
            InternalBedroom = true;
        } else if (OfficeNo_empty()) {
            InternalBath = true;
        } else {
            hideSoftKeyboard(editText);
        }
    }

    private void internalbedroom_cursor_moment(EditText editText) {
        if (FlatBathNo_empty()) {
            InternalBedroom = true;
        } else if (OfficeNo_empty()) {
            InternalBath = true;
        } else {
            hideSoftKeyboard(editText);
        }
    }

    private void internalBath_cursor_moment(EditText editText) {
        if (OfficeNo_empty()) {
            InternalBath = true;
        } else {
            hideSoftKeyboard(editText);
        }
    }


    public boolean FlatKitchenNo_empty() {
        boolean kitchen_checking = false;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatKitchenNo() == 0) {
                    kitchen_checking = true;
                }
            }
        }
        return kitchen_checking;
    }

    public boolean FlatBedroomNo_empty() {
        boolean bedroom_checking = false;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatBedroomNo() == 0) {
                    bedroom_checking = true;
                }
            }
        }
        return bedroom_checking;
    }

    public boolean FlatBathNo_empty() {
        boolean bath_checking = false;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatBathNo() == 0) {
                    bath_checking = true;
                }
            }
        }
        return bath_checking;
    }

    public boolean OfficeNo_empty() {
        boolean office_checking = false;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getOfficeNo() == 0) {
                    office_checking = true;
                }
            }
        }
        return office_checking;
    }


}
