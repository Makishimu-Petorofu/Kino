package com.example.kinosearch.auth.Api.serv;



import com.example.kinosearch.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InTrendService {
    @GET("/movies?filter=inTrend")
    Call<List<Movies>> getDate();
}
