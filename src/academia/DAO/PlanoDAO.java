package academia.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String exibirPlanos() {
		String sql = "SELECT nome, duracao, valor, descricao FROM plano";
		StringBuilder dados = new StringBuilder();
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.executeQuery();
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					String nome = rs.getString("nome");
					String duracao = rs.getString("duracao");
					String descricao = rs.getString("descricao");
					Double valor = rs.getDouble("valor");
					dados.append(String.format("""
					Nome: %s
					Duracão: %s
					Valor: R$ %.2f
					Descricao: %s
					""", nome, duracao, valor, descricao));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dados.toString();
	}
}
