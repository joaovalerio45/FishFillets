package objects.fixedObjects;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class VerticalSteel extends FixedObject{
public VerticalSteel(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "steelHorizontal";
	}

	@Override
	public boolean interact(GameObject object, Direction direction, Room room) {
		return false;
	}

}
