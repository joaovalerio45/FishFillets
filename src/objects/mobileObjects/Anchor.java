package objects.mobileObjects;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Anchor extends MobileObject{

	boolean hasMovedOnce;

    public Anchor(Point2D p) {
		super(p);
		this.hasMovedOnce = false;
	}


	@Override
	public boolean isHeavy(){
		return true;
	}
	
	@Override
	public String getName() {
		return "anchor";
	}


	@Override
	public boolean interact(GameObject object, Direction direction, Room room) {
		if (object instanceof BigFish) {
            return move((GameCharacter) object, direction, room);
        }
        return false;
	}


	@Override
	public boolean move(GameCharacter fish, Direction direction, Room room) {
		if (direction == Direction.UP || direction == Direction.DOWN) {
                return false;
        }

		if(hasMovedOnce){
			return false;
		}
		
		if(super.move(fish, direction, room) && fish != null){
			hasMovedOnce = true;
			return true;
		}
		return false;
	}

}
