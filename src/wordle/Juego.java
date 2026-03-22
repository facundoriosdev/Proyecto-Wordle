package wordle;

import java.util.ArrayList;


public class Juego {
	String palabraSecreta;
	int cantDeIntentosActuales;
	String [] historialDeIntentos; //elegi un array porque es de tamaño fijo
	ArrayList<String[]> colores;
	StringBuilder borrador;
	public Juego(String palabrasecreta, String[] historialDeIntentos, int dificultad) {
		this.palabraSecreta= palabrasecreta;
		this.cantDeIntentosActuales=0;
		this.historialDeIntentos= new String[elegirDificultad(dificultad)]; //llama a un metodo para definir la cantidad de intentos que va a tener el usuario
		this.colores= new ArrayList<>(); //lista que guarda los resultados de intentos anteriores
		this.borrador= new StringBuilder(palabraSecreta); //copia de la palabra secreta que sirve para que no detecte varias veces la misma letra

	}
	private int elegirDificultad(int nivelDeDificultad) {
		int tamaño = 0;
		
		switch (nivelDeDificultad) { //se me hizo mas comodo que usar muchos if, pero la verdad no tengo idea si funciona xd
		case 1: tamaño= 8;
		case 2:tamaño= 6;
		case 3:tamaño= 4;
	
		}
		return tamaño;

	}
	private boolean esLaPalabra(String intento) {      
		if (intento.equals(palabraSecreta)){
			return true;
		}
		historialDeIntentos[cantDeIntentosActuales]= intento;
		
		return false;
	}
	private boolean verificarIntentos () {
		return cantDeIntentosActuales==historialDeIntentos.length;
	
	}
	private void agregarPalabraAlHistorial(String palabra) {
		if (!verificarIntentos() && !esLaPalabra(palabra)) {
			historialDeIntentos[cantDeIntentosActuales]= palabra;
			cantDeIntentosActuales++;
		}
	}
	private void seEncuentraLaLetra(String intento) {
		String [] codColores= new String[palabraSecreta.length()]; //Parte importante, esto guarda los colores de las letras dependiendo de si estan en la posicion correcta o estan en la palabra
		for(int i=0;i>palabraSecreta.length();i++) {
			if (intento.charAt(i)==palabraSecreta.charAt(i)){
				borrador.setCharAt(i,'*');
				codColores[i]= "verde";
			}
		}
		for(int i=0;i>palabraSecreta.length();i++) {
			if(codColores[i]!="verde") {
				char letraIntento = intento.charAt(i);
				int indiceEnBorrador = borrador.indexOf(String.valueOf(letraIntento));
				if(indiceEnBorrador!=-1) {
					codColores[i]= "amarillo";
					borrador.setCharAt(indiceEnBorrador, '*');
				} else {
		            codColores[i] = "GRIS";
				}
				
			}
		}
		
	}
	//private int cantAparicionesLetra(char letra) {
	//	int cantApariciones=0;
		//for (char l: palabraSecreta.toCharArray()) {
		//	if(l== letra) {
		//	cantApariciones++;
			
	//	}}
	//	return cantApariciones;
		
	//}
	// quedo obsoleto despues de un tiempo pero lo guardo por si le encuentran un uso
}
