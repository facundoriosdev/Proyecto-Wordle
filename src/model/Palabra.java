package model;
public class Palabra {
	private final int largo;
	private final String palabra;
	
	//Crea la palabra secreta para manejarla
	public Palabra (String palabraSecreta) {
		this.palabra = palabraSecreta.toLowerCase(); // en el txt estan todas en LowerCase
		this.largo = palabraSecreta.length();
	}
	
	// Compara el intento del usuario con la palabra secreta
	public EstadoLetra[] compararPalabra(String intentoPalabra) {
		EstadoLetra[] comparacion = new EstadoLetra[this.largo];
		for(int i = 0; i < this.largo; i++) {
			char letra = intentoPalabra.charAt(i);
			if(letra == this.palabra.charAt(i))
				comparacion[i] = EstadoLetra.VERDE;
			else if(this.palabra.contains(""+letra)) // "" + letra = castea el char a String
				comparacion[i] = EstadoLetra.AMARILLO;
			else
				comparacion[i] = EstadoLetra.GRIS;
		}
		return comparacion;
	}
	
	// Estados para cada letra
	public enum EstadoLetra{
		VERDE,
		AMARILLO,
		GRIS;
	}
}
