package objects.mobileObjects;

import objects.*;
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

	public boolean isHeavy() {
		return true;
	}
}
