package academia.classes;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import academia.DAO.AlunoDAO;

public class Aluno extends Pessoa{
	private String planoContratado;
	private LocalDate dataMatricula;
	private List<Avaliacao> avaliacoesFisicas = new ArrayList<>();
	
	public Aluno(String nome, String cpf, LocalDate dataNascimento, String contato, String senha, String planoContratado, LocalDate dataMatricula) {
		super(nome, cpf, dataNascimento, contato, senha);
		this.planoContratado  = planoContratado;
		this.dataMatricula  = dataMatricula;
	}

	public String getPlanoContratado() {
		return planoContratado;
	}

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}
	
	public List<Avaliacao> getAvaliacoesFisicas() {
		return avaliacoesFisicas;
	}
	
	
	public void soliciarAgendamento(PersonalTrainer personalTrainer, LocalDate data, LocalDate horario) {
		
	}
	public void cancelarAgendamento(Agendamento agendamento) {
		
	}	
	
	public static void cadastraAluno() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o nome do Aluno: ");
		String nomeAluno = scanner.nextLine();
		
		System.out.println("Digite o CPF do Aluno: ");
		String cpf = scanner.nextLine();
		
		System.out.println("Digite a Data de Nascimento do Aluno: (no formato dd/MM/yyyy)");
		String data = scanner.nextLine();
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataNascimento = null;
		try {
			dataNascimento = LocalDate.parse(data, df);
		}catch(Exception e) {
			System.out.println("Dormato de data inválido. Use o formato dd/MM/yyyy.");
		}
		
		System.out.println("Digite o contato do Aluno: ");
		String contato = scanner.nextLine();
		
		System.out.println("Digite a senha do Aluno: ");
		String senha = scanner.nextLine();
		
		String plano = "";
		boolean valida;
		do {
			valida = true;
			System.out.println("Digite o Plano do Aluno: ");
			plano = scanner.nextLine();
			int planoId = AlunoDAO.getPlanoID(plano);
			if(planoId == 0){
				System.out.println("Plano inexistente!");
				valida = false;
			}
			
		}while(!valida);
		LocalDate dataMatricula = LocalDate.now();
		
		
		Aluno novoAluno = new Aluno(nomeAluno, cpf, dataNascimento, contato, senha, plano, dataMatricula);
		AlunoDAO.cadastrar(novoAluno, plano);
	}
}

