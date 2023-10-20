package com.hotelService.hotel.web.controller;


import com.hotelService.hotel.persistence.entity.HotelEntity;
import com.hotelService.hotel.service.HotelService;
import com.hotelService.hotel.service.dto.HotelDto;
import com.hotelService.hotel.service.dto.SearchHotelDto;
import com.hotelService.hotel.service.dto.SearchHotelWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;


@RestController()
@RequestMapping("/api")
public class HotelController {

    private static Logger LOGGER = LogManager.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    @Autowired
    private KafkaTemplate<String, SearchHotelDto> kafkaTemplate;

    @GetMapping("/count")
    @Operation(summary = "Get hotel search")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the hotel search", content = @Content),
            @ApiResponse(responseCode = "400", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error - not found", content = @Content)})
    public ResponseEntity<SearchHotelWrapper> count(@Parameter(description = "searchId", example = "5")
                                                    @RequestParam(value = "searchId", required = true) String searchId) {
        LOGGER.info("HotelController::getSearches {} ", searchId);
        return new ResponseEntity<>(hotelService.getCount(searchId), HttpStatus.OK);
    }

    @PostMapping("/search")
    @Operation(summary = "Get Id hotel search")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the hotel Id search", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid object supplied", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)})
    public ResponseEntity<String> search(@Parameter(description = "searchHotel")
                                         @Valid @RequestBody SearchHotelDto searchHotel) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(this.hotelService.sendKafkaMessage(searchHotel));
    }

    @PostMapping("/save")
    @Operation(summary = "To save hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel saved successfully", content = @Content) ,
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)})
    public ResponseEntity<HotelEntity> saveHotel(@Valid @RequestBody HotelDto hotelDto) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(this.hotelService.saveHotelKafkaMessage(hotelDto));
    }






}
