package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		this.idioma= Idioma("lenguaje");
		cargarDiccionario();
		this.ranking = new ArrayList<>();
	}

	private void cargarDiccionario() {
		// TODO Auto-generated method stub
		
	}
	public void iniciarPartida(int dificultad) {
		if(diccionario.isEmpty()) {
			throw new IllegalStateException("Diccionario vacio");
		}
		Random rand= new Random();
		int indice=rand.nextInt(diccionario.size());
		String palabraElegida=diccionario.get(indice);
		
		this.partidaActual= new Partida (palabraElegida,dificultad, "pepe");
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
	public void guardarPuntaje() {
	        this.ranking.add(new ResultadoJuego(partidaActual.getJugador(), partidaActual.getTiempoFinalSegundos()));
	
		
	}
	
	public record ResultadoJuego(String nombreJugador, double tiempo) {}
	}

	