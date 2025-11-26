package objects;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class GameObject implements ImageTile{
	
	private Point2D position;
		
	public GameObject(Point2D position) {
		this.position = position;
	}


	public void setPosition(int i, int j) {
		position = new Point2D(i, j);
	}
	
	public void setPosition(Point2D position) {
		this.position = position;
	}

	public void move(Vector2D dir) {
		setPosition(getPosition().plus(dir));

	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}	

	public boolean isSupport(){
		return true;
	}

	public boolean isObstacle(GameCharacter gc){
		return false;
	}

	public boolean isMobile(){
		return false;
	}

	public boolean isLight(){
		return false;
	}

}
