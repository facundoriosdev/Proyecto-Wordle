package presenter;
import model.Juego;
import model.Palabra.EstadoLetra;
import view.GameView;

public class GamePresenter {
	private GameView view;
    private Juego modeloJuego;
    private int filaActual;
    public GamePresenter(GameView view, String nombre, String dificultad) {
    	this.view = view;
        this.modeloJuego = new Juego();
        this.filaActual = 0;
        int nivel = traducirDificultad(dificultad);
        this.modeloJuego.iniciarPartida(nivel, nombre);;
        
    }
    public void arriesgarIntento(String intento) {
    	try {
    	modeloJuego.registrarIntento(intento);
    	EstadoLetra[] colores = modeloJuego.pedirUltimosColores();
    	view.pintarFila(filaActual, intento, colores);
    	filaActual++;
    	if (modeloJuego.partidaTerminada()) {
            view.deshabilitarInput();
            view.mostrarMensajeFinJuego(modeloJuego.jugadorGano(), modeloJuego.obtenerPalabraSecreta());
        }} catch (IllegalArgumentException e) {
        	view.mostrarError(e.getMessage());
        }
    }
	private int traducirDificultad(String dificultad) {
		switch (dificultad) {
		case "FACIL":return 1;
		case "MEDIO":return 2;
		default: return 3; //caso dificil
		}
}

}
