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
	private static final String CONTENIDO_DESCARTAR = "Contents";
	private static final String CONTENIDO_FIN_DESCARTAR = "How these papers";
	private static final String CAPITULOS_DESCARTAR = "of knowledge of those";
	private static final String FIN_CAPITULOS_DESCARTAR = "This then was the Un-Dead";
	private static final String INICIO_CAPITULO = "CHAPTER";
	
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
				boolean continua = false;
				while(linea != null) {
					if(linea.contains(CONTENIDO_FIN_DESCARTAR)) break;//FIN
					if(linea.contains(CONTENIDO_DESCARTAR) || continua) {//INICIO
						continua = true;
						if(linea.contains(CONTENIDO_DESCARTAR)) linea = br.readLine();
						cadena += linea + "\n";
					}					
					linea = br.readLine();
				}
				//cadena = cadena.trim();//LIMPIA
				conteo = cadena.split("CHAPTER").length-1;//Cuenta las veces que partio el segmento tomado
				cadena = "";
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
				cadena = "";
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
				//INTERPRETACION ACTUAL
				//INCLUYE DEL CAPITULO EL NOMBRE, TITULO Y ULTIMAS LINEAS ANTES DEL SIGUIENTE CAPITULO
				//TAMBIEN ESPACIOS Y SALTOS DE LINEA DENTRO DE CADA CAPITULO, MENOS LOS SALTOS DE LOS EXTREMOS

				int veces = 0;
				String texto = "";
				boolean continua = false;
				List<Integer> lista = new ArrayList<Integer>();

				while(linea != null) {
					if((linea.contains(INICIO_CAPITULO) && veces == 1) || linea.contains(FIN_CAPITULOS_DESCARTAR)) {
						//GUARDAR EL DATO EN LISTA, REINCIAR VECES, CADENA Y NO SALIR,
						//ES DECIR CONTINUAR RECORRIENDO EL ARCHIVO PARA MÁS ENCONTRAR MAS CAPITULOS
						lista.add(texto.trim().length());					
						veces = 0;
						texto = "";
						if(linea.contains(FIN_CAPITULOS_DESCARTAR)) break;
					}//FIN
					if(linea.contains(CAPITULOS_DESCARTAR) || continua) {//INICIO
						continua = true;
						if(linea.contains(CAPITULOS_DESCARTAR)) linea = br.readLine();
						if(linea.contains(INICIO_CAPITULO) && veces == 0) {
							texto = linea + "\n";
							veces = 1;
						} else
							texto += linea + "\n";
					}
					linea = br.readLine();
				}
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

	/**Lista de fechas
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
				//METODO MEJORABLE
				boolean continua = false;
				String[] meses = { "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE",
						"JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" };
				while(linea != null) {
					if(linea.contains(FIN_CAPITULOS_DESCARTAR)) break;//FIN
					if(linea.contains(CAPITULOS_DESCARTAR) || continua) {//INICIO
						continua = true;
						if(linea.contains(CAPITULOS_DESCARTAR)) linea = br.readLine();
						for (String mes : meses) {
					        if (linea.toUpperCase().contains(mes)) {
					        	if(linea.contains("_") && linea.contains("._"))
									lista.add(linea.substring(linea.indexOf("_")+1, linea.lastIndexOf("_")-1));//.replace("_", "").replace(".", ""));
					        	//ACA MEJORAS
					            break;
					        }
					    }
					}
					linea = br.readLine();
				}
			}
			con.disconnect();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return lista;
	}
	
	/**Lista de remitentes
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
				boolean continua = false;
				while(linea != null) {
					if(linea.contains(CONTENIDO_FIN_DESCARTAR)) break;//FIN
					if(linea.contains(CONTENIDO_DESCARTAR) || continua) {//INICIO
						continua = true;
						if(linea.contains(CONTENIDO_DESCARTAR)) linea = br.readLine();
						if(linea.contains(". ") && linea.contains("’s"))
							lista.add(linea.substring(linea.indexOf(". ")+2, linea.lastIndexOf("’s")-2));//.replace(". ", "").replace("’s", ""));
						//ACA MEJORAS
					}					
					linea = br.readLine();
				}
			}
			con.disconnect();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return lista;
	}
}
