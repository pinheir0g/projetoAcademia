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
	
	public static void exibeAlunos() {
		for(Aluno aluno: AlunoDAO.exibirAlunos()) {
    		System.out.println(aluno);
    	}
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