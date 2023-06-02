
package com.realappraiser.gharvalue.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "IndPropertyValuationModal")

public class IndPropertyValuation {

    @PrimaryKey(autoGenerate = true)
    public int dummyID;

    @SerializedName("CaseId")
    @Expose
    @ColumnInfo(name = "caseId")
    private int caseId;
    @SerializedName("GuidelineRate")
    @Expose
    @ColumnInfo(name = "guidelineRate")
    private String guidelineRate;
    @SerializedName("GuidelineRateUnit")
    @Expose
    @ColumnInfo(name = "guidelineRateUnit")
    private String guidelineRateUnit;
    @SerializedName("GuidelineValue")
    @Expose
    @ColumnInfo(name = "guidelineValue")
    private String guidelineValue;
    @SerializedName("DocumentLandRate")
    @Expose
    @ColumnInfo(name = "documentLandRate")
    private String documentLandRate;
    @SerializedName("DocumentLandValue")
    @Expose
    @ColumnInfo(name = "documentLandValue")
    private String documentLandValue;
    @SerializedName("MeasuredLandRate")
    @Expose
    @ColumnInfo(name = "measuredLandRate")
    private String measuredLandRate;
    @SerializedName("MeasuredLandValue")
    @Expose
    @ColumnInfo(name = "measuredLandValue")
    private String measuredLandValue;
    @SerializedName("DocumentedLandValueSel")
    @Expose
    @ColumnInfo(name = "documentedLandValueSel")
    private Boolean documentedLandValueSel;
    @SerializedName("MeasuredLandValueSel")
    @Expose
    @ColumnInfo(name = "measuredLandValueSel")
    private Boolean measuredLandValueSel;
    @SerializedName("DocumentedConstrValueSel")
    @Expose
    @ColumnInfo(name = "documentedConstrValueSel")
    private Boolean documentedConstrValueSel;
    @SerializedName("MeasuredConstrValueSel")
    @Expose
    @ColumnInfo(name = "measuredConstrValueSel")
    private Boolean measuredConstrValueSel;
    @SerializedName("TotalConstructionValue")
    @Expose
    @ColumnInfo(name = "totalConstructionValue")
    private String totalConstructionValue;
    @SerializedName("TotalPropertyValue")
    @Expose
    @ColumnInfo(name = "totalPropertyValue")
    private String totalPropertyValue;
    @SerializedName("BuildingDepreciationValue")
    @Expose
    @ColumnInfo(name = "buildingDepreciationValue")
    private String buildingDepreciationValue;
    @SerializedName("ProposedValuation")
    @Expose
    @ColumnInfo(name = "proposedValuation")
    private String proposedValuation;
    @SerializedName("ProposedValuationComments")
    @Expose
    @ColumnInfo(name = "proposedValuationComments")
    private String proposedValuationComments;
    @SerializedName("TotalValueAtCompletion")
    @Expose
    @ColumnInfo(name = "totalValueAtCompletion")
    private String totalValueAtCompletion;
    @SerializedName("InsuranceValue")
    @Expose
    @ColumnInfo(name = "insuranceValue")
    private String insuranceValue;
    @SerializedName("RealizationPercentage")
    @Expose
    @ColumnInfo(name = "realizationPercentage")
    private String realizationPercentage;
    @SerializedName("DistressPercentage")
    @Expose
    @ColumnInfo(name = "distressPercentage")
    private String distressPercentage;
    @SerializedName("RealizationValue")
    @Expose
    @ColumnInfo(name = "realizationValue")
    private String realizationValue;
    @SerializedName("DistressValue")
    @Expose
    @ColumnInfo(name = "distressValue")
    private String distressValue;
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
    @SerializedName("CompletionPercentage")
    @Expose
    @ColumnInfo(name = "completionPercentage")
    private String completionPercentage;
    @SerializedName("TotalPropertyValueDepreciation")
    @Expose
    @ColumnInfo(name = "totalPropertyValueDepreciation")
    private String totalPropertyValueDepreciation;
    @SerializedName("DistressValueDepreciation")
    @Expose
    @ColumnInfo(name = "distressValueDepreciation")
    private String distressValueDepreciation;
    @SerializedName("CarpetAreaPercentage")
    @Expose
    @ColumnInfo(name = "carpetAreaPercentage")
    private String carpetAreaPercentage;
    @SerializedName("CarpetAreaTypeId")
    @Expose
    @ColumnInfo(name = "carpetAreaTypeId")
    private String carpetAreaTypeId;
    @SerializedName("RealizationValueDepreciation")
    @Expose
    @ColumnInfo(name = "realizationValueDepreciation")
    private String realizationValueDepreciation;
    @SerializedName("RealizationLandValue")
    @Expose
    @ColumnInfo(name = "realizationLandValue")
    private String realizationLandValue;
    @SerializedName("ConstructionDLCRate")
    @Expose
    @ColumnInfo(name = "constructionDLCRate")
    private String constructionDLCRate;
    @SerializedName("ConstructionDLCRateUnit")
    @Expose
    @ColumnInfo(name = "constructionDLCRateUnit")
    private String constructionDLCRateUnit;
    @SerializedName("ConstructionDLCValue")
    @Expose
    @ColumnInfo(name = "constructionDLCValue")
    private String constructionDLCValue;
    @SerializedName("CarpetArea")
    @Expose
    @ColumnInfo(name = "carpetArea")
    private String carpetArea;
    @SerializedName("BuildUpArea")
    @Expose
    @ColumnInfo(name = "buildUpArea")
    private String buildUpArea;
    @SerializedName("SuperBuildUpArea")
    @Expose
    @ColumnInfo(name = "superBuildUpArea")
    private String superBuildUpArea;
    @SerializedName("CarpetAreaUnit")
    @Expose
    @ColumnInfo(name = "carpetAreaUnit")
    private String carpetAreaUnit;
    @SerializedName("SelectedConstructionArea")
    @Expose
    @ColumnInfo(name = "selectedConstructionArea")
    private String selectedConstructionArea;
    @SerializedName("BAULoadingPercentage")
    @Expose
    @ColumnInfo(name = "bAULoadingPercentage")
    private String bAULoadingPercentage;
    @SerializedName("ConstructionRate")
    @Expose
    @ColumnInfo(name = "constructionRate")
    private String constructionRate;
    @SerializedName("InsuredConstructionRate")
    @Expose
    @ColumnInfo(name = "insuredConstructionRate")
    private String insuredConstructionRate;

