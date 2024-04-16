package academia.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import academia.classes.Aluno;
import academia.classes.PersonalTrainer;
import academia.db.Conexao;

public class AvaliacaoDAO {
	static PreparedStatement ps = null;
	
	public static void cadastrarAvaliacao(PersonalTrainer personal, Aluno aluno, LocalDate data, String descricao) {
		String sql = "INSERT INTO avaliacao (aluno_id, personal_id, data, descricao) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setInt(1, AlunoDAO.getAlunoID(aluno));
			ps.setInt(2, PersonalTrainerDAO.getPersonalID(personal));
			ps.setDate(3, Date.valueOf(data));
			ps.setString(4, descricao);
			
			ps.executeUpdate();
			ps.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String exibirAvaliacaoAluno(String cpf) {
		String sql = "select pA.nome as Aluno, ppT.nome as Personal, descricao , to_char(data, 'dd/MM/yyyy') as periodo " 
				+ "from avaliacao av "
				+ "join personaltrainer pt on av.personal_id = pt.id "
				+ "join aluno a on av.aluno_id = a.id "
				+ "join pessoa pA on pA.id = a.id "
				+ "join pessoa ppT on ppT.id = pt.id "
				+ "where  pA.cpf = ? "
				+ "order by data";
		
		StringBuilder dadosAvaliacao = new StringBuilder();
		
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, cpf);
			
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					String aluno = rs.getString("Aluno");
					String personal = rs.getString("Personal");
					String descricao = rs.getString("descricao");
					String periodo = rs.getString("periodo");
					
					dadosAvaliacao.append(String.format("""
							
					Aluno: %s
					Personal: %s
					Descricao: %s
					Data: %s
					-----------------------
					""", aluno, personal, descricao, periodo));
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return dadosAvaliacao.toString();
		
	}
	
	public static String exibirAvaliacaoPeriodo(int dataInicio, int dataFim) {
		String sql = "select pA.nome as Aluno, ppT.nome as Personal, descricao, to_char(data, 'dd/MM/yyyy') as periodo "
				+ "from avaliacao av "
				+ "join personaltrainer pt on av.personal_id = pt.id "
				+ "join aluno a on av.aluno_id = a.id "
				+ "join pessoa pA on pA.id = a.id "
				+ "join pessoa ppT on ppT.id = pt.id "
				+ "where extract(month from data) >= ? and extract(month from data) <= ? "
				+ "order by data";
		
		StringBuilder dados = new StringBuilder();
		
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setInt(1, dataInicio);
			ps.setInt(2, dataFim);
			
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					String aluno = rs.getString("Aluno");
					String personal = rs.getString("Personal");
					String descricao = rs.getString("descricao");
					String periodo = rs.getString("periodo");
					
					dados.append(String.format("""
							
					Aluno: %s
					Personal: %s
					Descricao: %s
					Data: %s
					""", aluno, personal, descricao, periodo));
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return dados.toString();
	}

}
