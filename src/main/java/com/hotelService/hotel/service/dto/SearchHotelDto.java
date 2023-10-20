package com.hotelService.hotel.service.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SearchHotelDto implements Serializable {

    @NotNull
    private final String hotelId;

    @NotNull
    private final String checkIn;

    @NotNull
    private final String checkOut;

    @NotNull
    private final List<String> ages;

    public SearchHotelDto(String hotelId, String checkIn, String checkOut, List<String> ages) {
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ages = ages;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public List<String> getAges() {
        return ages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchHotelDto that = (SearchHotelDto) o;
        return Objects.equals(hotelId, that.hotelId) && Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut) && Objects.equals(ages, that.ages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, checkIn, checkOut, ages);
    }
}
