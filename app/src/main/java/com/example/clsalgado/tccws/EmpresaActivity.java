package com.example.clsalgado.tccws;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.clsalgado.tccws.app.TccApplication;
import com.example.clsalgado.tccws.callback.CallBackEmpresa;
import com.example.clsalgado.tccws.modelo.Empresa;
import com.example.clsalgado.tccws.service.EmpresaService;
import com.example.clsalgado.tccws.tarefa.EmpresaTask;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class EmpresaActivity extends AppCompatActivity {


    @Inject
    EmpresaService empresaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);

        ((TccApplication) getApplication()).getComponent().inject(this);

        listaEmpresas();

    }

    /**
     * Método de teste para verificar chamada para listagem de empresas
     */
    private void listaEmpresas() {
        Call<List<Empresa>> call = empresaService.listaEmpresas();
        call.enqueue(new CallBackEmpresa());
    }

    private void listaEmpresasJDBC() {
        new EmpresaTask(this).execute();
    }

    public void trataRetornoChamadaServidorBancoDeDados(List<Empresa> listaEmpresas) {
        Log.d("EmpresaActivity", "Tamanho da lista de empresas == " + listaEmpresas.size());
    }
}