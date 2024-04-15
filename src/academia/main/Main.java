package academia.main;

import java.util.ArrayList;
import java.util.List;

import academia.menus.Inicio;
import academia.menus.Menus;

public class Main {

	public static void main(String[] args) {
		
		List<String> login = new ArrayList<>();
		
		int inicio = Inicio.menu();
		while(true) {
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
			if(inicio == 2) {
				System.out.println("Programa Encerrado.");
				break;
			}	
		}
	}
}
