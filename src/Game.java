import java.util.Scanner;

public class Game {

    private Player player1;
    private Player player2;
    private Board board;

    /**
     * Constructor
     * @param player1
     * @param player2
     * @param board
     */
    public Game(Player player1, Player player2, Board board){
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
    }

    /**
     * Print the list of cells in the same group as the cell in parameter.
     * @param x
     * @param y
     */
    public void afficherComposante(int x, int y){
        int[][] list = getGroupOf(x,y);
        //ToDO print
    }

    /**
     * Return an array of the cases contained in the same group as (x,y)
     * @return An array of point (int x, int y) of size: int[2][n]
     */
    public int[][] getGroupOf(int x, int y) {

        if (Board.getColor(x, y) == Board.EMPTY_COLOR) {    //no player owns the case
            int[][] res = new int[2][1];
            res[0][0] = x;
            res[1][0] = y;
            return res;
        } else if (Board.getColor(x, y) == Board.P1_COLOR) {   //P1
            return player1.getGroupOf(x,y);
        } else {    //P2
            return player2.getGroupOf(x,y);
        }
    }

    /**
     * Turn done by one of the two players
     * @param p
     */
    public void playerTurn(Player p){
        System.out.println("**** Tour de joueur "+p.getColor()+" ("+this.board.intToColor(p.getColor())+") ****");
        //scan player's answer and store it
        int[] coord = new int[2];

        // If answer incorrect, repeat
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez les coordonnées de la case: ");

            int i = 0;
            while (sc.hasNextInt() && i < 2) {                //Browse each value of the current line
                coord[i] = sc.nextInt();                      // Fill score board
                i++;
            }
        }while(!acceptedAnswer(coord[0], coord[1]));

        //Color case with player's color
        this.board.colorerCase(coord[0],coord[1],p.getColor());
        this.board.neighborUnion(coord[0],coord[1]);

        this.board.printBoard();
    }

    /**
     * End of the game
     * Check which is the biggest size of set for each player
     * Count score of each highest set (TO BE IMPLEMENTED)
     */
    public void endGame(){
        this.board.connectColors();
        this.board.printSetBoard();

        int player1FinalSet = 0;
        int player2FinalSet = 0;
        int tmp;
        //Check which is the biggest set size for each player
        for(int i=0; i<this.board.getN(); ++i){
            for(int j=0; j<this.board.getN(); ++j){
                tmp = this.board.getSetSize(i,j);
                if(Board.getColor(i,j)==1){
                    if(player1FinalSet < tmp){
                        player1FinalSet = tmp;

                    }
                }
                else{
                    if(player2FinalSet < tmp){
                        player2FinalSet = tmp;
                    }

                }
            }
        }
        System.out.println("Plus grand nombre de cases connectées pour Joueur 1: "+player1FinalSet);
        System.out.println("Plus grand nombre de cases connectées pour Joueur 2: "+player2FinalSet);


        System.out.println("Calcul des scores non implémentés :(");
    }


    /**
     *  Check whether the coordinates choosen by user are corrects
     * @param x
     * @param y
     * @return
     */
    public boolean acceptedAnswer(int x, int y){
        if(x >= this.board.getN() || x < 0 || y >= this.board.getN() || y < 0 ){
            System.out.println("Cette case est hors du plateau.");
            return false;
        }
        else{
            if (Board.getColor(x, y) != 0) {
                System.out.println("Cette case est déjà colorée, veuillez en choisir une autre.");return false;
                }
        }
        return true;
    }

    /**
     * Count remaining non-colored cases
     * @return remaining cases
     */
    public int getWhite() {
        int white = 0;
        for(int i=0;i<this.board.getN(); ++i){
            for(int j=0;j<this.board.getN(); ++j){
                if(Board.getColor(i, j)==0){
                    white ++;
                }
            }
        }
        System.out.println("Cases restantes vide: "+white);  // print infos: To be removed
        return white;
    }


}
