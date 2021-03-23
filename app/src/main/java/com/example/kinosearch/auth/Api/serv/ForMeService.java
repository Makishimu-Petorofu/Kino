package com.example.kinosearch.auth.Api.serv;



import com.example.kinosearch.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ForMeService {
    @GET("/movies?filter=forMe")
    Call<List<Movies>> getDate();
}
