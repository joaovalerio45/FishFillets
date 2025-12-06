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
	private int levelNumber = 0;
	private int fishNumber = 0;

	private long startSeconds;
	private int moves;
	
	public GameEngine() {				
	}
	
	public void startGame() {
		currentRoom = Room.readRoom(new File("./rooms/room0.txt"));
		fishNumber = currentRoom.getFishes().size();
		startSeconds = System.currentTimeMillis();
		moves = 0;

	}

	private void updateStatus() {
    long elapsedMillis = System.currentTimeMillis() - startSeconds;
    int secondsCount = (int) elapsedMillis / 1000;
    
    ImageGUI.getInstance().setStatusMessage("Moves: " + moves + " Time: " + secondsCount + " seconds");
	}

	@Override
	public void update(Observed source) {

		if (ImageGUI.getInstance().wasKeyPressed()) {

			int k = ImageGUI.getInstance().keyPressed();
	
			
			if (Direction.isDirection(k)){

				Point2D to = currentRoom.getActiveFish().getPosition().plus(Direction.directionFor(k).asVector());
				List<GameObject> objs = currentRoom.getObjectsAt(to);

				boolean canMove = true;

					for(GameObject obj : objs){
						if(!obj.interact(currentRoom.getActiveFish(), Direction.directionFor(k), currentRoom)){
							canMove = false;
							break;
						}
					}
				
				if(canMove){
					currentRoom.getActiveFish().moveFish(Direction.directionFor(k).asVector());
					fishMoveActionEnable();

					currentRoom.checkExits(); 
					int fishesAfter = currentRoom.getFishes().size();

					if (fishesAfter < fishNumber) {
						fishNumber = fishesAfter; 
					}
					moves++;
					updateStatus();
				}
			}

			if(k == KeyEvent.VK_SPACE){
				currentRoom.switchActiveFish();
			}
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

	private void fishMoveActionEnable() {
		List<GameObject> objectsCopy = new ArrayList<>(currentRoom.getObjects());

		for(GameObject obj : objectsCopy){
			if(obj instanceof MobileObject && obj.isEnemy()){
				((MobileObject)obj).fishMoveAction(currentRoom);
			}
		}
	}

	private void processTick() {

		List<GameObject> objectsCopy = new ArrayList<>(currentRoom.getObjects());
		for(GameObject obj : objectsCopy){
			if(obj instanceof Tickable){
				((Tickable)obj).tickAction(currentRoom);
			}
		}
		if(currentRoom.getFishes().size() < fishNumber){
			ImageGUI.getInstance().showMessage("Perdeste :(", "Clica em OK para tentares de novo!");
			restartLevel();
		}

		if (currentRoom.getFishes().isEmpty()) {
			nextLevel();
		}

		updateStatus();

		
		lastTickProcessed++;
	}

	private void nextLevel() {
        levelNumber++;
        File nextRoomFile = new File("rooms/room" + levelNumber + ".txt");
        if(nextRoomFile.exists()){
            ImageGUI.getInstance().clearImages();
            currentRoom = Room.readRoom(nextRoomFile);
        } else {
			finishGame();
        }
    }
	
	
	private void restartLevel(){
		ImageGUI.getInstance().clearImages();
        currentRoom = Room.readRoom(new File("rooms/room" + levelNumber + ".txt"));
		fishNumber = currentRoom.getFishes().size();
	}


	private void finishGame(){
		int finalSeconds = (int)((System.currentTimeMillis() - startSeconds) / 1000);
		ScoreManager scman = new ScoreManager();
		scman.addScore(new Score(finalSeconds, moves));
		scman.writeHighscores();
		ImageGUI.getInstance().showMessage("Acabaste o jogo!","Clica OK para ver os Highscores");
		ImageGUI.getInstance().showMessage("=== TOP 10 ===\n\n",scman.writeList());
		System.exit(0);
	}
}
