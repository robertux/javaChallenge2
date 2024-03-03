package com.davivienda.sv.challenge;

import com.davivienda.sv.challenge.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
		Pattern pattern = Pattern.compile("CHAPTER [IVXLCDM]+");
        Matcher matcher = pattern.matcher(fileContent);
        
        Set<String> uniqueCapitulo = new HashSet<>();
        while (matcher.find()) {
        	uniqueCapitulo.add(matcher.group());
        }
        
        return uniqueCapitulo.size();
	}
	
	/**
	 * @return Retorna la cantidad de veces que es mencionado le nombre de Dracula en el libro
	 */
	public int getDraculaMentionCount() {
		//TODO: Implementar
		 Pattern pattern = Pattern.compile("dracula", Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(fileContent);
	        int count = 0;
	        
	        while (matcher.find()) {
	            count++;
	        }
	        return count;
	}
	
	/**
	 * @return Retorna el capitulo con mas lineas de contenido en el libro
	 */
	public int getBiggestChapter() {
		String[] palabras = fileContent.split("\\s+");
	    
	    String CapMasLargo = ""; 
	    int maxLength = 0; 
	    
	    String CapActual = null; 
	    int currentLength = 0;
	    
	    for (String palabra : palabras) {
	        if (palabra.equalsIgnoreCase("chapter")) {
	            if (CapActual != null && currentLength > maxLength) { 
	            	CapMasLargo = CapActual;
	                maxLength = currentLength;
	            }
	            CapActual = ""; 
	            currentLength = 0;
	        } else if (CapActual != null) { 
	        	CapActual += palabra + " "; 
	            currentLength += palabra.length();
	        }
	    }
	    if (CapActual != null && currentLength > maxLength) {
	    	CapMasLargo = CapActual;
	    }
	    String PrimeraPalabra = CapMasLargo.substring(0, CapMasLargo.indexOf(" "));
	    
	    int Numerocapitulo = Utilidades.romanToInteger(PrimeraPalabra);
	   
	    return Numerocapitulo;
	}

	/**
	 * @return Retorna la lista de fechas en las cartas, diarios y memorandums mencionados en le libro, en formato String tal cual como aparecen en el libro. Ej. 30 October, 4 November, ... 
	 */
	public List<String> getLetterDates(String fileContent) {
		//TODO: Implementar
		List<String> fechas = new ArrayList<>();
        Pattern pattern = Pattern.compile(Utilidades.DATE_PATTERN);
        Matcher matcher = pattern.matcher(fileContent);
        
        while (matcher.find()) {
        	fechas.add(matcher.group());
        }
  
        return fechas;
	}
	
	/**
	 * @return Retorna la lista de remitentes de las cartas, diarios y memorandums mencoinados en el libro, en formato String tal cual como aparece en el libro. Ej. Jonathan Harker’s Journal, Dr. Van Helsing’s Memorandum, Dr. Seward’s Diary...
	 */
	public List<String> getLetterRecipients() {
		//TODO: Implementar
		List<String> recipients = new ArrayList<>();
		String regexLetter = "_Letter\\s*(.*?)\\._";
		String Letter = "Letter ";
		String regexMemorandum = "_(.*?)\\bMemorandum\\._";
		String Memorandum = " Memorandum";
		String regexDiary = "_(.*?)\\bDiary\\._";
		String Diary = " Diary";
		
        Pattern patternLetter = Pattern.compile(regexLetter);
        Pattern patternMemorandum = Pattern.compile(regexMemorandum);
        Pattern patternDiary = Pattern.compile(regexDiary);
        
        Matcher matcherLetter = patternLetter.matcher(fileContent);
        Matcher matcherMemorandum = patternMemorandum.matcher(fileContent);
        Matcher matcherDiary = patternDiary.matcher(fileContent);
        
        while (matcherLetter.find()) {
            recipients.add(Letter + matcherLetter.group(1));
        }
        while (matcherMemorandum.find()) {
            recipients.add(matcherMemorandum.group(1) + Memorandum);
        }
        while (matcherDiary.find()) {
            recipients.add(matcherDiary.group(1) + Diary);
        }
        
        return recipients;
    }
	
	
}
