package com.davivienda.sv.challenge;

import java.net.URI;

public class FileManager {
	public static final String FILE_URL = "https://robertux-words.s3.amazonaws.com/pg345.txt";
	
	public static void main(String[] args) {
		String cadena = "";
		FileManager loadfile = new FileManager();
		cadena = loadfile.loadFile();
		//System.out.println("Archivo en blanco" + cadena);
		
		if(cadena != null && !cadena.isEmpty()) {		
			System.out.println("***Inicio de lectura****");
			//Leer Archivo
			FileCounter file = new FileCounter(cadena);	
			int numcap = file.getChapterCount();
			System.out.println("Cantidad de capitulos: " + numcap);
			System.out.println("Cantidad de veces que es mencionado el nombre de Dracula: " + file.getDraculaMentionCount());
			System.out.println("Capitulo con mas lineas: " + file.getBiggestChapter());
			System.out.println("Lista de fechas en las cartas, diarios y memorandums: " + file.getLetterDates());
			System.out.println("Lista de remitentes de las cartas, diarios y memorandums: " + file.getLetterRecipients(numcap));
			
			System.out.println("***Fin de lectura****");
		}
		else {
			System.out.println("Archivo en blanco");
		}
	}
	
	/**
	 * Carga el texto contenido en el archivo ubicado en la URL en la constante FILE_URL
	 * @return El contenido del arcihvo cargado
	 */
	public String loadFile() {
		//TODO: Implementar un cliente HTTP para cargar en memoria el archivo ubicado en la URL
		
		String salida = "";		
		
		try {
			java.net.http.HttpClient cliente = java.net.http.HttpClient.newHttpClient();
			java.net.http.HttpRequest sol = java.net.http.HttpRequest.newBuilder(URI.create(FILE_URL)).build();
			java.net.http.HttpResponse res = cliente.send(sol, java.net.http.HttpResponse.BodyHandlers.ofString());
			//System.out.println(res.body());			
			salida = (String) res.body();			
		}
		catch (Exception e){
			System.err.println("Error en la direccion Url: " +FILE_URL);
		}
				
		return salida;
	}
}
