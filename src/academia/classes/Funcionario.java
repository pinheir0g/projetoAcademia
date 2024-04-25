
package academia.classes;

import java.time.LocalDate;

import academia.DAO.FuncionarioDAO;

public class Funcionario extends Pessoa{
	private String cargo;
	
	public Funcionario(String nome, String cpf, LocalDate dataNascimento, String contato, String senha, String cargo, String tipo) {
		super(nome, cpf, dataNascimento, contato, senha, tipo);
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public static void exibeFuncionarios() {
		for(Funcionario funcionario: FuncionarioDAO.exibeFuncionarios()) {
    		System.out.println(funcionario);
    	}
	}
	@Override
	public String toString() {
		return super.toString() + String.format("""
				Cargo: %s
				---------------------------------
				""", cargo);
	}
	
	
}


