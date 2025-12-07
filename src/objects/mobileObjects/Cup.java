package objects.mobileObjects;

import objects.*;


import pt.iscte.poo.utils.Point2D;

public class Cup extends MobileObject {
    public Cup(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "cup";
	}

	//pode passar na holed wall
	@Override
	public boolean isSmall(){
		return true;
	}
}
