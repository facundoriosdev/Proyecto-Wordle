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

public class MenuScreen extends JFrame implements MenuView{
	private MenuPresenter presenter;
	private JTextField nombreField;
	private JComboBox<String> dificultadBox;
	private JComboBox<String> idiomaBox;
	private JLabel tituloLabel;
    private JLabel nombreLabel;
    private JLabel dificultadLabel;
    private JButton empezarButton;

	public MenuScreen() {
		this.presenter = new MenuPresenter(this);
		
		// Caracteristicas de la pantalla:
		getContentPane().setBackground(new Color(0, 153, 204));
		getContentPane().setForeground(new Color(0, 0, 0));
		setTitle("Programacion III - Juego Wordle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		getContentPane().setLayout(null);
		
		// Textos en Pantalla:
		tituloLabel = new JLabel("WORDLE TP1");
		tituloLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		tituloLabel.setBounds(123, 0, 335, 75);
		getContentPane().add(tituloLabel);
		
		nombreLabel = new JLabel("NOMBRE:");
		nombreLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		nombreLabel.setBounds(57, 137, 196, 67);
		getContentPane().add(nombreLabel);
		
		dificultadLabel = new JLabel("DIFICULTAD:");
		dificultadLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		dificultadLabel.setBounds(56, 201, 221, 67);
		getContentPane().add(dificultadLabel);
		
		idiomaBox = new JComboBox<>(new String[] {"ESPAÑOL", "ENGLISH"});
        idiomaBox.setFont(new Font("Tahoma", Font.BOLD, 20));
        idiomaBox.setBounds(50, 70, 150, 35);
        getContentPane().add(idiomaBox);
        idiomaBox.addActionListener(e -> {
            if (presenter != null) {
                presenter.alCambiarIdioma();
            }
        });
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
		empezarButton = new JButton("EMPEZAR");
		empezarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presenter.Empezar();
			}
		})
		;
		empezarButton.setForeground(new Color(46, 46, 46));
		empezarButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		empezarButton.setBounds(180, 281, 208, 50);
		getContentPane().add(empezarButton);
	}
	
	//***********************************************
	@Override
	public String getNombre() {
		return nombreField.getText();
	}
	
	@Override
	public String getDificultad() {
		return (String) dificultadBox.getSelectedItem();
	}
	
	@Override
	public void setPresenter(MenuPresenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public void mostrarErrorNombre() {
		String idioma = getIdiomaSeleccionado(); 

		if (idioma.equals("ENGLISH")) {
			JOptionPane.showMessageDialog(null, "Your name must not be empty and have between 3 and 10 characters", 
					"Error starting the game", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Tu nombre debe no ser vacio y tener más de 2 caracteres y menos de 11", 
					"Error al iniciar el juego", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@Override
	public void informacionDeJuego() {
		String idioma = getIdiomaSeleccionado();

		if (idioma.equals("ENGLISH")) {
			JOptionPane.showMessageDialog(null, "  The game consists of guessing a secret word of" + " \n " +
					"   X letters proposed by the application. Upon starting," + " \n " +
					"     the application randomly selects the secret " + " \n " +
					" word from a list, and the user must guess it" + " \n " +
					" by submitting words in turns. In each " + " \n " +
					"  turn the user submits a word. If the" + " \n " +
					"  word entered by the user matches the secret" + " \n " +
					"        word, the user wins the game." + "\n ", "Game Rules", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "  El juego consiste en adivinar una palabra secreta de" + " \n " +
					"   x letras que propone la aplicación. Al iniciar el juego," + " \n " +
					"     la aplicación selecciona aleatoriamente la palabra " + " \n " +
					" secreta de una lista de palabras, y el usuario debe adivinar" + " \n " +
					" la palabra secreta arriesgando palabras por turnos. En cada " + " \n " +
					"  turno el usuario le informa al juego una palabra. Si la" + " \n " +
					"  palabra que introdujo el usuario coincide con la palabra" + " \n " +
					"        secreta, el usuario gana el juego" + "\n ", "Reglas del juego", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	@Override
	public void cerrar() {
		dispose();
	}
	public String getIdiomaSeleccionado() {
		return (String) idiomaBox.getSelectedItem();
	}
	@Override
    public void actualizarTextos(String idioma) {
        if (idioma.equals("ENGLISH")) {
            tituloLabel.setText("WORDLE TP1");
            nombreLabel.setText("NAME:");
            dificultadLabel.setText("DIFFICULTY:");
            empezarButton.setText("START");
            dificultadBox.setModel(new DefaultComboBoxModel<>(new String []{"EASY", "MEDIUM", "HARD"}));
            
} if(idioma.equals("ESPAÑOL")){
	tituloLabel.setText("WORDLE TP1");
    nombreLabel.setText("NOMBRE:");
    dificultadLabel.setText("DIFICULTAD:");
    empezarButton.setText("EMPEZAR");
    dificultadBox.setModel(new DefaultComboBoxModel<>(new String []{"FACIL", "MEDIO", "DIFICIL"}));
}}}
