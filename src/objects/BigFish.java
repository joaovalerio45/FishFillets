package objects;

import java.util.List;

import objects.mobileObjects.Trap;
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
		// se o movimento for vertical
		if(direction == Direction.DOWN || direction == Direction.UP){
			// houver mais do que um objeto em cadeia na vertical
			if(!room.getObjectsAt(obj.getPosition().plus(direction.asVector())).isEmpty()){
				return false;
			}
		}
		// qualquer outro caso pode empurrar
		return true;
	}

	@Override
    public boolean interact(GameObject object, Direction direction, Room room) {
		// se uma trap interagir com o BigFish( cair em cima dele) mata-o e liberta a posição
        if (object instanceof Trap) {
            this.kill(room);
            return true;
        }
        return false;
	}


	public void kill(Room room){
		if(this.equals(room.getActiveFish())){
			room.switchActiveFish();
		}
		room.removeObject(this);
	}


	@Override
    public boolean isKilled(List<MobileObject> stack){
		//se tiver 2 ou mais objetos pesados em cima, morre
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
