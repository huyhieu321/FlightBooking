package com.hackathon.filighbooking.adapter;

import com.hackathon.filighbooking.model.entity.Flight;

public interface ChooseFlightTabHostListener {
    void getFlightDetails(Flight flight);
    void showNameFragment(String data);
}
