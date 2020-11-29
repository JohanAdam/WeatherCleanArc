package com.nyan.weather;

import android.content.Context;
import com.nyan.weather.di.AppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {

  private AppComponent appComponent;

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
  }

  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    appComponent = DaggerAppComponent.builder()
        .application(this)
        .build();

    return appComponent;
  }
}
