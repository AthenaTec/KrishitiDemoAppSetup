package com.realappraiser.gharvalue.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.ContextThemeWrapper;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.realappraiser.gharvalue.model.Document_list;
import com.realappraiser.gharvalue.model.GetPhoto_measurment;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.model.IndPropertyFloorsValuation;
import com.realappraiser.gharvalue.model.Proximity;
import com.realappraiser.gharvalue.model.RejectionComment;
import com.realappraiser.gharvalue.model.TypeOfProperty;
import com.realappraiser.gharvalue.utils.Connectivity;
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;
import com.realappraiser.gharvalue.worker.LocationTrackerApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by kaptas on 16/12/17.
 */

@SuppressWarnings("ALL")
public class PendingCasesAdapter extends RecyclerView.Adapter<PendingCasesAdapter.ViewHolder> {

    private ArrayList<DataModel> dataModels;
    private final Activity mContext;
    private General general;
    DataModel updateCaseStatusModel = new DataModel();
    DataModel updatePropertyTypeStatusModel = new DataModel();
    DataModel getReportTypeModel = new DataModel();
    ArrayList<DataModel> propertyTypeList;
    ArrayList<Document_list> documentRead;

    private String msg = "", info = "", propertyType = "";
    private String case_id = "", property_caseid = "", bank_id = "", reporty_type = "", agencybranchid = "", type_id = "", property_type = "", StatusId_is = "";
    public Dialog dialog;
    DataModel dataModel = new DataModel();
    private String document_name = "", document_base64 = "", title = "";
    private String propertyCategoryId = "";
    private static final String TAG = General.class.getSimpleName();
    public String SAMPLE_FILE = "sample.pdf";
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public String property_name_from_type = "";
    public String my_property_cate_id = "";
    boolean is_validation_check = false;
    private int stagespinner;
    private String percentagecomp;
    public int changed_TypeOfPropertyId;
    private TransferClickListener transferClickListener;
    private LocationTrackerApi locationTrackerApi;
    AppDatabase appDatabase;


    public interface TransferClickListener {
        void transfer(String caseId, String applicantName);

        void caseAction();
    }

    public PendingCasesAdapter(Activity mContext, ArrayList<DataModel> dataModels, TransferClickListener transferClickListener) {
        this.mContext = mContext;
        this.dataModels = dataModels;
        this.transferClickListener = transferClickListener;

        appDatabase = AppDatabase.getAppDatabase(mContext);

        locationTrackerApi =  new LocationTrackerApi(mContext);
        general = new General(mContext);
        SettingsUtils.init(mContext);
    }

    public void getDBValue() {

//        String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        int case_int = Integer.parseInt(case_id);
        if (appDatabase.interfaceCaseQuery().getCase_caseID(case_int).size() > 0) {

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


        }
    }

