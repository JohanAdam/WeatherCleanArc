package com.nyan.weather.di;

import com.nyan.weather.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

//@Module(includes = ViewModelModule.class)
@Module
public abstract class ActivityBindingModule {

  @ContributesAndroidInjector(modules = ViewModelModule.class)
  abstract MainActivity bindMainActivity();


}
