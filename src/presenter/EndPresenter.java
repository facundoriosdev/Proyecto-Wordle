package presenter;

import model.Juego;
import model.Juego.ResultadoJuego;
import view.EndView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EndPresenter {
	private EndView view;
    private Juego modeloJuego;

    public EndPresenter(EndView view, Juego modelo) {
        this.view = view;
        this.modeloJuego = modelo;
        
        cargarYMostrarRanking();
    }
    private void cargarYMostrarRanking() {
    	List<ResultadoJuego> rankingNoTraducido=modeloJuego.getRanking();
    	rankingNoTraducido.sort(Comparator.comparingDouble(ResultadoJuego::tiempo));
    	List<String> rankingParaScreen= new ArrayList <>();
    	for (int i = 0; i < rankingNoTraducido.size(); i++) {
            ResultadoJuego r = rankingNoTraducido.get(i);
            String linea = (i + 1) + ". " + r.nombreJugador() + " - " + r.tiempo() + " seg";
            rankingParaScreen.add(linea);
        }
    	view.mostrarRanking(rankingParaScreen);
    	}

}
