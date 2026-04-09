package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUtil {
	
	
	// Leer Archivos txt
	public static List<String> leerArchivo(String nombreArchivo) {
	    List<String> lineas = new ArrayList<>();
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            lineas.add(linea);
	        }
	        br.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return lineas;
	}
	
	// Escribir Archivos txt
	public static void escribirArchivo(String nombreArchivo, String contenido) {
	    try {
	        FileWriter fw = new FileWriter(nombreArchivo, true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        PrintWriter salida = new PrintWriter(bw);
	        salida.println(contenido);
	        salida.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
