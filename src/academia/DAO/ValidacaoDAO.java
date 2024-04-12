package academia.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import academia.classes.Pessoa;
import academia.db.Conexao;

public class ValidacaoDAO {
	static PreparedStatement ps = null;
	
	public static String conectaUsuario(String cpf, String senha) {
		
		String sql = "SELECT tipo FROM pessoa WHERE cpf = ? and senha = ?";
		String tipoUsuario = "";
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setString(2, senha);
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					tipoUsuario = rs.getString("tipo");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return tipoUsuario;
	}
	
}
