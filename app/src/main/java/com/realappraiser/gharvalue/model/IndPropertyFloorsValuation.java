package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "IndPropertyFloorsValuationModal")

public class IndPropertyFloorsValuation {

    @PrimaryKey(autoGenerate = true)
    public int dummyID;

    @SerializedName("CaseId")
    @Expose
    @ColumnInfo(name = "caseId")
    private int caseId;
    @SerializedName("FloorNo")
    @Expose
    @ColumnInfo(name = "floorNo")
    private int floorNo;
    @SerializedName("DocumentConstrRate")
    @Expose
    @ColumnInfo(name = "documentConstrRate")
    private String documentConstrRate;
    @SerializedName("DocumentConstrValue")
    @Expose
    @ColumnInfo(name = "documentConstrValue")
    private String documentConstrValue;
    @SerializedName("MeasuredConstrRate")
    @Expose
    @ColumnInfo(name = "measuredConstrRate")
    private String measuredConstrRate;
    @SerializedName("MeasuredConstrValue")
    @Expose
    @ColumnInfo(name = "measuredConstrValue")
    private String measuredConstrValue;
    @SerializedName("FloorDepreciationValue")
    @Expose
    @ColumnInfo(name = "floorDepreciationValue")
    private String floorDepreciationValue;
    @SerializedName("CreatedOn")
    @Expose
    @ColumnInfo(name = "createdOn")
    private String createdOn;
    @SerializedName("CreatedBy")
    @Expose
    @ColumnInfo(name = "createdBy")
    private int createdBy;
    @SerializedName("ModifiedBy")
    @Expose
    @ColumnInfo(name = "modifiedBy")
    private int modifiedBy;
    @SerializedName("FloorCarpetArea")
    @Expose
    @ColumnInfo(name = "floorCarpetArea")
    private String floorCarpetArea;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public String getDocumentConstrRate() {
        return documentConstrRate;
    }

    public void setDocumentConstrRate(String documentConstrRate) {
        this.documentConstrRate = documentConstrRate;
    }

    public String getDocumentConstrValue() {
        return documentConstrValue;
    }

    public void setDocumentConstrValue(String documentConstrValue) {
        this.documentConstrValue = documentConstrValue;
    }

    public String getMeasuredConstrRate() {
        return measuredConstrRate;
    }

    public void setMeasuredConstrRate(String measuredConstrRate) {
        this.measuredConstrRate = measuredConstrRate;
    }

    public String getMeasuredConstrValue() {
        return measuredConstrValue;
    }

    public void setMeasuredConstrValue(String measuredConstrValue) {
        this.measuredConstrValue = measuredConstrValue;
    }

    public String getFloorDepreciationValue() {
        return floorDepreciationValue;
    }

    public void setFloorDepreciationValue(String floorDepreciationValue) {
        this.floorDepreciationValue = floorDepreciationValue;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getFloorCarpetArea() {
        return floorCarpetArea;
    }

    public void setFloorCarpetArea(String floorCarpetArea) {
        this.floorCarpetArea = floorCarpetArea;
    }

}
