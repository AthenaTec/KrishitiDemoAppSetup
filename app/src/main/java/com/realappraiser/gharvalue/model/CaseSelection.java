package com.realappraiser.gharvalue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CaseSelection {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public static class Data {

        @SerializedName("PropertyType")
        @Expose
        private List<PropertyType> propertyType = null;
        @SerializedName("Bank")
        @Expose
        private List<Bank> bank = null;
        @SerializedName("CaseAdmin")
        @Expose
        private List<CaseAdmin> caseAdmin = null;
        @SerializedName("ReportMaker")
        @Expose
        private List<ReportMaker> reportMaker = null;

        public List<PropertyType> getPropertyType() {
            return propertyType;
        }

        public void setPropertyType(List<PropertyType> propertyType) {
            this.propertyType = propertyType;
        }

        public List<Bank> getBank() {
            return bank;
        }

        public void setBank(List<Bank> bank) {
            this.bank = bank;
        }

        public List<CaseAdmin> getCaseAdmin() {
            return caseAdmin;
        }

        public void setCaseAdmin(List<CaseAdmin> caseAdmin) {
            this.caseAdmin = caseAdmin;
        }

        public List<ReportMaker> getReportMaker() {
            return reportMaker;
        }

        public void setReportMaker(List<ReportMaker> reportMaker) {
            this.reportMaker = reportMaker;
        }

        public static class PropertyType {

            @SerializedName("TypeOfPropertyId")
            @Expose
            private Integer typeOfPropertyId;
            @SerializedName("Name")
            @Expose
            private String name;
            @SerializedName("isActive")
            @Expose
            private Boolean isActive;
            @SerializedName("PropertyCategoryId")
            @Expose
            private Integer propertyCategoryId;
            @SerializedName("CreatedOn")
            @Expose
            private String createdOn;
            @SerializedName("CreatedBy")
            @Expose
            private Integer createdBy;
            @SerializedName("ModifiedOn")
            @Expose
            private Object modifiedOn;
            @SerializedName("ModifiedBy")
            @Expose
            private Integer modifiedBy;

            public PropertyType(String name) {
                this.name = name;
            }

            public Integer getTypeOfPropertyId() {
                return typeOfPropertyId;
            }

            public void setTypeOfPropertyId(Integer typeOfPropertyId) {
                this.typeOfPropertyId = typeOfPropertyId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Boolean getIsActive() {
                return isActive;
            }

            public void setIsActive(Boolean isActive) {
                this.isActive = isActive;
            }

            public Integer getPropertyCategoryId() {
                return propertyCategoryId;
            }

            public void setPropertyCategoryId(Integer propertyCategoryId) {
                this.propertyCategoryId = propertyCategoryId;
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

            public Object getModifiedOn() {
                return modifiedOn;
            }

            public void setModifiedOn(Object modifiedOn) {
                this.modifiedOn = modifiedOn;
            }

            public Integer getModifiedBy() {
                return modifiedBy;
            }

            public void setModifiedBy(Integer modifiedBy) {
                this.modifiedBy = modifiedBy;
            }


            @Override
            public String toString() {
                return name ;
            }
        }

        public class Bank {

            @SerializedName("BankId")
            @Expose
            private Integer bankId;
            @SerializedName("Name")
            @Expose
            private String name;

            public Integer getBankId() {
                return bankId;
            }

            public void setBankId(Integer bankId) {
                this.bankId = bankId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

        }

        public static class CaseAdmin {

            @SerializedName("EmployeeId")
            @Expose
            private Integer employeeId;
            @SerializedName("FirstName")
            @Expose
            private String firstName;
            @SerializedName("LastName")
            @Expose
            private String lastName;
            @SerializedName("RoleId")
            @Expose
            private Integer roleId;
            @SerializedName("FullName")
            @Expose
            private String fullName;


            public CaseAdmin(String fullName) {
                this.fullName = fullName;
            }

            public Integer getEmployeeId() {
                return employeeId;
            }

            public void setEmployeeId(Integer employeeId) {
                this.employeeId = employeeId;
            }

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            public Integer getRoleId() {
                return roleId;
            }

            public void setRoleId(Integer roleId) {
                this.roleId = roleId;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            @Override
            public String toString() {
                return  fullName ;
            }
        }

        public static class ReportMaker {

            @SerializedName("EmployeeId")
            @Expose
            private Integer employeeId;
            @SerializedName("FirstName")
            @Expose
            private String firstName;
            @SerializedName("LastName")
            @Expose
            private Object lastName;
            @SerializedName("RoleId")
            @Expose
            private Integer roleId;
            @SerializedName("FullName")
            @Expose
            private String fullName;

            @Override
            public String toString() {
                return  fullName ;
            }

            public ReportMaker(String fullName) {
                this.fullName = fullName;
            }

            public Integer getEmployeeId() {
                return employeeId;
            }

            public void setEmployeeId(Integer employeeId) {
                this.employeeId = employeeId;
            }

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public Object getLastName() {
                return lastName;
            }

            public void setLastName(Object lastName) {
                this.lastName = lastName;
            }

            public Integer getRoleId() {
                return roleId;
            }

            public void setRoleId(Integer roleId) {
                this.roleId = roleId;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

        }

    }
}
