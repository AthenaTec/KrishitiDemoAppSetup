package com.realappraiser.gharvalue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FieldStaff {

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
    public static class Datum {

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

        public Datum(String fullName) {
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


        @Override
        public String toString() {
            return  fullName ;
        }
    }
}
