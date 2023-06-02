package com.realappraiser.gharvalue.model;

/**
 * Created by kaptas on 25/12/17.
 */

@SuppressWarnings("ALL")
public class Maintenance {

    public int MaintenanceOfBuildingId;
    public String Name;
    public String CreatedOn;
    public String CreatedBy;
    public String ModifiedOn;
    public String ModifiedBy;


    //to display object as a string in spinner
    @Override
    public String toString() {
        return Name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Maintenance){
            Maintenance c = (Maintenance ) obj;
            if(c.getName().equals(Name) && c.getMaintenanceOfBuildingId()== MaintenanceOfBuildingId ) return true;
        }

        return false;
    }

    public int getMaintenanceOfBuildingId() {
        return MaintenanceOfBuildingId;
    }

    public void setMaintenanceOfBuildingId(int maintenanceOfBuildingId) {
        MaintenanceOfBuildingId = maintenanceOfBuildingId;
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
}
