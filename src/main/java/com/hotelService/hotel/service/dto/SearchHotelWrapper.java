package com.hotelService.hotel.service.dto;

import java.util.Objects;

public class SearchHotelWrapper {

    private String searchId;

    private int occurrence;

    private SearchHotelDto search;

    public SearchHotelWrapper(String searchId, SearchHotelDto search, int occurrence) {
        this.searchId = searchId;
        this.occurrence = occurrence;
        this.search = search;
    }


    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public SearchHotelDto getSearch() {
        return search;
    }

    public void setSearch(SearchHotelDto search) {
        this.search = search;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchHotelWrapper that = (SearchHotelWrapper) o;
        return occurrence == that.occurrence && Objects.equals(searchId, that.searchId) && Objects.equals(search, that.search);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchId, occurrence, search);
    }
}
