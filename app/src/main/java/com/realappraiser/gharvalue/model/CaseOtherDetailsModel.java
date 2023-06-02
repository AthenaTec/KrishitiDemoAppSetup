package com.realappraiser.gharvalue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by P.Prabhakaran on 21,April,2019
 **/
public class CaseOtherDetailsModel {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public class Datum {

        @SerializedName("AgencyBranch")
        @Expose
        private Object agencyBranch;
        @SerializedName("AgencyBranchId")
        @Expose
        private Integer agencyBranchId;
        @SerializedName("CaseId")
        @Expose
        private Integer caseId;
        @SerializedName("BankBranchId")
        @Expose
        private Integer bankBranchId;
        @SerializedName("PropertyId")
        @Expose
        private Integer propertyId;
        @SerializedName("CustomerId")
        @Expose
        private Integer customerId;
        @SerializedName("FeesId")
        @Expose
        private Integer feesId;
        @SerializedName("Pincode")
        @Expose
        private Object pincode;
        @SerializedName("WorkflowId")
        @Expose
        private Object workflowId;
        @SerializedName("ReportId")
        @Expose
        private Integer reportId;
        @SerializedName("ApplicantName")
        @Expose
        private String applicantName;
        @SerializedName("ApplicantContactNo")
        @Expose
        private Object applicantContactNo;
        @SerializedName("ContactPersonName")
        @Expose
        private Object contactPersonName;
        @SerializedName("ContactPersonNumber")
        @Expose
        private Object contactPersonNumber;
        @SerializedName("ApplicantEmailId")
        @Expose
        private Object applicantEmailId;
        @SerializedName("BankContactPersonEmail")
        @Expose
        private Object bankContactPersonEmail;
        @SerializedName("BankContactPersonName")
        @Expose
        private Object bankContactPersonName;
        @SerializedName("BankContactPersonNumber")
        @Expose
        private Object bankContactPersonNumber;
        @SerializedName("LoanType")
        @Expose
        private Object loanType;
        @SerializedName("BankReferenceNO")
        @Expose
        private Object bankReferenceNO;
        @SerializedName("ReportType")
        @Expose
        private Integer reportType;
        @SerializedName("BankName")
        @Expose
        private Object bankName;
        @SerializedName("BranchName")
        @Expose
        private Object branchName;
        @SerializedName("PropertyAddress")
        @Expose
        private String propertyAddress;
        @SerializedName("PropertyType")
        @Expose
        private Integer propertyType;
        @SerializedName("TypeOfPropertyName")
        @Expose
        private String typeOfPropertyName;
        @SerializedName("ProfessionalFees")
        @Expose
        private Object professionalFees;
        @SerializedName("TaxType")
        @Expose
        private Object taxType;
        @SerializedName("Status")
        @Expose
        private Integer status;
        @SerializedName("AssignedTo")
        @Expose
        private Integer assignedTo;
        @SerializedName("AssignedBy")
        @Expose
        private Integer assignedBy;
        @SerializedName("AssignedAt")
        @Expose
        private String assignedAt;
        @SerializedName("AmountReceived")
        @Expose
        private Integer amountReceived;
        @SerializedName("FieldStaff")
        @Expose
        private Integer fieldStaff;
        @SerializedName("ReportMaker")
        @Expose
        private Integer reportMaker;
        @SerializedName("ReportChecker")
        @Expose
        private Object reportChecker;
        @SerializedName("ReportFinalizer")
        @Expose
        private Object reportFinalizer;
        @SerializedName("ReportDispatcher")
        @Expose
        private Object reportDispatcher;
        @SerializedName("Telecaller")
        @Expose
        private Object telecaller;
        @SerializedName("CaseSourced")
        @Expose
        private Object caseSourced;
        @SerializedName("SourcedBy")
        @Expose
        private Object sourcedBy;
        @SerializedName("GharvalueCity")
        @Expose
        private Object gharvalueCity;
        @SerializedName("GharvalueLocality")
        @Expose
        private Object gharvalueLocality;
        @SerializedName("GharvaluePropertyName")
        @Expose
        private Object gharvaluePropertyName;
        @SerializedName("CustomerFeedbackReceived")
        @Expose
        private Object customerFeedbackReceived;
        @SerializedName("BankFeedbackReceived")
        @Expose
        private Object bankFeedbackReceived;
        @SerializedName("AppointmentDateTime")
        @Expose
        private String appointmentDateTime;
        @SerializedName("ApplicantAddress")
        @Expose
        private String applicantAddress;
        @SerializedName("PropertyCity")
        @Expose
        private Integer propertyCity;
        @SerializedName("PropertyLocality")
        @Expose
        private Object propertyLocality;
        @SerializedName("Purpose")
        @Expose
        private Object purpose;
        @SerializedName("CreatedOn")
        @Expose
        private String createdOn;
        @SerializedName("CreatedBy")
        @Expose
        private Integer createdBy;
        @SerializedName("ModifiedOn")
        @Expose
        private String modifiedOn;
        @SerializedName("SubmittedTo")
        @Expose
        private Object submittedTo;
        @SerializedName("ModifiedBy")
        @Expose
        private Integer modifiedBy;
        @SerializedName("Signature1")
        @Expose
        private Object signature1;
        @SerializedName("Signature2")
        @Expose
        private Object signature2;
        @SerializedName("ReportTemplateId")
        @Expose
        private Integer reportTemplateId;
        @SerializedName("ReportFile")
        @Expose
        private Integer reportFile;
        @SerializedName("FooterReferenceNumber")
        @Expose
        private Object footerReferenceNumber;
        @SerializedName("CaseReferenceNumber")
        @Expose
        private Object caseReferenceNumber;
        @SerializedName("PropertyCategoryId")
        @Expose
        private Integer propertyCategoryId;
        @SerializedName("BankId")
        @Expose
        private Integer bankId;
        @SerializedName("VillageName")
        @Expose
        private String villageName;
        @SerializedName("Taluka")
        @Expose
        private String taluka;
        @SerializedName("District")
        @Expose
        private String district;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("BranchNameDetails")
        @Expose
        private String branchNameDetails;
        @SerializedName("IsSourcedByExternal")
        @Expose
        private Integer isSourcedByExternal;
        @SerializedName("IsReportFinalizerExternal")
        @Expose
        private Integer isReportFinalizerExternal;
        @SerializedName("NameOfPurchaser")
        @Expose
        private String nameOfPurchaser;
        @SerializedName("NameOfSeller")
        @Expose
        private String nameOfSeller;
        @SerializedName("DocBoundryEast")
        @Expose
        private Object docBoundryEast;
        @SerializedName("DocBoundryWest")
        @Expose
        private Object docBoundryWest;
        @SerializedName("DocBoundryNorth")
        @Expose
        private Object docBoundryNorth;
        @SerializedName("DocBoundrySouth")
        @Expose
        private Object docBoundrySouth;
        @SerializedName("DocEastMeasure")
        @Expose
        private Object docEastMeasure;
        @SerializedName("DocWestMeasure")
        @Expose
        private Object docWestMeasure;
        @SerializedName("DocNorthMeasure")
        @Expose
        private Object docNorthMeasure;
        @SerializedName("DocSouthMeasure")
        @Expose
        private Object docSouthMeasure;
        @SerializedName("DocSetBackFront")
        @Expose
        private Object docSetBackFront;
        @SerializedName("DocSetBackLeft")
        @Expose
        private Object docSetBackLeft;
        @SerializedName("DocSetBackRight")
        @Expose
        private Object docSetBackRight;
        @SerializedName("DocSetBackRear")
        @Expose
        private Object docSetBackRear;
        @SerializedName("PlotNo")
        @Expose
        private Object plotNo;
        @SerializedName("TypeOfLandId")
        @Expose
        private Object typeOfLandId;
        @SerializedName("TenureId")
        @Expose
        private Object tenureId;
        @SerializedName("OtherDocuments")
        @Expose
        private Object otherDocuments;
        @SerializedName("SurveyNo")
        @Expose
        private Object surveyNo;
        @SerializedName("CtsNo")
        @Expose
        private Object ctsNo;
        @SerializedName("NameofVendorId")
        @Expose
        private Object nameofVendorId;
        @SerializedName("NameofVendor")
        @Expose
        private Object nameofVendor;
        @SerializedName("ReportTypeName")
        @Expose
        private String reportTypeName;
        @SerializedName("AgeCheckbox")
        @Expose
        private String ageCheckbox;
        @SerializedName("AreaUnit")
        @Expose
        private String areaUnit;
        @SerializedName("DistressPercentage")
        @Expose
        private String distressPercentage;
        @SerializedName("RealizationPercentage")
        @Expose
        private String realizationPercentage;
        @SerializedName("PercentageDepreciation")
        @Expose
        private String percentageDepreciation;

