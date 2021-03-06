package com.stackroute.Movie.controller;

import com.stackroute.Movie.Exception.MovieAlreadyFoundException;
import com.stackroute.Movie.model.Movie;
import com.stackroute.Movie.service.MovieService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(value = "/api/v1")
@SpringBootApplication
public class MovieController {

    @Autowired
    public void setMovieService( MovieService movieService) {
        this.movieService = movieService;
    }

    MovieService movieService;




    public MovieController(MovieService movieService) {
        this.movieService =movieService;
    }


    @RequestMapping(value = "/")
    public String home() {
        return "Eureka Client application";
    }


    @PostMapping("movie")
    public ResponseEntity<?> savemovie(@RequestBody Movie movie) throws MovieAlreadyFoundException
    {
        ResponseEntity responseEntity;
            movieService.saveMovie(movie);
            responseEntity= new ResponseEntity<Movie>(movie,HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("movie")
    public ResponseEntity<?> getmovielist()
    {
        ResponseEntity responseEntity;

        try{
            List<Movie> movielist=movieService.getallmovie();
            responseEntity=new ResponseEntity<List<Movie>>(movielist,HttpStatus.OK);
        }
        catch(Exception e)
        {
            responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            return null;
        }
        return responseEntity;
    }


    @PutMapping("movie")
    public ResponseEntity<?> updatemovie(@RequestBody Movie movie) throws MovieAlreadyFoundException {
        ResponseEntity responseEntity;

        try{
            Movie updatemovie=movieService.updateMovieusingput(movie);
            responseEntity=new ResponseEntity<Movie>(movie,HttpStatus.OK);
        }
        catch(MovieAlreadyFoundException e)
        {
            responseEntity= new ResponseEntity<Movie>(movie,HttpStatus.CONFLICT);
            throw e;
        }
        return responseEntity;

    }




/*
    @PatchMapping(path = "/movie/{movieId}/{movieCast}")
    public ResponseEntity<?> updatemovie(@PathVariable("movieId") int movieId,@PathVariable String movieCast)
    {
        ResponseEntity responseEntity;

        try{
            System.out.println(movieId+movieCast);
            Movie updatemovie=movieService.updateMovie(movieId,movieCast);
            responseEntity=new ResponseEntity<String>("updated",HttpStatus.OK);
        }
        catch(Exception e)
        {
            responseEntity= new ResponseEntity<String>("couldn't update movie in database",HttpStatus.CONFLICT);
            return null;
        }
        return responseEntity;
    }
    */

    @GetMapping(path = "/movie/id/{movieId}")
    public ResponseEntity<?> getmoviebyid(@PathVariable int movieId)
    {
        ResponseEntity responseEntity;

        try{
            Movie movie=movieService.getmoviebyid(movieId);
            responseEntity=new ResponseEntity<Movie>(movie,HttpStatus.OK);
        }
        catch(Exception e)
        {
            responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            return null;
        }
        return responseEntity;
    }

 /*   @GetMapping(path = "/movie/name={movieName}")
    public ResponseEntity<?> getmoviebyname(@PathVariable String movieName)
    {
        ResponseEntity responseEntity;

        try{
            System.out.println("this is my "+movieName);
            Movie movie=movieService.getmoviebyname(movieName);
            responseEntity=new ResponseEntity<Movie>(movie,HttpStatus.OK);
        }
        catch(Exception e)
        {
            responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            return null;
        }
        return responseEntity;
    }*/

    @GetMapping(path = "/movie/moviename")
    public ResponseEntity<?> getmoviebyname(@RequestParam String movieName)
    {
        ResponseEntity responseEntity;
        try{
            System.out.println("this is my "+movieName);
            Movie movie=movieService.getmoviebyname(movieName);
            responseEntity=new ResponseEntity<Movie>(movie,HttpStatus.OK);
        }
        catch(Exception e)
        {
            responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            return null;
        }
        return responseEntity;
    }






    @DeleteMapping(path = "/movie/{movieId}")
    public ResponseEntity<?> deletemoviebyid(@PathVariable int movieId)
    {
        ResponseEntity responseEntity;

        try{
            Movie movie=movieService.deletemoviebyid(movieId);
            responseEntity=new ResponseEntity<Movie>(movie,HttpStatus.OK);
        }
        catch(Exception e)
        {
            responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            return null;
        }
        return responseEntity;
    }

}
