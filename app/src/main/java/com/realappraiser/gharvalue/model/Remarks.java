package com.realappraiser.gharvalue.model;

/**
 * Created by kaptas on 25/12/17.
 */

@SuppressWarnings("ALL")
public class Remarks {

    public int RemarksId;
    public String RemarksName;
    public String CreatedOn;
    public String CreatedBy;
    public String ModifiedOn;
    public String ModifiedBy;

    public int getRemarksId() {
        return RemarksId;
    }

    public void setRemarksId(int remarksId) {
        RemarksId = remarksId;
    }

    public String getRemarksName() {
        return RemarksName;
    }

    public void setRemarksName(String remarksName) {
        RemarksName = remarksName;
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
