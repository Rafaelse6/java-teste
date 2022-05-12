package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public Connection getConexao() {

        try {
            //tentar estabelecer a conexao
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gubee_project?serverTimezone=UTC",         
                    "root", 
                    "" //
            );
            return conn;
        } catch (Exception e) {

            System.out.println("Erro ao conectar" + e.getMessage());
            return null;
        }
    }

}
