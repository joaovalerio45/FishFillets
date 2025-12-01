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
	public boolean interact(GameCharacter fish, Direction direction, Room room) {
		return true;
	}

	@Override
	public boolean move(GameCharacter fish, Direction direction, Room room) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'move'");
	}


}
