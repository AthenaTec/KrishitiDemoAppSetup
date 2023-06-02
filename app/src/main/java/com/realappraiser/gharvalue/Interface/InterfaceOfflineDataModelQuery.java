package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.realappraiser.gharvalue.model.OfflineDataModel;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Created by kaptas on 24/2/18.
 */

@Dao
public interface InterfaceOfflineDataModelQuery {

    @Query("SELECT * FROM offlinedatamodel")
    List<OfflineDataModel> getDataModels();

    @Query("SELECT * FROM offlinedatamodel where offlinecase = (:is_offline)" )
    List<OfflineDataModel> getDataModal_offlinecase(boolean is_offline);


    @Query("SELECT * FROM offlinedatamodel where current_offline = (:is_offline)" )
    List<OfflineDataModel> getDataModal_current_offline(boolean is_offline);


    @Query("SELECT * FROM offlinedatamodel where showoffline = (:is_open)")
    List<OfflineDataModel> getDataModal_showOffline(boolean is_open);

    @Query("SELECT COUNT(*) from offlinedatamodel")
    int countdata();

    @Query("SELECT updatecasestatus FROM offlinedatamodel where caseid = (:caseidIn)")
    String getUpdateCaseIdOffline(String caseidIn);

    @Query("SELECT PropertyType FROM offlinedatamodel where caseid = (:caseidIn)")
    String getUpdatePropertyTypeOffline(String caseidIn);

    @Query("SELECT PropertyCategoryId FROM offlinedatamodel where caseid = (:caseidIn)")
    String getUpdatePropertyCategoryIdOffline(String caseidIn);

    @Query("SELECT rejectmessage FROM offlinedatamodel where caseid = (:caseidIn) AND updatecasestatus=:updatecasestatus")
    String getRejectMessagefromOffline(String caseidIn, String updatecasestatus);

    @Insert
    void insertAll(OfflineDataModel offlinedatamodel);

    @Update(onConflict = REPLACE)
    void updateData(OfflineDataModel offlinedatamodel);

    @Query("update offlinedatamodel set offlinecase=:isoffline where caseid in(:caseidIn)")
    long updateOfflineDataModel(String caseidIn, boolean isoffline);

    @Query("update offlinedatamodel set current_offline=:current_offline where caseid in(:caseidIn)")
    long updatecurrent_offlineDataModel(String caseidIn, boolean current_offline);

    @Query("update offlinedatamodel set updatecasestatus=:updatecasestatus where caseid in(:caseidIn)")
    long updateOfflineCaseStatus(String updatecasestatus, String caseidIn);

    @Query("update offlinedatamodel set updatecasestatus=:updatecasestatus, rejectmessage=:rejectmessage where caseid in(:caseidIn)")
    long updateOfflineRejectCaseStatus(String updatecasestatus, String rejectmessage, String caseidIn);

    @Query("update offlinedatamodel set PropertyType=:isPropertyType, PropertyCategoryId=:isPropertyCategoryId where caseid in(:caseidIn) AND offlinecase in(:is_offlinecase)")
    long updatePropertytype(String isPropertyType, String isPropertyCategoryId, String caseidIn, boolean is_offlinecase);

    @Query("delete from offlinedatamodel WHERE caseid in(:caseidIn)")
    int DeleteOfflineDatabyCaseid(String caseidIn);

    @Delete
    void delete(OfflineDataModel offlinedatamodel);

    @Query("DELETE FROM offlinedatamodel")
    public void deleteTable();

    @Query("SELECT * FROM offlinedatamodel where caseid = (:caseidIn)")
    OfflineDataModel get_case_is(String caseidIn);


    @Query("SELECT * FROM offlinedatamodel where caseid = (:caseidIn)")
    List<OfflineDataModel> get_case_list(String caseidIn);

    @Query("SELECT PropertyCategoryId FROM offlinedatamodel where caseid = (:caseidIn)")
    String get_PropertyCategoryId(String caseidIn);

    @Query("update offlinedatamodel set PropertyType=:isPropertyType, PropertyCategoryId=:isPropertyCategoryId,typeof_PropertyCategoryId_selected=:typeof_PropertyCategoryId_selected ,is_property_changed=:is_property_check where caseid in(:caseidIn) AND offlinecase in(:is_offlinecase)")
    long updatePropertytype_is_property_changed(String isPropertyType, String isPropertyCategoryId,String typeof_PropertyCategoryId_selected,boolean is_property_check ,String caseidIn, boolean is_offlinecase);

    @Query("SELECT is_property_changed FROM offlinedatamodel where caseid = (:caseidIn)")
    boolean getDataModal_is_property_changed(String caseidIn);

    @Query("SELECT PropertyId FROM offlinedatamodel where caseid = (:caseidIn)")
    String get_PropertyId(String caseidIn);

    @Query("update offlinedatamodel set PropertyType=:isPropertyType, PropertyCategoryId=:isPropertyCategoryId where caseid in(:caseidIn)")
    long updatePropertytypefor(String isPropertyType, String isPropertyCategoryId, String caseidIn);

    @Query("update offlinedatamodel set updatecasestatus=:updatecasestatus, StatusId=:updatecasestatus where caseid in(:caseidIn)")
    long updateOfflineCaseStatusonline(String updatecasestatus, String caseidIn);

    // Delete all rows
    @Query("DELETE FROM offlinedatamodel")
    public void deleteRow();

    @Query("update offlinedatamodel set sync_edit=:is_sync where caseid in(:caseidIn)")
    long update_sync_edit_DataModel(String caseidIn, boolean is_sync);

}
