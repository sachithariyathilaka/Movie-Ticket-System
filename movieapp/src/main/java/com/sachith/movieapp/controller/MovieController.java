package com.sachith.movieapp.controller;

import com.apple.eawt.Application;
import com.sachith.movieapp.dto.BookingResponseDto;
import com.sachith.movieapp.dto.MovieResponseDto;
import com.sachith.movieapp.model.Movie;
import com.sachith.movieapp.model.TicketData;
import com.sachith.movieapp.service.Impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.logging.Logger;

@RestController
@CrossOrigin
public class MovieController {

    private static final Logger logger = Logger.getLogger(String.valueOf(Application.class));

    @Autowired
    private MovieServiceImpl movieService;

    @RequestMapping(value = "/movie/register", method = RequestMethod.POST)
    public ResponseEntity<?> insertMovie(@RequestBody Movie movie) {
        logger.info(getClass().toString() + " << Movie Register Controller >>");
        Boolean status = movieService.saveMovie(movie);
        if (status) {
            return ResponseEntity.ok(new MovieResponseDto(1, "Movie added",null));
        } else {
            return ResponseEntity.ok(new MovieResponseDto(0, "Movie failed to add",null));
        }
    }

    @RequestMapping(value = "/movie/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchMovie(@RequestParam String name) {
        logger.info(getClass().toString() + " << Movie Search Controller >>");
        Movie movie = movieService.searchMovie(name);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        if(movie != null){
            return ResponseEntity.ok(new MovieResponseDto(1,"Movie exists", movies));
        } else{
            return ResponseEntity.ok(new MovieResponseDto(0,"No Movie Found", null));
        }
    }

    @RequestMapping(value = "/movie/remove", method = RequestMethod.POST)
    public ResponseEntity<?> removeMovie(@RequestParam int id) {
        logger.info(getClass().toString() + " << Movie Remove Controller >>");
        Movie movie = movieService.removeMovie(id);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        return ResponseEntity.ok(new MovieResponseDto(1,"Movie Removed", movies));
    }

    @RequestMapping(value = "/movie/booking", method = RequestMethod.POST)
    public ResponseEntity<?> bookingTicket(@RequestBody TicketData ticketData) {
        logger.info(getClass().toString() + " << Ticket Booking Controller >>");
        TicketData ticket = movieService.makeBooking(ticketData);
        ArrayList<TicketData> tickets = new ArrayList<>();
        tickets.add(ticket);
        if (ticket != null) {
            return ResponseEntity.ok(new BookingResponseDto(1, "Ticket Booking Success",tickets));
        } else {
            return ResponseEntity.ok(new BookingResponseDto(0, "No enough tickets available",null));
        }
    }

    @RequestMapping(value = "/movie/cancel", method = RequestMethod.POST)
    public ResponseEntity<?> bookingCancel(@RequestParam int id) {
        logger.info(getClass().toString() + " << Booking Cancel Controller >>");
        TicketData ticketData = movieService.cancelBooking(id);
        ArrayList<TicketData> tickets = new ArrayList<>();
        tickets.add(ticketData);
        return ResponseEntity.ok(new BookingResponseDto(1,"Ticket Booking Canceled", tickets));
    }

    @RequestMapping(value = "movie/all", method = RequestMethod.GET)
    public ResponseEntity<?> allMovies() {
        logger.info(getClass().toString() + " << Load all Movies Controller >>");
        ArrayList<Movie> movies = movieService.loadAll();
        return ResponseEntity.ok(new MovieResponseDto(1,"All Movies Fetched", movies));
    }

    @RequestMapping(value = "movie/allBookings", method = RequestMethod.GET)
    public ResponseEntity<?> allMovieBookings(@RequestParam int id) {
        logger.info(getClass().toString() + " << Load all Movie Bookings Controller >>");
        ArrayList<TicketData> bookings = movieService.loadAllBookings(id);
        return ResponseEntity.ok(new BookingResponseDto(1,"All Bookings Fetched",bookings));
    }
}
