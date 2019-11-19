package structure;


public class AscendingNode {
    private int value;
    private AscendingNode parent;


    /**
     * Constructor
     * @param value
     * @param parent

     */
    public AscendingNode(Integer value, AscendingNode parent){
        this.value = value;
        this.parent = parent;

    }



    /**
     * GETTERS
     */

    /**
     * get value of current node
     * @return value
     */
    public int getValue(){
        return this.value;
    }

    /**
     * get parent of current node
     * @return parent
     */
    public AscendingNode getParent(){
        if(!this.hasParent()){
            this.setParent(this);
            System.out.println(this.getValue()+" -- "+this.getParent().getValue());
        }
        return this.parent;
    }

    /**
     *  Get the root of the current node
     * @return root
     */
    public AscendingNode getRoot(){
        if(this.getParent() == null){
            return this;
        }
        else{
            return this.getParent().getRoot();
        }

    }

    /**
     * SETTERS
     */

    /**
     *  set a new value
     * @param value
     */
    protected void setValue(int value){
        this.value = value;
    }

    /**
     * set a new parent
     * @param parent
     */
    protected void setParent(AscendingNode parent){
        this.parent = parent;
    }


    /**
     * VERIFICATIONS
     */

    /**
     *  Check whether the current node is the root
     * @return
     */
    protected boolean isRoot(){
        return !hasParent();
    }

    /**
     *  Check whether the current node has a parent
     * @return
     */
     protected boolean hasParent(){
        return this.parent != this;
     }
}
