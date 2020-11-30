package com.nyan.weather.model;

import com.nyan.domain.models.LocationDomainModel;

public class LocationModel {

  private double latitude;
  private double longitude;

  public LocationModel() {

  }

  public LocationModel(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public LocationModel mapToPresentation(LocationDomainModel locationDomainModel) {
    latitude = locationDomainModel.getLatitude();
    longitude = locationDomainModel.getLongitude();
    return new LocationModel(latitude, longitude);
  }

}
