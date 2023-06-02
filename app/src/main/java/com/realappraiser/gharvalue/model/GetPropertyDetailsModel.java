package com.realappraiser.gharvalue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetPropertyDetailsModel {
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

    public class Data {

        @SerializedName("PropertyAddress")
        @Expose
        private Object propertyAddress;
        @SerializedName("LatLongDetails")
        @Expose
        private Object latLongDetails;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("PropertyCategoryId")
        @Expose
        private Integer propertyCategoryId;
        @SerializedName("PropertyCategoryName")
        @Expose
        private String propertyCategoryName;

        public String getPropertyCategoryName() {
            return propertyCategoryName;
        }

        public void setPropertyCategoryName(String propertyCategoryName) {
            this.propertyCategoryName = propertyCategoryName;
        }

        public Object getPropertyAddress() {
            return propertyAddress;
        }

        public void setPropertyAddress(Object propertyAddress) {
            this.propertyAddress = propertyAddress;
        }

        public Object getLatLongDetails() {
            return latLongDetails;
        }

        public void setLatLongDetails(Object latLongDetails) {
            this.latLongDetails = latLongDetails;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPropertyCategoryId() {
            return propertyCategoryId;
        }

        public void setPropertyCategoryId(Integer propertyCategoryId) {
            this.propertyCategoryId = propertyCategoryId;
        }

    }
}
