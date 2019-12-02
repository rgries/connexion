import structure.AscendingNode;
        import structure.UnionFind;

        import java.util.ArrayList;

public class Player {
    /** The color representing this player on the board*/
    private int color;
    /** current largest score among Union control by this Player*/
    private int score;

    /** List the 'Groups' of cells controlled by this Player*/
    private ArrayList<UnionFind> groups;

    /**
     * CTOR
     * @param color
     */
    public Player(int color){
        this.color = color;
        score = 0;
    }

    public int getColor(){
        return this.color;
    }


    public void setScore(int s){
        score = s;
    }



    /**
     * Get the list of cells in the same group as the cell (x,y)
     *
     * @param x
     * @param y
     * @return an array int[2][n] containing the list of cells in the same group as (x,y);
     * such as int[0][n] is the 'x' of these cells, and int[1][n] the 'y' of these cells.
     */
    public int[][] getGroupOf(int x, int y){
        //TODO
        //AscendingNode a = new AscendingNode();
        AscendingNode parent;
        int[][] res = new int[1][1];

        return res;

    }
}
