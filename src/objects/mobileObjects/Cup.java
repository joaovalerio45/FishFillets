package objects.mobileObjects;

import java.util.List;

import objects.*;
import objects.fixedObjects.HoledWall;
import objects.interfaces.Movable;
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
		return true;
	}

	@Override
	public boolean move(GameCharacter fish, Direction direction, Room room) {
		Point2D to = getPosition().plus(direction.asVector());
		
	}


}
