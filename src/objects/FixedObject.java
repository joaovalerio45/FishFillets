package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

//classe para objetos fixos( nao implementa movable)

public  abstract class FixedObject extends GameObject {

    public FixedObject(Point2D position) {
		super(position);
	}
    
	public boolean interact(GameObject object, Direction direction, Room room){
		return false;
	}

}
