package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RepositorioPalabra {
	
	// Obtener las palabras del archivo txt
	public static String obtenerPalabraRandom(String archivo, int largo) {
		List<String> palabras = ArchivoUtil.leerArchivo(archivo);
		List<String> filtrado = filtrarLargo(palabras, largo);
		Random r = new Random();
		return filtrado.get(r.nextInt(filtrado.size())); // Eligo una palabra random, de todas las filtradas
	}
	
	// Filtrar las palabras y quedarse con las de x largo
	private static List<String> filtrarLargo(List<String> palabras, int largo){
		List<String> filtrado = new ArrayList<>();
		for(String p: palabras)
			if(p.length() == largo)
				filtrado.add(p);
		return filtrado;
	}
}
