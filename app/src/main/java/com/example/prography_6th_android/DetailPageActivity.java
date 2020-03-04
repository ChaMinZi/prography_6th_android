package com.example.prography_6th_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailPageActivity extends AppCompatActivity {

    TextView title;
    TextView description;
    TextView director, producer, release_date, rt_score;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        title = (TextView)findViewById(R.id.title_detail);
        description = (TextView)findViewById(R.id.description_detail);
        director = (TextView)findViewById(R.id.director_detail);
        producer = (TextView)findViewById(R.id.producer_detail);
        release_date = (TextView)findViewById(R.id.release_date_detail);
        rt_score = (TextView)findViewById(R.id.rt_score_detail);

        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    private void processIntent(Intent intent) {
        ParcelableMovieData data = (ParcelableMovieData)intent.getParcelableExtra("data");

        if (data != null) {
            title.setText(data.title);
            description.setText(data.description);
            director.setText(data.director);
            producer.setText(data.producer);
            release_date.setText(data.releaseDate);
            rt_score.setText(data.rtScore);
        }
    }
}
