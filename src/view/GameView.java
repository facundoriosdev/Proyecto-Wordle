package view;

import model.Palabra.EstadoLetra;

public interface GameView {
	void pintarFila(int fila, String palabra, EstadoLetra[] colores);
	void mostrarError(String mensaje);
	void mostrarMensajeFinJuego(boolean gano, String palabraSecreta);
	void deshabilitarInput();

}
