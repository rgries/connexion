package structure;


import java.util.Collection;
import java.util.List;

public class AscendingNode {
    private Integer value;
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
    protected Integer getValue(){
        return this.value;
    }

    /**
     * get parent of current node
     * @return parent
     */
    protected AscendingNode getParent(){
        if(this.parent == null){
            throw new NullPointerException("This unionFind.node has no parent.");
        }
        return this.parent;
    }


    protected AscendingNode getRoot(){
        if(!this.hasParent()){
            return this;
        }
        else{
            return getParent().getRoot();
        }

    }

    /**
     * SETTERS
     */

    /**
     *  set a new value (useless?)
     * @param value
     */
    protected void setValue(Integer value){
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

    protected boolean isRoot(){
        return !hasParent();
    }

     private boolean hasParent(){
        return this.parent != null;
     }
}
