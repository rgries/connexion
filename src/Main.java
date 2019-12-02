import structure.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //testingUnionFind();
        Board b = new Board();

        b = b.RemplirGrilleFicher(new File("C:/Users/Geppetto/connexion/src/test.txt"));

        Player p1 = new Player(1);
        Player p2 = new Player(2);

        Game g = new Game(p1, p2, b);

        Player current = p1;
        while(g.getWhite()!= 0) {
            g.playerTurn(current);
            if(current == p1){
                current = p2;
            }else{
                current = p1;
            }
            System.out.println(" ");
        }
        g.endGame();
    }
}