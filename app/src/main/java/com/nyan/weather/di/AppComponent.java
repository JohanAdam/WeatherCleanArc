package com.nyan.weather.di;

import com.nyan.data.di.ApiModule;
import com.nyan.data.di.NetworkModule;
import com.nyan.weather.App;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Singleton
@Component(
    modules = {
        NetworkModule.class,
        ApiModule.class,
        AppModule.class,
        AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class,
        ActivityBindingModule.class
    }
)
public interface AppComponent extends AndroidInjector<App> {

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(App application);

    AppComponent build();
  }

//  void inject(App app);

}