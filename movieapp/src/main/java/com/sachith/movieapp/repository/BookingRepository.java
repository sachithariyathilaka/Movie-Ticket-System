package com.sachith.movieapp.repository;

import com.sachith.movieapp.model.TicketData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface BookingRepository extends JpaRepository<TicketData, Integer> {
    TicketData save(TicketData ticketData);
    TicketData deleteById(int id);
    TicketData findById(int id);
    ArrayList<TicketData> findAllByUserId(int id);
}
