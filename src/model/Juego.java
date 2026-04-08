package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Palabra.EstadoLetra;

//	 
// Clase que manejara la logica para que la clase GameScreen la utilze (seria la logica del juego)
//

//Esta clase orquesta todo el wordle, la idea es que aca tambien se guarde lo que es los puntajes y se pueda cambiar el idioma
public class Juego {
	private List <String> diccionario;
	private String idioma;
	private Partida partidaActual;
	private List <ResultadoJuego> ranking;
	
	public Juego() {
		this.diccionario=new ArrayList<>();
		cargarDiccionario();
		this.ranking = new ArrayList<>();
	}

	private void cargarDiccionario() {
		diccionario.add("CASAS");
	    diccionario.add("PERRO");
	    diccionario.add("GATOS");
	    diccionario.add("MUNDO");
	    diccionario.add("ARBOL");
		
	}
	public void iniciarPartida(int dificultad, String nombre) {
		if(diccionario.isEmpty()) {
			throw new IllegalStateException("Diccionario vacio");
		}
		Random rand= new Random();
		int indice=rand.nextInt(diccionario.size());
		String palabraElegida=diccionario.get(indice);
		
		this.partidaActual= new Partida (palabraElegida,dificultad, nombre);
	}
	private String Idioma(String idioma) {
		switch (idioma) {
		case "english": return "english";
		default:return "español";
		}
		}
	public void registrarIntento(String palabraIntento) {
		partidaActual.procesarIntento(palabraIntento);
		if(partidaActual.getGano()) {
			guardarPuntaje();
			}
	}
	public EstadoLetra[] pedirUltimosColores() {
		return partidaActual.coloresDeUltimoIntento();
		
	}
	public void guardarPuntaje() {
	        this.ranking.add(new ResultadoJuego(partidaActual.getJugador(), partidaActual.getTiempoFinalSegundos()));
	
		
	}
	
	public record ResultadoJuego(String nombreJugador, double tiempo) {}

	public EstadoLetra[] ObtenerUltimosColores() {
		
		return null;
	}

	public boolean partidaTerminada() {	
		return partidaActual.laPartidaTermino();
	}

	public boolean jugadorGano() {
		return partidaActual.getGano();
	}

	public String obtenerPalabraSecreta() {
		return partidaActual.pedirPalabraSecreta();
	}
	}

	