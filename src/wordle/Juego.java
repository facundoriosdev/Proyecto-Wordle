package wordle;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//Esta clase orquesta todo el wordle, la idea es que aca tambien se guarde lo que es los puntajes y se pueda cambiar el idioma
public class Juego {
	private List <String> diccionario;
	private Partida partidaActual;
	
	public Juego() {
		this.diccionario=new ArrayList<>();
		cargarDiccionario();
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
		
		this.partidaActual= new Partida (palabraElegida,dificultad);
	}
	
		
	}
	