package com.carbuying.car_buying_app.services.inspection;

import com.carbuying.car_buying_app.models.enums.InspectionCompany;

public interface InspectionAdapter {

    int requestInspection(Long offerId, String carDetails);

    InspectionCompany getCompanyType();

}
