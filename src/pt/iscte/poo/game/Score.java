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

    @Override
    public int compareTo(Score o) {
        if (this.seconds != o.seconds) {
            return this.seconds - o.seconds;
        }
        return this.moves - o.moves;
    }
}
