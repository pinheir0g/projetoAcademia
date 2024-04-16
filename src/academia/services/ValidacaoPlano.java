package academia.services;

import java.util.List;

import academia.DAO.PlanoDAO;
import academia.classes.Plano;

public class ValidacaoPlano {
	
	public static boolean validaNomePlano(String nomePlano) {
		int idPlano = PlanoDAO.getPlanoID(nomePlano);
		boolean verificado = true;
		if(idPlano != 0) {
			System.out.println("Plano já cadastrado! Digite um novo Plano.\n");
			verificado = false;
		}
		return verificado;
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