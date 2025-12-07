package objects.fixedObjects;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Trunk extends FixedObject {
public Trunk(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "trunk";
	}


	//se a direçao do objeto que vai interagir com o tronco for para baixo e se for um objeto pesado o tronco vai ser removido
	@Override
	public boolean interact(GameObject object, Direction direction, Room room) {
		if(direction.equals(Direction.DOWN) && object.isHeavy()){
			room.removeObject(this);
			return true;
		}
		return false;
	}
	
}
