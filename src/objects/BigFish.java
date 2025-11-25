package objects;

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
	public int getLayer() {
		return 2;
	}

	@Override
	public boolean canPassThrough(){
		return false;
	}
}
