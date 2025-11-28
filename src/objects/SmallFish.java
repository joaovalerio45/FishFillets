package objects;


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
	public boolean interact(GameCharacter fish, Direction direction) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'interact'");
	}
	
}
