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


	@Override
	public boolean interact(GameCharacter fish, Direction direction, Room room) {
		if(fish instanceof BigFish){
			return true;
		}else if(fish instanceof SmallFish){
			return false;
		}
		return false;
	}

	@Override
	public boolean move(GameCharacter fish, Direction direction, Room room) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'move'");
	}

}
