package com.nyan.weather;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.snackbar.Snackbar;
import com.nyan.domain.models.WeatherDetailsModel;
import com.nyan.weather.databinding.ActivityMainBinding;
import com.nyan.weather.utils.PermissionManager;
import com.nyan.weather.viewmodel.MainViewModel;
import com.nyan.weather.viewmodel.MainViewModelFactory;
import dagger.android.AndroidInjection;
import javax.inject.Inject;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

  @Inject
  MainViewModelFactory viewModelFactory;

  @Inject
  PermissionManager permissionManager;

  private MainViewModel mainViewModel;

  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    View rootView = binding.getRoot();
    setContentView(rootView);

    setSupportActionBar(binding.toolbar);

    mainViewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);

    binding.fab.setOnClickListener(view -> requestLocationPermission());

    mainViewModel.getWeatherLiveData().observe(this, weatherDetailsModel -> {
      Timber.d("onChanged");
      if (weatherDetailsModel != null) {
        setView(weatherDetailsModel);
      }
    });

    mainViewModel.getLocationLiveData().observe(this, locationModel -> {
      Timber.d("onChanged location!");
      mainViewModel.getWeatherData(locationModel.getLatitude(), locationModel.getLongitude());
    });

    mainViewModel.getErrorMsg().observe(this, msg -> {
      Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG).show();
    });
  }

  private void setView(WeatherDetailsModel weatherDetailsModel) {

    //Set lat lon.
    binding.tvLatLon.setText("Latitude : ".concat(String.valueOf(weatherDetailsModel.getCoord().getLat())).concat(" Longitude : ").concat(String.valueOf(weatherDetailsModel.getCoord().getLon())));

    //Location name.
    binding.tvLocation.setText(weatherDetailsModel.getName());

    //Forecast.
    binding.tvWeatherForecast.setText(weatherDetailsModel.getWeather().get(0).getMain());

    //Forecast description.
    binding.tvWeatherDescription.setText(weatherDetailsModel.getWeather().get(0).getDescription());

    //Temperature.
    binding.tvTemp.setText(String.valueOf(weatherDetailsModel.getMain().getTemp()).concat(getResources().getString(R.string.celcius)));
  }

  private void requestLocationPermission() {
    Timber.d("requestLocationPermission");
    if (permissionManager.isLocationPermissionGranted(this)) {
      mainViewModel.onLocationPermissionGranted();
    } else {
      permissionManager.requestLocationPermission(this);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    mainViewModel.onRequestPermissionRequest(requestCode, grantResults);
  }
}