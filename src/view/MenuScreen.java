package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import presenter.MenuPresenter;

public class MenuScreen extends JFrame{
	private MenuPresenter presenter;
	private JTextField nombreField;
	private JComboBox<String> dificultadBox;

	public MenuScreen() {
		// Caracteristicas de la pantalla:
		getContentPane().setBackground(new Color(0, 153, 204));
		getContentPane().setForeground(new Color(0, 0, 0));
		setTitle("Programacion III - Juego Wordle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		getContentPane().setLayout(null);
		
		// Textos en Pantalla:
		JLabel tituloLabel = new JLabel("WORDLE TP1");
		tituloLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		tituloLabel.setBounds(123, 22, 335, 75);
		getContentPane().add(tituloLabel);
		
		JLabel nombreLabel = new JLabel("NOMBRE:");
		nombreLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		nombreLabel.setBounds(57, 137, 196, 67);
		getContentPane().add(nombreLabel);
		
		JLabel dificultadLabel = new JLabel("DIFICULTAD:");
		dificultadLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		dificultadLabel.setBounds(56, 201, 221, 67);
		getContentPane().add(dificultadLabel);
			
		// Campo de llenar nombre
		nombreField = new JTextField();
		nombreField.setFont(new Font("Tahoma", Font.BOLD, 30));
		nombreField.setBounds(263, 149, 260, 35);
		getContentPane().add(nombreField);
		nombreField.setColumns(10);
		
		// Selector de dificultad
		dificultadBox = new JComboBox<>();
		dificultadBox.setFont(new Font("Tahoma", Font.BOLD, 30));
		dificultadBox.setBounds(315, 211, 208, 35);
		getContentPane().add(dificultadBox);
		dificultadBox.setModel(new DefaultComboBoxModel<>(new String[] {"FACIL", "MEDIO", "DIFICIL"}));
		
		// Boton empezar juego
		JButton empezarButton = new JButton("EMPEZAR");
		empezarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presenter.Empezar();
			}
		});
		empezarButton.setForeground(new Color(46, 46, 46));
		empezarButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		empezarButton.setBounds(180, 281, 208, 50);
		getContentPane().add(empezarButton);
	}
	
	//***********************************************
	public void setPresenter(MenuPresenter presenter) {
		this.presenter = presenter;
	}
	
	public String getNombre() {
		return nombreField.getText();
	}
	
	public String getDificultad() {
		return (String) dificultadBox.getSelectedItem();
	}
	
	public void mostrarErrorNombre() {
		JOptionPane.showMessageDialog(null, "Tu nombre debe no ser vacio y tener más de 2 caracteres y menos de 11", 
				"Error al iniciar el juego", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void informacionDeJuego() {
		JOptionPane.showMessageDialog(null, "  El juego consiste en adivinar una palabra secreta de" + " \n " +
			"   x letras que propone la aplicación. Al iniciar el juego," + " \n " +
			"     la aplicación selecciona aleatoriamente la palabra " + " \n " +
			" secreta de una lista de palabras, y el usuario debe adivinar" + " \n " +
			" la palabra secreta arriesgando palabras por turnos. En cada " + " \n " +
			"  turno el usuario le informa al juego una palabra. Si la" + " \n " +
			"  palabra que introdujo el usuario coincide con la palabra" + " \n " +
			"        secreta, el usuario gana el juego" + "\n ", "Reglas del juego", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void cerrar() {
		dispose();
	}
}
