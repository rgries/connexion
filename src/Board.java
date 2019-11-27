import structure.AscendingNode;
import structure.UnionFind;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Représente le plateau de jeu. les Cases sont données par leur coordonnées x et y.
 */

public class Board {

    public final static int EMPTY_COLOR = -1;
    public final static int P1_COLOR = 1;
    public final static int P2_COLOR = 2;
    /** La taille n du plateau*/
    private int n;
    /** La valeur max des cases*/
    private int k;
    /** Le germe utilisé pour remplir les valeur du tableau*/
    private long seed;

    private static int[][] color;   //1 for player 1, 2 for player 2
    private static int[][] value;
    private UnionFind scoring;


    public int size(){
        return n;
    }
    public int getN() {
        return n;
    }

    public int getK() {
        return k;
    }

    public long getSeed() {
        return seed;
    }

    public static int getColor(int x, int y){
        return color[x][y];
    }

    public static int getValue(int x, int y){
        return value[x][y];
    }

    public Board RemplirGrilleAleatoire(int n, int k){
        //TODO exception when n not an int or negative.
        //TODO maybe should be create by Game instead
        Board b = new Board();
        b.n = n;
        b.k = k;

        Board.color = new int[n][n];
        Board.value = new int[n][n];

        //choose a seed
        Random rand = new Random();
        seed = rand.nextLong();
        rand.setSeed(seed);

        //fill the board.
        for (int i=0; i < n; ++i) {
            for (int j=0; j < n; ++j) {
                color[i][j] = EMPTY_COLOR;   // -1 meaning, no player owns the case
                //TODO see method "Random->ints" and check if a stream would be faster.
                value[i][j] = rand.nextInt(k+1);
            }
        }
        return b;
    }

    //TODO
   public Board RemplirGrilleFicher(File f) throws IOException {
        Board b = new Board();
        Scanner currentLine =  new Scanner(f);
        String l = currentLine.nextLine();
        Scanner currentValue = new Scanner(l);

        if(!currentValue.hasNextInt()){
            throw new IllegalArgumentException("Incorrect next integer");
        }else b.n = currentValue.nextInt();

       b.value = new int[b.n][b.n];

       if(!currentValue.hasNextInt()){
           throw new IllegalArgumentException("Incorrect next integer");
       }else b.k = currentValue.nextInt();

        int i = 0;
        int j = 0;
        int k = 0;
        //Part 1:  Fill boards
        // Board 1: Plateau des points
        while(currentLine.hasNextLine() && i<b.n){   //parcours chaque lignes du fichier
            Scanner currentVal = new Scanner(currentLine.nextLine());
             while(currentVal.hasNextInt()){
                 b.value[i][j] = currentVal.nextInt();
                 j++;
             }
             i++;
             j=0;
        }
        i=0;

       b.color = new int[b.n][b.n];

       // Board 2: Plateau des couleurs
       AscendingNode[][] coloredNode = new AscendingNode[b.n][b.n];

       while(currentLine.hasNextLine() && i<b.n+b.k){   //parcours chaque lignes du fichier

           Scanner currentVal = new Scanner(currentLine.nextLine());
           while(currentVal.hasNextInt()){
               b.color[i][j] = currentVal.nextInt();
               coloredNode[i][j] = new AscendingNode(k,i,j);
               System.out.printf(" "+b.color[i][j]);  //To be removed
               j++;
               k++;
           }
           i++;
           j=0;
           System.out.println(" ");  //To be removed
       }

       b.scoring = new UnionFind(coloredNode);
       //Part 2: Union between compatible neighbor
       System.out.println("Printing  map of current connected color...");
       for(i=0;i<b.n; ++i){
           for(j=0;j<b.n; ++j){
               if(i!=0 && color[i][j]==color[i-1][j]){
                   b.scoring.union(b.scoring.getParentIdSet()[i][j],b.scoring.getParentIdSet()[i-1][j]);
               }
               if(i!=b.n-1 && color[i][j]==color[i+1][j]){
                   b.scoring.union(b.scoring.getParentIdSet()[i][j],b.scoring.getParentIdSet()[i+1][j]);
               }
               if(j!=0 && color[i][j]==color[i][j-1]){
                   b.scoring.union(b.scoring.getParentIdSet()[i][j],b.scoring.getParentIdSet()[i][j-1]);
               }
               if(j!=b.n-1 && color[i][j]==color[i][j+1]){
                   b.scoring.union(b.scoring.getParentIdSet()[i][j],b.scoring.getParentIdSet()[i][j+1]);
               }
               System.out.printf("(united="+b.scoring.getParentIdSet()[i][j].getId()+" ; col="+b.color[i][j]+" ; val="+b.value[i][j]+") ");  //To be removed
           }
           System.out.println("  ");
       }
        return b;
    }



    public void colorerCase(int x, int y, int couleur){
        color[x][y] = couleur;
    }
}