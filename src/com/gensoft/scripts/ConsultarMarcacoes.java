package com.gensoft.scripts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.gensoft.domain.DiaMarcacao;
import com.gensoft.domain.Marcacao;
import com.gensoft.util.SourceFile;

public class ConsultarMarcacoes {
	public static void main(String[] args) throws IOException {
		
		System.out.print("Digite a data inicial: ");
		String dataInicialStr = new Scanner(System.in).next();
		System.out.print("Digite a data fim: ");
		String dataFimStr = new Scanner(System.in).next();		
		
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
		
		Date dataIni = null;
		Date dataFim = null;
		
		try {
			dataIni = dateFormat.parse(dataInicialStr);
			
			dataFim = dateFormat.parse(dataFimStr);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dataFim);
			
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			
			dataFim = calendar.getTime();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(new FileReader(SourceFile.iniciarArquivoRegistro()));
			
		List<Marcacao> marcacoes = new ArrayList<Marcacao>();
		for(String line = reader.readLine(); line !=null; line = reader.readLine()){
			marcacoes.add(Marcacao.createFrom(line));
		}
		
		reader.close();
		
		
		List<DiaMarcacao> dias = new ArrayList<DiaMarcacao>();
		
		Collections.sort(marcacoes);
		for (Marcacao marcacao : marcacoes) {
			
			boolean adicionou = false;
			
			for (DiaMarcacao diaMarcacao : dias) {
				adicionou = diaMarcacao.addMarcacao(marcacao);
				if(adicionou)break;
			}
			
			if (!adicionou) {
				DiaMarcacao diaMarcacao = new DiaMarcacao();
				diaMarcacao.addMarcacao(marcacao);
				dias.add(diaMarcacao);				
			}
		}
		
		for (DiaMarcacao diaMarcacao : dias) {
			
//			System.out.printf("atual:%s<->ini:%s<->fim:%s\n", diaMarcacao.getDiaData(), dataIni, dataFim);
			
			if (diaMarcacao.getDiaData().getTime() >= dataIni.getTime() 
					&& diaMarcacao.getDiaData().getTime() <= dataFim.getTime()){
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(diaMarcacao.getDiaData());		
				
				System.out.print(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
				System.out.print("("+diaMarcacao.formatDiaData()+")-> ");
				for (Marcacao marcacao : diaMarcacao.getMarcacoes()) {
					System.out.print(marcacao.formatHora()+" ");
				}
				if (diaMarcacao.estaCompleto()) {
					System.out.println(" Completo");
				}else{
					System.out.println(" Incompleto");
				}
				
			}
			
//			System.out.println(diaMarcacao);
		}
	}
}
