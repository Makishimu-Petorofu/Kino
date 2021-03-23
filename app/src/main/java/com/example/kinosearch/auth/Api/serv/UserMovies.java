package com.example.kinosearch.auth.Api.serv;

import com.example.kinosearch.IdMovie;
import com.example.kinosearch.Movies;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserMovies {

    @GET("/usermovies?filter=compilation")
    Call<List<Movies>> getDate(@Path("token") int token);

    class ApiClient {
        public static Retrofit getRetrofit(){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("http://cinema.areas.su/")
                    .build();
            return retrofit;
        }

        public static LoginService getLogin(){
            LoginService loginService = getRetrofit().create(LoginService.class);

            return  loginService;
        }
        public static NewService getMovieNew(){
            NewService movieService = getRetrofit().create(NewService.class);

            return movieService;
        }
        public static InTrendService getMovieInTrend(){
            InTrendService inTrendService = getRetrofit().create(InTrendService.class);
            return  inTrendService;
        }
        public static ForMeService getMovieForMe(){
            ForMeService forMeService = getRetrofit().create(ForMeService.class);
            return  forMeService;
        }
        public static CoverService getMovieCover(){
            CoverService coverService = getRetrofit().create(CoverService.class);
            return  coverService;
        }
        public static UserMovies getUserMovies(){
            UserMovies userMovies = getRetrofit().create(UserMovies.class);
            return userMovies;
        }
        public static IdMovie getIdMovie(){
            IdMovie idMovie = getRetrofit().create(IdMovie.class);
            return  idMovie;
        }
        public static ApiService getService(){
            ApiService apiService = getRetrofit().create(ApiService.class);

            return  apiService;
        }
    }
}
