package com.realappraiser.gharvalue.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.model.InternalFloorModel;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.Singleton;

import java.util.ArrayList;

/**
 * Created by kaptas on 2/1/18.
 */

@SuppressWarnings("ALL")
public class PropertyFloorInternalAdapter extends RecyclerView.Adapter<PropertyFloorInternalAdapter.ViewHolder> {

    private Context context;
    private ArrayList<IndPropertyFloor> steps;
    private boolean spinnerhalldinning = false, spinnerkitchen = false, spinnerbedroom = false, spinnerbath = false, spinnershop = false;
    private boolean selecthall = true, selectkitchen = true, selectbedroom = true, selectbath = true, selectshop = true;
    private int position_select = 0;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_internal_floor_name_composition;
        public Spinner internal_hall_dinning, internal_kitchen, internal_bedroom, internal_bath, internal_shop_office;
        public FrameLayout frameUsagelay;
        public final IndPropertyFloor stepsModel = new IndPropertyFloor();

        public ViewHolder(View itemView) {
            super(itemView);

            textview_internal_floor_name_composition = (TextView) itemView.findViewById(R.id.textview_internal_floor_name_composition);
            internal_hall_dinning = (Spinner) itemView.findViewById(R.id.internal_hall_dinning);
            internal_kitchen = (Spinner) itemView.findViewById(R.id.internal_kitchen);
            internal_bedroom = (Spinner) itemView.findViewById(R.id.internal_bedroom);
            internal_bath = (Spinner) itemView.findViewById(R.id.internal_bath);
            internal_shop_office = (Spinner) itemView.findViewById(R.id.internal_shop_office);
            textview_internal_floor_name_composition.setTypeface(General.regularTypeface());

            //setFloorNoInternals(Singleton.getInstance().internalFloorHalldining);
            ArrayAdapter<InternalFloorModel> adapter = new ArrayAdapter<>(context,
                    R.layout.row_spinner_item, Singleton.getInstance().internalFloorHalldining);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            internal_hall_dinning.setAdapter(adapter);

            internal_hall_dinning.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    int proximity_id = (int) internal_hall_dinning.getSelectedItemPosition();

                     int adapterPosition = getAdapterPosition();
                     if(adapterPosition < 0) {
                         adapterPosition = 0;
                     }

                    int size_adap = steps.size();
                    Log.e("Adapter size:", size_adap+"");
                    Log.e("Adapter pos:", adapterPosition+"");
                    int size_floor = Singleton.getInstance().internalFloorHalldining.size();
                    Log.e("floorsinternal size:", size_floor+"");
                    Log.e("floorsinternal pos:", position+"");
                    int index = Singleton.getInstance().internalFloorHalldining.get(position).getFloorid();

                    final IndPropertyFloor stepsModel = steps.get(adapterPosition);
                    stepsModel.setFlatHallNo(Singleton.getInstance().internalFloorHalldining.get(position).getFloorid());
                    steps.set(adapterPosition, stepsModel);

                    if (!Singleton.getInstance().floorFromBackend) {
                        if (position > 0) {
                            if (adapterPosition == 0)
                                if (selecthall) {
                                    spinnerhalldinning = true;
                                    selecthall = false;
                                    position_select = proximity_id;
                                    notifyDataSetChanged();
                                }
                        }
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            //setFloorNoInternals(Singleton.getInstance().internalFloorKitchen);
            ArrayAdapter<InternalFloorModel> kitchenadapter = new ArrayAdapter<>(context,
                    R.layout.row_spinner_item, Singleton.getInstance().internalFloorKitchen);
            kitchenadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            internal_kitchen.setAdapter(kitchenadapter);

            internal_kitchen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int proximity_id = (int) internal_kitchen.getSelectedItemPosition();

                    int adapterPosition = getAdapterPosition();
                    if(adapterPosition < 0) {
                        adapterPosition = 0;
                    }

                    final IndPropertyFloor stepsModel = steps.get(adapterPosition);
                    stepsModel.setFlatKitchenNo(Singleton.getInstance().internalFloorKitchen.get(i).getFloorid());
                    steps.set(adapterPosition, stepsModel);

                    if (!Singleton.getInstance().floorFromBackend) {
                        if (i > 0) { //i>0
                            if (adapterPosition == 0)
                                if (selectkitchen) {
                                    spinnerkitchen = true;
                                    selectkitchen = false;
                                    position_select = proximity_id;
                                    notifyDataSetChanged();
                                }
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            //setFloorNoInternals(Singleton.getInstance().internalFloorBedroom);
            ArrayAdapter<InternalFloorModel> bedroomadapter = new ArrayAdapter<>(context,
                    R.layout.row_spinner_item, Singleton.getInstance().internalFloorBedroom);
            bedroomadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            internal_bedroom.setAdapter(bedroomadapter);

            internal_bedroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int proximity_id = (int) internal_bedroom.getSelectedItemId();


                    int adapterPosition = getAdapterPosition();
                    if(adapterPosition < 0) {
                        adapterPosition = 0;
                    }

                    //if (proximity_id <= 0) {
                        final IndPropertyFloor stepsModel = steps.get(adapterPosition);
                        stepsModel.setFlatBedroomNo(Singleton.getInstance().internalFloorBedroom.get(i).getFloorid());
                        steps.set(adapterPosition, stepsModel);

                        if (!Singleton.getInstance().floorFromBackend) {
                            if (i > 0) {
                                if (adapterPosition == 0)
                                    if (selectbedroom) {
                                        spinnerbedroom = true;
                                        selectbedroom = false;
                                        position_select = proximity_id;
                                        notifyDataSetChanged();
                                    }
                            }
                        }
                   // }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            //setFloorNoInternals(Singleton.getInstance().internalFloorBath);
            ArrayAdapter<InternalFloorModel> bathadapter = new ArrayAdapter<>(context,
                    R.layout.row_spinner_item, Singleton.getInstance().internalFloorBath);
            bathadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            internal_bath.setAdapter(bathadapter);

            internal_bath.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int proximity_id = (int) internal_bath.getSelectedItemId();

                    int adapterPosition = getAdapterPosition();
                    if(adapterPosition < 0) {
                        adapterPosition = 0;
                    }


                    final IndPropertyFloor stepsModel = steps.get(adapterPosition);
                    stepsModel.setFlatBathNo(Singleton.getInstance().internalFloorBath.get(i).getFloorid());
                    steps.set(adapterPosition, stepsModel);

                    if (!Singleton.getInstance().floorFromBackend) {
                        if (i > 0) {
                            if (adapterPosition == 0)
                                if (selectbath) {
                                    spinnerbath = true;
                                    selectbath = false;
                                    position_select = proximity_id;
                                    notifyDataSetChanged();
                                }
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            //setFloorNoInternals(Singleton.getInstance().internalFloorshopOffice);
            ArrayAdapter<InternalFloorModel> shopadapter = new ArrayAdapter<>(context,
                    R.layout.row_spinner_item, Singleton.getInstance().internalFloorshopOffice);
            shopadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            internal_shop_office.setAdapter(shopadapter);

            internal_shop_office.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int proximity_id = (int) internal_shop_office.getSelectedItemId();

                    int adapterPosition = getAdapterPosition();
                    if(adapterPosition < 0) {
                        adapterPosition = 0;
                    }

                    final IndPropertyFloor stepsModel = steps.get(adapterPosition);
                    stepsModel.setOfficeNo(Singleton.getInstance().internalFloorshopOffice.get(i).getFloorid());
                    steps.set(adapterPosition, stepsModel);

                    if (!Singleton.getInstance().floorFromBackend) {
                        if (i > 0) {
                            if (adapterPosition == 0)
                                if (selectshop) {
                                    spinnershop = true;
                                    selectshop = false;
                                    position_select = proximity_id;
                                    notifyDataSetChanged();
                                }
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }
    }


    public PropertyFloorInternalAdapter(ArrayList<IndPropertyFloor> steps, Context context) {
        this.steps = steps;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }


    @Override
    public PropertyFloorInternalAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.internal_floors, viewGroup, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        int x = holder.getLayoutPosition();
        int id = 0;
        if (Singleton.getInstance().indPropertyFloors.size() > 0) {
            String valdis = steps.get(position).getFloorName();
            id = steps.get(position).getFlatHallNo();
            Log.d("flat dinning:", id + "");

            holder.textview_internal_floor_name_composition.setText(steps.get(position).getFloorName());


            if (position < Singleton.getInstance().indPropertyFloors.size()) {

                setFloorNumbersSpinner(steps.get(position).getFlatHallNo(), Singleton.getInstance().internalFloorHalldining, holder.internal_hall_dinning);
                setFloorNumbersSpinner(steps.get(position).getFlatKitchenNo(), Singleton.getInstance().internalFloorKitchen, holder.internal_kitchen);
                setFloorNumbersSpinner(steps.get(position).getFlatBedroomNo(), Singleton.getInstance().internalFloorBedroom, holder.internal_bedroom);
                setFloorNumbersSpinner(steps.get(position).getFlatBathNo(), Singleton.getInstance().internalFloorBath, holder.internal_bath);
                setFloorNumbersSpinner(steps.get(position).getOfficeNo(), Singleton.getInstance().internalFloorshopOffice, holder.internal_shop_office);

            }
        } else {

            String valdis = steps.get(position).getFloorName();
            holder.textview_internal_floor_name_composition.setText(steps.get(position).getFloorName());

            if (position_select > 0) {
                if (steps.size() > 0) {
                    if (spinnerhalldinning) {
                        holder.internal_hall_dinning.setSelection(position_select);

                        String val = holder.internal_hall_dinning.getSelectedItem().toString();
                        int valueint = 0;
                        if (!(val.equalsIgnoreCase("")) || (!val.equalsIgnoreCase("null"))) {
                            valueint = Integer.valueOf(val);
                        }
                        final IndPropertyFloor stepsModel = steps.get(position);
                        stepsModel.setFlatHallNo(valueint);
                        steps.set(position, stepsModel);

                        if (position == steps.size() - 1) {
                            selecthall = true;
                            spinnerhalldinning = false;
                            position_select = 0;
                        }
                    } else if (spinnerkitchen) {
                        holder.internal_kitchen.setSelection(position_select);

                        String val = holder.internal_kitchen.getSelectedItem().toString();
                        int valueint = 0;
                        if (!(val.equalsIgnoreCase("")) || (!val.equalsIgnoreCase("null"))) {
                            valueint = Integer.valueOf(val);
                        }
                        final IndPropertyFloor stepsModel = steps.get(position);
                        stepsModel.setFlatKitchenNo(valueint);
                        steps.set(position, stepsModel);

                        if (position == steps.size() - 1) {
                            selectkitchen = true;
                            spinnerkitchen = false;
                            position_select = 0;
                        }
                    } else if (spinnerbedroom) {
                        holder.internal_bedroom.setSelection(position_select);

                        String val = holder.internal_bedroom.getSelectedItem().toString();
                        int valueint = 0;
                        if (!(val.equalsIgnoreCase("")) || (!val.equalsIgnoreCase("null"))) {
                            valueint = Integer.valueOf(val);
                        }
                        final IndPropertyFloor stepsModel = steps.get(position);
                        stepsModel.setFlatBedroomNo(valueint);
                        steps.set(position, stepsModel);

                        if (position == steps.size() - 1) {
                            selectbedroom = true;
                            spinnerbedroom = false;
                            position_select = 0;
                        }
                    } else if (spinnerbath) {
                        holder.internal_bath.setSelection(position_select);

                        String val = holder.internal_bath.getSelectedItem().toString();
                        int valueint = 0;
                        if (!(val.equalsIgnoreCase("")) || (!val.equalsIgnoreCase("null"))) {
                            valueint = Integer.valueOf(val);
                        }
                        final IndPropertyFloor stepsModel = steps.get(position);
                        stepsModel.setFlatBathNo(valueint);
                        steps.set(position, stepsModel);

                        if (position == steps.size() - 1) {
                            selectbath = true;
                            spinnerbath = false;
                            position_select = 0;
                        }
                    } else if (spinnershop) {
                        holder.internal_shop_office.setSelection(position_select);

                        String val = holder.internal_shop_office.getSelectedItem().toString();
                        int valueint = 0;
                        if (!(val.equalsIgnoreCase("")) || (!val.equalsIgnoreCase("null"))) {
                            valueint = Integer.valueOf(val);
                        }
                        final IndPropertyFloor stepsModel = steps.get(position);
                        stepsModel.setOfficeNo(valueint);
                        steps.set(position, stepsModel);


                        if (position == steps.size() - 1) {
                            selectshop = true;
                            spinnershop = false;
                            position_select = 0;
                        }
                    }
                }

            }
        }


    }


    public ArrayList<IndPropertyFloor> getStepList() {
        return steps;
    }


    private void setFloorNumbersSpinner(int id, ArrayList<InternalFloorModel> internalFloorModel, Spinner internalspinner) {
        if (internalFloorModel.size() > 0) {
            for (int i = 0; i < internalFloorModel.size(); i++) {
                if (internalFloorModel.get(i).getName().equalsIgnoreCase("Select")) {
                } else {
                    int floorno = internalFloorModel.get(i).getFloorid();
                    // int floorno = Integer.valueOf(internalFloorModel.get(i).getName());
                    if (id == floorno) {
                        internalspinner.setSelection(i);
                    }
                }
            }
        }
    }


    /*private void setFloorNoInternals(ArrayList<InternalFloorModel> internalFloorModels) {
       // internalFloorModels.clear();
        for (int i = 0; i <= 10; i++) {
            InternalFloorModel floorModel = new InternalFloorModel();
            floorModel.setId(i);
            floorModel.setName("" + i);
            internalFloorModels.add(floorModel);
        }
    }*/
}
