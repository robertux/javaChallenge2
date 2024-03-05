package com.davivienda.sv.challenge;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileCounter {
	private String fileContent;
	
	public FileCounter(String content) {
		this.fileContent = content;
	}
	
	/**Cuenta dentro de la porcion de contenido del libro
	 * @return Retorna la cantidad de capitulos en el libro 
	 */
	public int getChapterCount() {
		int conteo = 0;
		try {
			URL u = new URL(fileContent);
			HttpURLConnection con = (HttpURLConnection)u.openConnection();
			if(con.getResponseCode() == 200) {
				InputStream inputStream = con.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
				String linea = br.readLine();
				
				String cadena = "";
				int bandera=0;
				while(linea != null) {
					if(linea.contains("How these papers")) break;//FIN
					if(linea.contains("Contents") || bandera == 1) {//INICIO
						bandera = 1;
						if(linea.contains("Contents")) linea = br.readLine();
						cadena += linea + "\n";
					}					
					linea = br.readLine();
				}
				cadena = cadena.trim();//LIMPIA
				conteo = cadena.split("CHAPTER").length-1;//Cuenta las veces que partio el segmento tomado
			}
			con.disconnect();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return conteo;
	}
	
	/**Veces que aparece la palabra en el documento
	 * @return Retorna la cantidad de veces que es mencionado le nombre de Dracula en el libro
	 */
	public int getDraculaMentionCount() {
		int conteo = 0;
		try {
			URL u = new URL(fileContent);
			HttpURLConnection con = (HttpURLConnection)u.openConnection();
			if(con.getResponseCode() == 200) {
				InputStream inputStream = con.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
				String linea = br.readLine();
				
				String cadena = "";
				while(linea != null) {
					cadena += linea + "\n";
					linea = br.readLine();
				}
				conteo = cadena.toUpperCase().split("DRACULA").length-1;//Cuenta las veces que partio el segmento tomado
			}
			con.disconnect();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return conteo;
	}
	
	/**De la seccion de contenido obtiene el capitulo con mas caracteres
	 * @return Retorna el capitulo con mas lineas de contenido en el libro
	 */
	public int getBiggestChapter() {
		int mayor = 0;
		try {
			URL u = new URL(fileContent);
			HttpURLConnection con = (HttpURLConnection)u.openConnection();
			if(con.getResponseCode() == 200) {
				InputStream inputStream = con.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
				String linea = br.readLine();

				String CONTENIDO_BUSCAR = "of knowledge of those";
				String FIN_CAPITULOS = "This then was the Un-Dead";
				String cadena = "";
				//INTERPRETACION ACTUAL
				//INCLUYE DEL CAPITULO EL NOMBRE, TITULO Y ULTIMAS LINEAS ANTES DEL SIGUIENTE CAPITULO
				//TAMBIEN ESPACIOS Y SALTOS DE LINEA DENTRO DE CADA CAPITULO, MENOS LOS SALTOS DE LOS EXTREMOS

				int bandera = 0;
				int veces = 0;
				List<Integer> lista = new ArrayList<Integer>();

				while(linea != null) {
					if((linea.contains("CHAPTER") && veces == 1) || linea.contains(FIN_CAPITULOS)) {
						//GUARDAR EL DATO EN LISTA, REINCIAR VECES, CADENA Y NO SALIR,
						//ES DECIR CONTINUAR RECORRIENDO EL ARCHIVO PARA MÁS ENCONTRAR MAS CAPITULOS
						lista.add(cadena.trim().length());
						if(linea.contains(FIN_CAPITULOS))
							break;						
						veces = 0;
						cadena = "";
					}//FIN
					if(linea.contains(CONTENIDO_BUSCAR) || bandera == 1) {//INICIO
						bandera = 1;
						if(linea.contains(CONTENIDO_BUSCAR)) linea = br.readLine();
						if(linea.contains("CHAPTER") && veces != 1) {
							cadena = linea + "\n";
							veces++;
						} else
							cadena += linea + "\n";
					}
					linea = br.readLine();
				}
				cadena = cadena.trim();//LIMPIA
				for (Integer num : lista) {
					if(num > mayor) { 
						mayor = num;
					}
				}
			}
			con.disconnect();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return mayor;
	}

	/**
	 * @return Retorna la lista de fechas en las cartas, diarios y memorandums mencionados en le libro, en formato String tal cual como aparecen en el libro. Ej. 30 October, 4 November, ... 
	 */
	public List<String> getLetterDates() {
		List<String> lista = new ArrayList<String>();//Collections.emptyList();
		try {
			URL u = new URL(fileContent);
			HttpURLConnection con = (HttpURLConnection)u.openConnection();
			if(con.getResponseCode() == 200) {
				InputStream inputStream = con.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
				String linea = br.readLine();

				String CONTENIDO_BUSCAR = "of knowledge of those";
				String FIN_CAPITULOS = "This then was the Un-Dead";
				String cadena = "";
				//INTERPRETACION ACTUAL
				//ESTADO DEL METODO POR EL MOMENTO, MEJORABLE

				int bandera = 0;
				String[] meses = { "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE",
						"JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" };
				String cadenaFormato = "";

				while(linea != null) {
					cadenaFormato = linea.toUpperCase();
					for (String mes : meses) {
				        if (cadenaFormato.contains(mes)) {
				        	if(linea.contains("_") && linea.contains("._"))
								lista.add(linea.substring(linea.indexOf("_"), linea.lastIndexOf("_")).replace("_", "").replace(".", ""));
				            break;
				        }
				    }

					if(linea.contains(FIN_CAPITULOS))
						break;//FIN
					
					if(linea.contains(CONTENIDO_BUSCAR) || bandera == 1) {//INICIO
						bandera = 1;
						if(linea.contains(CONTENIDO_BUSCAR)) linea = br.readLine();
						if(linea.trim().equals("CHAPTER I")) {
							cadena = linea + "\n";
						} else
							cadena += linea + "\n";
					}

					linea = br.readLine();
				}
				//cadena = cadena.trim();//LIMPIA
			}
			con.disconnect();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return lista;
	}
	
	/**
	 * @return Retorna la lista de remitentes de las cartas, diarios y memorandums mencoinados en el libro, en formato String tal cual como aparece en el libro. Ej. Jonathan Harker’s Journal, Dr. Van Helsing’s Memorandum, Dr. Seward’s Diary...
	 */
	public List<String> getLetterRecipients() {
		List<String> lista = new ArrayList<String>();//Collections.emptyList();
		try {
			URL u = new URL(fileContent);
			HttpURLConnection con = (HttpURLConnection)u.openConnection();
			if(con.getResponseCode() == 200) {
				InputStream inputStream = con.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
				String linea = br.readLine();
				//METODO MEJORABLE
				String cadena = "";
				int bandera=0;
				while(linea != null) {
					if(linea.contains("How these papers")) break;//FIN
					if(linea.contains("Contents") || bandera == 1) {//INICIO
						bandera = 1;
						if(linea.contains("Contents")) linea = br.readLine();
						cadena += linea + "\n";
						if(linea.contains(". ") && linea.contains("’s"))
							lista.add(linea.substring(linea.indexOf(". "), linea.lastIndexOf("’s")).replace(". ", "").replace("’s", ""));
					}					
					linea = br.readLine();
				}
				cadena = cadena.trim();//LIMPIA
			}
			con.disconnect();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return lista;
	}
}
