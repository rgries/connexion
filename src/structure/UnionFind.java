package structure;


public class UnionFind {

    private AscendingNode[] idSet;

    /**
     *  Constructor
     * @param idSet
     */
    public UnionFind(AscendingNode[] idSet){
        this.idSet = idSet;
    }

    /**
     * Find the root of the current node
     * @param node
     * @return root
     */
    public AscendingNode find(AscendingNode node){

        if(node.getParent() == null){
            return node;
        }
        else{
            node.setParent(find(node.getParent()));
            return node.getParent();
        }
    }

    /**
     * Naive implementation of union (based on previous TD)
     * @param node1
     * @param node2
     */
    public AscendingNode union(AscendingNode node1, AscendingNode node2){
        AscendingNode parent1 = find(node1);
        AscendingNode parent2 = find(node2);

        if(parent1 != parent2){
            if(getNodeAmount(parent1) < getNodeAmount(parent2)){
                parent1.setParent(parent2);
                idSet[parent1.getValue()-1] = parent2;
                return parent2;
            }
            else{
                parent2.setParent(parent1);
                idSet[parent2.getValue()-1] = parent1;
                return parent1;
            }
        }
        else{
            return parent1;
        }
    }
    private int getNodeAmount(AscendingNode root){
        int accu = 0;
        for(int i=0; i <this.idSet.length; i++){
            if(root.getValue() == this.idSet[i].getRoot().getValue()){
                accu++;
            }
        }
        return accu;
    }

}
