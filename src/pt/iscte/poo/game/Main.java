package pt.iscte.poo.game;

import pt.iscte.poo.gui.ImageGUI;

public class Main {

	public static void main(String[] args) {
		//obtem a instancia unica da interface grafica
		ImageGUI gui = ImageGUI.getInstance();

		//cria uma nova instancia da GameEngine
		GameEngine engine = new GameEngine();

		//regista o GameEngine como um Observer da ImageGUI
        //isto significa que o motor de jogo vai ser notificado (vai chamar o metodo update())
        //sempre que houver um evento na GUI (ex: tecla pressionada ou um tick)
		gui.registerObserver(engine);
		//torna a janela da interface grafica visivel
		gui.go();
		// inicia o estado inicial do jogo (carrega o room0.txt, define o cronómetro, etc)
		engine.startGame();
		
		gui.setStatusMessage("Good luck!");
	}
	
}
