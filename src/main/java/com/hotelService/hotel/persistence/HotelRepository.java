package com.hotelService.hotel.persistence;


import com.hotelService.hotel.persistence.entity.HotelEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HotelRepository extends MongoRepository<HotelEntity, String> {

    Optional<HotelEntity> findByNameAndType(String name, String type);

}