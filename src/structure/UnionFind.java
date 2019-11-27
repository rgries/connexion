package structure;


public class UnionFind {

    private AscendingNode[][] parentIdSet;

    /**
     *  Constructor
     * @param parentIdSet
     */
    public UnionFind(AscendingNode[][] parentIdSet){
        this.parentIdSet = parentIdSet;
    }

    /**
     * Find the root of the current node
     * @param node
     * @return root
     */
    public AscendingNode find(AscendingNode node,int[] i){
        if(node.getParent() == node){
            return node;
        }
        else{
            node.setParent(find(node.getParent(), search(node.getParent())));
            this.parentIdSet[i[0]][i[1]] = node.getParent();
            return node.getParent();
        }
    }

    private int[] search(AscendingNode node) {
        for (int i = 0; i < this.parentIdSet.length; i++){
            for (int j = 0; j < this.parentIdSet.length; j++) {
                if (this.parentIdSet[i][j] == node) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[] {-1,-1};
    }

    /**
     * Implementation of union using path compression
     * @param node1
     * @param node2
     */
    public AscendingNode union(AscendingNode node1, AscendingNode node2){
        AscendingNode parent1 = find(node1,search(node1));
        AscendingNode parent2 = find(node2,search(node2));

        if(parent1 != parent2){
            if(getNodeAmount(parent1) < getNodeAmount(parent2)){
                parent1.setParent(parent2);
                parentIdSet[parent1.getX()][parent1.getY()] = parent2;
                return parent2;
            }
            else{
                parent2.setParent(parent1);
                parentIdSet[parent2.getX()][parent2.getY()] = parent1;
                return parent1;
            }
        }
        else{
            return parent1;
        }
    }

    /**
     *  Get the amount of node linked to one root
     * @param root
     * @return amount of children of a root
     */
    private int getNodeAmount(AscendingNode root){
        int accu = 0;
        for(int i = 0; i <this.parentIdSet.length; i++){
            for(int j = 0; j <this.parentIdSet.length; j++){
                if(root.getId() == this.parentIdSet[i][j].getRoot().getId()){
                    accu++;
                }
            }
        }
        return accu;
    }

    /**
     * get the parentIdSet of unionFind
     * @return parentIdSet
     */
    public AscendingNode[][] getParentIdSet(){
        return parentIdSet;
    }

}
