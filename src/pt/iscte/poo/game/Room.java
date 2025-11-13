package pt.iscte.poo.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import objects.Water;
import objects.BigFish;
import objects.GameObject;
import objects.SmallFish;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Room {
	
	private List<GameObject> objects;
	private String roomName;
	
	private SmallFish sf;
	private BigFish bf;
	
	public Room() {
		objects = new ArrayList<GameObject>();
	}

	private void setName(String name) {
		roomName = name;
	}
	
	public String getName() {
		return roomName;
	}
	
	public void addObject(GameObject obj) {
		objects.add(obj);
		ImageGUI.getInstance().addImage(obj);
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
		ImageGUI.getInstance().removeImage(obj);
	}
	
	public SmallFish getSmallFish() {
		return sf;
	}
	
	public BigFish getBigFish() {
		return bf;
	}
	
	public void setSmallFish(SmallFish sf) {
		this.sf = sf;
	}
	
	public void setBigFish(BigFish bf) {
		this.bf = bf;
	}
	
	public static Room readRoom(File f) {
		
		// Temos de adicionar aqui:
		// água no fundo(layer 0 )
		// leitura dos objetos obtidos do ficheiro
		
		Room r = new Room();
		r.setName(f.getName());
		
		
		return r;
		
	}
	
}