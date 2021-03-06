package com.example.prography_6th_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailPageActivity extends AppCompatActivity {

    TextView title;
    TextView description;
    TextView director, producer, release_date, rt_score;
    RatingBar ratingBar;
    float rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        title = (TextView)findViewById(R.id.title_detail);
        description = (TextView)findViewById(R.id.description_detail);
        director = (TextView)findViewById(R.id.director_detail);
        producer = (TextView)findViewById(R.id.producer_detail);
        release_date = (TextView)findViewById(R.id.release_date_detail);
        rt_score = (TextView)findViewById(R.id.rt_score_detail);

        Intent passedIntent = getIntent();
        processIntent(passedIntent);

        ratingBar = (RatingBar)findViewById(R.id.ratingbar);
        rate= ((rate*5) /100);
        ratingBar.setRating(rate);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void processIntent(Intent intent) {
        ParcelableMovieData data = (ParcelableMovieData)intent.getParcelableExtra("data");

        if (data != null) {
            title.setText(data.title);
            description.setText(data.description);
            director.setText("[ 감독 ]  "+data.director);
            producer.setText("[ 연출 ]  "+data.producer);
            release_date.setText("[ 개봉일 ] "+data.releaseDate);
            rt_score.setText(data.rtScore);
            rate = Float.parseFloat(data.rtScore);
        }
    }
}
