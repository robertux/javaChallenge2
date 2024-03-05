package com.davivienda.sv.utilidades;

import java.util.List;
import java.util.stream.Collectors;

public class Utilidades {
	/**
	 * @return Retorna una lista en formato separado por comas 
	 */
	public String obtieneCadena(List<String> intList) {
	    String res = intList.stream()
	    		//.map(n -> String.valueOf(n))
	    		.collect(Collectors.joining(", "));
		return res;
	}
}
