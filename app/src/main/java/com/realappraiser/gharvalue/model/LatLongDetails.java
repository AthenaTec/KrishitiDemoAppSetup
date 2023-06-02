package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by user on 27-02-2018.
 */

@Entity(tableName = "LatLongDetails")
public class LatLongDetails {

    @PrimaryKey(autoGenerate = true)
    public int dummyID;

    @ColumnInfo(name = "caseId")
    private int caseId;

    @ColumnInfo(name = "latLongDetails")
    private String latLongDetails;


    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getLatLongDetails() {
        return latLongDetails;
    }

    public void setLatLongDetails(String latLongDetails) {
        this.latLongDetails = latLongDetails;
    }


}
