package com.nyan.data.di;

import com.nyan.data.apiservice.ApiService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public
class ApiModule {

  @Provides
  ApiService bindApiService(Retrofit retrofit) {
    return retrofit.create(ApiService.class);
  }

}
