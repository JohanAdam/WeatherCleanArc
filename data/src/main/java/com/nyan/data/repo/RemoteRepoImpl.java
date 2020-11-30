package com.nyan.data.repo;

import com.nyan.data.Constants;
import com.nyan.data.apiservice.ApiService;
import com.nyan.data.mappers.WeatherMapper;
import com.nyan.data.models.WeatherDataModel;
import com.nyan.domain.models.WeatherDetailsModel;
import com.nyan.domain.repositories.RemoteRepo;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class RemoteRepoImpl implements RemoteRepo {

  private ApiService apiService;
  private WeatherMapper weatherMapper;

  public RemoteRepoImpl(ApiService apiService, WeatherMapper weatherMapper){
     this.apiService = apiService;
     this.weatherMapper = weatherMapper;
  }

  @Override
  public Single<WeatherDetailsModel> getCurrentWeather(String lat, String lon) {
    return apiService.getWeatherDetails(lat, lon, Constants.API_KEY, Constants.DEFAULT_METRIC)
        .map(new Function<WeatherDataModel, WeatherDetailsModel>() {
          @Override
          public WeatherDetailsModel apply(WeatherDataModel weatherDataModel) {
            return weatherMapper.toWeatherDetails(weatherDataModel);
          }
        });
  }
}
