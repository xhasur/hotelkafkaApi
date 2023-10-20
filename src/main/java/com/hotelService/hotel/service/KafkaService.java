package com.hotelService.hotel.service;


import com.hotelService.hotel.service.dto.HotelDto;
import com.hotelService.hotel.service.dto.SearchHotelDto;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, SearchHotelDto> kafkaTemplate;

    private static final String TOPIC = "availability";

    private static final String TOPIC_HOTEL = "hotel";

    private static final String GROUP = "group-id";

    private static Logger LOGGER = LogManager.getLogger(KafkaService.class);

    public CompletableFuture<SendResult<String, SearchHotelDto>> sendMessage(SearchHotelDto msgSearch) {
        LOGGER.debug("Searching Hotel search");
        return kafkaTemplate.send(TOPIC, msgSearch);
    }


    public CompletableFuture<SendResult<String, SearchHotelDto>> sendMessageHotel(@Valid HotelDto searchHotel) {
        LOGGER.debug("Saving Hotel");
        return kafkaTemplate.send(TOPIC_HOTEL, searchHotel);
    }
}
