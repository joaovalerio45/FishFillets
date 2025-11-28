package objects;

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
	
	@Override
	public boolean interact(GameCharacter fish, Direction direction) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'interact'");
	}
}
