package academia.DAO;

import academia.classes.PersonalTrainer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import academia.db.Conexao;

public class PersonalTrainerDAO {
	static PreparedStatement ps = null;
	
	
	public static void cadastraPersonal(PersonalTrainer personal) {
		String sqlPessoa = "INSERT INTO pessoa "
				+ "(nome, cpf, dataNascimento, contato, senha, tipo) VALUES (?, ?, ?, ?, ?, 'PersonalTrainer')";
		
		try {
			ps = Conexao.conectar().prepareStatement(sqlPessoa);
			ps.setString(1, personal.getNome());
			ps.setString(2, personal.getCpf());
			ps.setDate(3, java.sql.Date.valueOf(personal.getDataNascimento()));
			ps.setString(4, personal.getContato());
			ps.setString(5, personal.getSenha());
		
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		String sqlPersonal = "INSERT INTO personalTrainer (id, especialidade, cref, horarioAtendimento)"
				+ "VALUES (currval('pessoa_id_seq'), ?, ?, ?)";
		
		try {
			ps = Conexao.conectar().prepareStatement(sqlPersonal);
			ps.setString(1, personal.getEspecialidade());
			ps.setString(2, personal.getCref());
			ps.setTime(3, java.sql.Time.valueOf(personal.getHorarioAtendimento()));
			ps.executeUpdate();
			ps.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int getPersonalID(PersonalTrainer personal) {
		String sql = "SELECT p.id FROM pessoa p "
				+ "JOIN personalTrainer pt ON p.id = pt.id "
				+ "where cpf = ?";
		ResultSet rs;
		int personalID = 0;
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, personal.getCpf());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				personalID = rs.getInt("id");
			}else {
				System.out.println("Personal Trainer não encontrado para o CPF: " + personal.getCpf());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return personalID;
	}
	
}
