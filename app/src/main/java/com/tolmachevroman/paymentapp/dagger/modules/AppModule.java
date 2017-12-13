package com.tolmachevroman.paymentapp.dagger.modules;

import android.app.Application;
import android.content.Context;

import com.tolmachevroman.paymentapp.utils.Utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by romantolmachev on 12/12/2017.
 */
@Module(includes = {ViewModelModule.class})
public class AppModule {

    private Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    Utils providesUtils() {
        return new Utils(app);
    }
}
