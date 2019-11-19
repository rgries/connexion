import java.util.Random;

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
    public Board RemplirGrilleFicher(){
        return null;
    }


    public void colorerCase(int x, int y, int couleur){
        color[x][y] = couleur;
    }

}
