package academia.menus;

import java.util.Scanner;

public class Inicio {
	
	public static int menu() {
		Scanner scanner = new Scanner(System.in);
		int opcaoUsuario = 0;
		boolean validador;
		
		do{
			validador = true;
			System.out.println(
					"----------------------------------\n"
					+ "    BEM VINDO A ACADEMIA JAVACT"
					+ "\n----------------------------------");
			System.out.println("1 - Login");
			System.out.println("2 - Sair");
			if(scanner.hasNextInt()) {
				opcaoUsuario = scanner.nextInt();
				scanner.nextLine();
				if(opcaoUsuario < 1 || opcaoUsuario > 2) {
					System.out.println("Opção Inválida! Digite uma opção entre 1 e 2");
					validador = false;
				}
			}else {
				System.out.println("Opção inválida! Digite apenas números!");
			}
			
		}while(!validador);
		return opcaoUsuario;
	}
}
