package com.realappraiser.gharvalue.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.realappraiser.gharvalue.AppDatabase;
import com.realappraiser.gharvalue.MyApplication;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.activities.HomeActivity;
import com.realappraiser.gharvalue.adapter.CarParkingAdapter;
import com.realappraiser.gharvalue.adapter.DoorAdapter;
import com.realappraiser.gharvalue.adapter.ExterPaintAdapter;
import com.realappraiser.gharvalue.adapter.ExterStructureAdapter;
import com.realappraiser.gharvalue.adapter.FlooringAdapter;
import com.realappraiser.gharvalue.adapter.PaintAdapter;
import com.realappraiser.gharvalue.adapter.PavingAdapter;
import com.realappraiser.gharvalue.adapter.PresentlyOccupiedAdapter;
import com.realappraiser.gharvalue.adapter.PropertyGeneralFloorAdapter_ka;
import com.realappraiser.gharvalue.adapter.PropertyIdentificationChannelAdapter;
import com.realappraiser.gharvalue.adapter.ProximityAdapter;
import com.realappraiser.gharvalue.adapter.Recycler_remarks_adapter;
import com.realappraiser.gharvalue.adapter.RoofingAdapter;
import com.realappraiser.gharvalue.adapter.WCAdapter;
import com.realappraiser.gharvalue.adapter.WaterAvailabilityAdapter;
import com.realappraiser.gharvalue.adapter.WindowsAdapter;
import com.realappraiser.gharvalue.communicator.DataModel;
import com.realappraiser.gharvalue.communicator.DataResponse;
import com.realappraiser.gharvalue.communicator.JsonRequestData;
import com.realappraiser.gharvalue.communicator.RequestParam;
import com.realappraiser.gharvalue.communicator.ResponseParser;
import com.realappraiser.gharvalue.communicator.TaskCompleteListener;
import com.realappraiser.gharvalue.communicator.WebserviceCommunicator;
import com.realappraiser.gharvalue.model.AmenityQuality;
import com.realappraiser.gharvalue.model.ApproachRoadCondition;
import com.realappraiser.gharvalue.model.Bath;
import com.realappraiser.gharvalue.model.Building;
import com.realappraiser.gharvalue.model.CarParking;
import com.realappraiser.gharvalue.model.CaseDetail;
import com.realappraiser.gharvalue.model.ClassModel;
import com.realappraiser.gharvalue.model.Door;
import com.realappraiser.gharvalue.model.Exterior;
import com.realappraiser.gharvalue.model.FittingQuality;
import com.realappraiser.gharvalue.model.Floor;
import com.realappraiser.gharvalue.model.GetPhoto;
import com.realappraiser.gharvalue.model.GetPhoto_measurment;
import com.realappraiser.gharvalue.model.IndProperty;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.model.Kitchen;
import com.realappraiser.gharvalue.model.Kitchentype;
import com.realappraiser.gharvalue.model.Land;
import com.realappraiser.gharvalue.model.LatLongDetails;
import com.realappraiser.gharvalue.model.Locality;
import com.realappraiser.gharvalue.model.LocalityCategory;
import com.realappraiser.gharvalue.model.Maintenance;
import com.realappraiser.gharvalue.model.Marketablity;
import com.realappraiser.gharvalue.model.Nameofpersons;
import com.realappraiser.gharvalue.model.OfflineDataModel;
import com.realappraiser.gharvalue.model.OflineCase;
import com.realappraiser.gharvalue.model.Paint;
import com.realappraiser.gharvalue.model.Paving;
import com.realappraiser.gharvalue.model.PresentlyOccupied;
import com.realappraiser.gharvalue.model.PropertyIdentificationChannel;
import com.realappraiser.gharvalue.model.Proximity;
import com.realappraiser.gharvalue.model.QualityConstruction;
import com.realappraiser.gharvalue.model.Remarks;
import com.realappraiser.gharvalue.model.Roof;
import com.realappraiser.gharvalue.model.Structure;
import com.realappraiser.gharvalue.model.Tenure;
import com.realappraiser.gharvalue.model.Typeofcompound;
import com.realappraiser.gharvalue.model.WC;
import com.realappraiser.gharvalue.model.WaterAvailability;
import com.realappraiser.gharvalue.model.Window;
import com.realappraiser.gharvalue.utils.ConnectionReceiver;
import com.realappraiser.gharvalue.utils.Connectivity;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.OthersFormListener;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kaptas on 19/12/17.
 */

@SuppressWarnings("ALL")
public class OtherDetails_ka extends Fragment implements View.OnClickListener, OthersFormListener, CompoundButton.OnCheckedChangeListener, View.OnTouchListener, ConnectionReceiver.ConnectionReceiverListener {

    // TODO General class to call typeface and all...
    private General general;

    // TODO ImageView
    @BindView(R.id.icon_more)
    ImageView icon_more;

    // TODO LinearLayout
    @BindView(R.id.linear_more_details)
    LinearLayout linear_more_details;

    // TODO CheckBox - Address
    @BindView(R.id.checkbox_same_address)
    CheckBox checkbox_same_address;
    @BindView(R.id.checkbox_same_as_doc_boundary)
    CheckBox checkbox_same_as_doc_boundary;
    @BindView(R.id.checkbox_same_as_doc_dimension)
    CheckBox checkbox_same_as_doc_dimension;
    @BindView(R.id.checkbox_same_as_doc_setback)
    CheckBox checkbox_same_as_doc_setback;
    @BindView(R.id.checkbox_plot_demarcated)
    CheckBox checkbox_plot_demarcated;
    @BindView(R.id.checkbox_isproperty_demolish)
    CheckBox checkbox_isproperty_demolish;
    @BindView(R.id.checkbox_property_within_muni)
    CheckBox checkbox_property_within_muni;


    // TODO EditText - Address
    @BindView(R.id.editText_addr_perdoc)
    EditText editText_addr_perdoc;
    @SuppressLint("StaticFieldLeak")
    public static EditText editText_addr_site;
    @BindView(R.id.editText_landmark)
    EditText editText_landmark;
    @BindView(R.id.editText_number_of_building)
    EditText editText_number_of_building;
    @BindView(R.id.inputLayout_number_of_building)
    TextInputLayout inputLayout_number_of_building;
    @BindView(R.id.editText_servey_persence)
    EditText editText_survey_persence;
    @BindView(R.id.editText_plotno)
    EditText editText_plotno;
    @BindView(R.id.editText_db_east)
    EditText editText_db_east;
    @BindView(R.id.editText_db_west)
    EditText editText_db_west;
    @BindView(R.id.editText_db_north)
    EditText editText_db_north;
    @BindView(R.id.editText_db_south)
    EditText editText_db_south;
    @BindView(R.id.editText_ab_east)
    EditText editText_ab_east;
    @BindView(R.id.editText_ab_west)
    EditText editText_ab_west;
    @BindView(R.id.editText_ab_north)
    EditText editText_ab_north;
    @BindView(R.id.editText_ab_south)
    EditText editText_ab_south;
    @BindView(R.id.editText_dd_east)
    EditText editText_dd_east;
    @BindView(R.id.editText_dd_west)
    EditText editText_dd_west;
    @BindView(R.id.editText_dd_north)
    EditText editText_dd_north;
    @BindView(R.id.editText_dd_south)
    EditText editText_dd_south;
    @BindView(R.id.editText_ad_east)
    EditText editText_ad_east;
    @BindView(R.id.editText_ad_west)
    EditText editText_ad_west;
    @BindView(R.id.editText_ad_north)
    EditText editText_ad_north;
    @BindView(R.id.editText_ad_south)
    EditText editText_ad_south;
    @BindView(R.id.editText_ds_front)
    EditText editText_ds_front;
    @BindView(R.id.editText_ds_left)
    EditText editText_ds_left;
    @BindView(R.id.editText_ds_right)
    EditText editText_ds_right;
    @BindView(R.id.editText_ds_rear)
    EditText editText_ds_rear;
    @BindView(R.id.editText_as_front)
    EditText editText_as_front;
    @BindView(R.id.editText_as_left)
    EditText editText_as_left;
    @BindView(R.id.editText_as_right)
    EditText editText_as_right;
    @BindView(R.id.editText_as_rear)
    EditText editText_as_rear;

    // TODO EditText - Property


    // TODO EditText - Remarks
    @BindView(R.id.editText_additional_remarks)
    EditText editText_additional_remarks;
    @BindView(R.id.editText_special_remarks)
    EditText editText_special_remarks;
    // TODO EditText - Moreinfo
    @BindView(R.id.editText_municipal_ward)
    EditText editText_municipal_ward;
    @BindView(R.id.editText_taluka_mandal_tehsil)
    EditText editText_taluka_mandal_tehsil;
    @BindView(R.id.editText_villagename)
    EditText editText_villagename;
    @BindView(R.id.editText_district)
    EditText editText_district;
    @BindView(R.id.editText_surveyno)
    EditText editText_surveyno;
    @BindView(R.id.editText_nameofseller)
    EditText editText_nameofseller;
    @BindView(R.id.editText_vendor)
    EditText editText_vendor;
    @BindView(R.id.editText_unitno)
    EditText editText_unitno;
    @BindView(R.id.editText_ctsno)
    EditText editText_ctsno;
    @BindView(R.id.editText_wingno)
    EditText editText_wingno;
    @BindView(R.id.editText_colony_name)
    EditText editText_colony_name;
    @BindView(R.id.editText_year_of_current_tenancy)
    EditText editText_year_of_current_tenancy;
    @BindView(R.id.editText_amenities)
    EditText editText_amenities;
    @BindView(R.id.editText_rentalincome)
    EditText editText_rentalincome;
    @BindView(R.id.editText_nameoftenant)
    EditText editText_nameoftenant;
    @BindView(R.id.editText_wing_name)
    EditText editText_wing_name;
    @BindView(R.id.editText_floor_height)
    EditText editText_floor_height;

    // TODO RadioButton - Moreinfo
    @BindView(R.id.id_radio_pop_yes)
    RadioButton id_radio_pop_yes;
    @BindView(R.id.id_radio_pop_no)
    RadioButton id_radio_pop_no;
    @BindView(R.id.id_radio_garden_exists_yes)
    RadioButton id_radio_garden_exists_yes;
    @BindView(R.id.id_radio_garden_exists_no)
    RadioButton id_radio_garden_exists_no;
    @BindView(R.id.id_radio_separatecompound_yes)
    RadioButton id_radio_separatecompound_yes;
    @BindView(R.id.id_radio_separatecompound_no)
    RadioButton id_radio_separatecompound_no;


    boolean is_more_visible = false;

    @BindView(R.id.textview_save_top_dashboard)
    TextView textview_save_top_dashboard;
    @BindView(R.id.textview_save_bottom_dashboard)
    TextView textview_save_bottom_dashboard;

    // TODO TextView - Address
    @BindView(R.id.textview_address)
    TextView textview_address;
    @BindView(R.id.textview_save_top)
    TextView textview_save_top;
    @BindView(R.id.textview_save_bottom)
    TextView textview_save_bottom;
    @BindView(R.id.textview_document_boundary)
    TextView textview_document_boundary;
    @BindView(R.id.textview_actual_boundary)
    TextView textview_actual_boundary;
    @BindView(R.id.textview_document_dimension)
    TextView textview_document_dimension;
    @BindView(R.id.textview_actual_dimension)
    TextView textview_actual_dimension;
    @BindView(R.id.textview_document_setback)
    TextView textview_document_setback;
    @BindView(R.id.textview_actual_setback)
    TextView textview_actual_setback;
    @BindView(R.id.textview_proximity)
    TextView textview_proximity;
    @BindView(R.id.textview_proximities)
    TextView textview_proximities;
    @BindView(R.id.textview_name_head)
    TextView textview_name_head;
    @BindView(R.id.textview_km)
    TextView textview_km;
    @BindView(R.id.textview_condition_approad)
    TextView textview_condition_approad;
    @BindView(R.id.textview_type_of_compound)
    TextView textview_type_of_compound;
    @BindView(R.id.textview_localitycate)
    TextView textview_localitycate;
    @BindView(R.id.textview_class_head)
    TextView textview_class_head;
    @BindView(R.id.textview_tenureownership)
    TextView textview_tenureownership;
    @BindView(R.id.textview_landapproval)
    TextView textview_landapproval;
    @BindView(R.id.textview_property_identified)
    TextView textview_property_identified;
    @BindView(R.id.textview_persently_occupied)
    TextView textview_persently_occupied;
    // TODO TextView - Property
    @BindView(R.id.textview_property_details)
    TextView textview_property_details;


    @BindView(R.id.textview_interior)
    TextView textview_interior;
    @BindView(R.id.textview_exterior)
    TextView textview_exterior;
    @BindView(R.id.textview_flooring)
    TextView textview_flooring;
    @BindView(R.id.textview_door)
    TextView textview_door;
    @BindView(R.id.textview_roofing)
    TextView textview_roofing;
    @BindView(R.id.textview_paint)
    TextView textview_paint;
    @BindView(R.id.textview_windows)
    TextView textview_windows;
    @BindView(R.id.typeofstructure)
    TextView typeofstructure;
    @BindView(R.id.exteriorpaint)
    TextView exteriorpaint;
    @BindView(R.id.qualityofconstruction)
    TextView qualityofconstruction;
    @BindView(R.id.typeofbuilding)
    TextView typeofbuilding;
    @BindView(R.id.maintenanceofbuilding)
    TextView maintenanceofbuilding;
    @BindView(R.id.qualityoffittings)
    TextView qualityoffittings;
    @BindView(R.id.marketability)
    TextView marketability;


    // TODO TextView - Remarks
    @BindView(R.id.textview_remarks)
    TextView textview_remarks;
    @BindView(R.id.textview_broker)
    TextView textview_broker;
    // TODO TextView - Moreinfo
    @BindView(R.id.textview_more_info)
    TextView textview_more_info;
    @BindView(R.id.textview_pop)
    TextView textview_pop;
    @BindView(R.id.textview_garden_exists)
    TextView textview_garden_exists;
    @BindView(R.id.textview_separatecompound)
    TextView textview_separatecompound;
    @BindView(R.id.passagecorridorschowklobby)
    TextView passagecorridorschowklobby;
    @BindView(R.id.typeoflocality)
    TextView typeoflocality;
    @BindView(R.id.typeoflocality_for_land)
    TextView typeoflocality_for_land;
    @BindView(R.id.bathflooring)
    TextView bathflooring;
    @BindView(R.id.amenitiesquality)
    TextView amenitiesquality;
    @BindView(R.id.kitchentype)
    TextView kitchentypelding;
    @BindView(R.id.wateravailabilty)
    TextView wateravailabilty;
    @BindView(R.id.wc)
    TextView wc;
    @BindView(R.id.pavingaroundbuilding)
    TextView pavingaroundbuilding;
    @BindView(R.id.kitchenshape)
    TextView kitchenshape;
    @BindView(R.id.carparking)
    TextView carparking;

    // TODO - Linear - Address
    @BindView(R.id.div_dimension)
    LinearLayout div_dimension;
    @BindView(R.id.div_setback)
    LinearLayout div_setback;
    @BindView(R.id.div_interior_exterior)
    LinearLayout div_interior_exterior;
    @BindView(R.id.more_info_land)
    LinearLayout more_info_land;
    @BindView(R.id.div_typeoflocality_for_land)
    LinearLayout div_typeoflocality_for_land;

    // TODO - Spinner - Address
    @BindView(R.id.spinner_condition_approad)
    Spinner spinner_condition_approach;
    @BindView(R.id.spinner_type_of_compound)
    Spinner spinner_type_of_compound;
    @BindView(R.id.spinner_select_localitycate)
    Spinner spinner_select_localitycate;
    @BindView(R.id.spinner_select_class)
    Spinner spinner_select_class;
    @BindView(R.id.spinner_select_tenure_ownership)
    Spinner spinner_select_tenure_ownership;
    @BindView(R.id.spinner_landapproval)
    Spinner spinner_landapproval;

    // Todo spinner for interior and exterior
   /* @BindView(R.id.spinner_flooring)
    Spinner spinner_flooring;
    @BindView(R.id.spinner_door)
    Spinner spinner_door;
    @BindView(R.id.spinner_roofing)
    Spinner spinner_roofing;
    @BindView(R.id.spinner_paint)
    Spinner spinner_paint;
    @BindView(R.id.spinner_windows)
    Spinner spinner_windows;
    @BindView(R.id.spinner_typeofstructure)
    Spinner spinner_typeofstructure;
    @BindView(R.id.spinner_exteriorpaint)
    Spinner spinner_exteriorpaint;
    @BindView(R.id.spinner_qualityofconstruction)
    Spinner spinner_qualityofconstruction;
    @BindView(R.id.spinner_typeofbuilding)
    Spinner spinner_typeofbuilding;
    @BindView(R.id.spinner_maintenanceofbuilding)
    Spinner spinner_maintenanceofbuilding;
    @BindView(R.id.spinner_liftinbuilding)
    Spinner spinner_liftinbuilding;
    @BindView(R.id.spinner_qualityoffittings)
    Spinner spinner_qualityoffittings;
    @BindView(R.id.spinner_marketability)
    Spinner spinner_marketability;
*/
    // Todo spinner for more Info

    /* san Integration */
    @BindView(R.id.textview_flooring_text)
    TextView textview_flooring_text;
    @BindView(R.id.textview_roofing_text)
    TextView textview_roofing_text;
    @BindView(R.id.textview_paint_text)
    TextView textview_paint_text;
    @BindView(R.id.textview_door_text)
    TextView textview_door_text;
    @BindView(R.id.textview_window_text)
    TextView textview_window_text;
    @BindView(R.id.textview_exter_struc_text)
    TextView textview_exter_struc_text;
    @BindView(R.id.textview_exter_paint_text)
    TextView textview_exter_paint_text;
    @BindView(R.id.spinner_qualityofconstruction)
    Spinner spinner_qualityofconstruction;
    @BindView(R.id.spinner_typeofbuilding)
    Spinner spinner_typeofbuilding;
    @BindView(R.id.spinner_maintenanceofbuilding)
    Spinner spinner_maintenanceofbuilding;
    @BindView(R.id.spinner_qualityoffittings)
    Spinner spinner_qualityoffittings;
    @BindView(R.id.spinner_marketability)
    Spinner spinner_marketability;

    /***san integration****/

    @BindView(R.id.recyclerview_remarks)
    RecyclerView recyclerview_remarks;
    Recycler_remarks_adapter madapter_remarks;


    @BindView(R.id.id_radiogroup_pop)
    RadioGroup id_radiogroup_pop;
    @BindView(R.id.id_radiogroup_garden_exists)
    RadioGroup id_radiogroup_garden_exists;
    @BindView(R.id.id_radiogroup_separatecompound)
    RadioGroup id_radiogroup_separatecompound;

    @BindView(R.id.spinner_typeoflocality)
    Spinner spinner_typeoflocality;
    @BindView(R.id.spinner_typeoflocality_for_land)
    Spinner spinner_typeoflocality_for_land;
    @BindView(R.id.spinner_bathflooring)
    Spinner spinner_bathflooring;
    @BindView(R.id.spinner_amenitiesquality)
    Spinner spinner_amenitiesquality;
    @BindView(R.id.spinner_kitchentype)
    Spinner spinner_kitchentype;
    /*@BindView(R.id.spinner_pavingaroundbuilding)
    Spinner spinner_pavingaroundbuilding;*/
    @BindView(R.id.spinner_kitchenshape)
    Spinner spinner_kitchenshape;
    @BindView(R.id.spinner_passagecorridorschowklobby)
    Spinner spinner_passagecorridorschowklobby;
    @BindView(R.id.spinner_nameofseller)
    Spinner spinner_nameofseller;
    @BindView(R.id.spinner_nameofvendor)
    Spinner spinner_nameofvendor;


    // Floor
    ArrayList<Floor> Inter_floors_list = new ArrayList<>();
    // Roof
    ArrayList<Roof> Inter_roofing_list = new ArrayList<>();
    // Paint
    ArrayList<Paint> Inter_paint_list = new ArrayList<>();
    // Door
    ArrayList<Door> Inter_door_list = new ArrayList<>();
    // Window
    ArrayList<Window> Inter_window_list = new ArrayList<>();
    // Exterior - Type Of Structure
    ArrayList<Structure> Exter_struc_list = new ArrayList<>();
    // Exterior - Exterior Paint
    ArrayList<Exterior> Exter_paint_list = new ArrayList<>();


    @BindView(R.id.textview_property_identified_text)
    TextView textview_property_identified_text;
    @BindView(R.id.textview_persently_occupied_text)
    TextView textview_persently_occupied_text;
    @BindView(R.id.textview_wateravailabilty_text)
    TextView textview_wateravailabilty_text;
    @BindView(R.id.textview_wc_text)
    TextView textview_wc_text;
    @BindView(R.id.textview_carparking_text)
    TextView textview_carparking_text;
    @BindView(R.id.textview_pavingaroundbuilding)
    TextView textview_pavingaroundbuilding;

    @BindView(R.id.editText_liftinbuilding)
    EditText editText_liftinbuilding;

    @SuppressLint("StaticFieldLeak")
    public static EditText editText_cursor;

    // Address - PropertyIdentificationChannel
    ArrayList<PropertyIdentificationChannel> PropertyIdentificationChannel_list = new ArrayList<>();
    // Address - PresentlyOccupied
    ArrayList<PresentlyOccupied> PresentlyOccupied_list = new ArrayList<>();
    // Address - wateravailabilty
    ArrayList<WaterAvailability> WaterAvailability_list = new ArrayList<>();
    // Address - wc
    ArrayList<WC> WC_list = new ArrayList<>();
    // Address - carparking
    ArrayList<CarParking> CarParking_list = new ArrayList<>();
    // Address - Paving
    ArrayList<Paving> Paving_list = new ArrayList<>();
    /***san integration***/

    // Todo recyclerview variable
    @BindView(R.id.recyclerview_proxmity)
    RecyclerView recyclerview_proxmity;
    private ArrayList<Proximity> list;
    private ArrayList<IndPropertyFloor> floor_list;
    private ArrayList<IndPropertyFloor> floor_number_list;
    private ProximityAdapter listAdapter;
    private PropertyGeneralFloorAdapter_ka listfloorAdapter;
    private LinearLayoutManager llm;
    private Button submitButton;
    private Proximity stepsModel = new Proximity();
    private IndPropertyFloor stepsfloorModel = new IndPropertyFloor();


    Context mContext;
    private String msg = "", info = "";

    String property_type = "building";
//    String property_type = "flat";
//    String property_type = "penthouse";
//    String property_type = "land";

    private String addrsite = "", address_per_doc = "", landmark = "";
    private String surveypresence = "", plotno = "", approachcondition = "";
    private String localitycategory = "", classval = "", tenureOwnership = "";
    private String landapproval = "", propertyidentified = "", presentlyoccupied = "";
    private String db_east = "", db_west = "", db_north = "", db_south = "";
    private String ab_east = "", ab_west = "", ab_north = "", ab_south = "";
    private String dd_east = "", dd_west = "", dd_north = "", dd_south = "";
    private String ad_east = "", ad_west = "", ad_north = "", ad_south = "";
    private String ds_front = "", ds_left = "", ds_right = "", ds_rear = "";
    private String as_front = "", as_left = "", as_right = "", as_rear = "";
    private String caseid;
    public FragmentBuilding_ka fragment_building;
    private ArrayList<Boolean> mandatoryfloors;
    //private boolean floorgeneralfields = true;

    boolean isvalid = true;
    String str_latvalue, str_longvalue;


    /*17th*/
    @BindView(R.id.textview_property_heading)
    TextView textview_property_heading;
    @BindView(R.id.textview_property_type_heading)
    TextView textview_property_type_heading;
    @BindView(R.id.relative_more_click)
    RelativeLayout relative_more_click;
    @BindView(R.id.editText_brokername)
    EditText editText_brokername;
    @BindView(R.id.editText_brokernumber)
    EditText editText_brokernumber;
    @BindView(R.id.editText_brokervalue)
    EditText editText_brokervalue;
    @BindView(R.id.scroll_view)
    ScrollView scroll_view;
    ArrayList<String> initials_typeList = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    public static LinearLayout my_focuslayout;


    String save_type = "";
    String editText_db_east_str, editText_db_west_str, editText_db_north_str, editText_db_south_str;
    String editText_dd_east_str, editText_dd_west_str, editText_dd_north_str, editText_dd_south_str;
    String editText_ds_front_str, editText_ds_left_str, editText_ds_right_str, editText_ds_rear_str;

    // Todo network listener dialog variables
    private android.app.AlertDialog.Builder alert_build;
    private android.app.AlertDialog alert_show;

    //Todo Room persistance Library for offfline storage
    private AppDatabase appDatabase;
    int caseid_int = 0;
    boolean is_offline = false;
    int PropertyId_is = 0;
    public boolean enable_offline_button;

    @BindView(R.id.type_of_compound_div)
    FrameLayout type_of_compound_div;

