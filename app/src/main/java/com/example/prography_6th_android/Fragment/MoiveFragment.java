package com.example.prography_6th_android.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prography_6th_android.DetailPageActivity;
import com.example.prography_6th_android.Movie;
import com.example.prography_6th_android.ParcelableMovieData;
import com.example.prography_6th_android.R;
import com.example.prography_6th_android.RecyclerAdapter;
import com.example.prography_6th_android.RetrofitAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoiveFragment extends Fragment {
    final static String BASE_URL = "https://ghibliapi.herokuapp.com";
    private Retrofit mRetrofit;
    private RetrofitAPI mRetrofitAPI;
    private Call<List<Movie>> mCallMoiveList;

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        setmRetrofit();
        callMovieList();

        return rootView;
    }

    private void setmRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);
    }

    private void callMovieList() {
        mCallMoiveList = mRetrofitAPI.getMovie();
        mCallMoiveList.enqueue(mRetrofitCallback);
    }

    private Callback<List<Movie>> mRetrofitCallback = new Callback<List<Movie>>() {
        @Override
        public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
            List<Movie> result = response.body();

            ArrayList<Movie> movieList = new ArrayList<>();
            for (Movie re : result) {
                movieList.add(re);
            }

            recyclerAdapter = new RecyclerAdapter(movieList);
            recyclerView.setAdapter(recyclerAdapter);

            recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(RecyclerAdapter.ViewHolder holder, View v, int positon) {
                    Movie item = recyclerAdapter.getItem(positon);
                    Intent intent = new Intent(getContext(), DetailPageActivity.class);

                    String[] inputData = new String[] {
                            item.getTitle(),
                            item.getDescription(),
                            item.getDirector(),
                            item.getProducer(),
                            item.getReleaseDate(),
                            item.getRtScore()
                    };
                    ParcelableMovieData data = new ParcelableMovieData(inputData);
                    intent.putExtra("data", data);

                    startActivityForResult(intent, 101);
                }
            });
        }

        @Override
        public void onFailure(Call<List<Movie>> call, Throwable t) {
            Log.e("onFailure", "result");
        }
    };
}
