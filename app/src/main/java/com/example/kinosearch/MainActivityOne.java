package com.example.kinosearch;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kinosearch.auth.Api.serv.UserMovies;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityOne extends AppCompatActivity {

    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";
    Button btn_forMe,btn_inTrend, btn_new;
    ImageView img_cover;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);


        btn_forMe = findViewById(R.id.btn_forme);
        btn_inTrend = findViewById(R.id.btn_intrend);
        btn_new = findViewById(R.id.btn_new);


        final Call<MoviesCover> call = UserMovies.ApiClient.getMovieCover().getDate();
        call.enqueue(new Callback<MoviesCover>() {
            @Override
            public void onResponse(Call<MoviesCover> call, Response<MoviesCover> response) {
                if (response.isSuccessful()){
                    MoviesCover moviesCover = response.body();
                    Picasso.with(MainActivityOne.this)
                            .load(PHOTO_URL + moviesCover.getBackgroundImage())
                            .into(img_cover);
                }else{
                    Toast.makeText(MainActivityOne.this,"Error", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<MoviesCover> call, Throwable t) {
                Toast.makeText(MainActivityOne.this,t.getMessage() , Toast.LENGTH_LONG).show();
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_view, ForYouFrag.class, null)
                    .commit();
        }

    }

    public void onClick(View view){
        Drawable myIcon = this.getResources().getDrawable(R.drawable.drawable_bottom);
        switch (view.getId()){
            case R.id.btn_forme:
                btn_forMe.setCompoundDrawablesWithIntrinsicBounds(null, null, null, myIcon);
                btn_inTrend.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                btn_new.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_view, ForYouFrag.class, null)
                        .commit();
                break;
            case R.id.btn_intrend:
                btn_forMe.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                btn_inTrend.setCompoundDrawablesWithIntrinsicBounds(null, null, null, myIcon);
                btn_new.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_view, TrendFrag.class, null)
                        .commit();
                break;
            case  R.id.btn_new:
                btn_forMe.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                btn_inTrend.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                btn_new.setCompoundDrawablesWithIntrinsicBounds(null, null, null, myIcon);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_view, NewMovie.class, null)
                        .commit();
                break;
        }
    }

}
