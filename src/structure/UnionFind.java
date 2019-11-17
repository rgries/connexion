package structure;

import com.sun.tools.example.debug.expr.ASCII_UCodeESC_CharStream;

import java.util.ArrayList;
import java.util.Collection;


public class UnionFind {

    private Collection<AscendingNode> idSet;

    /**
     *  Constructor
     * @param idSet
     */
    public UnionFind(ArrayList<AscendingNode> idSet){
        this.idSet = idSet;
    }

    /**
     * Find the root of the current node
     * @param node
     * @return root
     */
    private AscendingNode find(AscendingNode node){
        if(node.getParent() == null){
            return node;
        }
        else{
            return find(node.getParent());
        }
    }

    /**
     * Naive implementation of union (based on previous TD)
     * @param node1
     * @param node2
     */
    private AscendingNode union(AscendingNode node1, AscendingNode node2){
        AscendingNode parent1 = find(node1);
        AscendingNode parent2 = find(node2);

        if(parent1 != parent2){
            if(getNodeAmount(parent1) < getNodeAmount(parent2)){
                parent1.setParent(parent2);
                return parent2;
            }
            else{
                parent2.setParent(parent1);
                return parent1;
            }
        }
        else{
            return parent1;
        }
    }
    private int getNodeAmount(AscendingNode root){
        int accu = 1;
        for(AscendingNode n :idSet){
            if(n.getRoot() == root){
                accu++;
            }
        }
        return accu;
    }

}
