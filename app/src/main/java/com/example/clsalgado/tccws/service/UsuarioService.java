package com.example.clsalgado.tccws.service;

import com.example.clsalgado.tccws.modelo.Usuario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UsuarioService {

    @POST("cadastrar")
    Call<ResponseBody> cadastrar(@Body Usuario usuario);

    @PUT("cadastrar")
    Call<ResponseBody> atualizar(@Body Usuario usuario);

    @DELETE("cadastrar")
    Call<ResponseBody> remover(@Body Usuario usuario);

}
