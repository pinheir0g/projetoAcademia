package academia.classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import academia.DAO.AgendamentoDAO;
import academia.DAO.AlunoDAO;
import academia.DAO.PersonalTrainerDAO;
import academia.services.ValidacaoAgendamento;
import academia.services.ValidacaoPersonal;

public class Agendamento {
	private LocalDate dataAgendamento;
	private LocalTime horaAgendamento;
	private Aluno aluno;
	private PersonalTrainer personalTrainer;
	private String status;
	
	public Agendamento(LocalDate dataAgendamento, LocalTime horaAgendamento, Aluno aluno,
			PersonalTrainer personalTrainer, String status) {
		super();
		this.dataAgendamento = dataAgendamento;
		this.horaAgendamento = horaAgendamento;
		this.aluno = aluno;
		this.personalTrainer = personalTrainer;
		this.status = status;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public LocalTime getHoraAgendamento() {
		return horaAgendamento;
	}

	public void setHoraAgendamento(LocalTime horaAgendamento) {
		this.horaAgendamento = horaAgendamento;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public PersonalTrainer getPersonalTrainer() {
		return personalTrainer;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public static void soliciarAgendamento(String cpf) {
		Aluno aluno = null;
		PersonalTrainer personal = null;
		
		for(PersonalTrainer personalTrainer: PersonalTrainerDAO.exibePersonalTrainers()) {
    		int idPersonal = PersonalTrainerDAO.getPersonalID(personalTrainer);
    		System.out.println("ID: " + idPersonal + "\n" + personalTrainer);
    	}
		
		aluno = AlunoDAO.getAluno(cpf);
		personal = ValidacaoPersonal.verificaPersonalExiste();
		LocalDate dataAgendamento = ValidacaoAgendamento.validaDataAgendamento();
		LocalTime horarioAgendamento = ValidacaoAgendamento.validaHorario();
		
		AgendamentoDAO.solicitarAgendamento(personal, aluno, dataAgendamento, horarioAgendamento, "Aberto");
		System.out.println("Agendamento solicitado com Sucesso!");
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

	@Override
	public String toString() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return String.format("""
				Data do Agendamento: %s
				Hora Agendada: %s
				Nome do Aluno: %s
				Nome do Personal: %s
				Status: %s
				-----------------------------------
				""", df.format(dataAgendamento), horaAgendamento, aluno.getNome(), personalTrainer.getNome(), status);
	}
}
