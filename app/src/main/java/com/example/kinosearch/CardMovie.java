package com.example.kinosearch;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.kinosearch.auth.Api.serv.UserMovies;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardMovie extends AppCompatActivity {

    TextView name, age, description;
    ImageView poster;
    int id;
    private Context mContext;
    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";
    private final static String VIDEO_URL = "http://cinema.areas.su/up/video/";

    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_movie);

        Intent intent = getIntent();
        id = intent.getIntExtra("movieId", 0);

        init();

        Call<Movies> call = UserMovies.ApiClient.getIdMovie().getMovieDate(id);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if(response.isSuccessful()){
                    Movies movie = response.body();
                    name.setText(movie.getName());
                    if(Integer.parseInt(movie.getAge()) >= 18){
                        age.setTextColor(Color.parseColor("#ff0000"));
                    }
                    else if(Integer.parseInt(movie.getAge()) < 18){
                        age.setTextColor(Color.parseColor("#008000"));
                    }
                    age.setText(movie.getAge() + "+");
                    description.setText(movie.getDescription());

                    Picasso.with(mContext)
                            .load(PHOTO_URL + movie.getPoster())
                            .resize(500,700)
                            .into(poster);


                    mSettings = getSharedPreferences("MoviePrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putInt("idMovie", movie.getMovieId());
                    editor.apply();
                }
                else{
                    Toast.makeText(mContext, response.errorBody().toString(), Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

    }

    public void init()
    {
        name = (TextView) findViewById(R.id.tvMovieName);
        age = (TextView)  findViewById(R.id.tvMovieAge);
        description = (TextView)  findViewById(R.id.tvMovieDesc);
        poster = (ImageView)  findViewById(R.id.ivMoviePoster);
    }
}