    @SerializedName("TerraceArea")
    @Expose
    @ColumnInfo(name = "terraceArea")
    private String terraceArea;
    @SerializedName("TerraceRate")
    @Expose
    @ColumnInfo(name = "terraceRate")
    private String terraceRate;
    @SerializedName("TerraceValue")
    @Expose
    @ColumnInfo(name = "terraceValue")
    private String terraceValue;


    @SerializedName("SaleableLoadingPercentage")
    @Expose
    @ColumnInfo(name = "SaleableLoadingPercentage")
    private String SaleableLoadingPercentage;
    @SerializedName("BalconyArea")
    @Expose
    @ColumnInfo(name = "BalconyArea")
    private String BalconyArea;
    @SerializedName("BalconyRate")
    @Expose
    @ColumnInfo(name = "BalconyRate")
    private String BalconyRate;
    @SerializedName("BalconyValue")
    @Expose
    @ColumnInfo(name = "BalconyValue")
    private String BalconyValue;

    @SerializedName("RealizationPercentageAsOnDate")
    @Expose
    @ColumnInfo(name = "RealizationPercentageAsOnDate")
    private String RealizationPercentageAsOnDate;
    @SerializedName("DistressPercentageAsOnDate")
    @Expose
    @ColumnInfo(name = "DistressPercentageAsOnDate")
    private String DistressPercentageAsOnDate;
    @SerializedName("RealizationValueAsOnDate")
    @Expose
    @ColumnInfo(name = "RealizationValueAsOnDate")
    private String RealizationValueAsOnDate;
    @SerializedName("DistressValueAsOnDate")
    @Expose
    @ColumnInfo(name = "DistressValueAsOnDate")
    private String DistressValueAsOnDate;

    @SerializedName("AdditionalDescription")
    @Expose
    @ColumnInfo(name = "AdditionalDescription")
    private String AdditionalDescription;
    @SerializedName("AdditionalDescriptionValue")
    @Expose
    @ColumnInfo(name = "AdditionalDescriptionValue")
    private String AdditionalDescriptionValue;

    @SerializedName("SelectedCarpetAreaTypeId")
    @Expose
    @ColumnInfo(name = "SelectedCarpetAreaTypeId")
    private String SelectedCarpetAreaTypeId;



    @SerializedName("GovernmentArea")
    @Expose
    @ColumnInfo(name = "GovernmentArea")
    private String GovernmentArea;

