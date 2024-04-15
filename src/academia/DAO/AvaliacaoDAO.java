package academia.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import academia.classes.Aluno;
import academia.classes.PersonalTrainer;
import academia.db.Conexao;

public class AvaliacaoDAO {
	static PreparedStatement ps = null;
	
	public void cadastrarAvaliacao(PersonalTrainer personal, Aluno aluno, LocalDate data, String descricao) {
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
}
