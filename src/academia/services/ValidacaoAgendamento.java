package academia.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ValidacaoAgendamento {
	static Scanner scanner = new Scanner(System.in);
	
	public static LocalDate validaDataAgendamento() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataNascimento = null;
		do {
			dataNascimento = null;
			System.out.println("Digite a Data do Agendamento: (no formato dd/MM/yyyy)");
			String data = scanner.nextLine();
			try {
				dataNascimento = LocalDate.parse(data, df);
			}catch(Exception e) {
				System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
			}
		}while(dataNascimento == null);
		
		return dataNascimento;
	}
	
	public static LocalTime validaHorario() {
		DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime horarioAtendimento;
		do {
			horarioAtendimento = null;
			System.out.println("Digite o horario do Agendamento: (HH:mm)");
			String horario = scanner.nextLine();
			try {
				horarioAtendimento = LocalTime.parse(horario, tf);
			}catch(Exception e) {
				System.out.println("Formato de horario inválido. Use o formato (HH:mm).");
			}
		}while(horarioAtendimento == null);
		
		return horarioAtendimento;
	}
}
