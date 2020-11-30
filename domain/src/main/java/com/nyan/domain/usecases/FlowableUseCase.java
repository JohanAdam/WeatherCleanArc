package com.nyan.domain.usecases;

import io.reactivex.Flowable;

interface FlowableUseCase<R> {

  Flowable<R> build();

}
