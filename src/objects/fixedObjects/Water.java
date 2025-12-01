package objects.fixedObjects;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Water extends FixedObject{

	public Water(Point2D p) {
		super(p);
	}

	@Override
	public String getName() {
		return "water";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public boolean interact(GameObject object, Direction direction, Room room) {
		return true;
	}

}
