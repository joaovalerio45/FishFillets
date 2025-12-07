package objects.mobileObjects;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Anchor extends MobileObject{
	
	//variavel que vai verificar se a ancora ja se moveu uma vez
	boolean hasMovedOnce;

    public Anchor(Point2D p) {
		super(p);
		this.hasMovedOnce = false;
	}

	//objeto é pesado
	@Override
	public boolean isHeavy(){
		return true;
	}
	
	@Override
	public String getName() {
		return "anchor";
	}


	//
	@Override
	public boolean move(GameCharacter fish, Direction direction, Room room) {
		// Se fish for null o movimento é automatico (gravidade), permite que a ancora caia
		if(fish == null){
			return super.move(fish, direction, room);
		}
		// a ancora so pode ser empurrada na horizontal
		if (direction == Direction.UP || direction == Direction.DOWN) {
                return false;
        }
		//se ja se moveu, nao pode ser mais empurrada
		if(hasMovedOnce){
			return false;
		}
		//se ainda nao foi movida e fish nao for null o peixe grande vai move la e hasMovedOnce passa a true 
		// (super.move() vai buscar a funcao implementada nos mobile objects, que verifica qual é o tipo de peixe e se o objeto é pesado ou nao, etc)
		if(super.move(fish, direction, room) && fish != null){
			hasMovedOnce = true;
			return true;
		}
		return false;
	}

}
