package com.nyan.weather.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.nyan.weather.R;

public class SampleActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample);

    //FOR DEMO PURPOSE.
    try {
      getSupportActionBar().hide();
    } catch (NullPointerException ex) {
      ex.printStackTrace();
    }
  }
}