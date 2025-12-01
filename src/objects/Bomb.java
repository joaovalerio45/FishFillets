package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Bomb extends MobileObject {
    public Bomb(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "bomb";
	}


	@Override
	public boolean interact(GameCharacter fish, Direction direction, Room room) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'interact'");
	}


}
