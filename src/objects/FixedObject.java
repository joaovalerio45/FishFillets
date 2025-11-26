package objects;

import pt.iscte.poo.utils.Point2D;

public  abstract class FixedObject extends GameObject {

    public FixedObject(Point2D position) {
		super(position);
	}
    
    @Override
    public boolean isObstacle(GameCharacter gc){
        return true;
    }
}
