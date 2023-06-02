
package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ProximityModal")

public class Proximity {

    @PrimaryKey(autoGenerate = true)
    public int dummyID;

    @SerializedName("Id")
    @Expose
    @ColumnInfo(name = "id")
    private int id;
    @SerializedName("CaseId")
    @Expose
    @ColumnInfo(name = "caseId")
    private int caseId;
    @SerializedName("ProximityId")
    @Expose
    @ColumnInfo(name = "proximityId")
    private int proximityId;
    @SerializedName("ProximityName")
    @Expose
    @ColumnInfo(name = "proximityName")
    private String proximityName;
    @SerializedName("ProximityDistance")
    @Expose
    @ColumnInfo(name = "proximityDistance")
    private String proximityDistance;
    @SerializedName("CreatedOn")
    @Expose
    @ColumnInfo(name = "createdOn")
    private String createdOn;
    @SerializedName("CreatedBy")
    @Expose
    @ColumnInfo(name = "createdBy")
    private int createdBy;
    @SerializedName("ModifiedOn")
    @Expose
    @ColumnInfo(name = "modifiedOn")
    private String modifiedOn;
    @SerializedName("ModifiedBy")
    @Expose
    @ColumnInfo(name = "modifiedBy")
    private int modifiedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getProximityId() {
        return proximityId;
    }

    public void setProximityId(int proximityId) {
        this.proximityId = proximityId;
    }

    public String getProximityName() {
        return proximityName;
    }

    public void setProximityName(String proximityName) {
        this.proximityName = proximityName;
    }

    public String getProximityDistance() {
        return proximityDistance;
    }

    public void setProximityDistance(String proximityDistance) {
        this.proximityDistance = proximityDistance;
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

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
