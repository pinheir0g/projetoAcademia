package academia.classes;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {	
	
	public static String exibeAluno() {
		return """
		---------------------------------------
			    GERENCIAMENTO DE ALUNOS
		---------------------------------------
		  1 - Dados Pessoais 
		  2 - Plano Cadastrado 
		  3 - Solicitar agendamento com Personal
		  4 - Histórico de Agendamento
		  5 - Cancelar Agendamento
		  6 - Visualizar Avaliações Físicas
		
		Escolha uma opção: 
		""";
	}
	
	public static String exibePersonal() {
		return """
		---------------------------------------
		   GERENCIAMENTO DE PERSONAL TRAINER
		---------------------------------------
		  1 - Visualizar Agenda 
		  2 - Registrar Avaliações 
		  3 - Visualizar as Avaliações Realizadas
				
		Escolha uma opção: 
		""";
	}
	
	public static String exibeFuncionario() {
		return """
		---------------------------------------
		     GERENCIAMENTO DE FUNCIONÁRIOS
		---------------------------------------
		  1 - Cadastrar Novo Plano
		  2 - Cadastrar Novo Aluno
		  3 - Cadastrar Novo Personal 
		  4 - Emitir Relatório de Planos
		  5 - Emitir Relatório de Alunos
		  6 - Emitir Relatório de Equipes
		  7 - Emitir Relatório de Avaliações por Período
				
		Escolha uma opção: 
		""";
	}
	
	
	public static int leMenu(String opcaoMenu) {
		Scanner  s = new Scanner(System.in);
		
		int opcao = 0;
		
		boolean validador = true;
		
		do {			
			if(opcaoMenu.equalsIgnoreCase("aluno")) {
			System.out.println(Menu.exibeAluno());
			 if (s.hasNextInt()) {
				 opcao = s.nextInt();
			 } else {
				 opcao = -1;
			 }
			
			if(opcao < 1 || opcao > 6 ) {
				System.out.println("Opção inválida!");
				validador = false;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
			} else if (opcaoMenu.equalsIgnoreCase("funcionario")) {
			System.out.println(Menu.exibeFuncionario());
			 if (s.hasNextInt()) {
				 opcao = s.nextInt();
			 } else {
				 opcao = -1;
			 }			
			if(opcao < 1 || opcao > 7 ) {
				System.out.println("Opção inválida!");
				validador = false;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
			
			} else if (opcaoMenu.equalsIgnoreCase("personal")) {
				System.out.println(Menu.exibePersonal());
				 if (s.hasNextInt()) {
					 opcao = s.nextInt();
				 } else {
					 opcao = -1;
				 }
				
				if(opcao < 1 || opcao > 3 ) {
					System.out.println("Opção inválida!");
					validador = false;
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			return opcao;
		}while(!validador);	
	}
	
}