package com.example.kinosearch.auth.Api.serv;



import com.example.kinosearch.LoginRequest;
import com.example.kinosearch.LoginResponses;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("auth/login")
    Call<LoginResponses> authUser(@Body LoginRequest loginBody);
}
