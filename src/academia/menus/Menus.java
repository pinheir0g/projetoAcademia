package academia.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import academia.DAO.ValidacaoDAO;
import academia.classes.Aluno;
import academia.classes.PersonalTrainer;
import academia.classes.Plano;

public class Menus {
	
	public static List<String> login() {
		
		List<String> dadosUsuario = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("Digite seu CPF: ");
			String cpf = sc.nextLine();
			
			System.out.println("Digite sua senha: ");
			String senha = sc.nextLine();
			
		
			dadosUsuario = ValidacaoDAO.conectaUsuario(cpf, senha);
		
			if (!dadosUsuario.isEmpty()) {
				System.out.println("Logado com sucesso!");
			}else {
				System.out.println("CPF ou senha inválidos!");
			}
			
		}while(dadosUsuario.isEmpty());
		return dadosUsuario;
	}
	
	public static void menuAluno() {
		
		 Scanner scanner = new Scanner(System.in);
		 boolean validador;
		 int opcaoUsuario = 0;
		 
		 String menu = """
		 ---------------------------------------
		 	GERENCIAMENTO DE ALUNOS
		 ---------------------------------------
			  1 - Dados Pessoais 
			  2 - Plano Cadastrado 
			  3 - Solicitar agendamento com Personal
			  4 - Histórico de Agendamento
			  5 - Cancelar Agendamento
			  6 - Visualizar Avaliações Físicas
			  7 - Sair
			
			Escolha uma opção: 
			""";
                    		
         while (true) {
           validador = true;
           do {
        	   System.out.println(menu);
        	   if(scanner.hasNextInt()) {
        		   opcaoUsuario = scanner.nextInt();
        		   scanner.nextLine();
        		 if(opcaoUsuario < 1 || opcaoUsuario > 7) {
        			 System.out.println("Opção inválida! Digite uma opção entre 1 e 7");
        			 validador = false;
        		 }
        	   }else {
        		   System.out.println("Opção inválida! Digite apenas números inteiros.");
        		   scanner.nextLine();
        	   }
        	   
           }while(!validador);

            switch (opcaoUsuario) {
                case 1:
                    System.out.println("Dados Pessoais");
                    break;
                case 2:
                    System.out.println("Plano Cadastrado");
                    break;
                case 3:
                    System.out.println("Solicitar Agendamento com Personal Trainer.");
                    break;
                case 4:
                    System.out.println("Histórico de Agendamento");
                    break;
                case 5:
                    System.out.println("Cancelar Agendamento");
                    break;
                case 6:
                    System.out.println("Visualizar Avaliação Física");
                    break;                    
                case 7:
                    scanner.close();
                    break;
	           }
           if(opcaoUsuario == 7) break;
         }
    }
	
	
	public static void menuPersonal() {
		
        Scanner scanner = new Scanner(System.in);
        boolean validador;
        int opcaoUsuario = 0;
        String menu = """
        ---------------------------------------
        	GERENCIAMENTO DE PERSONAL TRAINER
     	---------------------------------------
     		  1 - Visualizar Agenda 
     		  2 - Registrar Avaliações 
     		  3 - Visualizar as Avaliações Realizadas
     		  4 - Sair
     				
     		Escolha uma opção: 
     		""";
     	

         while (true) {
        	 
        	 validador = true;
             do {
          	   System.out.println(menu);
          	   if(scanner.hasNextInt()) {
          		   opcaoUsuario = scanner.nextInt();
          		   scanner.nextLine();
          		 if(opcaoUsuario < 1 || opcaoUsuario > 4) {
          			 System.out.println("Opção inválida! Digite uma opção entre 1 e 4");
          			 validador = false;
          		 }
          	   }else {
          		   System.out.println("Opção inválida! Digite apenas números.");
          		   scanner.nextLine();
          	   }
          	   
             }while(!validador);
        	
            switch (opcaoUsuario) {
                case 1:
                    System.out.println("Visualizar Agenda.");
                    break;
                case 2:
                    System.out.println("Registrar Avaliações.");
                    break;
                case 3:
                    System.out.println("Avaliações Realizadas.");
                    break;
                case 4:
                	scanner.close();
                	break;
	        }
            if (opcaoUsuario == 4) break;
        }
    }
	
	public static void menuFuncionario() {
        Scanner scanner = new Scanner(System.in);
        
        boolean validador;
        int opcaoUsuario = 0;
        String menu = 
        """
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
		  8 - Sair do Programa
				
		Escolha uma opção: 
	   		""";

        while (true) {
        	
            do {
            	validador = true;
            	System.out.println(menu);
            
            	if(scanner.hasNextInt()) {
         		   opcaoUsuario = scanner.nextInt();
         		   scanner.nextLine();
         		 if(opcaoUsuario < 1 || opcaoUsuario > 8) {
         			 System.out.println("Opção inválida! Digite uma opção entre 1 e 7");
         			 validador = false;
         		 }
         	   }else {
         		   System.out.println("Opção inválida! Digite apenas números.");
         		   scanner.nextLine();
         	   }
         	   
            }while(!validador);

            switch (opcaoUsuario) {
                case 1:
                    System.out.println("Cadastrar novo Plano\n");
                    Plano.cadastraPlano();
                    System.out.println("Plano cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.println("Cadastrar Aluno");
                    Aluno.cadastraAluno();
                    System.out.println("Aluno cadastrado com sucesso!");
                    break;
                case 3:
                    System.out.println("Cadastrar Novo Personal.");
                    PersonalTrainer.cadastraPersonal();
                    System.out.println("Personal Trainer cadastrado com sucesso!");
                    break;
                case 4:
                    System.out.println("Emitir Relatório de Planos");
                    break;
                case 5:
                    System.out.println("Emitir Relatório de Alunos");
                    break;
                case 6:
                    System.out.println("Emitir Relatório de Equipes");
                    break;   
                case 7:
                    System.out.println("Emitir Relatório de Avaliacoes por periodo");
                    break;        
                case 8:
                	scanner.close();
                	break;
	        }
            if(opcaoUsuario == 8) break;
        }
    }
	
}