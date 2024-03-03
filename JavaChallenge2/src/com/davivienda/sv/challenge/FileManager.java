package com.davivienda.sv.challenge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileManager {
	public static final String FILE_URL = "https://robertux-words.s3.amazonaws.com/pg345.txt";
	public static final String METHOD="GET";
	
	/**
	 * Carga el texto contenido en el archivo ubicado en la URL en la constante FILE_URL
	 * @return El contenido del arcihvo cargado
	 */
	public String loadFile() {
		//TODO: Implementar un cliente HTTP para cargar en memoria el archivo ubicado en la URL
		try {
			URL url = new URL(FILE_URL);
			HttpURLConnection establecerConexion = (HttpURLConnection) url.openConnection();
			establecerConexion.setRequestMethod(METHOD);
			int codigoRespuesta = establecerConexion.getResponseCode();
			String respuesta = establecerConexion.getResponseMessage();
			System.out.println("Se realizo la peticion a: "
					.concat(FILE_URL).concat(", \n con el metodo ")
					.concat(METHOD)
					.concat(",\n obteniendo el codigo de respuesta: ")
					.concat(""+codigoRespuesta).concat(", \n con el mensaje: ")
					.concat(respuesta));
			 StringBuffer archivoLeido = new StringBuffer();
			if(codigoRespuesta==HttpURLConnection.HTTP_OK) {
				
				 BufferedReader in = new BufferedReader(new InputStreamReader(establecerConexion.getInputStream()));
	                String inputLine;
	                
	                while ((inputLine = in.readLine()) != null) {
	                	archivoLeido.append(inputLine + "\n");
	                }
	                in.close();

	                return archivoLeido.toString();
	            } else {
	                System.out.println("Se denego la peticion");
	            }
			  
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    
		
		 
		return null;
	}
}
