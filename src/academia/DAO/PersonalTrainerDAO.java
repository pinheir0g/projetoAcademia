package academia.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import academia.classes.PersonalTrainer;
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
			ps.setDate(3, Date.valueOf(personal.getDataNascimento()));
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
			ps.setString(3, personal.getHorarioAtendimento());
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
	
	public static List<PersonalTrainer> exibePersonalTrainers() {
		String sql = "SELECT p.nome, p.cpf, p,dataNascimento, p.contato, pt.especialidade, p.senha, p.tipo, pt.cref, pt.horarioAtendimento, p.id "
				+ "FROM Pessoa p "
				+ "JOIN PersonalTrainer pt ON pt.id = p.id";
		
		List<PersonalTrainer> listaPersonal = new ArrayList<>();
		PersonalTrainer personal = null;
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					String nome = rs.getString("nome");
					String cpf = rs.getString("cpf");
					Date dataNascimento = rs.getDate("dataNascimento");
					String contato = rs.getString("contato");
					String especialidade = rs.getString("especialidade");
					String senha = rs.getString("senha");
					String cref = rs.getString("cref");
					String tipo = rs.getString("tipo");
					String horario = rs.getString("horarioatendimento");
					
					personal = new PersonalTrainer(nome, cpf, dataNascimento.toLocalDate(), contato, senha, especialidade, cref, horario, tipo);
					listaPersonal.add(personal);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaPersonal;			
	}
	
	public static PersonalTrainer getPersonalByID(int id) {
		ResultSet rs;
		PersonalTrainer personal = null;
		
		String sql = "select nome, cpf, datanascimento, contato, senha, tipo, pt.especialidade, pt.cref, pt.horarioatendimento "
				+ "from personaltrainer pt "
				+ "join pessoa p on pt.id = p.id "
				+ "where pt.id = ? ";
		
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				Date dataNascimento = rs.getDate("datanascimento");
				String contato = rs.getString("contato");
				String senha = rs.getString("senha");
				String especialidade = rs.getString("especialidade");
				String cref = rs.getString("cref");
				String horario = rs.getString("horarioatendimento");
				String tipo = rs.getString("tipo");
				
				personal = new PersonalTrainer(nome, cpf, dataNascimento.toLocalDate(), contato, senha, especialidade, cref, horario, tipo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return personal;
	}
	
	public static PersonalTrainer getPersonal(String cpf) {
		String sql = "SELECT p.nome, p.cpf, p.dataNascimento, p.contato, p.senha, p.tipo, pt.especialidade, pt.cref, pt.horarioAtendimento "
				+ "FROM Pessoa p "
				+ "JOIN PersonalTrainer pt ON pt.id = p.id "
				+ "where p.cpf = ?";
		PersonalTrainer personal = null;
		ResultSet rs;
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String nome = rs.getString("nome");
				String cpfPersonal = rs.getString("cpf");
				Date dataNascimento = rs.getDate("dataNascimento");
				String contato = rs.getString("contato");
				String senha = rs.getString("senha");
				String especialidade = rs.getString("especialidade");
				String cref = rs.getString("cref");
				String tipo = rs.getString("tipo");
				String horarioAtendimento = rs.getString("horarioAtendimento");
				
				personal = new PersonalTrainer(nome, cpfPersonal, dataNascimento.toLocalDate(), contato, senha, especialidade, cref, horarioAtendimento, tipo);
			}else {
				System.out.println("Personal Trainer não encontrado!");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return personal;
	}

}
