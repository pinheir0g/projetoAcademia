package academia.classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import academia.DAO.AgendamentoDAO;
import academia.DAO.AlunoDAO;
import academia.DAO.PersonalTrainerDAO;
import academia.DAO.PlanoDAO;
import academia.services.ValidacaoPessoa;

public class Aluno extends Pessoa{
	private Plano planoContratado;
	private LocalDate dataMatricula;
	private List<Avaliacao> avaliacoesFisicas = new ArrayList<>();
	
	public Aluno(String nome, String cpf, LocalDate dataNascimento, String contato, String senha, Plano planoContratado, LocalDate dataMatricula) {
		super(nome, cpf, dataNascimento, contato, senha);
		this.planoContratado  = planoContratado;
		this.dataMatricula  = dataMatricula;
	}

	public Plano getPlanoContratado() {
		return planoContratado;
	}

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}
	
	public List<Avaliacao> getAvaliacoesFisicas() {
		return avaliacoesFisicas;
	}
	
	public static void soliciarAgendamento(String cpf) {
		Scanner sc = new Scanner(System.in);
		Aluno aluno = null;
		PersonalTrainer personal = null;
		int id = 0;
		boolean continua = true;
		System.out.println(PersonalTrainerDAO.exibePersonalTrainers());
		System.out.println("Com qual Personal Trainer deseja agendar? ");
		do {
			continua = true;
			if(sc.hasNextInt()) { 
				id = sc.nextInt();
				continua = false;
				aluno = AlunoDAO.getAluno(cpf);
				personal = PersonalTrainerDAO.getPersonal(id);
				System.out.println("Digite a data do agendamento: ");
				sc.nextLine();
				String data = sc.nextLine();
				System.out.println("Digite o horario do agendamento: ");
				String horario = sc.nextLine();
				
				LocalDate dataAgendada = LocalDate.parse(data);
				LocalTime horarioAgendado = LocalTime.parse(horario);
				
				AgendamentoDAO.solicitarAgendamento(personal, aluno, dataAgendada, horarioAgendado);
				
				
			} else {
				sc.nextLine();
			}
		}while(continua == true);
	}
	
	public static void cancelarAgendamento(String cpf) {
		boolean continua;
		int id = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Realmente deseja excluir um agendamento? S/N ");
		String escolha = sc.nextLine();
		if(escolha.equalsIgnoreCase("S")) {
			System.out.println(AgendamentoDAO.hitoricoAgendamentos(cpf));
			do {
				continua = true;
				System.out.println("Digite o ID do agendamento que você quer cancelar: ");
				if(sc.hasNextInt()) {
					id = sc.nextInt();
					continua = false;
				} else {
					sc.nextLine();
				}
			} while(continua == true);
			
			AgendamentoDAO.cancelarAgendamento(id);
		} 
	}	
	
	public static void cadastraAluno() {
		Scanner scanner = new Scanner(System.in);
		
		String nomeAluno = ValidacaoPessoa.validaNome();
		String cpf = ValidacaoPessoa.validaCpf();
		LocalDate dataNascimento = ValidacaoPessoa.validaDataNascimento();
		
		System.out.println("Digite o contato do Aluno: ");
		String contato = scanner.nextLine();
		
		System.out.println("Digite a senha do Aluno: ");
		
		String senha = scanner.nextLine();
		
		boolean valida;
		int idPlano;
		do {
			valida = true;
			int cont = 0;
			System.out.println("Escolha o plano do Aluno: ");
			for(Plano plano: PlanoDAO.exibirPlanos()) {
				cont++;
				System.out.println("\nID: " + cont + "\n" + plano);
			}
			idPlano = scanner.nextInt();
			if(idPlano < 1 || idPlano > 4){
				System.out.println("Plano inexistente!");
				valida = false;
			}
			
		}while(!valida);
		LocalDate dataMatricula = LocalDate.now();
		Plano plano = PlanoDAO.getPlano(idPlano);
		Aluno novoAluno = new Aluno(nomeAluno, cpf, dataNascimento, contato, senha, plano, dataMatricula);
		AlunoDAO.cadastrar(novoAluno);
	}
}

