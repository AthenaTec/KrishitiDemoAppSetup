package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by kaptas on 16/2/18.
 */


@Entity
public class CaseDetail {

    @PrimaryKey(autoGenerate = true)
    public int dummyID;

    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "caseid")
    private String caseid;
    @ColumnInfo(name = "caseObj")
    private String caseObj;
    @ColumnInfo(name = "propertyObj")
    private String propertyObj;
    @ColumnInfo(name = "indpropertyObj")
    private String indpropertyObj;
    @ColumnInfo(name = "indpropertyvaluationObj")
    private String indpropertyvaluationObj;
    @ColumnInfo(name = "indpropertyfloorsObj")
    private String indpropertyfloorsObj;
    @ColumnInfo(name = "indpropertyfloorsvaluationObj")
    private String indpropertyfloorsvaluationObj;
    @ColumnInfo(name = "proximityObj")
    private String proximityObj;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    public String getCaseObj() {
        return caseObj;
    }

    public void setCaseObj(String caseObj) {
        this.caseObj = caseObj;
    }

    public String getPropertyObj() {
        return propertyObj;
    }

    public void setPropertyObj(String propertyObj) {
        this.propertyObj = propertyObj;
    }

    public String getIndpropertyObj() {
        return indpropertyObj;
    }

    public void setIndpropertyObj(String indpropertyObj) {
        this.indpropertyObj = indpropertyObj;
    }

    public String getIndpropertyfloorsObj() {
        return indpropertyfloorsObj;
    }

    public void setIndpropertyfloorsObj(String indpropertyfloorsObj) {
        this.indpropertyfloorsObj = indpropertyfloorsObj;
    }

    public String getIndpropertyfloorsvaluationObj() {
        return indpropertyfloorsvaluationObj;
    }

    public void setIndpropertyfloorsvaluationObj(String indpropertyfloorsvaluationObj) {
        this.indpropertyfloorsvaluationObj = indpropertyfloorsvaluationObj;
    }

    public String getProximityObj() {
        return proximityObj;
    }

    public void setProximityObj(String proximityObj) {
        this.proximityObj = proximityObj;
    }

    public String getIndpropertyvaluationObj() {
        return indpropertyvaluationObj;
    }

    public void setIndpropertyvaluationObj(String indpropertyvaluationObj) {
        this.indpropertyvaluationObj = indpropertyvaluationObj;
    }
}
