package com.gensoft.util;

import java.io.File;
import java.io.IOException;

public class SourceFile {
	
	public static File iniciarArquivoRegistro() throws IOException{
		String path = System.getProperty("user.home");
		String dirAppName = ".pontos";
		String appFileName = "marcacoes.txt";
		
		File dirUser = new File(path);
		
		File dirApp = new File(dirUser, dirAppName);
					
		if (!dirApp.exists()) {
			if (dirApp.mkdir()) {
				System.out.println("Diretório criado!");
			}
		}
		
		File appFile = new File(dirApp, appFileName);		
		if (!appFile.exists()) {
			if (appFile.createNewFile()) {
				System.out.println("Arquivo criado!");
			}
		}
		
		return appFile;
	}
	
	public static File iniciarArquivoPontoFacultativo() throws IOException{
		
		String path = System.getProperty("user.home");
		
		String dirAppName = ".pontos";
		String appFileName = "pontoFacultativo.txt";
		
		File dirUser = new File(path);
		
		File dirApp = new File(dirUser, dirAppName);
					
		if (!dirApp.exists()) {
			if (dirApp.mkdir()) {
				System.out.println("Diretório criado!");
			}
		}
		
		File appFile = new File(dirApp, appFileName);		
		if (!appFile.exists()) {
			if (appFile.createNewFile()) {
				System.out.println("Arquivo criado!");
			}
		}
		
		return appFile;
	}
}
