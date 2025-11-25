package objects;

//import java.util.Random;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class GameCharacter extends GameObject {

	private boolean isFacingRight = false;

	public boolean getIsFacingRight(){
		return isFacingRight;
	}
	
	public GameCharacter(Point2D p) {
		super(p);
	}
	
	public void move(Vector2D dir) {
		// Random rand = new Random();
		// Point2D destination = new Point2D(rand.nextInt(10), rand.nextInt(10)); 
		// setPosition(destination);
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
	
	public abstract boolean canPassThrough();
}