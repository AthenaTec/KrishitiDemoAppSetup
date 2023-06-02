
package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity(tableName = "IndPropertyFloorModal")

public class IndPropertyFloor {


    @PrimaryKey(autoGenerate = true)
    public int dummyID;

    @SerializedName("CaseId")
    @Expose
    @ColumnInfo(name = "caseId")
    private int caseId;
    @SerializedName("FloorNo")
    @Expose
    @ColumnInfo(name = "floorNo")
    private int floorNo;
    @SerializedName("FloorName")
    @Expose
    @ColumnInfo(name = "floorName")
    private String floorName;
    @SerializedName("ConstructionStageId")
    @Expose
    @ColumnInfo(name = "constructionStageId")
    private int constructionStageId;
    @SerializedName("PercentageCompletion")
    @Expose
    @ColumnInfo(name = "percentageCompletion")
    private Integer percentageCompletion;
    @SerializedName("PresentConditionId")
    @Expose
    @ColumnInfo(name = "presentConditionId")
    private String presentConditionId;
    @SerializedName("PropertyAge")
    @Expose
    @ColumnInfo(name = "propertyAge")
    private Integer propertyAge;
    @SerializedName("ResidualLife")
    @Expose
    @ColumnInfo(name = "residualLife")
    private Integer residualLife;
    @SerializedName("PropertyFloorUsageId")
    @Expose
    @ColumnInfo(name = "propertyFloorUsageId")
    private String propertyFloorUsageId;
    @SerializedName("DocumentFloorArea")
    @Expose
    @ColumnInfo(name = "documentFloorArea")
    private String documentFloorArea;
    @SerializedName("DocumentFloorAreaUnit")
    @Expose
    @ColumnInfo(name = "documentFloorAreaUnit")
    private String documentFloorAreaUnit;
    @SerializedName("MeasuredFloorArea")
    @Expose
    @ColumnInfo(name = "measuredFloorArea")
    private String measuredFloorArea;
    @SerializedName("MeasuredFloorAreaUnit")
    @Expose
    @ColumnInfo(name = "measuredFloorAreaUnit")
    private String measuredFloorAreaUnit;
    @SerializedName("FlatHallNo")
    @Expose
    @ColumnInfo(name = "flatHallNo")
    private int flatHallNo;
    @SerializedName("FlatKitchenNo")
    @Expose
    @ColumnInfo(name = "flatKitchenNo")
    private int flatKitchenNo;
    @SerializedName("FlatBedroomNo")
    @Expose
    @ColumnInfo(name = "flatBedroomNo")
    private int flatBedroomNo;
    @SerializedName("FlatDinningNo")
    @Expose
    @ColumnInfo(name = "flatDinningNo")
    private int flatDinningNo;
    @SerializedName("FlatBathNo")
    @Expose
    @ColumnInfo(name = "flatBathNo")
    private int flatBathNo;
    @SerializedName("FlatWcNo")
    @Expose
    @ColumnInfo(name = "flatWcNo")
    private int flatWcNo;
    @SerializedName("FlatAttBathWcNo")
    @Expose
    @ColumnInfo(name = "flatAttBathWcNo")
    private int flatAttBathWcNo;
    @SerializedName("FlatBalconyNo")
    @Expose
    @ColumnInfo(name = "flatBalconyNo")
    private int flatBalconyNo;
    @SerializedName("ShopNo")
    @Expose
    @ColumnInfo(name = "shopNo")
    private int shopNo;
    @SerializedName("OfficeNo")
    @Expose
    @ColumnInfo(name = "officeNo")
    private int officeNo;
    @SerializedName("IntFloorId")
    @Expose
    @ColumnInfo(name = "intFloorId")
    private String intFloorId;
    @SerializedName("IntKitchenType")
    @Expose
    @ColumnInfo(name = "intKitchenType")
    private String intKitchenType;
    @SerializedName("IntKitchenShape")
    @Expose
    @ColumnInfo(name = "intKitchenShape")
    private String intKitchenShape;
    @SerializedName("IntWindowId")
    @Expose
    @ColumnInfo(name = "intWindowId")
    private String intWindowId;
    @SerializedName("IntDoorId")
    @Expose
    @ColumnInfo(name = "intDoorId")
    private String intDoorId;
    @SerializedName("IntWcId")
    @Expose
    @ColumnInfo(name = "intWcId")
    private String intWcId;
    @SerializedName("IntBathId")
    @Expose
    @ColumnInfo(name = "intBathId")
    private String intBathId;
    @SerializedName("IntPaintId")
    @Expose
    @ColumnInfo(name = "intPaintId")
    private String intPaintId;
    @SerializedName("IntPop")
    @Expose
    @ColumnInfo(name = "intPop")
    private Boolean intPop;
    @SerializedName("IntRoofId")
    @Expose
    @ColumnInfo(name = "intRoofId")
    private String intRoofId;
    @SerializedName("PassageTypeId")
    @Expose
    @ColumnInfo(name = "passageTypeId")
    private String passageTypeId;
    @SerializedName("TypeOfStructureId")
    @Expose
    @ColumnInfo(name = "typeOfStructureId")
    private int typeOfStructureId;
    @SerializedName("FloorHeight")
    @Expose
    @ColumnInfo(name = "floorHeight")
    private String floorHeight;
    @SerializedName("YearsOfCurrentTenancy")
    @Expose
    @ColumnInfo(name = "yearsOfCurrentTenancy")
    private String yearsOfCurrentTenancy;
    @SerializedName("RentalIncome")
    @Expose
    @ColumnInfo(name = "rentalIncome")
    private String rentalIncome;
    @SerializedName("PercentageDepreciation")
    @Expose
    @ColumnInfo(name = "percentageDepreciation")
    private String percentageDepreciation;
    @SerializedName("CreatedOn")
    @Expose
    @ColumnInfo(name = "createdOn")
    private String createdOn;
    @SerializedName("CreatedBy")
    @Expose
    @ColumnInfo(name = "createdBy")
    private int createdBy;
    @SerializedName("ModifiedOn")
    @Expose
    @ColumnInfo(name = "modifiedOn")
    private String modifiedOn;
    @SerializedName("ModifiedBy")
    @Expose
    @ColumnInfo(name = "modifiedBy")
    private int modifiedBy;
    @SerializedName("PresentCondition")
    @Expose
    @ColumnInfo(name = "presentCondition")
    private String presentCondition;
    @SerializedName("PropertyFloorUsageId_str")
    @Expose
    @ColumnInfo(name = "PropertyFloorUsageId_str")
    private String PropertyFloorUsageId_str;
    @SerializedName("FlatFbNo")
    @Expose
    @ColumnInfo(name = "FlatFbNo")
    private int FlatFbNo;
    @SerializedName("FlatDbNo")
    @Expose
    @ColumnInfo(name = "FlatDbNo")
    private int FlatDbNo;
    @SerializedName("FlatTerraceNo")
    @Expose
    @ColumnInfo(name = "FlatTerraceNo")
    private int FlatTerraceNo;
    @SerializedName("FlatPassageNo")
    @Expose
    @ColumnInfo(name = "FlatPassageNo")
    private int FlatPassageNo;


