package objects;

import pt.iscte.poo.utils.Point2D;

public class Cup extends MobileObject {
    public Cup(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "cup";
	}

	@Override
	public boolean isObstacle(GameCharacter gc){
        return false;
    }

	@Override
	public boolean isLight(){
		return true;
	}
}
