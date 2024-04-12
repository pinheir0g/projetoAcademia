package academia.menus;

import java.util.Scanner;

import academia.DAO.ValidacaoDAO;

public class Menus {
	
	public static String login() {
		String tipoUsuario = "";
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("Digite seu CPF: ");
			String cpf = sc.nextLine();
			
			System.out.println("Digite sua senha: ");
			String senha = sc.nextLine();
			
		
			tipoUsuario = ValidacaoDAO.conectaUsuario(cpf, senha);
			
			if (!tipoUsuario.equalsIgnoreCase("")) {
				System.out.println("Logado com sucesso!");
			}else {
				System.out.println("CPF ou senha inválidos!");
			}
			
		}while(tipoUsuario.equalsIgnoreCase(""));
		sc.close();
		return tipoUsuario;
	}
	
	public static void menuAluno() {
		
		 Scanner scanner = new Scanner(System.in);
                    		
         while (true) {
           
            int opcaoUsuario = Menu.leMenu("aluno");

            switch (opcaoUsuario) {
                case 1:
                    System.out.println("Dados Pessoais");
                    break;
                case 2:
                    System.out.println("Plano Cadastrado");
                    break;
                case 3:
                    System.out.println("Cancelar Agendamento.");
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
                case 0:
                    System.out.println("Opção inválida!");
	           }
         }
    }
	
	
	public static void menuPersonal() {
		
        Scanner scanner = new Scanner(System.in);

         while (true) {
        	
            int opcaoUsuario = Menu.leMenu("personal");
            
            switch (opcaoUsuario) {
                case 1:
                    System.out.println("Dados Pessoais");
                    break;
                case 2:
                    System.out.println("Plano Cadastrado");
                    break;
                case 3:
                    System.out.println("Cancelar Agendamento.");
                    break;
                default:
                    System.out.println("Opção inválida!");
	        }
        }
    }
	
	public static void menuFuncionario() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	
        	
        	int opcaoUsuario = Menu.leMenu("funcionario");

            switch (opcaoUsuario) {
                case 1:
                    System.out.println("Cadastrar novo Plano");
                    break;
                case 2:
                    System.out.println("Cadastrar Aluno");
                    break;
                case 3:
                    System.out.println("Cadastrar Novo Personal.");
                    break;
                case 4:
                    System.out.println("Emitir Relatorio de Planos");
                    break;
                case 5:
                    System.out.println("Emitir Relatorio de Alunos");
                    break;
                case 6:
                    System.out.println("Emitir Relatorio de Equipes");
                    break;   
                case 7:
                    System.out.println("Emitir Relatorio de Avaliacoes por periodo");
                    break;        
                default:
                    System.out.println("Opção inválida!");
	        }
        }
    }
	
}