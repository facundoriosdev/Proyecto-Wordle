package wordle;

import java.util.ArrayList;

import wordle.Palabra.EstadoLetra;
//Aca solo se maneja lo que es la "Partida", por lo que no deberia guardar muchas cosas mas alla de los colores e historial de intentos.
public class Partida {
	private Palabra palabraSecreta;
	private String[] historial;
	private ArrayList<EstadoLetra[]> colores;
	private int cantIntentos;
	private int maxIntentos;
	public Partida(String secreta, int nivel) {
		this.palabraSecreta= new Palabra(secreta);
		this.colores=new ArrayList<>();
		this.cantIntentos=0;
		this.maxIntentos=dificultad(nivel);
		this.historial= new String [maxIntentos];
	}
	private int dificultad(int nivel) {
		switch(nivel) {
		case 1:return 8;
		case 2: return 6;
		default: return 4;
		}
	}
	private boolean arriesgar(Palabra intento) {
		if(cantIntentos>maxIntentos) {
			historial[cantIntentos]= intento.getString();
			EstadoLetra[] resultado= this.palabraSecreta.compararPalabra(intento);
			
			colores.add(resultado);
			cantIntentos++;
			return (palabraSecreta.getString()).equals(intento.getString()); //ganó
		}
		return false;
	}
	public ArrayList<EstadoLetra[]> getColores(){
		return colores;
	}
}
