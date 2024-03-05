package com.davivienda.sv.challenge;

public class Main {

	public static void main(String[] args) {
		FileManager fm = new FileManager();
		
		System.out.println("Proyecto Challenge");
		System.out.println(fm.loadFile());
	}

}
