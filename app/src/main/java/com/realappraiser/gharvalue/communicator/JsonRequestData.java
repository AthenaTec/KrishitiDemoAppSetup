package com.realappraiser.gharvalue.communicator;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.RequestBody;

/**
 * Created by ${Ashokkumar} on 8/21/2017.
 */

public class JsonRequestData {

    private String url;
    private String requestData;
    private RequestBody requestBody;
    private String response;
    private String email;
    private String pwd;

    private String agencyBranchId;
    private String proId;
    private String ReportType;


    private JSONObject insertMainObj;

    private String mainJson;

    private String id;
    private String empId;
    private String startDate;
    private String endDate;
    private String initQueryUrl;
    private String CaseId;
    private String ApplicantName;
    private String ApplicantContactNo;
    private String PropertyAddress;
    private String ContactPersonName;
    private String ContactPersonNumber;
    private String BankName;
    private String BankId;
    private String PropertyType;
    private String TypeID;
    private String AssignedAt;
    private String ReportChecker;
    private String Status;
    private String ReportDispatcher;
    private String FieldStaff;
    private String AssignedTo;
    private String ReportMaker;
    private String ReportFinalizer;
    private String PropertyId;
    private String ReportFile;
    private String ReportId;
    private String ReportTemplateId;
    private String Signature1;
    private String PropertyCategoryId;
    private String count;
    private String Signature2;
    private String EmployeeName;
    private String Role;
    private String StatusId;
    private String CaseAdminId;
    private String ReportMakerId;

    private String ModifiedBy;

    private JSONObject Case;
    private JSONObject Property;
    private JSONObject IndProperty;
    private JSONArray IndPropertyFloors;
    private JSONObject IndPropertyValuation;
    private JSONArray IndPropertyFloorsValuation;
    private JSONArray Proximity;


    private String jobID;
    private String propertyID;
    private String companyName;
    private String ProximityId;
    private String EmployeeRemarks;
    private String AgencyId;
    private String RoleId;
    private String LocationType;
    private String Latitude;
    private String Longitude;
    private String Address;
    private String TrackerTime;

    private String Distance;
    private String CurrentPropertyType;

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getCurrentPropertyType() {
        return CurrentPropertyType;
    }

    public void setCurrentPropertyType(String currentPropertyType) {
        CurrentPropertyType = currentPropertyType;
    }

    public String getMainJson() {
        return mainJson;
    }

    public void setMainJson(String mainJson) {
        this.mainJson = mainJson;
    }

    public String getTrackerTime() {
        return TrackerTime;
    }

