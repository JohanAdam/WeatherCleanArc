package com.nyan.weather.di;

import com.nyan.domain.repositories.LocationRepository;
import com.nyan.domain.repositories.RemoteRepo;
import com.nyan.domain.usecases.GetLocationUseCase;
import com.nyan.domain.usecases.GetWeatherDetailsUseCase;
import com.nyan.weather.rx.SchedulersProvider;
import com.nyan.weather.viewmodel.MainViewModelFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Define Main Activity - specific dependencies here.
 */

@Module
public class MainActivityModule {

  @Provides
  GetWeatherDetailsUseCase provideGetWeatherDetailsUserCase(RemoteRepo remoteRepo){
    return new GetWeatherDetailsUseCase(remoteRepo);
  }

  @Provides
  GetLocationUseCase provideGetLocationUseCase(LocationRepository locationRepository) {
    return new GetLocationUseCase(locationRepository);
  }

  @Provides
  MainViewModelFactory provideMainViewModelFactory(GetWeatherDetailsUseCase getWeatherDetailsUseCase,
      GetLocationUseCase getLocationUseCase,
      SchedulersProvider schedulersProvider) {
    return new MainViewModelFactory(getWeatherDetailsUseCase, getLocationUseCase, schedulersProvider);
  }

}
