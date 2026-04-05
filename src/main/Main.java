package main;

import javax.swing.UIManager;

import view.MenuScreen;

public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch(Exception e){
			System.out.println(e);
		}
		MenuScreen launch = new MenuScreen();
		launch.setResizable(false);
		launch.setVisible(true);
		launch.setLocationRelativeTo(null);
	}
}
