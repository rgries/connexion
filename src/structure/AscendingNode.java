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

    /**
     *  Get the root of the current node
     * @return root
     */
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
     *  set a new value
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
        return this.parent != null;
     }
}