    public int getFlatFbNo() {
        return FlatFbNo;
    }

    public void setFlatFbNo(int flatFbNo) {
        FlatFbNo = flatFbNo;
    }

    public int getFlatDbNo() {
        return FlatDbNo;
    }

    public void setFlatDbNo(int flatDbNo) {
        FlatDbNo = flatDbNo;
    }

    public int getFlatTerraceNo() {
        return FlatTerraceNo;
    }

    public void setFlatTerraceNo(int flatTerraceNo) {
        FlatTerraceNo = flatTerraceNo;
    }

    public int getFlatPassageNo() {
        return FlatPassageNo;
    }

    public void setFlatPassageNo(int flatPassageNo) {
        FlatPassageNo = flatPassageNo;
    }

    public String getPropertyFloorUsageId_str() {
        return PropertyFloorUsageId_str;
    }

    public void setPropertyFloorUsageId_str(String propertyFloorUsageId_str) {
        PropertyFloorUsageId_str = propertyFloorUsageId_str;
    }


    /*******************/
    @Ignore
    private ArrayList<String> floorUsage;

    public ArrayList<String> getFloorUsage() {
        return floorUsage;
    }

    public void setFloorUsage(ArrayList<String> floorUsage) {
        this.floorUsage = floorUsage;
    }
    /********************/

    public Integer getPercentageCompletion() {
        return percentageCompletion;
    }

    public void setPercentageCompletion(Integer percentageCompletion) {
        this.percentageCompletion = percentageCompletion;
    }

    public Integer getPropertyAge() {
        return propertyAge;
    }

    public void setPropertyAge(Integer propertyAge) {
        this.propertyAge = propertyAge;
    }

    public Integer getResidualLife() {
        return residualLife;
    }

