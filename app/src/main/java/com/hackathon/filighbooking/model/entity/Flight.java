package com.hackathon.filighbooking.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Flight implements Serializable,Parcelable {
    @SerializedName("cheapestClass")
    @Expose
    private String cheapestClass;
    @SerializedName("cheapestPrice")
    @Expose
    private Integer cheapestPrice;
    @SerializedName("provider")
    @Expose
    private String provider;
    @SerializedName("flightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("departureDate")
    @Expose
    private Integer departureDate;
    @SerializedName("arrivalTime")
    @Expose
    private String arrivalTime;
    @SerializedName("departureTime")
    @Expose
    private String departureTime;
    @SerializedName("destinationCode")
    @Expose
    private String destinationCode;
    @SerializedName("originCode")
    @Expose
    private String originCode;
    @SerializedName("lastUpdateTime")
    @Expose
    private String lastUpdateTime;
    @SerializedName("priceList")
    @Expose
    private List<PriceList> priceList = null;

    public Flight(Parcel in) {

    }
    public Flight(){}

    public String getCheapestClass() {
        return cheapestClass;
    }

    public void setCheapestClass(String cheapestClass) {
        this.cheapestClass = cheapestClass;
    }

    public Integer getCheapestPrice() {
        return cheapestPrice;
    }

    public void setCheapestPrice(Integer cheapestPrice) {
        this.cheapestPrice = cheapestPrice;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Integer getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Integer departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<PriceList> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<PriceList> priceList) {
        this.priceList = priceList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<Flight> CREATOR
            = new Parcelable.Creator<Flight>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Flight createFromParcel(Parcel in) {
            return new Flight(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Flight[] newArray(int size) {
            return new Flight[size];
        }
    };
}
