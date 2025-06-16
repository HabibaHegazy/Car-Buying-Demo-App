package com.carbuying.car_buying_app.services;

import com.carbuying.car_buying_app.dto.requests.SupplierOfferCreateDto;
import com.carbuying.car_buying_app.dto.responses.SupplierOfferResponseDto;
import com.carbuying.car_buying_app.mappers.SupplierOfferMapper;
import com.carbuying.car_buying_app.models.entity.CustomerRequest;
import com.carbuying.car_buying_app.models.entity.SupplierOffer;
import com.carbuying.car_buying_app.models.enums.InspectionCompany;
import com.carbuying.car_buying_app.models.enums.OfferStatus;
import com.carbuying.car_buying_app.models.enums.RequestStatus;
import com.carbuying.car_buying_app.repositories.SupplierOfferRepository;
import com.carbuying.car_buying_app.services.inspection.InspectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SupplierOfferServiceTest {

    @Mock
    private SupplierOfferRepository supplierOfferRepository;

    @Mock
    private SupplierOfferMapper supplierOfferMapper;

    @Mock
    private CustomerRequestServiceImpl customerRequestService;

    @Mock
    private InspectionService inspectionService;

    @InjectMocks
    private SupplierOfferServiceImpl supplierOfferService;

    private SupplierOfferCreateDto createDto;
    private SupplierOffer supplierOffer;
    private SupplierOfferResponseDto responseDto;
    private CustomerRequest customerRequest;

    @BeforeEach
    void setUp() {
        createDto = new SupplierOfferCreateDto(1L, 1L, new BigDecimal("25000.00"), "Excellent condition");

        customerRequest = new CustomerRequest();
        customerRequest.setId(1L);
        customerRequest.setStatus(RequestStatus.ACTIVE);
        customerRequest.setCheckedByCompany(InspectionCompany.AUTO_CHECK_CO);

        supplierOffer = new SupplierOffer();
        supplierOffer.setId(1L);
        supplierOffer.setSupplierId(1L);
        supplierOffer.setCustomerRequest(customerRequest);
        supplierOffer.setPrice(new BigDecimal("25000.00"));
        supplierOffer.setCarDetails("Excellent condition");
        supplierOffer.setStatus(OfferStatus.PENDING);

        responseDto = new SupplierOfferResponseDto();
        responseDto.setId(1L);
        responseDto.setSupplierId(1L);
        responseDto.setRequestId(1L);
        responseDto.setPrice(new BigDecimal("25000.00"));
        responseDto.setCarDetails("Excellent condition");
        responseDto.setStatus(OfferStatus.PENDING);
    }

    @Test
    void submitOffer_WhenValid_ShouldReturnResponseDto() {
        when(customerRequestService.findActiveRequestById(1L)).thenReturn(customerRequest);
        when(supplierOfferRepository.existsBySupplierIdAndCustomerRequestId(1L, 1L)).thenReturn(false);
        when(supplierOfferMapper.toEntity(createDto)).thenReturn(supplierOffer);
        when(supplierOfferRepository.save(any(SupplierOffer.class))).thenReturn(supplierOffer);
        when(inspectionService.requestInspection(any(), any(), any())).thenReturn(85);
        when(supplierOfferMapper.toResponseDto(supplierOffer)).thenReturn(responseDto);

        SupplierOfferResponseDto result = supplierOfferService.submitOffer(createDto);

        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
        assertEquals(responseDto.getSupplierId(), result.getSupplierId());
        verify(supplierOfferRepository, times(2)).save(any(SupplierOffer.class));
        verify(inspectionService).requestInspection(any(), any(), any());
    }

    @Test
    void submitOffer_WhenSupplierAlreadyHasOffer_ShouldThrowException() {
        assertThrows(RuntimeException.class, () -> supplierOfferService.submitOffer(createDto));
        verify(supplierOfferRepository, never()).save(any());
    }

    @Test
    void getOffersByRequestId_ShouldReturnListOfOffers() {
        when(supplierOfferRepository.findByCustomerRequestId(1L)).thenReturn(Collections.singletonList(supplierOffer));
        when(supplierOfferMapper.toResponseDto(supplierOffer)).thenReturn(responseDto);

        List<SupplierOfferResponseDto> result = supplierOfferService.getOffersByRequestId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDto.getId(), result.getFirst().getId());
    }

}
