package pt.iscte.poo.game;

import java.awt.event.KeyEvent;
import java.io.File;

import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;

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


				currentRoom.tryMove(Direction.directionFor(k).asVector());

			}
			if(k == KeyEvent.VK_SPACE){
				currentRoom.switchActiveFish();
			}
		}

		currentRoom.checkWeight();

		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();
		}
		
		ImageGUI.getInstance().update();
	}

	private void processTick() {
		currentRoom.applyGravity();		
		lastTickProcessed++;
	}	
}
