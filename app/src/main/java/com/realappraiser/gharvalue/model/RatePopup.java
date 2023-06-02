package com.realappraiser.gharvalue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatePopup {

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

        @SerializedName("ParentCaseId")
        @Expose
        private Integer parentCaseId;
        @SerializedName("CurrentLat")
        @Expose
        private Double currentLat;
        @SerializedName("CurrentLong")
        @Expose
        private Double currentLong;
        @SerializedName("Type")
        @Expose
        private Integer type;
        @SerializedName("PropertyCategoryId")
        @Expose
        private Integer propertyCategoryId;
        @SerializedName("MinAmount")
        @Expose
        private Double minAmount;
        @SerializedName("MaxAmount")
        @Expose
        private Double maxAmount;
        @SerializedName("lstProprtylatlong")
        @Expose
        private Object lstProprtylatlong;

        public Integer getParentCaseId() {
            return parentCaseId;
        }

        public void setParentCaseId(Integer parentCaseId) {
            this.parentCaseId = parentCaseId;
        }

        public Double getCurrentLat() {
            return currentLat;
        }

        public void setCurrentLat(Double currentLat) {
            this.currentLat = currentLat;
        }

        public Double getCurrentLong() {
            return currentLong;
        }

        public void setCurrentLong(Double currentLong) {
            this.currentLong = currentLong;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getPropertyCategoryId() {
            return propertyCategoryId;
        }

        public void setPropertyCategoryId(Integer propertyCategoryId) {
            this.propertyCategoryId = propertyCategoryId;
        }

        public Double getMinAmount() {
            return minAmount;
        }

        public void setMinAmount(Double minAmount) {
            this.minAmount = minAmount;
        }

        public Double getMaxAmount() {
            return maxAmount;
        }

        public void setMaxAmount(Double maxAmount) {
            this.maxAmount = maxAmount;
        }

        public Object getLstProprtylatlong() {
            return lstProprtylatlong;
        }

        public void setLstProprtylatlong(Object lstProprtylatlong) {
            this.lstProprtylatlong = lstProprtylatlong;
        }

    }
}