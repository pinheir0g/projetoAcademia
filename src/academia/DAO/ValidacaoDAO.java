package academia.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import academia.db.Conexao;

public class ValidacaoDAO {
	static PreparedStatement ps = null;
	
	public static List<String> conectaUsuario(String cpf, String senha) {
		
		String sql = "SELECT tipo, nome FROM pessoa WHERE cpf = ? and senha = ?";
		String tipoUsuario = "";
		String nomeUsuario = "";
		List<String> dadosUsuario = new ArrayList<>();
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setString(2, senha);
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					tipoUsuario = rs.getString("tipo");
					nomeUsuario = rs.getString("nome");
					dadosUsuario.add(tipoUsuario);
					dadosUsuario.add(nomeUsuario);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dadosUsuario;
	}
}
