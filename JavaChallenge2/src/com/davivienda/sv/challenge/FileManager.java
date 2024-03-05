package com.davivienda.sv.challenge;

import com.davivienda.sv.utilidades.Utilidades;

public class FileManager {
	public static final String FILE_URL = "https://robertux-words.s3.amazonaws.com/pg345.txt";
	
	/**
	 * Carga el texto contenido en el archivo ubicado en la URL en la constante FILE_URL
	 * @return El contenido del arcihvo cargado
	 */
	public String loadFile() {
		//TODO: Implementar un cliente HTTP para cargar en memoria el archivo ubicado en la URL
		FileCounter fc = new FileCounter(FILE_URL);
		Utilidades u = new Utilidades();
		String res = "";
		res += "Metodo getChapterCount: " + String.valueOf(fc.getChapterCount()) + "\n";
		res += "Metodo getDraculaMentionCount: " + String.valueOf(fc.getDraculaMentionCount()) + "\n";
		res += "Metodo getBiggestChapter: " + String.valueOf(fc.getBiggestChapter()) + "\n";
		res += "Metodo getLetterDates: " + u.obtieneCadena(fc.getLetterDates()) + "\n";
		res += "Metodo getLetterRecipients: " + u.obtieneCadena(fc.getLetterRecipients());
		return res;
	}
}
