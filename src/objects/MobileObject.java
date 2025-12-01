package objects;

import java.util.List;

import objects.interfaces.Movable;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

//decidir se adicionamos room aos parametros do movable e do interactable para saber se a posicao para a qual o objeto vai esta vazia
//se adicionarmos o room às duas interfaces tambem vai ser possivel fazer o kill dentro dos peixes(criamos no gameCharacter)
//e sempre que for preciso matar um dos peixes em alguma das interacoes é so invocar o kill


public abstract class MobileObject extends GameObject implements Movable{
    
	public MobileObject(Point2D position) {
		super(position);
	}

	public boolean move(GameCharacter fish, Direction direction, Room room) {
		Point2D to = getPosition().plus(direction.asVector());
        List<GameObject> targetObjs = room.getObjectsAt(to);


        if (targetObjs.isEmpty()) {
            setPosition(to);
            return true;
        }

        GameObject obstacle = targetObjs.get(0);

        if (obstacle.isTraversable(this)) {
            setPosition(to);
            return true;
        }

        if (obstacle instanceof Movable) {
            if (fish == null || fish instanceof SmallFish){
				return false;
			}

            if (((Movable) obstacle).move(fish,direction,room)) {
                setPosition(to);
                return true;
            }
        }
        
        return false;
	}
	
	public void sink(){
		
	}
	
	
	
}
