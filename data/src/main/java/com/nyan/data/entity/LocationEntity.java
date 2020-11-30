package com.nyan.data.entity;

import com.nyan.domain.models.LocationDomainModel;

public class LocationEntity {

  private double latitude;
  private double longitude;

  public LocationEntity(double latitude, double longitude) {
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

  public LocationDomainModel mapToDomain() {
    return new LocationDomainModel(latitude, longitude);
  }

}
