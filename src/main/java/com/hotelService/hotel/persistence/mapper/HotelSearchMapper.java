package com.hotelService.hotel.persistence.mapper;


import com.hotelService.hotel.persistence.entity.SearchHotelEntity;
import com.hotelService.hotel.service.dto.SearchHotelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface HotelSearchMapper {


    @Mappings({
            @Mapping(source = "checkIn", target = "check")
    })
    SearchHotelEntity toSearchHotelEntity(SearchHotelDto hotel);
}