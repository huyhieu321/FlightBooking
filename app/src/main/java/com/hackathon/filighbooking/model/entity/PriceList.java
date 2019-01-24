package com.hackathon.filighbooking.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PriceList implements Serializable {
    @SerializedName("priceTotal")
    @Expose
    private Integer priceTotal;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("seatClass")
    @Expose
    private String seatClass;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("isAgent")
    @Expose
    private Integer isAgent;
    @SerializedName("priceFeeList")
    @Expose
    private List<PriceFeeList> priceFeeList = null;
    @SerializedName("seatCount")
    @Expose
    private Integer seatCount;

    public Integer getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Integer priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(Integer isAgent) {
        this.isAgent = isAgent;
    }

    public List<PriceFeeList> getPriceFeeList() {
        return priceFeeList;
    }

    public void setPriceFeeList(List<PriceFeeList> priceFeeList) {
        this.priceFeeList = priceFeeList;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }
}
