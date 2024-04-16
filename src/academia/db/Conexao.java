package academia.db;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static String url = "jdbc:postgresql://localhost:5432/javact_db";
	private static String usuario = "";
	private static String senha = "";
	
	private static Connection conexao = null;
	
	public static Connection conectar() {
        try {
        	if(conexao == null) {
        		conexao = DriverManager.getConnection(url, usuario, senha);
        		return conexao;
        	}else {
        		return conexao;
        	}
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar ao banco de dados: " + ex.getMessage());
            return null;
        }
    }
}