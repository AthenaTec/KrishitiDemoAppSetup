package com.realappraiser.gharvalue.model;

public class Kitchentype {


    public int IntKitchenTypeId;
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
        if (obj instanceof Kitchentype) {
            Kitchentype c = (Kitchentype) obj;
            if (c.getName().equals(Name) && c.getIntKitchenTypeId() == IntKitchenTypeId)
                return true;
        }

        return false;
    }

    public int getIntKitchenTypeId() {
        return IntKitchenTypeId;
    }

    public void setIntKitchenTypeId(int intKitchenTypeId) {
        IntKitchenTypeId = intKitchenTypeId;
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
