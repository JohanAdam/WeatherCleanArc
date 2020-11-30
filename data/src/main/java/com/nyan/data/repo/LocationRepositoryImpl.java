package com.nyan.data.repo;


import com.nyan.data.entity.LocationEntity;
import com.nyan.data.source.GoogleLocationDataSource;
import com.nyan.domain.models.LocationDomainModel;
import com.nyan.domain.repositories.LocationRepository;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class LocationRepositoryImpl implements LocationRepository {

  private GoogleLocationDataSource googleLocationDataSource;

  public LocationRepositoryImpl(GoogleLocationDataSource googleLocationDataSource) {
    this.googleLocationDataSource = googleLocationDataSource;
  }

  @Override
  public Flowable<LocationDomainModel> getLocation() {
    return googleLocationDataSource
        .getLocationObservable()
        .map(new Function<LocationEntity, LocationDomainModel>() {
          @Override
          public LocationDomainModel apply(LocationEntity locationEntity) throws Exception {
            return locationEntity.mapToDomain();
          }
        });
  }
}
