
package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CaseModal")
public class Case {

    @PrimaryKey(autoGenerate = true)
    public int dummyID;

    @ColumnInfo(name = "CaseId")
    private int CaseId;
    @ColumnInfo(name = "AgencyBranchId")
    private int AgencyBranchId;
    @ColumnInfo(name = "BankBranchId")
    private int BankBranchId;
    @ColumnInfo(name = "PropertyId")
    private int PropertyId;
    @ColumnInfo(name = "CustomerId")
    private int CustomerId;
    @ColumnInfo(name = "FeesId")
    private int FeesId;
    @ColumnInfo(name = "WorkflowId")
    private String WorkflowId;
    @ColumnInfo(name = "ReportId")
    private int ReportId;
    @ColumnInfo(name = "ReportTemplateId")
    private int ReportTemplateId;
    @ColumnInfo(name = "AgencyBranch")
    private String AgencyBranch;
    @ColumnInfo(name = "ApplicantName")
    private String ApplicantName;
    @ColumnInfo(name = "ApplicantContactNo")
    private String ApplicantContactNo;
    @ColumnInfo(name = "ContactPersonName")
    private String ContactPersonName;
    @ColumnInfo(name = "ContactPersonNumber")
    private String ContactPersonNumber;
    @ColumnInfo(name = "BankContactPersonName")
    private String BankContactPersonName;
    @ColumnInfo(name = "BankContactPersonNumber")
    private String BankContactPersonNumber;
    @ColumnInfo(name = "BankContactPersonEmail")
    private String BankContactPersonEmail;
    @ColumnInfo(name = "LoanType")
    private String LoanType;
    @ColumnInfo(name = "BankReferenceNO")
    private String BankReferenceNO;
    @ColumnInfo(name = "ApplicantEmailId")
    private String ApplicantEmailId;
    @ColumnInfo(name = "ApplicantAddress")
    private String ApplicantAddress;
    @ColumnInfo(name = "ReportType")
    private int ReportType;
    @ColumnInfo(name = "ReportFile")
    private int ReportFile;
    @ColumnInfo(name = "BankName")
    private String BankName;
    @ColumnInfo(name = "BranchName")
    private String BranchName;
    @ColumnInfo(name = "PropertyAddress")
    private String PropertyAddress;
    @ColumnInfo(name = "Pincode")
    private int Pincode;
    @ColumnInfo(name = "PropertyType")
    private int PropertyType;
    @ColumnInfo(name = "ProfessionalFees")
    private String ProfessionalFees;
    @ColumnInfo(name = "PropertyCity")
    private int PropertyCity;
    @ColumnInfo(name = "PropertyLocality")
    private String PropertyLocality;
    @ColumnInfo(name = "VillageName")
    private String VillageName;
    @ColumnInfo(name = "District")
    private String District;
    @ColumnInfo(name = "Taluka")
    private String Taluka;
    @ColumnInfo(name = "Purpose")
    private String Purpose;
    @ColumnInfo(name = "TaxType")
    private String TaxType;
    @ColumnInfo(name = "Status")
    private int Status;
    @ColumnInfo(name = "AssignedTo")
    private int AssignedTo;
    @ColumnInfo(name = "AssignedBy")
    private int AssignedBy;
    @ColumnInfo(name = "AssignedAt")
    private String AssignedAt;
    @ColumnInfo(name = "AmountReceived")
    private int AmountReceived;
    @ColumnInfo(name = "FieldStaff")
    private int FieldStaff;
    @ColumnInfo(name = "ReportMaker")
    private int ReportMaker;
    @ColumnInfo(name = "ReportChecker")
    private String ReportChecker;
    @ColumnInfo(name = "ReportFinalizer")
    private String ReportFinalizer;
    @ColumnInfo(name = "ReportDispatcher")
    private String ReportDispatcher;
    @ColumnInfo(name = "Telecaller")
    private String Telecaller;
    @ColumnInfo(name = "CaseSourced")
    private String CaseSourced;
    @ColumnInfo(name = "SourcedBy")
    private String SourcedBy;
    @ColumnInfo(name = "GharvalueCity")
    private String GharvalueCity;
    @ColumnInfo(name = "GharvalueLocality")
    private String GharvalueLocality;
    @ColumnInfo(name = "GharvaluePropertyName")
    private String GharvaluePropertyName;
    @ColumnInfo(name = "CustomerFeedbackReceived")
    private String CustomerFeedbackReceived;
    @ColumnInfo(name = "BankFeedbackReceived")
    private String BankFeedbackReceived;
    @ColumnInfo(name = "AppointmentDateTime")
    private String AppointmentDateTime;
    @ColumnInfo(name = "SubmittedTo")
    private String SubmittedTo;
    @ColumnInfo(name = "CreatedOn")
    private String CreatedOn;
    @ColumnInfo(name = "CreatedBy")
    private int CreatedBy;
    @ColumnInfo(name = "ModifiedOn")
    private String ModifiedOn;
    @ColumnInfo(name = "ModifiedBy")
    private int ModifiedBy;
    @ColumnInfo(name = "Signature1")
    private String Signature1;
    @ColumnInfo(name = "Signature2")
    private String Signature2;
    @ColumnInfo(name = "AcceptanceTime")
    private float AcceptanceTime;
    @ColumnInfo(name = "InspectionTime")
    private String InspectionTime;









