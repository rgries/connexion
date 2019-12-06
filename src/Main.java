import structure.*;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws IOException {
        Board b = new Board();
        // choix entre grille pré-faite ou aléatoire
        try {
            try{
                b = b.RemplirGrilleFicher(new File(args[0]));
            }catch(FileNotFoundException e0){
                System.out.println("Chemin de fichier non valide:");
                b = b.RemplirGrilleAleatoire(5,3);

            }

        }
        catch(ArrayIndexOutOfBoundsException e1){   //Aucun fichier entrée
            System.out.println("Aucun fichier:");
            b = b.RemplirGrilleAleatoire(5,3);
        }

        jouerDeuxHumains(b);
    }


    /**
     *  Initialize the game according to the generated board
     * @param b
     */
    private static void jouerDeuxHumains(Board b) {
        Player p1 = new Player(b.P1_COLOR);
        Player p2 = new Player(b.P2_COLOR);
        Game g = new Game(p1, p2, b);

        Player current = p1;
        // Debut du jeu
        while(g.getWhite()!= 0) {
            g.playerTurn(current);
            if(current == p1){
                current = p2;
            }else{
                current = p1;
            }
            System.out.println(" ");
        }
        // Fin du jeu
        g.endGame();
    }

    static void testingUnionFind(){
        AscendingNode root1 = new AscendingNode(1,0,0);
        AscendingNode child1 = new AscendingNode(2,0,1, root1);
        AscendingNode child2 = new AscendingNode(3,0,2,root1);
        AscendingNode child3 = new AscendingNode(4,1,0, child1);
        AscendingNode child4 = new AscendingNode(5,1,1, child3);

        AscendingNode root2 = new AscendingNode(6,1,2);
        AscendingNode child5 = new AscendingNode(7,2,0, root2);
        AscendingNode child6 = new AscendingNode(8,2,1, root2);
        AscendingNode child7 = new AscendingNode(9,2,2, child6);

        AscendingNode[][] a = new AscendingNode[3][3];
        a[0][0] = root1.getParent();
        a[0][1] = child1.getParent();
        a[0][2] = child2.getParent();
        a[1][0] = child3.getParent();
        a[1][1] = child4.getParent();
        a[1][2] = root2.getParent();
        a[2][0] = child5.getParent();
        a[2][1] = child6.getParent();
        a[2][2] = child7.getParent();

        UnionFind unionFind = new UnionFind(a);

        System.out.println("n : parent");
        for(int i = 0; i< a.length; i++){
            for(int j = 0; j< a.length; j++){
            if(a[i] != null) System.out.println("(" +i +", "+j + ") : " + a[i][j].getId());
        }
        }
        System.out.println("********");
        unionFind.union(a[1][1],a[2][2]);

        for(int i = 0; i< a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i] != null) System.out.println("(" +i +", "+j + ") : " + a[i][j].getId());
            }
        }


    }
}