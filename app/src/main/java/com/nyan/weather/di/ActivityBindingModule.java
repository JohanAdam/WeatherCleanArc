package com.nyan.weather.di;

import com.nyan.weather.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = ViewModelModule.class)
abstract class ActivityBindingModule {

  @ContributesAndroidInjector
  abstract MainActivity bindMainScreenActivity();


}
