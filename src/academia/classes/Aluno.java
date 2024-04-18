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
import academia.services.ValidacaoAgendamento;
import academia.services.ValidacaoPessoa;

public class Aluno extends Pessoa{
	private Plano planoContratado;
	private LocalDate dataMatricula;
	private List<Avaliacao> avaliacoesFisicas = new ArrayList<>();
	
	public Aluno(String nome, String cpf, LocalDate dataNascimento, String contato, String senha, Plano planoContratado, LocalDate dataMatricula, String tipo) {
		super(nome, cpf, dataNascimento, contato, senha, tipo);
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
		PersonalTrainer novoPersonal = null;
		int id = 0;
		boolean continua;
		for(PersonalTrainer personal: PersonalTrainerDAO.exibePersonalTrainers()) {
    		int idPersonal = PersonalTrainerDAO.getPersonalID(personal);
    		System.out.println("ID: " + idPersonal + "\n" + personal);
    	}
		do {
			continua = true;
			System.out.println("Digite o ID do Personal Trainer deseja agendar \n");
			if(sc.hasNextInt()) { 
				id = sc.nextInt();
				sc.nextLine();
				aluno = AlunoDAO.getAluno(cpf);
				novoPersonal = PersonalTrainerDAO.getPersonalByID(id);
				
				LocalDate dataAgendamento = ValidacaoAgendamento.validaDataAgendamento();
				LocalTime horarioAgendamento = ValidacaoAgendamento.validaHorario();
				
				AgendamentoDAO.solicitarAgendamento(novoPersonal, aluno, dataAgendamento, horarioAgendamento, "Aberto");
				System.out.println("Agendamento solicitado com Sucesso!");
				continua = false;
			} else {
				System.out.println("Digite apenas números.");
				continua = false;
				sc.nextLine();
			}
		}while(continua == true);
	}
	
	public static void cancelarAgendamento(String cpf){
		boolean continua;
		int id = 0;
		int cont = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Realmente deseja excluir um agendamento? S/N ");
		String escolha = sc.nextLine();
		if(escolha.equalsIgnoreCase("S")) {
			for(Agendamento agendamento: AgendamentoDAO.hitoricoAgendamentos(cpf)) {
				List<Integer> ids = AgendamentoDAO.getAgendamentoID(agendamento);
				for (Integer inteiro : ids) {
					System.out.println("ID: " + inteiro + "\n" + agendamento);
					cont++;
				}
				if(cont == ids.size()) break;
				System.out.println(ids.size());
			}
			do {
				continua = true;
				System.out.println("Digite o ID do agendamento que você quer cancelar: ");
				if(sc.hasNextInt()) {
					id = sc.nextInt();
					continua = false;
				} else {
					System.out.println("\nDigite apenas números.");
					sc.nextLine();
				}
			} while(continua == true);
			
			AgendamentoDAO.cancelarAgendamento(id);
			System.out.println("Agendamento cancelado com Sucesso!");
		} 
	}	
	
	public static void cadastraAluno() {
		Scanner scanner = new Scanner(System.in);
		
		String nomeAluno = ValidacaoPessoa.validaNome();
		String cpf = ValidacaoPessoa.validaCpf();
		LocalDate dataNascimento = ValidacaoPessoa.validaDataNascimento();
		
		System.out.println("Digite o contato do Aluno: ");
		String contato = scanner.nextLine();
		String senha;
		do {
			System.out.println("Digite a senha do Aluno: ");
			senha = scanner.nextLine();
			if(senha.length() > 8) {
				System.out.println("A senha deve conter no máximo 8 digitos!");
			}
		}while(senha.length() > 8);
		
		boolean valida;
		int idPlano = 0;
		do {
			valida = true;
			int cont = 0;
			System.out.println("\nEscolha o ID do plano do Aluno: ");
			for(Plano plano: PlanoDAO.exibirPlanos()) {
				cont++;
				System.out.println("\nID: " + cont + "\n" + plano);
			}
			if(scanner.hasNextInt()) {
				idPlano = scanner.nextInt();
				scanner.nextLine();
				if(idPlano < 1 || idPlano > cont){
					System.out.println("Plano inexistente!");
					valida = false;
				}
			}else {
				System.out.println("Digite apenas números!");
				valida = false;
				scanner.nextLine();
			}
			
		}while(!valida);
		LocalDate dataMatricula = LocalDate.now();
		Plano plano = PlanoDAO.getPlano(idPlano);
		Aluno novoAluno = new Aluno(nomeAluno, cpf, dataNascimento, contato, senha, plano, dataMatricula, "Aluno");
		AlunoDAO.cadastrar(novoAluno);
	}

	@Override
	public String toString() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return super.toString() + String.format("""
                Plano: %s
                Data de Matricula: %s
                ---------------------------------------
                """, planoContratado.getNome(), df.format(dataMatricula));
	}	
}