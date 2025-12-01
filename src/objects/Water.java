package objects;

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
	public boolean interact(GameCharacter fish, Direction direction, Room room) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'interact'");
	}

}
