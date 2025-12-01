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
	public boolean interact(GameCharacter fish, Direction direction, Room room) {
		if(fish instanceof BigFish){
			return false;
		}
		return true;
	}



	
}
