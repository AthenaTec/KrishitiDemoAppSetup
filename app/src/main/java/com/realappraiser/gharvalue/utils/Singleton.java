package com.realappraiser.gharvalue.utils;

import android.content.Context;

import com.realappraiser.gharvalue.communicator.DataModel;
import com.realappraiser.gharvalue.model.AmenityQuality;
import com.realappraiser.gharvalue.model.ApproachRoadCondition;
import com.realappraiser.gharvalue.model.Bath;
import com.realappraiser.gharvalue.model.Building;
import com.realappraiser.gharvalue.model.CarParking;
import com.realappraiser.gharvalue.model.Case;
import com.realappraiser.gharvalue.model.CaseOtherDetailsModel;
import com.realappraiser.gharvalue.model.ClassModel;
import com.realappraiser.gharvalue.model.Construction;
import com.realappraiser.gharvalue.model.Document_list;
import com.realappraiser.gharvalue.model.DocumentsSeen;
import com.realappraiser.gharvalue.model.Door;
import com.realappraiser.gharvalue.model.Exterior;
import com.realappraiser.gharvalue.model.FittingQuality;
import com.realappraiser.gharvalue.model.Floor;
import com.realappraiser.gharvalue.model.FloorKind;
import com.realappraiser.gharvalue.model.FloorUsage;
import com.realappraiser.gharvalue.model.GetPhoto;
import com.realappraiser.gharvalue.model.GetPhoto_measurment;
import com.realappraiser.gharvalue.model.ImageBase64;
import com.realappraiser.gharvalue.model.IndProperty;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.model.IndPropertyFloorsValuation;
import com.realappraiser.gharvalue.model.IndPropertyValuation;
import com.realappraiser.gharvalue.model.InternalFloorModel;
import com.realappraiser.gharvalue.model.Kitchen;
import com.realappraiser.gharvalue.model.Kitchentype;
import com.realappraiser.gharvalue.model.Land;
import com.realappraiser.gharvalue.model.Locality;
import com.realappraiser.gharvalue.model.LocalityCategory;
import com.realappraiser.gharvalue.model.Maintenance;
import com.realappraiser.gharvalue.model.Marketablity;
import com.realappraiser.gharvalue.model.Measurements;
import com.realappraiser.gharvalue.model.Nameofpersons;
import com.realappraiser.gharvalue.model.NewImage;
import com.realappraiser.gharvalue.model.OfflineDataModel;
import com.realappraiser.gharvalue.model.Paint;
import com.realappraiser.gharvalue.model.PassageType;
import com.realappraiser.gharvalue.model.Paving;
import com.realappraiser.gharvalue.model.PlanApproval;
import com.realappraiser.gharvalue.model.PresentCondition;
import com.realappraiser.gharvalue.model.PresentlyOccupied;
import com.realappraiser.gharvalue.model.Property;
import com.realappraiser.gharvalue.model.PropertyActualUsage;
import com.realappraiser.gharvalue.model.PropertyIdentificationChannel;
import com.realappraiser.gharvalue.model.Proximity;
import com.realappraiser.gharvalue.model.ProximitySpinner;
import com.realappraiser.gharvalue.model.QualityConstruction;
import com.realappraiser.gharvalue.model.RejectionComment;
import com.realappraiser.gharvalue.model.Remarks;
import com.realappraiser.gharvalue.model.Roof;
import com.realappraiser.gharvalue.model.Structure;
import com.realappraiser.gharvalue.model.Tenure;
import com.realappraiser.gharvalue.model.TypeOfProperty;
import com.realappraiser.gharvalue.model.Typeofcompound;
import com.realappraiser.gharvalue.model.ValuationMethod;
import com.realappraiser.gharvalue.model.WC;
import com.realappraiser.gharvalue.model.WaterAvailability;
import com.realappraiser.gharvalue.model.Window;

import java.util.ArrayList;

/**
 * Created by kaptas on Dec17.
 * singleton class is used for which only one instance can be created.
 * It provides a global point of access this instance for overall application
 */

@SuppressWarnings("ALL")
public class Singleton {
    private static Singleton instance = null;



