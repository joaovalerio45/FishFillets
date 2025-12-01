package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objects.fixedObjects.HoledWall;
import objects.fixedObjects.HorizontalSteel;
import objects.fixedObjects.Trunk;
import objects.fixedObjects.VerticalSteel;
import objects.fixedObjects.Wall;
import objects.fixedObjects.Water;
import objects.interfaces.Interactable;
import objects.mobileObjects.Anchor;
import objects.mobileObjects.Bomb;
import objects.mobileObjects.Cup;
import objects.mobileObjects.Stone;
import objects.mobileObjects.Trap;
import objects.BigFish;
import objects.GameCharacter;
import objects.GameObject;
import objects.SmallFish;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Room {
	
	private List<GameObject> objects;
	private List<GameCharacter> fishes;
	private String roomName;
	
	private SmallFish sf;
	private BigFish bf;
	GameCharacter activeFish = null;
	
	public Room() {
		objects = new ArrayList<GameObject>();
		fishes = new ArrayList<GameCharacter>();
	}

	private void setName(String name) {
		roomName = name;
	}
	
	public String getName() {
		return roomName;
	}

	public List<GameObject> getObjectsAt(Point2D position) {
		List<GameObject> gameObj = new ArrayList<>();
		for(GameObject i : objects){
			if(i.getPosition().equals(position) && !(i instanceof Water)){
				gameObj.add(i);
			}
		}
		return gameObj;
	}

	public boolean isOutOfBounds(Point2D p){

		List<GameObject> objs = getObjectsAt(p);
		for(GameObject obj : objs){
			if(obj instanceof Wall){
				return false;
			}
		}
		return true;

	}
	


	
	public void addObject(GameObject obj) {
		objects.add(obj);
		ImageGUI.getInstance().addImage(obj);
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
		ImageGUI.getInstance().removeImage(obj);
	}

	public GameCharacter getActiveFish() {
		return activeFish;
	}
	
	public SmallFish getSmallFish() {
		return sf;
	}
	
	public BigFish getBigFish() {
		return bf;
	}

	public void setActiveFish(GameCharacter af){
		this.activeFish = af;
	}
	
	public void setSmallFish(SmallFish sf) {
		this.sf = sf;
		this.fishes.add(sf);
	}
	
	public void setBigFish(BigFish bf) {
		this.bf = bf;
		this.fishes.add(bf);
	}

	public void switchActiveFish(){
		if(activeFish == getBigFish()){
			activeFish = getSmallFish();
		}else{
			activeFish = getBigFish();
		}
	}
	
	public static Room readRoom(File f) {

		Room r = new Room();
		r.setName(f.getName());	

		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				r.addObject(new Water(new Point2D(i,j)));
			}
		}

		try {
			Scanner sc = new Scanner(f);
			int y = -1;
	
			while (sc.hasNextLine()) {
            	String line = sc.nextLine();
				y++;

            	for (int i = 0; i < line.length(); i++) {
                	char letter = line.charAt(i);

					if(letter == 'B'){
						BigFish bf = new BigFish(new Point2D(i,y));
						r.addObject(bf);
						r.setBigFish(bf);
					}
					
					if(letter == 'S'){
						SmallFish sf = new SmallFish(new Point2D(i,y));
						r.addObject(sf);
						r.setSmallFish(sf);
					}
					
					if(letter == 'W'){
						r.addObject(new Wall(new Point2D(i,y)));
					}

					if(letter == 'H'){
						r.addObject(new HorizontalSteel(new Point2D(i,y)));
					}
					
					if(letter == 'V'){
						r.addObject(new VerticalSteel(new Point2D(i,y)));
					}

					if(letter == 'C'){
						r.addObject(new Cup(new Point2D(i,y)));
					}

					if(letter == 'R'){
						r.addObject(new Stone(new Point2D(i,y)));
					}

					if(letter == 'A'){
						r.addObject(new Anchor(new Point2D(i,y)));
					}

					if(letter == 'b'){
						r.addObject(new Bomb(new Point2D(i,y)));
					}

					if(letter == 'T'){
						r.addObject(new Trap(new Point2D(i,y)));
					}

					if(letter == 'Y'){
						r.addObject(new Trunk(new Point2D(i,y)));
					}

					if(letter == 'X'){
						r.addObject(new HoledWall(new Point2D(i,y)));
					}

				}


			}
			r.setActiveFish(r.getBigFish());
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("Ficheiro não Encontrado");
		}
		
		return r;	
	}

}