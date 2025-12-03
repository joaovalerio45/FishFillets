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
	
	public GameEngine() {				
	}
	
	public void startGame() {
		currentRoom = Room.readRoom(new File("./rooms/room0.txt"));
	}

	private void nextLevel() {
        levelNumber++;
        File nextRoomFile = new File("rooms/room" + levelNumber + ".txt");
        if(nextRoomFile.exists()){
            ImageGUI.getInstance().clearImages();
            currentRoom = Room.readRoom(nextRoomFile);
        } else {
            System.exit(0);
        }
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
                
                if (currentRoom.getFishes().isEmpty()) {
                    nextLevel();
                }
				}
			}

			if(k == KeyEvent.VK_SPACE){
				currentRoom.switchActiveFish();
			}
			if(k == KeyEvent.VK_R){
				restartGame();
			}
		}

		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();
		}
		ImageGUI.getInstance().update();
	}

	private void fishMoveActionEnable() {
		for(GameObject obj : currentRoom.getObjects()){
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
		
		lastTickProcessed++;
	}
	
	private void restartGame() {
		ImageGUI.getInstance().clearImages();
		currentRoom = Room.readRoom(new File("./rooms/room0.txt"));
	}	

}
