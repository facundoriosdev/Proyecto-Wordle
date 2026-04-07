package presenter;

import view.MenuView;
import view.GameScreen;

public class MenuPresenter {
	private MenuView view;
	
	public MenuPresenter(MenuView view) {
		this.view = view;
	}
	
	public void Empezar() {
		String nombre = view.getNombre();
		String dificultad = view.getDificultad();
		if(nombre == null || nombre.length() < 3 || nombre.length() > 10) {
			view.mostrarErrorNombre();
			return;
		}
		view.informacionDeJuego();
		view.cerrar();
		GameScreen game = new GameScreen(nombre, dificultad);
		game.setVisible(true);
		game.setLocationRelativeTo(null);
	}
}
