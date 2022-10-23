package com.project.movie.service;

import com.project.movie.constant.AppConstant.ResponseCode;
import com.project.movie.domain.dao.Genre;
import com.project.movie.domain.dao.Movie;
import com.project.movie.domain.dao.User;
import com.project.movie.domain.dto.MovieDto;
import com.project.movie.repository.GenreRepository;
import com.project.movie.repository.MovieRepository;
import com.project.movie.repository.UserRepository;
import com.project.movie.util.ResponseUtil;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public ResponseEntity<Object> addMovie(MovieDto request) {
        log.info("Executing save new movie");
        try {
            Optional<Genre> genre = genreRepository.findById(request.getMovieGenre().getId());
            if (genre.isEmpty()) {
                log.info("Genre [{}] not found", request.getMovieGenre().getId());
                return ResponseUtil.build(ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }

            Optional<User> user = userRepository.findById(request.getMovieUser().getId());
            if (user.isEmpty()) {
                log.info("User [{}] not found", request.getMovieUser().getId());
                return ResponseUtil.build(ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }
            
            Movie movie = mapper.map(request, Movie.class);
            movie.setMovieGenre(genre.get());
            movie.setMovieUser(user.get());
            movieRepository.save(movie);
            return ResponseUtil.build(ResponseCode.SUCCESS, mapper.map(movie, MovieDto.class), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Got an error when saving new movie. Error: {}", e.getMessage());
            return ResponseUtil.build(ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getMovie() {
        log.info("Executing get all movie");
        try {
            List<Movie> movieList = movieRepository.findAll();
            return ResponseUtil.build(ResponseCode.SUCCESS, movieList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Got an error when saving new movie. Error: {}", e.getMessage());
            return ResponseUtil.build(ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getMovieById(Long id) {
        log.info("Executing get movie by id");
        try {
            Optional<Movie> movie = movieRepository.findById(id);
            return movie.map(value -> ResponseUtil.build(ResponseCode.SUCCESS, value, HttpStatus.OK)).orElseGet(() ->
                    ResponseUtil.build(ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND));
        }catch (Exception e){
            return ResponseUtil.build(ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateById(MovieDto request, Long id) {
        log.info("Executing update movie by id");
        try {
            Optional<Movie> getById = movieRepository.findById(id);
            if (!getById.isPresent()) return ResponseUtil.build(ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            Movie movie = getById.get();
            movie.setMovieTitle(request.getMovieTitle());
            movie.setMoviePlot(request.getMoviePlot());
            movie.setMovieLanguage(request.getMovieLanguage());
            movie.setMovieReleaseYear(request.getMovieReleaseYear());

            Optional<Genre> genre = genreRepository.findById(request.getMovieGenre().getId());
            if (genre.isEmpty()) {
                log.info("Genre [{}] not found", request.getMovieGenre().getId());
                return ResponseUtil.build(ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }

            Optional<User> user = userRepository.findById(request.getMovieUser().getId());
            if (user.isEmpty()) {
                log.info("User [{}] not found", request.getMovieUser().getId());
                return ResponseUtil.build(ResponseCode.DATA_NOT_FOUND, null, HttpStatus.BAD_REQUEST);
            }
            movie.setMovieGenre(genre.get());
            movie.setMovieUser(user.get());
            movieRepository.save(movie);
            return ResponseUtil.build(ResponseCode.SUCCESS, mapper.map(movie, MovieDto.class), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteById(Long id) {
        log.info("Executing delete movie by id");
        try {
            Optional<Movie> data = movieRepository.findById(id);
            if (!data.isPresent()) return ResponseUtil.build(ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            movieRepository.delete(data.get());
            return ResponseUtil.build(ResponseCode.SUCCESS, null, HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build(ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
