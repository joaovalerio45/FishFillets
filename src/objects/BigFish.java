package objects;

import java.util.List;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class BigFish extends GameCharacter {
	
	public BigFish(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		if(getIsFacingRight()){
			return "bigFishRight";
		}else{
			return "bigFishLeft";
		}
		
	}

	public boolean canPush(Room room, Direction direction, GameObject obj){
		if(direction == Direction.DOWN || direction == Direction.UP){
			if(!room.getObjectsAt(obj.getPosition().plus(direction.asVector())).isEmpty()){
				return false;
			}
		}
		return true;
	}

	@Override
    public boolean isKilled(List<MobileObject> stack){
        int heavyCount = 0;
        for(MobileObject obj : stack){
            if(obj.isHeavy()){
                heavyCount++;
            }
        }
        if(heavyCount > 1){
            return true;
        }
        return false;
    }

}
