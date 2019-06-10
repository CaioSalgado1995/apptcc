package com.example.clsalgado.tccws.tarefa;

import android.os.AsyncTask;
import android.util.Log;

import com.example.clsalgado.tccws.MainActivity;
import com.example.clsalgado.tccws.UsuarioActivity;
import com.example.clsalgado.tccws.modelo.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroTask extends AsyncTask<Usuario, Integer, Usuario> {
    private UsuarioActivity activity;

    public CadastroTask(UsuarioActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Usuario doInBackground(Usuario... usuarios) {
        Usuario retorno = new Usuario();
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mariadb://192.168.0.12:3306/utfpr_tcc_wsjpa", "root", "root");

            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into usuario(contador, senha) values(?,?)");
            preparedStatement.setInt(1, usuarios[0].getContador());
            preparedStatement.setString(2, usuarios[0].getSenha());


            int sucesso = preparedStatement.executeUpdate();

            if(sucesso == 0) {
                Log.d("DIRETODATABASE", "Erro ao inserir usuário!");
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
        activity.trataRetornoChamadaServidorBancoDeDados(usuario, "CADASTRAR");
    }
}
