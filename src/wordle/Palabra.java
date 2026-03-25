package wordle;
public class Palabra {
	private static int LENGTH_PALABRA= 5;
	private String letras;
	//Le delego a esta clase dar los colores, el largo de la palanbra y hacer que las letras esten siempre en mayuscula
	public Palabra (String letras) {
		if (letras== null || letras.length()!= LENGTH_PALABRA) {
			throw new IllegalArgumentException("Ingrese una palabra con 5 letras");
		}
		this.letras=letras.toUpperCase();
	}
	public EstadoLetra[] compararPalabra(Palabra otrapalabra) {
		EstadoLetra[] comparacion= new EstadoLetra[LENGTH_PALABRA];
		StringBuilder borrador = new StringBuilder(this.letras);
		for(int i=0; i<LENGTH_PALABRA; i++) {
			if(borrador.charAt(i)==otrapalabra.letras.charAt(i)) {
				comparacion[i]=EstadoLetra.VERDE;
				borrador.setCharAt(i,'*');
			}
			}
			for(int i=0; i<LENGTH_PALABRA; i++) {
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
