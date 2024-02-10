package com.obf.movie.rest;


import com.codahale.metrics.annotation.Timed;
import com.obf.movie.domain.Movie;
import com.obf.movie.service.MovieService;
import com.obf.movie.util.ResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/obf-movie-server/api")
public class MovieResource {

    private static final Logger log = LoggerFactory.getLogger(MovieResource.class);

    private final MovieService movieService;

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }


    @Timed
    @GetMapping(value = "/movie/getbyoid/{oId}")
    public ResponseEntity<Movie> getOneMovie(@PathVariable("oId") Long oId) {

        try {
            log.info("get invoked: {} ", oId);

            Movie response = movieService.getOneMovie(oId);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", "");
        }
    }




    @Timed
    @GetMapping(value = "/movie/getallmovies")
    public List<Movie> getAllMovies() {

        try {
            //log.info("get invoked: {} ", originalTitle);

            List<Movie> response = movieService.getAllMovies();

            return response;
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", "");
        }
    }

    @Timed
    @GetMapping(value = "/movie/getbyoriginalTitle/{originalTitle}")
    public List<Movie> getMoviesByOriginalTitle(@PathVariable("originalTitle") String originalTitle) {

        try {
            log.info("get invoked: {} ", originalTitle);

            List<Movie> response = movieService.getMoviesByOriginalTitle(originalTitle);

            return response;
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", "");
        }
    }

    @Timed
    @GetMapping(value = "/movie/searchbyoriginalTitle/{originalTitle}")
    public List<Movie> searchMoviesByOriginalTitle(@PathVariable("originalTitle") String originalTitle) {

        try {
            log.info("get invoked: {} ", originalTitle);

            List<Movie> response = movieService.searchMoviesByOriginalTitle(originalTitle);

            return response;
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", "");
        }
    }

    @Timed
    @PostMapping(value = "/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie request) {

        try {
            log.info("add() invoked for transaction with oid: {} ", request.getOid());

            Movie response = movieService.saveNewMovie(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "add", "");
        }
    }

    @Timed
    @PutMapping(value = "/movie")
    public ResponseEntity<Movie> update(@RequestBody @Valid Movie request) {

        try {
            log.info("update() invoked for movie with oid: {} ", request.getOid());

            Movie response = movieService.updateMovie(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "update", "");
        }
    }

    @Timed
    @PatchMapping(value = "/movie")
    public ResponseEntity<Movie> partialUpdate(@RequestBody Movie request) throws Exception {

        try {
            log.info("partialUpdate() invoked for transaction with oid: {} ", request.getOid());

            Movie response = movieService.partialUpdate(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "partialUpdate", "");
        }
    }

    @Timed
    @DeleteMapping(value = "/movie/deletemovie/{oId}")
    public boolean deleteMovie(@PathVariable("oId") Long oId) throws Exception{

        try {
            log.info("get invoked: {} ", oId);
            return movieService.deleteMovie(oId);
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", "");
        }
    }
}
