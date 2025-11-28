package objects;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Trap extends MobileObject {
public Trap(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "trap";
	}

	@Override
	public boolean move(GameCharacter fish, Direction direction) {
		return false;
	}

	public boolean interact(GameCharacter fish, Direction direction){
		if(fish instanceof BigFish){
			//matar o peixe grande
			return true;
		}
		return true;
	}
	@Override
	public void sink() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'sink'");
	}



}
