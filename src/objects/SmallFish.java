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
		//nao pode empurrar objetos pesados
		if(obj.isHeavy()){
			return false;
		}
		
		//obtem os objetos na posição á frente do objeto que quer empurrar
		Point2D nextPos = obj.getPosition().plus(direction.asVector());
		List<GameObject> objectsAtNextPos = room.getObjectsAt(nextPos);
		
		//se houver mais do que um objeto, retorna false e nao pode empurrar
		for(GameObject nextObj : objectsAtNextPos) {
			if (nextObj instanceof MobileObject) {
				return false;
			}
		}
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
		//se tiver mais do que um objeto suportado morre
        if(stack.size() > 1){
            return true;
        }
		//se tiver um objeto pesado suportado morre
        for(MobileObject obj : stack){
            if(obj.isHeavy()){
                return true;
            }
        }
        return false;
    }
	
}
