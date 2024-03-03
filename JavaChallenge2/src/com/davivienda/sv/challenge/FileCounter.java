package com.davivienda.sv.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileCounter {
	private String fileContent;
	
	public FileCounter(String content) {
		this.fileContent = content;
	}
	
	/**
	 * @return Retorna la cantidad de capitulos en el libro 
	 */
	public int getChapterCount() {
		//TODO: Implementar
		
		int Contador = 0;
		
		try {
			
			//Creo la expresión regular
			Pattern pattern = Pattern.compile("CHAPTER [IVX]+\\n", Pattern.CASE_INSENSITIVE);				
	        Matcher matcher = pattern.matcher(fileContent);
	        
	        while(matcher.find())
	        	Contador++; 
	        
	        return Contador;      	        
	        
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return -1;
		}       	
	}
	
	/**
	 * @return Retorna la cantidad de veces que es mencionado le nombre de Dracula en el libro
	 */
	public int getDraculaMentionCount() {
		//TODO: Implementar
		
		int Contador=0;
		
		try {
		
			//Creo la expresión regular
			Pattern pattern = Pattern.compile("Dracula", Pattern.CASE_INSENSITIVE);				
	        Matcher matcher = pattern.matcher(fileContent);
	        
	        while(matcher.find())
	        	Contador++; 
	        
	        return Contador;    
	        
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return -1;
		}       		
	}
	
	/**
	 * @return Retorna el capitulo con mas lineas de contenido en el libro
	 */
	public int getBiggestChapter() {
		//TODO: Implementar
		
		int CapituloMasLargo = 0;
		int LineasDelCapituloMasLargo = 0;
		
		int CapituloAnterior = 0;
		int CapituloActual= 0;
		int LineasCapituloActual = 0;
		
		String LineaActual = "";
		
		try {
			
			//Creo la expresión regular
			Pattern pattern = Pattern.compile("^CHAPTER [IVX]+$", Pattern.CASE_INSENSITIVE);				
	        Matcher matcher;
	        
	        //Separo el libro en lineas
	        String[] lineas = fileContent.split("\\n");       
	        
	        for(int i = 0; i < lineas.length; i++) {
	        	
	        	LineaActual = lineas[i].trim();
	        	
	        	matcher = pattern.matcher(LineaActual);
	        	
	        	if(matcher.find() || LineaActual.equals("THE END")) { 
	        		
	        		CapituloAnterior = CapituloActual;
	        		CapituloActual++;
	        		
	        		if(LineasCapituloActual > LineasDelCapituloMasLargo) {
        				LineasDelCapituloMasLargo = LineasCapituloActual;
        				CapituloMasLargo = CapituloAnterior;        				
        			}
	        		
	        		LineasCapituloActual = 0;	        		
	        		
	        		if(LineaActual.equals("THE END")) 
	        			break;
	        		
	        	}else {
	        		
	        		if(CapituloActual == 0) LineasCapituloActual = 0;
	        		else LineasCapituloActual++;
	        		
	        	}	        	
	        }	                
	        	        
	        return CapituloMasLargo;
	        
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return -1;
		}       				
	}

	/**
	 * @return Retorna la lista de fechas en las cartas, diarios y memorandums mencionados en le libro, en formato String tal cual como aparecen en el libro. Ej. 30 October, 4 November, ... 
	 */
	public List<String> getLetterDates() {
		//TODO: Implementar
		
		String FechaActual = "";
		
		try {
			
			//Lista de Fecha
			List<String> Fechas = new ArrayList<>();
			
			//Creo la expresión regular
			Pattern pattern = Pattern.compile("_([1-9]|[1-2][0-9]|3[0-1]) (January|February|March|April|May|June|July|August|September|October|November|December)", Pattern.CASE_INSENSITIVE);				
	        Matcher matcher = pattern.matcher(fileContent);
	        
	        while (matcher.find()) {
	        	FechaActual = matcher.group().toString().trim().substring(1); //Para quitar el _ al inicio
	        	
	        	if(!Fechas.contains(FechaActual))	        	
	        		Fechas.add(FechaActual); 
	        }
	        
	        return Fechas;			
	        
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		
	}
	
	/**
	 * @return Retorna la lista de remitentes de las cartas, diarios y memorandums mencoinados en el libro, en formato String tal cual como aparece en el libro. Ej. Jonathan Harker’s Journal, Dr. Van Helsing’s Memorandum, Dr. Seward’s Diary...
	 */
	public List<String> getLetterRecipients() {
		//TODO: Implementar
		
		String RemitenteActual;
		
		try {
			
			//Lista de Remitentes
			List<String> Remitentes = new ArrayList<>();
			
			//Creo la expresión regular
			Pattern pattern = Pattern.compile("_.+(Journal|Memorandum|Diary)\\._", Pattern.CASE_INSENSITIVE);				
	        Matcher matcher = pattern.matcher(fileContent);
	        
	        while (matcher.find()) {
	        	RemitenteActual = matcher.group().trim().substring(1,matcher.group().trim().length()-2); //Para quitar el _ al inicio y el ._ al final
	        	
	        	if(!Remitentes.contains(RemitenteActual) && !RemitenteActual.equals("Pasted in Mina Murray’s Journal"))
	        		Remitentes.add(RemitenteActual); 
	        }
	        
	        return Remitentes;			
	        
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}    			
	}
}
