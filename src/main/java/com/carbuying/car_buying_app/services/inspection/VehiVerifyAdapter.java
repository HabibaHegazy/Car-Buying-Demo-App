package com.carbuying.car_buying_app.services.inspection;

import com.carbuying.car_buying_app.models.enums.InspectionCompany;
import org.springframework.stereotype.Component;

@Component
public class VehiVerifyAdapter implements InspectionAdapter {

    @Override
    public int requestInspection(Long offerId, String carDetails) {
        // Simulate API call to VEHI_VERIFY_INC
        // In real implementation, this would make an HTTP request
        System.out.println("Requesting inspection from VEHI_VERIFY_INC for offer: " + offerId);
        System.out.println("Car details: " + carDetails);

        // Simulate inspection score (0-100)
        return (int) (Math.random() * 101);
    }

    @Override
    public InspectionCompany getCompanyType() {
        return InspectionCompany.VEHI_VERIFY_INC;
    }

}
