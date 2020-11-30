package com.nyan.weather.di;

import com.nyan.domain.repositories.RemoteRepo;
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
  MainViewModelFactory provideMainViewModelFactory(GetWeatherDetailsUseCase getWeatherDetailsUseCase,
      SchedulersProvider schedulersProvider) {
    return new MainViewModelFactory(getWeatherDetailsUseCase, schedulersProvider);
  }

}
