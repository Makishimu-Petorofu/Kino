package com.example.kinosearch.auth.Api.serv;



import com.example.kinosearch.RegisterRequest;
import com.example.kinosearch.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("auth/register")
    Call<RegisterResponse> saveUser(@Body RegisterRequest registerRequest);
}
