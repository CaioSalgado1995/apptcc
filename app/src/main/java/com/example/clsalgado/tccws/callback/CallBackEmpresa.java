package com.example.clsalgado.tccws.callback;

import android.util.Log;

import com.example.clsalgado.tccws.modelo.Empresa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallBackEmpresa implements Callback<List<Empresa>> {

    public CallBackEmpresa() {}

    @Override
    public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {
        Log.d("EmpresaService", String.valueOf(response.body().size()));
    }

    @Override
    public void onFailure(Call<List<Empresa>> call, Throwable t) {
        Log.d("EmpresaService", t.getMessage());
    }
}