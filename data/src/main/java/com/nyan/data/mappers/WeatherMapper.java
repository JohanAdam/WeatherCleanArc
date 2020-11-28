package com.nyan.data.mappers;

import com.nyan.data.models.WeatherDataModel;
import com.nyan.domain.models.Clouds;
import com.nyan.domain.models.Coord;
import com.nyan.domain.models.Main;
import com.nyan.domain.models.Sys;
import com.nyan.domain.models.Weather;
import com.nyan.domain.models.WeatherDetailsModel;
import com.nyan.domain.models.Wind;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Mapper to map the NetworkModel(DataModel) to DomainModel.
 */
public class WeatherMapper {

  @Inject
  WeatherMapper(){

  }

  public WeatherDetailsModel toWeatherDetails(WeatherDataModel networkModel) {

    Clouds domainCloud = new Clouds();
    domainCloud.setAll(networkModel.getClouds().getAll());

    Coord domainCoord = new Coord();
    domainCoord.setLat(networkModel.getCoord().getLat());
    domainCoord.setLon(networkModel.getCoord().getLon());

    Main domainMain = new Main();
    domainMain.setFeelsLike(networkModel.getMain().getFeelsLike());
    domainMain.setHumidity(networkModel.getMain().getHumidity());
    domainMain.setPressure(networkModel.getMain().getPressure());
    domainMain.setTemp(networkModel.getMain().getTemp());
    domainMain.setTempMax(networkModel.getMain().getTempMax());
    domainMain.setTempMin(networkModel.getMain().getTempMin());

    Sys domainSys = new Sys();
    domainSys.setCountry(networkModel.getSys().getCountry());
    domainSys.setId(networkModel.getSys().getId());
    domainSys.setSunrise(networkModel.getSys().getSunrise());
    domainSys.setSunset(networkModel.getSys().getSunset());
    domainSys.setType(networkModel.getSys().getType());

    List<Weather> domainWeatherList = new ArrayList<>();
    for (com.nyan.data.models.Weather item : networkModel.getWeather()) {
      Weather weather = new Weather();
      weather.setDescription(item.getDescription());
      weather.setIcon(item.getIcon());
      weather.setId(item.getId());
      weather.setMain(item.getMain());

      domainWeatherList.add(weather);
    }

    Wind domainWind = new Wind();
    domainWind.setDeg(networkModel.getWind().getDeg());
    domainWind.setSpeed(networkModel.getWind().getSpeed());

   return new WeatherDetailsModel(
       networkModel.getBase(),
       domainCloud,
       networkModel.getCod(),
       domainCoord,
       networkModel.getDt(),
       networkModel.getId(),
       domainMain,
       networkModel.getName(),
       domainSys,
       networkModel.getTimezone(),
       networkModel.getVisibility(),
       domainWeatherList,
       domainWind
   );
  }

//  public interface Mapper<From, To> {
//    To map(From value);
//  }

}
