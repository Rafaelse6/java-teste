package gubee.project;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.DriverManager;

public class GubeeProject {

   public static void main(String[] args){
      Conexao c = new Conexao();
      c.getConexao();
   }
}
