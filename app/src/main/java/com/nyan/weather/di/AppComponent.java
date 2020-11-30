package com.nyan.weather.di;

import com.nyan.data.di.ApiModule;
import com.nyan.data.di.NetworkModule;
import com.nyan.weather.App;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

@Singleton
@Component(
    modules = {
        NetworkModule.class,
        ApiModule.class,
        AppModule.class,
        AndroidSupportInjectionModule.class,
        BuilderModule.class
    }
)
public interface AppComponent{

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(App application);

    AppComponent build();
  }

  void inject(App app);

}
