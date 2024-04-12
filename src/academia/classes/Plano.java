package academia.classes;

public class Plano {
	private String nome;
	private String duracao;
	private double valor;
	private String descricao;

	public Plano(String nome, String duracao, double valor, String descricao) {
		this.nome = nome;
		this.duracao = duracao;
		this.valor = valor;
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDuracao() {
		return duracao;
	}

	@Override
	public String toString() {
		return String.format("""
				Nome:%s
				Duração: %s
				Valor: %d.2f
				Descricao: %s
				""", nome, duracao, valor, descricao);
	}
	
	
}
