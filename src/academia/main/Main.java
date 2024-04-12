package academia.main;

import java.time.LocalDate;

import academia.DAO.FuncionarioDAO;
import academia.classes.Funcionario;
import academia.menus.Menus;

public class Main {

	public static void main(String[] args) {
		System.out.println("------BEM VINDO A ACADEMIA JAVACT-------");
		String login = Menus.login();
		
		if(login.equalsIgnoreCase("aluno")) {
			Menus.menuAluno();
		}else if (login.equalsIgnoreCase("funcionario")){
			Menus.menuFuncionario();
		}else{
			Menus.menuPersonal();
		}
		//Funcionario funcionario1 = new Funcionario("Clebin", "12345678910", LocalDate.of(1990, 5, 30), "8888-8888", "123456", "Gerente");
		
		//FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		
	}
}
