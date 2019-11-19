import structure.AscendingNode;
import structure.UnionFind;

import java.util.ArrayList;

public class Player {
    /** The color representing this player on the board*/
    private int color;
    /** current largest score among Union control by this Player*/
    private int score;

    /** Groups of cells controlled by this Player*/
    private ArrayList<UnionFind> groups;



    public int[][] getGroupsOf(int x, int y){
        //TODO
        AscendingNode a = new AscendingNode()
        AscendingNode parent;
        for(UnionFind u: groups){
            if(u.find() != null){

            }
        }

    }
}
