package objects;

import java.util.ArrayList;
import java.util.List;

import objects.interfaces.Tickable;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.*;

// classe para as personagens controladas pelo jogador

public abstract class GameCharacter extends GameObject implements Tickable {

    // verificar a posicão que o peixe está virado
	private boolean isFacingRight = false;

	public boolean getIsFacingRight(){
		return isFacingRight;
	}
	
	public GameCharacter(Point2D p) {
		super(p);
	}
	
    //implementa o movimento do fish (sem verificações)
	public void moveFish(Vector2D dir) {
		if(dir.getX() < 0){
			isFacingRight = false;
		}else if(dir.getX() > 0){
			isFacingRight = true;
		}


		
		setPosition(getPosition().plus(dir));	
	}


	@Override
	public int getLayer() {
		return 2;
	}


	@Override
	public boolean interact(GameObject object, Direction direction, Room room) {
		return false;
	}


    // vai buscar todos os objetos que estao por cima do peixe, e se isKilled() for true , mata o peixe
	public void checkKilling(Room room){
        //cria lista
        List<MobileObject> stack = new ArrayList<>();
        Point2D currentPos = getPosition().plus(Direction.UP.asVector());
        
        //todas as posicoes acima do peixe
        while(currentPos.getY() >= 0){
            //todos os objetos em cada uma dessas posições
            List<GameObject> objs = room.getObjectsAt(currentPos);

            //se nao houver nenhum salta para a proxima iteração
            if(objs.isEmpty()){
                break;
            }
            boolean foundSupport = false;

            //procura um suporte, se encontrar quebra o loop e chama isKilled() com a lista
            for(GameObject o : objs){
                if(o instanceof FixedObject || o instanceof GameCharacter){
                    foundSupport = true;
                    break;
                } else if(o instanceof MobileObject){
                    stack.add((MobileObject) o);
                }
            }
            if(foundSupport){
                break;
            }

            currentPos = currentPos.plus(Direction.UP.asVector());
        }
        // mata o peixe se necessário
        if(this.isKilled(stack)){
            this.kill(room);
        }
    }
	
	

	@Override
    public void tickAction(Room room){
        this.checkKilling(room);
    }

    //verifica se, dado os objetos por cima do GameCharacter, ele morre
	public abstract boolean isKilled(List<MobileObject> stack);

    //mata o peixe (remove da lista room.objects e muda o activefish no room)
	public abstract void kill(Room room);

    //retorna um boolean que indica se o Bigfish consegue empurrar um determinado objeto num room
	public abstract boolean canPush(Room room ,Direction direction, GameObject object);
}