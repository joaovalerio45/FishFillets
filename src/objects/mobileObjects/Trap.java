package objects.mobileObjects;

import objects.*;
import pt.iscte.poo.game.Room;
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
	public boolean isHeavy(){
		return true;
	}


	public boolean interact(GameCharacter fish, Direction direction, Room room){
		if(fish instanceof BigFish){
			//matar o peixe grande
			return false;
		}else if(fish instanceof SmallFish){
			return true;
		}
		return false;
	}
	@Override
	public void sink() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'sink'");
	}

	@Override
	public boolean move(GameCharacter fish, Direction direction, Room room) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'move'");
	}



}
