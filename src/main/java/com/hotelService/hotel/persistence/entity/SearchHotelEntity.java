package com.hotelService.hotel.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document
public class SearchHotelEntity {

    @Id
    private String searchId;
    private String hotelId;
    private String check;

    private String checkOut;
    private List<String> ages;
    private int occurrence;

    public SearchHotelEntity() {
    }

    public SearchHotelEntity(String searchId, String hotelId, String check, String checkOut, List<String> ages, int occurrence) {
        this.searchId = searchId;
        this.hotelId = hotelId;
        this.check = check;
        this.checkOut = checkOut;
        this.ages = ages;
        this.occurrence = occurrence;
    }


    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public String getSearchId() {
        return searchId;
    }

    public String getCheck() {
        return check;
    }

    public List<String> getAges() {
        return ages;
    }

    public void setAges(List<String> ages) {
        this.ages = ages;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchHotelEntity that = (SearchHotelEntity) o;
        return occurrence == that.occurrence && Objects.equals(searchId, that.searchId) && Objects.equals(hotelId, that.hotelId) && Objects.equals(check, that.check) && Objects.equals(checkOut, that.checkOut) && Objects.equals(ages, that.ages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchId, hotelId, check, checkOut, ages, occurrence);
    }

}
