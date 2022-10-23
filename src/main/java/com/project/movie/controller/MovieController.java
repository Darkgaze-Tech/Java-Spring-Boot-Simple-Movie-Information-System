package com.project.movie.controller;

import com.project.movie.domain.dto.MovieDto;
import com.project.movie.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Object> createNewMovie(@RequestBody MovieDto request) {
        return movieService.addMovie(request);
    }
    @GetMapping
    public ResponseEntity<Object> getAllMovie() {
        return movieService.getMovie();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return movieService.getMovieById(id);
    }

    @PostMapping(value = "{id}")
    public ResponseEntity<Object> update(@RequestBody MovieDto request, @PathVariable Long id){
        return movieService.updateById(request, id);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        return movieService.deleteById(id);
    }
}

