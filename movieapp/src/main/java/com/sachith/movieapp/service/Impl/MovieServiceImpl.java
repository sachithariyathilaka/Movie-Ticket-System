package com.sachith.movieapp.service.Impl;

import com.apple.eawt.Application;
import com.sachith.movieapp.model.Movie;
import com.sachith.movieapp.model.TicketData;
import com.sachith.movieapp.repository.BookingRepository;
import com.sachith.movieapp.repository.MovieRepository;
import com.sachith.movieapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger logger= Logger.getLogger(String.valueOf(Application.class));

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private BookingRepository ticketRepository;

    @Override
    public Boolean saveMovie(Movie movie) {
        logger.info(getClass().toString() +" << Movie Register Done >>");
        Movie newMovie = new Movie();
        newMovie.setName(movie.getName());
        newMovie.setOwner(movie.getOwner());
        newMovie.setPrice(movie.getPrice());
        newMovie.setDate(movie.getDate());
        newMovie.setShowTime(movie.getShowTime());
        newMovie.setStatus(movie.getStatus());
        newMovie.setAvailableTickets(movie.getAvailableTickets());
        if(movieRepository.save(newMovie) != null){
          return true;
        } else{
            return false;
        }
    }

    @Override
    public Movie searchMovie(String name) {
        logger.info(getClass().toString() +" << Movie Search Performed >>");
        return movieRepository.findByName(name);
    }

    @Override
    public Movie removeMovie(int id) {
        return movieRepository.deleteById(id);
    }

    @Override
    public TicketData makeBooking(TicketData ticketData) {
        logger.info(getClass().toString() +" << Movie Booking Done >>");
        TicketData data = new TicketData();
        data.setMovie(ticketData.getMovie());
        data.setDate(ticketData.getDate());
        data.setPaymentStatus(ticketData.getPaymentStatus());
        data.setShowTime(ticketData.getShowTime());
        data.setTicketCount(ticketData.getTicketCount());
        data.setUserId(ticketData.getUserId());
        Movie movie = movieRepository.findByName(ticketData.getMovie());

        if (movie.getAvailableTickets() > ticketData.getTicketCount() || movie.getAvailableTickets() == ticketData.getTicketCount()) {
            Movie updatedMovie = new Movie();
            updatedMovie.setName(movie.getName());
            updatedMovie.setOwner(movie.getOwner());
            updatedMovie.setPrice(movie.getPrice());
            updatedMovie.setDate(movie.getDate());
            updatedMovie.setShowTime(movie.getShowTime());
            updatedMovie.setStatus(movie.getStatus());
            updatedMovie.setAvailableTickets(movie.getAvailableTickets() - ticketData.getTicketCount());
            movieRepository.deleteById(movie.getId());
            movieRepository.save(updatedMovie);
           return ticketRepository.save(data);
        }else{
           return null;
        }
    }

    @Override
    public TicketData cancelBooking(int id) {
        TicketData ticketData = ticketRepository.findById(id);
        Movie movie = movieRepository.findByName(ticketData.getMovie());
        Movie updatedMovie = new Movie();
        updatedMovie.setName(movie.getName());
        updatedMovie.setOwner(movie.getOwner());
        updatedMovie.setPrice(movie.getPrice());
        updatedMovie.setDate(movie.getDate());
        updatedMovie.setShowTime(movie.getShowTime());
        updatedMovie.setStatus(movie.getStatus());
        updatedMovie.setAvailableTickets(movie.getAvailableTickets() + ticketData.getTicketCount());
        movieRepository.deleteById(movie.getId());
        movieRepository.save(updatedMovie);
        return ticketRepository.deleteById(id);
    }

    @Override
    public ArrayList<Movie> loadAll() {
        return movieRepository.getAllByStatusTrue();
    }

    @Override
    public ArrayList<TicketData> loadAllBookings(int id) {
        return ticketRepository.findAllByUserId(id);
    }
}
