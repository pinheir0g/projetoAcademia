package academia.classes;

import java.time.LocalDate;
import java.time.LocalTime;

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

	@Override
	public String toString() {
		return String.format("""
				Data do Agendamento: %s
				Hora Agendada: %s
				Nome do Aluno: %s
				Nome do Personal: %s
				Status: %s
				-----------------------------------
				""", dataAgendamento, horaAgendamento, aluno.getNome(), personalTrainer.getNome(), status);
	}
	
		

}
