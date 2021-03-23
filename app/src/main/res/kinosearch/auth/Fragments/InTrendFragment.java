package com.example.kinosearch.auth.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kinosearch.R;
import com.example.kinosearch.MovieAdapter;
import com.example.kinosearch.Movies;
import com.example.kinosearch.auth.Api.serv.UserMovies;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InTrendFragment extends Fragment {


    Context context;
    private ProgressBar mProgressBar;
    RecyclerView mRecyclerView;

    List<Movies> mMovies = new ArrayList<Movies>();
    public InTrendFragment() {
        super(R.layout.fragment_in_trend);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStatem) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_in_trend, container, false);
        mProgressBar = view.findViewById(R.id.progressBar3);
        mProgressBar.setVisibility(View.INVISIBLE);

        mMovies = new ArrayList<>();

        mRecyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        MovieAdapter adapter = new MovieAdapter(mMovies);
        mRecyclerView.setAdapter(adapter);

        mProgressBar.setVisibility(View.VISIBLE);

        final Call<List<Movies>> call = UserMovies.ApiClient.getMovieInTrend().getDate();
        call.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
                if (response.isSuccessful()) {
                    mMovies.addAll(response.body());
                    mRecyclerView.getAdapter().notifyDataSetChanged();
                    mProgressBar.setVisibility(View.INVISIBLE);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

        return view;

    }
}