package academia.classes;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {
	private LocalDate dataAgendamento;
	private LocalTime horaAgendamento;
	private Aluno aluno;
	private PersonalTrainer personalTrainer;
	
	public Agendamento(LocalDate dataAgendamento, LocalTime horaAgendamento, Aluno aluno,
			PersonalTrainer personalTrainer) {
		super();
		this.dataAgendamento = dataAgendamento;
		this.horaAgendamento = horaAgendamento;
		this.aluno = aluno;
		this.personalTrainer = personalTrainer;
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

	@Override
	public String toString() {
		return "Data do Agendamento: " + dataAgendamento + "\nHora Agendada: " + horaAgendamento + " min" + "\nNome do aluno: " + aluno + "\nNome do Personal: " + personalTrainer;
	}
	
		

}
