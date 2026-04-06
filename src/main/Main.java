package main;

import presenter.MenuPresenter;
import view.MenuScreen;


public class Main {
	public static void main(String[] args) {
		MenuScreen view = new MenuScreen();
		MenuPresenter presenter = new MenuPresenter(view);
		view.setPresenter(presenter);
		view.setVisible(true);
		view.setLocationRelativeTo(null);
	}
}
