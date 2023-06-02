package com.realappraiser.gharvalue.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.realappraiser.gharvalue.AppDatabase;
import com.realappraiser.gharvalue.MyApplication;
import com.realappraiser.gharvalue.R;
import com.realappraiser.gharvalue.activities.HomeActivity;
import com.realappraiser.gharvalue.activities.PdfViewer;
import com.realappraiser.gharvalue.activities.PhotoLatLngTab;
import com.realappraiser.gharvalue.communicator.DataModel;
import com.realappraiser.gharvalue.communicator.DataResponse;
import com.realappraiser.gharvalue.communicator.JsonRequestData;
import com.realappraiser.gharvalue.communicator.RequestParam;
import com.realappraiser.gharvalue.communicator.ResponseParser;
import com.realappraiser.gharvalue.communicator.TaskCompleteListener;
import com.realappraiser.gharvalue.communicator.WebserviceCommunicator;
import com.realappraiser.gharvalue.model.Case;
import com.realappraiser.gharvalue.model.Document_list;
import com.realappraiser.gharvalue.model.GetPhoto;
import com.realappraiser.gharvalue.model.GetPhoto_measurment;
import com.realappraiser.gharvalue.model.IndProperty;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.model.IndPropertyFloorsValuation;
import com.realappraiser.gharvalue.model.IndPropertyValuation;
import com.realappraiser.gharvalue.model.LatLongDetails;
import com.realappraiser.gharvalue.model.OfflineDataModel;
import com.realappraiser.gharvalue.model.Property;
import com.realappraiser.gharvalue.model.PropertyUpdateRoomDB;
import com.realappraiser.gharvalue.model.Proximity;
import com.realappraiser.gharvalue.model.RejectionComment;
import com.realappraiser.gharvalue.model.TypeOfProperty;
import com.realappraiser.gharvalue.utils.Connectivity;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.itangqi.waveloadingview.WaveLoadingView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by kaptas on 22/2/18.
 */

public class OfflineCaseAdapter extends RecyclerView.Adapter<OfflineCaseAdapter.ViewHolder> {

    private ArrayList<OfflineDataModel> dataModels;
    private final Activity mContext;
    private General general;
    OfflineDataModel updateCaseStatusModel = new OfflineDataModel();
    OfflineDataModel updatePropertyTypeStatusModel = new OfflineDataModel();
    OfflineDataModel getReportTypeModel = new OfflineDataModel();
    ArrayList<OfflineDataModel> propertyTypeList;
    ArrayList<Document_list> documentRead;

    private String msg = "", info = "", propertyType = "";
    private String case_id = "", property_caseid = "", bank_id = "", reporty_type = "", agencybranchid = "", type_id = "", property_type = "";
    public Dialog dialog;
    OfflineDataModel dataModel = new OfflineDataModel();
    private String document_name = "", document_base64 = "", title = "";

    private static final String TAG = General.class.getSimpleName();
    public String SAMPLE_FILE = "sample.pdf";
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public String property_name_from_type = "";
    public String rejectMsg = "";
    AppDatabase appDatabase;
    public Dialog connectionDialog_circle;
    WaveLoadingView mWaveLoadingView;
    String type_of_sync = "";
    boolean is_edit_synu = false;

    public OfflineCaseAdapter(Activity mContext, ArrayList<OfflineDataModel> dataModels) {
        this.mContext = mContext;
        this.dataModels = dataModels;
        // Room - Get the Open case adapter
        appDatabase = AppDatabase.getAppDatabase(mContext);
    }

