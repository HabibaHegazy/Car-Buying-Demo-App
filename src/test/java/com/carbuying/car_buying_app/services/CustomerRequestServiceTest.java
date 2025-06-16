package com.carbuying.car_buying_app.services;

import com.carbuying.car_buying_app.dto.requests.CustomerRequestCreateDto;
import com.carbuying.car_buying_app.dto.responses.CustomerRequestResponseDto;
import com.carbuying.car_buying_app.mappers.CustomerRequestMapper;
import com.carbuying.car_buying_app.models.entity.CustomerRequest;
import com.carbuying.car_buying_app.models.enums.InspectionCompany;
import com.carbuying.car_buying_app.models.enums.RequestStatus;
import com.carbuying.car_buying_app.repositories.CustomerRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerRequestServiceTest {

    @Mock
    private CustomerRequestRepository customerRequestRepository;

    @Mock
    private CustomerRequestMapper customerRequestMapper;

    @InjectMocks
    private CustomerRequestServiceImpl customerRequestService;

    private CustomerRequestCreateDto createDto;
    private CustomerRequest customerRequest;
    private CustomerRequestResponseDto responseDto;

    @BeforeEach
    void setUp() {
        createDto = new CustomerRequestCreateDto(1L, "Need a reliable sedan", InspectionCompany.AUTO_CHECK_CO);

        customerRequest = new CustomerRequest();
        customerRequest.setId(1L);
        customerRequest.setCustomerId(1L);
        customerRequest.setDescription("Need a reliable sedan");
        customerRequest.setCheckedByCompany(InspectionCompany.AUTO_CHECK_CO);
        customerRequest.setStatus(RequestStatus.ACTIVE);

        responseDto = new CustomerRequestResponseDto();
        responseDto.setId(1L);
        responseDto.setCustomerId(1L);
        responseDto.setDescription("Need a reliable sedan");
        responseDto.setCheckedByCompany(InspectionCompany.AUTO_CHECK_CO);
        responseDto.setStatus(RequestStatus.ACTIVE);
    }

    @Test
    void createRequest_ShouldReturnResponseDto() {
        when(customerRequestMapper.toEntity(createDto)).thenReturn(customerRequest);
        when(customerRequestRepository.save(any(CustomerRequest.class))).thenReturn(customerRequest);
        when(customerRequestMapper.toResponseDto(customerRequest)).thenReturn(responseDto);

        CustomerRequestResponseDto result = customerRequestService.createRequest(createDto);

        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
        assertEquals(responseDto.getCustomerId(), result.getCustomerId());
        verify(customerRequestRepository).save(any(CustomerRequest.class));
    }

    @Test
    void getAllRequests_ShouldReturnPagedResults() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CustomerRequest> page = new PageImpl<>(Collections.singletonList(customerRequest));
        when(customerRequestRepository.findAll(pageable)).thenReturn(page);
        when(customerRequestMapper.toResponseDto(customerRequest)).thenReturn(responseDto);

        Page<CustomerRequestResponseDto> result = customerRequestService.getAllRequests(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(responseDto.getId(), result.getContent().getFirst().getId());
    }

    @Test
    void getRequestById_WhenExists_ShouldReturnResponseDto() {
        when(customerRequestRepository.findById(1L)).thenReturn(Optional.of(customerRequest));
        when(customerRequestMapper.toResponseDto(customerRequest)).thenReturn(responseDto);

        CustomerRequestResponseDto result = customerRequestService.getRequestById(1L);

        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
    }

    @Test
    void getRequestById_WhenNotExists_ShouldThrowException() {
        when(customerRequestRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> customerRequestService.getRequestById(1L));
    }

    @Test
    void updateRequestStatus_ShouldUpdateAndReturn() {
        when(customerRequestRepository.findById(1L)).thenReturn(Optional.of(customerRequest));
        when(customerRequestRepository.save(any(CustomerRequest.class))).thenReturn(customerRequest);
        when(customerRequestMapper.toResponseDto(customerRequest)).thenReturn(responseDto);

        CustomerRequestResponseDto result = customerRequestService.updateRequestStatus(1L, RequestStatus.CLOSED);

        assertNotNull(result);
        verify(customerRequestRepository).save(customerRequest);
        assertEquals(RequestStatus.CLOSED, customerRequest.getStatus());
    }

}
