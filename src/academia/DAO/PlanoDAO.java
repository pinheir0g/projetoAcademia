package academia.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import academia.classes.Plano;
import academia.db.Conexao;

public class PlanoDAO {
	static PreparedStatement ps = null;
	
	
	public static void cadastrarPlano(Plano plano) {
		String sql = "INSERT INTO plano (nome, duracao, valor, descricao) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, plano.getNome());
			ps.setString(2, plano.getDuracao());
			ps.setDouble(3, plano.getValor());
			ps.setString(4, plano.getDescricao());
			
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Plano> exibirPlanos(){
		String sql = "SELECT nome, duracao, valor, descricao FROM plano";
		List<Plano> planos = new ArrayList<>();
		Plano plano = null;
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.executeQuery();
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
	;
					String nome = rs.getString("nome");
					String duracao = rs.getString("duracao");
					double valor = rs.getDouble("valor");
					String descricao = rs.getString("descricao");
					
					plano = new Plano(nome, duracao, valor, descricao);
					planos.add(plano);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return planos;
	}
	
	public static int getPlanoID(String nomePlano) {
		String sql = "SELECT id FROM plano WHERE nome = ?";
		ResultSet rs;
		int idPlano = 0;
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, nomePlano);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				idPlano = rs.getInt("id");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return idPlano;
	}
	
	public static Plano getPlano(int id) {
		String sql = "SELECT * FROM plano WHERE id = ?";
		ResultSet rs;
		Plano plano1 = null;
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String nomePlano = rs.getString("nome");
				String duracao = rs.getString("duracao");
				Double valor = rs.getDouble("valor");
				String descricao = rs.getString("descricao");
				
				plano1 = new Plano(nomePlano, duracao, valor, descricao);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return plano1;
	}
}