    public int getCaseId() {
        return CaseId;
    }

    public void setCaseId(int caseId) {
        CaseId = caseId;
    }

    public int getAgencyBranchId() {
        return AgencyBranchId;
    }

    public void setAgencyBranchId(int agencyBranchId) {
        AgencyBranchId = agencyBranchId;
    }

    public int getBankBranchId() {
        return BankBranchId;
    }

    public void setBankBranchId(int bankBranchId) {
        BankBranchId = bankBranchId;
    }

    public int getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(int propertyId) {
        PropertyId = propertyId;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public int getFeesId() {
        return FeesId;
    }

    public void setFeesId(int feesId) {
        FeesId = feesId;
    }

    public String getWorkflowId() {
        return WorkflowId;
    }

    public void setWorkflowId(String workflowId) {
        WorkflowId = workflowId;
    }

    public int getReportId() {
        return ReportId;
    }

    public void setReportId(int reportId) {
        ReportId = reportId;
    }

    public int getReportTemplateId() {
        return ReportTemplateId;
    }

    public void setReportTemplateId(int reportTemplateId) {
        ReportTemplateId = reportTemplateId;
    }

    public String getAgencyBranch() {
        return AgencyBranch;
    }

    public void setAgencyBranch(String agencyBranch) {
        AgencyBranch = agencyBranch;
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

    public String getBankContactPersonName() {
        return BankContactPersonName;
    }

    public void setBankContactPersonName(String bankContactPersonName) {
        BankContactPersonName = bankContactPersonName;
    }

    public String getBankContactPersonNumber() {
        return BankContactPersonNumber;
    }

    public void setBankContactPersonNumber(String bankContactPersonNumber) {
        BankContactPersonNumber = bankContactPersonNumber;
    }

    public String getBankContactPersonEmail() {
        return BankContactPersonEmail;
    }

    public void setBankContactPersonEmail(String bankContactPersonEmail) {
        BankContactPersonEmail = bankContactPersonEmail;
    }

    public String getLoanType() {
        return LoanType;
    }

    public void setLoanType(String loanType) {
        LoanType = loanType;
    }

    public String getBankReferenceNO() {
        return BankReferenceNO;
    }

    public void setBankReferenceNO(String bankReferenceNO) {
        BankReferenceNO = bankReferenceNO;
    }

    public String getApplicantEmailId() {
        return ApplicantEmailId;
    }

    public void setApplicantEmailId(String applicantEmailId) {
        ApplicantEmailId = applicantEmailId;
    }

    public String getApplicantAddress() {
        return ApplicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        ApplicantAddress = applicantAddress;
    }

    public int getReportType() {
        return ReportType;
    }

    public void setReportType(int reportType) {
        ReportType = reportType;
    }

    public int getReportFile() {
        return ReportFile;
    }

    public void setReportFile(int reportFile) {
        ReportFile = reportFile;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getPropertyAddress() {
        return PropertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        PropertyAddress = propertyAddress;
    }

    public int getPincode() {
        return Pincode;
    }

    public void setPincode(int pincode) {
        Pincode = pincode;
    }

    public int getPropertyType() {
        return PropertyType;
    }

    public void setPropertyType(int propertyType) {
        PropertyType = propertyType;
    }

    public String getProfessionalFees() {
        return ProfessionalFees;
    }

    public void setProfessionalFees(String professionalFees) {
        ProfessionalFees = professionalFees;
    }

    public int getPropertyCity() {
        return PropertyCity;
    }

    public void setPropertyCity(int propertyCity) {
        PropertyCity = propertyCity;
    }

    public String getPropertyLocality() {
        return PropertyLocality;
    }

    public void setPropertyLocality(String propertyLocality) {
        PropertyLocality = propertyLocality;
    }

    public String getVillageName() {
        return VillageName;
    }

    public void setVillageName(String villageName) {
        VillageName = villageName;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getTaluka() {
        return Taluka;
    }

    public void setTaluka(String taluka) {
        Taluka = taluka;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public String getTaxType() {
        return TaxType;
    }

    public void setTaxType(String taxType) {
        TaxType = taxType;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getAssignedTo() {
        return AssignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        AssignedTo = assignedTo;
    }

    public int getAssignedBy() {
        return AssignedBy;
    }

    public void setAssignedBy(int assignedBy) {
        AssignedBy = assignedBy;
    }

    public String getAssignedAt() {
        return AssignedAt;
    }

    public void setAssignedAt(String assignedAt) {
        AssignedAt = assignedAt;
    }

    public int getAmountReceived() {
        return AmountReceived;
    }

    public void setAmountReceived(int amountReceived) {
        AmountReceived = amountReceived;
    }

    public int getFieldStaff() {
        return FieldStaff;
    }

    public void setFieldStaff(int fieldStaff) {
        FieldStaff = fieldStaff;
    }

    public int getReportMaker() {
        return ReportMaker;
    }

    public void setReportMaker(int reportMaker) {
        ReportMaker = reportMaker;
    }

    public String getReportChecker() {
        return ReportChecker;
    }

    public void setReportChecker(String reportChecker) {
        ReportChecker = reportChecker;
    }

    public String getReportFinalizer() {
        return ReportFinalizer;
    }

    public void setReportFinalizer(String reportFinalizer) {
        ReportFinalizer = reportFinalizer;
    }

    public String getReportDispatcher() {
        return ReportDispatcher;
    }

    public void setReportDispatcher(String reportDispatcher) {
        ReportDispatcher = reportDispatcher;
    }

    public String getTelecaller() {
        return Telecaller;
    }

    public void setTelecaller(String telecaller) {
        Telecaller = telecaller;
    }

    public String getCaseSourced() {
        return CaseSourced;
    }

    public void setCaseSourced(String caseSourced) {
        CaseSourced = caseSourced;
    }

    public String getSourcedBy() {
        return SourcedBy;
    }

    public void setSourcedBy(String sourcedBy) {
        SourcedBy = sourcedBy;
    }

    public String getGharvalueCity() {
        return GharvalueCity;
    }

    public void setGharvalueCity(String gharvalueCity) {
        GharvalueCity = gharvalueCity;
    }

    public String getGharvalueLocality() {
        return GharvalueLocality;
    }

    public void setGharvalueLocality(String gharvalueLocality) {
        GharvalueLocality = gharvalueLocality;
    }

    public String getGharvaluePropertyName() {
        return GharvaluePropertyName;
    }

    public void setGharvaluePropertyName(String gharvaluePropertyName) {
        GharvaluePropertyName = gharvaluePropertyName;
    }

    public String getCustomerFeedbackReceived() {
        return CustomerFeedbackReceived;
    }

    public void setCustomerFeedbackReceived(String customerFeedbackReceived) {
        CustomerFeedbackReceived = customerFeedbackReceived;
    }

    public String getBankFeedbackReceived() {
        return BankFeedbackReceived;
    }

    public void setBankFeedbackReceived(String bankFeedbackReceived) {
        BankFeedbackReceived = bankFeedbackReceived;
    }

    public String getAppointmentDateTime() {
        return AppointmentDateTime;
    }

    public void setAppointmentDateTime(String appointmentDateTime) {
        AppointmentDateTime = appointmentDateTime;
    }

    public String getSubmittedTo() {
        return SubmittedTo;
    }

    public void setSubmittedTo(String submittedTo) {
        SubmittedTo = submittedTo;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public int getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(int createdBy) {
        CreatedBy = createdBy;
    }

    public String getModifiedOn() {
        return ModifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        ModifiedOn = modifiedOn;
    }

    public int getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        ModifiedBy = modifiedBy;
    }

    public String getSignature1() {
        return Signature1;
    }

    public void setSignature1(String signature1) {
        Signature1 = signature1;
    }

    public String getSignature2() {
        return Signature2;
    }

    public void setSignature2(String signature2) {
        Signature2 = signature2;
    }

    public float getAcceptanceTime() {
        return AcceptanceTime;
    }

    public void setAcceptanceTime(float acceptanceTime) {
        AcceptanceTime = acceptanceTime;
    }

    public String getInspectionTime() {
        return InspectionTime;
    }

    public void setInspectionTime(String inspectionTime) {
        InspectionTime = inspectionTime;
    }
}
