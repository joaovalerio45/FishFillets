package pt.iscte.poo.game;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

import objects.GameObject;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class GameEngine implements Observer {
	
	private Room currentRoom;
	private int lastTickProcessed = 0;
	
	public GameEngine() {				
	}
	
	public void startGame() {
		currentRoom = Room.readRoom(new File("./rooms/room0.txt"));
	}

	@Override
	public void update(Observed source) {

		if (ImageGUI.getInstance().wasKeyPressed()) {

			int k = ImageGUI.getInstance().keyPressed();
	
			
			if (Direction.isDirection(k)){

				Point2D to = currentRoom.getActiveFish().getPosition().plus(Direction.directionFor(k).asVector());

				List<GameObject> objs = currentRoom.getObjectsAt(to);

				if(objs.isEmpty()){
					currentRoom.getActiveFish().moveFish(Direction.directionFor(k).asVector());
				}else{
					for(GameObject obj : objs){
						if(obj.interact(currentRoom.activeFish, Direction.directionFor(k), currentRoom))
							currentRoom.getActiveFish().moveFish(Direction.directionFor(k).asVector());
					}
				}



			}
			if(k == KeyEvent.VK_SPACE){
				currentRoom.switchActiveFish();
			}
			if(k == KeyEvent.VK_R){
				
			}
		}

		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();
		}
		ImageGUI.getInstance().update();
	}

	private void processTick() {	
		lastTickProcessed++;
	}	
}
