package academia.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import academia.classes.Aluno;
import academia.classes.Plano;
import academia.db.Conexao;


public class AlunoDAO {
	static PreparedStatement ps = null;
	
	public static void cadastrar(Aluno aluno) {
		String sqlPessoa = "INSERT INTO pessoa "
				+ "(nome, cpf, dataNascimento, contato, senha, tipo) VALUES (?, ?, ?, ?, ?, 'Aluno')";
		
		try {
			ps = Conexao.conectar().prepareStatement(sqlPessoa);
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getCpf());
			ps.setDate(3, Date.valueOf(aluno.getDataNascimento()));
			ps.setString(4, aluno.getContato());
			ps.setString(5, aluno.getSenha());
		
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sqlAluno = "INSERT INTO aluno (id, plano_id, dataMatricula) "
				+ "VALUES (currval('pessoa_id_seq'), ?, ?)";
		try {
			
			PreparedStatement ps = Conexao.conectar().prepareStatement(sqlAluno);
			
			System.out.println(aluno.getPlanoContratado().getNome());
			int result = PlanoDAO.getPlanoID(aluno.getPlanoContratado().getNome());
			System.out.println(result);
			ps.setInt(1, result);
			ps.setDate(2, Date.valueOf(aluno.getDataMatricula()));
			ps.executeUpdate();
			ps.close();
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static int getAlunoID(Aluno aluno) {
		String sql = "SELECT p.id FROM pessoa p "
				+ "JOIN Aluno a ON p.id = a.id "
				+ "where cpf = ?";
		ResultSet rs;
		int alunoID = 0;
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, aluno.getCpf());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				alunoID = rs.getInt("id");
			}else {
				System.out.println("Aluno não encontrado para o CPF: " + aluno.getCpf());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return alunoID;
	}
	
	public static String exibirDados(String cpf) {

		String sql = "SELECT p.nome as nome_aluno, p.cpf, p.dataNascimento, p.contato, pl.nome as nome_plano "
				+ "FROM Pessoa p "
				+ "JOIN Aluno a ON p.id = a.id "
				+ "JOIN Plano pl ON a.plano_id = pl.id "
				+ "WHERE cpf = ?";
		StringBuilder dados = new StringBuilder();
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, cpf);
			
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					String nome = rs.getString("nome_aluno");
					String cpfAluno = rs.getString("cpf");
					String dataNascimento = rs.getString("dataNascimento");
					String contato = rs.getString("contato");
					String plano = rs.getString("nome_plano");
					dados.append(String.format("""
					Nome: %s
					CPF: %s
					Data de Nascimento: %s
					Contato: %s
					Plano: %s 
					
					""", nome, cpfAluno, dataNascimento, contato, plano));
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dados.toString();
	}
	
	public static String exibirAlunos() {

		String sql = "SELECT p.nome as nome_aluno, p.cpf, p.dataNascimento, p.contato, pl.nome as nome_plano "
				+ "FROM Pessoa p "
				+ "INNER JOIN Aluno a ON p.id = a.id "
				+ "INNER JOIN Plano pl ON a.plano_id = pl.id ";
		StringBuilder dados = new StringBuilder();
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			
			
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					String nome = rs.getString("nome_aluno");
					String cpf = rs.getString("cpf");
					String dataNascimento = rs.getString("dataNascimento");
					String contato = rs.getString("contato");
					String plano = rs.getString("nome_plano");
					dados.append(String.format("""
							
					Nome: %s
					CPF: %s
					Data de Nascimento: %s
					Contato: %s
					Plano: %s
					------------------------------------
					""", nome, cpf, dataNascimento, contato, plano));
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dados.toString();
	}
	
	public static Aluno getAluno(String cpf) {
		ResultSet rs;
		Aluno aluno = null;
		Plano plano = null;
		
		String sql = "select nome, cpf, datanascimento, contato, senha, a.plano_id, a.datamatricula  "
				+ "from aluno a  "
				+ "join pessoa p on a.id = p.id "
				+ "where p.cpf = ?";
		
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			
			if(rs.next()) { 
				String nome = rs.getString("nome");
				String data = rs.getString("datanascimento");
				String contato = rs.getString("contato");
				String senha = rs.getString("senha");
				int planoId = rs.getInt("plano_id");
				String dataM = rs.getString("datamatricula");
				
				LocalDate dataNascimento = LocalDate.parse(data);
				LocalDate dataMatricula = LocalDate.parse(dataM);
				plano = PlanoDAO.getPlano(planoId);
				aluno = new Aluno(nome, cpf, dataNascimento, contato, senha, plano, dataMatricula);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	return aluno;
	}
}
