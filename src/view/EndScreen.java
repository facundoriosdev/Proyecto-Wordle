package view;

import presenter.EndPresenter;
import model.Juego;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EndScreen extends JFrame implements EndView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EndPresenter presenter;
	private DefaultListModel<String> listModel;
	private JList<String> rankingList;

	public EndScreen(Juego modeloJuego) { // Recibe el juego para pasárselo al presentador
		setTitle("Ranking");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JLabel tituloLabel = new JLabel("TOP", SwingConstants.CENTER);
		tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
		tituloLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		add(tituloLabel, BorderLayout.NORTH);
		listModel = new DefaultListModel<>();
		rankingList = new JList<>(listModel);
		rankingList.setFont(new Font("Monospaced", Font.PLAIN, 18));
		JScrollPane scrollPane = new JScrollPane(rankingList);
		add(scrollPane, BorderLayout.CENTER);

		JButton btnSalir = new JButton("SALIR");
		btnSalir.setFont(new Font("Arial", Font.BOLD, 16));
		btnSalir.addActionListener(e -> System.exit(0));
		add(btnSalir, BorderLayout.SOUTH);

		this.presenter = new EndPresenter(this, modeloJuego);

		setVisible(true);

	}

	@Override
	public void mostrarRanking(List<String> datosRanking) {
		listModel.clear();
		for (String dato : datosRanking) {
			listModel.addElement(dato);
		}
	}

	@Override
	public void cerrar() {
		dispose();
	}
}
