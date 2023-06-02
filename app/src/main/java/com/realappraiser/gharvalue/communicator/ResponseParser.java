package com.realappraiser.gharvalue.communicator;

import android.util.Log;

import com.google.gson.Gson;
import com.realappraiser.gharvalue.AppDatabase;
import com.realappraiser.gharvalue.MyApplication;
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
import com.realappraiser.gharvalue.model.ImageBase64;
import com.realappraiser.gharvalue.model.IndProperty;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.model.IndPropertyFloorsValuation;
import com.realappraiser.gharvalue.model.IndPropertyValuation;
import com.realappraiser.gharvalue.model.Kitchen;
import com.realappraiser.gharvalue.model.Kitchentype;
import com.realappraiser.gharvalue.model.Land;
import com.realappraiser.gharvalue.model.Locality;
import com.realappraiser.gharvalue.model.LocalityCategory;
import com.realappraiser.gharvalue.model.Maintenance;
import com.realappraiser.gharvalue.model.Marketablity;
import com.realappraiser.gharvalue.model.Measurements;
import com.realappraiser.gharvalue.model.Nameofpersons;
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
import com.realappraiser.gharvalue.utils.General;
import com.realappraiser.gharvalue.utils.SettingsUtils;
import com.realappraiser.gharvalue.utils.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Suganya on 3/8/2017.
 */

@SuppressWarnings("ALL")
public class ResponseParser {

    private static final String TAG_MSG = "msg";
    private static final String TAG_STATUS = "status";
    public static String msg = "";

