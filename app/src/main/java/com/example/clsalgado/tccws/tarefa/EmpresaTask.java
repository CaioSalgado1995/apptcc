package com.example.clsalgado.tccws.tarefa;

import android.os.AsyncTask;
import android.util.Log;

import com.example.clsalgado.tccws.EmpresaActivity;
import com.example.clsalgado.tccws.modelo.Empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaTask extends AsyncTask<Void, Integer, List<Empresa>> {

    private EmpresaActivity empresaActivity;

    public EmpresaTask(EmpresaActivity empresaActivity) {
        this.empresaActivity = empresaActivity;
    }

    @Override
    protected List<Empresa> doInBackground(Void... voids) {

        List<Empresa> listaEmpresas = new ArrayList<>();

        try {

            Connection connection =
                    DriverManager.getConnection("jdbc:mariadb://192.168.0.12:3306/utfpr_tcc_wsjpa", "root", "root");

            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from empresa");

            boolean sucesso = preparedStatement.execute();

            if (sucesso) {
                ResultSet resultSet = preparedStatement.getResultSet();

                while (resultSet.next()) {
                    String cnpj = resultSet.getString("cnpj");
                    String nome = resultSet.getString("nome");
                    boolean isRepresentanteLegal = resultSet.getBoolean("representanteLegal");
                    Log.i("EmpresaTaskAcessoBanco", cnpj);
                    Log.i("EmpresaTaskAcessoBanco", nome);

                    Empresa empresa = new Empresa(cnpj, nome, isRepresentanteLegal);
                    listaEmpresas.add(empresa);
                }
                resultSet.close();
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            Log.e("EmpresaTaskAcessoBanco", e.getMessage());
        }
        return listaEmpresas;
    }

    @Override
    protected void onPostExecute(List<Empresa> empresas) {
        empresaActivity.trataRetornoChamadaServidorBancoDeDados(empresas);
    }
}
