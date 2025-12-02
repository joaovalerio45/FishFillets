package objects;

import java.util.ArrayList;
import java.util.List;

import objects.interfaces.Tickable;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.*;

public abstract class GameCharacter extends GameObject implements Tickable {

	private boolean isFacingRight = false;

	public boolean getIsFacingRight(){
		return isFacingRight;
	}
	
	public GameCharacter(Point2D p) {
		super(p);
	}
	
	public void moveFish(Vector2D dir) {
		if(dir.getX() < 0){
			isFacingRight = false;
		}else if(dir.getX() > 0){
			isFacingRight = true;
		}


		
		setPosition(getPosition().plus(dir));	
	}


	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public boolean interact(GameObject object, Direction direction, Room room) {
		return false;
	}


	public void checkKilling(Room room){
        List<MobileObject> stack = new ArrayList<>();
        Point2D currentPos = getPosition().plus(Direction.UP.asVector());
        
        while(currentPos.getY() >= 0){
            List<GameObject> objs = room.getObjectsAt(currentPos);

            if(objs.isEmpty()){
                break;
            }
            boolean foundSupport = false;

            for(GameObject o : objs){
                if(o instanceof FixedObject || o instanceof GameCharacter){
                    foundSupport = true;
                    break;
                } else if(o instanceof MobileObject){
                    stack.add((MobileObject) o);
                }
            }
            if(foundSupport){
                break;
            }

            currentPos = currentPos.plus(Direction.UP.asVector());
        }
        if(this.isKilled(stack)){
            this.kill(room);
        }
    }
	
	

	@Override
    public void tickAction(Room room){
        this.checkKilling(room);
    }


	public abstract boolean isKilled(List<MobileObject> stack);
	public abstract void kill(Room room);
	public abstract boolean canPush(Room room ,Direction direction, GameObject object);
}