    //a private constructor so no instances can be made outside this class
    private Singleton() {
    }

    //Everytime you need an instance, call this
    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();

        return instance;
    }

    //Initialize this or any other variables in probably the Application class
    public void init(Context context) {
    }

    /******
     * int variables
     * ******/
    public int POSITION = 0;
    public int NTW_LISTENER_POPUP_COUNT = 0;
    public long current_camera_image = 0;

    /******
     * boolean variables
     * ******/
    public boolean floorFromBackend = false;
    public boolean floorsEntryList = false;
    public boolean floorsDeleteList = false;
    public boolean gps_condition_check = false;
    public boolean networkListenerAlert = false;
    public boolean latlong_valid = false;
    public boolean photo_valid = false;
    public boolean pendingChange = false;


    public static boolean isfirsttab_active = false;
    public static boolean issecondtab_active = false;

    /*******
     * double variables
     * *******/
    public double latitude = 0.0;
    public double longitude = 0.0;

    /******
     * String variables
     * ******/
    public String category_id = "";
    public String status = "";
    public String documentbase64 = "";
    public String document_name = "";
    public String document_tittle = "";
    public String document_header = "";
    public String latlng_details = ":";
    public String propertyId = "";
    public String curent_statusID = "";
    public boolean hit_photo_api = false;

    /******
     * Arraylist variables
     * ******/
    public ArrayList<String> itemList = new ArrayList<>();
    public ArrayList<String> customLift = new ArrayList<>();
    public ArrayList<String> customPassageLobby = new ArrayList<>();
    public ArrayList<String> samplesList = new ArrayList<>();
    public ArrayList<FloorUsage> floor_usage = new ArrayList<>();
    //public ArrayList<String> floor_usage = new ArrayList<String>();
    public ArrayList<String> property_identified = new ArrayList<String>();
    public ArrayList<String> presently_occupied = new ArrayList<String>();

    public ArrayList<DataModel> openCaseList = new ArrayList<>();
    public ArrayList<DataModel> pendingCaseList = new ArrayList<>();
    public ArrayList<DataModel> openCaseListOriginal = new ArrayList<>();
    public ArrayList<DataModel> closeCaseList = new ArrayList<>();
    public ArrayList<OfflineDataModel> offlineCaseList = new ArrayList<>();
    public ArrayList<OfflineDataModel> offlineCaseListOriginal = new ArrayList<>();
    public ArrayList<DataModel> propertyTypeList = new ArrayList<>();
    public ArrayList<Document_list> documentRead = new ArrayList<>();
    public ArrayList<RejectionComment> rejectionComments_list = new ArrayList<>();

    /******Dropdown arraylist values*****/

    public ArrayList<PropertyIdentificationChannel> propertyIdentificationChannels_list = new ArrayList<>();
    public ArrayList<FittingQuality> fittingQualities_List = new ArrayList<>();
    public ArrayList<ApproachRoadCondition> approachRoadConditions_list = new ArrayList<>();
    public ArrayList<Typeofcompound> typeofcompound_list = new ArrayList<>();
    public ArrayList<Remarks> remarks_list = new ArrayList<>();
    public ArrayList<Land> land_list = new ArrayList<>();
    public ArrayList<PresentlyOccupied> presentlyOccupied_list = new ArrayList<>();
    public ArrayList<Locality> localities_list = new ArrayList<>();
    public ArrayList<Tenure> tenures_list = new ArrayList<>();
    public ArrayList<LocalityCategory> localityCategories_list = new ArrayList<>();
    public ArrayList<ClassModel> classes_list = new ArrayList<>();
    public ArrayList<TypeOfProperty> typeOfProperties_list = new ArrayList<>();
    public ArrayList<Floor> floors_list = new ArrayList<>();
    public ArrayList<Kitchentype> kitchens_list = new ArrayList<>();
    public ArrayList<Kitchen> kitchens_shape_list = new ArrayList<>();
    public ArrayList<Window> windows_list = new ArrayList<>();
    public ArrayList<Door> doors_list = new ArrayList<>();
    public ArrayList<WC> wcs_list = new ArrayList<>();
    public ArrayList<Bath> bath_list = new ArrayList<>();
    public ArrayList<Paint> paints_list = new ArrayList<>();
    public ArrayList<Structure> structures_list = new ArrayList<>();
    public ArrayList<Construction> constructions_list = new ArrayList<>();
    public ArrayList<PresentCondition> presentConditions_list = new ArrayList<>();
    public ArrayList<QualityConstruction> qualityConstructions_list = new ArrayList<>();
    public ArrayList<Building> buildings_list = new ArrayList<>();
    public ArrayList<Maintenance> maintenances_list = new ArrayList<>();
    public ArrayList<Exterior> exteriors_list = new ArrayList<>();
    public ArrayList<Paving> pavings_list = new ArrayList<>();
    public ArrayList<Marketablity> marketablities_list = new ArrayList<>();
    public ArrayList<CarParking> carParkings_list = new ArrayList<>();
    public ArrayList<WaterAvailability> waterAvailabilities_list = new ArrayList<>();
    public ArrayList<DocumentsSeen> documentsSeen_list = new ArrayList<>();
    public ArrayList<PlanApproval> planApprovals_list = new ArrayList<>();
    public ArrayList<ValuationMethod> valuationMethods_list = new ArrayList<>();
    public ArrayList<PropertyActualUsage> propertyActualUsages_list = new ArrayList<>();
    public ArrayList<AmenityQuality> amenityQualities_list = new ArrayList<>();
    public ArrayList<Measurements> measurements_list = new ArrayList<>();
    public ArrayList<Measurements> measurements_list_val = new ArrayList<>();
    public ArrayList<Measurements> measurements_list_val_ka = new ArrayList<>();
    public ArrayList<Measurements> measurements_list_val_sqya = new ArrayList<>();
    public ArrayList<FloorKind> floorKind_list = new ArrayList<>();
    public ArrayList<FloorUsage> floorUsages_list = new ArrayList<>();
    public ArrayList<PassageType> passageTypes_list = new ArrayList<>();
    public ArrayList<ProximitySpinner> proximities_list = new ArrayList<>();
    public ArrayList<Roof> roof_list = new ArrayList<>();

    public ArrayList<InternalFloorModel> internalFloorHalldining = new ArrayList<>();
    public ArrayList<InternalFloorModel> internalFloorKitchen = new ArrayList<>();
    public ArrayList<InternalFloorModel> internalFloorBedroom = new ArrayList<>();
    public ArrayList<InternalFloorModel> internalFloorBath = new ArrayList<>();
    public ArrayList<InternalFloorModel> internalFloorshopOffice = new ArrayList<>();

    public ArrayList<Nameofpersons> nameofpersons_list = new ArrayList<>();

    /*********Ended dropdown values******/

    public DataModel updateCaseStatusModel = new DataModel();
    public DataModel updatePropertyTypeStatusModel = new DataModel();
    public DataModel getReportTypeModel = new DataModel();

    /******EditInspection jsonobj or json arraylist values*****/

    public Case aCase = new Case();
    public CaseOtherDetailsModel caseOtherDetailsModel = new CaseOtherDetailsModel();
    public Property property = new Property();
    public IndProperty indProperty = new IndProperty();
    public IndPropertyValuation indPropertyValuation = new IndPropertyValuation();
    public ArrayList<IndPropertyFloor> indPropertyFloors = new ArrayList<>();
    public ArrayList<IndPropertyFloorsValuation> indPropertyFloorsValuations = new ArrayList<>();
    public ArrayList<Proximity> proximities = new ArrayList<>();

    /*public ArrayList<IndPropertyValuation> indPropertyValuations = new ArrayList<>();*/

    public ArrayList<GetPhoto> GetPhoto_list_final = new ArrayList<>();
    public ArrayList<ImageBase64> base64image = new ArrayList<>();
    public ArrayList<String> imageID = new ArrayList<>();
    public ArrayList<NewImage> newimage = new ArrayList<>();


    public ArrayList<String> lift_list = new ArrayList<>();
    public ArrayList<Integer> interior_flooring_id = new ArrayList<>();
    public ArrayList<String> interior_flooring_name = new ArrayList<>();
    public ArrayList<Integer> interior_roofing_id = new ArrayList<>();
    public ArrayList<String> interior_roofing_name = new ArrayList<>();
    public ArrayList<Integer> interior_paint_id = new ArrayList<>();
    public ArrayList<String> interior_paint_name = new ArrayList<>();
    public ArrayList<Integer> interior_door_id = new ArrayList<>();
    public ArrayList<String> interior_door_name = new ArrayList<>();
    public ArrayList<Integer> interior_window_id = new ArrayList<>();
    public ArrayList<String> interior_window_name = new ArrayList<>();
    public ArrayList<Integer> exter_stru_id = new ArrayList<>();
    public ArrayList<String> exter_stru_name = new ArrayList<>();
    public ArrayList<Integer> exter_paint_id = new ArrayList<>();
    public ArrayList<String> exter_paint_name = new ArrayList<>();

    public ArrayList<Integer> PropertyActualUsage_id = new ArrayList<>();
    public ArrayList<String> PropertyActualUsage_name = new ArrayList<>();

    public ArrayList<Integer> PropertyIdentificationChannel_id = new ArrayList<>();
    public ArrayList<String> PropertyIdentificationChannel_name = new ArrayList<>();
    public ArrayList<Integer> PresentlyOccupied_id = new ArrayList<>();
    public ArrayList<String> PresentlyOccupied_name = new ArrayList<>();
    public ArrayList<Integer> Remarks_Id = new ArrayList<>();
    public ArrayList<Integer> WaterAvailability_id = new ArrayList<>();
    public ArrayList<String> WaterAvailability_name = new ArrayList<>();
    public ArrayList<Integer> WC_id = new ArrayList<>();
    public ArrayList<String> WC_name = new ArrayList<>();
    public ArrayList<Integer> CarParking_id = new ArrayList<>();
    public ArrayList<String> CarParking_name = new ArrayList<>();
    public ArrayList<String> lobby_list = new ArrayList<>();

    public ArrayList<Integer> Paving_id = new ArrayList<>();
    public ArrayList<String> Paving_name = new ArrayList<>();


    public ArrayList<Integer> FloorUsage_id = new ArrayList<>();
    public ArrayList<String> FloorUsage_name = new ArrayList<>();

    public static boolean is_new_floor_created = false;
    public static boolean as_per_com = true;
    public static boolean as_deper_first_time = true;
    // Set as true for General Info
    public static boolean is_edit_floor_satge = false;
    public static boolean is_edit_floor_docarea = false;
    public static boolean is_edit_floor_age = false;
    // Set as false for Internal composition
    public static boolean is_edit_floor_hall = false;
    public static boolean is_edit_floor_kitchen = false;
    public static boolean is_edit_floor_bedroom = false;
    public static boolean is_edit_floor_bath = false;
    public static boolean is_edit_floor_shop = false;
    public static boolean enable_validation_error = false;
    // Set as false for Internal composition
    public static boolean is_edit_floor_dinning = false;
    public static boolean is_edit_floor_wc = false;
    public static boolean is_edit_floor_attachbath = false;
    public static boolean is_edit_floor_balcony = false;
    public static boolean is_edit_floor_fbs = false;
    public static boolean is_edit_floor_dbs = false;
    public static boolean is_edit_floor_terrace = false;
    public static boolean is_edit_floor_passage = false;



    /********Valuation data********/


    /* Pent house */
    public ArrayList<String> areaType = new ArrayList<>();
    public ArrayList<Measurements> measurements_list_flat = new ArrayList<>();

    public ArrayList<String> CheckboxSelection = new ArrayList<>();
    public ArrayList<Integer> mCheckPosition = new ArrayList<Integer>();
    public ArrayList<String> AddSelectValue = new ArrayList<String>();

    public boolean PROPERTY_TYPE_UPDATED = false;
    public String propertyupdate_caseid = "";
    public String call_offline_tab = "";

    public boolean map_activity_click = false;
    public String map_activity_lat = "";
    public String map_activity_long = "";

    public ArrayList<GetPhoto_measurment> GetImage_list_flat = new ArrayList<>();



}
