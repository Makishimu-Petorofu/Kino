package com.example.kinosearch;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IdMovie {
    @GET("movies/{movieId}")
    Call<Movies> getMovieDate(@Path("movieId") int movieId);

}
