
package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "IndPropertyModal")
public class IndProperty {

    @PrimaryKey(autoGenerate = true)
    public int dummyID;


    @SerializedName("CaseId")
    @Expose
    @ColumnInfo(name = "caseId")
    private int caseId;
    @SerializedName("NoOfFloors")
    @Expose
    @ColumnInfo(name = "noOfFloors")
    private int noOfFloors;
    @SerializedName("ApprovedNoOfFloors")
    @Expose
    @ColumnInfo(name = "approvedNoOfFloors")
    private int approvedNoOfFloors;
    @SerializedName("FloorDetails")
    @Expose
    @ColumnInfo(name = "floorDetails")
    private String floorDetails;
    @SerializedName("PropertyActualUsageId")
    @Expose
    @ColumnInfo(name = "propertyActualUsageId")
    private String propertyActualUsageId;
    @SerializedName("TotalApartmentFloors")
    @Expose
    @ColumnInfo(name = "totalApartmentFloors")
    private String totalApartmentFloors;
    @SerializedName("TotalFlatsPerFloor")
    @Expose
    @ColumnInfo(name = "totalFlatsPerFloor")
    private String totalFlatsPerFloor;
    @SerializedName("FlatSituatedInFloor")
    @Expose
    @ColumnInfo(name = "flatSituatedInFloor")
    private String flatSituatedInFloor;
    @SerializedName("DocumentLandArea")
    @Expose
    @ColumnInfo(name = "documentLandArea")
    private String documentLandArea;
    @SerializedName("IsCompounded")
    @Expose
    @ColumnInfo(name = "isCompounded")
    private Boolean isCompounded;
    @SerializedName("DocumentLandAreaUnit")
    @Expose
    @ColumnInfo(name = "documentLandAreaUnit")
    private int documentLandAreaUnit;
    @SerializedName("MeasuredLandArea")
    @Expose
    @ColumnInfo(name = "measuredLandArea")
    private String measuredLandArea;
    @SerializedName("MeasuredLandAreaUnit")
    @Expose
    @ColumnInfo(name = "measuredLandAreaUnit")
    private int measuredLandAreaUnit;
    @SerializedName("AvgPercentageCompletion")
    @Expose
    @ColumnInfo(name = "avgPercentageCompletion")
    private String avgPercentageCompletion;
    @SerializedName("DocumentFloorAreaTotal")
    @Expose
    @ColumnInfo(name = "documentFloorAreaTotal")
    private String documentFloorAreaTotal;
    @SerializedName("MeasuredFloorAreaTotal")
    @Expose
    @ColumnInfo(name = "measuredFloorAreaTotal")
    private String measuredFloorAreaTotal;
    @SerializedName("TypeOfStructureId")
    @Expose
    @ColumnInfo(name = "typeOfStructureId")
    private String typeOfStructureId;
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
    @SerializedName("Basement")
    @Expose
    @ColumnInfo(name = "basement")
    private String basement;
    @SerializedName("NoOfLiftInBuilding")
    @Expose
    @ColumnInfo(name = "noOfLiftInBuilding")
    private String noOfLiftInBuilding;