        public Object getAgencyBranch() {
            return agencyBranch;
        }

        public void setAgencyBranch(Object agencyBranch) {
            this.agencyBranch = agencyBranch;
        }

        public Integer getAgencyBranchId() {
            return agencyBranchId;
        }

        public void setAgencyBranchId(Integer agencyBranchId) {
            this.agencyBranchId = agencyBranchId;
        }

        public Integer getCaseId() {
            return caseId;
        }

        public void setCaseId(Integer caseId) {
            this.caseId = caseId;
        }

        public Integer getBankBranchId() {
            return bankBranchId;
        }

        public void setBankBranchId(Integer bankBranchId) {
            this.bankBranchId = bankBranchId;
        }

        public Integer getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(Integer propertyId) {
            this.propertyId = propertyId;
        }

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public Integer getFeesId() {
            return feesId;
        }

        public void setFeesId(Integer feesId) {
            this.feesId = feesId;
        }

        public Object getPincode() {
            return pincode;
        }

        public void setPincode(Object pincode) {
            this.pincode = pincode;
        }

        public Object getWorkflowId() {
            return workflowId;
        }

        public void setWorkflowId(Object workflowId) {
            this.workflowId = workflowId;
        }

        public Integer getReportId() {
            return reportId;
        }

        public void setReportId(Integer reportId) {
            this.reportId = reportId;
        }