    public static DataResponse parseCurrentServerResponse(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();

        try {
            if (response != null) {
                try {

                    JSONObject jObj = new JSONObject(response.trim());
                    Iterator<?> jObj_Keys = jObj.keys();

                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        info = jObj.getString("data");

                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = info;

        return Response;
    }


    public static DataResponse parseLoginResponse(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();
        ArrayList<DataModel> loginModels = new ArrayList<DataModel>();

        try {
            if (response != null) {
                try {
                    JSONObject jObj = new JSONObject(response.trim());
                    Iterator<?> jObj_Keys = jObj.keys();

                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);

                        while (jObj_Keys.hasNext()) {
                            String jObj_key = (String) jObj_Keys.next();

                            if (jObj_key.equals("data")) {
                                DataModel model = new DataModel();

                                if (jObj.getString("data").equalsIgnoreCase("0")) {
                                    msg = "Invalid Credentials";
                                } else {

                                    JSONObject dataObj = (JSONObject) jObj.get(jObj_key);

                                    if (dataObj.has("LoginId")) {
                                        model.setLoginId(dataObj.getString("LoginId"));
                                        SettingsUtils.getInstance().putValue(SettingsUtils.KEY_LOGIN_ID, dataObj.getString("LoginId"));
                                    }

                                    if (dataObj.has("FirstName")) {
                                        model.setFirstName(dataObj.getString("FirstName"));
                                        SettingsUtils.getInstance().putValue(SettingsUtils.KEY_FIRSTNAME, dataObj.getString("FirstName"));
                                    }

                                    if (dataObj.has("LastName")) {
                                        model.setLastName(dataObj.getString("LastName"));
                                        SettingsUtils.getInstance().putValue(SettingsUtils.KEY_LASTNAME, dataObj.getString("LastName"));
                                    }

                                    if (dataObj.has("Email")) {
                                        model.setEmail(dataObj.getString("Email"));
                                        SettingsUtils.getInstance().putValue(SettingsUtils.KEY_EMAIL, dataObj.getString("Email"));
                                    }
                                    if (dataObj.has("AgencyName"))
                                        model.setAgencyName(dataObj.getString("AgencyName"));
                                    if (dataObj.has("CityName"))
                                        model.setCityName(dataObj.getString("CityName"));
                                    if (dataObj.has("AgencyCode"))
                                        model.setAgencyCode(dataObj.getString("AgencyCode"));
                                    if (dataObj.has("RoleDescription"))
                                        model.setRoleDescription(dataObj.getString("RoleDescription"));
                                    if (dataObj.has("RoleId"))
                                        model.setRoleId(dataObj.getString("RoleId"));
                                    if (dataObj.has("EmployeeId")) {
                                        model.setEmployeeId(dataObj.getString("EmployeeId"));
                                        SettingsUtils.getInstance().putValue(SettingsUtils.KEY_EMPID, dataObj.getString("EmployeeId"));
                                    }

                                    loginModels.add(model);
                                }
                            }
                        }
                        System.out.println(msg);

                    } else if (status.equals("2")) {
                        status = "2";
                        msg = jObj.getString(TAG_MSG);
                    } else {
                        status = "0";
                        msg = jObj.getString(TAG_MSG);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.loginModel = loginModels;

        return Response;
    }

    public static DataResponse parseGetReportTypeResponse(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();
        DataModel dataModel = new DataModel();

        try {
            if (response != null) {
                try {

                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        String jObj_data = jObj.getString("data");
                        JSONArray jsonArr = new JSONArray(jObj_data);
                        final DataModel mModel = new DataModel();
                        JSONObject jsonObject = jsonArr.getJSONObject(0);
                        if (jsonObject.has("TypeId"))
                            mModel.setTypeID(jsonObject.getString("TypeId"));
                        if (jsonObject.has("TypeDescription"))
                            mModel.setTypeDescription(jsonObject.getString("TypeDescription"));

                        dataModel = mModel;

                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = info;
        Response.getReportTypeModel = dataModel;

        return Response;
    }

    public static DataResponse parseGetCaseInspectionDetailsResponse(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();
        CaseOtherDetailsModel dataModel = new CaseOtherDetailsModel();

        try {
            if (response != null) {
                try {

                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        String jObj_data = jObj.getString("data");
                        JSONArray jsonArr = new JSONArray(jObj_data);
                        final CaseOtherDetailsModel mModel = new CaseOtherDetailsModel();
                        JSONObject jsonObject = jsonArr.getJSONObject(0);
                        CaseOtherDetailsModel.Datum data = new Gson().fromJson(jsonObject.toString(), CaseOtherDetailsModel.Datum.class);
                        List<CaseOtherDetailsModel.Datum> datumList = new ArrayList<>();
                        datumList.add(data);
                        mModel.setData(datumList);
                        dataModel = mModel;


                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = info;
        Response.caseOtherDetailsModel = dataModel;

        return Response;
    }

    public static DataResponse parsePendingCasesList(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();
        ArrayList<DataModel> pendingList = new ArrayList<DataModel>();

        // Room - Delete the Pending case adapter
        if (MyApplication.getAppContext() != null) {
            AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
            List<DataModel> pending = appDatabase.interfaceDataModelQuery().getDataModal_pendingcase(true,false);

            if (pending.size() > 0)
                for (DataModel model :
                        pending) {
                    appDatabase.interfaceDataModelQuery().delete(model);
                }
        }


        try {
            if (response != null) {
                try {

                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        String jObj_data = jObj.getString("data");

                        /******open and close Json Array response*******/

//                        JSONObject dataObj = new JSONObject(jObj_data);

                        JSONArray openArray = new JSONArray(jObj_data);
                        for (int i = 0; i < openArray.length(); i++) {
                            JSONObject openObj = openArray.getJSONObject(i);
                            final DataModel mModel = new DataModel();

                            if (openObj.has("CaseId")) {
                                mModel.setCaseId(openObj.getString("CaseId"));
                            }
                            if (openObj.has("ApplicantName")) {
                                mModel.setApplicantName(openObj.getString("ApplicantName"));
                            }
                            if (openObj.has("ApplicantContactNo")) {
                                mModel.setApplicantContactNo(openObj.getString("ApplicantContactNo"));
                            }
                            if (openObj.has("PropertyAddress")) {
                                mModel.setPropertyAddress(openObj.getString("PropertyAddress"));
                            }
                            if (openObj.has("ContactPersonName")) {
                                mModel.setContactPersonName(openObj.getString("ContactPersonName"));
                            }
                            if (openObj.has("ContactPersonNumber")) {
                                mModel.setContactPersonNumber(openObj.getString("ContactPersonNumber"));
                            }
                            if (openObj.has("BankName")) {
                                mModel.setBankName(openObj.getString("BankName"));
                            }
                            if (openObj.has("BankBranchName")) {
                                mModel.setBankBranchName(openObj.getString("BankBranchName"));
                            }
                            if (openObj.has("CreatedOn")) {
                                mModel.setCreatedOn(openObj.getString("CreatedOn"));
                            }
                            if (openObj.has("BankId")) {
                                mModel.setBankId(openObj.getString("BankId"));
                            }
                            if (openObj.has("AgencyBranchId")) {
                                mModel.setAgencyBranchId(openObj.getString("AgencyBranchId"));
                            }
                            if (openObj.has("PropertyType")) {
                                mModel.setPropertyType(openObj.getString("PropertyType"));
                            }
                            if (openObj.has("TypeID")) {
                                mModel.setTypeID(openObj.getString("TypeID"));
                            }
                            if (openObj.has("AssignedAt")) {
                                mModel.setAssignedAt(openObj.getString("AssignedAt"));
                            }
                            if (openObj.has("ReportChecker")) {
                                mModel.setReportChecker(openObj.getString("ReportChecker"));
                            }
                            if (openObj.has("Status")) {
                                mModel.setStatus(openObj.getString("Status"));
                            }
                            if (openObj.has("ReportDispatcher")) {
                                mModel.setReportDispatcher(openObj.getString("ReportDispatcher"));
                            }
                            if (openObj.has("FieldStaff")) {
                                mModel.setFieldStaff(openObj.getString("FieldStaff"));
                            }
                            if (openObj.has("AssignedTo")) {
                                mModel.setAssignedTo(openObj.getString("AssignedTo"));
                            }
                            if (openObj.has("ReportMaker")) {
                                mModel.setReportMaker(openObj.getString("ReportMaker"));
                            }
                            if (openObj.has("ReportFinalizer")) {
                                mModel.setReportFinalizer(openObj.getString("ReportFinalizer"));
                            }
                            if (openObj.has("PropertyId")) {
                                mModel.setPropertyId(openObj.getString("PropertyId"));
                            }
                            if (openObj.has("ReportFile")) {
                                mModel.setReportFile(openObj.getString("ReportFile"));
                            }
                            if (openObj.has("ReportId")) {
                                mModel.setReportId(openObj.getString("ReportId"));
                            }
                            if (openObj.has("ReportTemplateId")) {
                                mModel.setReportTemplateId(openObj.getString("ReportTemplateId"));
                            }
                            if (openObj.has("Signature1")) {
                                mModel.setSignature1(openObj.getString("Signature1"));
                            }
                            if (openObj.has("PropertyCategoryId")) {
                                mModel.setPropertyCategoryId(openObj.getString("PropertyCategoryId"));
                            }
                            if (openObj.has("count")) {
                                mModel.setCount(openObj.getString("count"));
                            }
                            if (openObj.has("Signature2")) {
                                mModel.setSignature2(openObj.getString("Signature2"));
                            }
                            if (openObj.has("EmployeeName")) {
                                mModel.setEmployeeName(openObj.getString("EmployeeName"));
                            }
                            if (openObj.has("Role")) {
                                mModel.setRole(openObj.getString("Role"));
                            }
                            if (openObj.has("StatusId")) {
                                mModel.setStatusId(openObj.getString("StatusId"));
                            }
                            if (openObj.has("ReportName")) {
                                mModel.setReportName(openObj.getString("ReportName"));
                            }

                            mModel.setPendingcase(true);


                            String statusId = openObj.getString("StatusId");
//                            if ((!statusId.equalsIgnoreCase("19")) && (!statusId.equalsIgnoreCase("21"))) {

                                // Set a Status 17 to 2 for kakode... Rejected by case admin after inspection the case.
                                if (statusId.equalsIgnoreCase("17")) {
                                    mModel.setStatusId("2");
                                }

                                pendingList.add(mModel);
                                // Room - Insert the pending case adapter
                                if (MyApplication.getAppContext() != null) {
                                    AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
                                    mModel.setPendingcase(true);
                                    appDatabase.interfaceDataModelQuery().insertAll(mModel);
                                }
//                            }
                        }

                        System.out.println(msg);

                    } else if (status.equals("2")) {
                        info = jObj.getString("info");
                        msg = "";
                        if (jObj.toString().contains("param")) {
                            JSONArray errorMsg = new JSONArray(jObj.getString("data"));
                            for (int i = 0; i < errorMsg.length(); i++) {

                                JSONObject obj = errorMsg.getJSONObject(i);
                                String param = obj.getString("param");
                                String msg2 = obj.getString("msg");

                                int lastindex = errorMsg.length() - 1;
                                if (i == lastindex) {
                                    msg += msg2;
                                } else {
                                    msg += msg2 + ", ";
                                }
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = info;
        Response.pendingCaseList = pendingList;

        Singleton.getInstance().pendingCaseList = pendingList;
        return Response;
    }


    public static DataResponse parseOpenCloseCaseListResponse(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();
        ArrayList<DataModel> openCaseList = new ArrayList<DataModel>();
        ArrayList<DataModel> openOriginalCaseList = new ArrayList<DataModel>();
        ArrayList<DataModel> closeCaseList = new ArrayList<DataModel>();
        ArrayList<OfflineDataModel> offlineCaseList = new ArrayList<OfflineDataModel>();

        // Room - Delete the Open case adapter
        if (MyApplication.getAppContext() != null) {
            AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
            appDatabase.interfaceDataModelQuery().deleteTable();
            // appDatabase.interfaceOfflineDataModelQuery().deleteTable();
        }


        try {
            if (response != null) {
                try {

                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        String jObj_data = jObj.getString("data");

                        /******open and close Json Array response*******/

                        JSONObject dataObj = new JSONObject(jObj_data);

                        String opencase_data = dataObj.getString("Open");
                        String closecase_data = dataObj.getString("Close");

                        JSONArray openArray = new JSONArray(opencase_data);
                        for (int i = 0; i < openArray.length(); i++) {
                            JSONObject openObj = openArray.getJSONObject(i);
                            final DataModel mModel = new DataModel();
                            final OfflineDataModel mOfflineModel = new OfflineDataModel();

                            if (openObj.has("CaseId")) {
                                mModel.setCaseId(openObj.getString("CaseId"));
                                mOfflineModel.setCaseId(openObj.getString("CaseId"));
                            }
                            if (openObj.has("ApplicantName")) {
                                mModel.setApplicantName(openObj.getString("ApplicantName"));
                                mOfflineModel.setApplicantName(openObj.getString("ApplicantName"));
                            }
                            if (openObj.has("ApplicantContactNo")) {
                                mModel.setApplicantContactNo(openObj.getString("ApplicantContactNo"));
                                mOfflineModel.setApplicantContactNo(openObj.getString("ApplicantContactNo"));
                            }
                            if (openObj.has("CreatedOn")) {
                                mModel.setCreatedOn(openObj.getString("CreatedOn"));
                                mOfflineModel.setCreatedOn(openObj.getString("CreatedOn"));
                            }
                            if (openObj.has("PropertyAddress")) {
                                mModel.setPropertyAddress(openObj.getString("PropertyAddress"));
                                mOfflineModel.setPropertyAddress(openObj.getString("PropertyAddress"));
                            }
                            if (openObj.has("ContactPersonName")) {
                                mModel.setContactPersonName(openObj.getString("ContactPersonName"));
                                mOfflineModel.setContactPersonName(openObj.getString("ContactPersonName"));
                            }
                            if (openObj.has("ContactPersonNumber")) {
                                mModel.setContactPersonNumber(openObj.getString("ContactPersonNumber"));
                                mOfflineModel.setContactPersonNumber(openObj.getString("ContactPersonNumber"));
                            }
                            if (openObj.has("BankName")) {
                                mModel.setBankName(openObj.getString("BankName"));
                                mOfflineModel.setBankName(openObj.getString("BankName"));
                            }
                            if (openObj.has("BankBranchName")) {
                                mModel.setBankBranchName(openObj.getString("BankBranchName"));
                                mOfflineModel.setBankBranchName(openObj.getString("BankBranchName"));
                            }
                            if (openObj.has("BankId")) {
                                mModel.setBankId(openObj.getString("BankId"));
                                mOfflineModel.setBankId(openObj.getString("BankId"));
                            }
                            if (openObj.has("AgencyBranchId")) {
                                mModel.setAgencyBranchId(openObj.getString("AgencyBranchId"));
                                mOfflineModel.setAgencyBranchId(openObj.getString("AgencyBranchId"));
                            }
                            if (openObj.has("PropertyType")) {
                                mModel.setPropertyType(openObj.getString("PropertyType"));
                                mOfflineModel.setPropertyType(openObj.getString("PropertyType"));
                            }
                            if (openObj.has("TypeID")) {
                                mModel.setTypeID(openObj.getString("TypeID"));
                                mOfflineModel.setTypeID(openObj.getString("TypeID"));
                            }
                            if (openObj.has("AssignedAt")) {
                                mModel.setAssignedAt(openObj.getString("AssignedAt"));
                                mOfflineModel.setAssignedAt(openObj.getString("AssignedAt"));
                            }
                            if (openObj.has("ReportChecker")) {
                                mModel.setReportChecker(openObj.getString("ReportChecker"));
                                mOfflineModel.setReportChecker(openObj.getString("ReportChecker"));
                            }
                            if (openObj.has("Status")) {
                                mModel.setStatus(openObj.getString("Status"));
                                mOfflineModel.setStatus(openObj.getString("Status"));
                            }
                            if (openObj.has("ReportDispatcher")) {
                                mModel.setReportDispatcher(openObj.getString("ReportDispatcher"));
                                mOfflineModel.setReportDispatcher(openObj.getString("ReportDispatcher"));
                            }
                            if (openObj.has("FieldStaff")) {
                                mModel.setFieldStaff(openObj.getString("FieldStaff"));
                                mOfflineModel.setFieldStaff(openObj.getString("FieldStaff"));
                            }
                            if (openObj.has("AssignedTo")) {
                                mModel.setAssignedTo(openObj.getString("AssignedTo"));
                                mOfflineModel.setAssignedTo(openObj.getString("AssignedTo"));
                            }
                            if (openObj.has("ReportMaker")) {
                                mModel.setReportMaker(openObj.getString("ReportMaker"));
                                mOfflineModel.setReportMaker(openObj.getString("ReportMaker"));
                            }
                            if (openObj.has("ReportFinalizer")) {
                                mModel.setReportFinalizer(openObj.getString("ReportFinalizer"));
                                mOfflineModel.setReportFinalizer(openObj.getString("ReportFinalizer"));
                            }
                            if (openObj.has("PropertyId")) {
                                mModel.setPropertyId(openObj.getString("PropertyId"));
                                mOfflineModel.setPropertyId(openObj.getString("PropertyId"));
                            }
                            if (openObj.has("ReportFile")) {
                                mModel.setReportFile(openObj.getString("ReportFile"));
                                mOfflineModel.setReportFile(openObj.getString("ReportFile"));
                            }
                            if (openObj.has("ReportId")) {
                                mModel.setReportId(openObj.getString("ReportId"));
                                mOfflineModel.setReportId(openObj.getString("ReportId"));
                            }
                            if (openObj.has("ReportTemplateId")) {
                                mModel.setReportTemplateId(openObj.getString("ReportTemplateId"));
                                mOfflineModel.setReportTemplateId(openObj.getString("ReportTemplateId"));
                            }
                            if (openObj.has("Signature1")) {
                                mModel.setSignature1(openObj.getString("Signature1"));
                                mOfflineModel.setSignature1(openObj.getString("Signature1"));
                            }
                            if (openObj.has("PropertyCategoryId")) {
                                mModel.setPropertyCategoryId(openObj.getString("PropertyCategoryId"));
                                mOfflineModel.setPropertyCategoryId(openObj.getString("PropertyCategoryId"));
                            }
                            if (openObj.has("count")) {
                                mModel.setCount(openObj.getString("count"));
                                mOfflineModel.setCount(openObj.getString("count"));
                            }
                            if (openObj.has("Signature2")) {
                                mModel.setSignature2(openObj.getString("Signature2"));
                                mOfflineModel.setSignature2(openObj.getString("Signature2"));
                            }
                            if (openObj.has("EmployeeName")) {
                                mModel.setEmployeeName(openObj.getString("EmployeeName"));
                                mOfflineModel.setEmployeeName(openObj.getString("EmployeeName"));
                            }
                            if (openObj.has("Role")) {
                                mModel.setRole(openObj.getString("Role"));
                                mOfflineModel.setRole(openObj.getString("Role"));
                            }
                            if (openObj.has("StatusId")) {
                                mModel.setStatusId(openObj.getString("StatusId"));
                                mOfflineModel.setStatusId(openObj.getString("StatusId"));
                            }
                            if (openObj.has("ReportName")) {
                                mModel.setReportName(openObj.getString("ReportName"));
                                mOfflineModel.setReportName(openObj.getString("ReportName"));
                            }


                            String statusId = openObj.getString("StatusId");
                            if ((!statusId.equalsIgnoreCase("19")) && (!statusId.equalsIgnoreCase("21"))) {

                                // Set a Status 17 to 2 for kakode... Rejected by case admin after inspection the case.
                                if (statusId.equalsIgnoreCase("17")) {
                                    mModel.setStatusId("2");
                                    mOfflineModel.setStatusId("2");
                                }

                                openCaseList.add(mModel);
                                openOriginalCaseList.add(mModel);
                                // Room - Insert the Open case adapter
                                if (MyApplication.getAppContext() != null) {
                                    AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
                                    mModel.setOpencase(true);
                                    appDatabase.interfaceDataModelQuery().insertAll(mModel);
                                }

                                // Room - Insert the Offline case popup list adapter
                                if ((statusId.equalsIgnoreCase("12")) || (statusId.equalsIgnoreCase("13"))) {

                                    String value = "";
                                    if (MyApplication.getAppContext() != null) {
                                        AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
                                        /************Get the update case detail for caseid,
                                         * if it present then not to add again to table,
                                         * hence skips of inserting the value which is already presents**************/
                                        if (appDatabase.interfaceOfflineDataModelQuery().getDataModels().size() > 0) {
                                            value = appDatabase.interfaceOfflineDataModelQuery().getUpdateCaseIdOffline(openObj.getString("CaseId"));
                                            Log.e("update_case_status", value + " update_case_ID " + openObj.getString("CaseId"));
                                        }
                                    }

                                    /**************Check whether the caseid is already in
                                     *  offline table with some updatecase status, so only the empty or null result from updatecase for
                                     *  offline table will be newly get inserted into this table or else it remains
                                     *  same status with casedetails in offline table*******************/
                                    if (General.isEmpty(value)) {
                                        // If empty
                                        mOfflineModel.setUpdatecasestatus(openObj.getString("StatusId"));
                                        offlineCaseList.add(mOfflineModel);
                                    }
                                }
                            }
                        }

                        JSONArray closeArray = new JSONArray(closecase_data);
                        for (int i = 0; i < closeArray.length(); i++) {
                            JSONObject closeObj = closeArray.getJSONObject(i);
                            final DataModel mModel = new DataModel();

                            if (closeObj.has("CaseId"))
                                mModel.setCaseId(closeObj.getString("CaseId"));
                            if (closeObj.has("ApplicantName"))
                                mModel.setApplicantName(closeObj.getString("ApplicantName"));
                            if (closeObj.has("ApplicantContactNo"))
                                mModel.setApplicantContactNo(closeObj.getString("ApplicantContactNo"));
                            if (closeObj.has("PropertyAddress"))
                                mModel.setPropertyAddress(closeObj.getString("PropertyAddress"));
                            if (closeObj.has("ContactPersonName"))
                                mModel.setContactPersonName(closeObj.getString("ContactPersonName"));
                            if (closeObj.has("ContactPersonNumber"))
                                mModel.setContactPersonNumber(closeObj.getString("ContactPersonNumber"));
                            if (closeObj.has("BankName"))
                                mModel.setBankName(closeObj.getString("BankName"));
                            if (closeObj.has("BankBranchName"))
                                mModel.setBankBranchName(closeObj.getString("BankBranchName"));
                            if (closeObj.has("BankId"))
                                mModel.setBankId(closeObj.getString("BankId"));
                            if (closeObj.has("PropertyType"))
                                mModel.setPropertyType(closeObj.getString("PropertyType"));
                            if (closeObj.has("TypeID"))
                                mModel.setTypeID(closeObj.getString("TypeID"));
                            if (closeObj.has("AssignedAt"))
                                mModel.setAssignedAt(closeObj.getString("AssignedAt"));
                            if (closeObj.has("ReportChecker"))
                                mModel.setReportChecker(closeObj.getString("ReportChecker"));
                            if (closeObj.has("Status"))
                                mModel.setStatus(closeObj.getString("Status"));
                            if (closeObj.has("ReportDispatcher"))
                                mModel.setReportDispatcher(closeObj.getString("ReportDispatcher"));
                            if (closeObj.has("FieldStaff"))
                                mModel.setFieldStaff(closeObj.getString("FieldStaff"));
                            if (closeObj.has("AssignedTo"))
                                mModel.setAssignedTo(closeObj.getString("AssignedTo"));
                            if (closeObj.has("ReportMaker"))
                                mModel.setReportMaker(closeObj.getString("ReportMaker"));
                            if (closeObj.has("ReportFinalizer"))
                                mModel.setReportFinalizer(closeObj.getString("ReportFinalizer"));
                            if (closeObj.has("PropertyId"))
                                mModel.setPropertyId(closeObj.getString("PropertyId"));
                            if (closeObj.has("ReportFile"))
                                mModel.setReportFile(closeObj.getString("ReportFile"));
                            if (closeObj.has("ReportId"))
                                mModel.setReportId(closeObj.getString("ReportId"));
                            if (closeObj.has("ReportTemplateId"))
                                mModel.setReportTemplateId(closeObj.getString("ReportTemplateId"));
                            if (closeObj.has("Signature1"))
                                mModel.setSignature1(closeObj.getString("Signature1"));
                            if (closeObj.has("PropertyCategoryId"))
                                mModel.setPropertyCategoryId(closeObj.getString("PropertyCategoryId"));
                            if (closeObj.has("count"))
                                mModel.setCount(closeObj.getString("count"));
                            if (closeObj.has("Signature2"))
                                mModel.setSignature2(closeObj.getString("Signature2"));
                            if (closeObj.has("EmployeeName"))
                                mModel.setEmployeeName(closeObj.getString("EmployeeName"));
                            if (closeObj.has("Role"))
                                mModel.setRole(closeObj.getString("Role"));
                            if (closeObj.has("StatusId"))
                                mModel.setStatusId(closeObj.getString("StatusId"));

                            closeCaseList.add(mModel);
                            // Room - Insert the Close case adapter
                            if (MyApplication.getAppContext() != null) {
                                AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
                                mModel.setOpencase(false);
                                appDatabase.interfaceDataModelQuery().insertAll(mModel);
                            }


                        }

                        System.out.println(msg);

                    } else if (status.equals("2")) {
                        info = jObj.getString("info");
                        msg = "";
                        if (jObj.toString().contains("param")) {
                            JSONArray errorMsg = new JSONArray(jObj.getString("data"));
                            for (int i = 0; i < errorMsg.length(); i++) {

                                JSONObject obj = errorMsg.getJSONObject(i);
                                String param = obj.getString("param");
                                String msg2 = obj.getString("msg");

                                int lastindex = errorMsg.length() - 1;
                                if (i == lastindex) {
                                    msg += msg2;
                                } else {
                                    msg += msg2 + ", ";
                                }
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = info;
        Response.openCaseList = openCaseList;
        Response.closeCaseList = closeCaseList;
        Response.offlineCaseList = offlineCaseList;
        Response.openCaseOriginalList = openOriginalCaseList;

        Singleton.getInstance().openCaseListOriginal = openOriginalCaseList;

        //For Offline Cases Display
        for (int i = 0; i < offlineCaseList.size(); i++) {
            OfflineDataModel dataModel = offlineCaseList.get(i);
            // Room - Insert the offline case adapter
            if (MyApplication.getAppContext() != null) {
                AppDatabase appOfflineDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
                dataModel.setShowoffline(true);
                dataModel.setOfflinecase(false);
                appOfflineDatabase.interfaceOfflineDataModelQuery().insertAll(dataModel);
            }
        }

        return Response;
    }


    public static DataResponse parseEditInspectionResponse_offline(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();

        AppDatabase appDatabase = null;
        if (MyApplication.getAppContext() != null) {
            //Room
            appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
        }

        int caseid_int = 0;
        String caseid_str = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        if (!General.isEmpty(caseid_str)) {
            caseid_int = Integer.parseInt(caseid_str);
        }


        try {
            if (response != null) {
                try {
                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        String jObj_data = jObj.getString("data");

                        JSONObject jsonObject = new JSONObject(jObj_data);

                        /*******Set Case Data*******/
                        JSONObject jobj = jsonObject.getJSONObject("Case");
                        Gson gson = new Gson();
                        Case obj = null;
                        obj = new Case();
                        obj = gson.fromJson(jobj.toString(), Case.class);
                        Singleton.getInstance().aCase = obj;

                        //if(appDatabase.interfaceCaseQuery().getCase_caseID(caseid_int).size() == 0) {
                        // Room Delete
                        appDatabase.interfaceCaseQuery().deleteRow(caseid_int);
                        // Room Add
                        appDatabase.interfaceCaseQuery().insertAll(obj);
                        //}

                        /*******Set Property Data*******/
                        JSONObject property_jObj = jsonObject.getJSONObject("Property");
                        Gson propertygson = new Gson();
                        Property propertyobj = null;
                        propertyobj = new Property();
                        propertyobj = propertygson.fromJson(property_jObj.toString(), Property.class);
                        propertyobj.setCaseId(caseid_int);
                        Singleton.getInstance().property = propertyobj;

                        // Room Delete
                        appDatabase.interfacePropertyQuery().deleteRow(caseid_int);
                        // Room Add
                        appDatabase.interfacePropertyQuery().insertAll(propertyobj);

                        /*******Set IndProperty Data*******/
                        JSONObject IndProperty_jobj = jsonObject.getJSONObject("IndProperty");
                        Gson IndPropertygson = new Gson();
                        IndProperty IndPropertyobj = null;
                        IndPropertyobj = new IndProperty();
                        IndPropertyobj = IndPropertygson.fromJson(IndProperty_jobj.toString(), IndProperty.class);
                        IndPropertyobj.setCaseId(caseid_int);
                        Singleton.getInstance().indProperty = IndPropertyobj;

                        // Room Delete
                        appDatabase.interfaceIndpropertyQuery().deleteRow(caseid_int);
                        // Room Add
                        appDatabase.interfaceIndpropertyQuery().insertAll(IndPropertyobj);

                        /*******Set IndPropertyValuation Data*******/
                        JSONObject IndPropertyValuation_jobj = jsonObject.getJSONObject("IndPropertyValuation");
                        Gson IndPropertyValuationgson = new Gson();
                        IndPropertyValuation IndPropertyValuationobj = null;
                        IndPropertyValuationobj = new IndPropertyValuation();
                        IndPropertyValuationobj = IndPropertyValuationgson.fromJson(IndPropertyValuation_jobj.toString(), IndPropertyValuation.class);
                        IndPropertyValuationobj.setCaseId(caseid_int);
                        Singleton.getInstance().indPropertyValuation = IndPropertyValuationobj;

                        // Room Delete
                        appDatabase.interfaceIndPropertyValuationQuery().deleteRow(caseid_int);
                        // Room Add
                        appDatabase.interfaceIndPropertyValuationQuery().insertAll(IndPropertyValuationobj);

                        /* Json array for IndPropertyFloors*/
                        ArrayList<IndPropertyFloor> IndPropertyFloor_list = new ArrayList<>();
                        JSONArray IndPropertyFloors_array = jsonObject.getJSONArray("IndPropertyFloors");
                        // Room Delete
                        appDatabase.interfaceIndPropertyFloorsQuery().deleteRow(caseid_int);
                        for (int p = 0; p < IndPropertyFloors_array.length(); p++) {
                            JSONObject IndPropertyFloors_jobj = IndPropertyFloors_array.getJSONObject(p);
                            Gson IndPropertyFloorsgson = new Gson();
                            IndPropertyFloor IndPropertyFloorsobj = null;
                            IndPropertyFloorsobj = new IndPropertyFloor();
                            IndPropertyFloorsobj = IndPropertyFloorsgson.fromJson(IndPropertyFloors_jobj.toString(), IndPropertyFloor.class);
                            IndPropertyFloor_list.add(IndPropertyFloorsobj);
                            // Room Add
                            appDatabase.interfaceIndPropertyFloorsQuery().insertAll(IndPropertyFloorsobj);
                        }
                        Singleton.getInstance().indPropertyFloors = IndPropertyFloor_list;

                        /* Json array for IndPropertyFloors valuation*/
                        ArrayList<IndPropertyFloorsValuation> IndfloorsValuation_list = new ArrayList<>();
                        JSONArray IndFloorsValuation_array = jsonObject.getJSONArray("IndPropertyFloorsValuation");
                        // Room Delete
                        appDatabase.interfaceIndPropertyFloorsValuationQuery().deleteRow(caseid_int);
                        for (int p = 0; p < IndFloorsValuation_array.length(); p++) {
                            JSONObject IndPropertyFloors_jobj = IndFloorsValuation_array.getJSONObject(p);
                            Gson IndFloorsValuationgson = new Gson();
                            IndPropertyFloorsValuation IndPropertyFloorsValobj = null;
                            IndPropertyFloorsValobj = new IndPropertyFloorsValuation();
                            IndPropertyFloorsValobj = IndFloorsValuationgson.fromJson(IndPropertyFloors_jobj.toString(), IndPropertyFloorsValuation.class);
                            IndfloorsValuation_list.add(IndPropertyFloorsValobj);
                            // Room Add
                            appDatabase.interfaceIndPropertyFloorsValuationQuery().insertAll(IndPropertyFloorsValobj);
                        }
                        Singleton.getInstance().indPropertyFloorsValuations = IndfloorsValuation_list;

                        /* Json array for Roof*/
                        ArrayList<Proximity> Proximity_list = new ArrayList<>();
                        JSONArray roof_array = jsonObject.getJSONArray("Proximity");
                        // Room Delete
                        appDatabase.interfaceProximityQuery().deleteRow(caseid_int);
                        for (int p = 0; p < roof_array.length(); p++) {
                            JSONObject Proximity_jobj = roof_array.getJSONObject(p);
                            Gson Proximitygson = new Gson();
                            Proximity Proximityobj = null;
                            Proximityobj = new Proximity();
                            Proximityobj = Proximitygson.fromJson(Proximity_jobj.toString(), Proximity.class);
                            Proximity_list.add(Proximityobj);
                            // Room Add
                            appDatabase.interfaceProximityQuery().insertAll(Proximityobj);
                        }
                        Singleton.getInstance().proximities = Proximity_list;


                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = info;
        Response.aCase = Singleton.getInstance().aCase;
        Response.property = Singleton.getInstance().property;
        Response.indProperty = Singleton.getInstance().indProperty;
        Response.indPropertyValuation = Singleton.getInstance().indPropertyValuation;
        Response.indPropertyFloors = Singleton.getInstance().indPropertyFloors;
        Response.indPropertyFloorsValuations = Singleton.getInstance().indPropertyFloorsValuations;
        Response.proximities = Singleton.getInstance().proximities;

        return Response;
    }


    public static DataResponse parseUpdateStatusCaseResponse(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();
        DataModel dataModel = new DataModel();

        try {
            if (response != null) {
                try {
                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        String jObj_data = jObj.getString("data");
                        JSONObject jsonObject = new JSONObject(jObj_data);
                        final DataModel mModel = new DataModel();

                        if (jsonObject.has("CaseId"))
                            mModel.setCaseId(jsonObject.getString("CaseId"));
                        if (jsonObject.has("ApplicantName"))
                            mModel.setApplicantName(jsonObject.getString("ApplicantName"));
                        if (jsonObject.has("ApplicantContactNo"))
                            mModel.setApplicantContactNo(jsonObject.getString("ApplicantContactNo"));
                        if (jsonObject.has("PropertyAddress"))
                            mModel.setPropertyAddress(jsonObject.getString("PropertyAddress"));
                        if (jsonObject.has("ContactPersonName"))
                            mModel.setContactPersonName(jsonObject.getString("ContactPersonName"));
                        if (jsonObject.has("ContactPersonNumber"))
                            mModel.setContactPersonNumber(jsonObject.getString("ContactPersonNumber"));
                        if (jsonObject.has("BankName"))
                            mModel.setBankName(jsonObject.getString("BankName"));
                        if (jsonObject.has("PropertyType"))
                            mModel.setPropertyType(jsonObject.getString("PropertyType"));
                        if (jsonObject.has("AssignedAt"))
                            mModel.setAssignedAt(jsonObject.getString("AssignedAt"));
                        if (jsonObject.has("ReportChecker"))
                            mModel.setReportChecker(jsonObject.getString("ReportChecker"));
                        if (jsonObject.has("Status"))
                            mModel.setStatus(jsonObject.getString("Status"));
                        if (jsonObject.has("ReportDispatcher"))
                            mModel.setReportDispatcher(jsonObject.getString("ReportDispatcher"));
                        if (jsonObject.has("FieldStaff"))
                            mModel.setFieldStaff(jsonObject.getString("FieldStaff"));
                        if (jsonObject.has("AssignedTo"))
                            mModel.setAssignedTo(jsonObject.getString("AssignedTo"));
                        if (jsonObject.has("ReportMaker"))
                            mModel.setReportMaker(jsonObject.getString("ReportMaker"));
                        if (jsonObject.has("ReportFinalizer"))
                            mModel.setReportFinalizer(jsonObject.getString("ReportFinalizer"));
                        if (jsonObject.has("PropertyId"))
                            mModel.setPropertyId(jsonObject.getString("PropertyId"));
                        if (jsonObject.has("ReportFile"))
                            mModel.setReportFile(jsonObject.getString("ReportFile"));
                        if (jsonObject.has("ReportId"))
                            mModel.setReportId(jsonObject.getString("ReportId"));
                        if (jsonObject.has("ReportTemplateId"))
                            mModel.setReportTemplateId(jsonObject.getString("ReportTemplateId"));
                        if (jsonObject.has("Signature1"))
                            mModel.setSignature1(jsonObject.getString("Signature1"));
                        if (jsonObject.has("Signature2"))
                            mModel.setSignature2(jsonObject.getString("Signature2"));

                        // closeCaseList.add(mModel);
                        dataModel = mModel;

                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.updateCaseStatusModel = dataModel;

        return Response;
    }

    public static DataResponse parsePropertyTypeListResponse(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();
        ArrayList<DataModel> dataModels = new ArrayList<>();

        try {
            if (response != null) {
                try {
                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        String jObj_data = jObj.getString("data");

                        /******data Json Array response*******/
                        JSONArray jsonArray = new JSONArray(jObj_data);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObj = jsonArray.getJSONObject(i);
                            final DataModel mModel = new DataModel();

                            if (jsonObj.has("Name"))
                                mModel.setName(jsonObj.getString("Name"));
                            if (jsonObj.has("TypeOfPropertyId"))
                                mModel.setTypeOfPropertyId(jsonObj.getString("TypeOfPropertyId"));
                            dataModels.add(mModel);
                        }
                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = info;
        Response.propertyTypeList = dataModels;

        return Response;
    }

    public static DataResponse parseUpdatePropertyTypeResponse(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();
        DataModel dataModel = new DataModel();

        try {
            if (response != null) {
                try {

                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        String jObj_data = jObj.getString("data");
                        JSONObject jsonObject = new JSONObject(jObj_data);
                        final DataModel mModel = new DataModel();

                        if (jsonObject.has("CaseId"))
                            mModel.setCaseId(jsonObject.getString("CaseId"));
                        if (jsonObject.has("ApplicantName"))
                            mModel.setApplicantName(jsonObject.getString("ApplicantName"));
                        if (jsonObject.has("ApplicantContactNo"))
                            mModel.setApplicantContactNo(jsonObject.getString("ApplicantContactNo"));
                        if (jsonObject.has("PropertyAddress"))
                            mModel.setPropertyAddress(jsonObject.getString("PropertyAddress"));
                        if (jsonObject.has("ContactPersonName"))
                            mModel.setContactPersonName(jsonObject.getString("ContactPersonName"));
                        if (jsonObject.has("ContactPersonNumber"))
                            mModel.setContactPersonNumber(jsonObject.getString("ContactPersonNumber"));
                        if (jsonObject.has("BankName"))
                            mModel.setBankName(jsonObject.getString("BankName"));
                        if (jsonObject.has("PropertyType"))
                            mModel.setPropertyType(jsonObject.getString("PropertyType"));
                        if (jsonObject.has("AssignedAt"))
                            mModel.setAssignedAt(jsonObject.getString("AssignedAt"));
                        if (jsonObject.has("ReportChecker"))
                            mModel.setReportChecker(jsonObject.getString("ReportChecker"));
                        if (jsonObject.has("Status"))
                            mModel.setStatus(jsonObject.getString("Status"));
                        if (jsonObject.has("ReportDispatcher"))
                            mModel.setReportDispatcher(jsonObject.getString("ReportDispatcher"));
                        if (jsonObject.has("FieldStaff"))
                            mModel.setFieldStaff(jsonObject.getString("FieldStaff"));
                        if (jsonObject.has("AssignedTo"))
                            mModel.setAssignedTo(jsonObject.getString("AssignedTo"));
                        if (jsonObject.has("ReportMaker"))
                            mModel.setReportMaker(jsonObject.getString("ReportMaker"));
                        if (jsonObject.has("ReportFinalizer"))
                            mModel.setReportFinalizer(jsonObject.getString("ReportFinalizer"));
                        if (jsonObject.has("PropertyId"))
                            mModel.setPropertyId(jsonObject.getString("PropertyId"));
                        if (jsonObject.has("ReportFile"))
                            mModel.setReportFile(jsonObject.getString("ReportFile"));
                        if (jsonObject.has("ReportId"))
                            mModel.setReportId(jsonObject.getString("ReportId"));
                        if (jsonObject.has("ReportTemplateId"))
                            mModel.setReportTemplateId(jsonObject.getString("ReportTemplateId"));
                        if (jsonObject.has("Signature1"))
                            mModel.setSignature1(jsonObject.getString("Signature1"));
                        if (jsonObject.has("Signature2"))
                            mModel.setSignature2(jsonObject.getString("Signature2"));

                        dataModel = mModel;

                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = info;
        Response.updatePropertyTypeStatusModel = dataModel;

        return Response;
    }

    public static DataResponse parseDocumentReadResponse(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();
        ArrayList<Document_list> dataModels = new ArrayList<>();

        try {
            if (response != null) {
                try {
                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        String jObj_data = jObj.getString("data");

                        if (jObj_data.equalsIgnoreCase("[]")) {
                            System.err.print("document null");
                        } else {
                            /******data Json Array response*******/
                            JSONArray jsonArray = new JSONArray(jObj_data);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObj = jsonArray.getJSONObject(i);
                                Gson gson = new Gson();
                                Document_list obj = null;
                                obj = new Document_list();
                                obj = gson.fromJson(jsonObj.toString(), Document_list.class);
                                dataModels.add(obj);
                            }
                        }
                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = info;
        Response.documentRead = dataModels;

        return Response;
    }

    /* TODO form details webservice call */
    public static DataResponse parseGetFieldDropDownResponse(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();
        ArrayList<PropertyIdentificationChannel> dataModels = new ArrayList<>();

        // Room - Delete the type of property table
        if (MyApplication.getAppContext() != null) {
            AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
            appDatabase.typeofPropertyQuery().deleteTable();
        }

        try {
            if (response != null) {
                try {
                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        String jObj_data = jObj.getString("data");

                        JSONObject jsonObject = new JSONObject(jObj_data);

                        /* Json array for propertyidentificationchannel*/
                        ArrayList<PropertyIdentificationChannel> propertyIdentificationChannels_list = new ArrayList<>();

                        /* *//* ******Set Dummy Data*******//*
                        PropertyIdentificationChannel dummy_PropertyIdentificationChannel = new PropertyIdentificationChannel();
                        dummy_PropertyIdentificationChannel.setPropertyIdentificationChannelId(0);
                        dummy_PropertyIdentificationChannel.setName("Select");
                        Gson gson_dummy_PropertyIdentificationChannel = new Gson();
                        gson_dummy_PropertyIdentificationChannel.toJson(dummy_PropertyIdentificationChannel);
                        propertyIdentificationChannels_list.add(dummy_PropertyIdentificationChannel);
*/
                        /*******Set Real Data*******/
                        JSONArray propertyidentificationchannel_array = jsonObject.getJSONArray("propertyidentificationchannel");
                        for (int p = 0; p < propertyidentificationchannel_array.length(); p++) {
                            JSONObject jobj = propertyidentificationchannel_array.getJSONObject(p);
                            Gson gson = new Gson();
                            PropertyIdentificationChannel obj = null;
                            obj = new PropertyIdentificationChannel();
                            obj = gson.fromJson(jobj.toString(), PropertyIdentificationChannel.class);
                            propertyIdentificationChannels_list.add(obj);
                        }
                        Singleton.getInstance().propertyIdentificationChannels_list = propertyIdentificationChannels_list;



                        /* ******Set fittingQualities Dummy Data*******/
                        ArrayList<FittingQuality> fittingQualities_List = new ArrayList<>();
                        FittingQuality dummy_FittingQuality = new FittingQuality();
                        dummy_FittingQuality.setFittingQualityId(0);
                        dummy_FittingQuality.setName("Select");
                        Gson gson_dummy_FittingQuality = new Gson();
                        gson_dummy_FittingQuality.toJson(dummy_FittingQuality);
                        fittingQualities_List.add(dummy_FittingQuality);

                        /* Json array for fittingQualities*/
                        JSONArray fittingQualities_array = jsonObject.getJSONArray("FittingQuality");
                        for (int p = 0; p < fittingQualities_array.length(); p++) {
                            JSONObject jobj = fittingQualities_array.getJSONObject(p);

                            Gson gson = new Gson();
                            FittingQuality obj = null;
                            obj = new FittingQuality();
                            obj = gson.fromJson(jobj.toString(), FittingQuality.class);
                            fittingQualities_List.add(obj);
                        }
                        Singleton.getInstance().fittingQualities_List = fittingQualities_List;


                        /* ******Set ApproachRoadCondition Dummy Data*******/
                        ArrayList<ApproachRoadCondition> approachRoadConditions_list = new ArrayList<>();
                        ApproachRoadCondition dummy_ApproachRoadCondition = new ApproachRoadCondition();
                        dummy_ApproachRoadCondition.setApproachRoadConditionId(0);
                        dummy_ApproachRoadCondition.setName("Select");
                        Gson gson_dummy_ApproachRoadCondition = new Gson();
                        gson_dummy_ApproachRoadCondition.toJson(dummy_ApproachRoadCondition);
                        approachRoadConditions_list.add(dummy_ApproachRoadCondition);

                        /* Json array for ApproachRoadCondition*/
                        JSONArray approachRoadConditions_array = jsonObject.getJSONArray("ApproachRoadCondition");
                        for (int p = 0; p < approachRoadConditions_array.length(); p++) {
                            JSONObject jobj = approachRoadConditions_array.getJSONObject(p);
                            Gson gson = new Gson();
                            ApproachRoadCondition obj = null;
                            obj = new ApproachRoadCondition();
                            obj = gson.fromJson(jobj.toString(), ApproachRoadCondition.class);
                            approachRoadConditions_list.add(obj);
                        }
                        Singleton.getInstance().approachRoadConditions_list = approachRoadConditions_list;

                        /* ******Set Typeofcompound Dummy Data*******/
                        ArrayList<Typeofcompound> Typeofcompound_list = new ArrayList<>();
                        Typeofcompound dummy_Typeofcompound = new Typeofcompound();
                        dummy_Typeofcompound.setTypeOfCompoundId(0);
                        dummy_Typeofcompound.setName("Select");
                        Gson gson_dummy_Typeofcompound = new Gson();
                        gson_dummy_Typeofcompound.toJson(dummy_Typeofcompound);
                        Typeofcompound_list.add(dummy_Typeofcompound);

                        /* Json array for ApproachRoadCondition*/
                        JSONArray Typeofcompound_array = jsonObject.getJSONArray("Typeofcompound");
                        for (int p = 0; p < Typeofcompound_array.length(); p++) {
                            JSONObject jobj = Typeofcompound_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Typeofcompound obj = null;
                            obj = new Typeofcompound();
                            obj = gson.fromJson(jobj.toString(), Typeofcompound.class);
                            Typeofcompound_list.add(obj);
                        }
                        Singleton.getInstance().typeofcompound_list = Typeofcompound_list;



                        /* ******Set Remarks Dummy Data*******/
                        ArrayList<Remarks> remarks_list = new ArrayList<>();

                        /* Json array for Remarks*/
                        JSONArray remarks_array = jsonObject.getJSONArray("Remarks");
                        for (int p = 0; p < remarks_array.length(); p++) {
                            JSONObject jobj = remarks_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Remarks obj = null;
                            obj = new Remarks();
                            obj = gson.fromJson(jobj.toString(), Remarks.class);
                            remarks_list.add(obj);
                        }
                        Singleton.getInstance().remarks_list = remarks_list;



                        /* ******Set Land Dummy Data*******/
                        ArrayList<Land> land_list = new ArrayList<>();
                        Land dummy_Land = new Land();
                        dummy_Land.setTypeOfLandId(0);
                        dummy_Land.setName("Select");
                        Gson gson_dummy_Land = new Gson();
                        gson_dummy_Land.toJson(dummy_Land);
                        land_list.add(dummy_Land);

                        /* Json array for Land*/
                        JSONArray land_array = jsonObject.getJSONArray("Land");
                        for (int p = 0; p < land_array.length(); p++) {
                            JSONObject jobj = land_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Land obj = null;
                            obj = new Land();
                            obj = gson.fromJson(jobj.toString(), Land.class);
                            land_list.add(obj);
                        }
                        Singleton.getInstance().land_list = land_list;



                        /* ******Set PresentlyOccupied Dummy Data*******/
                        ArrayList<PresentlyOccupied> presentlyOccupied_list = new ArrayList<>();
                       /* PresentlyOccupied dummy_PresentlyOccupied = new PresentlyOccupied();
                        dummy_PresentlyOccupied.setPresentlyOccupiedId(0);
                        dummy_PresentlyOccupied.setName("Select");
                        Gson gson_dummy_PresentlyOccupied = new Gson();
                        gson_dummy_PresentlyOccupied.toJson(dummy_PresentlyOccupied);
                        presentlyOccupied_list.add(dummy_PresentlyOccupied);
*/
                        /* Json array for PresentlyOccupied*/
                        JSONArray presentlyOccupied_array = jsonObject.getJSONArray("PresentlyOccupied");
                        for (int p = 0; p < presentlyOccupied_array.length(); p++) {
                            JSONObject jobj = presentlyOccupied_array.getJSONObject(p);
                            Gson gson = new Gson();
                            PresentlyOccupied obj = null;
                            obj = new PresentlyOccupied();
                            obj = gson.fromJson(jobj.toString(), PresentlyOccupied.class);
                            presentlyOccupied_list.add(obj);
                        }
                        Singleton.getInstance().presentlyOccupied_list = presentlyOccupied_list;

                        /* ******Set Locality Dummy Data*******/
                        ArrayList<Locality> localities_list = new ArrayList<>();
                        Locality dummy_Locality = new Locality();
                        dummy_Locality.setTypeLocalityId(0);
                        dummy_Locality.setName("Select");
                        Gson gson_dummy_Locality = new Gson();
                        gson_dummy_Locality.toJson(dummy_Locality);
                        localities_list.add(dummy_Locality);

                        /* Json array for Locality*/
                        JSONArray localities_array = jsonObject.getJSONArray("Locality");
                        for (int p = 0; p < localities_array.length(); p++) {
                            JSONObject jobj = localities_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Locality obj = null;
                            obj = new Locality();
                            obj = gson.fromJson(jobj.toString(), Locality.class);
                            localities_list.add(obj);
                        }
                        Singleton.getInstance().localities_list = localities_list;



                        /* ******Set Tenure Dummy Data*******/
                        ArrayList<Tenure> tenures_list = new ArrayList<>();
                        Tenure dummy_Tenure = new Tenure();
                        dummy_Tenure.setTenureId(0);
                        dummy_Tenure.setName("Select");
                        Gson gson_dummy_Tenure = new Gson();
                        gson_dummy_Tenure.toJson(dummy_Tenure);
                        tenures_list.add(dummy_Tenure);

                        /* Json array for Tenure*/
                        JSONArray tenures_array = jsonObject.getJSONArray("Tenure");
                        for (int p = 0; p < tenures_array.length(); p++) {
                            JSONObject interviewobj = tenures_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Tenure obj = null;
                            obj = new Tenure();
                            obj = gson.fromJson(interviewobj.toString(), Tenure.class);
                            tenures_list.add(obj);
                        }
                        Singleton.getInstance().tenures_list = tenures_list;



                        /* ******Set LocalityCategory Dummy Data*******/
                        ArrayList<LocalityCategory> localityCategories_list = new ArrayList<>();
                        LocalityCategory dummy_LocalityCategory = new LocalityCategory();
                        dummy_LocalityCategory.setLocalityCategoryId(0);
                        dummy_LocalityCategory.setName("Select");
                        Gson gson_dummy_LocalityCategory = new Gson();
                        gson_dummy_LocalityCategory.toJson(dummy_LocalityCategory);
                        localityCategories_list.add(dummy_LocalityCategory);

                        /* Json array for LocalityCategory*/
                        JSONArray localityCategories_array = jsonObject.getJSONArray("LocalityCategory");
                        for (int p = 0; p < localityCategories_array.length(); p++) {
                            JSONObject jobj = localityCategories_array.getJSONObject(p);
                            Gson gson = new Gson();
                            LocalityCategory obj = null;
                            obj = new LocalityCategory();
                            obj = gson.fromJson(jobj.toString(), LocalityCategory.class);
                            localityCategories_list.add(obj);
                        }
                        Singleton.getInstance().localityCategories_list = localityCategories_list;


                        /* ******Set ClassModel Dummy Data*******/
                        ArrayList<ClassModel> classes_list = new ArrayList<>();
                        ClassModel dummy_ClassModel = new ClassModel();
                        dummy_ClassModel.setClassId(0);
                        dummy_ClassModel.setName("Select");
                        Gson gson_dummy_ClassModel = new Gson();
                        gson_dummy_ClassModel.toJson(dummy_ClassModel);
                        classes_list.add(dummy_ClassModel);

                        /* Json array for Class*/
                        JSONArray classes_array = jsonObject.getJSONArray("Class");
                        for (int p = 0; p < classes_array.length(); p++) {
                            JSONObject jobj = classes_array.getJSONObject(p);
                            Gson gson = new Gson();
                            ClassModel obj = null;
                            obj = new ClassModel();
                            obj = gson.fromJson(jobj.toString(), ClassModel.class);
                            classes_list.add(obj);
                        }
                        Singleton.getInstance().classes_list = classes_list;

                        /* ******Set TypeOfProperty Dummy Data*******/
                        ArrayList<TypeOfProperty> typeOfProperties_list = new ArrayList<>();
                        TypeOfProperty dummy_TypeOfProperty = new TypeOfProperty();
                        dummy_TypeOfProperty.setTypeOfPropertyId(0);
                        dummy_TypeOfProperty.setName("Select");
                        Gson gson_dummy_TypeOfProperty = new Gson();
                        gson_dummy_TypeOfProperty.toJson(dummy_TypeOfProperty);
                        typeOfProperties_list.add(dummy_TypeOfProperty);

                        /* Json array for TypeOfProperty*/
                        JSONArray typeOfProperty_array = jsonObject.getJSONArray("TypeOfProperty");
                        for (int p = 0; p < typeOfProperty_array.length(); p++) {
                            JSONObject interviewobj = typeOfProperty_array.getJSONObject(p);
                            Gson gson = new Gson();
                            TypeOfProperty obj = null;
                            obj = new TypeOfProperty();
                            obj = gson.fromJson(interviewobj.toString(), TypeOfProperty.class);
                            typeOfProperties_list.add(obj);
                        }
                        Singleton.getInstance().typeOfProperties_list = typeOfProperties_list;

                        int size = typeOfProperties_list.size();
                        for (int i = 1; i < typeOfProperties_list.size(); i++) {
                            TypeOfProperty typeOfProperty = typeOfProperties_list.get(i);
                            // Room - Insert the Open case adapter
                            if (MyApplication.getAppContext() != null) {
                                AppDatabase appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
                                appDatabase.typeofPropertyQuery().insertAll(typeOfProperty);
                            }
                        }

                        /* ******Set Floor Dummy Data*******/
                        ArrayList<Floor> floors_list = new ArrayList<>();
                        /*Floor dummy_Floor = new Floor();
                        dummy_Floor.setIntFloorId(0);
                        dummy_Floor.setName("Select");
                        Gson gson_dummy_Floor = new Gson();
                        gson_dummy_Floor.toJson(dummy_Floor);
                        floors_list.add(dummy_Floor);*/

                        /* Json array for Floor*/
                        JSONArray floor_array = jsonObject.getJSONArray("Floor");
                        for (int p = 0; p < floor_array.length(); p++) {
                            JSONObject interviewobj = floor_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Floor obj = null;
                            obj = new Floor();
                            obj = gson.fromJson(interviewobj.toString(), Floor.class);
                            floors_list.add(obj);
                        }
                        Singleton.getInstance().floors_list = floors_list;


                        /* ******Set Kitchenshape Dummy Data*******/
                        ArrayList<Kitchen> kitchens_shape_list = new ArrayList<>();
                        Kitchen dummy_Kitchenshape = new Kitchen();
                        dummy_Kitchenshape.setIntKitchenId(0);
                        dummy_Kitchenshape.setName("Select");
                        Gson gson_dummy_Kitchenshape = new Gson();
                        gson_dummy_Kitchenshape.toJson(dummy_Kitchenshape);
                        kitchens_shape_list.add(dummy_Kitchenshape);

                        JSONArray kitchen_array = jsonObject.getJSONArray("Kitchen");
                        for (int p = 0; p < kitchen_array.length(); p++) {
                            JSONObject interviewobj = kitchen_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Kitchen obj = null;
                            obj = new Kitchen();
                            obj = gson.fromJson(interviewobj.toString(), Kitchen.class);
                            kitchens_shape_list.add(obj);
                        }

                        Singleton.getInstance().kitchens_shape_list = kitchens_shape_list;

                        /* ******Set Kitchentype Dummy Data*******/
                        ArrayList<Kitchentype> kitchens_list = new ArrayList<>();
                        Kitchentype dummy_Kitchen = new Kitchentype();
                        dummy_Kitchen.setIntKitchenTypeId(0);
                        dummy_Kitchen.setName("Select");
                        Gson gson_dummy_Kitchen = new Gson();
                        gson_dummy_Kitchen.toJson(dummy_Kitchen);
                        kitchens_list.add(dummy_Kitchen);

                        JSONArray kitchen_array_type = jsonObject.getJSONArray("Kitchentype");
                        for (int p = 0; p < kitchen_array_type.length(); p++) {
                            JSONObject interviewobj = kitchen_array_type.getJSONObject(p);
                            Gson gson = new Gson();
                            Kitchentype obj = null;
                            obj = new Kitchentype();
                            obj = gson.fromJson(interviewobj.toString(), Kitchentype.class);
                            kitchens_list.add(obj);
                        }

                        Singleton.getInstance().kitchens_list = kitchens_list;

                        /* Json array for propertyidentificationchannel*/
                        /*JSONArray kitchen_array = jsonObject.getJSONArray("Kitchen");
                        for (int p = 0; p < kitchen_array.length(); p++) {

                            if (p < 2) {
                                JSONObject interviewobj = kitchen_array.getJSONObject(p);
                                Gson gson = new Gson();
                                Kitchen obj = null;
                                obj = new Kitchen();
                                obj = gson.fromJson(interviewobj.toString(), Kitchen.class);
                                kitchens_shape_list.add(obj);
                            } else {
                                JSONObject interviewobj = kitchen_array.getJSONObject(p);
                                Gson gson = new Gson();
                                Kitchen obj = null;
                                obj = new Kitchen();
                                obj = gson.fromJson(interviewobj.toString(), Kitchen.class);
                                kitchens_list.add(obj);
                            }
                        }*/




                        /* ******Set Window Dummy Data*******/
                        ArrayList<Window> windows_list = new ArrayList<>();
                        /*Window dummy_Window = new Window();
                        dummy_Window.setIntWindowId(0);
                        dummy_Window.setName("Select");
                        Gson gson_dummy_Window = new Gson();
                        gson_dummy_Window.toJson(dummy_Window);
                        windows_list.add(dummy_Window);*/

                        /* Json array for windows*/
                        JSONArray window_array = jsonObject.getJSONArray("Window");
                        for (int p = 0; p < window_array.length(); p++) {
                            JSONObject interviewobj = window_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Window obj = null;
                            obj = new Window();
                            obj = gson.fromJson(interviewobj.toString(), Window.class);
                            windows_list.add(obj);
                        }
                        Singleton.getInstance().windows_list = windows_list;


                        /* ******Set Door Dummy Data*******/
                        ArrayList<Door> doors_list = new ArrayList<>();
                      /*  Door dummy_Door = new Door();
                        dummy_Door.setIntDoorId(0);
                        dummy_Door.setName("Select");
                        Gson gson_dummy_Door = new Gson();
                        gson_dummy_Door.toJson(dummy_Door);
                        doors_list.add(dummy_Door);*/

                        /* Json array for propertyidentificationchannel*/
                        JSONArray door_array = jsonObject.getJSONArray("Door");
                        for (int p = 0; p < door_array.length(); p++) {
                            JSONObject interviewobj = door_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Door obj = null;
                            obj = new Door();
                            obj = gson.fromJson(interviewobj.toString(), Door.class);
                            doors_list.add(obj);
                        }
                        Singleton.getInstance().doors_list = doors_list;


                        /* ******Set WC Dummy Data*******/
                        ArrayList<WC> wcs_list = new ArrayList<>();

                        /* Json array for WC*/
                        JSONArray wc_array = jsonObject.getJSONArray("WC");
                        for (int p = 0; p < wc_array.length(); p++) {
                            JSONObject interviewobj = wc_array.getJSONObject(p);
                            Gson gson = new Gson();
                            WC obj = null;
                            obj = new WC();
                            obj = gson.fromJson(interviewobj.toString(), WC.class);
                            wcs_list.add(obj);
                        }
                        Singleton.getInstance().wcs_list = wcs_list;


                        /* ******Set Bath Dummy Data*******/
                        ArrayList<Bath> bath_list = new ArrayList<>();
                        Bath dummy_Bath = new Bath();
                        dummy_Bath.setIntBathId(0);
                        dummy_Bath.setName("Select");
                        Gson gson_dummy_Bath = new Gson();
                        gson_dummy_Bath.toJson(dummy_Bath);
                        bath_list.add(dummy_Bath);

                        /* Json array for Bath*/
                        JSONArray bath_array = jsonObject.getJSONArray("Bath");
                        for (int p = 0; p < bath_array.length(); p++) {
                            JSONObject interviewobj = bath_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Bath obj = null;
                            obj = new Bath();
                            obj = gson.fromJson(interviewobj.toString(), Bath.class);
                            bath_list.add(obj);
                        }
                        Singleton.getInstance().bath_list = bath_list;


                        /* ******Set Paint Dummy Data*******/
                        ArrayList<Paint> paints_list = new ArrayList<>();
                        /*Paint dummy_Paint = new Paint();
                        dummy_Paint.setIntPaintId(0);
                        dummy_Paint.setName("Select");
                        Gson gson_dummy_Paint = new Gson();
                        gson_dummy_Paint.toJson(dummy_Paint);
                        paints_list.add(dummy_Paint);*/

                        /* Json array for Paint*/
                        JSONArray paint_array = jsonObject.getJSONArray("Paint");
                        for (int p = 0; p < paint_array.length(); p++) {
                            JSONObject interviewobj = paint_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Paint obj = null;
                            obj = new Paint();
                            obj = gson.fromJson(interviewobj.toString(), Paint.class);
                            paints_list.add(obj);
                        }
                        Singleton.getInstance().paints_list = paints_list;


                        /* ******Set Structure Dummy Data*******/
                        ArrayList<Structure> structures_list = new ArrayList<>();
                       /* Structure dummy_Structure = new Structure();
                        dummy_Structure.setTypeOfStructureId(0);
                        dummy_Structure.setName("Select");
                        Gson gson_dummy_Structure = new Gson();
                        gson_dummy_Structure.toJson(dummy_Structure);
                        structures_list.add(dummy_Structure);*/

                        /* Json array for Structure*/
                        JSONArray structure_array = jsonObject.getJSONArray("Structure");
                        for (int p = 0; p < structure_array.length(); p++) {
                            JSONObject interviewobj = structure_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Structure obj = null;
                            obj = new Structure();
                            obj = gson.fromJson(interviewobj.toString(), Structure.class);
                            structures_list.add(obj);
                        }
                        Singleton.getInstance().structures_list = structures_list;


                        /* ******Set Construction Dummy Data*******/
                        ArrayList<Construction> constructions_list = new ArrayList<>();
                        Construction dummy_Construction = new Construction();
                        dummy_Construction.setConstructionId(0);
                        dummy_Construction.setName("Select");
                        Gson gson_dummy_Construction = new Gson();
                        gson_dummy_Construction.toJson(dummy_Construction);
                        constructions_list.add(dummy_Construction);

                        /* Json array for Construction*/
                        JSONArray constructions_array = jsonObject.getJSONArray("Construction");
                        for (int p = 0; p < constructions_array.length(); p++) {
                            JSONObject interviewobj = constructions_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Construction obj = null;
                            obj = new Construction();
                            obj = gson.fromJson(interviewobj.toString(), Construction.class);
                            constructions_list.add(obj);
                        }
                        Singleton.getInstance().constructions_list = constructions_list;


                        /* ******Set Remarks Dummy Data*******/
                        ArrayList<PresentCondition> presentConditions_list = new ArrayList<>();
                        PresentCondition dummy_PresentCondition = new PresentCondition();
                        dummy_PresentCondition.setPresentConditionId(0);
                        dummy_PresentCondition.setName("Select");
                        Gson gson_dummy_PresentCondition = new Gson();
                        gson_dummy_PresentCondition.toJson(dummy_PresentCondition);
                        presentConditions_list.add(dummy_PresentCondition);

                        /* Json array for PresentCondition*/
                        JSONArray presentCondition_array = jsonObject.getJSONArray("PresentCondition");
                        for (int p = 0; p < presentCondition_array.length(); p++) {
                            JSONObject interviewobj = presentCondition_array.getJSONObject(p);
                            Gson gson = new Gson();
                            PresentCondition obj = null;
                            obj = new PresentCondition();
                            obj = gson.fromJson(interviewobj.toString(), PresentCondition.class);
                            presentConditions_list.add(obj);
                        }
                        Singleton.getInstance().presentConditions_list = presentConditions_list;


                        /* ******Set Remarks Dummy Data*******/
                        ArrayList<QualityConstruction> qualityConstructions_list = new ArrayList<>();
                        QualityConstruction dummy_QualityConstruction = new QualityConstruction();
                        dummy_QualityConstruction.setQualityConstructionId(0);
                        dummy_QualityConstruction.setName("Select");
                        Gson gson_dummy_QualityConstruction = new Gson();
                        gson_dummy_QualityConstruction.toJson(dummy_QualityConstruction);
                        qualityConstructions_list.add(dummy_QualityConstruction);

                        /* Json array for QualityConstruction*/
                        JSONArray qualityConstruction_array = jsonObject.getJSONArray("QualityConstruction");
                        for (int p = 0; p < qualityConstruction_array.length(); p++) {
                            JSONObject interviewobj = qualityConstruction_array.getJSONObject(p);
                            Gson gson = new Gson();
                            QualityConstruction obj = null;
                            obj = new QualityConstruction();
                            obj = gson.fromJson(interviewobj.toString(), QualityConstruction.class);
                            qualityConstructions_list.add(obj);
                        }
                        Singleton.getInstance().qualityConstructions_list = qualityConstructions_list;


                        /* ******Set Building Dummy Data*******/
                        ArrayList<Building> buildings_list = new ArrayList<>();
                        Building dummy_Building = new Building();
                        dummy_Building.setTypeOfBuildingId(0);
                        dummy_Building.setName("Select");
                        Gson gson_dummy_Building = new Gson();
                        gson_dummy_Building.toJson(dummy_Building);
                        buildings_list.add(dummy_Building);

                        /* Json array for Building*/
                        JSONArray building_array = jsonObject.getJSONArray("Building");
                        for (int p = 0; p < building_array.length(); p++) {
                            JSONObject interviewobj = building_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Building obj = null;
                            obj = new Building();
                            obj = gson.fromJson(interviewobj.toString(), Building.class);
                            buildings_list.add(obj);
                        }
                        Singleton.getInstance().buildings_list = buildings_list;


                        /* ******Set Maintenance Dummy Data*******/
                        ArrayList<Maintenance> maintenances_list = new ArrayList<>();
                        Maintenance dummy_Maintenance = new Maintenance();
                        dummy_Maintenance.setMaintenanceOfBuildingId(0);
                        dummy_Maintenance.setName("Select");
                        Gson gson_dummy_Maintenance = new Gson();
                        gson_dummy_Maintenance.toJson(dummy_Maintenance);
                        maintenances_list.add(dummy_Maintenance);

                        /* Json array for Maintenance*/
                        JSONArray maintenance_array = jsonObject.getJSONArray("Maintenance");
                        for (int p = 0; p < maintenance_array.length(); p++) {
                            JSONObject interviewobj = maintenance_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Maintenance obj = null;
                            obj = new Maintenance();
                            obj = gson.fromJson(interviewobj.toString(), Maintenance.class);
                            maintenances_list.add(obj);
                        }
                        Singleton.getInstance().maintenances_list = maintenances_list;


                        /* ******Set Remarks Dummy Data*******/
                        ArrayList<Exterior> exteriors_list = new ArrayList<>();
                     /*   Exterior dummy_Exterior = new Exterior();
                        dummy_Exterior.setExteriorPaintId(0);
                        dummy_Exterior.setName("Select");
                        Gson gson_dummy_Exterior = new Gson();
                        gson_dummy_Exterior.toJson(dummy_Exterior);
                        exteriors_list.add(dummy_Exterior);*/

                        /* Json array for Exterior*/
                        JSONArray exterior_array = jsonObject.getJSONArray("Exterior");
                        for (int p = 0; p < exterior_array.length(); p++) {
                            JSONObject interviewobj = exterior_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Exterior obj = null;
                            obj = new Exterior();
                            obj = gson.fromJson(interviewobj.toString(), Exterior.class);
                            exteriors_list.add(obj);
                        }
                        Singleton.getInstance().exteriors_list = exteriors_list;


                        /* ******Set Paving Dummy Data*******/
                        ArrayList<Paving> pavings_list = new ArrayList<>();
                        /*Paving dummy_Paving = new Paving();
                        dummy_Paving.setPavingAroundBuildingId(0);
                        dummy_Paving.setName("Select");
                        Gson gson_dummy_Paving = new Gson();
                        gson_dummy_Paving.toJson(dummy_ApproachRoadCondition);
                        pavings_list.add(dummy_Paving);*/

                        /* Json array for Paving*/
                        JSONArray paving_array = jsonObject.getJSONArray("Paving");
                        for (int p = 0; p < paving_array.length(); p++) {
                            JSONObject interviewobj = paving_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Paving obj = null;
                            obj = new Paving();
                            obj = gson.fromJson(interviewobj.toString(), Paving.class);
                            pavings_list.add(obj);
                        }
                        Singleton.getInstance().pavings_list = pavings_list;


                        /* ******Set Marketablity Dummy Data*******/
                        ArrayList<Marketablity> marketablities_list = new ArrayList<>();
                        Marketablity dummy_Marketablity = new Marketablity();
                        dummy_Marketablity.setMarketabilityId(0);
                        dummy_Marketablity.setName("Select");
                        Gson gson_dummy_Marketablity = new Gson();
                        gson_dummy_Marketablity.toJson(dummy_Marketablity);
                        marketablities_list.add(dummy_Marketablity);

                        /* Json array for Marketablity*/
                        JSONArray marketablity_array = jsonObject.getJSONArray("Marketablity");
                        for (int p = 0; p < marketablity_array.length(); p++) {
                            JSONObject interviewobj = marketablity_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Marketablity obj = null;
                            obj = new Marketablity();
                            obj = gson.fromJson(interviewobj.toString(), Marketablity.class);
                            marketablities_list.add(obj);
                        }
                        Singleton.getInstance().marketablities_list = marketablities_list;


                        /* ******Set Nameofpersons Dummy Data*******/
                        ArrayList<Nameofpersons> Nameofpersons_list = new ArrayList<>();
                        Nameofpersons dummy_Nameofpersons = new Nameofpersons();
                        dummy_Nameofpersons.setNameofVendorId(0);
                        dummy_Nameofpersons.setName("Select");
                        Gson gson_dummy_Nameofpersons = new Gson();
                        gson_dummy_Nameofpersons.toJson(dummy_Nameofpersons);
                        Nameofpersons_list.add(dummy_Nameofpersons);

                        /* Json array for Marketablity*/
                        JSONArray Nameofpersons_array = jsonObject.getJSONArray("Nameofvendors");
                        for (int p = 0; p < Nameofpersons_array.length(); p++) {
                            JSONObject interviewobj = Nameofpersons_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Nameofpersons obj = null;
                            obj = new Nameofpersons();
                            obj = gson.fromJson(interviewobj.toString(), Nameofpersons.class);
                            Nameofpersons_list.add(obj);
                        }
                        Singleton.getInstance().nameofpersons_list = Nameofpersons_list;


                        /* ******Set CarParking Dummy Data*******/
                        ArrayList<CarParking> carParkings_list = new ArrayList<>();

                        /* Json array for CarParking*/
                        JSONArray carParking_array = jsonObject.getJSONArray("CarParking");
                        for (int p = 0; p < carParking_array.length(); p++) {
                            JSONObject interviewobj = carParking_array.getJSONObject(p);
                            Gson gson = new Gson();
                            CarParking obj = null;
                            obj = new CarParking();
                            obj = gson.fromJson(interviewobj.toString(), CarParking.class);
                            carParkings_list.add(obj);
                        }
                        Singleton.getInstance().carParkings_list = carParkings_list;


                        /* ******Set waterAvailability Dummy Data*******/
                        ArrayList<WaterAvailability> waterAvailabilities_list = new ArrayList<>();

                        /* Json array for WaterAvailability*/
                        JSONArray waterAvailability_array = jsonObject.getJSONArray("WaterAvailability");
                        for (int p = 0; p < waterAvailability_array.length(); p++) {
                            JSONObject interviewobj = waterAvailability_array.getJSONObject(p);
                            Gson gson = new Gson();
                            WaterAvailability obj = null;
                            obj = new WaterAvailability();
                            obj = gson.fromJson(interviewobj.toString(), WaterAvailability.class);
                            waterAvailabilities_list.add(obj);
                        }
                        Singleton.getInstance().waterAvailabilities_list = waterAvailabilities_list;


                        /* ******Set DocumentsSeen Dummy Data*******/
                        ArrayList<DocumentsSeen> documentsSeen_list = new ArrayList<>();
                        DocumentsSeen dummy_DocumentsSeen = new DocumentsSeen();
                        dummy_DocumentsSeen.setDocumentSeenId(0);
                        dummy_DocumentsSeen.setName("Select");
                        Gson gson_dummy_DocumentsSeen = new Gson();
                        gson_dummy_DocumentsSeen.toJson(dummy_DocumentsSeen);
                        documentsSeen_list.add(dummy_DocumentsSeen);

                        /* Json array for DocumentsSeen*/
                        JSONArray documentsSeen_array = jsonObject.getJSONArray("DocumentsSeen");
                        for (int p = 0; p < documentsSeen_array.length(); p++) {
                            JSONObject interviewobj = documentsSeen_array.getJSONObject(p);
                            Gson gson = new Gson();
                            DocumentsSeen obj = null;
                            obj = new DocumentsSeen();
                            obj = gson.fromJson(interviewobj.toString(), DocumentsSeen.class);
                            documentsSeen_list.add(obj);
                        }
                        Singleton.getInstance().documentsSeen_list = documentsSeen_list;


                        /* ******Set Remarks Dummy Data*******/
                        ArrayList<PlanApproval> planApprovals_list = new ArrayList<>();
                        PlanApproval dummy_PlanApproval = new PlanApproval();
                        dummy_PlanApproval.setPlanApprovedById(0);
                        dummy_PlanApproval.setName("Select");
                        Gson gson_dummy_PlanApproval = new Gson();
                        gson_dummy_PlanApproval.toJson(dummy_PlanApproval);
                        planApprovals_list.add(dummy_PlanApproval);

                        /* Json array for PlanApproval*/
                        JSONArray planApproval_array = jsonObject.getJSONArray("PlanApproval");
                        for (int p = 0; p < planApproval_array.length(); p++) {
                            JSONObject interviewobj = planApproval_array.getJSONObject(p);
                            Gson gson = new Gson();
                            PlanApproval obj = null;
                            obj = new PlanApproval();
                            obj = gson.fromJson(interviewobj.toString(), PlanApproval.class);
                            planApprovals_list.add(obj);
                        }
                        Singleton.getInstance().planApprovals_list = planApprovals_list;


                        /* ******Set ValuationMethod Dummy Data*******/
                        ArrayList<ValuationMethod> valuationMethods_list = new ArrayList<>();
                        ValuationMethod dummy_ValuationMethod = new ValuationMethod();
                        dummy_ValuationMethod.setValuationMethodId(0);
                        dummy_ValuationMethod.setName("Select");
                        Gson gson_dummy_ValuationMethod = new Gson();
                        gson_dummy_ValuationMethod.toJson(dummy_ValuationMethod);
                        valuationMethods_list.add(dummy_ValuationMethod);

                        /* Json array for ValuationMethod*/
                        JSONArray valuationMethod_array = jsonObject.getJSONArray("ValuationMethod");
                        for (int p = 0; p < valuationMethod_array.length(); p++) {
                            JSONObject interviewobj = valuationMethod_array.getJSONObject(p);
                            Gson gson = new Gson();
                            ValuationMethod obj = null;
                            obj = new ValuationMethod();
                            obj = gson.fromJson(interviewobj.toString(), ValuationMethod.class);
                            valuationMethods_list.add(obj);
                        }
                        Singleton.getInstance().valuationMethods_list = valuationMethods_list;


                        /* ******Set PropertyActualUsage Dummy Data*******/
                        ArrayList<PropertyActualUsage> propertyActualUsages_list = new ArrayList<>();
                        /*PropertyActualUsage dummy_PropertyActualUsage = new PropertyActualUsage();
                        dummy_PropertyActualUsage.setPropertyActualUsageId(0);
                        dummy_PropertyActualUsage.setName("Select");
                        Gson gson_dummy_PropertyActualUsage = new Gson();
                        gson_dummy_PropertyActualUsage.toJson(dummy_PropertyActualUsage);
                        propertyActualUsages_list.add(dummy_PropertyActualUsage);*/

                        /* Json array for PropertyActualUsage*/
                        JSONArray PropertyActualUsage_array = jsonObject.getJSONArray("PropertyActualUsage");
                        for (int p = 0; p < PropertyActualUsage_array.length(); p++) {
                            JSONObject interviewobj = PropertyActualUsage_array.getJSONObject(p);
                            Gson gson = new Gson();
                            PropertyActualUsage obj = null;
                            obj = new PropertyActualUsage();
                            obj = gson.fromJson(interviewobj.toString(), PropertyActualUsage.class);
                            propertyActualUsages_list.add(obj);
                        }
                        Singleton.getInstance().propertyActualUsages_list = propertyActualUsages_list;


                        /* ******Set Remarks Dummy Data*******/
                        ArrayList<AmenityQuality> amenityQualities_list = new ArrayList<>();
                        AmenityQuality dummy_AmenityQuality = new AmenityQuality();
                        dummy_AmenityQuality.setAmenityQualityId(0);
                        dummy_AmenityQuality.setName("Select");
                        Gson gson_dummy_AmenityQuality = new Gson();
                        gson_dummy_AmenityQuality.toJson(dummy_AmenityQuality);
                        amenityQualities_list.add(dummy_AmenityQuality);

                        /* Json array for AmenityQuality*/
                        JSONArray amenityQuality_array = jsonObject.getJSONArray("AmenityQuality");
                        for (int p = 0; p < amenityQuality_array.length(); p++) {
                            JSONObject interviewobj = amenityQuality_array.getJSONObject(p);
                            Gson gson = new Gson();
                            AmenityQuality obj = null;
                            obj = new AmenityQuality();
                            obj = gson.fromJson(interviewobj.toString(), AmenityQuality.class);
                            amenityQualities_list.add(obj);
                        }
                        Singleton.getInstance().amenityQualities_list = amenityQualities_list;


                        /* ******Set Measurements Dummy Data*******/
                        ArrayList<Measurements> measurements_list = new ArrayList<>();
                        Measurements dummy_Measurements = new Measurements();
                        dummy_Measurements.setMeasureUnitId(0);
                        dummy_Measurements.setName("Select");
                        Gson gson_dummy_Measurements = new Gson();
                        gson_dummy_Measurements.toJson(dummy_Measurements);
                        measurements_list.add(dummy_Measurements);

                        /* Json array for Measurements*/
                        JSONArray measurements_array = jsonObject.getJSONArray("Measurements");
                        for (int p = 0; p < measurements_array.length(); p++) {
                            JSONObject interviewobj = measurements_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Measurements obj = null;
                            obj = new Measurements();
                            obj = gson.fromJson(interviewobj.toString(), Measurements.class);
                            measurements_list.add(obj);
                        }
                        Singleton.getInstance().measurements_list = measurements_list;

                        /* Measurment valuation */
                        ArrayList<Measurements> measurements_list_val = new ArrayList<>();
                        ArrayList<Measurements> measurements_list_val_ka = new ArrayList<>();
                        measurements_list_val.add(dummy_Measurements);
                        /* Json array for Measurements*/
                        JSONArray measurements_array_new = jsonObject.getJSONArray("Measurements");
                        for (int p = 0; p < 2; p++) {
                            JSONObject interviewobj = measurements_array_new.getJSONObject(p);
                            Gson gson = new Gson();
                            Measurements obj = null;
                            obj = new Measurements();
                            obj = gson.fromJson(interviewobj.toString(), Measurements.class);
                            measurements_list_val.add(obj);
                            measurements_list_val_ka.add(obj);
                        }
                        Singleton.getInstance().measurements_list_val = measurements_list_val;
                        Singleton.getInstance().measurements_list_val_ka = measurements_list_val_ka;

                        // New - sq.y is added for Jaipur - LB and Land
                        ArrayList<Measurements> measurements_list_val_sqya = new ArrayList<>();
                        measurements_list_val_sqya.add(dummy_Measurements);
                        /* Json array for Measurements*/
                        JSONArray measurements_array_flat1 = jsonObject.getJSONArray("Measurements");
                        for (int p = 0; p < 3; p++) {
                            JSONObject interviewobj = measurements_array_flat1.getJSONObject(p);
                            Gson gson = new Gson();
                            Measurements obj = null;
                            obj = new Measurements();
                            obj = gson.fromJson(interviewobj.toString(), Measurements.class);
                            measurements_list_val_sqya.add(obj);
                        }
                        Singleton.getInstance().measurements_list_val_sqya = measurements_list_val_sqya;

                        /* Measurment Flat or Penthouse valuation */
                        ArrayList<Measurements> measurements_list_flat = new ArrayList<>();
                        measurements_list_flat.add(dummy_Measurements);
                        /* Json array for Measurements*/
                        JSONArray measurements_array_flat = jsonObject.getJSONArray("Measurements");
                        for (int p = 0; p < 1; p++) {
                            JSONObject interviewobj = measurements_array_flat.getJSONObject(p);
                            Gson gson = new Gson();
                            Measurements obj = null;
                            obj = new Measurements();
                            obj = gson.fromJson(interviewobj.toString(), Measurements.class);
                            measurements_list_flat.add(obj);
                        }
                        Singleton.getInstance().measurements_list_flat = measurements_list_flat;


                        /* ******Set FloorKind Dummy Data*******/
                        ArrayList<FloorKind> floorKind_list = new ArrayList<>();
                        FloorKind dummy_FloorKind = new FloorKind();
                        dummy_FloorKind.setFloorKindId(0);
                        dummy_FloorKind.setName("Select");
                        Gson gson_dummy_FloorKind = new Gson();
                        gson_dummy_FloorKind.toJson(dummy_FloorKind);
                        floorKind_list.add(dummy_FloorKind);

                        /* Json array for FloorKind*/
                        JSONArray floorkind_array = jsonObject.getJSONArray("FloorKind");
                        for (int p = 0; p < floorkind_array.length(); p++) {
                            JSONObject interviewobj = floorkind_array.getJSONObject(p);
                            Gson gson = new Gson();
                            FloorKind obj = null;
                            obj = new FloorKind();
                            obj = gson.fromJson(interviewobj.toString(), FloorKind.class);
                            floorKind_list.add(obj);
                        }
                        Singleton.getInstance().floorKind_list = floorKind_list;


                        /* ******Set floorUsage Dummy Data*******/
                        ArrayList<FloorUsage> floorUsages_list = new ArrayList<>();
                      /*  FloorUsage dummy_FloorUsage = new FloorUsage();
                        dummy_FloorUsage.setPropertyFloorUsageId(0);
                        dummy_FloorUsage.setName("Select");
                        Gson gson_dummy_FloorUsage = new Gson();
                        gson_dummy_FloorUsage.toJson(dummy_FloorUsage);
                        floorUsages_list.add(dummy_FloorUsage);*/

                        /* Json array for FloorUsage*/
                        JSONArray floorusage_array = jsonObject.getJSONArray("FloorUsage");
                        for (int p = 0; p < floorusage_array.length(); p++) {
                            JSONObject interviewobj = floorusage_array.getJSONObject(p);
                            Gson gson = new Gson();
                            FloorUsage obj = null;
                            obj = new FloorUsage();
                            obj = gson.fromJson(interviewobj.toString(), FloorUsage.class);
                            floorUsages_list.add(obj);
                        }
                        Singleton.getInstance().floorUsages_list = floorUsages_list;
                        Singleton.getInstance().floor_usage = floorUsages_list;


                        /* ******Set PassageType Dummy Data*******/
                        ArrayList<PassageType> passageTypes_list = new ArrayList<>();
                        PassageType dummy_PassageType = new PassageType();
                        dummy_PassageType.setPassageTypeId(0);
                        dummy_PassageType.setName("Select");
                        Gson gson_dummy_PassageType = new Gson();
                        gson_dummy_PassageType.toJson(dummy_PassageType);
                        passageTypes_list.add(dummy_PassageType);

                        /* Json array for PassageType*/
                        JSONArray passageType_array = jsonObject.getJSONArray("PassageType");
                        for (int p = 0; p < passageType_array.length(); p++) {
                            JSONObject interviewobj = passageType_array.getJSONObject(p);
                            Gson gson = new Gson();
                            PassageType obj = null;
                            obj = new PassageType();
                            obj = gson.fromJson(interviewobj.toString(), PassageType.class);
                            passageTypes_list.add(obj);
                        }
                        Singleton.getInstance().passageTypes_list = passageTypes_list;


                        /* ******Set ProximitySpinner Dummy Data*******/
                        ArrayList<ProximitySpinner> proximities_list = new ArrayList<>();
                        ProximitySpinner dummy_Proximity = new ProximitySpinner();
                        dummy_Proximity.setProximityId(0);
                        dummy_Proximity.setName("Select");
                        Gson gson_dummy_Proximity = new Gson();
                        gson_dummy_Proximity.toJson(dummy_Proximity);
                        proximities_list.add(dummy_Proximity);

                        /* Json array for ProximitySpinner*/
                        JSONArray proximity_array = jsonObject.getJSONArray("Proximity");
                        for (int p = 0; p < proximity_array.length(); p++) {
                            JSONObject interviewobj = proximity_array.getJSONObject(p);
                            Gson gson = new Gson();
                            ProximitySpinner obj = null;
                            obj = new ProximitySpinner();
                            obj = gson.fromJson(interviewobj.toString(), ProximitySpinner.class);
                            proximities_list.add(obj);
                        }
                        Singleton.getInstance().proximities_list = proximities_list;


                        /* ******Set roof Dummy Data*******/
                        ArrayList<Roof> roof_list = new ArrayList<>();
                        /*Roof dummy_Roof = new Roof();
                        dummy_Roof.setIntRoofId(0);
                        dummy_Roof.setName("Select");
                        Gson gson_dummy_Roof = new Gson();
                        gson_dummy_Roof.toJson(dummy_Roof);
                        roof_list.add(dummy_Roof);*/

                        /* Json array for Roof*/
                        JSONArray roof_array = jsonObject.getJSONArray("Roof");
                        for (int p = 0; p < roof_array.length(); p++) {
                            JSONObject interviewobj = roof_array.getJSONObject(p);
                            Gson gson = new Gson();
                            Roof obj = null;
                            obj = new Roof();
                            obj = gson.fromJson(interviewobj.toString(), Roof.class);
                            roof_list.add(obj);
                        }
                        Singleton.getInstance().roof_list = roof_list;


                        /* ******Set RejectionComment Dummy Data*******/
                        ArrayList<RejectionComment> rejectionComments = new ArrayList<>();
                        RejectionComment dummy_reject = new RejectionComment();
                        dummy_reject.setRejectionId(0);
                        dummy_reject.setComment("Select");
                        Gson gson_dummy_reject = new Gson();
                        gson_dummy_reject.toJson(dummy_reject);
                        rejectionComments.add(dummy_reject);

                        /* Json array for RejectionComment*/
                        JSONArray rejectionComments_array = jsonObject.getJSONArray("RejectionComment");
                        for (int p = 0; p < rejectionComments_array.length(); p++) {
                            JSONObject interviewobj = rejectionComments_array.getJSONObject(p);
                            Gson gson = new Gson();
                            RejectionComment obj = null;
                            obj = new RejectionComment();
                            obj = gson.fromJson(interviewobj.toString(), RejectionComment.class);
                            rejectionComments.add(obj);
                        }

                        /* RejectionComment valuation */
                        ArrayList<RejectionComment> RejectionComment_val = new ArrayList<>();
                        RejectionComment_val.add(dummy_reject);
                        /* Json array for Measurements*/
                        JSONArray RejectionComment_array_new = jsonObject.getJSONArray("RejectionComment");
                        for (int p = 0; p < RejectionComment_array_new.length(); p++) {
                            JSONObject interviewobj = RejectionComment_array_new.getJSONObject(p);
                            Gson gson = new Gson();
                            RejectionComment obj = null;
                            obj = new RejectionComment();
                            obj = gson.fromJson(interviewobj.toString(), RejectionComment.class);
                            if (obj.getCategoryId().equalsIgnoreCase("1")) {
                                RejectionComment_val.add(obj);
                            }
                        }
                        Singleton.getInstance().rejectionComments_list = RejectionComment_val;


                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = info;
        // Response.propertyTypeList = dataModels;

        return Response;
    }


    public static DataResponse parseSaveCaseInspectionResponse(String response) {
        String status = "", info = "", jObj_data = "";
        DataResponse Response = new DataResponse();

        try {
            if (response != null) {
                try {
                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        jObj_data = jObj.getString("data");

                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = jObj_data;

        return Response;
    }

    public static DataResponse parseEditInspectionResponse(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();

        try {
            if (response != null) {
                try {
                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        String jObj_data = jObj.getString("data");

                        JSONObject jsonObject = new JSONObject(jObj_data);

                        /*******Set Case Data*******/
                        JSONObject jobj = jsonObject.getJSONObject("Case");
                        Gson gson = new Gson();
                        Case obj = null;
                        obj = new Case();
                        obj = gson.fromJson(jobj.toString(), Case.class);
                        Singleton.getInstance().aCase = obj;

                        /*******Set Property Data*******/
                        JSONObject property_jObj = jsonObject.getJSONObject("Property");
                        Gson propertygson = new Gson();
                        Property propertyobj = null;
                        propertyobj = new Property();
                        propertyobj = propertygson.fromJson(property_jObj.toString(), Property.class);
                        Singleton.getInstance().property = propertyobj;

                        /*******Set IndProperty Data*******/
                        JSONObject IndProperty_jobj = jsonObject.getJSONObject("IndProperty");
                        Gson IndPropertygson = new Gson();
                        IndProperty IndPropertyobj = null;
                        IndPropertyobj = new IndProperty();
                        IndPropertyobj = IndPropertygson.fromJson(IndProperty_jobj.toString(), IndProperty.class);
                        Singleton.getInstance().indProperty = IndPropertyobj;

                        /*******Set IndPropertyValuation Data*******/
                        JSONObject IndPropertyValuation_jobj = jsonObject.getJSONObject("IndPropertyValuation");
                        Gson IndPropertyValuationgson = new Gson();
                        IndPropertyValuation IndPropertyValuationobj = null;
                        IndPropertyValuationobj = new IndPropertyValuation();
                        IndPropertyValuationobj = IndPropertyValuationgson.fromJson(IndPropertyValuation_jobj.toString(), IndPropertyValuation.class);
                        Singleton.getInstance().indPropertyValuation = IndPropertyValuationobj;


                        /* Json array for IndPropertyFloors*/
                        ArrayList<IndPropertyFloor> IndPropertyFloor_list = new ArrayList<>();
                        JSONArray IndPropertyFloors_array = jsonObject.getJSONArray("IndPropertyFloors");
                        for (int p = 0; p < IndPropertyFloors_array.length(); p++) {
                            JSONObject IndPropertyFloors_jobj = IndPropertyFloors_array.getJSONObject(p);
                            Gson IndPropertyFloorsgson = new Gson();
                            IndPropertyFloor IndPropertyFloorsobj = null;
                            IndPropertyFloorsobj = new IndPropertyFloor();
                            IndPropertyFloorsobj = IndPropertyFloorsgson.fromJson(IndPropertyFloors_jobj.toString(), IndPropertyFloor.class);
                            IndPropertyFloor_list.add(IndPropertyFloorsobj);
                        }
                        Singleton.getInstance().indPropertyFloors = IndPropertyFloor_list;


                        /* Json array for IndPropertyFloors valuation*/
                        ArrayList<IndPropertyFloorsValuation> IndfloorsValuation_list = new ArrayList<>();
                        JSONArray IndFloorsValuation_array = jsonObject.getJSONArray("IndPropertyFloorsValuation");
                        for (int p = 0; p < IndFloorsValuation_array.length(); p++) {
                            JSONObject IndPropertyFloors_jobj = IndFloorsValuation_array.getJSONObject(p);
                            Gson IndFloorsValuationgson = new Gson();
                            IndPropertyFloorsValuation IndPropertyFloorsValobj = null;
                            IndPropertyFloorsValobj = new IndPropertyFloorsValuation();
                            IndPropertyFloorsValobj = IndFloorsValuationgson.fromJson(IndPropertyFloors_jobj.toString(), IndPropertyFloorsValuation.class);
                            IndfloorsValuation_list.add(IndPropertyFloorsValobj);
                        }
                        Singleton.getInstance().indPropertyFloorsValuations = IndfloorsValuation_list;


                        /* Json array for Roof*/
                        ArrayList<Proximity> Proximity_list = new ArrayList<>();
                        JSONArray roof_array = jsonObject.getJSONArray("Proximity");
                        for (int p = 0; p < roof_array.length(); p++) {
                            JSONObject Proximity_jobj = roof_array.getJSONObject(p);
                            Gson Proximitygson = new Gson();
                            Proximity Proximityobj = null;
                            Proximityobj = new Proximity();
                            Proximityobj = Proximitygson.fromJson(Proximity_jobj.toString(), Proximity.class);
                            Proximity_list.add(Proximityobj);
                        }
                        Singleton.getInstance().proximities = Proximity_list;


                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = info;
        Response.aCase = Singleton.getInstance().aCase;
        Response.property = Singleton.getInstance().property;
        Response.indProperty = Singleton.getInstance().indProperty;
        Response.indPropertyValuation = Singleton.getInstance().indPropertyValuation;
        Response.indPropertyFloors = Singleton.getInstance().indPropertyFloors;
        Response.proximities = Singleton.getInstance().proximities;

        return Response;
    }


    /******
     * Delete proximity
     * *******/
    public static DataResponse parseRemoveProximityResponse(String response) {
        String status = "", info = "", jObj_data = "";
        DataResponse Response = new DataResponse();

        try {
            if (response != null) {
                try {
                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        jObj_data = jObj.getString("data");

                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = jObj_data;

        return Response;
    }

    /******
     * Photo add update and delete
     * ********/
    public static DataResponse viewimage(String response) {
        DataResponse dataResponse = new DataResponse();
        ArrayList<ImageBase64> list = new ArrayList<>();
        try {
            if (response != null) {
                JSONObject object = new JSONObject(response);
                dataResponse.status = object.getString("status");
                if (dataResponse.status.equalsIgnoreCase("1")) {
                    String arr = object.getString("data");
                    ImageBase64 base64 = new ImageBase64();
                    if (arr.equalsIgnoreCase("[]")) {
                        base64.setImage64("Logo");
                        base64.setEditTextValue("Title");
                        list.add(base64);
                    } else {
                        JSONArray array = object.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {

                            JSONObject object1 = array.getJSONObject(i);
                            //base64.setID(object1.getString("Id"));
                            String id = object1.getString("Id");
                            dataResponse.imageid.add(id);
                            base64.setImage64(object1.getString("Logo"));
                            base64.setEditTextValue(object1.getString("Title"));
                            list.add(base64);
                        }
                    }

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        dataResponse.base64image = list;
        Singleton.getInstance().base64image = list;
        return dataResponse;
    }

    public static DataResponse uploadimage(String response) {
        DataResponse dataResponse = new DataResponse();
        try {
            if (response != null) {
                JSONObject object = new JSONObject(response);
                dataResponse.status = object.getString("status");
                dataResponse.msg = object.getString("msg");
                if (dataResponse.status.equalsIgnoreCase("1")) {
                    dataResponse.info = object.getString("data");
                    dataResponse.msg = object.getString("msg");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataResponse;
    }

    public static DataResponse deleteimage(String response) {
        DataResponse dataResponse = new DataResponse();
        try {
            if (response != null) {
                JSONObject object = new JSONObject(response);
                dataResponse.status = object.getString("status");
                dataResponse.msg = object.getString("msg");
                if (dataResponse.status.equalsIgnoreCase("1")) {
                    dataResponse.info = object.getString("data");
                    dataResponse.msg = object.getString("msg");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataResponse;
    }

    public static DataResponse parseOfflineCaseCountResponse(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();

        try {
            if (response != null) {
                try {

                    JSONObject jObj = new JSONObject(response.trim());
                    Iterator<?> jObj_Keys = jObj.keys();

                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        info = jObj.getString("data");

                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = info;

        return Response;
    }


    public static DataResponse parseDocumentReadResponse_offline(String response) {
        String status = "", info = "", request = "";
        DataResponse Response = new DataResponse();
        ArrayList<Document_list> dataModels = new ArrayList<>();

        AppDatabase appDatabase = null;
        if (MyApplication.getAppContext() != null) {
            //Room
            appDatabase = AppDatabase.getAppDatabase(MyApplication.getAppContext());
        }

        int caseid_int = 0;
        String caseid_str = SettingsUtils.getInstance().getValue(SettingsUtils.CASE_ID, "");
        if (!General.isEmpty(caseid_str)) {
            caseid_int = Integer.parseInt(caseid_str);
        }

        try {
            if (response != null) {
                try {
                    JSONObject jObj = new JSONObject(response.trim());
                    status = jObj.getString(TAG_STATUS);

                    if (status.equals("1")) {
                        msg = jObj.getString(TAG_MSG);
                        String jObj_data = jObj.getString("data");

                        if (jObj_data.equalsIgnoreCase("[]")) {
                            System.err.print("document null");
                        } else {
                            /******data Json Array response*******/
                            // Room - Delete
                            appDatabase.interfaceDocumentListQuery().deleteRow(caseid_int);
                            JSONArray jsonArray = new JSONArray(jObj_data);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                /* Gson gson = new Gson();
                                Document_list obj = null;
                                obj = new Document_list();
                                obj = gson.fromJson(jsonObj.toString(), Document_list.class);
                                dataModels.add(obj);*/

                                JSONObject jsonObj = jsonArray.getJSONObject(i);
                                Document_list obj = new Document_list();
                                String document = jsonObj.getString("Document");
                                // Check the Base64Image should be less than 19,80,000 ...
                                if (document.length() < 1980000) {
                                    Gson gson = new Gson();
                                    obj = gson.fromJson(jsonObj.toString(), Document_list.class);
                                } else {
                                    obj.setCaseId(jsonObj.getInt("CaseId"));
                                    obj.setId(jsonObj.getString("Id"));
                                    obj.setDocumentName(jsonObj.getString("DocumentName"));
                                    obj.setTitle(jsonObj.getString("Title"));
                                    obj.setVisibleToFieldStaff(jsonObj.getString("VisibleToFieldStaff"));
                                    // set dummy refernece for avoid the click function of the document
                                    obj.setDocument("dummy");
                                }

                                // Room - Add
                                appDatabase.interfaceDocumentListQuery().insertAll(obj);
                            }
                        }
                    } else {
                        status = "0";
                        msg = jObj.getString("Message");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (IllegalArgumentException | IllegalStateException |
                SecurityException | IndexOutOfBoundsException | OutOfMemoryError e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("InputStream", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        Response.status = status;
        Response.msg = msg;
        Response.info = info;
        Response.documentRead = dataModels;

        return Response;
    }


}
