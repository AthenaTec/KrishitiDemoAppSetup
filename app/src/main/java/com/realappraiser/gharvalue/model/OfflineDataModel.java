package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by kaptas on 24/2/18.
 */

@SuppressWarnings("ALL")
@Entity(tableName = "OfflineDataModel")
public class OfflineDataModel {

    @PrimaryKey(autoGenerate = true)
    public int dummyID;

    @ColumnInfo(name = "CaseId")
    private String CaseId = "";
    @ColumnInfo(name = "_id")
    public String _id = "";
    @ColumnInfo(name = "IsExternal")
    public String IsExternal = "";
    @ColumnInfo(name = "OTP")
    public String OTP = "";
    @ColumnInfo(name = "Email")
    public String Email = "";
    @ColumnInfo(name = "LoginId")
    public String LoginId = "";
    @ColumnInfo(name = "EmployeeId")
    public String EmployeeId = "";
    @ColumnInfo(name = "FirstName")
    public String FirstName = "";
    @ColumnInfo(name = "RoleId")
    public String RoleId = "";
    @ColumnInfo(name = "LastName")
    public String LastName = "";
    @ColumnInfo(name = "Mobile")
    public String Mobile = "";
    @ColumnInfo(name = "AgencyId")
    public String AgencyId = "";
    @ColumnInfo(name = "BranchId")
    public String BranchId = "";
    @ColumnInfo(name = "AgencyName")
    public String AgencyName = "";
    @ColumnInfo(name = "CityName")
    public String CityName = "";
    @ColumnInfo(name = "AgencyCode")
    public String AgencyCode = "";
    @ColumnInfo(name = "RoleDescription")
    public String RoleDescription = "";
    @ColumnInfo(name = "empId")
    private String empId = "";
    @ColumnInfo(name = "startDate")
    private String startDate = "";
    @ColumnInfo(name = "endDate")
    private String endDate = "";
    @ColumnInfo(name = "initQueryUrl")
    private String initQueryUrl = "";
    @ColumnInfo(name = "ApplicantName")
    private String ApplicantName = "";
    @ColumnInfo(name = "ApplicantContactNo")
    private String ApplicantContactNo = "";
    @ColumnInfo(name = "PropertyAddress")
    private String PropertyAddress = "";
    @ColumnInfo(name = "ContactPersonName")
    private String ContactPersonName = "";
    @ColumnInfo(name = "ContactPersonNumber")
    private String ContactPersonNumber = "";
    @ColumnInfo(name = "BankName")
    private String BankName = "";
    @ColumnInfo(name = "BankId")
    private String BankId = "";
    @ColumnInfo(name = "PropertyType")
    private String PropertyType = "";
    @ColumnInfo(name = "TypeID")
    private String TypeID = "";
    @ColumnInfo(name = "AssignedAt")
    private String AssignedAt = "";
    @ColumnInfo(name = "CreatedOn")
    private String CreatedOn = "";
    @ColumnInfo(name = "ReportChecker")
    private String ReportChecker = "";
    @ColumnInfo(name = "Status")
    private String Status = "";
    @ColumnInfo(name = "ReportDispatcher")
    private String ReportDispatcher = "";
    @ColumnInfo(name = "FieldStaff")
    private String FieldStaff = "";
    @ColumnInfo(name = "AssignedTo")
    private String AssignedTo = "";
    @ColumnInfo(name = "ReportMaker")
    private String ReportMaker = "";
    @ColumnInfo(name = "ReportFinalizer")
    private String ReportFinalizer = "";
    @ColumnInfo(name = "PropertyId")
    private String PropertyId = "";
    @ColumnInfo(name = "ReportFile")
    private String ReportFile = "";
    @ColumnInfo(name = "ReportId")
    private String ReportId = "";
    @ColumnInfo(name = "ReportTemplateId")
    private String ReportTemplateId = "";
    @ColumnInfo(name = "Signature1")
    private String Signature1 = "";
    @ColumnInfo(name = "PropertyCategoryId")
    private String PropertyCategoryId = "";
    @ColumnInfo(name = "count")
    private String count = "";
    @ColumnInfo(name = "Signature2")
    private String Signature2 = "";
    @ColumnInfo(name = "EmployeeName")
    private String EmployeeName = "";
    @ColumnInfo(name = "Role")
    private String Role = "";
    @ColumnInfo(name = "StatusId")
    private String StatusId = "";
    @ColumnInfo(name = "TypeDescription")
    private String TypeDescription = "";
    @ColumnInfo(name = "Name")
    private String Name = "";
    @ColumnInfo(name = "TypeOfPropertyId")
    private String TypeOfPropertyId = "";
    @ColumnInfo(name = "Id")
    private String Id = "";
    @ColumnInfo(name = "DocumentName")
    private String DocumentName = "";
    @ColumnInfo(name = "VisibleToFieldStaff")
    private String VisibleToFieldStaff = "";
    @ColumnInfo(name = "Title")
    private String Title = "";
    @ColumnInfo(name = "Document")
    private String Document = "";
    @ColumnInfo(name = "BankBranchName")
    private String BankBranchName = "";
    @ColumnInfo(name = "AgencyBranchId")
    private String AgencyBranchId = "";


