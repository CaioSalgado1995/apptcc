package com.example.clsalgado.tccws.module;

import com.example.clsalgado.tccws.service.LoginService;
import com.example.clsalgado.tccws.service.UsuarioService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class TccModule {

    @Provides
    public LoginService getLoginService() {
        Retrofit retrofit = configuracaoRetrofit();
        return retrofit.create(LoginService.class);
    }

    @Provides
    public UsuarioService getUsuarioService() {
        Retrofit retrofit = configuracaoRetrofit();
        return retrofit.create(UsuarioService.class);
    }

    private Retrofit configuracaoRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.12:8080/wsjpa/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