    @SerializedName("ExteriorPaintId")
    @Expose
    @ColumnInfo(name = "exteriorPaintId")
    private String exteriorPaintId;
    @SerializedName("CarParkingId")
    @Expose
    @ColumnInfo(name = "carParkingId")
    private String carParkingId;
    @SerializedName("WaterAvailabilityId")
    @Expose
    @ColumnInfo(name = "waterAvailabilityId")
    private String waterAvailabilityId;
    @SerializedName("Amenities")
    @Expose
    @ColumnInfo(name = "amenities")
    private String amenities;
    @SerializedName("WingName")
    @Expose
    @ColumnInfo(name = "wingName")
    private String wingName;
    @SerializedName("IntFloorId")
    @Expose
    @ColumnInfo(name = "intFloorId")
    private String intFloorId;
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
    @SerializedName("IntPaintId")
    @Expose
    @ColumnInfo(name = "intPaintId")
    private String intPaintId;
    @SerializedName("IntRoofId")
    @Expose
    @ColumnInfo(name = "intRoofId")
    private String intRoofId;
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
    @SerializedName("FloorKindId")
    @Expose
    @ColumnInfo(name = "floorKindId")
    private int floorKindId;
    @SerializedName("WingNo")
    @Expose
    @ColumnInfo(name = "wingNo")
    private Integer wingNo;
    @SerializedName("IntPop")
    @Expose
    @ColumnInfo(name = "intPop")
    private Boolean intPop;
    @SerializedName("GardenExist")
    @Expose
    @ColumnInfo(name = "gardenExist")
    private Boolean gardenExist;
    @SerializedName("SeperateCompoundId")
    @Expose
    @ColumnInfo(name = "seperateCompoundId")
    private Integer seperateCompoundId;
    @SerializedName("PassageTypeId")
    @Expose
    @ColumnInfo(name = "passageTypeId")
    private Integer passageTypeId;
    @SerializedName("MarketabilityId")
    @Expose
    @ColumnInfo(name = "marketabilityId")
    private Integer marketabilityId;
    @SerializedName("FittingQualityId")
    @Expose
    @ColumnInfo(name = "fittingQualityId")
    private Integer fittingQualityId;
    @SerializedName("MaintenanceOfBuildingId")
    @Expose
    @ColumnInfo(name = "maintenanceOfBuildingId")
    private Integer maintenanceOfBuildingId;
    @SerializedName("TypeOfBuildingId")
    @Expose
    @ColumnInfo(name = "typeOfBuildingId")
    private Integer typeOfBuildingId;
    @SerializedName("QualityConstructionId")
    @Expose
    @ColumnInfo(name = "qualityConstructionId")
    private Integer qualityConstructionId;
    @SerializedName("IntBathId")
    @Expose
    @ColumnInfo(name = "intBathId")
    private Integer intBathId;
    @SerializedName("AmenityQualityId")
    @Expose
    @ColumnInfo(name = "amenityQualityId")
    private Integer amenityQualityId;
    @SerializedName("IntKitchenType")
    @Expose
    @ColumnInfo(name = "intKitchenType")
    private Integer intKitchenType;
    @SerializedName("IntKitchenShape")
    @Expose
    @ColumnInfo(name = "intKitchenShape")
    private Integer intKitchenShape;
    @SerializedName("PavingAroundBuildingId")
    @Expose
    @ColumnInfo(name = "pavingAroundBuildingId")
    private String pavingAroundBuildingId;
    //private Integer pavingAroundBuildingId;

    @SerializedName("LandAreaDescription")
    @Expose
    @ColumnInfo(name = "LandAreaDescription")
    private String LandAreaDescription;

    @SerializedName("NumberOfBuildings")
    @Expose
    @ColumnInfo(name = "NumberOfBuildings")
    private String NumberOfBuildings;

    @SerializedName("TypeOfCompound")
    @Expose
    @ColumnInfo(name = "TypeOfCompound")
    private Integer TypeOfCompound;


    @SerializedName("NameOfTenant")
    @Expose
    @ColumnInfo(name = "NameOfTenant")
    private String NameOfTenant;

    public String getNameOfTenant() {
        return NameOfTenant;
    }

    public void setNameOfTenant(String nameOfTenant) {
        NameOfTenant = nameOfTenant;
    }

    public Integer getTypeOfCompound() {
        return TypeOfCompound;
    }

    public void setTypeOfCompound(Integer typeOfCompound) {
        TypeOfCompound = typeOfCompound;
    }

    public String getNumberOfBuildings() {
        return NumberOfBuildings;
    }

    public void setNumberOfBuildings(String numberOfBuildings) {
        NumberOfBuildings = numberOfBuildings;
    }

    public String getLandAreaDescription() {
        return LandAreaDescription;
    }

    public void setLandAreaDescription(String landAreaDescription) {
        LandAreaDescription = landAreaDescription;
    }




