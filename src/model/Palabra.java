package model;
public class Palabra {
	private final int largo_palabra;
	private String letras;
	
	//Crea la palabra secreta para manejarla
	public Palabra (String letras) {
		this.largo_palabra= letras.length();
		
		this.letras=letras.toUpperCase();
	}
	
	//El metodo anterior no puede marcar letras de colores incorrectos
	//asi que no borren el stringbuilder ni toquen nada de esta logica
	public EstadoLetra[] compararPalabra(Palabra otrapalabra) {
		if(otrapalabra.largo_palabra!= largo_palabra) {
			throw new IllegalArgumentException("Ingrese una palabra con 5 letras");
			}
		EstadoLetra[] comparacion= new EstadoLetra[largo_palabra];
		StringBuilder borrador = new StringBuilder(this.letras);
		for(int i=0; i<largo_palabra; i++) {
			if(borrador.charAt(i)==otrapalabra.letras.charAt(i)) {
				comparacion[i]=EstadoLetra.VERDE;
				borrador.setCharAt(i,'*');
			}
			}
			for(int i=0; i<largo_palabra; i++) {
				if(comparacion[i]==null) {
					int index=borrador.indexOf(String.valueOf(otrapalabra.letras.charAt(i)));
					if(index!=-1) {
						comparacion[i]=EstadoLetra.AMARILLO;
						borrador.setCharAt(index, '*');
					}else {
					comparacion[i]=EstadoLetra.GRIS;
				}
			}
				
		}
			
		return comparacion;
	}
	
	public enum EstadoLetra{
		VERDE("V"), AMARILLO("A"),
		GRIS("G");
		private final String color;
		EstadoLetra(String color){
			this.color=color;
		}
		@Override
		public String toString() {
			return color;
		}
	}
	public String getString() {
		return letras;
	}
	
}
