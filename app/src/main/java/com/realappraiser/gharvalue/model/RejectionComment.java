package com.realappraiser.gharvalue.model;

/**
 * Created by kaptas on 26/1/18.
 */

@SuppressWarnings("ALL")
public class RejectionComment {

    public int RejectionId;
    public String CategoryId;
    public String Comment;
    public String CreatedOn;
    public String CreatedBy;
    public String ModifiedOn;
    public String ModifiedBy;


    //to display object as a string in spinner
    @Override
    public String toString() {
        return Comment;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RejectionComment){
            RejectionComment c = (RejectionComment) obj;
            if(c.getComment().equals(Comment) && c.getRejectionId()==RejectionId ) return true;
        }

        return false;
    }

    public int getRejectionId() {
        return RejectionId;
    }

    public void setRejectionId(int rejectionId) {
        RejectionId = rejectionId;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
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
