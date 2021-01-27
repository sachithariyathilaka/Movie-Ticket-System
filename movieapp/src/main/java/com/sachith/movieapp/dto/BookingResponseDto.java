package com.sachith.movieapp.dto;

import com.sachith.movieapp.model.TicketData;

import java.util.ArrayList;

public class BookingResponseDto {
    private int status;
    private String message;
    private ArrayList<TicketData> tickets;

    public BookingResponseDto(int status, String message, ArrayList<TicketData> tickets) {
        this.status = status;
        this.message = message;
        this.tickets = tickets;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<TicketData> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<TicketData> tickets) {
        this.tickets = tickets;
    }
}
