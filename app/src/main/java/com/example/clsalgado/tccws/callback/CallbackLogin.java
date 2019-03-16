package com.example.clsalgado.tccws.callback;

import android.util.Log;

import com.example.clsalgado.tccws.MainActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Classe que recebe o callback do servidor na chamada ao webservice de login
 */
public class CallbackLogin implements Callback<ResponseBody> {

    private MainActivity activity;

    public CallbackLogin(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Log.i("CallbackWebServiceLogin", "Sucesso");
        activity.trataRetornoChamadaWebService();
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.i("CallbackWebServiceLogin", "Erro");
        Log.e("CallbackWebServiceLogin", t.getLocalizedMessage());
    }
}
