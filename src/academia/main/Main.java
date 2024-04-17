package academia.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import academia.menus.Inicio;
import academia.menus.Menus;

public class Main {

	public static void main(String[] args) throws SQLException {
		List<String> login = new ArrayList<>();
		
		
		int inicio = Inicio.menu();
		while(inicio != 2) {
			login.clear();
			login.addAll(Menus.login());
			switch(inicio) {
				case 1:
					if(login.get(0).equalsIgnoreCase("aluno")) {
						System.out.println("Bem Vindo " + login.get(1) + "!\n");
						Menus.menuAluno();
					}else if (login.get(0).equalsIgnoreCase("funcionario")){
						System.out.println("Bem Vindo " + login.get(1) + "!\n");
						Menus.menuFuncionario();
					}else{
						System.out.println("Bem Vindo " + login.get(1) + "!\n");
						Menus.menuPersonal();
					}
					inicio = Inicio.menu();
					break;
				case 2:
					break;
			}
		}
		System.out.println("Programa Encerrado!");
	}
}
