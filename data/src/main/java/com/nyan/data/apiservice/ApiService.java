package com.nyan.data.apiservice;

import com.nyan.data.models.WeatherDataModel;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

  @GET("/data/2.5/weather")
  Single<WeatherDataModel> getWeatherDetails(
      @Query("lat") String lat,
      @Query("lon") String lon,
      @Query("appid") String appId,
      @Query("units") String units
  );

}
