package com.nyan.weather.ui;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.nyan.domain.models.WeatherDetailsModel;
import com.nyan.weather.R;
import com.nyan.weather.databinding.ActivityMainBinding;
import com.nyan.weather.utils.LocationChecker;
import com.nyan.weather.utils.LocationChecker.LocationCheckerCallback;
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

  @Inject
  LocationChecker locationChecker;

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
      showSnackbar(msg, rootView, Snackbar.LENGTH_LONG);
    });

    requestLocationPermission();
  }

  private void setView(WeatherDetailsModel weatherDetailsModel) {

    String uri_base_icon = "http://openweathermap.org/img/wn/---@4x.png";
    uri_base_icon = uri_base_icon.replace("---", weatherDetailsModel.getWeather().get(0).getIcon());
    Timber.d("icon link %s", uri_base_icon);

    Glide.with(this)
        .load(uri_base_icon)
        .fitCenter()
        .placeholder(R.drawable.ic_refresh_light)
        .into(binding.ivIconForecast);

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

    locationChecker.checkLocationEnable(new LocationCheckerCallback() {
      @Override
      public void onSuccess() {
        if (permissionManager.isLocationPermissionGranted(MainActivity.this)) {
          mainViewModel.onLocationPermissionGranted();
        } else {
          permissionManager.requestLocationPermission(MainActivity.this);
        }
      }

      @Override
      public void onError(String msg) {
        showSnackbar(msg, binding.getRoot(), Snackbar.LENGTH_INDEFINITE);
      }
    });
  }

  private void showSnackbar(String msg, View root, int length) {
    Snackbar.make(root, msg, length).show();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    mainViewModel.onRequestPermissionRequest(requestCode, grantResults);
  }
}