package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Utilidades {
	static Scanner input = new Scanner(System.in);

	public static void muestraMenu() {

		System.out.println("""
				1.Listar categorías
				2.Listar artículos
				3.Insertar artículo
				4.Modificar artículo
				5.Insertar categoría
				6.Insertar artículo en una categoría
				7.Buscar artículo
				8.salir
				""");

	}

	public static int solicitaId() {
		int id = 0;
		try {
			id = input.nextInt();
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("no has introducido un número");
			input.nextLine();
		}

		return id;
	}

	public static float solicitaFloat() {
	    float precio=0;
	    boolean entradaValida = false;
	    do {
	        try {
	            precio = Float.parseFloat(input.nextLine());
	            entradaValida = true;
	        } catch (NumberFormatException e) {
	            System.out.println("Error: Ingresa un número válido.");
	        }
	    } while (!entradaValida);
	    return precio;
	}


	public static String solicitaString() {
		String descripcion = input.nextLine();
		return descripcion;
	}

	

	public static Date solicitarFecha() {

		boolean fechaValida = false;
		Date fecha = null;
		do {
			System.out.println("Introduce la fecha (YYYY-MM-DD):");
			String fechaStr = input.nextLine();
			try {
				fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
				fechaValida = true;
			} catch (ParseException e) {
				System.out.println(
						"Error al parsear la fecha. Asegúrate de que esté en el formato correcto (YYYY-MM-DD).");
			}
		} while (!fechaValida);
		return fecha;
	}

}
