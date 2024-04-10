package academia.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa{
	private Plano planoContratado;
	private LocalDate dataMatricula;
	private List<Avaliacao> avaliacoesFisicas = new ArrayList<>();
	
	public Aluno(String nome, String cpf, LocalDate dataNascimento, String contato, String senha, Plano planoContratado, LocalDate dataMatricula) {
		super(nome, cpf, dataNascimento, contato, senha);
		this.planoContratado = planoContratado;
		this.dataMatricula = dataMatricula;
	}
}
