package academia.classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import academia.DAO.AlunoDAO;

public class ArquivoTxt {

	public static void listaAlunoTxt() {
		
		List<Aluno> texto = AlunoDAO.exibirAlunos();

	    String nomeArquivo = "relatorioAlunos.txt";

	    try {
	        FileWriter escritor = new FileWriter(nomeArquivo);
	        BufferedWriter bufferEscrita = new BufferedWriter(escritor);
	        
	        for (Aluno aluno : texto) {
	        	bufferEscrita.append(aluno.toString());	
			}
	        
	    	bufferEscrita.flush();
			bufferEscrita.close();

	        System.out.println("As informações foram gravadas com sucesso no arquivo " + nomeArquivo);
	    } catch (IOException e) {
	        System.err.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
	    }
	}
}
