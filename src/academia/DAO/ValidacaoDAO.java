package academia.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import academia.classes.Pessoa;
import academia.db.Conexao;

public class ValidacaoDAO {
	static PreparedStatement ps = null;
	
	public static Pessoa conectaUsuario(String cpf, String senha) {
		
		String sql = "SELECT tipo FROM pessoa WHERE cpf = ? and senha = ?";
		String tipoUsuario = "";
		Pessoa usuario = null;
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setString(2, senha);
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					tipoUsuario = rs.getString("tipo");
					
					if(tipoUsuario.equalsIgnoreCase("aluno")) {
						usuario = AlunoDAO.getAluno(cpf);
					}else if(tipoUsuario.equalsIgnoreCase("funcionario")) {
						usuario = FuncionarioDAO.getFuncionario(cpf);
					}else {
						usuario = PersonalTrainerDAO.getPersonal(cpf);
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
}
