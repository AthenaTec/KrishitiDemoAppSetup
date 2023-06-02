package com.realappraiser.gharvalue.communicator;

import android.util.Log;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Suganya on 3/8/2017.
 */

@SuppressWarnings("ALL")
public class RequestParam {

    public static RequestBody LoginRequestParams(JsonRequestData jsonRequestData) {
        RequestBody requestBody = new FormBody.Builder()
                .add("email", jsonRequestData.getEmail())
                .add("password", jsonRequestData.getPwd())
                .build();
        return requestBody;
    }

    public static String OpenCloseCaseListRequestParams(JsonRequestData jsonRequestData) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(jsonRequestData.getInitQueryUrl()).newBuilder();
        urlBuilder.addQueryParameter("empId", jsonRequestData.getEmpId());
        urlBuilder.addQueryParameter("startDate", jsonRequestData.getStartDate());
        // urlBuilder.addQueryParameter("endDate", "09/Jan/2018");
        urlBuilder.addQueryParameter("endDate", jsonRequestData.getEndDate());
        String geturl = urlBuilder.build().toString();

        return geturl;
    }

    public static RequestBody UpdateCaseStatusRequestParams(JsonRequestData jsonRequestData) {
        RequestBody requestBody = new FormBody.Builder()
                .add("CaseId", jsonRequestData.getCaseId())
                .add("ModifiedBy", jsonRequestData.getModifiedBy())
                .add("Status", jsonRequestData.getStatus())
                .build();
        return requestBody;
    }

    public static String PropertyTypeListRequestParams(JsonRequestData jsonRequestData) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(jsonRequestData.getInitQueryUrl()).newBuilder();
        urlBuilder.addQueryParameter("Bid", jsonRequestData.getBankId());
        //urlBuilder.addQueryParameter("Tid", jsonRequestData.getTypeID());
        String geturl = urlBuilder.build().toString();

        return geturl;
    }

    public static String GetReportPropertyTypeRequestParams(JsonRequestData jsonRequestData) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(jsonRequestData.getInitQueryUrl()).newBuilder();
        urlBuilder.addQueryParameter("bankId", jsonRequestData.getBankId());
        urlBuilder.addQueryParameter("agencyBranchId", jsonRequestData.getAgencyBranchId());
        urlBuilder.addQueryParameter("proId", jsonRequestData.getProId());
        String geturl = urlBuilder.build().toString();
        return geturl;
    }


    public static String GetFreshCaseSelectionRequest(JsonRequestData jsonRequestData) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(jsonRequestData.getInitQueryUrl()).newBuilder();
        urlBuilder.addQueryParameter("agencyBranchId", jsonRequestData.getAgencyBranchId());
        urlBuilder.addQueryParameter("agencyId", jsonRequestData.getAgencyId());
        String geturl = urlBuilder.build().toString();

        return geturl;
    }


    public static String GetBankSelection(JsonRequestData jsonRequestData) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(jsonRequestData.getInitQueryUrl()).newBuilder();
        urlBuilder.addQueryParameter("PropertytypeId", jsonRequestData.getPropertyId());
        urlBuilder.addQueryParameter("agencyBranchId", jsonRequestData.getAgencyBranchId());
        urlBuilder.addQueryParameter("agencyId", jsonRequestData.getAgencyId());
        String geturl = urlBuilder.build().toString();
        return geturl;
    }

    public static String GetByFieldStaffByCity(JsonRequestData jsonRequestData) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(jsonRequestData.getInitQueryUrl()).newBuilder();
        urlBuilder.addQueryParameter("agencyId", jsonRequestData.getAgencyId());
        urlBuilder.addQueryParameter("roleId", jsonRequestData.getRoleId());
        urlBuilder.addQueryParameter("branchId", jsonRequestData.getAgencyBranchId());

        String geturl = urlBuilder.build().toString();
        return geturl;
    }

    public static String GetPendingCases(JsonRequestData jsonRequestData){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(jsonRequestData.getInitQueryUrl()).newBuilder();
        urlBuilder.addQueryParameter("RoleId", jsonRequestData.getRoleId());
        urlBuilder.addQueryParameter("EmpId", jsonRequestData.getEmpId());
        String geturl = urlBuilder.build().toString();
        return geturl;
    }

    public static RequestBody TransferCase(JsonRequestData jsonRequestData) {
        return new FormBody.Builder()
                .add("CaseId", jsonRequestData.getCaseId())
                .add("AssignedTo", jsonRequestData.getAssignedTo())
                .add("ModifiedBy", jsonRequestData.getModifiedBy())
                .build();
    }

    public static RequestBody LocationTracker(JsonRequestData jsonRequestData) {
        return new FormBody.Builder()
                .add("CaseId", jsonRequestData.getCaseId())
                .add("FieldStaffId", jsonRequestData.getEmpId())
                .add("Type", jsonRequestData.getLocationType())
                .add("Latitude", jsonRequestData.getLatitude())
                .add("Longitude", jsonRequestData.getLongitude())
                .add("TrackerTime", jsonRequestData.getTrackerTime())
                .add("Address", jsonRequestData.getAddress())
                .build();
    }

    public static String GetRatePopupDetails(JsonRequestData jsonRequestData){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(jsonRequestData.getInitQueryUrl()).newBuilder();
        urlBuilder.addQueryParameter("caseId", jsonRequestData.getCaseId());
        urlBuilder.addQueryParameter("lat", jsonRequestData.getLatitude());
        urlBuilder.addQueryParameter("longs", jsonRequestData.getLongitude());

        String geturl = urlBuilder.build().toString();
        return geturl;
    }


    public static RequestBody CreateCaseNewRequestParams(JsonRequestData jsonRequestData) {
        RequestBody requestBody = new FormBody.Builder()
                .add("ApplicantName", jsonRequestData.getApplicantName())
                .add("PropertyType", jsonRequestData.getPropertyType())
                .add("BankId", jsonRequestData.getBankId())
                .add("CreatedBy", jsonRequestData.getModifiedBy())
                .add("CaseAdminId", jsonRequestData.getCaseAdminId())
                .add("ReportMakerId", jsonRequestData.getReportMakerId())
                .build();
        return requestBody;
    }


    public static RequestBody UpdatePropertyTypeNewRequestParams(JsonRequestData jsonRequestData) {
        RequestBody requestBody = new FormBody.Builder()
                .add("CaseId", jsonRequestData.getCaseId())
                .add("PropertyType", jsonRequestData.getPropertyType())
                .add("BankName", jsonRequestData.getBankName())
                .add("ReportType", jsonRequestData.getReportType())
                .add("ModifiedBy", jsonRequestData.getModifiedBy())
                .build();
        return requestBody;
    }

    public static RequestBody UpdatePropertyTypeRequestParams(JsonRequestData jsonRequestData) {
        RequestBody requestBody = new FormBody.Builder()
                .add("CaseId", jsonRequestData.getCaseId())
                .add("PropertyType", jsonRequestData.getPropertyType())
                .build();
        return requestBody;
    }

    public static String DocumentReadRequestParams(JsonRequestData jsonRequestData) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(jsonRequestData.getInitQueryUrl()).newBuilder();
        urlBuilder.addQueryParameter("CaseId", jsonRequestData.getCaseId());
        String geturl = urlBuilder.build().toString();

        return geturl;
    }

    public static RequestBody UpdateCaseStatusRejectRequestParams(JsonRequestData jsonRequestData) {
        RequestBody requestBody = new FormBody.Builder()
                .add("CaseId", jsonRequestData.getCaseId())
                .add("ModifiedBy", jsonRequestData.getModifiedBy())
                .add("Status", jsonRequestData.getStatus())
                .add("EmployeeRemarks", jsonRequestData.getEmployeeRemarks())
                .build();
        return requestBody;
    }

    public static RequestBody RejectCaseStatusRejectRequestParams(JsonRequestData jsonRequestData) {
        RequestBody requestBody = new FormBody.Builder()
                .add("CaseId", jsonRequestData.getCaseId())
                .add("ModifiedBy", jsonRequestData.getModifiedBy())
                .add("StatusId", jsonRequestData.getStatus())
                .add("EmployeeRemarks", jsonRequestData.getEmployeeRemarks())
                .build();
        return requestBody;
    }

    public static RequestBody SaveCaseInspectionRequestParams(JsonRequestData jsonRequestData) {

       /* String vale = jsonRequestData.getInsertMainObj().toString();
        Log.e("", "" + jsonRequestData.getInsertMainObj().toString());

        RequestBody newreq = new FormBody.Builder()
                .add("", jsonRequestData.getInsertMainObj().toString())
                .build();*/

        RequestBody newreq = RequestBody.create(
                MediaType.parse("application/json"), jsonRequestData.getMainJson());

        return newreq;
    }

    public static String EditInspectionRequestParams(JsonRequestData jsonRequestData) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(jsonRequestData.getInitQueryUrl()).newBuilder();
        urlBuilder.addQueryParameter("id", jsonRequestData.getId());
        String geturl = urlBuilder.build().toString();

        return geturl;
    }

    public static String getCaseInspectionRequestParams(JsonRequestData jsonRequestData) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(jsonRequestData.getInitQueryUrl()).newBuilder();
        urlBuilder.addQueryParameter("id", jsonRequestData.getId());
        String geturl = urlBuilder.build().toString();

        return geturl;
    }


    public static RequestBody DeleteProximityonMinus(JsonRequestData jsonRequestData) {
        RequestBody requestBody = new FormBody.Builder()
                .add("Id", jsonRequestData.getId())
                .add("CaseId", jsonRequestData.getCaseId())
                .add("ProximityId", jsonRequestData.getProximityId())
                .build();
        return requestBody;
    }

    public static String DeleteFloorRequestParams(JsonRequestData jsonRequestData) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(jsonRequestData.getInitQueryUrl()).newBuilder();
        urlBuilder.addQueryParameter("caseId", jsonRequestData.getCaseId());
        String geturl = urlBuilder.build().toString();

        return geturl;
    }

    public static RequestBody uploadimageRequestParams(JsonRequestData jsonRequestData) {
        RequestBody requestBody = new FormBody.Builder()
                .add("img", jsonRequestData.getCompanyName())
                .add("location", jsonRequestData.getContactPersonName())
                .add("edit_synk", jsonRequestData.getApplicantName())
                .build();
        return requestBody;
    }

    public static RequestBody uploadimageRequestParams_measurment_offline(JsonRequestData jsonRequestData) {
        RequestBody requestBody = new FormBody.Builder()
                .add("img", jsonRequestData.getCompanyName())
                .add("location", jsonRequestData.getContactPersonName())
                .add("edit_synk", jsonRequestData.getApplicantName())
                .add("measurementimg", jsonRequestData.getEmail())
                .build();
        return requestBody;
    }

    public static RequestBody uploadimageRequestParams_oneImage(JsonRequestData jsonRequestData) {
        RequestBody requestBody = new FormBody.Builder()
                .add("measurementimg", jsonRequestData.getCompanyName())
                .add("edit_synk", jsonRequestData.getApplicantName())
                .build();
        return requestBody;
    }

    public static RequestBody deleteimageRequestParams(JsonRequestData jsonRequestData) {
        RequestBody requestBody = new FormBody.Builder()
                .add("Id", jsonRequestData.getJobID())
                .add("PropertyId", jsonRequestData.getPropertyID())
                .build();
        return requestBody;
    }

    public static String getPropertyDetails(JsonRequestData requestData) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(requestData.getInitQueryUrl()).newBuilder();
        urlBuilder.addQueryParameter("CaseId", requestData.getCaseId());
        String geturl = urlBuilder.build().toString();
        return geturl;
    }

    public static RequestBody GetPropertyCompareDetails(JsonRequestData requestData) {
        return new FormBody.Builder()
                .add("Distance", requestData.getDistance())
                .add("Caseid", requestData.getCaseId())
                .add("CurrentPropertyType", requestData.getCurrentPropertyType())
                .add("Lat", requestData.getLatitude())
                .add("Long", requestData.getLongitude())
                .add("PropertyType", requestData.getPropertyType())
                .build();
    }

   /* public static RequestBody PhlebotomistRescheduleRequestParams(JsonRequestData jsonRequestData) {

        RequestBody requestBody = new FormBody.Builder()
                .add("enquiry_id", jsonRequestData.getEnquiry_id())
                .add("user_id", jsonRequestData.getUser_id())
                .add("type", jsonRequestData.getType())
                .add("date", jsonRequestData.getDate())
                .add("time", jsonRequestData.getTime())
                .add("reason", jsonRequestData.getReason())
                .build();
        return requestBody;
    }

    public static RequestBody PhotoUpload(JsonRequestData jsonRequestData) {
        String realPath = jsonRequestData.getImage();
        File sourceFile = new File(realPath);

        Log.d("", "File...::::" + sourceFile + " : " + sourceFile.exists());
        String filename = realPath.substring(realPath.lastIndexOf("/") + 1);

        //Compress Image
        Bitmap bmp = BitmapFactory.decodeFile(sourceFile.getAbsolutePath());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 70, bos);


        final MediaType MEDIA_TYPE_PNG = MediaType.parse("image*//*");

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "enquiryreceipt.png", RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray()))
                .build();

        return requestBody;
    }


    public static RequestBody CompleteCollectionRequestParams(JsonRequestData jsonRequestData) {

        String realPath = jsonRequestData.getImage();

        File sourceFile = new File(realPath);

        Log.d("", "File...::::" + sourceFile + " : " + sourceFile.exists());
        String filename = realPath.substring(realPath.lastIndexOf("/") + 1);

        //Compress Image
        Bitmap bmp = BitmapFactory.decodeFile(sourceFile.getAbsolutePath());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 70, bos);


        final MediaType MEDIA_TYPE_PNG = MediaType.parse("image*//*");

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "signature.png", RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray()))
                .addFormDataPart("test", jsonRequestData.getTest())
                .addFormDataPart("amount", jsonRequestData.getAmount())
                .addFormDataPart("user_id", jsonRequestData.getUser_id())
                .addFormDataPart("enquiry_id", jsonRequestData.getEnquiry_id())
                .addFormDataPart("barcode", jsonRequestData.getBarcode())
                .addFormDataPart("payment_method", jsonRequestData.getPayment_method())
                .addFormDataPart("reference_no", jsonRequestData.getReference_no())
                .build();

        return requestBody;
    }*/

}
