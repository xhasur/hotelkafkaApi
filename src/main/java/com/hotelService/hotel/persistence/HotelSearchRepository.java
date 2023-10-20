package com.hotelService.hotel.persistence;


import com.hotelService.hotel.persistence.entity.SearchHotelEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface HotelSearchRepository extends MongoRepository<SearchHotelEntity, String> {

    Optional<SearchHotelEntity> findByHotelIdAndCheckAndCheckOutAndAges(String hotelId, String checkIn, String checkOut, List<String> ages);

}