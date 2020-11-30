package com.nyan.domain.repositories;

import com.nyan.domain.models.LocationDomainModel;
import io.reactivex.Flowable;

public interface LocationRepository {

  Flowable<LocationDomainModel> getLocation();

}
