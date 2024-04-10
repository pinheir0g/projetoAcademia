package academia.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa{
	private Plano planoContratado;
	private LocalDate dataMatricula;
	private List<Avaliacao> avaliacoesFisicas = new ArrayList<>();
	
	public Aluno(Plano plano, LocalDate dataMatricula) {
		this.plano = plano;
		this.dataMatricula = dataMatricula;
	}
}
