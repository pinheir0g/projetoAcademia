package academia.services;

import java.util.List;
import java.util.Scanner;

import academia.DAO.PlanoDAO;
import academia.classes.Plano;

public class ValidacaoPlano {
	
	public static String validaNomePlano() {
		Scanner scanner = new Scanner(System.in);
		boolean verificado;
		String nomePlano;
		
		do {
			verificado = true;
        	System.out.println("Digite o nome do Plano: ");
            nomePlano = scanner.nextLine();
            int idPlano = PlanoDAO.getPlanoID(nomePlano);
			if(idPlano != 0) {
				System.out.println("Plano já cadastrado! Digite um novo Plano.\n");
				verificado = false;
			}
		}while(!verificado);
		
		return nomePlano;
	}
	
	public static boolean validaTotalDePlanos() {
		List<Plano> planos = PlanoDAO.exibirPlanos();
		boolean verificado = true;
		if(planos.size() >= 4) {
			System.out.println("Limite de Planos cadastrados alcançado!");
			System.out.println("Não é possivel cadastrar mais Planos.");
			verificado = false;
		}
		return verificado;
	}
}