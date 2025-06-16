package com.carbuying.car_buying_app.services;

import com.carbuying.car_buying_app.dto.requests.SupplierOfferCreateDto;
import com.carbuying.car_buying_app.dto.responses.SupplierOfferResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupplierOfferService {

    SupplierOfferResponseDto submitOffer(SupplierOfferCreateDto createDto);

    List<SupplierOfferResponseDto> getOffersByRequestId(Long requestId);

    Page<SupplierOfferResponseDto> getOffersBySupplierId(Long supplierId, Pageable pageable);

    SupplierOfferResponseDto getOfferById(Long id);

}
