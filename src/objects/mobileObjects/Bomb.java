package objects.mobileObjects;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Bomb extends MobileObject {
    public Bomb(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "bomb";
	}


	@Override
	public boolean interact(GameObject object, Direction direction, Room room) {
		if(object instanceof GameCharacter){
			((GameCharacter)object).kill(room);
		}
		return false;
	}
}
