package objects;

import java.awt.event.KeyEvent;

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
	public int getLayer() {
		return 2;
	}

	@Override
	public boolean canPassThrough(){
		return true;
	}
}
