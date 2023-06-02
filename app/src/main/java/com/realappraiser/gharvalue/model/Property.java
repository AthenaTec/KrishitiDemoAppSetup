
package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "PropertyModal")
public class Property {

    @PrimaryKey(autoGenerate = true)
    public int dummyID;

    @SerializedName("CaseId")
    @Expose
    @ColumnInfo(name = "CaseId")
    private int CaseId;
    @SerializedName("PropertyId")
    @Expose
    @ColumnInfo(name = "propertyId")
    private int propertyId;
    @SerializedName("EmployeeId")
    @Expose
    @ColumnInfo(name = "employeeId")
    private int employeeId;
    @SerializedName("ApplicantAddress")
    @Expose
    @ColumnInfo(name = "applicantAddress")
    private String applicantAddress;
    @SerializedName("Landmark")
    @Expose
    @ColumnInfo(name = "landmark")
    private String landmark;
    @SerializedName("Purpose")
    @Expose
    @ColumnInfo(name = "purpose")
    private String purpose;
    @SerializedName("NameOfPurchaser")
    @Expose
    @ColumnInfo(name = "nameOfPurchaser")
    private String nameOfPurchaser;
    @SerializedName("PropertyAddress")
    @Expose
    @ColumnInfo(name = "propertyAddress")
    private String propertyAddress;
    @SerializedName("MunicipalWard")
    @Expose
    @ColumnInfo(name = "municipalWard")
    private String municipalWard;
    @SerializedName("IsWithinMunicipalArea")
    @Expose
    @ColumnInfo(name = "isWithinMunicipalArea")
    private Boolean isWithinMunicipalArea;
    @SerializedName("IsPropertyInDemolitionList")
    @Expose
    @ColumnInfo(name = "isPropertyInDemolitionList")
    private Boolean isPropertyInDemolitionList;
    @SerializedName("VillageName")
    @Expose
    @ColumnInfo(name = "villageName")
    private String villageName;
    @SerializedName("ColonyName")
    @Expose
    @ColumnInfo(name = "colonyName")
    private String colonyName;
    @SerializedName("UnitNo")
    @Expose
    @ColumnInfo(name = "unitNo")
    private String unitNo;
    @SerializedName("PlotNo")
    @Expose
    @ColumnInfo(name = "plotNo")
    private String plotNo;
    @SerializedName("SurveyNo")
    @Expose
    @ColumnInfo(name = "surveyNo")
    private String surveyNo;
    @SerializedName("CtsNo")
    @Expose
    @ColumnInfo(name = "ctsNo")
    private String ctsNo;
    @SerializedName("AreaOfPlot")
    @Expose
    @ColumnInfo(name = "areaOfPlot")
    private String areaOfPlot;
    @SerializedName("Variable")
    @Expose
    @ColumnInfo(name = "variable")
    private String variable;
    @SerializedName("SurveyInPresenceOf")
    @Expose
    @ColumnInfo(name = "surveyInPresenceOf")
    private String surveyInPresenceOf;
    @SerializedName("NearestStation")
    @Expose
    @ColumnInfo(name = "nearestStation")
    private String nearestStation;
    @SerializedName("Distance")
    @Expose
    @ColumnInfo(name = "distance")
    private String distance;
    @SerializedName("PresentlyOccupiedId")
    @Expose
    @ColumnInfo(name = "presentlyOccupiedId")
    private String presentlyOccupiedId;
    @SerializedName("PresentlyOccupied")
    @Expose
    @ColumnInfo(name = "presentlyOccupied")
    private String presentlyOccupied;
    @SerializedName("NameOfSeller")
    @Expose
    @ColumnInfo(name = "nameOfSeller")
    private String nameOfSeller;
    @SerializedName("BoundryNorth")
    @Expose
    @ColumnInfo(name = "boundryNorth")
    private String boundryNorth;
    @SerializedName("BoundrySouth")
    @Expose
    @ColumnInfo(name = "boundrySouth")
    private String boundrySouth;
    @SerializedName("BoundryEast")
    @Expose
    @ColumnInfo(name = "boundryEast")
    private String boundryEast;
    @SerializedName("BoundryWest")
    @Expose
    @ColumnInfo(name = "boundryWest")
    private String boundryWest;
    @SerializedName("DocBoundryNorth")
    @Expose
    @ColumnInfo(name = "docBoundryNorth")
    private String docBoundryNorth;
    @SerializedName("DocBoundrySouth")
    @Expose
    @ColumnInfo(name = "docBoundrySouth")
    private String docBoundrySouth;
    @SerializedName("DocBoundryEast")
    @Expose
    @ColumnInfo(name = "docBoundryEast")
    private String docBoundryEast;
    @SerializedName("DocBoundryWest")
    @Expose
    @ColumnInfo(name = "docBoundryWest")
    private String docBoundryWest;
    @SerializedName("MatchBoundaryNorth")
    @Expose
    @ColumnInfo(name = "matchBoundaryNorth")
    private String matchBoundaryNorth;
    @SerializedName("MatchBoundarySouth")
    @Expose
    @ColumnInfo(name = "matchBoundarySouth")
    private String matchBoundarySouth;
    @SerializedName("MatchBoundaryEast")
    @Expose
    @ColumnInfo(name = "matchBoundaryEast")
    private String matchBoundaryEast;
    @SerializedName("MatchBoundaryWest")
    @Expose
    @ColumnInfo(name = "matchBoundaryWest")
    private String matchBoundaryWest;
    @SerializedName("NorthMeasure")
    @Expose
    @ColumnInfo(name = "northMeasure")
    private String northMeasure;
    @SerializedName("SouthMeasure")
    @Expose
    @ColumnInfo(name = "southMeasure")
    private String southMeasure;
    @SerializedName("EastMeasure")
    @Expose
    @ColumnInfo(name = "eastMeasure")
    private String eastMeasure;
    @SerializedName("WestMeasure")
    @Expose
    @ColumnInfo(name = "westMeasure")
    private String westMeasure;
    @SerializedName("DocNorthMeasure")
    @Expose
    @ColumnInfo(name = "docNorthMeasure")
    private String docNorthMeasure;
    @SerializedName("DocSouthMeasure")
    @Expose
    @ColumnInfo(name = "docSouthMeasure")
    private String docSouthMeasure;
    @SerializedName("DocEastMeasure")
    @Expose
    @ColumnInfo(name = "docEastMeasure")
    private String docEastMeasure;
    @SerializedName("DocWestMeasure")
    @Expose
    @ColumnInfo(name = "docWestMeasure")
    private String docWestMeasure;
    @SerializedName("BoundaryMeasureUnit")
    @Expose
    @ColumnInfo(name = "boundaryMeasureUnit")
    private String boundaryMeasureUnit;
    @SerializedName("DocSetBackLeft")
    @Expose
    @ColumnInfo(name = "docSetBackLeft")
    private String docSetBackLeft;
    @SerializedName("DocSetBackRight")
    @Expose
    @ColumnInfo(name = "docSetBackRight")
    private String docSetBackRight;
    @SerializedName("DocSetBackFront")
    @Expose
    @ColumnInfo(name = "docSetBackFront")
    private String docSetBackFront;
    @SerializedName("DocSetBackRear")
    @Expose
    @ColumnInfo(name = "docSetBackRear")
    private String docSetBackRear;
    @SerializedName("SetBackLeft")
    @Expose
    @ColumnInfo(name = "setBackLeft")
    private String setBackLeft;
    @SerializedName("SetBackRight")
    @Expose
    @ColumnInfo(name = "setBackRight")
    private String setBackRight;
    @SerializedName("SetBackFront")
    @Expose
    @ColumnInfo(name = "setBackFront")
    private String setBackFront;
    @SerializedName("SetBackRear")
    @Expose
    @ColumnInfo(name = "setBackRear")
    private String setBackRear;
    @SerializedName("TypeOfPropertyId")
    @Expose
    @ColumnInfo(name = "typeOfPropertyId")
    private int typeOfPropertyId;
    @SerializedName("FlatHallNo")
    @Expose
    @ColumnInfo(name = "flatHallNo")
    private String flatHallNo;
    @SerializedName("FlatKitchenNo")
    @Expose
    @ColumnInfo(name = "flatKitchenNo")
    private String flatKitchenNo;
    @SerializedName("FlatBedroomNo")
    @Expose
    @ColumnInfo(name = "flatBedroomNo")
    private String flatBedroomNo;
    @SerializedName("FlatDinningNo")
    @Expose
    @ColumnInfo(name = "flatDinningNo")
    private String flatDinningNo;
    @SerializedName("FlatBathNo")
    @Expose
    @ColumnInfo(name = "flatBathNo")
    private String flatBathNo;
    @SerializedName("FlatWcNo")
    @Expose
    @ColumnInfo(name = "flatWcNo")
    private String flatWcNo;
    @SerializedName("FlatAttBathWcNo")
    @Expose
    @ColumnInfo(name = "flatAttBathWcNo")
    private String flatAttBathWcNo;
    @SerializedName("FlatPassageNo")
    @Expose
    @ColumnInfo(name = "flatPassageNo")
    private String flatPassageNo;
    @SerializedName("FlatBalconyNo")
    @Expose
    @ColumnInfo(name = "flatBalconyNo")
    private String flatBalconyNo;
    @SerializedName("FlatFbNo")
    @Expose
    @ColumnInfo(name = "flatFbNo")
    private String flatFbNo;
    @SerializedName("FlatDbNo")
    @Expose
    @ColumnInfo(name = "flatDbNo")
    private String flatDbNo;
    @SerializedName("FlatTerraceNo")
    @Expose
    @ColumnInfo(name = "flatTerraceNo")
    private String flatTerraceNo;
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
    private String intPop;
    @SerializedName("TypeOfStructureId")
    @Expose
    @ColumnInfo(name = "typeOfStructureId")
    private String typeOfStructureId;
    @SerializedName("NoOfFloors")
    @Expose
    @ColumnInfo(name = "noOfFloors")
    private String noOfFloors;
    @SerializedName("Podiums")
    @Expose
    @ColumnInfo(name = "podiums")
    private String podiums;
    @SerializedName("Basement")
    @Expose
    @ColumnInfo(name = "basement")
    private String basement;
    @SerializedName("NoOfLiftInBuilding")
    @Expose
    @ColumnInfo(name = "noOfLiftInBuilding")
    private String noOfLiftInBuilding;
    @SerializedName("NoOfFlatPerFloor")
    @Expose
    @ColumnInfo(name = "noOfFlatPerFloor")
    private String noOfFlatPerFloor;
    @SerializedName("ConstructionId")
    @Expose
    @ColumnInfo(name = "constructionId")
    private String constructionId;
    @SerializedName("UnderconstructionLoading")
    @Expose
    @ColumnInfo(name = "underconstructionLoading")
    private String underconstructionLoading;
    @SerializedName("PlinthComp")
    @Expose
    @ColumnInfo(name = "plinthComp")
    private String plinthComp;
    @SerializedName("RccComp")
    @Expose
    @ColumnInfo(name = "rccComp")
    private String rccComp;
    @SerializedName("BrickWorkComp")
    @Expose
    @ColumnInfo(name = "brickWorkComp")
    private String brickWorkComp;
    @SerializedName("PlasteringIntComp")
    @Expose
    @ColumnInfo(name = "plasteringIntComp")
    private String plasteringIntComp;
    @SerializedName("PlasteringExtComp")
    @Expose
    @ColumnInfo(name = "plasteringExtComp")
    private String plasteringExtComp;
    @SerializedName("FlooringComp")
    @Expose
    @ColumnInfo(name = "flooringComp")
    private String flooringComp;
    @SerializedName("ElectricComp")
    @Expose
    @ColumnInfo(name = "electricComp")
    private String electricComp;
    @SerializedName("PlumbingComp")
    @Expose
    @ColumnInfo(name = "plumbingComp")
    private String plumbingComp;
    @SerializedName("WoodworkComp")
    @Expose
    @ColumnInfo(name = "woodworkComp")
    private String woodworkComp;
    @SerializedName("PaintingComp")
    @Expose
    @ColumnInfo(name = "paintingComp")
    private String paintingComp;
    @SerializedName("PresentConditionId")
    @Expose
    @ColumnInfo(name = "presentConditionId")
    private String presentConditionId;
    @SerializedName("QualityConstructionId")
    @Expose
    @ColumnInfo(name = "qualityConstructionId")
    private String qualityConstructionId;
    @SerializedName("AgeOfProperty")
    @Expose
    @ColumnInfo(name = "ageOfProperty")
    private String ageOfProperty;
    @SerializedName("ResidualLife")
    @Expose
    @ColumnInfo(name = "residualLife")
    private String residualLife;
    @SerializedName("TypeOfBuildingId")
    @Expose
    @ColumnInfo(name = "typeOfBuildingId")
    private String typeOfBuildingId;
    @SerializedName("MaintenanceOfBuildingId")
    @Expose
    @ColumnInfo(name = "maintenanceOfBuildingId")
    private String maintenanceOfBuildingId;
    @SerializedName("ExteriorPaintId")
    @Expose
    @ColumnInfo(name = "exteriorPaintId")
    private String exteriorPaintId;
    @SerializedName("GardenExist")
    @Expose
    @ColumnInfo(name = "gardenExist")
    private String gardenExist;
    @SerializedName("SeperateCompoundId")
    @Expose
    @ColumnInfo(name = "seperateCompoundId")
    private String seperateCompoundId;
    @SerializedName("PavingAroundBuildingId")
    @Expose
    @ColumnInfo(name = "pavingAroundBuildingId")
    private String pavingAroundBuildingId;
    @SerializedName("CarParkingId")
    @Expose
    @ColumnInfo(name = "carParkingId")
    private String carParkingId;
    @SerializedName("WaterAvailabilityId")
    @Expose
    @ColumnInfo(name = "waterAvailabilityId")
    private String waterAvailabilityId;
    @SerializedName("AmenityQualityId")
    @Expose
    @ColumnInfo(name = "amenityQualityId")
    private String amenityQualityId;
    @SerializedName("FittingQualityId")
    @Expose
    @ColumnInfo(name = "fittingQualityId")
    private String fittingQualityId;
    @SerializedName("Amenities")
    @Expose
    @ColumnInfo(name = "amenities")
    private String amenities;
    @SerializedName("WingNo")
    @Expose
    @ColumnInfo(name = "wingNo")
    private String wingNo;
    @SerializedName("WingName")
    @Expose
    @ColumnInfo(name = "wingName")
    private String wingName;
    @SerializedName("DocumentSeenId")
    @Expose
    @ColumnInfo(name = "documentSeenId")
    private String documentSeenId;
    @SerializedName("NameOfSociety")
    @Expose
    @ColumnInfo(name = "nameOfSociety")
    private String nameOfSociety;
    @SerializedName("SocietyRegnNo")
    @Expose
    @ColumnInfo(name = "societyRegnNo")
    private String societyRegnNo;
    @SerializedName("PropertyTaxReceiptNo")
    @Expose
    @ColumnInfo(name = "propertyTaxReceiptNo")
    private String propertyTaxReceiptNo;
    @SerializedName("PropertyTaxAmount")
    @Expose
    @ColumnInfo(name = "propertyTaxAmount")
    private String propertyTaxAmount;
    @SerializedName("PropertyTaxYear")
    @Expose
    @ColumnInfo(name = "propertyTaxYear")
    private String propertyTaxYear;
    @SerializedName("ShareCertificateNo")
    @Expose
    @ColumnInfo(name = "shareCertificateNo")
    private String shareCertificateNo;
    @SerializedName("ShareCertificateNoOfShares")
    @Expose
    @ColumnInfo(name = "shareCertificateNoOfShares")
    private String shareCertificateNoOfShares;
    @SerializedName("ShareCertificateFaceValue")
    @Expose
    @ColumnInfo(name = "shareCertificateFaceValue")
    private String shareCertificateFaceValue;
    @SerializedName("ShareCertificateDistinctiveNo")
    @Expose
    @ColumnInfo(name = "shareCertificateDistinctiveNo")
    private String shareCertificateDistinctiveNo;
    @SerializedName("PlanApprovedById")
    @Expose
    @ColumnInfo(name = "planApprovedById")
    private String planApprovedById;
    @SerializedName("CommencementNo")
    @Expose
    @ColumnInfo(name = "commencementNo")
    private String commencementNo;
    @SerializedName("CommencementDate")
    @Expose
    @ColumnInfo(name = "commencementDate")
    private String commencementDate;
    @SerializedName("OccupancyNo")
    @Expose
    @ColumnInfo(name = "occupancyNo")
    private String occupancyNo;
    @SerializedName("OccupancyDate")
    @Expose
    @ColumnInfo(name = "occupancyDate")
    private String occupancyDate;
    @SerializedName("PremisesDetails")
    @Expose
    @ColumnInfo(name = "premisesDetails")
    private String premisesDetails;
    @SerializedName("StampDutyDate")
    @Expose
    @ColumnInfo(name = "stampDutyDate")
    private String stampDutyDate;
    @SerializedName("AgreementDate")
    @Expose
    @ColumnInfo(name = "agreementDate")
    private String agreementDate;
    @SerializedName("AgreementAmount")
    @Expose
    @ColumnInfo(name = "agreementAmount")
    private String agreementAmount;
    @SerializedName("RegistrationNo")
    @Expose
    @ColumnInfo(name = "registrationNo")
    private String registrationNo;
    @SerializedName("RegistrationDate")
    @Expose
    @ColumnInfo(name = "registrationDate")
    private String registrationDate;
    @SerializedName("RegistrationValue")
    @Expose
    @ColumnInfo(name = "registrationValue")
    private String registrationValue;
    @SerializedName("Remarks")
    @Expose
    @ColumnInfo(name = "remarks")
    private String remarks;
    @SerializedName("OtherRemarks")
    @Expose
    @ColumnInfo(name = "otherRemarks")
    private String otherRemarks;
    @SerializedName("SpecialRemarks")
    @Expose
    @ColumnInfo(name = "specialRemarks")
    private String specialRemarks;
    @SerializedName("OtherDocuments")
    @Expose
    @ColumnInfo(name = "otherDocuments")
    private String otherDocuments;
    @SerializedName("RegistrationReceiptNo")
    @Expose
    @ColumnInfo(name = "registrationReceiptNo")
    private String registrationReceiptNo;
    @SerializedName("RegistrarsValue")
    @Expose
    @ColumnInfo(name = "registrarsValue")
    private String registrarsValue;
    @SerializedName("HallDim")
    @Expose
    @ColumnInfo(name = "hallDim")
    private String hallDim;
    @SerializedName("KitchenDim")
    @Expose
    @ColumnInfo(name = "kitchenDim")
    private String kitchenDim;
    @SerializedName("BedroomDim")
    @Expose
    @ColumnInfo(name = "bedroomDim")
    private String bedroomDim;
    @SerializedName("DinningDim")
    @Expose
    @ColumnInfo(name = "dinningDim")
    private String dinningDim;
    @SerializedName("PassageDim")
    @Expose
    @ColumnInfo(name = "passageDim")
    private String passageDim;
    @SerializedName("WcDim")
    @Expose
    @ColumnInfo(name = "wcDim")
    private String wcDim;
    @SerializedName("BathDim")
    @Expose
    @ColumnInfo(name = "bathDim")
    private String bathDim;
    @SerializedName("WbDim")
    @Expose
    @ColumnInfo(name = "wbDim")
    private String wbDim;
    @SerializedName("FbDim")
    @Expose
    @ColumnInfo(name = "fbDim")
    private String fbDim;
    @SerializedName("DbDim")
    @Expose
    @ColumnInfo(name = "dbDim")
    private String dbDim;
    @SerializedName("BalconyDim")
    @Expose
    @ColumnInfo(name = "balconyDim")
    private String balconyDim;
    @SerializedName("TerraceDim")
    @Expose
    @ColumnInfo(name = "terraceDim")
    private String terraceDim;
    @SerializedName("LatLongDetails")
    @Expose
    @ColumnInfo(name = "latLongDetails")
    private String latLongDetails;
    @SerializedName("BrokerDetails")
    @Expose
    @ColumnInfo(name = "brokerDetails")
    private String brokerDetails;
    @SerializedName("TotalMeasurement")
    @Expose
    @ColumnInfo(name = "totalMeasurement")
    private String totalMeasurement;
    @SerializedName("GharvalueInterior")
    @Expose
    @ColumnInfo(name = "gharvalueInterior")
    private String gharvalueInterior;
    @SerializedName("DescribeInteriors")
    @Expose
    @ColumnInfo(name = "describeInteriors")
    private String describeInteriors;
    @SerializedName("GharvalueAmenities")
    @Expose
    @ColumnInfo(name = "gharvalueAmenities")
    private String gharvalueAmenities;
    @SerializedName("DescribeAmenities")
    @Expose
    @ColumnInfo(name = "describeAmenities")
    private String describeAmenities;
    @SerializedName("GharvalueLocation")
    @Expose
    @ColumnInfo(name = "gharvalueLocation")
    private String gharvalueLocation;
    @SerializedName("DescribeLocation")
    @Expose
    @ColumnInfo(name = "describeLocation")
    private String describeLocation;
    @SerializedName("PlotDemarcatedAtSite")
    @Expose
    @ColumnInfo(name = "plotDemarcatedAtSite")
    private Boolean plotDemarcatedAtSite;
    @SerializedName("PropertyIdentificationChannelId")
    @Expose
    @ColumnInfo(name = "propertyIdentificationChannelId")
    private String propertyIdentificationChannelId;
    @SerializedName("PassageTypeId")
    @Expose
    @ColumnInfo(name = "passageTypeId")
    private String passageTypeId;
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
    @SerializedName("MarketabilityId")
    @Expose
    @ColumnInfo(name = "marketabilityId")
    private String marketabilityId;
    @SerializedName("PropertyAddressAtSite")
    @Expose
    @ColumnInfo(name = "propertyAddressAtSite")
    private String propertyAddressAtSite;
    @SerializedName("SameAsDocumentAddress")
    @Expose
    @ColumnInfo(name = "sameAsDocumentAddress")
    private Boolean sameAsDocumentAddress;
    @SerializedName("SameAsDocumentBoundary")
    @Expose
    @ColumnInfo(name = "sameAsDocumentBoundary")
    private Boolean sameAsDocumentBoundary;
    @SerializedName("SameAsDocumentDimension")
    @Expose
    @ColumnInfo(name = "sameAsDocumentDimension")
    private Boolean sameAsDocumentDimension;
    @SerializedName("SameAsDocumentSetBack")
    @Expose
    @ColumnInfo(name = "sameAsDocumentSetBack")
    private Boolean sameAsDocumentSetBack;
    @SerializedName("ApproachRoadConditionId")
    @Expose
    @ColumnInfo(name = "approachRoadConditionId")
    private Integer approachRoadConditionId;
    @SerializedName("LocalityCategoryId")
    @Expose
    @ColumnInfo(name = "localityCategoryId")
    private Integer localityCategoryId;
    @SerializedName("ClassId")
    @Expose
    @ColumnInfo(name = "classId")
    private Integer classId;
    @SerializedName("TenureId")
    @Expose
    @ColumnInfo(name = "tenureId")
    private Integer tenureId;
    @SerializedName("TypeOfLandId")
    @Expose
    @ColumnInfo(name = "typeOfLandId")
    private Integer typeOfLandId;
    @SerializedName("TypeLocalityId")
    @Expose
    @ColumnInfo(name = "typeLocalityId")
    private Integer typeLocalityId;

