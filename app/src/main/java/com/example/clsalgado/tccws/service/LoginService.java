package com.example.clsalgado.tccws.service;

import com.example.clsalgado.tccws.modelo.Usuario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login")
    Call<ResponseBody> login(@Body Usuario usuario);
}