    @Override
    public OfflineCaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        general = new General(mContext);
        SettingsUtils.init(mContext);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offine_case_adapter, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"RecyclerView", "SetTextI18n"})
    @Override
    public void onBindViewHolder(final OfflineCaseAdapter.ViewHolder holder, final int position) {
        OfflineDataModel offModel = dataModels.get(position);

        if (General.compareDateTime48hrsOld(offModel.getCreatedOn())>1){
            holder.tvPending.setVisibility(View.VISIBLE);
        }else {
            holder.tvPending.setVisibility(View.GONE);
        }

//        holder.tvPending.setVisibility(View.VISIBLE);


        /*String real_status_id = dataModels.get(position).getStatusId();*/
        String caseid = dataModels.get(position).getCaseId();
        final String real_status_id = UpdateOfflineStatusCaseId(caseid);

        if (real_status_id.equalsIgnoreCase("15")) {
            rejectMsg = UpdateOfflineStatusRejectMsg(caseid, real_status_id);
        }


        final String caseId = dataModels.get(position).getCaseId();
        if (!general.isEmpty(caseId)) {
            holder.case_id.setText("Case Id : " + caseId);
        } else {
            holder.case_id.setText(R.string.dot);
        }

        String applicantName = dataModels.get(position).getApplicantName();
        if (!general.isEmpty(applicantName)) {
            holder.case_person_name.setText(applicantName);
        } else {
            holder.case_person_name.setText(R.string.dot);
        }

        String bankName = dataModels.get(position).getBankName();
        if (!general.isEmpty(bankName)) {
            holder.case_bank_name.setText(bankName);
        } else {
            holder.case_bank_name.setText(R.string.dot);
        }

        /******Added @jan31****/
        String applicantContactNo = dataModels.get(position).getApplicantContactNo();
        String contactPersonName = dataModels.get(position).getContactPersonName();
        String contactPersonMobile = dataModels.get(position).getContactPersonNumber();
        if (!general.isEmptyString(contactPersonMobile) && !general.isEmptyString(contactPersonName)) {
            holder.case_mobile.setText(contactPersonMobile + " (" + contactPersonName + ")");
            mobileNumberOnClick(holder.case_mobile, position, contactPersonMobile);
        } else if (!general.isEmptyString(applicantContactNo) && !general.isEmptyString(contactPersonName)) {
            holder.case_mobile.setText(applicantContactNo + " (" + contactPersonName + ")");
            mobileNumberOnClick(holder.case_mobile, position, applicantContactNo);
        } else if (!General.isEmptyString(contactPersonMobile)) {
            holder.case_mobile.setText(contactPersonMobile);
            mobileNumberOnClick(holder.case_mobile, position, contactPersonMobile);
        } else if (!General.isEmptyString(applicantContactNo)) {
            holder.case_mobile.setText(applicantContactNo);
            mobileNumberOnClick(holder.case_mobile, position, applicantContactNo);
        } else {
            holder.case_mobile.setText(R.string.dot);
        }
        /******Ended @jan31****/

        String propertyAddress = dataModels.get(position).getPropertyAddress();
        if (!general.isEmpty(propertyAddress)) {
            holder.case_addressloc.setText(propertyAddress);
        } else {
            holder.case_addressloc.setText(R.string.dot);
        }


        if (!general.isEmpty(dataModels.get(position).getCreatedOn())) {
            String assigned_date = General.AssignedDate(dataModels.get(position).getCreatedOn());
            String assigned_time = General.AssignedTime(dataModels.get(position).getCreatedOn());
            holder.case_assigned_date.setText(assigned_date);
            holder.case_assigned_time.setText(assigned_time.toUpperCase());
        } else {
            holder.case_assigned_date.setText("");
            holder.case_assigned_time.setText("");
        }

        String updatepropertytype = GetUpdatedPropertyType(caseId);
        String propertyType = dataModels.get(position).getPropertyType();
        if (!general.isEmpty(updatepropertytype)) {
            holder.textview_property_type_heading.setText(updatepropertytype);
        } else {
            holder.textview_property_type_heading.setText(R.string.dot);
        }

        /*******
         * Set the Button field process as per the Real Status
         * *******/
        ChecktoUpdateUIforRealStatus(holder.acceptLay, holder.rejectLay, holder.accept, holder.reject, holder.property_exits_lay, holder.property_exits_text, holder.property_exits, real_status_id, caseid);


        if (real_status_id.equalsIgnoreCase("15") || real_status_id.equalsIgnoreCase("19")) {
            holder.property_exits_lay.setClickable(false);
        }

        /******
         * Property Doesnt exists
         * *****/
        holder.property_exits.setChecked(false);
        holder.property_exits.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if (ischecked) {
                    /* if (Connectivity.isConnected(mContext)) {*/
                    case_id = dataModels.get(position).getCaseId();
                    PropertyExistsRejectPopup(case_id, mContext.getResources().getString(R.string.property_exits), mContext.getResources().getString(R.string.property_exists_text));
                    general.hideloading();
                   /* } else {
                        Connectivity.showNoConnectionDialog(mContext);
                        holder.property_exits.setChecked(false);
                        general.hideloading();
                    }*/
                } else {
                    System.err.println("Doesnt the Property Exists:No");
                }
            }
        });

        /******
         * Accept / Start Button Click Listener
         * ******/
        holder.acceptLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                case_id = dataModels.get(position).getCaseId();
                String property_cate_id = dataModels.get(position).getPropertyCategoryId();

                Singleton.getInstance().propertyId = dataModels.get(position).getPropertyId();
                SettingsUtils.getInstance().putValue(SettingsUtils.CASE_ID, case_id);
                SettingsUtils.getInstance().putValue(SettingsUtils.Case_Title, dataModels.get(position).getApplicantName());
                SettingsUtils.getInstance().putValue(SettingsUtils.Case_Bank, dataModels.get(position).getBankName());
                SettingsUtils.getInstance().putValue(SettingsUtils.PropertyId, dataModels.get(position).getPropertyId());
                SettingsUtils.getInstance().putValue(SettingsUtils.PropertyCategoryId, property_cate_id);
                SettingsUtils.getInstance().putValue(SettingsUtils.PropertyType, dataModels.get(position).getPropertyType());
                SettingsUtils.getInstance().putValue(SettingsUtils.Case_BankBranch, dataModels.get(position).getBankBranchName());
                SettingsUtils.getInstance().putValue(SettingsUtils.is_local, false);

                if (real_status_id.equalsIgnoreCase("12")) {
                    // Assigned - Going to Accept
                    Singleton.getInstance().status = "accept";
                    CallUpdateStatusfromRealStatus(holder.acceptLay, "accept", real_status_id, case_id, property_cate_id);
                } else {
                    // start and edit
                    // offline
                    SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, true);
                    Singleton.getInstance().enable_validation_error = false;

                    // offline - Room
                    final int case_int = Integer.parseInt(case_id);

                    Log.e("size_case", "size_case: " + appDatabase.interfaceCaseQuery().getCase_caseID(case_int).size());

                    if (appDatabase.interfaceCaseQuery().getCase_caseID(case_int).size() > 0) {
                        general.showloading(mContext);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                /*Log.e("appDatabase_size", "interfaceCaseQuery: " + appDatabase.interfaceCaseQuery().getCase_caseID(case_int).size());
                                Log.e("appDatabase_size", "interfacePropertyQuery: " + appDatabase.interfacePropertyQuery().getProperty_caseID(case_int).size());
                                Log.e("appDatabase_size", "interfaceIndpropertyQuery: " + appDatabase.interfaceIndpropertyQuery().getIndProperty_caseID(case_int).size());
                                Log.e("appDatabase_size", "interfaceIndPropertyValuationQuery: " + appDatabase.interfaceIndPropertyValuationQuery().getIndPropertyValuation_caseID(case_int).size());
                                Log.e("appDatabase_size", "interfaceIndPropertyFloorsQuery: " + appDatabase.interfaceIndPropertyFloorsQuery().getIndPropertyFloor_caseID(case_int).size());
                                Log.e("appDatabase_size", "interfaceIndPropertyFloorsValuationQuery: " + appDatabase.interfaceIndPropertyFloorsValuationQuery().getIndPropertyFloorsValuation_caseID(case_int).size());
                                Log.e("appDatabase_size", "interfaceProximityQuery: " + appDatabase.interfaceProximityQuery().getProximity_caseID(case_int).size());*/

                                // Case
                                Singleton.getInstance().aCase = appDatabase.interfaceCaseQuery().getCase_caseID(case_int).get(0);
                                // Property
                                if (appDatabase.interfacePropertyQuery().getProperty_caseID(case_int).size() > 0) {
                                    Singleton.getInstance().property = appDatabase.interfacePropertyQuery().getProperty_caseID(case_int).get(0);
                                }
                                // IndProperty
                                if (appDatabase.interfaceIndpropertyQuery().getIndProperty_caseID(case_int).size() > 0) {
                                    Singleton.getInstance().indProperty = appDatabase.interfaceIndpropertyQuery().getIndProperty_caseID(case_int).get(0);
                                }
                                // IndPropertyValuation
                                if (appDatabase.interfaceIndPropertyValuationQuery().getIndPropertyValuation_caseID(case_int).size() > 0) {
                                    Singleton.getInstance().indPropertyValuation = appDatabase.interfaceIndPropertyValuationQuery().getIndPropertyValuation_caseID(case_int).get(0);
                                }
                                // IndPropertyFloor
                                if (appDatabase.interfaceIndPropertyFloorsQuery().getIndPropertyFloor_caseID(case_int).size() > 0) {
                                    Singleton.getInstance().indPropertyFloors = (ArrayList<IndPropertyFloor>) appDatabase.interfaceIndPropertyFloorsQuery().getIndPropertyFloor_caseID(case_int);
                                } else {
                                    ArrayList<IndPropertyFloor> indPropertyFloors = new ArrayList<>();
                                    Singleton.getInstance().indPropertyFloors = indPropertyFloors;
                                }
                                // IndPropertyFloorsValuation
                                if (appDatabase.interfaceIndPropertyFloorsValuationQuery().getIndPropertyFloorsValuation_caseID(case_int).size() > 0) {
                                    Singleton.getInstance().indPropertyFloorsValuations = (ArrayList<IndPropertyFloorsValuation>) appDatabase.interfaceIndPropertyFloorsValuationQuery().getIndPropertyFloorsValuation_caseID(case_int);
                                } else {
                                    ArrayList<IndPropertyFloorsValuation> indPropertyFloorsValuations = new ArrayList<>();
                                    Singleton.getInstance().indPropertyFloorsValuations = indPropertyFloorsValuations;
                                }
                                // Proximity
                                if (appDatabase.interfaceProximityQuery().getProximity_caseID(case_int).size() > 0) {
                                    Singleton.getInstance().proximities = (ArrayList<Proximity>) appDatabase.interfaceProximityQuery().getProximity_caseID(case_int);
                                } else {
                                    ArrayList<Proximity> proximities = new ArrayList<>();
                                    Singleton.getInstance().proximities = proximities;
                                }
                                // Drop down
                                String dropdown_response = SettingsUtils.getInstance().getValue(SettingsUtils.DropDownSave, "");
                                parseGetDropDownsDataResponse(dropdown_response);

                                boolean real_appraiser_jaipur = SettingsUtils.getInstance().getValue(SettingsUtils.real_appraiser_jaipur, false);
                                if (!real_appraiser_jaipur) {
                                    // Kakode - GetPhoto_measurment
                                    final int PropertyId_is = Integer.parseInt(Singleton.getInstance().propertyId);
                                    if (appDatabase.interfaceGetPhotoMeasurmentQuery().getPhoto_propertyid(PropertyId_is).size() > 0) {
                                        ArrayList<GetPhoto_measurment> dataModel_open = new ArrayList<>();
                                        dataModel_open = (ArrayList<GetPhoto_measurment>) appDatabase.interfaceGetPhotoMeasurmentQuery().getPhoto_propertyid(PropertyId_is);
                                        Singleton.getInstance().GetImage_list_flat = dataModel_open;
                                    } else {
                                        ArrayList<GetPhoto_measurment> dataModel_open = new ArrayList<>();
                                        Singleton.getInstance().GetImage_list_flat = dataModel_open;
                                    }
                                }


                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        general.hideloading();
                                        mContext.startActivity(new Intent(mContext, PhotoLatLngTab.class));
                                    }
                                }, 500);

                            }
                        }, 300);
                    } else {
                        Singleton.getInstance().aCase = new Case();
                        Singleton.getInstance().property = new Property();
                        Singleton.getInstance().indProperty = new IndProperty();
                        Singleton.getInstance().indPropertyValuation = new IndPropertyValuation();
                        Singleton.getInstance().indPropertyFloors = new ArrayList<>();
                        Singleton.getInstance().proximities = new ArrayList<>();
                        Connectivity.showNoConnectionDialog(mContext);
                        general.hideloading();
                    }
                }
            }
        });

        // Todo - cut this line and paste in once the data are saved in others page
        // UpdateOfflineStatusEditcase("2466", "2");

        /******
         * Reject / Edit Button click listener
         * *******/
        holder.rejectLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                case_id = dataModels.get(position).getCaseId();
                SettingsUtils.getInstance().putValue(SettingsUtils.CASE_ID, case_id);

                String rejecttext = holder.reject.getText().toString();
                if (rejecttext.equalsIgnoreCase(mContext.getResources().getString(R.string.sync))) {
                    if (real_status_id.equalsIgnoreCase(mContext.getResources().getString(R.string.reject_status))) { //reject

                        if (Connectivity.isConnected(mContext)) {
                            general.showloading(mContext);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (check_is_property_changed()) {
                                        /* Property type updated */
                                        type_of_sync = "reject";
                                        Getting_propertyChanged_case();
                                    } else {
                                        /* Property type not updated */
                                        UpdateStatusRejectCaseIdWebservice(case_id, real_status_id, rejectMsg);
                                    }
                                }
                            }, 1000);
                        } else {
                            Connectivity.showNoConnectionDialog(mContext);
                            general.hideloading();
                        }

                    } else if (real_status_id.equalsIgnoreCase(mContext.getResources().getString(R.string.property_exits))) {//property doesnt exist

                        if (Connectivity.isConnected(mContext)) {
                            general.showloading(mContext);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (check_is_property_changed()) {
                                        /* Property type updated */
                                        type_of_sync = "property_exits";
                                        Getting_propertyChanged_case();
                                    } else {
                                        /* Property type not updated */
                                        UpdateStatusCaseIdWebservice(case_id, real_status_id);
                                    }
                                }
                            }, 1000);

                        } else {
                            Connectivity.showNoConnectionDialog(mContext);
                            general.hideloading();
                        }

                    } else if (real_status_id.equalsIgnoreCase(mContext.getResources().getString(R.string.edit_inspection))) {//edit_inspection

                        if (Connectivity.isConnected(mContext)) {
                            call_circle_loading_send();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    if (check_is_property_changed()) {
                                        /* Property type updated */
                                        type_of_sync = "start_inspection";
                                        Getting_propertyChanged_case();
                                    } else {
                                        /* Property type not updated */

                                        if (dataModels.get(position).isSync_edit()) {
                                            // TODO - delete the data in server database and hit the insert api
                                            is_edit_synu = true;
                                        } else {
                                            is_edit_synu = false;
                                        }

                                        is_edit_synu = true;


                                        Log.e("isSync_edit", "isSync_edit_offlcaseada : " + dataModels.get(position).isSync_edit());

                                        // send upload api and other data api
                                        String propertyId = dataModels.get(position).getPropertyId();
                                        int case_str = Integer.parseInt(case_id);
                                        int propertyId_str = Integer.parseInt(propertyId);
                                        send_image_data(case_str, propertyId_str);


                                    }

                                }
                            }, 1000);

                        } else {
                            Connectivity.showNoConnectionDialog(mContext);
                            general.hideloading();
                        }

                    }
                } else {
                    if (real_status_id.equalsIgnoreCase("12")) {
                        Singleton.getInstance().status = "reject";
                        CheckSingletonDropdownsData();
                        RejectPopup(case_id, mContext.getResources().getString(R.string.reject_status), mContext.getResources().getString(R.string.reject_text));
                    } else {
                        //PropertyExistsRejectPopup(case_id, mContext.getResources().getString(R.string.send_to_admin), mContext.getResources().getString(R.string.admin_text));
                    }
                }

            }
        });

        /*******
         * Property Type Update Click Listener
         * *****/

        if (real_status_id.equalsIgnoreCase("2")) {
            /* If the case in EDIT mode */
            holder.property_type_update_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    general.CustomToast(mContext.getResources().getString(R.string.property_type_cant));
                }
            });
        } else {
            holder.property_type_update_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (real_status_id.equalsIgnoreCase("15") || (real_status_id.equalsIgnoreCase("19"))) {

                    } else {
                        case_id = dataModels.get(position).getCaseId();
                        SettingsUtils.getInstance().putValue(SettingsUtils.CASE_ID, case_id);
                        String PropertyType_is = dataModels.get(position).getPropertyType();
                        PropertyTypeUpdatePopup_offline(case_id, PropertyType_is);


                        /*bank_id = dataModels.get(position).getBankId();
                        type_id = dataModels.get(position).getTypeID();
                        property_type = GetUpdatedPropertyType(caseId);
                        agencybranchid = dataModels.get(position).getAgencyBranchId();
                        // Offline Property Type list items from Room db
                        InitiateOfflinePropertyType(bank_id, type_id);*/


                    }
                }
            });
        }


        /*******
         * Document Read Click Listener
         * *****/
       /* holder.document_read_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (real_status_id.equalsIgnoreCase("15") || (real_status_id.equalsIgnoreCase("19"))) {

                } else if (real_status_id.equalsIgnoreCase("")) {
                    property_caseid = dataModels.get(position).getCaseId();
                    DocumentReadWebservice(property_caseid);
                }
            }
        });*/

        holder.document_read_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                property_caseid = dataModels.get(position).getCaseId();
                DocumentReadWebservice(property_caseid);
            }
        });

    }

    private void mobileNumberOnClick(final TextView case_mobile, final int position, final String mobilenumber) {
        case_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "tel:" + mobilenumber;
                //String number = "tel:" + dataModels.get(position).getApplicantContactNo();
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(number));
                mContext.startActivity(callIntent);
            }
        });
    }


    private void ChecktoUpdateUIforRealStatus(LinearLayout acceptLay, LinearLayout rejectLay, TextView accept, TextView reject, LinearLayout property_exits_lay, TextView property_exits_text, Switch property_exits, String real_status_id, String case_id) {

        switch (real_status_id) {
            /*********
             * *Assigned Case, i.e Accept will toggle here
             * *********/
            case "12":
                accept.setText(mContext.getResources().getString(R.string.accept));
                reject.setText(mContext.getResources().getString(R.string.reject));
                rejectLay.setVisibility(View.VISIBLE);
                property_exits_text.setVisibility(View.VISIBLE);
                property_exits_lay.setVisibility(View.VISIBLE);
                property_exits.setVisibility(View.VISIBLE);
                property_exits_text.setText(mContext.getResources().getString(R.string.property_exists));
                break;

            /*********
             * *Accepted Case, i.e Start Inspection will toggle here
             * *********/
            case "13":
                accept.setText(mContext.getResources().getString(R.string.startInspection));
                reject.setText(mContext.getResources().getString(R.string.send));
                rejectLay.setVisibility(View.GONE);
//                property_exits_text.setVisibility(View.INVISIBLE);
                property_exits_lay.setVisibility(View.VISIBLE); //GONE
                property_exits.setVisibility(View.INVISIBLE);
                property_exits_text.setText(mContext.getResources().getString(R.string.property_has_exists));
                /*property_heading_type_div.setVisibility(View.VISIBLE);*/
                break;

            /*********
             * *Rejected Case, sync will show here
             * *********/
            case "15":
                acceptLay.setVisibility(View.GONE);
                reject.setText(mContext.getResources().getString(R.string.sync));
                property_exits_lay.setVisibility(View.VISIBLE); //GONE
                property_exits.setVisibility(View.INVISIBLE);
                property_exits_text.setText(mContext.getResources().getString(R.string.caserejected));

                break;

            /*********
             * *Property doesnt exists Case, sync will show here
             * *********/
            case "19":
                acceptLay.setVisibility(View.GONE);
                reject.setText(mContext.getResources().getString(R.string.sync));
                property_exits_lay.setVisibility(View.VISIBLE); //GONE
                property_exits.setVisibility(View.INVISIBLE);
                property_exits_text.setText(mContext.getResources().getString(R.string.property_not_exists));

                break;

            /*********
             * *Edit Inspection, when one or two items entered and
             * saved from detail page
             * *********/
            case "2":
                accept.setText(mContext.getResources().getString(R.string.edit));
                reject.setText(mContext.getResources().getString(R.string.sync));
                reject.setEnabled(true);
                rejectLay.setVisibility(View.VISIBLE);
//                property_exits_text.setVisibility(View.INVISIBLE);
                property_exits_lay.setVisibility(View.VISIBLE); // GONE
                property_exits.setVisibility(View.INVISIBLE);
                property_exits_text.setText(mContext.getResources().getString(R.string.property_has_exists));
                /*  property_heading_type_div.setVisibility(View.VISIBLE);*/
                break;

            /*********
             * *Send to Admin Case, all the mandatory fields entered and
             * saved on detail page, then send to admin
             * *********/
            case "21":
                reject.setText(mContext.getResources().getString(R.string.send));
                rejectLay.setVisibility(View.VISIBLE);
//                property_exits_text.setVisibility(View.INVISIBLE);
                property_exits_lay.setVisibility(View.VISIBLE); // GONE
                property_exits.setVisibility(View.INVISIBLE);
                property_exits_text.setText(mContext.getResources().getString(R.string.property_has_exists));
                /*     property_heading_type_div.setVisibility(View.VISIBLE);*/
                break;
        }
    }


    private void CallUpdateStatusfromRealStatus(LinearLayout acceptrejectLay, String laymode, String real_status_id, String case_id, String property_cate_id) {

        switch (real_status_id) {
            /*********
             * *Assigned Case, i.e Accept will toggle here
             * *********/
            case "12":
                if (Singleton.getInstance().status.equalsIgnoreCase("accept")) {
                    String accept_status_id = mContext.getResources().getString(R.string.accept_status);
                    UpdateOfflineStatusCaseAccept(case_id, accept_status_id);
                    CheckUpdateStatusCaseOffline(accept_status_id);
                    // UpdateStatusCaseIdWebservice(case_id, accept_status_id);
                }

                break;

            /*********
             * *Accepted Case, i.e Start Inspection will toggle here
             * *********/
            case "13":

                if (laymode.equalsIgnoreCase("accept")) {
                    if (property_cate_id.equalsIgnoreCase("2") || property_cate_id.equalsIgnoreCase("4") || property_cate_id.equalsIgnoreCase("1") || property_cate_id.equalsIgnoreCase("3")) {

                        general.CustomToast("Property In Process");

                        /**************
                         * Property type Updated from the Offline room db
                         * is uploaded via API call to change in Online opencase
                         * It is needed for syncing the data after start inspection or editinspection
                         * *****************/
                        // CheckPropertyTypeUpdation();
                        // getCaseDetailsforInspection();
                    } else {
                        general.CustomToast("Property In Process");
                    }
                } else {
                    System.err.print("startinspection" + "sendbtn");
                }

                // general.CustomToast("In Process");
                break;

            /*********
             * *Rejected Case, case item will remove from open list
             * *********/
            case "15":

                break;

            /*********
             * *Edit Inspection, when one or two items entered and
             * saved from detail page
             * *********/
            case "2":
                if (laymode.equalsIgnoreCase("accept")) {
                    if (property_cate_id.equalsIgnoreCase("2") || property_cate_id.equalsIgnoreCase("4") || property_cate_id.equalsIgnoreCase("1") || property_cate_id.equalsIgnoreCase("3")) {
                        //getCaseDetailsforInspection();
                    } else {
                        general.CustomToast("Property In Process");
                    }
                } else if (laymode.equalsIgnoreCase("reject")) {
                    /*String send_status_id = mContext.getResources().getString(R.string.send_to_admin);
                    UpdateStatusCaseIdWebservice(case_id, send_status_id);*/
                }
                //  general.CustomToast("In Process");
                break;

            /*********
             * *Send to Admin Case, all the mandatory fields entered and
             * saved on detail page, then send to admin
             * *********/
            case "21":

                break;
        }
    }

    /**************Property type changes from offline
     *  Room db is updated to Online******************/
    private void CheckPropertyTypeUpdation() {
        if (Connectivity.isConnected(mContext)) {
            UpdatePropertyTypetoOnline();
        } else {
            general.CustomToast("No internet");
        }
    }

    /**************Todo Property type changes from offline roomdb
     *  Room db is updated to Online ******************/
    private void UpdatePropertyTypetoOnline() {
        if (MyApplication.getAppContext() != null) {

            if (appDatabase.propertyUpdateCategory().getPropertyUpdateModel().size() > 0) {
                List<PropertyUpdateRoomDB> updateModel = appDatabase.propertyUpdateCategory().getPropertyUpdateModel();

                ArrayList<PropertyUpdateRoomDB> propertylistOfStrings = new ArrayList<PropertyUpdateRoomDB>(updateModel.size());
                propertylistOfStrings.addAll(updateModel);
                Log.e("update case status", updateModel.size() + "");

                for (int i = 0; i < propertylistOfStrings.size(); i++) {
                    String updatecaseid = propertylistOfStrings.get(i).getCaseid();

                    if (updatecaseid.equalsIgnoreCase(case_id)) {
                        property_type = GetUpdatedPropertyType(case_id);
                        String propertyCategoryId = propertylistOfStrings.get(i).getProperty_category_id();

                        if (Connectivity.isConnected(mContext)) {
                            general.showloading(mContext);
                            //InitiateGetReportPropertyTypeTask(bank_id, agencybranchid, propertyCategoryId);
                        } else {
                            Connectivity.showNoConnectionDialog(mContext);
                            general.hideloading();
                        }

                    }
                }
            }
        }
    }

    /**************
     * Todo Get the Real status id for the status from roomdb
     * on the button action changes
     * ******************/
    private String UpdateOfflineStatusCaseId(String case_id) {
        //make the offline case datamodel to set status
        String value = "";
        if (MyApplication.getAppContext() != null) {
            if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
                value = appDatabase.interfaceOfflineDataModelQuery().getUpdateCaseIdOffline(case_id);
                Log.e("update case status", value + "");
            }
        }
        return value;
    }

    /**************
     * Todo Get the updated property type value from roomdb
     * as per the case ID
     * ******************/
    private String GetUpdatedPropertyType(String case_id) {
        //make the offline case datamodel to set status
        String value = "";
        if (MyApplication.getAppContext() != null) {
            if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
                value = appDatabase.interfaceOfflineDataModelQuery().getUpdatePropertyTypeOffline(case_id);
                Log.e("update case status", value + "");
            }
        }
        return value;
    }

    /**************
     * Todo Get the updated offline reject message as per the status and caseid
     * from the roomdb
     * ******************/
    private String UpdateOfflineStatusRejectMsg(String case_id, String status_id) {
        //make the offline case datamodel to set status
        String value = "";
        if (MyApplication.getAppContext() != null) {
            if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
                if (status_id.equalsIgnoreCase(mContext.getResources().getString(R.string.reject_status))) {
                    value = appDatabase.interfaceOfflineDataModelQuery().getRejectMessagefromOffline(case_id, status_id);
                    Log.e("update case status", value + "");
                } else {
                    Log.e("update case status", value + "empty");
                }

            }
        }
        return value;
    }

    /**************
     * Todo update the offline status case Accept
     * on the button action changes for assigned list of items
     * ******************/
    private void UpdateOfflineStatusCaseAccept(String case_id, String updateCaseStatus) {
        //make the offline case datamodel to set status
        if (MyApplication.getAppContext() != null) {
            if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
                long value = appDatabase.interfaceOfflineDataModelQuery().updateOfflineCaseStatus(updateCaseStatus, case_id);
                Log.e("update case status", value + "");
                // update the case for casemodal
                appDatabase.interfaceCaseQuery().updateCaseStatus(updateCaseStatus, case_id);
            }
        }
    }

    /**************
     * Todo update status reject case to offline as the status is 15
     * on the reject popup yes button click event
     * ******************/
    private void UpdateStatusRejectCaseOffline(String case_id, String statusid, String remarks) {
        //make the offline case datamodel to set status
        if (MyApplication.getAppContext() != null) {
            if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
                String status_id = mContext.getResources().getString(R.string.reject_status);
                long value = appDatabase.interfaceOfflineDataModelQuery().updateOfflineRejectCaseStatus(status_id, remarks, case_id);
                Log.e("update case status", value + "");
            }
        }
    }

    /**************
     * Todo update status property exists to offline as the status is 19
     * on the property exists popup yes button  click event
     * ******************/
    private void UpdateStatusPropertyExistsOffline(String case_id, String status_id) {
        //make the offline case datamodel to set status
        if (MyApplication.getAppContext() != null) {
            if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
                long value = appDatabase.interfaceOfflineDataModelQuery().updateOfflineCaseStatus(status_id, case_id);
                Log.e("update case status", value + "");
            }
        }
    }

    /**************
     * Todo delete the offline case data for status is 15 or 19 after synched to server
     * when the case is rejected or property doesnt exists is synched to server
     * ******************/
    private void DeleteOfflineDatabyCaseID(String case_id) {
        //make the offline case datamodel to delete the case
        if (MyApplication.getAppContext() != null) {
            if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
                int value = appDatabase.interfaceOfflineDataModelQuery().DeleteOfflineDatabyCaseid(case_id);
                Log.e("delete offline case row", value + "");
            }
        }
    }

    /**************
     * Todo get case details for Inspection for edit inspection
     * ******************/
    private void getCaseDetailsforInspection() {
        /********Form Detail intent start activitycall*******/
        Singleton.getInstance().itemList.clear();
        Singleton.getInstance().itemList.add("" + R.drawable.holder);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SettingsUtils.getInstance().putValue(SettingsUtils.CASE_ID, case_id);
                CaseEditInspectionWebservice(case_id); //case_id  2538

            }
        }, 0);
    }


    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    @SuppressWarnings("RedundantCast")
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView case_id, case_person_name, case_mobile, case_addressloc,
                case_bank_name, case_assigned_date, case_assigned_time, property_exits_text;
        private Switch property_exits;
        private LinearLayout acceptLay, rejectLay;
        private LinearLayout property_type_update_lay, document_read_lay, property_heading_type_div, property_exits_lay;
        private TextView accept, reject, textview_property_heading, textview_property_type_heading,tvPending;

        public ViewHolder(View itemView) {
            super(itemView);
            case_id = (TextView) itemView.findViewById(R.id.case_id);
            tvPending = itemView.findViewById(R.id.tvPending);
            case_person_name = (TextView) itemView.findViewById(R.id.case_person_name);
            case_mobile = (TextView) itemView.findViewById(R.id.case_mobile);
            case_addressloc = (TextView) itemView.findViewById(R.id.case_addressloc);
            case_bank_name = (TextView) itemView.findViewById(R.id.case_bank);
            case_assigned_date = (TextView) itemView.findViewById(R.id.case_assigned_date);
            case_assigned_time = (TextView) itemView.findViewById(R.id.case_assigned_time);
            accept = (TextView) itemView.findViewById(R.id.accept);
            reject = (TextView) itemView.findViewById(R.id.reject);
            property_exits_text = (TextView) itemView.findViewById(R.id.property_exits_text);
            acceptLay = (LinearLayout) itemView.findViewById(R.id.acceptLay);
            rejectLay = (LinearLayout) itemView.findViewById(R.id.rejectLay);
            property_heading_type_div = (LinearLayout) itemView.findViewById(R.id.property_heading_type_div);
            property_exits_lay = (LinearLayout) itemView.findViewById(R.id.property_exits_lay);
            property_exits = (Switch) itemView.findViewById(R.id.property_exits);
            property_type_update_lay = (LinearLayout) itemView.findViewById(R.id.property_type_update_lay);
            document_read_lay = (LinearLayout) itemView.findViewById(R.id.document_read_lay);
            textview_property_heading = (TextView) itemView.findViewById(R.id.textview_property_heading);
            textview_property_type_heading = (TextView) itemView.findViewById(R.id.textview_property_type_heading);


            case_id.setTypeface(general.regulartypeface());
            case_person_name.setTypeface(general.regulartypeface());
            case_mobile.setTypeface(general.regulartypeface());
            case_addressloc.setTypeface(general.regulartypeface());
            case_bank_name.setTypeface(general.regulartypeface());
            case_assigned_date.setTypeface(general.regulartypeface());
            case_assigned_time.setTypeface(general.regulartypeface());
            textview_property_heading.setTypeface(general.regulartypeface());
            textview_property_type_heading.setTypeface(general.regulartypeface());
        }
    }


    private void CheckUpdateStatusCaseOffline(String status_id) {
        switch (status_id) {
            /*********
             * *Accepted Case, i.e Start Inspection will toggle here
             * *********/
            case "13":
                AcceptedCasefromRoomDB();
                break;

            /*********
             * *Rejected Case, case item will remove from open list
             * *********/
            case "15":
                RejectPropertyExistsCasefromRoomDB();
                break;

            /*********
             * *Property Doesnt Exists, case item will remove from open list
             * *********/
            case "19":
                RejectPropertyExistsCasefromRoomDB();
                break;

            /*********
             * *Edit Inspection, when one or two items entered and
             * saved from detail page
             * *********/
            case "2":

                break;

            /*********
             * *Send to Admin Case, all the mandatory fields entered and
             * saved on detail page, then send to admin
             * *********/
            case "21":
                general.CustomToast("Succesfully send to Report maker");
                RemoveCasefromList();
                    /*Intent i = new Intent(mContext, HomeActivity.class);
                    mContext.startActivity(i);*/
                break;
        }

    }

    @SuppressLint("SetTextI18n")
    private void CheckUpdateStatusforAction(DataModel updateCaseStatusModel) {

        if (updateCaseStatusModel != null) {
            String status_id = updateCaseStatusModel.getStatus();

            switch (status_id) {
                /*********
                 * *Accepted Case, i.e Start Inspection will toggle here
                 * *********/
                case "13":
                    AcceptedCasefromList();

                    break;

                /*********
                 * *Rejected Case, case item will remove from open list
                 * *********/
                case "15":
                    RemoveCasefromList();
                    break;

                /*********
                 * *Property Doesnt Exists, case item will remove from open list
                 * *********/
                case "19":
                    RemoveCasefromList();
                    break;

                /*********
                 * *Edit Inspection, when one or two items entered and
                 * saved from detail page
                 * *********/
                case "2":

                    break;

                /*********
                 * *Send to Admin Case, all the mandatory fields entered and
                 * saved on detail page, then send to admin
                 * *********/
                case "21":
                    general.CustomToast("Succesfully send to Report maker");
                    RemoveCasefromList();
                    /*Intent i = new Intent(mContext, HomeActivity.class);
                    mContext.startActivity(i);*/
                    break;
            }

            HomeActivity.opened_case.setText(mContext.getResources().getString(R.string.open) + " (" + Singleton.getInstance().openCaseList.size() + ")");
            HomeActivity.closed_case.setText(mContext.getResources().getString(R.string.close) + " (" + Singleton.getInstance().closeCaseList.size() + ")");
        }
    }

    private void RemoveCasefromList() {
        if (Singleton.getInstance().openCaseList != null) {
            if (Singleton.getInstance().openCaseList.size() > 0) {

                String caseid = Singleton.getInstance().updateCaseStatusModel.getCaseId();
                String statusid = Singleton.getInstance().updateCaseStatusModel.getStatus();
                for (int i = 0; i < Singleton.getInstance().openCaseList.size(); i++) {
                    String opencaseid = Singleton.getInstance().openCaseList.get(i).getCaseId();

                    if (caseid.equalsIgnoreCase(opencaseid)) {

                        /*****Rejected case has to add on Closed list*****/
                        if (statusid.equalsIgnoreCase("15") || statusid.equalsIgnoreCase("21")) {
                            Log.e("send_toadmin", "true");
                            DataModel dataModel = Singleton.getInstance().openCaseList.get(i);
                            AddRemovedCasetoClosedList(dataModel);
                        }

                        /*******Remove the rejected and property doesnt exists******/
                        Singleton.getInstance().openCaseList.remove(i);
                    }
                }

                notifyDataSetChanged();
            }
        }
    }

    private void AddRemovedCasetoClosedList(DataModel dataModel) {
        Singleton.getInstance().closeCaseList.add(dataModel);
        notifyDataSetChanged();
    }

    private void AcceptedCasefromRoomDB() {
        notifyDataSetChanged();
    }

    private void RejectPropertyExistsCasefromRoomDB() {
        notifyDataSetChanged();
    }

    private void AcceptedCasefromList() {
        if (Singleton.getInstance().openCaseList != null) {
            if (Singleton.getInstance().openCaseList.size() > 0) {

                String caseid = Singleton.getInstance().updateCaseStatusModel.getCaseId();
                for (int i = 0; i < Singleton.getInstance().openCaseList.size(); i++) {
                    String opencaseid = Singleton.getInstance().openCaseList.get(i).getCaseId();

                    /*if (caseid.equalsIgnoreCase(opencaseid)) {
                        dataModel = Singleton.getInstance().openCaseList.get(i);
                        dataModel.setStatusId(mContext.getResources().getString(R.string.accept_status));
                        Singleton.getInstance().openCaseList.set(i, dataModel);
                    }*/
                }
                notifyDataSetChanged();
            }
        }
    }


    /*******
     * Property exists Dialog
     * *******/
    private void PropertyExistsRejectPopup(final String case_id, final String statusid, String msg) {
        dialog = new Dialog(mContext, R.style.MyThemeDialog2);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.property_exists_popup);

        LinearLayout layout_spinner = (LinearLayout) dialog.findViewById(R.id.layout_spinner);
        layout_spinner.setVisibility(View.GONE);

        TextView popuptitle = (TextView) dialog.findViewById(R.id.title);
        Button yesBtn = (Button) dialog.findViewById(R.id.yesBtn);
        Button noBtn = (Button) dialog.findViewById(R.id.noBtn);
        yesBtn.setTypeface(general.mediumtypeface());
        noBtn.setTypeface(general.mediumtypeface());
        popuptitle.setTypeface(general.mediumtypeface());
        popuptitle.setText(msg);

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // UpdateStatusCaseIdWebservice(case_id, statusid); //Doesn't property exists or reject
                UpdateStatusPropertyExistsOffline(case_id, statusid);
                RejectPropertyExistsCasefromRoomDB();
                dialog.dismiss();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });
        dialog.show();
      /*  Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);*/
    }


    /* Rejection - start */
    private void RejectPopup(final String case_id, final String statusid, String msg) {
        dialog = new Dialog(mContext, R.style.MyThemeDialog2);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.property_exists_popup);

        LinearLayout layout_spinner = (LinearLayout) dialog.findViewById(R.id.layout_spinner);
        final Spinner spinner_reject = (Spinner) dialog.findViewById(R.id.spinner_reject);


        ArrayAdapter<RejectionComment> adapterReject = new ArrayAdapter<RejectionComment>(mContext,
                R.layout.row_spinner_item, Singleton.getInstance().rejectionComments_list);
        adapterReject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_reject.setAdapter(adapterReject);


        TextView popuptitle = (TextView) dialog.findViewById(R.id.title);
        Button yesBtn = (Button) dialog.findViewById(R.id.yesBtn);
        Button noBtn = (Button) dialog.findViewById(R.id.noBtn);
        yesBtn.setTypeface(general.mediumtypeface());
        noBtn.setTypeface(general.mediumtypeface());
        popuptitle.setTypeface(general.mediumtypeface());
        popuptitle.setText(msg);

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = spinner_reject.getSelectedItemPosition();
                if (position == 0) {
                    general.CustomToast("Select the rejection message");
                } else {
                    String remarks = String.valueOf(Singleton.getInstance().rejectionComments_list.get(position).getComment());
                    UpdateStatusRejectCaseOffline(case_id, statusid, remarks);
                    RejectPropertyExistsCasefromRoomDB();
                    //UpdateStatusRejectCaseIdWebservice(case_id, statusid, remarks);
                    dialog.dismiss();
                }

            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });
        dialog.show();
    }

    /*******
     * Property type update Dialogdialog
     * *******/
    private void PropertyTypeUpdatePopup(String msg) {
        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.property_type_popup);

        final RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.property_type_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);

        final PropertyTypeAdapter adapter = new PropertyTypeAdapter(mContext, property_type, property_caseid, Singleton.getInstance().propertyTypeList);
        recyclerView.setAdapter(adapter);
        TextView popuptitle = (TextView) dialog.findViewById(R.id.title);
        Button updateBtn = (Button) dialog.findViewById(R.id.updateBtn);
        Button cancelBtn = (Button) dialog.findViewById(R.id.cancelBtn);
        updateBtn.setTypeface(general.mediumtypeface());
        cancelBtn.setTypeface(general.mediumtypeface());
        popuptitle.setTypeface(general.mediumtypeface());
        popuptitle.setText(msg);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                propertyType = adapter.getSelectedItem();

                if (Singleton.getInstance().propertyTypeList.size() > 0) {
                    for (int x = 0; x < Singleton.getInstance().propertyTypeList.size(); x++) {
                        Singleton.getInstance().propertyTypeList.get(x).getName();
                        if (propertyType == Singleton.getInstance().propertyTypeList.get(x).getTypeOfPropertyId()) {
                            property_name_from_type = Singleton.getInstance().propertyTypeList.get(x).getName();
                        }
                    }
                }

                if (propertyType.equalsIgnoreCase("")) {
                    general.CustomToast("Select property type");
                } else {
                    UpdateOfflinePropertyTypeCase(propertyType, bank_id, agencybranchid);

                    //GetReportPropertyTypeWebservice(propertyType, bank_id, agencybranchid);
                    dialog.dismiss();
                }

            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });
        dialog.show();
      /*  Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);*/
    }

    private void PropertyTypeUpdatePopup_offline(final String case_id, String PropertyType_is) {
        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.property_type_popup);

        final RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.property_type_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<TypeOfProperty> typeOfProperties_list = (ArrayList<TypeOfProperty>) appDatabase.typeofPropertyQuery().getTypeofPropertyModels();

        final PropertyTypeAdapter_offline adapter = new PropertyTypeAdapter_offline(mContext, PropertyType_is, typeOfProperties_list);
        recyclerView.setAdapter(adapter);
        TextView popuptitle = (TextView) dialog.findViewById(R.id.title);
        Button updateBtn = (Button) dialog.findViewById(R.id.updateBtn);
        Button cancelBtn = (Button) dialog.findViewById(R.id.cancelBtn);
        updateBtn.setTypeface(general.mediumtypeface());
        cancelBtn.setTypeface(general.mediumtypeface());
        popuptitle.setTypeface(general.mediumtypeface());
        popuptitle.setText(mContext.getResources().getString(R.string.property_type) + case_id);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //propertyType = adapter.getSelectedItem();



               /* if (Singleton.getInstance().propertyTypeList.size() > 0) {
                    for (int x = 0; x < Singleton.getInstance().propertyTypeList.size(); x++) {
                        Singleton.getInstance().propertyTypeList.get(x).getName();
                        if (propertyType == Singleton.getInstance().propertyTypeList.get(x).getTypeOfPropertyId()) {
                            property_name_from_type = Singleton.getInstance().propertyTypeList.get(x).getName();
                        }
                    }
                }*/

                TypeOfProperty typeOfProperty = new TypeOfProperty();
                typeOfProperty = adapter.typeOfProperty();
                String typeOfProperty_Name = typeOfProperty.getName();
                String typeOfProperty_PropertyCategoryId = typeOfProperty.getPropertyCategoryId();
                String typeOfProperty_TypeOfPropertyId = String.valueOf(typeOfProperty.getTypeOfPropertyId());

                // set the data - Property_Name , PropertyCategoryId, TypeOfPropertyId and is_property_check as true
                appDatabase.interfaceOfflineDataModelQuery().updatePropertytype_is_property_changed(typeOfProperty_Name, typeOfProperty_PropertyCategoryId, typeOfProperty_TypeOfPropertyId, true, case_id, true);

                // update the PropertyType in case Modal
                appDatabase.interfaceCaseQuery().updateCase_PropertyType(typeOfProperty.getTypeOfPropertyId(), case_id);

                // refesh the data
                ArrayList<OfflineDataModel> dataModel_refresh = new ArrayList<>();
                dataModel_refresh = (ArrayList<OfflineDataModel>) appDatabase.interfaceOfflineDataModelQuery().getDataModal_offlinecase(true);
                dataModels = dataModel_refresh;

                // UpdateOfflinePropertyTypeCase(propertyType, bank_id, agencybranchid);
                //GetReportPropertyTypeWebservice(propertyType, bank_id, agencybranchid);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }, 500);

            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });
        dialog.show();
      /*  Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);*/
    }


    private void UpdateOfflinePropertyTypeCase(String property_typeid, String bank_id, String agencybranchid) {
        String propertyCategoryId = "";
        String propertytype = "";

        if (Singleton.getInstance().typeOfProperties_list != null)
            if (Singleton.getInstance().typeOfProperties_list.size() > 0) {

                for (int i = 0; i < Singleton.getInstance().typeOfProperties_list.size(); i++) {
                    int typeproperty = Singleton.getInstance().typeOfProperties_list.get(i).getTypeOfPropertyId();
                    if (property_typeid.equalsIgnoreCase(String.valueOf(typeproperty))) {
                        propertyCategoryId = Singleton.getInstance().typeOfProperties_list.get(i).getPropertyCategoryId();
                        propertytype = Singleton.getInstance().typeOfProperties_list.get(i).getName();
                    }
                }
            }

        /*********
         * Set the Property Category id in Opencase room db
         * and create reference table for its caseid
         * *********/
        if (MyApplication.getAppContext() != null) {
            if (appDatabase.interfaceDataModelQuery().getDataModal_opencase(true).size() > 0) {
                List<OfflineDataModel> opencaseModel = appDatabase.interfaceOfflineDataModelQuery().getDataModal_offlinecase(true);

                ArrayList<OfflineDataModel> listOfStrings = new ArrayList<OfflineDataModel>(opencaseModel.size());
                listOfStrings.addAll(opencaseModel);

                for (int i = 0; i < listOfStrings.size(); i++) {
                    String opencaseid = listOfStrings.get(i).getCaseId();
                    if (case_id.equalsIgnoreCase(opencaseid)) {

                        Log.e("my_propertytype", "my_propertytype: " + propertytype);
                        Log.e("my_propertytype", "my_propertytype: " + propertyCategoryId);

                        long value = appDatabase.interfaceOfflineDataModelQuery().updatePropertytype(propertytype, propertyCategoryId, case_id, true);

                        Log.e("updated offlinecase db", ":" + value);


                        /**************Insert or update the case property updated
                         *  in reference table****************/
                        ReferenceUpdatePropertyType(i, case_id, appDatabase, listOfStrings, propertyCategoryId, propertytype);

                        break;
                    }
                }

                /*Intent intent = new Intent(mContext,HomeActivity.class);
                mContext.startActivity(intent);*/
                notifyDataSetChanged();
            }
        }
    }


    private void ReferenceUpdatePropertyType(int i, String case_id, AppDatabase appDatabase, ArrayList<OfflineDataModel> listOfStrings, String propertyCategoryId, String propertytype) {

        /**************Insert or update the case property updated
         *  in reference table****************/
        boolean propertycase_updated = false;
        if (appDatabase.propertyUpdateCategory().getPropertyUpdateModel().size() > 0) {
            List<PropertyUpdateRoomDB> updatepropertytype = appDatabase.propertyUpdateCategory().getPropertyUpdateModel();
            ArrayList<PropertyUpdateRoomDB> updatedPropertyList = new ArrayList<PropertyUpdateRoomDB>(updatepropertytype.size());
            updatedPropertyList.addAll(updatepropertytype);

            for (int j = 0; j < updatedPropertyList.size(); j++) {
                String alreadyinsert_caseid = updatedPropertyList.get(j).getCaseid();
                if (case_id.equalsIgnoreCase(alreadyinsert_caseid)) {
                    long updated = appDatabase.propertyUpdateCategory().updatePropertytype(propertytype, propertyCategoryId, case_id);
                    Log.e("updated", ":" + updated);
                    propertycase_updated = true;
                    break;
                }
            }
        }

        /**************************/
        if (!propertycase_updated) {
            PropertyUpdateRoomDB propertyUpdateRoomDB = new PropertyUpdateRoomDB();
            propertyUpdateRoomDB.setCaseid(case_id);

            propertyUpdateRoomDB.setProperty_category_id(listOfStrings.get(i).getPropertyCategoryId());
            propertyUpdateRoomDB.setProperty_type(listOfStrings.get(i).getPropertyType());
            appDatabase.propertyUpdateCategory().insertAll(propertyUpdateRoomDB);
        }
    }

    /**********
     * Load Property Type List call Webservice
     * **********/
    private void LoadPropertyTypeWebservice(String bankid, String typeid) {

        if (Connectivity.isConnected(mContext)) {
            general.showloading(mContext);
            InitiatePropertyTypeListTask(bankid, typeid);

        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
    }

    /**********
     * Load the offline property type for display list
     * ************/
    private void InitiateOfflinePropertyType(String bankid, String typeid) {

        if (MyApplication.getAppContext() != null) {
            AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
            if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
                List<TypeOfProperty> typeOfProperties = appDatabase.typeofPropertyQuery().getTypeofPropertyModels();
                ArrayList<DataModel> dataModelsType = new ArrayList<>();

                ArrayList<TypeOfProperty> listOfStrings = new ArrayList<TypeOfProperty>(typeOfProperties.size());
                listOfStrings.addAll(typeOfProperties);
                Singleton.getInstance().typeOfProperties_list = listOfStrings;

                for (int i = 0; i < typeOfProperties.size(); i++) {
                    DataModel dataModel = new DataModel();
                    String name = typeOfProperties.get(i).getName();
                    dataModel.setName(typeOfProperties.get(i).getName());
                    dataModel.setTypeOfPropertyId(typeOfProperties.get(i).getTypeOfPropertyId() + "");
                    dataModelsType.add(dataModel);
                }
                Singleton.getInstance().propertyTypeList = dataModelsType;

                Log.e("type of property", typeOfProperties.size() + "");

                PropertyTypeUpdatePopup(mContext.getResources().getString(R.string.property_type) + property_caseid);
            }
        }
    }

    /*******Load the property type list from API call*********/
    private void InitiatePropertyTypeListTask(String bankid, String typeid) {

        String url = general.ApiBaseUrl() + SettingsUtils.LoadPropertyType;
        JsonRequestData requestData = new JsonRequestData();
        requestData.setInitQueryUrl(url);
        requestData.setBankId(bankid);
        requestData.setTypeID(typeid);
        requestData.setUrl(RequestParam.PropertyTypeListRequestParams(requestData));
//        requestData.setUrl(url + bankid);

        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.GET);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                parsePropertyTypeListResponse(requestData.getResponse());
            }
        });
        webserviceTask.execute();
    }

    private void parsePropertyTypeListResponse(String response) {

        DataResponse dataResponse = ResponseParser.parsePropertyTypeListResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
            propertyTypeList = new ArrayList<>();
            //propertyTypeList = dataResponse.propertyTypeList;
            Singleton.getInstance().propertyTypeList = dataResponse.propertyTypeList;
        }

        if (result != null) {
            if (result.equals("1")) {
                //  general.CustomToast(msg);

                /*******If Property type size is greater than 0********/
                PropertyTypeUpdatePopup(mContext.getResources().getString(R.string.property_type) + property_caseid);
                general.hideloading();
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

    /**********Todo Deprecated @Feb 1st
     * Update Property Type for particular case Webservice
     * **********/
    private void UpdatePropertyTypeWebservice(String CaseId, String PropertyType) {

        if (Connectivity.isConnected(mContext)) {
            general.showloading(mContext);
            InitiateUpdatePropertyTypeTask(CaseId, PropertyType);
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
    }

    /***********Update the property type from API call***************/
    private void InitiateUpdatePropertyTypeTask(String CaseId, String PropertyType) {

        String url = general.ApiBaseUrl() + SettingsUtils.UpdatePropertyType;
        //  String url = SettingsUtils.BASE_URL + SettingsUtils.UpdatePropertyType;
        JsonRequestData requestData = new JsonRequestData();
        requestData.setUrl(url);
        requestData.setCaseId(CaseId);
        requestData.setPropertyType(PropertyType);
        requestData.setRequestBody(RequestParam.UpdatePropertyTypeRequestParams(requestData));

        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.PUT);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                parseUpdatePropertyTypeResponse(requestData.getResponse());
            }
        });
        webserviceTask.execute();
    }

    private void parseUpdatePropertyTypeResponse(String response) {

        DataResponse dataResponse = ResponseParser.parseUpdatePropertyTypeResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
           /* updatePropertyTypeStatusModel = new DataModel();
            updatePropertyTypeStatusModel = dataResponse.updatePropertyTypeStatusModel;
            */
            Singleton.getInstance().updatePropertyTypeStatusModel = dataResponse.updatePropertyTypeStatusModel;
            Singleton.getInstance().updatePropertyTypeStatusModel.setPropertyType(property_name_from_type);
        }

        if (result != null) {
            if (result.equals("1")) {
                UpdatePropertyTypefromList();
                general.hideloading();
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

    /********
     * Set the updated property to opencase
     * ********/
    private void UpdatePropertyTypefromList() {
        if (Singleton.getInstance().openCaseList != null) {
            if (Singleton.getInstance().openCaseList.size() > 0) {
                String caseid = Singleton.getInstance().updatePropertyTypeStatusModel.getCaseId();
                String propertytype = Singleton.getInstance().updatePropertyTypeStatusModel.getPropertyType();
                for (int i = 0; i < Singleton.getInstance().openCaseList.size(); i++) {
                    String opencaseid = Singleton.getInstance().openCaseList.get(i).getCaseId();
                    if (caseid.equalsIgnoreCase(opencaseid)) {
                        /*dataModel = Singleton.getInstance().openCaseList.get(i);*/
                        dataModel.setPropertyType(propertytype);
                    }
                }
                notifyDataSetChanged();
            }
        }
    }


    /**********
     * Document Read for particular case Webservice
     * **********/
    private void DocumentReadWebservice(final String CaseId) {

        // Set case Id
        SettingsUtils.getInstance().putValue(SettingsUtils.CASE_ID, CaseId);
        // offline
        SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, true);
        // offline
        general.showloading(mContext);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Document List
                documentRead = (ArrayList<Document_list>) appDatabase.interfaceDocumentListQuery().getDocument_list_caseID(Integer.parseInt(CaseId));
                Log.e("my_caseID", "my_caseID: " + documentRead.size());
                if (documentRead.size() >= 1) {
                    documentpopup(mContext, documentRead);
                } else {
                    Toast.makeText(mContext, "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
        }, 100);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                general.hideloading();
            }
        }, 1000);


        /*// Room Add - OfflineCase
        if (appDatabase.interfaceOfflineCaseQuery().getOflineCase_caseID(Integer.parseInt(CaseId)).size() == 0) {
            // online
            SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, false);
        } else {
            // offline
            SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, true);
        }
        boolean is_offline = SettingsUtils.getInstance().getValue(SettingsUtils.is_offline, false);
        if (!is_offline) {
            // online
            if (Connectivity.isConnected(mContext)) {
                general.showloading(mContext);
                InitiateDocumentReadTask(CaseId);
            } else {
                Connectivity.showNoConnectionDialog(mContext);
                general.hideloading();
            }
        } else {
            // offline
            general.showloading(mContext);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Document List
                    documentRead = (ArrayList<Document_list>) appDatabase.interfaceDocumentListQuery().getDocument_list_caseID(Integer.parseInt(CaseId));
                    Log.e("my_caseID", "my_caseID: " + documentRead.size());


                    if (documentRead.size() >= 1) {
                        documentpopup(mContext, documentRead);
                    } else {
                        Toast.makeText(mContext, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }
            }, 100);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    general.hideloading();
                }
            }, 1000);
        }*/
    }

    private void InitiateDocumentReadTask(String CaseId) {

        String url = general.ApiBaseUrl() + SettingsUtils.DocumentRead;
        // String url = SettingsUtils.BASE_URL + SettingsUtils.DocumentRead;
        JsonRequestData requestData = new JsonRequestData();
        requestData.setInitQueryUrl(url);
        requestData.setCaseId(CaseId);//CaseId
        requestData.setUrl(RequestParam.DocumentReadRequestParams(requestData));

        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.GET);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                parseDocumentReadResponse(requestData.getResponse());
                SettingsUtils.getInstance().putValue(SettingsUtils.KEY_DOCUMENT, requestData.getResponse());
            }
        });
        webserviceTask.execute();
    }

    private void parseDocumentReadResponse(String response) {

        DataResponse dataResponse = ResponseParser.parseDocumentReadResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
            //   documentRead = dataResponse.documentRead;
            Singleton.getInstance().documentRead = dataResponse.documentRead;
            if (documentRead.size() >= 1) {
                general.hideloading();
                //    documentpopup(mContext, documentRead);
            } else {
                general.hideloading();
                Toast.makeText(mContext, "No Data", Toast.LENGTH_SHORT).show();
            }
        }

        if (result != null) {
            if (result.equals("1")) {
                general.hideloading();
            } else if (result.equals("2")) {
                general.hideloading();
                general.CustomToast(msg);
            } else if (result.equals("0")) {
                general.hideloading();
                general.CustomToast(msg);
            }
        } else {
            general.hideloading();
            general.CustomToast(mContext.getResources().getString(R.string.serverProblem));
        }
    }

    private void LoadDocumentReaderforImgorPdf() {
        if (Singleton.getInstance().documentRead != null)
            if (Singleton.getInstance().documentRead.size() > 0) {

                for (Document_list document : Singleton.getInstance().documentRead) {
                    document_name = document.getDocumentName();
                    document_base64 = document.getDocument();
                    title = document.getTitle();
                }
            }

        if (general.checkPermissions()) {

            if (Singleton.getInstance().documentRead.size() > 0) {
                if (document_name.contains("jpg") || document_name.contains("jpeg")) {
                    DocumentReaderPopup(document_base64, title, mContext.getResources().getString(R.string.document_view) + property_caseid);
                } else if (document_name.contains("pdf")) {
                    Singleton.getInstance().document_header = mContext.getResources().getString(R.string.document_view) + property_caseid;
                    Singleton.getInstance().documentbase64 = document_base64;
                    Singleton.getInstance().document_name = document_name;
                    mContext.startActivity(new Intent(mContext, PdfViewer.class));
                } else if (document_base64.equalsIgnoreCase("")) {
                    general.CustomToast("No Data");
                }
            } else {
                general.CustomToast("No Data");
            }
        }
    }


    /*******
     * Document Reader Dialog
     * *******/
    private void DocumentReaderPopup(final String documentimage, String title, String msg) {
        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.document_reader);

        TextView popuptitle = (TextView) dialog.findViewById(R.id.title);
        TextView titletext = (TextView) dialog.findViewById(R.id.titletext);
        ImageView document_image = (ImageView) dialog.findViewById(R.id.document_read_image);
        Button yesBtn = (Button) dialog.findViewById(R.id.yesBtn);
        Button noBtn = (Button) dialog.findViewById(R.id.noBtn);
        yesBtn.setTypeface(general.mediumtypeface());
        noBtn.setTypeface(general.mediumtypeface());
        popuptitle.setTypeface(general.mediumtypeface());
        titletext.setTypeface(general.mediumtypeface());
        popuptitle.setText(msg);
        titletext.setText(title);

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
                /*Singleton.getInstance().documentbase64 = documentimage;*/
                Singleton.getInstance().document_header = mContext.getResources().getString(R.string.document_view) + property_caseid;
                Singleton.getInstance().documentbase64 = document_base64;
                Singleton.getInstance().document_name = document_name;
                //  mContext.startActivity(new Intent(mContext, PdfViewer.class));
                dialog.dismiss();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
      /*  Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);*/
    }


    /*******
     * getInspection for save or edit Inspection on Button Webservice call
     * *******/
    private void CaseEditInspectionWebservice(String case_id) {

        if (Connectivity.isConnected(mContext)) {
            general.showloading(mContext);
            InitiateEditCaseInspectionTask(case_id);
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
    }

    private void InitiateEditCaseInspectionTask(String case_id) {

        String url = general.ApiBaseUrl() + SettingsUtils.EditCaseInspection;
        //  String url = SettingsUtils.BASE_URL + SettingsUtils.EditCaseInspection;

        JsonRequestData requestData = new JsonRequestData();
        requestData.setInitQueryUrl(url);
        requestData.setId(case_id); // id
        requestData.setUrl(RequestParam.EditInspectionRequestParams(requestData));

        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.GET);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                parseEditCaseInspectionResponse(requestData.getResponse());
            }
        });
        webserviceTask.execute();
    }

    private void parseEditCaseInspectionResponse(String response) {

        DataResponse dataResponse = ResponseParser.parseEditInspectionResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
            Singleton.getInstance().aCase = dataResponse.aCase;
            Singleton.getInstance().property = dataResponse.property;
            Singleton.getInstance().indProperty = dataResponse.indProperty;
            Singleton.getInstance().indPropertyValuation = dataResponse.indPropertyValuation;
            Singleton.getInstance().indPropertyFloors = dataResponse.indPropertyFloors;
            Singleton.getInstance().proximities = dataResponse.proximities;
        }

        if (result != null) {
            if (result.equals("1")) {
                /*****Check dropdown singleton****/
                CheckSingletonDropdowns();
                /*****Check dropdown singleton****/
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

    /*********Check for Dropdown cache to remain or else load it again from shared prefs***********/
    public void CheckSingletonDropdowns() {
        if (Singleton.getInstance().constructions_list != null) {
            if (Singleton.getInstance().constructions_list.size() == 0) {
                String dropdown_response = SettingsUtils.getInstance().getValue(SettingsUtils.DropDownSave, "");
                parseGetDropDownsDataResponse(dropdown_response);
            }
        } else {
            String dropdown_response = SettingsUtils.getInstance().getValue(SettingsUtils.DropDownSave, "");
            parseGetDropDownsDataResponse(dropdown_response);
        }
        mContext.startActivity(new Intent(mContext, PhotoLatLngTab.class));
        general.hideloading();
    }


    private void parseGetDropDownsDataResponse(String response) {
        DataResponse dataResponse = ResponseParser.parseGetFieldDropDownResponse(response);
        general.hideloading();
    }

    /*********Check for Dropdown cache to remain or else load it again from shared prefs***********/
    public void CheckSingletonDropdownsData() {
        if (Singleton.getInstance().constructions_list != null) {
            if (Singleton.getInstance().constructions_list.size() == 0) {
                String dropdown_response = SettingsUtils.getInstance().getValue(SettingsUtils.DropDownSave, "");
                parseGetDropDownsDataResponse(dropdown_response);
            }
        } else {
            String dropdown_response = SettingsUtils.getInstance().getValue(SettingsUtils.DropDownSave, "");
            parseGetDropDownsDataResponse(dropdown_response);
        }

    }


    /* Document Popup */
    public void documentpopup(Activity mContext, ArrayList<Document_list> documentRead) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.documentpopup, null);
        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(mContext);
        alertdialogBuilder.setView(view);
        final AlertDialog alertDialog = alertdialogBuilder.show();
        alertDialog.setCancelable(false);

        TextView textview_heading = (TextView) alertDialog.findViewById(R.id.textview_heading);
        Button btn_cancel = (Button) alertDialog.findViewById(R.id.btn_cancel);

        RecyclerView documentlist = (RecyclerView) alertDialog.findViewById(R.id.documentlist);
        documentlist.setHasFixedSize(true);
        documentlist.setLayoutManager(new LinearLayoutManager(this.mContext));
        setRecyclerViewClickListner(documentlist);

        DocumentAdapter adapter = new DocumentAdapter(mContext, documentRead);
        documentlist.setAdapter(adapter);
        documentlist.setItemAnimator(new DefaultItemAnimator());
        documentlist.setNestedScrollingEnabled(false);
        adapter.notifyDataSetChanged();

        textview_heading.setTypeface(general.mediumtypeface());
        btn_cancel.setTypeface(general.mediumtypeface());


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private void setRecyclerViewClickListner(RecyclerView documentlist) {
        final GestureDetector gestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        documentlist.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildLayoutPosition(child);
                    Singleton.getInstance().POSITION = position;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }


    // Todo - Check the Propety type is changed or not
    private boolean check_is_property_changed() {
        boolean is_property_changed = false;
        if (appDatabase.interfaceOfflineDataModelQuery().getDataModal_is_property_changed(case_id)) {
            /* Property type updated */
            is_property_changed = true;
        } else {
            /* Property type not updated */
            is_property_changed = false;
        }
        return is_property_changed;
    }

    private void Getting_propertyChanged_case() {
        String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        OfflineDataModel offlineDataModel = appDatabase.interfaceOfflineDataModelQuery().get_case_is(caseid);
        InitiateGetReportPropertyTypeTask(offlineDataModel.getBankId(), offlineDataModel.getAgencyBranchId(), offlineDataModel.getPropertyCategoryId());
    }

    private void InitiateGetReportPropertyTypeTask(String bank_id, String agencybranchid, String property_categoryid) {
        if (Connectivity.isConnected(mContext)) {
            String url = general.ApiBaseUrl() + SettingsUtils.GetReportTypeProperty;
            JsonRequestData requestData = new JsonRequestData();
            requestData.setInitQueryUrl(url);
            requestData.setBankId(bank_id);
            requestData.setAgencyBranchId(agencybranchid);
            requestData.setProId(property_categoryid);
            requestData.setUrl(RequestParam.GetReportPropertyTypeRequestParams(requestData));
            WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.GET);
            webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                @Override
                public void onTaskComplete(JsonRequestData requestData) {
                    parseGetReportPropertyTypeResponse(requestData.getResponse());
                }
            });
            webserviceTask.execute();
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
    }

    private void parseGetReportPropertyTypeResponse(String response) {

        DataResponse dataResponse = ResponseParser.parseGetReportTypeResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
            DataModel getReportTypeModel = new DataModel();
            getReportTypeModel = dataResponse.getReportTypeModel;
            Singleton.getInstance().getReportTypeModel = dataResponse.getReportTypeModel;
            reporty_type = dataResponse.getReportTypeModel.getTypeID();
            //  Singleton.getInstance().getReportTypeModel.setPropertyType(property_name_from_type);
        }

        if (result != null) {
            if (result.equals("1")) {
                InitiatePropertyTypeUpdateTask(reporty_type);//bankname, reportytype
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

    /**********
     *  Property Type Update for particular case Webservice
     * **********/
    private void InitiatePropertyTypeUpdateTask(String ReportType) {
        if (Connectivity.isConnected(mContext)) {
            String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
            OfflineDataModel offlineDataModel = appDatabase.interfaceOfflineDataModelQuery().get_case_is(caseid);

            String modifiedby = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_LOGIN_ID, "");
            String url = general.ApiBaseUrl() + SettingsUtils.UpdatePropertytype;
            JsonRequestData requestData = new JsonRequestData();
            requestData.setUrl(url);
            requestData.setCaseId(caseid);
            requestData.setPropertyType(offlineDataModel.getTypeof_PropertyCategoryId_selected());
            requestData.setBankName(offlineDataModel.getBankId());
            requestData.setReportType(ReportType);
            requestData.setModifiedBy(modifiedby);
            requestData.setRequestBody(RequestParam.UpdatePropertyTypeNewRequestParams(requestData));

            WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.PUT);
            webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                @Override
                public void onTaskComplete(JsonRequestData requestData) {
                    parsePropertyTypeUpdateResponse(requestData.getResponse());
                }
            });
            webserviceTask.execute();
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
    }

    private void parsePropertyTypeUpdateResponse(String response) {

        String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");

        if (type_of_sync.equalsIgnoreCase("reject")) {
            UpdateStatusRejectCaseIdWebservice(caseid, mContext.getResources().getString(R.string.reject_status), rejectMsg);
        } else if (type_of_sync.equalsIgnoreCase("property_exits")) {
            UpdateStatusCaseIdWebservice(case_id, mContext.getResources().getString(R.string.property_exits));
        } else if (type_of_sync.equalsIgnoreCase("start_inspection")) {
            // send upload api and other data api
            String propertyId = appDatabase.interfaceOfflineDataModelQuery().get_PropertyId(caseid);
            int case_str = Integer.parseInt(caseid);
            int propertyId_str = Integer.parseInt(propertyId);
            send_image_data(case_str, propertyId_str);
        }

    }


    // Todo Reject  API call
    private void UpdateStatusRejectCaseIdWebservice(String case_id, String statusId, String remarks) {
        if (Connectivity.isConnected(mContext)) {
            InitiateUpdateStatusRejectCaseidTask(case_id, statusId, remarks);
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
    }

    private void InitiateUpdateStatusRejectCaseidTask(final String case_id, String statusId, String remarks) {

        String url = general.ApiBaseUrl() + SettingsUtils.RejectCaseStatusById;
        String modifiedby = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_LOGIN_ID, "");

        JsonRequestData requestData = new JsonRequestData();
        requestData.setUrl(url);
        requestData.setCaseId(case_id);//case_id
        requestData.setModifiedBy(modifiedby);
        requestData.setStatus(statusId);//statusId
        requestData.setEmployeeRemarks(remarks);//statusId
        requestData.setRequestBody(RequestParam.RejectCaseStatusRejectRequestParams(requestData));

        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.PUT);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                parseRejectStatusCaseidResponse(requestData.getResponse(), case_id);
            }
        });
        webserviceTask.execute();
    }

    private void parseRejectStatusCaseidResponse(String response, String case_id) {

        DataResponse dataResponse = ResponseParser.parseUpdateStatusCaseResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
            //updateCaseStatusModel = dataResponse.updateCaseStatusModel;
            Singleton.getInstance().updateCaseStatusModel = dataResponse.updateCaseStatusModel;
        }

        if (result != null) {
            if (result.equals("1")) {

                DeleteOfflineDatabyCaseID(case_id);
                Singleton.getInstance().openCaseList.clear();
                Singleton.getInstance().closeCaseList.clear();
                general.CustomToast("Caseid: " + case_id + " is synced to server");
                Singleton.getInstance().call_offline_tab = "call_offline_tab";
                Intent intent = new Intent(mContext, HomeActivity.class);
                mContext.startActivity(intent);
                general.hideloading();
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
    /* Rejection - end */

    // Todo Property doesnt exists check / Accept / Reject  API call
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
            //   updateCaseStatusModel = dataResponse.updateCaseStatusModel;
            Singleton.getInstance().updateCaseStatusModel = dataResponse.updateCaseStatusModel;
        }

        if (result != null) {
            if (result.equals("1")) {
                DeleteOfflineDatabyCaseID(case_id);
                Singleton.getInstance().openCaseList.clear();
                Singleton.getInstance().closeCaseList.clear();
                general.CustomToast("Caseid: " + case_id + " is synced to server");
                Singleton.getInstance().call_offline_tab = "call_offline_tab";
                Intent intent = new Intent(mContext, HomeActivity.class);
                mContext.startActivity(intent);

                general.hideloading();
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


    /* circle Loading */
    public void call_circle_loading_send() {
        connectionDialog_circle = new Dialog(mContext, R.style.CustomAlertDialog);
        connectionDialog_circle.setContentView(R.layout.progress_loading);
        connectionDialog_circle.setCancelable(false);
        connectionDialog_circle.show();

        mWaveLoadingView = (WaveLoadingView) connectionDialog_circle.findViewById(R.id.waveLoadingView);
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        mWaveLoadingView.setTopTitleColor(mContext.getResources().getColor(R.color.font_heading));
        mWaveLoadingView.setTopTitleSize(13);
        mWaveLoadingView.setCenterTitle(mContext.getResources().getString(R.string.case_send_wait));
        mWaveLoadingView.setCenterTitleColor(mContext.getResources().getColor(R.color.font_heading));
        mWaveLoadingView.setCenterTitleSize(13);
        mWaveLoadingView.setProgressValue(10);
        mWaveLoadingView.setBorderWidth(5);
        mWaveLoadingView.setAmplitudeRatio(60);
        mWaveLoadingView.setWaveBgColor(mContext.getResources().getColor(R.color.White));
        mWaveLoadingView.setWaveColor(mContext.getResources().getColor(R.color.colorPrimary));
        mWaveLoadingView.setBorderColor(mContext.getResources().getColor(R.color.colorPrimary));
        mWaveLoadingView.setAnimDuration(3000);
        mWaveLoadingView.startAnimation();
    }

    // TODO - If case want to sync
    private void send_image_data(final int case_int, final int PropertyId_is) {
        if (Connectivity.isConnected(mContext)) {
            //general.showloading(mContext);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Room - Get Lat and Long
                    LatLongDetails latLongDetails = new LatLongDetails();
                    String lat_long = "";
                    if (appDatabase.interfaceLatLongQuery().getLatLongDetails_caseID(case_int).size() > 0) {
                        latLongDetails = appDatabase.interfaceLatLongQuery().getLatLongDetails_caseID(case_int).get(0);
                        lat_long = latLongDetails.getLatLongDetails();
                    }
                    // Room - Get Photo
                    ArrayList<GetPhoto> GetPhoto_list_response = new ArrayList<>();
                    if (appDatabase.interfaceGetPhotoQuery().getPhoto_propertyid(PropertyId_is).size() > 0) {
                        ArrayList<GetPhoto> dataModel_open = new ArrayList<>();


                        // GetPhoto_list_response = (ArrayList<GetPhoto>) appDatabase.interfaceGetPhotoQuery().getPhoto_propertyid(PropertyId_is);
                        GetPhoto_list_response = (ArrayList<GetPhoto>) appDatabase.interfaceGetPhotoQuery().getPhoto_propertyid_getDefaultimage(PropertyId_is, false);
                        // if (GetPhoto_list_response.size() > 2) {
                        // Photo API can be hit first
                        JSONArray jsonArray = new JSONArray();
                        for (int x = 0; x < GetPhoto_list_response.size(); x++) {
                            if (!GetPhoto_list_response.get(x).isDefaultimage()) {
                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("ID", 0);
                                    // jsonObject.put("ID", GetPhoto_list_response.get(x).getId());
                                    jsonObject.put("PropertyId", GetPhoto_list_response.get(x).getPropertyId());
                                    if (!general.isEmpty(GetPhoto_list_response.get(x).getTitle())) {
                                        jsonObject.put("Title", GetPhoto_list_response.get(x).getTitle());
                                    } else {
                                        jsonObject.put("Title", "");
                                    }
                                    jsonObject.put("Logo", GetPhoto_list_response.get(x).getLogo());
                                    if (SettingsUtils.getInstance().getValue(SettingsUtils.KEY_FLAG_URL, "").equals("NEW"))
                                        jsonObject.put("FilleName", GetPhoto_list_response.get(x).getFileName());
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                                jsonArray.put(jsonObject);
                            }
                        }

                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("PropertyId", PropertyId_is);
                            jsonObject.put("LatLongDetails", lat_long);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                        JSONObject edit_synk_obj = new JSONObject();
                        Log.e("is_edit_synu:", "is_edit_synu: " + is_edit_synu);
                        try {
                            edit_synk_obj.put("PropertyId", PropertyId_is);
                            // TODO - set Default as true for restore all the data
                            edit_synk_obj.put("is_sync", true);
                            //edit_synk_obj.put("is_sync", is_edit_synu);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // For measurment Image
                        ArrayList<GetPhoto_measurment> dataModel_measur = new ArrayList<>();
                        dataModel_measur = (ArrayList<GetPhoto_measurment>) appDatabase.interfaceGetPhotoMeasurmentQuery().getPhoto_propertyid(PropertyId_is);
                        Singleton.getInstance().GetImage_list_flat = dataModel_measur;

                        JSONArray jsonArray_measurment = new JSONArray();
                        JSONObject jsonObject_measurment = new JSONObject();

                        if (dataModel_measur.size() > 0) {
                            try {
                                //jsonObject_measurment.put("ID", Singleton.getInstance().GetImage_list_flat.get(0).getId());
                                jsonObject_measurment.put("ID", 0);
                                jsonObject_measurment.put("PropertyId", Singleton.getInstance().GetImage_list_flat.get(0).getPropertyId());
                                if (!general.isEmpty(Singleton.getInstance().GetImage_list_flat.get(0).getTitle())) {
                                    jsonObject_measurment.put("Title", Singleton.getInstance().GetImage_list_flat.get(0).getTitle());
                                } else {
                                    jsonObject_measurment.put("Title", "");
                                }
                                jsonObject_measurment.put("Logo", Singleton.getInstance().GetImage_list_flat.get(0).getLogo());
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                            jsonArray_measurment.put(jsonObject_measurment);
                        }

                        JsonRequestData data = new JsonRequestData();
                        // set Photo
                        data.setCompanyName(jsonArray.toString());
                        // set the lat and long
                        data.setContactPersonName(jsonObject.toString());
                        // set edit_synk
                        data.setApplicantName(edit_synk_obj.toString());
                        // set measurment Photo
                        data.setEmail(jsonArray_measurment.toString());


                        data.setUrl(general.ApiBaseUrl() + SettingsUtils.IMAGE);
                        data.setRequestBody(RequestParam.uploadimageRequestParams_measurment_offline(data));
                        // set loading
                        mWaveLoadingView.setProgressValue(45);
                        mWaveLoadingView.setCenterTitle(mContext.getResources().getString(R.string.case_send_photo));

                        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, data, SettingsUtils.POST);
                        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                            @Override
                            public void onTaskComplete(JsonRequestData requestData) {

                                // check IndPropertyFloor - > If size is zero - it will call case-update api only
                                // set loading
                                mWaveLoadingView.setProgressValue(85);
                                mWaveLoadingView.setCenterTitle(mContext.getResources().getString(R.string.case_send_details));


                                if (appDatabase.interfaceIndPropertyFloorsQuery().getIndPropertyFloor_caseID(case_int).size() == 0) {
                                    // If its LAND - > IndPropertyFloor is 0 always ... so, we can check the PropertyAddressAtSite as empty or not ...
                                    if (appDatabase.interfacePropertyQuery().getProperty_caseID(case_int).size() > 0) {
                                        /*if (general.isEmpty(appDatabase.interfacePropertyQuery().getProperty_caseID(case_int).get(0).getPropertyAddressAtSite())) {
                                            // Is Empty
                                            send_updatecaseID_data(case_int, mContext.getResources().getString(R.string.edit_inspection), PropertyId_is);
                                        } else {*/
                                            // Is not empty
                                            send_other_data(case_int, PropertyId_is);
                                       /* }*/
                                    } else {
                                        // Is Empty
                                        send_updatecaseID_data(case_int, mContext.getResources().getString(R.string.edit_inspection), PropertyId_is);
                                    }
                                } else {
                                    // - > If greater than zero > it will call others tab and hit case-update api
                                    send_other_data(case_int, PropertyId_is);
                                }
                            }
                        });
                        webserviceTask.execute();
                        //}
                        /*} else {
                            // only details can be sync > No photo can be synced
                            mWaveLoadingView.setProgressValue(60);
                            mWaveLoadingView.setCenterTitle(mContext.getResources().getString(R.string.case_send_details));
                            if (appDatabase.interfaceIndPropertyFloorsQuery().getIndPropertyFloor_caseID(case_int).size() == 0) {
                                send_updatecaseID_data(case_int, mContext.getResources().getString(R.string.edit_inspection), PropertyId_is);
                            } else {
                                // - > If greater than zero > it will call others tab and hit case-update api
                                send_other_data(case_int, PropertyId_is);
                            }
                        }*/
                    } else {
                        connectionDialog_circle.hide();
                        general.hideloading();
                    }
                }
            }, 1000);
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            connectionDialog_circle.hide();
            general.hideloading();
        }
    }

    private void send_other_data(final int case_int, final int PropertyId_is) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Connectivity.isConnected(mContext)) {
                    // Case
                    String propertyJsonObj = "";
                    String indpropertyJsonObj ="";
                    String IndPropertyValuationJsonObj="";
                    String IndPropertyfloorJsonObj="";
                    String IndPropertyfloorValuationJsonObj="";
                    String proximityJsonObj="";
                    Gson gson = new Gson();
                    LinkedTreeMap<String,Object>  mainObj = new LinkedTreeMap<>();
                    mainObj.put("Case", appDatabase.interfaceCaseQuery().getCase_caseID(case_int).get(0));

//                    Singleton.getInstance().aCase = appDatabase.interfaceCaseQuery().getCase_caseID(case_int).get(0);
                    // Property
                    if (appDatabase.interfacePropertyQuery().getProperty_caseID(case_int).size() > 0) {
//                        Singleton.getInstance().property = appDatabase.interfacePropertyQuery().getProperty_caseID(case_int).get(0);
                        mainObj.put("Property", appDatabase.interfacePropertyQuery().getProperty_caseID(case_int).get(0));

                    }
                    // IndProperty
                    if (appDatabase.interfaceIndpropertyQuery().getIndProperty_caseID(case_int).size() > 0) {
//                        Singleton.getInstance().indProperty = appDatabase.interfaceIndpropertyQuery().getIndProperty_caseID(case_int).get(0);
                        mainObj.put("IndProperty", appDatabase.interfaceIndpropertyQuery().getIndProperty_caseID(case_int).get(0));

                    }
                    // IndPropertyValuation
                    if (appDatabase.interfaceIndPropertyValuationQuery().getIndPropertyValuation_caseID(case_int).size() > 0) {
//                        Singleton.getInstance().indPropertyValuation = appDatabase.interfaceIndPropertyValuationQuery().getIndPropertyValuation_caseID(case_int).get(0);
                        mainObj.put("IndPropertyValuation", appDatabase.interfaceIndPropertyValuationQuery().getIndPropertyValuation_caseID(case_int).get(0));

                    }
                    // IndPropertyFloor
                    if (appDatabase.interfaceIndPropertyFloorsQuery().getIndPropertyFloor_caseID(case_int).size() > 0) {
//                        Singleton.getInstance().indPropertyFloors = (ArrayList<IndPropertyFloor>) appDatabase.interfaceIndPropertyFloorsQuery().getIndPropertyFloor_caseID(case_int);
                        IndPropertyfloorJsonObj = gson.toJson((ArrayList<IndPropertyFloor>) appDatabase.interfaceIndPropertyFloorsQuery().getIndPropertyFloor_caseID(case_int));
                        mainObj.put("IndPropertyFloors", appDatabase.interfaceIndPropertyFloorsQuery().getIndPropertyFloor_caseID(case_int));

                    } else {
                        ArrayList<IndPropertyFloor> indPropertyFloors = new ArrayList<>();
//                        Singleton.getInstance().indPropertyFloors = indPropertyFloors;
                        mainObj.put("IndPropertyFloors", indPropertyFloors);

                    }
                    // IndPropertyFloorsValuation
                    if (appDatabase.interfaceIndPropertyFloorsValuationQuery().getIndPropertyFloorsValuation_caseID(case_int).size() > 0) {
//                        Singleton.getInstance().indPropertyFloorsValuations = (ArrayList<IndPropertyFloorsValuation>) appDatabase.interfaceIndPropertyFloorsValuationQuery().getIndPropertyFloorsValuation_caseID(case_int);
                        mainObj.put("IndPropertyFloorsValuation", appDatabase.interfaceIndPropertyFloorsValuationQuery().getIndPropertyFloorsValuation_caseID(case_int));
                    } else {
                        ArrayList<IndPropertyFloorsValuation> indPropertyFloorsValuations = new ArrayList<>();
//                        Singleton.getInstance().indPropertyFloorsValuations = indPropertyFloorsValuations;
                        IndPropertyfloorValuationJsonObj = gson.toJson(indPropertyFloorsValuations);
                        mainObj.put("IndPropertyFloorsValuation", indPropertyFloorsValuations);

                    }
                    // Proximity
                    if (appDatabase.interfaceProximityQuery().getProximity_caseID(case_int).size() > 0) {
//                        Singleton.getInstance().proximities = (ArrayList<Proximity>) appDatabase.interfaceProximityQuery().getProximity_caseID(case_int);
                        mainObj.put("Proximity", appDatabase.interfaceProximityQuery().getProximity_caseID(case_int));

                    } else {
                        ArrayList<Proximity> proximities = new ArrayList<>();
//                        Singleton.getInstance().proximities = proximities;
                        mainObj.put("Proximity", proximities);

                    }

                    LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
                    map.put("is_sync", true);
                    mainObj.put("edit_synk", map);

                    String url = general.ApiBaseUrl() + SettingsUtils.SaveCaseInspection;
                    JsonRequestData requestData = new JsonRequestData();

                    requestData.setUrl(url);
                    requestData.setMainJson(new Gson().toJson(mainObj));

                    requestData.setRequestBody(RequestParam.SaveCaseInspectionRequestParams(requestData));

                    WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.POST);
                    webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                        @Override
                        public void onTaskComplete(JsonRequestData requestData) {
                            // Update caseID

                            /*boolean real_appraiser_jaipur = SettingsUtils.getInstance().getValue(SettingsUtils.real_appraiser_jaipur, false);
                            if (real_appraiser_jaipur) {
                                // Jaipur
                                send_updatecaseID_data(case_int, mContext.getResources().getString(R.string.edit_inspection), PropertyId_is);
                            } else {
                                // Kakode
                                upload_image_measurment(case_int, PropertyId_is);
                            }*/

                            send_updatecaseID_data(case_int, mContext.getResources().getString(R.string.edit_inspection), PropertyId_is);


                        }
                    });
                    webserviceTask.execute();
                } else {
                    Connectivity.showNoConnectionDialog(mContext);
                    connectionDialog_circle.hide();
                    general.hideloading();
                }
            }
        }, 100);
    }

    private void upload_image_measurment(final int case_int, final int PropertyId_is) {
        if (appDatabase.interfaceGetPhotoMeasurmentQuery().getPhoto_propertyid(PropertyId_is).size() > 0) {
            ArrayList<GetPhoto_measurment> dataModel_open = new ArrayList<>();
            dataModel_open = (ArrayList<GetPhoto_measurment>) appDatabase.interfaceGetPhotoMeasurmentQuery().getPhoto_propertyid(PropertyId_is);
            Singleton.getInstance().GetImage_list_flat = dataModel_open;

            JSONArray jsonArray = new JSONArray();
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
            WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, data, SettingsUtils.POST);
            webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                @Override
                public void onTaskComplete(JsonRequestData requestData) {
                    // Hide the loading
                    send_updatecaseID_data(case_int, mContext.getResources().getString(R.string.edit_inspection), PropertyId_is);
                }
            });
            webserviceTask.execute();

        } else {
            send_updatecaseID_data(case_int, mContext.getResources().getString(R.string.edit_inspection), PropertyId_is);
        }
    }

    private void send_updatecaseID_data(final int case_id, String statusId, final int propertyId_is) {

        String url = general.ApiBaseUrl() + SettingsUtils.UpdateCaseStatusById;
        String modifiedby = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_LOGIN_ID, "");
        JsonRequestData requestData = new JsonRequestData();
        requestData.setUrl(url);
        requestData.setCaseId(String.valueOf(case_id));//case_id
        requestData.setModifiedBy(modifiedby);
        requestData.setStatus(statusId);//statusId
        requestData.setRequestBody(RequestParam.UpdateCaseStatusRequestParams(requestData));

        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.PUT);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {

                // Room - Delete - OfflineCase
                appDatabase.interfaceOfflineCaseQuery().deleteRow(case_id);
                // Room - Delete - Document_list
                appDatabase.interfaceDocumentListQuery().deleteRow(case_id);
                // Room - Delete - GetPhoto
                appDatabase.interfaceGetPhotoQuery().deleteRow(propertyId_is);
                // Room - Delete - LatLong
                appDatabase.interfaceLatLongQuery().deleteRow(case_id);
                // Room Delete - Case
                appDatabase.interfaceCaseQuery().deleteRow(case_id);
                // Room Delete - Property
                appDatabase.interfacePropertyQuery().deleteRow(case_id);
                // Room Delete - Indproperty
                appDatabase.interfaceIndpropertyQuery().deleteRow(case_id);
                // Room Delete - IndPropertyValuation
                appDatabase.interfaceIndPropertyValuationQuery().deleteRow(case_id);
                // Room Delete - IndPropertyFloors
                appDatabase.interfaceIndPropertyFloorsQuery().deleteRow(case_id);
                // Room Delete - IndPropertyFloorsValuation
                appDatabase.interfaceIndPropertyFloorsValuationQuery().deleteRow(case_id);
                // Room Delete - Proximity
                appDatabase.interfaceProximityQuery().deleteRow(case_id);

                // Room Delete - Offline case
                DeleteOfflineDatabyCaseID("" + case_id);
                general.hideloading();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        connectionDialog_circle.hide();
                        Singleton.getInstance().openCaseList.clear();
                        Singleton.getInstance().closeCaseList.clear();
                        general.CustomToast("Caseid: " + case_id + " is synced to server");
                        Singleton.getInstance().call_offline_tab = "call_offline_tab";
                        Intent intent = new Intent(mContext, HomeActivity.class);
                        mContext.startActivity(intent);

                    }
                }, 1000);
            }
        });
        webserviceTask.execute();
    }


}
