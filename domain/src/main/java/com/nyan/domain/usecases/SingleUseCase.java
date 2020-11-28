package com.nyan.domain.usecases;

import io.reactivex.Single;

interface SingleUseCase<R> {

  Single<R> execute();

}