    @ColumnInfo(name = "opencase")
    public boolean opencase = false;

    @ColumnInfo(name = "offlinecase")
    public boolean offlinecase = false;

    @ColumnInfo(name = "showoffline")
    public boolean showoffline = false;

    @ColumnInfo(name = "current_offline")
    public boolean current_offline = false;


    @ColumnInfo(name = "updatecasestatus")
    public String updatecasestatus = "";

    @ColumnInfo(name = "rejectmessage")
    public String rejectmessage = "";



    @ColumnInfo(name = "is_property_changed")
    public boolean is_property_changed = false;
    @ColumnInfo(name = "typeof_PropertyCategoryId_selected")
    private String typeof_PropertyCategoryId_selected = "";

    @ColumnInfo(name = "sync_edit")
    public boolean sync_edit = false;

    @ColumnInfo(name = "ReportName")
    private String ReportName="";

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getReportName() {
        return ReportName;
    }

    public void setReportName(String reportName) {
        ReportName = reportName;
    }

    public boolean isSync_edit() {
        return sync_edit;
    }

    public void setSync_edit(boolean sync_edit) {
        this.sync_edit = sync_edit;
    }

    public boolean isIs_property_changed() {
        return is_property_changed;
    }

    public void setIs_property_changed(boolean is_property_changed) {
        this.is_property_changed = is_property_changed;
    }

    public String getTypeof_PropertyCategoryId_selected() {
        return typeof_PropertyCategoryId_selected;
    }

    public void setTypeof_PropertyCategoryId_selected(String typeof_PropertyCategoryId_selected) {
        this.typeof_PropertyCategoryId_selected = typeof_PropertyCategoryId_selected;
    }

    public boolean isCurrent_offline() {
        return current_offline;
    }

    public void setCurrent_offline(boolean current_offline) {
        this.current_offline = current_offline;
    }


    public boolean isOpencase() {
        return opencase;
    }

    public void setOpencase(boolean opencase) {
        this.opencase = opencase;
    }

    public boolean isOfflinecase() {
        return offlinecase;
    }

    public void setOfflinecase(boolean offlinecase) {
        this.offlinecase = offlinecase;
    }

    public boolean isShowoffline() {
        return showoffline;
    }

    public void setShowoffline(boolean showoffline) {
        this.showoffline = showoffline;
    }

    public String getUpdatecasestatus() {
        return updatecasestatus;
    }

    public void setUpdatecasestatus(String updatecasestatus) {
        this.updatecasestatus = updatecasestatus;
    }

    public String getRejectmessage() {
        return rejectmessage;
    }

    public void setRejectmessage(String rejectmessage) {
        this.rejectmessage = rejectmessage;
    }

    /*******
     * Getter and Setter Methods
     * ******/
    public String getAgencyBranchId() {
        return AgencyBranchId;
    }

    public void setAgencyBranchId(String agencyBranchId) {
        AgencyBranchId = agencyBranchId;
    }

    public String getBankBranchName() {
        return BankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        BankBranchName = bankBranchName;
    }


    public String getTypeDescription() {
        return TypeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        TypeDescription = typeDescription;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIsExternal() {
        return IsExternal;
    }

    public void setIsExternal(String isExternal) {
        IsExternal = isExternal;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAgencyId() {
        return AgencyId;
    }

    public void setAgencyId(String agencyId) {
        AgencyId = agencyId;
    }

    public String getBranchId() {
        return BranchId;
    }

    public void setBranchId(String branchId) {
        BranchId = branchId;
    }

    public String getAgencyName() {
        return AgencyName;
    }

    public void setAgencyName(String agencyName) {
        AgencyName = agencyName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getAgencyCode() {
        return AgencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        AgencyCode = agencyCode;
    }

    public String getRoleDescription() {
        return RoleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        RoleDescription = roleDescription;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTypeOfPropertyId() {
        return TypeOfPropertyId;
    }

    public void setTypeOfPropertyId(String typeOfPropertyId) {
        TypeOfPropertyId = typeOfPropertyId;
    }

    /*******
     * Open Case List items
     * *******/
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

    /*******
     * Ended Open Case List items
     * *******/

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDocumentName() {
        return DocumentName;
    }

    public void setDocumentName(String documentName) {
        DocumentName = documentName;
    }

    public String getVisibleToFieldStaff() {
        return VisibleToFieldStaff;
    }

    public void setVisibleToFieldStaff(String visibleToFieldStaff) {
        VisibleToFieldStaff = visibleToFieldStaff;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }
}

