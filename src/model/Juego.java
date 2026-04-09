package model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	private Partida partidaActual;
	private List <ResultadoJuego> ranking;
	
	public Juego(String idioma) {
		this.diccionario=new ArrayList<>();
		this.ranking = new ArrayList<>();
		cargarRankingDesdeArchivo();
	}

	public void iniciarPartida(int dificultad, String nombre, String idioma) {
		cargarDiccionario(idioma, obtenerLargoPorDificultad(dificultad));
		if(diccionario.isEmpty()) {
			throw new IllegalStateException("Diccionario vacio");
		}
		Random rand= new Random();
		int indice=rand.nextInt(diccionario.size());
		String palabraElegida=diccionario.get(indice);
		
		this.partidaActual= new Partida (palabraElegida,dificultad, nombre);
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
		 ResultadoJuego puntaje= new ResultadoJuego(partidaActual.getJugador(), partidaActual.getTiempoFinalSegundos());
	        this.ranking.add(new ResultadoJuego(partidaActual.getJugador(), partidaActual.getTiempoFinalSegundos()));
	        guardarRecordEnArchivo(puntaje);
		
	}
	private void cargarDiccionario(String idioma, int dificultad) {
	    diccionario.clear(); 
	    int largoBuscado = dificultad;
	    
	   
	    String rutaArchivo = idioma.equals("ENGLISH") 
	        ? "src/material/palabrasEnglish.txt" 
	        : "src/material/palabrasEspañol.txt";

	    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String palabraLimpiada = linea.trim().toUpperCase();
	            
	          
	            if (palabraLimpiada.length() == largoBuscado) {
	                diccionario.add(palabraLimpiada);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo de palabras: " + e.getMessage());   
	    }

	    if (diccionario.isEmpty()) {
	        throw new IllegalStateException("No se encontraron palabras de " + largoBuscado + " letras en el idioma " + idioma);
	    }
	}
	
	private void cargarRankingDesdeArchivo() {
	//lee el archivo de ranking
	    try (BufferedReader br = new BufferedReader(new FileReader("ranking.txt"))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            
	            String[] partes = linea.split(","); 
	            if (partes.length == 2) {
	                String nombre = partes[0];
	                double tiempo = Double.parseDouble(partes[1]);
	                
	                this.ranking.add(new ResultadoJuego(nombre, tiempo));
	                
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("No se encontró ranking previo. Se creará uno nuevo al ganar.");
	    }
	}
	private void guardarRecordEnArchivo(ResultadoJuego nuevoRecord) {
	    try (FileWriter fw = new FileWriter("ranking.txt", true);
	         BufferedWriter bw = new BufferedWriter(fw);
	         PrintWriter out = new PrintWriter(bw)) {
	         
	         
	         out.println(nuevoRecord.nombreJugador() + "," + nuevoRecord.tiempo());
	         
	    } catch (IOException e) {
	        System.out.println("Error al guardar el archivo de ranking.");
	    }
	}
	private int obtenerLargoPorDificultad(int dificultad) {
	    switch (dificultad) {
	        case 1: return 4; // FACIL: 4 letras
	        case 2: return 5; // MEDIO: 6 letras
	        default: return 6; // DIFICIL: 7 letras
	    }
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
	public int consultarlargodepalabra() {
		return partidaActual.getLargodepalabra();
	}
	public List<ResultadoJuego> getRanking() {
		return ranking;
	}
	
	
	}

	