package com.nyan.weather.di;

import android.app.Application;
import android.content.Context;
import com.nyan.weather.rx.SchedulersFacade;
import com.nyan.weather.rx.SchedulersProvider;
import dagger.Binds;
import dagger.Module;

@Module
abstract class AppModule {

  @Binds
  abstract Context bindContext(Application application);

  @Binds
  abstract SchedulersProvider providerScheduler(SchedulersFacade schedulersFacade);

}
