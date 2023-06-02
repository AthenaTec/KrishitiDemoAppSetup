package com.realappraiser.gharvalue;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;

import com.realappraiser.gharvalue.Interface.CaseDetailDAO;
import com.realappraiser.gharvalue.Interface.InterfaceCaseQuery;
import com.realappraiser.gharvalue.Interface.InterfaceDataModelQuery;
import com.realappraiser.gharvalue.Interface.InterfaceDocumentListQuery;
import com.realappraiser.gharvalue.Interface.InterfaceGetPhotoMeasurmentQuery;
import com.realappraiser.gharvalue.Interface.InterfaceGetPhotoQuery;
import com.realappraiser.gharvalue.Interface.InterfaceIndPropertyFloorsQuery;
import com.realappraiser.gharvalue.Interface.InterfaceIndPropertyFloorsValuationQuery;
import com.realappraiser.gharvalue.Interface.InterfaceIndPropertyValuationQuery;
import com.realappraiser.gharvalue.Interface.InterfaceIndpropertyQuery;
import com.realappraiser.gharvalue.Interface.InterfaceLatLongQuery;
import com.realappraiser.gharvalue.Interface.InterfaceOfflineCaseQuery;
import com.realappraiser.gharvalue.Interface.InterfaceOfflineDataModelQuery;
import com.realappraiser.gharvalue.Interface.InterfacePropertyQuery;
import com.realappraiser.gharvalue.Interface.InterfaceProximityQuery;
import com.realappraiser.gharvalue.Interface.PropertyUpdateCategory;
import com.realappraiser.gharvalue.Interface.TypeofPropertyQuery;
import com.realappraiser.gharvalue.communicator.DataModel;
import com.realappraiser.gharvalue.model.Case;
import com.realappraiser.gharvalue.model.CaseDetail;
import com.realappraiser.gharvalue.model.Document_list;
import com.realappraiser.gharvalue.model.GetPhoto;
import com.realappraiser.gharvalue.model.GetPhoto_measurment;
import com.realappraiser.gharvalue.model.IndProperty;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.model.IndPropertyFloorsValuation;
import com.realappraiser.gharvalue.model.IndPropertyValuation;
import com.realappraiser.gharvalue.model.LatLongDetails;
import com.realappraiser.gharvalue.model.OfflineDataModel;
import com.realappraiser.gharvalue.model.OflineCase;
import com.realappraiser.gharvalue.model.Property;
import com.realappraiser.gharvalue.model.PropertyUpdateRoomDB;
import com.realappraiser.gharvalue.model.Proximity;
import com.realappraiser.gharvalue.model.TypeOfProperty;

import org.jetbrains.annotations.NotNull;

/**
 * Created by user on 15-02-2018.
 */

@Database(entities = {DataModel.class, OfflineDataModel.class, TypeOfProperty.class, PropertyUpdateRoomDB.class, Case.class, Property.class, IndProperty.class, IndPropertyValuation.class, IndPropertyFloor.class, IndPropertyFloorsValuation.class, Proximity.class, GetPhoto.class, CaseDetail.class,OflineCase.class,Document_list.class,LatLongDetails.class,GetPhoto_measurment.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    // DataModal
    public abstract InterfaceDataModelQuery interfaceDataModelQuery();

    // DataModal
    public abstract InterfaceOfflineDataModelQuery interfaceOfflineDataModelQuery();

    // Case
    public abstract InterfaceCaseQuery interfaceCaseQuery();

    // Property
    public abstract InterfacePropertyQuery interfacePropertyQuery();

    // IndProperty
    public abstract InterfaceIndpropertyQuery interfaceIndpropertyQuery();

    // IndPropertyValuation
    public abstract InterfaceIndPropertyValuationQuery interfaceIndPropertyValuationQuery();

    // IndPropertyFloor
    public abstract InterfaceIndPropertyFloorsQuery interfaceIndPropertyFloorsQuery();

    // IndPropertyFloorsValuation
    public abstract InterfaceIndPropertyFloorsValuationQuery interfaceIndPropertyFloorsValuationQuery();

    // Proximity
    public abstract InterfaceProximityQuery interfaceProximityQuery();

    // GetPhoto
    public abstract InterfaceGetPhotoQuery interfaceGetPhotoQuery();

    // Offlinecase
    public abstract InterfaceOfflineCaseQuery interfaceOfflineCaseQuery();

    // DocumentList
    public abstract InterfaceDocumentListQuery interfaceDocumentListQuery();

    // LatLong
    public abstract InterfaceLatLongQuery interfaceLatLongQuery();

    // typeofproperty
    public abstract TypeofPropertyQuery typeofPropertyQuery();

    // Total Case
    public abstract CaseDetailDAO daoAccess();

    // property Update category
    public abstract PropertyUpdateCategory propertyUpdateCategory();

    // GetPhotoMeasurment
    public abstract InterfaceGetPhotoMeasurmentQuery interfaceGetPhotoMeasurmentQuery();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "RA-Database.db")
                    // allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2,MIGRATION_2_3)
                    /* call fallbackToDestructiveMigration in the builder in which case Room will re-create all of the tables */
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE GetPhotoModel "
                    +"ADD COLUMN Filename TEXT;");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE DataModel ADD COLUMN pendingcase INTEGER;");
            database.execSQL("ALTER TABLE DataModel ADD COLUMN CreatedOn TEXT;");
            database.execSQL("ALTER TABLE OfflineDataModel ADD COLUMN CreatedOn TEXT;");
        }
    };



}
