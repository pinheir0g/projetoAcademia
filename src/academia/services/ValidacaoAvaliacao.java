package academia.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ValidacaoAvaliacao {
	static Scanner scanner = new Scanner(System.in);

	public static LocalDate validaDataAvaliacao() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataNascimento = null;
		do {
			dataNascimento = null;
			System.out.println("Digite a Data de Avaliacao: (no formato dd/MM/yyyy)");
			String data = scanner.nextLine();
			try {
				dataNascimento = LocalDate.parse(data, df);
			}catch(Exception e) {
				System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
			}
		}while(dataNascimento == null);
		
		return dataNascimento;
	}
	
	public static int validaPeriodoAvaliacao(String mensagem) {
		int mes = 0;
		do {
			System.out.println(mensagem);
			if(scanner.hasNextInt()) {
				mes = scanner.nextInt();
		    	if(mes < 1 || mes > 12) {
		    		System.out.println("Erro: Digite uma mês válido (1-12)");
		    	}
			}else {
				System.out.println("Erro: Digite apenas números!");
				scanner.next();
			}
		}while(mes < 1 || mes > 12);
		return mes;
	}
}
