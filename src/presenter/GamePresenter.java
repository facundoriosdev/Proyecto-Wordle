package presenter;
import model.Juego;
import model.Palabra.EstadoLetra;
import view.EndScreen;
import view.GameView;

public class GamePresenter {
	private GameView view;
    private Juego modeloJuego;
    private int filaActual;
    public GamePresenter(GameView view, String nombre, String dificultad, String idioma) {
    	this.view = view;
        this.modeloJuego = new Juego(idioma);
        this.filaActual = 0;
        int nivel = traducirDificultad(dificultad);
        this.modeloJuego.iniciarPartida(nivel, nombre, idioma);;
        System.out.println("Dificultad recibida del Menú: [" + dificultad + "]");
        System.out.println("Nivel devuelto por el switch: " + nivel);
        System.out.println("Largo de la palabra final: " + modeloJuego.consultarlargodepalabra());
        
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
            new EndScreen(modeloJuego);
        }} catch (IllegalArgumentException e) {
        	view.mostrarError(e.getMessage());
        }
    }
    private int traducirDificultad(String dif) {
    	String difLimpia = dif.trim().toUpperCase();
        switch(difLimpia) {
            case "FACIL":
            case "EASY":
                return 1;
            case "MEDIO":
            case "MEDIUM":
                return 2;
            default: 
                return 3; // DIFICIL y HARD 
        }
    }
	public int obtenerPalabra() {
	
		return (modeloJuego.obtenerPalabraSecreta()).length();
	}
	public int pasarLargodePalabra() {
	
		return modeloJuego.consultarlargodepalabra();
	}

}
