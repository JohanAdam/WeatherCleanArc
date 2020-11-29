package com.nyan.domain.usecases;

import com.nyan.domain.models.WeatherDetailsModel;
import com.nyan.domain.repositories.RemoteRepo;
import io.reactivex.Single;
import javax.inject.Inject;

public class GetWeatherDetailsUseCase implements SingleUseCase<WeatherDetailsModel> {

  private RemoteRepo remoteRepo;

  @Inject
  GetWeatherDetailsUseCase(RemoteRepo remoteRepo) {
    this.remoteRepo = remoteRepo;
  }


  @Override
  public Single<WeatherDetailsModel> execute() {
    return remoteRepo.getCurrentWeather();
  }

}

