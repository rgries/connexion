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

    /*
     * 1) MAIN GAME METHODS
     */

    /**
     * Turn done by one of the two players
     * @param p
     */
    public void playerTurn(Player p){
        System.out.println("**** Tour de joueur "+p.getColor()+" ("+this.board.intToColor(p.getColor())+") ****");
        //scan player's answer and store it
        int[] coord = new int[4];

        // CHECK INFO BEFORE PLAYING?
        checkInfo(coord);

        // ANSWER
        // If answer not valide, repeat
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez les coordonnées de la case: ");               //Browse each value of the current line
            coord[0] = sc.nextInt();             //Browse each value of the current line
            coord[1] = sc.nextInt();
        }while(!acceptedAnswer(coord[0], coord[1]));

        //Color case with player's color
        this.board.colorerCase(coord[0],coord[1],p.getColor());
        this.board.neighborUnion(coord[0],coord[1]);
        this.board.printValueBoard();
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
        int player1RootId = -1;
        int player2FinalSet = 0;
        int player2RootId = -1;
        int tmp;

        //Check which is the biggest set size for each player
        for(int i=0; i<this.board.getN(); ++i){
            for(int j=0; j<this.board.getN(); ++j){
                tmp = this.board.getSetSize(i,j);
                if(Board.getColor(i,j)==Board.P1_COLOR){
                    if(player1FinalSet < tmp){
                        player1FinalSet = tmp;
                        player1RootId = this.board.getScoring().getParentIdSet()[i][j].getId();
                    }
                }
                else{
                    if(player2FinalSet < tmp){
                        player2FinalSet = tmp;
                        player2RootId = this.board.getScoring().getParentIdSet()[i][j].getId();
                    }

                }
            }
        }
        System.out.println("Plus grand nombre de cases connectées pour Joueur 1: "+player1FinalSet);
        System.out.println("Plus grand nombre de cases connectées pour Joueur 2: "+player2FinalSet);

        //FinalScore
        int scoreP1 = 0;
        int scoreP2 = 0;

        for(int i=0; i<this.board.getN(); ++i){
            for(int j=0; j<this.board.getN(); ++j){
                if(Board.getColor(i,j)==Board.P1_COLOR){
                    if(player1RootId == this.board.getScoring().getParentIdSet()[i][j].getId()){
                       scoreP1 += Board.getValue(i,j);
                    }
                }
                else{
                    if(player2RootId == this.board.getScoring().getParentIdSet()[i][j].getId()){
                        scoreP2 += Board.getValue(i,j);
                    }
                }
            }
        }
        player1.setScore(scoreP1);
        player2.setScore(scoreP2);
        System.out.println("Score de Joueur 1: "+scoreP1);
        System.out.println("Score de Joueur 2: "+scoreP2);

        if(scoreP1 > scoreP2){
            System.out.println("Joueur 1 Gagnant!");
        }else if(scoreP2 > scoreP1){
            System.out.println("Joueur 2 Gagnant!");
        }else{
            System.out.println("Egalité!");
        }
    }


    /**
     * Check whether the player wants info on composantes or continue playing
     * @param coord
     */
    private void checkInfo(int[] coord) {
        int info = 0;
        do{
            Scanner scInfo = new Scanner(System.in);
            System.out.println("Voulez vous afficher une composante? (taper 1)");
            System.out.println("Voulez vous afficher le score d'une composante? (taper 2)");
            System.out.println("Voulez vous savoir si une case relie deux composantes? (taper 3)");
            System.out.println("Continuer? (taper 0)");
            if(scInfo.hasNextInt()){
                info = scInfo.nextInt();
                switch(info){
                    case 0:
                        break;
                    case 1:
                        askUserAnswer(coord);
                        afficherComposante(coord[0], coord[1]);
                        break;
                    case 2:
                        askUserAnswer(coord);
                        afficherScore(coord[0], coord[1], Board.getColor(coord[0], coord[1]));
                        break;
                    case 3:
                        askUserAnswer(coord);
                        relierComposante(coord[0], coord[1]);
                        break;
                    default:
                        System.out.println("Réponse non valide.");
                }
            }else{
                System.out.println("Réponse non valide.");
            }
        }while(info != 0);
    }

    /**
     * Asks the player to give a case to play on
     * @param coord
     */
    private void askUserAnswer(int[] coord) {
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez les coordonnées de la case: ");               //Browse each value of the current line
            coord[0] = sc.nextInt();             //Browse each value of the current line
            coord[1] = sc.nextInt();
        } while (!acceptedInfoAnswer(coord[0], coord[1]));
    }


    /**
     * Count remaining non-colored cases
     * @return remaining cases
     */
    public int getWhite() {
        int white = 0;
        for(int i=0;i<this.board.getN(); ++i){
            for(int j=0;j<this.board.getN(); ++j){
                if(Board.getColor(i, j)==Board.EMPTY_COLOR){
                    white ++;
                }
            }
        }
        System.out.println("Cases restantes vide: "+white);  // print infos: To be removed
        return white;
    }

    /*
     * 2) PRINT METHOD
     */

    /**
     * Print the list of cells in the same group as the cell in parameter.
     * @param x
     * @param y
     */
    public void afficherComposante(int x, int y){
        //int[][] list = getGroupOf(x,y); //pas compris comment utiliser
        int color = Board.getColor(x,y);
        System.out.println("Couleur de la composante: "+this.board.intToColor(color));
        System.out.println("Composante: ");
        if(color == Board.EMPTY_COLOR) {
            System.out.println("("+x+","+y+")");
        }
        else{
            int tmp = this.board.getScoring().getParentIdSet()[x][y].getId();
            for (int i = 0; i<this.board.getN(); ++i) {
                for (int j = 0; j<this.board.getN(); ++j) {
                    if (Board.getColor(i, j) == color) {
                        if (tmp == this.board.getScoring().getParentIdSet()[i][j].getId()) {
                            System.out.printf("("+i+","+j+") ");
                        }
                    }
                }
                System.out.println(" ");
            }
        }
    }

    /**
     *  Print the current score of a composante
     * @param x
     * @param y
     * @param color
     */
    private void afficherScore(int x, int y, int color){
        int score = 0;
        if(color == Board.EMPTY_COLOR){
            score = Board.getValue(x, y);
        }else {
            int tmp = this.board.getScoring().getParentIdSet()[x][y].getId();
            for (int i = 0; i < this.board.getN(); ++i) {
                for (int j = 0; j < this.board.getN(); ++j) {
                    if (Board.getColor(i, j) == color) {
                        if (tmp == this.board.getScoring().getParentIdSet()[i][j].getId()) {
                            score += Board.getValue(i, j);
                        }
                    }
                }
            }
            // MAJ score joueur
            if(color == Board.P1_COLOR){
                if(player1.getScore() < score){
                    player1.setScore(score);
                }
            }else{
                if(player2.getScore() < score){
                    player2.setScore(score);
                }
            }
        }
        System.out.printf("Score de la composante de ("+x+","+y+"): "+score+". ");
        if(color == 0){
            System.out.println("Cette case est libre");
        }else{
            System.out.println("Cette case est colorée par le joueur n°"+color+".");
        }
    }

    /*
     *  3) VERIFICATIONS METHODS
     */

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
            if (Board.getColor(x, y) != Board.EMPTY_COLOR) {
                System.out.println("Cette case est déjà colorée, veuillez en choisir une autre.");
                return false;
            }
        }
        return true;
    }

    /**
     *  Check whether the coordinates choosen by user are corrects
     *  Version for info
     * @param x
     * @param y
     * @return
     */
    public boolean acceptedInfoAnswer(int x, int y){
        if(x >= this.board.getN() || x < 0 || y >= this.board.getN() || y < 0 ){
            System.out.println("Cette case est hors du plateau.");
            return false;
        }
        return true;
    }

    /**
     *Check  if a case links two composantes and whose composantes they belong to
     * @param x
     * @param y
     * @return
     */
    private void relierComposante(int x, int y){
        if(Board.getColor(x,y)==Board.EMPTY_COLOR){
            int i = 0;
            int j = 0;
            if(x+1<this.board.getN()){
                if(Board.getColor(x+1,y) == Board.P1_COLOR){
                    i++;
                }else if(Board.getColor(x+1,y) == Board.P2_COLOR){
                    j++;
                }
            }
            if(y+1<this.board.getN()){
                if(Board.getColor(x,y+1) == Board.P1_COLOR){
                    i++;
                }else if(Board.getColor(x,y+1) == Board.P2_COLOR){
                    j++;
                }
            }
            if(x-1>=0){
                if(Board.getColor(x-1,y) == Board.P1_COLOR){
                    i++;
                }else if(Board.getColor(x-1,y) == Board.P2_COLOR){
                    j++;
                }
            }
            if(y-1>=0){
                if(Board.getColor(x,y-1) == Board.P1_COLOR){
                    i++;
                }else if(Board.getColor(x,y-1) == Board.P2_COLOR){
                    j++;
                }
            }
            if(i >=2){
                System.out.println("("+x+","+y+") relie deux composantes appartenant au joueur 1.");
            }
            if(j >=2){
                System.out.println("("+x+","+y+") relie deux composantes appartenant au joueur 2.");
            }
            if(i<2 && j<2){
                System.out.println("("+x+","+y+") ne relie aucunes composantes de même couleurs.");

            }
        }else{
            System.out.println("("+x+","+y+") est déja colorée.");

        }
    }

}
