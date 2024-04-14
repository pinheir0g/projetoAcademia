package academia.main;

import java.util.ArrayList;
import java.util.List;

import academia.menus.Menus;

public class Main {

	public static void main(String[] args) {
		System.out.println(
				"----------------------------------\n"
				+ "    BEM VINDO A ACADEMIA JAVACT"
				+ "\n----------------------------------");

		List<String> login = new ArrayList<>();
		
		login.addAll(Menus.login());
		
		if(login.get(0).equalsIgnoreCase("aluno")) {
			System.out.println("BEM VINDO " + login.get(1));
			Menus.menuAluno();
		}else if (login.get(0).equalsIgnoreCase("funcionario")){
			System.out.println("BEM VINDO " + login.get(1));
			Menus.menuFuncionario();
		}else{
			Menus.menuPersonal();
		}
		
		//Funcionario funcionario1 = new Funcionario("Matheus Augusto", "12345678910", LocalDate.of(1990, 5, 30), "98799-5467", "123456", "Gerente");
		//FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		//funcionarioDAO.cadastrar(funcionario1);
		
	}
}
