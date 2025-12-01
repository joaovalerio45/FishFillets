package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Stone extends MobileObject {
    public Stone(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "stone";
	}


	@Override
	public boolean interact(GameCharacter fish, Direction direction, Room room) {
		return true;
	}

}
