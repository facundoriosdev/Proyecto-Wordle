package view;

import presenter.GamePresenter;
import model.Palabra.EstadoLetra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JFrame implements GameView {
    

	private GamePresenter presenter;
    private JLabel[][] matrizLetras;
    private JTextField inputIntento;
    private JButton btnArriesgar;
    private int maxIntentos;
    private final int LARGO_PALABRA = 5;

    public GameScreen(String nombre, String dificultadStr) {
        
        setTitle("Wordle - Jugador: " + nombre);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

       //calcula la cantidad de filas dependiendo de la dificultad
        this.maxIntentos = calcularFilas(dificultadStr);

        
        JPanel panelGrilla = new JPanel();
        panelGrilla.setLayout(new GridLayout(maxIntentos, LARGO_PALABRA, 5, 5)); // Filas, Columnas
        panelGrilla.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panelGrilla.setBackground(Color.DARK_GRAY);

        
        matrizLetras = new JLabel[maxIntentos][LARGO_PALABRA];
        Font fuenteLetra = new Font("Arial", Font.BOLD, 30);

        for (int f = 0; f < maxIntentos; f++) {
            for (int c = 0; c < LARGO_PALABRA; c++) {
                JLabel lblLetra = new JLabel("", SwingConstants.CENTER);
                lblLetra.setFont(fuenteLetra);
                lblLetra.setOpaque(true); 
                lblLetra.setBackground(Color.WHITE);
                lblLetra.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                
                matrizLetras[f][c] = lblLetra;
                panelGrilla.add(lblLetra);
            }
        }
        add(panelGrilla, BorderLayout.CENTER);

        //crea el panel inferior
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());

        inputIntento = new JTextField(10);
        inputIntento.setFont(new Font("Arial", Font.PLAIN, 20));
        
        btnArriesgar = new JButton("Arriesgar");
        btnArriesgar.setFont(new Font("Arial", Font.BOLD, 20));

        panelInferior.add(new JLabel("Intento: "));
        panelInferior.add(inputIntento);
        panelInferior.add(btnArriesgar);
        add(panelInferior, BorderLayout.SOUTH);

        this.presenter = new GamePresenter(this, nombre, dificultadStr);

        btnArriesgar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String intento = inputIntento.getText().toUpperCase();
                presenter.arriesgarIntento(intento); 
                inputIntento.setText(""); 
            }
        });
        this.setVisible(true);
    }


    @Override
    public void pintarFila(int fila, String palabra, EstadoLetra[] colores) {
        for (int c = 0; c < LARGO_PALABRA; c++) {
            JLabel lblActual = matrizLetras[fila][c];
            
           
            lblActual.setText(String.valueOf(palabra.charAt(c)));
            
            
            EstadoLetra estado = colores[c];
            if (estado == EstadoLetra.VERDE) {
                lblActual.setBackground(new Color(83, 141, 78)); 
                lblActual.setForeground(Color.WHITE);
            } else if (estado == EstadoLetra.AMARILLO) {
                lblActual.setBackground(new Color(181, 159, 59)); 
                lblActual.setForeground(Color.WHITE);
            } else {
                lblActual.setBackground(new Color(58, 58, 60)); 
                lblActual.setForeground(Color.WHITE);
            }
        }
    }

    @Override
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void mostrarMensajeFinJuego(boolean gano, String palabraSecreta) {
        String mensaje = gano ? "¡Felicitaciones! Adivinaste la palabra." : "Juego terminado. La palabra era: " + palabraSecreta;
        JOptionPane.showMessageDialog(this, mensaje, "Fin de la Partida", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void deshabilitarInput() {
        inputIntento.setEnabled(false);
        btnArriesgar.setEnabled(false);
    }

    private int calcularFilas(String dif) {
        switch(dif) {
            case "FACIL": return 8;
            case "MEDIO": return 6;
            default: return 4;
        }
    }
}