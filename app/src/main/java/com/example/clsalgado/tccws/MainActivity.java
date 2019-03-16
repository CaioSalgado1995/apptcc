package com.example.clsalgado.tccws;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.clsalgado.tccws.app.TccApplication;
import com.example.clsalgado.tccws.callback.CallbackLogin;
import com.example.clsalgado.tccws.modelo.Usuario;
import com.example.clsalgado.tccws.service.LoginService;
import com.example.clsalgado.tccws.tarefa.LoginTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @Inject
    LoginService loginService;

    private int contadorIteracoes;

    private static int MAX_CHAMADAS = 500;

    private Usuario usuario;

    // receiver para verificar mudança na bateria
    private BroadcastReceiver bateriaReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int bateria = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int maximo = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);

            Log.d("MainActivity", "Nível de bateria == " + bateria);
            Log.d("MainActivity", "Nível máximo de bateria == " + maximo);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // responsável por injeter as dependências com Dagger
        ((TccApplication) getApplication()).getComponent().inject(this);
        this.registerReceiver(this.bateriaReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        // inicialize iteracoes
        contadorIteracoes = 1;

        // configurando usuário fixo
        usuario = new Usuario("1505602", "789456123");

        // transferindo chamada ao webservice ou banco de dados direto para o onCreate
        executaChamadaWebService(usuario);
        //executaChamadaServidorBancoDeDados(usuario);

    }

    /**
     * Método que faz a chamada ao web service passando um usuário no formato JSON como parâmatro
     * @param usuario objeto que contêm matrícula e senha
     */
    private void executaChamadaWebService(Usuario usuario) {
        Log.d("MainActivity","Contador de chamadas == " + contadorIteracoes);
        Call<ResponseBody> call = loginService.login(usuario);
        call.enqueue(new CallbackLogin(this));
    }

    public void trataRetornoChamadaWebService() {
        if(contadorIteracoes <= MAX_CHAMADAS) {
            contadorIteracoes++;
            Log.d("MainActivity","Contador de chamadas == " + contadorIteracoes);
            Call<ResponseBody> call = loginService.login(usuario);
            call.enqueue(new CallbackLogin(this));
        }
    }

    /**
     * Método que aciona tarefa assíncrona para executar chamada ao banco de dados
     * @param usuario objeto usuário
     */
    private void executaChamadaServidorBancoDeDados(Usuario usuario) {
        Log.d("MainActivity", "Contador de chamadas banco == " + contadorIteracoes);
        new LoginTask(this).execute(usuario);
    }

    /**
     * Método que trata retorno da chamada ao servidor de banco de dados
     * @param usuario objeto usuário
     */
    public void trataRetornoChamadaServidorBancoDeDados(Usuario usuario) {
        if(contadorIteracoes <= MAX_CHAMADAS) {
            contadorIteracoes++;
            Log.d("MainActivity", "Contador de chamadas banco == " + contadorIteracoes);
            new LoginTask(this).execute(usuario);
        }

    }
}