package academia.classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import academia.DAO.PersonalTrainerDAO;

public class PersonalTrainer extends Pessoa {
	private String especialidade;
	private String cref;
	private LocalTime horarioAtendimento;
	
	
	public PersonalTrainer(String nome, String cpf, LocalDate dataNascimento, String contato, String senha,
			String especialidade, String cref, LocalTime horarioAtendimento) {
		super(nome, cpf, dataNascimento, contato, senha);
		this.especialidade = especialidade;
		this.cref = cref;
		this.horarioAtendimento = horarioAtendimento;
	}
 
	public LocalTime getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(LocalTime horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public String getCref() {
		return cref;
	}

	
	public void vizualizarAgenda() {
		
	}
	
	public void registrarAvaliacao(Avaliacao avaliacao) {
		
	}
	
	public void viasualizarAvaliacoesFeitas() {
	 	
  }
	public static void cadastraPersonal() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o nome do Personal Trainer: ");
		String nomePersonal = scanner.nextLine();
		
		System.out.println("Digite o CPF do Personal Trainer: ");
		String cpf = scanner.nextLine();
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataNascimento;
		do {
			dataNascimento = null;
			System.out.println("Digite a Data de Nascimento do Personal Trainer: (no formato dd/MM/yyyy)");
			String data = scanner.nextLine();
			try {
				dataNascimento = LocalDate.parse(data, df);
			}catch(Exception e) {
				System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
			}
		}while(dataNascimento == null);
		
		System.out.println("Digite o contato do Personal Trainer: ");
		String contato = scanner.nextLine();
		
		System.out.println("Digite a senha do Personal Trainer: ");
		String senha = scanner.nextLine();
		
		System.out.println("Digite a especialidade do Personal Trainer");
		String especialidade = scanner.nextLine();
		
		System.out.println("Digite o CREF do Personal Trainer: ");
		String cref = scanner.nextLine();
		
		DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime horarioAtendimento;
		do {
			horarioAtendimento = null;
			System.out.println("Digite o horario de atendimento do Personal Trainer: (HH:mm)");
			String horario = scanner.nextLine();
			try {
				horarioAtendimento = LocalTime.parse(horario, tf);
			}catch(Exception e) {
				System.out.println("Formato de horario inválido. Use o formato dd/MM/yyyy.");
			}
		}while(horarioAtendimento == null);
		
		PersonalTrainer novoPersonal = new PersonalTrainer(nomePersonal, cpf, dataNascimento, contato, senha, especialidade, cref, horarioAtendimento);
		PersonalTrainerDAO.cadastraPersonal(novoPersonal);
	}
		
	@Override
	public String toString() {
		return super.toString() + String.format("""
				Especialidade: %s
				CREF: %s
				Horário de Atendiamento: %s
				""", especialidade, cref, horarioAtendimento);
	}	
	
}
