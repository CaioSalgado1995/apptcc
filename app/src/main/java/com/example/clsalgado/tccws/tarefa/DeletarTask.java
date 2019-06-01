package com.example.clsalgado.tccws.tarefa;

import android.os.AsyncTask;
import android.util.Log;

import com.example.clsalgado.tccws.UsuarioActivity;
import com.example.clsalgado.tccws.modelo.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletarTask extends AsyncTask<Usuario, Integer, Usuario> {
    private UsuarioActivity activity;

    public DeletarTask(UsuarioActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Usuario doInBackground(Usuario... usuarios) {
        Usuario retorno = new Usuario();
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mariadb://192.168.0.12:3306/utfpr_tcc_wsjpa", "root", "root");

            PreparedStatement preparedStatement =
                    connection.prepareStatement("delete from usuario where matricula = ?");
            preparedStatement.setInt(1, usuarios[0].getMatricula());


            boolean sucesso = preparedStatement.execute();

            if(!sucesso) {
                Log.d("DIRETODATABASE", "Erro ao remover usuário!");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Log.e("DIRETODATABASE-ERRO", e.getMessage());
        }
        return usuarios[0];
    }

    @Override
    protected void onPostExecute(Usuario usuario) {
        activity.trataRetornoChamadaServidorBancoDeDados(usuario, "REMOVER");
    }
}
