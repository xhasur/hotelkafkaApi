package com.hotelService.hotel.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document
public class HotelEntity {


    @Id
    private String hotelId;
    private String name;
    private String address;

    private String location;
    private String type;

    public HotelEntity(String hotelId, String name, String address, String location, String type) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.location = location;
        this.type = type;
    }

    public HotelEntity() {
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelEntity that = (HotelEntity) o;
        return Objects.equals(hotelId, that.hotelId) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(location, that.location) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, name, address, location, type);
    }
}
