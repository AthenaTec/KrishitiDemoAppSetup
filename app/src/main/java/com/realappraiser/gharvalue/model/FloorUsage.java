package com.realappraiser.gharvalue.model;

import java.util.ArrayList;

/**
 * Created by kaptas on 25/12/17.
 */

@SuppressWarnings("ALL")
public class FloorUsage {

    public int PropertyFloorUsageId;
    public String Name;
    public String CreatedOn;
    public String CreatedBy;
    public String ModifiedOn;
    public String ModifiedBy;

    public ArrayList<String> floorUsage = new ArrayList<>();


    public int getPropertyFloorUsageId() {
        return PropertyFloorUsageId;
    }

    public void setPropertyFloorUsageId(int propertyFloorUsageId) {
        PropertyFloorUsageId = propertyFloorUsageId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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


    public ArrayList<String> getFloorUsage() {
        return floorUsage;
    }

    public void setFloorUsage(ArrayList<String> floorUsage) {
        this.floorUsage = floorUsage;
    }
}
