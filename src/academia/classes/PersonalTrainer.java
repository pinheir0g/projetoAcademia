package academia.classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import academia.DAO.PersonalTrainerDAO;
import academia.services.ValidacaoPersonal;
import academia.services.ValidacaoPessoa;

public class PersonalTrainer extends Pessoa {
	private String especialidade;
	private String cref;
	private LocalTime horarioAtendimento;
	
	
	public PersonalTrainer(String nome, String cpf, LocalDate dataNascimento, String contato, String senha,
			String especialidade, String cref, LocalTime horarioAtendimento, String tipo) {
		super(nome, cpf, dataNascimento, contato, senha, tipo);
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

	public static void cadastraPersonal() {
		Scanner scanner = new Scanner(System.in);
		
		String nomePersonal = ValidacaoPessoa.validaNome();
		
		String cpf = ValidacaoPessoa.validaCpf();
		LocalDate dataNascimento = ValidacaoPessoa.validaDataNascimento();
		
		System.out.println("Digite o contato do Personal Trainer: ");
		String contato = scanner.nextLine();
		
		System.out.println("Digite a senha do Personal Trainer: ");
		String senha = scanner.nextLine();
		
		System.out.println("Digite a especialidade do Personal Trainer");
		String especialidade = scanner.nextLine();
		
		String cref = ValidacaoPersonal.validaCref();
		LocalTime horarioAtendimento = ValidacaoPersonal.validaHorario();
		
		PersonalTrainer novoPersonal = new PersonalTrainer(nomePersonal, cpf, dataNascimento, contato, senha, especialidade, cref, horarioAtendimento, "Personal Trainer");
		PersonalTrainerDAO.cadastraPersonal(novoPersonal);
	}
		
	@Override
	public String toString() {
		return super.toString() + String.format("""
				Especialidade: %s
				CREF: %s
				Horário de Atendiamento: %s
				---------------------------------
				""", especialidade, cref, horarioAtendimento);
	}	
}
