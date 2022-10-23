package com.project.movie.controller;

import com.project.movie.domain.dto.GenreDto;
import com.project.movie.service.GenreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    public ResponseEntity<Object> createNewGenre(@RequestBody GenreDto request) {
        return genreService.addGenre(request);
    }

    @GetMapping
    public ResponseEntity<Object> getAllGenre() {
        return genreService.getGenre();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return genreService.getGenreById(id);
    }

    @PostMapping(value = "{id}")
    public ResponseEntity<Object> update(@RequestBody GenreDto request, @PathVariable Long id){
        return genreService.updateById(request, id);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        return genreService.deleteById(id);
    }
    
}

