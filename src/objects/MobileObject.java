package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class MobileObject extends GameObject{
    
	public MobileObject(Point2D position) {
		super(position);
	}

	@Override
<<<<<<< HEAD
	public boolean isObstacle(GameCharacter gc){
		return true;
	}
	@Override
	public boolean isSupport() {
		return true;
=======
	public int getLayer() {
		return 1;
>>>>>>> f4ad5b500a29c5f7dae51244fb50636e017e6d88
	}
}
