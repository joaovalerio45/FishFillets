package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class MobileObject  extends GameObject implements Sinkable, Movable{
    
	public MobileObject(Point2D position) {
		super(position);
	}

	public void sink(){
		
	}
	
	
	
}
