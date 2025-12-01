package objects.mobileObjects;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Trap extends MobileObject {
public Trap(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "trap";
	}

	@Override
	public boolean isHeavy(){
		return true;
	}


	public boolean interact(GameCharacter fish, Direction direction, Room room){
		if(fish instanceof BigFish){
			fish.kill(room);
			return false;
		}else if(fish instanceof SmallFish){
			return true;
		}
		return false;
	}

	@Override
	public boolean move(GameCharacter fish, Direction direction, Room room) {
		Point2D to = getPosition().plus(direction.asVector());
		if(fish == null && direction == Direction.DOWN){
			if(room.getObjectsAt(to).isEmpty()){
				setPosition(to);
				return true;
			}
		}
		return false;
	}



}
