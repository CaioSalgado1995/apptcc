package com.example.clsalgado.tccws;

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

    @BindView(R.id.campo_usuario)
    EditText editUsuario;

    @BindView(R.id.campo_senha)
    EditText editSenha;

    @BindView(R.id.botao_continuar)
    Button btnContinuar;

    @Inject
    LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // responsável por injeter as dependências com Dagger
        ((TccApplication) getApplication()).getComponent().inject(this);
        // responsável por fazer o bind das views com ButterKnife
        ButterKnife.bind(this);
    }

    @OnClick(R.id.botao_continuar)
    public void efetuarLogin() {
        Usuario u = new Usuario(
                editUsuario.getText().toString(),
                editSenha.getText().toString());

        executaChamadaWebService(u);
        //executaChamadaServidorBancoDeDados(u);
    }

    /**
     * Método que faz a chamada ao web service passando um usuário no formato JSON como parâmatro
     * @param usuario objeto que contêm matrícula e senha
     */
    private void executaChamadaWebService(Usuario usuario) {
        Call<ResponseBody> call = loginService.login(usuario);
        call.enqueue(new CallbackLogin());
    }

    /**
     * Método que aciona tarefa assíncrona para executar chamada ao banco de dados
     * @param usuario objeto usuário
     */
    private void executaChamadaServidorBancoDeDados(Usuario usuario) {
        new LoginTask(this).execute(usuario);
    }

    /**
     * Método que trata retorno da chamada ao servidor de banco de dados
     * @param usuario objeto usuário
     */
    public void trataRetornoChamadaServidorBancoDeDados(Usuario usuario) {
        Log.i("LoginServiceAcessoBanco", usuario.getMatricula());
        Log.i("LoginServiceAcessoBanco", usuario.getSenha());
    }
}