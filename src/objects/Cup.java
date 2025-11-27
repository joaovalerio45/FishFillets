package objects;

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
	public boolean move(GameCharacter fish, Direction direction) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'move'");
	}


}
