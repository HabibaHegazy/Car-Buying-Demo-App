package com.carbuying.car_buying_app.mappers;

import com.carbuying.car_buying_app.dto.requests.SupplierOfferCreateDto;
import com.carbuying.car_buying_app.dto.responses.SupplierOfferResponseDto;
import com.carbuying.car_buying_app.models.entity.SupplierOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierOfferMapper {

    @Mapping(target = "customerRequest", ignore = true)
    SupplierOffer toEntity(SupplierOfferCreateDto dto);

    @Mapping(target = "requestId", source = "customerRequest.id")
    SupplierOfferResponseDto toResponseDto(SupplierOffer entity);

}
