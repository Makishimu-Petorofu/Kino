package com.example.kinosearch.auth.Api.serv;



import com.example.kinosearch.MoviesCover;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoverService {
    @GET("/movies/cover")
    Call<MoviesCover> getDate();
}
