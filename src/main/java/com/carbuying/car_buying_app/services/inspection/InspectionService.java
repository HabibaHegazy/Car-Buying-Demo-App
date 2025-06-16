package com.carbuying.car_buying_app.services.inspection;

import com.carbuying.car_buying_app.models.enums.InspectionCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InspectionService {

    private final Map<InspectionCompany, InspectionAdapter> adapters;

    @Autowired
    public InspectionService(List<InspectionAdapter> adaptersList) {
        this.adapters = adaptersList.stream()
                .collect(Collectors.toMap(InspectionAdapter::getCompanyType, Function.identity()));
    }

    public int requestInspection(InspectionCompany company, Long offerId, String carDetails) {
        InspectionAdapter adapter = adapters.get(company);
        if (adapter == null) {
            throw new IllegalArgumentException("No adapter found for inspection company: " + company);
        }
        return adapter.requestInspection(offerId, carDetails);
    }

}