        public String getApplicantName() {
            return applicantName;
        }

        public void setApplicantName(String applicantName) {
            this.applicantName = applicantName;
        }

        public Object getApplicantContactNo() {
            return applicantContactNo;
        }

        public void setApplicantContactNo(Object applicantContactNo) {
            this.applicantContactNo = applicantContactNo;
        }

        public Object getContactPersonName() {
            return contactPersonName;
        }

        public void setContactPersonName(Object contactPersonName) {
            this.contactPersonName = contactPersonName;
        }

        public Object getContactPersonNumber() {
            return contactPersonNumber;
        }

        public void setContactPersonNumber(Object contactPersonNumber) {
            this.contactPersonNumber = contactPersonNumber;
        }

        public Object getApplicantEmailId() {
            return applicantEmailId;
        }

        public void setApplicantEmailId(Object applicantEmailId) {
            this.applicantEmailId = applicantEmailId;
        }

        public Object getBankContactPersonEmail() {
            return bankContactPersonEmail;
        }

        public void setBankContactPersonEmail(Object bankContactPersonEmail) {
            this.bankContactPersonEmail = bankContactPersonEmail;
        }

        public Object getBankContactPersonName() {
            return bankContactPersonName;
        }

        public void setBankContactPersonName(Object bankContactPersonName) {
            this.bankContactPersonName = bankContactPersonName;
        }

        public Object getBankContactPersonNumber() {
            return bankContactPersonNumber;
        }

        public void setBankContactPersonNumber(Object bankContactPersonNumber) {
            this.bankContactPersonNumber = bankContactPersonNumber;
        }

        public Object getLoanType() {
            return loanType;
        }

        public void setLoanType(Object loanType) {
            this.loanType = loanType;
        }

        public Object getBankReferenceNO() {
            return bankReferenceNO;
        }

        public void setBankReferenceNO(Object bankReferenceNO) {
            this.bankReferenceNO = bankReferenceNO;
        }

        public Integer getReportType() {
            return reportType;
        }

        public void setReportType(Integer reportType) {
            this.reportType = reportType;
        }

        public Object getBankName() {
            return bankName;
        }

        public void setBankName(Object bankName) {
            this.bankName = bankName;
        }

        public Object getBranchName() {
            return branchName;
        }

        public void setBranchName(Object branchName) {
            this.branchName = branchName;
        }

        public String getPropertyAddress() {
            return propertyAddress;
        }

        public void setPropertyAddress(String propertyAddress) {
            this.propertyAddress = propertyAddress;
        }

