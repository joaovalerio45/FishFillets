package objects.fixedObjects;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class HoledWall extends FixedObject {
public HoledWall(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "holedWall";
	}

	@Override
	public boolean isTraversable(GameObject obj) {
		return obj.isSmall();
	}

	@Override
	public boolean interact(GameObject object, Direction direction, Room room) {
		if(isTraversable(object)){
			return true;
		}
		return false;
	}



	
}
