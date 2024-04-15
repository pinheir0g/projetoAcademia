package academia.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import academia.classes.Aluno;
import academia.classes.PersonalTrainer;
import academia.db.Conexao;

public class AgendamentoDAO {
	static PreparedStatement ps = null;
	
	public void solicitarAgendamento(PersonalTrainer personal, Aluno aluno, LocalDate data, LocalTime hora) {
		String sql = "INSERT INTO agendamento (aluno_id, personal_id, data, horario)"
				+ "VALUES (?, ?, ?, ?)";
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
	
	public static String hitoricoAgendamentos(String cpf) {
		String sql = "SELECT p.nome as nome_aluno, pa.nome as nome_personal, ag.data, ag.horario "
				+ "from Agendamento ag "
				+ "JOIN Aluno a ON ag.aluno_id = a.id "
				+ "JOIN Pessoa p ON a.id = p.id "
				+ "JOIN PersonalTrainer pt ON ag.personal_id = pt.id "
				+ "JOIN Pessoa pa ON pt.id = pa.id "
				+ "WHERE p.cpf = ?";
		StringBuilder dados = new StringBuilder();
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, cpf);
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					String nomeAluno = rs.getString("nome_aluno");
					String nomePersonal = rs.getString("nome_personal");
					String data = rs.getString("data");
					String horario = rs.getString("horario");
					dados.append(String.format( """
							Aluno: %s
							Personal Trainer: %s
							Data: %s
							Horario: %s
							
							""", nomeAluno, nomePersonal, data, horario));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dados.toString();
	}
}
