package com.nyan.weather.rx;

import io.reactivex.Scheduler;

public interface SchedulersProvider {

  Scheduler ui();

  Scheduler io();

  Scheduler computation();

}
