package com.davivienda.sv.challenge;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		FileManager FM = new FileManager();		
		FileCounter FC = new FileCounter(FM.loadFile());
		
		System.out.println("El libro contiene " + FC.getChapterCount() + " Capitulos");
		System.out.println();
		System.out.println("Se menciona a Dracula " + FC.getDraculaMentionCount() + " veces en el libro");
		System.out.println();
		System.out.println("El capitulo con mas lineas es el " + FC.getBiggestChapter());
		System.out.println();
		
		List<String> Fechas =  FC.getLetterDates();
		System.out.println("Las fechas en las cartas, diarios y memorandums mencionados en el libro son: ");	
		
		for(int i = 0; i < Fechas.size() ; i++) {
			System.out.println(Fechas.get(i));
		}
		System.out.println();
		
		List<String> Remitentes =  FC.getLetterRecipients();
		System.out.println("La lista de remitentes de las cartas, diarios y memorandums mencionados en el libro son: ");
		
		for(int i = 0; i < Remitentes.size() ; i++) {
			System.out.println(Remitentes.get(i));
		}
		System.out.println();		
			
	}

}