    @SerializedName("InsuranceArea")
    @Expose
    @ColumnInfo(name = "InsuranceArea")
    private String InsuranceArea;

    // 28-Dec-2018 changes
    @SerializedName("OtlaArea")
    @Expose
    @ColumnInfo(name = "OtlaArea")
    private String OtlaArea;
    @SerializedName("OtlaRate")
    @Expose
    @ColumnInfo(name = "OtlaRate")
    private String OtlaRate;
    @SerializedName("OtlaValue")
    @Expose
    @ColumnInfo(name = "OtlaValue")
    private String OtlaValue;

    @SerializedName("MezzanineArea")
    @Expose
    @ColumnInfo(name = "MezzanineArea")
    private String MezzanineArea;
    @SerializedName("MezzanineRate")
    @Expose
    @ColumnInfo(name = "MezzanineRate")
    private String MezzanineRate;
    @SerializedName("MezzanineValue")
    @Expose
    @ColumnInfo(name = "MezzanineValue")
    private String MezzanineValue;


    public String getOtlaArea() {
        return OtlaArea;
    }

    public void setOtlaArea(String otlaArea) {
        OtlaArea = otlaArea;
    }

    public String getOtlaRate() {
        return OtlaRate;
    }

    public void setOtlaRate(String otlaRate) {
        OtlaRate = otlaRate;
    }

    public String getOtlaValue() {
        return OtlaValue;
    }

    public void setOtlaValue(String otlaValue) {
        OtlaValue = otlaValue;
    }

    public String getMezzanineArea() {
        return MezzanineArea;
    }

    public void setMezzanineArea(String mezzanineArea) {
        MezzanineArea = mezzanineArea;
    }

    public String getMezzanineRate() {
        return MezzanineRate;
    }

    public void setMezzanineRate(String mezzanineRate) {
        MezzanineRate = mezzanineRate;
    }

    public String getMezzanineValue() {
        return MezzanineValue;
    }

    public void setMezzanineValue(String mezzanineValue) {
        MezzanineValue = mezzanineValue;
    }

    public String getInsuranceArea() {
        return InsuranceArea;
    }

    public void setInsuranceArea(String insuranceArea) {
        InsuranceArea = insuranceArea;
    }

    public String getGovernmentArea() {
        return GovernmentArea;
    }

    public void setGovernmentArea(String governmentArea) {
        GovernmentArea = governmentArea;
    }

    public String getSelectedCarpetAreaTypeId() {
        return SelectedCarpetAreaTypeId;
    }

    public void setSelectedCarpetAreaTypeId(String selectedCarpetAreaTypeId) {
        SelectedCarpetAreaTypeId = selectedCarpetAreaTypeId;
    }

    public String getAdditionalDescription() {
        return AdditionalDescription;
    }

    public void setAdditionalDescription(String additionalDescription) {
        AdditionalDescription = additionalDescription;
    }

    public String getAdditionalDescriptionValue() {
        return AdditionalDescriptionValue;
    }

    public void setAdditionalDescriptionValue(String additionalDescriptionValue) {
        AdditionalDescriptionValue = additionalDescriptionValue;
    }




    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getGuidelineRate() {
        return guidelineRate;
    }

    public void setGuidelineRate(String guidelineRate) {
        this.guidelineRate = guidelineRate;
    }

    public String getGuidelineRateUnit() {
        return guidelineRateUnit;
    }

    public void setGuidelineRateUnit(String guidelineRateUnit) {
        this.guidelineRateUnit = guidelineRateUnit;
    }

    public String getGuidelineValue() {
        return guidelineValue;
    }

    public void setGuidelineValue(String guidelineValue) {
        this.guidelineValue = guidelineValue;
    }

    public String getDocumentLandRate() {
        return documentLandRate;
    }

    public void setDocumentLandRate(String documentLandRate) {
        this.documentLandRate = documentLandRate;
    }

    public String getDocumentLandValue() {
        return documentLandValue;
    }

    public void setDocumentLandValue(String documentLandValue) {
        this.documentLandValue = documentLandValue;
    }

    public String getMeasuredLandRate() {
        return measuredLandRate;
    }

    public void setMeasuredLandRate(String measuredLandRate) {
        this.measuredLandRate = measuredLandRate;
    }

    public String getMeasuredLandValue() {
        return measuredLandValue;
    }

    public void setMeasuredLandValue(String measuredLandValue) {
        this.measuredLandValue = measuredLandValue;
    }

