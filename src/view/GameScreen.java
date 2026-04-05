package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class GameScreen extends JFrame{
	
	//La idea es generar un matriz, y la cantidad de columnas sera la dificultad que elgio el jugador(FACIL,MEDIO,DIFICIL)
	//Ademas cada fila sera la cantidad de intento, cada celda de la matriz la podemos pintar con el color que corresponda al
	// Comprar la palabra del usuario con la palabra secreta generada.
	private JFrame frame;

	public GameScreen(String nombre, String dificultad) {
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Programacion III - Juego Wordle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
	}

}
