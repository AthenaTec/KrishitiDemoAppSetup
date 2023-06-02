package com.realappraiser.gharvalue.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.realappraiser.gharvalue.model.TypeOfProperty;

import java.util.List;

/**
 * Created by kaptas on 5/3/18.
 */

@Dao
public interface TypeofPropertyQuery {

    @Query("SELECT * FROM typeofproperty")
    List<TypeOfProperty> getTypeofPropertyModels();

    /* @Query("SELECT * FROM typeofproperty where offlinecase = (:is_offline) AND opencase = (:is_open)")
    List<DataModel> getDataModal_offlinecase(boolean is_offline, boolean is_open);*/

    @Query("SELECT COUNT(*) from typeofproperty")
    int countdata();

    @Insert
    void insertAll(TypeOfProperty dataModel);

    @Update
    void updateData(TypeOfProperty dataModel);

    @Delete
    void delete(TypeOfProperty dataModel);

    @Query("DELETE FROM typeofproperty")
    public void deleteTable();

    // Delete all rows
    @Query("DELETE FROM typeofproperty")
    public void deleteRow();



}
