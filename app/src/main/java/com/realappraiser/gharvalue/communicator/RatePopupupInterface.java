package com.realappraiser.gharvalue.communicator;

import com.realappraiser.gharvalue.model.RatePopup;

public interface RatePopupupInterface {
    void onRatePopupSucess(RatePopup ratePopup);
    void onRatePopupFailed(String msg);
}
