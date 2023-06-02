
package com.realappraiser.gharvalue.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("ALL")
public class Data {

    @SerializedName("Case")
    @Expose
    private Case _case;
    @SerializedName("Property")
    @Expose
    private Property property;
    @SerializedName("IndProperty")
    @Expose
    private IndProperty indProperty;
    @SerializedName("IndPropertyValuation")
    @Expose
    private IndPropertyValuation indPropertyValuation;
    @SerializedName("IndPropertyFloors")
    @Expose
    private List<IndPropertyFloor> indPropertyFloors = null;
    @SerializedName("IndPropertyFloorsValuation")
    @Expose
    private List<Object> indPropertyFloorsValuation = null;
    @SerializedName("ProximitySpinner")
    @Expose
    private List<Proximity> proximity = null;

    public Case getCase() {
        return _case;
    }

    public void setCase(Case _case) {
        this._case = _case;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public IndProperty getIndProperty() {
        return indProperty;
    }

    public void setIndProperty(IndProperty indProperty) {
        this.indProperty = indProperty;
    }

    public IndPropertyValuation getIndPropertyValuation() {
        return indPropertyValuation;
    }

    public void setIndPropertyValuation(IndPropertyValuation indPropertyValuation) {
        this.indPropertyValuation = indPropertyValuation;
    }

    public List<IndPropertyFloor> getIndPropertyFloors() {
        return indPropertyFloors;
    }

    public void setIndPropertyFloors(List<IndPropertyFloor> indPropertyFloors) {
        this.indPropertyFloors = indPropertyFloors;
    }

    public List<Object> getIndPropertyFloorsValuation() {
        return indPropertyFloorsValuation;
    }

    public void setIndPropertyFloorsValuation(List<Object> indPropertyFloorsValuation) {
        this.indPropertyFloorsValuation = indPropertyFloorsValuation;
    }

    public List<Proximity> getProximity() {
        return proximity;
    }

    public void setProximity(List<Proximity> proximity) {
        this.proximity = proximity;
    }

}
