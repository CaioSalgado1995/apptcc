package com.example.clsalgado.tccws.callback;

import android.util.Log;

import com.example.clsalgado.tccws.MainActivity;
import com.example.clsalgado.tccws.UsuarioActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallbackUsuario implements Callback<ResponseBody> {
    private UsuarioActivity activity;

    public CallbackUsuario(UsuarioActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Log.i("WEBSERVICE", "Sucesso");
        activity.trataRetornoChamadaWebService();
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.e("WEBSERVICE-ERRO", t.getLocalizedMessage());
    }
}
