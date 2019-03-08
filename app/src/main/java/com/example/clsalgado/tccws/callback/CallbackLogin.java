package com.example.clsalgado.tccws.callback;

import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Classe que recebe o callback do servidor na chamada ao webservice de login
 */
public class CallbackLogin implements Callback<ResponseBody> {

    public CallbackLogin() {}

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Log.i("CallbackWebServiceLogin", "Sucesso");
        Log.i("CallbackWebServiceLogin", response.message());
        Log.i("CallbackWebServiceLogin", String.valueOf(response.code()));
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.i("CallbackWebServiceLogin", "Erro");
        Log.e("CallbackWebServiceLogin", t.getLocalizedMessage());
    }
}
