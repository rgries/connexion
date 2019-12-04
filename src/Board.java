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

    public final static int EMPTY_COLOR = 0;
    public final static int P1_COLOR = 1;
    public final static int P2_COLOR = 2;
    /** La taille n du plateau*/
    private static int n;
    /** La valeur max des cases*/
    private static int k;
    /** Le germe utilisé pour remplir les valeur du tableau*/
    private long seed;

    private static int[][] color;   //1 for player 1, 2 for player 2
    private static int[][] value;
    private static UnionFind scoring;

    /**
     * return current size of the board
     * @return
     */
    public static int getN() {
        return n;
    }

    /**
     * get k value (k decide what is highest score possible in one case)
     * @return
     */
    public static int getK() {
        return k;
    }

    /**
     * get calculated seed
     * @return
     */
    public long getSeed() {
        return seed;
    }

    /**
     * get color at coordinates (x,y)
     * @param x
     * @param y
     * @return
     */
    public static int getColor(int x, int y){
        return color[x][y];
    }

    /**
     * get value at coordinates (x,y)
     * @param x
     * @param y
     * @return
     */
    public static int getValue(int x, int y){
        return value[x][y];
    }

    public static UnionFind getScoring(){
        return scoring;
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
        //Part 2: Union between compatible neighbor
        b.printValueBoard();
        b.connectColors();
        return b;
    }

    /**
     *  Fill the current board with informations found in a given file
     * @param  f  a file givent to extract its informations
     * @return full
     * @throws IOException
     */
   public Board RemplirGrilleFicher(File f) throws IOException {
        Board b = new Board();
        Scanner currentLine =  new Scanner(f);
        String l = currentLine.nextLine();
        Scanner currentValue = new Scanner(l);

       // Check n value
        if(!currentValue.hasNextInt()){
            throw new IllegalArgumentException("Valeur suivante non valide");
        }else {
            b.n = currentValue.nextInt();
            if(b.n <=0){
                throw new IllegalArgumentException("Valeur N non valide (doit être supérieure ou égale à 1)");
            }
        }

       // Check k value
       if(!currentValue.hasNextInt()){
           throw new IllegalArgumentException("Valeur suivante non valide.");
       }else {
           b.k = currentValue.nextInt();
           if(b.k <=0){
               throw new IllegalArgumentException("Valeur K non valide (doit être supérieure ou égale à 1)");
           }
       }
       // Initialize color and score boards
       b.value = new int[b.n][b.n];
       b.color = new int[b.n][b.n];

        int i = 0;
        int j = 0;
        int k = 0;

        //Part 1:  Fill boards
        // Board 1: Score board
        while(currentLine.hasNextLine() && i<b.n){           //Browse each line of the current file
            Scanner currentVal = new Scanner(currentLine.nextLine());
             while(currentVal.hasNextInt() && i<b.n){                //Browse each value of the current line

                 b.value[i][j] = currentVal.nextInt();               // Fill score board
                 if(b.value[i][j]<=0 || b.value[i][j]>b.k){
                    throw new IllegalArgumentException("La valeur de la case ("+i+","+j+") doit être entre 1 et "+ b.k);
                 }
                 j++;
             }
             i++;
             j=0;
        }
        i=0;

       // Board 2: Plateau des couleurs
       AscendingNode[][] coloredNode = new AscendingNode[b.n][b.n];

       while(currentLine.hasNextLine() && i<b.n+b.k){                   //Browse each line of the file

           Scanner currentVal = new Scanner(currentLine.nextLine());
           while(currentVal.hasNextInt() && j<b.n+b.k){                 //Browse each color number of the current line
               b.color[i][j] = currentVal.nextInt();                    // Fill color board

               if(b.color[i][j]<0 || b.color[i][j]>2){
                   throw new IllegalArgumentException("La valeur de la case ("+i+","+j+") doit être entre 0 et 2");
               }
               coloredNode[i][j] = new AscendingNode(k,i,j);            // Create AscendingNode for each color and fill the future idParentSet
               j++;
               k++;
           }
           i++;
           j=0;
       }
       //Create unionFind using idParentSet pre-filled
       b.scoring = new UnionFind(coloredNode);

       //Part 2: Union between compatible neighbor
       b.connectColors();
       b.printValueBoard();
       return b;
    }

    /**
     *  Connect colors and print the board
     */
    public void connectColors() {
        System.out.println("Affichage du plateau...");
        for(int i=0;i<this.n; ++i){
            for(int j=0;j<this.n; ++j){
                neighborUnion(i, j);
                System.out.printf("["+intToColor(color[i][j])+"("+i+";"+j+")] ");  // print infos: To be removed
            }
            System.out.println("  ");
        }
        System.out.println("Le plateau a rempli correctement.");
    }

    /**
     *  Print the current board
     */
    public void printBoard() {
        int i;
        int j;
        System.out.println("Affichage du plateau...");
        for(i=0;i<this.n; ++i){
            for(j=0;j<this.n; ++j){
                System.out.printf("["+intToColor(color[i][j])+"("+i+";"+j+")] ");
            }
            System.out.println("  ");
        }
    }

    /**
     *  Print the current score board
     */
    public void printValueBoard() {
        System.out.println("Valeurs du plateau....");
        for(int i=0;i<this.n; ++i){
            for(int j=0;j<this.n; ++j){
                System.out.printf("["+this.value[i][j]+"]");
            }
            System.out.println("  ");
        }
    }

    /**
     *  Print the current board
     */
    public void printSetBoard() {
        System.out.println("Ensembles du plateau....");
        for(int i=0;i<this.n; ++i){
            for(int j=0;j<this.n; ++j){
                System.out.printf("["+scoring.getParentIdSet()[i][j].getId()+"]");
            }
            System.out.println("  ");
        }
    }

    /***
     *  Check whether or not you can do union between a case and its neighbor (if it's not white
     * @param x
     * @param y
     */
    public void neighborUnion(int x, int y) {
        if(color[x][y]!=0) {
            // Union between a node and its left neighbor if their share the same color value
            if (x != 0 && color[x][y] == color[x - 1][y]) {
                this.scoring.union(this.scoring.getParentIdSet()[x][y], this.scoring.getParentIdSet()[x - 1][y]);
            }
            // Union between a node and its right neighbor if their share the same color value
            if (x != this.n - 1 && color[x][y] == color[x + 1][y]) {
                this.scoring.union(this.scoring.getParentIdSet()[x][y], this.scoring.getParentIdSet()[x + 1][y]);
            }
            // Union between a node and its lower neighbor if their share the same color value
            if (y != 0 && color[x][y] == color[x][y - 1]) {
                this.scoring.union(this.scoring.getParentIdSet()[x][y], this.scoring.getParentIdSet()[x][y - 1]);
            }
            // Union between a node and its upper neighbor if their share the same color value
            if (y != this.n - 1 && color[x][y] == color[x][y + 1]) {
                    this.scoring.union(this.scoring.getParentIdSet()[x][y], this.scoring.getParentIdSet()[x][y + 1]);
            }
        }
    }

    /**
     * Change an int into the letter associated with its color
     * For printing purpose
     * @param color
     * @return
     */
    public String intToColor(int color){
        if(color == P1_COLOR){
            return "R";
        }else if(color == P2_COLOR){
            return "B";
        }
        else{
            return "W";
        }

    }

    /**
     * Change color of a case
     * @param x
     * @param y
     * @param couleur
     */
    public void colorerCase(int x, int y, int couleur){
        color[x][y] = couleur;
    }

    /**
     * Get the size of the set  linked to the current case
     * @param x
     * @param y
     * @return
     */
    public int getSetSize(int x, int y){
        return this.scoring.getNodeAmount(this.scoring.getParentIdSet()[x][y]);
    }
}