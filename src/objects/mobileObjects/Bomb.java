package objects.mobileObjects;

import java.util.List;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Bomb extends MobileObject {

	private boolean isFalling = false;

    public Bomb(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "bomb";
	}
	public void tickAction(Room room){
		boolean moved = move(null,Direction.DOWN,room);

		if(moved){
			isFalling = true;
		} else {
			if(isFalling){
			explode(room);
			}
			isFalling = false;
		}
	}
			
	private void explode(Room room) {
		List<Point2D> area = getPosition().getNeighbourhoodPoints();

		for(Point2D p : area){
			List<GameObject> objects = room.getObjectsAt(p);

				for(int i = objects.size() - 1; i >= 0; i--){
					GameObject obj = objects.get(i);
					if(obj instanceof GameCharacter){
						((GameCharacter) obj).kill(room);
					}
					else {
						room.removeObject(obj);
					}
				}
			}
		room.removeObject(this);
		}

}
