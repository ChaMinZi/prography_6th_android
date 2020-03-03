package com.example.prography_6th_android;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {
    @GET("/films")
    Call<List<Movie>> getMovie();
}
