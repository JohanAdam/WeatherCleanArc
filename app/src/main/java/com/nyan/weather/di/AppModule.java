package com.nyan.weather.di;

import android.content.Context;
import com.nyan.data.apiservice.ApiService;
import com.nyan.data.mappers.WeatherMapper;
import com.nyan.data.repo.LocationRepositoryImpl;
import com.nyan.data.repo.RemoteRepoImpl;
import com.nyan.data.source.GoogleLocationDataSource;
import com.nyan.domain.repositories.LocationRepository;
import com.nyan.domain.repositories.RemoteRepo;
import com.nyan.weather.App;
import com.nyan.weather.rx.SchedulersFacade;
import com.nyan.weather.rx.SchedulersProvider;
import com.nyan.weather.utils.PermissionManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {

  @Provides
  Context bindContext(App application) {
    return application.getApplicationContext();
  }

  @Provides
  SchedulersProvider providerScheduler() {
    return new SchedulersFacade();
  }

  @Provides
  PermissionManager providerPermissionManager() {
    return new PermissionManager();
  }

  @Provides
  GoogleLocationDataSource providerGoogleLocationDataSource(Context context) {
    return new GoogleLocationDataSource(context);
  }

  @Singleton
  @Provides
  WeatherMapper provideWeatherMapper() {
    return new WeatherMapper();
  }

  @Singleton
  @Provides
  RemoteRepo provideRemoteRepo(ApiService apiService, WeatherMapper weatherMapper) {
    return new RemoteRepoImpl(apiService, weatherMapper);
  }

  @Singleton
  @Provides
  LocationRepository provideLocationRepo(GoogleLocationDataSource googleLocationDataSource){
    return new LocationRepositoryImpl(googleLocationDataSource);
  }

}
