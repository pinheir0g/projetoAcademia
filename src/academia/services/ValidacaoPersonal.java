package academia.services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import academia.DAO.PersonalTrainerDAO;
import academia.classes.PersonalTrainer;

public class ValidacaoPersonal {
	static Scanner scanner = new Scanner(System.in);
	
	public static LocalTime validaHorario() {
		DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime horarioAtendimento;
		do {
			horarioAtendimento = null;
			System.out.println("Digite o horario de atendimento do Personal Trainer: (HH:mm)");
			String horario = scanner.nextLine();
			try {
				horarioAtendimento = LocalTime.parse(horario, tf);
			}catch(Exception e) {
				System.out.println("Formato de horario inválido. Use o formato (HH:mm).");
			}
		}while(horarioAtendimento == null);
		
		return horarioAtendimento;
	}
	
	public static String validaCref() {
		String cref = "";
		boolean validador;
		do {
			validador = true;
			System.out.println("Digite o CREF do Personal: ");
			cref = scanner.nextLine();
			
			Pattern pattern = Pattern.compile("^\\d{6}-[A-Z]/[A-Z]{2}$");
			Matcher matcher = pattern.matcher(cref);
			if(!matcher.matches()) {
				System.out.println("CREF inválido! Digite no formato (dddddd-Graduação/UF)");
				validador = false;
			}
		}while(!validador);
		
		return cref;
	}
	
	public static PersonalTrainer verificaPersonalExiste() {
		int id;
		PersonalTrainer personal = null;
		do {
			System.out.println("Digite o ID do Personal Trainer deseja agendar \n");
			if(scanner.hasNextInt()) {
				id = scanner.nextInt();
				scanner.nextLine();
				personal = PersonalTrainerDAO.getPersonalByID(id);
				if(personal != null) {
					return personal;
				}
				System.out.println("Erro: Personal Trainer não encontrado no sistema! Escolha um Personal existente.");
			}else {
				System.out.println("Digite apenas números.");
				scanner.nextLine();
			}
			
		}while(personal == null);
		
		return personal;
	}
}
