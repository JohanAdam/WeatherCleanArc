package com.nyan.domain.usecases;

import com.nyan.domain.models.WeatherDetailsModel;
import com.nyan.domain.repositories.RemoteRepo;
import io.reactivex.Single;

public class GetWeatherDetailsUseCase implements SingleUseCase<WeatherDetailsModel> {

  private RemoteRepo remoteRepo;

  public GetWeatherDetailsUseCase(RemoteRepo remoteRepo) {
    this.remoteRepo = remoteRepo;
  }


  @Override
  public Single<WeatherDetailsModel> execute(String lat, String lon) {
    return remoteRepo.getCurrentWeather(lat, lon);
  }

}

