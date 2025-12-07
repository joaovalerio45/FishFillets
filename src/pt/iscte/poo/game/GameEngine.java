package pt.iscte.poo.game;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import objects.GameObject;
import objects.MobileObject;
import objects.interfaces.Tickable;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class GameEngine implements Observer {
	
	private Room currentRoom;
	private int lastTickProcessed = 0;
	//variavel que guarda o numero do nivel
	private int levelNumber = 0;
	//variavel que guarda o numero de peixes
	private int fishNumber = 0;

	//variavel que guarda o instante que começa o jogo
	private long startSeconds;
	//variavel que guarda os número de moves
	private int moves;
	
	public GameEngine() {				
	}
	
	//começa o jogo: cria o room, inicializa as variaveis fishNumber, startSeconds e moves
	public void startGame() {
		currentRoom = Room.readRoom(new File("./rooms/room0.txt"));
		fishNumber = currentRoom.getFishes().size();
		startSeconds = System.currentTimeMillis();
		moves = 0;

	}


	//atualiza a barra de Status com a contagem de segundos e os moves efetuados pelo jogador
	private void updateStatus() {
    long elapsedMillis = System.currentTimeMillis() - startSeconds;
    int secondsCount = (int) elapsedMillis / 1000;
    
    ImageGUI.getInstance().setStatusMessage("Moves: " + moves + " Time: " + secondsCount + " seconds");
	}

	@Override
	public void update(Observed source) {

		//quando o jogador carrega numa tecla
		if (ImageGUI.getInstance().wasKeyPressed()) {

			int k = ImageGUI.getInstance().keyPressed();
	
			//se for uma seta
			if (Direction.isDirection(k)){

				//seleciona os objetos na posicao de destino
				Point2D to = currentRoom.getActiveFish().getPosition().plus(Direction.directionFor(k).asVector());
				List<GameObject> objs = currentRoom.getObjectsAt(to);

				boolean canMove = true;

					for(GameObject obj : objs){
						// se o return da interação com algum objeto for false, desiste do movimento
						if(!obj.interact(currentRoom.getActiveFish(), Direction.directionFor(k), currentRoom)){
							canMove = false;
							break;
						}
					}
				
				//se o movimento for permitido
				if(canMove){
					//move o peixe ativo e aciona o método fishMoveActionEnable()
					currentRoom.getActiveFish().moveFish(Direction.directionFor(k).asVector());
					fishMoveActionEnable();

					//verifica se o movimento foi uma saida
					currentRoom.checkExits(); 
					int fishesAfter = currentRoom.getFishes().size();
					// se foi, atualiza o fishNumber de forma a não dar "derrota falsa" após o desaparecimento de um peixe
					if (fishesAfter < fishNumber) {
						fishNumber = fishesAfter; 
					}
					//incrementa moves e atualiza a barra de status
					moves++;
					updateStatus();
				}
			}
			//se clicar no espaço muda o peixe ativo
			if(k == KeyEvent.VK_SPACE){
				currentRoom.switchActiveFish();
			}
			//se clicar no R reinicia o nivel
			if(k == KeyEvent.VK_R){
				restartLevel();
			}
		}

		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();
		}
		ImageGUI.getInstance().update();
	}

	// em todos os objetos móveis que agem após um movimento dum peixe ativa essa ação
	private void fishMoveActionEnable() {
		List<GameObject> objectsCopy = new ArrayList<>(currentRoom.getObjects());

		for(GameObject obj : objectsCopy){
			if(obj instanceof MobileObject && obj.isEnemy()){
				((MobileObject)obj).fishMoveAction(currentRoom);
			}
		}
	}

	private void processTick() {

		//em todos os objetos que implementam tickable ativa essa ação (a cada tick)
		List<GameObject> objectsCopy = new ArrayList<>(currentRoom.getObjects());
		for(GameObject obj : objectsCopy){
			if(obj instanceof Tickable){
				((Tickable)obj).tickAction(currentRoom);
			}
		}

		//se o numero de peixes diminuir significa que algum morreu logo perde-se o nível e reinicia
		if(currentRoom.getFishes().size() < fishNumber){
			ImageGUI.getInstance().showMessage("Perdeste :(", "Clica em OK para tentares de novo!");
			restartLevel();
		}

		//se não há peixes passa para o nivel seguinte
		if (currentRoom.getFishes().isEmpty()) {
			nextLevel();
		}

		//atualiza a barra de status
		updateStatus();

		
		lastTickProcessed++;
	}


	//muda para o nível seguinte
	private void nextLevel() {
		//incrementa o nível
        levelNumber++;
        File nextRoomFile = new File("rooms/room" + levelNumber + ".txt");
        if(nextRoomFile.exists()){
            ImageGUI.getInstance().clearImages();
			//carrega o nivel com esse número se existir o ficheiro
            currentRoom = Room.readRoom(nextRoomFile);
        } else {
			//se não acaba o jogo
			finishGame();
        }
    }
	
	//reinicia o nivel
	private void restartLevel(){
		ImageGUI.getInstance().clearImages();
        currentRoom = Room.readRoom(new File("rooms/room" + levelNumber + ".txt"));
		fishNumber = currentRoom.getFishes().size();
	}

	//acaba o jogo
	private void finishGame(){
		//determina o tempo final
		int finalSeconds = (int)((System.currentTimeMillis() - startSeconds) / 1000);
		//cria um score manager e gere os recordes (ver ScoreManager)
		ScoreManager scman = new ScoreManager();
		scman.addScore(new Score(finalSeconds, moves));
		scman.writeHighscores();
		//exibe os resultados e fecha
		ImageGUI.getInstance().showMessage("Acabaste o jogo!","Clica OK para ver os Highscores");
		ImageGUI.getInstance().showMessage("=== TOP 10 ===\n\n",scman.writeList());
		System.exit(0);
	}
}
