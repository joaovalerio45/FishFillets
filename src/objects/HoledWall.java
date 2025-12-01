package objects;

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
		return true;
	}



	
}
