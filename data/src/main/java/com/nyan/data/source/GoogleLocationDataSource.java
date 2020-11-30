package com.nyan.data.source;

import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.nyan.data.entity.LocationEntity;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import org.reactivestreams.Subscription;
import timber.log.Timber;

public class GoogleLocationDataSource {

  private static final String TAG = "GoogleLocationDataSourc";

  private static long LOCATION_REQUEST_INTERVAL = 10000L;
  private static long LOCATION_REQUEST_FASTEST_INTERVAL = 5000L;

  private final PublishSubject<LocationEntity> locationSubject;
  private final FusedLocationProviderClient fusedLocationClient;
  private  final LocationRequest locationRequest;

  private Context context;

  private LocationCallback locationCallback;

  private Flowable<LocationEntity> locationObservable;

  public GoogleLocationDataSource(Context context) {
    this.context = context;

    locationSubject = PublishSubject.create();
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    locationRequest = LocationRequest.create()
        .setInterval(LOCATION_REQUEST_INTERVAL)
        .setFastestInterval(LOCATION_REQUEST_FASTEST_INTERVAL)
        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    locationCallback = new LocationCallback() {
      @Override
      public void onLocationResult(LocationResult locationResult) {
        super.onLocationResult(locationResult);
        if (locationResult != null) {
          Timber.d("onLocationResult: %s", locationResult.getLocations().size());
          for (Location location: locationResult.getLocations()) {
            setLocation(location);
          }
        }
      }
    };

    locationObservable = locationSubject.toFlowable(BackpressureStrategy.MISSING)
        .doOnSubscribe(new Consumer<Subscription>() {
          @Override
          public void accept(Subscription subscription) throws Exception {
            Timber.d("accept");
            startLocationUpdate();
          }
        })
        .doOnCancel(new Action() {
          @Override
          public void run() throws Exception {
            Timber.d("doOnCancel");
            stopLocationUpdate();
          }
        });
  }

  private void startLocationUpdate() {
    Timber.d("startLocationUpdate");
    fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
      @Override
      public void onSuccess(Location location) {
        setLocation(location);
      }
    });
    fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback,
        Looper.getMainLooper());
  }

  public void stopLocationUpdate() {
    Timber.d("stopLocationUpdate");
    fusedLocationClient.removeLocationUpdates(locationCallback);
  }

  private void setLocation(Location location) {
    Timber.d("setLocation");
    if (location != null ){
      locationSubject.onNext(
          new LocationEntity(
              location.getLatitude(),
              location.getLongitude())
      );
    }
  }

  public Flowable<LocationEntity> getLocationObservable() {
    return locationObservable;
  }
}
