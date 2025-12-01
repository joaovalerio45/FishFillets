package objects.fixedObjects;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Trunk extends FixedObject {
public Trunk(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "trunk";
	}

	@Override
	public boolean interact(GameCharacter fish, Direction direction, Room room) {
		return false;
	}
	
}
