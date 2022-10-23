package com.project.movie.service;

import com.project.movie.constant.AppConstant.ResponseCode;
import com.project.movie.domain.dao.Genre;
import com.project.movie.domain.dto.GenreDto;
import com.project.movie.repository.GenreRepository;
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
public class GenreService {
    
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper mapper;

    public ResponseEntity<Object> addGenre(GenreDto request) {
        log.info("Executing save new genre");
        try {
            Genre genre = mapper.map(request, Genre.class);
            genreRepository.save(genre);
            return ResponseUtil.build(ResponseCode.SUCCESS, mapper.map(genre, GenreDto.class), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Got an error when saving new genre. Error: {}", e.getMessage());
            return ResponseUtil.build(ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getGenre() {
        log.info("Executing get all genre");
        try {
            List<Genre> genres = genreRepository.findAll();
            return ResponseUtil.build(ResponseCode.SUCCESS, genres, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Got an error when saving new genre. Error: {}", e.getMessage());
            return ResponseUtil.build(ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getGenreById(Long id) {
        log.info("Executing get genre by id");
        try {
            Optional<Genre> genre = genreRepository.findById(id);
            return genre.map(value -> ResponseUtil.build(ResponseCode.SUCCESS, value, HttpStatus.OK)).orElseGet(() ->
                    ResponseUtil.build(ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND));
        }catch (Exception e){
            return ResponseUtil.build(ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateById(GenreDto request, Long id) {
        log.info("Executing update genre by id");
        try {
            Optional<Genre> getById = genreRepository.findById(id);
            if (!getById.isPresent()) return ResponseUtil.build(ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            Genre genre = getById.get();
            genre.setGenreName(request.getGenreName());
            genreRepository.save(genre);
            return ResponseUtil.build(ResponseCode.SUCCESS, mapper.map(genre, GenreDto.class), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteById(Long id) {
        log.info("Executing delete genre by id");
        try {
            Optional<Genre> data = genreRepository.findById(id);
            if (!data.isPresent()) return ResponseUtil.build(ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            genreRepository.delete(data.get());
            return ResponseUtil.build(ResponseCode.SUCCESS, null, HttpStatus.OK);
        }catch (Exception e){
            return ResponseUtil.build(ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

