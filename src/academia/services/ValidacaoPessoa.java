package academia.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import academia.db.Conexao;

public class ValidacaoPessoa {
	static PreparedStatement ps = null;
	static Scanner scanner = new Scanner(System.in);
	
	public static String validaNome() {
		String nome = "";
		boolean validador;
        do {
        	validador = true;
            System.out.println("Digite o nome: ");
            nome = scanner.nextLine();

            Pattern pattern = Pattern.compile("^[a-zA-ZÀ-ÿ]+(\\s+[a-zA-ZÀ-ÿ]+)*$");
            Matcher matcher = pattern.matcher(nome);

            if (!matcher.matches()) {
            	System.out.println("Nome inválido. Use apenas letras e espaços.");
            	validador = false;
            }
        }while(!validador);
        return nome;
    }
	
	public static String validaCpf() {
		String cpfFormatado;
		boolean validador;
	
		do {
			validador = true;
			System.out.println("Digite o CPF: ");
			String cpf = scanner.nextLine();
			cpfFormatado = cpf.replaceAll("[.-]", "");
			Pattern pattern = Pattern.compile("\\d{11}$");
			Matcher matcher = pattern.matcher(cpfFormatado);
			if(!matcher.matches()) {
				System.out.println("CPF inválido! Digite o CPF Novamente!");
				validador = false;
			}if(!validaCpfExistente(cpfFormatado)) {
				System.out.println("CPF já cadastrado no Sistema!");
				validador = false;
			}
			
		}while(!validador);
		
		return cpfFormatado;
	}
	
	public static boolean validaCpfExistente(String cpfPessoa) {
		String sql = "SELECT cpf from Pessoa WHERE cpf = ?";
		try {
			ps = Conexao.conectar().prepareStatement(sql);
			ps.setString(1, cpfPessoa);
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					return false;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static LocalDate validaDataNascimento() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataNascimento = null;
		do {
			dataNascimento = null;
			System.out.println("Digite a Data de Nascimento: (no formato dd/MM/yyyy)");
			String data = scanner.nextLine();
			try {
				dataNascimento = LocalDate.parse(data, df);
			}catch(Exception e) {
				System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
			}
		}while(dataNascimento == null);
		
		return dataNascimento;
	}
	
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
}