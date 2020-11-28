package com.nyan.domain.repositories;

import com.nyan.domain.models.WeatherDetailsModel;
import io.reactivex.Single;

public interface RemoteRepo {

  Single<WeatherDetailsModel> getCurrentWeather();

}
