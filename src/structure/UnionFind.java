package structure;

public class UnionFind {

    private AscendingNode[][] idSet;

    /**
     *  Constructor
     * @param idSet
     */
    public UnionFind(AscendingNode[][] idSet){
        this.idSet = idSet;
    }

    /**
     * Find the root of the current node
     * @param node
     * @return root
     */



    public AscendingNode find(AscendingNode node, int i, int j){
        if(node.getParent() == node){
            return node;
        }
        else{
            node.setParent(find(node.getParent(),searchX(node.getParent()), searchY(node.getParent())));
            this.idSet[i][j] = node.getParent();
            return node.getParent();
        }
    }

    private int searchX(AscendingNode node){
        for(int i=0; i<this.idSet.length; i++)
            for(int j=0; j<this.idSet.length; j++)
                if(this.idSet[i][j] == node){
                    return i;
                }
        return -1;
    }
    private int searchY(AscendingNode node){
        for(int i=0; i<this.idSet.length; i++)
            for(int j=0; j<this.idSet.length; j++)
                if(this.idSet[i][j] == node){
                    return j;
                }
        return -1;
    }

    /**
     * Find the root of a node known by its coordinate
     * @param x
     * @param y
     * @return root
     */
    public AscendingNode find(int x, int y){

    }

    /**
     * Naive implementation of union (based on previous TD)
     * @param node1
     * @param node2
     */
    public AscendingNode union(AscendingNode node1, AscendingNode node2){
        AscendingNode parent1 = find(node1,searchX(node1),searchY(node1));
        AscendingNode parent2 = find(node2,searchX(node2),searchY(node2));

        if(parent1 != parent2){
            if(getNodeAmount(parent1) < getNodeAmount(parent2)){
                parent1.setParent(parent2);
                idSet[parent1.getX()][parent1.getY()] = parent2;
                return parent2;
            }
            else{
                parent2.setParent(parent1);
                System.out.println(parent2.getX());
                System.out.println(parent2.getY());
                idSet[parent2.getX()][parent2.getY()] = parent1;
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
            for(int j=0; j <this.idSet.length; j++){
                if(root.getValue() == this.idSet[i][j].getRoot().getValue()){
                    accu++;
                }
            }
        }
        return accu;
    }

}
