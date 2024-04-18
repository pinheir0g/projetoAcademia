package academia.classes;

import java.util.Scanner;

import academia.DAO.PlanoDAO;
import academia.services.ValidacaoPlano;

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

	public static void cadastraPlano() {
		Scanner scanner = new Scanner(System.in);
		String nomePlano = ValidacaoPlano.validaNomePlano();
		System.out.println("Digite a duração do Plano (Mensal/ Trimestral/ Semestral/ Anual): ");
		String duracaoPlano = scanner.nextLine();
		
        System.out.println("Digite o valor do Plano: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Digite a descrição do Plano: ");
        String descricao = scanner.nextLine();
        
        Plano novoPlano = new Plano(nomePlano, duracaoPlano, valor, descricao);
        PlanoDAO.cadastrarPlano(novoPlano);
	}
	
	@Override
	public String toString() {
		return String.format("""
				Nome: %s
				Duração: %s
				Valor: R$ %s
				Descricao: %s
				---------------------------------------
				""", nome, duracao, valor, descricao);
	}
}
