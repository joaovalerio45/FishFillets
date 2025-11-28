package objects;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Direction;

//tirar o sinkable porque todos os mobileobjects afundam e por isso implementar a funcao diretamente no mobileObject
//decidir se adicionamos room aos parametros do movable e do interactable para saber se a posicao para a qual o objeto vai esta vazia
//se adicionarmos o room às duas interfaces tambem vai ser possivel fazer o kill dentro dos peixes(criamos no gameCharacter)
//e sempre que for preciso matar um dos peixes em alguma das interacoes é so invocar o kill


public abstract class MobileObject extends GameObject implements Sinkable, Movable{
    
	public MobileObject(Point2D position) {
		super(position);
	}

	@Override
	public boolean interact(GameCharacter fish, Direction direction){
		return move(fish,direction);
	}
	
	@Override
	public boolean move(GameCharacter fish, Direction direction){
		return false;
	}
	public void sink(){
		
	}
	
	
	
}
