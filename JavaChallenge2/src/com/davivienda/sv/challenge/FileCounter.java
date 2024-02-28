package com.davivienda.sv.challenge;

import java.util.Collections;
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
		return 0;
	}
	
	/**
	 * @return Retorna la cantidad de veces que es mencionado le nombre de Dracula en el libro
	 */
	public int getDraculaMentionCount() {
		//TODO: Implementar
		return 0;
	}
	
	/**
	 * @return Retorna el capitulo con mas lineas de contenido en el libro
	 */
	public int getBiggestChapter() {
		//TODO: Implementar
		return 0;
	}

	/**
	 * @return Retorna la lista de fechas en las cartas, diarios y memorandums mencionados en le libro, en formato String tal cual como aparecen en el libro. Ej. 30 October, 4 November, ... 
	 */
	public List<String> getLetterDates() {
		//TODO: Implementar
		return Collections.emptyList();
	}
	
	/**
	 * @return Retorna la lista de remitentes de las cartas, diarios y memorandums mencoinados en el libro, en formato String tal cual como aparece en el libro. Ej. Jonathan Harker’s Journal, Dr. Van Helsing’s Memorandum, Dr. Seward’s Diary...
	 */
	public List<String> getLetterRecipients() {
		//TODO: Implementar
		return Collections.emptyList();
	}
}
