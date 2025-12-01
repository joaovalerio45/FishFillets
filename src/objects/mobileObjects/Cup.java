package objects.mobileObjects;

import objects.*;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Cup extends MobileObject {
    public Cup(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "cup";
	}


	@Override
	public boolean interact(GameObject object, Direction direction, Room room) {
		if(object instanceof GameCharacter){
			return move((GameCharacter)object, direction, room);
		}
		return false;
	}
	


}
