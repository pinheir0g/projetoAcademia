package academia.classes;

import java.time.LocalDate;
import java.util.Scanner;

import academia.DAO.AlunoDAO;
import academia.DAO.AvaliacaoDAO;
import academia.DAO.PersonalTrainerDAO;
import academia.services.ValidacaoPessoa;

public class Avaliacao {
	private Aluno aluno;
	private LocalDate dataAvaliacao;
	private PersonalTrainer personalTrainer; 
	private String descricao;
	
	public Avaliacao(Aluno aluno, LocalDate dataAvaliacao, PersonalTrainer personalTrainer, String descricao) {
		this.aluno = aluno;
		this.dataAvaliacao = dataAvaliacao;
		this.personalTrainer = personalTrainer;
	 	this.descricao = descricao;
	}

	public LocalDate getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(LocalDate dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public PersonalTrainer getPersonalTrainer() {
		return personalTrainer;
	}
	
	public static void cadastrarAvaliacao() {
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("Digite o seu CPF: ");
		String cpfPersonal = scanner.nextLine();
		System.out.println("Digite o CPF do Aluno: ");
		String cpfAluno = scanner.nextLine();
		
		PersonalTrainer personal = PersonalTrainerDAO.getDadosPersonal(cpfPersonal);
		Aluno aluno = AlunoDAO.getAluno(cpfAluno);
		
		LocalDate dataAvaliacao = ValidacaoPessoa.validaDataAvaliacao();
		System.out.println("Digite a descrição da Avaliação: ");
		String descricao = scanner.nextLine();
		
		AvaliacaoDAO.cadastrarAvaliacao(personal, aluno, dataAvaliacao, descricao);
	}
	
	public static String exibirAvaliacoes() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o inicio do periodo: ");
		int dataInicio = sc.nextInt();
    	System.out.println("Digite o fim do periodo: ");
    	int dataFim = sc.nextInt();
		return AvaliacaoDAO.exibirAvaliacaoPeriodo(dataInicio, dataFim);
	}
	
	@Override
	public String toString() {
		return "Nome do aluno: " + aluno + "\nData da Avaliação: " + dataAvaliacao + "\nPersonal Trainer: " + personalTrainer
				+ "\nDescrição: " + descricao ;
	}
	
	
}
