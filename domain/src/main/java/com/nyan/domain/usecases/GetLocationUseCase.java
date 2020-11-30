package com.nyan.domain.usecases;

import com.nyan.domain.models.LocationDomainModel;
import com.nyan.domain.repositories.LocationRepository;
import io.reactivex.Flowable;

public class GetLocationUseCase implements FlowableUseCase<LocationDomainModel>{

  private LocationRepository locationRepository;

  public GetLocationUseCase(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  @Override
  public Flowable<LocationDomainModel> build() {
    return locationRepository.getLocation();
  }
}
