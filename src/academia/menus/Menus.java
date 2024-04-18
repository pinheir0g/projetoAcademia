package academia.menus;

import java.util.Scanner;
import academia.DAO.AgendamentoDAO;
import academia.DAO.AlunoDAO;
import academia.DAO.AvaliacaoDAO;
import academia.DAO.FuncionarioDAO;
import academia.DAO.PersonalTrainerDAO;
import academia.DAO.PlanoDAO;
import academia.DAO.ValidacaoDAO;
import academia.classes.Agendamento;
import academia.classes.Aluno;
import academia.classes.ArquivoTxt;
import academia.classes.Avaliacao;
import academia.classes.Funcionario;
import academia.classes.PersonalTrainer;
import academia.classes.Pessoa;
import academia.classes.Plano;
import academia.services.ValidacaoPlano;

public class Menus {
	private static String cpf = "";
	
	public static Pessoa login() {
		Scanner sc = new Scanner(System.in);
		Pessoa usuario = null;
		do {
			System.out.println("Digite seu CPF: ");
			cpf = sc.nextLine();
			
			System.out.println("Digite sua senha: ");
			String senha = sc.nextLine();
			
			usuario = ValidacaoDAO.conectaUsuario(cpf, senha);
		
			if (usuario != null) {
				System.out.println("Logado com sucesso!\n");
			}else {
				System.out.println("CPF ou senha inválidos!");
			}
		}while(usuario == null);
		return usuario;
	}
	
	public static void menuAluno() {
		 Scanner scanner = new Scanner(System.in);
		 boolean validador;
		 int opcaoUsuario = 0;
		 
		 String menu = """
		 ---------------------------------------
		 	GERENCIAMENTO DE ALUNOS
		 ---------------------------------------
			  1 - Dados Pessoais e Plano Cadastrado 
			  2 - Solicitar agendamento com Personal
			  3 - Histórico de Agendamento
			  4 - Cancelar Agendamento
			  5 - Visualizar Avaliações Físicas
			  6 - Sair
			
			Escolha uma opção: 
			""";
		 
         while (true) {
             do {
            	 validador = true;

            	 System.out.println(menu);

            	 if(scanner.hasNextInt()) {
            		 opcaoUsuario = scanner.nextInt();
            		 scanner.nextLine();
            		 if(opcaoUsuario < 1 || opcaoUsuario > 6) {
            			 System.out.println("Opção inválida! Digite uma opção entre 1 e 6");
            			 validador = false;
            		 }
            	 }else {
            		 System.out.println("Opção inválida! Digite apenas números inteiros.");
            		 scanner.nextLine();
            	 }
             }while(!validador);

             switch (opcaoUsuario) {
                case 1:
                    System.out.println("---------------------------------------");
                    System.out.println("           DADOS DO ALUNO              ");
                    System.out.println("---------------------------------------");
                    System.out.println(AlunoDAO.getAluno(cpf));
                    break;
                case 2:
                	System.out.println("-----------------------------------");
                	System.out.println("      SOLICITAR AGENDAMENTO        ");
                	System.out.println("-----------------------------------");
                	Aluno.soliciarAgendamento(cpf);
                	break;
                case 3:
                	System.out.println("-----------------------------------");
                	System.out.println("     HISTÓRICO DE AGENDAMENTO      ");
                	System.out.println("-----------------------------------");
                	for(Agendamento agendamentos : AgendamentoDAO.hitoricoAgendamentos(cpf)) {
                		System.out.println(agendamentos);
                	}
                	break;
                case 4:
                	System.out.println("---------------------------------------");
               	 	System.out.println("          CANCELAR AGENDAMENTO         ");
                 	System.out.println("---------------------------------------");
                 	Aluno.cancelarAgendamento(cpf);
                    break;
                case 5:
                	System.out.println("---------------------------------------");
                	System.out.println("     VISUALIZAR AVALIAÇÃO FÍSICA       ");
                	System.out.println("---------------------------------------"); 
                	System.out.println(AvaliacaoDAO.exibirAvaliacao(cpf));
                    break;                    
                case 6:
                    break;
             }
             if(opcaoUsuario == 6) break;
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
             do {
            	 validador = true;
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
                	System.out.println("Visualizar Agenda.\n");
                	if(AgendamentoDAO.hitoricoAgendamentos(cpf).size() == 0) {
                		System.out.println("Nenhum agendamento cadastrado!");
                		break;
                	}
                	 for(Agendamento agendamentos : AgendamentoDAO.hitoricoAgendamentos(cpf)) {
                		System.out.println(agendamentos);
                	}
                    break;
                case 2:
                    System.out.println("Registrar Avaliação");
                    Avaliacao.cadastrarAvaliacao();
                    System.out.println("Avaliação registrada com Sucesso!");
                    break;
                case 3:
                	System.out.println("---------------------------------------");
                	System.out.println("     VISUALIZAR AVALIAÇÕES FÍSICAS  ");
                	System.out.println("---------------------------------------"); 
                	System.out.println(AvaliacaoDAO.exibirAvaliacao(cpf));
                    break;
                case 4:
                	break;
	        }
            if (opcaoUsuario == 4) break;
        }
    }
	
	public static void menuFuncionario(){
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
        while(true) {
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
	            	if(ValidacaoPlano.validaTotalDePlanos()) {
	            		Plano.cadastraPlano();
	            		System.out.println("Plano cadastrado com sucesso!");
	            	}
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
                	System.out.println("---------------------------------------");
                    System.out.println("	  RELATÓRIO DE PLANOS");
                    System.out.println("---------------------------------------");
                    for(Plano plano : PlanoDAO.exibirPlanos()) {
                    	System.out.println(plano);
                    	
                    }
                    break;
                case 5:
                	System.out.println("-----------------------------------");
                	System.out.println("	RELATÓRIO DE ALUNOS");
                	System.out.println("-----------------------------------");
                	for(Aluno aluno: AlunoDAO.exibirAlunos()) {
                		System.out.println(aluno);
                	}
                	System.out.println("Deseja gravar as informações em um arquivo? (S/N)");
                	String opcao = scanner.nextLine();
                	if(opcao.equalsIgnoreCase("s")) {
                		ArquivoTxt.listaAlunoTxt();
                	}
                    break;
                case 6:
                	System.out.println("-----------------------------------");
                	System.out.println("	RELATÓRIO DE EQUIPE");
                	System.out.println("-----------------------------------");
                	System.out.println("FUNCIONÁRIOS: \n");
                	for(Funcionario funcionario: FuncionarioDAO.exibeFuncionarios()) {
                		System.out.println(funcionario);
                	}
                	
                	System.out.println("PERSONAL TRAINERS: \n");
                	for(PersonalTrainer personal: PersonalTrainerDAO.exibePersonalTrainers()) {
                		System.out.println(personal);
                	}
                    break;
                case 7:
                    System.out.println("Relatorio de Avaliacoes por periodo");
                    System.out.println("--------------------------------------");
                    System.out.println("	RELATÓRIO DE AVALIAÇÕES");
                    System.out.println("--------------------------------------");
                    System.out.println(Avaliacao.exibirAvaliacoes());
                    break;        
                case 8:
                	break;
	        }
            if(opcaoUsuario == 8) break;
        }
    }
}