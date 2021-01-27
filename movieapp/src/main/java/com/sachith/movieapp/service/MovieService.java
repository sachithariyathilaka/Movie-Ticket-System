package com.sachith.movieapp.service;

import com.sachith.movieapp.model.Movie;
import com.sachith.movieapp.model.TicketData;

import java.util.ArrayList;

public interface MovieService {
    Boolean saveMovie(Movie movie);
    Movie searchMovie(String name);
    Movie removeMovie(int id);
    TicketData makeBooking(TicketData ticketData);
    TicketData cancelBooking(int id);
    ArrayList<Movie> loadAll();
    ArrayList<TicketData> loadAllBookings(int id);
}