    @Override
    public PendingCasesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_case_adapter, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"RecyclerView", "SetTextI18n"})
    @Override
    public void onBindViewHolder(final PendingCasesAdapter.ViewHolder holder, final int position) {

        String real_status_id = dataModels.get(position).getStatusId();


        Log.e(TAG, "onBindViewHolder: real_status_id==>"+real_status_id );

        String caseid = dataModels.get(position).getCaseId();

        String caseId = dataModels.get(position).getCaseId();
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

      /*  if (real_status_id.equals("12")||real_status_id.equals("13"))
            holder.transfer_lay.setVisibility(View.VISIBLE);
        else
            holder.transfer_lay.setVisibility(View.GONE);*/

        /******Added @jan31****/
        String applicantContactNo = dataModels.get(position).getApplicantContactNo();
        String contactPersonName = dataModels.get(position).getContactPersonName();
        String contactPersonMobile = dataModels.get(position).getContactPersonNumber();
        if (!general.isEmpty(contactPersonMobile) && !general.isEmpty(contactPersonName)) {
            holder.case_mobile.setText(contactPersonMobile + " (" + contactPersonName + ")");
            mobileNumberOnClick(holder.case_mobile, position, contactPersonMobile);
        } else if (!general.isEmpty(applicantContactNo) && !general.isEmpty(contactPersonName)) {
            holder.case_mobile.setText(applicantContactNo + " (" + contactPersonName + ")");
            mobileNumberOnClick(holder.case_mobile, position, applicantContactNo);
        } else if (!general.isEmpty(contactPersonMobile)) {
            holder.case_mobile.setText(contactPersonMobile);
            mobileNumberOnClick(holder.case_mobile, position, contactPersonMobile);
        } else if (!general.isEmpty(applicantContactNo)) {
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

        holder.relative_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String caseId = dataModels.get(position).getCaseId();
                String applicantName = dataModels.get(position).getApplicantName();
                String propertyAddress = dataModels.get(position).getPropertyAddress();
                addresspopup(mContext, caseId, applicantName, propertyAddress);
            }
        });

        holder.relative_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String caseId = dataModels.get(position).getCaseId();
                String applicantName = dataModels.get(position).getApplicantName();
                String propertyAddress = dataModels.get(position).getPropertyAddress();
                addresspopup(mContext, caseId, applicantName, propertyAddress);
            }
        });


        if (!general.isEmpty(dataModels.get(position).getCreatedOn())) {
            String assigned_date = General.AssignedDate(dataModels.get(position).getCreatedOn());
            String assigned_time = General.AssignedTime(dataModels.get(position).getCreatedOn());
            holder.case_assigned_date.setText(assigned_date);
            holder.case_assigned_time.setText(assigned_time.toUpperCase());
        } else {
            holder.case_assigned_date.setText("");
            holder.case_assigned_time.setText("");
        }

        String propertyType = dataModels.get(position).getPropertyType();
        if (!general.isEmpty(propertyType)) {
            holder.textview_property_type_heading.setText(propertyType);
        } else {
            holder.textview_property_type_heading.setText(R.string.dot);
        }

        /*******
         * Set the Button field process as per the Real Status
         * *******/
        ChecktoUpdateUIforRealStatus(holder.acceptLay, holder.rejectLay, holder.accept, holder.reject, holder.property_exits_lay, holder.property_exits_text, holder.property_exits, real_status_id, caseid);
        //ChecktoUpdateUIforRealStatus(holder.acceptLay, holder.rejectLay, holder.accept, holder.reject, holder.property_exits_lay, holder.property_heading_type_div, real_status_id, caseid);

        /******
         * Property Doesnt exists
         * *****/
        holder.property_exits.setChecked(false);
        holder.property_exits.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if (ischecked) {
                    if (Connectivity.isConnected(mContext)) {
                        case_id = dataModels.get(position).getCaseId();
                        PropertyExistsRejectPopup(case_id, mContext.getResources().getString(R.string.property_exits), mContext.getResources().getString(R.string.property_exists_text));
                    } else {
                        Connectivity.showNoConnectionDialog(mContext);
                        holder.property_exits.setChecked(false);
                        general.hideloading();
                    }
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
                String real_status_id = dataModels.get(position).getStatusId();
                String property_cate_id = dataModels.get(position).getPropertyCategoryId();
                Singleton.getInstance().propertyId = dataModels.get(position).getPropertyId();

                SettingsUtils.getInstance().putValue(SettingsUtils.Case_Title, dataModels.get(position).getApplicantName());
                SettingsUtils.getInstance().putValue(SettingsUtils.Case_Bank, dataModels.get(position).getBankName());
                SettingsUtils.getInstance().putValue(SettingsUtils.PropertyId, dataModels.get(position).getPropertyId());
                SettingsUtils.getInstance().putValue(SettingsUtils.PropertyCategoryId, property_cate_id);
                SettingsUtils.getInstance().putValue(SettingsUtils.PropertyType, dataModels.get(position).getPropertyType());
                SettingsUtils.getInstance().putValue(SettingsUtils.CASE_ID, case_id);
                SettingsUtils.getInstance().putValue(SettingsUtils.Case_BankBranch, dataModels.get(position).getBankBranchName());
                SettingsUtils.getInstance().putValue(SettingsUtils.StatusId, dataModels.get(position).getStatusId());
                SettingsUtils.getInstance().putValue(SettingsUtils.ReportName, dataModels.get(position).getReportName());
                SettingsUtils.getInstance().putValue(SettingsUtils.is_local, false);


                CallUpdateStatusfromRealStatus(holder.acceptLay, "accept", real_status_id, case_id, property_cate_id);
                if (real_status_id.equalsIgnoreCase("12")) {
                    Singleton.getInstance().status = "accept";

                    if (appDatabase.interfaceDataModelQuery().getDataModels().size() > 0) {
                        appDatabase.interfaceDataModelQuery().updatePendingDataModel(caseid, false);
                        appDatabase.interfaceDataModelQuery().updatePendingDataModelToOPen(caseid,true);
                    }

                }

                transferClickListener.caseAction();

            }
        });

        /******
         * Reject / Edit Button click listener
         * *******/
        holder.rejectLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                case_id = dataModels.get(position).getCaseId();
                SettingsUtils.getInstance().putValue(SettingsUtils.CASE_ID, case_id);
                SettingsUtils.getInstance().putValue(SettingsUtils.PropertyType, dataModels.get(position).getPropertyType());
                SettingsUtils.getInstance().putValue(SettingsUtils.Case_Title, dataModels.get(position).getApplicantName());
                SettingsUtils.getInstance().putValue(SettingsUtils.Case_Bank, dataModels.get(position).getBankName());
                SettingsUtils.getInstance().putValue(SettingsUtils.StatusId, dataModels.get(position).getStatusId());

                String real_status_id = dataModels.get(position).getStatusId();
                String property_cate_id = dataModels.get(position).getPropertyCategoryId();
                SettingsUtils.getInstance().putValue(SettingsUtils.PropertyCategoryId, property_cate_id);

                my_property_cate_id = dataModels.get(position).getPropertyCategoryId();
                SettingsUtils.getInstance().putValue(SettingsUtils.PropertyId, dataModels.get(position).getPropertyId());


                if (Connectivity.isConnected(mContext)) {
                    if (real_status_id.equalsIgnoreCase("12")) {
                        Singleton.getInstance().status = "reject";
                        RejectPopup(case_id, mContext.getResources().getString(R.string.reject_status), mContext.getResources().getString(R.string.reject_text));
                    } else {
                        // set as true to check the the function 1) send to report maker or 2) Show popup
                        is_validation_check = true;
                        Log.e("RejectLay :", "Ok");
                        CaseEditInspectionWebservice(case_id);
                        // PropertyExistsRejectPopup(case_id, mContext.getResources().getString(R.string.send_to_admin), mContext.getResources().getString(R.string.admin_text));
                    }
                } else {
                    Connectivity.showNoConnectionDialog(mContext);
                }

            }
        });


        holder.transfer_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferClickListener.transfer(caseId,applicantName);
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
                    boolean real_appraiser_jaipur = SettingsUtils.getInstance().getValue(SettingsUtils.real_appraiser_jaipur, false);
                    if (real_appraiser_jaipur) {
                        // Jaipur
                        case_id = dataModels.get(position).getCaseId();
                        StatusId_is = dataModels.get(position).getStatusId();
                        SettingsUtils.getInstance().putValue(SettingsUtils.CASE_ID, case_id);
                        property_caseid = dataModels.get(position).getCaseId();
                        bank_id = dataModels.get(position).getBankId();
                        type_id = dataModels.get(position).getTypeID();
                        property_type = dataModels.get(position).getPropertyType();
                        agencybranchid = dataModels.get(position).getAgencyBranchId();
                        // Old Method
                        // LoadPropertyTypeWebservice(bank_id, type_id);
                        // New Method
                        PropertyTypeUpdatePopup_kakode(mContext.getResources().getString(R.string.property_type) + property_caseid, case_id);
                    } else {
                        // Kakode
                        case_id = dataModels.get(position).getCaseId();
                        StatusId_is = dataModels.get(position).getStatusId();
                        SettingsUtils.getInstance().putValue(SettingsUtils.CASE_ID, case_id);
                        property_caseid = dataModels.get(position).getCaseId();
                        bank_id = dataModels.get(position).getBankId();
                        type_id = dataModels.get(position).getTypeID();
                        property_type = dataModels.get(position).getPropertyType();
                        agencybranchid = dataModels.get(position).getAgencyBranchId();
                        // Old Method
                        // LoadPropertyTypeWebservice(bank_id, type_id);
                        // New Method
                        PropertyTypeUpdatePopup_kakode(mContext.getResources().getString(R.string.property_type) + property_caseid, case_id);
                    }
                }
            });
        }


        /*******
         * Document Read Click Listener
         * *****/
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
                /*property_heading_type_div.setVisibility(View.GONE);*/
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
             * *Rejected Case, case item will remove from open list
             * *********/
            case "15":


                break;

            /*********
             * *Edit Inspection, when one or two items entered and
             * saved from detail page
             * *********/
            case "2":
                accept.setText(mContext.getResources().getString(R.string.edit));
                reject.setText(mContext.getResources().getString(R.string.send));
                reject.setEnabled(true);
                rejectLay.setVisibility(View.VISIBLE);
                property_exits_lay.setVisibility(View.VISIBLE); // GONE
                property_exits.setVisibility(View.INVISIBLE);
                property_exits_text.setText(mContext.getResources().getString(R.string.property_has_exists));
                /******Delete Offline data when it already stored*****/
                DeleteOfflineDatabyCaseID(case_id);
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
                    UpdateStatusCaseIdWebservice(case_id, accept_status_id);
                }

                break;

            /*********
             * *Accepted Case, i.e Start Inspection will toggle here
             * *********/
            case "13":


                if (laymode.equalsIgnoreCase("accept")) {
                    if (property_cate_id.equalsIgnoreCase("2") || property_cate_id.equalsIgnoreCase("4") || property_cate_id.equalsIgnoreCase("1") || property_cate_id.equalsIgnoreCase("3")) {
                        getCaseDetailsforInspection();
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
                        getCaseDetailsforInspection();
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

    private void getCaseDetailsforInspection() {
        /********Form Detail intent start activitycall*******/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SettingsUtils.getInstance().putValue(SettingsUtils.CASE_ID, case_id);
                // online
                SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, false);
                getCaseIdDetailsWebSerice(case_id);


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
        private LinearLayout property_type_update_lay, document_read_lay, property_heading_type_div, property_exits_lay,transfer_lay;
        private TextView accept, reject, textview_property_heading, textview_property_type_heading;
        RelativeLayout relative_name, relative_address;

        public ViewHolder(View itemView) {
            super(itemView);
            case_id = (TextView) itemView.findViewById(R.id.case_id);
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
            relative_name = (RelativeLayout) itemView.findViewById(R.id.relative_name);
            relative_address = (RelativeLayout) itemView.findViewById(R.id.relative_address);
            transfer_lay = itemView.findViewById(R.id.transfer_lay);


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


    /*******
     * Property doesnt exists check / Accept / Reject  API call
     * *******/
    private void UpdateStatusCaseIdWebservice(String case_id, String statusId) {
        if (Connectivity.isConnected(mContext)) {
            general.showloading(mContext);
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
            updateCaseStatusModel = dataResponse.updateCaseStatusModel;
            Singleton.getInstance().updateCaseStatusModel = dataResponse.updateCaseStatusModel;
        }

        if (result != null) {
            if (result.equals("1")) {
                CheckUpdateStatusforAction(Singleton.getInstance().updateCaseStatusModel);
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

    @SuppressLint("SetTextI18n")
    private void CheckUpdateStatusforAction(DataModel updateCaseStatusModel) {

        if (updateCaseStatusModel != null) {
            String status_id = updateCaseStatusModel.getStatus();

            switch (status_id) {
                /*********
                 * *Accepted Case, i.e Start Inspection will toggle here
                 * *********/
                case "13":
                    UpdateOfflineStatusCaseStartInspect(case_id, mContext.getResources().getString(R.string.accept_status));
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
                    DeleteOfflineDatabyCaseID(case_id);
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
                   Singleton.getInstance().openCaseList.clear();
                    Singleton.getInstance().pendingCaseList.clear();
                    Singleton.getInstance().pendingCaseList.clear();
                    Singleton.getInstance().closeCaseList.clear();
                    Intent intent = new Intent(mContext, HomeActivity.class);
                    mContext.startActivity(intent);
                    break;

                case "3":
                    general.CustomToast("Succesfully send to Case admin");
                    RemoveCasefromList();
                   Singleton.getInstance().openCaseList.clear();
                    Singleton.getInstance().pendingCaseList.clear();
                    Singleton.getInstance().closeCaseList.clear();
                    Intent intent1 = new Intent(mContext, HomeActivity.class);
                    mContext.startActivity(intent1);
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

    private void AcceptedCasefromList() {
        if (Singleton.getInstance().openCaseList != null) {
            if (Singleton.getInstance().openCaseList.size() > 0) {

                String caseid = Singleton.getInstance().updateCaseStatusModel.getCaseId();
                for (int i = 0; i < Singleton.getInstance().openCaseList.size(); i++) {
                    String opencaseid = Singleton.getInstance().openCaseList.get(i).getCaseId();

                    if (caseid.equalsIgnoreCase(opencaseid)) {
                        dataModel = Singleton.getInstance().openCaseList.get(i);
                        dataModel.setStatusId(mContext.getResources().getString(R.string.accept_status));
                        Singleton.getInstance().openCaseList.set(i, dataModel);
                    }
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
                UpdateStatusCaseIdWebservice(case_id, statusid); //Doesn't property exists or reject
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

        if (Singleton.getInstance().rejectionComments_list.size() == 0) {
            String dropdown_response = SettingsUtils.getInstance().getValue(SettingsUtils.DropDownSave, "");
            parseGetDropDownsDataResponse(dropdown_response);
        }

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
                    if (Singleton.getInstance().rejectionComments_list.size() > 0) {
                        String remarks = String.valueOf(Singleton.getInstance().rejectionComments_list.get(position).getComment());
                        UpdateStatusRejectCaseIdWebservice(case_id, statusid, remarks);
                        dialog.dismiss();
                    } else {
                        dialog.dismiss();
                    }
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

    private void UpdateStatusRejectCaseIdWebservice(String case_id, String statusId, String remarks) {

        if (Connectivity.isConnected(mContext)) {
            general.showloading(mContext);
            InitiateUpdateStatusRejectCaseidTask(case_id, statusId, remarks);
            if (appDatabase.interfaceDataModelQuery().getDataModels().size() > 0) {
                appDatabase.interfaceDataModelQuery().updatePendingDataModel(case_id, false);
                appDatabase.interfaceDataModelQuery().updatePendingDataModelToOPen(case_id,true);
            }
            transferClickListener.caseAction();
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
    }

    private void InitiateUpdateStatusRejectCaseidTask(String case_id, String statusId, String remarks) {

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
                parseRejectStatusCaseidResponse(requestData.getResponse());
            }
        });
        webserviceTask.execute();
    }

    private void parseRejectStatusCaseidResponse(String response) {

        DataResponse dataResponse = ResponseParser.parseUpdateStatusCaseResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
            updateCaseStatusModel = dataResponse.updateCaseStatusModel;
            Singleton.getInstance().updateCaseStatusModel = dataResponse.updateCaseStatusModel;
        }

        if (result != null) {
            if (result.equals("1")) {
                DeleteOfflineDatabyCaseID(case_id);
               Singleton.getInstance().openCaseList.clear();
                    Singleton.getInstance().pendingCaseList.clear();
                Singleton.getInstance().closeCaseList.clear();
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

                    GetReportPropertyTypeWebservice(propertyType, bank_id, agencybranchid);
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
            propertyTypeList = dataResponse.propertyTypeList;
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

    /**********
     * get Report Property Type for particular case Webservice
     * **********/
    private void GetReportPropertyTypeWebservice(String property_typeid, String bank_id, String agencybranchid) {


        if (Singleton.getInstance().typeOfProperties_list != null)
            if (Singleton.getInstance().typeOfProperties_list.size() > 0) {

                for (int i = 0; i < Singleton.getInstance().typeOfProperties_list.size(); i++) {
                    int typeproperty = Singleton.getInstance().typeOfProperties_list.get(i).getTypeOfPropertyId();
                    if (property_typeid.equalsIgnoreCase(String.valueOf(typeproperty))) {
                        propertyCategoryId = Singleton.getInstance().typeOfProperties_list.get(i).getPropertyCategoryId();
                    }
                }
            }

        if (Connectivity.isConnected(mContext)) {
            general.showloading(mContext);
            InitiateGetReportPropertyTypeTask(bank_id, agencybranchid, propertyCategoryId);
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
    }

    private void InitiateGetReportPropertyTypeTask(String bank_id, String agencybranchid, String property_categoryid) {

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
    }

    private void parseGetReportPropertyTypeResponse(String response) {

        DataResponse dataResponse = ResponseParser.parseGetReportTypeResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
            getReportTypeModel = new DataModel();
            getReportTypeModel = dataResponse.getReportTypeModel;
            Singleton.getInstance().getReportTypeModel = dataResponse.getReportTypeModel;
            reporty_type = dataResponse.getReportTypeModel.getTypeID();
            //  Singleton.getInstance().getReportTypeModel.setPropertyType(property_name_from_type);
        }

        if (result != null) {
            if (result.equals("1")) {
                PropertyTypeUpdateWebservice(propertyType, bank_id, reporty_type);//bankname, reportytype
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

    /**********
     *  Property Type Update for particular case Webservice
     * **********/
    private void PropertyTypeUpdateWebservice(String PropertyType, String BankNameid, String ReportType) {

        if (Connectivity.isConnected(mContext)) {
            general.showloading(mContext);
            InitiatePropertyTypeUpdateTask(PropertyType, BankNameid, ReportType);
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
    }

    private void InitiatePropertyTypeUpdateTask(String PropertyType, String BankNameid, String ReportType) {

        String caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        String modifiedby = SettingsUtils.getInstance().getValue(SettingsUtils.KEY_LOGIN_ID, "");
        String url = general.ApiBaseUrl() + SettingsUtils.UpdatePropertytype;
        JsonRequestData requestData = new JsonRequestData();
        requestData.setUrl(url);
        requestData.setCaseId(caseid);
        requestData.setPropertyType(PropertyType);
        requestData.setBankName(BankNameid);
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
    }

    private void parsePropertyTypeUpdateResponse(String response) {

        DataResponse dataResponse = ResponseParser.parseUpdatePropertyTypeResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
            updatePropertyTypeStatusModel = new DataModel();
            updatePropertyTypeStatusModel = dataResponse.updatePropertyTypeStatusModel;
            Singleton.getInstance().updatePropertyTypeStatusModel = dataResponse.updatePropertyTypeStatusModel;
            Singleton.getInstance().updatePropertyTypeStatusModel.setPropertyType(property_name_from_type);
        }

        if (result != null) {
            if (result.equals("1")) {
                /******Update offline table for room db*******/
                Singleton.getInstance().PROPERTY_TYPE_UPDATED = true;
                Singleton.getInstance().propertyupdate_caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
                updateOfflinePropertyType();
                /*******property type offline update ended******/
               Singleton.getInstance().openCaseList.clear();
                    Singleton.getInstance().pendingCaseList.clear();
                Singleton.getInstance().closeCaseList.clear();
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

    /*************when the Property Type is Updated @opencase, then change in offline case for Roomdb also
     * because the offline case is initially only deleting the table once its inserted it cant be delete and
     * restore again****************/
    private void updateOfflinePropertyType() {

        if (Singleton.getInstance().PROPERTY_TYPE_UPDATED) {
            String caseid = Singleton.getInstance().propertyupdate_caseid;
            if (!General.isEmpty(caseid)) {

                if (MyApplication.getAppContext() != null) {
                    AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());

                    long propertytype = appDatabase.interfaceOfflineDataModelQuery().updatePropertytypefor(property_name_from_type, propertyCategoryId, caseid);
                    Log.e("updated offlinecase db", ":" + propertytype);
                    Singleton.getInstance().PROPERTY_TYPE_UPDATED = false;
                }
            }
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
            updatePropertyTypeStatusModel = new DataModel();
            updatePropertyTypeStatusModel = dataResponse.updatePropertyTypeStatusModel;
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
                        dataModel = Singleton.getInstance().openCaseList.get(i);
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
    private void DocumentReadWebservice(String CaseId) {

        if (Connectivity.isConnected(mContext)) {
            general.showloading(mContext);
            InitiateDocumentReadTask(CaseId);
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
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
            documentRead = dataResponse.documentRead;
            Singleton.getInstance().documentRead = dataResponse.documentRead;
            if (documentRead.size() >= 1) {
                general.hideloading();
                documentpopup(mContext, documentRead);
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


    /*******
     * getInspection for save or edit Inspection on Button Webservice call
     * *******/
    private void getCaseIdDetailsWebSerice(String case_id) {
        if (Connectivity.isConnected(mContext)) {
            general.showloading(mContext);
            InitiateCaseInspectionDeatailsTask(case_id);
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
    }

    private void InitiateCaseInspectionDeatailsTask(String case_id) {

        String url = general.ApiBaseUrl() + SettingsUtils.GetCaseInspection;
        //  String url = SettingsUtils.BASE_URL + SettingsUtils.EditCaseInspection;

        JsonRequestData requestData = new JsonRequestData();
        requestData.setInitQueryUrl(url);
        requestData.setId(case_id); // id
        requestData.setUrl(RequestParam.getCaseInspectionRequestParams(requestData));

        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.GET);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                parseGetCaseInspectionDetailsResponse(requestData.getResponse());
            }
        });
        webserviceTask.execute();
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


    private void parseGetCaseInspectionDetailsResponse(String response) {

        DataResponse dataResponse = ResponseParser.parseGetCaseInspectionDetailsResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;
            Singleton.getInstance().caseOtherDetailsModel = dataResponse.caseOtherDetailsModel;
            Log.e("CaseDetailsReponse :", new Gson().toJson(Singleton.getInstance().caseOtherDetailsModel));

        }

        if (result != null) {
            if (result.equals("1")) {
                // Jaipur
                //CheckSingffletonDropdowns();
                general.hideloading();
                Singleton.getInstance().itemList.clear();
                Singleton.getInstance().itemList.add("" + R.drawable.holder);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SettingsUtils.getInstance().putValue(SettingsUtils.CASE_ID, case_id);
                        // online
                        SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, false);
                        // set as false to start activity to PhotoTab
                        is_validation_check = false;
                        Log.e("getCaseDetailsForInspection :", "Ok");
                        CaseEditInspectionWebservice(case_id);

                    }
                }, 0);
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
            Log.e("Called Edit Inspection :", "Ok");
        }

        getDBValue();
        if (result != null) {
            if (result.equals("1")) {
                boolean real_appraiser_jaipur = SettingsUtils.getInstance().getValue(SettingsUtils.real_appraiser_jaipur, false);
                if (real_appraiser_jaipur) {
                    // Jaipur
                    CheckSingletonDropdowns();
                } else {
                    // Kakode
                    get_image();
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

        // TODO - validation_check for Mandatory feilds for all the property types
        if (is_validation_check) {
            // check validation for the particular property and show validation popup or send to report maker
            if (my_property_cate_id.equalsIgnoreCase("2")) {
                // building
                if (building_validation()) {
                    // After all validation check the Image api for altest one Image
                    HitImageAPI();
                } else {
                    // show All mandatory field popup
                    general.hideloading();
                    call_validationpopup();
                    //call_validation_popup();
                }
            } else if (my_property_cate_id.equalsIgnoreCase("1")) {
                // flat
                if (flat_validation()) {
                    // After all validation check the Image api for altest one Image
                    HitImageAPI();
                } else {
                    // show All mandatory field popup
                    general.hideloading();
                    call_validationpopup();
                }
            } else if (my_property_cate_id.equalsIgnoreCase("4")) {
                // penthouse
                if (penthouse_validation()) {
                    // After all validation check the Image api for altest one Image
                    HitImageAPI();
                } else {
                    // show All mandatory field popup
                    general.hideloading();
                    call_validationpopup();
                }
            } else if (my_property_cate_id.equalsIgnoreCase("3")) {
                // land
                if (land_validation()) {
                    // After all validation check the Image api for altest one Image
                    HitImageAPI();
                } else {
                    // show All mandatory field popup
                    general.hideloading();
                    call_validationpopup();
                }
            }
        } else {
            general.hideloading();
            boolean real_appraiser_jaipur = SettingsUtils.getInstance().getValue(SettingsUtils.real_appraiser_jaipur, false);
            if (real_appraiser_jaipur) {
                // Jaipur
                String status_id = SettingsUtils.getInstance().getValue(SettingsUtils.StatusId, "");
                if (status_id.equalsIgnoreCase("13")) {
                    // Start Inspection as 13
                    StartInspectionPopup();
                } else {
                    // Edit Inspection
                    Singleton.getInstance().enable_validation_error = false;
                    // online
                    SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, false);
                    mContext.startActivity(new Intent(mContext, PhotoLatLngTab.class));
                }
            } else {
                // Kakode
                Singleton.getInstance().enable_validation_error = false;
                // online
                SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, false);
                mContext.startActivity(new Intent(mContext, PhotoLatLngTab.class));
            }
        }
    }


    /*******
     * StartInspectionPopup Dialog
     * *******/
    private void StartInspectionPopup() {
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

        String title_is = "Do you want to start inspection for " + SettingsUtils.getInstance().getValue(SettingsUtils.PropertyType, "") + " case?";
        popuptitle.setText(title_is);



        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (general.isNetworkAvailable()&general.isLocationEnabled(mContext)){

                    boolean shareLocation = locationTrackerApi.shareLocation(case_id,
                            SettingsUtils.getInstance().getValue(SettingsUtils.KEY_LOGIN_ID, ""),
                            "CaseStart", SettingsUtils.Latitudes, SettingsUtils.Longitudes);


                        Singleton.getInstance().enable_validation_error = false;
                        // online
                        SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, false);
                        mContext.startActivity(new Intent(mContext, PhotoLatLngTab.class));
                        dialog.dismiss();

                }else {

                    general.customToast("Make sure that your internet connection and location enabled!",mContext);
                }

            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private boolean building_validation() {

        boolean is_valid = true;

        // Lat Long
        if (general.isEmpty(Singleton.getInstance().property.getLatLongDetails())) {
            // empty
            is_valid = false;
        }
        // PropertyAddress
        if (general.isEmpty(Singleton.getInstance().aCase.getPropertyAddress())) {
            // empty
            is_valid = false;
        }
       /* if (general.isEmpty(Singleton.getInstance().aCase.getContactPersonName())) {
            // empty
            is_valid = false;
        }
        if (general.isEmpty(Singleton.getInstance().aCase.getContactPersonNumber())) {
            // empty
            is_valid = false;
        }*/
        // PropertyAddressAtSite
        if (general.isEmpty(Singleton.getInstance().property.getPropertyAddressAtSite())) {
            // empty
            is_valid = false;
        }
        // DocumentLandArea (Compound)
        if (general.isEmpty(Singleton.getInstance().indProperty.getDocumentLandArea())) {
            // empty
            is_valid = false;
        }
        // MeasuredLandArea (Permissible area)
        if (general.isEmpty(Singleton.getInstance().indProperty.getMeasuredLandArea())) {
            // empty
            is_valid = false;
        }
        // DocumentLandAreaUnit (As per measurement)
        if (!general.isEmpty(String.valueOf(Singleton.getInstance().indProperty.getDocumentLandAreaUnit()))) {
            // not empty
            /*if (Singleton.getInstance().indProperty.getDocumentLandAreaUnit() == 0) {
                is_valid = false;
            }*/
        }
        // Floors
        ArrayList<IndPropertyFloor> floor_list = new ArrayList<>();
        floor_list = Singleton.getInstance().indPropertyFloors;
        if (floor_list.size() == 0) {
            is_valid = false;
        } else {
            for (int j = 0; j < floor_list.size(); j++) {
                String floorName = floor_list.get(j).getFloorName();
                if (general.isEmpty(floorName)) {
                    is_valid = false;
                }
                int constructionStageId = floor_list.get(j).getConstructionStageId();
                if (constructionStageId == 0) {
                    is_valid = false;
                }
                String percentageCompletion = String.valueOf(floor_list.get(j).getPercentageCompletion());
                if (general.isEmpty(percentageCompletion)) {
                    is_valid = false;
                }
                // For Measurment Construction
                String DocumentFloorAreaUnit = String.valueOf(floor_list.get(j).getDocumentFloorAreaUnit());
                if (general.isEmpty(DocumentFloorAreaUnit)) {
                    is_valid = false;
                } else {
                    if (DocumentFloorAreaUnit.equalsIgnoreCase("0")) {
                        is_valid = false;
                    }
                }
            }
        }


        // Check the Measurment Image on kakode
        boolean real_appraiser_jaipur = SettingsUtils.getInstance().getValue(SettingsUtils.real_appraiser_jaipur, false);
        if (!real_appraiser_jaipur) {
            // Kakode
            if (Singleton.getInstance().GetImage_list_flat.size() == 0) {
                is_valid = false;
            }
        }


        return is_valid;
    }

    private void HitImageAPI() {
        JsonRequestData data = new JsonRequestData();
        String PropertyId = SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, "");
        data.setUrl(general.ApiBaseUrl() + SettingsUtils.GETIMAGE + PropertyId);
        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, data, SettingsUtils.GET);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                //parseViewimageResponse(requestData.getResponse());
                String response = requestData.getResponse();
                DataResponse dataResponse = new DataResponse();
                try {
                    if (response != null) {
                        JSONObject object = new JSONObject(response);
                        dataResponse.status = object.getString("status");
                        if (dataResponse.status.equalsIgnoreCase("1")) {
                            JSONArray jsonArray = object.getJSONArray("data");
                            if (jsonArray.length() == 0) {
                                // show All mandatory field popup
                                general.hideloading();
                                call_validationpopup();
                            } else {
                                general.hideloading();
                                boolean real_appraiser_jaipur = SettingsUtils.getInstance().getValue(SettingsUtils.real_appraiser_jaipur, false);
                                if (real_appraiser_jaipur) {
                                    // Jaipur
                                    PropertyExistsRejectPopup(case_id, mContext.getResources().getString(R.string.send_to_admin), mContext.getResources().getString(R.string.admin_text));
                                } else {
                                    // Kakode
                                    PropertyExistsRejectPopup(case_id, mContext.getResources().getString(R.string.send_to_admin_kakode), mContext.getResources().getString(R.string.admin_text_kakode));
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        webserviceTask.execute();
    }

    private void call_validationpopup() {
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

        boolean real_appraiser_jaipur = SettingsUtils.getInstance().getValue(SettingsUtils.real_appraiser_jaipur, false);
        if (real_appraiser_jaipur) {
            // Jaipur
            popuptitle.setText(mContext.getResources().getString(R.string.mandatory_fieldmsg));
        } else {
            // Kakode
            popuptitle.setText(mContext.getResources().getString(R.string.mandatory_fieldmsg_kakode));
        }

        yesBtn.setText("OK");
        noBtn.setText("CANCEL");
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (general.isNetworkAvailable()&general.isLocationEnabled(mContext)){

                    boolean shareLocation = locationTrackerApi.shareLocation(case_id,
                            SettingsUtils.getInstance().getValue(SettingsUtils.KEY_LOGIN_ID, ""),
                            "CaseFinish", SettingsUtils.Latitudes, SettingsUtils.Longitudes);
                        Singleton.getInstance().enable_validation_error = true;
                        // online
                        SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, false);
                        mContext.startActivity(new Intent(mContext, PhotoLatLngTab.class));
                        dialog.dismiss();

                }else {
                    general.hideloading();
                    general.customToast("Make sure that your internet connection and location enabled!",mContext);
                }



            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void call_validation_popup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AlertDialogCustom));
        builder.setMessage(mContext.getResources().getString(R.string.mandatory_field))
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Singleton.getInstance().enable_validation_error = true;
                        // online
                        SettingsUtils.getInstance().putValue(SettingsUtils.is_offline, false);
                        mContext.startActivity(new Intent(mContext, PhotoLatLngTab.class));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void parseGetDropDownsDataResponse(String response) {
        DataResponse dataResponse = ResponseParser.parseGetFieldDropDownResponse(response);
        general.hideloading();
    }


    private boolean penthouse_validation() {

        boolean is_valid = true;

        // Lat Long
        if (general.isEmpty(Singleton.getInstance().property.getLatLongDetails())) {
            // empty
            is_valid = false;
        }
        // PropertyAddress
        if (general.isEmpty(Singleton.getInstance().aCase.getPropertyAddress())) {
            // empty
            is_valid = false;
        }
       /* if (general.isEmpty(Singleton.getInstance().aCase.getContactPersonName())) {
            // empty
            is_valid = false;
        }
        if (general.isEmpty(Singleton.getInstance().aCase.getContactPersonNumber())) {
            // empty
            is_valid = false;
        }*/
        // PropertyAddressAtSite
        if (general.isEmpty(Singleton.getInstance().property.getPropertyAddressAtSite())) {
            // empty
            is_valid = false;
        }
        // DocumentLandArea (Compound)
       /* if (general.isEmpty(Singleton.getInstance().indProperty.getDocumentLandArea())) {
            // empty
            is_valid = false;
        }*/

        String flatsituated = Singleton.getInstance().indProperty.getFlatSituatedInFloor();
        if (Singleton.getInstance().indPropertyFloors.size() > 0) {
            stagespinner = Singleton.getInstance().indPropertyFloors.get(0).getConstructionStageId();
            percentagecomp = String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getPercentageCompletion());
        }

        /*if (general.isEmpty(Singleton.getInstance().indPropertyValuation.getTerraceArea())) {
            is_valid = false;
        }

        if (general.isEmpty(Singleton.getInstance().indPropertyValuation.getBalconyArea())) {
            is_valid = false;
        }*/

        if (general.isEmpty(flatsituated)) {
            is_valid = false;
        }

        if (stagespinner == 0) {
            is_valid = false;
        }

        if (general.isEmpty(percentagecomp)) {
            is_valid = false;
        } else if (percentagecomp.equalsIgnoreCase("0")) {
            is_valid = false;
        }

        // Check the Measurment Image on kakode
        boolean real_appraiser_jaipur = SettingsUtils.getInstance().getValue(SettingsUtils.real_appraiser_jaipur, false);
        if (!real_appraiser_jaipur) {
            // Kakode
            if (Singleton.getInstance().GetImage_list_flat.size() == 0) {
                is_valid = false;
            }
            if (general.isEmpty(Singleton.getInstance().indPropertyValuation.getConstructionDLCRate())) {
                // empty
                is_valid = false;
            }
            if (general.isEmpty(Singleton.getInstance().indPropertyValuation.getGovernmentArea())) {
                // empty
                is_valid = false;
            }
        }

        return is_valid;
    }

    private boolean flat_validation() {

        boolean is_valid = true;

        // Lat Long
        if (general.isEmpty(Singleton.getInstance().property.getLatLongDetails())) {
            // empty
            is_valid = false;
        }
        // PropertyAddress
        if (general.isEmpty(Singleton.getInstance().aCase.getPropertyAddress())) {
            // empty
            is_valid = false;
        }
        // PropertyAddressAtSite
        if (general.isEmpty(Singleton.getInstance().property.getPropertyAddressAtSite())) {
            // empty
            is_valid = false;
        }
       /* if (general.isEmpty(Singleton.getInstance().aCase.getContactPersonName())) {
            // empty
            is_valid = false;
        }
        if (general.isEmpty(Singleton.getInstance().aCase.getContactPersonNumber())) {
            // empty
            is_valid = false;
       } */

        String flatsituated = Singleton.getInstance().indProperty.getFlatSituatedInFloor();
        if (Singleton.getInstance().indPropertyFloors.size() > 0) {
            stagespinner = Singleton.getInstance().indPropertyFloors.get(0).getConstructionStageId();
            percentagecomp = String.valueOf(Singleton.getInstance().indPropertyFloors.get(0).getPercentageCompletion());
        }

        if (general.isEmpty(flatsituated)) {
            is_valid = false;
        }

        if (stagespinner == 0) {
            is_valid = false;
        }

        if (general.isEmpty(percentagecomp)) {
            is_valid = false;
        } else if (percentagecomp.equalsIgnoreCase("0")) {
            is_valid = false;
        }

        // Check the Measurment Image on kakode
        boolean real_appraiser_jaipur = SettingsUtils.getInstance().getValue(SettingsUtils.real_appraiser_jaipur, false);
        if (!real_appraiser_jaipur) {
            // Kakode
            if (Singleton.getInstance().GetImage_list_flat.size() == 0) {
                is_valid = false;
            }
            if (general.isEmpty(Singleton.getInstance().indPropertyValuation.getConstructionDLCRate())) {
                // empty
                is_valid = false;
            }
            if (general.isEmpty(Singleton.getInstance().indPropertyValuation.getGovernmentArea())) {
                // empty
                is_valid = false;
            }
        }

        return is_valid;
    }


    private boolean land_validation() {
        boolean is_valid = true;

        // Lat Long
        if (general.isEmpty(Singleton.getInstance().property.getLatLongDetails())) {
            // empty
            is_valid = false;
        }
        // PropertyAddress
        if (general.isEmpty(Singleton.getInstance().aCase.getPropertyAddress())) {
            // empty
            is_valid = false;
        }
       /* if (general.isEmpty(Singleton.getInstance().aCase.getContactPersonName())) {
            // empty
            is_valid = false;
        }
        if (general.isEmpty(Singleton.getInstance().aCase.getContactPersonNumber())) {
            // empty
            is_valid = false;
        }*/
        // PropertyAddressAtSite
        if (general.isEmpty(Singleton.getInstance().property.getPropertyAddressAtSite())) {
            // empty
            is_valid = false;
        }
        // DocumentLandArea (Compound)
        if (general.isEmpty(Singleton.getInstance().indProperty.getDocumentLandArea())) {
            // empty
            is_valid = false;
        }
        // MeasuredLandArea (Permissible area)
        if (general.isEmpty(Singleton.getInstance().indProperty.getMeasuredLandArea())) {
            // empty
            is_valid = false;
        }

        return is_valid;
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

    /**************
     * Todo delete the offline case data for status is 15 or 19 or it changed to edit inspection "2"
     * when the case is rejected or property doesnt exists is synched to server
     * ******************/
    private void DeleteOfflineDatabyCaseID(String case_id) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(mContext);
        //make the offline case datamodel to delete the case
        if (MyApplication.getAppContext() != null) {
            if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
                int value = appDatabase.interfaceOfflineDataModelQuery().DeleteOfflineDatabyCaseid(case_id);
                Log.e("delete offline case row", value + "");
            }
        }
    }

    /**************
     * Todo update the offline status case Startinspection
     * on the button action changes for assigned list of items
     * ******************/
    private void UpdateOfflineStatusCaseStartInspect(String case_id, String updateCaseStatus) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(mContext);
        //make the offline case datamodel to set status
        if (MyApplication.getAppContext() != null) {
            if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
                long value = appDatabase.interfaceOfflineDataModelQuery().updateOfflineCaseStatusonline(updateCaseStatus, case_id);
                long value1 = appDatabase.interfaceDataModelQuery().updateOnlineCaseStatusonline(updateCaseStatus, case_id);
                Log.e("update case status", value + "");
            }
        }
    }


    private void get_image() {
        if (Connectivity.isConnected(mContext)) {
            JsonRequestData data = new JsonRequestData();
            String PropertyId = SettingsUtils.getInstance().getValue(SettingsUtils.PropertyId, "");
            data.setUrl(general.ApiBaseUrl() + SettingsUtils.GETIMAGE_ONE + PropertyId);
            WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, data, SettingsUtils.GET);
            webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                @Override
                public void onTaskComplete(JsonRequestData requestData) {
                    parseViewimageResponse_one(requestData.getResponse());
                }
            });
            webserviceTask.execute();
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
    }

    private void parseViewimageResponse_one(String response) {
        // Init as empty
        ArrayList<GetPhoto_measurment> GetImage_list_flat_response_empty = new ArrayList<>();
        Singleton.getInstance().GetImage_list_flat = GetImage_list_flat_response_empty;

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
                        CheckSingletonDropdowns();
                    } else {
                        CheckSingletonDropdowns();
                    }
                } else {
                    CheckSingletonDropdowns();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // Kakode Propety Type change
    private void PropertyTypeUpdatePopup_kakode(String msg, final String case_id) {
        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.property_type_popup);

        final RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.property_type_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<TypeOfProperty> typeOfProperties_list = new ArrayList<>();
        if (MyApplication.getAppContext() != null) {
            AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
            typeOfProperties_list = (ArrayList<TypeOfProperty>) appDatabase.typeofPropertyQuery().getTypeofPropertyModels();
        }
        final PropertyTypeAdapter_offline adapter = new PropertyTypeAdapter_offline(mContext, property_type, typeOfProperties_list);
        // final PropertyTypeAdapter adapter = new PropertyTypeAdapter(mContext, property_type, property_caseid, Singleton.getInstance().propertyTypeList);
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

                TypeOfProperty typeOfProperty = new TypeOfProperty();
                typeOfProperty = adapter.typeOfProperty();
                String typeOfProperty_Name = typeOfProperty.getName();
                String typeOfProperty_PropertyCategoryId = typeOfProperty.getPropertyCategoryId();
                String typeOfProperty_TypeOfPropertyId = String.valueOf(typeOfProperty.getTypeOfPropertyId());

                property_name_from_type = typeOfProperty.getName();
                propertyCategoryId = typeOfProperty.getPropertyCategoryId();
                changed_TypeOfPropertyId = typeOfProperty.getTypeOfPropertyId();

                Log.e("typeOfProperty_TypeOfPropertyId", ": " + typeOfProperty_TypeOfPropertyId);

                /******Update offline table for room db*******/
                Singleton.getInstance().PROPERTY_TYPE_UPDATED = true;
                Singleton.getInstance().propertyupdate_caseid = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
                updateOfflinePropertyType();

                InitiateEditCaseInspectionTask_kakode(case_id);
                dialog.dismiss();

               /* propertyType = adapter.getSelectedItem();
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

                    GetReportPropertyTypeWebservice(propertyType, bank_id, agencybranchid);
                    dialog.dismiss();
                }*/

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

    private void InitiateEditCaseInspectionTask_kakode(String case_id) {
        if (Connectivity.isConnected(mContext)) {
            general.showloading(mContext);
            String url = general.ApiBaseUrl() + SettingsUtils.EditCaseInspection;
            JsonRequestData requestData = new JsonRequestData();
            requestData.setInitQueryUrl(url);
            requestData.setId(case_id); // id
            requestData.setUrl(RequestParam.EditInspectionRequestParams(requestData));
            WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.GET);
            webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
                @Override
                public void onTaskComplete(JsonRequestData requestData) {
                    parseEditCaseInspectionResponse_kakode(requestData.getResponse());
                }
            });
            webserviceTask.execute();
        } else {
            Connectivity.showNoConnectionDialog(mContext);
            general.hideloading();
        }
    }

    private void parseEditCaseInspectionResponse_kakode(String response) {

        DataResponse dataResponse = ResponseParser.parseEditInspectionResponse(response);
        String result = "";
        if (response != null) {
            result = dataResponse.status;
            msg = dataResponse.msg;
            info = dataResponse.info;

            // set the Property type and update in case
            dataResponse.aCase.setPropertyType(changed_TypeOfPropertyId);
            Singleton.getInstance().aCase = dataResponse.aCase;
            // call the post method
            InitiateSaveCaseInspectionTask_kakode();

            /*Singleton.getInstance().property = dataResponse.property;
            Singleton.getInstance().indProperty = dataResponse.indProperty;
            Singleton.getInstance().indPropertyValuation = dataResponse.indPropertyValuation;
            Singleton.getInstance().indPropertyFloors = dataResponse.indPropertyFloors;
            Singleton.getInstance().proximities = dataResponse.proximities;*/

        }
    }

    private void InitiateSaveCaseInspectionTask_kakode() {
        LinkedTreeMap<String, Object> mainOb = new LinkedTreeMap<>();
        mainOb.put("Case", Singleton.getInstance().aCase);
        LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
        map.put("is_sync", true);
        mainOb.put("edit_synk", map);

        String url = general.ApiBaseUrl() + SettingsUtils.SaveCaseInspection;
        JsonRequestData requestData = new JsonRequestData();
        requestData.setUrl(url);
        requestData.setMainJson(new Gson().toJson(mainOb));
        requestData.setRequestBody(RequestParam.SaveCaseInspectionRequestParams(requestData));
        WebserviceCommunicator webserviceTask = new WebserviceCommunicator(mContext, requestData, SettingsUtils.POST);
        webserviceTask.setFetchMyData(new TaskCompleteListener<JsonRequestData>() {
            @Override
            public void onTaskComplete(JsonRequestData requestData) {
                if (StatusId_is.equalsIgnoreCase("13")) {
                    // Accepted case can be change to Accepted case - Because, the case will convert as 12 Assigned case
                    InitiateUpdateStatusCaseidTask_kakode(case_id, "13");
                } else {
                    general.hideloading();
                   Singleton.getInstance().openCaseList.clear();
                    Singleton.getInstance().pendingCaseList.clear();
                    Singleton.getInstance().closeCaseList.clear();
                    Intent intent = new Intent(mContext, HomeActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });
        webserviceTask.execute();
    }

    private void InitiateUpdateStatusCaseidTask_kakode(String case_id, String statusId) {
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
                // Reload the page
                general.hideloading();
               Singleton.getInstance().openCaseList.clear();
                    Singleton.getInstance().pendingCaseList.clear();
                Singleton.getInstance().closeCaseList.clear();
                Intent intent = new Intent(mContext, HomeActivity.class);
                mContext.startActivity(intent);
            }
        });
        webserviceTask.execute();
    }


    /* Address Popup */
    public void addresspopup(Activity mContext, String caseId, String applicantName, String propertyAddress) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.addresspopup, null);
        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(mContext);
        alertdialogBuilder.setView(view);
        final AlertDialog alertDialog = alertdialogBuilder.show();
        alertDialog.setCancelable(false);

        TextView textview_heading = (TextView) alertDialog.findViewById(R.id.textview_heading);
        TextView textview_name_heading = (TextView) alertDialog.findViewById(R.id.textview_name_heading);
        TextView textview_name = (TextView) alertDialog.findViewById(R.id.textview_name);
        TextView textview_address_heading = (TextView) alertDialog.findViewById(R.id.textview_address_heading);
        TextView textview_address = (TextView) alertDialog.findViewById(R.id.textview_address);
        Button btn_cancel = (Button) alertDialog.findViewById(R.id.btn_cancel);

        textview_heading.setTypeface(general.mediumtypeface());
        textview_name_heading.setTypeface(general.mediumtypeface());
        textview_name.setTypeface(general.mediumtypeface());
        textview_address_heading.setTypeface(general.mediumtypeface());
        textview_address.setTypeface(general.mediumtypeface());
        btn_cancel.setTypeface(general.mediumtypeface());

        if (!general.isEmpty(caseId)) {
            textview_heading.setText("Case Id : " + caseId);
        }

        if (!general.isEmpty(applicantName)) {
            textview_name.setText(applicantName);
        }

        if (!general.isEmpty(propertyAddress)) {
            textview_address.setText(propertyAddress);
        }

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }


}