    @SerializedName("NameofVendor")
    @Expose
    @ColumnInfo(name = "NameofVendor")
    private String NameofVendor;

    @SerializedName("NameofVendorId")
    @Expose
    @ColumnInfo(name = "NameofVendorId")
    private Integer NameofVendorId;

    public String getNameofVendor() {
        return NameofVendor;
    }

    public void setNameofVendor(String nameofVendor) {
        NameofVendor = nameofVendor;
    }

    public Integer getNameofVendorId() {
        return NameofVendorId;
    }

    public void setNameofVendorId(Integer nameofVendorId) {
        NameofVendorId = nameofVendorId;
    }


    public Integer getTypeLocalityId() {
        return typeLocalityId;
    }

    public void setTypeLocalityId(Integer typeLocalityId) {
        this.typeLocalityId = typeLocalityId;
    }

    public Integer getApproachRoadConditionId() {
        return approachRoadConditionId;
    }

    public void setApproachRoadConditionId(Integer approachRoadConditionId) {
        this.approachRoadConditionId = approachRoadConditionId;
    }

    public Integer getLocalityCategoryId() {
        return localityCategoryId;
    }

    public void setLocalityCategoryId(Integer localityCategoryId) {
        this.localityCategoryId = localityCategoryId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTenureId() {
        return tenureId;
    }

    public void setTenureId(Integer tenureId) {
        this.tenureId = tenureId;
    }

    public Integer getTypeOfLandId() {
        return typeOfLandId;
    }

    public void setTypeOfLandId(Integer typeOfLandId) {
        this.typeOfLandId = typeOfLandId;
    }

    public int getCaseId() {
        return CaseId;
    }

    public void setCaseId(int caseId) {
        CaseId = caseId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getApplicantAddress() {
        return applicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getNameOfPurchaser() {
        return nameOfPurchaser;
    }

    public void setNameOfPurchaser(String nameOfPurchaser) {
        this.nameOfPurchaser = nameOfPurchaser;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getMunicipalWard() {
        return municipalWard;
    }

    public void setMunicipalWard(String municipalWard) {
        this.municipalWard = municipalWard;
    }

    public Boolean getIsWithinMunicipalArea() {
        return isWithinMunicipalArea;
    }

    public void setIsWithinMunicipalArea(Boolean isWithinMunicipalArea) {
        this.isWithinMunicipalArea = isWithinMunicipalArea;
    }

    public Boolean getIsPropertyInDemolitionList() {
        return isPropertyInDemolitionList;
    }

    public void setIsPropertyInDemolitionList(Boolean isPropertyInDemolitionList) {
        this.isPropertyInDemolitionList = isPropertyInDemolitionList;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getColonyName() {
        return colonyName;
    }

    public void setColonyName(String colonyName) {
        this.colonyName = colonyName;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getPlotNo() {
        return plotNo;
    }

    public void setPlotNo(String plotNo) {
        this.plotNo = plotNo;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getCtsNo() {
        return ctsNo;
    }

    public void setCtsNo(String ctsNo) {
        this.ctsNo = ctsNo;
    }

    public String getAreaOfPlot() {
        return areaOfPlot;
    }

    public void setAreaOfPlot(String areaOfPlot) {
        this.areaOfPlot = areaOfPlot;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getSurveyInPresenceOf() {
        return surveyInPresenceOf;
    }

    public void setSurveyInPresenceOf(String surveyInPresenceOf) {
        this.surveyInPresenceOf = surveyInPresenceOf;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPresentlyOccupiedId() {
        return presentlyOccupiedId;
    }

    public void setPresentlyOccupiedId(String presentlyOccupiedId) {
        this.presentlyOccupiedId = presentlyOccupiedId;
    }

    public String getPresentlyOccupied() {
        return presentlyOccupied;
    }

    public void setPresentlyOccupied(String presentlyOccupied) {
        this.presentlyOccupied = presentlyOccupied;
    }

    public String getNameOfSeller() {
        return nameOfSeller;
    }

    public void setNameOfSeller(String nameOfSeller) {
        this.nameOfSeller = nameOfSeller;
    }

    public String getBoundryNorth() {
        return boundryNorth;
    }

    public void setBoundryNorth(String boundryNorth) {
        this.boundryNorth = boundryNorth;
    }

    public String getBoundrySouth() {
        return boundrySouth;
    }

    public void setBoundrySouth(String boundrySouth) {
        this.boundrySouth = boundrySouth;
    }

    public String getBoundryEast() {
        return boundryEast;
    }

    public void setBoundryEast(String boundryEast) {
        this.boundryEast = boundryEast;
    }

    public String getBoundryWest() {
        return boundryWest;
    }

    public void setBoundryWest(String boundryWest) {
        this.boundryWest = boundryWest;
    }

    public String getDocBoundryNorth() {
        return docBoundryNorth;
    }

    public void setDocBoundryNorth(String docBoundryNorth) {
        this.docBoundryNorth = docBoundryNorth;
    }

    public String getDocBoundrySouth() {
        return docBoundrySouth;
    }

    public void setDocBoundrySouth(String docBoundrySouth) {
        this.docBoundrySouth = docBoundrySouth;
    }

    public String getDocBoundryEast() {
        return docBoundryEast;
    }

    public void setDocBoundryEast(String docBoundryEast) {
        this.docBoundryEast = docBoundryEast;
    }

    public String getDocBoundryWest() {
        return docBoundryWest;
    }

    public void setDocBoundryWest(String docBoundryWest) {
        this.docBoundryWest = docBoundryWest;
    }

    public String getMatchBoundaryNorth() {
        return matchBoundaryNorth;
    }

    public void setMatchBoundaryNorth(String matchBoundaryNorth) {
        this.matchBoundaryNorth = matchBoundaryNorth;
    }

    public String getMatchBoundarySouth() {
        return matchBoundarySouth;
    }

    public void setMatchBoundarySouth(String matchBoundarySouth) {
        this.matchBoundarySouth = matchBoundarySouth;
    }

    public String getMatchBoundaryEast() {
        return matchBoundaryEast;
    }

    public void setMatchBoundaryEast(String matchBoundaryEast) {
        this.matchBoundaryEast = matchBoundaryEast;
    }

    public String getMatchBoundaryWest() {
        return matchBoundaryWest;
    }

    public void setMatchBoundaryWest(String matchBoundaryWest) {
        this.matchBoundaryWest = matchBoundaryWest;
    }

    public String getNorthMeasure() {
        return northMeasure;
    }

    public void setNorthMeasure(String northMeasure) {
        this.northMeasure = northMeasure;
    }

    public String getSouthMeasure() {
        return southMeasure;
    }

    public void setSouthMeasure(String southMeasure) {
        this.southMeasure = southMeasure;
    }

    public String getEastMeasure() {
        return eastMeasure;
    }

    public void setEastMeasure(String eastMeasure) {
        this.eastMeasure = eastMeasure;
    }

    public String getWestMeasure() {
        return westMeasure;
    }

    public void setWestMeasure(String westMeasure) {
        this.westMeasure = westMeasure;
    }

    public String getDocNorthMeasure() {
        return docNorthMeasure;
    }

    public void setDocNorthMeasure(String docNorthMeasure) {
        this.docNorthMeasure = docNorthMeasure;
    }

    public String getDocSouthMeasure() {
        return docSouthMeasure;
    }

    public void setDocSouthMeasure(String docSouthMeasure) {
        this.docSouthMeasure = docSouthMeasure;
    }

    public String getDocEastMeasure() {
        return docEastMeasure;
    }

    public void setDocEastMeasure(String docEastMeasure) {
        this.docEastMeasure = docEastMeasure;
    }

    public String getDocWestMeasure() {
        return docWestMeasure;
    }

    public void setDocWestMeasure(String docWestMeasure) {
        this.docWestMeasure = docWestMeasure;
    }

    public String getBoundaryMeasureUnit() {
        return boundaryMeasureUnit;
    }

    public void setBoundaryMeasureUnit(String boundaryMeasureUnit) {
        this.boundaryMeasureUnit = boundaryMeasureUnit;
    }

    public String getDocSetBackLeft() {
        return docSetBackLeft;
    }

    public void setDocSetBackLeft(String docSetBackLeft) {
        this.docSetBackLeft = docSetBackLeft;
    }

    public String getDocSetBackRight() {
        return docSetBackRight;
    }

    public void setDocSetBackRight(String docSetBackRight) {
        this.docSetBackRight = docSetBackRight;
    }

    public String getDocSetBackFront() {
        return docSetBackFront;
    }

    public void setDocSetBackFront(String docSetBackFront) {
        this.docSetBackFront = docSetBackFront;
    }

    public String getDocSetBackRear() {
        return docSetBackRear;
    }

    public void setDocSetBackRear(String docSetBackRear) {
        this.docSetBackRear = docSetBackRear;
    }

    public String getSetBackLeft() {
        return setBackLeft;
    }

    public void setSetBackLeft(String setBackLeft) {
        this.setBackLeft = setBackLeft;
    }

    public String getSetBackRight() {
        return setBackRight;
    }

    public void setSetBackRight(String setBackRight) {
        this.setBackRight = setBackRight;
    }

    public String getSetBackFront() {
        return setBackFront;
    }

    public void setSetBackFront(String setBackFront) {
        this.setBackFront = setBackFront;
    }

    public String getSetBackRear() {
        return setBackRear;
    }

    public void setSetBackRear(String setBackRear) {
        this.setBackRear = setBackRear;
    }

    public int getTypeOfPropertyId() {
        return typeOfPropertyId;
    }

    public void setTypeOfPropertyId(int typeOfPropertyId) {
        this.typeOfPropertyId = typeOfPropertyId;
    }

    public String getFlatHallNo() {
        return flatHallNo;
    }

    public void setFlatHallNo(String flatHallNo) {
        this.flatHallNo = flatHallNo;
    }

    public String getFlatKitchenNo() {
        return flatKitchenNo;
    }

    public void setFlatKitchenNo(String flatKitchenNo) {
        this.flatKitchenNo = flatKitchenNo;
    }

    public String getFlatBedroomNo() {
        return flatBedroomNo;
    }

    public void setFlatBedroomNo(String flatBedroomNo) {
        this.flatBedroomNo = flatBedroomNo;
    }

    public String getFlatDinningNo() {
        return flatDinningNo;
    }

    public void setFlatDinningNo(String flatDinningNo) {
        this.flatDinningNo = flatDinningNo;
    }

    public String getFlatBathNo() {
        return flatBathNo;
    }

    public void setFlatBathNo(String flatBathNo) {
        this.flatBathNo = flatBathNo;
    }

    public String getFlatWcNo() {
        return flatWcNo;
    }

    public void setFlatWcNo(String flatWcNo) {
        this.flatWcNo = flatWcNo;
    }

    public String getFlatAttBathWcNo() {
        return flatAttBathWcNo;
    }

    public void setFlatAttBathWcNo(String flatAttBathWcNo) {
        this.flatAttBathWcNo = flatAttBathWcNo;
    }

    public String getFlatPassageNo() {
        return flatPassageNo;
    }

    public void setFlatPassageNo(String flatPassageNo) {
        this.flatPassageNo = flatPassageNo;
    }

    public String getFlatBalconyNo() {
        return flatBalconyNo;
    }

    public void setFlatBalconyNo(String flatBalconyNo) {
        this.flatBalconyNo = flatBalconyNo;
    }

    public String getFlatFbNo() {
        return flatFbNo;
    }

    public void setFlatFbNo(String flatFbNo) {
        this.flatFbNo = flatFbNo;
    }

    public String getFlatDbNo() {
        return flatDbNo;
    }

    public void setFlatDbNo(String flatDbNo) {
        this.flatDbNo = flatDbNo;
    }

    public String getFlatTerraceNo() {
        return flatTerraceNo;
    }

    public void setFlatTerraceNo(String flatTerraceNo) {
        this.flatTerraceNo = flatTerraceNo;
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

    public String getIntPop() {
        return intPop;
    }

    public void setIntPop(String intPop) {
        this.intPop = intPop;
    }

    public String getTypeOfStructureId() {
        return typeOfStructureId;
    }

    public void setTypeOfStructureId(String typeOfStructureId) {
        this.typeOfStructureId = typeOfStructureId;
    }

    public String getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(String noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public String getPodiums() {
        return podiums;
    }

    public void setPodiums(String podiums) {
        this.podiums = podiums;
    }

    public String getBasement() {
        return basement;
    }

    public void setBasement(String basement) {
        this.basement = basement;
    }

    public String getNoOfLiftInBuilding() {
        return noOfLiftInBuilding;
    }

    public void setNoOfLiftInBuilding(String noOfLiftInBuilding) {
        this.noOfLiftInBuilding = noOfLiftInBuilding;
    }

    public String getNoOfFlatPerFloor() {
        return noOfFlatPerFloor;
    }

    public void setNoOfFlatPerFloor(String noOfFlatPerFloor) {
        this.noOfFlatPerFloor = noOfFlatPerFloor;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public String getUnderconstructionLoading() {
        return underconstructionLoading;
    }

    public void setUnderconstructionLoading(String underconstructionLoading) {
        this.underconstructionLoading = underconstructionLoading;
    }

    public String getPlinthComp() {
        return plinthComp;
    }

    public void setPlinthComp(String plinthComp) {
        this.plinthComp = plinthComp;
    }

    public String getRccComp() {
        return rccComp;
    }

    public void setRccComp(String rccComp) {
        this.rccComp = rccComp;
    }

    public String getBrickWorkComp() {
        return brickWorkComp;
    }

    public void setBrickWorkComp(String brickWorkComp) {
        this.brickWorkComp = brickWorkComp;
    }

    public String getPlasteringIntComp() {
        return plasteringIntComp;
    }

    public void setPlasteringIntComp(String plasteringIntComp) {
        this.plasteringIntComp = plasteringIntComp;
    }

    public String getPlasteringExtComp() {
        return plasteringExtComp;
    }

    public void setPlasteringExtComp(String plasteringExtComp) {
        this.plasteringExtComp = plasteringExtComp;
    }

    public String getFlooringComp() {
        return flooringComp;
    }

    public void setFlooringComp(String flooringComp) {
        this.flooringComp = flooringComp;
    }

    public String getElectricComp() {
        return electricComp;
    }

    public void setElectricComp(String electricComp) {
        this.electricComp = electricComp;
    }

    public String getPlumbingComp() {
        return plumbingComp;
    }

    public void setPlumbingComp(String plumbingComp) {
        this.plumbingComp = plumbingComp;
    }

    public String getWoodworkComp() {
        return woodworkComp;
    }

    public void setWoodworkComp(String woodworkComp) {
        this.woodworkComp = woodworkComp;
    }

    public String getPaintingComp() {
        return paintingComp;
    }

    public void setPaintingComp(String paintingComp) {
        this.paintingComp = paintingComp;
    }

    public String getPresentConditionId() {
        return presentConditionId;
    }

    public void setPresentConditionId(String presentConditionId) {
        this.presentConditionId = presentConditionId;
    }

    public String getQualityConstructionId() {
        return qualityConstructionId;
    }

    public void setQualityConstructionId(String qualityConstructionId) {
        this.qualityConstructionId = qualityConstructionId;
    }

    public String getAgeOfProperty() {
        return ageOfProperty;
    }

    public void setAgeOfProperty(String ageOfProperty) {
        this.ageOfProperty = ageOfProperty;
    }

    public String getResidualLife() {
        return residualLife;
    }

    public void setResidualLife(String residualLife) {
        this.residualLife = residualLife;
    }

    public String getTypeOfBuildingId() {
        return typeOfBuildingId;
    }

    public void setTypeOfBuildingId(String typeOfBuildingId) {
        this.typeOfBuildingId = typeOfBuildingId;
    }

    public String getMaintenanceOfBuildingId() {
        return maintenanceOfBuildingId;
    }

    public void setMaintenanceOfBuildingId(String maintenanceOfBuildingId) {
        this.maintenanceOfBuildingId = maintenanceOfBuildingId;
    }

    public String getExteriorPaintId() {
        return exteriorPaintId;
    }

    public void setExteriorPaintId(String exteriorPaintId) {
        this.exteriorPaintId = exteriorPaintId;
    }

    public String getGardenExist() {
        return gardenExist;
    }

    public void setGardenExist(String gardenExist) {
        this.gardenExist = gardenExist;
    }

    public String getSeperateCompoundId() {
        return seperateCompoundId;
    }

    public void setSeperateCompoundId(String seperateCompoundId) {
        this.seperateCompoundId = seperateCompoundId;
    }

    public String getPavingAroundBuildingId() {
        return pavingAroundBuildingId;
    }

    public void setPavingAroundBuildingId(String pavingAroundBuildingId) {
        this.pavingAroundBuildingId = pavingAroundBuildingId;
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

    public String getAmenityQualityId() {
        return amenityQualityId;
    }

    public void setAmenityQualityId(String amenityQualityId) {
        this.amenityQualityId = amenityQualityId;
    }

    public String getFittingQualityId() {
        return fittingQualityId;
    }

    public void setFittingQualityId(String fittingQualityId) {
        this.fittingQualityId = fittingQualityId;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getWingNo() {
        return wingNo;
    }

    public void setWingNo(String wingNo) {
        this.wingNo = wingNo;
    }

    public String getWingName() {
        return wingName;
    }

    public void setWingName(String wingName) {
        this.wingName = wingName;
    }

    public String getDocumentSeenId() {
        return documentSeenId;
    }

    public void setDocumentSeenId(String documentSeenId) {
        this.documentSeenId = documentSeenId;
    }

    public String getNameOfSociety() {
        return nameOfSociety;
    }

    public void setNameOfSociety(String nameOfSociety) {
        this.nameOfSociety = nameOfSociety;
    }

    public String getSocietyRegnNo() {
        return societyRegnNo;
    }

    public void setSocietyRegnNo(String societyRegnNo) {
        this.societyRegnNo = societyRegnNo;
    }

    public String getPropertyTaxReceiptNo() {
        return propertyTaxReceiptNo;
    }

    public void setPropertyTaxReceiptNo(String propertyTaxReceiptNo) {
        this.propertyTaxReceiptNo = propertyTaxReceiptNo;
    }

    public String getPropertyTaxAmount() {
        return propertyTaxAmount;
    }

    public void setPropertyTaxAmount(String propertyTaxAmount) {
        this.propertyTaxAmount = propertyTaxAmount;
    }

    public String getPropertyTaxYear() {
        return propertyTaxYear;
    }

    public void setPropertyTaxYear(String propertyTaxYear) {
        this.propertyTaxYear = propertyTaxYear;
    }

    public String getShareCertificateNo() {
        return shareCertificateNo;
    }

    public void setShareCertificateNo(String shareCertificateNo) {
        this.shareCertificateNo = shareCertificateNo;
    }

    public String getShareCertificateNoOfShares() {
        return shareCertificateNoOfShares;
    }

    public void setShareCertificateNoOfShares(String shareCertificateNoOfShares) {
        this.shareCertificateNoOfShares = shareCertificateNoOfShares;
    }

    public String getShareCertificateFaceValue() {
        return shareCertificateFaceValue;
    }

    public void setShareCertificateFaceValue(String shareCertificateFaceValue) {
        this.shareCertificateFaceValue = shareCertificateFaceValue;
    }

    public String getShareCertificateDistinctiveNo() {
        return shareCertificateDistinctiveNo;
    }

    public void setShareCertificateDistinctiveNo(String shareCertificateDistinctiveNo) {
        this.shareCertificateDistinctiveNo = shareCertificateDistinctiveNo;
    }

    public String getPlanApprovedById() {
        return planApprovedById;
    }

    public void setPlanApprovedById(String planApprovedById) {
        this.planApprovedById = planApprovedById;
    }

    public String getCommencementNo() {
        return commencementNo;
    }

    public void setCommencementNo(String commencementNo) {
        this.commencementNo = commencementNo;
    }

    public String getCommencementDate() {
        return commencementDate;
    }

    public void setCommencementDate(String commencementDate) {
        this.commencementDate = commencementDate;
    }

    public String getOccupancyNo() {
        return occupancyNo;
    }

    public void setOccupancyNo(String occupancyNo) {
        this.occupancyNo = occupancyNo;
    }

    public String getOccupancyDate() {
        return occupancyDate;
    }

    public void setOccupancyDate(String occupancyDate) {
        this.occupancyDate = occupancyDate;
    }

    public String getPremisesDetails() {
        return premisesDetails;
    }

    public void setPremisesDetails(String premisesDetails) {
        this.premisesDetails = premisesDetails;
    }

    public String getStampDutyDate() {
        return stampDutyDate;
    }

    public void setStampDutyDate(String stampDutyDate) {
        this.stampDutyDate = stampDutyDate;
    }

    public String getAgreementDate() {
        return agreementDate;
    }

    public void setAgreementDate(String agreementDate) {
        this.agreementDate = agreementDate;
    }

    public String getAgreementAmount() {
        return agreementAmount;
    }

    public void setAgreementAmount(String agreementAmount) {
        this.agreementAmount = agreementAmount;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationValue() {
        return registrationValue;
    }

    public void setRegistrationValue(String registrationValue) {
        this.registrationValue = registrationValue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOtherRemarks() {
        return otherRemarks;
    }

    public void setOtherRemarks(String otherRemarks) {
        this.otherRemarks = otherRemarks;
    }

    public String getSpecialRemarks() {
        return specialRemarks;
    }

    public void setSpecialRemarks(String specialRemarks) {
        this.specialRemarks = specialRemarks;
    }

    public String getOtherDocuments() {
        return otherDocuments;
    }

    public void setOtherDocuments(String otherDocuments) {
        this.otherDocuments = otherDocuments;
    }

    public String getRegistrationReceiptNo() {
        return registrationReceiptNo;
    }

    public void setRegistrationReceiptNo(String registrationReceiptNo) {
        this.registrationReceiptNo = registrationReceiptNo;
    }

    public String getRegistrarsValue() {
        return registrarsValue;
    }

    public void setRegistrarsValue(String registrarsValue) {
        this.registrarsValue = registrarsValue;
    }

    public String getHallDim() {
        return hallDim;
    }

    public void setHallDim(String hallDim) {
        this.hallDim = hallDim;
    }

    public String getKitchenDim() {
        return kitchenDim;
    }

    public void setKitchenDim(String kitchenDim) {
        this.kitchenDim = kitchenDim;
    }

    public String getBedroomDim() {
        return bedroomDim;
    }

    public void setBedroomDim(String bedroomDim) {
        this.bedroomDim = bedroomDim;
    }

    public String getDinningDim() {
        return dinningDim;
    }

    public void setDinningDim(String dinningDim) {
        this.dinningDim = dinningDim;
    }

    public String getPassageDim() {
        return passageDim;
    }

    public void setPassageDim(String passageDim) {
        this.passageDim = passageDim;
    }

    public String getWcDim() {
        return wcDim;
    }

    public void setWcDim(String wcDim) {
        this.wcDim = wcDim;
    }

    public String getBathDim() {
        return bathDim;
    }

    public void setBathDim(String bathDim) {
        this.bathDim = bathDim;
    }

    public String getWbDim() {
        return wbDim;
    }

    public void setWbDim(String wbDim) {
        this.wbDim = wbDim;
    }

    public String getFbDim() {
        return fbDim;
    }

    public void setFbDim(String fbDim) {
        this.fbDim = fbDim;
    }

    public String getDbDim() {
        return dbDim;
    }

    public void setDbDim(String dbDim) {
        this.dbDim = dbDim;
    }

    public String getBalconyDim() {
        return balconyDim;
    }

    public void setBalconyDim(String balconyDim) {
        this.balconyDim = balconyDim;
    }

    public String getTerraceDim() {
        return terraceDim;
    }

    public void setTerraceDim(String terraceDim) {
        this.terraceDim = terraceDim;
    }

    public String getLatLongDetails() {
        return latLongDetails;
    }

    public void setLatLongDetails(String latLongDetails) {
        this.latLongDetails = latLongDetails;
    }

    public String getBrokerDetails() {
        return brokerDetails;
    }

    public void setBrokerDetails(String brokerDetails) {
        this.brokerDetails = brokerDetails;
    }

    public String getTotalMeasurement() {
        return totalMeasurement;
    }

    public void setTotalMeasurement(String totalMeasurement) {
        this.totalMeasurement = totalMeasurement;
    }

    public String getGharvalueInterior() {
        return gharvalueInterior;
    }

    public void setGharvalueInterior(String gharvalueInterior) {
        this.gharvalueInterior = gharvalueInterior;
    }

    public String getDescribeInteriors() {
        return describeInteriors;
    }

    public void setDescribeInteriors(String describeInteriors) {
        this.describeInteriors = describeInteriors;
    }

    public String getGharvalueAmenities() {
        return gharvalueAmenities;
    }

    public void setGharvalueAmenities(String gharvalueAmenities) {
        this.gharvalueAmenities = gharvalueAmenities;
    }

    public String getDescribeAmenities() {
        return describeAmenities;
    }

    public void setDescribeAmenities(String describeAmenities) {
        this.describeAmenities = describeAmenities;
    }

    public String getGharvalueLocation() {
        return gharvalueLocation;
    }

    public void setGharvalueLocation(String gharvalueLocation) {
        this.gharvalueLocation = gharvalueLocation;
    }

    public String getDescribeLocation() {
        return describeLocation;
    }

    public void setDescribeLocation(String describeLocation) {
        this.describeLocation = describeLocation;
    }

    public Boolean getPlotDemarcatedAtSite() {
        return plotDemarcatedAtSite;
    }

    public void setPlotDemarcatedAtSite(Boolean plotDemarcatedAtSite) {
        this.plotDemarcatedAtSite = plotDemarcatedAtSite;
    }

    public String getPropertyIdentificationChannelId() {
        return propertyIdentificationChannelId;
    }

    public void setPropertyIdentificationChannelId(String propertyIdentificationChannelId) {
        this.propertyIdentificationChannelId = propertyIdentificationChannelId;
    }

    public String getPassageTypeId() {
        return passageTypeId;
    }

    public void setPassageTypeId(String passageTypeId) {
        this.passageTypeId = passageTypeId;
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

    public String getMarketabilityId() {
        return marketabilityId;
    }

    public void setMarketabilityId(String marketabilityId) {
        this.marketabilityId = marketabilityId;
    }

    public String getPropertyAddressAtSite() {
        return propertyAddressAtSite;
    }

    public void setPropertyAddressAtSite(String propertyAddressAtSite) {
        this.propertyAddressAtSite = propertyAddressAtSite;
    }

    public Boolean getSameAsDocumentAddress() {
        return sameAsDocumentAddress;
    }

    public void setSameAsDocumentAddress(Boolean sameAsDocumentAddress) {
        this.sameAsDocumentAddress = sameAsDocumentAddress;
    }

    public Boolean getSameAsDocumentBoundary() {
        return sameAsDocumentBoundary;
    }

    public void setSameAsDocumentBoundary(Boolean sameAsDocumentBoundary) {
        this.sameAsDocumentBoundary = sameAsDocumentBoundary;
    }

    public Boolean getSameAsDocumentDimension() {
        return sameAsDocumentDimension;
    }

    public void setSameAsDocumentDimension(Boolean sameAsDocumentDimension) {
        this.sameAsDocumentDimension = sameAsDocumentDimension;
    }

    public Boolean getSameAsDocumentSetBack() {
        return sameAsDocumentSetBack;
    }

    public void setSameAsDocumentSetBack(Boolean sameAsDocumentSetBack) {
        this.sameAsDocumentSetBack = sameAsDocumentSetBack;
    }

}
