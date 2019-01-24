package com.hackathon.filighbooking.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class PriceFeeList implements Serializable {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("fee")
    @Expose
    private Double fee;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}