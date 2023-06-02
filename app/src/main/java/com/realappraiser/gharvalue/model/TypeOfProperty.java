package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by kaptas on 25/12/17.
 */

@SuppressWarnings("ALL")

@Entity(tableName = "TypeOfProperty")
public class TypeOfProperty {

    @PrimaryKey(autoGenerate = true)
    public int dummyID;

    @ColumnInfo(name = "TypeOfPropertyId")
    public int TypeOfPropertyId;
    @ColumnInfo(name = "Name")
    public String Name;
    @ColumnInfo(name = "isActive")
    public String isActive;
    @ColumnInfo(name = "PropertyCategoryId")
    public String PropertyCategoryId;
    @ColumnInfo(name = "CreatedOn")
    public String CreatedOn;
    @ColumnInfo(name = "CreatedBy")
    public String CreatedBy;
    @ColumnInfo(name = "ModifiedOn")
    public String ModifiedOn;
    @ColumnInfo(name = "ModifiedBy")
    public String ModifiedBy;

    public int getTypeOfPropertyId() {
        return TypeOfPropertyId;
    }

    public void setTypeOfPropertyId(int typeOfPropertyId) {
        TypeOfPropertyId = typeOfPropertyId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getPropertyCategoryId() {
        return PropertyCategoryId;
    }

    public void setPropertyCategoryId(String propertyCategoryId) {
        PropertyCategoryId = propertyCategoryId;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getModifiedOn() {
        return ModifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        ModifiedOn = modifiedOn;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        ModifiedBy = modifiedBy;
    }
}
