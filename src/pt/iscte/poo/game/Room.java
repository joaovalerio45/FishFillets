package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objects.Water;
import objects.Anchor;
import objects.BigFish;
import objects.Bomb;
import objects.Cup;
import objects.GameCharacter;
import objects.GameObject;
import objects.HoledWall;
import objects.HorizontalSteel;
import objects.SmallFish;
import objects.Stone;
import objects.Trap;
import objects.Trunk;
import objects.VerticalSteel;
import objects.Wall;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Room {
	
	private List<GameObject> objects;
	private List<GameCharacter> fishes;
	private String roomName;
	
	private SmallFish sf;
	private BigFish bf;
	GameCharacter activeFish = null;
	
	public Room() {
		objects = new ArrayList<GameObject>();
		fishes.add(this.sf);
		fishes.add(this.bf);
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
			if(i.getPosition().equals(position)){
				if( !i.getName().equals("water")){
					gameObj.add(i);
				}
			}
		}
		return gameObj;
	}

	public boolean isOutOfBounds(Point2D p){

		List<GameObject> objs = getObjectsAt(p);
		for(GameObject obj : objs){
			if(!obj.getName().equals("wall")){
				return false;
			}
		}
		return true;

	}


	public void tryMove(Vector2D vec){

		Point2D to = getActiveFish().getPosition().plus(vec);

		if(activeFish == sf && objects.contains(bf)){
			if(to.equals(bf.getPosition())){
				return;
			}
		}else if(activeFish == bf && objects.contains(sf)){
			if(to.equals(sf.getPosition())){
				return;
			}
		}

		for(GameObject o : getObjectsAt(to)){

			if(o.getName().equals("trap")){
				if(!getActiveFish().canPassThrough()){
					removeObject(getActiveFish());
					switchActiveFish();
					return;
				}else{
					getActiveFish().move(vec);
				}

			}

			if(o.isObstacle(activeFish)){
				return;
			}

			if(o.isMobile()){
				if(activeFish.canPassThrough()){
					if((o.isLight() && getObjectsAt(to.plus(vec)).isEmpty()) ){
						getActiveFish().move(vec);
						o.move(vec);
						return;
					}
				} else {
					if(vec.getX() == 0){
						if(getObjectsAt(to.plus(vec)).isEmpty()){
							getActiveFish().move(vec);
							o.move(vec);
							return;
						}
					}
					if(vec.getY() == 0){
						Point2D pos = to;
						List<GameObject> mobile = new ArrayList<>();

						while(true){
							if(getObjectsAt(pos).isEmpty()){
								break;
							}
							for(GameObject obj : getObjectsAt(pos)){
								if(obj.isObstacle(activeFish)){
									return;
								}
								if(obj.isMobile()){
									mobile.add(obj);
								}
								pos = pos.plus(vec);
							}
						}

						for (int i = mobile.size() - 1; i >= 0; i--) {
   							GameObject obj = mobile.get(i);
							obj.move(vec);
						}
						getActiveFish().move(vec);


					}
				}
				return;
			}


		}

		getActiveFish().move(vec);

	}

	public void checkWeight(){
		
		for(GameObject o : getObjectsAt(sf.getPosition().plus(new Vector2D(0, -1)))){
			if(o.isMobile()){
				if(!o.isLight()){
					removeObject(sf);
					switchActiveFish();
					return;
				}else{
					for(GameObject o1 : getObjectsAt(sf.getPosition().plus(new Vector2D(0, -2)))){
						if(o1.isMobile()){
							removeObject(sf);
							switchActiveFish();
							return;
						}
					}
				}
			}
		}
		int heavyObjects = 0;
		for(int y = 0; !isOutOfBounds(bf.getPosition().plus(new Vector2D(0,-y))); y++){
			for(GameObject o : getObjectsAt(bf.getPosition().plus(new Vector2D(0, -y)))){
				if( o.isMobile() && !o.isLight()){
				heavyObjects++;
				}
			}
			if(heavyObjects >= 2){
				removeObject(bf);
				switchActiveFish();
				return;
			}
		}

	}

	public void applyGravity(){
		for(GameObject o : objects){
			if(o.isMobile()){
				List <GameObject> obj = getObjectsAt(o.getPosition().plus(new Vector2D(0, 1)));
				if(obj.isEmpty()){
					o.move(new Vector2D(0, 1));
				}
				
			}

			
		}
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
	}
	
	public void setBigFish(BigFish bf) {
		this.bf = bf;
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