    public Integer getIntBathId() {
        return intBathId;
    }

    public void setIntBathId(Integer intBathId) {
        this.intBathId = intBathId;
    }

    public Integer getAmenityQualityId() {
        return amenityQualityId;
    }

    public void setAmenityQualityId(Integer amenityQualityId) {
        this.amenityQualityId = amenityQualityId;
    }

    public Integer getIntKitchenType() {
        return intKitchenType;
    }

    public void setIntKitchenType(Integer intKitchenType) {
        this.intKitchenType = intKitchenType;
    }

    public Integer getIntKitchenShape() {
        return intKitchenShape;
    }

    public void setIntKitchenShape(Integer intKitchenShape) {
        this.intKitchenShape = intKitchenShape;
    }



    public Integer getMarketabilityId() {
        return marketabilityId;
    }

    public void setMarketabilityId(Integer marketabilityId) {
        this.marketabilityId = marketabilityId;
    }

    public Integer getFittingQualityId() {
        return fittingQualityId;
    }

    public void setFittingQualityId(Integer fittingQualityId) {
        this.fittingQualityId = fittingQualityId;
    }

    public Integer getMaintenanceOfBuildingId() {
        return maintenanceOfBuildingId;
    }

    public void setMaintenanceOfBuildingId(Integer maintenanceOfBuildingId) {
        this.maintenanceOfBuildingId = maintenanceOfBuildingId;
    }

    public Integer getTypeOfBuildingId() {
        return typeOfBuildingId;
    }

    public void setTypeOfBuildingId(Integer typeOfBuildingId) {
        this.typeOfBuildingId = typeOfBuildingId;
    }

    public Integer getQualityConstructionId() {
        return qualityConstructionId;
    }

    public void setQualityConstructionId(Integer qualityConstructionId) {
        this.qualityConstructionId = qualityConstructionId;
    }


    public Integer getPassageTypeId() {
        return passageTypeId;
    }

    public void setPassageTypeId(Integer passageTypeId) {
        this.passageTypeId = passageTypeId;
    }

    public Integer getWingNo() {
        return wingNo;
    }

    public void setWingNo(Integer wingNo) {
        this.wingNo = wingNo;
    }

    public Boolean getIntPop() {
        return intPop;
    }

    public void setIntPop(Boolean intPop) {
        this.intPop = intPop;
    }

    public Boolean getGardenExist() {
        return gardenExist;
    }

    public void setGardenExist(Boolean gardenExist) {
        this.gardenExist = gardenExist;
    }

    public Integer getSeperateCompoundId() {
        return seperateCompoundId;
    }

    public void setSeperateCompoundId(Integer seperateCompoundId) {
        this.seperateCompoundId = seperateCompoundId;
    }

    public String getPavingAroundBuildingId() {
        return pavingAroundBuildingId;
    }

