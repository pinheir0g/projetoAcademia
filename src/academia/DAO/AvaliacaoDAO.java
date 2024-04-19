package academia.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import academia.classes.Aluno;
import academia.classes.Avaliacao;
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
	
	public static List<Avaliacao> exibirAvaliacao(String cpf) {
		String sql = "select p_aluno.cpf as cpf_aluno, p_personal.cpf as cpf_personal, descricao , data " 
				+ "from avaliacao av "
				+ "join personaltrainer pt on av.personal_id = pt.id "
				+ "join aluno a on av.aluno_id = a.id "
				+ "join pessoa p_aluno on p_aluno.id = a.id "
				+ "join pessoa p_personal on p_personal.id = pt.id "
				+ "where p_aluno.cpf = ? or p_personal.cpf = ? "
				+ "order by data";
		
		List<Avaliacao> avaliacoes = new ArrayList<>();
		Avaliacao avaliacao = null;
		Aluno aluno = null;
		PersonalTrainer personal = null;
		
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setString(2, cpf);
			
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					String cpfAluno = rs.getString("cpf_aluno");
					String cpfPersonal = rs.getString("cpf_personal");
					String descricao = rs.getString("descricao");
					Date data = rs.getDate("data");
					
					aluno = AlunoDAO.getAluno(cpfAluno);
					personal = PersonalTrainerDAO.getPersonal(cpfPersonal);
					avaliacao = new Avaliacao(aluno, data.toLocalDate(), personal, descricao);
					avaliacoes.add(avaliacao);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return avaliacoes;		
	}
	
	public static List<Avaliacao> exibirAvaliacaoPeriodo(int dataInicio, int dataFim) {
		String sql = "select p_aluno.cpf as cpf_aluno, p_personal.cpf as cpf_personal, descricao , data " 
				+ "from avaliacao av "
				+ "join personaltrainer pt on av.personal_id = pt.id "
				+ "join aluno a on av.aluno_id = a.id "
				+ "join pessoa p_aluno on p_aluno.id = a.id "
				+ "join pessoa p_personal on p_personal.id = pt.id "
				+ "where extract(month from data) >= ? and extract(month from data) <= ? "
				+ "order by data";
		
		List<Avaliacao> avaliacoes = new ArrayList<>();
		Avaliacao avaliacao = null;
		Aluno aluno = null;
		PersonalTrainer personal = null;
		
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setInt(1, dataInicio);
			ps.setInt(2, dataFim);
			
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					String cpfAluno = rs.getString("cpf_aluno");
					String cpfPersonal = rs.getString("cpf_personal");
					String descricao = rs.getString("descricao");
					Date data = rs.getDate("data");
					
					aluno = AlunoDAO.getAluno(cpfAluno);
					personal = PersonalTrainerDAO.getPersonal(cpfPersonal);
					avaliacao = new Avaliacao(aluno, data.toLocalDate(), personal, descricao);
					avaliacoes.add(avaliacao);
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return avaliacoes;
	}

}
