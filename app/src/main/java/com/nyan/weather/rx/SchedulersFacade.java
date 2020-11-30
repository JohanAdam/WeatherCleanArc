package com.nyan.weather.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulersFacade implements SchedulersProvider {

  public SchedulersFacade(){
  }

  @Override
  public Scheduler ui() {
    return AndroidSchedulers.mainThread();
  }

  @Override
  public Scheduler io() {
    return Schedulers.io();
  }

  @Override
  public Scheduler computation() {
    return Schedulers.computation();
  }
}
