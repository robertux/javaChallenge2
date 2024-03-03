package com.davivienda.sv.challenge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileManager {
	public static final String FILE_URL = "https://robertux-words.s3.amazonaws.com/pg345.txt";
	
	/**
	 * Carga el texto contenido en el archivo ubicado en la URL en la constante FILE_URL
	 * @return El contenido del arcihvo cargado
	 */
	public String loadFile() {
		//TODO: Implementar un cliente HTTP para cargar en memoria el archivo ubicado en la URL
		
		try {
			
			// Crear objeto URL
            URL url = new URL(FILE_URL);

			//Abrir la conexión
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //Se configura la conexión con el metodo GET
            con.setRequestMethod("GET");

            //Recolectamos el libro entero
            BufferedReader BR = new BufferedReader(new InputStreamReader(con.getInputStream()));
            
            String Linea;
            StringBuilder SB = new StringBuilder();

            while ((Linea = BR.readLine()) != null) {
            	SB.append(Linea);
            	SB.append("\n");
            }
            BR.close();
            
            return SB.toString();
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
        	return null;
        }		
	}
}
