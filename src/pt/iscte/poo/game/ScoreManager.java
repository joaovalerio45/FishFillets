package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ScoreManager{

    private List<Score> highscores = new ArrayList<>();

    public ScoreManager(){
        highscores = new ArrayList<>();
        readHighscores(new File("scores/highscores.txt"));
    }

    public void addScore(Score s){
        highscores.add(s);
        highscores.sort((score1, score2) -> score1.compareTo(score2));
        if (highscores.size() > 10) {
            highscores.subList(10, highscores.size()).clear();
        }
    }

    public void readHighscores(File f){
        
        if (!f.exists()) {
            return;
        }

        try {
            Scanner sc = new Scanner(f);
            
            if (sc.hasNextLine()) {
                sc.nextLine();
            }

            // 2. Ler linha a linha
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // A linha tem o formato: "Time: 123seconds Moves: 456"
                
                // Usamos um Scanner auxiliar para ler dentro da linha
                Scanner lineScanner = new Scanner(line);
                
                if (lineScanner.hasNext()) {

                    lineScanner.next();
                    
                    if (lineScanner.hasNextInt()) {
                        int seconds = lineScanner.nextInt();
                    
                        lineScanner.next();
                        lineScanner.next();
                    
                        if (lineScanner.hasNextInt()) {
                            int moves = lineScanner.nextInt();
                            highscores.add(new Score(seconds, moves));
                        }
                    }
                }
                lineScanner.close();
            }
            sc.close();
        } catch (FileNotFoundException e) {
             throw new IllegalArgumentException("File not found");
        }
    }

    public void writeHighscores(){
        try {
            PrintWriter pw = new PrintWriter(new File("scores/highscores.txt"));
            pw.println("Highscores: ");
            for(Score s : highscores){
                pw.println(s.toString());
            }
            pw.close();

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found");
        }
    }

    public String writeList(){
        String text = "";
        int i = 1;
        
        for (Score s : highscores) {
            text += i + ". " + s.toString() + "\n";
            i++;
        }
        
        return text;
    }


}
