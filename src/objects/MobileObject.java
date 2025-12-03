package objects;

import java.util.List;

import objects.interfaces.Movable;
import objects.interfaces.Tickable;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

//decidir se adicionamos room aos parametros do movable e do interactable para saber se a posicao para a qual o objeto vai esta vazia
//se adicionarmos o room às duas interfaces tambem vai ser possivel fazer o kill dentro dos peixes(criamos no gameCharacter)
//e sempre que for preciso matar um dos peixes em alguma das interacoes é so invocar o kill


public abstract class MobileObject extends GameObject implements Movable,Tickable{
    
	public MobileObject(Point2D position) {
		super(position);
	}

    public boolean interact(GameObject object, Direction direction, Room room){
        if (object instanceof GameCharacter) {
            GameCharacter fish = (GameCharacter) object;
        
            if (!fish.canPush(room, direction, this)) {
                return false;
            }
        
            return this.move(fish, direction, room);
        }
    return false;
    }
    
	public boolean move(GameCharacter fish, Direction direction, Room room) {
        Point2D nextPos = getPosition().plus(direction.asVector());

        List<GameObject> objects = room.getObjectsAt(nextPos);


        for(GameObject obj : objects){

            if (obj.interact(this, direction, room)) {
                continue;
            }

            if (obj.isTraversable(this)) {
                continue;
            }

            if(!(obj instanceof MobileObject)){
                return false;
            }
            if(fish != null && fish.canPush(room,direction, obj)){
                MobileObject nextMobile = (MobileObject) obj;
                if(nextMobile.move(fish, direction, room)){
                    this.setPosition(nextPos);
                    return true;
                }
            }
            return false;
        }
        this.setPosition(nextPos);
        return true;
    }

	@Override
	public void tickAction(Room room) {


		//Comportamento default para objetos móveis : ação da gravidade

		move(null, Direction.DOWN, room);


	}
	
	
	
	
}
