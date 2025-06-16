package com.carbuying.car_buying_app.services;

import com.carbuying.car_buying_app.dto.requests.CustomerRequestCreateDto;
import com.carbuying.car_buying_app.dto.responses.CustomerRequestResponseDto;
import com.carbuying.car_buying_app.mappers.CustomerRequestMapper;
import com.carbuying.car_buying_app.models.entity.CustomerRequest;
import com.carbuying.car_buying_app.models.enums.RequestStatus;
import com.carbuying.car_buying_app.repositories.CustomerRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerRequestServiceImpl implements CustomerRequestService {

    private final CustomerRequestRepository customerRequestRepository;
    private final CustomerRequestMapper customerRequestMapper;

    @Override
    public CustomerRequestResponseDto createRequest(CustomerRequestCreateDto createDto) {
        CustomerRequest request = customerRequestMapper.toEntity(createDto);
        CustomerRequest savedRequest = customerRequestRepository.save(request);
        return customerRequestMapper.toResponseDto(savedRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerRequestResponseDto> getAllRequests(Pageable pageable) {
        return customerRequestRepository.findAll(pageable)
                .map(customerRequestMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerRequestResponseDto> getRequestsByStatus(RequestStatus status, Pageable pageable) {
        return customerRequestRepository.findByStatus(status, pageable)
                .map(customerRequestMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerRequestResponseDto getRequestById(Long id) {
        CustomerRequest request = customerRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer request not found with id: " + id));
        return customerRequestMapper.toResponseDto(request);
    }

    @Override
    public CustomerRequestResponseDto updateRequestStatus(Long id, RequestStatus status) {
        CustomerRequest request = customerRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer request not found with id: " + id));

        request.setStatus(status);
        CustomerRequest updatedRequest = customerRequestRepository.save(request);
        return customerRequestMapper.toResponseDto(updatedRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerRequest findActiveRequestById(Long id) {
        CustomerRequest request = customerRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer request not found with id: " + id));

        if (!request.isActive()) {
            throw new RuntimeException("Request is not active and cannot accept new offers");
        }

        return request;
    }

}
