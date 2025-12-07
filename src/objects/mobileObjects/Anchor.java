package objects.mobileObjects;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Anchor extends MobileObject{
	
	//variavel que vai verificar se a ancora ja se moveu uma vez
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
	public boolean move(GameCharacter fish, Direction direction, Room room) {
		if(fish == null){
			return super.move(fish, direction, room);
		}
		
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
