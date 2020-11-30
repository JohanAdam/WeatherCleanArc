package com.nyan.weather.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.nyan.domain.models.WeatherDetailsModel;
import com.nyan.domain.usecases.GetLocationUseCase;
import com.nyan.domain.usecases.GetWeatherDetailsUseCase;
import com.nyan.weather.model.LocationModel;
import com.nyan.weather.rx.SchedulersProvider;
import com.nyan.weather.utils.PermissionManager;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;


public class MainViewModel extends ViewModel {

  private GetWeatherDetailsUseCase weatherUseCase;
  private GetLocationUseCase locationUseCase;
  private SchedulersProvider schedulersProvider;

  private MutableLiveData<WeatherDetailsModel> _weatherDetailsLiveData = new MutableLiveData<>();
  private MutableLiveData<LocationModel> _locationModel = new MutableLiveData<>();
  private MutableLiveData<String> _errorMsg = new MutableLiveData<>();

  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  public MainViewModel(GetWeatherDetailsUseCase getWeatherDetailsUseCase,
      GetLocationUseCase getLocationUseCase,
      SchedulersProvider schedulersProvider) {
    this.weatherUseCase = getWeatherDetailsUseCase;
    this.locationUseCase = getLocationUseCase;
    this.schedulersProvider = schedulersProvider;
  }

  public void getWeatherData(double latitude, double longitude) {
    weatherUseCase.execute(String.valueOf(latitude), String.valueOf(longitude))
        .subscribeOn(schedulersProvider.io())
        .subscribe(new SingleObserver<WeatherDetailsModel>() {
          @Override
          public void onSubscribe(Disposable d) {
            compositeDisposable.add(d);
          }

          @Override
          public void onSuccess(WeatherDetailsModel weatherDetailsModel) {
            _weatherDetailsLiveData.postValue(weatherDetailsModel);
          }

          @Override
          public void onError(Throwable e) {
            e.printStackTrace();
            _errorMsg.postValue(e.getMessage());
          }
        });
  }

  public MutableLiveData<WeatherDetailsModel> getWeatherLiveData() {
    return _weatherDetailsLiveData;
  }

  public MutableLiveData<LocationModel> getLocationLiveData() {
    return _locationModel;
  }

  public MutableLiveData<String> getErrorMsg() {
    return _errorMsg;
  }

  public void onRequestPermissionRequest(int requestCode, int[] grantResults) {
    Timber.d("onRequestPermissionRequest ");
    if (requestCode == PermissionManager.LOCATION_PERMISSION_REQUEST_CODE) {
      if (grantResults.length != 0) {
        switch (grantResults[0]) {
          case PermissionManager.PERMISSION_GRANTED:
            onLocationPermissionGranted();
            break;
          case PermissionManager.PERMISSION_DENIED:
            onLocationPermissionDenied();
            break;
        }
      }
    }
  }

  public void onLocationPermissionGranted() {
    Timber.d("onLocationPermissionGranted");
    Disposable disposable = locationUseCase
        .build()
        .subscribeOn(schedulersProvider.io())
        .map(locationDomainModel ->
            new LocationModel(locationDomainModel.getLatitude(), locationDomainModel.getLongitude()))
        .doOnError(this::getLocationError)
        .subscribe(this::getLocationSuccess);
    compositeDisposable.add(disposable);
  }

  private void onLocationPermissionDenied() {
    Timber.e("onLocationPermissionDenied");
    _errorMsg.postValue("Please check enable location permission.");
  }

  private void getLocationSuccess(LocationModel locationModel) {
    Timber.e("getLocationSuccess");
    _locationModel.postValue(locationModel);
  }

  private void getLocationError(Throwable throwable) {
    Timber.e("getLocationError");
    throwable.printStackTrace();
    _errorMsg.postValue(throwable.getMessage());
  }

  @Override
  protected void onCleared() {
    super.onCleared();
    compositeDisposable.clear();
  }
}
