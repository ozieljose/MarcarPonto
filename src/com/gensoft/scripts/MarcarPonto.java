package com.gensoft.scripts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.gensoft.domain.Marcacao;
import com.gensoft.util.Constantes;
import com.gensoft.util.SourceFile;

public class MarcarPonto {
	
	public static void main(String[] args) throws IOException {
		
		System.out.print("Marcar ponto para o dia de hoje?(1-Sim,2-Não) ");
		Scanner scanner = new Scanner(System.in);
		int decisao = scanner.nextInt();
		
		String dia = null;
		switch (decisao) {
			case 1: break;
			case 2: System.out.print("Digite o dia(d"+Constantes.DELIMITADOR_DATA+"m"+Constantes.DELIMITADOR_DATA+"yy): ");
				dia = scanner.next();
				break;
			default: System.out.println("Opção Inexistente"); System.exit(0);
		}
		
		System.out.print("Digite a hora(HH"+Constantes.DELIMITADOR_HORA+"MM): ");
		String horaStr = scanner.next();
		System.out.print("Digite um comentário: ");		
		String comment = new BufferedReader(new InputStreamReader(System.in)).readLine(); 
		
		System.out.println(decisao + " " + horaStr+" "+comment);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setLenient(false);
		
		StringTokenizer token = new StringTokenizer(horaStr, Constantes.DELIMITADOR_HORA);
		int hora = Integer.parseInt(token.nextToken());
		int minuto = Integer.parseInt(token.nextToken());
		System.out.println(hora + " " + minuto);
		
		if (dia != null) {
			
			DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
			dateFormat.setLenient(false);
			
			try {
				calendar.setTime(dateFormat.parse(dia));
			} catch (ParseException e) {
				System.err.println("Campo "+e.getMessage()+" inválido!");
				System.exit(0);
			}			
		}
		
		try{
			calendar.set(Calendar.HOUR_OF_DAY, hora);
			calendar.set(Calendar.MINUTE, minuto);
			System.out.println(calendar.getTime());
		}catch (IllegalArgumentException e) {
			System.err.println("Campo "+e.getMessage()+" inválido!");
			System.exit(0);
		}
		
		Marcacao marcacao = new Marcacao();
		marcacao.setData(calendar.getTime());
		marcacao.setComentario(comment);
		
		File appFile = SourceFile.iniciarArquivoRegistro();
		
		PrintWriter printWriter = new PrintWriter(new FileWriter(appFile, true));
				
		printWriter.append(marcacao.formatToWrite()+"\n");
		
		printWriter.flush();
		printWriter.close();
	}
}
