package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objects.fixedObjects.*;
import objects.mobileObjects.*;
import objects.*;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Room{
	
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

	public List<GameCharacter> getFishes(){
		return fishes;
	}

	//lista de objetos numa posicao dada
	public List<GameObject> getObjectsAt(Point2D position) {
		List<GameObject> gameObj = new ArrayList<>();
		for(GameObject i : objects){
			if(i.getPosition().equals(position) && !(i instanceof Water)){
				gameObj.add(i);
			}
		}
		return gameObj;
	}

	public List<GameObject> getObjects(){
		return objects;
	}
	


	
	public void addObject(GameObject obj) {
		objects.add(obj);
		ImageGUI.getInstance().addImage(obj);
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
		if(obj instanceof GameCharacter){
			fishes.remove(obj);
		}
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
		
		//garante que ha pelo menos um peixe
		if (fishes.isEmpty()){
			return;
		}

		//encontra a posicao do peixe que esta ativo 
    	int currentIndex = fishes.indexOf(activeFish);
		//calcula o indice do proximo peixe
    	int nextIndex = (currentIndex + 1) % fishes.size();

		//define o peixe na posicao calculada como peixe ativo
    	setActiveFish(fishes.get(nextIndex));
	}
	
	public static Room readRoom(File f) {

		Room r = new Room();
		r.setName(f.getName());	

		//coloca agua em todas as posicoes do nivel
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				r.addObject(new Water(new Point2D(i,j)));
			}
		}

		try {
			//le o ficheiro f 
			Scanner sc = new Scanner(f);
			int y = -1;
			
			//enquanto o ficheiro tiver uma linha, le todas as letras da linha ate acabar e coloca o objeto correspondente a essa letra no nivel
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
					if(letter == 'F'){
						r.addObject(new Buoy(new Point2D(i,y)));
					}
					if(letter == 'K'){
						r.addObject(new Krab(new Point2D(i,y)));
					}
				}


			}

			//coloca o primeiro peixe da lista como peixe ativo
			if (!r.fishes.isEmpty()) {
				r.setActiveFish(r.fishes.get(0));
			} else {
				System.err.println("Nenhum peixe encontrado no nivel: " + f.getName());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("Ficheiro não Encontrado");
		}
		
		return r;	
	}

	
	public void checkExits() {

		//cria uma cópia da lista para evitar bugs
		List<GameCharacter> fishesCopy = new ArrayList<>(fishes);

		//percorre a lista de peixes, e ve algum deles passou das bordas do nivel
        for(GameCharacter fish : fishesCopy){
            Point2D p = fish.getPosition();
            if(p.getX() == -1 || p.getX() == 10 || p.getY() == -1 || p.getY() == 10) {
				//se havia dois peixes, muda para o peixe que ainda nao passou das bordas
                if(activeFish == fish){
                    switchActiveFish();
                }
				//remove o peixe da lista de objetos
                removeObject(fish);
            }
        }
    }
}