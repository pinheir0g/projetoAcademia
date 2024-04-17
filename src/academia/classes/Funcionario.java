
package academia.classes;

import java.time.LocalDate;

public class Funcionario extends Pessoa{
	private String cargo;
	
	public Funcionario(String nome, String cpf, LocalDate dataNascimento, String contato, String senha, String cargo) {
		super(nome, cpf, dataNascimento, contato, senha);
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("""
				Cargo: %s
				---------------------------------
				""", cargo);
	}
	
	
}


