package com.hotelService.hotel.service.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class HotelDto implements Serializable {


    @NotNull
    private final String hotelId;

    @NotNull
    private final String name;

    @NotNull
    private final String address;

    @NotNull
    private final String location;

    @NotNull
    private final String type;

    public HotelDto(String hotelId, String name, String address, String location, String type) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.location = location;
        this.type = type;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelDto hotelDto = (HotelDto) o;
        return Objects.equals(hotelId, hotelDto.hotelId) && Objects.equals(name, hotelDto.name) && Objects.equals(address, hotelDto.address) && Objects.equals(location, hotelDto.location) && Objects.equals(type, hotelDto.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, name, address, location, type);
    }
}