        public Integer getPropertyType() {
            return propertyType;
        }

        public void setPropertyType(Integer propertyType) {
            this.propertyType = propertyType;
        }

        public String getTypeOfPropertyName() {
            return typeOfPropertyName;
        }

        public void setTypeOfPropertyName(String typeOfPropertyName) {
            this.typeOfPropertyName = typeOfPropertyName;
        }

        public Object getProfessionalFees() {
            return professionalFees;
        }

        public void setProfessionalFees(Object professionalFees) {
            this.professionalFees = professionalFees;
        }

        public Object getTaxType() {
            return taxType;
        }

        public void setTaxType(Object taxType) {
            this.taxType = taxType;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getAssignedTo() {
            return assignedTo;
        }

        public void setAssignedTo(Integer assignedTo) {
            this.assignedTo = assignedTo;
        }

        public Integer getAssignedBy() {
            return assignedBy;
        }

        public void setAssignedBy(Integer assignedBy) {
            this.assignedBy = assignedBy;
        }

        public String getAssignedAt() {
            return assignedAt;
        }

        public void setAssignedAt(String assignedAt) {
            this.assignedAt = assignedAt;
        }

        public Integer getAmountReceived() {
            return amountReceived;
        }

        public void setAmountReceived(Integer amountReceived) {
            this.amountReceived = amountReceived;
        }

        public Integer getFieldStaff() {
            return fieldStaff;
        }

        public void setFieldStaff(Integer fieldStaff) {
            this.fieldStaff = fieldStaff;
        }

        public Integer getReportMaker() {
            return reportMaker;
        }

        public void setReportMaker(Integer reportMaker) {
            this.reportMaker = reportMaker;
        }

        public Object getReportChecker() {
            return reportChecker;
        }

        public void setReportChecker(Object reportChecker) {
            this.reportChecker = reportChecker;
        }

        public Object getReportFinalizer() {
            return reportFinalizer;
        }

        public void setReportFinalizer(Object reportFinalizer) {
            this.reportFinalizer = reportFinalizer;
        }

        public Object getReportDispatcher() {
            return reportDispatcher;
        }

        public void setReportDispatcher(Object reportDispatcher) {
            this.reportDispatcher = reportDispatcher;
        }

        public Object getTelecaller() {
            return telecaller;
        }

        public void setTelecaller(Object telecaller) {
            this.telecaller = telecaller;
        }

        public Object getCaseSourced() {
            return caseSourced;
        }

        public void setCaseSourced(Object caseSourced) {
            this.caseSourced = caseSourced;
        }

        public Object getSourcedBy() {
            return sourcedBy;
        }

        public void setSourcedBy(Object sourcedBy) {
            this.sourcedBy = sourcedBy;
        }

        public Object getGharvalueCity() {
            return gharvalueCity;
        }

        public void setGharvalueCity(Object gharvalueCity) {
            this.gharvalueCity = gharvalueCity;
        }

        public Object getGharvalueLocality() {
            return gharvalueLocality;
        }

        public void setGharvalueLocality(Object gharvalueLocality) {
            this.gharvalueLocality = gharvalueLocality;
        }

        public Object getGharvaluePropertyName() {
            return gharvaluePropertyName;
        }

        public void setGharvaluePropertyName(Object gharvaluePropertyName) {
            this.gharvaluePropertyName = gharvaluePropertyName;
        }

        public Object getCustomerFeedbackReceived() {
            return customerFeedbackReceived;
        }

        public void setCustomerFeedbackReceived(Object customerFeedbackReceived) {
            this.customerFeedbackReceived = customerFeedbackReceived;
        }

        public Object getBankFeedbackReceived() {
            return bankFeedbackReceived;
        }

        public void setBankFeedbackReceived(Object bankFeedbackReceived) {
            this.bankFeedbackReceived = bankFeedbackReceived;
        }

        public String getAppointmentDateTime() {
            return appointmentDateTime;
        }

        public void setAppointmentDateTime(String appointmentDateTime) {
            this.appointmentDateTime = appointmentDateTime;
        }

        public String getApplicantAddress() {
            return applicantAddress;
        }

        public void setApplicantAddress(String applicantAddress) {
            this.applicantAddress = applicantAddress;
        }

        public Integer getPropertyCity() {
            return propertyCity;
        }

        public void setPropertyCity(Integer propertyCity) {
            this.propertyCity = propertyCity;
        }

        public Object getPropertyLocality() {
            return propertyLocality;
        }

        public void setPropertyLocality(Object propertyLocality) {
            this.propertyLocality = propertyLocality;
        }

        public Object getPurpose() {
            return purpose;
        }

        public void setPurpose(Object purpose) {
            this.purpose = purpose;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }

        public Integer getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Integer createdBy) {
            this.createdBy = createdBy;
        }

