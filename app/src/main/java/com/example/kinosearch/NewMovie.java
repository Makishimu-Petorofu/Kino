package com.example.kinosearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kinosearch.auth.Api.serv.UserMovies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewMovie extends Fragment {
    private NotificationsViewModel notificationsViewModel;
    TextView movieName;
    ImageView moviePoster;

    int id;
    private SharedPreferences mSettings;

    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_new, container, false);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        movieName = root.findViewById(R.id.tvLastMovieName);
        moviePoster = root.findViewById(R.id.tvLastMoviePoster);
        mSettings = getContext().getApplicationContext().getSharedPreferences("MoviePrefs", Context.MODE_PRIVATE);


        id = mSettings.getInt("idMovie", 0);

        Call<Movies> call = UserMovies.ApiClient.getService().getMovieDate(id);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if(response.isSuccessful()){
                    Movies movie = response.body();
                    movieName.setText(movie.getName());

                    Picasso.with(getContext())
                            .load(PHOTO_URL + movie.getPoster())
                            .resize(500,700)
                            .into(moviePoster);

                }
                else{
                    Toast.makeText(getContext(), response.errorBody().toString(), Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

        moviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CardMovie.class).putExtra("movieId", id);
                getContext().startActivity(intent);
            }
        });

        return root;

    }
}