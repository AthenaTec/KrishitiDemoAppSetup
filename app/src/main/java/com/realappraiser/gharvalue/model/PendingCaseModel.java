package com.realappraiser.gharvalue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PendingCaseModel {
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

        @SerializedName("ApplicantName")
        @Expose
        private String applicantName;
        @SerializedName("ApplicantContactNo")
        @Expose
        private String applicantContactNo;
        @SerializedName("FIELDSTAFF_NAME")
        @Expose
        private String fieldstaffName;
        @SerializedName("Locality")
        @Expose
        private String locality;
        @SerializedName("PropertyLocality")
        @Expose
        private Object propertyLocality;
        @SerializedName("BankName")
        @Expose
        private String bankName;
        @SerializedName("AssignedAt")
        @Expose
        private String assignedAt;
        @SerializedName("Status")
        @Expose
        private String status;

        public String getApplicantName() {
            return applicantName;
        }

        public void setApplicantName(String applicantName) {
            this.applicantName = applicantName;
        }

        public String getApplicantContactNo() {
            return applicantContactNo;
        }

        public void setApplicantContactNo(String applicantContactNo) {
            this.applicantContactNo = applicantContactNo;
        }

        public String getFieldstaffName() {
            return fieldstaffName;
        }

        public void setFieldstaffName(String fieldstaffName) {
            this.fieldstaffName = fieldstaffName;
        }

        public String getLocality() {
            return locality;
        }

        public void setLocality(String locality) {
            this.locality = locality;
        }

        public Object getPropertyLocality() {
            return propertyLocality;
        }

        public void setPropertyLocality(Object propertyLocality) {
            this.propertyLocality = propertyLocality;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getAssignedAt() {
            return assignedAt;
        }

        public void setAssignedAt(String assignedAt) {
            this.assignedAt = assignedAt;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
}
