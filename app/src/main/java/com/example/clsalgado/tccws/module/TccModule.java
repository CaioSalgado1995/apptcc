package com.example.clsalgado.tccws.module;

import com.example.clsalgado.tccws.service.LoginService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class TccModule {

    @Provides
    public LoginService getLoginService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.12:8080/wsjpa/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(LoginService.class);
    }
}
