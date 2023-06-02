package com.realappraiser.gharvalue.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import androidx.recyclerview.widget.RecyclerView;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.fragments.OtherDetails_ka;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;

/**
 * Created by kaptas on 22/1/18.
 * Internal Floor composition New Adapter for Textbox initiate values
 */

@SuppressWarnings("ALL")
public class InternalFloorNewAdapter_ka extends RecyclerView.Adapter<InternalFloorNewAdapter_ka.ViewHolder> {

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
    Dialog dialog;
    public boolean KEY_INTERNAL_COMPOSTION_NOT_COPY;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_internal_floor_name_composition;
        public EditText internal_hall_dinning, internal_kitchen, internal_bedroom, internal_bath, internal_shop_office;
        public LinearLayout image_add;

        public ViewHolder(View itemView) {
            super(itemView);

            image_add = (LinearLayout) itemView.findViewById(R.id.image_add);
            textview_internal_floor_name_composition = (TextView) itemView.findViewById(R.id.textview_internal_floor_name_composition);
            internal_hall_dinning = (EditText) itemView.findViewById(R.id.internal_hall_dinning);
            internal_kitchen = (EditText) itemView.findViewById(R.id.internal_kitchen);
            internal_bedroom = (EditText) itemView.findViewById(R.id.internal_bedroom);
            internal_bath = (EditText) itemView.findViewById(R.id.internal_bath);
            internal_shop_office = (EditText) itemView.findViewById(R.id.internal_shop_office);
            textview_internal_floor_name_composition.setTypeface(General.regularTypeface());
            textview_internal_floor_name_composition.setTag(getAdapterPosition());

            image_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /* internal_hall_dinning.clearFocus();
                    internal_kitchen.clearFocus();
                    internal_bedroom.clearFocus();
                    internal_bath.clearFocus();
                    internal_shop_office.clearFocus();*/
                    // show_emptyFocus();

                    int position = getAdapterPosition();
                    floors_popup(position);
                }
            });

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

    public InternalFloorNewAdapter_ka(ArrayList<IndPropertyFloor> steps, Context context) {
        this.steps = steps;
        this.context = context;
        this.mActivity = (Activity) context;
        general = new General((Activity) context);
        // check for KEY_INTERNAL_COMPOSTION_NOT_COPY  or not
        KEY_INTERNAL_COMPOSTION_NOT_COPY = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_INTERNAL_COMPOSTION_NOT_COPY, false);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.internalfloors_adapternew_ka, viewGroup, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


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
        if (OtherDetails_ka.my_focuslayout != null) {
            OtherDetails_ka.my_focuslayout.requestFocus();
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

        if (KEY_INTERNAL_COMPOSTION_NOT_COPY) {
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

        if (KEY_INTERNAL_COMPOSTION_NOT_COPY) {
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

        if (KEY_INTERNAL_COMPOSTION_NOT_COPY) {
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

        if (KEY_INTERNAL_COMPOSTION_NOT_COPY) {
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

        if (KEY_INTERNAL_COMPOSTION_NOT_COPY) {
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

    /**********
     * Todo show the floors popup for each floor internal composition as per the position
     * ***********/
    private void floors_popup(final int position) {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.floors_popup);

        final EditText internal_dinning = (EditText) dialog.findViewById(R.id.internal_dinning);
        final EditText internal_wc = (EditText) dialog.findViewById(R.id.internal_wc);
        final EditText internal_attachbath = (EditText) dialog.findViewById(R.id.internal_attachbath);
        final EditText internal_balcony = (EditText) dialog.findViewById(R.id.internal_balcony);
        final EditText internal_fb = (EditText) dialog.findViewById(R.id.internal_fb);
        final EditText internal_db = (EditText) dialog.findViewById(R.id.internal_db);
        final EditText internal_terrace = (EditText) dialog.findViewById(R.id.internal_terrace);
        final EditText internal_passage = (EditText) dialog.findViewById(R.id.internal_passage);

        TextView popuptitle = (TextView) dialog.findViewById(R.id.title);
        Button noBtn = (Button) dialog.findViewById(R.id.noBtn);
        Button yesBtn = (Button) dialog.findViewById(R.id.yesBtn);
        popuptitle.setTypeface(general.mediumtypeface());

        popuptitle.setText(steps.get(position).getFloorName());
        setPopupData(steps, position, internal_dinning, internal_wc, internal_attachbath, internal_balcony,
                internal_fb, internal_db, internal_terrace, internal_passage);

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internal_dinning.clearFocus();
                internal_wc.clearFocus();
                internal_attachbath.clearFocus();
                internal_balcony.clearFocus();
                internal_fb.clearFocus();
                internal_db.clearFocus();
                internal_terrace.clearFocus();
                internal_passage.clearFocus();
                SaveInternalData(steps, position, internal_dinning, internal_wc, internal_attachbath, internal_balcony,
                        internal_fb, internal_db, internal_terrace, internal_passage);
                dialog.dismiss();
            }
        });
        dialog.show();

        // internal_dinning.setSelection(internal_dinning.getText().toString().length());

        /********* Let Cursor will display after the entered number
         * Cursor Visible on Next or Enter button editor listener
         * **********/
        internal_dinning.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    internal_wc.setSelection(internal_wc.getText().toString().length());
                }
                return false;
            }
        });

        internal_wc.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    internal_attachbath.setSelection(internal_attachbath.getText().toString().length());
                }
                return false;
            }
        });

        internal_attachbath.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    internal_balcony.setSelection(internal_balcony.getText().toString().length());
                }
                return false;
            }
        });

        internal_balcony.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    internal_fb.setSelection(internal_fb.getText().toString().length());
                }
                return false;
            }
        });

        internal_fb.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    internal_db.setSelection(internal_db.getText().toString().length());
                }
                return false;
            }
        });

        internal_db.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    internal_terrace.setSelection(internal_terrace.getText().toString().length());
                }
                return false;
            }
        });

        internal_terrace.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    internal_passage.setSelection(internal_passage.getText().toString().length());
                }
                return false;
            }
        });
        internal_passage.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    internal_dinning.setSelection(internal_dinning.getText().toString().length());
                }
                return false;
            }
        });
    }

    /**********
     * Todo Save the Internal Composition popup data in Button onclick (save Btn)
     * ***********/
    private void SaveInternalData(ArrayList<IndPropertyFloor> steps, int position, EditText internal_dinning, EditText internal_wc, EditText internal_attachbath,
                                  EditText internal_balcony, EditText internal_fb, EditText internal_db, EditText internal_terrace, EditText internal_passage) {

        IndPropertyFloor stepsModel = steps.get(position);
        /*******Set the Values*********/
        String dinningno = internal_dinning.getText().toString();
        String wcno = internal_wc.getText().toString();
        String attachbathno = internal_attachbath.getText().toString();
        String balconyno = internal_balcony.getText().toString();
        String fbno = internal_fb.getText().toString();
        String dbno = internal_db.getText().toString();
        String terraceno = internal_terrace.getText().toString();
        String passageno = internal_passage.getText().toString();

        /*****Dinning****/
        if (!general.isEmpty(dinningno)) {
            int dinning = Integer.parseInt(dinningno);
            if (dinning == 0) {
                String toString = internal_dinning.getText().toString();
                setInternalDinning(toString, position, internal_dinning);
            }
            stepsModel.setFlatDinningNo(dinning);
        } else {
            stepsModel.setFlatDinningNo(0);
        }

        /*****Flatwc****/
        if (!general.isEmpty(wcno)) {
            int wc = Integer.parseInt(wcno);
            if (wc == 0) {
                String toString = internal_wc.getText().toString();
                setInternalWC(toString, position, internal_wc);
            }
            stepsModel.setFlatWcNo(wc);
        } else {
            stepsModel.setFlatWcNo(0);
        }

        /*****Attachbath****/
        if (!general.isEmpty(attachbathno)) {
            int attachbath = Integer.parseInt(attachbathno);
            if (attachbath == 0) {
                String toString = internal_attachbath.getText().toString();
                setInternalAttachBath(toString, position, internal_attachbath);
            }
            stepsModel.setFlatAttBathWcNo(attachbath);
        } else {
            stepsModel.setFlatAttBathWcNo(0);
        }

        /*****Balcony****/
        if (!general.isEmpty(balconyno)) {
            int balcony = Integer.parseInt(balconyno);
            if (balcony == 0) {
                String toString = internal_balcony.getText().toString();
                setInternalBalcony(toString, position, internal_balcony);
            }
            stepsModel.setFlatBalconyNo(balcony);
        } else {
            stepsModel.setFlatBalconyNo(0);
        }

        /*****Flat fb****/
        if (!general.isEmpty(fbno)) {
            int fb = Integer.parseInt(fbno);
            if (fb == 0) {
                String toString = internal_fb.getText().toString();
                setInternalFB(toString, position, internal_fb);
            }
            stepsModel.setFlatFbNo(fb);
        } else {
            stepsModel.setFlatFbNo(0);
        }

        /*****Flat Db****/
        if (!general.isEmpty(dbno)) {
            int db = Integer.parseInt(dbno);
            if (db == 0) {
                String toString = internal_db.getText().toString();
                setInternalDB(toString, position, internal_db);
            }
            stepsModel.setFlatDbNo(db);
        } else {
            stepsModel.setFlatDbNo(0);
        }

        /*****Terrace****/
        if (!general.isEmpty(terraceno)) {
            int terrace = Integer.parseInt(terraceno);
            if (terrace == 0) {
                String toString = internal_terrace.getText().toString();
                setInternalTerrace(toString, position, internal_terrace);
            }
            stepsModel.setFlatTerraceNo(terrace);
        } else {
            stepsModel.setFlatTerraceNo(0);
        }

        /*****Passage****/
        if (!general.isEmpty(passageno)) {
            int passage = Integer.parseInt(passageno);
            if (passage == 0) {
                String toString = internal_passage.getText().toString();
                setInternalPassage(toString, position, internal_passage);
            }
            stepsModel.setFlatPassageNo(passage);
        } else {
            stepsModel.setFlatPassageNo(0);
        }
    }

    /********* Todo suganya Integrated the internal composition popup floors
     * & Todo the Cursor Visible on Next button focus listener
     * **********/
    public void setPopupData(ArrayList<IndPropertyFloor> steps, int position, final EditText internal_dinning, final EditText internal_wc, final EditText internal_attachbath,
                             final EditText internal_balcony, final EditText internal_fb, final EditText internal_db, final EditText internal_terrace,
                             final EditText internal_passage) {

        IndPropertyFloor stepsModel = steps.get(position);
        /*******Set the Values*********/
        if (stepsModel.getFlatDinningNo() > 0) {
            internal_dinning.setText(stepsModel.getFlatDinningNo() + "");
        }
        if (stepsModel.getFlatWcNo() > 0)
            internal_wc.setText(stepsModel.getFlatWcNo() + "");
        if (stepsModel.getFlatAttBathWcNo() > 0)
            internal_attachbath.setText(stepsModel.getFlatAttBathWcNo() + "");
        if (stepsModel.getFlatBalconyNo() > 0)
            internal_balcony.setText(stepsModel.getFlatBalconyNo() + "");
        if (stepsModel.getFlatFbNo() > 0)
            internal_fb.setText(stepsModel.getFlatFbNo() + "");
        if (stepsModel.getFlatDbNo() > 0)
            internal_db.setText(stepsModel.getFlatDbNo() + "");
        if (stepsModel.getFlatTerraceNo() > 0)
            internal_terrace.setText(stepsModel.getFlatTerraceNo() + "");
        if (stepsModel.getFlatPassageNo() > 0)
            internal_passage.setText(stepsModel.getFlatPassageNo() + "");

        InternalDinningFocusChange(position, internal_dinning);
        InternalWCFocusChange(position, internal_wc);
        InternalAttachBathFocusChange(position, internal_attachbath);
        InternalBalconyFocusChange(position, internal_balcony);
        InternalFBFocusChange(position, internal_fb);
        InternalDBFocusChange(position, internal_db);
        InternalTerraceFocusChange(position, internal_terrace);
        InternalPassageFocusChange(position, internal_passage);

        int dinning = stepsModel.getFlatDinningNo();
        int wc = stepsModel.getFlatWcNo();
        int attachbath = stepsModel.getFlatAttBathWcNo();
        int balcony = stepsModel.getFlatBalconyNo();
        int fb = stepsModel.getFlatFbNo();
        int db = stepsModel.getFlatDbNo();
        int terrace = stepsModel.getFlatTerraceNo();
        int passage = stepsModel.getFlatPassageNo();
        if (dinning == 0) {
            internal_dinning.post(new Runnable() {
                @Override
                public void run() {
                    internal_dinning.setSelection(internal_dinning.getText().toString().length());
                    internal_dinning.requestFocus();
                }
            });
        } else if (wc == 0) {
            internal_wc.post(new Runnable() {
                @Override
                public void run() {
                    internal_wc.setSelection(internal_wc.getText().toString().length());
                    internal_wc.requestFocus();
                }
            });
        } else if (attachbath == 0) {
            internal_attachbath.post(new Runnable() {
                @Override
                public void run() {
                    internal_attachbath.setSelection(internal_attachbath.getText().toString().length());
                    internal_attachbath.requestFocus();
                }
            });
        } else if (balcony == 0) {
            internal_balcony.post(new Runnable() {
                @Override
                public void run() {
                    internal_balcony.setSelection(internal_balcony.getText().toString().length());
                    internal_balcony.requestFocus();
                }
            });
        } else if (fb == 0) {
            internal_fb.post(new Runnable() {
                @Override
                public void run() {
                    internal_fb.setSelection(internal_fb.getText().toString().length());
                    internal_fb.requestFocus();
                }
            });
        } else if (db == 0) {
            internal_db.post(new Runnable() {
                @Override
                public void run() {
                    internal_db.setSelection(internal_db.getText().toString().length());
                    internal_db.requestFocus();
                }
            });
        } else if (terrace == 0) {
            internal_terrace.post(new Runnable() {
                @Override
                public void run() {
                    internal_terrace.setSelection(internal_terrace.getText().toString().length());
                    internal_terrace.requestFocus();
                }
            });
        } else if (passage == 0) {
            internal_passage.post(new Runnable() {
                @Override
                public void run() {
                    internal_passage.setSelection(internal_passage.getText().toString().length());
                    internal_passage.requestFocus();
                }
            });
        }


    }

    /**********
     * Todo #1 Internal dinning focus listener
     * *******/
    private void InternalDinningFocusChange(final int position, final EditText internal_dinning) {
        internal_dinning.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                IndPropertyFloor stepsModel = steps.get(position);
                int dinning = stepsModel.getFlatDinningNo();
                if (dinning == 0) {
                    if (!hasFocus) {
                        String toString = internal_dinning.getText().toString();
                        setInternalDinning(toString, position, internal_dinning);
                    }
                } else {
                    if (!hasFocus) {
                        String toString = internal_dinning.getText().toString();
                        if (!general.isEmpty(toString)) {
                            stepsModel.setFlatDinningNo(Integer.valueOf(toString));
                        }
                    }
                }
            }
        });
    }

    /**********
     * Todo Inernal dinning to set the values in stepmodel
     * *******/
    private void setInternalDinning(String charsequence, final int adapterposition, EditText editText) {
        if (adapterposition != -1) {
            if (!general.isEmpty(charsequence)) {
                if (internalDinning()) {
                    for (int i = adapterposition; i < steps.size(); i++) {
                        final IndPropertyFloor stepsModel = steps.get(i);
                        stepsModel.setFlatDinningNo(Integer.valueOf(charsequence));
                        steps.set(i, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(i, stepsModel);
                    }
                    Singleton.getInstance().is_edit_floor_dinning = false;
                    //  hideSoftKeyboard(editText);
                    notifyDataSetChanged();
                } else {
                    if ((Singleton.getInstance().is_edit_floor_dinning) && (!general.isEmpty(charsequence))) {
                        // Edit First Time and not null too
                        Singleton.getInstance().is_edit_floor_dinning = false;
                        if (steps.size() > 0) {
                            // For copying rows for the selected one
                            final IndPropertyFloor stepsModel_new = steps.get(adapterposition);
                            stepsModel_new.setFlatDinningNo(Integer.valueOf(charsequence));
                            steps.set(adapterposition, stepsModel_new);
                            Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel_new);
                            // Check all rows
                            for (int x = adapterposition; x < steps.size(); x++) {
                                IndPropertyFloor stepsModel = steps.get(x);
                                if (stepsModel.getFlatDinningNo() == 0) {
                                    stepsModel.setFlatDinningNo(Integer.valueOf(charsequence));
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(x, stepsModel);
                                }
                            }
                            //   hideSoftKeyboard(editText);
                            notifyDataSetChanged();
                        }
                    } else {
                        final IndPropertyFloor stepsModel = steps.get(adapterposition);
                        stepsModel.setFlatDinningNo(Integer.valueOf(charsequence));
                        steps.set(adapterposition, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                    }
                }
            } else {
                final IndPropertyFloor stepsModel = steps.get(adapterposition);
                stepsModel.setFlatDinningNo(0);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
            }
        }
    }

    /**********
     * Todo flat Internal dinning to set the values entered to all floors
     * *******/
    private boolean internalDinning() {
        boolean construction_empty = false;
        int empty_loop = 0;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatDinningNo() == 0) {
                    empty_loop = empty_loop + 1;
                }
            }
        }
        if (steps.size() == empty_loop) {
            construction_empty = true;
        } else {
            construction_empty = false;
        }

        if (KEY_INTERNAL_COMPOSTION_NOT_COPY) {
            construction_empty = false;
        }

        return construction_empty;
    }


    /**********
     * Todo #2 Internal WC focus listener
     * *******/
    private void InternalWCFocusChange(final int position, final EditText internal_wc) {
        internal_wc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                IndPropertyFloor stepsModel = steps.get(position);
                int wcNo = stepsModel.getFlatWcNo();
                if (wcNo == 0) {
                    if (!hasFocus) {
                        String toString = internal_wc.getText().toString();
                        setInternalWC(toString, position, internal_wc);
                    }
                } else {
                    if (!hasFocus) {
                        String toString = internal_wc.getText().toString();
                        if (!general.isEmpty(toString)) {
                            stepsModel.setFlatWcNo(Integer.valueOf(toString));
                        }
                    }
                }

            }
        });
    }

    /**********
     * Todo Inernal dinning to set the values in stepmodel
     * *******/
    private void setInternalWC(String charsequence, final int adapterposition, EditText editText) {
        if (adapterposition != -1) {
            if (!general.isEmpty(charsequence)) {
                if (internalWC()) {
                    for (int i = adapterposition; i < steps.size(); i++) {
                        final IndPropertyFloor stepsModel = steps.get(i);
                        stepsModel.setFlatWcNo(Integer.valueOf(charsequence));
                        steps.set(i, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(i, stepsModel);
                    }
                    Singleton.getInstance().is_edit_floor_wc = false;
                    //   hideSoftKeyboard(editText);
                    notifyDataSetChanged();
                } else {
                    if ((Singleton.getInstance().is_edit_floor_wc) && (!general.isEmpty(charsequence))) {
                        // Edit First Time and not null too
                        Singleton.getInstance().is_edit_floor_wc = false;
                        if (steps.size() > 0) {
                            // For copying rows for the selected one
                            final IndPropertyFloor stepsModel_new = steps.get(adapterposition);
                            stepsModel_new.setFlatWcNo(Integer.valueOf(charsequence));
                            steps.set(adapterposition, stepsModel_new);
                            Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel_new);
                            // Check all rows
                            for (int x = adapterposition; x < steps.size(); x++) {
                                IndPropertyFloor stepsModel = steps.get(x);
                                if (stepsModel.getFlatWcNo() == 0) {
                                    stepsModel.setFlatWcNo(Integer.valueOf(charsequence));
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(x, stepsModel);
                                }
                            }
                            //      hideSoftKeyboard(editText);
                            notifyDataSetChanged();
                        }
                    } else {
                        final IndPropertyFloor stepsModel = steps.get(adapterposition);
                        stepsModel.setFlatWcNo(Integer.valueOf(charsequence));
                        steps.set(adapterposition, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                    }
                }
            } else {
                final IndPropertyFloor stepsModel = steps.get(adapterposition);
                stepsModel.setFlatWcNo(0);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
            }
        }
    }

    /**********
     * Todo flat Internal wc to set the values entered to all floors
     * *******/
    private boolean internalWC() {
        boolean construction_empty = false;
        int empty_loop = 0;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatWcNo() == 0) {
                    empty_loop = empty_loop + 1;
                }
            }
        }
        if (steps.size() == empty_loop) {
            construction_empty = true;
        } else {
            construction_empty = false;
        }
        if (KEY_INTERNAL_COMPOSTION_NOT_COPY) {
            construction_empty = false;
        }
        return construction_empty;
    }

    /**********
     * Todo #3 Internal internal_attachbath focus listener
     * *******/
    private void InternalAttachBathFocusChange(final int position, final EditText internal_attachbath) {
        internal_attachbath.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                IndPropertyFloor stepsModel = steps.get(position);
                int attBathWcNo = stepsModel.getFlatAttBathWcNo();
                if (attBathWcNo == 0) {
                    if (!hasFocus) {
                        String toString = internal_attachbath.getText().toString();
                        setInternalAttachBath(toString, position, internal_attachbath);
                    }
                } else {
                    if (!hasFocus) {
                        String toString = internal_attachbath.getText().toString();
                        if (!general.isEmpty(toString)) {
                            stepsModel.setFlatAttBathWcNo(Integer.valueOf(toString));
                        }
                    }
                }

            }
        });
    }

    /**********
     * Todo Inernal AttachBath to set the values in stepmodel
     * *******/
    private void setInternalAttachBath(String charsequence, final int adapterposition, EditText editText) {
        if (adapterposition != -1) {
            if (!general.isEmpty(charsequence)) {
                if (internalAttachBath()) {
                    for (int i = adapterposition; i < steps.size(); i++) {
                        final IndPropertyFloor stepsModel = steps.get(i);
                        stepsModel.setFlatAttBathWcNo(Integer.valueOf(charsequence));
                        steps.set(i, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(i, stepsModel);
                    }
                    Singleton.getInstance().is_edit_floor_attachbath = false;
                    //   hideSoftKeyboard(editText);
                    notifyDataSetChanged();
                } else {
                    if ((Singleton.getInstance().is_edit_floor_attachbath) && (!general.isEmpty(charsequence))) {
                        // Edit First Time and not null too
                        Singleton.getInstance().is_edit_floor_attachbath = false;
                        if (steps.size() > 0) {
                            // For copying rows for the selected one
                            final IndPropertyFloor stepsModel_new = steps.get(adapterposition);
                            stepsModel_new.setFlatAttBathWcNo(Integer.valueOf(charsequence));
                            steps.set(adapterposition, stepsModel_new);
                            Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel_new);
                            // Check all rows
                            for (int x = adapterposition; x < steps.size(); x++) {
                                IndPropertyFloor stepsModel = steps.get(x);
                                if (stepsModel.getFlatAttBathWcNo() == 0) {
                                    stepsModel.setFlatAttBathWcNo(Integer.valueOf(charsequence));
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(x, stepsModel);
                                }
                            }
                            //      hideSoftKeyboard(editText);
                            notifyDataSetChanged();
                        }
                    } else {
                        final IndPropertyFloor stepsModel = steps.get(adapterposition);
                        stepsModel.setFlatAttBathWcNo(Integer.valueOf(charsequence));
                        steps.set(adapterposition, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                    }
                }
            } else {
                final IndPropertyFloor stepsModel = steps.get(adapterposition);
                stepsModel.setFlatAttBathWcNo(0);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
            }
        }
    }

    /**********
     * Todo flat Internal AttachBath to set the values entered to all floors
     * *******/
    private boolean internalAttachBath() {
        boolean construction_empty = false;
        int empty_loop = 0;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatAttBathWcNo() == 0) {
                    empty_loop = empty_loop + 1;
                }
            }
        }
        if (steps.size() == empty_loop) {
            construction_empty = true;
        } else {
            construction_empty = false;
        }
        if (KEY_INTERNAL_COMPOSTION_NOT_COPY) {
            construction_empty = false;
        }
        return construction_empty;
    }


    /**********
     * Todo #4 Internal internal_balcony focus listener
     * *******/
    private void InternalBalconyFocusChange(final int position, final EditText internal_balcony) {
        internal_balcony.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                IndPropertyFloor stepsModel = steps.get(position);
                int balconyNo = stepsModel.getFlatBalconyNo();
                if (balconyNo == 0) {
                    if (!hasFocus) {
                        String toString = internal_balcony.getText().toString();
                        setInternalBalcony(toString, position, internal_balcony);
                    }
                } else {
                    if (!hasFocus) {
                        String toString = internal_balcony.getText().toString();
                        if (!general.isEmpty(toString)) {
                            stepsModel.setFlatBalconyNo(Integer.valueOf(toString));
                        }
                    }
                }
            }
        });
    }

    /**********
     * Todo Inernal balcony to set the values in stepmodel
     * *******/
    private void setInternalBalcony(String charsequence, final int adapterposition, EditText editText) {
        if (adapterposition != -1) {
            if (!general.isEmpty(charsequence)) {
                if (internalBalcony()) {
                    for (int i = adapterposition; i < steps.size(); i++) {
                        final IndPropertyFloor stepsModel = steps.get(i);
                        stepsModel.setFlatBalconyNo(Integer.valueOf(charsequence));
                        steps.set(i, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(i, stepsModel);
                    }
                    Singleton.getInstance().is_edit_floor_balcony = false;
                    //     hideSoftKeyboard(editText);
                    notifyDataSetChanged();
                } else {
                    if ((Singleton.getInstance().is_edit_floor_balcony) && (!general.isEmpty(charsequence))) {
                        // Edit First Time and not null too
                        Singleton.getInstance().is_edit_floor_balcony = false;
                        if (steps.size() > 0) {
                            // For copying rows for the selected one
                            final IndPropertyFloor stepsModel_new = steps.get(adapterposition);
                            stepsModel_new.setFlatBalconyNo(Integer.valueOf(charsequence));
                            steps.set(adapterposition, stepsModel_new);
                            Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel_new);
                            // Check all rows
                            for (int x = adapterposition; x < steps.size(); x++) {
                                IndPropertyFloor stepsModel = steps.get(x);
                                if (stepsModel.getFlatBalconyNo() == 0) {
                                    stepsModel.setFlatBalconyNo(Integer.valueOf(charsequence));
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(x, stepsModel);
                                }
                            }
                            //         hideSoftKeyboard(editText);
                            notifyDataSetChanged();
                        }
                    } else {
                        final IndPropertyFloor stepsModel = steps.get(adapterposition);
                        stepsModel.setFlatBalconyNo(Integer.valueOf(charsequence));
                        steps.set(adapterposition, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                    }
                }
            } else {
                final IndPropertyFloor stepsModel = steps.get(adapterposition);
                stepsModel.setFlatBalconyNo(0);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
            }
        }
    }

    /**********
     * Todo flat Internal balcony to set the values entered to all floors
     * *******/
    private boolean internalBalcony() {
        boolean construction_empty = false;
        int empty_loop = 0;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatBalconyNo() == 0) {
                    empty_loop = empty_loop + 1;
                }
            }
        }
        if (steps.size() == empty_loop) {
            construction_empty = true;
        } else {
            construction_empty = false;
        }
        if (KEY_INTERNAL_COMPOSTION_NOT_COPY) {
            construction_empty = false;
        }
        return construction_empty;
    }

    /**********
     * Todo #5 internal_fb focus listener
     * *******/
    private void InternalFBFocusChange(final int position, final EditText internal_fb) {
        internal_fb.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                IndPropertyFloor stepsModel = steps.get(position);
                int fbNo = stepsModel.getFlatFbNo();
                if (fbNo == 0) {
                    if (!hasFocus) {
                        String toString = internal_fb.getText().toString();
                        setInternalFB(toString, position, internal_fb);
                    }
                } else {
                    if (!hasFocus) {
                        String toString = internal_fb.getText().toString();
                        if (!general.isEmpty(toString)) {
                            stepsModel.setFlatFbNo(Integer.valueOf(toString));
                        }
                    }
                }

            }
        });
    }

    /**********
     * Todo internal_fb to set the values in stepmodel
     * *******/
    private void setInternalFB(String charsequence, final int adapterposition, EditText editText) {
        if (adapterposition != -1) {
            if (!general.isEmpty(charsequence)) {
                if (internalFB()) {
                    for (int i = adapterposition; i < steps.size(); i++) {
                        final IndPropertyFloor stepsModel = steps.get(i);
                        stepsModel.setFlatFbNo(Integer.valueOf(charsequence));
                        steps.set(i, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(i, stepsModel);
                    }
                    Singleton.getInstance().is_edit_floor_fbs = false;
                    //    hideSoftKeyboard(editText);
                    notifyDataSetChanged();
                } else {
                    if ((Singleton.getInstance().is_edit_floor_fbs) && (!general.isEmpty(charsequence))) {
                        // Edit First Time and not null too
                        Singleton.getInstance().is_edit_floor_fbs = false;
                        if (steps.size() > 0) {
                            // For copying rows for the selected one
                            final IndPropertyFloor stepsModel_new = steps.get(adapterposition);
                            stepsModel_new.setFlatFbNo(Integer.valueOf(charsequence));
                            steps.set(adapterposition, stepsModel_new);
                            Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel_new);
                            // Check all rows
                            for (int x = adapterposition; x < steps.size(); x++) {
                                IndPropertyFloor stepsModel = steps.get(x);
                                if (stepsModel.getFlatFbNo() == 0) {
                                    stepsModel.setFlatFbNo(Integer.valueOf(charsequence));
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(x, stepsModel);
                                }
                            }
                            //       hideSoftKeyboard(editText);
                            notifyDataSetChanged();
                        }
                    } else {
                        final IndPropertyFloor stepsModel = steps.get(adapterposition);
                        stepsModel.setFlatFbNo(Integer.valueOf(charsequence));
                        steps.set(adapterposition, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                    }
                }
            } else {
                final IndPropertyFloor stepsModel = steps.get(adapterposition);
                stepsModel.setFlatFbNo(0);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
            }
        }
    }

    /**********
     * Todo flat internal_fb to set the values entered to all floors
     * *******/
    private boolean internalFB() {
        boolean construction_empty = false;
        int empty_loop = 0;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatFbNo() == 0) {
                    empty_loop = empty_loop + 1;
                }
            }
        }
        if (steps.size() == empty_loop) {
            construction_empty = true;
        } else {
            construction_empty = false;
        }
        if (KEY_INTERNAL_COMPOSTION_NOT_COPY) {
            construction_empty = false;
        }
        return construction_empty;
    }

    /**********
     * Todo #6 internal_db focus listener
     * *******/
    private void InternalDBFocusChange(final int position, final EditText internal_db) {
        internal_db.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                IndPropertyFloor stepsModel = steps.get(position);
                int dbNo = stepsModel.getFlatDbNo();
                if (dbNo == 0) {
                    if (!hasFocus) {
                        String toString = internal_db.getText().toString();
                        setInternalDB(toString, position, internal_db);
                    }
                } else {
                    if (!hasFocus) {
                        String toString = internal_db.getText().toString();
                        if (!general.isEmpty(toString)) {
                            stepsModel.setFlatDbNo(Integer.valueOf(toString));
                        }
                    }
                }
            }
        });
    }

    /**********
     * Todo internal_db to set the values in stepmodel
     * *******/
    private void setInternalDB(String charsequence, final int adapterposition, EditText editText) {
        if (adapterposition != -1) {
            if (!general.isEmpty(charsequence)) {
                if (internalDB()) {
                    for (int i = adapterposition; i < steps.size(); i++) {
                        final IndPropertyFloor stepsModel = steps.get(i);
                        stepsModel.setFlatDbNo(Integer.valueOf(charsequence));
                        steps.set(i, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(i, stepsModel);
                    }
                    Singleton.getInstance().is_edit_floor_dbs = false;
                    //    hideSoftKeyboard(editText);
                    notifyDataSetChanged();
                } else {
                    if ((Singleton.getInstance().is_edit_floor_dbs) && (!general.isEmpty(charsequence))) {
                        // Edit First Time and not null too
                        Singleton.getInstance().is_edit_floor_dbs = false;
                        if (steps.size() > 0) {
                            // For copying rows for the selected one
                            final IndPropertyFloor stepsModel_new = steps.get(adapterposition);
                            stepsModel_new.setFlatDbNo(Integer.valueOf(charsequence));
                            steps.set(adapterposition, stepsModel_new);
                            Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel_new);
                            // Check all rows
                            for (int x = adapterposition; x < steps.size(); x++) {
                                IndPropertyFloor stepsModel = steps.get(x);
                                if (stepsModel.getFlatDbNo() == 0) {
                                    stepsModel.setFlatDbNo(Integer.valueOf(charsequence));
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(x, stepsModel);
                                }
                            }
                            //         hideSoftKeyboard(editText);
                            notifyDataSetChanged();
                        }
                    } else {
                        final IndPropertyFloor stepsModel = steps.get(adapterposition);
                        stepsModel.setFlatDbNo(Integer.valueOf(charsequence));
                        steps.set(adapterposition, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                    }
                }
            } else {
                final IndPropertyFloor stepsModel = steps.get(adapterposition);
                stepsModel.setFlatDbNo(0);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
            }
        }
    }

    /**********
     * Todo flat internal_db to set the values entered to all floors
     * *******/
    private boolean internalDB() {
        boolean construction_empty = false;
        int empty_loop = 0;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatDbNo() == 0) {
                    empty_loop = empty_loop + 1;
                }
            }
        }
        if (steps.size() == empty_loop) {
            construction_empty = true;
        } else {
            construction_empty = false;
        }
        if (KEY_INTERNAL_COMPOSTION_NOT_COPY) {
            construction_empty = false;
        }
        return construction_empty;
    }


    /**********
     * Todo #7 internal_terrace focus listener
     * *******/
    private void InternalTerraceFocusChange(final int position, final EditText internal_terrace) {
        internal_terrace.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                IndPropertyFloor stepsModel = steps.get(position);
                int terraceNo = stepsModel.getFlatTerraceNo();
                if (terraceNo == 0) {
                    if (!hasFocus) {
                        String toString = internal_terrace.getText().toString();
                        setInternalTerrace(toString, position, internal_terrace);
                    }
                } else {
                    if (!hasFocus) {
                        String toString = internal_terrace.getText().toString();
                        if (!general.isEmpty(toString)) {
                            stepsModel.setFlatTerraceNo(Integer.valueOf(toString));
                        }
                    }
                }
            }
        });
    }

    /**********
     * Todo internal_terrace to set the values in stepsmodel
     * *******/
    private void setInternalTerrace(String charsequence, final int adapterposition, EditText editText) {
        if (adapterposition != -1) {
            if (!general.isEmpty(charsequence)) {
                if (internalTerrace()) {
                    for (int i = adapterposition; i < steps.size(); i++) {
                        final IndPropertyFloor stepsModel = steps.get(i);
                        stepsModel.setFlatTerraceNo(Integer.valueOf(charsequence));
                        steps.set(i, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(i, stepsModel);
                    }
                    Singleton.getInstance().is_edit_floor_terrace = false;
                    //    hideSoftKeyboard(editText);
                    notifyDataSetChanged();
                } else {
                    if ((Singleton.getInstance().is_edit_floor_terrace) && (!general.isEmpty(charsequence))) {
                        // Edit First Time and not null too
                        Singleton.getInstance().is_edit_floor_terrace = false;
                        if (steps.size() > 0) {
                            // For copying rows for the selected one
                            final IndPropertyFloor stepsModel_new = steps.get(adapterposition);
                            stepsModel_new.setFlatTerraceNo(Integer.valueOf(charsequence));
                            steps.set(adapterposition, stepsModel_new);
                            Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel_new);
                            // Check all rows
                            for (int x = adapterposition; x < steps.size(); x++) {
                                IndPropertyFloor stepsModel = steps.get(x);
                                if (stepsModel.getFlatTerraceNo() == 0) {
                                    stepsModel.setFlatTerraceNo(Integer.valueOf(charsequence));
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(x, stepsModel);
                                }
                            }
                            //        hideSoftKeyboard(editText);
                            notifyDataSetChanged();
                        }
                    } else {
                        final IndPropertyFloor stepsModel = steps.get(adapterposition);
                        stepsModel.setFlatTerraceNo(Integer.valueOf(charsequence));
                        steps.set(adapterposition, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                    }
                }
            } else {
                final IndPropertyFloor stepsModel = steps.get(adapterposition);
                stepsModel.setFlatTerraceNo(0);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
            }
        }
    }

    /**********
     * Todo flat internal_terrace to set the values entered to all floors
     * *******/
    private boolean internalTerrace() {
        boolean construction_empty = false;
        int empty_loop = 0;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatTerraceNo() == 0) {
                    empty_loop = empty_loop + 1;
                }
            }
        }
        if (steps.size() == empty_loop) {
            construction_empty = true;
        } else {
            construction_empty = false;
        }
        if (KEY_INTERNAL_COMPOSTION_NOT_COPY) {
            construction_empty = false;
        }
        return construction_empty;
    }


    /**********
     * Todo #8 internal_passage focus listener
     * *******/
    private void InternalPassageFocusChange(final int position, final EditText internal_passage) {
        internal_passage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                IndPropertyFloor stepsModel = steps.get(position);
                int passageNo = stepsModel.getFlatPassageNo();
                if (passageNo == 0) {
                    if (!hasFocus) {
                        String toString = internal_passage.getText().toString();
                        setInternalPassage(toString, position, internal_passage);
                    }
                } else {
                    if (!hasFocus) {
                        String toString = internal_passage.getText().toString();
                        if (!general.isEmpty(toString)) {
                            stepsModel.setFlatPassageNo(Integer.valueOf(toString));
                        }
                    }
                }
            }
        });
    }

    /**********
     * Todo internal_passage to set the values in stepsmodel
     * *******/
    private void setInternalPassage(String charsequence, final int adapterposition, EditText editText) {
        if (adapterposition != -1) {
            if (!general.isEmpty(charsequence)) {
                if (internalPassage()) {
                    for (int i = adapterposition; i < steps.size(); i++) {
                        final IndPropertyFloor stepsModel = steps.get(i);
                        stepsModel.setFlatPassageNo(Integer.valueOf(charsequence));
                        steps.set(i, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(i, stepsModel);
                    }
                    Singleton.getInstance().is_edit_floor_passage = false;
                    //    hideSoftKeyboard(editText);
                    notifyDataSetChanged();
                } else {
                    if ((Singleton.getInstance().is_edit_floor_passage) && (!general.isEmpty(charsequence))) {
                        // Edit First Time and not null too
                        Singleton.getInstance().is_edit_floor_passage = false;
                        if (steps.size() > 0) {
                            // For copying rows for the selected one
                            final IndPropertyFloor stepsModel_new = steps.get(adapterposition);
                            stepsModel_new.setFlatPassageNo(Integer.valueOf(charsequence));
                            steps.set(adapterposition, stepsModel_new);
                            Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel_new);
                            // Check all rows
                            for (int x = adapterposition; x < steps.size(); x++) {
                                IndPropertyFloor stepsModel = steps.get(x);
                                if (stepsModel.getFlatPassageNo() == 0) {
                                    stepsModel.setFlatPassageNo(Integer.valueOf(charsequence));
                                    steps.set(x, stepsModel);
                                    Singleton.getInstance().indPropertyFloors.set(x, stepsModel);
                                }
                            }
                            //    hideSoftKeyboard(editText);
                            notifyDataSetChanged();
                        }
                    } else {
                        final IndPropertyFloor stepsModel = steps.get(adapterposition);
                        stepsModel.setFlatPassageNo(Integer.valueOf(charsequence));
                        steps.set(adapterposition, stepsModel);
                        Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
                    }
                }
            } else {
                final IndPropertyFloor stepsModel = steps.get(adapterposition);
                stepsModel.setFlatPassageNo(0);
                steps.set(adapterposition, stepsModel);
                Singleton.getInstance().indPropertyFloors.set(adapterposition, stepsModel);
            }
        }
    }

    /**********
     * Todo flat internal_passage to set the values entered to all floors
     * *******/
    private boolean internalPassage() {
        boolean construction_empty = false;
        int empty_loop = 0;
        if (steps.size() > 0) {
            for (int x = 0; x < steps.size(); x++) {
                IndPropertyFloor stepsModel = steps.get(x);
                if (stepsModel.getFlatPassageNo() == 0) {
                    empty_loop = empty_loop + 1;
                }
            }
        }
        if (steps.size() == empty_loop) {
            construction_empty = true;
        } else {
            construction_empty = false;
        }
        if (KEY_INTERNAL_COMPOSTION_NOT_COPY) {
            construction_empty = false;
        }
        return construction_empty;
    }
}
