package structure;

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
        if(node.getParent() == node){
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
    private void union(AscendingNode node1, AscendingNode node2){
        AscendingNode parent1 = find(node1);
        AscendingNode parent2 = find(node2);

        if(parent1 != parent2){
            if(parent1.getValue() < parent2.getValue()){
                parent1.setParent(parent2);
            }
            else{
                parent2.setParent(parent1);
            }
        }
        else{
            parent1.setParent(parent2);
            parent2.setValue(parent2.getValue()+1);
        }
    }

}
