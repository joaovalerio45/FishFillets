package pt.iscte.poo.game;

public class Score implements Comparable<Score>{
    
    private int seconds = 0;
    private int moves = 0;
    

    public Score(int seconds, int moves){
        this.seconds = seconds;
        this.moves = moves;
    }
    
    @Override
    public String toString(){
        return "Time: " + seconds + " seconds " + "Moves: " + moves;
    }

    //utiliza a interface Comparable para comparar o tempo e se o tempo for igual compara o movimento
    @Override
    public int compareTo(Score o) {
        //se o valor for negativo significa que esta pontuacao é menor e deve vir primeiro
        if (this.seconds != o.seconds) {
            return this.seconds - o.seconds;
        }
        //o menor numero de movimentos é considerado melhor
        return this.moves - o.moves;
    }
}
