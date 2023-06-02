package com.realappraiser.gharvalue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPropertyCompareDetailsModel {

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

        @SerializedName("CaseId")
        @Expose
        private Integer caseId;
        @SerializedName("PropertyId")
        @Expose
        private Integer propertyId;
        @SerializedName("PropertyAddress")
        @Expose
        private String propertyAddress;
        @SerializedName("LatLongDetails")
        @Expose
        private String latLongDetails;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("TypeOfPropertyId")
        @Expose
        private Integer typeOfPropertyId;
        @SerializedName("PropertyCategoryId")
        @Expose
        private Integer propertyCategoryId;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("ConstructionRate")
        @Expose
        private String constructionRate;
        @SerializedName("DocumentLandRate")
        @Expose
        private Object documentLandRate;
        @SerializedName("Rate")
        @Expose
        private String rate;
        @SerializedName("TotalPropertyValue")
        @Expose
        private String totalPropertyValue;
        @SerializedName("Distance")
        @Expose
        private Float distance;

        public Integer getCaseId() {
            return caseId;
        }

        public void setCaseId(Integer caseId) {
            this.caseId = caseId;
        }

        public Integer getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(Integer propertyId) {
            this.propertyId = propertyId;
        }

        public String getPropertyAddress() {
            return propertyAddress;
        }

        public void setPropertyAddress(String propertyAddress) {
            this.propertyAddress = propertyAddress;
        }

        public String getLatLongDetails() {
            return latLongDetails;
        }

        public void setLatLongDetails(String latLongDetails) {
            this.latLongDetails = latLongDetails;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public Integer getTypeOfPropertyId() {
            return typeOfPropertyId;
        }

        public void setTypeOfPropertyId(Integer typeOfPropertyId) {
            this.typeOfPropertyId = typeOfPropertyId;
        }

        public Integer getPropertyCategoryId() {
            return propertyCategoryId;
        }

        public void setPropertyCategoryId(Integer propertyCategoryId) {
            this.propertyCategoryId = propertyCategoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getConstructionRate() {
            return constructionRate;
        }

        public void setConstructionRate(String constructionRate) {
            this.constructionRate = constructionRate;
        }

        public Object getDocumentLandRate() {
            return documentLandRate;
        }

        public void setDocumentLandRate(Object documentLandRate) {
            this.documentLandRate = documentLandRate;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getTotalPropertyValue() {
            return totalPropertyValue;
        }

        public void setTotalPropertyValue(String totalPropertyValue) {
            this.totalPropertyValue = totalPropertyValue;
        }

        public Float getDistance() {
            return distance;
        }

        public void setDistance(Float distance) {
            this.distance = distance;
        }

    }
}

