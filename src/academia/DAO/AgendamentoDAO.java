package academia.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import academia.classes.Agendamento;
import academia.classes.Aluno;
import academia.classes.PersonalTrainer;
import academia.db.Conexao;

public class AgendamentoDAO {
	static PreparedStatement ps = null;
	
	public static void solicitarAgendamento(PersonalTrainer personal, Aluno aluno, LocalDate data, LocalTime hora, String status) {
		String sql = "INSERT INTO agendamento (aluno_id, personal_id, data, horario, status)"
				+ "VALUES (?, ?, ?, ?, 'Aberto')";
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setInt(1, AlunoDAO.getAlunoID(aluno));
			ps.setInt(2, PersonalTrainerDAO.getPersonalID(personal));
			ps.setDate(3, Date.valueOf(data));
			ps.setTime(4, Time.valueOf(hora));
			ps.executeUpdate();
			ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static List<Agendamento> hitoricoAgendamentos(String cpf) {
		String sql = "SELECT p.cpf as cpf_aluno, ag.data, ag.horario, ag.status, pt.id "
				+ "from Agendamento ag "
				+ "JOIN Aluno a ON ag.aluno_id = a.id "
				+ "JOIN Pessoa p ON a.id = p.id "
				+ "JOIN PersonalTrainer pt ON ag.personal_id = pt.id "
				+ "JOIN Pessoa pa ON pt.id = pa.id "
				+ "WHERE pa.cpf = ? or p.cpf = ?";
		
		List<Agendamento> listaAgendamentos = new ArrayList<>();
		Agendamento agendamentos = null;
		Aluno aluno = null;
		PersonalTrainer personal = null;
		
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setString(2, cpf);
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					String cpfAluno = rs.getString("cpf_aluno");
					Date data = rs.getDate("data");
					Time horario = rs.getTime("horario");
					String status = rs.getString("status");
					int idPersonal = rs.getInt("id");
					
					aluno = AlunoDAO.getAluno(cpfAluno);
					personal = PersonalTrainerDAO.getPersonalByID(idPersonal);
					agendamentos = new Agendamento(data.toLocalDate(), horario.toLocalTime(), aluno, personal, status);
					listaAgendamentos.add(agendamentos);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaAgendamentos;
	}
	
	public static void cancelarAgendamento(int id) {
		String sql = "UPDATE agendamento SET status = 'cancelado' "
				+ "where id = ? ";
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Integer> getAgendamentoID(Agendamento agendamento) {
		String sql = "SELECT ag.id FROM Agendamento ag "
				+ "JOIN Pessoa p ON p.id = ag.aluno_id "
				+ "WHERE p.cpf = ?";

		int agendamentoId = 0;
		List<Integer> idLista = new ArrayList<>();
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, agendamento.getAluno().getCpf());

			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					agendamentoId = rs.getInt("id");
					idLista.add(agendamentoId);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return idLista;
	}
}
