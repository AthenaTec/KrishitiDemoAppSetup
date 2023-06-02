package com.realappraiser.gharvalue.communicator;

import com.realappraiser.gharvalue.model.Case;
import com.realappraiser.gharvalue.model.CaseOtherDetailsModel;
import com.realappraiser.gharvalue.model.Document_list;
import com.realappraiser.gharvalue.model.ImageBase64;
import com.realappraiser.gharvalue.model.IndProperty;
import com.realappraiser.gharvalue.model.IndPropertyFloor;
import com.realappraiser.gharvalue.model.IndPropertyFloorsValuation;
import com.realappraiser.gharvalue.model.IndPropertyValuation;
import com.realappraiser.gharvalue.model.OfflineDataModel;
import com.realappraiser.gharvalue.model.Property;
import com.realappraiser.gharvalue.model.Proximity;

import java.util.ArrayList;

/**
 * Created by kaptas on 15/12/17.
 */

@SuppressWarnings("ALL")
public class DataResponse {

    public String status = "";
    public String info = "";
    public String msg = "";
    public String totalamount = "";
    public String image = "";


    public ArrayList<DataModel> loginModel = new ArrayList<>();
    public ArrayList<DataModel> openCaseList = new ArrayList<>();
    public ArrayList<DataModel> pendingCaseList = new ArrayList<>();
    public ArrayList<DataModel> openCaseOriginalList = new ArrayList<>();
    public ArrayList<DataModel> closeCaseList = new ArrayList<>();
    public ArrayList<OfflineDataModel> offlineCaseList = new ArrayList<>();
    public ArrayList<DataModel> propertyTypeList = new ArrayList<>();
    public ArrayList<Document_list> documentRead = new ArrayList<>();
    public DataModel updateCaseStatusModel = new DataModel();
    public DataModel updatePropertyTypeStatusModel = new DataModel();
    public DataModel getReportTypeModel = new DataModel();

    public Case aCase = new Case();
    public CaseOtherDetailsModel caseOtherDetailsModel = new CaseOtherDetailsModel();
    public Property property = new Property();
    public IndProperty indProperty = new IndProperty();
    public IndPropertyValuation indPropertyValuation = new IndPropertyValuation();
    public ArrayList<IndPropertyFloor> indPropertyFloors = new ArrayList<>();
    public ArrayList<Proximity> proximities = new ArrayList<>();
    public ArrayList<IndPropertyFloorsValuation> indPropertyFloorsValuations = new ArrayList<>();

    public ArrayList<ImageBase64> base64image = new ArrayList<>();
    public ArrayList<String> imageid = new ArrayList<>();
}
