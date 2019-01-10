package com.hackathon.filighbooking.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PriceFeeList {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("fee")
    @Expose
    private Integer fee;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }
}