    public void setTrackerTime(String trackerTime) {
        TrackerTime = trackerTime;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLocationType() {
        return LocationType;
    }

    public void setLocationType(String locationType) {
        LocationType = locationType;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public String getAgencyId() {
        return AgencyId;
    }

    public void setAgencyId(String agencyId) {
        AgencyId = agencyId;
    }

    public String getCaseAdminId() {
        return CaseAdminId;
    }

    public void setCaseAdminId(String caseAdminId) {
        CaseAdminId = caseAdminId;
    }

    public String getReportMakerId() {
        return ReportMakerId;
    }

    public void setReportMakerId(String reportMakerId) {
        ReportMakerId = reportMakerId;
    }

    public String getAgencyBranchId() {
        return agencyBranchId;
    }

    public void setAgencyBranchId(String agencyBranchId) {
        this.agencyBranchId = agencyBranchId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getReportType() {
        return ReportType;
    }

    public void setReportType(String reportType) {
        ReportType = reportType;
    }


    public String getEmployeeRemarks() {
        return EmployeeRemarks;
    }

    public void setEmployeeRemarks(String employeeRemarks) {
        EmployeeRemarks = employeeRemarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /********
     * Insert Case Inspection on save or edit
     * *******/
    public JSONObject getCase() {
        return Case;
    }

    public void setCase(JSONObject aCase) {
        Case = aCase;
    }

    public JSONObject getProperty() {
        return Property;
    }

    public void setProperty(JSONObject property) {
        Property = property;
    }

    public JSONObject getIndProperty() {
        return IndProperty;
    }

    public void setIndProperty(JSONObject indProperty) {
        IndProperty = indProperty;
    }

    public JSONArray getIndPropertyFloors() {
        return IndPropertyFloors;
    }

    public void setIndPropertyFloors(JSONArray indPropertyFloors) {
        IndPropertyFloors = indPropertyFloors;
    }

    public JSONObject getIndPropertyValuation() {
        return IndPropertyValuation;
    }

    public void setIndPropertyValuation(JSONObject indPropertyValuation) {
        IndPropertyValuation = indPropertyValuation;
    }

    public JSONArray getIndPropertyFloorsValuation() {
        return IndPropertyFloorsValuation;
    }

    public void setIndPropertyFloorsValuation(JSONArray indPropertyFloorsValuation) {
        IndPropertyFloorsValuation = indPropertyFloorsValuation;
    }

    public JSONArray getProximity() {
        return Proximity;
    }

    public void setProximity(JSONArray proximity) {
        Proximity = proximity;
    }


    public JSONObject getInsertMainObj() {
        return insertMainObj;
    }


    public void setInsertMainObj(JSONObject insertMainObj) {
        this.insertMainObj = insertMainObj;
    }



    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(String propertyID) {
        this.propertyID = propertyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getProximityId() {
        return ProximityId;
    }

    public void setProximityId(String proximityId) {
        ProximityId = proximityId;
    }

    /******
     * Open & Closed case getter and setter items
     * *******/



    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        ModifiedBy = modifiedBy;
    }

    public String getInitQueryUrl() {
        return initQueryUrl;
    }

    public void setInitQueryUrl(String initQueryUrl) {
        this.initQueryUrl = initQueryUrl;
    }

    public String getCaseId() {
        return CaseId;
    }

    public void setCaseId(String caseId) {
        CaseId = caseId;
    }

    public String getApplicantName() {
        return ApplicantName;
    }

    public void setApplicantName(String applicantName) {
        ApplicantName = applicantName;
    }

    public String getApplicantContactNo() {
        return ApplicantContactNo;
    }

    public void setApplicantContactNo(String applicantContactNo) {
        ApplicantContactNo = applicantContactNo;
    }

    public String getPropertyAddress() {
        return PropertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        PropertyAddress = propertyAddress;
    }

    public String getContactPersonName() {
        return ContactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        ContactPersonName = contactPersonName;
    }

    public String getContactPersonNumber() {
        return ContactPersonNumber;
    }

    public void setContactPersonNumber(String contactPersonNumber) {
        ContactPersonNumber = contactPersonNumber;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getBankId() {
        return BankId;
    }

    public void setBankId(String bankId) {
        BankId = bankId;
    }

    public String getPropertyType() {
        return PropertyType;
    }

    public void setPropertyType(String propertyType) {
        PropertyType = propertyType;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String typeID) {
        TypeID = typeID;
    }

    public String getAssignedAt() {
        return AssignedAt;
    }

    public void setAssignedAt(String assignedAt) {
        AssignedAt = assignedAt;
    }

    public String getReportChecker() {
        return ReportChecker;
    }

    public void setReportChecker(String reportChecker) {
        ReportChecker = reportChecker;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getReportDispatcher() {
        return ReportDispatcher;
    }

    public void setReportDispatcher(String reportDispatcher) {
        ReportDispatcher = reportDispatcher;
    }

    public String getFieldStaff() {
        return FieldStaff;
    }

    public void setFieldStaff(String fieldStaff) {
        FieldStaff = fieldStaff;
    }

    public String getAssignedTo() {
        return AssignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        AssignedTo = assignedTo;
    }

    public String getReportMaker() {
        return ReportMaker;
    }

    public void setReportMaker(String reportMaker) {
        ReportMaker = reportMaker;
    }

    public String getReportFinalizer() {
        return ReportFinalizer;
    }

    public void setReportFinalizer(String reportFinalizer) {
        ReportFinalizer = reportFinalizer;
    }

    public String getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(String propertyId) {
        PropertyId = propertyId;
    }

    public String getReportFile() {
        return ReportFile;
    }

    public void setReportFile(String reportFile) {
        ReportFile = reportFile;
    }

    public String getReportId() {
        return ReportId;
    }

    public void setReportId(String reportId) {
        ReportId = reportId;
    }

    public String getReportTemplateId() {
        return ReportTemplateId;
    }

    public void setReportTemplateId(String reportTemplateId) {
        ReportTemplateId = reportTemplateId;
    }

    public String getSignature1() {
        return Signature1;
    }

    public void setSignature1(String signature1) {
        Signature1 = signature1;
    }

    public String getPropertyCategoryId() {
        return PropertyCategoryId;
    }

    public void setPropertyCategoryId(String propertyCategoryId) {
        PropertyCategoryId = propertyCategoryId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSignature2() {
        return Signature2;
    }

    public void setSignature2(String signature2) {
        Signature2 = signature2;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getStatusId() {
        return StatusId;
    }

    public void setStatusId(String statusId) {
        StatusId = statusId;
    }


    /******
     * Ended Open & Closed case getter and setter items
     * *******/

}