    public void setPavingAroundBuildingId(String pavingAroundBuildingId) {
        this.pavingAroundBuildingId = pavingAroundBuildingId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(int noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public int getApprovedNoOfFloors() {
        return approvedNoOfFloors;
    }

    public void setApprovedNoOfFloors(int approvedNoOfFloors) {
        this.approvedNoOfFloors = approvedNoOfFloors;
    }

    public String getFloorDetails() {
        return floorDetails;
    }

    public void setFloorDetails(String floorDetails) {
        this.floorDetails = floorDetails;
    }

    public String getPropertyActualUsageId() {
        return propertyActualUsageId;
    }

    public void setPropertyActualUsageId(String propertyActualUsageId) {
        this.propertyActualUsageId = propertyActualUsageId;
    }

    public String getTotalApartmentFloors() {
        return totalApartmentFloors;
    }

    public void setTotalApartmentFloors(String totalApartmentFloors) {
        this.totalApartmentFloors = totalApartmentFloors;
    }

    public String getTotalFlatsPerFloor() {
        return totalFlatsPerFloor;
    }

    public void setTotalFlatsPerFloor(String totalFlatsPerFloor) {
        this.totalFlatsPerFloor = totalFlatsPerFloor;
    }

    public String getFlatSituatedInFloor() {
        return flatSituatedInFloor;
    }

    public void setFlatSituatedInFloor(String flatSituatedInFloor) {
        this.flatSituatedInFloor = flatSituatedInFloor;
    }

    public String getDocumentLandArea() {
        return documentLandArea;
    }

    public void setDocumentLandArea(String documentLandArea) {
        this.documentLandArea = documentLandArea;
    }

    public Boolean getIsCompounded() {
        return isCompounded;
    }

    public void setIsCompounded(Boolean isCompounded) {
        this.isCompounded = isCompounded;
    }

    public int getDocumentLandAreaUnit() {
        return documentLandAreaUnit;
    }

    public void setDocumentLandAreaUnit(int documentLandAreaUnit) {
        this.documentLandAreaUnit = documentLandAreaUnit;
    }

    public String getMeasuredLandArea() {
        return measuredLandArea;
    }

    public void setMeasuredLandArea(String measuredLandArea) {
        this.measuredLandArea = measuredLandArea;
    }

    public int getMeasuredLandAreaUnit() {
        return measuredLandAreaUnit;
    }

    public void setMeasuredLandAreaUnit(int measuredLandAreaUnit) {
        this.measuredLandAreaUnit = measuredLandAreaUnit;
    }

    public String getAvgPercentageCompletion() {
        return avgPercentageCompletion;
    }

    public void setAvgPercentageCompletion(String avgPercentageCompletion) {
        this.avgPercentageCompletion = avgPercentageCompletion;
    }

    public String getDocumentFloorAreaTotal() {
        return documentFloorAreaTotal;
    }

    public void setDocumentFloorAreaTotal(String documentFloorAreaTotal) {
        this.documentFloorAreaTotal = documentFloorAreaTotal;
    }

    public String getMeasuredFloorAreaTotal() {
        return measuredFloorAreaTotal;
    }

    public void setMeasuredFloorAreaTotal(String measuredFloorAreaTotal) {
        this.measuredFloorAreaTotal = measuredFloorAreaTotal;
    }

    public String getTypeOfStructureId() {
        return typeOfStructureId;
    }

    public void setTypeOfStructureId(String typeOfStructureId) {
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

    public String getBasement() {
        return basement;
    }

    public void setBasement(String basement) {
        this.basement = basement;
    }



    public String getExteriorPaintId() {
        return exteriorPaintId;
    }

    public void setExteriorPaintId(String exteriorPaintId) {
        this.exteriorPaintId = exteriorPaintId;
    }

    public String getCarParkingId() {
        return carParkingId;
    }

    public void setCarParkingId(String carParkingId) {
        this.carParkingId = carParkingId;
    }

    public String getWaterAvailabilityId() {
        return waterAvailabilityId;
    }

    public void setWaterAvailabilityId(String waterAvailabilityId) {
        this.waterAvailabilityId = waterAvailabilityId;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getWingName() {
        return wingName;
    }

    public void setWingName(String wingName) {
        this.wingName = wingName;
    }

    public String getIntFloorId() {
        return intFloorId;
    }

    public void setIntFloorId(String intFloorId) {
        this.intFloorId = intFloorId;
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

    public String getIntPaintId() {
        return intPaintId;
    }

    public void setIntPaintId(String intPaintId) {
        this.intPaintId = intPaintId;
    }

    public String getIntRoofId() {
        return intRoofId;
    }

    public void setIntRoofId(String intRoofId) {
        this.intRoofId = intRoofId;
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

    public int getFloorKindId() {
        return floorKindId;
    }

    public void setFloorKindId(int floorKindId) {
        this.floorKindId = floorKindId;
    }

    public String getNoOfLiftInBuilding() {
        return noOfLiftInBuilding;
    }

    public void setNoOfLiftInBuilding(String noOfLiftInBuilding) {
        this.noOfLiftInBuilding = noOfLiftInBuilding;
    }
}
