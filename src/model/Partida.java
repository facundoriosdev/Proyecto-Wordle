package model;

import java.util.ArrayList;

import model.Palabra.EstadoLetra;
//Aca solo se maneja lo que es la "Partida", por lo que no deberia guardar muchas cosas mas alla de los colores e historial de intentos.
public class Partida {
	private Palabra palabraSecreta;
	private String[] historial;
	private String jugador;
	private ArrayList<EstadoLetra[]> colores;
	private int cantIntentos;
	private int maxIntentos;
	private long tiempoInicio;
	private long tiempoFin;
	private boolean gano;
	public Partida(String secreta, int nivel, String jugador) {
		this.palabraSecreta= new Palabra(secreta);
		this.jugador=jugador;
		this.colores=new ArrayList<>();
		this.cantIntentos=0;
		this.maxIntentos=dificultad(nivel);
		this.historial= new String [maxIntentos];
		this.tiempoInicio = System.currentTimeMillis();
		this.gano=false;
	}
	private int dificultad(int nivel) {
		switch(nivel) {
		case 1:return 8;
		case 2: return 6;
		default: return 4;
		}
	}
	public void procesarIntento(String intentoStr) {
        if (cantIntentos < maxIntentos && !gano) {
            Palabra intento = new Palabra(intentoStr);
            EstadoLetra[] resultado = this.palabraSecreta.compararPalabra(intento);
            colores.add(resultado);
            historial[cantIntentos] = intentoStr; 
            if (palabraSecreta.getString().equals(intentoStr)) {
                this.gano = true;
                this.tiempoFin= System.currentTimeMillis();   
            }
            
            cantIntentos++;
        }
        if(!gano && cantIntentos== maxIntentos) {
        	this.tiempoFin = System.currentTimeMillis();
        }
    }

	public ArrayList<EstadoLetra[]> getColores(){
		return colores;
	}
	public EstadoLetra[] coloresDeUltimoIntento(){
		return colores.get(colores.size()-1);
		
	}
	public boolean getGano() {
		return gano; 
	}
	public double getTiempoFinalSegundos() {
		return(tiempoFin-tiempoInicio)/1000;
	}
	public String getJugador() {
		return jugador;
	}
	public boolean laPartidaTermino() {
        return gano || cantIntentos == maxIntentos;
    }
	public String pedirPalabraSecreta() {
		return palabraSecreta.getString();
	}
	public int getLargodepalabra() {
		return palabraSecreta.getlargo_palabra();
	}
}