    @BindView(R.id.reportname_text)
    TextView reportname_text;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.others_ka, container, false);
        ButterKnife.bind(this, view);

        String ReportName = SettingsUtils.getInstance().getValue(SettingsUtils.ReportName, "-");
        reportname_text.setText(ReportName);

        Log.e("real_app_kakode", "real_app_kakode");
        general = new General(getActivity());

        initiateViewsLoading(view);
        /*initiateOtherDetailsView(view);*/

        return view;
    }

    public void initiateViewsLoading(View view) {

        if (Singleton.getInstance().aCase.getStatus() == 13 || Singleton.getInstance().enable_validation_error) {
            //
        } else {
            general.showloading(getActivity());
        }

        initiateOtherDetailsView(view);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        InitiateOtherDetailForm();
                        if (Singleton.getInstance().aCase.getStatus() == 13 || Singleton.getInstance().enable_validation_error) {
                            //
                        } else {
                            setHandlerDelay();
                        }
                    }
                }, 300);

            }
        }, 300);

    }

    private void InitiateOtherDetailForm() {
        initValues();
        initiatePropertyFragmentViews();
        initiateMoreViews();
        spinnerValuesInitiate();
        DisplayViewsfromResult();
        initProximityRecyclerView();
        InteriorExteriorDisplay();
        setBrokerdetails();

        checkbox_boundary();
        checkbox_dimension();
        checkbox_setback();

        // TODO -  call the mandatory_valiadation
        if (Singleton.getInstance().enable_validation_error) {
            set_mandatory_address();
        }

    }

    public void setHandlerDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                general.hideloading();
            }
        }, 900); //900
    }

    private void initiateOtherDetailsView(final View view) {

        editText_addr_site = (EditText) view.findViewById(R.id.editText_addr_site);
        my_focuslayout = (LinearLayout) view.findViewById(R.id.my_focuslayout);
        my_focuslayout.requestFocus();

        // Room - Declared
        if (MyApplication.getAppContext() != null) {
            appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
        }
        // CaseID to interger
        String caseid_str = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        if (!General.isEmpty(caseid_str)) {
            caseid_int = Integer.parseInt(caseid_str);
        }
        // check the case is offline (or) online
        is_offline = SettingsUtils.getInstance().getValue(SettingsUtils.is_offline, false);
        // Property
        if (!general.isEmpty(SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, ""))) {
            PropertyId_is = Integer.parseInt(SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, ""));
        }

        if ((general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyValuation.getDocumentedLandValueSel()))) && (general.isEmpty(String.valueOf(Singleton.getInstance().indPropertyValuation.getMeasuredLandValueSel())))) {
            // If both are null
            Singleton.getInstance().indPropertyValuation.setDocumentedLandValueSel(true);
            Singleton.getInstance().indPropertyValuation.setMeasuredLandValueSel(false);
            Singleton.getInstance().indPropertyValuation.setDocumentedConstrValueSel(true);
            Singleton.getInstance().indPropertyValuation.setMeasuredConstrValueSel(false);
        } else {
            if ((!Singleton.getInstance().indPropertyValuation.getDocumentedLandValueSel()) && (!Singleton.getInstance().indPropertyValuation.getMeasuredLandValueSel())) {
                // If both are Not null and false
                Singleton.getInstance().indPropertyValuation.setDocumentedLandValueSel(true);
                Singleton.getInstance().indPropertyValuation.setMeasuredLandValueSel(false);
                Singleton.getInstance().indPropertyValuation.setDocumentedConstrValueSel(true);
                Singleton.getInstance().indPropertyValuation.setMeasuredConstrValueSel(false);
            }
        }

        // check the offline module is present or not
        enable_offline_button = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_ENABLE_OFFLINE, false);

        if (general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getIntPop()))) {
            Singleton.getInstance().indProperty.setIntPop(false);
        }

        if (general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getGardenExist()))) {
            Singleton.getInstance().indProperty.setGardenExist(false);
        }

        if (general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getSeperateCompoundId()))) {
            Singleton.getInstance().indProperty.setSeperateCompoundId(0);
        }

        // document_boundary
        editText_db_east.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_db_east_str = s.toString();
                checkbox_boundary();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText_db_west.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_db_west_str = s.toString();
                checkbox_boundary();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText_db_north.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_db_north_str = s.toString();
                checkbox_boundary();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText_db_south.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_db_south_str = s.toString();
                checkbox_boundary();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // document_dimension
        editText_dd_east.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_dd_east_str = s.toString();
                checkbox_dimension();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText_dd_west.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_dd_west_str = s.toString();
                checkbox_dimension();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText_dd_north.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_dd_north_str = s.toString();
                checkbox_dimension();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText_dd_south.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_dd_south_str = s.toString();
                checkbox_dimension();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // document_setback
        editText_ds_front.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_ds_front_str = s.toString();
                checkbox_setback();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText_ds_left.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_ds_left_str = s.toString();
                checkbox_setback();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText_ds_right.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_ds_right_str = s.toString();
                checkbox_setback();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText_ds_rear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_ds_rear_str = s.toString();
                checkbox_setback();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        String PropertyType = SettingsUtils.getInstance().getValue(SettingsUtils.PropertyType, "");
        if (!general.isEmpty(PropertyType)) {
            textview_property_type_heading.setText(PropertyType);
        } else {
            textview_property_heading.setVisibility(View.GONE);
            textview_property_type_heading.setVisibility(View.GONE);
        }


        // IntPop - Radio Button
        id_radiogroup_pop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton id_radiogenearal = (RadioButton) view.findViewById(group.getCheckedRadioButtonId());
                if (id_radiogenearal.getText().toString().equalsIgnoreCase(getResources().getString(R.string.yes)))
                    Singleton.getInstance().indProperty.setIntPop(true);
                else
                    Singleton.getInstance().indProperty.setIntPop(false);
            }
        });
        // GardenExist - Radio Button
        id_radiogroup_garden_exists.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton id_radiogenearal = (RadioButton) view.findViewById(group.getCheckedRadioButtonId());
                if (id_radiogenearal.getText().toString().equalsIgnoreCase(getResources().getString(R.string.yes)))
                    Singleton.getInstance().indProperty.setGardenExist(true);
                else
                    Singleton.getInstance().indProperty.setGardenExist(false);
            }
        });
        // SeperateCompoundId - Radio Button
        id_radiogroup_separatecompound.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton id_radiogenearal = (RadioButton) view.findViewById(group.getCheckedRadioButtonId());
                if (id_radiogenearal.getText().toString().equalsIgnoreCase(getResources().getString(R.string.yes))) {
                    // yes
                    Singleton.getInstance().indProperty.setSeperateCompoundId(1);
                    type_of_compound_div.setVisibility(View.VISIBLE);
                } else {
                    // no
                    Singleton.getInstance().indProperty.setSeperateCompoundId(0);
                    type_of_compound_div.setVisibility(View.GONE);
                }
            }
        });

        editText_brokernumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_brokernumber.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void checkbox_boundary() {
        if ((general.isEmpty(editText_db_east_str)) && (general.isEmpty(editText_db_west_str)) && (general.isEmpty(editText_db_north_str)) && (general.isEmpty(editText_db_south_str))) {
            checkbox_same_as_doc_boundary.setVisibility(View.INVISIBLE);
        } else {
            checkbox_same_as_doc_boundary.setVisibility(View.VISIBLE);
        }
    }

    private void checkbox_dimension() {
        if ((general.isEmpty(editText_dd_east_str)) && (general.isEmpty(editText_dd_west_str)) && (general.isEmpty(editText_dd_north_str)) && (general.isEmpty(editText_dd_south_str))) {
            checkbox_same_as_doc_dimension.setVisibility(View.INVISIBLE);
        } else {
            checkbox_same_as_doc_dimension.setVisibility(View.VISIBLE);
        }
    }

    private void checkbox_setback() {
        if ((general.isEmpty(editText_ds_front_str)) && (general.isEmpty(editText_ds_left_str)) && (general.isEmpty(editText_ds_right_str)) && (general.isEmpty(editText_ds_rear_str))) {
            checkbox_same_as_doc_setback.setVisibility(View.INVISIBLE);
        } else {
            checkbox_same_as_doc_setback.setVisibility(View.VISIBLE);
        }
    }

    private void setBrokerdetails() {
        /* Broker details - split ":" */
        String BrokerDetails = Singleton.getInstance().property.getBrokerDetails();
        if (!general.isEmpty(BrokerDetails)) {
            if (BrokerDetails.contains(":")) {
                String[] split_latlng = BrokerDetails.split(":");
                if (split_latlng.length > 0) {
                    for (int x = 0; x < split_latlng.length; x++) {
                        if (x == 0) {
                            if (!general.isEmpty(split_latlng[x]))
                                editText_brokername.setText(split_latlng[x]);
                        } else if (x == 1) {
                            if (!general.isEmpty(split_latlng[x]))
                                editText_brokernumber.setText(split_latlng[x]);
                        } else if (x == 2) {
                            if (!general.isEmpty(split_latlng[x]))
                                editText_brokervalue.setText(split_latlng[x]);
                        }
                    }
                }
            }
        }
    }

    private void getBrokerdetails() {
        String brokername = "";
        if (!general.isEmpty(editText_brokername.getText().toString())) {
            brokername = editText_brokername.getText().toString().trim();
        }

        String brokernumber = "";
        if (!general.isEmpty(editText_brokernumber.getText().toString())) {
            brokernumber = editText_brokernumber.getText().toString().trim();
        }

        String brokervalue = "";
        if (!general.isEmpty(editText_brokervalue.getText().toString())) {
            brokervalue = editText_brokervalue.getText().toString().trim();
        }
        /* set Broker Details */
        String BrokerDetails_str = brokername + ":" + brokernumber + ":" + brokervalue;
        Singleton.getInstance().property.setBrokerDetails(BrokerDetails_str);
    }

    private void initiateMoreViews() {

        // TODO - More details Hide and Show
        icon_more.setImageResource(R.drawable.icon_more);
        linear_more_details.setVisibility(View.GONE);
        relative_more_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_more_visible) {
                    is_more_visible = false;
                    icon_more.setImageResource(R.drawable.icon_more);
                    linear_more_details.setVisibility(View.GONE);
                } else {
                    is_more_visible = true;
                    icon_more.setImageResource(R.drawable.icon_less);
                    linear_more_details.setVisibility(View.VISIBLE);
                }
            }
        });
        /*icon_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/
    }

    private void initiatePropertyFragmentViews() {

        // TODO - Load the fragment according to property
        if (property_type.equalsIgnoreCase("building")) {

            /* Load Building in fragment tab */
            fragment_building = new FragmentBuilding_ka();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_property_type_insert, fragment_building).commitAllowingStateLoss();
            /*dimension and setback - visible */
            div_dimension.setVisibility(View.VISIBLE);
            div_setback.setVisibility(View.VISIBLE);

            /* Valuation Building in fragment tab */
            FragmentValuationBuilding_ka fragment_valuation_building = new FragmentValuationBuilding_ka();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_valuation_type_insert, fragment_valuation_building).commitAllowingStateLoss();
            DisplayBuildingAverage();

        } else if (property_type.equalsIgnoreCase("flat") || property_type.equalsIgnoreCase("penthouse")) {

            /* Load Flat in fragment tab */
            FragmentFlat_ka fragment_flat = new FragmentFlat_ka();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_property_type_insert, fragment_flat).commitAllowingStateLoss();
            /*dimension and setback - gone */
            div_dimension.setVisibility(View.GONE);
            div_setback.setVisibility(View.GONE);

            /* set bundel type and send to common fragment */
            Bundle bundle_pass = new Bundle();
            bundle_pass.putString("property_type", property_type);
            /* Valuation Penthouse in fragment tab */
            FragmentValuationPenthouse_ka fragment_valuation_penthouse = new FragmentValuationPenthouse_ka();
            fragment_valuation_penthouse.setArguments(bundle_pass);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_valuation_type_insert, fragment_valuation_penthouse).commitAllowingStateLoss();


        } else if (property_type.equalsIgnoreCase("land")) {

            /* Load Land in fragment tab */
            FragmentLand_ka fragment_land = new FragmentLand_ka();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_property_type_insert, fragment_land).commitAllowingStateLoss();
            /*dimension and setback - visible and gone */
            div_dimension.setVisibility(View.VISIBLE);
            div_setback.setVisibility(View.GONE);
            div_interior_exterior.setVisibility(View.GONE);
            checkbox_isproperty_demolish.setVisibility(View.GONE);
            /* Hide more Info - Show locality dropdown */
            more_info_land.setVisibility(View.GONE);
            div_typeoflocality_for_land.setVisibility(View.GONE);

            /* Valuation Land in fragment tab */
            FragmentValuationLand_ka fragment_valuation_land = new FragmentValuationLand_ka();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_valuation_type_insert, fragment_valuation_land).commitAllowingStateLoss();

        }
    }

    private void initProximityRecyclerView() {
        // list = getIntent().getStringArrayListExtra("list");
        //To show at least one row
        if (Singleton.getInstance().proximities != null)
            if (Singleton.getInstance().proximities.size() > 0) {
                list = new ArrayList<>();
                for (int i = 0; i < Singleton.getInstance().proximities.size(); i++) {
                    Proximity stepsModel = new Proximity();
                    int id = Singleton.getInstance().proximities.get(i).getProximityId();
                    String distid = Singleton.getInstance().proximities.get(i).getProximityDistance();

                    stepsModel.setId(Singleton.getInstance().proximities.get(i).getId());
                    stepsModel.setCaseId(Singleton.getInstance().proximities.get(i).getCaseId());
                    stepsModel.setProximityId(Singleton.getInstance().proximities.get(i).getProximityId());
                    stepsModel.setProximityName(Singleton.getInstance().proximities.get(i).getProximityName());
                    stepsModel.setProximityDistance(Singleton.getInstance().proximities.get(i).getProximityDistance());
                    list.add(stepsModel);
                }

            } else {
                Singleton.getInstance().proximities.clear();
                if (list == null || list.size() == 0) {
                    list = new ArrayList<>();
                    //  list.add("");
                    Proximity stepsModel = new Proximity();
                    stepsModel.setProximityId(5);
                    list.add(stepsModel);
                    //Suganya added on jan19
                    Singleton.getInstance().proximities.add(stepsModel);
                    Proximity stepsModel2 = new Proximity();
                    stepsModel2.setProximityId(1);
                    list.add(stepsModel2);
                    //Suganya added on jan19
                    Singleton.getInstance().proximities.add(stepsModel);
                }
            }

        listAdapter = new ProximityAdapter(list, getActivity());
        llm = new LinearLayoutManager(getActivity());

        //Setting the adapter
        recyclerview_proxmity.setAdapter(listAdapter);
        recyclerview_proxmity.setLayoutManager(llm);
    }


    private void initValues() {

        mContext = this.getContext();
        //SettingsUtils.init(getActivity());
        // 11/09/2018 - v3.1 changed
        SettingsUtils.init(mContext);
        caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

        //TODO - Address - mediumtypeface
        textview_address.setTypeface(general.mediumtypeface());
        textview_save_bottom.setTypeface(general.mediumtypeface());
        textview_save_top.setTypeface(general.mediumtypeface());
        textview_save_top_dashboard.setTypeface(general.mediumtypeface());
        textview_save_bottom_dashboard.setTypeface(general.mediumtypeface());
        textview_document_boundary.setTypeface(general.mediumtypeface());
        textview_actual_boundary.setTypeface(general.mediumtypeface());
        textview_document_dimension.setTypeface(general.mediumtypeface());
        textview_actual_dimension.setTypeface(general.mediumtypeface());
        textview_document_setback.setTypeface(general.mediumtypeface());
        textview_actual_setback.setTypeface(general.mediumtypeface());
        textview_proximity.setTypeface(general.mediumtypeface());


        //TODO - Address - regulartypeface
        editText_addr_perdoc.setTypeface(general.regulartypeface());
        editText_addr_site.setTypeface(general.regulartypeface());
        editText_landmark.setTypeface(general.regulartypeface());
        editText_number_of_building.setTypeface(general.regulartypeface());
        editText_survey_persence.setTypeface(general.regulartypeface());
        editText_plotno.setTypeface(general.regulartypeface());
        editText_db_east.setTypeface(general.regulartypeface());
        editText_db_west.setTypeface(general.regulartypeface());
        editText_db_north.setTypeface(general.regulartypeface());
        editText_db_south.setTypeface(general.regulartypeface());
        editText_ab_east.setTypeface(general.regulartypeface());
        editText_ab_west.setTypeface(general.regulartypeface());
        editText_ab_north.setTypeface(general.regulartypeface());
        editText_ab_south.setTypeface(general.regulartypeface());
        editText_dd_east.setTypeface(general.regulartypeface());
        editText_dd_west.setTypeface(general.regulartypeface());
        editText_dd_north.setTypeface(general.regulartypeface());
        editText_dd_south.setTypeface(general.regulartypeface());
        editText_ad_east.setTypeface(general.regulartypeface());
        editText_ad_west.setTypeface(general.regulartypeface());
        editText_ad_north.setTypeface(general.regulartypeface());
        editText_ad_south.setTypeface(general.regulartypeface());
        editText_ds_front.setTypeface(general.regulartypeface());
        editText_ds_left.setTypeface(general.regulartypeface());
        editText_ds_right.setTypeface(general.regulartypeface());
        editText_ds_rear.setTypeface(general.regulartypeface());
        editText_as_front.setTypeface(general.regulartypeface());
        editText_as_left.setTypeface(general.regulartypeface());
        editText_as_right.setTypeface(general.regulartypeface());
        editText_as_rear.setTypeface(general.regulartypeface());

        textview_proximities.setTypeface(general.regulartypeface());
        textview_name_head.setTypeface(general.regulartypeface());
        textview_km.setTypeface(general.regulartypeface());
        checkbox_same_address.setTypeface(general.regulartypeface());
        checkbox_same_as_doc_boundary.setTypeface(general.regulartypeface());
        checkbox_same_as_doc_dimension.setTypeface(general.regulartypeface());
        checkbox_same_as_doc_setback.setTypeface(general.regulartypeface());
        checkbox_plot_demarcated.setTypeface(general.regulartypeface());
        checkbox_isproperty_demolish.setTypeface(general.regulartypeface());
        checkbox_property_within_muni.setTypeface(general.regulartypeface());
        textview_condition_approad.setTypeface(general.regulartypeface());
        textview_type_of_compound.setTypeface(general.regulartypeface());
        textview_localitycate.setTypeface(general.regulartypeface());
        textview_class_head.setTypeface(general.regulartypeface());
        textview_tenureownership.setTypeface(general.regulartypeface());
        textview_landapproval.setTypeface(general.regulartypeface());
        textview_property_identified.setTypeface(general.regulartypeface());
        textview_persently_occupied.setTypeface(general.regulartypeface());
        /*spinner_property_identified.setTypeface(general.regulartypeface());
        spinner_persently_occupied.setTypeface(general.regulartypeface());*/


        /* san Integration */
        textview_flooring_text.setTypeface(general.regulartypeface());
        textview_roofing_text.setTypeface(general.regulartypeface());
        textview_paint_text.setTypeface(general.regulartypeface());
        textview_door_text.setTypeface(general.regulartypeface());
        textview_window_text.setTypeface(general.regulartypeface());
        textview_exter_struc_text.setTypeface(general.regulartypeface());
        textview_exter_paint_text.setTypeface(general.regulartypeface());

        //TODO - Property - mediumtypeface
        textview_property_details.setTypeface(general.mediumtypeface());

        textview_interior.setTypeface(general.mediumtypeface());
        textview_exterior.setTypeface(general.mediumtypeface());
        //TODO - Property - regulartypeface
        textview_flooring.setTypeface(general.regulartypeface());
        textview_door.setTypeface(general.regulartypeface());
        textview_roofing.setTypeface(general.regulartypeface());
        textview_paint.setTypeface(general.regulartypeface());
        textview_windows.setTypeface(general.regulartypeface());
        typeofstructure.setTypeface(general.regulartypeface());
        exteriorpaint.setTypeface(general.regulartypeface());
        qualityofconstruction.setTypeface(general.regulartypeface());
        typeofbuilding.setTypeface(general.regulartypeface());
        maintenanceofbuilding.setTypeface(general.regulartypeface());
        editText_liftinbuilding.setTypeface(general.regulartypeface());
        qualityoffittings.setTypeface(general.regulartypeface());
        marketability.setTypeface(general.regulartypeface());


        //TODO - Remarks - mediumtypeface
        textview_remarks.setTypeface(general.mediumtypeface());
        textview_broker.setTypeface(general.mediumtypeface());
        //TODO - Remarks - regulartypeface
        editText_additional_remarks.setTypeface(general.regulartypeface());
        editText_special_remarks.setTypeface(general.regulartypeface());

        // TODO - Moreinfo - mediumtypeface
        textview_more_info.setTypeface(general.mediumtypeface());
        //TODO - Moreinfo - regulartypeface
        editText_municipal_ward.setTypeface(general.regulartypeface());
        editText_taluka_mandal_tehsil.setTypeface(general.regulartypeface());
        editText_villagename.setTypeface(general.regulartypeface());
        editText_district.setTypeface(general.regulartypeface());
        editText_surveyno.setTypeface(general.regulartypeface());
        editText_nameofseller.setTypeface(general.regulartypeface());
        editText_vendor.setTypeface(general.regulartypeface());
        editText_unitno.setTypeface(general.regulartypeface());
        editText_ctsno.setTypeface(general.regulartypeface());
        editText_wingno.setTypeface(general.regulartypeface());
        editText_colony_name.setTypeface(general.regulartypeface());
        editText_year_of_current_tenancy.setTypeface(general.regulartypeface());
        editText_amenities.setTypeface(general.regulartypeface());
        editText_rentalincome.setTypeface(general.regulartypeface());
        editText_nameoftenant.setTypeface(general.regulartypeface());
        editText_wing_name.setTypeface(general.regulartypeface());
        editText_floor_height.setTypeface(general.regulartypeface());
        textview_pop.setTypeface(general.regulartypeface());
        textview_garden_exists.setTypeface(general.regulartypeface());
        textview_separatecompound.setTypeface(general.regulartypeface());
        id_radio_pop_yes.setTypeface(general.regulartypeface());
        id_radio_pop_no.setTypeface(general.regulartypeface());
        id_radio_garden_exists_yes.setTypeface(general.regulartypeface());
        id_radio_garden_exists_no.setTypeface(general.regulartypeface());
        id_radio_separatecompound_yes.setTypeface(general.regulartypeface());
        id_radio_separatecompound_no.setTypeface(general.regulartypeface());
        passagecorridorschowklobby.setTypeface(general.regulartypeface());
        typeoflocality.setTypeface(general.regulartypeface());
        typeoflocality_for_land.setTypeface(general.regulartypeface());
        bathflooring.setTypeface(general.regulartypeface());
        amenitiesquality.setTypeface(general.regulartypeface());
        kitchentypelding.setTypeface(general.regulartypeface());
        wateravailabilty.setTypeface(general.regulartypeface());
        wc.setTypeface(general.regulartypeface());
        pavingaroundbuilding.setTypeface(general.regulartypeface());
        kitchenshape.setTypeface(general.regulartypeface());
        carparking.setTypeface(general.regulartypeface());

        /*17th*/
        textview_property_type_heading.setTypeface(general.regulartypeface());
        textview_property_heading.setTypeface(general.regulartypeface());

        editText_brokername.setTypeface(general.regulartypeface());
        editText_brokernumber.setTypeface(general.regulartypeface());
        editText_brokervalue.setTypeface(general.regulartypeface());


        textview_save_top.setOnClickListener(this);
        textview_save_bottom.setOnClickListener(this);
        textview_save_top_dashboard.setOnClickListener(this);
        textview_save_bottom_dashboard.setOnClickListener(this);


        checkbox_same_address.setOnCheckedChangeListener(this);
        checkbox_same_as_doc_boundary.setOnCheckedChangeListener(this);
        checkbox_same_as_doc_dimension.setOnCheckedChangeListener(this);
        checkbox_same_as_doc_setback.setOnCheckedChangeListener(this);

        /******
         * Set the property category type
         * ******/
        String property_cate_id = SettingsUtils.getInstance().getValue(SettingsUtils.PropertyCategoryId, "");
        setPropertyType(property_cate_id);
    }

    private void setPropertyType(String property_cate_id) {
        if (property_cate_id.equalsIgnoreCase("2")) {
            property_type = "building";
        } else if (property_cate_id.equalsIgnoreCase("1")) {
            property_type = "flat";
        } else if (property_cate_id.equalsIgnoreCase("4")) {
            property_type = "penthouse";
        } else if (property_cate_id.equalsIgnoreCase("3")) {
            property_type = "land";
        }
    }

    private void spinnerValuesInitiate() {

        ArrayAdapter<ApproachRoadCondition> adapterApproachRoadCondition = new ArrayAdapter<ApproachRoadCondition>(mContext, R.layout.row_spinner_item, Singleton.getInstance().approachRoadConditions_list) {
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
        adapterApproachRoadCondition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_condition_approach.setAdapter(adapterApproachRoadCondition);
        spinner_condition_approach.setOnTouchListener(this);

        ArrayAdapter<Typeofcompound> adapterspinner_type_of_compound = new ArrayAdapter<Typeofcompound>(mContext, R.layout.row_spinner_item, Singleton.getInstance().typeofcompound_list) {
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
        adapterspinner_type_of_compound.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type_of_compound.setAdapter(adapterspinner_type_of_compound);
        spinner_type_of_compound.setOnTouchListener(this);


        ArrayAdapter<LocalityCategory> adapterLocalityCategory = new ArrayAdapter<LocalityCategory>(mContext, R.layout.row_spinner_item, Singleton.getInstance().localityCategories_list) {
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
        adapterLocalityCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_select_localitycate.setAdapter(adapterLocalityCategory);
        spinner_select_localitycate.setOnTouchListener(this);

        ArrayAdapter<ClassModel> adapterClassModel = new ArrayAdapter<ClassModel>(mContext, R.layout.row_spinner_item, Singleton.getInstance().classes_list) {
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
        adapterClassModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_select_class.setAdapter(adapterClassModel);
        spinner_select_class.setOnTouchListener(this);

        ArrayAdapter<Tenure> adapterTenure = new ArrayAdapter<Tenure>(mContext, R.layout.row_spinner_item, Singleton.getInstance().tenures_list) {
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
        adapterTenure.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_select_tenure_ownership.setAdapter(adapterTenure);
        spinner_select_tenure_ownership.setOnTouchListener(this);

        ArrayAdapter<Land> adapterLand = new ArrayAdapter<Land>(mContext, R.layout.row_spinner_item, Singleton.getInstance().land_list) {
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
        adapterLand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_landapproval.setAdapter(adapterLand);
        spinner_landapproval.setOnTouchListener(this);

        // spinner - Quality Of Construction
        ArrayAdapter<QualityConstruction> qualityConstructionArrayAdapter = new ArrayAdapter<QualityConstruction>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().qualityConstructions_list) {
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
        qualityConstructionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_qualityofconstruction.setAdapter(qualityConstructionArrayAdapter);
        spinner_qualityofconstruction.setOnTouchListener(this);

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getQualityConstructionId()))) {
            Log.e("spinner_qualityofcon", "::: " + Singleton.getInstance().indProperty.getQualityConstructionId());
            if (Singleton.getInstance().indProperty.getQualityConstructionId() != 0) {
                ArrayList<QualityConstruction> qualityConstructions_ = new ArrayList<>();
                qualityConstructions_ = Singleton.getInstance().qualityConstructions_list;
                for (int x = 0; x < qualityConstructions_.size(); x++) {
                    if (qualityConstructions_.get(x).getQualityConstructionId() == Singleton.getInstance().indProperty.getQualityConstructionId()) {
                        spinner_qualityofconstruction.setSelection(x);
                    }
                }
            }
        }

        // spinner - Type Of Building
        ArrayAdapter<Building> buildingArrayAdapter = new ArrayAdapter<Building>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().buildings_list) {
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
        buildingArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typeofbuilding.setAdapter(buildingArrayAdapter);
        spinner_typeofbuilding.setOnTouchListener(this);

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getTypeOfBuildingId()))) {
            Log.e("spinner_typeofbuilding", "::: " + Singleton.getInstance().indProperty.getTypeOfBuildingId());
            if (Singleton.getInstance().indProperty.getTypeOfBuildingId() != 0) {
                ArrayList<Building> qualityConstructions_ = new ArrayList<>();
                qualityConstructions_ = Singleton.getInstance().buildings_list;
                for (int x = 0; x < qualityConstructions_.size(); x++) {
                    if (qualityConstructions_.get(x).getTypeOfBuildingId() == Singleton.getInstance().indProperty.getTypeOfBuildingId()) {
                        spinner_typeofbuilding.setSelection(x);
                    }
                }
            }
        }

        // spinner - Maintenance Of Building
        ArrayAdapter<Maintenance> maintenanceArrayAdapter = new ArrayAdapter<Maintenance>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().maintenances_list) {
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
        maintenanceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_maintenanceofbuilding.setAdapter(maintenanceArrayAdapter);
        spinner_maintenanceofbuilding.setOnTouchListener(this);

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getMaintenanceOfBuildingId()))) {
            Log.e("spinner_maintenance", "::: " + Singleton.getInstance().indProperty.getMaintenanceOfBuildingId());
            if (Singleton.getInstance().indProperty.getMaintenanceOfBuildingId() != 0) {
                ArrayList<Maintenance> qualityConstructions_ = new ArrayList<>();
                qualityConstructions_ = Singleton.getInstance().maintenances_list;
                for (int x = 0; x < qualityConstructions_.size(); x++) {
                    if (qualityConstructions_.get(x).getMaintenanceOfBuildingId() == Singleton.getInstance().indProperty.getMaintenanceOfBuildingId()) {
                        spinner_maintenanceofbuilding.setSelection(x);
                    }
                }
            }
        }

        // spinner - Quality of Fittings
        ArrayAdapter<FittingQuality> fittingQualityArrayAdapter = new ArrayAdapter<FittingQuality>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().fittingQualities_List) {
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
        fittingQualityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_qualityoffittings.setAdapter(fittingQualityArrayAdapter);
        spinner_qualityoffittings.setOnTouchListener(this);

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getFittingQualityId()))) {
            if (Singleton.getInstance().indProperty.getFittingQualityId() != 0) {
                ArrayList<FittingQuality> qualityConstructions_ = new ArrayList<>();
                qualityConstructions_ = Singleton.getInstance().fittingQualities_List;
                for (int x = 0; x < qualityConstructions_.size(); x++) {
                    if (qualityConstructions_.get(x).getFittingQualityId() == Singleton.getInstance().indProperty.getFittingQualityId()) {
                        spinner_qualityoffittings.setSelection(x);
                    }
                }
            }
        }

        // spinner - Marketability
        ArrayAdapter<Marketablity> marketablityArrayAdapter = new ArrayAdapter<Marketablity>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().marketablities_list) {
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
        marketablityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_marketability.setAdapter(marketablityArrayAdapter);
        spinner_marketability.setOnTouchListener(this);

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getMarketabilityId()))) {
            Log.e("spinner_marketability", "::: " + Singleton.getInstance().indProperty.getMarketabilityId());
            if (Singleton.getInstance().indProperty.getMarketabilityId() != 0) {
                ArrayList<Marketablity> qualityConstructions_ = new ArrayList<>();
                qualityConstructions_ = Singleton.getInstance().marketablities_list;
                for (int x = 0; x < qualityConstructions_.size(); x++) {
                    if (qualityConstructions_.get(x).getMarketabilityId() == Singleton.getInstance().indProperty.getMarketabilityId()) {
                        spinner_marketability.setSelection(x);
                    }
                }
            }
        }


        /******
         * Add custom lift
         * ********/
        ArrayList<String> customLift = new ArrayList<>();
        customLift.add("Select");
        for (int i = 1; i <= 20; i++) {
            customLift.add(String.valueOf(i));
        }
        Singleton.getInstance().customLift = customLift;


        /* Hide the spinner_nameofseller as per cient request for only kakode - Date - 04/09/2018 */
        // setNameofSellerSpinner();


        // spinner - name of vendor
        ArrayAdapter<Nameofpersons> NameofpersonsArrayAdapter = new ArrayAdapter<Nameofpersons>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().nameofpersons_list) {
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
        NameofpersonsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_nameofvendor.setAdapter(NameofpersonsArrayAdapter);
        spinner_nameofvendor.setOnTouchListener(this);

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().property.getNameofVendorId()))) {
            if (Singleton.getInstance().property.getNameofVendorId() != 0) {
                ArrayList<Nameofpersons> qualityConstructions_ = new ArrayList<>();
                qualityConstructions_ = Singleton.getInstance().nameofpersons_list;
                for (int x = 0; x < qualityConstructions_.size(); x++) {
                    if (qualityConstructions_.get(x).getNameofVendorId() == Singleton.getInstance().property.getNameofVendorId()) {
                        spinner_nameofvendor.setSelection(x);
                    }
                }
            }
        }


    }

    private void setNameofSellerSpinner() {
        // spinner - nameof seller initials
        initials_typeList = new ArrayList<>();
        initials_typeList = general.NameofSellerInitials_array();
        ArrayAdapter<String> nameofsellerListAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row_spinner_item, initials_typeList) {
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

        nameofsellerListAdapter.setDropDownViewResource(R.layout.row_spinner_item_popup);
        spinner_nameofseller.setPrompt(getResources().getString(R.string.nameof_seller));
        spinner_nameofseller.setAdapter(nameofsellerListAdapter);
        spinner_nameofseller.setOnTouchListener(this);

        if (!general.isEmpty(Singleton.getInstance().property.getNameOfSeller())) {
            String nameseller_id = Singleton.getInstance().property.getNameOfSeller();
            String[] splitInitial = {};
            String nameofseller = "", initialconcat = "";
            int itemCount = 0;


            //splitInitial = nameseller_id.split("\\s+");
            for (int i = 0; i < initials_typeList.size(); i++) {
                String initials = initials_typeList.get(i);

                if (nameseller_id.trim().equalsIgnoreCase(initials.trim())) {
                    spinner_nameofseller.setSelection(i, false);
                    nameofseller = "";
                    splitInitial = null;
                    break;
                } else {
                    if (nameseller_id.trim().contains(".")) {
                        itemCount = nameseller_id.split("\\.").length;
                        splitInitial = nameseller_id.split("\\.");

                        if (itemCount > 2) {
                            String first = splitInitial[0].trim();
                            String second = splitInitial[1].trim();
                            initialconcat = first + "." + second;
                        }
                    }

                    if (itemCount > 2) {
                        if (initials.trim().equalsIgnoreCase(initialconcat.trim())) {
                            spinner_nameofseller.setSelection(i, false);
                            break;
                        }
                    } else {

                        if (nameseller_id.trim().equalsIgnoreCase(initials.trim())) {
                            spinner_nameofseller.setSelection(i, false);
                            break;
                        } else if (splitInitial.length > 0) {
                            if (splitInitial[0].equalsIgnoreCase(initials.trim())) {
                                spinner_nameofseller.setSelection(i, false);
                                break;
                            }
                        }

                    }
                }
            }


            if (splitInitial != null && splitInitial.length > 0) {
                for (int i = 0; i < splitInitial.length; i++) {

                    if (itemCount > 2) {
                        if (i != 0 && i != 1)
                            nameofseller += splitInitial[i];
                    } else {
                        if (i != 0) {
                            nameofseller += splitInitial[i] + " "; // pipe symbol "\\|"
                            //nameofseller += splitInitial[i] + " "; // pipe symbol "\\|"
                        }
                    }
                }
            } else {

                for (int i = 0; i < initials_typeList.size(); i++) {
                    String initials = initials_typeList.get(i);
                    if (initials.trim().equalsIgnoreCase(nameseller_id.trim())) {
                        spinner_nameofseller.setSelection(i, false);
                        nameofseller = "";
                        break;

                    } else {
                        nameofseller = nameseller_id;
                    }
                }

            }


            if (!general.isEmpty(nameofseller))
                editText_nameofseller.setText(nameofseller);

        } else {
            spinner_nameofseller.setSelection(0, false);
            editText_nameofseller.setText("");
        }

        /*spinner_nameofseller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_coming_inside", "spinner_coming_inside");
                if (initials_typeList.get(position).toString().equalsIgnoreCase("Select")) {
                    Singleton.getInstance().property.setNameOfSeller("");
                } else {
                    String textbox = editText_nameofseller.getText().toString().trim();
                    Singleton.getInstance().property.setNameOfSeller(initials_typeList.get(position).toString() + " " + textbox);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/
    }


    private void getProximityListData() {

        caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

        JSONArray jsonArray = new JSONArray();
        ArrayList<Proximity> proximityArrayList = new ArrayList<>();
        list = listAdapter.getStepList();
        for (int j = 0; j < list.size(); j++) {
            if (Singleton.getInstance().proximities.size() > 0) {
                // Update from the proximity values
                Proximity proximity = new Proximity();
                proximity.setId(list.get(j).getId());
                proximity.setCaseId(Integer.valueOf(caseid)); //Integer.value(caseid);
                proximity.setProximityId(list.get(j).getProximityId());
                proximity.setProximityName(list.get(j).getProximityName());
                proximity.setProximityDistance(list.get(j).getProximityDistance());
                proximityArrayList.add(proximity);
            } else {
                // Add New proximity
                try {
                    JSONObject proximityObj = new JSONObject();
                    proximityObj.put("CaseId    ", caseid);
                    proximityObj.put("ProximityId", list.get(j).getProximityId());
                    proximityObj.put("ProximityName", list.get(j).getProximityName());
                    proximityObj.put("ProximityDistance", list.get(j).getProximityDistance());
                    jsonArray.put(proximityObj);

                    Proximity proximity = new Proximity();
                    proximity.setCaseId(Integer.valueOf(caseid)); //list.get(j).getProximityId()
                    proximity.setProximityId(list.get(j).getProximityId());
                    proximity.setProximityName(list.get(j).getProximityName());
                    proximity.setProximityDistance(list.get(j).getProximityDistance());
                    proximityArrayList.add(proximity);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            Singleton.getInstance().proximities = proximityArrayList;

        }
    }


    private int getDocSumValue(ArrayList<IndPropertyFloor> floor_list) {
        int sumtotal = 0;

        for (int i = 0; i < floor_list.size(); i++) {
            if (!general.isEmpty(floor_list.get(i).getDocumentFloorArea())) {
                String numOnly = general.NumOnly(floor_list.get(i).getDocumentFloorArea());
                if (!numOnly.equalsIgnoreCase("")) {
                    int area = Integer.valueOf(floor_list.get(i).getDocumentFloorArea());
                    sumtotal = sumtotal + area;
                }
            }
        }
        return sumtotal;
    }

    private int getMeasureSumValue(ArrayList<IndPropertyFloor> floor_list) {
        int sumtotal = 0;

        for (int i = 0; i < floor_list.size(); i++) {
            if (!general.isEmpty(floor_list.get(i).getMeasuredFloorArea())) {
                String numOnly = general.NumOnly(floor_list.get(i).getMeasuredFloorArea());
                if (!numOnly.equalsIgnoreCase("")) {
                    int area = Integer.valueOf(floor_list.get(i).getMeasuredFloorArea());
                    sumtotal = sumtotal + area;
                }
            }
        }
        return sumtotal;
    }

    private int getCompletedSumValue(ArrayList<IndPropertyFloor> floor_list) {
        int sumtotal = 0;

        if (floor_list.size() > 0) {
            for (int i = 0; i < floor_list.size(); i++) {
                String numOnly = general.NumOnly(String.valueOf(floor_list.get(i).getPercentageCompletion()));
                if (!numOnly.equalsIgnoreCase("")) {
                    int area = floor_list.get(i).getPercentageCompletion();
                    sumtotal = sumtotal + area;
                }
            }
            sumtotal = sumtotal / floor_list.size();
        }
        return sumtotal;
    }

    private void getIndPropertyFloorListData() {

        caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        JSONArray jsonArray = new JSONArray();
        ArrayList<IndPropertyFloor> floorsArrayList = new ArrayList<>();
        ArrayList<IndPropertyFloor> floorList = Singleton.getInstance().indPropertyFloors;

        floor_list = FragmentBuilding_ka.listAdapter.getStepList();
        for (int j = 0; j < floor_list.size(); j++) {

           /* general.CustomToast(floor_list.get(j).getFloorName() + ",," +
                    floor_list.get(j).getPercentageCompletion() + "  spinner::" +
                    floor_list.get(j).getConstructionStageId());*/

            // Add New IndPropertyFloors
            try {
                JSONObject floorObj = new JSONObject();
                floorObj.put("CaseId    ", caseid);
                floorObj.put("ConstructionStageId", floor_list.get(j).getConstructionStageId());
                floorObj.put("FloorName", floor_list.get(j).getFloorName());
                floorObj.put("PercentageCompletion", floor_list.get(j).getPercentageCompletion());
                jsonArray.put(floorObj);

                String nem = floor_list.get(j).getFloorName();

                if (j < floorList.size()) {
                    IndPropertyFloor propertyFloor = floorList.get(j);
                    propertyFloor.setCaseId(Integer.valueOf(caseid));
                    propertyFloor.setConstructionStageId(floor_list.get(j).getConstructionStageId());
                    propertyFloor.setFloorName(floor_list.get(j).getFloorName());
                    propertyFloor.setPercentageCompletion(floor_list.get(j).getPercentageCompletion());
                    propertyFloor.setPropertyAge(floor_list.get(j).getPropertyAge());
                    propertyFloor.setResidualLife(floor_list.get(j).getResidualLife());
                    propertyFloor.setDocumentFloorArea(floor_list.get(j).getDocumentFloorArea());
                    propertyFloor.setMeasuredFloorArea(floor_list.get(j).getMeasuredFloorArea());
                    propertyFloor.setPresentCondition(floor_list.get(j).getPresentCondition());
                    propertyFloor.setPropertyFloorUsageId(floor_list.get(j).getPropertyFloorUsageId());
                    /* save DocumentFloorAreaUnit */
                    propertyFloor.setDocumentFloorAreaUnit(FragmentBuilding_ka.measurment_floor_id);
                    propertyFloor.setMeasuredFloorAreaUnit(FragmentBuilding_ka.measurment_floor_id);
                    propertyFloor.setPercentageDepreciation(floor_list.get(j).getPercentageDepreciation());

                    final int floorno = j + 1;
                    propertyFloor.setFloorNo(floorno);

                    floorsArrayList.add(propertyFloor);

                } else {
                    IndPropertyFloor propertyFloor = new IndPropertyFloor();
                    propertyFloor.setCaseId(Integer.valueOf(caseid));
                    propertyFloor.setConstructionStageId(floor_list.get(j).getConstructionStageId());
                    propertyFloor.setFloorName(floor_list.get(j).getFloorName());
                    propertyFloor.setPercentageCompletion(floor_list.get(j).getPercentageCompletion());
                    propertyFloor.setPropertyAge(floor_list.get(j).getPropertyAge());
                    propertyFloor.setResidualLife(floor_list.get(j).getResidualLife());
                    propertyFloor.setDocumentFloorArea(floor_list.get(j).getDocumentFloorArea());
                    propertyFloor.setMeasuredFloorArea(floor_list.get(j).getMeasuredFloorArea());
                    propertyFloor.setPresentCondition(floor_list.get(j).getPresentCondition());
                    propertyFloor.setPropertyFloorUsageId(floor_list.get(j).getPropertyFloorUsageId());
                    /* save DocumentFloorAreaUnit */
                    propertyFloor.setDocumentFloorAreaUnit(FragmentBuilding_ka.measurment_floor_id);
                    propertyFloor.setMeasuredFloorAreaUnit(FragmentBuilding_ka.measurment_floor_id);
                    propertyFloor.setPercentageDepreciation(floor_list.get(j).getPercentageDepreciation());

                    final int floorno = j + 1;
                    propertyFloor.setFloorNo(floorno);

                    floorsArrayList.add(propertyFloor);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            Singleton.getInstance().indPropertyFloors = floorsArrayList;
        }
        /********
         * Floor Numbers internal composition update
         * **********/
        ArrayList<IndPropertyFloor> floorsNumbersArrayList = new ArrayList<>();
        floor_number_list = FragmentBuilding_ka.internalfloorlistAdapter.getStepList();
        ArrayList<IndPropertyFloor> floors = Singleton.getInstance().indPropertyFloors;
        for (int j = 0; j < floor_number_list.size(); j++) {
            try {

                IndPropertyFloor propertyFloor = floors.get(j);
                propertyFloor.setCaseId(Integer.valueOf(caseid));
                propertyFloor.setFlatHallNo(floor_number_list.get(j).getFlatHallNo());
                propertyFloor.setFlatKitchenNo(floor_number_list.get(j).getFlatKitchenNo());
                propertyFloor.setFlatBedroomNo(floor_number_list.get(j).getFlatBedroomNo());
                propertyFloor.setFlatBathNo(floor_number_list.get(j).getFlatBathNo());
                propertyFloor.setOfficeNo(floor_number_list.get(j).getOfficeNo());
                floorsNumbersArrayList.add(propertyFloor);

                /*if (j < floors.size()) {
                    IndPropertyFloor propertyFloor = floors.get(j);
                    propertyFloor.setCaseId(Integer.valueOf(caseid));
                    propertyFloor.setFlatHallNo(floor_number_list.get(j).getFlatHallNo());
                    propertyFloor.setFlatKitchenNo(floor_number_list.get(j).getFlatKitchenNo());
                    propertyFloor.setFlatBedroomNo(floor_number_list.get(j).getFlatBedroomNo());
                    propertyFloor.setFlatBathNo(floor_number_list.get(j).getFlatBathNo());
                    propertyFloor.setOfficeNo(floor_number_list.get(j).getOfficeNo());
                    floorsNumbersArrayList.add(propertyFloor);
                } else {
                    IndPropertyFloor propertyFloor = new IndPropertyFloor();
                    propertyFloor.setCaseId(Integer.valueOf(caseid));
                    propertyFloor.setFlatHallNo(floor_number_list.get(j).getFlatHallNo());
                    propertyFloor.setFlatKitchenNo(floor_number_list.get(j).getFlatKitchenNo());
                    propertyFloor.setFlatBedroomNo(floor_number_list.get(j).getFlatBedroomNo());
                    propertyFloor.setFlatBathNo(floor_number_list.get(j).getFlatBathNo());
                    propertyFloor.setOfficeNo(floor_number_list.get(j).getOfficeNo());
                    floorsNumbersArrayList.add(propertyFloor);

                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }

            Singleton.getInstance().indPropertyFloors = floorsNumbersArrayList;
        }

    }


    private void ValidateFloorsGeneralMandatory() {
        mandatoryfloors = validateFloorsGeneralInfo();
        //floorgeneralfields = true;

        for (int i = 0; i < mandatoryfloors.size(); i++) {
            if (!mandatoryfloors.get(i)) {
                // floorgeneralfields = false;
            }
        }
    }


    private ArrayList<Boolean> validateFloorsGeneralInfo() {

        ArrayList<Boolean> mandatoryFloors = new ArrayList<>();
        if (Singleton.getInstance().indPropertyFloors != null) {
            for (int i = 0; i < Singleton.getInstance().indPropertyFloors.size(); i++) {
                boolean mandatory = false;
                String floorname = Singleton.getInstance().indPropertyFloors.get(i).getFloorName();
                int floorstage = Singleton.getInstance().indPropertyFloors.get(i).getConstructionStageId();
                //int floorpercentage_complete = Singleton.getInstance().indPropertyFloors.get(i).getPercentageCompletion();

                if (general.isEmpty(floorname)) {
                    mandatory = false;
                } else if (floorstage == 0) {
                    mandatory = false;
                /*} else if (floorpercentage_complete == 0) {
                    mandatory = false;*/
                } else {
                    mandatory = true;
                }

                mandatoryFloors.add(mandatory);
            }
        }
        return mandatoryFloors;
    }

    private void getAddressDetailsInputData() {

        caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        Singleton.getInstance().property.setCaseId(Integer.valueOf(caseid));
        if (Singleton.getInstance().property != null) {
            addrsite = editText_addr_site.getText().toString().trim();
            if (!general.isEmpty(addrsite)) {
                Singleton.getInstance().property.setPropertyAddressAtSite(addrsite);
            } else {
                Singleton.getInstance().property.setPropertyAddressAtSite("");
            }

            address_per_doc = editText_addr_perdoc.getText().toString().trim();
            if (!general.isEmpty(address_per_doc)) {
                Singleton.getInstance().aCase.setPropertyAddress(address_per_doc);
            } else {
                Singleton.getInstance().aCase.setPropertyAddress("");
            }

            landmark = editText_landmark.getText().toString().trim();
            if (!general.isEmpty(landmark)) {
                Singleton.getInstance().property.setLandmark(landmark);
            } else {
                Singleton.getInstance().property.setLandmark("");
            }

            String str_number_of_building = editText_number_of_building.getText().toString().trim();
            if (!general.isEmpty(str_number_of_building)) {
                Singleton.getInstance().indProperty.setNumberOfBuildings(str_number_of_building);
            } else {
                Singleton.getInstance().indProperty.setNumberOfBuildings("");
            }


            surveypresence = editText_survey_persence.getText().toString().trim();
            if (!general.isEmpty(surveypresence)) {
                Singleton.getInstance().property.setSurveyInPresenceOf(surveypresence);
            } else {
                Singleton.getInstance().property.setSurveyInPresenceOf("");
            }

            plotno = editText_plotno.getText().toString().trim();
            if (!general.isEmpty(plotno)) {
                Singleton.getInstance().property.setPlotNo(plotno);
            } else {
                Singleton.getInstance().property.setPlotNo("");
            }

            /**************
             * Get Address Spinner single select value
             * *********************/
            int pos = spinner_condition_approach.getSelectedItemPosition();
            if (pos > 0) {
                int conditionapproachid = Singleton.getInstance().approachRoadConditions_list.get(pos).getApproachRoadConditionId();
                if (conditionapproachid != 0)
                    Singleton.getInstance().property.setApproachRoadConditionId(conditionapproachid);
            } else {
                Singleton.getInstance().property.setApproachRoadConditionId(null);
            }

            int pos_spinner_type_of_compound = spinner_type_of_compound.getSelectedItemPosition();
            if (pos_spinner_type_of_compound > 0) {
                int conditionapproachid = Singleton.getInstance().typeofcompound_list.get(pos_spinner_type_of_compound).getTypeOfCompoundId();
                if (conditionapproachid != 0)
                    Singleton.getInstance().indProperty.setTypeOfCompound(conditionapproachid);
            } else {
                Singleton.getInstance().indProperty.setTypeOfCompound(null);
            }


            int posLocality = spinner_select_localitycate.getSelectedItemPosition();
            if (posLocality > 0) {
                int categoryId = Singleton.getInstance().localityCategories_list.get(posLocality).getLocalityCategoryId();
                if (categoryId != 0)
                    Singleton.getInstance().property.setLocalityCategoryId(categoryId);
            } else {
                Singleton.getInstance().property.setLocalityCategoryId(null);
            }

            int posClass = spinner_select_class.getSelectedItemPosition();
            if (posClass > 0) {
                int classId = Singleton.getInstance().classes_list.get(posClass).getClassId();
                if (classId != 0)
                    Singleton.getInstance().property.setClassId(classId);
            } else {
                Singleton.getInstance().property.setClassId(null);
            }

            int posTenure = spinner_select_tenure_ownership.getSelectedItemPosition();
            if (posTenure > 0) {
                int tenureId = Singleton.getInstance().tenures_list.get(posTenure).getTenureId();
                if (tenureId != 0)
                    Singleton.getInstance().property.setTenureId(tenureId);
            } else {
                Singleton.getInstance().property.setTenureId(null);
            }

            int poslandmark = spinner_landapproval.getSelectedItemPosition();
            if (poslandmark > 0) {
                int landId = Singleton.getInstance().land_list.get(poslandmark).getTypeOfLandId();
                if (landId != 0)
                    Singleton.getInstance().property.setTypeOfLandId(landId);
            } else {
                Singleton.getInstance().property.setTypeOfLandId(null);
            }

            // spinner_qualityofconstructionSelectedItemPosition
            int spinner_qualityofconstructionSelectedItemPosition = spinner_qualityofconstruction.getSelectedItemPosition();
            if (spinner_qualityofconstructionSelectedItemPosition > 0) {
                int landId = Singleton.getInstance().qualityConstructions_list.get(spinner_qualityofconstructionSelectedItemPosition).getQualityConstructionId();
                if (landId != 0)
                    Singleton.getInstance().indProperty.setQualityConstructionId(landId);
            } else {
                Singleton.getInstance().indProperty.setQualityConstructionId(null);
            }

            int spinner_typeofbuildingSelectedItemPosition = spinner_typeofbuilding.getSelectedItemPosition();
            if (spinner_typeofbuildingSelectedItemPosition > 0) {
                int landId = Singleton.getInstance().buildings_list.get(spinner_typeofbuildingSelectedItemPosition).getTypeOfBuildingId();
                if (landId != 0)
                    Singleton.getInstance().indProperty.setTypeOfBuildingId(landId);
            } else {
                Singleton.getInstance().indProperty.setTypeOfBuildingId(null);
            }

            int spinner_maintenanceofbuildingSelectedItemPosition = spinner_maintenanceofbuilding.getSelectedItemPosition();
            if (spinner_maintenanceofbuildingSelectedItemPosition > 0) {
                int landId = Singleton.getInstance().maintenances_list.get(spinner_maintenanceofbuildingSelectedItemPosition).getMaintenanceOfBuildingId();
                if (landId != 0)
                    Singleton.getInstance().indProperty.setMaintenanceOfBuildingId(landId);
            } else {
                Singleton.getInstance().indProperty.setMaintenanceOfBuildingId(null);
            }

            int spinner_qualityoffittingsSelectedItemPosition = spinner_qualityoffittings.getSelectedItemPosition();
            if (spinner_qualityoffittingsSelectedItemPosition > 0) {
                int landId = Singleton.getInstance().fittingQualities_List.get(spinner_qualityoffittingsSelectedItemPosition).getFittingQualityId();
                if (landId != 0)
                    Singleton.getInstance().indProperty.setFittingQualityId(landId);
            } else {
                Singleton.getInstance().indProperty.setFittingQualityId(null);
            }

            int spinner_marketabilitySelectedItemPosition = spinner_marketability.getSelectedItemPosition();
            if (spinner_marketabilitySelectedItemPosition > 0) {
                int landId = Singleton.getInstance().marketablities_list.get(spinner_marketabilitySelectedItemPosition).getMarketabilityId();
                if (landId != 0)
                    Singleton.getInstance().indProperty.setMarketabilityId(landId);
            } else {
                Singleton.getInstance().indProperty.setMarketabilityId(null);
            }

            int spinner_nameofvendorSelectedItemPosition = spinner_nameofvendor.getSelectedItemPosition();
            if (spinner_nameofvendorSelectedItemPosition > 0) {
                int landId = Singleton.getInstance().nameofpersons_list.get(spinner_nameofvendorSelectedItemPosition).getNameofVendorId();
                if (landId != 0)
                    Singleton.getInstance().property.setNameofVendorId(landId);
            } else {
                Singleton.getInstance().property.setNameofVendorId(null);
            }

            // Repalced More Section - Drop Down
            int spinner_typeoflocality_posClass = spinner_typeoflocality.getSelectedItemPosition();
            if (spinner_typeoflocality_posClass > 0) {
                int classId = Singleton.getInstance().localities_list.get(spinner_typeoflocality_posClass).getTypeLocalityId();
                if (classId != 0)
                    Singleton.getInstance().property.setTypeLocalityId(classId);
            } else {
                Singleton.getInstance().property.setTypeLocalityId(null);
            }

            /*if (!property_type.equalsIgnoreCase("land")) {
                // More Section - Drop Down
                int spinner_typeoflocality_posClass = spinner_typeoflocality.getSelectedItemPosition();
                if (spinner_typeoflocality_posClass > 0) {
                    int classId = Singleton.getInstance().localities_list.get(spinner_typeoflocality_posClass).getTypeLocalityId();
                    if (classId != 0)
                        Singleton.getInstance().property.setTypeLocalityId(classId);
                } else {
                    Singleton.getInstance().property.setTypeLocalityId(null);
                }
            } else {
                // More Section - Drop Down
                int spinner_typeoflocality_for_land_posClass = spinner_typeoflocality_for_land.getSelectedItemPosition();
                if (spinner_typeoflocality_for_land_posClass > 0) {
                    int classId = Singleton.getInstance().localities_list.get(spinner_typeoflocality_for_land_posClass).getTypeLocalityId();
                    if (classId != 0)
                        Singleton.getInstance().property.setTypeLocalityId(classId);
                } else {
                    Singleton.getInstance().property.setTypeLocalityId(null);
                }
            }*/


            int spinner_bathflooring_posClass = spinner_bathflooring.getSelectedItemPosition();
            if (spinner_bathflooring_posClass > 0) {
                int classId = Singleton.getInstance().bath_list.get(spinner_bathflooring_posClass).getIntBathId();
                if (classId != 0)
                    Singleton.getInstance().indProperty.setIntBathId(classId);
            } else {
                Singleton.getInstance().indProperty.setIntBathId(null);
            }

            int spinner_amenitiesquality_posClass = spinner_amenitiesquality.getSelectedItemPosition();
            if (spinner_amenitiesquality_posClass > 0) {
                int classId = Singleton.getInstance().amenityQualities_list.get(spinner_amenitiesquality_posClass).getAmenityQualityId();
                if (classId != 0)
                    Singleton.getInstance().indProperty.setAmenityQualityId(classId);
            } else {
                Singleton.getInstance().indProperty.setAmenityQualityId(null);
            }

            int spinner_kitchentype_posClass = spinner_kitchentype.getSelectedItemPosition();
            if (spinner_kitchentype_posClass > 0) {
                int classId = Singleton.getInstance().kitchens_list.get(spinner_kitchentype_posClass).getIntKitchenTypeId();
                if (classId != 0)
                    Singleton.getInstance().indProperty.setIntKitchenType(classId);
            } else {
                Singleton.getInstance().indProperty.setIntKitchenType(null);
            }

            int spinner_kitchenshape_posClass = spinner_kitchenshape.getSelectedItemPosition();
            if (spinner_kitchenshape_posClass > 0) {
                int classId = Singleton.getInstance().kitchens_shape_list.get(spinner_kitchenshape_posClass).getIntKitchenId();
                if (classId != 0)
                    Singleton.getInstance().indProperty.setIntKitchenShape(classId);
            } else {
                Singleton.getInstance().indProperty.setIntKitchenShape(null);
            }

            /*int spinner_pavingaroundbuilding_posClass = spinner_pavingaroundbuilding.getSelectedItemPosition();
            if (spinner_pavingaroundbuilding_posClass > 0) {
                int classId = Singleton.getInstance().pavings_list.get(spinner_pavingaroundbuilding_posClass).getPavingAroundBuildingId();
                if (classId != 0)
                    Singleton.getInstance().indProperty.setPavingAroundBuildingId(classId);
            } else {
                Singleton.getInstance().indProperty.setPavingAroundBuildingId(null);
            }*/


            /**************
             * Document and Actual Boundary
             * *********************/
            DispDocumentBoundary();

            ab_east = editText_ab_east.getText().toString().trim();
            if (!general.isEmpty(ab_east)) {
                Singleton.getInstance().property.setBoundryEast(ab_east);
            } else {
                Singleton.getInstance().property.setBoundryEast("");
            }

            ab_west = editText_ab_west.getText().toString().trim();
            if (!general.isEmpty(ab_west)) {
                Singleton.getInstance().property.setBoundryWest(ab_west);
            } else {
                Singleton.getInstance().property.setBoundryWest("");
            }

            ab_north = editText_ab_north.getText().toString().trim();
            if (!general.isEmpty(ab_north)) {
                Singleton.getInstance().property.setBoundryNorth(ab_north);
            } else {
                Singleton.getInstance().property.setBoundryNorth("");
            }

            ab_south = editText_ab_south.getText().toString().trim();
            if (!general.isEmpty(ab_south)) {
                Singleton.getInstance().property.setBoundrySouth(ab_south);
            } else {
                Singleton.getInstance().property.setBoundrySouth("");
            }

            /**************
             * Document and Actual Dimension
             * *********************/
            DispDocumentDimension();


            ad_east = editText_ad_east.getText().toString().trim();
            if (!general.isEmpty(ad_east)) {
                Singleton.getInstance().property.setEastMeasure(ad_east);
            } else {
                Singleton.getInstance().property.setEastMeasure("");
            }
            ad_west = editText_ad_west.getText().toString().trim();
            if (!general.isEmpty(ad_west)) {
                Singleton.getInstance().property.setWestMeasure(ad_west);
            } else {
                Singleton.getInstance().property.setWestMeasure("");
            }
            ad_north = editText_ad_north.getText().toString().trim();
            if (!general.isEmpty(ad_north)) {
                Singleton.getInstance().property.setNorthMeasure(ad_north);
            } else {
                Singleton.getInstance().property.setNorthMeasure("");
            }
            ad_south = editText_ad_south.getText().toString().trim();
            if (!general.isEmpty(ad_south)) {
                Singleton.getInstance().property.setSouthMeasure(ad_south);
            } else {
                Singleton.getInstance().property.setSouthMeasure("");
            }

            /**************
             * Document and Actual Setback
             * *********************/
            DispDocumentSetback();

            as_front = editText_as_front.getText().toString().trim();
            if (!general.isEmpty(as_front)) {
                Singleton.getInstance().property.setSetBackFront(as_front);
            } else {
                Singleton.getInstance().property.setSetBackFront("");
            }
            as_left = editText_as_left.getText().toString().trim();
            if (!general.isEmpty(as_left)) {
                Singleton.getInstance().property.setSetBackLeft(as_left);
            } else {
                Singleton.getInstance().property.setSetBackLeft("");
            }
            as_rear = editText_as_rear.getText().toString().trim();
            if (!general.isEmpty(as_rear)) {
                Singleton.getInstance().property.setSetBackRear(as_rear);
            } else {
                Singleton.getInstance().property.setSetBackRear("");
            }
            as_right = editText_as_right.getText().toString().trim();
            if (!general.isEmpty(as_right)) {
                Singleton.getInstance().property.setSetBackRight(as_right);
            } else {
                Singleton.getInstance().property.setSetBackRight("");
            }

            /**************
             * CheckBox values view
             * *********************/
            boolean check_addr = checkbox_same_address.isChecked();
            boolean check_boundary = checkbox_same_as_doc_boundary.isChecked();
            boolean check_dimension = checkbox_same_as_doc_dimension.isChecked();
            boolean check_setback = checkbox_same_as_doc_setback.isChecked();
            boolean check_plodemarcate = checkbox_plot_demarcated.isChecked();
            boolean check_demolish = checkbox_isproperty_demolish.isChecked();
            boolean check_municipality = checkbox_property_within_muni.isChecked();
            Singleton.getInstance().property.setSameAsDocumentAddress(check_addr);
            Singleton.getInstance().property.setSameAsDocumentBoundary(check_boundary);
            Singleton.getInstance().property.setSameAsDocumentDimension(check_dimension);
            Singleton.getInstance().property.setSameAsDocumentSetBack(check_setback);
            Singleton.getInstance().property.setPlotDemarcatedAtSite(check_plodemarcate);
            Singleton.getInstance().property.setIsPropertyInDemolitionList(check_demolish);
            Singleton.getInstance().property.setIsWithinMunicipalArea(check_municipality);
            Singleton.getInstance().property.setLatLongDetails(Singleton.getInstance().latlng_details);


            /* Convert latlong to DMS *//*
            String latlng = Singleton.getInstance().latlng_details;
            String latlong_InDegree = "";
            if (!general.isEmpty(latlng)) {
                if (latlng.contains(":")) {
                    String[] split_latlng = latlng.split(":");
                    if (split_latlng.length > 0) {
                        if ((!general.isEmpty(split_latlng[0])) && (!general.isEmpty(split_latlng[1]))) {
                            double dob_split_lat = Double.parseDouble(split_latlng[0]);
                            double dob_split_lng = Double.parseDouble(split_latlng[1]);
                            latlong_InDegree = general.getFormattedLocationInDegree(dob_split_lat, dob_split_lng);
                            Log.e("latlong_InDegree", "kakode_is: " +  latlong_InDegree);
                            Singleton.getInstance().property.setLatLongDegreeCordinates(latlong_InDegree);
                        }
                    }
                }
            }*/


        }
    }

    private void DispDocumentBoundary() {
        /**************
         * Document and Actual Boundary
         * *********************/
        db_east = editText_db_east.getText().toString().trim();
        if (!general.isEmpty(db_east)) {
            Singleton.getInstance().property.setDocBoundryEast(db_east);
        } else {
            Singleton.getInstance().property.setDocBoundryEast("");
        }

        db_west = editText_db_west.getText().toString().trim();
        if (!general.isEmpty(db_west)) {
            Singleton.getInstance().property.setDocBoundryWest(db_west);
        } else {
            Singleton.getInstance().property.setDocBoundryWest("");
        }

        db_north = editText_db_north.getText().toString().trim();
        if (!general.isEmpty(db_north)) {
            Singleton.getInstance().property.setDocBoundryNorth(db_north);
        } else {
            Singleton.getInstance().property.setDocBoundryNorth("");
        }

        db_south = editText_db_south.getText().toString().trim();
        if (!general.isEmpty(db_south)) {
            Singleton.getInstance().property.setDocBoundrySouth(db_south);
        } else {
            Singleton.getInstance().property.setDocBoundrySouth("");
        }
    }

    private void DispDocumentDimension() {
        /**************
         * Document and Actual Dimension
         * *********************/
        dd_east = editText_dd_east.getText().toString().trim();
        if (!general.isEmpty(dd_east)) {
            Singleton.getInstance().property.setDocEastMeasure(dd_east);
        } else {
            Singleton.getInstance().property.setDocEastMeasure("");
        }
        dd_west = editText_dd_west.getText().toString().trim();
        if (!general.isEmpty(dd_west)) {
            Singleton.getInstance().property.setDocWestMeasure(dd_west);
        } else {
            Singleton.getInstance().property.setDocWestMeasure("");
        }
        dd_north = editText_dd_north.getText().toString().trim();
        if (!general.isEmpty(dd_north)) {
            Singleton.getInstance().property.setDocNorthMeasure(dd_north);
        } else {
            Singleton.getInstance().property.setDocNorthMeasure("");
        }
        dd_south = editText_dd_south.getText().toString().trim();
        if (!general.isEmpty(dd_south)) {
            Singleton.getInstance().property.setDocSouthMeasure(dd_south);
        } else {
            Singleton.getInstance().property.setDocSouthMeasure("");
        }
    }

    private void DispDocumentSetback() {
        /**************
         * Document and Actual Setback
         * *********************/
        ds_front = editText_ds_front.getText().toString().trim();
        if (!general.isEmpty(ds_front)) {
            Singleton.getInstance().property.setDocSetBackFront(ds_front);
        } else {
            Singleton.getInstance().property.setDocSetBackFront("");
        }
        ds_left = editText_ds_left.getText().toString().trim();
        if (!general.isEmpty(ds_left)) {
            Singleton.getInstance().property.setDocSetBackLeft(ds_left);
        } else {
            Singleton.getInstance().property.setDocSetBackLeft("");
        }
        ds_rear = editText_ds_rear.getText().toString().trim();
        if (!general.isEmpty(ds_rear)) {
            Singleton.getInstance().property.setDocSetBackRear(ds_rear);
        } else {
            Singleton.getInstance().property.setDocSetBackRear("");
        }
        ds_right = editText_ds_right.getText().toString().trim();
        if (!general.isEmpty(ds_right)) {
            Singleton.getInstance().property.setDocSetBackRight(ds_right);
        } else {
            Singleton.getInstance().property.setDocSetBackRight("");
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
        switch (compoundButton.getId()) {
            case R.id.checkbox_same_address:
                if (checkbox_same_address.isPressed()) {
                    if (ischecked) {
                        address_per_doc = editText_addr_perdoc.getText().toString().trim();
                        editText_addr_site.setText(getResources().getString(R.string.sameabove));
                        editText_addr_site.setError(null);
                    } else {
                        editText_addr_site.setText("");
                    }
                }
                break;

            case R.id.checkbox_same_as_doc_boundary:
                if (checkbox_same_as_doc_boundary.isPressed()) {
                    if (ischecked) {
                        DispDocumentBoundary();
                        editText_ab_east.setText(db_east);
                        editText_ab_west.setText(db_west);
                        editText_ab_north.setText(db_north);
                        editText_ab_south.setText(db_south);
                    } else {
                    /*editText_ab_east.setText("");
                    editText_ab_west.setText("");
                    editText_ab_north.setText("");
                    editText_ab_south.setText("");*/
                    }
                }
                break;

            case R.id.checkbox_same_as_doc_dimension:
                if (checkbox_same_as_doc_dimension.isPressed()) {
                    if (ischecked) {
                        DispDocumentDimension();
                        editText_ad_east.setText(dd_east);
                        editText_ad_west.setText(dd_west);
                        editText_ad_north.setText(dd_north);
                        editText_ad_south.setText(dd_south);
                    } else {
                   /* editText_ad_east.setText("");
                    editText_ad_west.setText("");
                    editText_ad_north.setText("");
                    editText_ad_south.setText("");*/
                    }
                }
                break;

            case R.id.checkbox_same_as_doc_setback:
                if (checkbox_same_as_doc_setback.isPressed()) {
                    if (ischecked) {
                        DispDocumentSetback();
                        editText_as_front.setText(ds_front);
                        editText_as_left.setText(ds_left);
                        editText_as_right.setText(ds_right);
                        editText_as_rear.setText(ds_rear);
                    } else {
                   /* editText_as_front.setText("");
                    editText_as_left.setText("");
                    editText_as_right.setText("");
                    editText_as_rear.setText("");*/
                    }
                }
                break;
        }


    }

    private void getBuildingFragmentDisplay() {
        fragment_building.setBuildingData(new OthersFormListener() {
            @Override
            public void getBuildingData(IndProperty indProperty) {
                Singleton.getInstance().indProperty = indProperty;
            }
        }, fragment_building.getView());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textview_save_top:
                save_type = "save";
                SaveFormDetails();
                break;
            case R.id.textview_save_bottom:
                save_type = "save";
                SaveFormDetails();
                break;
            case R.id.textview_save_top_dashboard:
                save_type = "savego";
                SaveFormDetails();
                break;
            case R.id.textview_save_bottom_dashboard:
                save_type = "savego";
                SaveFormDetails();
                break;

        }
    }

    private void SaveFormDetails() {

        // The case is filled from Mobile Tab
        Singleton.getInstance().property.setPodiums("1");

        // check offline or not
        is_offline = SettingsUtils.getInstance().getValue(SettingsUtils.is_offline, false);
        if (!is_offline) {
            // online
            if (Connectivity.isConnected(getActivity())) {
                save_function();
            } else {
                String curent_statusID = Singleton.getInstance().curent_statusID;
                Log.e("curent_statusID", "curent_statusID: " + curent_statusID);
                if (!general.isEmpty(curent_statusID)) {
                    if (curent_statusID.equalsIgnoreCase("13")) {
                        // Start Inspection - Data will save in LOCALDB
                        // TODO - Incase, If thr is no internet on start Inspection - move the case to offline mode
                        internet_check_box("start_inspec");
                    } else {
                        // Internet Required - Edit case
                        internet_check_box("edit_inspec");
                        /*general.hideloading();
                        Connectivity.showNoConnectionDialog(getActivity());*/
                    }
                }
            }
        } else {
            // offline
            save_function();
        }

    }

    public void save_function() {
        if (property_type.equalsIgnoreCase("building")) {
            function_save();
        } else if (property_type.equalsIgnoreCase("flat") || property_type.equalsIgnoreCase("penthouse")) {
            function_save_penthouse();
        } else if (property_type.equalsIgnoreCase("land")) {
            function_save_land();
        }
    }

    public void internet_check_box(final String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Network Information");
        builder.setMessage("Please check your Internet connection");


        if (enable_offline_button) {
            // Show offline Button
            builder.setNeutralButton("Save offline", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (str.equalsIgnoreCase("start_inspec")) {
                        // TODO - Start_inspec - Add the offline case to the modal
                        // set as offline
                        SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, true);
                        is_offline = SettingsUtils.getInstance().getValue(SettingsUtils.is_offline, false);

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
                            // Check the getPhoto_propertyid is zero > we want to add the dummy images
                            if (appDatabase.interfaceGetPhotoQuery().getPhoto_propertyid(PropertyId_is).size() == 0) {
                                // Room Delete - PhotoCase
                                appDatabase.interfaceGetPhotoQuery().deleteRow(PropertyId_is);
                                // Room Add - PhotoCase
                                GetPhoto dummy_cameraImage = new GetPhoto();
                                dummy_cameraImage.setDefaultimage(true);
                                dummy_cameraImage.setPropertyId(PropertyId_is);
                                appDatabase.interfaceGetPhotoQuery().insertAll(dummy_cameraImage);
                                // Room Add - PhotoCase
                                GetPhoto dummy_galleryImage = new GetPhoto();
                                dummy_galleryImage.setDefaultimage(true);
                                dummy_galleryImage.setPropertyId(PropertyId_is);
                                appDatabase.interfaceGetPhotoQuery().insertAll(dummy_galleryImage);
                            }

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

                            save_function();
                        }
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
                            // Check the getPhoto_propertyid is zero > we want to add the dummy images
                            if (appDatabase.interfaceGetPhotoQuery().getPhoto_propertyid(PropertyId_is).size() == 0) {
                                // Room Delete - PhotoCase
                                appDatabase.interfaceGetPhotoQuery().deleteRow(PropertyId_is);
                                // Room Add - PhotoCase
                                GetPhoto dummy_cameraImage = new GetPhoto();
                                dummy_cameraImage.setDefaultimage(true);
                                dummy_cameraImage.setPropertyId(PropertyId_is);
                                appDatabase.interfaceGetPhotoQuery().insertAll(dummy_cameraImage);
                                // Room Add - PhotoCase
                                GetPhoto dummy_galleryImage = new GetPhoto();
                                dummy_galleryImage.setDefaultimage(true);
                                dummy_galleryImage.setPropertyId(PropertyId_is);
                                appDatabase.interfaceGetPhotoQuery().insertAll(dummy_galleryImage);
                            }

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

                            // hit_photo_api > false
                            Singleton.getInstance().hit_photo_api = true;

                            save_function();
                        }
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
                mContext.startActivity(i);
                dialog.dismiss();
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void SaveAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(getResources().getString(R.string.title));
        builder.setMessage(getResources().getString(R.string.save_message));

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                save_type = "save";
                function_save();
                dialog.cancel();
            }
        });
        builder.setNeutralButton("Save & Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                save_type = "savego";
                function_save();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                save_type = "";
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean valiadation_lat_pic() {
        isvalid = true;
        if (PhotoLatLong_ka.latvalue != null) {
            str_latvalue = PhotoLatLong_ka.latvalue.getText().toString();
            str_longvalue = PhotoLatLong_ka.longvalue.getText().toString();
            // Lat
            if (!general.isEmpty(str_latvalue)) {
                PhotoLatLong_ka.latvalue.setError(null);
            } else {
                isvalid = false;
                PhotoLatLong_ka.latvalue.requestFocus();
                PhotoLatLong_ka.latvalue.setError(getResources().getString(R.string.error_lat));
            }
            // Long
            if (!general.isEmpty(str_longvalue)) {
                PhotoLatLong_ka.longvalue.setError(null);
            } else {
                isvalid = false;
                PhotoLatLong_ka.longvalue.requestFocus();
                PhotoLatLong_ka.longvalue.setError(getResources().getString(R.string.error_long));
            }

            if (PhotoLatLong_ka.GetPhoto_list_response.size() <= 2) {
                // Picture less than one
                isvalid = false;
                general.CustomToast(getResources().getString(R.string.error_oneimage));
            }

        }
        return isvalid;
    }

    private boolean valiadation_address_site() {
        isvalid = true;
        // Address per docs
        String address_per_doc = editText_addr_perdoc.getText().toString();
        if (!general.isEmpty(address_per_doc)) {
            editText_addr_perdoc.setError(null);
        } else {
            editText_addr_perdoc.requestFocus();
            editText_addr_perdoc.setError(getResources().getString(R.string.err_addperdoc));
            isvalid = false;
        }
        // Address site
        String addr_site = editText_addr_site.getText().toString();
        if (!general.isEmpty(addr_site)) {
            editText_addr_site.setError(null);
        } else {
            editText_addr_site.requestFocus();
            editText_addr_site.setError(getResources().getString(R.string.err_addrsite));
            isvalid = false;
        }
        return isvalid;
    }

    private void set_mandatory_address() {
        String address_per_doc = editText_addr_perdoc.getText().toString();
        if (!general.isEmpty(address_per_doc)) {
            editText_addr_perdoc.setError(null);
        } else {
            editText_addr_perdoc.requestFocus();
            editText_addr_perdoc.setError(getResources().getString(R.string.err_addperdoc));
        }
        // Address site
        String addr_site = editText_addr_site.getText().toString();
        if (!general.isEmpty(addr_site)) {
            editText_addr_site.setError(null);
        } else {
            editText_addr_site.requestFocus();
            editText_addr_site.setError(getResources().getString(R.string.err_addrsite));

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    scroll_view.scrollTo(0, 0);
                    /*****Scroll to top of position******/
                    int nY_Pos = editText_addr_site.getTop(); // getBottom(); X_pos  getLeft(); getRight();
                    scroll_view.scrollTo(0, nY_Pos);

                }
            }, 2000);
        }
    }

    // Broker number - Valiation Mobile number
    private boolean valiadation_broker_mobilenumber() {
        isvalid = true;
        String str_brokernumber = editText_brokernumber.getText().toString();
        if (!general.isEmpty(str_brokernumber)) {
            int len = str_brokernumber.length();
            if (len != 10) {
                editText_brokernumber.requestFocus();
                editText_brokernumber.setError(getResources().getString(R.string.error_mobile_number));
                isvalid = false;
            } else {
                editText_brokernumber.setError(null);
            }
        } else {
            editText_brokernumber.setError(null);
        }
        return isvalid;
    }

    // save Function //
    public void function_save() {
        if (OtherDetails_ka.my_focuslayout != null) {
            OtherDetails_ka.my_focuslayout.requestFocus();
        }
        //boolean checksAll = fragment_building.checkValidationonSave();
        boolean checksAll = fragment_building.checkValidationonSave_nonmandatory();
        //if (valiadation_lat_pic()) {
        // Lat and Long validation
        //if (valiadation_address_site()) {
        // Address and site validation
        if (checksAll) {

            // Address, compound_permissiblearea, as_per_measurement, Nooffloors, measurementland, measurementconstruction validation
            if (FragmentValuationBuilding_ka.valid_realizationValue()) {
                // Realizable  validation
                if (FragmentValuationBuilding_ka.valid_distressValue()) {
                    // Distress  validation
                    if (FragmentValuationBuilding_ka.valid_carpetValue()) {
                        // Carpet  validation

                        if (valiadation_broker_mobilenumber()) {
                            // Broker number - Valiation Mobile number
                            //if (Connectivity.isConnected(getActivity())) {
                            // Invisible the save button
                            invisible_save_button();
                            general.showloading(getActivity());
                            // Setting values and sending to data
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    str_latvalue = PhotoLatLong_ka.latvalue.getText().toString();
                                    str_longvalue = PhotoLatLong_ka.longvalue.getText().toString();
                                    Singleton.getInstance().latlng_details = str_latvalue + ":" + str_longvalue;
                                    Singleton.getInstance().aCase.setStatus(Integer.valueOf(getResources().getString(R.string.edit_inspection)));
                                    //submitAddressForm();
                                    getProximityListData();
                                    getIndPropertyFloorListData();
                                    getAddressDetailsInputData();
                                    getBuildingFragmentDisplay();
                                    getMoreRemarks();
                                    getBrokerdetails();

                                    /* valuation - Building */
                                    FragmentValuationBuilding_ka fragmentValuationBuilding = new FragmentValuationBuilding_ka();
                                    fragmentValuationBuilding.save_landval();
                                    FragmentValuationBuilding_ka.saveIndValuationFloorsCalculation();

                                    // Visible the save button
                                    visible_save_button();

                                    if (Singleton.getInstance().hit_photo_api) {
                                        // True - call photo function first
                                        call_photo_function();
                                    } else {
                                        // False
                                        // TODO - Final Api
                                        InitiateSaveCaseInspectionTask();
                                    }


                                }
                            }, 1000);
                                    /*} else {
                                        Connectivity.showNoConnectionDialog(getActivity());
                                        general.hideloading();
                                    }*/
                            // Broker number - Valiation Mobile number
                        }


                        // Carpet  validation
                    }
                    // Distress  validation
                }
                // Realizable  validation
            }


            // Address, compound_permissiblearea, as_per_measurement, Nooffloors, measurementland, measurementconstruction validation
            //}
            // Address and site validation
            //}
        }/* else {
            PhotoLatLngTab.pager.setCurrentItem(0);
        }*/
    }

    // save Function //
    public void function_save_penthouse() {
        if (OtherDetails_ka.my_focuslayout != null) {
            OtherDetails_ka.my_focuslayout.requestFocus();
        }
        //boolean checksAll = fragment_building.checkValidationonSave();
        // if (valiadation_lat_pic()) {
        // Lat and Long validation
        //  if (valiadation_address_site()) {
        // Address and site validation
        //  if (validatePenthouseFlat()) {
        // Flatsituatedinfloor,stageSpinner,general_comp
        //if (validatePenthouseFlat_terrace()) {
        // Terrace_area,Terrace_rate
        if (valiadation_broker_mobilenumber()) {
            // Broker number - Valiation Mobile number
            //if (Connectivity.isConnected(getActivity())) {
            // Invisible the save button
            invisible_save_button();
            general.showloading(getActivity());
            // Setting values and sending to data
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    str_latvalue = PhotoLatLong_ka.latvalue.getText().toString();
                    str_longvalue = PhotoLatLong_ka.longvalue.getText().toString();
                    Singleton.getInstance().latlng_details = str_latvalue + ":" + str_longvalue;
                    Singleton.getInstance().aCase.setStatus(Integer.valueOf(getResources().getString(R.string.edit_inspection)));
                    //submitAddressForm();
                    getProximityListData();
                    getAddressDetailsInputData();
                    getMoreRemarks();
                    getBrokerdetails();

                    /******Penthouse or flat values*******/
                    FragmentFlat_ka fragmentFlat = new FragmentFlat_ka();
                    fragmentFlat.PostPentHouseValues();

                    FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
                    fragmentValuationPenthouse.setIndPropertyValuationforPentHouseFlat();

                    // Visible the save button
                    visible_save_button();

                    if (Singleton.getInstance().hit_photo_api) {
                        // True - call photo function first
                        call_photo_function();
                    } else {
                        // False
                        // TODO - Final Api
                        InitiateSaveCaseInspectionTask();
                    }

                }
            }, 1000);
                        /*} else {
                            Connectivity.showNoConnectionDialog(getActivity());
                            general.hideloading();
                        }*/
            // Broker number - Valiation Mobile number
            // }
            // Terrace_area,Terrace_rate
        }
        // Address, compound_permissiblearea, as_per_measurement, Nooffloors, measurementland, measurementconstruction validation
        //  }
        // Address and site validation
        //}
       /* } else {
            PhotoLatLngTab.pager.setCurrentItem(0);
        }*/
    }

    // save Function //
    public void function_save_land() {
        if (OtherDetails_ka.my_focuslayout != null) {
            OtherDetails_ka.my_focuslayout.requestFocus();
        }
        //boolean checksAll = fragment_building.checkValidationonSave();
        // if (valiadation_lat_pic()) {
        // Lat and Long validation
        // if (valiadation_address_site()) {
        // Address and site validation
        //  if (validateLand()) {
        // Flatsituatedinfloor,stageSpinner,general_comp
        //if (validatePenthouseFlat_terrace()) {
        // Terrace_area,Terrace_rate
        if (valiadation_broker_mobilenumber()) {
            // Broker number - Valiation Mobile number
            //if (Connectivity.isConnected(getActivity())) {
            // Invisible the save button
            invisible_save_button();
            general.showloading(getActivity());
            // Setting values and sending to data
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    str_latvalue = PhotoLatLong_ka.latvalue.getText().toString();
                    str_longvalue = PhotoLatLong_ka.longvalue.getText().toString();
                    Singleton.getInstance().latlng_details = str_latvalue + ":" + str_longvalue;
                    Singleton.getInstance().aCase.setStatus(Integer.valueOf(getResources().getString(R.string.edit_inspection)));
                    //submitAddressForm();
                    getProximityListData();
                    getAddressDetailsInputData();
                    getMoreRemarks();
                    getBrokerdetails();

                    FragmentLand_ka fragmentLand = new FragmentLand_ka();
                    fragmentLand.PostLandValues();

                    /* valuation - Building */
                    FragmentValuationLand_ka fragmentValuationLand = new FragmentValuationLand_ka();
                    fragmentValuationLand.save_landval();

                    // Visible the save button
                    visible_save_button();

                    if (Singleton.getInstance().hit_photo_api) {
                        // True - call photo function first
                        call_photo_function();
                    } else {
                        // False
                        // TODO - Final Api
                        InitiateSaveCaseInspectionTask();
                    }

                }
            }, 1000);
                        /*} else {
                            Connectivity.showNoConnectionDialog(getActivity());
                            general.hideloading();
                        }*/
            // Broker number - Valiation Mobile number
            // }
            // Terrace_area,Terrace_rate
        }
        // Address, compound_permissiblearea, as_per_measurement, Nooffloors, measurementland, measurementconstruction validation
        //  }
        // Address and site validation
        // }
        /*} else {
            PhotoLatLngTab.pager.setCurrentItem(0);
        }*/
    }


    public boolean validatePenthouseFlat() {

        boolean checkvalidation = true;

        String flatsituated = FragmentFlat_ka.editText_flatsituatedinfloor.getText().toString();
        String stagespinner = FragmentFlat_ka.stageSpinner.getSelectedItem().toString();
        String percentagecomp = FragmentFlat_ka.edittext_general_comp.getText().toString();

        if (!general.isEmpty(flatsituated)) {
            FragmentFlat_ka.editText_flatsituatedinfloor.setError(null);
        } else {
            FragmentFlat_ka.editText_flatsituatedinfloor.requestFocus();
            FragmentFlat_ka.editText_flatsituatedinfloor.setError(getActivity().getResources().getString(R.string.err_flatsituated));
            checkvalidation = false;
        }

        if (stagespinner.equalsIgnoreCase("Select")) {
            ((TextView) FragmentFlat_ka.stageSpinner.getSelectedView()).setError(getActivity().getResources().getString(R.string.err_construction_stage));
            checkvalidation = false;
            FragmentFlat_ka.edittext_general_comp.requestFocus();
        }

        if (!general.isEmpty(percentagecomp)) {
            FragmentFlat_ka.edittext_general_comp.setError(null);
        } else {
            FragmentFlat_ka.edittext_general_comp.requestFocus();
            FragmentFlat_ka.edittext_general_comp.setError(getActivity().getResources().getString(R.string.err_percentage_completion));
            checkvalidation = false;
        }

        return checkvalidation;
    }

    public boolean validateLand() {

        boolean checkvalidation = true;

        String flatsituated = FragmentLand_ka.editText_compound_permissiblearea_land.getText().toString();
        String stagespinner = FragmentLand_ka.spinner_measurementland.getSelectedItem().toString();
        String percentagecomp = FragmentLand_ka.editText_land_measurement_land.getText().toString();

        if (!general.isEmpty(flatsituated)) {
            FragmentLand_ka.editText_compound_permissiblearea_land.setError(null);
        } else {
            FragmentLand_ka.editText_compound_permissiblearea_land.requestFocus();
            FragmentLand_ka.editText_compound_permissiblearea_land.setError(getActivity().getResources().getString(R.string.error_land_comd));
            checkvalidation = false;
        }

        if (stagespinner.equalsIgnoreCase("Select")) {
            ((TextView) FragmentLand_ka.spinner_measurementland.getSelectedView()).setError(getActivity().getResources().getString(R.string.error_land_mease_unit));
            checkvalidation = false;
            FragmentLand_ka.editText_land_measurement_land.requestFocus();
        }

        if (!general.isEmpty(percentagecomp)) {
            FragmentLand_ka.editText_land_measurement_land.setError(null);
        } else {
            FragmentLand_ka.editText_land_measurement_land.requestFocus();
            FragmentLand_ka.editText_land_measurement_land.setError(getActivity().getResources().getString(R.string.error_land_mease));
            checkvalidation = false;
        }

        return checkvalidation;
    }

    public boolean validatePenthouseFlat_terrace() {

        boolean checkvalidation = true;

        if (property_type.equalsIgnoreCase("penthouse")) {

            String terracearea = FragmentValuationPenthouse_ka.edittext_terrace_area.getText().toString();
            if (!general.isEmpty(terracearea)) {
                FragmentValuationPenthouse_ka.edittext_terrace_area.setError(null);
            } else {
                FragmentValuationPenthouse_ka.edittext_terrace_area.requestFocus();
                FragmentValuationPenthouse_ka.edittext_terrace_area.setError(getActivity().getResources().getString(R.string.terracearea));
                checkvalidation = false;
            }

            String terracerate = FragmentValuationPenthouse_ka.edittext_terrace_rate.getText().toString();
            if (!general.isEmpty(terracerate)) {
                FragmentValuationPenthouse_ka.edittext_terrace_rate.setError(null);
            } else {
                FragmentValuationPenthouse_ka.edittext_terrace_rate.requestFocus();
                FragmentValuationPenthouse_ka.edittext_terrace_rate.setError(getActivity().getResources().getString(R.string.terracerate));
                checkvalidation = false;
            }

        }

        return checkvalidation;
    }


    private void invisible_save_button() {
        textview_save_top.setVisibility(View.INVISIBLE);
        textview_save_top_dashboard.setVisibility(View.INVISIBLE);
        textview_save_bottom.setVisibility(View.INVISIBLE);
        textview_save_bottom_dashboard.setVisibility(View.INVISIBLE);
    }

    private void visible_save_button() {
        textview_save_top.setVisibility(View.VISIBLE);
        textview_save_top_dashboard.setVisibility(View.VISIBLE);
        textview_save_bottom.setVisibility(View.VISIBLE);
        textview_save_bottom_dashboard.setVisibility(View.VISIBLE);
    }

    private void InitiateSaveCaseInspectionTask() {

        is_offline = SettingsUtils.getInstance().getValue(SettingsUtils.is_offline, false);
        if (!is_offline) {
            //online
            if (Connectivity.isConnected(getActivity())) {

                LinkedTreeMap<String, Object> mainOb = new LinkedTreeMap<>();
                mainOb.put("Case", Singleton.getInstance().aCase);
                mainOb.put("Property", Singleton.getInstance().property);
                mainOb.put("IndProperty", Singleton.getInstance().indProperty);
                mainOb.put("IndPropertyValuation", Singleton.getInstance().indPropertyValuation);
                mainOb.put("IndPropertyFloors", Singleton.getInstance().indPropertyFloors);
                mainOb.put("IndPropertyFloorsValuation", Singleton.getInstance().indPropertyFloorsValuations);
                mainOb.put("Proximity", Singleton.getInstance().proximities);
                LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
                map.put("is_sync", false);
                mainOb.put("edit_synk", map);


                String url = general.ApiBaseUrl() + SettingsUtils.SaveCaseInspection;
                // String url = SettingsUtils.BASE_URL + SettingsUtils.SaveCaseInspection;
                JsonRequestData requestData = new JsonRequestData();
                requestData.setUrl(url);
                requestData.setMainJson(new Gson().toJson(mainOb));
                requestData.setRequestBody(RequestParam.SaveCaseInspectionRequestParams(requestData));

                WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.POST);
                webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                    @Override
                    public void onTaskComplete(JsonRequestData requestData) {
                        parseSaveCaseInspectionResponse(requestData.getResponse());
                    }
                });
                webserviceTask.execute();
            } else {
                Connectivity.showNoConnectionDialog(getActivity());
                general.hideloading();
            }
        } else {
            //offline
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

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

                    // Property - Room Delete
                    appDatabase.interfacePropertyQuery().deleteRow(caseid_int);
                    // Property - Room Add
                    Singleton.getInstance().property.setCaseId(caseid_int);
                    appDatabase.interfacePropertyQuery().insertAll(Singleton.getInstance().property);

                    // IndProperty - Room Delete
                    appDatabase.interfaceIndpropertyQuery().deleteRow(caseid_int);
                    // IndProperty - Room Add
                    Singleton.getInstance().indProperty.setCaseId(caseid_int);
                    appDatabase.interfaceIndpropertyQuery().insertAll(Singleton.getInstance().indProperty);

                    // IndPropertyValuation - Room Delete
                    appDatabase.interfaceIndPropertyValuationQuery().deleteRow(caseid_int);
                    // IndPropertyValuation - Room Add
                    Singleton.getInstance().indPropertyValuation.setCaseId(caseid_int);
                    appDatabase.interfaceIndPropertyValuationQuery().insertAll(Singleton.getInstance().indPropertyValuation);

                    // IndPropertyFloor - Room Delete
                    appDatabase.interfaceIndPropertyFloorsQuery().deleteRow(caseid_int);
                    // IndPropertyFloor - Room Add
                    if (Singleton.getInstance().indPropertyFloors.size() > 0) {
                        for (int x = 0; x < Singleton.getInstance().indPropertyFloors.size(); x++) {
                            appDatabase.interfaceIndPropertyFloorsQuery().insertAll(Singleton.getInstance().indPropertyFloors.get(x));
                        }
                    }

                    // IndPropertyFloorsValuation - Room Delete
                    appDatabase.interfaceIndPropertyFloorsValuationQuery().deleteRow(caseid_int);
                    // IndPropertyFloorsValuation - Room Add
                    if (Singleton.getInstance().indPropertyFloorsValuations.size() > 0) {
                        for (int x = 0; x < Singleton.getInstance().indPropertyFloorsValuations.size(); x++) {
                            appDatabase.interfaceIndPropertyFloorsValuationQuery().insertAll(Singleton.getInstance().indPropertyFloorsValuations.get(x));
                        }
                    }

                    // IndPropertyFloorsValuation - Room Delete
                    appDatabase.interfaceProximityQuery().deleteRow(caseid_int);
                    // IndPropertyFloorsValuation - Room Add
                    if (Singleton.getInstance().proximities.size() > 0) {
                        for (int x = 0; x < Singleton.getInstance().proximities.size(); x++) {
                            /*if (x > 1) {
                                // TODO - set ID as 0 from Thrid row
                            }*/
                            Singleton.getInstance().proximities.get(x).setId(0);
                            appDatabase.interfaceProximityQuery().insertAll(Singleton.getInstance().proximities.get(x));
                        }
                    }

                    // Room Delete
                    appDatabase.interfaceGetPhotoMeasurmentQuery().deleteRow(PropertyId_is);
                    if (Singleton.getInstance().GetImage_list_flat.size() > 0) {
                        GetPhoto_measurment getPhoto = new GetPhoto_measurment();
                        getPhoto.setId(Singleton.getInstance().GetImage_list_flat.get(0).getId());
                        getPhoto.setLogo(Singleton.getInstance().GetImage_list_flat.get(0).getLogo());
                        getPhoto.setTitle(Singleton.getInstance().GetImage_list_flat.get(0).getTitle());
                        getPhoto.setPropertyId(Singleton.getInstance().GetImage_list_flat.get(0).getPropertyId());
                        // Check the Base64Image should be less than 19,80,000 ...
                        if (Singleton.getInstance().GetImage_list_flat.get(0).getLogo().length() < 1980000) {
                            // Room Add
                            appDatabase.interfaceGetPhotoMeasurmentQuery().insertAll(getPhoto);
                        }
                    }

                    // update the offline caseID
                    UpdateOfflineStatusEditcase("" + caseid_int, "2");


                }
            }, 0);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    general.CustomToast(mContext.getResources().getString(R.string.case_save_offline));
                    general.hideloading();
                    if (save_type.equalsIgnoreCase("savego")) {
                        Singleton.getInstance().openCaseList.clear();
                        Singleton.getInstance().closeCaseList.clear();
                        Singleton.getInstance().call_offline_tab = "call_offline_tab";
                        startActivity(new Intent(getContext(), HomeActivity.class));
                    }
                }
            }, 1000);


        }
    }

    // Todo update Start to Edit Inspecction
    private void UpdateOfflineStatusEditcase(String case_id, String updateCaseStatus) {
        if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
            appDatabase.interfaceOfflineDataModelQuery().updateOfflineCaseStatus(updateCaseStatus, case_id);
            // update the case for casemodal
            appDatabase.interfaceCaseQuery().updateCaseStatus(updateCaseStatus, case_id);
        }
    }

    private void parseSaveCaseInspectionResponse(String response) {

        // Re - Initial the curent_statusID as 0 for move to case to online
        Singleton.getInstance().curent_statusID = "0";

        DataResponse dataResponse = ResponseParser.parseSaveCaseInspectionResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
        }

        if (result != null) {
            if (result.equals("1")) {
                general.CustomToast(info);
                //floorgeneralfields = true;
                if (Singleton.getInstance().is_new_floor_created) {
                    /* Start Inspection - Update the case ID */
                    String case_id = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
                    UpdateStatusCaseIdWebservice(case_id, getResources().getString(R.string.edit_inspection));
                } else {
                    check_imageMeasurment_upload();

                }
            } else if (result.equals("2")) {
                general.CustomToast(msg);
                general.hideloading();
            } else if (result.equals("0")) {
                general.CustomToast(msg);
                general.hideloading();
            }
        } else {
            general.CustomToast(mContext.getResources().getString(R.string.serverProblem));
            general.hideloading();
        }
    }

    // Status Edit inspection Update  API call //
    private void UpdateStatusCaseIdWebservice(String case_id, String statusId) {

        if (Connectivity.isConnected(mContext)) {
            InitiateUpdateStatusCaseidTask(case_id, statusId);
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
    }

    private void InitiateUpdateStatusCaseidTask(String case_id, String statusId) {

        String url = general.ApiBaseUrl() + SettingsUtils.UpdateCaseStatusById;
        // String url = SettingsUtils.BASE_URL + SettingsUtils.UpdateCaseStatusById;
        String modifiedby = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_LOGIN_ID, "");
        JsonRequestData requestData = new JsonRequestData();
        requestData.setUrl(url);
        requestData.setCaseId(case_id);//case_id
        requestData.setModifiedBy(modifiedby);
        requestData.setStatus(statusId);//statusId
        requestData.setRequestBody(RequestParam.UpdateCaseStatusRequestParams(requestData));

        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.PUT);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                parseUpdateStatusCaseidResponse(requestData.getResponse());
            }
        });
        webserviceTask.execute();
    }

    private void parseUpdateStatusCaseidResponse(String response) {

        DataResponse dataResponse = ResponseParser.parseUpdateStatusCaseResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
            Singleton.getInstance().updateCaseStatusModel = dataResponse.updateCaseStatusModel;
        }

        if (result != null) {
            if (result.equals("1")) {

                check_imageMeasurment_upload();

                /*if (save_type.equalsIgnoreCase("savego")) {
                    Singleton.getInstance().openCaseList.clear();
                    Singleton.getInstance().closeCaseList.clear();
                    startActivity(new Intent(getContext(), HomeActivity.class));
                }
                general.hideloading();*/
            } else if (result.equals("2")) {
                general.CustomToast(msg);
            } else if (result.equals("0")) {
                general.CustomToast(msg);
            }
        } else {
            general.CustomToast(mContext.getResources().getString(R.string.serverProblem));
        }
    }


    /********
     * Display views from GetFieldCase id from result
     * ********/
    private void DisplayViewsfromResult() {

        /********
         *  TODO Address Spinner values display
         * ********/
        Integer approach_condition = Singleton.getInstance().property.getApproachRoadConditionId();
        if (!general.isEmpty(String.valueOf(approach_condition)))
            spinner_condition_approach.setSelection(approach_condition);


        Integer typeof_compound = Singleton.getInstance().indProperty.getTypeOfCompound();
        if (!general.isEmpty(String.valueOf(typeof_compound)))
            spinner_type_of_compound.setSelection(typeof_compound);

        Integer locality_category = Singleton.getInstance().property.getLocalityCategoryId();
        if (!general.isEmpty(String.valueOf(locality_category)))
            spinner_select_localitycate.setSelection(locality_category);

        Integer class_id = Singleton.getInstance().property.getClassId();
        if (!general.isEmpty(String.valueOf(class_id)))
            spinner_select_class.setSelection(class_id);

        Integer tenure = Singleton.getInstance().property.getTenureId();
        if (!general.isEmpty(String.valueOf(tenure)))
            spinner_select_tenure_ownership.setSelection(tenure);

        Integer land_approve = Singleton.getInstance().property.getTypeOfLandId();
        if (!general.isEmpty(String.valueOf(land_approve)))
            spinner_landapproval.setSelection(land_approve);


        /********
         *  TODO Boundary and dimension - Address
         * and setback values display
         * ********/
        if (!general.isEmpty(Singleton.getInstance().property.getDocBoundryEast())) {
            editText_db_east.setText(Singleton.getInstance().property.getDocBoundryEast());
            editText_db_east_str = Singleton.getInstance().property.getDocBoundryEast();
        }
        if (!general.isEmpty(Singleton.getInstance().property.getDocBoundryWest())) {
            editText_db_west.setText(Singleton.getInstance().property.getDocBoundryWest());
            editText_db_west_str = Singleton.getInstance().property.getDocBoundryWest();
        }
        if (!general.isEmpty(Singleton.getInstance().property.getDocBoundryNorth())) {
            editText_db_north.setText(Singleton.getInstance().property.getDocBoundryNorth());
            editText_db_north_str = Singleton.getInstance().property.getDocBoundryNorth();
        }
        if (!general.isEmpty(Singleton.getInstance().property.getDocBoundrySouth())) {
            editText_db_south.setText(Singleton.getInstance().property.getDocBoundrySouth());
            editText_db_south_str = Singleton.getInstance().property.getDocBoundrySouth();
        }

        if (!general.isEmpty(Singleton.getInstance().property.getBoundryEast()))
            editText_ab_east.setText(Singleton.getInstance().property.getBoundryEast());
        if (!general.isEmpty(Singleton.getInstance().property.getBoundryWest()))
            editText_ab_west.setText(Singleton.getInstance().property.getBoundryWest());
        if (!general.isEmpty(Singleton.getInstance().property.getBoundryNorth()))
            editText_ab_north.setText(Singleton.getInstance().property.getBoundryNorth());
        if (!general.isEmpty(Singleton.getInstance().property.getBoundrySouth()))
            editText_ab_south.setText(Singleton.getInstance().property.getBoundrySouth());


        if (!general.isEmpty(Singleton.getInstance().property.getDocEastMeasure())) {
            editText_dd_east.setText(Singleton.getInstance().property.getDocEastMeasure());
            editText_dd_east_str = Singleton.getInstance().property.getDocEastMeasure();
        }
        if (!general.isEmpty(Singleton.getInstance().property.getDocWestMeasure())) {
            editText_dd_west.setText(Singleton.getInstance().property.getDocWestMeasure());
            editText_dd_west_str = Singleton.getInstance().property.getDocWestMeasure();
        }
        if (!general.isEmpty(Singleton.getInstance().property.getDocNorthMeasure())) {
            editText_dd_north.setText(Singleton.getInstance().property.getDocNorthMeasure());
            editText_dd_north_str = Singleton.getInstance().property.getDocNorthMeasure();
        }
        if (!general.isEmpty(Singleton.getInstance().property.getDocSouthMeasure())) {
            editText_dd_south.setText(Singleton.getInstance().property.getDocSouthMeasure());
            editText_dd_south_str = Singleton.getInstance().property.getDocSouthMeasure();
        }

        if (!general.isEmpty(Singleton.getInstance().property.getEastMeasure()))
            editText_ad_east.setText(Singleton.getInstance().property.getEastMeasure());
        if (!general.isEmpty(Singleton.getInstance().property.getWestMeasure()))
            editText_ad_west.setText(Singleton.getInstance().property.getWestMeasure());
        if (!general.isEmpty(Singleton.getInstance().property.getNorthMeasure()))
            editText_ad_north.setText(Singleton.getInstance().property.getNorthMeasure());
        if (!general.isEmpty(Singleton.getInstance().property.getSouthMeasure()))
            editText_ad_south.setText(Singleton.getInstance().property.getSouthMeasure());


        if (!general.isEmpty(Singleton.getInstance().property.getDocSetBackFront())) {
            editText_ds_front.setText(Singleton.getInstance().property.getDocSetBackFront());
            editText_ds_front_str = Singleton.getInstance().property.getDocSetBackFront();
        }
        if (!general.isEmpty(Singleton.getInstance().property.getDocSetBackLeft())) {
            editText_ds_left.setText(Singleton.getInstance().property.getDocSetBackLeft());
            editText_ds_left_str = Singleton.getInstance().property.getDocSetBackLeft();
        }
        if (!general.isEmpty(Singleton.getInstance().property.getDocSetBackRight())) {
            editText_ds_right.setText(Singleton.getInstance().property.getDocSetBackRight());
            editText_ds_right_str = Singleton.getInstance().property.getDocSetBackRight();
        }
        if (!general.isEmpty(Singleton.getInstance().property.getDocSetBackRear())) {
            editText_ds_rear.setText(Singleton.getInstance().property.getDocSetBackRear());
            editText_ds_rear_str = Singleton.getInstance().property.getDocSetBackRear();
        }

        if (!general.isEmpty(Singleton.getInstance().property.getSetBackFront()))
            editText_as_front.setText(Singleton.getInstance().property.getSetBackFront());
        if (!general.isEmpty(Singleton.getInstance().property.getSetBackLeft()))
            editText_as_left.setText(Singleton.getInstance().property.getSetBackLeft());
        if (!general.isEmpty(Singleton.getInstance().property.getSetBackRight()))
            editText_as_right.setText(Singleton.getInstance().property.getSetBackRight());
        if (!general.isEmpty(Singleton.getInstance().property.getSetBackRear()))
            editText_as_rear.setText(Singleton.getInstance().property.getSetBackRear());


        // TODO CheckBox - Address
        if (Singleton.getInstance().property.getSameAsDocumentAddress() != null)
            checkbox_same_address.setChecked(Singleton.getInstance().property.getSameAsDocumentAddress());
        if (Singleton.getInstance().property.getSameAsDocumentBoundary() != null)
            checkbox_same_as_doc_boundary.setChecked(Singleton.getInstance().property.getSameAsDocumentBoundary());
        if (Singleton.getInstance().property.getSameAsDocumentDimension() != null)
            checkbox_same_as_doc_dimension.setChecked(Singleton.getInstance().property.getSameAsDocumentDimension());
        if (Singleton.getInstance().property.getSameAsDocumentSetBack() != null)
            checkbox_same_as_doc_setback.setChecked(Singleton.getInstance().property.getSameAsDocumentSetBack());
        if (Singleton.getInstance().property.getPlotDemarcatedAtSite() != null)
            checkbox_plot_demarcated.setChecked(Singleton.getInstance().property.getPlotDemarcatedAtSite());
        if (Singleton.getInstance().property.getIsPropertyInDemolitionList() != null)
            checkbox_isproperty_demolish.setChecked(Singleton.getInstance().property.getIsPropertyInDemolitionList());
        if (Singleton.getInstance().property.getIsWithinMunicipalArea() != null)
            checkbox_property_within_muni.setChecked(Singleton.getInstance().property.getIsWithinMunicipalArea());

        // Todo Address Values Display
        if (Singleton.getInstance().aCase.getPropertyAddress() != null)
            editText_addr_perdoc.setText(Singleton.getInstance().aCase.getPropertyAddress());
        if (Singleton.getInstance().property.getPropertyAddressAtSite() != null)
            editText_addr_site.setText(Singleton.getInstance().property.getPropertyAddressAtSite());
        if (Singleton.getInstance().property.getLandmark() != null)
            editText_landmark.setText(Singleton.getInstance().property.getLandmark());
        if (Singleton.getInstance().property.getSurveyInPresenceOf() != null)
            editText_survey_persence.setText(Singleton.getInstance().property.getSurveyInPresenceOf());
        if (Singleton.getInstance().property.getPlotNo() != null)
            editText_plotno.setText(Singleton.getInstance().property.getPlotNo());

        if (property_type.equalsIgnoreCase("flat") || property_type.equalsIgnoreCase("penthouse")) {
            // number_of_building
            inputLayout_number_of_building.setVisibility(View.VISIBLE);
            if (!general.isEmpty(Singleton.getInstance().indProperty.getNumberOfBuildings())) {
                editText_number_of_building.setText(Singleton.getInstance().indProperty.getNumberOfBuildings());
            }
            /*// type_of_compound
            type_of_compound_div.setVisibility(View.VISIBLE);*/
        } else {
            // number_of_building
            inputLayout_number_of_building.setVisibility(View.GONE);
            /*// type_of_compound
            type_of_compound_div.setVisibility(View.GONE);*/
        }

        // Todo set propertyidentified and presently occupied text values
        /*setCheckboxProIdentified();
        String propertyidentified = "", identified_text = "";
        for (int i = 0; i < Singleton.getInstance().property_identified.size(); i++) {
            propertyidentified += Singleton.getInstance().property_identified.get(i) + ",";
            identified_text = PropertyIdentifiedSetValue(identified_text, Singleton.getInstance().property_identified.get(i));
        }
        Singleton.getInstance().property.setPropertyIdentificationChannelId(propertyidentified);
        spinner_property_identified.setText(identified_text);

        setCheckboxPreOccupied();
        String presently_occupied = "", occupied_text = "";
        for (int i = 0; i < Singleton.getInstance().presently_occupied.size(); i++) {
            presently_occupied += Singleton.getInstance().presently_occupied.get(i) + ",";
            occupied_text = PresentlyOccupiedSetValue(occupied_text, Singleton.getInstance().presently_occupied.get(i));
        }
        Singleton.getInstance().property.setPresentlyOccupied(presently_occupied);

        spinner_persently_occupied.setText(occupied_text);*/
    }


    private void DisplayBuildingAverage() {

        final int docSumValue = general.getDocSumValue(Singleton.getInstance().indPropertyFloors);
        final int measureSumValue = general.getMeasureSumValue(Singleton.getInstance().indPropertyFloors);
        final int completedSumValue = getCompletedSumValue(Singleton.getInstance().indPropertyFloors);
        long DELAY_TIME = 3000; //3 seconds
        Handler mHandler;
        Runnable mJumpRunnable;
        mJumpRunnable = new Runnable() {
            @SuppressLint("SetTextI18n")
            public void run() {
                View view = fragment_building.getView();
                if (view != null) {
                    TextView comptext = (TextView) view.findViewById(R.id.textview_comp_total);
                    TextView doctext = (TextView) view.findViewById(R.id.textview_doc_total);
                    TextView measuretext = (TextView) view.findViewById(R.id.textview_actual_total);
                    Spinner spinner = (Spinner) view.findViewById(R.id.spinner_measurement1);
                    if (Singleton.getInstance().caseOtherDetailsModel != null && Singleton.getInstance().caseOtherDetailsModel.getData() != null && Singleton.getInstance().caseOtherDetailsModel.getData().get(0) != null) {
                        if (Singleton.getInstance().caseOtherDetailsModel.getData().get(0).getAreaUnit() != null)
                            spinner.setSelection(Integer.parseInt(Singleton.getInstance().caseOtherDetailsModel.getData().get(0).getAreaUnit()));
                    } else {
                        spinner.setSelection(0);
                    }
                    doctext.setText("" + docSumValue);
                    measuretext.setText("" + measureSumValue);
                    comptext.setText("" + completedSumValue);
                }
            }
        };
        mHandler = new Handler();
        mHandler.postDelayed(mJumpRunnable, DELAY_TIME);
    }


    /******
     * Validation takes place here
     * *******/
    private boolean validateEditText(EditText editText, String errorMsg) {
        String editText_value = editText.getText().toString().trim();
        if (editText_value.isEmpty()) {
            editText.setError(errorMsg);
            requestFocus(editText);
            return false;
        }
        return true;
    }

    /*if (emailreg.isEmpty() || !isValidEmail(emailreg)) {
        email.setError(getString(R.string.err_msg_email));
        requestFocus(email);
        return false;
    }*/


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    /**
     * Validating form
     */
    private void submitAddressForm() {
        if (!validateEditText(editText_addr_perdoc, getString(R.string.err_msg_password))) {
            return;
        } else {
            general.CustomToast("success");
        }
    }

    @Override
    public void getBuildingData(IndProperty indProperty) {
        String value = indProperty.getMeasuredLandArea();
        Singleton.getInstance().indProperty = indProperty;
    }

    private void hideSoftKeyboard(View addkeys) {
        if ((addkeys != null) && (getActivity() != null)) {
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(addkeys.getWindowToken(), 0);
        }
        show_emptyFocus();
    }

    @SuppressLint("SetTextI18n")
    private void InteriorExteriorDisplay() {
        /* san Integration */
        Singleton.getInstance().interior_flooring_id.clear();
        Singleton.getInstance().interior_flooring_name.clear();
        Singleton.getInstance().interior_roofing_id.clear();
        Singleton.getInstance().interior_roofing_name.clear();
        Singleton.getInstance().interior_paint_id.clear();
        Singleton.getInstance().interior_paint_name.clear();
        Singleton.getInstance().interior_door_id.clear();
        Singleton.getInstance().interior_door_name.clear();
        Singleton.getInstance().interior_window_id.clear();
        Singleton.getInstance().interior_window_name.clear();
        Singleton.getInstance().exter_stru_id.clear();
        Singleton.getInstance().exter_stru_name.clear();
        Singleton.getInstance().exter_paint_id.clear();
        Singleton.getInstance().exter_paint_name.clear();
        Singleton.getInstance().PropertyActualUsage_id.clear();
        Singleton.getInstance().PropertyActualUsage_name.clear();
        Singleton.getInstance().PropertyIdentificationChannel_id.clear();
        Singleton.getInstance().PropertyIdentificationChannel_name.clear();
        Singleton.getInstance().PresentlyOccupied_id.clear();
        Singleton.getInstance().PresentlyOccupied_name.clear();
        Singleton.getInstance().lift_list.clear();
        Singleton.getInstance().Remarks_Id.clear();
        Singleton.getInstance().WaterAvailability_id.clear();
        Singleton.getInstance().WaterAvailability_name.clear();
        Singleton.getInstance().WC_id.clear();
        Singleton.getInstance().WC_name.clear();
        Singleton.getInstance().CarParking_id.clear();
        Singleton.getInstance().CarParking_name.clear();
        Singleton.getInstance().Paving_id.clear();
        Singleton.getInstance().Paving_name.clear();
        Singleton.getInstance().lobby_list.clear();
        Singleton.getInstance().FloorUsage_id.clear();
        Singleton.getInstance().FloorUsage_name.clear();

        Singleton.getInstance().is_new_floor_created = false;
        Singleton.getInstance().as_per_com = true;
        Singleton.getInstance().as_deper_first_time = true;
        // Set as false for General Info
        Singleton.getInstance().is_edit_floor_satge = false;
        Singleton.getInstance().is_edit_floor_docarea = false;
        Singleton.getInstance().is_edit_floor_age = false;
        // Set as false for Internal composition
        Singleton.getInstance().is_edit_floor_hall = false;
        Singleton.getInstance().is_edit_floor_kitchen = false;
        Singleton.getInstance().is_edit_floor_bedroom = false;
        Singleton.getInstance().is_edit_floor_bath = false;
        Singleton.getInstance().is_edit_floor_shop = false;
        // Set as true for Internal composition popup
        Singleton.getInstance().is_edit_floor_dinning = false;
        Singleton.getInstance().is_edit_floor_wc = false;
        Singleton.getInstance().is_edit_floor_attachbath = false;
        Singleton.getInstance().is_edit_floor_balcony = false;
        Singleton.getInstance().is_edit_floor_fbs = false;
        Singleton.getInstance().is_edit_floor_dbs = false;
        Singleton.getInstance().is_edit_floor_terrace = false;
        Singleton.getInstance().is_edit_floor_passage = false;

        Singleton.getInstance().floorsEntryList = false;
        Singleton.getInstance().floorsDeleteList = false;


        textview_property_identified_text.setTypeface(general.regulartypeface());
        textview_persently_occupied_text.setTypeface(general.regulartypeface());

        function_interior_floor();
        textview_flooring_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_flooring_text);
                showdialog_dynamic("floor");
            }
        });

        function_interior_roof();
        textview_roofing_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_roofing_text);
                showdialog_dynamic("roofing");
            }
        });

        function_interior_paint();
        textview_paint_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_paint_text);
                showdialog_dynamic("paint");
            }
        });

        function_interior_door();
        textview_door_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_door_text);
                showdialog_dynamic("door");
            }
        });

        function_interior_window();
        textview_window_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_window_text);
                showdialog_dynamic("window");
            }
        });


        function_exterior_structure();
        textview_exter_struc_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_exter_struc_text);
                showdialog_dynamic("exter_stru");
            }
        });


        function_exterior_paint();
        textview_exter_paint_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_exter_paint_text);
                showdialog_dynamic("exter_paint");
            }
        });


        function_PropertyIdentificationChannel();
        textview_property_identified_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_property_identified_text);
                showdialog_dynamic("property_ident_cha");
            }
        });

        function_PresentlyOccupied();
        textview_persently_occupied_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_persently_occupied_text);
                showdialog_dynamic("presently_occupied");
            }
        });

        // Lift In Building
        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getNoOfLiftInBuilding()))) {
            editText_liftinbuilding.setText("" + Singleton.getInstance().indProperty.getNoOfLiftInBuilding());
        } else {
            editText_liftinbuilding.setText("");
        }

        editText_liftinbuilding.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String toString = editText_liftinbuilding.getText().toString();
                if (!general.isEmpty(toString)) {
                    //Singleton.getInstance().indProperty.setNoOfLiftInBuilding(Integer.valueOf(toString));
                    Singleton.getInstance().indProperty.setNoOfLiftInBuilding(toString);
                } else {
                    Singleton.getInstance().indProperty.setNoOfLiftInBuilding(null);
                }
            }
        });

        editText_liftinbuilding.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    hideSoftKeyboard(editText_liftinbuilding);
                }
                return false;
            }
        });

        editText_brokervalue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    hideSoftKeyboard(editText_brokervalue);
                }
                return false;
            }
        });

        editText_nameoftenant.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    Log.i("Next key", "Enter pressed");
                    hideSoftKeyboard(editText_nameoftenant);
                }
                return false;
            }
        });

        // Remarks Recyclerview
        LinearLayoutManager linearLayoutManager_remarks = new LinearLayoutManager(getActivity());
        recyclerview_remarks.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ArrayList<Remarks> remarks_list = new ArrayList<>();
        remarks_list = Singleton.getInstance().remarks_list;
        String selected_remarks_id = Singleton.getInstance().property.getRemarks();
        madapter_remarks = new Recycler_remarks_adapter(getActivity(), remarks_list, selected_remarks_id);
        recyclerview_remarks.setAdapter(madapter_remarks);
        // additional_remarks
        if (!general.isEmpty(Singleton.getInstance().property.getOtherRemarks())) {
            editText_additional_remarks.setText(Singleton.getInstance().property.getOtherRemarks().trim());
        }
        // special_remarks
        if (!general.isEmpty(Singleton.getInstance().property.getSpecialRemarks())) {
            editText_special_remarks.setText(Singleton.getInstance().property.getSpecialRemarks().trim());
        }
        // municipal_ward
        if (!general.isEmpty(Singleton.getInstance().property.getMunicipalWard())) {
            editText_municipal_ward.setText(Singleton.getInstance().property.getMunicipalWard().trim());
        }
        // taluka_mandal_tehsil
        if (!general.isEmpty(Singleton.getInstance().aCase.getTaluka())) {
            editText_taluka_mandal_tehsil.setText(Singleton.getInstance().aCase.getTaluka().trim());
        }
        // villagename
        if (!general.isEmpty(Singleton.getInstance().aCase.getVillageName())) {
            editText_villagename.setText(Singleton.getInstance().aCase.getVillageName().trim());
        }
        // district
        if (!general.isEmpty(Singleton.getInstance().aCase.getDistrict())) {
            editText_district.setText(Singleton.getInstance().aCase.getDistrict().trim());
        }
        // surveyno
        if (!general.isEmpty(Singleton.getInstance().property.getSurveyNo())) {
            editText_surveyno.setText(Singleton.getInstance().property.getSurveyNo().trim());
        }
        // nameofseller
        /*if (!general.isEmpty(Singleton.getInstance().property.getNameOfSeller())) {
            editText_nameofseller.setText(Singleton.getInstance().property.getNameOfSeller().trim());
        }*/
        if (!general.isEmpty(Singleton.getInstance().property.getNameofVendor())) {
            editText_vendor.setText(Singleton.getInstance().property.getNameofVendor());
        }
        // unitno
        if (!general.isEmpty(Singleton.getInstance().property.getUnitNo())) {
            editText_unitno.setText(Singleton.getInstance().property.getUnitNo().trim());
        }
        // ctsno
        if (!general.isEmpty(Singleton.getInstance().property.getCtsNo())) {
            editText_ctsno.setText(Singleton.getInstance().property.getCtsNo().trim());
        }
        // colony_name
        if (!general.isEmpty(Singleton.getInstance().property.getColonyName())) {
            editText_colony_name.setText(Singleton.getInstance().property.getColonyName().trim());
        }
        // wingno
        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getWingNo()))) {
            editText_wingno.setText(String.valueOf(Singleton.getInstance().indProperty.getWingNo()));
        }
        // year_of_current_tenancy
        if (!general.isEmpty(Singleton.getInstance().indProperty.getYearsOfCurrentTenancy())) {
            editText_year_of_current_tenancy.setText(Singleton.getInstance().indProperty.getYearsOfCurrentTenancy().trim());
        }
        // amenities
        if (!general.isEmpty(Singleton.getInstance().indProperty.getAmenities())) {
            editText_amenities.setText(Singleton.getInstance().indProperty.getAmenities().trim());
        }
        // rentalincome
        if (!general.isEmpty(Singleton.getInstance().indProperty.getRentalIncome())) {
            editText_rentalincome.setText(Singleton.getInstance().indProperty.getRentalIncome().trim());
        }
        // nameoftenant
        if (!general.isEmpty(Singleton.getInstance().indProperty.getNameOfTenant())) {
            editText_nameoftenant.setText(Singleton.getInstance().indProperty.getNameOfTenant().trim());
        }
        // wing_nameancy
        if (!general.isEmpty(Singleton.getInstance().indProperty.getWingName())) {
            editText_wing_name.setText(Singleton.getInstance().indProperty.getWingName().trim());
        }
        // floor_height
        if (!general.isEmpty(Singleton.getInstance().indProperty.getFloorHeight())) {
            editText_floor_height.setText(Singleton.getInstance().indProperty.getFloorHeight().trim());
        }

        // IntPop
        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getIntPop()))) {
            Log.e("Property_radio", "IntPop: " + Singleton.getInstance().indProperty.getIntPop());
            if (Singleton.getInstance().indProperty.getIntPop())
                id_radio_pop_yes.setChecked(true);
            else
                id_radio_pop_no.setChecked(true);
        }
        // GardenExist
        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getGardenExist()))) {
            Log.e("Property_radio", "GardenExist: " + Singleton.getInstance().indProperty.getGardenExist());
            if (Singleton.getInstance().indProperty.getGardenExist())
                id_radio_garden_exists_yes.setChecked(true);
            else
                id_radio_garden_exists_no.setChecked(true);
        }
        // SeperateCompoundId
        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getSeperateCompoundId()))) {
            if (Singleton.getInstance().indProperty.getSeperateCompoundId() == 1) {
                id_radio_separatecompound_yes.setChecked(true);
                type_of_compound_div.setVisibility(View.VISIBLE);
            } else {
                id_radio_separatecompound_no.setChecked(true);
                type_of_compound_div.setVisibility(View.GONE);
            }
        }


        // spinner - Lobby
        Singleton.getInstance().lobby_list = general.lobby_array();
        ArrayAdapter<String> lobbyArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().lobby_list) {
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
        lobbyArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_passagecorridorschowklobby.setAdapter(lobbyArrayAdapter);
        spinner_passagecorridorschowklobby.setOnTouchListener(this);

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getPassageTypeId()))) {
            Log.e("spinner_lobby", "::: " + Singleton.getInstance().indProperty.getPassageTypeId());
            ArrayList<String> qualityConstructions_ = new ArrayList<>();
            qualityConstructions_ = Singleton.getInstance().lobby_list;
            for (int x = 0; x < qualityConstructions_.size(); x++) {
                if (qualityConstructions_.get(x).equalsIgnoreCase("" + Singleton.getInstance().indProperty.getPassageTypeId())) {
                    spinner_passagecorridorschowklobby.setSelection(x);
                }
            }
        } else {
            spinner_passagecorridorschowklobby.setSelection(0);
        }

        spinner_passagecorridorschowklobby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_lobby", "::: " + Singleton.getInstance().lobby_list.get(position));
                if (position == 0) {
                    Singleton.getInstance().indProperty.setPassageTypeId(null);
                } else {
                    Singleton.getInstance().indProperty.setPassageTypeId(Integer.valueOf(Singleton.getInstance().lobby_list.get(position)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        // spinner - Locality
        ArrayAdapter<Locality> localityArrayAdapter = new ArrayAdapter<Locality>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().localities_list) {
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
        localityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typeoflocality.setAdapter(localityArrayAdapter);
        spinner_typeoflocality_for_land.setAdapter(localityArrayAdapter);
        spinner_typeoflocality.setOnTouchListener(this);
        spinner_typeoflocality_for_land.setOnTouchListener(this);

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().property.getTypeLocalityId()))) {
            Log.e("spinner_Locality", "::: " + Singleton.getInstance().property.getTypeLocalityId());
            if (Singleton.getInstance().property.getTypeLocalityId() != 0) {
                ArrayList<Locality> qualityConstructions_ = new ArrayList<>();
                qualityConstructions_ = Singleton.getInstance().localities_list;
                for (int x = 0; x < qualityConstructions_.size(); x++) {
                    if (qualityConstructions_.get(x).getTypeLocalityId() == Singleton.getInstance().property.getTypeLocalityId()) {
                        spinner_typeoflocality.setSelection(x);
                        spinner_typeoflocality_for_land.setSelection(x);
                    }
                }
            }
        }

        // spinner - bathflooring
        ArrayAdapter<Bath> bathArrayAdapter = new ArrayAdapter<Bath>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().bath_list) {
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
        bathArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_bathflooring.setAdapter(bathArrayAdapter);
        spinner_bathflooring.setOnTouchListener(this);

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getIntBathId()))) {
            Log.e("spinner_bath", "::: " + Singleton.getInstance().indProperty.getIntBathId());
            if (Singleton.getInstance().indProperty.getIntBathId() != 0) {
                ArrayList<Bath> qualityConstructions_ = new ArrayList<>();
                qualityConstructions_ = Singleton.getInstance().bath_list;
                for (int x = 0; x < qualityConstructions_.size(); x++) {
                    if (qualityConstructions_.get(x).getIntBathId() == Singleton.getInstance().indProperty.getIntBathId()) {
                        spinner_bathflooring.setSelection(x);
                    }
                }
            }
        }

        /*spinner_bathflooring.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_bath", "::: " + Singleton.getInstance().bath_list.get(position).getIntBathId());
                if (position == 0) {
                    Singleton.getInstance().indProperty.setIntBathId(null);
                } else {
                    Singleton.getInstance().indProperty.setIntBathId(Singleton.getInstance().bath_list.get(position).getIntBathId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/


        // spinner - amenitiesquality
        ArrayAdapter<AmenityQuality> amenityQualityArrayAdapter = new ArrayAdapter<AmenityQuality>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().amenityQualities_list) {
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
        amenityQualityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_amenitiesquality.setAdapter(amenityQualityArrayAdapter);
        spinner_amenitiesquality.setOnTouchListener(this);

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getAmenityQualityId()))) {
            Log.e("spinner_amenities", "::: " + Singleton.getInstance().indProperty.getAmenityQualityId());
            if (Singleton.getInstance().indProperty.getAmenityQualityId() != 0) {
                ArrayList<AmenityQuality> qualityConstructions_ = new ArrayList<>();
                qualityConstructions_ = Singleton.getInstance().amenityQualities_list;
                for (int x = 0; x < qualityConstructions_.size(); x++) {
                    if (qualityConstructions_.get(x).getAmenityQualityId() == Singleton.getInstance().indProperty.getAmenityQualityId()) {
                        spinner_amenitiesquality.setSelection(x);
                    }
                }
            }
        }


       /* spinner_amenitiesquality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_amenities", "::: " + Singleton.getInstance().amenityQualities_list.get(position).getAmenityQualityId());
                if (position == 0) {
                    Singleton.getInstance().indProperty.setAmenityQualityId(null);
                } else {
                    Singleton.getInstance().indProperty.setAmenityQualityId(Singleton.getInstance().amenityQualities_list.get(position).getAmenityQualityId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/


        // spinner - kitchentype
        ArrayAdapter<Kitchentype> kitchenArrayAdapter = new ArrayAdapter<Kitchentype>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().kitchens_list) {
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
        kitchenArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_kitchentype.setAdapter(kitchenArrayAdapter);
        spinner_kitchentype.setOnTouchListener(this);

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getIntKitchenType()))) {
            Log.e("spinner_kitchentype", "::: " + Singleton.getInstance().indProperty.getIntKitchenType());
            if (Singleton.getInstance().indProperty.getIntKitchenType() != 0) {
                ArrayList<Kitchentype> qualityConstructions_ = new ArrayList<>();
                qualityConstructions_ = Singleton.getInstance().kitchens_list;
                for (int x = 0; x < qualityConstructions_.size(); x++) {
                    if (qualityConstructions_.get(x).getIntKitchenTypeId() == Singleton.getInstance().indProperty.getIntKitchenType()) {
                        spinner_kitchentype.setSelection(x);
                    }
                }
            }
        }

        /*spinner_kitchentype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_kitchentype", "::: " + Singleton.getInstance().kitchens_list.get(position).getIntKitchenId());
                if (position == 0) {
                    Singleton.getInstance().indProperty.setIntKitchenType(null);
                } else {
                    Singleton.getInstance().indProperty.setIntKitchenType(Singleton.getInstance().kitchens_list.get(position).getIntKitchenId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/


        // spinner - kitchenshape
        ArrayAdapter<Kitchen> kitchenshapeArrayAdapter = new ArrayAdapter<Kitchen>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().kitchens_shape_list) {
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
        kitchenshapeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_kitchenshape.setAdapter(kitchenshapeArrayAdapter);
        spinner_kitchenshape.setOnTouchListener(this);

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getIntKitchenShape()))) {
            Log.e("spinner_kitchentype", "::: " + Singleton.getInstance().indProperty.getIntKitchenShape());
            if (Singleton.getInstance().indProperty.getIntKitchenShape() != 0) {
                ArrayList<Kitchen> qualityConstructions_ = new ArrayList<>();
                qualityConstructions_ = Singleton.getInstance().kitchens_shape_list;
                for (int x = 0; x < qualityConstructions_.size(); x++) {
                    if (qualityConstructions_.get(x).getIntKitchenId() == Singleton.getInstance().indProperty.getIntKitchenShape()) {
                        spinner_kitchenshape.setSelection(x);
                    }
                }
            }
        }

        /*spinner_kitchenshape.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinner_kitchentype", "::: " + Singleton.getInstance().kitchens_shape_list.get(position).getIntKitchenId());
                if (position == 0) {
                    Singleton.getInstance().indProperty.setIntKitchenShape(null);
                } else {
                    Singleton.getInstance().indProperty.setIntKitchenShape(Singleton.getInstance().kitchens_shape_list.get(position).getIntKitchenId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/


        // spinner - pavingaroundbuilding
        /*ArrayAdapter<Paving> pavingArrayAdapter = new ArrayAdapter<Paving>(getActivity(), R.layout.row_spinner_item, Singleton.getInstance().pavings_list) {
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
        pavingArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pavingaroundbuilding.setAdapter(pavingArrayAdapter);
        spinner_pavingaroundbuilding.setOnTouchListener(this);

        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getPavingAroundBuildingId()))) {
            Log.e("spinner_paving", "::: " + Singleton.getInstance().indProperty.getPavingAroundBuildingId());
            if (Singleton.getInstance().indProperty.getPavingAroundBuildingId() != 0) {
                ArrayList<Paving> qualityConstructions_ = new ArrayList<>();
                qualityConstructions_ = Singleton.getInstance().pavings_list;
                for (int x = 0; x < qualityConstructions_.size(); x++) {
                    if (qualityConstructions_.get(x).getPavingAroundBuildingId() == Singleton.getInstance().indProperty.getPavingAroundBuildingId()) {
                        spinner_pavingaroundbuilding.setSelection(x);
                    }
                }
            }
        }*/

        function_wateravailabilty();
        textview_wateravailabilty_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_wateravailabilty_text);
                showdialog_dynamic("wateravailabilty");
            }
        });

        function_wc();
        textview_wc_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_wc_text);
                showdialog_dynamic("wc");
            }
        });

        function_carparking();
        textview_carparking_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_carparking_text);
                showdialog_dynamic("carparking");
            }
        });

        function_pavingaroundbuilding();
        textview_pavingaroundbuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(textview_pavingaroundbuilding);
                showdialog_dynamic("pavingaroundbuilding");
            }
        });

    }


    private void function_interior_floor() {
        // clear the array and set the floor list in array
        Inter_floors_list = new ArrayList<>();
        Inter_floors_list = Singleton.getInstance().floors_list;
        // check Floor Dropdown is empty
        if (Inter_floors_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().indProperty.getIntFloorId();
//            Inter_floors_list_selected_id_dummy = getIntFloorId;
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> Inter_floors_list_selected_id_commo = new ArrayList<>();
                Inter_floors_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (Inter_floors_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < Inter_floors_list.size(); x++) {
                        if (Inter_floors_list_selected_id_commo.toString().contains("" + Inter_floors_list.get(x).getIntFloorId())) {
                            for (int y = 0; y < Inter_floors_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(Inter_floors_list_selected_id_commo.get(y));
                                if (Inter_floors_list.get(x).getIntFloorId() == one_by_one_id) {
                                    Singleton.getInstance().interior_flooring_id.add(Inter_floors_list.get(x).getIntFloorId());
                                    Singleton.getInstance().interior_flooring_name.add(Inter_floors_list.get(x).getName());
                                }
                            }
                            textview_flooring_text.setText(general.remove_array_brac_and_space(Singleton.getInstance().interior_flooring_name.toString()));
                        }
                    }
                }
            } else {
                textview_flooring_text.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void function_interior_roof() {
        // clear the array and set the floor list in array
        Inter_roofing_list = new ArrayList<>();
        Inter_roofing_list = Singleton.getInstance().roof_list;
        // check Floor Dropdown is empty
        if (Inter_roofing_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().indProperty.getIntRoofId();
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> Inter_roofing_list_selected_id_commo = new ArrayList<>();
                Inter_roofing_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (Inter_roofing_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < Inter_roofing_list.size(); x++) {
                        if (Inter_roofing_list_selected_id_commo.toString().contains("" + Inter_roofing_list.get(x).getIntRoofId())) {
                            for (int y = 0; y < Inter_roofing_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(Inter_roofing_list_selected_id_commo.get(y));
                                if (Inter_roofing_list.get(x).getIntRoofId() == one_by_one_id) {
                                    Singleton.getInstance().interior_roofing_id.add(Inter_roofing_list.get(x).getIntRoofId());
                                    Singleton.getInstance().interior_roofing_name.add(Inter_roofing_list.get(x).getName());
                                }
                            }
                            textview_roofing_text.setText(general.remove_array_brac_and_space(Singleton.getInstance().interior_roofing_name.toString()));
                        }
                    }
                }
            } else {
                textview_roofing_text.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void function_interior_paint() {
        // clear the array and set the floor list in array
        Inter_paint_list = new ArrayList<>();
        Inter_paint_list = Singleton.getInstance().paints_list;
        // check Floor Dropdown is empty
        if (Inter_paint_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().indProperty.getIntPaintId();
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> Inter_paint_list_selected_id_commo = new ArrayList<>();
                Inter_paint_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (Inter_paint_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < Inter_paint_list.size(); x++) {
                        if (Inter_paint_list_selected_id_commo.toString().contains("" + Inter_paint_list.get(x).getIntPaintId())) {
                            for (int y = 0; y < Inter_paint_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(Inter_paint_list_selected_id_commo.get(y));
                                if (Inter_paint_list.get(x).getIntPaintId() == one_by_one_id) {
                                    Singleton.getInstance().interior_paint_id.add(Inter_paint_list.get(x).getIntPaintId());
                                    Singleton.getInstance().interior_paint_name.add(Inter_paint_list.get(x).getName());
                                }
                            }
                            textview_paint_text.setText(general.remove_array_brac_and_space(Singleton.getInstance().interior_paint_name.toString()));
                        }
                    }
                }
            } else {
                textview_paint_text.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void function_interior_door() {
        // clear the array and set the floor list in array
        Inter_door_list = new ArrayList<>();
        Inter_door_list = Singleton.getInstance().doors_list;
        // check Floor Dropdown is empty
        if (Inter_door_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().indProperty.getIntDoorId();
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> Inter_door_list_selected_id_commo = new ArrayList<>();
                Inter_door_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (Inter_door_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < Inter_door_list.size(); x++) {
                        if (Inter_door_list_selected_id_commo.toString().contains("" + Inter_door_list.get(x).getIntDoorId())) {
                            for (int y = 0; y < Inter_door_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(Inter_door_list_selected_id_commo.get(y));
                                if (Inter_door_list.get(x).getIntDoorId() == one_by_one_id) {
                                    Singleton.getInstance().interior_door_id.add(Inter_door_list.get(x).getIntDoorId());
                                    Singleton.getInstance().interior_door_name.add(Inter_door_list.get(x).getName());
                                }
                            }
                            textview_door_text.setText(general.remove_array_brac_and_space(Singleton.getInstance().interior_door_name.toString()));
                        }
                    }
                }
            } else {
                textview_door_text.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void function_interior_window() {
        // clear the array and set the floor list in array
        Inter_window_list = new ArrayList<>();
        Inter_window_list = Singleton.getInstance().windows_list;
        // check Floor Dropdown is empty
        if (Inter_window_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().indProperty.getIntWindowId();
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> Inter_window_list_selected_id_commo = new ArrayList<>();
                Inter_window_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (Inter_window_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < Inter_window_list.size(); x++) {
                        if (Inter_window_list_selected_id_commo.toString().contains("" + Inter_window_list.get(x).getIntWindowId())) {
                            for (int y = 0; y < Inter_window_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(Inter_window_list_selected_id_commo.get(y));
                                if (Inter_window_list.get(x).getIntWindowId() == one_by_one_id) {
                                    Singleton.getInstance().interior_window_id.add(Inter_window_list.get(x).getIntWindowId());
                                    Singleton.getInstance().interior_window_name.add(Inter_window_list.get(x).getName());
                                }
                            }
                            textview_window_text.setText(general.remove_array_brac_and_space(Singleton.getInstance().interior_window_name.toString()));
                        }
                    }
                }
            } else {
                textview_window_text.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void function_exterior_structure() {
        // clear the array and set the floor list in array
        Exter_struc_list = new ArrayList<>();
        Exter_struc_list = Singleton.getInstance().structures_list;
        // check Floor Dropdown is empty
        if (Exter_struc_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().indProperty.getTypeOfStructureId();
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> Exter_struc_list_selected_id_commo = new ArrayList<>();
                Exter_struc_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (Exter_struc_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < Exter_struc_list.size(); x++) {
                        if (Exter_struc_list_selected_id_commo.toString().contains("" + Exter_struc_list.get(x).getTypeOfStructureId())) {
                            for (int y = 0; y < Exter_struc_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(Exter_struc_list_selected_id_commo.get(y));
                                if (Exter_struc_list.get(x).getTypeOfStructureId() == one_by_one_id) {
                                    Singleton.getInstance().exter_stru_id.add(Exter_struc_list.get(x).getTypeOfStructureId());
                                    Singleton.getInstance().exter_stru_name.add(Exter_struc_list.get(x).getName());
                                }
                            }
                            textview_exter_struc_text.setText(general.remove_array_brac_and_space(Singleton.getInstance().exter_stru_name.toString()));
                        }
                    }
                }
            } else {
                textview_exter_struc_text.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void function_exterior_paint() {
        // clear the array and set the floor list in array
        Exter_paint_list = new ArrayList<>();
        Exter_paint_list = Singleton.getInstance().exteriors_list;
        // check Floor Dropdown is empty
        if (Exter_paint_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().indProperty.getExteriorPaintId();
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> Exter_paint_list_selected_id_commo = new ArrayList<>();
                Exter_paint_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (Exter_paint_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < Exter_paint_list.size(); x++) {
                        if (Exter_paint_list_selected_id_commo.toString().contains("" + Exter_paint_list.get(x).getExteriorPaintId())) {
                            for (int y = 0; y < Exter_paint_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(Exter_paint_list_selected_id_commo.get(y));
                                if (Exter_paint_list.get(x).getExteriorPaintId() == one_by_one_id) {
                                    Singleton.getInstance().exter_paint_id.add(Exter_paint_list.get(x).getExteriorPaintId());
                                    Singleton.getInstance().exter_paint_name.add(Exter_paint_list.get(x).getName());
                                }
                            }
                            textview_exter_paint_text.setText(general.remove_array_brac_and_space(Singleton.getInstance().exter_paint_name.toString()));
                        }
                    }
                }
            } else {
                textview_exter_paint_text.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void function_PropertyIdentificationChannel() {
        // clear the array and set the floor list in array
        // clear the array and set the floor list in array
        PropertyIdentificationChannel_list = new ArrayList<>();
        PropertyIdentificationChannel_list = Singleton.getInstance().propertyIdentificationChannels_list;
        // check Floor Dropdown is empty
        if (PropertyIdentificationChannel_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().property.getPropertyIdentificationChannelId();
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> PropertyIdentificationChannel_list_selected_id_commo = new ArrayList<>();
                PropertyIdentificationChannel_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (PropertyIdentificationChannel_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < PropertyIdentificationChannel_list.size(); x++) {
                        if (PropertyIdentificationChannel_list_selected_id_commo.toString().contains("" + PropertyIdentificationChannel_list.get(x).getPropertyIdentificationChannelId())) {
                            for (int y = 0; y < PropertyIdentificationChannel_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(PropertyIdentificationChannel_list_selected_id_commo.get(y));
                                if (PropertyIdentificationChannel_list.get(x).getPropertyIdentificationChannelId() == one_by_one_id) {
                                    Singleton.getInstance().PropertyIdentificationChannel_id.add(PropertyIdentificationChannel_list.get(x).getPropertyIdentificationChannelId());
                                    Singleton.getInstance().PropertyIdentificationChannel_name.add(PropertyIdentificationChannel_list.get(x).getName());
                                }
                            }
                            textview_property_identified_text.setText(general.remove_array_brac_and_space(Singleton.getInstance().PropertyIdentificationChannel_name.toString()));
                        }
                    }
                }
            } else {
                textview_property_identified_text.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void function_PresentlyOccupied() {
        // clear the array and set the floor list in array
        // clear the array and set the floor list in array
        PresentlyOccupied_list = new ArrayList<>();
        PresentlyOccupied_list = Singleton.getInstance().presentlyOccupied_list;
        // check Floor Dropdown is empty
        if (PresentlyOccupied_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().property.getPresentlyOccupied();
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> PresentlyOccupied_list_selected_id_commo = new ArrayList<>();
                PresentlyOccupied_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (PresentlyOccupied_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < PresentlyOccupied_list.size(); x++) {
                        if (PresentlyOccupied_list_selected_id_commo.toString().contains("" + PresentlyOccupied_list.get(x).getPresentlyOccupiedId())) {
                            for (int y = 0; y < PresentlyOccupied_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(PresentlyOccupied_list_selected_id_commo.get(y));
                                if (PresentlyOccupied_list.get(x).getPresentlyOccupiedId() == one_by_one_id) {
                                    Singleton.getInstance().PresentlyOccupied_id.add(PresentlyOccupied_list.get(x).getPresentlyOccupiedId());
                                    Singleton.getInstance().PresentlyOccupied_name.add(PresentlyOccupied_list.get(x).getName());
                                }
                            }
                            textview_persently_occupied_text.setText(general.remove_array_brac_and_space(Singleton.getInstance().PresentlyOccupied_name.toString()));
                        }
                    }
                }
            } else {
                textview_persently_occupied_text.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void function_wateravailabilty() {
        // clear the array and set the floor list in array
        WaterAvailability_list = new ArrayList<>();
        WaterAvailability_list = Singleton.getInstance().waterAvailabilities_list;
        // check Floor Dropdown is empty
        if (WaterAvailability_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().indProperty.getWaterAvailabilityId();
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> WaterAvailability_list_selected_id_commo = new ArrayList<>();
                WaterAvailability_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (WaterAvailability_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < WaterAvailability_list.size(); x++) {
                        if (WaterAvailability_list_selected_id_commo.toString().contains("" + WaterAvailability_list.get(x).getWaterAvailabilityId())) {
                            for (int y = 0; y < WaterAvailability_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(WaterAvailability_list_selected_id_commo.get(y));
                                if (WaterAvailability_list.get(x).getWaterAvailabilityId() == one_by_one_id) {
                                    Singleton.getInstance().WaterAvailability_id.add(WaterAvailability_list.get(x).getWaterAvailabilityId());
                                    Singleton.getInstance().WaterAvailability_name.add(WaterAvailability_list.get(x).getName());
                                }
                            }
                            textview_wateravailabilty_text.setText(general.remove_array_brac_and_space(Singleton.getInstance().WaterAvailability_name.toString()));
                        }
                    }
                }
            } else {
                textview_wateravailabilty_text.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void function_wc() {
        // clear the array and set the floor list in array
        WC_list = new ArrayList<>();
        WC_list = Singleton.getInstance().wcs_list;
        // check Floor Dropdown is empty
        if (WC_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().indProperty.getIntWcId();
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> WC_list_selected_id_commo = new ArrayList<>();
                WC_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (WC_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < WC_list.size(); x++) {
                        if (WC_list_selected_id_commo.toString().contains("" + WC_list.get(x).getIntWcId())) {
                            for (int y = 0; y < WC_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(WC_list_selected_id_commo.get(y));
                                if (WC_list.get(x).getIntWcId() == one_by_one_id) {
                                    Singleton.getInstance().WC_id.add(WC_list.get(x).getIntWcId());
                                    Singleton.getInstance().WC_name.add(WC_list.get(x).getName());
                                }
                            }
                            textview_wc_text.setText(general.remove_array_brac_and_space(Singleton.getInstance().WC_name.toString()));
                        }
                    }
                }
            } else {
                textview_wc_text.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void function_carparking() {
        // clear the array and set the floor list in array
        CarParking_list = new ArrayList<>();
        CarParking_list = Singleton.getInstance().carParkings_list;
        // check Floor Dropdown is empty
        if (CarParking_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().indProperty.getCarParkingId();
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> CarParking_list_selected_id_commo = new ArrayList<>();
                CarParking_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (CarParking_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < CarParking_list.size(); x++) {
                        if (CarParking_list_selected_id_commo.toString().contains("" + CarParking_list.get(x).getCarParkingId())) {
                            for (int y = 0; y < CarParking_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(CarParking_list_selected_id_commo.get(y));
                                if (CarParking_list.get(x).getCarParkingId() == one_by_one_id) {
                                    Singleton.getInstance().CarParking_id.add(CarParking_list.get(x).getCarParkingId());
                                    Singleton.getInstance().CarParking_name.add(CarParking_list.get(x).getName());
                                }
                            }
                            textview_carparking_text.setText(general.remove_array_brac_and_space(Singleton.getInstance().CarParking_name.toString()));
                        }
                    }
                }
            } else {
                textview_carparking_text.setText(getResources().getString(R.string.select));
            }
        }
    }

    private void function_pavingaroundbuilding() {
        // clear the array and set the floor list in array
        Paving_list = new ArrayList<>();
        Paving_list = Singleton.getInstance().pavings_list;
        // check Floor Dropdown is empty
        if (Paving_list.size() > 0) {
            String getIntFloorId = Singleton.getInstance().indProperty.getPavingAroundBuildingId();
            // check selected array is empty
            if (!general.isEmpty(getIntFloorId)) {
                // selected array convert to comma separate array
                List<String> Paving_list_selected_id_commo = new ArrayList<>();
                Paving_list_selected_id_commo = new ArrayList<String>(Arrays.asList(getIntFloorId.split(",")));
                if (Paving_list_selected_id_commo.size() > 0) {
                    // Loop the Floor and get ID > check If selected array contains ID
                    for (int x = 0; x < Paving_list.size(); x++) {
                        if (Paving_list_selected_id_commo.toString().contains("" + Paving_list.get(x).getPavingAroundBuildingId())) {
                            for (int y = 0; y < Paving_list_selected_id_commo.size(); y++) {
                                // Loop the selected array > If the selected array ID equal to Floor ID > save ID and name to Instance
                                int one_by_one_id = Integer.parseInt(Paving_list_selected_id_commo.get(y));
                                if (Paving_list.get(x).getPavingAroundBuildingId() == one_by_one_id) {
                                    Singleton.getInstance().Paving_id.add(Paving_list.get(x).getPavingAroundBuildingId());
                                    Singleton.getInstance().Paving_name.add(Paving_list.get(x).getName());
                                }
                            }
                            textview_pavingaroundbuilding.setText(general.remove_array_brac_and_space(Singleton.getInstance().Paving_name.toString()));
                        }
                    }
                }
            } else {
                textview_pavingaroundbuilding.setText(getResources().getString(R.string.select));
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

        if (type_of_dialog.equalsIgnoreCase("floor")) {
            // Type -> Floor
            textview_heading.setText(getResources().getString(R.string.flooring));
            String selectedId = Singleton.getInstance().indProperty.getIntFloorId();
            FlooringAdapter flooringAdapter = new FlooringAdapter(getActivity(), Inter_floors_list, selectedId);
            recyclerview_dialog.setAdapter(flooringAdapter);
        } else if (type_of_dialog.equalsIgnoreCase("roofing")) {
            // Type -> Roofing
            textview_heading.setText(getResources().getString(R.string.roofing));
            String selectedId = Singleton.getInstance().indProperty.getIntRoofId();
            RoofingAdapter roofingAdapter = new RoofingAdapter(getActivity(), Inter_roofing_list, selectedId);
            recyclerview_dialog.setAdapter(roofingAdapter);
        } else if (type_of_dialog.equalsIgnoreCase("paint")) {
            // Type -> Roofing
            textview_heading.setText(getResources().getString(R.string.paint));
            String selectedId = Singleton.getInstance().indProperty.getIntPaintId();
            PaintAdapter paintAdapter = new PaintAdapter(getActivity(), Inter_paint_list, selectedId);
            recyclerview_dialog.setAdapter(paintAdapter);
        } else if (type_of_dialog.equalsIgnoreCase("door")) {
            // Type -> Roofing
            textview_heading.setText(getResources().getString(R.string.door));
            String selectedId = Singleton.getInstance().indProperty.getIntDoorId();
            DoorAdapter doorAdapter = new DoorAdapter(getActivity(), Inter_door_list, selectedId);
            recyclerview_dialog.setAdapter(doorAdapter);
        } else if (type_of_dialog.equalsIgnoreCase("window")) {
            // Type -> Roofing
            textview_heading.setText(getResources().getString(R.string.windows));
            String selectedId = Singleton.getInstance().indProperty.getIntWindowId();
            WindowsAdapter windowsAdapter = new WindowsAdapter(getActivity(), Inter_window_list, selectedId);
            recyclerview_dialog.setAdapter(windowsAdapter);


        } else if (type_of_dialog.equalsIgnoreCase("exter_stru")) {
            // Type -> Roofing
            textview_heading.setText(getResources().getString(R.string.typeofstructure));
            String selectedId = Singleton.getInstance().indProperty.getTypeOfStructureId();
            ExterStructureAdapter exterStructureAdapter = new ExterStructureAdapter(getActivity(), Exter_struc_list, selectedId);
            recyclerview_dialog.setAdapter(exterStructureAdapter);
        } else if (type_of_dialog.equalsIgnoreCase("exter_paint")) {
            // Type -> Roofing
            textview_heading.setText(getResources().getString(R.string.exteriorpaint));
            String selectedId = Singleton.getInstance().indProperty.getExteriorPaintId();
            ExterPaintAdapter exterPaintAdapter = new ExterPaintAdapter(getActivity(), Exter_paint_list, selectedId);
            recyclerview_dialog.setAdapter(exterPaintAdapter);


        } else if (type_of_dialog.equalsIgnoreCase("property_ident_cha")) {
            // Type -> property_ident_cha
            textview_heading.setText(getResources().getString(R.string.property_identified));
            String selectedId = Singleton.getInstance().property.getPropertyIdentificationChannelId();

            PropertyIdentificationChannelAdapter propertyIdentificationChannelAdapter = new PropertyIdentificationChannelAdapter(getActivity(), PropertyIdentificationChannel_list, selectedId);
            recyclerview_dialog.setAdapter(propertyIdentificationChannelAdapter);


        } else if (type_of_dialog.equalsIgnoreCase("presently_occupied")) {
            // Type -> presently_occupied
            textview_heading.setText(getResources().getString(R.string.persently_occupied));
            String selectedId = Singleton.getInstance().property.getPresentlyOccupied();
            PresentlyOccupiedAdapter presentlyOccupiedAdapter = new PresentlyOccupiedAdapter(getActivity(), PresentlyOccupied_list, selectedId);
            recyclerview_dialog.setAdapter(presentlyOccupiedAdapter);

        } else if (type_of_dialog.equalsIgnoreCase("wateravailabilty")) {
            // Type -> presently_occupied
            textview_heading.setText(getResources().getString(R.string.wateravailabilty));
            String selectedId = Singleton.getInstance().indProperty.getWaterAvailabilityId();
            WaterAvailabilityAdapter waterAvailabilityAdapter = new WaterAvailabilityAdapter(getActivity(), WaterAvailability_list, selectedId);
            recyclerview_dialog.setAdapter(waterAvailabilityAdapter);

        } else if (type_of_dialog.equalsIgnoreCase("wc")) {
            // Type -> presently_occupied
            textview_heading.setText(getResources().getString(R.string.wc));
            String selectedId = Singleton.getInstance().indProperty.getIntWcId();
            WCAdapter wcAdapter = new WCAdapter(getActivity(), WC_list, selectedId);
            recyclerview_dialog.setAdapter(wcAdapter);
        } else if (type_of_dialog.equalsIgnoreCase("carparking")) {
            // Type -> presently_occupied
            textview_heading.setText(getResources().getString(R.string.carparking));
            String selectedId = Singleton.getInstance().indProperty.getCarParkingId();
            CarParkingAdapter carParkingAdapter = new CarParkingAdapter(getActivity(), CarParking_list, selectedId);
            recyclerview_dialog.setAdapter(carParkingAdapter);
        } else if (type_of_dialog.equalsIgnoreCase("pavingaroundbuilding")) {
            // Type -> presently_occupied
            textview_heading.setText(getResources().getString(R.string.pavingaroundbuilding));
            String selectedId = Singleton.getInstance().indProperty.getPavingAroundBuildingId();
            PavingAdapter pavingAdapter = new PavingAdapter(getActivity(), Paving_list, selectedId);
            recyclerview_dialog.setAdapter(pavingAdapter);
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

                if (type_of_dialog.equalsIgnoreCase("floor")) {
                    // Type -> Floor
                    if (Singleton.getInstance().interior_flooring_id.size() > 0) {
                        String interior_flooring_id = general.remove_array_brac_and_space(Singleton.getInstance().interior_flooring_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().indProperty.setIntFloorId(interior_flooring_id);
                        // setText to the floor text
                        String interior_flooring_name = general.remove_array_brac_and_space(Singleton.getInstance().interior_flooring_name.toString());
                        textview_flooring_text.setText(interior_flooring_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().indProperty.setIntFloorId("");
                        textview_flooring_text.setText(getResources().getString(R.string.select));
                    }
                    Log.e("interior_flooring_id", "::: " + Singleton.getInstance().interior_flooring_id);
                    Log.e("interior_flooring_name", ":: " + Singleton.getInstance().interior_flooring_name);
                } else if (type_of_dialog.equalsIgnoreCase("roofing")) {
                    // Type -> Roofing
                    if (Singleton.getInstance().interior_roofing_id.size() > 0) {
                        String interior_roofing_id = general.remove_array_brac_and_space(Singleton.getInstance().interior_roofing_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().indProperty.setIntRoofId(interior_roofing_id);
                        // setText to the floor text
                        String interior_roofing_name = general.remove_array_brac_and_space(Singleton.getInstance().interior_roofing_name.toString());
                        textview_roofing_text.setText(interior_roofing_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().indProperty.setIntRoofId("");
                        textview_roofing_text.setText(getResources().getString(R.string.select));
                    }
                    Log.e("interior_roofing_id", "::: " + Singleton.getInstance().interior_roofing_id);
                    Log.e("interior_roofing_name", ":: " + Singleton.getInstance().interior_roofing_name);
                } else if (type_of_dialog.equalsIgnoreCase("paint")) {
                    // Type -> paint
                    if (Singleton.getInstance().interior_paint_id.size() > 0) {
                        String interior_paint_id = general.remove_array_brac_and_space(Singleton.getInstance().interior_paint_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().indProperty.setIntPaintId(interior_paint_id);
                        // setText to the floor text
                        String interior_paint_name = general.remove_array_brac_and_space(Singleton.getInstance().interior_paint_name.toString());
                        textview_paint_text.setText(interior_paint_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().indProperty.setIntPaintId("");
                        textview_paint_text.setText(getResources().getString(R.string.select));
                    }
                    Log.e("interior_paint_id", "::: " + Singleton.getInstance().interior_paint_id);
                    Log.e("interior_paint_name", ":: " + Singleton.getInstance().interior_paint_name);
                } else if (type_of_dialog.equalsIgnoreCase("door")) {
                    // Type -> door
                    if (Singleton.getInstance().interior_door_id.size() > 0) {
                        String interior_door_id = general.remove_array_brac_and_space(Singleton.getInstance().interior_door_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().indProperty.setIntDoorId(interior_door_id);
                        // setText to the floor text
                        String interior_door_name = general.remove_array_brac_and_space(Singleton.getInstance().interior_door_name.toString());
                        textview_door_text.setText(interior_door_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().indProperty.setIntDoorId("");
                        textview_door_text.setText(getResources().getString(R.string.select));
                    }
                    Log.e("interior_door_id", "::: " + Singleton.getInstance().interior_door_id);
                    Log.e("interior_door_name", ":: " + Singleton.getInstance().interior_door_name);
                } else if (type_of_dialog.equalsIgnoreCase("window")) {
                    // Type -> door
                    if (Singleton.getInstance().interior_window_id.size() > 0) {
                        String interior_window_id = general.remove_array_brac_and_space(Singleton.getInstance().interior_window_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().indProperty.setIntWindowId(interior_window_id);
                        // setText to the floor text
                        String interior_window_name = general.remove_array_brac_and_space(Singleton.getInstance().interior_window_name.toString());
                        textview_window_text.setText(interior_window_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().indProperty.setIntWindowId("");
                        textview_window_text.setText(getResources().getString(R.string.select));
                    }
                    Log.e("interior_window_id", "::: " + Singleton.getInstance().interior_window_id);
                    Log.e("interior_window_name", ":: " + Singleton.getInstance().interior_window_name);
                } else if (type_of_dialog.equalsIgnoreCase("exter_stru")) {
                    // Type -> door
                    if (Singleton.getInstance().exter_stru_id.size() > 0) {
                        String exter_stru_id = general.remove_array_brac_and_space(Singleton.getInstance().exter_stru_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().indProperty.setTypeOfStructureId(exter_stru_id);
                        // setText to the floor text
                        String exter_stru_name = general.remove_array_brac_and_space(Singleton.getInstance().exter_stru_name.toString());
                        textview_exter_struc_text.setText(exter_stru_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().indProperty.setTypeOfStructureId("");
                        textview_exter_struc_text.setText(getResources().getString(R.string.select));
                    }
                    Log.e("exter_stru_id", "::: " + Singleton.getInstance().exter_stru_id);
                    Log.e("exter_stru_name", ":: " + Singleton.getInstance().exter_stru_name);
                } else if (type_of_dialog.equalsIgnoreCase("exter_paint")) {
                    // Type -> door
                    if (Singleton.getInstance().exter_paint_id.size() > 0) {
                        String exter_paint_id = general.remove_array_brac_and_space(Singleton.getInstance().exter_paint_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().indProperty.setExteriorPaintId(exter_paint_id);
                        // setText to the floor text
                        String exter_paint_name = general.remove_array_brac_and_space(Singleton.getInstance().exter_paint_name.toString());
                        textview_exter_paint_text.setText(exter_paint_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().indProperty.setExteriorPaintId("");
                        textview_exter_paint_text.setText(getResources().getString(R.string.select));
                    }
                    Log.e("exter_paint_id", "::: " + Singleton.getInstance().exter_paint_id);
                    Log.e("exter_paint_name", ":: " + Singleton.getInstance().exter_paint_name);


                } else if (type_of_dialog.equalsIgnoreCase("property_ident_cha")) {
                    // Type -> property_ident_cha
                    if (Singleton.getInstance().PropertyIdentificationChannel_id.size() > 0) {
                        String PropertyIdentificationChannel_id = general.remove_array_brac_and_space(Singleton.getInstance().PropertyIdentificationChannel_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().property.setPropertyIdentificationChannelId(PropertyIdentificationChannel_id);
                        // setText to the floor text
                        String PropertyIdentificationChannel_name = general.remove_array_brac_and_space(Singleton.getInstance().PropertyIdentificationChannel_name.toString());
                        textview_property_identified_text.setText(PropertyIdentificationChannel_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().property.setPropertyIdentificationChannelId("");
                        textview_property_identified_text.setText(getResources().getString(R.string.select));
                    }
                    Log.e("PropertyIdenChan_id", "::: " + Singleton.getInstance().PropertyIdentificationChannel_id);
                    Log.e("PropertyIdenChan_name", ":: " + Singleton.getInstance().PropertyIdentificationChannel_name);


                } else if (type_of_dialog.equalsIgnoreCase("presently_occupied")) {
                    // Type -> presently_occupied
                    if (Singleton.getInstance().PresentlyOccupied_id.size() > 0) {
                        String PresentlyOccupied_id = general.remove_array_brac_and_space(Singleton.getInstance().PresentlyOccupied_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().property.setPresentlyOccupied(PresentlyOccupied_id);
                        // setText to the floor text
                        String PresentlyOccupied_name = general.remove_array_brac_and_space(Singleton.getInstance().PresentlyOccupied_name.toString());
                        textview_persently_occupied_text.setText(PresentlyOccupied_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().property.setPresentlyOccupied("");
                        textview_persently_occupied_text.setText(getResources().getString(R.string.select));
                    }
                    Log.e("PresentlyOccupied_id", "::: " + Singleton.getInstance().PresentlyOccupied_id);
                    Log.e("PresentlyOccupied_name", ":: " + Singleton.getInstance().PresentlyOccupied_name);

                } else if (type_of_dialog.equalsIgnoreCase("wateravailabilty")) {
                    // Type -> wateravailabilty
                    if (Singleton.getInstance().WaterAvailability_id.size() > 0) {
                        String WaterAvailability_id = general.remove_array_brac_and_space(Singleton.getInstance().WaterAvailability_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().indProperty.setWaterAvailabilityId(WaterAvailability_id);
                        // setText to the floor text
                        String WaterAvailability_name = general.remove_array_brac_and_space(Singleton.getInstance().WaterAvailability_name.toString());
                        textview_wateravailabilty_text.setText(WaterAvailability_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().indProperty.setWaterAvailabilityId("");
                        textview_wateravailabilty_text.setText(getResources().getString(R.string.select));
                    }
                    Log.e("WaterAvailability_id", "::: " + Singleton.getInstance().WaterAvailability_id);
                    Log.e("WaterAvailability_name", ":: " + Singleton.getInstance().WaterAvailability_name);


                } else if (type_of_dialog.equalsIgnoreCase("wc")) {
                    // Type -> wc
                    if (Singleton.getInstance().WC_id.size() > 0) {
                        String WC_id = general.remove_array_brac_and_space(Singleton.getInstance().WC_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().indProperty.setIntWcId(WC_id);
                        // setText to the floor text
                        String WC_name = general.remove_array_brac_and_space(Singleton.getInstance().WC_name.toString());
                        textview_wc_text.setText(WC_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().indProperty.setIntWcId("");
                        textview_wc_text.setText(getResources().getString(R.string.select));
                    }
                    Log.e("WC_id", "::: " + Singleton.getInstance().WC_id);
                    Log.e("WC_name", ":: " + Singleton.getInstance().WC_name);

                } else if (type_of_dialog.equalsIgnoreCase("carparking")) {
                    // Type -> carparking
                    if (Singleton.getInstance().CarParking_id.size() > 0) {
                        String CarParking_id = general.remove_array_brac_and_space(Singleton.getInstance().CarParking_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().indProperty.setCarParkingId(CarParking_id);
                        // setText to the floor text
                        String CarParking_name = general.remove_array_brac_and_space(Singleton.getInstance().CarParking_name.toString());
                        textview_carparking_text.setText(CarParking_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().indProperty.setCarParkingId("");
                        textview_carparking_text.setText(getResources().getString(R.string.select));
                    }
                    Log.e("CarParking_id", "::: " + Singleton.getInstance().CarParking_id);
                    Log.e("CarParking_name", ":: " + Singleton.getInstance().CarParking_name);
                } else if (type_of_dialog.equalsIgnoreCase("pavingaroundbuilding")) {
                    // Type -> pavingaroundbuilding
                    if (Singleton.getInstance().Paving_id.size() > 0) {
                        String Paving_id = general.remove_array_brac_and_space(Singleton.getInstance().Paving_id.toString());
                        // set the Inter floor ID
                        Singleton.getInstance().indProperty.setPavingAroundBuildingId(Paving_id);
                        // setText to the floor text
                        String Paving_name = general.remove_array_brac_and_space(Singleton.getInstance().Paving_name.toString());
                        textview_pavingaroundbuilding.setText(Paving_name);
                    } else {
                        // clear all the ID and dummy data and clear the settext
                        Singleton.getInstance().indProperty.setPavingAroundBuildingId("");
                        textview_pavingaroundbuilding.setText(getResources().getString(R.string.select));
                    }
                    Log.e("Paving_id", "::: " + Singleton.getInstance().Paving_id);
                    Log.e("Paving_name", ":: " + Singleton.getInstance().Paving_name);
                }


                alertDialog.dismiss();
            }
        });
    }

    private void getMoreRemarks() {
        // Remarks_id_
        String remarks_id_ = general.remove_array_brac_and_space(Singleton.getInstance().Remarks_Id.toString());
        Log.e("other_save", "remarks_id_is: " + remarks_id_);
        Singleton.getInstance().property.setRemarks(remarks_id_);
        // OtherRemarks
        String OtherRemarks = editText_additional_remarks.getText().toString();
        if (!general.isEmpty(OtherRemarks)) {
            Singleton.getInstance().property.setOtherRemarks(OtherRemarks.trim());
        } else {
            Singleton.getInstance().property.setOtherRemarks("");
        }
        Log.e("other_save", "OtherRemarks: " + Singleton.getInstance().property.getOtherRemarks());
        // SpecialRemarks
        String SpecialRemarks = editText_special_remarks.getText().toString();
        if (!general.isEmpty(OtherRemarks)) {
            Singleton.getInstance().property.setSpecialRemarks(SpecialRemarks.trim());
        } else {
            Singleton.getInstance().property.setSpecialRemarks("");
        }
        Log.e("other_save", "SpecialRemarks: " + Singleton.getInstance().property.getSpecialRemarks());
        // More Info - municipal_ward
        String municipal_ward = editText_municipal_ward.getText().toString();
        if (!general.isEmpty(municipal_ward)) {
            Singleton.getInstance().property.setMunicipalWard(municipal_ward.trim());
        } else {
            Singleton.getInstance().property.setMunicipalWard("");
        }
        Log.e("other_save", "municipal_ward: " + Singleton.getInstance().property.getMunicipalWard());
        // More Info - taluka_mandal_tehsil
        String taluka_mandal_tehsil = editText_taluka_mandal_tehsil.getText().toString();
        if (!general.isEmpty(taluka_mandal_tehsil)) {
            Singleton.getInstance().aCase.setTaluka(taluka_mandal_tehsil.trim());
        } else {
            Singleton.getInstance().aCase.setTaluka("");
        }
        Log.e("other_save", "taluka_mandal_tehsil: " + Singleton.getInstance().aCase.getTaluka());
        // More Info - villagename
        String villagename = editText_villagename.getText().toString();
        if (!general.isEmpty(villagename)) {
            Singleton.getInstance().aCase.setVillageName(villagename.trim());
        } else {
            Singleton.getInstance().aCase.setVillageName("");
        }
        Log.e("other_save", "villagename: " + Singleton.getInstance().aCase.getVillageName());
        // More Info - district
        String district = editText_district.getText().toString();
        if (!general.isEmpty(district)) {
            Singleton.getInstance().aCase.setDistrict(district.trim());
        } else {
            Singleton.getInstance().aCase.setDistrict("");
        }
        Log.e("other_save", "district: " + Singleton.getInstance().aCase.getDistrict());
        // More Info - surveyno
        String surveyno = editText_surveyno.getText().toString();
        if (!general.isEmpty(surveyno)) {
            Singleton.getInstance().property.setSurveyNo(surveyno.trim());
        } else {
            Singleton.getInstance().property.setSurveyNo("");
        }
        Log.e("other_save", "surveyno: " + Singleton.getInstance().property.getSurveyNo());

        // More Info - vendor
        String vendor = editText_vendor.getText().toString();
        if (!general.isEmpty(vendor)) {
            Singleton.getInstance().property.setNameofVendor(vendor.trim());
        } else {
            Singleton.getInstance().property.setNameofVendor("");
        }

        /* Hide the spinner_nameofseller as per cient request for only kakode - Date - 04/09/2018 */
        // More Info - nameofseller
        /*String nameofseller = editText_nameofseller.getText().toString();
        if (!general.isEmpty(nameofseller)) {
            String spinnerinitial = spinner_nameofseller.getSelectedItem().toString();
            if (spinnerinitial.contains(".")) {
                spinnerinitial = spinnerinitial.replace(".", ". ");
                //  spinnerinitial = spinnerinitial + ". ";
            }
            if (spinnerinitial.equalsIgnoreCase("Select"))
                Singleton.getInstance().property.setNameOfSeller(nameofseller.trim());
            else
                Singleton.getInstance().property.setNameOfSeller(spinnerinitial + ". " + nameofseller.trim());
        } else {
            String spinnerinitial = spinner_nameofseller.getSelectedItem().toString();
            if (spinnerinitial.contains(".")) {
                spinnerinitial = spinnerinitial.replace(".", ". ");
                //spinnerinitial = spinnerinitial + ". ";
            }
            if (spinnerinitial.equalsIgnoreCase("Select"))
                Singleton.getInstance().property.setNameOfSeller("");
            else
                Singleton.getInstance().property.setNameOfSeller(spinnerinitial);
        }
        Log.e("other_save", "nameofseller: " + Singleton.getInstance().property.getNameOfSeller());*/


        // More Info
        String unitno = editText_unitno.getText().toString();
        if (!general.isEmpty(unitno)) {
            Singleton.getInstance().property.setUnitNo(unitno.trim());
        } else {
            Singleton.getInstance().property.setUnitNo("");
        }
        Log.e("other_save", "unitno: " + Singleton.getInstance().property.getUnitNo());
        // More Info
        String ctsno = editText_ctsno.getText().toString();
        if (!general.isEmpty(ctsno)) {
            Singleton.getInstance().property.setCtsNo(ctsno.trim());
        } else {
            Singleton.getInstance().property.setCtsNo("");
        }
        Log.e("other_save", "ctsno: " + Singleton.getInstance().property.getCtsNo());
        // More Info
        String colony_name = editText_colony_name.getText().toString();
        if (!general.isEmpty(colony_name)) {
            Singleton.getInstance().property.setColonyName(colony_name.trim());
        } else {
            Singleton.getInstance().property.setColonyName("");
        }
        Log.e("other_save", "colony_name: " + Singleton.getInstance().property.getColonyName());
        // More Info - wingno
        String wingno = editText_wingno.getText().toString();
        if (!general.isEmpty(wingno)) {
            Singleton.getInstance().indProperty.setWingNo(Integer.parseInt(wingno));
        }
        Log.e("other_save", "wingno: " + Singleton.getInstance().indProperty.getWingNo());
        // More Info - year_of_current_tenancy
        String year_of_current_tenancy = editText_year_of_current_tenancy.getText().toString();
        if (!general.isEmpty(year_of_current_tenancy)) {
            Singleton.getInstance().indProperty.setYearsOfCurrentTenancy(year_of_current_tenancy.trim());
        } else {
            Singleton.getInstance().indProperty.setYearsOfCurrentTenancy("");
        }
        Log.e("other_save", "year_of_current_tenancy: " + Singleton.getInstance().indProperty.getYearsOfCurrentTenancy());
        // More Info - Amenities
        String amenities = editText_amenities.getText().toString();
        if (!general.isEmpty(amenities)) {
            Singleton.getInstance().indProperty.setAmenities(amenities.trim());
        } else {
            Singleton.getInstance().indProperty.setAmenities("");
        }
        Log.e("other_save", "amenities: " + Singleton.getInstance().indProperty.getAmenities());
        // More Info - rentalincome
        String rentalincome = editText_rentalincome.getText().toString();
        if (!general.isEmpty(rentalincome)) {
            Singleton.getInstance().indProperty.setRentalIncome(rentalincome.trim());
        } else {
            Singleton.getInstance().indProperty.setRentalIncome("");
        }
        Log.e("other_save", "rentalincome: " + Singleton.getInstance().indProperty.getRentalIncome());

        // More Info - nameoftenant
        String nameoftenant = editText_nameoftenant.getText().toString();
        if (!general.isEmpty(nameoftenant)) {
            Singleton.getInstance().indProperty.setNameOfTenant(nameoftenant.trim());
        } else {
            Singleton.getInstance().indProperty.setNameOfTenant("");
        }
        Log.e("other_save", "nameoftenant: " + Singleton.getInstance().indProperty.getNameOfTenant());

        // More Info - wing_name
        String wing_name = editText_wing_name.getText().toString();
        if (!general.isEmpty(wing_name)) {
            Singleton.getInstance().indProperty.setWingName(wing_name.trim());
        } else {
            Singleton.getInstance().indProperty.setWingName("");
        }
        Log.e("other_save", "wing_name: " + Singleton.getInstance().indProperty.getWingName());
        // More Info - floor_height
        String floor_height = editText_floor_height.getText().toString();
        if (!general.isEmpty(floor_height)) {
            Singleton.getInstance().indProperty.setFloorHeight(floor_height.trim());
        } else {
            Singleton.getInstance().indProperty.setFloorHeight("");
        }
        Log.e("other_save", "floor_height: " + Singleton.getInstance().indProperty.getFloorHeight());

        Log.e("other_save", "getIntPop: " + Singleton.getInstance().indProperty.getIntPop());
        Log.e("other_save", "getGardenExist: " + Singleton.getInstance().indProperty.getGardenExist());
        Log.e("other_save", "getSeperateCompoundId: " + Singleton.getInstance().indProperty.getSeperateCompoundId());

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v == spinner_condition_approach || v == spinner_select_localitycate || v == spinner_select_class || v == spinner_select_tenure_ownership || v == spinner_landapproval || v == spinner_qualityofconstruction || v == spinner_typeofbuilding || v == spinner_maintenanceofbuilding || v == spinner_qualityoffittings || v == spinner_marketability || v == spinner_passagecorridorschowklobby || v == spinner_typeoflocality || v == spinner_typeoflocality_for_land || v == spinner_bathflooring || v == spinner_amenitiesquality || v == spinner_kitchentype || v == spinner_kitchenshape || v == spinner_nameofseller || v == spinner_nameofvendor) {
            hideSoftKeyboard(spinner_condition_approach);
        }
        return false;
    }

    private void show_emptyFocus() {
        // Show focus
        if (OtherDetails_ka.my_focuslayout != null) {
            OtherDetails_ka.my_focuslayout.requestFocus();
        }
    }


    private void call_photo_function() {
        // check offline or not
        if (!is_offline) {
            // online
            if (Connectivity.isConnected(getActivity())) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        JSONArray jsonArray = new JSONArray();
                        for (int x = 0; x < PhotoLatLong_ka.GetPhoto_list_response.size(); x++) {
                            if (!PhotoLatLong_ka.GetPhoto_list_response.get(x).isDefaultimage()) {
                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("ID", PhotoLatLong_ka.GetPhoto_list_response.get(x).getId());
                                    jsonObject.put("PropertyId", PhotoLatLong_ka.GetPhoto_list_response.get(x).getPropertyId());
                                    if (!general.isEmpty(PhotoLatLong_ka.GetPhoto_list_response.get(x).getTitle())) {
                                        jsonObject.put("Title", PhotoLatLong_ka.GetPhoto_list_response.get(x).getTitle());
                                    } else {
                                        jsonObject.put("Title", "");
                                    }
                                    jsonObject.put("Logo", PhotoLatLong_ka.GetPhoto_list_response.get(x).getLogo());
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                                jsonArray.put(jsonObject);
                            }
                        }
                        if (jsonArray.length() > 0) {
                            UploadImage(jsonArray.toString());
                        } else {
                            // hit_photo_api > false
                            Singleton.getInstance().hit_photo_api = false;
                            // TODO - Final Api
                            InitiateSaveCaseInspectionTask();
                        }
                    }
                }, 500);
            } else {
                // hit_photo_api > false
                Singleton.getInstance().hit_photo_api = false;
                // TODO - Final Api
                InitiateSaveCaseInspectionTask();
            }
        } else {
            // offline
            // Room - delete lat long
            appDatabase.interfaceLatLongQuery().deleteRow(caseid_int);
            LatLongDetails latLongDetails = new LatLongDetails();
            latLongDetails.setCaseId(caseid_int);
            latLongDetails.setLatLongDetails(Singleton.getInstance().latlng_details);
            // Room - add lat long
            appDatabase.interfaceLatLongQuery().insertAll(latLongDetails);
            // Photo delete and add
            save_image_offline("next_btn");
        }

    }


    private void UploadImage(String s) {

        JSONObject jsonObject = new JSONObject();
        String PropertyId = SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, "");
        if (general.isEmpty(PropertyId)) {
            PropertyId = "0";
        }
        try {
            jsonObject.put("PropertyId", PropertyId);
            jsonObject.put("LatLongDetails", Singleton.getInstance().latlng_details);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        JSONObject edit_synk_obj = new JSONObject();
        try {
            edit_synk_obj.put("PropertyId", PropertyId);
            edit_synk_obj.put("is_sync", false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonRequestData data = new JsonRequestData();
        // set Photo
        data.setCompanyName(s);
        // set the lat and long
        data.setContactPersonName(jsonObject.toString());
        // set edit_synk
        data.setApplicantName(edit_synk_obj.toString());

        data.setUrl(general.ApiBaseUrl() + SettingsUtils.IMAGE);
        data.setRequestBody(RequestParam.uploadimageRequestParams(data));

        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(getActivity(), data, SettingsUtils.POST);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                parseuploadimageResponse(requestData.getResponse());
            }
        });
        webserviceTask.execute();
    }

    private void parseuploadimageResponse(String response) {

        // hit_photo_api > false
        Singleton.getInstance().hit_photo_api = false;
        // TODO - Final Api
        InitiateSaveCaseInspectionTask();

    }

    private void save_image_offline(final String btn_is) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
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

                // hit_photo_api > false
                Singleton.getInstance().hit_photo_api = false;
                // TODO - Final Api
                InitiateSaveCaseInspectionTask();

            }
        }, 100);
    }


    /*********
     * Show Network Listener for Connectivity changes
     * from broadcast Receiver********/
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        /*if (!isConnected) {

            if (!Singleton.getInstance().networkListenerAlert) {
                //show a No Internet Alert or Dialog
                showNoConnectionDialog(getActivity());

                *//* This(NTW_LISTENER_POPUP_COUNT) has been added where the popup is forced
                once user cancel on first popup, so while turn on or off network it again
                 shows a sec that popup alert for offline, so @next time it shouldnot needed to
                 store the value to session again, since the value may be empty @any singleton instance.*//*
                int value = Singleton.getInstance().NTW_LISTENER_POPUP_COUNT;
                if (Singleton.getInstance().NTW_LISTENER_POPUP_COUNT == 0) {
                    *//***************Session storage is very important***************************//*
                    if (save_type.equalsIgnoreCase("")) {
                        Log.e("Save Btn not clicked", "null returns");
                        checkPropertyTypesavedtoSingleton();
                        *//********Room Persistance Library Database Storage*********//*
                        new DataBaseSyncTask().execute();
                    } else {
                        *//*****Optional for getting input or entered values to Singleton store******//*
                        checkPropertyTypesavedtoSingleton();
                        general.hideloading();
                        *//********Room Persistance Library Database Storage*********//*
                        new DataBaseSyncTask().execute();
                    }
                    *//******************************************//*
                } else {
                    general.hideloading();
                }
            }
        } else {

            if (alert_show != null) {
                if (alert_show.isShowing()) {
                    Singleton.getInstance().networkListenerAlert = false;
                    Singleton.getInstance().NTW_LISTENER_POPUP_COUNT = 0;
                    alert_show.dismiss();
                }
            }

            // dismiss the dialog or refresh the activity

            *//******************Session Retrieve to API************************//*
            if (save_type.equalsIgnoreCase("")) {
                Log.e("Save Btn not clicked", "null on dialog dismiss");
                *//*******Retrieve room db to singleton************//*
                new DataBaseRetrieveSyncTask().execute();
            } else {
                *//*******Retrieve room db to singleton************//*
                new DataBaseRetrieveSyncTask().execute();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SaveFormDetails();
                    }
                }, 1500);

            }
            *//******************************************//*
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();

        // register connection status listener
        MyApplication.getInstance().setConnectionListener(this);

/*
        if (General.issecondtab_active) {

            initiateViewsLoading();
        }*/
    }

    private void checkConnection() {
        boolean isConnected = ConnectionReceiver.isConnected();
        if (!isConnected) {
            //show a No Internet Alert or Dialog
            showNoConnectionDialog(getActivity());
        }
    }

    /*******No internet Connectivity*******/
    public void showNoConnectionDialog(final Context context) {
        Singleton.getInstance().networkListenerAlert = true;

        alert_build = new android.app.AlertDialog.Builder(context);
        alert_build.setTitle("Network Information");
        alert_build.setMessage("Please check your Internet connection");
        alert_build.setCancelable(false);
        alert_build.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /* Go to Settings page Intent */
                Intent i = new Intent(Settings.ACTION_SETTINGS);
                context.startActivity(i);
                // dialog.dismiss();
                Singleton.getInstance().networkListenerAlert = false;

            }
        });
        alert_build.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Singleton.getInstance().networkListenerAlert = false;
            }
        });
        alert_show = alert_build.create();
        alert_show.show();
    }

    /********
     * Ended the network listener code changes for this fragment class
     * ********/

    private void checkPropertyTypesavedtoSingleton() {
        if (property_type.equalsIgnoreCase("building")) {
            Singleton.getInstance().latlng_details = str_latvalue + ":" + str_longvalue;
            Singleton.getInstance().aCase.setStatus(Integer.valueOf(getResources().getString(R.string.edit_inspection)));
            //submitAddressForm();
            getProximityListData();
            getIndPropertyFloorListData();
            getAddressDetailsInputData();
            getBuildingFragmentDisplay();
            getMoreRemarks();
            getBrokerdetails();

            /* valuation - Building */
            FragmentValuationBuilding_ka fragmentValuationBuilding = new FragmentValuationBuilding_ka();
            fragmentValuationBuilding.save_landval();
            FragmentValuationBuilding_ka.saveIndValuationFloorsCalculation();

        } else if (property_type.equalsIgnoreCase("flat") || property_type.equalsIgnoreCase("penthouse")) {
            Singleton.getInstance().latlng_details = str_latvalue + ":" + str_longvalue;
            Singleton.getInstance().aCase.setStatus(Integer.valueOf(getResources().getString(R.string.edit_inspection)));
            getProximityListData();
            getAddressDetailsInputData();
            getMoreRemarks();
            getBrokerdetails();

            /******Penthouse or flat values*******/
            FragmentFlat_ka fragmentFlat = new FragmentFlat_ka();
            fragmentFlat.PostPentHouseValues();
            /* valuation - penthouse or flat */
            FragmentValuationPenthouse_ka fragmentValuationPenthouse = new FragmentValuationPenthouse_ka();
            fragmentValuationPenthouse.setIndPropertyValuationforPentHouseFlat();

        } else if (property_type.equalsIgnoreCase("land")) {
            Singleton.getInstance().latlng_details = str_latvalue + ":" + str_longvalue;
            Singleton.getInstance().aCase.setStatus(Integer.valueOf(getResources().getString(R.string.edit_inspection)));
            getProximityListData();
            getAddressDetailsInputData();
            getMoreRemarks();
            getBrokerdetails();

            FragmentLand_ka fragmentLand = new FragmentLand_ka();
            fragmentLand.PostLandValues();

            /* valuation - Land */
            FragmentValuationLand_ka fragmentValuationLand = new FragmentValuationLand_ka();
            fragmentValuationLand.save_landval();
        }
    }

    /**************
     * Store the Data when it gets Offline to Room Persisitance Library
     * Database
     * ******************/
    public class DataBaseSyncTask extends AsyncTask<Void, String, List<CaseDetail>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Perform pre-adding operation here.
            general.showloading(getActivity());
        }

        @Override
        protected List<CaseDetail> doInBackground(Void... voids) {

            appDatabase.daoAccess().insertCase(createorEditCase());

            List<CaseDetail> val = appDatabase.daoAccess().getallDetails();

            return val;
        }

        @Override
        protected void onPostExecute(List<CaseDetail> sCaseDetailList) {
            super.onPostExecute(sCaseDetailList);
            general.hideloading();

            for (CaseDetail caseDetail : sCaseDetailList) {
                String strName = caseDetail.getName();
                String strCase = caseDetail.getCaseid();
                // general.CustomToast(strName + ", case:" + strCase);
            }

        }
    }

    /*********
     * Insert the Case Edit or Save details to Room Persistance Library
     * ***********/
    private CaseDetail createorEditCase() {

        if (Singleton.getInstance().NTW_LISTENER_POPUP_COUNT == 0)
            Singleton.getInstance().NTW_LISTENER_POPUP_COUNT = Singleton.getInstance().NTW_LISTENER_POPUP_COUNT + 1;
        JSONArray IndPropertyFloorsObj = null, proximityObj = null, IndPropertyFloorsValuationObj = null;
        JSONObject caseObj = null, propertyObj = null;
        JSONObject indpropertyObj = null, IndPropertyValuationObj = null;
        JSONObject mainObj = new JSONObject();
        CaseDetail p = new CaseDetail();
        try {
            Gson gson = new Gson();
            String caseJsonObj = gson.toJson(Singleton.getInstance().aCase);
            String propertyJsonObj = gson.toJson(Singleton.getInstance().property);
            String indpropertyJsonObj = gson.toJson(Singleton.getInstance().indProperty);
            String IndPropertyValuationJsonObj = gson.toJson(Singleton.getInstance().indPropertyValuation);
            String IndPropertyfloorJsonObj = gson.toJson(Singleton.getInstance().indPropertyFloors);
            String IndPropertyfloorValuationJsonObj = gson.toJson(Singleton.getInstance().indPropertyFloorsValuations);
            String proximityJsonObj = gson.toJson(Singleton.getInstance().proximities);

            String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

            /********Store the data to Room Persistance Library insert setvalues*******/
            p.setName(caseid);
            p.setCaseid(caseid);
            p.setCaseObj(caseJsonObj);
            p.setPropertyObj(propertyJsonObj);
            p.setIndpropertyObj(indpropertyJsonObj);
            p.setIndpropertyvaluationObj(IndPropertyValuationJsonObj);
            p.setIndpropertyfloorsObj(IndPropertyfloorJsonObj);
            p.setIndpropertyfloorsvaluationObj(IndPropertyfloorValuationJsonObj);
            p.setProximityObj(proximityJsonObj);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return p;
    }

    /**************
     * Store the Data when it gets Offline to Room Persisitance Library
     * Database
     * ******************/
    public class DataBaseRetrieveSyncTask extends AsyncTask<Void, String, CaseDetail> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Perform pre-adding operation here.
            general.showloading(getActivity());
        }

        @Override
        protected CaseDetail doInBackground(Void... voids) {
            String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

            CaseDetail val = appDatabase.daoAccess().getCaseByID(caseid);

            return val;
        }

        @Override
        protected void onPostExecute(CaseDetail sCaseDetail) {
            super.onPostExecute(sCaseDetail);
            general.hideloading();

           /* final CaseDetail caseDetail = sCaseDetail;
            SaveCaseInspectionRetreiveRoomSessionTask(caseDetail);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    new DataBaseDeleteSyncTask(caseDetail).execute();
                }
            }, 500);*/

        }
    }

    /**************
     * Store the Data when it gets Offline to Room Persisitance Library
     * Database
     * ******************/
    public class DataBaseDeleteSyncTask extends AsyncTask<Void, String, CaseDetail> {
        CaseDetail caseDetail;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Perform pre-adding operation here.
            general.showloading(getActivity());
        }

        DataBaseDeleteSyncTask(CaseDetail casedetail) {
            caseDetail = casedetail;
        }

        @Override
        protected CaseDetail doInBackground(Void... voids) {
            String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

            appDatabase.daoAccess().deleteCase(caseDetail);

            List<CaseDetail> val = appDatabase.daoAccess().getallDetails();
            appDatabase.daoAccess().deleteAllCase(val);

            return null;
        }

        @Override
        protected void onPostExecute(CaseDetail sCaseDetail) {
            super.onPostExecute(sCaseDetail);
            general.hideloading();
            // save_type = "";
            // general.CustomToast("Deleted local data");

        }

    }


    private void save_loading_hide() {
        if (save_type.equalsIgnoreCase("savego")) {
            Singleton.getInstance().openCaseList.clear();
            Singleton.getInstance().closeCaseList.clear();
            startActivity(new Intent(getContext(), HomeActivity.class));
        }
        general.hideloading();
    }

    private void check_imageMeasurment_upload() {
        if (Singleton.getInstance().GetImage_list_flat.size() > 0) {
            if (Singleton.getInstance().GetImage_list_flat.get(0).getId() == 0) {
                // New Image Added
                upload_image_measurment();
            } else {
                save_loading_hide();
            }
        } else {
            save_loading_hide();
        }
    }

    private void upload_image_measurment() {
        JSONArray jsonArray = new JSONArray();
        if (Singleton.getInstance().GetImage_list_flat.size() > 0) {
            if (Singleton.getInstance().GetImage_list_flat.get(0).getId() == 0) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("ID", Singleton.getInstance().GetImage_list_flat.get(0).getId());
                    jsonObject.put("PropertyId", Singleton.getInstance().GetImage_list_flat.get(0).getPropertyId());
                    if (!general.isEmpty(Singleton.getInstance().GetImage_list_flat.get(0).getTitle())) {
                        jsonObject.put("Title", Singleton.getInstance().GetImage_list_flat.get(0).getTitle());
                    } else {
                        jsonObject.put("Title", "");
                    }
                    jsonObject.put("Logo", Singleton.getInstance().GetImage_list_flat.get(0).getLogo());
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                jsonArray.put(jsonObject);
                JsonRequestData data = new JsonRequestData();
                // set Photo
                data.setCompanyName(jsonArray.toString());

                JSONObject edit_synk_obj = new JSONObject();
                try {
                    edit_synk_obj.put("PropertyId", PropertyId_is);
                    edit_synk_obj.put("is_sync", false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // set sync
                data.setApplicantName(edit_synk_obj.toString());

                data.setUrl(general.ApiBaseUrl() + SettingsUtils.IMAGE);
                data.setRequestBody(RequestParam.uploadimageRequestParams_oneImage(data));
                WebserviceCommunicator webserviceTask = new WebserviceCommunicator(getActivity(), data, SettingsUtils.POST);
                webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                    @Override
                    public void onTaskComplete(JsonRequestData requestData) {
                        // Hide the loading
                        save_loading_hide();
                        if (!save_type.equalsIgnoreCase("savego")) {
                            // run in Background
                            get_image();
                        }
                    }
                });
                webserviceTask.execute();
            }
        }
    }

    private void get_image() {
        if (Connectivity.isConnected(getActivity())) {
            JsonRequestData data = new JsonRequestData();
            String PropertyId = SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, "");
            data.setUrl(general.ApiBaseUrl() + SettingsUtils.GETIMAGE_ONE + PropertyId);
            WebserviceCommunicator webserviceTask = new WebserviceCommunicator(getActivity(), data, SettingsUtils.GET);
            webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                @Override
                public void onTaskComplete(JsonRequestData requestData) {
                    parseViewimageResponse_one(requestData.getResponse());
                }
            });
            webserviceTask.execute();
        } else {
            Connectivity.showNoConnectionDialog(getActivity());
            general.hideloading();
        }
    }

    private void parseViewimageResponse_one(String response) {
        DataResponse dataResponse = new DataResponse();
        try {
            if (response != null) {
                JSONObject object = new JSONObject(response);
                dataResponse.status = object.getString("status");
                if (dataResponse.status.equalsIgnoreCase("1")) {
                    JSONArray jsonArray = object.getJSONArray("data");
                    if (jsonArray.length() > 0) {
                        JSONObject IndPropertyFloors_jobj = jsonArray.getJSONObject(0);
                        Gson IndPropertyFloorsgson = new Gson();
                        GetPhoto_measurment getPhoto = null;
                        getPhoto = new GetPhoto_measurment();
                        getPhoto = IndPropertyFloorsgson.fromJson(IndPropertyFloors_jobj.toString(), GetPhoto_measurment.class);
                        // Add
                        ArrayList<GetPhoto_measurment> GetImage_list_flat_response = new ArrayList<>();
                        GetImage_list_flat_response.add(getPhoto);
                        Singleton.getInstance().GetImage_list_flat = GetImage_list_flat_response;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
