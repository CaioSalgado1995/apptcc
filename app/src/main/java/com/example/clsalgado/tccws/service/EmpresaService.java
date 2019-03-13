package com.example.clsalgado.tccws.service;

import com.example.clsalgado.tccws.modelo.Empresa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmpresaService {

    @GET("empresas")
    Call<List<Empresa>> listaEmpresas();
}
