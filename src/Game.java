public class Game {

    private Player player1;
    private Player player2;
    private Board board;
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

}