    public Boolean getDocumentedLandValueSel() {
        return documentedLandValueSel;
    }

    public void setDocumentedLandValueSel(Boolean documentedLandValueSel) {
        this.documentedLandValueSel = documentedLandValueSel;
    }

    public Boolean getMeasuredLandValueSel() {
        return measuredLandValueSel;
    }

    public void setMeasuredLandValueSel(Boolean measuredLandValueSel) {
        this.measuredLandValueSel = measuredLandValueSel;
    }

    public Boolean getDocumentedConstrValueSel() {
        return documentedConstrValueSel;
    }

    public void setDocumentedConstrValueSel(Boolean documentedConstrValueSel) {
        this.documentedConstrValueSel = documentedConstrValueSel;
    }

    public Boolean getMeasuredConstrValueSel() {
        return measuredConstrValueSel;
    }

    public void setMeasuredConstrValueSel(Boolean measuredConstrValueSel) {
        this.measuredConstrValueSel = measuredConstrValueSel;
    }

    public String getTotalConstructionValue() {
        return totalConstructionValue;
    }

    public void setTotalConstructionValue(String totalConstructionValue) {
        this.totalConstructionValue = totalConstructionValue;
    }

    public String getTotalPropertyValue() {
        return totalPropertyValue;
    }

    public void setTotalPropertyValue(String totalPropertyValue) {
        this.totalPropertyValue = totalPropertyValue;
    }

    public String getBuildingDepreciationValue() {
        return buildingDepreciationValue;
    }

    public void setBuildingDepreciationValue(String buildingDepreciationValue) {
        this.buildingDepreciationValue = buildingDepreciationValue;
    }

    public String getProposedValuation() {
        return proposedValuation;
    }

    public void setProposedValuation(String proposedValuation) {
        this.proposedValuation = proposedValuation;
    }

    public String getProposedValuationComments() {
        return proposedValuationComments;
    }

    public void setProposedValuationComments(String proposedValuationComments) {
        this.proposedValuationComments = proposedValuationComments;
    }

    public String getTotalValueAtCompletion() {
        return totalValueAtCompletion;
    }

    public void setTotalValueAtCompletion(String totalValueAtCompletion) {
        this.totalValueAtCompletion = totalValueAtCompletion;
    }

