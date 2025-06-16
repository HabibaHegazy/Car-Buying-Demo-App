package com.carbuying.car_buying_app.services;

import com.carbuying.car_buying_app.dto.requests.CustomerRequestCreateDto;
import com.carbuying.car_buying_app.dto.responses.CustomerRequestResponseDto;
import com.carbuying.car_buying_app.models.entity.CustomerRequest;
import com.carbuying.car_buying_app.models.enums.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerRequestService {

    CustomerRequestResponseDto createRequest(CustomerRequestCreateDto createDto);

    Page<CustomerRequestResponseDto> getAllRequests(Pageable pageable);

    Page<CustomerRequestResponseDto> getRequestsByStatus(RequestStatus status, Pageable pageable);

    CustomerRequestResponseDto getRequestById(Long id);

    CustomerRequestResponseDto updateRequestStatus(Long id, RequestStatus status);

    CustomerRequest findActiveRequestById(Long id);

}

