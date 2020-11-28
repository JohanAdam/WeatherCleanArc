package com.nyan.data.repo;

import com.nyan.data.apiservice.ApiService;
import com.nyan.data.mappers.WeatherMapper;
import com.nyan.data.models.WeatherDataModel;
import com.nyan.domain.models.WeatherDetailsModel;
import com.nyan.domain.repositories.RemoteRepo;
import dagger.Lazy;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import javax.inject.Inject;

class RemoteRepoImpl implements RemoteRepo {

  private ApiService apiService;
  private Lazy<WeatherMapper> weatherMapper;

  @Inject
  RemoteRepoImpl(ApiService apiService, Lazy<WeatherMapper> weatherMapper){
     this.apiService = apiService;
     this.weatherMapper = weatherMapper;
  }

  @Override
  public Single<WeatherDetailsModel> getCurrentWeather() {
    return apiService.getWeatherDetails("2.946570", "101.612660", "5f402abdaf70ed7af20e1681f275afd5", "metric")
        .map(new Function<WeatherDataModel, WeatherDetailsModel>() {
          @Override
          public WeatherDetailsModel apply(WeatherDataModel weatherDataModel) {
            return weatherMapper.get().toWeatherDetails(weatherDataModel);
          }
        });
  }
}
