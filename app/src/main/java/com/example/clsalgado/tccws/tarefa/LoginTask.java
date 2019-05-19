package com.example.clsalgado.tccws.tarefa;

import android.os.AsyncTask;
import android.util.Log;

import com.example.clsalgado.tccws.MainActivity;
import com.example.clsalgado.tccws.modelo.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe criada para executar chamada ao servidor de banco de dados
 * Necessário criação de uma AsyncTask pois não é permitido fazer esse tipo de acesso na thread atual da Activity
 */
public class LoginTask extends AsyncTask<Usuario, Integer, Usuario> {

    private MainActivity mainActivity;

    public LoginTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected Usuario doInBackground(Usuario... usuarios) {
        Usuario retorno = new Usuario();
        try {
            Connection connection =
                        DriverManager.getConnection("jdbc:mariadb://192.168.0.12:3306/utfpr_tcc_wsjpa", "root", "root");

            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from usuario where matricula = ? and senha = ?");
            preparedStatement.setString(1, usuarios[0].getMatricula());
            preparedStatement.setString(2, usuarios[0].getSenha());

            boolean sucesso = preparedStatement.execute();

            if(sucesso) {
                ResultSet resultSet = preparedStatement.getResultSet();

                while(resultSet.next()) {
                    String matricula = resultSet.getString("matricula");
                    String senha = resultSet.getString("senha");
                    Log.i("DIRETODATABASE", matricula + " " + senha);
                    retorno.setMatricula(matricula);
                    retorno.setSenha(senha);
                }
                resultSet.close();
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Log.e("DIRETODATABASE-ERRO", e.getMessage());
        }
        return retorno;
    }

    @Override
    protected void onPostExecute(Usuario usuario) {
        mainActivity.trataRetornoChamadaServidorBancoDeDados(usuario);
    }
}
