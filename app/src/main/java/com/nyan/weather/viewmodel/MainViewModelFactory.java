package com.nyan.weather.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.nyan.domain.usecases.GetWeatherDetailsUseCase;
import com.nyan.weather.rx.SchedulersProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {

  private final GetWeatherDetailsUseCase getWeatherDetailsUseCase;
  private final SchedulersProvider schedulersProvider;

  public MainViewModelFactory(GetWeatherDetailsUseCase getWeatherDetailsUseCase, SchedulersProvider schedulersProvider) {
    this.getWeatherDetailsUseCase = getWeatherDetailsUseCase;
    this.schedulersProvider = schedulersProvider;
  }

  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(MainViewModel.class)) {
      return (T) new MainViewModel(getWeatherDetailsUseCase, schedulersProvider);
    }
    throw new IllegalArgumentException("Unknown ViewModel class");
  }
}
