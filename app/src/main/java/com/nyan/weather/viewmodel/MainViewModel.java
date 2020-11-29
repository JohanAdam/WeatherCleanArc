package com.nyan.weather.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.nyan.domain.models.WeatherDetailsModel;
import com.nyan.domain.usecases.GetWeatherDetailsUseCase;
import com.nyan.weather.rx.SchedulersProvider;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;


public class MainViewModel extends ViewModel {

  private GetWeatherDetailsUseCase weatherUseCase;
  private SchedulersProvider schedulersProvider;


  private MutableLiveData<WeatherDetailsModel> weatherDetailsLiveData = new MutableLiveData<>();
  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  @Inject
  MainViewModel(GetWeatherDetailsUseCase getWeatherDetailsUseCase, SchedulersProvider schedulersProvider) {
    this.weatherUseCase = getWeatherDetailsUseCase;
    this.schedulersProvider = schedulersProvider;
  }

  public void getWeatherData() {
    weatherUseCase.execute()
        .subscribeOn(schedulersProvider.io())
        .subscribe(new SingleObserver<WeatherDetailsModel>() {
          @Override
          public void onSubscribe(Disposable d) {
            compositeDisposable.add(d);
          }

          @Override
          public void onSuccess(WeatherDetailsModel weatherDetailsModel) {
            weatherDetailsLiveData.postValue(weatherDetailsModel);
          }

          @Override
          public void onError(Throwable e) {
            e.printStackTrace();
          }
        });
  }

  public MutableLiveData<WeatherDetailsModel> getWeatherLiveData() {
    return weatherDetailsLiveData;
  }

  @Override
  protected void onCleared() {
    super.onCleared();
    compositeDisposable.clear();
  }
}
