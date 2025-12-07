package objects;

import objects.interfaces.Interactable;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;


// Classe base para todo os objetos de jogo
public abstract class GameObject implements ImageTile, Interactable{
	
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

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}


	// flag para objetos pesados
	public boolean isHeavy(){
		return false;
	}

	// flag para objetos leves
	public boolean isSmall(){
		return false;
	}

	//flag para objetos que sao atravessáveis por um outro objeto(obj)
	public boolean isTraversable(GameObject obj){
		return false;
	}

	//flag para inimigos
	public boolean isEnemy(){
		return false;
	}

}
