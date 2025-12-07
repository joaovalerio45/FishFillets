package objects;



import java.util.List;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class SmallFish extends GameCharacter {

	public SmallFish(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		if(getIsFacingRight()){
			return "smallFishRight";
		}else{
			return "smallFishLeft";
		}
		
	}

	@Override
	public boolean isSmall(){
		return true;
	}

	public boolean canPush(Room room, Direction direction, GameObject obj){
		if(obj.isHeavy()){
			return false;
		}
	
		Point2D nextPos = obj.getPosition().plus(direction.asVector());
		List<GameObject> objectsAtNextPos = room.getObjectsAt(nextPos);
		
		// 2. Não pode empurrar para uma posição que contenha outro objeto móvel (viola regra de "um único objeto")
		for(GameObject nextObj : objectsAtNextPos) {
			if (nextObj instanceof MobileObject) {
				return false;
			}
		}
		
		// Permite o empurrão para a posição da HoledWall ou para espaços vazios.
		// A lógica de travessia (HoledWall) é tratada pelo método move() do Cup.
		return true;
	}


	public void kill(Room room){
		if(this.equals(room.getActiveFish())){
			room.switchActiveFish();
		}
		room.removeObject(this);
	}

	@Override
    public boolean isKilled(List<MobileObject> stack){
        if(stack.size() > 1){
            return true;
        }
        for(MobileObject obj : stack){
            if(obj.isHeavy()){
                return true;
            }
        }
        return false;
    }
	
}
