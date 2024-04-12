package academia.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import academia.classes.Funcionario;
import academia.db.Conexao;

public class FuncionarioDAO {
	static PreparedStatement ps = null;
	
	public void cadastrar(Funcionario funcionario) {
		String sqlPessoa = "INSERT INTO pessoa "
				+ "(nome, cpf, dataNascimento, contato, senha, tipo) VALUES (?, ?, ?, ?, ?, 'Funcionario')";
		
		try {
			ps = Conexao.conectar().prepareStatement(sqlPessoa);
			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getCpf());
			ps.setDate(3, Date.valueOf(funcionario.getDataNascimento()));
			ps.setString(4, funcionario.getContato());
			ps.setString(5, funcionario.getSenha());
		
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sqlFuncionario = "INSERT INTO funcionario (id, cargo) "
				+ "VALUES (currval('pessoa_id_seq'), ?)";
		
		try {
			PreparedStatement ps = Conexao.conectar().prepareStatement(sqlFuncionario);
			ps.setString(1, funcionario.getCargo());
			
			ps.executeUpdate();
			ps.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String exibeFuncionarios() {
		String sql = "SELECT p.nome, p.dataNascimento, p.contato, f.cargo "
				+ "FROM Pessoa p "
				+ "INNER JOIN Funcionario f ON p.id = f.id ";
		StringBuilder dados = new StringBuilder();
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String nome = rs.getString("nome");
					String dataNascimento = rs.getString("dataNascimento");
					String contato = rs.getString("contato");
					String cargo = rs.getString("cargo");
					dados.append(String.format("""
					Nome: %s
					Data de Nascimento: %s
					Contato: %s
					Cargo: %s
					
					""", nome, dataNascimento, contato, cargo));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dados.toString();
	}
}
