package academia.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import academia.classes.Aluno;
import academia.classes.PersonalTrainer;
import academia.db.Conexao;

public class AgendamentoDAO {
	PreparedStatement ps = null;
	
	public void solicitarAgendamento(PersonalTrainer personal, Aluno aluno, LocalDate data, LocalTime hora) {
		String sql = "INSERT INTO agendamento (aluno_id, personal_id, data, horario)"
				+ "VALUES (?, ?, ?, ?)";
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setInt(1, AlunoDAO.getAlunoID(aluno));
			ps.setInt(2, PersonalTrainerDAO.getPersonalID(personal));
			ps.setDate(3, java.sql.Date.valueOf(data));
			ps.setTime(4, java.sql.Time.valueOf(hora));
			
			ps.executeUpdate();
			ps.close();
			}catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
