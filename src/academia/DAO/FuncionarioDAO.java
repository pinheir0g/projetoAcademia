package academia.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import academia.classes.Funcionario;
import academia.db.Conexao;

public class FuncionarioDAO {
	static PreparedStatement ps = null;
	
	public static void cadastrar(Funcionario funcionario) {
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

	public static List<Funcionario> exibeFuncionarios() {
		String sql = "SELECT p.nome, p.cpf, p.dataNascimento, p.contato, p.senha, p.tipo, f.cargo "
				+ "FROM Pessoa p "
				+ "INNER JOIN Funcionario f ON p.id = f.id";
		List<Funcionario> funcionarios = new ArrayList<>();
		Funcionario funcionario = null;
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String nome = rs.getString("nome");
					Date dataNascimento = rs.getDate("dataNascimento");
					String cpf = rs.getString("cpf");
					String contato = rs.getString("contato");
					String senha = rs.getString("senha");
					String cargo = rs.getString("cargo");
					String tipo = rs.getString("tipo");
					
					funcionario = new Funcionario(nome, cpf, dataNascimento.toLocalDate(), contato, senha, cargo, tipo);
					funcionarios.add(funcionario);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}
	
	public static Funcionario getFuncionario(String cpf) {
		String sql = "SELECT p.nome, p.cpf, p.dataNascimento, p.contato, p.senha, p.tipo, f.cargo "
				+ "FROM Pessoa p "
				+ "INNER JOIN Funcionario f ON p.id = f.id "
				+ "WHERE cpf = ? ";
		Funcionario funcionario = null;
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, cpf);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String nome = rs.getString("nome");
					Date dataNascimento = rs.getDate("dataNascimento");
					String contato = rs.getString("contato");
					String senha = rs.getString("senha");
					String cargo = rs.getString("cargo");
					String tipo = rs.getString("tipo");
					
					funcionario = new Funcionario(nome, cpf, dataNascimento.toLocalDate(), contato, senha, cargo, tipo);
				
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return funcionario;
	}
}
