package model;

import org.junit.jupiter.api.Test;

import model.Palabra.EstadoLetra;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//algunos tests para comprobrar el funcionamiento correcto de los metodos.
//seria bastante util si ponen mas test para corroborar ciertos casos.
public class TestPalabra {
	@Test
	@DisplayName("constructor con la palabra correcta")
	void testCorrecta() {
		Palabra palabraSecreta = new Palabra("12345");
		String palabraIntento = "12345";
		EstadoLetra[] estadoCorrecto= {EstadoLetra.VERDE,EstadoLetra.VERDE,EstadoLetra.VERDE, EstadoLetra.VERDE, EstadoLetra.VERDE };
		assertArrayEquals(palabraSecreta.compararPalabra(palabraIntento), estadoCorrecto);
		
	}
	@Test
	@DisplayName("Constructor con palabra incorrecta")
	void testDosCorrecta() {
		Palabra palabraSecreta= new Palabra("12345");
		String palabraIntento = "abcde";
		EstadoLetra[] estadoCorrecto= {EstadoLetra.GRIS, EstadoLetra.GRIS,EstadoLetra.GRIS,EstadoLetra.GRIS,EstadoLetra.GRIS};
		assertArrayEquals(palabraSecreta.compararPalabra(palabraIntento),estadoCorrecto);
		
	}
	@Test
	@DisplayName ("Constructor con palabra incorrecta, pero con letras correctas")
	void testparcial () {
		Palabra palabraSecreta = new Palabra ("12345");
		String palabraIntento= "a2c4d";
		EstadoLetra[] estadoCorrecto= {EstadoLetra.GRIS,EstadoLetra.VERDE,EstadoLetra.GRIS,EstadoLetra.VERDE,EstadoLetra.GRIS};
		assertArrayEquals(palabraSecreta.compararPalabra(palabraIntento),estadoCorrecto);

	}
	@Test
	@DisplayName("Constructor con palabra incorrecta, pero con algunas letras contenidas")
	void testLetrasContenidas() {
		Palabra palabraSecreta = new Palabra ("12845");
		String palabraIntento= "54721";
		EstadoLetra[] estadoCorrecto= {EstadoLetra.AMARILLO,EstadoLetra.AMARILLO,EstadoLetra.GRIS,EstadoLetra.AMARILLO,EstadoLetra.AMARILLO};
		assertArrayEquals(palabraSecreta.compararPalabra(palabraIntento),estadoCorrecto);
	}

}