        public String getModifiedOn() {
            return modifiedOn;
        }

        public void setModifiedOn(String modifiedOn) {
            this.modifiedOn = modifiedOn;
        }

        public Object getSubmittedTo() {
            return submittedTo;
        }

        public void setSubmittedTo(Object submittedTo) {
            this.submittedTo = submittedTo;
        }

        public Integer getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(Integer modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public Object getSignature1() {
            return signature1;
        }

        public void setSignature1(Object signature1) {
            this.signature1 = signature1;
        }

        public Object getSignature2() {
            return signature2;
        }

        public void setSignature2(Object signature2) {
            this.signature2 = signature2;
        }

        public Integer getReportTemplateId() {
            return reportTemplateId;
        }

        public void setReportTemplateId(Integer reportTemplateId) {
            this.reportTemplateId = reportTemplateId;
        }

        public Integer getReportFile() {
            return reportFile;
        }

        public void setReportFile(Integer reportFile) {
            this.reportFile = reportFile;
        }

        public Object getFooterReferenceNumber() {
            return footerReferenceNumber;
        }

        public void setFooterReferenceNumber(Object footerReferenceNumber) {
            this.footerReferenceNumber = footerReferenceNumber;
        }

        public Object getCaseReferenceNumber() {
            return caseReferenceNumber;
        }

        public void setCaseReferenceNumber(Object caseReferenceNumber) {
            this.caseReferenceNumber = caseReferenceNumber;
        }

        public Integer getPropertyCategoryId() {
            return propertyCategoryId;
        }

        public void setPropertyCategoryId(Integer propertyCategoryId) {
            this.propertyCategoryId = propertyCategoryId;
        }

        public Integer getBankId() {
            return bankId;
        }

        public void setBankId(Integer bankId) {
            this.bankId = bankId;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public String getTaluka() {
            return taluka;
        }

        public void setTaluka(String taluka) {
            this.taluka = taluka;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBranchNameDetails() {
            return branchNameDetails;
        }

        public void setBranchNameDetails(String branchNameDetails) {
            this.branchNameDetails = branchNameDetails;
        }

        public Integer getIsSourcedByExternal() {
            return isSourcedByExternal;
        }

        public void setIsSourcedByExternal(Integer isSourcedByExternal) {
            this.isSourcedByExternal = isSourcedByExternal;
        }

        public Integer getIsReportFinalizerExternal() {
            return isReportFinalizerExternal;
        }

        public void setIsReportFinalizerExternal(Integer isReportFinalizerExternal) {
            this.isReportFinalizerExternal = isReportFinalizerExternal;
        }

        public String getNameOfPurchaser() {
            return nameOfPurchaser;
        }

        public void setNameOfPurchaser(String nameOfPurchaser) {
            this.nameOfPurchaser = nameOfPurchaser;
        }

        public String getNameOfSeller() {
            return nameOfSeller;
        }

        public void setNameOfSeller(String nameOfSeller) {
            this.nameOfSeller = nameOfSeller;
        }

        public Object getDocBoundryEast() {
            return docBoundryEast;
        }

        public void setDocBoundryEast(Object docBoundryEast) {
            this.docBoundryEast = docBoundryEast;
        }

        public Object getDocBoundryWest() {
            return docBoundryWest;
        }

        public void setDocBoundryWest(Object docBoundryWest) {
            this.docBoundryWest = docBoundryWest;
        }

        public Object getDocBoundryNorth() {
            return docBoundryNorth;
        }

        public void setDocBoundryNorth(Object docBoundryNorth) {
            this.docBoundryNorth = docBoundryNorth;
        }

        public Object getDocBoundrySouth() {
            return docBoundrySouth;
        }

        public void setDocBoundrySouth(Object docBoundrySouth) {
            this.docBoundrySouth = docBoundrySouth;
        }

        public Object getDocEastMeasure() {
            return docEastMeasure;
        }

        public void setDocEastMeasure(Object docEastMeasure) {
            this.docEastMeasure = docEastMeasure;
        }

        public Object getDocWestMeasure() {
            return docWestMeasure;
        }

        public void setDocWestMeasure(Object docWestMeasure) {
            this.docWestMeasure = docWestMeasure;
        }

        public Object getDocNorthMeasure() {
            return docNorthMeasure;
        }

        public void setDocNorthMeasure(Object docNorthMeasure) {
            this.docNorthMeasure = docNorthMeasure;
        }

        public Object getDocSouthMeasure() {
            return docSouthMeasure;
        }

        public void setDocSouthMeasure(Object docSouthMeasure) {
            this.docSouthMeasure = docSouthMeasure;
        }

        public Object getDocSetBackFront() {
            return docSetBackFront;
        }

        public void setDocSetBackFront(Object docSetBackFront) {
            this.docSetBackFront = docSetBackFront;
        }

        public Object getDocSetBackLeft() {
            return docSetBackLeft;
        }

        public void setDocSetBackLeft(Object docSetBackLeft) {
            this.docSetBackLeft = docSetBackLeft;
        }

        public Object getDocSetBackRight() {
            return docSetBackRight;
        }

        public void setDocSetBackRight(Object docSetBackRight) {
            this.docSetBackRight = docSetBackRight;
        }

        public Object getDocSetBackRear() {
            return docSetBackRear;
        }

        public void setDocSetBackRear(Object docSetBackRear) {
            this.docSetBackRear = docSetBackRear;
        }

        public Object getPlotNo() {
            return plotNo;
        }

        public void setPlotNo(Object plotNo) {
            this.plotNo = plotNo;
        }

        public Object getTypeOfLandId() {
            return typeOfLandId;
        }

        public void setTypeOfLandId(Object typeOfLandId) {
            this.typeOfLandId = typeOfLandId;
        }

        public Object getTenureId() {
            return tenureId;
        }

        public void setTenureId(Object tenureId) {
            this.tenureId = tenureId;
        }

        public Object getOtherDocuments() {
            return otherDocuments;
        }

        public void setOtherDocuments(Object otherDocuments) {
            this.otherDocuments = otherDocuments;
        }

        public Object getSurveyNo() {
            return surveyNo;
        }

        public void setSurveyNo(Object surveyNo) {
            this.surveyNo = surveyNo;
        }

        public Object getCtsNo() {
            return ctsNo;
        }

        public void setCtsNo(Object ctsNo) {
            this.ctsNo = ctsNo;
        }

        public Object getNameofVendorId() {
            return nameofVendorId;
        }

        public void setNameofVendorId(Object nameofVendorId) {
            this.nameofVendorId = nameofVendorId;
        }

        public Object getNameofVendor() {
            return nameofVendor;
        }

        public void setNameofVendor(Object nameofVendor) {
            this.nameofVendor = nameofVendor;
        }

        public String getReportTypeName() {
            return reportTypeName;
        }

        public void setReportTypeName(String reportTypeName) {
            this.reportTypeName = reportTypeName;
        }

        public String getAgeCheckbox() {
            return ageCheckbox;
        }

        public void setAgeCheckbox(String ageCheckbox) {
            this.ageCheckbox = ageCheckbox;
        }

        public String getAreaUnit() {
            return areaUnit;
        }

        public void setAreaUnit(String areaUnit) {
            this.areaUnit = areaUnit;
        }

        public String getDistressPercentage() {
            return distressPercentage;
        }

        public void setDistressPercentage(String distressPercentage) {
            this.distressPercentage = distressPercentage;
        }

        public String getRealizationPercentage() {
            return realizationPercentage;
        }

        public void setRealizationPercentage(String realizationPercentage) {
            this.realizationPercentage = realizationPercentage;
        }

        public String getPercentageDepreciation() {
            return percentageDepreciation;
        }

        public void setPercentageDepreciation(String percentageDepreciation) {
            this.percentageDepreciation = percentageDepreciation;
        }

    }
}