    public void setResidualLife(Integer residualLife) {
        this.residualLife = residualLife;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public int getConstructionStageId() {
        return constructionStageId;
    }

    public void setConstructionStageId(int constructionStageId) {
        this.constructionStageId = constructionStageId;
    }

    public String getPresentConditionId() {
        return presentConditionId;
    }

    public void setPresentConditionId(String presentConditionId) {
        this.presentConditionId = presentConditionId;
    }

    public String getPropertyFloorUsageId() {
        return propertyFloorUsageId;
    }

    public void setPropertyFloorUsageId(String propertyFloorUsageId) {
        this.propertyFloorUsageId = propertyFloorUsageId;
    }

    public String getDocumentFloorArea() {
        return documentFloorArea;
    }

    public void setDocumentFloorArea(String documentFloorArea) {
        this.documentFloorArea = documentFloorArea;
    }

    public String getDocumentFloorAreaUnit() {
        return documentFloorAreaUnit;
    }

    public void setDocumentFloorAreaUnit(String documentFloorAreaUnit) {
        this.documentFloorAreaUnit = documentFloorAreaUnit;
    }

    public String getMeasuredFloorArea() {
        return measuredFloorArea;
    }

    public void setMeasuredFloorArea(String measuredFloorArea) {
        this.measuredFloorArea = measuredFloorArea;
    }

    public String getMeasuredFloorAreaUnit() {
        return measuredFloorAreaUnit;
    }

    public void setMeasuredFloorAreaUnit(String measuredFloorAreaUnit) {
        this.measuredFloorAreaUnit = measuredFloorAreaUnit;
    }

    public int getFlatHallNo() {
        return flatHallNo;
    }

    public void setFlatHallNo(int flatHallNo) {
        this.flatHallNo = flatHallNo;
    }

    public int getFlatKitchenNo() {
        return flatKitchenNo;
    }

    public void setFlatKitchenNo(int flatKitchenNo) {
        this.flatKitchenNo = flatKitchenNo;
    }

    public int getFlatBedroomNo() {
        return flatBedroomNo;
    }

    public void setFlatBedroomNo(int flatBedroomNo) {
        this.flatBedroomNo = flatBedroomNo;
    }

    public int getFlatDinningNo() {
        return flatDinningNo;
    }

    public void setFlatDinningNo(int flatDinningNo) {
        this.flatDinningNo = flatDinningNo;
    }

    public int getFlatBathNo() {
        return flatBathNo;
    }

    public void setFlatBathNo(int flatBathNo) {
        this.flatBathNo = flatBathNo;
    }

    public int getFlatWcNo() {
        return flatWcNo;
    }

    public void setFlatWcNo(int flatWcNo) {
        this.flatWcNo = flatWcNo;
    }

    public int getFlatAttBathWcNo() {
        return flatAttBathWcNo;
    }

    public void setFlatAttBathWcNo(int flatAttBathWcNo) {
        this.flatAttBathWcNo = flatAttBathWcNo;
    }

    public int getFlatBalconyNo() {
        return flatBalconyNo;
    }

    public void setFlatBalconyNo(int flatBalconyNo) {
        this.flatBalconyNo = flatBalconyNo;
    }

    public int getShopNo() {
        return shopNo;
    }

    public void setShopNo(int shopNo) {
        this.shopNo = shopNo;
    }

    public int getOfficeNo() {
        return officeNo;
    }

    public void setOfficeNo(int officeNo) {
        this.officeNo = officeNo;
    }

    public String getIntFloorId() {
        return intFloorId;
    }

    public void setIntFloorId(String intFloorId) {
        this.intFloorId = intFloorId;
    }

    public String getIntKitchenType() {
        return intKitchenType;
    }

    public void setIntKitchenType(String intKitchenType) {
        this.intKitchenType = intKitchenType;
    }

    public String getIntKitchenShape() {
        return intKitchenShape;
    }

    public void setIntKitchenShape(String intKitchenShape) {
        this.intKitchenShape = intKitchenShape;
    }

    public String getIntWindowId() {
        return intWindowId;
    }

    public void setIntWindowId(String intWindowId) {
        this.intWindowId = intWindowId;
    }

    public String getIntDoorId() {
        return intDoorId;
    }

    public void setIntDoorId(String intDoorId) {
        this.intDoorId = intDoorId;
    }

    public String getIntWcId() {
        return intWcId;
    }

    public void setIntWcId(String intWcId) {
        this.intWcId = intWcId;
    }

    public String getIntBathId() {
        return intBathId;
    }

    public void setIntBathId(String intBathId) {
        this.intBathId = intBathId;
    }

    public String getIntPaintId() {
        return intPaintId;
    }

    public void setIntPaintId(String intPaintId) {
        this.intPaintId = intPaintId;
    }

    public Boolean getIntPop() {
        return intPop;
    }

    public void setIntPop(Boolean intPop) {
        this.intPop = intPop;
    }

    public String getIntRoofId() {
        return intRoofId;
    }

    public void setIntRoofId(String intRoofId) {
        this.intRoofId = intRoofId;
    }

    public String getPassageTypeId() {
        return passageTypeId;
    }

    public void setPassageTypeId(String passageTypeId) {
        this.passageTypeId = passageTypeId;
    }

    public int getTypeOfStructureId() {
        return typeOfStructureId;
    }

    public void setTypeOfStructureId(int typeOfStructureId) {
        this.typeOfStructureId = typeOfStructureId;
    }

    public String getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(String floorHeight) {
        this.floorHeight = floorHeight;
    }

    public String getYearsOfCurrentTenancy() {
        return yearsOfCurrentTenancy;
    }

    public void setYearsOfCurrentTenancy(String yearsOfCurrentTenancy) {
        this.yearsOfCurrentTenancy = yearsOfCurrentTenancy;
    }

    public String getRentalIncome() {
        return rentalIncome;
    }

    public void setRentalIncome(String rentalIncome) {
        this.rentalIncome = rentalIncome;
    }

    public String getPercentageDepreciation() {
        return percentageDepreciation;
    }

    public void setPercentageDepreciation(String percentageDepreciation) {
        this.percentageDepreciation = percentageDepreciation;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getPresentCondition() {
        return presentCondition;
    }

    public void setPresentCondition(String presentCondition) {
        this.presentCondition = presentCondition;
    }

}
