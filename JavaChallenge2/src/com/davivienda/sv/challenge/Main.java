package com.davivienda.sv.challenge;

import java.util.Scanner;

public class Main {
	 public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
	     boolean continuar = true;
		FileManager fileManager = new FileManager();
		
		String fichero = fileManager.loadFile();
		int longitud = fichero.length(); 
		FileCounter fileCounter = new FileCounter(fichero);
		
		while (continuar) {
            System.out.println("Menú:");
            System.out.println("1. Opción 1 - Retornar la cantidad de capitulos en el libro");
            System.out.println("2. Opción 2 - Retornar la cantidad de veces que es mencionado le nombre de Dracula en el libro");
            System.out.println("3. Opción 3 - Retornar el capitulo con mas lineas de contenido en el libro");
            System.out.println("4. Opción 4 - Retornar la lista de fechas en las cartas, diarios y memorandums mencionados en le libro");
            System.out.println("5. Opcion 5 - Retorna la lista de remitentes de las cartas, diarios y memorandums mencoinados en el libro");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (opcion) {
                case 1:
                    System.out.println("Cantidad de capitulos en el libro: " + fileCounter.getChapterCount());
                    break;
                case 2:
                    System.out.println("Cantidad de veces que se menciona a Dracula: " + fileCounter.getDraculaMentionCount());
                    break;
                case 3:
                    System.out.println("El capitulo con mas lineas de contenido en el libro de Dracula: " + fileCounter.getBiggestChapter());
                    break;
                case 4:
                    System.out.println("Retorna la lista de fechas en las cartas, diarios y memorandums mencionados en le libro: " + fileCounter.getLetterDates(fichero));
                    break;
                case 5:
                    System.out.println("Retorna la lista de remitentes de las cartas, diarios y memorandums mencoinados en el libro: " + fileCounter.getLetterRecipients());
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Saliendo del menú. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
            if (opcion >= 1 && opcion <= 5) {
                System.out.print("Presione Enter para volver al menú o escriba 'salir' para terminar: ");
                String continuarStr = scanner.nextLine().toLowerCase();
                if (continuarStr.equals("salir")) {
                    continuar = false;
                    System.out.println("Terminando la ejecución. ¡Hasta luego!");
                }
            }
        }
        
		
	}
}
