package com.example.kinosearch.auth.Api.serv;



import com.example.kinosearch.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewService {
    @GET("/movies?filter=new")
    Call<List<Movies>> getDate();
}
