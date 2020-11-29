package com.nyan.weather;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nyan.domain.models.WeatherDetailsModel;
import com.nyan.weather.viewmodel.MainViewModel;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  @Inject
  ViewModelProvider.Factory viewModelFactory;

  private MainViewModel mainViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    mainViewModel = viewModelFactory.create(MainViewModel.class);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show();
        mainViewModel.getWeatherData();
      }
    });

    mainViewModel.getWeatherLiveData().observe(this, new Observer<WeatherDetailsModel>() {
      @Override
      public void onChanged(WeatherDetailsModel weatherDetailsModel) {
        Log.d(this.getClass().getSimpleName(), "onChanged");
        if (weatherDetailsModel != null) {
          Toast.makeText(MainActivity.this, weatherDetailsModel.getWeather().get(0).getMain(), Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//    // Inflate the menu; this adds items to the action bar if it is present.
//    getMenuInflater().inflate(R.menu.menu_main, menu);
//    return true;
//  }
//
//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//    // Handle action bar item clicks here. The action bar will
//    // automatically handle clicks on the Home/Up button, so long
//    // as you specify a parent activity in AndroidManifest.xml.
//    int id = item.getItemId();
//
//    //noinspection SimplifiableIfStatement
//    if (id == R.id.action_settings) {
//      return true;
//    }
//
//    return super.onOptionsItemSelected(item);
//  }
}