package academia.classes;

import java.time.LocalDate;
import java.time.LocalTime;

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
		
	@Override
	public String toString() {
		return super.toString() + String.format("""
				Especialidade: %s
				CREF: %s
				Horário de Atendiamento: %s
				""", especialidade, cref, horarioAtendimento);
	}	
	
}
