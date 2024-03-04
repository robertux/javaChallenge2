package com.davivienda.sv.challenge;

import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;

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
		String palabra = "CHAPTER", palabra1 = "", linea;
		int totalcap = 0;
		List<String> capi = new ArrayList<String>();
		try {			
			StringReader reader = new StringReader(this.fileContent);
			BufferedReader br = new BufferedReader(reader);	

			while(true){
				linea = br.readLine();
				if(linea!=null) {					
					if (linea.toLowerCase().contains(palabra.toLowerCase())) {
						capi.add(linea);
					}						
				}else{					
					break;
				}
			}
			br.close();
			reader.close();
			
			palabra1 = capi.get(0).substring(0,palabra.length() + 2);
			for(int i=1;i<capi.size();i++) {
				totalcap ++;
				if(capi.get(i).equalsIgnoreCase(palabra1)) {					
					break;
				}
			}
		
		}catch (Exception e){
			System.err.println("Error Obteniendo Capitulos ");
		}
		//System.out.println("totalcap: " + totalcap);
		return totalcap;
	}
	
	/**
	 * @return Retorna la cantidad de veces que es mencionado le nombre de Dracula en el libro
	 */
	public int getDraculaMentionCount() {
		//TODO: Implementar
		//toLowerCase
		String palabra = "DRACULA", linea;
		int totalnom = 0;
		try {			
			StringReader reader = new StringReader(this.fileContent);
			BufferedReader br = new BufferedReader(reader);			
			while(true){
				linea = br.readLine();
				if(linea!=null) {
					if (linea.toLowerCase().contains(palabra.toLowerCase())) {
						totalnom ++;
					}				
				}else{					
					break;
				}
			}
			br.close();
			reader.close();
		
		}catch (Exception e){
			System.err.println("Error Obteniendo Nombre DRACULA");
		}			
		//System.out.println("totalnom: " + totalnom);
		return totalnom;
	}
	
	/**
	 * @return Retorna el capitulo con mas lineas de contenido en el libro
	 */
	public int getBiggestChapter() {
		//TODO: Implementar
		String palabra = "CHAPTER", linea;
		List<Integer> capi = new ArrayList<Integer>();
		int  numlinea = 0,capitulo = 0,lineas = 0, totalcap = 0;
		try {			
			StringReader reader = new StringReader(this.fileContent);
			BufferedReader br = new BufferedReader(reader);			
			while(true){
				linea = br.readLine();
				if(linea!=null) {
					lineas ++;
					if (linea.toLowerCase().contains(palabra.toLowerCase())) {						
						capi.add(lineas);
						//System.out.println("capitulo["+totalcap+"]= numlinea: " + lineas);
						totalcap ++;
						lineas = 0;
					}				
				}else{					
					break;
				}
			}
			br.close();
			reader.close();
			
			for(int i=0;i<capi.size();i++) {				
				if(capi.get(i)>numlinea) {
					capitulo = i+1;
					numlinea = capi.get(i);
				}
			}
					
		}catch (Exception e){
			System.err.println("Error Obteniendo Capitulos ");
		}
		
		System.out.println("Capitulo con mas lineas: capitulo["+capitulo+"]= " + numlinea); 
		return capitulo;
	}

	/**
	 * @return Retorna la lista de fechas en las cartas, diarios y memorandums mencionados en le libro, en formato String tal cual como aparecen en el libro. Ej. 30 October, 4 November, ... 
	 */

	public List<String> getLetterDates() {
		//TODO: Implementar
		String linea;
		String[] meses = {"January","February","March","April","May","June","July","August","September","October", "November","December"};
		List<String> date = new ArrayList<String>();
		try {			
			StringReader reader = new StringReader(this.fileContent);
			BufferedReader br = new BufferedReader(reader);			
			while(true){
				linea = br.readLine();
				if(linea!=null) {					
					for(String mes: meses) {								
						if (linea.toLowerCase().contains(mes.toLowerCase())) {
							date.add(linea);
							//System.out.println("linea: " + linea);
						 }
					}
				}else{					
					break;
				}
			}
			br.close();
			reader.close();
					
		}catch (Exception e){
			System.err.println("Error Obteniendo Fechas ");
		}
		System.out.println("numero de fechas: " + date.size());	
		return date; 
	}
	
	
	/**
	 * @return Retorna la lista de remitentes de las cartas, diarios y memorandums mencoinados en el libro, en formato String tal cual como aparece en el libro. Ej. Jonathan Harker’s Journal, Dr. Van Helsing’s Memorandum, Dr. Seward’s Diary...
	 */
	public List<String> getLetterRecipients(int numcap) {
		//TODO: Implementar
		String palabra = "CHAPTER", separador = ".",linea;
		int totalcap = 0;
		List<String> capi = new ArrayList<String>();
		List<String> remitentes = new ArrayList<String>();
		try {			
			StringReader reader = new StringReader(this.fileContent);
			BufferedReader br = new BufferedReader(reader);	

			while(true){
				linea = br.readLine();
				if(linea!=null) {					
					if (linea.toLowerCase().contains(palabra.toLowerCase())) {
						totalcap ++;
						capi.add(linea);
						if (numcap == totalcap) {
							break;
						}
					}	
				}else{					
					break;
				}
			}
			br.close();
			reader.close();
			//Limpiar solo remitentes
			for(int i=0;i<capi.size();i++) {
				remitentes.add(capi.get(i).substring(capi.get(i).indexOf(separador)+2));
			}
			//Limpiar repetidos
			for(int i=0;i<remitentes.size();i++) {
				for(int j=0;j<remitentes.size()-1;j++) {
					if(i!=j) {
						if(remitentes.get(i).equals(remitentes.get(j))) {
							remitentes.set(j, "");
						}						
					}
				}				
			}
			//Remitentes sin repetir
			capi = new ArrayList<String>();
			for(int i=0;i<remitentes.size();i++) {				
				if(!remitentes.get(i).equals("")) {					
					capi.add(remitentes.get(i));
				}				
			}
		}catch (Exception e){
			System.err.println("Error Obteniendo Capitulos ");
		}
		System.out.println("numero de remitentes: " + capi.size());
		//System.out.println("remitentes: " + capi);
		return capi;
	}
}
