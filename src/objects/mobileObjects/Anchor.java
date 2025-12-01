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
	public boolean interact(GameCharacter fish, Direction direction, Room room) {

		if (direction == Direction.UP || direction == Direction.DOWN) {
                return false;
        }

		if(fish instanceof SmallFish){
			return false;
		}else if(fish instanceof BigFish){
			if(hasMovedOnce){
				return false;
			}else{
				this.setPosition(getPosition().plus(direction.asVector()));
				hasMovedOnce = true;
				return true;
			}
		}
		return false;
	}


	@Override
	public boolean move(GameCharacter fish, Direction direction, Room room) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'move'");
	}


}
