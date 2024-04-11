package academia.classes;

<<<<<<< Updated upstream
public class Funcionario {
=======
import java.time.LocalDate;

public class Funcionario extends Pessoa {
	
	private String cargo;
	
	public Funcionario(String nome, String cpf, LocalDate dataNascimento, String contato, String senha, String cargo) {
		super(nome, cpf, dataNascimento, contato, senha);
		this.cargo = cargo;
			}
>>>>>>> Stashed changes

}
