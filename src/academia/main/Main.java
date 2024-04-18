package academia.main;

import academia.classes.Pessoa;
import academia.menus.Inicio;
import academia.menus.Menus;

public class Main {

	public static void main(String[] args) {
		
		int inicio = Inicio.menu();
		while(inicio != 2) {
			Pessoa usuario = Menus.login();
			switch(inicio) {
				case 1:
					if(usuario.getTipo().equalsIgnoreCase("aluno")) {
						System.out.println("Bem Vindo " + usuario.getNome() + "!\n");
						Menus.menuAluno();
					}else if (usuario.getTipo().equalsIgnoreCase("funcionario")){
						System.out.println("Bem Vindo " + usuario.getNome() + "!\n");
						Menus.menuFuncionario();
					}else{
						System.out.println("Bem Vindo " + usuario.getNome() + "!\n");
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