package com.example.clsalgado.tccws;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

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

    @BindView(R.id.botao_100)
    Button btn100Ws;

    @BindView(R.id.botao_500)
    Button btn500Ws;

    @BindView(R.id.botao_1000)
    Button btn1000Ws;

    @BindView(R.id.botao_100_db)
    Button btn100Db;

    @BindView(R.id.botao_500_db)
    Button btn500Db;

    @BindView(R.id.botao_1000_db)
    Button btn1000Db;

    private int contadorIteracoes = 1;

    private int maximoChamadas = 100;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // responsável por injeter as dependências com Dagger
        ((TccApplication) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        // configurando usuário fixo
        usuario = new Usuario("1505602", "789456123");

    }

    @OnClick(R.id.botao_100)
    public void efetuarChamadaWs100Requisicoes() {
        contadorIteracoes = 1;
        maximoChamadas = 100;
        executaChamadaWebService(usuario);
    }

    @OnClick(R.id.botao_500)
    public void efetuaChamadaWs500Requisicoes() {
        contadorIteracoes = 1;
        maximoChamadas = 500;
        executaChamadaWebService(usuario);
    }

    @OnClick(R.id.botao_1000)
    public void efetuaChamadaWs1000Requisicoes(){
        contadorIteracoes = 1;
        maximoChamadas = 1000;
        executaChamadaWebService(usuario);
    }

    @OnClick(R.id.botao_100_db)
    public void efetuarChamadaDb100Requisicoes() {
        contadorIteracoes = 1;
        maximoChamadas = 100;
        executaChamadaServidorBancoDeDados(usuario);
    }

    @OnClick(R.id.botao_500_db)
    public void efetuaChamadaDb500Requisicoes() {
        contadorIteracoes = 1;
        maximoChamadas = 500;
        executaChamadaServidorBancoDeDados(usuario);
    }

    @OnClick(R.id.botao_1000_db)
    public void efetuaChamadaDb1000Requisicoes(){
        contadorIteracoes = 1;
        maximoChamadas = 1000;
        executaChamadaServidorBancoDeDados(usuario);
    }

    /**
     * Método que faz a chamada ao web service passando um usuário no formato JSON como parâmatro
     * @param usuario objeto que contêm matrícula e senha
     */
    private void executaChamadaWebService(Usuario usuario) {
        Toast.makeText(this, "Iniciando chamadas webservice", Toast.LENGTH_SHORT).show();
        String tagLog = "WEBSERVICE" + maximoChamadas;
        Log.i(tagLog, "Iniciando chamadas webservice " + contadorIteracoes + " DE " + maximoChamadas);
        Call<ResponseBody> call = loginService.login(usuario);
        call.enqueue(new CallbackLogin(this));
    }

    public void trataRetornoChamadaWebService() {
        if(contadorIteracoes <= maximoChamadas) {
            contadorIteracoes++;
            String tagLog = "WEBSERVICE" + maximoChamadas;
            Log.i(tagLog, "Continuação chamadas webservice " + contadorIteracoes + " DE " + maximoChamadas);
            Call<ResponseBody> call = loginService.login(usuario);
            call.enqueue(new CallbackLogin(this));
        }else {
            Toast.makeText(this, "Finalizando chamadas webservice", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método que aciona tarefa assíncrona para executar chamada ao banco de dados
     * @param usuario objeto usuário
     */
    private void executaChamadaServidorBancoDeDados(Usuario usuario) {
        Toast.makeText(this, "Iniciando chamadas direta a base de dados", Toast.LENGTH_SHORT).show();
        String tagLog = "DIRETODATABASE" + maximoChamadas;
        Log.i(tagLog, "Iniciando chamadas direta ao banco de dados " + contadorIteracoes + " DE " + maximoChamadas);
        new LoginTask(this).execute(usuario);
    }

    /**
     * Método que trata retorno da chamada ao servidor de banco de dados
     * @param usuario objeto usuário
     */
    public void trataRetornoChamadaServidorBancoDeDados(Usuario usuario) {
        if(contadorIteracoes <= maximoChamadas) {
            contadorIteracoes++;
            String tagLog = "DIRETODATABASE" + maximoChamadas;
            Log.i(tagLog, "Continuação chamadas direta ao banco de dados " + contadorIteracoes + " DE " + maximoChamadas);
            new LoginTask(this).execute(usuario);
        }else {
            Toast.makeText(this, "Finalizando chamadas direta a base de dados", Toast.LENGTH_SHORT).show();
        }
    }
}