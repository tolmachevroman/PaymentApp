package com.tolmachevroman.paymentapp.dagger.modules;

import dagger.Module;

/**
 * Created by romantolmachev on 12/12/2017.
 */

@Module
public class NetModule {

    private String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

}