    public String getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(String insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public String getRealizationPercentage() {
        return realizationPercentage;
    }

    public void setRealizationPercentage(String realizationPercentage) {
        this.realizationPercentage = realizationPercentage;
    }

    public String getDistressPercentage() {
        return distressPercentage;
    }

    public void setDistressPercentage(String distressPercentage) {
        this.distressPercentage = distressPercentage;
    }

    public String getRealizationValue() {
        return realizationValue;
    }

    public void setRealizationValue(String realizationValue) {
        this.realizationValue = realizationValue;
    }

    public String getDistressValue() {
        return distressValue;
    }

    public void setDistressValue(String distressValue) {
        this.distressValue = distressValue;
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

    public String getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(String completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public String getTotalPropertyValueDepreciation() {
        return totalPropertyValueDepreciation;
    }

    public void setTotalPropertyValueDepreciation(String totalPropertyValueDepreciation) {
        this.totalPropertyValueDepreciation = totalPropertyValueDepreciation;
    }

    public String getDistressValueDepreciation() {
        return distressValueDepreciation;
    }

    public void setDistressValueDepreciation(String distressValueDepreciation) {
        this.distressValueDepreciation = distressValueDepreciation;
    }

    public String getCarpetAreaPercentage() {
        return carpetAreaPercentage;
    }

    public void setCarpetAreaPercentage(String carpetAreaPercentage) {
        this.carpetAreaPercentage = carpetAreaPercentage;
    }

    public String getCarpetAreaTypeId() {
        return carpetAreaTypeId;
    }

    public void setCarpetAreaTypeId(String carpetAreaTypeId) {
        this.carpetAreaTypeId = carpetAreaTypeId;
    }

    public String getRealizationValueDepreciation() {
        return realizationValueDepreciation;
    }

    public void setRealizationValueDepreciation(String realizationValueDepreciation) {
        this.realizationValueDepreciation = realizationValueDepreciation;
    }

    public String getRealizationLandValue() {
        return realizationLandValue;
    }

    public void setRealizationLandValue(String realizationLandValue) {
        this.realizationLandValue = realizationLandValue;
    }

    public String getConstructionDLCRate() {
        return constructionDLCRate;
    }

    public void setConstructionDLCRate(String constructionDLCRate) {
        this.constructionDLCRate = constructionDLCRate;
    }

    public String getConstructionDLCRateUnit() {
        return constructionDLCRateUnit;
    }

    public void setConstructionDLCRateUnit(String constructionDLCRateUnit) {
        this.constructionDLCRateUnit = constructionDLCRateUnit;
    }

    public String getConstructionDLCValue() {
        return constructionDLCValue;
    }

    public void setConstructionDLCValue(String constructionDLCValue) {
        this.constructionDLCValue = constructionDLCValue;
    }

    public String getCarpetArea() {
        return carpetArea;
    }

    public void setCarpetArea(String carpetArea) {
        this.carpetArea = carpetArea;
    }

    public String getBuildUpArea() {
        return buildUpArea;
    }

    public void setBuildUpArea(String buildUpArea) {
        this.buildUpArea = buildUpArea;
    }

    public String getSuperBuildUpArea() {
        return superBuildUpArea;
    }

    public void setSuperBuildUpArea(String superBuildUpArea) {
        this.superBuildUpArea = superBuildUpArea;
    }

    public String getCarpetAreaUnit() {
        return carpetAreaUnit;
    }

    public void setCarpetAreaUnit(String carpetAreaUnit) {
        this.carpetAreaUnit = carpetAreaUnit;
    }

    public String getSelectedConstructionArea() {
        return selectedConstructionArea;
    }

    public void setSelectedConstructionArea(String selectedConstructionArea) {
        this.selectedConstructionArea = selectedConstructionArea;
    }

    public String getBAULoadingPercentage() {
        return bAULoadingPercentage;
    }

    public void setBAULoadingPercentage(String bAULoadingPercentage) {
        this.bAULoadingPercentage = bAULoadingPercentage;
    }

    public String getConstructionRate() {
        return constructionRate;
    }

    public void setConstructionRate(String constructionRate) {
        this.constructionRate = constructionRate;
    }

    public String getInsuredConstructionRate() {
        return insuredConstructionRate;
    }

    public void setInsuredConstructionRate(String insuredConstructionRate) {
        this.insuredConstructionRate = insuredConstructionRate;
    }

    public String getTerraceArea() {
        return terraceArea;
    }

    public void setTerraceArea(String terraceArea) {
        this.terraceArea = terraceArea;
    }

    public String getTerraceRate() {
        return terraceRate;
    }

    public void setTerraceRate(String terraceRate) {
        this.terraceRate = terraceRate;
    }

    public String getTerraceValue() {
        return terraceValue;
    }

    public void setTerraceValue(String terraceValue) {
        this.terraceValue = terraceValue;
    }

    public String getSaleableLoadingPercentage() {
        return SaleableLoadingPercentage;
    }

    public void setSaleableLoadingPercentage(String saleableLoadingPercentage) {
        SaleableLoadingPercentage = saleableLoadingPercentage;
    }

    public String getBalconyArea() {
        return BalconyArea;
    }

    public void setBalconyArea(String balconyArea) {
        BalconyArea = balconyArea;
    }

    public String getBalconyRate() {
        return BalconyRate;
    }

    public void setBalconyRate(String balconyRate) {
        BalconyRate = balconyRate;
    }

    public String getBalconyValue() {
        return BalconyValue;
    }

    public void setBalconyValue(String balconyValue) {
        BalconyValue = balconyValue;
    }

    public String getRealizationPercentageAsOnDate() {
        return RealizationPercentageAsOnDate;
    }

    public void setRealizationPercentageAsOnDate(String realizationPercentageAsOnDate) {
        RealizationPercentageAsOnDate = realizationPercentageAsOnDate;
    }

    public String getDistressPercentageAsOnDate() {
        return DistressPercentageAsOnDate;
    }

    public void setDistressPercentageAsOnDate(String distressPercentageAsOnDate) {
        DistressPercentageAsOnDate = distressPercentageAsOnDate;
    }

    public String getRealizationValueAsOnDate() {
        return RealizationValueAsOnDate;
    }

    public void setRealizationValueAsOnDate(String realizationValueAsOnDate) {
        RealizationValueAsOnDate = realizationValueAsOnDate;
    }

    public String getDistressValueAsOnDate() {
        return DistressValueAsOnDate;
    }

    public void setDistressValueAsOnDate(String distressValueAsOnDate) {
        DistressValueAsOnDate = distressValueAsOnDate;
    }
}
