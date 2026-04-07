package view;

import presenter.MenuPresenter;

public interface MenuView {
	public String getNombre();
	public String getDificultad();
	public void setPresenter(MenuPresenter presenter);
	public void mostrarErrorNombre();
	public void informacionDeJuego() ;
	public void cerrar();
}
