package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by user on 01-03-2018.
 */

@Entity(tableName = "OflineCase")
public class OflineCase {

    @PrimaryKey(autoGenerate = true)
    public int dummyID;

    @ColumnInfo(name = "caseId")
    private int caseId;

    @ColumnInfo(name = "PropertyId")
    private int PropertyId;

    public int getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(int propertyId) {
        PropertyId = propertyId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }


}
