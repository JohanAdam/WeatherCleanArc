package com.nyan.data.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

  public NetworkModule() {

  }

  @Provides
  public OkHttpClient provideOkHttpClient() {

    //Setup logging.
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.level(Level.BODY);
//    if (BuildConfig.DEBUG) {
//      Logging for debug mode.
//      logging.level(Level.BODY);
//    } else {
      //Logging for production mode.
//      logging.level(Level.BASIC);
//    }

    //Setup client.
   return new OkHttpClient.Builder()
        .addInterceptor(logging)
        .followRedirects(true)
        .followSslRedirects(true)
        .cache(null)
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES).build();
  }

  @Singleton
  @Provides
  Gson provideGson() {
    return new GsonBuilder()
        .setLenient()
        .create();
  }

  @Provides
  public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .baseUrl("http://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build();
  }



}
